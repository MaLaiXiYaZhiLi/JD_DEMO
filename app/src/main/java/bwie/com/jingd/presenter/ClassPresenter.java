package bwie.com.jingd.presenter;

import bwie.com.jingd.bean.ChildBean;
import bwie.com.jingd.bean.ClassBean;
import bwie.com.jingd.api.Api;
import bwie.com.jingd.model.ClassModel;
import bwie.com.jingd.view.IClassView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ClassPresenter {
    private ClassModel classModel;
    IClassView miClassView;

    public ClassPresenter(IClassView iClassView) {
        miClassView = iClassView;
        classModel = new ClassModel();
    }

    public void classBean(){
        classModel.classBean(Api.CLASS_URL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(ClassBean value) {
                        if(miClassView!=null){
                            miClassView.onLeftResponse(value);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        if(miClassView!=null){
                            miClassView.onLeftFailure(e.toString());
                        }
                    }
                    @Override
                    public void onComplete() {

                    }
                });
        }

        public void child(int cid){
        classModel.childBean(Api.CHILD_URL,cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChildBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ChildBean value) {
                        if(miClassView!=null){
                            miClassView.onRightResponse(value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (miClassView!=null) {
                            miClassView.onLeftFailure(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        }

}
