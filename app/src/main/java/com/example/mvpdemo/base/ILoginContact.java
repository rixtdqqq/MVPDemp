package com.example.mvpdemo.base;

/**
 * 登录页面MVP管理接口
 * Created by Administrator on 2017/3/26.
 */

public interface ILoginContact {
    interface ILoginView extends BaseMVP.BaseView {
        /**
         * 获取用户名
         */
        String getUserName();

        /**
         * 获取用户密码
         */
        String getUserPassword();

        /**
         * 跳转到主页面
         */
        void jump2Main();

        /**
         * 跳转到注册新用户页面
         */
        void jump2RegisterUser();

        /**
         * 跳转到找回密码页面
         */
        void jump2FindPassword();

        /**
         * 判断是否可以登录操作
         */
        boolean canLogin();

    }

    interface ILoginModel extends BaseMVP.BaseModel {
        /**
         * 登录的业务
         */
        void doRequestLogin(String userName, String password, OnDataCallBack callBack);
    }

    interface ILoginPresenter extends BaseMVP.BasePresenter {
        /**
         * 登录：登录页面只有一个业务操作，登录需要用户名和密码两个参数
         */
        void login();
    }
}
