package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvConfig extends MtkTvConfigBase {
    public static MtkTvConfig mtkTvConfig;

    public static MtkTvConfig getInstance() {
        MtkTvConfig mtkTvConfig2 = mtkTvConfig;
        if (mtkTvConfig2 != null) {
            return mtkTvConfig2;
        }
        mtkTvConfig = new MtkTvConfig();
        return mtkTvConfig;
    }

    public int setAndroidWorldInfoToLinux(int i, int i2) {
        int androidWorldInfoToLinux_native;
        Log.d(MtkTvConfigBase.TAG, "Enter setAndroidWorldInfoToLinux\n");
        synchronized (MtkTvConfig.class) {
            androidWorldInfoToLinux_native = TVNativeWrapperCustom.setAndroidWorldInfoToLinux_native(i, i2);
        }
        a.a("Leave setAndroidWorldInfoToLinux, return value = ", androidWorldInfoToLinux_native, "\n", MtkTvConfigBase.TAG);
        return androidWorldInfoToLinux_native;
    }
}
