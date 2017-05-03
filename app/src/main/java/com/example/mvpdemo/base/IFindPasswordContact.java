package com.example.mvpdemo.base;

/**
 * 找回密码的MVP接口管理类
 * Created by Administrator on 2017/4/16.
 */

public interface IFindPasswordContact {
    interface View extends BaseMVP.BaseView {
        String getAccount();

        String getEmail();

        String getCheckCode();

        boolean canSubmit();

        void submit();

        /**
         * 发送验证码
         */
        void sendCheckCode();

        /**
         * 倒数按钮不可用
         */
        void countDownDisable(String data);

        /**
         * 倒数按钮可用
         */
        void countDownEnable(String data);

        void jump2ModifyPassword();
    }

    interface Presenter extends BaseMVP.BasePresenter {
        void submit();

        void sendCheckCode();

        void checkAccountAndEmail();

        void countDown();
    }

    interface Model extends BaseMVP.BaseModel {

        /**
         * 找回密码
         */
        void doRequestFindPassword(String account, String email, String checkCode, OnDataCallBack callBack);

        /**
         * 发送验证码
         */
        void doRequestSendCheckCode(String account, String email, OnDataCallBack callBack);

        /**
         * 校验邮箱和账号、验证码
         */
        void doRequestCheckAccountAndEmail(String userName, String email, String checkCode, OnDataCallBack callBack);

        /**
         * 倒数按钮的读秒
         */
        void countDown(OnDataCallBack callBack);
    }
}
