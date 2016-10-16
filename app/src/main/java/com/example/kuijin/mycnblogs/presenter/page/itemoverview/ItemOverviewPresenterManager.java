package com.example.kuijin.mycnblogs.presenter.page.itemoverview;

import android.content.Context;

/**
 * Created by kuijin on 2016/9/15.
 */
public class ItemOverviewPresenterManager {

    public static IItemOverviewPresenter getItemOverviewPresenter(Context context) {
        return ItemOverviewPresenter.getInstance(context);
    }
}
