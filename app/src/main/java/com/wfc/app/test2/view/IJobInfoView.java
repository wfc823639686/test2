package com.wfc.app.test2.view;

import com.wfc.app.test2.bean.CommentListResult;
import com.wfc.app.test2.bean.Job;
import com.wfc.app.test2.bean.JobResult;

/**
 * Created by wangfengchen on 16/7/5.
 *
 */
public interface IJobInfoView {

    String getId();

    String getEid();

    void onJobInfoLoadResult(JobResult result);

    void onJobInfoFailure(Throwable t);

    void onJobInfoLoadStart();

    void onJobInfoLoadEnd();

    void onEnterpriseCommentsLoadResult(CommentListResult result);

    void onEnterpriseCommentsFailure(Throwable t);

    void onEnterpriseCommentsLoadStart();

    void onEnterpriseCommentsLoadEnd();

}
