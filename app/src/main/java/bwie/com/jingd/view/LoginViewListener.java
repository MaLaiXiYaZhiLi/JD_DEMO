package bwie.com.jingd.view;


import bwie.com.jingd.bean.DengluBean;

/**
 * 登录view层
 */

public interface LoginViewListener {
    public void success(DengluBean dengluBean);
    public void failure(Throwable e);
}
