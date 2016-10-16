package com.example.kuijin.mycnblogs.common.cache;

import android.util.LruCache;

/**
 * Created by kuijin on 2016/9/17.
 */
public class OverviewModuleCache extends LruCache<String, OverviewListCache> {
    /**
     * @param maxSize for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     */
    public OverviewModuleCache(int maxSize) {
        super(maxSize);
    }

    @Override
    protected int sizeOf(String key, OverviewListCache value) {
        int size = key.length();
        size += value.size();

        return size;
    }
}
