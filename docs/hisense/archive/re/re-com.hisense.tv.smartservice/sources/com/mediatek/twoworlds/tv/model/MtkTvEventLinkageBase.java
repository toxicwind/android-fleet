package com.mediatek.twoworlds.tv.model;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvEventLinkageBase {
    public int onId;
    public int svcId;
    public int targetEventId;
    public int tsId;

    public MtkTvEventLinkageBase() {
        this.onId = 0;
        this.tsId = 0;
        this.svcId = 0;
        this.targetEventId = 0;
    }

    public int getOnId() {
        return this.onId;
    }

    public int getSvcId() {
        return this.svcId;
    }

    public int getTargetEventId() {
        return this.targetEventId;
    }

    public int getTsId() {
        return this.tsId;
    }

    public void setOnId(int i) {
        this.onId = i;
    }

    public void setSvcId(int i) {
        this.svcId = i;
    }

    public void setTargetEventId(int i) {
        this.targetEventId = i;
    }

    public void setTsId(int i) {
        this.tsId = i;
    }

    public String toString() {
        StringBuilder sbA = a.a("MtkTvEventLinkAge: Onid=");
        sbA.append(this.onId);
        sbA.append(", tsId=");
        sbA.append(this.tsId);
        sbA.append(", svcId=");
        sbA.append(this.svcId);
        sbA.append(", targetEventId=");
        sbA.append(this.targetEventId);
        return sbA.toString();
    }

    public MtkTvEventLinkageBase(int i, int i2, int i3, int i4) {
        this.onId = i;
        this.tsId = i2;
        this.svcId = i3;
        this.targetEventId = i4;
    }
}
