package com.wfc.app.test2.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.wfc.app.test2.bean.JobListResult;
import com.wfc.app.test2.model.JobListModel;
import com.wfc.app.test2.utils.GsonUtils;
import com.wfc.app.test2.view.IJobListView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by wangfengchen on 16/7/4.
 *
 */
public class JobListPresenter {

    private static final String TAG = "JobListPresenter";

    private IJobListView jobListView;
    private JobListModel jobListModel;

    public JobListPresenter(IJobListView jobListView) {
        this.jobListView = jobListView;
        jobListModel = new JobListModel();
    }

    public void getJobs() {
        jobListModel.getJobs(jobListView.getOrderBy(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, e.getMessage(), e);
                jobListView.onLoadCompleted();
                jobListView.onFailure();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                JobListResult jobListResult = gson.fromJson(response.body().charStream(), JobListResult.class);
                jobListView.onLoad(jobListResult);
                jobListView.onLoadCompleted();
            }
        });
    }
}
