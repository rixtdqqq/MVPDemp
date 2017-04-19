package com.example.mvpdemo.model.impl;

import com.example.mvpdemo.base.ILoginContact;
import com.example.mvpdemo.base.OnDataCallBack;
import com.example.mvpdemo.model.entity.ResponseMessage;
import com.example.mvpdemo.model.entity.ResponseResult2;
import com.example.mvpdemo.util.RequestService;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 登录数据接口实现类
 * Created by Administrator on 2017/3/19.
 */

public class LoginModelImpl implements ILoginContact.ILoginModel {

    @Override
    public void doRequestLogin(String userName, String password, OnDataCallBack loginListener) {
        RequestService.getHttpService(true).login(userName, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseResult2<ResponseMessage>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        loginListener.onFail(e.toString());
                    }

                    @Override
                    public void onNext(ResponseResult2<ResponseMessage> result2) {
                        loginListener.onSuccess(result2);
                    }
                });
    }
}
