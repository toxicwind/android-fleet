package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvBannerBase {
    public static final String TAG = "MtkTvBannerBase";

    public MtkTvBannerBase() {
        Log.d(TAG, "MtkTvBannerBase object created");
    }

    public String getAudioInfo() {
        String audioInfo_native = TVNativeWrapper.getAudioInfo_native(1, 19);
        a.c("getAudioInfo=", audioInfo_native, TAG);
        return audioInfo_native;
    }

    public String getCaption() {
        String caption_native = TVNativeWrapper.getCaption_native(1, 16);
        a.c("getCaption=", caption_native, TAG);
        return caption_native;
    }

    public String getChannelLogo() {
        String channelLogo_native = TVNativeWrapper.getChannelLogo_native(1, 40);
        a.c("getChannelLogo=", channelLogo_native, TAG);
        return channelLogo_native;
    }

    public String getChannelName() {
        String channelName_native = TVNativeWrapper.getChannelName_native(1, 4);
        a.c("getChannelName=", channelName_native, TAG);
        return channelName_native;
    }

    public String getChannelNumber() {
        String channelNumber_native = TVNativeWrapper.getChannelNumber_native(1, 3);
        a.c("getChannelNumber=", channelNumber_native, TAG);
        return channelNumber_native;
    }

    public String getIptChannelNumber() {
        String iptChannelNumber_native = TVNativeWrapper.getIptChannelNumber_native(1, 23);
        a.c("getIptChannelNumber=", iptChannelNumber_native, TAG);
        return iptChannelNumber_native;
    }

    public String getIptsCC() {
        String iptsCC_native = TVNativeWrapper.getIptsCC_native(1, 17);
        a.c("getIptsCC=", iptsCC_native, TAG);
        return iptsCC_native;
    }

    public String getIptsName() {
        String iptsName_native = TVNativeWrapper.getIptsName_native(1, 21);
        a.c("getIptsName=", iptsName_native, TAG);
        return iptsName_native;
    }

    public String getIptsRating() {
        String iptsRating_native = TVNativeWrapper.getIptsRating_native(1, 18);
        a.c("getIptsRating=", iptsRating_native, TAG);
        return iptsRating_native;
    }

    public String getIptsRslt() {
        String iptsRslt_native = TVNativeWrapper.getIptsRslt_native(1, 22);
        a.c("getIptsRslt=", iptsRslt_native, TAG);
        return iptsRslt_native;
    }

    public String getMsg() {
        String msg_native = TVNativeWrapper.getMsg_native(1, 7);
        a.c("getMsg=", msg_native, TAG);
        return msg_native;
    }

    public String getNextProgCategoryIdx() {
        String nextProgCategoryIdx_native = TVNativeWrapper.getNextProgCategoryIdx_native(1, 43);
        a.c("getNextProgCategoryIdx=", nextProgCategoryIdx_native, TAG);
        return nextProgCategoryIdx_native;
    }

    public String getNextProgDetail() {
        String nextProgDetail_native = TVNativeWrapper.getNextProgDetail_native(1, 41);
        a.c("getNextProgDetail=", nextProgDetail_native, TAG);
        return nextProgDetail_native;
    }

    public String getNextProgSecondaryTitle() {
        String nextProgSecondaryTitle_native = TVNativeWrapper.getNextProgSecondaryTitle_native(1, 39);
        a.c("getNextProgSecondaryTitle=", nextProgSecondaryTitle_native, TAG);
        return nextProgSecondaryTitle_native;
    }

    public String getNextProgTime() {
        String nextProgTime_native = TVNativeWrapper.getNextProgTime_native(1, 12);
        a.c("getNextProgTime=", nextProgTime_native, TAG);
        return nextProgTime_native;
    }

    public String getNextProgTitle() {
        String nextProgTitle_native = TVNativeWrapper.getNextProgTitle_native(1, 11);
        a.c("getNextProgTitle=", nextProgTitle_native, TAG);
        return nextProgTitle_native;
    }

    public String getNextRating() {
        String nextRating_native = TVNativeWrapper.getNextRating_native(1, 42);
        a.c("getNextRating=", nextRating_native, TAG);
        return nextRating_native;
    }

    public String getProgCategory() {
        String progCategory_native = TVNativeWrapper.getProgCategory_native(1, 9);
        a.c("getProgCategory=", progCategory_native, TAG);
        return progCategory_native;
    }

    public String getProgCategoryIdx() {
        String progCategoryIdx_native = TVNativeWrapper.getProgCategoryIdx_native(1, 37);
        a.c("getProgCategoryIdx=", progCategoryIdx_native, TAG);
        return progCategoryIdx_native;
    }

    public String getProgDetail() {
        String progDetail_native = TVNativeWrapper.getProgDetail_native(1, 13);
        a.c("getProgDetail=", progDetail_native, TAG);
        return progDetail_native;
    }

    public String getProgDetailPageIdx() {
        String progDetailPageIdx_native = TVNativeWrapper.getProgDetailPageIdx_native(1, 14);
        a.c("getProgDetailPageIdx=", progDetailPageIdx_native, TAG);
        return progDetailPageIdx_native;
    }

    public String getProgSecondaryTitle() {
        String progSecondaryTitle_native = TVNativeWrapper.getProgSecondaryTitle_native(1, 38);
        a.c("getProgSecondaryTitle=", progSecondaryTitle_native, TAG);
        return progSecondaryTitle_native;
    }

    public String getProgTime() {
        String progTime_native = TVNativeWrapper.getProgTime_native(1, 10);
        a.c("getProgTime=", progTime_native, TAG);
        return progTime_native;
    }

    public String getProgTitle() {
        String progTitle_native = TVNativeWrapper.getProgTitle_native(1, 8);
        a.c("getProgTitle=", progTitle_native, TAG);
        return progTitle_native;
    }

    public String getRating() {
        String rating_native = TVNativeWrapper.getRating_native(1, 15);
        a.c("getRating=", rating_native, TAG);
        return rating_native;
    }

    public String getTVSrc() {
        String tVSrc_native = TVNativeWrapper.getTVSrc_native(1, 5);
        a.c("getTVSrc=", tVSrc_native, TAG);
        return tVSrc_native;
    }

    public String getTimer() {
        String timer_native = TVNativeWrapper.getTimer_native(1, 6);
        a.c("getTimer=", timer_native, TAG);
        return timer_native;
    }

    public String getVideoInfo() {
        String videoInfo_native = TVNativeWrapper.getVideoInfo_native(1, 20);
        a.c("getVideoInfo=", videoInfo_native, TAG);
        return videoInfo_native;
    }

    public boolean isDisplayADAtmos() {
        boolean zIsDisplayADAtmos_native = TVNativeWrapper.isDisplayADAtmos_native(1, 36);
        a.a("isDisplayADAtmos=", zIsDisplayADAtmos_native, TAG);
        return zIsDisplayADAtmos_native;
    }

    public boolean isDisplayADEarIcon() {
        boolean zIsDisplayADEarIcon_native = TVNativeWrapper.isDisplayADEarIcon_native(1, 34);
        a.a("isDisplayADEarIcon=", zIsDisplayADEarIcon_native, TAG);
        return zIsDisplayADEarIcon_native;
    }

    public boolean isDisplayADEyeIcon() {
        boolean zIsDisplayADEyeIcon_native = TVNativeWrapper.isDisplayADEyeIcon_native(1, 33);
        a.a("isDisplayADEyeIcon=", zIsDisplayADEyeIcon_native, TAG);
        return zIsDisplayADEyeIcon_native;
    }

    public boolean isDisplayBanner() {
        boolean zIsDisplayBanner_native = TVNativeWrapper.isDisplayBanner_native(1, 35);
        a.a("isDisplayBanner=", zIsDisplayBanner_native, TAG);
        return zIsDisplayBanner_native;
    }

    public boolean isDisplayCaptionIcon() {
        boolean zIsDisplayCaptionIcon_native = TVNativeWrapper.isDisplayCaptionIcon_native(1, 27);
        a.a("isDisplayCaptionIcon=", zIsDisplayCaptionIcon_native, TAG);
        return zIsDisplayCaptionIcon_native;
    }

    public boolean isDisplayFavIcon() {
        boolean zIsDisplayFavIcon_native = TVNativeWrapper.isDisplayFavIcon_native(1, 26);
        a.a("isDisplayFavIcon=", zIsDisplayFavIcon_native, TAG);
        return zIsDisplayFavIcon_native;
    }

    public boolean isDisplayFrmCH() {
        boolean zIsDisplayFrmCH_native = TVNativeWrapper.isDisplayFrmCH_native(1, 0);
        a.a("isDisplayFrmCH=", zIsDisplayFrmCH_native, TAG);
        return zIsDisplayFrmCH_native;
    }

    public boolean isDisplayFrmDetail() {
        boolean zIsDisplayFrmDetail_native = TVNativeWrapper.isDisplayFrmDetail_native(1, 2);
        a.a("isDisplayFrmDetail=", zIsDisplayFrmDetail_native, TAG);
        return zIsDisplayFrmDetail_native;
    }

    public boolean isDisplayFrmInfo() {
        boolean zIsDisplayFrmInfo_native = TVNativeWrapper.isDisplayFrmInfo_native(1, 1);
        a.a("isDisplayFrmInfo=", zIsDisplayFrmInfo_native, TAG);
        return zIsDisplayFrmInfo_native;
    }

    public boolean isDisplayGingaIcon() {
        boolean zIsDisplayGingaIcon_native = TVNativeWrapper.isDisplayGingaIcon_native(1, 32);
        a.a("isDisplayGingaIcon=", zIsDisplayGingaIcon_native, TAG);
        return zIsDisplayGingaIcon_native;
    }

    public boolean isDisplayIptsLockIcon() {
        boolean zIsDisplayIptsLockIcon_native = TVNativeWrapper.isDisplayIptsLockIcon_native(1, 24);
        a.a("isDisplayIptsLockIcon=", zIsDisplayIptsLockIcon_native, TAG);
        return zIsDisplayIptsLockIcon_native;
    }

    public boolean isDisplayLogoIcon() {
        boolean zIsDisplayLogoIcon_native = TVNativeWrapper.isDisplayLogoIcon_native(1, 31);
        a.a("isDisplayLogoIcon=", zIsDisplayLogoIcon_native, TAG);
        return zIsDisplayLogoIcon_native;
    }

    public boolean isDisplayProgDetailDownIcon() {
        boolean zIsDisplayProgDetailDownIcon_native = TVNativeWrapper.isDisplayProgDetailDownIcon_native(1, 30);
        a.a("isDisplayProgDetailDownIcon=", zIsDisplayProgDetailDownIcon_native, TAG);
        return zIsDisplayProgDetailDownIcon_native;
    }

    public boolean isDisplayProgDetailUpIcon() {
        boolean zIsDisplayProgDetailUpIcon_native = TVNativeWrapper.isDisplayProgDetailUpIcon_native(1, 29);
        a.a("isDisplayProgDetailUpIcon=", zIsDisplayProgDetailUpIcon_native, TAG);
        return zIsDisplayProgDetailUpIcon_native;
    }

    public boolean isDisplayTVLockIcon() {
        boolean zIsDisplayTVLockIcon_native = TVNativeWrapper.isDisplayTVLockIcon_native(1, 25);
        a.a("isDisplayTVLockIcon=", zIsDisplayTVLockIcon_native, TAG);
        return zIsDisplayTVLockIcon_native;
    }

    public boolean isDisplayTtxIcon() {
        boolean zIsDisplayTtxIcon_native = TVNativeWrapper.isDisplayTtxIcon_native(1, 28);
        a.a("isDisplayTtxIcon=", zIsDisplayTtxIcon_native, TAG);
        return zIsDisplayTtxIcon_native;
    }
}
