package com.wfc.app.test2.api;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseApi {
    public static final String API_SERVER = "http://apitest.shangshaban.com";
    private static Retrofit mRetrofit;

    protected static Retrofit getRetrofit() {
            if (mRetrofit == null) {
                //设定30秒超时
                //构建Retrofit
                mRetrofit = new Retrofit.Builder()
                        //配置服务器路径
                        .baseUrl(API_SERVER + "/")
                        //设置日期解析格式，这样可以直接解析Date类型
//                        .setDateFormat("yyyy-MM-dd HH:mm:ss")
                        //配置转化库，默认是Gson
                        .addConverterFactory(GsonConverterFactory.create())
                        //配置回调库，采用RxJava
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        //设置OKHttpClient为网络客户端
                        .client(new OkHttpClient
                                .Builder()
                                .addInterceptor(new StethoInterceptor())
                                .build())
                        .build();
            }
            return mRetrofit;
        }
}