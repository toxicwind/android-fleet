package com.mediatek.twoworlds.tv;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class TVNativeCustom {
    public static final String TAG = "TVNative";

    static {
        Log.i("TVNative", "Load libcom_mediatek_twoworlds_tv_jni.so start !");
        System.loadLibrary("com_mediatek_twoworlds_tv_jni");
        Log.i("TVNative", "Load libcom_mediatek_twoworlds_tv_jni.so OK !");
    }

    public static native int genericSetAPI_native(int i, int i2, int i3);

    public static native int setAndroidWorldInfoToLinux_native(int i, int i2);

    public static native int testCustom_native(int i, int i2);
}
