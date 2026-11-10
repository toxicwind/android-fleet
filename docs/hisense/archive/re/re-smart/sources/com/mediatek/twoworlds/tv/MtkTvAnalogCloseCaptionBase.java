package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvAnalogCloseCaptionBase {
    public static final String TAG = "MtkTvAnalogCloseCaption";

    public MtkTvAnalogCloseCaptionBase() {
        Log.d(TAG, "MtkTvAnalogCloseCaptionBase object created");
    }

    public int analogCCEnable(boolean z) {
        StringBuilder sbA = a.a("CC is ");
        sbA.append(z ? "enable" : "disable");
        Log.d(TAG, sbA.toString());
        return TVNativeWrapper.analogCCEnable_native(z);
    }

    public int analogCCGetCcIndex() {
        int iAnalogCCGetCcIndex_native = TVNativeWrapper.analogCCGetCcIndex_native();
        a.b("getChannelNumber=", iAnalogCCGetCcIndex_native, TAG);
        return iAnalogCCGetCcIndex_native;
    }

    public int analogCCNextStream() {
        int iAnalogCCNextStream_native = TVNativeWrapper.analogCCNextStream_native();
        a.b("Next cc stream is CC", iAnalogCCNextStream_native, TAG);
        return iAnalogCCNextStream_native;
    }

    public int analogCCSetCcVisible(boolean z) {
        Log.d(TAG, "analogCCSetCcVisible=" + z);
        return TVNativeWrapper.analogCCSetCcVisible_native(z);
    }
}
