package com.example.mvpdemo.presenter;

import android.text.TextUtils;

import com.example.mvpdemo.base.IFindPasswordContact;
import com.example.mvpdemo.base.OnDataCallBack;
import com.example.mvpdemo.model.entity.ResponseMessage;
import com.example.mvpdemo.model.entity.ResponseResult2;
import com.example.mvpdemo.model.impl.PasswordFindModelImpl;

/**
 * 注册用户
 * Created by Administrator on 2017/4/2.
 */

public class FindPasswordPresenterImpl implements IFindPasswordContact.Presenter {

    private IFindPasswordContact.View mView;
    private IFindPasswordContact.Model mModel;

    public FindPasswordPresenterImpl(IFindPasswordContact.View view) {
        mView = view;
        mModel = new PasswordFindModelImpl();
    }

    @Override
    public void submit() {
        mView.showLoadingDialog();
        mModel.doRequestFindPassword(mView.getAccount(), mView.getEmail(), mView.getCheckCode(), new OnDataCallBack() {
            @Override
            public void onSuccess(Object result) {
                mView.hideLoadingDialog();
                ResponseResult2<ResponseMessage> messageResponseResult2 = (ResponseResult2<ResponseMessage>) result;
                ResponseMessage data = messageResponseResult2.getData();
                String message = data.getMessage();
                int status = data.getStatus();
                if (1 == status) {
                    mView.jump2ModifyPassword();
                } else {
                    mView.showToast(message);
                }
            }

            @Override
            public void onFail(String errorMsg) {
                mView.hideLoadingDialog();
                mView.showToast(errorMsg);
            }

        });
    }

    @Override
    public void sendCheckCode() {
        mView.showLoadingDialog();
        mModel.doRequestSendCheckCode(mView.getAccount(), mView.getEmail(), new OnDataCallBack() {
            @Override
            public void onSuccess(Object result) {
                mView.hideLoadingDialog();
                ResponseResult2<ResponseMessage> responseResult = (ResponseResult2<ResponseMessage>) result;
                ResponseMessage data = responseResult.getData();
                String message = data.getMessage();
                mView.showToast(message);
            }

            @Override
            public void onFail(String errorMsg) {
                mView.hideLoadingDialog();
                mView.showToast(errorMsg);
            }
        });
    }

    @Override
    public void checkAccountAndEmail() {
        mView.showLoadingDialog();
        mModel.doRequestCheckAccountAndEmail(mView.getAccount(), mView.getEmail(), mView.getCheckCode(),new OnDataCallBack() {
            @Override
            public void onSuccess(Object result) {
                mView.hideLoadingDialog();
                ResponseResult2<ResponseMessage> messageResponseResult = (ResponseResult2<ResponseMessage>) result;
                ResponseMessage data = messageResponseResult.getData();
                String message = data.getMessage();
                int status = data.getStatus();
                if (1 == status) {
                    if (TextUtils.isEmpty(mView.getCheckCode())) {
                        countDown();
                        mView.showToast("已发送");
                        mView.sendCheckCode();
                    } else {
                        mView.jump2ModifyPassword();
                    }
                } else {
                    mView.showToast(message);
                }
            }

            @Override
            public void onFail(String errorMsg) {
                mView.hideLoadingDialog();
                mView.showToast(errorMsg);
            }
        });
    }

    @Override
    public void countDown() {
        mModel.countDown(new OnDataCallBack() {
            @Override
            public void onSuccess(Object result) {
                String data = (String) result;
                if (TextUtils.equals("complete", data)) {
                    mView.countDownEnable(data);
                } else {
                    mView.countDownDisable(data);
                }
            }

            @Override
            public void onFail(String errorMsg) {
                mView.hideLoadingDialog();
                mView.showToast(errorMsg);
            }
        });
    }

    @Override
    public void start() {
        //首页校验邮箱和账号--->验证通过就发送验证码到邮箱 ---> 填写验证码后服务成功进入修改密码界面（共3个服务）
        checkAccountAndEmail();
    }
}
