package com.mediatek.twoworlds.tv;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvConfig extends MtkTvConfigBase {
    private static MtkTvConfig mtkTvConfig;

    private MtkTvConfig() {
    }

    public static MtkTvConfig getInstance() {
        MtkTvConfig mtkTvConfig2 = mtkTvConfig;
        if (mtkTvConfig2 != null) {
            return mtkTvConfig2;
        }
        MtkTvConfig mtkTvConfig3 = new MtkTvConfig();
        mtkTvConfig = mtkTvConfig3;
        return mtkTvConfig3;
    }

    public int setAndroidWorldInfoToLinux(int i, int i2) {
        int androidWorldInfoToLinux_native;
        Log.d(MtkTvConfigBase.TAG, "Enter setAndroidWorldInfoToLinux\n");
        synchronized (MtkTvConfig.class) {
            androidWorldInfoToLinux_native = TVNativeWrapperCustom.setAndroidWorldInfoToLinux_native(i, i2);
        }
        Log.d(MtkTvConfigBase.TAG, "Leave setAndroidWorldInfoToLinux, return value = " + androidWorldInfoToLinux_native + "\n");
        return androidWorldInfoToLinux_native;
    }
}
