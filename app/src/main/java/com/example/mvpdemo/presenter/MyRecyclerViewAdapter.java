package com.example.mvpdemo.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvpdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * recyclerView的adapter
 * Created by Administrator on 2017/4/4.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private LayoutInflater mInflater;
    private List<String> data;
    private static final int TYPE_ITEM = 782;
    private static final int TYPE_FOOTER = 576;
    public MyRecyclerViewAdapter(Context context,List<String > data) {
        mInflater = LayoutInflater.from(context);
        if (null == data) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (TYPE_ITEM == viewType) {
            View view = mInflater.inflate(R.layout.item_view, parent, false);
            return new ItemViewHolder(view);
        } else if (TYPE_FOOTER == viewType) {
            View view = mInflater.inflate(R.layout.item_footer_view, parent, false);
            return new FooterViewHolder(view);
        }
        return null;
        //这边可以做一些属性设置，甚至事件监听绑定
        //view.setBackgroundColor(Color.RED);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder)holder).item.setText(data.get(position));
            holder.itemView.setTag(position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    // RecyclerView的count设置为数据总条数+ 1（footerView）
    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    private static class ItemViewHolder extends ViewHolder {
        private TextView item;

        private ItemViewHolder(View itemView) {
            super(itemView);
            item = (TextView) itemView.findViewById(R.id.list_item);
        }
    }
    private static class FooterViewHolder extends ViewHolder {

        private FooterViewHolder(View view) {
            super(view);
        }

    }
}
