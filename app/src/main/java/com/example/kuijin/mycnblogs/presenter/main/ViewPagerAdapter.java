package com.example.kuijin.mycnblogs.presenter.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.kuijin.mycnblogs.common.CnBlogsLog;
import com.example.kuijin.mycnblogs.view.page.HomePageFragment;
import com.example.kuijin.mycnblogs.view.page.ReadRankingFragment;
import com.example.kuijin.mycnblogs.view.page.RecommendBlogsFragment;
import com.example.kuijin.mycnblogs.view.page.RecommendWriterFragment;
import com.example.kuijin.mycnblogs.view.page.TestFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kuijin on 2016/9/13.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter{

    private List<String> listFragmentNames = null;
    private List<String> listFragmentClassNames = null;
    private Map<String, Fragment> mapFragments = null;
    private int size = -1;

    public ViewPagerAdapter(FragmentManager fm, List<String> listFragmentNames, List<String> listFragmentClassNames) {
        super(fm);

        if (listFragmentClassNames.size() != listFragmentNames.size()) {
            CnBlogsLog.write("ViewPagerAdapter","Constructor", "names.size not equals classNames.size", CnBlogsLog.LEVEL_ERROR);
            return;
        }
        this.listFragmentNames = listFragmentNames;
        this.listFragmentClassNames = listFragmentClassNames;
        size = listFragmentNames.size();
        mapFragments = new HashMap<>(size, 1);
    }

    @Override
    public Fragment getItem(int position) {
        if (position < 0 || position >= size) {
            CnBlogsLog.write("ViewPagerAdapter","getItem", "position is out of Range", CnBlogsLog.LEVEL_ERROR);
        }

        String className = listFragmentClassNames.get(position);
        Fragment fragment = mapFragments.get(className);
        if (null != fragment) {
            return fragment;
        }

//        try {
//            Class<?> clazz = Class.forName(className);
//            Object o = clazz.newInstance();
//            if (o instanceof Fragment) {
//                fragment = (Fragment)o;
//                mapFragments.put(className, fragment);
//                return fragment;
//            } else {
//                CnBlogsLog.write("ViewPagerAdapter","getItem", "cannot parse object to Fragment", CnBlogsLog.LEVEL_ERROR);
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            CnBlogsLog.write(e);
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//            CnBlogsLog.write(e);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//            CnBlogsLog.write(e);
//        }
        if (className.equals(HomePageFragment.class.getName())) {
            fragment = new HomePageFragment();
        } else if (className.equals(RecommendBlogsFragment.class.getName())) {
            fragment = new RecommendBlogsFragment();
        } else if (className.equals(ReadRankingFragment.class.getName())) {
            fragment = new ReadRankingFragment();
        } else if (className.equals(RecommendWriterFragment.class.getName())) {
            fragment = new RecommendWriterFragment();
        } else {
            fragment = new TestFragment();
        }

        if (null != fragment) {
            mapFragments.put(className, fragment);
            return fragment;
        }

        return null;
    }

    @Override
    public int getCount() {
        return size;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position < 0 || position >= size) {
            CnBlogsLog.write("ViewPagerAdapter","getPageTitle", "position is out of Range", CnBlogsLog.LEVEL_ERROR);
        }

        return listFragmentNames.get(position);
    }
}
