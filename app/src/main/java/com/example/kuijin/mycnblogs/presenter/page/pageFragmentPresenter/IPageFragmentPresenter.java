package com.example.kuijin.mycnblogs.presenter.page.pageFragmentPresenter;

/**
 * Created by kuijin on 2016/10/17.
 */
public interface IPageFragmentPresenter {
    void setAdapter(OnItemClickListener listener);

    void refresh();

    void more();

    public interface OnItemClickListener {
        void onItemClick(String url);
    }
}
