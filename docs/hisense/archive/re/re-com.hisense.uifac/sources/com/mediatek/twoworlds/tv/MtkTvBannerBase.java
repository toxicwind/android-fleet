package com.mediatek.twoworlds.tv;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvBannerBase {
    public static final String TAG = "MtkTvBannerBase";

    public MtkTvBannerBase() {
        Log.d(TAG, "MtkTvBannerBase object created");
    }

    public boolean isDisplayFrmCH() {
        boolean zIsDisplayFrmCH_native = TVNativeWrapper.isDisplayFrmCH_native(1, 0);
        Log.d(TAG, "isDisplayFrmCH=" + zIsDisplayFrmCH_native);
        return zIsDisplayFrmCH_native;
    }

    public boolean isDisplayFrmInfo() {
        boolean zIsDisplayFrmInfo_native = TVNativeWrapper.isDisplayFrmInfo_native(1, 1);
        Log.d(TAG, "isDisplayFrmInfo=" + zIsDisplayFrmInfo_native);
        return zIsDisplayFrmInfo_native;
    }

    public boolean isDisplayFrmDetail() {
        boolean zIsDisplayFrmDetail_native = TVNativeWrapper.isDisplayFrmDetail_native(1, 2);
        Log.d(TAG, "isDisplayFrmDetail=" + zIsDisplayFrmDetail_native);
        return zIsDisplayFrmDetail_native;
    }

    public String getChannelNumber() {
        String channelNumber_native = TVNativeWrapper.getChannelNumber_native(1, 3);
        Log.d(TAG, "getChannelNumber=" + channelNumber_native);
        return channelNumber_native;
    }

    public String getChannelName() {
        String channelName_native = TVNativeWrapper.getChannelName_native(1, 4);
        Log.d(TAG, "getChannelName=" + channelName_native);
        return channelName_native;
    }

    public String getTVSrc() {
        String tVSrc_native = TVNativeWrapper.getTVSrc_native(1, 5);
        Log.d(TAG, "getTVSrc=" + tVSrc_native);
        return tVSrc_native;
    }

    public String getTimer() {
        String timer_native = TVNativeWrapper.getTimer_native(1, 6);
        Log.d(TAG, "getTimer=" + timer_native);
        return timer_native;
    }

    public String getMsg() {
        String msg_native = TVNativeWrapper.getMsg_native(1, 7);
        Log.d(TAG, "getMsg=" + msg_native);
        return msg_native;
    }

    public String getProgTitle() {
        String progTitle_native = TVNativeWrapper.getProgTitle_native(1, 8);
        Log.d(TAG, "getProgTitle=" + progTitle_native);
        return progTitle_native;
    }

    public String getProgCategory() {
        String progCategory_native = TVNativeWrapper.getProgCategory_native(1, 9);
        Log.d(TAG, "getProgCategory=" + progCategory_native);
        return progCategory_native;
    }

    public String getProgCategoryIdx() {
        String progCategoryIdx_native = TVNativeWrapper.getProgCategoryIdx_native(1, 37);
        Log.d(TAG, "getProgCategoryIdx=" + progCategoryIdx_native);
        return progCategoryIdx_native;
    }

    public String getProgSecondaryTitle() {
        String progSecondaryTitle_native = TVNativeWrapper.getProgSecondaryTitle_native(1, 38);
        Log.d(TAG, "getProgSecondaryTitle=" + progSecondaryTitle_native);
        return progSecondaryTitle_native;
    }

    public String getNextProgSecondaryTitle() {
        String nextProgSecondaryTitle_native = TVNativeWrapper.getNextProgSecondaryTitle_native(1, 39);
        Log.d(TAG, "getNextProgSecondaryTitle=" + nextProgSecondaryTitle_native);
        return nextProgSecondaryTitle_native;
    }

    public String getChannelLogo() {
        String channelLogo_native = TVNativeWrapper.getChannelLogo_native(1, 40);
        Log.d(TAG, "getChannelLogo=" + channelLogo_native);
        return channelLogo_native;
    }

    public String getNextProgDetail() {
        String nextProgDetail_native = TVNativeWrapper.getNextProgDetail_native(1, 41);
        Log.d(TAG, "getNextProgDetail=" + nextProgDetail_native);
        return nextProgDetail_native;
    }

    public String getNextProgCategoryIdx() {
        String nextProgCategoryIdx_native = TVNativeWrapper.getNextProgCategoryIdx_native(1, 43);
        Log.d(TAG, "getNextProgCategoryIdx=" + nextProgCategoryIdx_native);
        return nextProgCategoryIdx_native;
    }

    public String getNextRating() {
        String nextRating_native = TVNativeWrapper.getNextRating_native(1, 42);
        Log.d(TAG, "getNextRating=" + nextRating_native);
        return nextRating_native;
    }

    public String getProgTime() {
        String progTime_native = TVNativeWrapper.getProgTime_native(1, 10);
        Log.d(TAG, "getProgTime=" + progTime_native);
        return progTime_native;
    }

    public String getNextProgTitle() {
        String nextProgTitle_native = TVNativeWrapper.getNextProgTitle_native(1, 11);
        Log.d(TAG, "getNextProgTitle=" + nextProgTitle_native);
        return nextProgTitle_native;
    }

    public String getNextProgTime() {
        String nextProgTime_native = TVNativeWrapper.getNextProgTime_native(1, 12);
        Log.d(TAG, "getNextProgTime=" + nextProgTime_native);
        return nextProgTime_native;
    }

    public String getProgDetail() {
        String progDetail_native = TVNativeWrapper.getProgDetail_native(1, 13);
        Log.d(TAG, "getProgDetail=" + progDetail_native);
        return progDetail_native;
    }

    public String getProgDetailPageIdx() {
        String progDetailPageIdx_native = TVNativeWrapper.getProgDetailPageIdx_native(1, 14);
        Log.d(TAG, "getProgDetailPageIdx=" + progDetailPageIdx_native);
        return progDetailPageIdx_native;
    }

    public String getRating() {
        String rating_native = TVNativeWrapper.getRating_native(1, 15);
        Log.d(TAG, "getRating=" + rating_native);
        return rating_native;
    }

    public String getCaption() {
        String caption_native = TVNativeWrapper.getCaption_native(1, 16);
        Log.d(TAG, "getCaption=" + caption_native);
        return caption_native;
    }

    public String getIptsCC() {
        String iptsCC_native = TVNativeWrapper.getIptsCC_native(1, 17);
        Log.d(TAG, "getIptsCC=" + iptsCC_native);
        return iptsCC_native;
    }

    public String getIptsRating() {
        String iptsRating_native = TVNativeWrapper.getIptsRating_native(1, 18);
        Log.d(TAG, "getIptsRating=" + iptsRating_native);
        return iptsRating_native;
    }

    public String getAudioInfo() {
        String audioInfo_native = TVNativeWrapper.getAudioInfo_native(1, 19);
        Log.d(TAG, "getAudioInfo=" + audioInfo_native);
        return audioInfo_native;
    }

    public String getVideoInfo() {
        String videoInfo_native = TVNativeWrapper.getVideoInfo_native(1, 20);
        Log.d(TAG, "getVideoInfo=" + videoInfo_native);
        return videoInfo_native;
    }

    public String getIptsName() {
        String iptsName_native = TVNativeWrapper.getIptsName_native(1, 21);
        Log.d(TAG, "getIptsName=" + iptsName_native);
        return iptsName_native;
    }

    public String getIptsRslt() {
        String iptsRslt_native = TVNativeWrapper.getIptsRslt_native(1, 22);
        Log.d(TAG, "getIptsRslt=" + iptsRslt_native);
        return iptsRslt_native;
    }

    public String getIptChannelNumber() {
        String iptChannelNumber_native = TVNativeWrapper.getIptChannelNumber_native(1, 23);
        Log.d(TAG, "getIptChannelNumber=" + iptChannelNumber_native);
        return iptChannelNumber_native;
    }

    public boolean isDisplayIptsLockIcon() {
        boolean zIsDisplayIptsLockIcon_native = TVNativeWrapper.isDisplayIptsLockIcon_native(1, 24);
        Log.d(TAG, "isDisplayIptsLockIcon=" + zIsDisplayIptsLockIcon_native);
        return zIsDisplayIptsLockIcon_native;
    }

    public boolean isDisplayTVLockIcon() {
        boolean zIsDisplayTVLockIcon_native = TVNativeWrapper.isDisplayTVLockIcon_native(1, 25);
        Log.d(TAG, "isDisplayTVLockIcon=" + zIsDisplayTVLockIcon_native);
        return zIsDisplayTVLockIcon_native;
    }

    public boolean isDisplayFavIcon() {
        boolean zIsDisplayFavIcon_native = TVNativeWrapper.isDisplayFavIcon_native(1, 26);
        Log.d(TAG, "isDisplayFavIcon=" + zIsDisplayFavIcon_native);
        return zIsDisplayFavIcon_native;
    }

    public boolean isDisplayCaptionIcon() {
        boolean zIsDisplayCaptionIcon_native = TVNativeWrapper.isDisplayCaptionIcon_native(1, 27);
        Log.d(TAG, "isDisplayCaptionIcon=" + zIsDisplayCaptionIcon_native);
        return zIsDisplayCaptionIcon_native;
    }

    public boolean isDisplayTtxIcon() {
        boolean zIsDisplayTtxIcon_native = TVNativeWrapper.isDisplayTtxIcon_native(1, 28);
        Log.d(TAG, "isDisplayTtxIcon=" + zIsDisplayTtxIcon_native);
        return zIsDisplayTtxIcon_native;
    }

    public boolean isDisplayProgDetailUpIcon() {
        boolean zIsDisplayProgDetailUpIcon_native = TVNativeWrapper.isDisplayProgDetailUpIcon_native(1, 29);
        Log.d(TAG, "isDisplayProgDetailUpIcon=" + zIsDisplayProgDetailUpIcon_native);
        return zIsDisplayProgDetailUpIcon_native;
    }

    public boolean isDisplayProgDetailDownIcon() {
        boolean zIsDisplayProgDetailDownIcon_native = TVNativeWrapper.isDisplayProgDetailDownIcon_native(1, 30);
        Log.d(TAG, "isDisplayProgDetailDownIcon=" + zIsDisplayProgDetailDownIcon_native);
        return zIsDisplayProgDetailDownIcon_native;
    }

    public boolean isDisplayLogoIcon() {
        boolean zIsDisplayLogoIcon_native = TVNativeWrapper.isDisplayLogoIcon_native(1, 31);
        Log.d(TAG, "isDisplayLogoIcon=" + zIsDisplayLogoIcon_native);
        return zIsDisplayLogoIcon_native;
    }

    public boolean isDisplayGingaIcon() {
        boolean zIsDisplayGingaIcon_native = TVNativeWrapper.isDisplayGingaIcon_native(1, 32);
        Log.d(TAG, "isDisplayGingaIcon=" + zIsDisplayGingaIcon_native);
        return zIsDisplayGingaIcon_native;
    }

    public boolean isDisplayADEyeIcon() {
        boolean zIsDisplayADEyeIcon_native = TVNativeWrapper.isDisplayADEyeIcon_native(1, 33);
        Log.d(TAG, "isDisplayADEyeIcon=" + zIsDisplayADEyeIcon_native);
        return zIsDisplayADEyeIcon_native;
    }

    public boolean isDisplayADEarIcon() {
        boolean zIsDisplayADEarIcon_native = TVNativeWrapper.isDisplayADEarIcon_native(1, 34);
        Log.d(TAG, "isDisplayADEarIcon=" + zIsDisplayADEarIcon_native);
        return zIsDisplayADEarIcon_native;
    }

    public boolean isDisplayADAtmos() {
        boolean zIsDisplayADAtmos_native = TVNativeWrapper.isDisplayADAtmos_native(1, 36);
        Log.d(TAG, "isDisplayADAtmos=" + zIsDisplayADAtmos_native);
        return zIsDisplayADAtmos_native;
    }

    public boolean isDisplayBanner() {
        boolean zIsDisplayBanner_native = TVNativeWrapper.isDisplayBanner_native(1, 35);
        Log.d(TAG, "isDisplayBanner=" + zIsDisplayBanner_native);
        return zIsDisplayBanner_native;
    }
}
