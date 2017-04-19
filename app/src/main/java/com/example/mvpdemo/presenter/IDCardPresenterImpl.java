package com.example.mvpdemo.presenter;

import android.graphics.Bitmap;

import com.example.mvpdemo.base.IIDCardContact;
import com.example.mvpdemo.base.OnDataCallBack;
import com.example.mvpdemo.model.impl.IDCardModelImpl;

/**
 * 身份证信息的presenter
 * Created by Administrator on 2017/4/2.
 */

public class IDCardPresenterImpl implements IIDCardContact.Presenter {

    private IIDCardContact.View mView;
    private IIDCardContact.Model mModel;

    public IDCardPresenterImpl(IIDCardContact.View view) {
        mView = view;
        mModel = new IDCardModelImpl();
    }

    @Override
    public void getFind(String idCard) {
        mView.showLoadingDialog();
        mModel.doRequestFind(idCard, mCallBack);
    }

    @Override
    public void getLeak(String idCard) {
        mView.showLoadingDialog();
        mModel.doRequestLeak(idCard, mCallBack);
    }

    @Override
    public void getLoss(String idCard) {
        mView.showLoadingDialog();
        mModel.doRequestLoss(idCard, mCallBack);
    }

    @Override
    public void getGirlBitmap() {
        mView.showLoadingDialog();
        mModel.doRequestBitmap(new OnDataCallBack() {
            @Override
            public void onSuccess( Object result) {
                if (result instanceof Bitmap) {
                    mView.hideLoadingDialog();
                    mView.showBitmap((Bitmap) result);
                }
            }

            @Override
            public void onFail(String errorMsg) {
                mView.hideLoadingDialog();
            }

        });
    }

    private OnDataCallBack mCallBack = new OnDataCallBack() {
        @Override
        public void onSuccess(Object result) {
//            if (200 != resultCode) {
//                mView.showFailed(reason);
//            } else {
//                mView.showResult((String) result);
//            }
        }

        @Override
        public void onFail(String errorMsg) {
            mView.showFailed(errorMsg);
        }

    };

    @Override
    public void start() {

    }
}
