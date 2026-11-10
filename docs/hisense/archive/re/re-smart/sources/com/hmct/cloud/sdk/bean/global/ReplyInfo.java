package com.hmct.cloud.sdk.bean.global;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class ReplyInfo implements Serializable {
    public static final long serialVersionUID = 9062430960119306826L;
    public ErrorInfo error;
    public int reply = Integer.MIN_VALUE;
    public String replyDesc;

    public ErrorInfo getError() {
        return this.error;
    }

    public int getReply() {
        return this.reply;
    }

    public String getReplyDesc() {
        return this.replyDesc;
    }

    public void setError(ErrorInfo errorInfo) {
        this.error = errorInfo;
    }

    public void setReply(int i) {
        this.reply = i;
    }

    public void setReplyDesc(String str) {
        this.replyDesc = str;
    }
}
