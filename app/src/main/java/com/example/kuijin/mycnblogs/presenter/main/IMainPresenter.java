package com.example.kuijin.mycnblogs.presenter.main;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

/**
 * Created by kuijin on 2016/9/13.
 */
public interface IMainPresenter {
    void setViewAdapter(ViewPager viewPager, FragmentManager fragmentManager);
}
