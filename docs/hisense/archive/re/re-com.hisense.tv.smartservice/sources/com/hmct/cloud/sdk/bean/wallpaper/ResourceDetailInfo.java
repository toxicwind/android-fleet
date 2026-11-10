package com.hmct.cloud.sdk.bean.wallpaper;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ResourceDetailInfo implements Serializable {
    public static final long serialVersionUID = -371031453388578597L;
    public int customerscore;
    public String designer;
    public int downloadTimes;
    public int freeFlag;
    public int isPurchased;
    public long lastModify;
    public List<MetaInfo> meta;
    public List<Picture> pictureInfo;
    public int price;
    public String producer;
    public int resourceType;
    public long resourcetId;
    public int score;
    public long size;
    public String version;

    public int getCustomerscore() {
        return this.customerscore;
    }

    public String getDesigner() {
        return this.designer;
    }

    public int getDownloadTimes() {
        return this.downloadTimes;
    }

    public int getFreeFlag() {
        return this.freeFlag;
    }

    public int getIsPurchased() {
        return this.isPurchased;
    }

    public long getLastModify() {
        return this.lastModify;
    }

    public List<MetaInfo> getMeta() {
        return this.meta;
    }

    public List<Picture> getPictureInfo() {
        return this.pictureInfo;
    }

    public int getPrice() {
        return this.price;
    }

    public String getProducer() {
        return this.producer;
    }

    public int getResourceType() {
        return this.resourceType;
    }

    public long getResourcetId() {
        return this.resourcetId;
    }

    public int getScore() {
        return this.score;
    }

    public long getSize() {
        return this.size;
    }

    public String getVersion() {
        return this.version;
    }

    public void setCustomerscore(int i) {
        this.customerscore = i;
    }

    public void setDesigner(String str) {
        this.designer = str;
    }

    public void setDownloadTimes(int i) {
        this.downloadTimes = i;
    }

    public void setFreeFlag(int i) {
        this.freeFlag = i;
    }

    public void setIsPurchased(int i) {
        this.isPurchased = i;
    }

    public void setLastModify(long j) {
        this.lastModify = j;
    }

    public void setMeta(List<MetaInfo> list) {
        this.meta = list;
    }

    public void setPictureInfo(List<Picture> list) {
        this.pictureInfo = list;
    }

    public void setPrice(int i) {
        this.price = i;
    }

    public void setProducer(String str) {
        this.producer = str;
    }

    public void setResourceType(int i) {
        this.resourceType = i;
    }

    public void setResourcetId(long j) {
        this.resourcetId = j;
    }

    public void setScore(int i) {
        this.score = i;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public void setVersion(String str) {
        this.version = str;
    }
}
