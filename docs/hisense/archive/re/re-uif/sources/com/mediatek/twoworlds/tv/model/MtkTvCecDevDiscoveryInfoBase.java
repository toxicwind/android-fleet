package com.mediatek.twoworlds.tv.model;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvCecDevDiscoveryInfoBase {
    private static final String TAG = "MtkTvCecDevDiscoveryInfoBase";
    private boolean batchPolling;
    private int itvl;
    private int startLa;
    private int stopLa;

    public MtkTvCecDevDiscoveryInfoBase() {
        Log.d(TAG, "MtkTvCecDevDiscoveryInfoBase Constructor\n");
    }

    public String toString() {
        return "startLa= 0x" + Integer.toHexString(this.startLa) + "\nstopLa= 0x" + Integer.toHexString(this.stopLa) + "\nitvl= 0x" + Integer.toHexString(this.itvl) + "\nbatchPolling= " + this.batchPolling;
    }

    public int getStartLa() {
        return this.startLa;
    }

    public void setStartLa(int i) {
        this.startLa = i;
    }

    public int getStopLa() {
        return this.stopLa;
    }

    public void setStopLa(int i) {
        this.stopLa = i;
    }

    public int getItvl() {
        return this.itvl;
    }

    public void setItvl(int i) {
        this.itvl = i;
    }

    public boolean getBatchPolling() {
        return this.batchPolling;
    }

    public void setBatchPolling(boolean z) {
        this.batchPolling = z;
    }
}
