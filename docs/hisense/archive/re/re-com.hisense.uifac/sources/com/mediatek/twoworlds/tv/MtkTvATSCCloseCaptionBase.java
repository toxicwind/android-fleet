package com.mediatek.twoworlds.tv;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvATSCCloseCaptionBase {
    public static final String TAG = "MtkTvATSCCloseCaption";

    public enum atscCCDemeType {
        DEMO_TYPE_IS_SHOWING,
        DEMO_TYPE_FONT_SIZE,
        DEMO_TYPE_FONT_STYLE,
        DEMO_TYPE_FONT_COLOR,
        DEMO_TYPE_FONT_OPACITY,
        DEMO_TYPE_BG_COLOR,
        DEMO_TYPE_BG_OPACITY,
        DEMO_TYPE_WC_COLOR,
        DEMO_TYPE_WC_OPACITY,
        DEMO_TYPE_TOTAL_NUM
    }

    public MtkTvATSCCloseCaptionBase() {
        Log.d(TAG, "MtkTvAnalogCloseCaptionBase object created");
    }

    public int atscCCEnable(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("CC is ");
        sb.append(z ? "enable" : "disable");
        Log.d(TAG, sb.toString());
        return TVNativeWrapper.atscCCEnable_native(z);
    }

    public int atscCCNextStream() {
        int iAtscCCNextStream_native = TVNativeWrapper.atscCCNextStream_native();
        Log.d(TAG, "Next cc stream is CS" + iAtscCCNextStream_native);
        return iAtscCCNextStream_native;
    }

    public int atscCCGetCCIndex() {
        int iAtscCCGetCCIndex_native = TVNativeWrapper.atscCCGetCCIndex_native();
        Log.d(TAG, "CC index is " + iAtscCCGetCCIndex_native);
        return iAtscCCGetCCIndex_native;
    }

    public int atscCCSetCcVisible(boolean z) {
        Log.d(TAG, "atscCCSetCcVisible=" + z);
        return TVNativeWrapper.atscCCSetCcVisible_native(z);
    }

    public int atscCCDemoSet(int i, int i2) {
        Log.d(TAG, "atscCCDemoSet setType=" + i + ", setValue=" + i2);
        return TVNativeWrapper.atscCCDemoSet_native(i, i2);
    }
}
