package com.wfc.app.test2.view;

import com.wfc.app.test2.bean.User;

/**
 * Created by wangfengchen on 16/7/4.
 */
public interface ILoginView {

    String getPhone();

    String getPassword();

    void loginSuccess(User user);

    void loginFailure();

}
