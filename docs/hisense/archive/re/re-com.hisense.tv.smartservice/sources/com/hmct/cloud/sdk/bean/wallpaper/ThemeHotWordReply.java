package com.hmct.cloud.sdk.bean.wallpaper;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ThemeHotWordReply extends ThemeBaseReply {
    public static final long serialVersionUID = -1548926676573447913L;
    public int count;
    public List<String> hotWord;
    public long lastUpdateTime;
    public int updateFlag;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getCount() {
        return this.count;
    }

    public List<String> getHotWord() {
        return this.hotWord;
    }

    public long getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public int getUpdateFlag() {
        return this.updateFlag;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public void setHotWord(List<String> list) {
        this.hotWord = list;
    }

    public void setLastUpdateTime(long j) {
        this.lastUpdateTime = j;
    }

    public void setUpdateFlag(int i) {
        this.updateFlag = i;
    }
}
