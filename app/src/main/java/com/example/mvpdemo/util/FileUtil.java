package com.example.mvpdemo.util;

import android.os.Environment;

/**
 * 文件工具类
 * Created by Administrator on 2017/4/17.
 */

public final class FileUtil {
    /**
     * 是否有SDCard
     */
    public static boolean hasSDCard() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }
}
