package bwie.com.jingd.view;

import bwie.com.jingd.bean.ChildBean;
import bwie.com.jingd.bean.ClassBean;
import bwie.com.jingd.bean.HomeBean;

public interface IClassView {
    void onLeftResponse(ClassBean classBean);
    void onLeftFailure(String e);

    void onRightResponse(ChildBean childBean);
    void onRightFailure(String e);
}
