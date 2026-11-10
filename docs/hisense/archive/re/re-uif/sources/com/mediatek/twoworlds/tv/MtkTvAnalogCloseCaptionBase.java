package com.mediatek.twoworlds.tv;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvAnalogCloseCaptionBase {
    public static final String TAG = "MtkTvAnalogCloseCaption";

    public MtkTvAnalogCloseCaptionBase() {
        Log.d(TAG, "MtkTvAnalogCloseCaptionBase object created");
    }

    public int analogCCEnable(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("CC is ");
        sb.append(z ? "enable" : "disable");
        Log.d(TAG, sb.toString());
        return TVNativeWrapper.analogCCEnable_native(z);
    }

    public int analogCCNextStream() {
        int iAnalogCCNextStream_native = TVNativeWrapper.analogCCNextStream_native();
        Log.d(TAG, "Next cc stream is CC" + iAnalogCCNextStream_native);
        return iAnalogCCNextStream_native;
    }

    public int analogCCGetCcIndex() {
        int iAnalogCCGetCcIndex_native = TVNativeWrapper.analogCCGetCcIndex_native();
        Log.d(TAG, "getChannelNumber=" + iAnalogCCGetCcIndex_native);
        return iAnalogCCGetCcIndex_native;
    }

    public int analogCCSetCcVisible(boolean z) {
        Log.d(TAG, "analogCCSetCcVisible=" + z);
        return TVNativeWrapper.analogCCSetCcVisible_native(z);
    }
}
