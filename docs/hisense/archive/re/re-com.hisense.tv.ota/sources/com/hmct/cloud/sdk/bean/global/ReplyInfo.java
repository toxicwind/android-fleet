package com.hmct.cloud.sdk.bean.global;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class ReplyInfo implements Serializable {
    private static final long serialVersionUID = 9062430960119306826L;
    private ErrorInfo error;
    private int reply = Integer.MIN_VALUE;
    private String replyDesc;

    public ErrorInfo a() {
        return this.error;
    }

    public int b() {
        return this.reply;
    }

    public void c(ErrorInfo errorInfo) {
        this.error = errorInfo;
    }

    public void d(int i) {
        this.reply = i;
    }

    public void e(String str) {
        this.replyDesc = str;
    }
}
