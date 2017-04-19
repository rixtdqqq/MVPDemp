package com.example.mvpdemo.model.entity;

/**
 * 服务数据返回字段
 * Created by Administrator on 2017/4/2.
 */

public class ResponseResult<T> {
    private int resultcode;
    private String reason;
    private T result;

    public int getResultCode() {
        return resultcode;
    }


    public String getReason() {
        return reason;
    }


    public T getResult() {
        return result;
    }


    @Override
    public String toString() {
        return "ResponseResult{" +
                "resultCode=" + resultcode +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                '}';
    }
}
