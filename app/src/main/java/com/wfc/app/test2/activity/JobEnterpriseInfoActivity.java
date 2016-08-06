package com.wfc.app.test2.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceActivity;
import com.wfc.app.test2.R;
import com.wfc.app.test2.bean.Enterprise;
import com.wfc.app.test2.bean.EnterpriseResult;
import com.wfc.app.test2.presenter.JobEnterpriseInfoPresenter;
import com.wfc.app.test2.view.IJobEnterpriseInfoView;

/**
 * Created by wangfengchen on 16/8/5.
 */
public class JobEnterpriseInfoActivity
        extends MvpLceActivity<View, EnterpriseResult, IJobEnterpriseInfoView, JobEnterpriseInfoPresenter>
        implements IJobEnterpriseInfoView {

    private static final String TAG = "JobEnterpriseInfo";
    TextView nameTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_enterprise_info);
        nameTv = (TextView) findViewById(R.id.act_job_ent_info_name);
        loadData(false);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return "404";
    }

    @NonNull
    @Override
    public JobEnterpriseInfoPresenter createPresenter() {
        return new JobEnterpriseInfoPresenter();
    }

    @Override
    public void setData(EnterpriseResult data) {
        Log.d(TAG, "setData");
        Log.d(TAG, "sn " + data.getDetail().getShortName());
        nameTv.setText(data.getDetail().getShortName());
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadEnterpriseInfo(pullToRefresh);
    }

    @Override
    public String getEid() {
        return "366";
    }
}
