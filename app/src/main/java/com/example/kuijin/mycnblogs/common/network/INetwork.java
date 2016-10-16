package com.example.kuijin.mycnblogs.common.network;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.example.kuijin.mycnblogs.model.ItemOverviewModel;

import java.util.List;

/**
 * Created by kuijin on 2016/9/19.
 */
public interface INetwork {

    void getItemOverviewModels(String url,
                              @NonNull ResponseItemOverviewModelListener listener,
                              @NonNull ResponseErrorListener errorListener);
    void getImage(String url, int maxWidth, int maxHeight, Bitmap.Config config,
                  @NonNull ResponseImageListenrer listenrer,
                  @NonNull ResponseErrorListener errorListener);

    interface ResponseItemOverviewModelListener {
        void onResponse(List<ItemOverviewModel> list);
    }

    interface ResponseErrorListener {
        void onErrorResponse(String error);
    }

    interface ResponseImageListenrer {
        void onResponse(Bitmap bitmap);
    }

}
