package com.hmct.cloud.sdk.bean.wallpaper;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ThemeRankReply extends ThemeBaseReply {
    public static final long serialVersionUID = 2406841779389385372L;
    public int count;
    public long lastUpdateTime;
    public List<ResourceInfo> ranks;
    public int resourceType;
    public int updateFlag;

    public int getCount() {
        return this.count;
    }

    public long getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public List<ResourceInfo> getRanks() {
        return this.ranks;
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

    public void setRanks(List<ResourceInfo> list) {
        this.ranks = list;
    }

    public void setResourceType(int i) {
        this.resourceType = i;
    }

    public void setUpdateFlag(int i) {
        this.updateFlag = i;
    }
}
