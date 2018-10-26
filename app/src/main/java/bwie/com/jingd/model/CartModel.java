package bwie.com.jingd.model;

import bwie.com.jingd.api.Api;
import bwie.com.jingd.api.ICartApi;
import bwie.com.jingd.api.IClassApi;
import bwie.com.jingd.bean.CartBean;
import bwie.com.jingd.bean.ChildBean;
import bwie.com.jingd.utils.RetrofitUtil;
import io.reactivex.Observable;

public class CartModel {
    public Observable<CartBean> cart(String url, String uid) {

        return RetrofitUtil.getDefault().create(ICartApi.class).cart(url, uid);
    }
}
