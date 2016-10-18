package com.wfc.app.test2.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.wfc.app.test2.BuildConfig;
import com.wfc.app.test2.R;
import com.wfc.app.test2.presenter.RegisterPresenter;
import com.wfc.app.test2.utils.MapUtils;
import com.wfc.app.test2.view.IRegisterView;
import com.zhy.http.okhttp.request.PostStringRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * Created by wangfengchen on 16/8/5.
 */
public class RegisterActivity
        extends MvpActivity<IRegisterView, RegisterPresenter>
        implements IRegisterView {
    private final static String TAG = "RegisterActivity";

    EditText phoneEt;
    EditText passwordEt;

    Button registerBtn;
    //上传参数
    private String phone, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        phoneEt = (EditText) findViewById(R.id.reg_phone_et);
        passwordEt = (EditText) findViewById(R.id.reg_password_et);
        registerBtn = (Button) findViewById(R.id.reg_submit_btn);
        Log.d(TAG, BuildConfig.API_SERVICE);
        phoneEt.setText(BuildConfig.API_SERVICE);
    }

    @NonNull
    @Override
    public RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }

    @Override
    public boolean validRegisterParams() {
        phone = phoneEt.getText().toString();
        if(TextUtils.isEmpty(phone)) {
            setError("手机号不能为空", null);
            return false;
        } else if(phone.length()!=11) {
            setError("手机号格式不正确", null);
            return false;
        }
        password = passwordEt.getText().toString();
        if(TextUtils.isEmpty(password)) {
            setError("密码不能为空", null);
            return false;
        }
        return true;
    }

    @Override
    public Map<String, String> getRegisterParams() {
        return MapUtils.create(
                "phone", phone,
                "password", password);
    }

    @Override
    public void setError(String msg, Exception e) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setData(String r) {
        Toast.makeText(this, r, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setRefresh(boolean b) {
        registerBtn.setEnabled(!b);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reg_submit_btn:
                getPresenter().register();
                break;
        }
    }
}
