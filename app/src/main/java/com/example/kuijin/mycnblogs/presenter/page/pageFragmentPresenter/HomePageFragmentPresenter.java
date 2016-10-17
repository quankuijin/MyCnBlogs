package com.example.kuijin.mycnblogs.presenter.page.pageFragmentPresenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.kuijin.mycnblogs.R;
import com.example.kuijin.mycnblogs.common.CnBlogsLog;
import com.example.kuijin.mycnblogs.common.cache.CacheManager;
import com.example.kuijin.mycnblogs.common.config.ConfigManager;
import com.example.kuijin.mycnblogs.common.disk.DiskManager;
import com.example.kuijin.mycnblogs.common.language.MessageInfo;
import com.example.kuijin.mycnblogs.common.network.INetwork;
import com.example.kuijin.mycnblogs.common.network.NetworkManager;
import com.example.kuijin.mycnblogs.model.IItemOverviewModel;
import com.example.kuijin.mycnblogs.presenter.page.recyclerview.RecyclerViewAdapter;
import com.example.kuijin.mycnblogs.view.page.AbsPageFragment;

import java.io.IOException;
import java.util.List;

/**
 * Created by kuijin on 2016/10/17.
 */
public class HomePageFragmentPresenter implements IPageFragmentPresenter {

    private Context context;
    private AbsPageFragment fragment;
    private RecyclerViewAdapter adapter;

    private int lastPageIndex;
    //    private int pageIndex;
    private int pageSize;

//    private boolean refreshed = false;
//    private int refreshedPageIndex;
//    private List<ItemOverviewModel> listRefreshed = null;

    public HomePageFragmentPresenter(Context context, AbsPageFragment fragment) {
        this.context = context;
        this.fragment = fragment;
        lastPageIndex = 1;
//        pageIndex = -1;
        pageSize = ConfigManager.getItemOverviewModelPageSize();
//        refreshedPageIndex = 0;
//        refreshed = false;
    }

    @Override
    public void setAdapter(OnItemClickListener listener) {
        final RecyclerView recyclerView = fragment.getRecyclerView();

//        if (null == recyclerView) {
//            return;
//        }
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(context));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(new RecyclerViewItemDecoration());


        List<IItemOverviewModel> list = null;
        try {
            list = getItemOverviewModelsFromDisk();
        } catch (IOException e) {
            CnBlogsLog.write(e);
            e.printStackTrace();
            Toast.makeText(context, "Read file failed!", Toast.LENGTH_SHORT).show();
            return;
        }

        adapter = new RecyclerViewAdapter(context, list, listener);
        recyclerView.setAdapter(adapter);

        INetwork.ResponseItemOverviewModelListener responseItemOverviewModelListener = new INetwork.ResponseItemOverviewModelListener() {
            @Override
            public void onResponse(List<IItemOverviewModel> list) {
                if (null == list) {
                    return;
                }

//                try {
//                    DiskManager.setItemOverviewListToDisk(fragment.getUrl(), list);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    CnBlogsLog.write(e);
//                }

                List<IItemOverviewModel> listCache = getItemOverviewModelsFromCache(list);

                if (null == listCache || 0 > listCache.size()) {
                    Toast.makeText(context,
                            context.getResources().getString(R.string.not_get_more_item_overview_model),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                adapter.addItemsToLast(listCache);
            }
        };

        if (null == list) {
            //没有获取到本地信息，则从网络获取信息
            getItemOverviewModels(lastPageIndex, responseItemOverviewModelListener);
        }
        lastPageIndex++;
    }

    @Override
    public void refresh() {

        INetwork.ResponseItemOverviewModelListener responseItemOverviewModelListener = new INetwork.ResponseItemOverviewModelListener() {
            @Override
            public void onResponse(List<IItemOverviewModel> list) {
                List<IItemOverviewModel> listCache = getItemOverviewModelsFromCache(list);

                if (null == listCache || 0 > listCache.size()) {
                    Toast.makeText(context,
                            context.getResources().getString(R.string.not_get_more_item_overview_model),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pageSize == listCache.size()) {
                    lastPageIndex = 1;
                    adapter.clearAndAddItems(listCache);
                } else {
                    adapter.addItemsToFirst(listCache);
                }
            }
        };

        getItemOverviewModels(0, responseItemOverviewModelListener);
    }

    @Override
    public void more() {
        INetwork.ResponseItemOverviewModelListener responseItemOverviewModelListener = new INetwork.ResponseItemOverviewModelListener() {
            @Override
            public void onResponse(List<IItemOverviewModel> list) {
                List<IItemOverviewModel> listCache = getItemOverviewModelsFromCache(list);

                if (null == listCache || 0 > listCache.size()) {
                    Toast.makeText(context,
                            context.getResources().getString(R.string.not_get_more_item_overview_model),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                adapter.addItemsToLast(listCache);
            }
        };

        getItemOverviewModels(lastPageIndex, responseItemOverviewModelListener);

        lastPageIndex++;
    }


    private List<IItemOverviewModel> getItemOverviewModelsFromDisk() throws IOException {
        String url = fragment.getUrl();
        if (TextUtils.isEmpty(url)) {
            CnBlogsLog.write("PageFragmentPresenter", "getItemOverviewModels",
                    "url is null or empty!", CnBlogsLog.LEVEL_ERROR);
            return null;
        }

        List<IItemOverviewModel> list = DiskManager.getItemOverviewModels(url);
        return list;
    }

    private void getItemOverviewModels(int pageIndex,
                                       @NonNull INetwork.ResponseItemOverviewModelListener listenrer) {
        getItemOverviewModels(pageIndex, listenrer, null);
    }

    private void getItemOverviewModels(int pageIndex,
                                       @NonNull INetwork.ResponseItemOverviewModelListener listener,
                                       @Nullable INetwork.ResponseErrorListener errorListener) {

        String url = fragment.getUrl();
        if (null == errorListener) {
            errorListener = new ItemListErrorListener();
        }

        if (TextUtils.isEmpty(url)) {
            CnBlogsLog.write("PageFragmentPresenter", "getItemOverviewModels",
                    "url is null or empty!", CnBlogsLog.LEVEL_ERROR);
            errorListener.onErrorResponse("url is null or empty!");
        }

        if (-1 > pageIndex) {
            CnBlogsLog.write("PageFragmentPresenter", "getItemOverviewModels",
                    MessageInfo.ItemOverviewPresenter_PageIndexLessThan, CnBlogsLog.LEVEL_ERROR);
            errorListener.onErrorResponse(MessageInfo.ItemOverviewPresenter_PageIndexLessThan);
        }

        //不允许访问网络
        if (!NetworkManager.canAccessWeb()) {
            CnBlogsLog.write("PageFragmentPresenter", "getItemOverviewModels",
                    MessageInfo.ItemOverviewPresenter_CannotAccessNetwork, CnBlogsLog.LEVEL_ERROR);
            errorListener.onErrorResponse(MessageInfo.ItemOverviewPresenter_CannotAccessNetwork);
        }

        url = url + "/" + pageIndex + "/" + pageSize;
        //从网络获取信息
        NetworkManager.getItemOverviewModels(url, listener, errorListener);

    }

    private List<IItemOverviewModel> getItemOverviewModelsFromCache(List<IItemOverviewModel> list) {
        String url = fragment.getUrl();
        if (null == list || 0 > list.size()) {
            CnBlogsLog.write("ItemOverviewPresenter", "getItemOverviewModels",
                    MessageInfo.ItemOverviewPresenter_ItemInfosNotFound, CnBlogsLog.LEVEL_ERROR);
            return null;
        }

        return CacheManager.putItemOverviewModels(url, list);
    }

    private void clearCache(String url, List<IItemOverviewModel> list) throws IOException {
        if (TextUtils.isEmpty(url)) {
            CnBlogsLog.write("PageFragmentPresenter", "clearCache", "url is null or empty",
                    CnBlogsLog.LEVEL_ERROR);
            return;
        }

        CacheManager.clearItemOverviewModelsCache(url, list);
    }

    class ItemListErrorListener implements INetwork.ResponseErrorListener {

        @Override
        public void onErrorResponse(String error) {
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
        }
    }

}
