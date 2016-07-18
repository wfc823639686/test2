package com.wfc.app.test2.bean;

/**
 * Created by wangfengchen on 16/7/5.
 */
public class Commodity {
    private int id;

    private int commodityId;

    private int enterpriseId;

    private String name;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getCommodityId() {
        return this.commodityId;
    }

    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public int getEnterpriseId() {
        return this.enterpriseId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
