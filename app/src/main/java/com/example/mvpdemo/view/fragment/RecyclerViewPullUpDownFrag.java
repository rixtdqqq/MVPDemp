package com.example.mvpdemo.view.fragment;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvpdemo.R;
import com.example.mvpdemo.base.ICityRecyclerViewContact;
import com.example.mvpdemo.model.entity.City;
import com.example.mvpdemo.presenter.CityPresenterImpl;
import com.example.mvpdemo.presenter.CityRecyclerViewAdapter;
import com.example.mvpdemo.util.Util;
import com.example.mvpdemo.view.base.BaseFragment;
import com.example.mvpdemo.view.base.MVPApp;

import java.util.ArrayList;
import java.util.List;

/**
 * recyclerView 下拉刷新上拉加载
 * SwipeRefreshLayout里面需要注意的Api：
 * 1、setOnRefreshListener(OnRefreshListener listener)  设置下拉监听，当用户下拉的时候会去执行回调
 * 2、setColorSchemeColors(int... colors) 设置 进度条的颜色变化，最多可以设置4种颜色
 * 3、setProgressViewOffset(boolean scale, int start, int end) 调整进度条距离屏幕顶部的距离
 * 4、setRefreshing(boolean refreshing) 设置SwipeRefreshLayout当前是否处于刷新状态，一般是在请求数据的时候设置为true，在数据被加载到View中后，设置为false。
 */
public class RecyclerViewPullUpDownFrag extends BaseFragment implements ICityRecyclerViewContact.View {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private EditText etCountryName;
    private EditText etCountryCode;
    private EditText etDistrict;
    /**
     * 数据源
     */
    private List<City> data;
    private CityRecyclerViewAdapter mAdapter2;
    private int lastVisibleItem = 0;
    /**
     * 设置每页显示列表的item个数
     */
    private final int PAGE_COUNT = 10;
    private LinearLayoutManager mLayoutManager;
    private ICityRecyclerViewContact.Presenter mPresenter;
    private boolean hasMore = true;
    /**
     * 当前页数
     */
    private int currentPage = 1;

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void hideLoadingDialog() {
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(MVPApp.getInstance(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCityListView(List<City> cities, boolean hasMore) {
        this.hasMore = hasMore;
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
        //如果是下拉刷新，就把数据先清空
        if (1 == currentPage) {
            data.clear();
        }
        data.addAll(cities);
        mAdapter2.isHasMore(hasMore);
        mAdapter2.notifyDataSetChanged();
    }

    @Override
    public String getCountryName() {
        String countryName = etCountryName.getText().toString().trim();
        return TextUtils.isEmpty(countryName) ? "" : countryName;
    }

    @Override
    public String getCountryCode() {
        String countryCode = etCountryCode.getText().toString().trim();
        return TextUtils.isEmpty(countryCode) ? "" : countryCode;
    }

    @Override
    public String getDistrict() {
        String district = etDistrict.getText().toString().trim();
        return TextUtils.isEmpty(district) ? "" : district;
    }

    public RecyclerViewPullUpDownFrag() {
        // Required empty public constructor
    }

    public static RecyclerViewPullUpDownFrag newInstance() {
        Bundle args = new Bundle();
        RecyclerViewPullUpDownFrag fragment = new RecyclerViewPullUpDownFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView(view);
        initRefreshView();
        initRecyclerView();
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        mPresenter = new CityPresenterImpl(this);
        data = new ArrayList<>();
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        Util.initToolbar((Toolbar) view.findViewById(R.id.toolbar), mActivity);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        etDistrict = (EditText) view.findViewById(R.id.district);
        etCountryCode = (EditText) view.findViewById(R.id.country_code);
        etCountryName = (EditText) view.findViewById(R.id.country_name);

    }

    @Override
    public void initListener() {

    }

    /**
     * 初始化刷新控件
     */
    private void initRefreshView() {
        mRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mRefreshLayout.setOnRefreshListener(() -> {
            // 设置可见
            mRefreshLayout.setRefreshing(true);
            //下拉刷新时置为1
            currentPage = 1;
            mPresenter.dealCityData(PAGE_COUNT, currentPage, getCountryName(), getCountryCode(), getDistrict());
        });
    }

    /**
     * 初始化列表
     */
    private void initRecyclerView() {
        mAdapter2 = new CityRecyclerViewAdapter(data, mActivity, hasMore);
        mLayoutManager = new LinearLayoutManager(mActivity, OrientationHelper.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter2);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            /**
             * 当RecyclerView的滑动状态改变时触发
             */
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //当前的recycleView不滑动(滑动已经停止时)
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter2.getItemCount()) {
                    boolean refreshing = mRefreshLayout.isRefreshing();
                    if (refreshing) {
                        mAdapter2.notifyItemRemoved(mAdapter2.getItemCount());
                        return;
                    }
                    if (hasMore) {
                        currentPage++;
                        mPresenter.dealCityData(PAGE_COUNT, currentPage, getCountryName(), getCountryCode(), getDistrict());
                    } else {
                        showToast("没有更多数据");
                    }
                }
            }

            /**
             * 当RecyclerView滑动时触发
             * 类似点击事件的MotionEvent.ACTION_MOVE
             */
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    @Override
    protected void lazyLoad(boolean isVisible) {
//        if (isVisible) {
//            currentPage = 1;
//            mPresenter.dealCityData(PAGE_COUNT, currentPage, getCountryName(), getCountryCode(), getDistrict());
//        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_recycler_view_pull_up_down;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        data.clear();
    }
}
