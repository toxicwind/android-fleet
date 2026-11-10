package com.hmct.cloud.sdk.bean.wallpaper;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class OrderInfo implements Serializable {
    public static final long serialVersionUID = -5697218502458162012L;
    public int freeFlag;
    public long id;
    public long lastModify;
    public List<MetaInfo> meta;
    public int orderPrice;
    public String picture;
    public int price;
    public int resourceType;

    public int getFreeFlag() {
        return this.freeFlag;
    }

    public long getId() {
        return this.id;
    }

    public long getLastModify() {
        return this.lastModify;
    }

    public List<MetaInfo> getMeta() {
        return this.meta;
    }

    public int getOrderPrice() {
        return this.orderPrice;
    }

    public String getPicture() {
        return this.picture;
    }

    public int getPrice() {
        return this.price;
    }

    public int getResourceType() {
        return this.resourceType;
    }

    public void setFreeFlag(int i) {
        this.freeFlag = i;
    }

    public void setId(long j) {
        this.id = j;
    }

    public void setLastModify(long j) {
        this.lastModify = j;
    }

    public void setMeta(List<MetaInfo> list) {
        this.meta = list;
    }

    public void setOrderPrice(int i) {
        this.orderPrice = i;
    }

    public void setPicture(String str) {
        this.picture = str;
    }

    public void setPrice(int i) {
        this.price = i;
    }

    public void setResourceType(int i) {
        this.resourceType = i;
    }
}
