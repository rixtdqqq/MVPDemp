package com.example.mvpdemo.view.base;

import android.app.Application;
import android.content.Context;

/**
 * 程序入口，当程序运行时执行的第一个类
 * Created by Administrator on 2017/3/19.
 */

public class MVPApp extends Application {

    private static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static Context getInstance() {
        return sContext;
    }
}
