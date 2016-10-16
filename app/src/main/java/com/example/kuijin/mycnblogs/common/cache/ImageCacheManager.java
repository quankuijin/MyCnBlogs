package com.example.kuijin.mycnblogs.common.cache;

import android.graphics.Bitmap;
import android.os.Build;

import com.example.kuijin.mycnblogs.common.CnBlogsLog;
import com.example.kuijin.mycnblogs.common.config.ConfigManager;
import com.example.kuijin.mycnblogs.common.disk.DiskManager;

import java.io.FileNotFoundException;

/**
 * Created by kuijin on 2016/9/17.
 */
public class ImageCacheManager {

    private static ImageCacheManager manager = null;

    private ImageCache imageCache = null;
    private int cacheSize = 0;
    private ImageCacheManager() {
        initCacheSize();
        imageCache = new ImageCache(cacheSize);
    }

    private void initCacheSize() {
        cacheSize = ConfigManager.getItemOverviewHeaderImageCacheSize() * 1024 * 1024;
    }

    public static ImageCacheManager getInstance() {
        if (null == manager) {
            manager = new ImageCacheManager();
        }

        return manager;
    }

    public void put(String key, Bitmap value) {
        imageCache.put(key, value);
    }

    public Bitmap get(String key) {
        Bitmap bitmap = imageCache.get(key);
        if (null != bitmap) {
            return bitmap;
        }

        try {
            bitmap = DiskManager.getHeaderBitmap(key);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            CnBlogsLog.write(e);
            return null;
        }

        if (null != bitmap) {
            imageCache.put(key, bitmap);
        }

        return bitmap;
    }

    public Bitmap remove(String key) {
        return imageCache.remove(key);
    }

    public void clear() {
        initCacheSize();
        imageCache = new ImageCache(cacheSize);
    }
}
