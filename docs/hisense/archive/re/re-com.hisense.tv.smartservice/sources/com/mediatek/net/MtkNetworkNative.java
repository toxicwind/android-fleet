package com.mediatek.net;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class MtkNetworkNative {
    public static final String TAG = "MtkNetworkNative";
    public String mInterface;

    static {
        Log.d(TAG, "MtkNetworkNative begin load  libcom_mediatek_net_jni.so !");
        try {
            System.loadLibrary("com_mediatek_net_jni");
        } catch (UnsatisfiedLinkError unused) {
            Log.i(TAG, "WARNING: Could not load library!");
        }
    }

    public MtkNetworkNative(String str) {
        Log.d(TAG, "structure");
        this.mInterface = str;
    }

    public static native boolean isWoPacketEnableNative();

    public static native boolean setEnableWifiCSANative(boolean z);

    public static native boolean setEnableWifiPsAwakeNative(boolean z);

    public static native boolean setWifiPsAwakeIntervalNative(int i);

    public static native boolean setWoPacketNative(boolean z);

    public static native boolean wifiNativeSetWowl(boolean z);
}
