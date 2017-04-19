package com.example.mvpdemo.model.impl;

import com.example.mvpdemo.base.IModifyPasswordContact;
import com.example.mvpdemo.base.OnDataCallBack;
import com.example.mvpdemo.model.entity.ResponseMessage;
import com.example.mvpdemo.model.entity.ResponseResult2;
import com.example.mvpdemo.util.RequestService;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 修改密码
 * Created by Administrator on 2017/4/2.
 */

public class ModifyPasswordModelImpl implements IModifyPasswordContact.Model {

    @Override
    public void doRequestModifyPassword(String account, String password, OnDataCallBack callBack) {
        RequestService.getHttpService(true).modifyPassword(account, password)
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
                    public void onNext(ResponseResult2<ResponseMessage> responseMessageResponseResult2) {
                        callBack.onSuccess(responseMessageResponseResult2);
                    }
                });
    }
}
