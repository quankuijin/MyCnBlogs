package com.example.kuijin.mycnblogs.presenter.page.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kuijin.mycnblogs.common.CnBlogsLog;
import com.example.kuijin.mycnblogs.model.IItemOverviewModel;
import com.example.kuijin.mycnblogs.model.ItemOverviewModel;
import com.example.kuijin.mycnblogs.presenter.page.pageFragmentPresenter.IPageFragmentPresenter;
import com.example.kuijin.mycnblogs.view.page.recyclerview.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kuijin on 2016/9/15.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<IItemOverviewModel> list;

    private IPageFragmentPresenter.OnItemClickListener itemClickListener;

    public RecyclerViewAdapter(Context context, List<IItemOverviewModel> list, IPageFragmentPresenter.OnItemClickListener listener) {
        this.context = context;
        if (null == list) {
            this.list = new ArrayList<>();
//            ItemOverviewModel model = new ItemOverviewModel();
//            model.setTitle("aaa");
//            model.setSummary("bb");
//            this.list.add(model);
        } else {
            this.list = list;
        }
        itemClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = RecyclerViewHolder.getView(LayoutInflater.from(context), parent, viewType);
        RecyclerViewHolder holder = new RecyclerViewHolder(context, view, itemClickListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RecyclerViewHolder) {
            IItemOverviewModel item = list.get(position);

            RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) holder;
            recyclerViewHolder.bindViewHolder(item);
        } else {
            CnBlogsLog.write("RecyclerViewAdapter", "onBindViewHolder", "ViewHolder cannot change to RecyclerViewHodler!", CnBlogsLog.LEVEL_ERROR);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItemsToLast(List<IItemOverviewModel> items) {
        if (null == items) {
            CnBlogsLog.write("RecyclerViewAdapter", "addItemsToLast", "items is null", CnBlogsLog.LEVEL_ERROR);
            return;
        }

        int count = items.size();
        if (count == 0) {
            CnBlogsLog.write("RecyclerViewAdapter", "addItemsToLast", "items is empty", CnBlogsLog.LEVEL_ERROR);
            return;
        }
        int start = getItemCount();

        list.addAll(items);
        notifyItemRangeInserted(start, count);
    }

    public void addItemsToFirst(List<IItemOverviewModel> items) {
        if (null == items) {
            CnBlogsLog.write("RecyclerViewAdapter", "addItemsToFirst", "items is null", CnBlogsLog.LEVEL_ERROR);
            return;
        }

        int count = items.size();
        if (count == 0) {
            CnBlogsLog.write("RecyclerViewAdapter", "addItemsToFirst", "items is empty", CnBlogsLog.LEVEL_ERROR);
            return;
        }

        list.addAll(0, items);
        notifyItemRangeInserted(0, count);
    }

    public void clearAndAddItems(List<IItemOverviewModel> items) {
        if (null == items) {
            CnBlogsLog.write("RecyclerViewAdapter", "clearAndAddItems", "items is null", CnBlogsLog.LEVEL_ERROR);
            return;
        }

        int count = items.size();
        if (count == 0) {
            CnBlogsLog.write("RecyclerViewAdapter", "clearAndAddItems", "items is empty", CnBlogsLog.LEVEL_ERROR);
            return;
        }

        int oldCount = getItemCount();
        list.clear();
        list = items;
        int newCount = getItemCount();

        if (oldCount > newCount) {
            notifyItemRangeChanged(0, newCount - 1);
            notifyItemMoved(newCount, oldCount - newCount);
        } else if (oldCount == newCount) {
            notifyItemRangeChanged(0, newCount - 1);
        } else {
            notifyItemRangeChanged(0, oldCount - 1);
            notifyItemRangeInserted(oldCount, newCount - oldCount);
        }

    }

}
