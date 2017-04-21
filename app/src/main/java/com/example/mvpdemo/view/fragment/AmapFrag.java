package com.example.mvpdemo.view.fragment;


import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.example.mvpdemo.R;
import com.example.mvpdemo.base.IMapContact;
import com.example.mvpdemo.util.Constant;
import com.example.mvpdemo.util.Util;
import com.example.mvpdemo.view.base.BaseFragment;

/**
 * 高德地图
 */
public class AmapFrag extends BaseFragment implements IMapContact.View ,PoiSearch.OnPoiSearchListener {

    private MapView mMapView;
    private AMap mAMap;
    private TextView tvPosition,tvSearch;
    private EditText etSearchAddition;
    private PopupWindow mPopupWindow;
    private PoiSearch.Query mQuery;
    private PoiSearch mPoiSearch;
    private int currentPage = 1;

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
        initMap();
    }

    private void initMap() {
        if (null == mAMap) {
            mAMap = mMapView.getMap();
        }
        MyLocationStyle myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        mAMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        mAMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        mAMap.showIndoorMap(true);     //true：显示室内地图；false：不显示；
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mMapView = (MapView) view.findViewById(R.id.map);
        tvPosition = (TextView) view.findViewById(R.id.position);
        tvSearch = (TextView) view.findViewById(R.id.search);
        etSearchAddition = (EditText) view.findViewById(R.id.search_position_et);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        tvPosition.setOnClickListener(v -> {
            showMapTypeDialog(v);
        });
        tvSearch.setOnClickListener(v -> {
            if (null == mQuery) {
                mQuery = new PoiSearch.Query(getSearchAddition(), "");
                mQuery.setPageSize(Constant.PAGE_SIZE);
                mQuery.setPageNum(currentPage);
            }
            mPoiSearch = new PoiSearch(mActivity, mQuery);
            mPoiSearch.setOnPoiSearchListener(this);
            mPoiSearch.searchPOIAsyn();
        });
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

    @Override
    public void showMapTypeDialog(View refer) {
        if (null == mPopupWindow) {
            View view = LayoutInflater.from(mActivity).inflate(R.layout.popu_map_type, null);
            mPopupWindow = new PopupWindow(view, Util.getDisplayScreenWH(mActivity)[0] / 2, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            TextView tvNavi = (TextView) view.findViewById(R.id.navi_map_tv);
            TextView tvSatellite = (TextView) view.findViewById(R.id.satellite_map_tv);
            TextView tvNormal = (TextView) view.findViewById(R.id.normal_map_tv);
            TextView tvNight = (TextView) view.findViewById(R.id.night_map_tv);
            TextView tvSearch = (TextView) view.findViewById(R.id.search_map_tv);
            mPopupWindow.setTouchable(true);
            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
            tvNavi.setOnClickListener(v -> {
                mAMap.setMapType(AMap.MAP_TYPE_NAVI);
                TextView tv = (TextView) v;
                tv.setTextColor(mActivity.getResources().getColor(R.color.colorAccent));
                tvSatellite.setTextColor(mActivity.getResources().getColor(R.color.colorPrimary));
                tvNormal.setTextColor(mActivity.getResources().getColor(R.color.colorPrimary));
                tvNight.setTextColor(mActivity.getResources().getColor(R.color.colorPrimary));
                if (mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }
            });
            tvSatellite.setOnClickListener(v -> {
                mAMap.setMapType(AMap.MAP_TYPE_SATELLITE);
                TextView tv = (TextView) v;
                tv.setTextColor(mActivity.getResources().getColor(R.color.colorAccent));
                tvNavi.setTextColor(mActivity.getResources().getColor(R.color.colorPrimary));
                tvNormal.setTextColor(mActivity.getResources().getColor(R.color.colorPrimary));
                tvNight.setTextColor(mActivity.getResources().getColor(R.color.colorPrimary));
                if (mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }
            });
            tvNormal.setOnClickListener(v -> {
                mAMap.setMapType(AMap.MAP_TYPE_NORMAL);
                TextView tv = (TextView) v;
                tv.setTextColor(mActivity.getResources().getColor(R.color.colorAccent));
                tvNavi.setTextColor(mActivity.getResources().getColor(R.color.colorPrimary));
                tvSatellite.setTextColor(mActivity.getResources().getColor(R.color.colorPrimary));
                tvNight.setTextColor(mActivity.getResources().getColor(R.color.colorPrimary));
                if (mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }
            });
            tvNight.setOnClickListener(v -> {
                mAMap.setMapType(AMap.MAP_TYPE_NIGHT);
                TextView tv = (TextView) v;
                tv.setTextColor(mActivity.getResources().getColor(R.color.colorAccent));
                tvNavi.setTextColor(mActivity.getResources().getColor(R.color.colorPrimary));
                tvSatellite.setTextColor(mActivity.getResources().getColor(R.color.colorPrimary));
                tvNormal.setTextColor(mActivity.getResources().getColor(R.color.colorPrimary));
                if (mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }
            });
            tvSearch.setOnClickListener(v -> {

                if (mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }
            });
        }
        if (mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        } else {
            mPopupWindow.showAtLocation(refer, Gravity.CENTER, 0, 0);
        }
    }

    @Override
    public String getSearchAddition() {
        return etSearchAddition.getText().toString().trim();
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {

    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
}
