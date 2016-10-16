package com.example.kuijin.mycnblogs.presenter.page;

import android.content.Context;

import com.example.kuijin.mycnblogs.view.page.AbsPageFragment;

/**
 * Created by kuijin on 2016/9/15.
 */
public class PageFragmentPresenterManager {

    public static IPageFragmentPresenter getPageFragmentPresenter(Context context, AbsPageFragment fragment) {
        PageFragmentPresenter presenter = new PageFragmentPresenter(context, fragment);
        return presenter;
    }
}
