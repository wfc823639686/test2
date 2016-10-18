package com.wfc.app.test2.bean;

/**
 * Created by wangfengchen on 16/7/6.
 */
public class Comment {

    private String content;

    private float score;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "content='" + content + '\'' +
                ", score=" + score +
                '}';
    }
}
