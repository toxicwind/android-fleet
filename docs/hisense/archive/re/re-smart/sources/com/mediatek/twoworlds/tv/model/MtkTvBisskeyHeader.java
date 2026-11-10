package com.mediatek.twoworlds.tv.model;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvBisskeyHeader {
    public static final String TAG = "MtkTvBisskeyHeader";
    public int frequency = 0;
    public int symRate = 0;
    public int polarization = 0;
    public int programId = 0;

    public int getFrequency() {
        return this.frequency;
    }

    public int getPolarization() {
        return this.polarization;
    }

    public int getProgramId() {
        return this.programId;
    }

    public int getSymRate() {
        return this.symRate;
    }

    public void setFrequency(int i) {
        this.frequency = i;
    }

    public void setPolarization(int i) {
        this.polarization = i;
    }

    public void setProgramId(int i) {
        this.programId = i;
    }

    public void setSymRate(int i) {
        this.symRate = i;
    }

    public String toString() {
        StringBuilder sbA = a.a("MtkTvBisskeyHeader: [frequency=");
        sbA.append(this.frequency);
        sbA.append(", symRate=");
        sbA.append(this.symRate);
        sbA.append(", polarization=");
        sbA.append(this.polarization);
        sbA.append(", programId=");
        return a.a(sbA, this.programId, "]");
    }
}
