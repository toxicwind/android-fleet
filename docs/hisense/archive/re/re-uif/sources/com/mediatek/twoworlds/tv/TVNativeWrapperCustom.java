package com.mediatek.twoworlds.tv;

import android.util.Log;
import mediatek.sysprop.VendorProperties;

/* JADX INFO: loaded from: classes.dex */
public class TVNativeWrapperCustom {
    private static final String TAG = "TVNativeWrapperCustom(Emu)";

    protected static boolean is_emulator() {
        if (VendorProperties.mtk_inside().orElse(Boolean.FALSE).booleanValue()) {
            Log.d(TAG, "handle MTK target case here");
            return false;
        }
        Log.d(TAG, "handle Emulator case here called");
        return true;
    }

    protected static int testCustom_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "testCustom_native called");
            return 0;
        }
        return TVNativeCustom.testCustom_native(i, i2);
    }

    protected static int setAndroidWorldInfoToLinux_native(int i, int i2) {
        if (is_emulator()) {
            Log.d(TAG, "setAndroidWorldInfoToLinux_native called");
            return 0;
        }
        return TVNativeCustom.setAndroidWorldInfoToLinux_native(i, i2);
    }

    protected static int genericSetAPI_native(int i, int i2, int i3) {
        if (is_emulator()) {
            Log.d(TAG, "genericSetAPI_native called");
            return 0;
        }
        return TVNativeCustom.genericSetAPI_native(i, i2, i3);
    }
}
