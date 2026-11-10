package com.mediatek.twoworlds.tv;

import android.util.Log;
import com.mediatek.twoworlds.tv.model.MtkTvEASParaBase;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvEASBase {
    private static final String TAG = "MtkTvEAS";

    public MtkTvEASBase() {
        Log.d(TAG, "MtkTvEASBase object created");
    }

    public int getEASCurrentStatus(MtkTvEASParaBase mtkTvEASParaBase) {
        Log.d(TAG, "Enter getEASCurrentStatus Here.\n");
        int eASCurrentStatus_native = TVNativeWrapper.getEASCurrentStatus_native(mtkTvEASParaBase);
        if (eASCurrentStatus_native != 0) {
            Log.e(TAG, "TVNativeWrapper.getEASCurrentStatus_native failed! return " + eASCurrentStatus_native + ".\n");
        }
        return eASCurrentStatus_native;
    }

    public int EASSetAndroidLaunchStatus(boolean z) {
        Log.d(TAG, "EASSetAndroidLaunchStatus=" + z);
        return TVNativeWrapper.setEASAndroidLaunchStatus_native(z);
    }

    public boolean EASGetAndroidLaunchStatus() {
        Log.d(TAG, "EASGetAndroidLaunchStatus");
        boolean eASAndroidLaunchStatus_native = TVNativeWrapper.getEASAndroidLaunchStatus_native();
        Log.d(TAG, "EASGetAndroidLaunchStatus " + eASAndroidLaunchStatus_native);
        Log.d(TAG, "Leave EASGetAndroidLaunchStatus.");
        return eASAndroidLaunchStatus_native;
    }
}
