package com.wfc.app.test2.base;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class BasePresenter<V extends MvpLceView> extends MvpBasePresenter<V> {

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    @SafeVarargs
    protected final <M> void subscribe(Observable<M> ob, boolean pullToRefresh, Subscriber<M>... subscribers) {
        if (isViewAttached()) {
            getView().showLoading(pullToRefresh);
        }
        compositeSubscription.add(ob
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<M>() {
            @Override
            public void onCompleted() {
                if(subscribers!=null && subscribers.length>0) {
                    subscribers[0].onCompleted();
                } else {
                    if (isViewAttached()) {
                        getView().showContent();
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                if(subscribers!=null && subscribers.length>0) {
                    subscribers[0].onError(e);
                } else {
                    if (isViewAttached()) {
                        getView().showError(e, pullToRefresh);
                    }
                }
            }

            @Override
            public void onNext(M m) {
                if(subscribers!=null && subscribers.length>0) {
                    subscribers[0].onNext(m);
                } else {
                    if (isViewAttached()) {
                        getView().setData(m);
                    }
                }
            }
        }));
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (!retainInstance) {
            compositeSubscription.unsubscribe();
        }
    }
}
