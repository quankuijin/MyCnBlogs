package com.example.kuijin.mycnblogs.common.config;

/**
 * Created by kuijin on 2016/9/17.
 */
public interface IConfig {
    static final String ITEM_OVERVIEW_MODEL_PAGE_SIZE = "ItemOverviewModelPageSize";
    static final String ITEM_OVERVIEW_MODEL_CACHE_SIZE = "ItemOverviewModelCacheSize";
    static final String ITEM_OVERVIEW_MODEL_HEADER_IMAGE_CACHE_SIZE = "ItemOverviewModelHeaderImageCacheSize";
    static final String DISK_CACHE_SIZE = "DiskCacheSize";

    static final int ITEM_OVERVIEW_MODEL_PAGE_SIZE_DEFAULT = 30;
    static final int ITEM_OVERVIEW_MODEL_CACHE_SIZE_DEFAULT = 10;
    static final int ITEM_OVERVIEW_MODEL_HEADER_IMAGE_CACHE_SIZE_DEFAULT = 10;
    static final int DISK_CACHE_SIZE_DEFAULT = 10;


    int getItemOverviewModelPageSize();
    void setItemOverviewModelPageSize(int pageSize);

    int getItemOverviewModelCacheSize();
    void setItemOverviewModelCacheSize(int cacheSize);

    int getItemOverviewModelHeaderImageCacheSize();
    void setItemOverviewModelHeaderImageCacheSize(int cacheSize);

    int getDiskCacheSize();
    void setDiskCacheSize(int cacheSize);

    boolean getCanUseMobileNetwork();
    void setCanUseMobileNetwork(boolean canUseMobileNetwork);

}
