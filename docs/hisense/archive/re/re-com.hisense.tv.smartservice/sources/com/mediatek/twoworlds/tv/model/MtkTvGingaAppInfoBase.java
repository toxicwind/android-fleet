package com.mediatek.twoworlds.tv.model;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvGingaAppInfoBase {
    public String AppName = "";
    public String AppId = "";
    public boolean bIsRunning = false;
    public boolean bIsPersistent = false;

    public String getAppId() {
        return this.AppId;
    }

    public String getAppName() {
        return this.AppName;
    }

    public boolean isPersistent() {
        return this.bIsPersistent;
    }

    public boolean isRunning() {
        return this.bIsRunning;
    }

    public void setAppId(String str) {
        this.AppId = str;
    }

    public void setAppName(String str) {
        this.AppName = str;
    }

    public void setIsPersistent(boolean z) {
        this.bIsPersistent = z;
    }

    public void setIsRunning(boolean z) {
        this.bIsRunning = z;
    }

    public String toString() {
        StringBuilder sbA = a.a("MtkTvGingaAppInfoBase: AppName=");
        sbA.append(this.AppName);
        sbA.append(", AppId=");
        sbA.append(this.AppId);
        sbA.append(", isRunning=");
        sbA.append(this.bIsRunning);
        sbA.append(", isPersistent=");
        sbA.append(this.bIsPersistent);
        return sbA.toString();
    }
}
