package com.example.kuijin.mycnblogs.presenter.main;

import android.content.Context;

/**
 * Created by kuijin on 2016/9/13.
 */
public class MainPresenterManager {

    public static MainPresenter getMainPresenter(Context context) {
        return new MainPresenter(context);
    }
}
