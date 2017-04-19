package com.example.mvpdemo.model.entity;

/**
 * 注册成功或失败的状态信息
 * Created by Administrator on 2017/4/16.
 */

public class ResponseMessage {
    private int status;
    private String message;

    private ResponseMessage(Builder builder) {
        message = builder.message;
        status = builder.status;
    }

    public static final class Builder {
        private String message;
        private int status;

        public Builder() {
        }

        public Builder message(String val) {
            message = val;
            return this;
        }
        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public ResponseMessage build() {
            return new ResponseMessage(this);
        }
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
