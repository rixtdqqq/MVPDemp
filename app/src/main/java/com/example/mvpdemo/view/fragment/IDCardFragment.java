package com.example.mvpdemo.view.fragment;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvpdemo.R;
import com.example.mvpdemo.base.IIDCardContact;
import com.example.mvpdemo.presenter.IDCardPresenterImpl;
import com.example.mvpdemo.util.Util;
import com.example.mvpdemo.view.base.BaseFragment;

/**
 * 身份证信息界面
 */
public class IDCardFragment extends BaseFragment implements IIDCardContact.View {
    /**
     * 身份证信息查询
     */
    Button btnFind;
    /**
     * 身份证泄露查询
     */
    Button btnLoss;
    /**
     * 身份证挂失查询
     */
    Button btnCheck;

    /**
     * 展示查询的内容
     */
    TextView tvContent;

    /**
     * 身份证输入框
     */
    EditText etIDCard;

    ImageView ivGirl;
    Button ivGirlGet;
    /**
     * SwipeRefreshLayout里面需要注意的Api：
     * 1、setOnRefreshListener(OnRefreshListener listener)  设置下拉监听，当用户下拉的时候会去执行回调
     * 2、setColorSchemeColors(int... colors) 设置 进度条的颜色变化，最多可以设置4种颜色
     * 3、setProgressViewOffset(boolean scale, int start, int end) 调整进度条距离屏幕顶部的距离
     * 4、setRefreshing(boolean refreshing) 设置SwipeRefreshLayout当前是否处于刷新状态，一般是在请求数据的时候设置为true，在数据被加载到View中后，设置为false。
     */
    private IIDCardContact.Presenter mPresenter;

    public IDCardFragment() {
        // Required empty public constructor
    }

    public static IDCardFragment newInstance() {
        Bundle args = new Bundle();
        IDCardFragment fragment = new IDCardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView(view);
        initListener();
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        Util.initToolbar((Toolbar) view.findViewById(R.id.toolbar), mActivity);
        btnFind = (Button) view.findViewById(R.id.find);
        btnLoss = (Button) view.findViewById(R.id.loss);
        btnCheck = (Button) view.findViewById(R.id.check);
        ivGirlGet = (Button) view.findViewById(R.id.bitmap);
        tvContent = (TextView) view.findViewById(R.id.content);
        etIDCard = (EditText) view.findViewById(R.id.idcard);
        ivGirl = (ImageView) view.findViewById(R.id.iv_glide);
    }

    @Override
    protected void lazyLoad(boolean isVisible) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_idcard;
    }

    @Override
    public void initData() {
        mPresenter = new IDCardPresenterImpl(this);
    }

    @Override
    public void initListener() {
        btnFind.setOnClickListener(v -> {
            mPresenter.getFind(getIDCard());
        });
        btnLoss.setOnClickListener(v -> {
            mPresenter.getLeak(getIDCard());
        });
        btnCheck.setOnClickListener(v -> {
            mPresenter.getLoss(getIDCard());
        });
        ivGirlGet.setOnClickListener(v -> {
            mPresenter.getGirlBitmap();
//            Picasso.with(getContext())
//                    .load(Constant.BITMAP_PATH)
//                    .error(R.mipmap.splash)
//                    .placeholder(R.mipmap.splash)
//                    .into(ivGirl);
        });
    }

    @Override
    public void showResult(String result) {
        tvContent.setText(result);
    }

    @Override
    public String getIDCard() {
        return etIDCard.getText().toString().trim();
    }

    @Override
    public void showSuccess(String msg) {
        Toast.makeText(getContext().getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailed(String msg) {
        Toast.makeText(getContext().getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showBitmap(Bitmap bitmap) {
        ivGirl.setImageBitmap(bitmap);
    }


    @Override
    public void showToast(String message) {

    }

}
