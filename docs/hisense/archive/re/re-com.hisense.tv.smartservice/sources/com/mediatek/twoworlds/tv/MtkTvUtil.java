package com.mediatek.twoworlds.tv;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvUtil extends MtkTvUtilBase {
    public static MtkTvUtil mtkTvUtil;

    public static int IRRemoteControl(int i) {
        return TVNativeWrapper.HighLevel_native(117, i, 0, 0, 0, 0, 0);
    }

    public static int KeypadControl(int i) {
        return TVNativeWrapper.HighLevel_native(118, i, 0, 0, 0, 0, 0);
    }

    public static int QHB_ClearQuietHotBoot() {
        return TVNativeWrapper.HighLevel_native(13012, 0, 0, 0, 0, 0, 0);
    }

    public static int QHB_IsCancelledQuietHotBoot() {
        return TVNativeWrapper.HighLevel_native(13011, 0, 0, 0, 0, 0, 0);
    }

    public static int QHB_IsQuietHotBoot() {
        return TVNativeWrapper.HighLevel_native(13010, 0, 0, 0, 0, 0, 0);
    }

    public static int QHB_StopCancellationCheckBeforeBootComplete() {
        return TVNativeWrapper.HighLevel_native(13013, 0, 0, 0, 0, 0, 0);
    }

    public static MtkTvUtil getInstance() {
        MtkTvUtil mtkTvUtil2 = mtkTvUtil;
        if (mtkTvUtil2 != null) {
            return mtkTvUtil2;
        }
        mtkTvUtil = new MtkTvUtil();
        return mtkTvUtil;
    }

    public static int turnOnPanelAndSpeaker() {
        return TVNativeWrapper.HighLevel_native(13001, 0, 0, 0, 0, 0, 0);
    }

    public int muteVideo(boolean z) {
        return TVNativeWrapperCustom.genericSetAPI_native(12000, z ? 1 : 0, 0);
    }
}
