package com.example.mvpdemo.util;

import android.util.Log;

import com.example.mvpdemo.BuildConfig;

/**
 * 打印日志工具类
 * Created by Administrator on 2017/4/2.
 */

public final class LogUtil {
    private static final boolean isDebug = BuildConfig.DEBUG;

    /**
     * 打印一个debug等级的 log
     */
    public static void d(String tag, String msg) {
        if (isDebug) {
            Log.d("mvp_" + tag, msg);
        }
    }

    /**
     * 打印一个debug等级的 log
     */
    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e("mvp_" + tag, msg);
        }
    }

    /**
     * 打印一个debug等级的 log
     */
    public static void e(Class cls, String msg) {
        if (isDebug) {
            Log.e("mvp_" + cls.getSimpleName(), msg);
        }
    }
}
