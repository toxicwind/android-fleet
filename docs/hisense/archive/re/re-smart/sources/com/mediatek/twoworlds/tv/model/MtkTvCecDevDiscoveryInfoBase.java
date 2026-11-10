package com.mediatek.twoworlds.tv.model;

import android.util.Log;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvCecDevDiscoveryInfoBase {
    public static final String TAG = "MtkTvCecDevDiscoveryInfoBase";
    public boolean batchPolling;
    public int itvl;
    public int startLa;
    public int stopLa;

    public MtkTvCecDevDiscoveryInfoBase() {
        Log.d(TAG, "MtkTvCecDevDiscoveryInfoBase Constructor\n");
    }

    public boolean getBatchPolling() {
        return this.batchPolling;
    }

    public int getItvl() {
        return this.itvl;
    }

    public int getStartLa() {
        return this.startLa;
    }

    public int getStopLa() {
        return this.stopLa;
    }

    public void setBatchPolling(boolean z) {
        this.batchPolling = z;
    }

    public void setItvl(int i) {
        this.itvl = i;
    }

    public void setStartLa(int i) {
        this.startLa = i;
    }

    public void setStopLa(int i) {
        this.stopLa = i;
    }

    public String toString() {
        StringBuilder sbA = a.a("startLa= 0x");
        sbA.append(Integer.toHexString(this.startLa));
        sbA.append("\nstopLa= 0x");
        sbA.append(Integer.toHexString(this.stopLa));
        sbA.append("\nitvl= 0x");
        sbA.append(Integer.toHexString(this.itvl));
        sbA.append("\nbatchPolling= ");
        sbA.append(this.batchPolling);
        return sbA.toString();
    }
}
