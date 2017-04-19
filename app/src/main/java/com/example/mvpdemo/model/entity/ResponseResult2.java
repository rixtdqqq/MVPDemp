package com.example.mvpdemo.model.entity;

/**
 * 服务数据返回字段
 * Created by Administrator on 2017/4/2.
 */

public class ResponseResult2<T> {
    private int status;
    private String message;
    private T data;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
