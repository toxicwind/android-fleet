package com.mediatek.twoworlds.tv;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvHighLevel extends MtkTvHighLevelBase {
    public static MtkTvHighLevel mtkTvHighLevel;

    public int launch3rdParty(int i, String str) {
        a.b("launch_3rd_party id=", i, MtkTvHighLevelBase.TAG);
        if (i <= 0 || i >= 4) {
            return -1;
        }
        TVNativeWrapper.setConfigString_native("MISC_PREFIX__Content_ID", str);
        return launch3rdParty(i);
    }
}
