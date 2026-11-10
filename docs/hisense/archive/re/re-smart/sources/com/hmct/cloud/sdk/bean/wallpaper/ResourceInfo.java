package com.hmct.cloud.sdk.bean.wallpaper;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ResourceInfo implements Serializable {
    public static final long serialVersionUID = -7440341507555560439L;
    public int displayOrder;
    public int freeFlag;
    public long id;
    public long lastModify;
    public List<MetaInfo> meta;
    public String picture;
    public int price;
    public int resourceType;

    public int getDisplayOrder() {
        return this.displayOrder;
    }

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

    public String getPicture() {
        return this.picture;
    }

    public int getPrice() {
        return this.price;
    }

    public int getResourceType() {
        return this.resourceType;
    }

    public void setDisplayOrder(int i) {
        this.displayOrder = i;
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
