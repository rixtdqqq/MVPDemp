package com.example.mvpdemo.view.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * activity基类
 * Created by Administrator on 2017/3/19.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
//        ButterKnife.bind(this);
    }

    public abstract int getContentViewId();
}
