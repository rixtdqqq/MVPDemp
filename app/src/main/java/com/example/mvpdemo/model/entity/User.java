package com.example.mvpdemo.model.entity;

/**
 * 用户实体
 * Created by Administrator on 2017/3/19.
 */

public class User {
    /**
     * 用户名
     */
    private final String userName;
    /**
     * 用户密码
     */
    private final String password;


    private User(Builder builder) {
        userName = builder.userName;
        password = builder.password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public static final class Builder {
        private String userName;
        private String password;

        public Builder(String userName,String password) {
            this.userName = userName;
            this.password = password;
        }

        public User build() {
            return new User(this);
        }
    }
}
