package com.example.mvpdemo.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvpdemo.R;
import com.example.mvpdemo.base.IFindPasswordContact;
import com.example.mvpdemo.presenter.FindPasswordPresenterImpl;
import com.example.mvpdemo.util.Constant;
import com.example.mvpdemo.util.NetworkUtil;
import com.example.mvpdemo.util.Util;
import com.example.mvpdemo.view.activity.ModifyPasswordActivity;
import com.example.mvpdemo.view.base.BaseFragment;
import com.example.mvpdemo.view.base.MVPApp;

/**
 * 找回密码页面
 */
public class FindPasswordFrag extends BaseFragment implements IFindPasswordContact.View {

    private IFindPasswordContact.Presenter mPresenter;
    private EditText etAccount, etEmail, etCheckCode;
    private TextView btnSubmit, btnSendCheckCode;

    public FindPasswordFrag() {
        // Required empty public constructor
    }

    @Override
    protected void lazyLoad(boolean isVisible) {

    }

    public static FindPasswordFrag newInstance() {
        Bundle args = new Bundle();
        FindPasswordFrag fragment = new FindPasswordFrag();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.frag_find_password;
    }

    @Override
    public String getAccount() {
        return etAccount.getText().toString().trim();
    }

    @Override
    public String getEmail() {
        return etEmail.getText().toString().trim();
    }

    @Override
    public String getCheckCode() {
        return etCheckCode.getText().toString().trim();
    }

    @Override
    public boolean canSubmit() {
        boolean canSubmit = true;
        if (TextUtils.isEmpty(getAccount())) {
            canSubmit = false;
        }
        if (TextUtils.isEmpty(getEmail())) {
            canSubmit = false;
        }
        return canSubmit;
    }

    /**
     * 验证码不能为空
     */
    private boolean checkCodeNotNull() {
        return !TextUtils.isEmpty(getCheckCode());
    }

    @Override
    public void submit() {
        if (NetworkUtil.isNetworkAvailable(mActivity)) {
            if (canSubmit() && checkCodeNotNull()) {
                mPresenter.submit();
            } else {
                showToast("请您正确填写资料以便找回密码！");
            }
        } else {
            showToast(getString(R.string.network_interruption));
        }
    }

    @Override
    public void initData() {
        mPresenter = new FindPasswordPresenterImpl(this);
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        Util.initToolbar((Toolbar) view.findViewById(R.id.toolbar), mActivity);
        etAccount = (EditText) view.findViewById(R.id.user_name);
        etCheckCode = (EditText) view.findViewById(R.id.check_code);
        etEmail = (EditText) view.findViewById(R.id.user_email);
        btnSendCheckCode = (TextView) view.findViewById(R.id.send_check_code);
        btnSubmit = (TextView) view.findViewById(R.id.submit);
        Util.showSoftKeyBoard(etAccount);
    }

    @Override
    public void initListener() {
        btnSendCheckCode.setOnClickListener(v -> {
            if (NetworkUtil.isNetworkAvailable(mActivity)) {
                if (canSubmit()) {
                    mPresenter.start();
                } else {
                    showToast("请您填写账号和邮箱");
                }
            } else {
                showToast(getString(R.string.network_interruption));
            }
        });
        btnSubmit.setOnClickListener(v -> {
            submit();
        });
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(MVPApp.getInstance(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sendCheckCode() {
        if (NetworkUtil.isNetworkAvailable(mActivity)) {
            if (canSubmit()) {
                mPresenter.sendCheckCode();
            } else {
                showToast("请您填写资料以便找回密码！");
            }
        } else {
            showToast(getString(R.string.network_interruption));
        }
    }

    @Override
    public void countDownDisable(String data) {
        btnSendCheckCode.setEnabled(false);
        btnSendCheckCode.setText(data);
        btnSendCheckCode.setTextColor(mActivity.getResources().getColor(android.R.color.darker_gray));
    }

    @Override
    public void countDownEnable(String data) {
        btnSendCheckCode.setEnabled(true);
        btnSendCheckCode.setText("发送验证码");
        btnSendCheckCode.setTextColor(mActivity.getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void jump2ModifyPassword() {
        Intent intent = new Intent(mActivity, ModifyPasswordActivity.class);
        intent.putExtra(Constant.ACCOUNT, getAccount());
        mActivity.startActivity(intent);
        mActivity.finish();
    }
}
