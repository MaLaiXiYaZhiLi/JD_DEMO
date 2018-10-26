package bwie.com.jingd.presenter;

import bwie.com.jingd.bean.RegisterBean;
import bwie.com.jingd.utils.HttpUtils;
import bwie.com.jingd.view.IRegView;
import bwie.com.jingd.view.RegisterViewListener;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegPresenter {
    RegisterViewListener registerViewListener;

    public RegPresenter(RegisterViewListener registerViewListener) {
        this.registerViewListener = registerViewListener;
    }
    public void getReg(String m,String p){
        Observable<RegisterBean> reg = HttpUtils.getHttpUtilsInstance().apiClient.getReg(m, p);
        reg.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<RegisterBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RegisterBean value) {
                registerViewListener.success(value);
            }

            @Override
            public void onError(Throwable e) {
                registerViewListener.failure(e);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
