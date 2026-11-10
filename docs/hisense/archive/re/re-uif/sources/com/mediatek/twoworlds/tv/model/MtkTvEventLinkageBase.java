package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvEventLinkageBase {
    private int onId;
    private int svcId;
    private int targetEventId;
    private int tsId;

    public MtkTvEventLinkageBase() {
        this.onId = 0;
        this.tsId = 0;
        this.svcId = 0;
        this.targetEventId = 0;
    }

    public MtkTvEventLinkageBase(int i, int i2, int i3, int i4) {
        this.onId = i;
        this.tsId = i2;
        this.svcId = i3;
        this.targetEventId = i4;
    }

    public int getOnId() {
        return this.onId;
    }

    public void setOnId(int i) {
        this.onId = i;
    }

    public int getTsId() {
        return this.tsId;
    }

    public void setTsId(int i) {
        this.tsId = i;
    }

    public int getSvcId() {
        return this.svcId;
    }

    public void setSvcId(int i) {
        this.svcId = i;
    }

    public int getTargetEventId() {
        return this.targetEventId;
    }

    public void setTargetEventId(int i) {
        this.targetEventId = i;
    }

    public String toString() {
        return "MtkTvEventLinkAge: Onid=" + this.onId + ", tsId=" + this.tsId + ", svcId=" + this.svcId + ", targetEventId=" + this.targetEventId;
    }
}
