package com.example.mvpdemo.base;

/**
 * MVP接口基类
 * Created by Administrator on 2017/4/4.
 */

public interface BaseMVP {
    interface BaseView {
        /**
         * 初始化数据
         */
        void initData();

        /**
         * 初始化监听
         */
        void initListener();

        /**
         * 显示加载框
         */
        void showLoadingDialog();

        /**
         * 隐藏加载框
         */
        void hideLoadingDialog();

        /**
         * 显示toast信息
         */
        void showToast(String message);
    }

    interface BaseModel {
    }

    interface BasePresenter {
        void start();
    }
}
