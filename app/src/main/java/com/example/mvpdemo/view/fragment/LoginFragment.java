package com.example.mvpdemo.view.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvpdemo.R;
import com.example.mvpdemo.base.ILoginContact;
import com.example.mvpdemo.presenter.LoginPresenterImpl;
import com.example.mvpdemo.util.Constant;
import com.example.mvpdemo.util.NetworkUtil;
import com.example.mvpdemo.view.activity.MainActivity;
import com.example.mvpdemo.view.base.BaseFragment;
import com.example.mvpdemo.view.base.MVPApp;

/**
 * 登录界面
 */
public class LoginFragment extends BaseFragment implements ILoginContact.ILoginView {


    private ILoginContact.ILoginPresenter mPresenter;
    private Button btnLogin;
    private TextInputEditText etUserName;
    private TextInputEditText etPassword;
    private TextView btnRegisterUser, btnFindPassword;


    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initListener();
    }

    @Override
    public void initData() {
        mPresenter = new LoginPresenterImpl(this);
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        btnLogin = (Button) view.findViewById(R.id.login_btn);
        etUserName = (TextInputEditText) view.findViewById(R.id.user_name);
        etPassword = (TextInputEditText) view.findViewById(R.id.user_pwd);
        btnFindPassword = (TextView) view.findViewById(R.id.find_password);
        btnRegisterUser = (TextView) view.findViewById(R.id.new_user);
    }

    @Override
    protected void lazyLoad(boolean isVisible) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void initListener() {
        btnLogin.setOnClickListener((v) -> {
            login();
        });
        etPassword.setOnEditorActionListener((view, actionId, event) -> {
           login();
            return true;
        });
        btnFindPassword.setOnClickListener(v -> {
            jump2FindPassword();
        });
        btnRegisterUser.setOnClickListener(v -> {
            jump2RegisterUser();
        });
    }

    @Override
    public String getUserName() {
        return etUserName.getText().toString();
    }

    @Override
    public String getUserPassword() {
        return etPassword.getText().toString();
    }

    @Override
    public void jump2Main() {
        Intent intent = new Intent(mActivity, MainActivity.class);
        intent.putExtra(Constant.FRAGMENT_FLAG, Constant.RECYCLER_VIEW_PULL_UP_DOWN_FRAG);
        mActivity.startActivity(intent);
        mActivity.finish();
    }

    @Override
    public void jump2RegisterUser() {
        Intent intent = new Intent(mActivity, MainActivity.class);
        intent.putExtra(Constant.FRAGMENT_FLAG, Constant.REGISTER_USER_FRAG);
        mActivity.startActivityForResult(intent, 10);
    }

    @Override
    public void jump2FindPassword() {
        Intent intent = new Intent(mActivity, MainActivity.class);
        intent.putExtra(Constant.FRAGMENT_FLAG, Constant.FIND_PASSWORD_FRAG);
        mActivity.startActivity(intent);
    }

    @Override
    public boolean canLogin() {
        boolean canLogin = true;
        if (TextUtils.isEmpty(getUserName())) {
            canLogin = false;
        }
        if (TextUtils.isEmpty(getUserPassword())) {
            canLogin = false;
        }
        return canLogin;
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(MVPApp.getInstance(), message, Toast.LENGTH_SHORT).show();
    }

    private void login() {
        if (NetworkUtil.isNetworkAvailable(mActivity)) {
            if (canLogin()) {
                mPresenter.start();
            } else {
                showToast("请输入用户名或密码");
            }
        } else {
            showToast("网络异常");
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (10 == requestCode && Activity.RESULT_OK == resultCode) {
            String userName = data.getStringExtra(Constant.ACCOUNT);
            if (!TextUtils.isEmpty(userName)) {
                etUserName.setText(userName);
            }
        }
    }
}
