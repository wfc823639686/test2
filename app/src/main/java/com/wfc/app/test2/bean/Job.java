package com.wfc.app.test2.bean;

/**
 * Created by wangfengchen on 16/7/4.
 *
 */
public class Job {

    private int enterpriseId;

    private Enterprise enterprise;

    private int id;

    private String position;

    private String position1;

    private String jobName;

    private String temptation;

    private int salaryMinimum;

    private int salaryHighest;

    private int baseSalary;

    private int status;

    private String updateTime;

    private String createTime;

    private double distance;

    private String jobCommoditiesStr;

    private int reported;

    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public int getEnterpriseId() {
        return this.enterpriseId;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public Enterprise getEnterprise() {
        return this.enterprise;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition1(String position1) {
        this.position1 = position1;
    }

    public String getPosition1() {
        return this.position1;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobName() {
        return this.jobName;
    }

    public void setTemptation(String temptation) {
        this.temptation = temptation;
    }

    public String getTemptation() {
        return this.temptation;
    }

    public void setSalaryMinimum(int salaryMinimum) {
        this.salaryMinimum = salaryMinimum;
    }

    public int getSalaryMinimum() {
        return this.salaryMinimum;
    }

    public void setSalaryHighest(int salaryHighest) {
        this.salaryHighest = salaryHighest;
    }

    public int getSalaryHighest() {
        return this.salaryHighest;
    }

    public void setBaseSalary(int baseSalary) {
        this.baseSalary = baseSalary;
    }

    public int getBaseSalary() {
        return this.baseSalary;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setJobCommoditiesStr(String jobCommoditiesStr) {
        this.jobCommoditiesStr = jobCommoditiesStr;
    }

    public String getJobCommoditiesStr() {
        return this.jobCommoditiesStr;
    }

    public void setReported(int reported) {
        this.reported = reported;
    }

    public int getReported() {
        return this.reported;
    }

    @Override
    public String toString() {
        return "Job{" +
                "enterpriseId=" + enterpriseId +
                ", enterprise=" + enterprise +
                ", id=" + id +
                ", position='" + position + '\'' +
                ", position1='" + position1 + '\'' +
                ", jobName='" + jobName + '\'' +
                ", temptation='" + temptation + '\'' +
                ", salaryMinimum=" + salaryMinimum +
                ", salaryHighest=" + salaryHighest +
                ", baseSalary=" + baseSalary +
                ", status=" + status +
                ", updateTime='" + updateTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", distance=" + distance +
                ", jobCommoditiesStr='" + jobCommoditiesStr + '\'' +
                ", reported=" + reported +
                '}';
    }
}
