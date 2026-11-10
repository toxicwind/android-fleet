package com.mediatek.twoworlds.tv.model;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvCecRecordSouceInfoBase {
    private static final String TAG = "MtkTvCecRecordSouceInfo";
    protected int digBrdcstSys;
    protected int digIdMethod;
    protected int[] serviceId = new int[6];

    public MtkTvCecRecordSouceInfoBase() {
        Log.d(TAG, "MtkTvCecRecordSouceInfo Constructor\n");
    }

    public int getDigIdMethod() {
        return this.digIdMethod;
    }

    protected void setDigIdMethod(int i) {
        this.digIdMethod = i;
    }

    public int getDigBrdcstSys() {
        return this.digBrdcstSys;
    }

    protected void setDigBrdcstSys(int i) {
        this.digBrdcstSys = i;
    }

    public int[] getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(int[] iArr) {
        this.serviceId = iArr;
    }
}
