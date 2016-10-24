package com.wfc.app.test2.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.wfc.app.test2.R;
import com.wfc.app.test2.base.BaseActivity;
import com.wfc.app.test2.presenter.DatingPresenter;
import com.wfc.app.test2.view.IDatingView;

public class DatingActivity extends BaseActivity<IDatingView, DatingPresenter> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dating);
    }

    @Override
    public void onClick(View v) {

    }

    @NonNull
    @Override
    public DatingPresenter createPresenter() {
        return new DatingPresenter();
    }
}
