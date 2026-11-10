package com.google.android.exoplayer.text.pgs;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class ToolBox {
    static {
        new DecimalFormat("##.###", DecimalFormatSymbols.getInstance(Locale.US));
    }

    public static String leftZeroPad(int i, int i2) {
        return String.format("%0" + i2 + "d", Integer.valueOf(i));
    }

    public static String toHexLeftZeroPadded(long j, int i) {
        return String.format("0x%0" + i + "x", Long.valueOf(j));
    }
}
