package com.mediatek.twoworlds.tv.model;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvDvbcScanParaBase {
    public static final String TAG = "TV_MtkTvDvbcScanPara";
    public int mFreq = -1;
    public DvbcNitMode mNitMode = DvbcNitMode.DVBC_NIT_SEARCH_MODE_OFF;
    public int mNetWorkId = -1;

    public enum DvbcNitMode {
        DVBC_NIT_SEARCH_MODE_OFF,
        DVBC_NIT_SEARCH_MODE_QUICK,
        DVBC_NIT_SEARCH_MODE_EX_QUICK,
        DVBC_NIT_SEARCH_MODE_NUM
    }

    public int getFreq() {
        Log.d(TAG, "getScanStartFreq Ocurr\n");
        return this.mFreq;
    }

    public int getNetWorkID() {
        Log.d(TAG, "getNetWorkID Ocurr\n");
        return this.mNetWorkId;
    }

    public int getNitMode() {
        Log.d(TAG, "getScanStartFreq Ocurr\n");
        DvbcNitMode dvbcNitMode = DvbcNitMode.DVBC_NIT_SEARCH_MODE_OFF;
        DvbcNitMode dvbcNitMode2 = this.mNitMode;
        if (dvbcNitMode == dvbcNitMode2) {
            return 0;
        }
        if (DvbcNitMode.DVBC_NIT_SEARCH_MODE_QUICK == dvbcNitMode2) {
            return 1;
        }
        return DvbcNitMode.DVBC_NIT_SEARCH_MODE_EX_QUICK == dvbcNitMode2 ? 2 : 0;
    }

    public int setFreq(int i) {
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

    public int setNetWorkID(int i) {
        int i2;
        Log.d(TAG, "Enter setNetWorkID\n");
        if (i < 0 || i > 9999) {
            i2 = -1;
        } else {
            this.mNetWorkId = i;
            i2 = 0;
        }
        Log.d(TAG, "Leave setNetWorkID\n");
        return i2;
    }

    public int setNitMode(DvbcNitMode dvbcNitMode) {
        Log.d(TAG, "Enter setNitMode\n");
        this.mNitMode = dvbcNitMode;
        Log.d(TAG, "Leave setNitMode\n");
        return 0;
    }
}
