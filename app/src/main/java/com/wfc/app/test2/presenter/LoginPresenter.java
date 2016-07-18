package com.wfc.app.test2.presenter;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.wfc.app.test2.bean.User;
import com.wfc.app.test2.model.LoginModel;
import com.wfc.app.test2.utils.GsonUtils;
import com.wfc.app.test2.view.ILoginView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by wangfengchen on 16/7/4.
 *
 */
public class LoginPresenter {

    private static final String TAG = "LoginPresenter";

    private ILoginView loginView;
    private LoginModel loginModel;
    private Handler mHandler = new Handler();

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
        loginModel = new LoginModel();
    }

    public void login() {
        loginModel.login(loginView.getPhone(), loginView.getPassword(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, e.getMessage(), e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject resultJson = new JSONObject(response.body().string());
                    if (TextUtils.equals("1", resultJson.optString("status"))) {
                        String uid = resultJson.optString("resumeId");
                        final User user = new User();
                        user.setId(uid);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                loginView.loginSuccess(user);
                            }
                        });
                    } else {
                        loginView.loginFailure();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    loginView.loginFailure();
                }
            }
        });
    }

}
