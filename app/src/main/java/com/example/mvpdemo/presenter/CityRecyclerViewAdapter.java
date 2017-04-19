package com.example.mvpdemo.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvpdemo.R;
import com.example.mvpdemo.model.entity.City;
import com.example.mvpdemo.view.base.MVPApp;

import java.util.ArrayList;
import java.util.List;

/**
 * recyclerView的adapter
 * Created by Administrator on 2017/4/4.
 */

public class CityRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<City> data; // 数据源

    private static final int NORMAL_TYPE = 0;     // 第一种ViewType，正常的item
    private static final int FOOT_TYPE = 1;       // 第二种ViewType，底部的提示View

    private boolean hasMore = true;   // 变量，是否有更多数据
    private LayoutInflater mInflater;

    /**
     * @param data    数据，上拉加载的原理就是分页，所以我设置常量PAGE_COUNT=10，即每次加载10个数据
     * @param context 上下文
     * @param hasMore 是否有新数据
     */
    public CityRecyclerViewAdapter(List<City> data, Context context, boolean hasMore) {
        // 初始化变量
        if (null == data) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }
        this.hasMore = hasMore;
        mInflater = LayoutInflater.from(context);
    }

    // 获取条目数量，之所以要加1是因为增加了一条footView
    @Override
    public int getItemCount() {
        return data.size() == 0 ? 0 : (data.size() + 1);
    }

    public void isHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    // 根据条目位置返回ViewType，以供onCreateViewHolder方法内获取不同的Holder
    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return FOOT_TYPE;
        } else {
            return NORMAL_TYPE;
        }
    }

    // 正常item的ViewHolder，用以缓存findView操作
    private static class NormalHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvDistrict;
        private TextView tvCountryCode;
        private TextView tvPopulation;

        private NormalHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.country_name);
            tvDistrict = (TextView) itemView.findViewById(R.id.district);
            tvCountryCode = (TextView) itemView.findViewById(R.id.country_code);
            tvPopulation = (TextView) itemView.findViewById(R.id.population);
        }
    }

    // 底部footView的ViewHolder，用以缓存findView操作
    private static class FootHolder extends RecyclerView.ViewHolder {
        private TextView tips;
        private ProgressBar mProgressBar;

        private FootHolder(View itemView) {
            super(itemView);
            tips = (TextView) itemView.findViewById(R.id.loading);
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 根据返回的ViewType，绑定不同的布局文件，这里只有两种
        if (viewType == NORMAL_TYPE) {
            return new NormalHolder(mInflater.inflate(R.layout.item_view, parent, false));
        } else {
            return new FootHolder(mInflater.inflate(R.layout.item_footer_view, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        // 如果是正常的item，直接设置TextView的值
        NormalHolder normalHolder;
        FootHolder footHolder;
        if (holder instanceof NormalHolder) {
            City city = data.get(position);
            normalHolder = (NormalHolder) holder;
            normalHolder.tvCountryCode.setText(city.getCountryCode());
            normalHolder.tvDistrict.setText(city.getDistrict());
            normalHolder.tvName.setText(city.getName());
            normalHolder.tvPopulation.setText(String.valueOf(city.getPopulation()));
        } else {
            footHolder = (FootHolder) holder;
            // 之所以要设置可见，是因为我在没有更多数据时会隐藏了这个footView
            footHolder.itemView.setVisibility(View.VISIBLE);
            // 只有获取数据为空时，hasMore为false，所以当我们拉到底部时基本都会首先显示“正在加载更多...”
            if (hasMore) {
                // 如果查询数据发现增加之后，就显示正在加载更多
                footHolder.tips.setText("正在加载更多...");
                footHolder.mProgressBar.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(MVPApp.getInstance(), "没有更多数据", Toast.LENGTH_SHORT).show();
                // 隐藏提示条
                footHolder.itemView.setVisibility(View.GONE);
            }
        }
    }

}
