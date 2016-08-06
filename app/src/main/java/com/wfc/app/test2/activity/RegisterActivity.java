package com.wfc.app.test2.activity;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceActivity;
import com.wfc.app.test2.bean.User;
import com.wfc.app.test2.presenter.RegisterPresenter;
import com.wfc.app.test2.view.IRegisterView;

/**
 * Created by wangfengchen on 16/8/5.
 */
public class RegisterActivity
        extends MvpLceActivity<SwipeRefreshLayout, User, IRegisterView, RegisterPresenter>
        implements IRegisterView {

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    @NonNull
    @Override
    public RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }

    @Override
    public void setData(User data) {

    }

    @Override
    public void loadData(boolean pullToRefresh) {

    }
}
