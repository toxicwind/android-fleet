package com.hmct.cloud.sdk.bean.upgrade;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class AppUpgradeInfo implements Serializable {
    public static final long serialVersionUID = 4759667634988646627L;
    public long appId;
    public String packageName;

    public long getAppId() {
        return this.appId;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setAppId(long j) {
        this.appId = j;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }
}
