package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvScanATSCBase {
    public static final int EXCHANGE_GET_CHANNELID_BY_RF_INDEX = 5;
    public static final int EXCHANGE_GET_CURRENT_RF_INDEX = 4;
    public static final int EXCHANGE_GET_FIRST_SCAN_INDEX = 1;
    public static final int EXCHANGE_GET_LAST_SCAN_INDEX = 2;
    public static final int EXCHANGE_GET_TUNER_MODE_INDEX = 3;
    public static final int EXCHANGE_GET_TYPE_UNKNOWN = 0;
    public static final int EXCHANGE_HEADER_LEN = 3;
    public static final int EXCHANGE_HEADER_LEN_IDX = 1;
    public static final int EXCHANGE_HEADER_TYPE_IDX = 2;
    public static final int EXCHANGE_MAX_LEN = 50;
    public static final int EXCHANGE_PAYLOAD_1ST_IDX = 3;
    public static final int EXCHANGE_SET_TYPE_UNKNOWN = 1000;
    public static final int EXCHANGE_TOTAL_LEN_IDX = 0;
    public static final String TAG = "MtkTvScanATSC";

    public enum ScanATSCRet {
        SCAN_ATSC_RET_OK,
        SCAN_ATSC_RET_INTERNAL_ERROR
    }

    private ScanATSCRet exchangeData(int i, int[] iArr) {
        ScanATSCRet scanATSCRet = ScanATSCRet.SCAN_ATSC_RET_INTERNAL_ERROR;
        if (iArr == null) {
            iArr = new int[1];
        }
        int length = iArr.length + 3;
        if (length > 50) {
            Log.d(TAG, "[Error]bigger than EXCHANGE_MAX_LEN\n");
            return ScanATSCRet.SCAN_ATSC_RET_INTERNAL_ERROR;
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
        int iScanATSCExchangeData_native = TVNativeWrapper.ScanATSCExchangeData_native(iArr2);
        for (int i5 = 3; i5 < length; i5++) {
            iArr[i2] = iArr2[i5];
            i2++;
        }
        return iScanATSCExchangeData_native >= 0 ? ScanATSCRet.SCAN_ATSC_RET_OK : ScanATSCRet.SCAN_ATSC_RET_INTERNAL_ERROR;
    }

    public int getChannelIDByRFIndex(int i) {
        int[] iArr = {i};
        if (ScanATSCRet.SCAN_ATSC_RET_OK == exchangeData(5, iArr)) {
            a.b(a.a("ATSC getChannelIDByRFIndex="), iArr[0], TAG);
            return iArr[0];
        }
        Log.d(TAG, "ATSC exchangeData fail");
        return -1;
    }

    public int getCurrentRFIndex() {
        int[] iArr = {0};
        if (ScanATSCRet.SCAN_ATSC_RET_OK == exchangeData(4, iArr)) {
            a.b(a.a("ATSC getCurrentRFIndex="), iArr[0], TAG);
            return iArr[0];
        }
        Log.d(TAG, "ATSC exchangeData fail");
        return -1;
    }

    public int getFirstScanIndex() {
        int[] iArr = {0};
        if (ScanATSCRet.SCAN_ATSC_RET_OK == exchangeData(1, iArr)) {
            a.b(a.a("ATSC firstIndex="), iArr[0], TAG);
            return iArr[0];
        }
        Log.d(TAG, "ATSC exchangeData fail");
        return 0;
    }

    public int getLastScanIndex() {
        int[] iArr = {0};
        if (ScanATSCRet.SCAN_ATSC_RET_OK == exchangeData(2, iArr)) {
            a.b(a.a("ATSC lastIndex="), iArr[0], TAG);
            return iArr[0];
        }
        Log.d(TAG, "ATSC exchangeData fail");
        return 0;
    }

    public int getTunerModeIndex() {
        int[] iArr = {0};
        if (ScanATSCRet.SCAN_ATSC_RET_OK == exchangeData(3, iArr)) {
            a.b(a.a("ATSC tunerModeIndex="), iArr[0], TAG);
            return iArr[0];
        }
        Log.d(TAG, "ATSC exchangeData fail");
        return -1;
    }
}
