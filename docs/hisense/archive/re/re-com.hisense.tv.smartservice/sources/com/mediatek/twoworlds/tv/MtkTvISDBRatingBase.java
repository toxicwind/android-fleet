package com.mediatek.twoworlds.tv;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvISDBRatingBase {
    public static final String TAG = "MtkTvISDBRatingBase";

    public int getISDBAgeRatingSetting() {
        int iSDBAgeRatingSetting_native = TVNativeWrapper.getISDBAgeRatingSetting_native();
        a.b("isdbCntRatingsetting=", iSDBAgeRatingSetting_native, TAG);
        return iSDBAgeRatingSetting_native;
    }

    public int getISDBContentRatingSetting() {
        int iSDBContentRatingSetting_native = TVNativeWrapper.getISDBContentRatingSetting_native();
        a.b("isdbCntRatingsetting=", iSDBContentRatingSetting_native, TAG);
        return iSDBContentRatingSetting_native;
    }

    public void setISDBAgeRatingSetting(int i) {
        a.b("ageRatingSetting ret=", TVNativeWrapper.setISDBAgeRatingSetting_native(i), TAG);
    }

    public void setISDBContentRatingSetting(int i) {
        a.b("setISDBContentRating ret=", TVNativeWrapper.setISDBContentRatingSetting_native(i), TAG);
    }
}
