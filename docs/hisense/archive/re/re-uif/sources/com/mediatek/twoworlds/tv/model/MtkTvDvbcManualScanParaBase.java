package com.mediatek.twoworlds.tv.model;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvDvbcManualScanParaBase {
    private static final String TAG = "TV_MtkTvDvbcManualScanPara";
    private int mStartFreq = 47000;
    private int mEndFreq = 862000;
    private boolean isScanUp = false;

    public int setScanStartFreq(int i) {
        int i2;
        Log.d(TAG, "Enter setScanFreq\n");
        if (i < 47000 || i > 862000) {
            i2 = -1;
        } else {
            this.mStartFreq = i;
            i2 = 0;
        }
        Log.d(TAG, "Leave setScanFreq\n");
        return i2;
    }

    public int getScanStartFreq() {
        Log.d(TAG, "getScanStartFreq Ocurr\n");
        return this.mStartFreq;
    }

    public int setScanEndFreq(int i) {
        int i2;
        Log.d(TAG, "Enter setScanFreq\n");
        if (i < 47000 || i > 862000) {
            i2 = -1;
        } else {
            this.mEndFreq = i;
            i2 = 0;
        }
        Log.d(TAG, "Leave setScanFreq\n");
        return i2;
    }

    public int getScanEndFreq() {
        Log.d(TAG, "getScanEndFreq Ocurr\n");
        return this.mEndFreq;
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
