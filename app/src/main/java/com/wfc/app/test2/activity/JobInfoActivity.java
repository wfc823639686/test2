package com.wfc.app.test2.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceActivity;
import com.wfc.app.test2.R;
import com.wfc.app.test2.bean.CommentListResult;
import com.wfc.app.test2.bean.JobResult;
import com.wfc.app.test2.presenter.JobInfoPresenter;
import com.wfc.app.test2.view.IJobInfoView;

/**
 * Created by wangfengchen on 16/7/5.
 *
 */
public class JobInfoActivity extends MvpLceActivity<View, JobResult, IJobInfoView, JobInfoPresenter> {

    private static final String TAG = "JobInfoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_info);
        getPresenter().getJobInfo();
    }

    @NonNull
    @Override
    public JobInfoPresenter createPresenter() {
        return new JobInfoPresenter();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    @Override
    public void setData(JobResult data) {

    }

    @Override
    public void loadData(boolean pullToRefresh) {

    }
}
