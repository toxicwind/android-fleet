package com.mediatek.twoworlds.tv;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvCDTChLogoBase {
    public static final String TAG = "MtkTvCDTChLogoBase";

    public String getChLogoPNGFilePath(int i, int i2, int i3) {
        Log.d(TAG, "#### CH LOGO onid=" + i + ",svlid=" + i2 + ",freq=" + i3);
        String strIsdbCDTGetChannelLogo_native = TVNativeWrapper.isdbCDTGetChannelLogo_native(i, i2, i3);
        StringBuilder sb = new StringBuilder();
        sb.append("#### CH LOGO getChLogoPNGFilePath=");
        sb.append(strIsdbCDTGetChannelLogo_native);
        Log.d(TAG, sb.toString());
        return strIsdbCDTGetChannelLogo_native;
    }
}
