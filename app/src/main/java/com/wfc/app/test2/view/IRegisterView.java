package com.wfc.app.test2.view;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.wfc.app.test2.bean.User;

import java.util.Map;

/**
 * Created by wangfengchen on 16/8/5.
 */
public interface IRegisterView extends MvpView {

    Map<String, String> getRegisterParams();

    boolean validRegisterParams();

    void setError(String msg, Exception e);

    void setData(String r);

    void setRefresh(boolean b);
}
