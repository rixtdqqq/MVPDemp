package com.example.mvpdemo.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.example.mvpdemo.R;
import com.example.mvpdemo.presenter.FragPagerAdapter;
import com.example.mvpdemo.util.Util;
import com.example.mvpdemo.view.base.BaseActivity;
import com.example.mvpdemo.view.base.BaseFragment;
import com.example.mvpdemo.view.fragment.AmapFrag;
import com.example.mvpdemo.view.fragment.RecyclerViewPullUpDownFrag;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private List<String> titles ;
    private List<BaseFragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titles = new ArrayList<>();
        mFragments = new ArrayList<>();
        titles.add("高德地图");
        titles.add("下拉刷新上拉加载");
        mFragments.add(AmapFrag.newInstance());
        mFragments.add(RecyclerViewPullUpDownFrag.newInstance());
        initView();
    }

    private void initView() {
        Util.initToolbar((Toolbar) findViewById(R.id.toolbar), this);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        FragmentPagerAdapter adapter = new FragPagerAdapter(getSupportFragmentManager(), mFragments, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

}
