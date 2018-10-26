package bwie.com.jingd.api;

import bwie.com.jingd.bean.CartBean;
import bwie.com.jingd.bean.DetailBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ICartApi {
    @GET()
    Observable<CartBean> cart(@Url String url, @Query("uid") String uid);
}
