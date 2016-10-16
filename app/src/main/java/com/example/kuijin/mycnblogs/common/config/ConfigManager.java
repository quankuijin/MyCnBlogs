package com.example.kuijin.mycnblogs.common.config;

/**
 * Created by kuijin on 2016/9/16.
 */
public class ConfigManager {

    private static IConfig getIConfig() {
        IConfig config = ConfigShared.getInstance();
        return config;
    }

    public static int getItemOverviewModelPageSize(){
        IConfig config = getIConfig();
        return config.getItemOverviewModelPageSize();
    }

    public static void setItemOverviewModelPageSize(int size) {
        IConfig config = getIConfig();
        config.setItemOverviewModelPageSize(size);
    }

    public static int getItemOverviewCacheSize() {
        IConfig config = getIConfig();
        return config.getItemOverviewModelCacheSize();
    }

    public static void setItemOverviewCacheSize(int size) {
        IConfig config = getIConfig();
        config.setItemOverviewModelCacheSize(size);
    }

    public static int getItemOverviewHeaderImageCacheSize() {
        IConfig config = getIConfig();
        return config.getItemOverviewModelHeaderImageCacheSize();
    }

    public static void setItemOverviewHeaderImageCacheSize(int cacheSize) {
        IConfig config = getIConfig();
        config.setItemOverviewModelHeaderImageCacheSize(cacheSize);
    }

    public static int getDiskCacheSize() {
        IConfig config = getIConfig();
        return config.getDiskCacheSize();
    }

    public static void setDiskCacheSize(int cacheSize) {
        IConfig config = getIConfig();
        config.setDiskCacheSize(cacheSize);
    }

    public static boolean getCanUseMobileNetwork() {
        IConfig config = getIConfig();
        return config.getCanUseMobileNetwork();
    }

    public static void setCanUseMobileNetwork(boolean canUseMobileNetwork) {
        IConfig config = getIConfig();
        config.setCanUseMobileNetwork(canUseMobileNetwork);
    }

}
