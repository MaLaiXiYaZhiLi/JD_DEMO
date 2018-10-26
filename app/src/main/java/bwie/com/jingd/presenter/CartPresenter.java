package bwie.com.jingd.presenter;

import bwie.com.jingd.api.Api;
import bwie.com.jingd.bean.CartBean;
import bwie.com.jingd.model.CartModel;
import bwie.com.jingd.view.ICartView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CartPresenter {
    private CartModel cartModel;
    ICartView miCartView;

    public CartPresenter(ICartView iCartView) {
        miCartView = iCartView;
        cartModel = new CartModel();
    }

    public void getCart(String uid) {
        cartModel.cart(Api.CART_URL, uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CartBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CartBean value) {

                            miCartView.Success(value);

                    }

                    @Override
                    public void onError(Throwable e) {

                            miCartView.Failure(e.toString());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}



