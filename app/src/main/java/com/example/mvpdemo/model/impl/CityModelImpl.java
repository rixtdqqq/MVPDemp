package com.example.mvpdemo.model.impl;

import com.example.mvpdemo.base.ICityRecyclerViewContact;
import com.example.mvpdemo.base.OnDataCallBack;
import com.example.mvpdemo.model.entity.City;
import com.example.mvpdemo.model.entity.Page;
import com.example.mvpdemo.model.entity.ResponseResult2;
import com.example.mvpdemo.util.RequestService;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 身份证信息数据
 * Created by Administrator on 2017/4/2.
 */

public class CityModelImpl implements ICityRecyclerViewContact.Model {

    @Override
    public void doRequestCity(int pageSize, int pageNum, String countryName, String countryCode, String district, OnDataCallBack callBack) {
        RequestService.getHttpService(true).findCity(pageSize, pageNum, countryName, countryCode, district)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseResult2<Page<City>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.toString());
                    }

                    @Override
                    public void onNext(ResponseResult2<Page<City>> result) {
                        callBack.onSuccess(result);
                    }
                });
    }
}
