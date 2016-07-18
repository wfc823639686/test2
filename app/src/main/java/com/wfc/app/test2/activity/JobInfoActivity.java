package com.wfc.app.test2.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.wfc.app.test2.R;
import com.wfc.app.test2.bean.CommentListResult;
import com.wfc.app.test2.bean.JobResult;
import com.wfc.app.test2.presenter.JobInfoPresenter;
import com.wfc.app.test2.view.IJobInfoView;

/**
 * Created by wangfengchen on 16/7/5.
 *
 */
public class JobInfoActivity extends Activity implements IJobInfoView {

    private static final String TAG = "JobInfoActivity";

    private JobInfoPresenter jobInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_info);
        jobInfoPresenter = new JobInfoPresenter(this);
        jobInfoPresenter.getJobInfo();
        jobInfoPresenter.getEnterpriseComments();
    }

    @Override
    public String getId() {
        return "31330";
    }

    @Override
    public String getEid() {
        return null;
    }

    @Override
    public void onJobInfoLoadResult(JobResult result) {

    }

    @Override
    public void onJobInfoFailure(Throwable t) {
        Log.e(TAG, t.getMessage(), t);
    }

    @Override
    public void onJobInfoLoadStart() {
        Log.d(TAG, "onJobInfoLoadStart");
    }

    @Override
    public void onJobInfoLoadEnd() {
        Log.d(TAG, "onJobInfoLoadEnd");
    }

    @Override
    public void onEnterpriseCommentsLoadResult(CommentListResult result) {

    }

    @Override
    public void onEnterpriseCommentsFailure(Throwable t) {
        Log.e(TAG, t.getMessage(), t);
    }

    @Override
    public void onEnterpriseCommentsLoadStart() {
        Log.d(TAG, "onEnterpriseCommentsLoadEnd");
    }

    @Override
    public void onEnterpriseCommentsLoadEnd() {
        Log.d(TAG, "onEnterpriseCommentsLoadEnd");
    }


}
