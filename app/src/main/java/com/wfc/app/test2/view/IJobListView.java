package com.wfc.app.test2.view;

import com.wfc.app.test2.bean.JobListResult;

import java.util.List;

/**
 * Created by wangfengchen on 16/7/4.
 *
 */
public interface IJobListView {

    String getOrderBy();

    String getLast();

    void onLoad(JobListResult list);

    void onFailure();

    void onLoadCompleted();

}
