package com.wfc.app.test2.presenter;

import com.google.gson.Gson;
import com.wfc.app.test2.bean.CommentListResult;
import com.wfc.app.test2.bean.JobResult;
import com.wfc.app.test2.model.JobInfoModel;
import com.wfc.app.test2.view.IJobInfoView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by wangfengchen on 16/7/5.
 *
 */
public class JobInfoPresenter {

    private static final String TAG = "JobInfoPresenter";

    private JobInfoModel jobInfoModel;
    private IJobInfoView jobInfoView;

    public JobInfoPresenter(IJobInfoView jobInfoView) {
        this.jobInfoView = jobInfoView;
        jobInfoModel = new JobInfoModel();
    }

    public void getJobInfo() {
        jobInfoView.onJobInfoLoadStart();
        jobInfoModel.getJobInfo(jobInfoView.getId(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                jobInfoView.onJobInfoFailure(e);
                jobInfoView.onJobInfoLoadEnd();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                Log.d(TAG, response.body().string());

                Gson gson = new Gson();
                JobResult result = gson.fromJson(response.body().charStream(), JobResult.class);
                jobInfoView.onJobInfoLoadResult(result);
                jobInfoView.onJobInfoLoadEnd();
            }
        });
    }

    public void getEnterpriseComments() {
        jobInfoModel.getEnterpriseComments(jobInfoView.getEid(), new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                jobInfoView.onEnterpriseCommentsFailure(e);
                jobInfoView.onEnterpriseCommentsLoadEnd();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                CommentListResult result = gson.fromJson(response.body().charStream(), CommentListResult.class);
                jobInfoView.onEnterpriseCommentsLoadResult(result);
                jobInfoView.onEnterpriseCommentsLoadEnd();
            }
        });
    }
}
