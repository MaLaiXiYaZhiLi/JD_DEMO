package bwie.com.jingd.presenter;

import bwie.com.jingd.bean.HomeBean;
import bwie.com.jingd.api.Api;
import bwie.com.jingd.model.HomeModel;
import bwie.com.jingd.view.IHomeView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter {
    private HomeModel homeModel;
    IHomeView miHomeView;

    public HomePresenter(IHomeView iHomeView) {
        miHomeView = iHomeView;
        homeModel = new HomeModel();
    }

    public void cartBean(){
        homeModel.homeBean(Api.HOME_URL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean value) {
                        if(miHomeView!=null){
                            miHomeView.success(value);
                        }
                    }


                    @Override
                    public void onError(Throwable e) {
                        if(miHomeView!=null){
                            miHomeView.failure(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }
}
