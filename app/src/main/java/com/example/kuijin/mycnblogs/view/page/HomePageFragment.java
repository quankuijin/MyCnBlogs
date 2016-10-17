package com.example.kuijin.mycnblogs.view.page;

import com.example.kuijin.mycnblogs.presenter.page.pageFragmentPresenter.PageFragmentPresenterManager;

/**
 * Created by kuijin on 2016/9/13.
 */
public class HomePageFragment extends AbsPageFragment {
    @Override
    public String getUrl() {
        return "http://wcf.open.cnblogs.com/blog/sitehome/paged";
    }

    @Override
    protected void initPresenter() {
        presenter = PageFragmentPresenterManager.getPageFragmentPresenter(getActivity(), this);
    }
}
