package com.wfc.app.test2.view;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.wfc.app.test2.bean.Enterprise;
import com.wfc.app.test2.bean.EnterpriseResult;

/**
 * Created by wangfengchen on 16/8/5.
 */
public interface IJobEnterpriseInfoView extends MvpLceView<EnterpriseResult> {

    String getEid();

}
