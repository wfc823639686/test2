package com.wfc.app.test2.model;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wangfengchen on 16/7/4.
 *
 */
public class JobListModel {

    OkHttpClient client = new OkHttpClient();

    /**
     * 获取最新职位
     */
    public void getJobs(String orderBy, Callback callback) {
        String format = "http://apitest.shangshaban.com/api/job/getJobs.htm?orderBy=%s";
        String url = String.format(format, orderBy);
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
