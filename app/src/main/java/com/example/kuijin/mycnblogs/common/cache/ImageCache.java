package com.example.kuijin.mycnblogs.common.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by kuijin on 2016/9/17.
 */
public class ImageCache extends LruCache<String, Bitmap> {

    /**
     * @param maxSize for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     */
    public ImageCache(int maxSize) {
        super(maxSize);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        int size = key.length();
        size += value.getByteCount();

        return size;
    }
}
