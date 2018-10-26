package bwie.com.jingd.model;

import bwie.com.jingd.bean.DengluBean;
import bwie.com.jingd.bean.RegisterBean;

public interface RegisterModelCallBack {
     void success(RegisterBean register);
     void failure(Exception e);
}
