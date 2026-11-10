package com.mediatek.twoworlds.tv.model;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvDvbScanParaBase {
    private static final String TAG = "TV_MtkTvDvbScanPara";
    private String s_rf_name = null;
    private int mFreq = 47000;
    private boolean isScanUp = false;

    public int setScanFreq(int i) {
        int i2;
        Log.d(TAG, "Enter setScanFreq\n");
        if (i < 47000 || i > 862000) {
            i2 = -1;
        } else {
            this.mFreq = i;
            i2 = 0;
        }
        Log.d(TAG, "Leave setScanFreq\n");
        return i2;
    }

    public int getScanFreq() {
        Log.d(TAG, "getScanFreq Ocurr\n");
        return this.mFreq;
    }

    public int setScanUp(boolean z) {
        Log.d(TAG, "Enter setScanUp\n");
        this.isScanUp = z;
        Log.d(TAG, "Leave setScanUp\n");
        return 0;
    }

    public boolean getScanUp() {
        Log.d(TAG, "getScanUp Ocurr\n");
        return this.isScanUp;
    }
}
