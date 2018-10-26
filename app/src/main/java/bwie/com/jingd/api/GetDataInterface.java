package bwie.com.jingd.api;

import java.util.Map;

import bwie.com.jingd.bean.DengluBean;
import bwie.com.jingd.bean.PersonInfoBean;
import bwie.com.jingd.bean.RegisterBean;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * 网络接口数据的请求类
 */
public interface GetDataInterface {

    //登录的接口
    //https://www.zhaoapi.cn/user/login?mobile=15810680959&password=123456
    @FormUrlEncoded
    @POST("/user/login")
    Call<DengluBean> denglu(@FieldMap Map<String, String> map);
    //个人中心接口：
    //https://www.zhaoapi.cn/user/getUserInfo?uid=71
    @GET("/user/getUserInfo")
    Observable<PersonInfoBean> person(@Query("uid") int uid);
    //注册
    @FormUrlEncoded
    @POST("/user/reg")
    Call<RegisterBean> reg(@FieldMap Map<String, String> map);
}
