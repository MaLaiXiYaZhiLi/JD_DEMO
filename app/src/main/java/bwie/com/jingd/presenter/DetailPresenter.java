package bwie.com.jingd.presenter;

import bwie.com.jingd.bean.DetailBean;
import bwie.com.jingd.model.DetailModel;
import bwie.com.jingd.utils.HttpUtils;
import bwie.com.jingd.view.IProductDetailView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailPresenter {

    IProductDetailView mIProductDetailView;

    public DetailPresenter(IProductDetailView mIProductDetailView) {
        this.mIProductDetailView = mIProductDetailView;
    }

    public void detail(int pid) {
        Observable<DetailBean> drtail = HttpUtils.getHttpUtilsInstance().apiClient.getDrtail(pid);
        drtail.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<DetailBean>() {
            @Override
            public void onSubscribe(Disposable d) {


            }

            @Override
            public void onNext(DetailBean value) {
                mIProductDetailView.success(value);
            }

            @Override
            public void onError(Throwable e) {
                mIProductDetailView.failure("shibai");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
