package com.example.kuijin.mycnblogs.common.cache;

import android.graphics.Bitmap;

import com.example.kuijin.mycnblogs.common.config.ConfigManager;
import com.example.kuijin.mycnblogs.model.IItemOverviewModel;
import com.example.kuijin.mycnblogs.model.ItemOverviewModel;

import java.util.List;

/**
 * Created by kuijin on 2016/9/15.
 */
public class CacheManager {
    private static OverviewModuleCacheManager overviewModuleCacheManager = OverviewModuleCacheManager.getInstance();
    private static ImageCacheManager imageCacheManager = ImageCacheManager.getInstance();

    public static  List<IItemOverviewModel> getItemOverviewModels(String url) {
        return overviewModuleCacheManager.getItemOverviewModels(url);
    }

    public static  List<IItemOverviewModel> putItemOverviewModels(String url, List<IItemOverviewModel> list) {
        return overviewModuleCacheManager.putItemOverviewModels(url, list);
    }

    public static  void removeItemOverviewModels(String url) {
        overviewModuleCacheManager.removeItemOverviewModels(url);
    }

    public static  void clearItemOverviewModelsCache(String url, List<IItemOverviewModel> list) {
        //清除Cache中原有的信息，并将list中的信息添加到Cache中
        overviewModuleCacheManager.clearItemOverviewModelsCache(url, list);
    }

    public static  Bitmap getImage(String url) {
        return imageCacheManager.get(url);
    }

    public static  void putImage(String url, Bitmap bitmap) {
        imageCacheManager.put(url, bitmap);
    }


    public static  void removeImage(String url) {
        imageCacheManager.remove(url);
    }

    public static  void clearImageCache() {
        imageCacheManager.clear();
    }


}
