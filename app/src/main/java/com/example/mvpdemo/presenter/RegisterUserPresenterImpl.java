package com.example.mvpdemo.presenter;

import com.example.mvpdemo.base.IRegisterContact;
import com.example.mvpdemo.base.OnDataCallBack;
import com.example.mvpdemo.model.entity.ResponseMessage;
import com.example.mvpdemo.model.entity.ResponseResult2;
import com.example.mvpdemo.model.impl.UserRegisterModelImpl;

/**
 * 注册用户
 * Created by Administrator on 2017/4/2.
 */

public class RegisterUserPresenterImpl implements IRegisterContact.Presenter {

    private IRegisterContact.View mView;
    private IRegisterContact.Model mModel;

    public RegisterUserPresenterImpl(IRegisterContact.View view) {
        mView = view;
        mModel = new UserRegisterModelImpl();
    }

    @Override
    public void submit(String userName, String password, String createTime,
                       String qq, String weChat, String phone, String email) {
        mView.showLoadingDialog();
        mModel.doRequestRegisterUser(userName, password, createTime, qq, weChat, phone, email, new OnDataCallBack() {
            @Override
            public void onSuccess(Object result) {
                ResponseResult2<ResponseMessage> data = (ResponseResult2<ResponseMessage>) result;
                ResponseMessage responseMessage = data.getData();
                String message = responseMessage.getMessage();
                if (1 == responseMessage.getStatus()) {
                    mView.back();
                }
                mView.showToast(message);
            }

            @Override
            public void onFail(String errorMsg) {
            }

        });
    }

    @Override
    public void reset() {
        mView.reset();
    }

    @Override
    public void start() {

    }
}
