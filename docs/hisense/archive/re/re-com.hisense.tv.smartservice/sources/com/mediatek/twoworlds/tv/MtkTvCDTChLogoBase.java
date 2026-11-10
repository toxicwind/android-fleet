package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvCDTChLogoBase {
    public static final String TAG = "MtkTvCDTChLogoBase";

    public String getChLogoPNGFilePath(int i, int i2, int i3) {
        StringBuilder sbB = a.b("#### CH LOGO onid=", i, ",svlid=", i2, ",freq=");
        sbB.append(i3);
        Log.d(TAG, sbB.toString());
        String strIsdbCDTGetChannelLogo_native = TVNativeWrapper.isdbCDTGetChannelLogo_native(i, i2, i3);
        a.c("#### CH LOGO getChLogoPNGFilePath=", strIsdbCDTGetChannelLogo_native, TAG);
        return strIsdbCDTGetChannelLogo_native;
    }
}
