package com.example.mvpdemo.model.impl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.mvpdemo.base.IIDCardContact;
import com.example.mvpdemo.base.OnDataCallBack;
import com.example.mvpdemo.model.entity.IDCard;
import com.example.mvpdemo.model.entity.IDCardLoss;
import com.example.mvpdemo.model.entity.ResponseResult;
import com.example.mvpdemo.util.Constant;
import com.example.mvpdemo.util.LogUtil;
import com.example.mvpdemo.util.RequestService;
import com.google.gson.Gson;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 身份证信息数据
 * Created by Administrator on 2017/4/2.
 */

public class IDCardModelImpl implements IIDCardContact.Model {
    @Override
    public void doRequestFind(String idCard, OnDataCallBack callBack) {
        RequestService.getHttpService(false).find(Constant.JUHE_DATA_TYPE, Constant.JUHE_API_KEY, idCard)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseResult<IDCard>>() {

                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onNext(ResponseResult<IDCard> idCardBaseHttpResult) {
                        LogUtil.d(IDCardModelImpl.class.getName(), idCardBaseHttpResult.toString());
                        IDCard result = idCardBaseHttpResult.getResult();
                        int resultcode = idCardBaseHttpResult.getResultCode();
                        String reason = idCardBaseHttpResult.getReason();
                        Gson gson = new Gson();
                        callBack.onSuccess(gson.toJson(result));
                    }
                });

    }

    @Override
    public void doRequestLeak(String idCard, OnDataCallBack callBack) {
        RequestService.getHttpService(false).leak(Constant.JUHE_DATA_TYPE, Constant.JUHE_API_KEY, idCard)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseResult<IDCardLoss>>() {

                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.toString());
                    }

                    @Override
                    public void onNext(ResponseResult<IDCardLoss> idCardLossBaseHttpResult) {
                        LogUtil.d(IDCardModelImpl.class.getName(), idCardLossBaseHttpResult.toString());
                        IDCardLoss result = idCardLossBaseHttpResult.getResult();
                        int resultcode = idCardLossBaseHttpResult.getResultCode();
                        String reason = idCardLossBaseHttpResult.getReason();
                        Gson gson = new Gson();
                        callBack.onSuccess(gson.toJson(result));
                    }
                });
    }

    @Override
    public void doRequestLoss(String idCard, OnDataCallBack callBack) {
        RequestService.getHttpService(false).loss(Constant.JUHE_DATA_TYPE, Constant.JUHE_API_KEY, idCard)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseResult<IDCardLoss>>() {

                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.toString());
                    }
                    @Override
                    public void onNext(ResponseResult<IDCardLoss> idCardLossBaseHttpResult) {
                        LogUtil.d(IDCardModelImpl.class.getName(), idCardLossBaseHttpResult.toString());
                        IDCardLoss result = idCardLossBaseHttpResult.getResult();
                        int resultcode = idCardLossBaseHttpResult.getResultCode();
                        String reason = idCardLossBaseHttpResult.getReason();
                        Gson gson = new Gson();
                        callBack.onSuccess(gson.toJson(result));
                    }
                });
    }

    @Override
    public void doRequestBitmap(OnDataCallBack callBack) {
        RequestService.getHttpService(false).getBitmap(Constant.BITMAP_PATH)
                .subscribeOn(Schedulers.newThread())
                .map(new Func1<ResponseBody, Bitmap>() {
                    @Override
                    public Bitmap call(ResponseBody baseHttpResult) {
                        Bitmap bitmap = BitmapFactory.decodeStream(baseHttpResult.byteStream());
                        return  bitmap;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Bitmap>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        callBack.onSuccess(bitmap);
                    }
                });
    }
}
