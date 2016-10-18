package com.wfc.app.test2.model;

import android.app.job.JobInfo;

import com.wfc.app.test2.api.UserApi;
import com.wfc.app.test2.bean.JobResult;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wangfengchen on 16/7/5.
 *
 */
public class JobInfoModel {

//    OkHttpClient client = new OkHttpClient();

    public void getJobInfo(int id, Subscriber<JobResult> subscriber) {
//        Request request = new Request.Builder()
//                .url("http://apitest.shangshaban.com/api/job/getInfo.htm?id="+id)
//                .build();
//        client.newCall(request).enqueue(callback);
        UserApi.getJobInfo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getEnterpriseComments(String eid, Callback callback) {
//        String urlFormat = "http://apitest.shangshaban.com/api/enterprise/getComments.htm?id=%s&last=%s&pagesize=%s";
//        String url = String.format(urlFormat, eid, "", 1);
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//        client.newCall(request).enqueue(callback);
    }
}
