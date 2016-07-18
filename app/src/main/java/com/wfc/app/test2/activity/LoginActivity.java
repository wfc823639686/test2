package com.wfc.app.test2.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.wfc.app.test2.MainActivity;
import com.wfc.app.test2.R;
import com.wfc.app.test2.bean.User;
import com.wfc.app.test2.presenter.LoginPresenter;
import com.wfc.app.test2.view.ILoginView;

/**
 * Created by wangfengchen on 16/7/4.
 *
 */
public class LoginActivity extends Activity implements ILoginView {

    private static final String TAG = "LoginActivity";

    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPresenter = new LoginPresenter(this);
        loginPresenter.login();
    }

    @Override
    public String getPhone() {
        return "18612011897";
    }

    @Override
    public String getPassword() {
        return "111111";
    }

    @Override
    public void loginSuccess(User user) {
        Log.d(TAG, user.toString());
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        intent.putExtra("uid", user.getId());
        startActivity(intent);
    }

    @Override
    public void loginFailure() {

    }
}
