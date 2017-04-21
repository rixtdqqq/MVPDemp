package com.example.mvpdemo.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mvpdemo.view.base.BaseFragment;

import java.util.List;

/**
 * fragment的adapter
 * Created by Administrator on 2017/4/20.
 */

public class FragPagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> mFragments;
    private List<String> mTitles;

    public FragPagerAdapter(FragmentManager fm,List<BaseFragment> fragments, List<String> titles) {
        super(fm);
        mTitles = titles;
        mFragments = fragments;
    }


    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
