package com.wfc.app.test2.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.wfc.app.test2.bean.User;
import com.wfc.app.test2.utils.Api;
import com.wfc.app.test2.view.IRegisterView;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by wangfengchen on 16/8/5.
 */
public class RegisterPresenter extends MvpBasePresenter<IRegisterView> {

    public void register() {
        if (isViewAttached()) {
            getView().showLoading(true);
        }
        Api.register("1", "1", new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {

            }
        });
    }

}
