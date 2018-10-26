package bwie.com.jingd.view;

import bwie.com.jingd.bean.CartBean;


public interface ICartView {
    void Success(CartBean cartBean);
    void Failure(String msg);

}
