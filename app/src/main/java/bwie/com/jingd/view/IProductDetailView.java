package bwie.com.jingd.view;

import bwie.com.jingd.bean.DetailBean;
import bwie.com.jingd.bean.HomeBean;

public interface IProductDetailView {
    void success(DetailBean detailBean);
    void failure(String msg);
}
