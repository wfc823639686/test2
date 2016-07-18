package com.wfc.app.test2.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangfengchen on 16/7/4.
 *
 */
public class JobListResult {

    private List<Job> results;

    private boolean finish;

    private int size;

    private int count;

    private int status;

    public void setResults(List<Job> results){
        this.results = results;
    }
    public List<Job> getResults(){
        return this.results;
    }
    public void setFinish(boolean finish){
        this.finish = finish;
    }
    public boolean getFinish(){
        return this.finish;
    }
    public void setSize(int size){
        this.size = size;
    }
    public int getSize(){
        return this.size;
    }
    public void setCount(int count){
        this.count = count;
    }
    public int getCount(){
        return this.count;
    }
    public void setStatus(int status){
        this.status = status;
    }
    public int getStatus(){
        return this.status;
    }
}
