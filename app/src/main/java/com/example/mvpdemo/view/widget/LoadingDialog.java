package com.example.mvpdemo.view.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.example.mvpdemo.R;

/**
 * 自定义的加载框
 * Created by Administrator on 2017/4/4.
 */

public class LoadingDialog extends Dialog {
    public LoadingDialog(@NonNull Context context) {
        super(context,R.style.LoadingDialogStyle);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_view,null);
        super.setContentView(view);
    }
}
