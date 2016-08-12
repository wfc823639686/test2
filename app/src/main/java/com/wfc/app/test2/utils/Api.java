package com.wfc.app.test2.utils;

import android.content.Intent;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.Map;

/**
 * Created by wangfengchen on 16/8/5.
 */
public class Api {

    public static void register(Map<String, String> params, Callback callback) {
        OkHttpUtils
                .post()
                .url("http://apitest.shangshaban.com/api/user/register.htm")
                .params(params)
                .build()
                .execute(callback);
    }

    public static void loadEnterpriseInfo(String eid, Callback callback) {
        OkHttpUtils
                .get()
                .url("http://apitest.shangshaban.com/api/enterprise/getInfo.htm")
                .addParams("id", eid)
                .build()
                .execute(callback);
    }
}
