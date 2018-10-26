package bwie.com.jingd.api;

import bwie.com.jingd.bean.DetailBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface IProductDetailApi {
    @GET("product/getProductDetail")
    Observable<DetailBean> detail( @Query("pid") int pid);
}
