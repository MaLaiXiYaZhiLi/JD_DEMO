package bwie.com.jingd.api;


import bwie.com.jingd.bean.HomeBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface IHomeApi {
    @GET()
    Observable<HomeBean> homeBean(@Url String url);
}
