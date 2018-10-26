package bwie.com.jingd.presenter;


import bwie.com.jingd.bean.PersonInfoBean;
import bwie.com.jingd.model.PersonModel;
import bwie.com.jingd.model.PersonModelCallBack;
import bwie.com.jingd.view.PersonView;

/**
 * 个人p层
 */
public class PersonPresenter {
    PersonModel personModel = new PersonModel();
    PersonView personView;
    public PersonPresenter(PersonView personView) {
        this.personView = personView;
    }

    public void getData(int uid) {
        personModel.getPersonData(uid, new PersonModelCallBack() {
            @Override
            public void success(PersonInfoBean personInfoBean) {
                personView.sucess(personInfoBean);
                System.out.println("个人p数据："+personInfoBean.toString());
            }

            @Override
            public void failed(Throwable code) {
                System.out.println("个人p错误："+code);
            }
        });
    }
}
