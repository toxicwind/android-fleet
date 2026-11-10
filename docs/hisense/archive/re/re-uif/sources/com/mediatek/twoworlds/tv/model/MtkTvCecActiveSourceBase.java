package com.mediatek.twoworlds.tv.model;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvCecActiveSourceBase {
    private static final String TAG = "MtkTvCecActiveSource";
    protected boolean activeRoutingPath;
    protected int logAddr;
    protected int phyAddr;

    public MtkTvCecActiveSourceBase() {
        Log.d(TAG, "MtkTvCecActiveSource Constructor\n");
    }

    public String toString() {
        return "logaddr= 0x" + Integer.toHexString(this.logAddr) + "\nphyaddr= 0x" + Integer.toHexString(this.phyAddr) + "\nisActiveRoutingPath= " + this.activeRoutingPath;
    }

    public int getLogAddr() {
        return this.logAddr;
    }

    public void setLogAddr(int i) {
        this.logAddr = i;
    }

    public int getPhyAddr() {
        return this.phyAddr;
    }

    public void setPhyAddr(int i) {
        this.phyAddr = i;
    }

    public boolean getIsActiveRoutingPath() {
        return this.activeRoutingPath;
    }

    public void setIsActiveRoutingPath(boolean z) {
        this.activeRoutingPath = z;
    }
}
