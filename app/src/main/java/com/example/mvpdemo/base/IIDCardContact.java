package com.example.mvpdemo.base;

import android.graphics.Bitmap;

/**
 * 身份证信息MVP接口管理类
 * Created by Administrator on 2017/4/2.
 */

public interface IIDCardContact {
    interface View extends BaseMVP.BaseView {
        /**
         * 展示数据
         */
        void showResult(String result);

        /**
         * 获取身份证号
         */
        String getIDCard();

        /**
         * 查询成功
         */
        void showSuccess(String msg);

        /**
         * 查询失败
         */
        void showFailed(String msg);

        void showBitmap(Bitmap bitmap);

    }

    interface Presenter extends BaseMVP.BasePresenter {
        /**
         * 加载身份证信息查询数据
         */
        void getFind(String idCard);

        /**
         * 加载身份证泄露查询数据
         */
        void getLeak(String idCard);

        /**
         * 加载身份证挂失查询数据
         */
        void getLoss(String idCard);

        /**
         * 获取图片
         */
        void getGirlBitmap();
    }

    interface Model extends BaseMVP.BaseModel {
        /**
         * 请求身份证信息查询数据
         */
        void doRequestFind(String idCard, OnDataCallBack callBack);

        /**
         * 请求身份证泄露查询数据
         */
        void doRequestLeak(String idCard, OnDataCallBack callBack);

        /**
         * 请求身份证挂失查询数据
         */
        void doRequestLoss(String idCard, OnDataCallBack callBack);

        /**
         * 获取图片
         */
        void doRequestBitmap(OnDataCallBack callBack);
    }
}
