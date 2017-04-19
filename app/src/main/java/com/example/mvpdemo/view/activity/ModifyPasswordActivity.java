package com.example.mvpdemo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.mvpdemo.R;
import com.example.mvpdemo.util.Constant;
import com.example.mvpdemo.view.base.BaseActivity;
import com.example.mvpdemo.view.base.BaseFragment;
import com.example.mvpdemo.view.fragment.ModifyPasswordFrg;

/**
 * 修改密码
 */
public class ModifyPasswordActivity extends BaseActivity {

    private BaseFragment mBaseFragment;
    private String account = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentData();
        loadFindPasswordFrag();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constant.ACCOUNT)) {
            account = intent.getStringExtra(Constant.ACCOUNT);
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_modify_password;
    }

    /**
     * 跳转到找回密码页面
     */
    private void loadFindPasswordFrag() {
        if (null == mBaseFragment) {
            mBaseFragment = ModifyPasswordFrg.newInstance(account);
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
