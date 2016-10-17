package com.example.kuijin.mycnblogs.common.cache;

import android.text.TextUtils;

import com.example.kuijin.mycnblogs.common.CnBlogsLog;
import com.example.kuijin.mycnblogs.common.config.ConfigManager;
import com.example.kuijin.mycnblogs.common.disk.DiskManager;
import com.example.kuijin.mycnblogs.model.IItemOverviewModel;
import com.example.kuijin.mycnblogs.model.ItemOverviewModel;

import java.io.IOException;
import java.util.List;

/**
 * Created by kuijin on 2016/9/17.
 */
public class OverviewModuleCacheManager {

    private OverviewModuleCache overviewModuleCache = null;

    private int overviewModuleCacheSize = 0;
    private int overviewListCacheSize = 0;

    private OverviewModuleCacheManager() {
        overviewModuleCacheSize = ConfigManager.getItemOverviewModelPageSize() * 1024 * 1024;
        overviewListCacheSize = overviewModuleCacheSize;
        overviewModuleCache = new OverviewModuleCache(overviewModuleCacheSize);
    }

    public static OverviewModuleCacheManager getInstance() {
        return new OverviewModuleCacheManager();
    }

    public List<IItemOverviewModel> getItemOverviewModels(String url) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }

        OverviewListCache overviewListCache = overviewModuleCache.get(url);
        if (null == overviewListCache) {
            return null;
        }

        int index = overviewListCache.getMaxIndex();
        List<IItemOverviewModel> listResult = null;
        for (int i = 0; i < (index + 1); i ++) {
            List<IItemOverviewModel> list = overviewListCache.get(i);
            if (null != list) {
                if (null == listResult) {
                    listResult = list;
                } else {
                    listResult.addAll(list);
                }
            }
        }

        return listResult;
    }

    public List<IItemOverviewModel> putItemOverviewModels(String url, List<IItemOverviewModel> list) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }

        try {
            DiskManager.setItemOverviewListToDisk(url, list);
        } catch (IOException e) {
            CnBlogsLog.write(e);
            e.printStackTrace();
        }

        OverviewListCache overviewListCache = overviewModuleCache.get(url);
        if (null == overviewListCache) {
            overviewListCache = new OverviewListCache(overviewListCacheSize);
            overviewListCache.addItemIds(list);
            overviewListCache.put(overviewListCache.getKey(0), list);
            overviewModuleCache.put(url, overviewListCache);

            return overviewListCache.getAddedItemOverviewModels(list);
        }

        overviewListCache.addItemIds(list);
        overviewListCache.put(overviewListCache.getKey(0), list);
        return overviewListCache.getAddedItemOverviewModels(list);
    }

    public void removeItemOverviewModels(String url) {
        if (!TextUtils.isEmpty(url) && null != overviewModuleCache.get(url)) {
            overviewModuleCache.remove(url);
        }
    }

    public void clearItemOverviewModelsCache(String url, List<IItemOverviewModel> list) {
        //清除Cache中原有的信息，并将list中的信息添加到Cache中
        removeItemOverviewModels(url);
        putItemOverviewModels(url, list);
    }



}

