package com.example.mvpdemo.view.base;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;

import com.example.mvpdemo.util.Constant;
import com.example.mvpdemo.util.CrashHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * activity基类
 * Created by Administrator on 2017/3/19.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public static String phoneIMEI,deviceSoftwareVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isPermissionsAllGranted(Constant.PERMISSION_ARRAY, Constant.GRANT_ALL);

        CrashHandler.getInstance().init();
        setContentView(getContentViewId());
    }

    public abstract int getContentViewId();

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        if (null != tm) {
            phoneIMEI = tm.getDeviceId();
            deviceSoftwareVersion = tm.getDeviceSoftwareVersion();
        }
    }

    protected boolean isPermissionGranted(String permissionName, int requestCode) {
        //6.0以下系统，取消请求权限
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        //判断是否需要请求允许权限，否则请求用户授权
        int hasPermission = checkSelfPermission(permissionName);
        if (hasPermission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{permissionName}, requestCode);
            return false;
        }
        return true;
    }

    protected boolean isPermissionsAllGranted(String[] permArray, int questCode) {
        //6.0以下系统，取消请求权限
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        //获得批量请求但被禁止的权限列表
        List<String> deniedPerms = new ArrayList<>();
        for (int i = 0; permArray != null && i < permArray.length; i++) {
            if (PackageManager.PERMISSION_GRANTED != checkSelfPermission(permArray[i])) {
                deniedPerms.add(permArray[i]);
            }
        }
        int denyPermNum = deniedPerms.size();
        //进行批量请求
        if (denyPermNum != 0) {
            requestPermissions(deniedPerms.toArray(new String[denyPermNum]), questCode);
            return false;
        }
        return true;
    }
}
