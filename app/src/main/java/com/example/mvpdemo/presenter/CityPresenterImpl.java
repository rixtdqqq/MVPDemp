package com.example.mvpdemo.presenter;

import com.example.mvpdemo.base.ICityRecyclerViewContact;
import com.example.mvpdemo.base.OnDataCallBack;
import com.example.mvpdemo.model.entity.City;
import com.example.mvpdemo.model.entity.Page;
import com.example.mvpdemo.model.entity.ResponseResult2;
import com.example.mvpdemo.model.impl.CityModelImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * 身份证信息的presenter
 * Created by Administrator on 2017/4/2.
 */

public class CityPresenterImpl implements ICityRecyclerViewContact.Presenter {

    private ICityRecyclerViewContact.View mView;
    private ICityRecyclerViewContact.Model mModel;
    private boolean hasMore = false;

    public CityPresenterImpl(ICityRecyclerViewContact.View view) {
        mView = view;
        mModel = new CityModelImpl();
    }

    @Override
    public void dealCityData(int pageSize, int pageNum, String countryName, String countryCode, String district) {
        mModel.doRequestCity(pageSize, pageNum, countryName, countryCode, district, new OnDataCallBack() {
            @Override
            public void onSuccess(Object result) {
                mView.hideLoadingDialog();
                List<City> cities = parseCityData(result, pageSize);
                if (null != cities && !cities.isEmpty()) {
                    mView.showCityListView(cities, hasMore);
                }
            }

            @Override
            public void onFail(String errorMsg) {
                mView.hideLoadingDialog();
                mView.showToast(errorMsg);
            }

        });
    }

    private List<City> parseCityData(Object result, int pageSize) {
        ResponseResult2<Page<City>> responseResult = (ResponseResult2<Page<City>>) result;
        List<City> cities = new ArrayList<>();
        Page<City> cityPage = responseResult.getData();
        int resultCode = responseResult.getStatus();
        if (0 == resultCode || 99 == resultCode) {
            mView.showToast(responseResult.getMessage());
            mView.hideLoadingDialog();
        } else if (1 == resultCode) {
            cities.addAll(cityPage.getData());
            int totalRecord = cityPage.getTotalRecord();
            int currentPage = cityPage.getCurrentPage();
            hasMore = currentPage * pageSize < totalRecord;
        }
        return cities;
    }

    @Override
    public void start() {

    }
}
