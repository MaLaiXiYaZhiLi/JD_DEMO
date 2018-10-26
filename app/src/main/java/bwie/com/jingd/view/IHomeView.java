package bwie.com.jingd.view;

import bwie.com.jingd.bean.HomeBean;

public interface IHomeView {
    void success(HomeBean homeBean);
    void failure(String msg);
}
