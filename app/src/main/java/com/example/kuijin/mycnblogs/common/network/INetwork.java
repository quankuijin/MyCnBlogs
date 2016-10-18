package com.example.kuijin.mycnblogs.common.network;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.example.kuijin.mycnblogs.common.xml.IItemOverviewModelXmlPullParser;
import com.example.kuijin.mycnblogs.model.IItemOverviewModel;
import com.example.kuijin.mycnblogs.model.ItemOverviewModel;

import java.util.List;

/**
 * Created by kuijin on 2016/9/19.
 */
public interface INetwork {

    void getItemOverviewModels(String url,
                               @NonNull ResponseItemOverviewModelListener listener,
                               @NonNull ResponseErrorListener errorListener,
                               @NonNull IItemOverviewModelXmlPullParser xmlPullParser);

    void getImage(String url, int maxWidth, int maxHeight, Bitmap.Config config,
                  @NonNull ResponseImageListenrer listenrer,
                  @NonNull ResponseErrorListener errorListener);

    interface ResponseItemOverviewModelListener {
        void onResponse(List<IItemOverviewModel> list);
    }

    interface ResponseErrorListener {
        void onErrorResponse(String error);
    }

    interface ResponseImageListenrer {
        void onResponse(Bitmap bitmap);
    }

}
