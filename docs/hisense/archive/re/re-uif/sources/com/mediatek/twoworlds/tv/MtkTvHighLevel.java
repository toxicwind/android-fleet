package com.mediatek.twoworlds.tv;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvHighLevel extends MtkTvHighLevelBase {
    private static MtkTvHighLevel mtkTvHighLevel;

    public int launch3rdParty(int i, String str) {
        Log.d(MtkTvHighLevelBase.TAG, "launch_3rd_party id=" + i);
        if (i <= 0 || i >= 4) {
            return -1;
        }
        TVNativeWrapper.setConfigString_native("MISC_PREFIX__Content_ID", str);
        return launch3rdParty(i);
    }
}
