package com.wfc.app.test2.bean;

import java.util.List;

/**
 * Created by wangfengchen on 16/7/6.
 */
public class CommentListResult {

    private int status;

    private List<Comment> results;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Comment> getResults() {
        return results;
    }

    public void setResults(List<Comment> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        String listStr = "";
        for (Comment c : results) {
            listStr +=c;
        }
        return "CommentListResult{" +
                "status=" + status +
                ", results=" + listStr +
                '}';
    }
}
