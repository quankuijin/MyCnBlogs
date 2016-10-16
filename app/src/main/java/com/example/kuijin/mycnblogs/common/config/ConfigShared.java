package com.example.kuijin.mycnblogs.common.config;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.kuijin.mycnblogs.common.CnBlogsApplication;
import com.example.kuijin.mycnblogs.common.CnBlogsLog;

/**
 * Created by kuijin on 2016/9/17.
 */
public class ConfigShared implements  IConfig {
    private static final String CAN_USE_MOBILE_NETWORK = "CanUseMobilNetwork";
    private static ConfigShared configShared = new ConfigShared();
    private static final String APP_SHARED = "APPSHARED";
    private SharedPreferences sharedPreferences = null;


    private ConfigShared() {
        Context appContext = CnBlogsApplication.getApplication();
        if (null == appContext) {
            CnBlogsLog.write("ConfigShared", "Constructor", "CnBlogsApplication is null", CnBlogsLog.LEVEL_ERROR);
        }
        sharedPreferences = appContext.getSharedPreferences(APP_SHARED, Context.MODE_PRIVATE);
    }

    public static ConfigShared getInstance() {
//        if ( null == configShared) {
//            configShared = new ConfigShared();
//        }
        return configShared;
    }


    @Override
    public int getItemOverviewModelPageSize() {
        return sharedPreferences.getInt(ITEM_OVERVIEW_MODEL_PAGE_SIZE, ITEM_OVERVIEW_MODEL_PAGE_SIZE_DEFAULT);
    }

    @Override
    public void setItemOverviewModelPageSize(int pageSize) {
        if (1 > pageSize) {
            CnBlogsLog.write("ConfigShared", "setItemOverviewModelPageSize",
                    "pageSize is less than 1!", CnBlogsLog.LEVEL_ERROR);
            return;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(ITEM_OVERVIEW_MODEL_PAGE_SIZE, pageSize);
        editor.commit();
    }

    @Override
    public int getItemOverviewModelCacheSize() {
        return sharedPreferences.getInt(ITEM_OVERVIEW_MODEL_CACHE_SIZE, ITEM_OVERVIEW_MODEL_CACHE_SIZE_DEFAULT);
    }

    @Override
    public void setItemOverviewModelCacheSize(int cacheSize) {
        if (0 > cacheSize) {
            CnBlogsLog.write("ConfigShared", "setItemOverviewModelCacheSize",
                    "Item model cacheSize is less than 0!", CnBlogsLog.LEVEL_ERROR);
            return;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(ITEM_OVERVIEW_MODEL_CACHE_SIZE, cacheSize);
        editor.commit();
    }

    @Override
    public int getItemOverviewModelHeaderImageCacheSize() {
        return sharedPreferences.getInt(ITEM_OVERVIEW_MODEL_HEADER_IMAGE_CACHE_SIZE, ITEM_OVERVIEW_MODEL_HEADER_IMAGE_CACHE_SIZE_DEFAULT);
    }

    @Override
    public void setItemOverviewModelHeaderImageCacheSize(int cacheSize) {
        if (0 > cacheSize) {
            CnBlogsLog.write("ConfigShared", "setItemOverviewModelHeaderImageCacheSize",
                    "Header image cacheSize is less than 0!",CnBlogsLog.LEVEL_ERROR);
            return;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(ITEM_OVERVIEW_MODEL_HEADER_IMAGE_CACHE_SIZE, cacheSize);
        editor.commit();
    }

    @Override
    public int getDiskCacheSize() {
        return sharedPreferences.getInt(DISK_CACHE_SIZE, DISK_CACHE_SIZE_DEFAULT);
    }

    @Override
    public void setDiskCacheSize(int cacheSize) {
        if (0 > cacheSize) {
            CnBlogsLog.write("ConfigShared", "setDiskCacheSize",
                    "Disk cache size is less than 0!", CnBlogsLog.LEVEL_ERROR);
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(DISK_CACHE_SIZE, cacheSize);
        editor.commit();
    }

    @Override
    public boolean getCanUseMobileNetwork() {
        return sharedPreferences.getBoolean(CAN_USE_MOBILE_NETWORK, true);
    }

    @Override
    public void setCanUseMobileNetwork(boolean canUseMobileNetwork) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(CAN_USE_MOBILE_NETWORK, canUseMobileNetwork);
        editor.commit();
    }
}
