package com.mediatek.twoworlds.tv;

import android.util.Log;
import com.mediatek.twoworlds.tv.model.MtkTvBisskeyHeader;
import com.mediatek.twoworlds.tv.model.MtkTvBisskeyInfoBase;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvBisskeyBase {
    public static final int BSL_OPERATOR_ADD = 0;
    public static final int BSL_OPERATOR_DEL = 2;
    public static final int BSL_OPERATOR_MOD = 1;
    public static final int BSL_RET_FAIL = -1;
    public static final int BSL_RET_OK = 0;
    static final String TAG = "MtkTvBisskey";

    public int getRecordsNumber(int i) {
        Log.d(TAG, "Enter getRecordsNumber(" + i + ")\n");
        int recordsNumber_native = TVNativeWrapper.getRecordsNumber_native(i);
        if (recordsNumber_native >= 0) {
            return recordsNumber_native;
        }
        Log.e(TAG, "TVNativeWrapper.getRecordsNumber failed! return " + recordsNumber_native + ".\n");
        return -1;
    }

    public int setBisskeyInfo(int i, MtkTvBisskeyInfoBase mtkTvBisskeyInfoBase, int i2) {
        Log.d(TAG, "Enter setBisskeyInfo(" + i + ", bslInfo ," + i2 + ")\n");
        if (i2 < 0 || i2 > 2) {
            Log.e(TAG, "operator unrecognized failed! operator = " + i2 + ".\n");
            return -1;
        }
        int bisskeyInfo_native = TVNativeWrapper.setBisskeyInfo_native(i, mtkTvBisskeyInfoBase, i2);
        if (bisskeyInfo_native >= 0) {
            return bisskeyInfo_native;
        }
        Log.e(TAG, "TVNativeWrapper.setBisskeyInfo failed! return " + bisskeyInfo_native + ".\n");
        return -1;
    }

    public MtkTvBisskeyInfoBase getRecordByBslRecId(int i, int i2) {
        Log.d(TAG, "Enter getRecordByBslRecId(" + i + ", " + i2 + ")\n");
        return TVNativeWrapper.getRecordByBslRecId_native(i, i2);
    }

    public int getRecordNumByHeader(int i, MtkTvBisskeyHeader mtkTvBisskeyHeader) {
        Log.d(TAG, "Enter getRecordNumByHeader(" + i + ")\n");
        if (mtkTvBisskeyHeader == null) {
            Log.e(TAG, "header is null! .\n");
            return -1;
        }
        int recordNumByHeader_native = TVNativeWrapper.getRecordNumByHeader_native(i, mtkTvBisskeyHeader);
        if (recordNumByHeader_native >= 0) {
            return recordNumByHeader_native;
        }
        Log.e(TAG, "TVNativeWrapper.getRecordNumByHeader failed! return " + recordNumByHeader_native + ".\n");
        return -1;
    }

    public MtkTvBisskeyInfoBase getRecordByHeader(int i, MtkTvBisskeyHeader mtkTvBisskeyHeader, int i2) {
        Log.d(TAG, "Enter getRecordByHeader(" + i + ", index=" + i2 + ")\n");
        if (mtkTvBisskeyHeader == null) {
            Log.e(TAG, "header is null! .\n");
            return null;
        }
        return TVNativeWrapper.getRecordByHeader_native(i, mtkTvBisskeyHeader, i2);
    }

    public MtkTvBisskeyInfoBase getRecordByIndex(int i, int i2) {
        Log.d(TAG, "Enter getRecordByIndex(" + i + ", " + i2 + ")\n");
        return TVNativeWrapper.getRecordByIndex_native(i, i2);
    }

    public int bisskeyClean(int i) {
        Log.d(TAG, "Enter bisskeyClean(" + i + ")\n");
        int iBisskeyClean_native = TVNativeWrapper.bisskeyClean_native(i);
        if (iBisskeyClean_native >= 0) {
            return iBisskeyClean_native;
        }
        Log.e(TAG, "TVNativeWrapper.bisskeyClean failed! return " + iBisskeyClean_native + ".\n");
        return -1;
    }

    public boolean bisskeyIsSameRecord(MtkTvBisskeyInfoBase mtkTvBisskeyInfoBase, MtkTvBisskeyInfoBase mtkTvBisskeyInfoBase2) {
        Log.d(TAG, "Enter bisskeyIsSameRecord()\n");
        int iBisskeyIsSameRecord_native = TVNativeWrapper.bisskeyIsSameRecord_native(mtkTvBisskeyInfoBase, mtkTvBisskeyInfoBase2);
        Log.d(TAG, "TVNativeWrapper.bisskeyIsSameRecord , return " + iBisskeyIsSameRecord_native + ".\n");
        return iBisskeyIsSameRecord_native == 1;
    }

    public MtkTvBisskeyInfoBase bisskeyGetDefaultRecord() {
        Log.d(TAG, "Enter bisskeyGetDefaultRecord()\n");
        return TVNativeWrapper.bisskeyGetDefaultRecord_native();
    }

    public int bisskeySetKeyForCurrentChannel() {
        Log.d(TAG, "Enter bisskeySetKeyForCurrentChannels()\n");
        return TVNativeWrapper.bisskeySetKeyForCurrentChannel_native();
    }
}
