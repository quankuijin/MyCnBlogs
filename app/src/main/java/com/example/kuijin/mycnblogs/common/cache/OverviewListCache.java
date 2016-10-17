package com.example.kuijin.mycnblogs.common.cache;

import android.util.LruCache;

import com.example.kuijin.mycnblogs.model.IItemOverviewModel;
import com.example.kuijin.mycnblogs.model.ItemOverviewModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by kuijin on 2016/9/17.
 */
public class OverviewListCache extends LruCache<Integer, List<IItemOverviewModel>> {

    private int index = -1;

    private List<String> listItemIds = null;

    /**
     * @param maxSize for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     */
    public OverviewListCache(int maxSize) {
        super(maxSize);
        listItemIds = new ArrayList<>();
    }

    public int getKey(Integer key) {
        index ++;
        return index;
    }

    @Override
    protected int sizeOf(Integer key, List<IItemOverviewModel> value) {
        int size = 0;

        for (IItemOverviewModel item : value) {
            size += item.getSize();
        }

        return size;
    }

    public int getMaxIndex() {
        return  index;
    }

    public void addItemIds(List<IItemOverviewModel> items) {
        List<String> list = new LinkedList<>();

        for (IItemOverviewModel item : items) {
            if (!listItemIds.contains(item.getId())) {
                list.add(item.getId());
            }
        }

        if (0 < list.size()) {
            listItemIds.addAll(list);
        }
    }

    public List<String> getItemIds() {
        return listItemIds;
    }

    @Override
    protected void entryRemoved(boolean evicted, Integer key, List<IItemOverviewModel> oldValue, List<IItemOverviewModel> newValue) {
        super.entryRemoved(evicted, key, oldValue, newValue);

        List<String> delIds = new LinkedList<>();
        for (IItemOverviewModel item : oldValue) {
            if (listItemIds.contains(item.getId())) {
                delIds.add(item.getId());
            }
        }

        if (0 < delIds.size()) {
            listItemIds.removeAll(delIds);
        }
    }

    public List<IItemOverviewModel> getAddedItemOverviewModels(List<IItemOverviewModel> list) {
        List<IItemOverviewModel> listAdded = new LinkedList<>();
        for (IItemOverviewModel item : list) {
            if (listItemIds.contains(item.getId())) {
                listAdded.add(item);
            }
        }

        return listAdded;
    }
}
