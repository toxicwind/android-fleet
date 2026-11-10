package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvScanPalSecamBase {
    public static final int EXCHANGE_GET_TYPE_FREQ_RANGE = 2;
    public static final int EXCHANGE_GET_TYPE_RF_INFO = 1;
    public static final int EXCHANGE_GET_TYPE_UNKNOWN = 0;
    public static final int EXCHANGE_HEADER_LEN = 3;
    public static final int EXCHANGE_HEADER_LEN_IDX = 1;
    public static final int EXCHANGE_HEADER_TYPE_IDX = 2;
    public static final int EXCHANGE_MAX_LEN = 50;
    public static final int EXCHANGE_PAYLOAD_1ST_IDX = 3;
    public static final int EXCHANGE_SET_TYPE_AUTO_SCAN = 1002;
    public static final int EXCHANGE_SET_TYPE_CANCEL_SCAN = 1001;
    public static final int EXCHANGE_SET_TYPE_RANGE_SCAN = 1004;
    public static final int EXCHANGE_SET_TYPE_UNKNOWN = 1000;
    public static final int EXCHANGE_SET_TYPE_UPDATE_SCAN = 1003;
    public static final int EXCHANGE_TOTAL_LEN_IDX = 0;
    public static final String TAG = "MtkTvScanPalSecam";

    public class ScanPalSecamFreqRange {
        public int lower_freq;
        public int upper_freq;

        public ScanPalSecamFreqRange() {
        }
    }

    public enum ScanPalSecamRet {
        SCAN_PAL_SECAM_RET_OK,
        SCAN_PAL_SECAM_RET_INTERNAL_ERROR
    }

    private ScanPalSecamRet exchangeData(int i, int[] iArr) {
        ScanPalSecamRet scanPalSecamRet = ScanPalSecamRet.SCAN_PAL_SECAM_RET_INTERNAL_ERROR;
        if (iArr == null) {
            iArr = new int[1];
        }
        int length = iArr.length + 3;
        if (length > 50) {
            Log.d(TAG, "[Error]bigger than EXCHANGE_MAX_LEN\n");
            return ScanPalSecamRet.SCAN_PAL_SECAM_RET_INTERNAL_ERROR;
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
        int iScanPalSecamExchangeData_native = TVNativeWrapper.ScanPalSecamExchangeData_native(iArr2);
        for (int i5 = 3; i5 < length; i5++) {
            iArr[i2] = iArr2[i5];
            i2++;
        }
        return iScanPalSecamExchangeData_native >= 0 ? ScanPalSecamRet.SCAN_PAL_SECAM_RET_OK : ScanPalSecamRet.SCAN_PAL_SECAM_RET_INTERNAL_ERROR;
    }

    private ScanPalSecamRet opOnlyTypeAndRet(int i) {
        a.a("Enter opOnlyTypeAndRet:", i, "\n", TAG);
        ScanPalSecamRet scanPalSecamRet = ScanPalSecamRet.SCAN_PAL_SECAM_RET_INTERNAL_ERROR;
        int[] iArr = {0};
        exchangeData(i, iArr);
        int i2 = iArr[0];
        a.a("ret opOnlyTypeAndRet:", i2, "\n", TAG);
        if (i2 == 0) {
            scanPalSecamRet = ScanPalSecamRet.SCAN_PAL_SECAM_RET_OK;
        }
        Log.d(TAG, "Leave opOnlyTypeAndRet\n");
        return scanPalSecamRet;
    }

    public ScanPalSecamRet cancelScan() {
        Log.d(TAG, "Enter cancelScan\n");
        ScanPalSecamRet scanPalSecamRetOpOnlyTypeAndRet = opOnlyTypeAndRet(1001);
        Log.d(TAG, "Leave cancelScan\n");
        return scanPalSecamRetOpOnlyTypeAndRet;
    }

    public ScanPalSecamRet getFreqRange(ScanPalSecamFreqRange scanPalSecamFreqRange) {
        ScanPalSecamRet scanPalSecamRet = ScanPalSecamRet.SCAN_PAL_SECAM_RET_INTERNAL_ERROR;
        int[] iArr = new int[3];
        Log.d(TAG, "Enter getFreqRange\n");
        exchangeData(2, iArr);
        int i = iArr[0];
        if (i == 0) {
            scanPalSecamRet = ScanPalSecamRet.SCAN_PAL_SECAM_RET_OK;
        }
        scanPalSecamFreqRange.upper_freq = iArr[1];
        scanPalSecamFreqRange.lower_freq = iArr[2];
        StringBuilder sbA = a.a("getFreqRange: ret ", i, " upper_freq ");
        sbA.append(scanPalSecamFreqRange.upper_freq);
        sbA.append(" lower_freq ");
        sbA.append(scanPalSecamFreqRange.lower_freq);
        sbA.append("\n");
        Log.d(TAG, sbA.toString());
        Log.d(TAG, "Leave getFreqRange\n");
        return scanPalSecamRet;
    }

    public ScanPalSecamRet startAutoScan() {
        Log.d(TAG, "Enter startAutoScan\n");
        ScanPalSecamRet scanPalSecamRetOpOnlyTypeAndRet = opOnlyTypeAndRet(1002);
        Log.d(TAG, "Leave startAutoScan\n");
        return scanPalSecamRetOpOnlyTypeAndRet;
    }

    public ScanPalSecamRet startRangeScan(int i, int i2) {
        ScanPalSecamRet scanPalSecamRet = ScanPalSecamRet.SCAN_PAL_SECAM_RET_INTERNAL_ERROR;
        Log.d(TAG, a.a("Enter startRangeScan startFreq ", i, " endFreq ", i2, "\n"));
        int[] iArr = {i, i2};
        exchangeData(1004, iArr);
        if (iArr[0] == 0) {
            scanPalSecamRet = ScanPalSecamRet.SCAN_PAL_SECAM_RET_OK;
        }
        Log.d(TAG, "Leave startRangeScan return" + scanPalSecamRet + "\n");
        return scanPalSecamRet;
    }

    public ScanPalSecamRet startUpdateScan() {
        Log.d(TAG, "Enter startUpdateScan\n");
        ScanPalSecamRet scanPalSecamRetOpOnlyTypeAndRet = opOnlyTypeAndRet(1003);
        Log.d(TAG, "Leave startUpdateScan\n");
        return scanPalSecamRetOpOnlyTypeAndRet;
    }
}
