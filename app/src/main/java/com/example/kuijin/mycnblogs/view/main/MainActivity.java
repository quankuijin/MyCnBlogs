package com.example.kuijin.mycnblogs.view.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kuijin.mycnblogs.R;
import com.example.kuijin.mycnblogs.presenter.main.IMainPresenter;
import com.example.kuijin.mycnblogs.presenter.main.MainPresenterManager;

/**
 * Created by kuijin on 2016/9/11.
 */
public class MainActivity extends AppCompatActivity{

    IMainPresenter mainPresenter = null;

    private Toolbar toolbar = null;
    private DrawerLayout drawerLayout = null;
    private ActionBarDrawerToggle toggle = null;

    private TabLayout tabLayout = null;
    private ViewPager viewPager = null;

    private NavigationView navigationView = null;
    private ImageView ivHeader = null;
    private TextView tvHeader = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initData() {
        initPresenter();
    }

    private void initPresenter() {
        mainPresenter = MainPresenterManager.getMainPresenter(this);
    }

    private void initView() {

        initDrawerLayoutAndToolbar();

        //导航页
        initNavagationView();

        initTabLayout();
    }

    private void initTabLayout() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        mainPresenter.setViewAdapter(viewPager, getSupportFragmentManager());
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initDrawerLayoutAndToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getResources().getString(R.string.my_cn_blogs));
        toolbar.setNavigationIcon(R.drawable.ic_navigation_icon);
        toolbar.setLogo(R.drawable.ic_logo);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_opened, R.string.navigation_closed);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);
    }

    private void initNavagationView() {
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        ivHeader = (ImageView) findViewById(R.id.iv_navigation_header);
        tvHeader = (TextView) findViewById(R.id.tv_name_navigation_header);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return false;
            }
        });
    }
}
