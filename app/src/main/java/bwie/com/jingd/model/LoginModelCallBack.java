package bwie.com.jingd.model;


import bwie.com.jingd.bean.DengluBean;

/**
 * 登录model层接口
 */

public interface LoginModelCallBack {
    public void success(DengluBean dengluBean);
    public void failure(Exception e);
}
