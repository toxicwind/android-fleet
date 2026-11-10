package com.google.android.exoplayer;

import com.google.android.exoplayer.util.Util;
import com.mediatek.twoworlds.tv.MtkTvScanCeBase;

/* JADX INFO: loaded from: classes.dex */
public final class C {
    public static final int CHANNEL_OUT_7POINT1_SURROUND;
    public static final int ENCODING_AC4;

    static {
        int i;
        int i2 = Util.SDK_INT;
        if (i2 > 25) {
            i = i2 > 27 ? 17 : 15;
        } else {
            i = 14;
        }
        ENCODING_AC4 = i;
        CHANNEL_OUT_7POINT1_SURROUND = Util.SDK_INT < 23 ? MtkTvScanCeBase.OPERATOR_KUNSHAN : 6396;
    }
}
