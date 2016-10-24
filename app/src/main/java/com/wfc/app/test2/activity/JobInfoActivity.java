package com.wfc.app.test2.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.wfc.app.test2.R;
import com.wfc.app.test2.base.BaseLceActivity;
import com.wfc.app.test2.bean.CommentListResult;
import com.wfc.app.test2.bean.JobResult;
import com.wfc.app.test2.presenter.JobInfoPresenter;
import com.wfc.app.test2.view.IJobInfoView;

public class JobInfoActivity extends BaseLceActivity<View, JobResult, IJobInfoView, JobInfoPresenter> implements IJobInfoView {

    private static final String TAG = "JobInfoActivity";
    TextView textTv;
    private int enterpriseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_info);
        textTv = (TextView) findViewById(R.id.text);
        loadData(false);
        //设置图片
//        Drawable drawable = getResources().getDrawable(R.mipmap.img_share1);
//        drawable.setBounds(0, 0, DensityUtils.dip2px(this, 24), DensityUtils.dip2px(this, 23));
//        textTv.setText("分享");
//        textTv.setCompoundDrawables(drawable, null, null, null);
//        textTv.setCompoundDrawablePadding(DensityUtils.dip2px(this, 10));
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
        Log.i(TAG, data.getDetail().getJobName());
        textTv.setText(data.getDetail().getJobName());
        enterpriseId = data.getDetail().getEnterpriseId();
        getPresenter().getEnterpriseComments();
    }

    @Override
    public void setCommentsData(CommentListResult r) {
        Log.i(TAG, r.toString());
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        getPresenter().getJobInfo();
    }

    @Override
    public int getId() {
        return 32132;
    }

    @Override
    public int getEnterpriseId() {
        return enterpriseId;
    }

    @Override
    public void onClick(View v) {

    }
}
