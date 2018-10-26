package bwie.com.jingd.model;

import bwie.com.jingd.api.IListApi;
import bwie.com.jingd.bean.ListBean;
import bwie.com.jingd.utils.RetrofitUtil;
import io.reactivex.Observable;

public class ListModel {
    public Observable<ListBean> listBean(String url, String keywords) {

        return RetrofitUtil.getDefault().create(IListApi.class).listBean(url, keywords);
    }
}
