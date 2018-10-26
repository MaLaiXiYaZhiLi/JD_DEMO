package bwie.com.jingd.model;

import bwie.com.jingd.bean.ChildBean;
import bwie.com.jingd.bean.ClassBean;
import bwie.com.jingd.api.Api;
import bwie.com.jingd.utils.RetrofitUtil;
import bwie.com.jingd.api.IClassApi;
import io.reactivex.Observable;

public class ClassModel {
    public Observable<ClassBean> classBean(String url) {

        return RetrofitUtil.getDefault().create(IClassApi.class).classBean(Api.CLASS_URL);
    }


    public Observable<ChildBean> childBean(String url, int cid) {

        return RetrofitUtil.getDefault().create(IClassApi.class).childBean(Api.CHILD_URL, cid);
    }

}
