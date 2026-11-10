package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvGingaWebServiceClientInfoBase {
    private String WscName = "";
    private String WscUUId = "";
    private String WscState = "Undefined";

    public String getWscName() {
        return this.WscName;
    }

    public String getWscUUID() {
        return this.WscUUId;
    }

    public String getWscState() {
        return this.WscState;
    }

    public void setWscName(String str) {
        this.WscName = str;
    }

    public void setWscUUId(String str) {
        this.WscUUId = str;
    }

    public void setWscState(String str) {
        this.WscState = str;
    }

    public String toString() {
        return "MtkTvGingaWebServiceClientInfoBase: WscName=" + this.WscName + ", WscUUId=" + this.WscUUId + ", WscState=" + this.WscState;
    }
}
