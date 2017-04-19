package com.example.mvpdemo.presenter;

import com.example.mvpdemo.base.IModifyPasswordContact;
import com.example.mvpdemo.base.OnDataCallBack;
import com.example.mvpdemo.model.entity.ResponseMessage;
import com.example.mvpdemo.model.entity.ResponseResult2;
import com.example.mvpdemo.model.impl.ModifyPasswordModelImpl;

/**
 * 注册用户
 * Created by Administrator on 2017/4/2.
 */

public class ModifyPasswordPresenterImpl implements IModifyPasswordContact.Presenter {

    private IModifyPasswordContact.View mView;
    private IModifyPasswordContact.Model mModel;

    public ModifyPasswordPresenterImpl(IModifyPasswordContact.View view) {
        mView = view;
        mModel = new ModifyPasswordModelImpl();
    }

    @Override
    public void start() {
        modifyPassword(mView.getAccount(), mView.getPassword());
    }

    @Override
    public void modifyPassword(String account, String password) {
        mView.showLoadingDialog();
        mModel.doRequestModifyPassword(account, password, new OnDataCallBack() {
            @Override
            public void onSuccess(Object result) {
                mView.hideLoadingDialog();
                ResponseResult2<ResponseMessage> res = (ResponseResult2<ResponseMessage>) result;
                ResponseMessage data = res.getData();
                if (1 == data.getStatus()) {
                    mView.finish();
                }
                mView.showToast(data.getMessage());
            }

            @Override
            public void onFail(String errorMsg) {
                mView.hideLoadingDialog();
                mView.showToast(errorMsg);
            }
        });
    }
}
