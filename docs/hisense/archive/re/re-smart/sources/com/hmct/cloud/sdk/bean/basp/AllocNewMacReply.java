package com.hmct.cloud.sdk.bean.basp;

import com.hmct.cloud.sdk.bean.global.ReplyInfo;

/* JADX INFO: loaded from: classes2.dex */
public class AllocNewMacReply extends ReplyInfo {
    public static final long serialVersionUID = -2658220106052357128L;
    public int allocResult;
    public String mac;

    public int getAllocResult() {
        return this.allocResult;
    }

    public String getMac() {
        return this.mac;
    }

    public void setAllocResult(int i) {
        this.allocResult = i;
    }

    public void setMac(String str) {
        this.mac = str;
    }
}
