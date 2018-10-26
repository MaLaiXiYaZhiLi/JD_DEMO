package bwie.com.jingd.presenter;

import bwie.com.jingd.bean.RegisterBean;
import bwie.com.jingd.model.RegisterModel;
import bwie.com.jingd.model.RegisterModelCallBack;
import bwie.com.jingd.view.RegisterViewListener;

public class RegisterPresenter {
    RegisterModel registerModel = new RegisterModel();
    RegisterViewListener registerViewListener;
    public RegisterPresenter(RegisterViewListener registerViewListener) {
        this.registerViewListener = registerViewListener;
    }

    public void getData(String zhce_name, String zhuce_pwd) {
        registerModel.getData(zhce_name,zhuce_pwd, new RegisterModelCallBack() {
            @Override
            public void success(RegisterBean registerBean) {
                registerViewListener.success(registerBean);
                System.out.println("zhuce："+registerBean.toString());
            }

            @Override
            public void failure(Exception e) {
                System.out.println("zhuce："+"请求失败");
            }
        });
    }
}
