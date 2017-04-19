package com.example.mvpdemo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvpdemo.R;
import com.example.mvpdemo.base.IModifyPasswordContact;
import com.example.mvpdemo.presenter.ModifyPasswordPresenterImpl;
import com.example.mvpdemo.util.Constant;
import com.example.mvpdemo.util.NetworkUtil;
import com.example.mvpdemo.util.Util;
import com.example.mvpdemo.view.base.BaseFragment;
import com.example.mvpdemo.view.base.MVPApp;

/**
 * 修改密码界面
 * Created by Administrator on 2017/4/17.
 */

public class ModifyPasswordFrg extends BaseFragment implements IModifyPasswordContact.View {

    private EditText etPassword, etPasswordConfirm;
    private TextView tvAccount, tvSubmit;
    private IModifyPasswordContact.Presenter mPresenter;
    private String account;

    public ModifyPasswordFrg() {
    }

    public static ModifyPasswordFrg newInstance(String account) {
        Bundle args = new Bundle();
        args.putString(Constant.ACCOUNT, account);
        ModifyPasswordFrg fragment = new ModifyPasswordFrg();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void lazyLoad(boolean isVisible) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_modify_password;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView(view);
        initListener();
    }

    @Override
    public void initData() {
        Bundle arguments = getArguments();
        account = arguments.getString(Constant.ACCOUNT, "");
        mPresenter = new ModifyPasswordPresenterImpl(this);
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        Util.initToolbar((Toolbar) view.findViewById(R.id.toolbar), mActivity);
        tvAccount = (TextView) view.findViewById(R.id.user_name);
        etPassword = (EditText) view.findViewById(R.id.user_password);
        etPasswordConfirm = (EditText) view.findViewById(R.id.user_password_confirm);
        tvSubmit = (TextView) view.findViewById(R.id.submit);
        tvAccount.setText(account);
    }

    @Override
    public void initListener() {
        tvSubmit.setOnClickListener(v -> {
            if (NetworkUtil.isNetworkAvailable(mActivity)) {
                if (TextUtils.equals(getPassword(), getPasswordConfirm())) {
                    mPresenter.start();
                } else {
                    showToast("密码输入不一致，请重新输入！");
                }
            } else {
                showToast(getString(R.string.network_interruption));
            }

        });
        etPasswordConfirm.setOnFocusChangeListener((v, hasFocus) -> {
            if (!TextUtils.equals(getPassword(), getPasswordConfirm())) {
                showToast("密码输入不一致，请重新输入！");
            }
        });

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(MVPApp.getInstance(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getAccount() {
        return tvAccount.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString().trim();
    }

    @Override
    public String getPasswordConfirm() {
        return etPasswordConfirm.getText().toString().trim();
    }

    @Override
    public void finish() {
        mActivity.finish();
    }
}
