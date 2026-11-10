package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
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

    public int atscCCDemoSet(int i, int i2) {
        Log.d(TAG, "atscCCDemoSet setType=" + i + ", setValue=" + i2);
        return TVNativeWrapper.atscCCDemoSet_native(i, i2);
    }

    public int atscCCEnable(boolean z) {
        StringBuilder sbA = a.a("CC is ");
        sbA.append(z ? "enable" : "disable");
        Log.d(TAG, sbA.toString());
        return TVNativeWrapper.atscCCEnable_native(z);
    }

    public int atscCCGetCCIndex() {
        int iAtscCCGetCCIndex_native = TVNativeWrapper.atscCCGetCCIndex_native();
        a.b("CC index is ", iAtscCCGetCCIndex_native, TAG);
        return iAtscCCGetCCIndex_native;
    }

    public int atscCCNextStream() {
        int iAtscCCNextStream_native = TVNativeWrapper.atscCCNextStream_native();
        a.b("Next cc stream is CS", iAtscCCNextStream_native, TAG);
        return iAtscCCNextStream_native;
    }

    public int atscCCSetCcVisible(boolean z) {
        Log.d(TAG, "atscCCSetCcVisible=" + z);
        return TVNativeWrapper.atscCCSetCcVisible_native(z);
    }
}
