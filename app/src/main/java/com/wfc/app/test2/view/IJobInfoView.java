package com.wfc.app.test2.view;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.wfc.app.test2.bean.CommentListResult;
import com.wfc.app.test2.bean.Job;
import com.wfc.app.test2.bean.JobResult;

/**
 * Created by wangfengchen on 16/7/5.
 *
 */
public interface IJobInfoView extends MvpLceView<JobResult> {

    int getId();

    void setCommentsData(CommentListResult r);

}
