package com.mediatek.twoworlds.tv;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvDVBRatingBase {
    public static final String TAG = "MtkTvDVBRatingBase";

    public int getDVBAgeRatingSetting() {
        int dVBAgeRatingSetting_native = TVNativeWrapper.getDVBAgeRatingSetting_native();
        Log.d(TAG, "dvbAgeRatingsetting=" + dVBAgeRatingSetting_native);
        return dVBAgeRatingSetting_native;
    }

    public void setDVBAgeRatingSetting(int i) {
        Log.d(TAG, "ageRatingSetting ret=" + TVNativeWrapper.setDVBAgeRatingSetting_native(i));
    }
}
