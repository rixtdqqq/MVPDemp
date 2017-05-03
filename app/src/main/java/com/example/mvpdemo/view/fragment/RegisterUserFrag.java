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
import com.example.mvpdemo.base.IRegisterContact;
import com.example.mvpdemo.presenter.RegisterUserPresenterImpl;
import com.example.mvpdemo.util.Util;
import com.example.mvpdemo.view.base.BaseFragment;
import com.example.mvpdemo.view.base.MVPApp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 注册界面
 */
public class RegisterUserFrag extends BaseFragment implements IRegisterContact.View {


    private EditText etUserName, etPassword, etPasswordConfirm, etEmail, etPhone, etQQ, etWeChat;
    private TextView btnSubmit, btnReset;
    private IRegisterContact.Presenter mPresenter;

    public RegisterUserFrag() {
        // Required empty public constructor
    }

    public static RegisterUserFrag newInstance() {
        Bundle args = new Bundle();
        RegisterUserFrag fragment = new RegisterUserFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void lazyLoad(boolean isVisible) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_register;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView(view);
        initListener();
    }

    @Override
    public String getUserName() {
        return etUserName.getText().toString().trim();
    }

    @Override
    public String getUserPassword() {
        return etPassword.getText().toString().trim();
    }

    @Override
    public String getUserPasswordConfirm() {
        return etPasswordConfirm.getText().toString().trim();
    }

    @Override
    public String getUserEmail() {
        return etEmail.getText().toString().trim();
    }

    @Override
    public String getUserQQ() {
        String qq = etQQ.getText().toString().trim();
        return TextUtils.isEmpty(qq) ? "" : qq;
    }

    @Override
    public String getUserPhone() {
        return etPhone.getText().toString().trim();
    }

    @Override
    public String getUserWeChat() {
        String weChat = etWeChat.getText().toString().trim();
        return TextUtils.isEmpty(weChat) ? "" : weChat;
    }

    @Override
    public String getCreateTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
        return format.format(new Date());
    }

    @Override
    public void reset() {
        etEmail.setText("");
        etQQ.setText("");
        etPassword.setText("");
        etPasswordConfirm.setText("");
        etWeChat.setText("");
        etUserName.setText("");
        etPhone.setText("");
    }

    @Override
    public void back() {
        mActivity.finish();
    }

    @Override
    public void initData() {
        mPresenter = new RegisterUserPresenterImpl(this);
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        Util.initToolbar((Toolbar) view.findViewById(R.id.toolbar), mActivity);
        etEmail = (EditText) view.findViewById(R.id.user_email);
        etQQ = (EditText) view.findViewById(R.id.user_qq);
        etPassword = (EditText) view.findViewById(R.id.user_password);
        etPasswordConfirm = (EditText) view.findViewById(R.id.user_password_confirm);
        etWeChat = (EditText) view.findViewById(R.id.user_we_chat);
        etUserName = (EditText) view.findViewById(R.id.user_name);
        etPhone = (EditText) view.findViewById(R.id.user_phone);
        btnSubmit = (TextView) view.findViewById(R.id.submit);
        btnReset = (TextView) view.findViewById(R.id.reset);
        Util.showSoftKeyBoard(etUserName);
    }

    @Override
    public void initListener() {
        btnReset.setOnClickListener(v -> {
            mPresenter.reset();
        });
        btnSubmit.setOnClickListener(v -> {
            if (canSubmit()) {
                mPresenter.start();
            } else {
                showToast("请将资料补全再提交");
            }
        });
        etPasswordConfirm.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                if (!TextUtils.equals(getUserPassword(), getUserPasswordConfirm())) {
                    showToast("密码输入不一致，请重新输入！");
                }
            }
        });
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(MVPApp.getInstance(), message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean canSubmit() {
        boolean canSubmit = true;
        if (TextUtils.isEmpty(getUserName())) {
            canSubmit = false;
        }
        if (TextUtils.isEmpty(getUserEmail())) {
            canSubmit = false;
        }
        if (TextUtils.isEmpty(getUserPassword())) {
            canSubmit = false;
        }
        if (TextUtils.isEmpty(getUserPasswordConfirm())) {
            canSubmit = false;
        }
        if (!TextUtils.equals(getUserPassword(), getUserPasswordConfirm())) {
            canSubmit = false;
        }
        if (TextUtils.isEmpty(getUserPhone())) {
            canSubmit = false;
        }

        return canSubmit;
    }
}
