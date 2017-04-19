package com.example.mvpdemo.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.mvpdemo.R;
import com.example.mvpdemo.view.base.BaseActivity;
import com.example.mvpdemo.view.base.BaseFragment;
import com.example.mvpdemo.view.fragment.LoginFragment;

public class LoginActivity extends BaseActivity {

    private BaseFragment mFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadFragment();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_login;
    }

    private void loadFragment() {
        if (null == mFragment) {
            mFragment = LoginFragment.newInstance();
        }
        changeFragment();
    }

    private void changeFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container, mFragment);
        transaction.commit();
    }
}
