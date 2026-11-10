package com.mediatek.twoworlds.tv;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvScanISDBBase {
    private static final int EXCHANGE_GET_CHANNELID_BY_RF_INDEX = 5;
    private static final int EXCHANGE_GET_CURRENT_RF_INDEX = 4;
    private static final int EXCHANGE_GET_FIRST_SCAN_INDEX = 1;
    private static final int EXCHANGE_GET_LAST_SCAN_INDEX = 2;
    private static final int EXCHANGE_GET_TUNER_MODE_INDEX = 3;
    private static final int EXCHANGE_GET_TYPE_UNKNOWN = 0;
    private static final int EXCHANGE_HEADER_LEN = 3;
    private static final int EXCHANGE_HEADER_LEN_IDX = 1;
    private static final int EXCHANGE_HEADER_TYPE_IDX = 2;
    private static final int EXCHANGE_MAX_LEN = 50;
    private static final int EXCHANGE_PAYLOAD_1ST_IDX = 3;
    private static final int EXCHANGE_SET_TYPE_UNKNOWN = 1000;
    private static final int EXCHANGE_TOTAL_LEN_IDX = 0;
    private static final String TAG = "MtkTvScanISDB";

    public enum ScanISDBRet {
        SCAN_ISDB_RET_OK,
        SCAN_ISDB_RET_INTERNAL_ERROR
    }

    public int getFirstScanIndex() {
        int[] iArr = {0};
        if (ScanISDBRet.SCAN_ISDB_RET_OK == exchangeData(1, iArr)) {
            Log.d(TAG, "ISDB firstIndex=" + iArr[0]);
            return iArr[0];
        }
        Log.d(TAG, "ISDB exchangeData fail");
        return 0;
    }

    public int getLastScanIndex() {
        int[] iArr = {0};
        if (ScanISDBRet.SCAN_ISDB_RET_OK == exchangeData(2, iArr)) {
            Log.d(TAG, "ISDB lastIndex=" + iArr[0]);
            return iArr[0];
        }
        Log.d(TAG, "ISDB exchangeData fail");
        return 0;
    }

    public int getTunerModeIndex() {
        int[] iArr = {0};
        if (ScanISDBRet.SCAN_ISDB_RET_OK == exchangeData(3, iArr)) {
            Log.d(TAG, "ISDB tunerModeIndex=" + iArr[0]);
            return iArr[0];
        }
        Log.d(TAG, "ISDB exchangeData fail");
        return -1;
    }

    public int getCurrentRFIndex() {
        int[] iArr = {0};
        if (ScanISDBRet.SCAN_ISDB_RET_OK == exchangeData(4, iArr)) {
            Log.d(TAG, "ISDB getCurrentRFIndex=" + iArr[0]);
            return iArr[0];
        }
        Log.d(TAG, "ISDB exchangeData fail");
        return -1;
    }

    public int getChannelIDByRFIndex(int i) {
        int[] iArr = {i};
        if (ScanISDBRet.SCAN_ISDB_RET_OK == exchangeData(5, iArr)) {
            Log.d(TAG, "ISDB getChannelIDByRFIndex=" + iArr[0]);
            return iArr[0];
        }
        Log.d(TAG, "ISDB exchangeData fail");
        return -1;
    }

    private ScanISDBRet exchangeData(int i, int[] iArr) {
        ScanISDBRet scanISDBRet = ScanISDBRet.SCAN_ISDB_RET_INTERNAL_ERROR;
        if (iArr == null) {
            iArr = new int[1];
        }
        int length = iArr.length + 3;
        if (length > 50) {
            Log.d(TAG, "[Error]bigger than EXCHANGE_MAX_LEN\n");
            return ScanISDBRet.SCAN_ISDB_RET_INTERNAL_ERROR;
        }
        int[] iArr2 = new int[length];
        int i2 = 0;
        iArr2[0] = length;
        iArr2[1] = 3;
        iArr2[2] = i;
        int i3 = 0;
        for (int i4 = 3; i4 < length; i4++) {
            iArr2[i4] = iArr[i3];
            i3++;
        }
        int iScanISDBExchangeData_native = TVNativeWrapper.ScanISDBExchangeData_native(iArr2);
        for (int i5 = 3; i5 < length; i5++) {
            iArr[i2] = iArr2[i5];
            i2++;
        }
        if (iScanISDBExchangeData_native >= 0) {
            return ScanISDBRet.SCAN_ISDB_RET_OK;
        }
        return ScanISDBRet.SCAN_ISDB_RET_INTERNAL_ERROR;
    }
}
