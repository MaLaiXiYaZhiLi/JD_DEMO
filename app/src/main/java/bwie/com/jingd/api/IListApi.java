package bwie.com.jingd.api;

import bwie.com.jingd.bean.ListBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface IListApi {
    @GET()
    Observable<ListBean> listBean(@Url String url, @Query("keywords") String keywords);
}
