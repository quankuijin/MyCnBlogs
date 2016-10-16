package com.example.kuijin.mycnblogs.presenter.page.itemoverview;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.kuijin.mycnblogs.R;
import com.example.kuijin.mycnblogs.common.CnBlogsLog;
import com.example.kuijin.mycnblogs.common.cache.CacheManager;
import com.example.kuijin.mycnblogs.common.disk.DiskManager;
import com.example.kuijin.mycnblogs.common.language.MessageInfo;
import com.example.kuijin.mycnblogs.common.network.INetwork;
import com.example.kuijin.mycnblogs.common.network.NetworkManager;
import com.example.kuijin.mycnblogs.model.ItemOverviewModel;

import java.util.List;

/**
 * Created by kuijin on 2016/9/15.
 */
public class ItemOverviewPresenter implements IItemOverviewPresenter {
    private static ItemOverviewPresenter presenter = null;

    private Context context;
    private ItemOverviewPresenter(Context context) {
        this.context = context;
    }

    public static ItemOverviewPresenter getInstance(Context context) {
        if (null == presenter) {
            presenter = new ItemOverviewPresenter(context);
        }

        return presenter;
    }

    @Override
    public void setImage(ImageView imageView, String url, @DrawableRes int defaultImageRes, @DrawableRes int errorImageRes) {
        imageView.setImageResource(defaultImageRes);

        Bitmap bitmap = getImageFromCache(url);
        if (null != bitmap) {
            imageView.setImageBitmap(bitmap);
        }

        try {
            getImageFromNetwork(imageView, url, errorImageRes);
        } catch (NetworkErrorException e) {
            e.printStackTrace();
            CnBlogsLog.write(e);
        }
    }

    private Bitmap getImageFromCache(String url) {
        if (TextUtils.isEmpty(url)) {
            CnBlogsLog.write("ItemOverviewPresenter", "getImage", "url is null or empty",
                    CnBlogsLog.LEVEL_ERROR);
            return null;
        }

        Bitmap bitmap = CacheManager.getImage(url);
        if (null != bitmap) {
            return bitmap;
        }

        return null;
    }

    private void getImageFromNetwork(ImageView imageView, String url, @DrawableRes int errorImageRes) throws NetworkErrorException {
        if (TextUtils.isEmpty(url)) {
            CnBlogsLog.write("ItemOverviewPresenter", "getImage", "url is null or empty",
                    CnBlogsLog.LEVEL_ERROR);
        }

        if (!NetworkManager.canAccessWeb()) {
            throw new NetworkErrorException(MessageInfo.ItemOverviewPresenter_CannotAccessNetwork);
        }

        NetworkManager.getImage(url,
                (int) context.getResources().getDimension(R.dimen.item_writerinfo_item_image_width),
                (int) context.getResources().getDimension(R.dimen.item_writerinfo_item_image_height),
                Bitmap.Config.RGB_565,
                new ImageListener(imageView, url),
                new ImageErrorListenre());
    }


    class ImageListener implements INetwork.ResponseImageListenrer {

        private ImageView imageView;
        private String url;

        public ImageListener(ImageView imageView, String url) {
            this.imageView = imageView;
            this.url = url;
        }

        @Override
        public void onResponse(Bitmap bitmap) {
            if (null != bitmap) {
                CacheManager.putImage(url, bitmap);
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    class ImageErrorListenre implements INetwork.ResponseErrorListener {

        @Override
        public void onErrorResponse(String error) {
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
        }
    }

}
