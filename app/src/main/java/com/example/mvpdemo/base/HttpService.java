package com.example.mvpdemo.base;

import com.example.mvpdemo.model.entity.City;
import com.example.mvpdemo.model.entity.IDCard;
import com.example.mvpdemo.model.entity.IDCardLoss;
import com.example.mvpdemo.model.entity.Page;
import com.example.mvpdemo.model.entity.ResponseMessage;
import com.example.mvpdemo.model.entity.ResponseResult;
import com.example.mvpdemo.model.entity.ResponseResult2;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 所有网络请求的接口都在这里管理
 * Created by Administrator on 2017/4/2.
 */

public interface HttpService {
    /**
     * 身份证信息查询服务
     *
     * @param dtype  数据类型：json/xml
     * @param apiKey 聚合数据的apiKey
     * @param idCard 身份证号码
     * @return rxjava的被观察者对象
     */
    @GET("index")
    Observable<ResponseResult<IDCard>> find(@Query("dtype") String dtype, @Query("key") String apiKey, @Query("cardno") String idCard);

    /**
     * 身份证信息泄露服务
     *
     * @param dtype  数据类型：json/xml
     * @param apiKey 聚合数据的apiKey
     * @param idCard 身份证号码
     * @return rxjava的被观察者对象
     */
    @GET("leak")
    Observable<ResponseResult<IDCardLoss>> leak(@Query("dtype") String dtype, @Query("key") String apiKey, @Query("cardno") String idCard);

    /**
     * 身份证信息挂失服务
     *
     * @param dtype  数据类型：json/xml
     * @param apiKey 聚合数据的apiKey
     * @param idCard 身份证号码
     * @return rxjava的被观察者对象
     */
    @GET("loss")
    Observable<ResponseResult<IDCardLoss>> loss(@Query("dtype") String dtype, @Query("key") String apiKey, @Query("cardno") String idCard);

    /**
     * 获取百度图片
     */
    @GET
    Observable<ResponseBody> getBitmap(@Url String url);

    /**
     * 查询城市
     *
     * @param pageSize    默认为10
     * @param pageNum     当前页
     * @param countryName 国家名
     * @param countryCode 国家编码
     * @param district    地区
     * @return 被观察者对象
     */
    @FormUrlEncoded
    @POST("CityServlet")
    Observable<ResponseResult2<Page<City>>> findCity(@Field("pageSize") int pageSize,
                                                     @Field("pageNum") int pageNum,
                                                     @Field("Name") String countryName,
                                                     @Field("CountryCode") String countryCode,
                                                     @Field("District") String district);

    /**
     * 注册用户
     */
    @FormUrlEncoded
    @POST("RegisterUserServlet")
    Observable<ResponseResult2<ResponseMessage>> registerUser(@Field("userName") String userName,
                                                              @Field("password") String password,
                                                              @Field("createTime") String createTime,
                                                              @Field("qq") String qq,
                                                              @Field("weChat") String weChat,
                                                              @Field("phone") String phone,
                                                              @Field("email") String email);

    /**
     * 找回密码
     */
    @FormUrlEncoded
    @POST("FindPasswordServlet")
    Observable<ResponseResult2<ResponseMessage>> findPassword(@Field("userName") String userName,
                                                              @Field("email") String email,
                                                              @Field("checkCode") String checkCode);
    /**
     * 校验账号和邮箱
     */
    @FormUrlEncoded
    @POST("CheckAccountAndEmailServlet")
    Observable<ResponseResult2<ResponseMessage>> checkAccountAndEmail(@Field("userName") String userName,
                                                              @Field("email") String email);

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("LoginServlet")
    Observable<ResponseResult2<ResponseMessage>> login(@Field("userName") String userName,
                                                       @Field("password") String password);

    /**
     * 修改密码
     */
    @FormUrlEncoded
    @POST("ModifyPasswordServlet")
    Observable<ResponseResult2<ResponseMessage>> modifyPassword(@Field("userName") String userName,
                                                                @Field("password") String password);
}
