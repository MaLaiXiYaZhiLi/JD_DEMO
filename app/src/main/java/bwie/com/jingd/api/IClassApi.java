package bwie.com.jingd.api;


import bwie.com.jingd.bean.ChildBean;
import bwie.com.jingd.bean.ClassBean;
import bwie.com.jingd.bean.HomeBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface IClassApi {
    @GET()
    Observable<ClassBean> classBean(@Url String url);

    @GET()
    Observable<ChildBean> childBean(@Url String url, @Query("cid") int cid);


}
