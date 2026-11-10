package com.mediatek.twoworlds.tv;

import android.util.Log;
import com.mediatek.twoworlds.tv.model.MtkTvOpenVCHIPInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvOpenVCHIPParaBase;
import com.mediatek.twoworlds.tv.model.MtkTvOpenVCHIPSettingInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvUSTvRatingSettingInfoBase;

/* JADX INFO: loaded from: classes.dex */
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

    public MtkTvUSTvRatingSettingInfoBase getUSTvRatingSettingInfo() {
        MtkTvUSTvRatingSettingInfoBase mtkTvUSTvRatingSettingInfoBase = new MtkTvUSTvRatingSettingInfoBase();
        Log.d(TAG, "getUSTvRating ret=" + TVNativeWrapper.getUSTvRatingSettingInfo_native(mtkTvUSTvRatingSettingInfoBase));
        return mtkTvUSTvRatingSettingInfoBase;
    }

    public void setUSTvRatingSettingInfo(MtkTvUSTvRatingSettingInfoBase mtkTvUSTvRatingSettingInfoBase) {
        Log.d(TAG, "setUSTvRating ret=" + TVNativeWrapper.setUSTvRatingSettingInfo_native(mtkTvUSTvRatingSettingInfoBase));
    }

    public int getUSMovieRatingSettingInfo() {
        int uSMovieRatingSettingInfo_native = TVNativeWrapper.getUSMovieRatingSettingInfo_native();
        Log.d(TAG, "movieRatingSettingInfo=" + uSMovieRatingSettingInfo_native);
        return uSMovieRatingSettingInfo_native;
    }

    public void setUSMovieRatingSettingInfo(int i) {
        Log.d(TAG, "setUSMovieRating ret=" + TVNativeWrapper.setUSMovieRatingSettingInfo_native(i));
    }

    public int getCANEngRatingSettingInfo() {
        int cANEngRatingSettingInfo_native = TVNativeWrapper.getCANEngRatingSettingInfo_native();
        Log.d(TAG, "engRatingSettingInfo=" + cANEngRatingSettingInfo_native);
        return cANEngRatingSettingInfo_native;
    }

    public void setCANEngRatingSettingInfo(int i) {
        Log.d(TAG, "setCANEngRating ret=" + TVNativeWrapper.setCANEngRatingSettingInfo_native(i));
    }

    public int getCANFreRatingSettingInfo() {
        int cANFreRatingSettingInfo_native = TVNativeWrapper.getCANFreRatingSettingInfo_native();
        Log.d(TAG, "freRatingSettingInfo=" + cANFreRatingSettingInfo_native);
        return cANFreRatingSettingInfo_native;
    }

    public void setCANFreRatingSettingInfo(int i) {
        Log.d(TAG, "setCANFreRating ret=" + TVNativeWrapper.setCANFreRatingSettingInfo_native(i));
    }

    public boolean getBlockUnrated() {
        boolean blockUnrated_native = TVNativeWrapper.getBlockUnrated_native();
        Log.d(TAG, "isBlockUnrated=" + blockUnrated_native);
        return blockUnrated_native;
    }

    public void setBlockUnrated(boolean z) {
        Log.d(TAG, "setBlockUnrated ret=" + TVNativeWrapper.setBlockUnrated_native(z));
    }

    public boolean getRatingEnable() {
        boolean ratingEnable_native = TVNativeWrapper.getRatingEnable_native();
        Log.d(TAG, "isRatingEnable=" + ratingEnable_native);
        return ratingEnable_native;
    }

    public void setRatingEnable(boolean z) {
        Log.d(TAG, "setRatingEnable ret=" + TVNativeWrapper.setRatingEnable_native(z));
    }

    public boolean isOpenVCHIPInfoAvailable() {
        boolean zIsOpenVCHIPInfoAvailable_native = TVNativeWrapper.isOpenVCHIPInfoAvailable_native();
        Log.d(TAG, "isOpenVCHIPAvail=" + zIsOpenVCHIPInfoAvailable_native);
        return zIsOpenVCHIPInfoAvailable_native;
    }

    public MtkTvOpenVCHIPInfoBase getOpenVCHIPInfo(MtkTvOpenVCHIPParaBase mtkTvOpenVCHIPParaBase) {
        MtkTvOpenVCHIPInfoBase mtkTvOpenVCHIPInfoBase = new MtkTvOpenVCHIPInfoBase();
        Log.d(TAG, "getOpenVCHIPInfo ret=" + TVNativeWrapper.getOpenVCHIPInfo_native(mtkTvOpenVCHIPParaBase, mtkTvOpenVCHIPInfoBase));
        return mtkTvOpenVCHIPInfoBase;
    }

    public void setOpenVCHIPSettingInfo(MtkTvOpenVCHIPSettingInfoBase mtkTvOpenVCHIPSettingInfoBase) {
        Log.d(TAG, "setOpenVCHIPSettingInfo ret=" + TVNativeWrapper.setOpenVCHIPSettingInfo_native(mtkTvOpenVCHIPSettingInfoBase));
    }

    public MtkTvOpenVCHIPSettingInfoBase getOpenVCHIPSettingInfo() {
        MtkTvOpenVCHIPSettingInfoBase mtkTvOpenVCHIPSettingInfoBase = new MtkTvOpenVCHIPSettingInfoBase();
        Log.d(TAG, "getOpenVCHIPSettingInfo ret=" + TVNativeWrapper.getOpenVCHIPSettingInfo_native(mtkTvOpenVCHIPSettingInfoBase));
        return mtkTvOpenVCHIPSettingInfoBase;
    }

    public MtkTvOpenVCHIPSettingInfoBase getOpenVCHIPSettingInfo(int i, int i2) {
        MtkTvOpenVCHIPSettingInfoBase mtkTvOpenVCHIPSettingInfoBase = new MtkTvOpenVCHIPSettingInfoBase();
        mtkTvOpenVCHIPSettingInfoBase.setRegionIndex(i);
        mtkTvOpenVCHIPSettingInfoBase.setDimIndex(i2);
        mtkTvOpenVCHIPSettingInfoBase.setUnratedBlock(0);
        Log.d(TAG, "getOpenVCHIPSettingInfo ret=" + TVNativeWrapper.getOpenVCHIPSettingInfo_native(mtkTvOpenVCHIPSettingInfoBase));
        return mtkTvOpenVCHIPSettingInfoBase;
    }

    public int setAtscStorage(boolean z) {
        int atscStorage_native = TVNativeWrapper.setAtscStorage_native(z);
        Log.d(TAG, "setAtscStorage ret=" + atscStorage_native);
        return atscStorage_native;
    }

    public boolean getUSTVGrougByChild() {
        boolean uSTVGrougByChild_native = TVNativeWrapper.getUSTVGrougByChild_native();
        Log.d(TAG, "getUSTVGrougByChild_native ret=" + uSTVGrougByChild_native);
        return uSTVGrougByChild_native;
    }
}
