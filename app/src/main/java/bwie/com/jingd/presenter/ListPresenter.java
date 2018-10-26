package bwie.com.jingd.presenter;

import bwie.com.jingd.api.Api;
import bwie.com.jingd.bean.ListBean;
import bwie.com.jingd.model.ListModel;
import bwie.com.jingd.view.IListView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ListPresenter {
    private ListModel listModel;
    IListView mlistView;

    public ListPresenter(IListView iListView) {
        mlistView = iListView;
        listModel = new ListModel();
    }

    public void getList(String keywords) {
        listModel.listBean(Api.LIST_URL,keywords)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListBean value) {
                        mlistView.Success(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mlistView.Failure(e.toString());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}



