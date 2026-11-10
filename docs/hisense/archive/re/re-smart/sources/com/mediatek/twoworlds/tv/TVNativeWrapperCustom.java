package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.b.a.c.a;

/* JADX INFO: loaded from: classes2.dex */
public class TVNativeWrapperCustom {
    public static final String TAG = "TVNativeWrapperCustom(Emu)";

    public static int genericSetAPI_native(int i, int i2, int i3) {
        if (!is_emulator()) {
            return TVNativeCustom.genericSetAPI_native(i, i2, i3);
        }
        Log.d(TAG, "genericSetAPI_native called");
        return 0;
    }

    public static boolean is_emulator() {
        if (a.b().orElse(false).booleanValue()) {
            Log.d(TAG, "handle MTK target case here");
            return false;
        }
        Log.d(TAG, "handle Emulator case here called");
        return true;
    }

    public static int setAndroidWorldInfoToLinux_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNativeCustom.setAndroidWorldInfoToLinux_native(i, i2);
        }
        Log.d(TAG, "setAndroidWorldInfoToLinux_native called");
        return 0;
    }

    public static int testCustom_native(int i, int i2) {
        if (!is_emulator()) {
            return TVNativeCustom.testCustom_native(i, i2);
        }
        Log.d(TAG, "testCustom_native called");
        return 0;
    }
}
