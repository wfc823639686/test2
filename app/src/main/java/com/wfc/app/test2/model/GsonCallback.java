package com.wfc.app.test2.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by wangfengchen on 16/7/21.
 * GSON
 */
public abstract class GsonCallback<T> extends Callback<T> {

    private Class<T> clz;

    public GsonCallback(Class<T> c) {
        clz = c;
    }

    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {
//        Gson gson = new Gson();
//        Type objectType = new TypeToken<T>(){}.getType();
//        Object object = gson.fromJson(response.body().charStream(), objectType);
//        System.out.println(object.getClass().getName());
//        return (T) object;
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.enableComplexMapKeySerialization().create();
        try {
            return gson.fromJson(response.body().charStream(), clz);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
