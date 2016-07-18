package com.wfc.app.test2.model;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by wangfengchen on 16/7/4.
 *
 */
public class LoginModel {

    OkHttpClient client = new OkHttpClient();

    public void login(String phone, String password, Callback callback) {
        MultipartBody body = new MultipartBody.Builder()
                .addFormDataPart("phone", phone)
                .addFormDataPart("password", password)
                .addFormDataPart("identities", "1")
                .build();
        Request request = new Request.Builder()
                .url("http://apitest.shangshaban.com/api/user/login.htm")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
