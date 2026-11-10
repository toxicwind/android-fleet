package com.mediatek.twoworlds.tv;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvDVBRatingBase {
    public static final String TAG = "MtkTvDVBRatingBase";

    public int getDVBAgeRatingSetting() {
        int dVBAgeRatingSetting_native = TVNativeWrapper.getDVBAgeRatingSetting_native();
        a.b("dvbAgeRatingsetting=", dVBAgeRatingSetting_native, TAG);
        return dVBAgeRatingSetting_native;
    }

    public void setDVBAgeRatingSetting(int i) {
        a.b("ageRatingSetting ret=", TVNativeWrapper.setDVBAgeRatingSetting_native(i), TAG);
    }
}
