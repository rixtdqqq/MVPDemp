package com.example.mvpdemo.model.impl;

import com.example.mvpdemo.base.IRegisterContact;
import com.example.mvpdemo.base.OnDataCallBack;
import com.example.mvpdemo.model.entity.ResponseMessage;
import com.example.mvpdemo.model.entity.ResponseResult2;
import com.example.mvpdemo.util.RequestService;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 注册用户
 * Created by Administrator on 2017/4/2.
 */

public class UserRegisterModelImpl implements IRegisterContact.Model {
    @Override
    public void doRequestRegisterUser(String userName, String password, String createTime,
                                      String qq, String weChat, String phone, String email, OnDataCallBack callBack) {
        RequestService.getHttpService(true).registerUser(userName, password, createTime, qq, weChat, phone, email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseResult2<ResponseMessage>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.toString());
                    }

                    @Override
                    public void onNext(ResponseResult2<ResponseMessage> result) {
                        callBack.onSuccess(result);
                    }
                });

    }
}
