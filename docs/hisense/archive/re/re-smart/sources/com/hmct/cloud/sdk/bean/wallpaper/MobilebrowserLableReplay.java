package com.hmct.cloud.sdk.bean.wallpaper;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MobilebrowserLableReplay extends ThemeBaseReply {
    public static final long serialVersionUID = -4276032303833639386L;
    public int count;
    public long lastUpdateTime;
    public List<ObjectInfo> objectInfos;
    public int updateFlag;

    public int getCount() {
        return this.count;
    }

    public long getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public List<ObjectInfo> getObjectInfos() {
        return this.objectInfos;
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

    public void setObjectInfos(List<ObjectInfo> list) {
        this.objectInfos = list;
    }

    public void setUpdateFlag(int i) {
        this.updateFlag = i;
    }
}
