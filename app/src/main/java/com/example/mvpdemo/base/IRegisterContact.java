package com.example.mvpdemo.base;

/**
 * 注册新用户的MVP接口管理类
 * Created by Administrator on 2017/4/16.
 */

public interface IRegisterContact {
    interface View extends BaseMVP.BaseView {
        String getUserName();

        String getUserPassword();

        String getUserPasswordConfirm();

        String getUserEmail();

        String getUserQQ();

        String getUserPhone();

        String getUserWeChat();

        void reset();

        void back();

        boolean canSubmit();
    }

    interface Presenter extends BaseMVP.BasePresenter {
        void submit(String userName, String password, String createTime,
                    String qq, String weChat, String phone, String email);

        void reset();
    }

    interface Model extends BaseMVP.BaseModel {
        /**
         * 注册新用户
         *
         * @param userName   用户名
         * @param password   密码
         * @param createTime 创建时间
         * @param qq         qq号
         * @param weChat     微信号
         * @param phone      电话
         * @param email      邮箱
         * @param callBack   服务数据返回的回调
         */
        void doRequestRegisterUser(String userName, String password, String createTime,
                                   String qq, String weChat, String phone, String email, OnDataCallBack callBack);
    }
}
