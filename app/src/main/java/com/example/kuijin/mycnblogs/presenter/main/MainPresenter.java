package com.example.kuijin.mycnblogs.presenter.main;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.kuijin.mycnblogs.R;
import com.example.kuijin.mycnblogs.view.page.HomePageFragment;
import com.example.kuijin.mycnblogs.view.page.ReadRankingFragment;
import com.example.kuijin.mycnblogs.view.page.RecommendBlogsFragment;
import com.example.kuijin.mycnblogs.view.page.RecommendWriterFragment;
import com.example.kuijin.mycnblogs.view.page.TestFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kuijin on 2016/9/13.
 */
public class MainPresenter implements IMainPresenter {

    private List<String> listFragmentNames = null;
    private List<String> listFragmentClassNames = null;

    private Context context = null;

    public MainPresenter(Context context) {
        this.context = context;

        listFragmentClassNames = new ArrayList<>();
        listFragmentClassNames.add(HomePageFragment.class.getName());
        listFragmentClassNames.add(RecommendBlogsFragment.class.getName());
        listFragmentClassNames.add(ReadRankingFragment.class.getName());
        listFragmentClassNames.add(RecommendWriterFragment.class.getName());
        listFragmentClassNames.add(TestFragment.class.getName());

        listFragmentNames = new ArrayList<>(listFragmentClassNames.size());
        listFragmentNames.add(context.getResources().getString(R.string.fragment_home));
        listFragmentNames.add(context.getResources().getString(R.string.fragment_recommend_blogs));
        listFragmentNames.add(context.getResources().getString(R.string.fragment_read_ranking));
        listFragmentNames.add(context.getResources().getString(R.string.fragment_recommend_writer));
        listFragmentNames.add("test");
    }

    @Override
    public void setViewAdapter(ViewPager viewPager, FragmentManager fragmentManager) {
        if (null == viewPager || null == fragmentManager) {
            return;
        }

        FragmentPagerAdapter adapter  = new ViewPagerAdapter(fragmentManager, listFragmentNames, listFragmentClassNames);
        viewPager.setAdapter(adapter);
    }
}
