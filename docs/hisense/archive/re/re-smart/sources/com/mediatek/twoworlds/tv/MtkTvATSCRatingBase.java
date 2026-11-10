package com.mediatek.twoworlds.tv;

import b.a.a.a.a;
import com.mediatek.twoworlds.tv.model.MtkTvOpenVCHIPInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvOpenVCHIPParaBase;
import com.mediatek.twoworlds.tv.model.MtkTvOpenVCHIPSettingInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvUSTvRatingSettingInfoBase;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvATSCRatingBase {
    public static final int MTKTV_ATSC_CANADA_ENG_14 = 5;
    public static final int MTKTV_ATSC_CANADA_ENG_18 = 6;
    public static final int MTKTV_ATSC_CANADA_ENG_C = 1;
    public static final int MTKTV_ATSC_CANADA_ENG_C8 = 2;
    public static final int MTKTV_ATSC_CANADA_ENG_E = 0;
    public static final int MTKTV_ATSC_CANADA_ENG_G = 3;
    public static final int MTKTV_ATSC_CANADA_ENG_OFF = 7;
    public static final int MTKTV_ATSC_CANADA_ENG_PG = 4;
    public static final int MTKTV_ATSC_CANADA_FRE_13 = 3;
    public static final int MTKTV_ATSC_CANADA_FRE_16 = 4;
    public static final int MTKTV_ATSC_CANADA_FRE_18 = 5;
    public static final int MTKTV_ATSC_CANADA_FRE_8 = 2;
    public static final int MTKTV_ATSC_CANADA_FRE_E = 0;
    public static final int MTKTV_ATSC_CANADA_FRE_G = 1;
    public static final int MTKTV_ATSC_CANADA_FRE_OFF = 6;
    public static final int MTKTV_ATSC_MOVIE_G = 0;
    public static final int MTKTV_ATSC_MOVIE_NC_17 = 4;
    public static final int MTKTV_ATSC_MOVIE_OFF = 6;
    public static final int MTKTV_ATSC_MOVIE_PG = 1;
    public static final int MTKTV_ATSC_MOVIE_PG13 = 2;
    public static final int MTKTV_ATSC_MOVIE_R = 3;
    public static final int MTKTV_ATSC_MOVIE_X = 5;
    public static final String TAG = "MtkTvATSCRatingBase";

    public boolean getBlockUnrated() {
        boolean blockUnrated_native = TVNativeWrapper.getBlockUnrated_native();
        a.a("isBlockUnrated=", blockUnrated_native, TAG);
        return blockUnrated_native;
    }

    public int getCANEngRatingSettingInfo() {
        int cANEngRatingSettingInfo_native = TVNativeWrapper.getCANEngRatingSettingInfo_native();
        a.b("engRatingSettingInfo=", cANEngRatingSettingInfo_native, TAG);
        return cANEngRatingSettingInfo_native;
    }

    public int getCANFreRatingSettingInfo() {
        int cANFreRatingSettingInfo_native = TVNativeWrapper.getCANFreRatingSettingInfo_native();
        a.b("freRatingSettingInfo=", cANFreRatingSettingInfo_native, TAG);
        return cANFreRatingSettingInfo_native;
    }

    public MtkTvOpenVCHIPInfoBase getOpenVCHIPInfo(MtkTvOpenVCHIPParaBase mtkTvOpenVCHIPParaBase) {
        MtkTvOpenVCHIPInfoBase mtkTvOpenVCHIPInfoBase = new MtkTvOpenVCHIPInfoBase();
        a.b("getOpenVCHIPInfo ret=", TVNativeWrapper.getOpenVCHIPInfo_native(mtkTvOpenVCHIPParaBase, mtkTvOpenVCHIPInfoBase), TAG);
        return mtkTvOpenVCHIPInfoBase;
    }

    public MtkTvOpenVCHIPSettingInfoBase getOpenVCHIPSettingInfo() {
        MtkTvOpenVCHIPSettingInfoBase mtkTvOpenVCHIPSettingInfoBase = new MtkTvOpenVCHIPSettingInfoBase();
        a.b("getOpenVCHIPSettingInfo ret=", TVNativeWrapper.getOpenVCHIPSettingInfo_native(mtkTvOpenVCHIPSettingInfoBase), TAG);
        return mtkTvOpenVCHIPSettingInfoBase;
    }

    public boolean getRatingEnable() {
        boolean ratingEnable_native = TVNativeWrapper.getRatingEnable_native();
        a.a("isRatingEnable=", ratingEnable_native, TAG);
        return ratingEnable_native;
    }

    public int getUSMovieRatingSettingInfo() {
        int uSMovieRatingSettingInfo_native = TVNativeWrapper.getUSMovieRatingSettingInfo_native();
        a.b("movieRatingSettingInfo=", uSMovieRatingSettingInfo_native, TAG);
        return uSMovieRatingSettingInfo_native;
    }

    public boolean getUSTVGrougByChild() {
        boolean uSTVGrougByChild_native = TVNativeWrapper.getUSTVGrougByChild_native();
        a.a("getUSTVGrougByChild_native ret=", uSTVGrougByChild_native, TAG);
        return uSTVGrougByChild_native;
    }

    public MtkTvUSTvRatingSettingInfoBase getUSTvRatingSettingInfo() {
        MtkTvUSTvRatingSettingInfoBase mtkTvUSTvRatingSettingInfoBase = new MtkTvUSTvRatingSettingInfoBase();
        a.b("getUSTvRating ret=", TVNativeWrapper.getUSTvRatingSettingInfo_native(mtkTvUSTvRatingSettingInfoBase), TAG);
        return mtkTvUSTvRatingSettingInfoBase;
    }

    public boolean isOpenVCHIPInfoAvailable() {
        boolean zIsOpenVCHIPInfoAvailable_native = TVNativeWrapper.isOpenVCHIPInfoAvailable_native();
        a.a("isOpenVCHIPAvail=", zIsOpenVCHIPInfoAvailable_native, TAG);
        return zIsOpenVCHIPInfoAvailable_native;
    }

    public int setAtscStorage(boolean z) {
        int atscStorage_native = TVNativeWrapper.setAtscStorage_native(z);
        a.b("setAtscStorage ret=", atscStorage_native, TAG);
        return atscStorage_native;
    }

    public void setBlockUnrated(boolean z) {
        a.b("setBlockUnrated ret=", TVNativeWrapper.setBlockUnrated_native(z), TAG);
    }

    public void setCANEngRatingSettingInfo(int i) {
        a.b("setCANEngRating ret=", TVNativeWrapper.setCANEngRatingSettingInfo_native(i), TAG);
    }

    public void setCANFreRatingSettingInfo(int i) {
        a.b("setCANFreRating ret=", TVNativeWrapper.setCANFreRatingSettingInfo_native(i), TAG);
    }

    public void setOpenVCHIPSettingInfo(MtkTvOpenVCHIPSettingInfoBase mtkTvOpenVCHIPSettingInfoBase) {
        a.b("setOpenVCHIPSettingInfo ret=", TVNativeWrapper.setOpenVCHIPSettingInfo_native(mtkTvOpenVCHIPSettingInfoBase), TAG);
    }

    public void setRatingEnable(boolean z) {
        a.b("setRatingEnable ret=", TVNativeWrapper.setRatingEnable_native(z), TAG);
    }

    public void setUSMovieRatingSettingInfo(int i) {
        a.b("setUSMovieRating ret=", TVNativeWrapper.setUSMovieRatingSettingInfo_native(i), TAG);
    }

    public void setUSTvRatingSettingInfo(MtkTvUSTvRatingSettingInfoBase mtkTvUSTvRatingSettingInfoBase) {
        a.b("setUSTvRating ret=", TVNativeWrapper.setUSTvRatingSettingInfo_native(mtkTvUSTvRatingSettingInfoBase), TAG);
    }

    public MtkTvOpenVCHIPSettingInfoBase getOpenVCHIPSettingInfo(int i, int i2) {
        MtkTvOpenVCHIPSettingInfoBase mtkTvOpenVCHIPSettingInfoBase = new MtkTvOpenVCHIPSettingInfoBase();
        mtkTvOpenVCHIPSettingInfoBase.setRegionIndex(i);
        mtkTvOpenVCHIPSettingInfoBase.setDimIndex(i2);
        mtkTvOpenVCHIPSettingInfoBase.setUnratedBlock(0);
        a.b("getOpenVCHIPSettingInfo ret=", TVNativeWrapper.getOpenVCHIPSettingInfo_native(mtkTvOpenVCHIPSettingInfoBase), TAG);
        return mtkTvOpenVCHIPSettingInfoBase;
    }
}
