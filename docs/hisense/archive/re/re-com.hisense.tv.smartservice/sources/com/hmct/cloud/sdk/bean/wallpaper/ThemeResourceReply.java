package com.hmct.cloud.sdk.bean.wallpaper;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ThemeResourceReply extends ThemeBaseReply {
    public static final long serialVersionUID = 9192220596512158703L;
    public int count;
    public long lastUpdateTime;
    public List<ResourceInfo> resource;
    public int resourceType;
    public int updateFlag;

    public int getCount() {
        return this.count;
    }

    public long getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public List<ResourceInfo> getResourceInfo() {
        return this.resource;
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

    public void setResourceInfo(List<ResourceInfo> list) {
        this.resource = list;
    }

    public void setResourceType(int i) {
        this.resourceType = i;
    }

    public void setUpdateFlag(int i) {
        this.updateFlag = i;
    }
}
