package com.wfc.app.test2.presenter;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.wfc.app.test2.bean.Enterprise;
import com.wfc.app.test2.bean.EnterpriseResult;
import com.wfc.app.test2.model.GsonCallback;
import com.wfc.app.test2.utils.Api;
import com.wfc.app.test2.view.IJobEnterpriseInfoView;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by wangfengchen on 16/8/5.
 */
public class JobEnterpriseInfoPresenter extends MvpBasePresenter<IJobEnterpriseInfoView> {

    private static final String TAG = "JobEntInfoPresenter";

    public void loadEnterpriseInfo(final boolean pullToRefresh) {
        getView().showLoading(pullToRefresh);
        Log.d(TAG, "loadEnterpriseInfo");

        Api.loadEnterpriseInfo(getView().getEid(), new GsonCallback<EnterpriseResult>(EnterpriseResult.class){

            @Override
            public void onError(Call call, Exception e, int id) {
                if (isViewAttached()) {
                    getView().showError(new Exception("msg:"+e.getMessage()), pullToRefresh);
                }
            }

            @Override
            public void onResponse(EnterpriseResult response, int id) {
                Log.d(TAG, "onResponse");
                if (isViewAttached()) {
                    Log.d(TAG, "setData");
                    getView().setData(response);
                    getView().showContent();
                }
            }


        });
    }
}
