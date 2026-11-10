package com.mediatek.twoworlds.tv;

import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvUtil extends MtkTvUtilBase {
    private static MtkTvUtil mtkTvUtil;

    private MtkTvUtil() {
    }

    public static MtkTvUtil getInstance() {
        MtkTvUtil mtkTvUtil2 = mtkTvUtil;
        if (mtkTvUtil2 != null) {
            return mtkTvUtil2;
        }
        MtkTvUtil mtkTvUtil3 = new MtkTvUtil();
        mtkTvUtil = mtkTvUtil3;
        return mtkTvUtil3;
    }

    public int muteVideo(boolean z) {
        return TVNativeWrapperCustom.genericSetAPI_native(12000, z ? 1 : 0, 0);
    }

    public static int turnOnPanelAndSpeaker() {
        return TVNativeWrapper.HighLevel_native(13001, 0, 0, 0, 0, 0, 0);
    }

    public static int QHB_IsQuietHotBoot() {
        return TVNativeWrapper.HighLevel_native(13010, 0, 0, 0, 0, 0, 0);
    }

    public static int QHB_IsCancelledQuietHotBoot() {
        return TVNativeWrapper.HighLevel_native(13011, 0, 0, 0, 0, 0, 0);
    }

    public static int IRRemoteControl(int i) {
        return TVNativeWrapper.HighLevel_native(117, i, 0, 0, 0, 0, 0);
    }

    public static int KeypadControl(int i) {
        return TVNativeWrapper.HighLevel_native(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_FRAMERATE_NOT_SUPPORT, i, 0, 0, 0, 0, 0);
    }

    public static int QHB_ClearQuietHotBoot() {
        return TVNativeWrapper.HighLevel_native(13012, 0, 0, 0, 0, 0, 0);
    }

    public static int QHB_StopCancellationCheckBeforeBootComplete() {
        return TVNativeWrapper.HighLevel_native(13013, 0, 0, 0, 0, 0, 0);
    }
}
