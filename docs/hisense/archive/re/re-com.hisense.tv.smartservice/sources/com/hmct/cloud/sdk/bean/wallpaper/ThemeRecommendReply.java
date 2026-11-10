package com.hmct.cloud.sdk.bean.wallpaper;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ThemeRecommendReply extends ThemeBaseReply {
    public static final long serialVersionUID = 4415183955268432997L;
    public int count;
    public long lastUpdateTime;
    public List<ResourceInfo> recommends;
    public int resourceType;
    public int updateFlag;

    public int getCount() {
        return this.count;
    }

    public long getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public List<ResourceInfo> getRecommends() {
        return this.recommends;
    }

    public int getResourceType() {
        return this.resourceType;
    }

    public int getUpdateFlag() {
        return this.updateFlag;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public void setLastUpdateTime(long j) {
        this.lastUpdateTime = j;
    }

    public void setRecommends(List<ResourceInfo> list) {
        this.recommends = list;
    }

    public void setResourceType(int i) {
        this.resourceType = i;
    }

    public void setUpdateFlag(int i) {
        this.updateFlag = i;
    }
}
