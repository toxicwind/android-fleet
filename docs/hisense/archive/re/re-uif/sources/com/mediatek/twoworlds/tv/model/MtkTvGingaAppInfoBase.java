package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvGingaAppInfoBase {
    private String AppName = "";
    private String AppId = "";
    private boolean bIsRunning = false;
    private boolean bIsPersistent = false;

    public String getAppName() {
        return this.AppName;
    }

    public String getAppId() {
        return this.AppId;
    }

    public boolean isRunning() {
        return this.bIsRunning;
    }

    public boolean isPersistent() {
        return this.bIsPersistent;
    }

    public void setAppName(String str) {
        this.AppName = str;
    }

    public void setAppId(String str) {
        this.AppId = str;
    }

    public void setIsRunning(boolean z) {
        this.bIsRunning = z;
    }

    public void setIsPersistent(boolean z) {
        this.bIsPersistent = z;
    }

    public String toString() {
        return "MtkTvGingaAppInfoBase: AppName=" + this.AppName + ", AppId=" + this.AppId + ", isRunning=" + this.bIsRunning + ", isPersistent=" + this.bIsPersistent;
    }
}
