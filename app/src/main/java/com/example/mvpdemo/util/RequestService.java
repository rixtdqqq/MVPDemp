package com.example.mvpdemo.util;

import com.example.mvpdemo.base.HttpService;
import com.example.mvpdemo.view.base.MVPApp;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 请求服务类
 * Created by Administrator on 2017/4/2.
 */

public final class RequestService {
    private static OkHttpClient client;
    private static volatile Retrofit retrofit;
    private static HttpService httpService;

    /**
     * @param local 本地服务
     * @return retrofit的底层利用反射的方式, 获取所有的api接口的类
     */
    public static HttpService getHttpService(boolean local) {
        if (httpService == null) {
            httpService = getRetrofit(local).create(HttpService.class);
        }
        return httpService;
    }

    private static Retrofit getRetrofit(boolean local) {
        if (null == retrofit) {
            synchronized (RequestService.class) {
                if (null == retrofit) {
                    //添加一个log拦截器,打印所有的log
                    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                    //可以设置请求过滤的水平,body,basic,headers
                    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    //设置 请求的缓存的大小跟位置
                    File cacheFile = new File(MVPApp.getInstance().getCacheDir(), "cache");
                    Cache cache = new Cache(cacheFile, 1024 * 1024 * 50); //50Mb 缓存的大小
                    client = new OkHttpClient.Builder()
                            .addInterceptor(addQueryParameterInterceptor())  //参数添加
                            .addInterceptor(addHeaderInterceptor()) // token过滤
                            .addInterceptor(httpLoggingInterceptor)//日志,所有的请求响应都看到
                            .cache(cache) //添加缓存
                            .connectTimeout(6, TimeUnit.SECONDS)
                            .readTimeout(6, TimeUnit.SECONDS)
                            .writeTimeout(6, TimeUnit.SECONDS)
                            .build();

                    retrofit = new Retrofit.Builder()
                            .baseUrl(/*Constant.ID_CARD_BASE_URL*/Constant.LOCAL_SERVICE)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())//这里是用的fastjson的
                            .client(client)
                            .build();
                }
            }
        }
        return retrofit;
    }

    /**
     * 设置公共参数
     */
    private static Interceptor addQueryParameterInterceptor() {
        return chain -> {
            Request originalRequest = chain.request();
            Request request;
            HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                    // Provide your custom parameter here
//                    .addQueryParameter("phoneSystem", MVPApp.deviceSoftwareVersion)
//                    .addQueryParameter("phoneSerial", MVPApp.phoneIMEI)
                    .build();
            request = originalRequest.newBuilder().url(modifiedUrl).build();
            return chain.proceed(request);
        };
    }

    /**
     * 设置头
     */
    private static Interceptor addHeaderInterceptor() {
        return chain -> {
            Request originalRequest = chain.request();
            Request.Builder requestBuilder = originalRequest.newBuilder()
                    // Provide your custom header here
                    .header("token", /*(String) SpUtils.get("token", "")*/"")
                    .method(originalRequest.method(), originalRequest.body());
            Request request = requestBuilder.build();
            return chain.proceed(request);
        };
    }

    /**
     * 设置缓存
     */
    private static Interceptor addCacheInterceptor() {
        return chain -> {
            Request request = chain.request();
            if (!NetworkUtil.isNetworkAvailable(MVPApp.getInstance())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            if (NetworkUtil.isNetworkAvailable(MVPApp.getInstance())) {
                int maxAge = 0;
                // 有网络时 设置缓存超时时间0个小时 ,意思就是不读取缓存数据,只对get有用,post没有缓冲
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Retrofit")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .build();
            } else {
                // 无网络时，设置超时为4周  只对get有用,post没有缓冲
                int maxStale = 60 * 60 * 24 * 28;
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" +
                                maxStale)
                        .removeHeader("nyn")
                        .build();
            }
            return response;
        };
    }
}
