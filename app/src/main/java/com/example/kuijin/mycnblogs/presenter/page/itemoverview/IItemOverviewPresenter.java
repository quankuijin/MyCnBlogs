package com.example.kuijin.mycnblogs.presenter.page.itemoverview;

import android.accounts.NetworkErrorException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.example.kuijin.mycnblogs.model.ItemOverviewModel;

import java.util.List;

/**
 * Created by kuijin on 2016/9/15.
 */
public interface IItemOverviewPresenter {

    void setImage(ImageView view, String url, int defaultImageRes, int errorImageRes);
}
