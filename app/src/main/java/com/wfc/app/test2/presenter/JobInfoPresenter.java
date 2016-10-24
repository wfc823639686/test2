package com.wfc.app.test2.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.rx.lce.MvpLceRxPresenter;
import com.wfc.app.test2.api.UserApi;
import com.wfc.app.test2.base.BasePresenter;
import com.wfc.app.test2.bean.CommentListResult;
import com.wfc.app.test2.bean.JobResult;
import com.wfc.app.test2.model.JobInfoModel;
import com.wfc.app.test2.view.IJobInfoView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by wangfengchen on 16/7/5.
 *
 */
public class JobInfoPresenter extends BasePresenter<IJobInfoView> {

    private static final String TAG = "JobInfoPresenter";

    public JobInfoPresenter() {

    }

    public void getJobInfo() {
        subscribe(UserApi.getJobInfo(getView().getId()), false);
    }

    public void getEnterpriseComments() {
        subscribe(UserApi.getEnterpriseComments(getView().getId()), false, new Subscriber<CommentListResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(CommentListResult commentListResult) {
                if(isViewAttached()) {
                    getView().setCommentsData(commentListResult);
                }
            }
        });
    }


}
