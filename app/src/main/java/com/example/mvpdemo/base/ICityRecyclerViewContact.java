package com.example.mvpdemo.base;

import android.graphics.Bitmap;

import com.example.mvpdemo.model.entity.City;

import java.util.List;

/**
 * 城市信息MVP接口管理类
 * Created by Administrator on 2017/4/2.
 */

public interface ICityRecyclerViewContact {
    interface View extends BaseMVP.BaseView {
        /**
         * 展示城市列表
         * @param cities 城市列表数据
         */
        void showCityListView(List<City> cities,boolean hasMore);

        /**
         * 获取国家名称
         */
        String getCountryName();

        /**
         * 获取国家编码
         */
        String getCountryCode();

        /**
         * 获取国家的地区
         */
        String getDistrict();

    }

    interface Presenter extends BaseMVP.BasePresenter {
        /**
         * 处理城市数据和view的逻辑
         */
        void dealCityData(int pageSize, int pageNum, String countryName, String countryCode, String district);
    }

    interface Model extends BaseMVP.BaseModel {
        /**
         * 获取城市信息服务
         *
         * @param pageSize    默认为每页10条数据
         * @param pageNum     默认为第一页
         * @param countryName 国家名 eg.Utrecht
         * @param countryCode 国家编码 eg.NLD
         * @param district    地区名eg.Utrecht
         * @param callBack    从服务获取数据返回的回调
         */
        void doRequestCity(int pageSize, int pageNum, String countryName, String countryCode, String district, OnDataCallBack callBack);
    }
}
