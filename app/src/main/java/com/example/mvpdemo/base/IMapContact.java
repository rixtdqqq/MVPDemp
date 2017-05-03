package com.example.mvpdemo.base;

/**
 * 身份证信息MVP接口管理类
 * Created by Administrator on 2017/4/2.
 */

public interface IMapContact {
    interface View extends BaseMVP.BaseView {
        /**
         * 展示地图类型：卫星地图，普通地图，搜索地图等
         */
        void showMapTypeDialog(android.view.View view);

        /**
         * 获取搜索条件
         */
        String getSearchAddition();

        /**
         * 隐藏搜索控件
         */
        void hideSearchView();
        /**
         * 显示搜索控件
         */
        void showSearchView();

    }

    interface Presenter extends BaseMVP.BasePresenter {

    }

    interface Model extends BaseMVP.BaseModel {

    }
}
