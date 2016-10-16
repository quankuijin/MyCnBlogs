package com.example.kuijin.mycnblogs.common;

import android.app.Application;

/**
 * Created by kuijin on 2016/9/17.
 */
public class CnBlogsApplication extends Application {

    private static CnBlogsApplication application;

    public static CnBlogsApplication getApplication() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }
}
