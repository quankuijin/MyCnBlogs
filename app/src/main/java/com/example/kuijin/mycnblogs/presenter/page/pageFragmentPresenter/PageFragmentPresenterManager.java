package com.example.kuijin.mycnblogs.presenter.page.pageFragmentPresenter;

import android.content.Context;

import com.example.kuijin.mycnblogs.presenter.page.*;
import com.example.kuijin.mycnblogs.view.page.AbsPageFragment;


/**
 * Created by kuijin on 2016/10/17.
 */
public class PageFragmentPresenterManager {

    public static IPageFragmentPresenter getPageFragmentPresenter(Context context, AbsPageFragment fragment) {
        HomePageFragmentPresenter presenter = new HomePageFragmentPresenter(context, fragment);
        return presenter;
    }
}
