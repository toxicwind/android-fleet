package com.hmct.cloud.sdk.bean.wallpaper;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ThemeCommentReply extends ThemeBaseReply {
    public static final long serialVersionUID = -3795150357268350912L;
    public List<Comment> comments;
    public int count;
    public long lastUpdateTime;
    public int resultCode;
    public int updateFlag;

    public List<Comment> getComments() {
        return this.comments;
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

    public int getUpdateFlag() {
        return this.updateFlag;
    }

    public void setComments(List<Comment> list) {
        this.comments = list;
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

    public void setUpdateFlag(int i) {
        this.updateFlag = i;
    }
}
