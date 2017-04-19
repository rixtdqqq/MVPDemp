package com.example.mvpdemo.base;

/**
 * 修改密码MVP接口管理类
 * Created by Administrator on 2017/4/2.
 */

public interface IModifyPasswordContact {
    interface View extends BaseMVP.BaseView {
        String getAccount();

        String getPassword();

        String getPasswordConfirm();

        void finish();

    }

    interface Presenter extends BaseMVP.BasePresenter {
        void modifyPassword(String account, String password);
    }

    interface Model extends BaseMVP.BaseModel {
        void doRequestModifyPassword(String account, String password, OnDataCallBack callBack);
    }
}
