package com.mediatek.twoworlds.tv.model;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvBisskeyHeader {
    private static final String TAG = "MtkTvBisskeyHeader";
    protected int frequency = 0;
    protected int symRate = 0;
    protected int polarization = 0;
    protected int programId = 0;

    public int getFrequency() {
        return this.frequency;
    }

    public void setFrequency(int i) {
        this.frequency = i;
    }

    public int getSymRate() {
        return this.symRate;
    }

    public void setSymRate(int i) {
        this.symRate = i;
    }

    public int getPolarization() {
        return this.polarization;
    }

    public void setPolarization(int i) {
        this.polarization = i;
    }

    public int getProgramId() {
        return this.programId;
    }

    public void setProgramId(int i) {
        this.programId = i;
    }

    public String toString() {
        return "MtkTvBisskeyHeader: [frequency=" + this.frequency + ", symRate=" + this.symRate + ", polarization=" + this.polarization + ", programId=" + this.programId + "]";
    }
}
