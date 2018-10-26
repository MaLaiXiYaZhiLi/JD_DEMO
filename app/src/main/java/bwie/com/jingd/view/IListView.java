package bwie.com.jingd.view;

import bwie.com.jingd.bean.ListBean;


public interface IListView {
    void Success(ListBean listBean);
    void Failure(String msg);

}
