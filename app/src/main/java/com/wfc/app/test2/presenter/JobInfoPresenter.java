package com.wfc.app.test2.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.wfc.app.test2.api.UserApi;
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
public class JobInfoPresenter extends MvpBasePresenter<IJobInfoView> {

    private static final String TAG = "JobInfoPresenter";

    public JobInfoPresenter() {

    }

    public void getJobInfo() {
        getView().showLoading(true);
        UserApi.getJobInfo(getView().getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<JobResult, JobResult>() {

                    @Override
                    public JobResult call(JobResult jobResult) {
                        getView().setData(jobResult);
                        Log.e(TAG, "map "+Thread.currentThread().getName());
                        return jobResult;
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Func1<JobResult, Observable<CommentListResult>>() {
                    @Override
                    public Observable<CommentListResult> call(JobResult jobResult) {
                        Log.e(TAG, "flatmap "+Thread.currentThread().getName());
                        return UserApi.getEnterpriseComments(jobResult.getDetail().getEnterpriseId());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CommentListResult>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted "+Thread.currentThread().getName());
                getView().showLoading(false);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, e.getMessage(), e);
                getView().showError(e, false);
            }

            @Override
            public void onNext(CommentListResult r) {
                Log.e(TAG, "onNext "+Thread.currentThread().getName());
                getView().setCommentsData(r);
                getView().showContent();
            }
        });
    }
}
