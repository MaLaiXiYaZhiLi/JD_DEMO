package bwie.com.jingd.model;

import bwie.com.jingd.bean.DetailBean;
import bwie.com.jingd.utils.RetrofitUtil;
import bwie.com.jingd.api.IProductDetailApi;
import io.reactivex.Observable;

public class DetailModel {
    public Observable<DetailBean> detail( int pid) {

        return RetrofitUtil.getDefault().create(IProductDetailApi.class).detail( pid);
    }

}
