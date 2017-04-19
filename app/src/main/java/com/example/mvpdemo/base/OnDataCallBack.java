package com.example.mvpdemo.base;

/**
 * 服务返回数据时的接口回调
 * Created by Administrator on 2017/4/2.
 */

public interface OnDataCallBack {
    /**
     * 成功的回调方法
     *
     * @param result     服务返回的结果
     */
    void onSuccess( Object result);

    /**
     * 失败的回调方法
     * @param errorMsg 失败的信息
     */
    void onFail(String errorMsg);
}
