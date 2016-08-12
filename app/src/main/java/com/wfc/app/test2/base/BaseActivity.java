package com.wfc.app.test2.base;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by wangfengchen on 16/8/12.
 */
public abstract class BaseActivity<V extends MvpView, P extends MvpPresenter<V>>
        extends MvpActivity<V, P> {

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
