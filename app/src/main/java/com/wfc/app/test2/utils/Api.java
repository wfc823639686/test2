package com.wfc.app.test2.utils;

import android.content.Intent;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

/**
 * Created by wangfengchen on 16/8/5.
 */
public class Api {

    public static void register(String phone, String password, Callback callback) {
        OkHttpUtils
                .post()
                .url("")
                .addParams("phone", phone)
                .addParams("password", password)
                .build()
                .execute(callback);
    }

    public static void loadEnterpriseInfo(String eid, Callback callback) {
        OkHttpUtils
                .get()
                .url("http://apitest.shangshaban.com/api/enterprise/getInfo.htm")
                .addParams("eid", eid)
                .build()
                .execute(callback);
    }
}
