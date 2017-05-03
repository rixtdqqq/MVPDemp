package com.example.mvpdemo.util;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.mvpdemo.view.activity.LoginActivity;
import com.example.mvpdemo.view.activity.MainActivity;
import com.example.mvpdemo.view.base.BaseActivity;
import com.example.mvpdemo.view.base.BaseFragment;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 工具类
 * Created by Administrator on 2017/3/19.
 */

public final class Util {
    private Util() {
    }

    private static FragmentManager sManager;

    /**
     * IllegalStateException: Can not perform this action after onSaveInstanceState
     * 解决办法是transaction.commit()替换成transaction.commitAllowingStateLoss()
     */
    public static void loadFragment(Context context, BaseFragment fragment, int containerId) {
        BaseActivity activity;
        if (context instanceof LoginActivity) {
            activity = (LoginActivity) context;
        } else if (context instanceof MainActivity) {
            activity = (MainActivity) context;
        } else {
            activity = (BaseActivity) context;
        }
        if (null == sManager) {
            sManager = activity.getSupportFragmentManager();
        }
        FragmentTransaction transaction = sManager.beginTransaction();
        transaction.add(containerId, fragment);
        transaction.commitAllowingStateLoss();
    }

    /**
     * 初始化toolbar
     */
    public static void initToolbar(Toolbar toolbar, BaseActivity activity) {
        activity.setSupportActionBar(toolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        toolbar.setNavigationOnClickListener(v -> {
            activity.finish();
        });
    }

    /**
     * 获取屏幕的宽高
     */
    public static int[] getDisplayScreenWH(Context context) {
        int[] wh = new int[2];
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wh[0] = windowManager.getDefaultDisplay().getWidth();
        wh[1] = windowManager.getDefaultDisplay().getHeight();
        return wh;
    }

    /**
     * 对于刚跳到一个新的界面就要弹出软键盘的情况上述代码可能由于界面未加载完全而无法弹出软键盘。此时应该适当的延迟弹出软键盘如500毫秒（保证界面的数据加载完成）
     */
    public static void showSoftKeyBoard(EditText editText) {
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
                           @Override
                           public void run() {
                               InputMethodManager inputManager =
                                       (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                               inputManager.showSoftInput(editText, 0);
                           }

                       },
                500);
    }

    /**
     * 对于刚跳到一个新的界面就要弹出软键盘的情况上述代码可能由于界面未加载完全而无法弹出软键盘。此时应该适当的延迟弹出软键盘如500毫秒（保证界面的数据加载完成）
     */
    public static void hideSoftKeyBoard(EditText editText) {
        InputMethodManager inputManager =
                (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

}
