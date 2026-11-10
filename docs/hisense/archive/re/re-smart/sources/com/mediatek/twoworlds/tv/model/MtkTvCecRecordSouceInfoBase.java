package com.mediatek.twoworlds.tv.model;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvCecRecordSouceInfoBase {
    public static final String TAG = "MtkTvCecRecordSouceInfo";
    public int digBrdcstSys;
    public int digIdMethod;
    public int[] serviceId = new int[6];

    public MtkTvCecRecordSouceInfoBase() {
        Log.d(TAG, "MtkTvCecRecordSouceInfo Constructor\n");
    }

    public int getDigBrdcstSys() {
        return this.digBrdcstSys;
    }

    public int getDigIdMethod() {
        return this.digIdMethod;
    }

    public int[] getServiceId() {
        return this.serviceId;
    }

    public void setDigBrdcstSys(int i) {
        this.digBrdcstSys = i;
    }

    public void setDigIdMethod(int i) {
        this.digIdMethod = i;
    }

    public void setServiceId(int[] iArr) {
        this.serviceId = iArr;
    }
}
