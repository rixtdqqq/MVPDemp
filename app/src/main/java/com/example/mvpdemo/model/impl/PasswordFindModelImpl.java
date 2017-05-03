package com.example.mvpdemo.model.impl;

import com.example.mvpdemo.base.IFindPasswordContact;
import com.example.mvpdemo.base.OnDataCallBack;
import com.example.mvpdemo.model.entity.ResponseMessage;
import com.example.mvpdemo.model.entity.ResponseResult2;
import com.example.mvpdemo.util.Constant;
import com.example.mvpdemo.util.RequestService;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 找回密码
 * Created by Administrator on 2017/4/2.
 */

public class PasswordFindModelImpl implements IFindPasswordContact.Model {

    @Override
    public void doRequestFindPassword(String account, String email, String checkCode, OnDataCallBack callBack) {
        RequestService.getHttpService(true).findPassword(account, email, checkCode)
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

    @Override
    public void doRequestSendCheckCode(String account, String email, OnDataCallBack callBack) {
        doRequestFindPassword(account, email, "", callBack);
    }

    @Override
    public void doRequestCheckAccountAndEmail(String userName, String email, String checkCode,OnDataCallBack callBack) {
        RequestService.getHttpService(true).checkAccountAndEmail(userName, email,checkCode)
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

    @Override
    public void countDown(OnDataCallBack callBack) {
        Observable.interval(1, TimeUnit.SECONDS).take(Constant.COUNT_DOWN).map(l -> {
            return Constant.COUNT_DOWN - l;
        }).doOnSubscribe(() -> {
            callBack.onSuccess(Constant.COUNT_DOWN + "s后重新发送");
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        callBack.onSuccess("complete");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        callBack.onSuccess(aLong + "s后重新发送");
                    }
                });
    }


}
