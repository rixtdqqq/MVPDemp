package com.example.mvpdemo.view.base;

import android.app.Application;
import android.content.Context;
import android.telephony.TelephonyManager;

import com.example.mvpdemo.util.CrashHandler;

/**
 * 程序入口，当程序运行时执行的第一个类
 * Created by Administrator on 2017/3/19.
 */

public class MVPApp extends Application {

    private static Context sContext;
    public static String phoneIMEI;
    public static String deviceSoftwareVersion;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        CrashHandler.getInstance().init(this);
        TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
//        phoneIMEI = tm.getDeviceId();
//        deviceSoftwareVersion = tm.getDeviceSoftwareVersion();
    }

    public static Context getInstance() {
        return sContext;
    }
}
