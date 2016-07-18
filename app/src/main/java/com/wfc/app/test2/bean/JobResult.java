package com.wfc.app.test2.bean;

/**
 * Created by wangfengchen on 16/7/5.
 */
public class JobResult {

    private Job detail;

    private int status;

    public Job getDetail() {
        return detail;
    }

    public void setDetail(Job detail) {
        this.detail = detail;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }
}
