package com.example.mvpdemo.util;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * recyclerView的自定义的holder
 * Created by Administrator on 2017/4/19.
 */

public class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private T mBinding;
    public BindingViewHolder(T binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public T getBinding() {
        return mBinding;
    }

}
