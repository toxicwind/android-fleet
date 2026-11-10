package com.mediatek.twoworlds.tv;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvISDBRatingBase {
    public static final String TAG = "MtkTvISDBRatingBase";

    public int getISDBContentRatingSetting() {
        int iSDBContentRatingSetting_native = TVNativeWrapper.getISDBContentRatingSetting_native();
        Log.d(TAG, "isdbCntRatingsetting=" + iSDBContentRatingSetting_native);
        return iSDBContentRatingSetting_native;
    }

    public void setISDBContentRatingSetting(int i) {
        Log.d(TAG, "setISDBContentRating ret=" + TVNativeWrapper.setISDBContentRatingSetting_native(i));
    }

    public int getISDBAgeRatingSetting() {
        int iSDBAgeRatingSetting_native = TVNativeWrapper.getISDBAgeRatingSetting_native();
        Log.d(TAG, "isdbCntRatingsetting=" + iSDBAgeRatingSetting_native);
        return iSDBAgeRatingSetting_native;
    }

    public void setISDBAgeRatingSetting(int i) {
        Log.d(TAG, "ageRatingSetting ret=" + TVNativeWrapper.setISDBAgeRatingSetting_native(i));
    }
}
