package com.mediatek.twoworlds.tv.model;

import android.util.Log;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvCecActiveSourceBase {
    public static final String TAG = "MtkTvCecActiveSource";
    public boolean activeRoutingPath;
    public int logAddr;
    public int phyAddr;

    public MtkTvCecActiveSourceBase() {
        Log.d(TAG, "MtkTvCecActiveSource Constructor\n");
    }

    public boolean getIsActiveRoutingPath() {
        return this.activeRoutingPath;
    }

    public int getLogAddr() {
        return this.logAddr;
    }

    public int getPhyAddr() {
        return this.phyAddr;
    }

    public void setIsActiveRoutingPath(boolean z) {
        this.activeRoutingPath = z;
    }

    public void setLogAddr(int i) {
        this.logAddr = i;
    }

    public void setPhyAddr(int i) {
        this.phyAddr = i;
    }

    public String toString() {
        StringBuilder sbA = a.a("logaddr= 0x");
        sbA.append(Integer.toHexString(this.logAddr));
        sbA.append("\nphyaddr= 0x");
        sbA.append(Integer.toHexString(this.phyAddr));
        sbA.append("\nisActiveRoutingPath= ");
        sbA.append(this.activeRoutingPath);
        return sbA.toString();
    }
}
