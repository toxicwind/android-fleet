package com.mediatek.twoworlds.tv;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvScanPalSecamBase {
    private static final int EXCHANGE_GET_TYPE_FREQ_RANGE = 2;
    private static final int EXCHANGE_GET_TYPE_RF_INFO = 1;
    private static final int EXCHANGE_GET_TYPE_UNKNOWN = 0;
    private static final int EXCHANGE_HEADER_LEN = 3;
    private static final int EXCHANGE_HEADER_LEN_IDX = 1;
    private static final int EXCHANGE_HEADER_TYPE_IDX = 2;
    private static final int EXCHANGE_MAX_LEN = 50;
    private static final int EXCHANGE_PAYLOAD_1ST_IDX = 3;
    private static final int EXCHANGE_SET_TYPE_AUTO_SCAN = 1002;
    private static final int EXCHANGE_SET_TYPE_CANCEL_SCAN = 1001;
    private static final int EXCHANGE_SET_TYPE_RANGE_SCAN = 1004;
    private static final int EXCHANGE_SET_TYPE_UNKNOWN = 1000;
    private static final int EXCHANGE_SET_TYPE_UPDATE_SCAN = 1003;
    private static final int EXCHANGE_TOTAL_LEN_IDX = 0;
    private static final String TAG = "MtkTvScanPalSecam";

    public enum ScanPalSecamRet {
        SCAN_PAL_SECAM_RET_OK,
        SCAN_PAL_SECAM_RET_INTERNAL_ERROR
    }

    public class ScanPalSecamFreqRange {
        public int lower_freq;
        public int upper_freq;

        public ScanPalSecamFreqRange() {
        }
    }

    public ScanPalSecamRet startAutoScan() {
        Log.d(TAG, "Enter startAutoScan\n");
        ScanPalSecamRet scanPalSecamRetOpOnlyTypeAndRet = opOnlyTypeAndRet(1002);
        Log.d(TAG, "Leave startAutoScan\n");
        return scanPalSecamRetOpOnlyTypeAndRet;
    }

    public ScanPalSecamRet startRangeScan(int i, int i2) {
        ScanPalSecamRet scanPalSecamRet = ScanPalSecamRet.SCAN_PAL_SECAM_RET_INTERNAL_ERROR;
        Log.d(TAG, "Enter startRangeScan startFreq " + i + " endFreq " + i2 + "\n");
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
        Log.d(TAG, "getFreqRange: ret " + i + " upper_freq " + scanPalSecamFreqRange.upper_freq + " lower_freq " + scanPalSecamFreqRange.lower_freq + "\n");
        Log.d(TAG, "Leave getFreqRange\n");
        return scanPalSecamRet;
    }

    private ScanPalSecamRet opOnlyTypeAndRet(int i) {
        Log.d(TAG, "Enter opOnlyTypeAndRet:" + i + "\n");
        ScanPalSecamRet scanPalSecamRet = ScanPalSecamRet.SCAN_PAL_SECAM_RET_INTERNAL_ERROR;
        int[] iArr = {0};
        exchangeData(i, iArr);
        int i2 = iArr[0];
        Log.d(TAG, "ret opOnlyTypeAndRet:" + i2 + "\n");
        if (i2 == 0) {
            scanPalSecamRet = ScanPalSecamRet.SCAN_PAL_SECAM_RET_OK;
        }
        Log.d(TAG, "Leave opOnlyTypeAndRet\n");
        return scanPalSecamRet;
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
        if (iScanPalSecamExchangeData_native >= 0) {
            return ScanPalSecamRet.SCAN_PAL_SECAM_RET_OK;
        }
        return ScanPalSecamRet.SCAN_PAL_SECAM_RET_INTERNAL_ERROR;
    }
}
