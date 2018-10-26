package bwie.com.jingd.api;

import bwie.com.jingd.bean.DengluBean;
import bwie.com.jingd.bean.DetailBean;
import bwie.com.jingd.bean.RegisterBean;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api2 {
    @FormUrlEncoded
    @POST("user/reg")
    Observable<RegisterBean> getReg(@Field("mobile")String m, @Field("password")String p);
    @GET("product/getProductDetail")
    Observable<DetailBean>getDrtail(@Query("pid")int pid);
    @FormUrlEncoded
    @POST("user/login")
    Observable<DengluBean>getLogin(@Field("mobile") String m,@Field("password")String p);
}
