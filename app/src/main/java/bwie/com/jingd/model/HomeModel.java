package bwie.com.jingd.model;

import bwie.com.jingd.bean.HomeBean;
import bwie.com.jingd.api.Api;
import bwie.com.jingd.utils.RetrofitUtil;
import bwie.com.jingd.api.IHomeApi;
import io.reactivex.Observable;

public class HomeModel {
    public Observable<HomeBean> homeBean(String url){

        return RetrofitUtil.getDefault().create(IHomeApi.class).homeBean(Api.HOME_URL);
    }
}
