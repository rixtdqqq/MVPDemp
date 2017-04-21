package com.example.mvpdemo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import com.example.mvpdemo.R;
import com.example.mvpdemo.util.Constant;
import com.example.mvpdemo.view.base.BaseActivity;
import com.example.mvpdemo.view.base.BaseFragment;
import com.example.mvpdemo.view.fragment.FindPasswordFrag;
import com.example.mvpdemo.view.fragment.RegisterUserFrag;

/**
 * 注册用户、找回密码界面容器
 */
public class RegisterAndFindActivity extends BaseActivity {

    private BaseFragment mBaseFragment;
    private String fragmentFlag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentData();
        loadFragment();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constant.FRAGMENT_FLAG)) {
            fragmentFlag = intent.getStringExtra(Constant.FRAGMENT_FLAG);
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_register_and_find;
    }

    private void loadFragment() {
        if (TextUtils.equals(Constant.REGISTER_USER_FRAG, fragmentFlag)) {//注册页面
            loadRegisterFrag();
        } else if (TextUtils.equals(Constant.FIND_PASSWORD_FRAG, fragmentFlag)) { //找回密码页面
            loadFindPasswordFrag();
        }
    }

    /**
     * 跳转到注册页面
     */
    private void loadRegisterFrag() {
        if (null == mBaseFragment) {
            mBaseFragment = RegisterUserFrag.newInstance();
        }
        changeFragment(mBaseFragment);
    }

    /**
     * 跳转到找回密码页面
     */
    private void loadFindPasswordFrag() {
        if (null == mBaseFragment) {
            mBaseFragment = FindPasswordFrag.newInstance();
        }
        changeFragment(mBaseFragment);
    }

    /**
     * 加载fragment
     */
    private void changeFragment(BaseFragment baseFragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container, baseFragment);
        transaction.commit();
    }
}
