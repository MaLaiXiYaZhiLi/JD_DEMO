package bwie.com.jingd.presenter;



import bwie.com.jingd.bean.DengluBean;
import bwie.com.jingd.model.LoginModel;
import bwie.com.jingd.model.LoginModelCallBack;
import bwie.com.jingd.utils.HttpUtils;
import bwie.com.jingd.view.LoginViewListener;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 登录Presenter 层
 */

public class LoginPresenter {
    LoginViewListener loginViewListener;

    public LoginPresenter(LoginViewListener loginViewListener) {
        this.loginViewListener = loginViewListener;
    }
    public void getLogin(String m,String p){
        Observable<DengluBean> login = HttpUtils.getHttpUtilsInstance().apiClient.getLogin(m, p);
        login.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<DengluBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(DengluBean value) {
                loginViewListener.success(value);
            }

            @Override
            public void onError(Throwable e) {
                loginViewListener.failure(e);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
