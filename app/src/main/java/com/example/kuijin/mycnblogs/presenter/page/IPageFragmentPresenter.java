package com.example.kuijin.mycnblogs.presenter.page;

import android.support.v7.widget.RecyclerView;

import com.example.kuijin.mycnblogs.view.page.recyclerview.RecyclerViewHolder;

/**
 * Created by kuijin on 2016/9/15.
 */
public interface IPageFragmentPresenter {
    void setAdapter(OnItemClickListener listener);

    void refresh();

    void more();

    public interface OnItemClickListener {
        void onItemClick(String url);
    }
}
