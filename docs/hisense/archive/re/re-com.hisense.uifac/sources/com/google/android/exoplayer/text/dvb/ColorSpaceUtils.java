package com.google.android.exoplayer.text.dvb;

/* JADX INFO: loaded from: classes.dex */
final class ColorSpaceUtils {
    public static int YCbCr2RGB(int i, int i2, int i3, boolean z) {
        double d;
        double d2;
        double d3;
        double d4;
        int[] iArr = new int[3];
        int i4 = i2 - 128;
        int i5 = i3 - 128;
        double d5 = ((double) (i - 16)) * 1.164383562d;
        if (z) {
            double d6 = i5;
            d = (1.596026317d * d6) + d5;
            d2 = i4;
            d3 = (d5 - (d6 * 0.8129674985d)) - (0.3917615979d * d2);
            d4 = 2.017232218d;
        } else {
            double d7 = i5;
            d = (1.792741071d * d7) + d5;
            d2 = i4;
            d3 = (d5 - (d7 * 0.5329093286d)) - (0.2132486143d * d2);
            d4 = 2.112401786d;
        }
        iArr[0] = (int) (d + 0.5d);
        iArr[1] = (int) (d3 + 0.5d);
        iArr[2] = (int) (d5 + (d2 * d4) + 0.5d);
        for (int i6 = 0; i6 < 3; i6++) {
            if (iArr[i6] < 0) {
                iArr[i6] = 0;
            } else if (iArr[i6] > 255) {
                iArr[i6] = 255;
            }
        }
        return ((iArr[0] << 16) - 16777216) + (iArr[1] << 8) + iArr[2];
    }
}
