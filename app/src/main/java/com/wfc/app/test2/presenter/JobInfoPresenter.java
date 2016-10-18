package com.wfc.app.test2.presenter;

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
import rx.Subscriber;

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
        UserApi.getJobInfo(1).subscribe(new Subscriber<JobResult>() {
            @Override
            public void onCompleted() {
                getView().showLoading(false);
            }

            @Override
            public void onError(Throwable e) {
                getView().showError(e, false);
            }

            @Override
            public void onNext(JobResult jobResult) {
                getView().showContent();
            }
        });
    }
}
