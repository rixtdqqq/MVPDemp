package com.example.mvpdemo.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.amap.api.maps.MapView;
import com.example.mvpdemo.R;
import com.example.mvpdemo.view.base.BaseFragment;

/**
 * 高德地图
 */
public class AmapFrag extends BaseFragment {

    private MapView mMapView;
    public AmapFrag() {
        // Required empty public constructor
    }

    @Override
    protected void lazyLoad(boolean isVisible) {

    }

    public static AmapFrag newInstance() {
        Bundle args = new Bundle();
        AmapFrag fragment = new AmapFrag();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_amap;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapView.onCreate(savedInstanceState);
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mMapView = (MapView) view.findViewById(R.id.map);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }
}
