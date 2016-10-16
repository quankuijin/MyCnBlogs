package com.example.kuijin.mycnblogs.view.page;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kuijin.mycnblogs.R;
import com.example.kuijin.mycnblogs.presenter.page.IPageFragmentPresenter;
import com.example.kuijin.mycnblogs.view.page.recyclerview.RecyclerViewItemDecoration;

/**
 * Created by kuijin on 2016/9/15.
 */
public abstract class AbsPageFragment extends Fragment {
    protected RecyclerView recyclerView = null;
    protected IPageFragmentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        initPresenter();

        initRecyclerView();

        return view;
    }

    public abstract String getUrl();

    protected abstract void initPresenter();

    private void initRecyclerView() {

        if (null == recyclerView) {
            return;
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new RecyclerViewItemDecoration());

        if (null != presenter) {
            presenter.setAdapter(new IPageFragmentPresenter.OnItemClickListener() {
                @Override
                public void onItemClick(String url) {

                }
            });
        }
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
}
