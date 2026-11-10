package com.hmct.cloud.sdk.bean.wallpaper;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ThemeTopicReply extends ThemeBaseReply {
    public static final long serialVersionUID = 8640857787522725130L;
    public int count;
    public long lastUpdateTime;
    public List<ResourceInfo> resourceInfoList;
    public int resultCode;
    public int topicType;
    public int updateFlag;

    public List<ResourceInfo> getContentInfoList() {
        return this.resourceInfoList;
    }

    public int getCount() {
        return this.count;
    }

    public long getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    @Override // com.hmct.cloud.sdk.bean.wallpaper.ThemeBaseReply
    public int getResultCode() {
        return this.resultCode;
    }

    public int getTopicType() {
        return this.topicType;
    }

    public int getUpdateFlag() {
        return this.updateFlag;
    }

    public void setContentInfoList(List<ResourceInfo> list) {
        this.resourceInfoList = list;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public void setLastUpdateTime(long j) {
        this.lastUpdateTime = j;
    }

    @Override // com.hmct.cloud.sdk.bean.wallpaper.ThemeBaseReply
    public void setResultCode(int i) {
        this.resultCode = i;
    }

    public void setTopicType(int i) {
        this.topicType = i;
    }

    public void setUpdateFlag(int i) {
        this.updateFlag = i;
    }
}
