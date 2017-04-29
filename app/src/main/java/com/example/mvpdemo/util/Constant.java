package com.example.mvpdemo.util;

import android.Manifest;

/**
 * 常量类
 * Created by Administrator on 2017/3/19.
 */

public final class Constant {

    /**
     * fragment的标记，用于判断加载哪个fragment
     */
    public static final String FRAGMENT_FLAG = "fragment_flag";
    /**
     * 聚合数据的身份证apiKey
     */
    public static final String JUHE_API_KEY = "d80ccaa9a4f37bdd69e8e70a745a76d0";
    /**
     * 数据类型
     */
    public static final String JUHE_DATA_TYPE = "json";

    public static final String ID_CARD_BASE_URL = "http://apis.juhe.cn/idcard/";

    public static final String LOCAL_SERVICE = "http://192.168.1.102:8080/WebProject/";

    public static final String BITMAP_PATH = "http://pic41.nipic.com/20140509/4746986_145156378323_2.jpg";
    /**
     * 注册页面
     */
    public static final String REGISTER_USER_FRAG = "RegisterUserFrag";
    /**
     * 下拉刷新与上拉加载页面
     */
    public static final String RECYCLER_VIEW_PULL_UP_DOWN_FRAG = "RecyclerViewPullUpDownFrag";
    /**
     * 找回密码页面
     */
    public static final String FIND_PASSWORD_FRAG = "FindPasswordFrag";
    /**
     * 倒计时时间
     */
    public static final int COUNT_DOWN = 60;
    /**
     * 账号key
     */
    public static final String ACCOUNT = "account";
    /**
     * 高德地图的key
     */
    public static final String AMAP_KEY = "d33209a0a62fc61b82e8f60b3433569a";
    /**
     * 权限数组
     */
    public static final String[] PERMISSION_ARRAY = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE};
    /**
     * 所有权限的requestCode
     */
    public static final int GRANT_ALL = 986;
    /**
     * 页数大小
     */
    public static final int PAGE_SIZE = 10;
}
