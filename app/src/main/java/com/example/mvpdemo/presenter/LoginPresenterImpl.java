package com.example.mvpdemo.presenter;

import com.example.mvpdemo.base.ILoginContact;
import com.example.mvpdemo.base.OnDataCallBack;
import com.example.mvpdemo.model.entity.ResponseMessage;
import com.example.mvpdemo.model.entity.ResponseResult2;
import com.example.mvpdemo.model.impl.LoginModelImpl;

/**
 * 登录控制器实现类
 * Created by Administrator on 2017/3/19.
 */

public class LoginPresenterImpl implements ILoginContact.ILoginPresenter {
    private ILoginContact.ILoginModel mLoginModel;
    private ILoginContact.ILoginView mLoginView;

    public LoginPresenterImpl(ILoginContact.ILoginView loginView) {
        mLoginView = loginView;
        mLoginModel = new LoginModelImpl();
    }

    @Override
    public void login(String userName, String password) {
        mLoginView.showLoadingDialog();
        mLoginModel.doRequestLogin(userName, password, new OnDataCallBack() {
            @Override
            public void onSuccess(Object result) {
                ResponseResult2<ResponseMessage> responseMessage = (ResponseResult2<ResponseMessage>) result;
                ResponseMessage data = responseMessage.getData();
                int status = data.getStatus();
                if (1 == status) {
                    mLoginView.jump2Main();
                }
                mLoginView.showToast(data.getMessage());
                mLoginView.hideLoadingDialog();
            }

            @Override
            public void onFail(String errorMsg) {
                mLoginView.hideLoadingDialog();
                mLoginView.showToast(errorMsg);
            }

        });
    }

    @Override
    public void start() {
        login(mLoginView.getUserName(),mLoginView.getUserPassword());
    }
}
