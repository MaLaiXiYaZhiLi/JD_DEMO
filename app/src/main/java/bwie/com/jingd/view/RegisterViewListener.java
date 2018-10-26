package bwie.com.jingd.view;


import bwie.com.jingd.bean.DengluBean;
import bwie.com.jingd.bean.RegisterBean;

/**
 * 注册view层
 */

public interface RegisterViewListener {
    public void success(RegisterBean registerBean);
    public void failure(Throwable e);
}
