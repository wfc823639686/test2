package com.wfc.app.test2.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceActivity;
import com.wfc.app.test2.R;
import com.wfc.app.test2.bean.CommentListResult;
import com.wfc.app.test2.bean.JobResult;
import com.wfc.app.test2.presenter.JobInfoPresenter;
import com.wfc.app.test2.view.IJobInfoView;

public class JobInfoActivity extends MvpLceActivity<View, JobResult, IJobInfoView, JobInfoPresenter> implements IJobInfoView {

    private static final String TAG = "JobInfoActivity";
    TextView textTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_info);
        textTv = (TextView) findViewById(R.id.text);
        loadData(false);
    }

    @NonNull
    @Override
    public JobInfoPresenter createPresenter() {
        return new JobInfoPresenter();
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    @Override
    public void setData(JobResult data) {
        Log.d(TAG, data.getDetail().getJobName());
        textTv.setText(data.getDetail().getJobName());
    }

    @Override
    public void setCommentsData(CommentListResult r) {

    }

    @Override
    public void loadData(boolean pullToRefresh) {
        getPresenter().getJobInfo();
    }

    @Override
    public int getId() {
        return 32132;
    }
}
