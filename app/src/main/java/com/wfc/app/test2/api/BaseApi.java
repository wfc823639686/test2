package com.wfc.app.test2.api;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

abstract class BaseApi {
    private static final String API_SERVER = "http://apitest.shangshaban.com";
    private static Retrofit mRetrofit;
    private static HttpLoggingInterceptor httpLoggingInterceptor;
    static {
        httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("OK_HTTP", message);
            }
        });
        //包含header、body数据
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    }

    static Retrofit getRetrofit() {
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
                                //FaceBook 网络调试器，可在Chrome调试网络请求，查看SharePreferences,数据库等
//                                .addNetworkInterceptor(new StethoInterceptor())
                                .addInterceptor(httpLoggingInterceptor)
                                .build())
                        .build();
            }
            return mRetrofit;
        }
}