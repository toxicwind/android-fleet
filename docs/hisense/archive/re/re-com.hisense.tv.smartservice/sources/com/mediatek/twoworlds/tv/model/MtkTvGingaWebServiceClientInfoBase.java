package com.mediatek.twoworlds.tv.model;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvGingaWebServiceClientInfoBase {
    public String WscName = "";
    public String WscUUId = "";
    public String WscState = "Undefined";

    public String getWscName() {
        return this.WscName;
    }

    public String getWscState() {
        return this.WscState;
    }

    public String getWscUUID() {
        return this.WscUUId;
    }

    public void setWscName(String str) {
        this.WscName = str;
    }

    public void setWscState(String str) {
        this.WscState = str;
    }

    public void setWscUUId(String str) {
        this.WscUUId = str;
    }

    public String toString() {
        StringBuilder sbA = a.a("MtkTvGingaWebServiceClientInfoBase: WscName=");
        sbA.append(this.WscName);
        sbA.append(", WscUUId=");
        sbA.append(this.WscUUId);
        sbA.append(", WscState=");
        sbA.append(this.WscState);
        return sbA.toString();
    }
}
