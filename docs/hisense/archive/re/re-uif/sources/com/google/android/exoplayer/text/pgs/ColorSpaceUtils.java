package com.google.android.exoplayer.text.pgs;

/* JADX INFO: loaded from: classes.dex */
final class ColorSpaceUtils {
    public static int[] YCbCr2RGB(int i, int i2, int i3, boolean z) {
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
        return iArr;
    }

    public static int[] RGB2YCbCr(int i, int i2, int i3, boolean z) {
        double d;
        double d2;
        double d3;
        double d4;
        int[] iArr = new int[3];
        if (z) {
            double d5 = i;
            double d6 = i2;
            double d7 = i3;
            d = (((0.299d * d5) * 219.0d) / 255.0d) + (((0.587d * d6) * 219.0d) / 255.0d) + (((0.114d * d7) * 219.0d) / 255.0d);
            d2 = ((((((double) (-i)) * 0.168736d) * 224.0d) / 255.0d) - (((0.331264d * d6) * 224.0d) / 255.0d)) + (((d7 * 0.5d) * 224.0d) / 255.0d);
            d3 = ((((d5 * 0.5d) * 224.0d) / 255.0d) - (((d6 * 0.418688d) * 224.0d) / 255.0d)) - (((d7 * 0.081312d) * 224.0d) / 255.0d);
            d4 = 0.5d;
        } else {
            double d8 = i;
            double d9 = ((double) i2) * 0.7152d;
            double d10 = i3;
            double d11 = 0.0722d * d10;
            d = (((d8 * 0.2126d) * 219.0d) / 255.0d) + ((d9 * 219.0d) / 255.0d) + ((219.0d * d11) / 255.0d);
            d2 = (((((((double) (-i)) * 0.2126d) / 1.8556d) * 224.0d) / 255.0d) - (((d9 / 1.8556d) * 224.0d) / 255.0d)) + (((d10 * 0.5d) * 224.0d) / 255.0d);
            d3 = ((((d8 * 0.5d) * 224.0d) / 255.0d) - (((d9 / 1.5748d) * 224.0d) / 255.0d)) - (((d11 / 1.5748d) * 224.0d) / 255.0d);
            d4 = 0.5d;
        }
        iArr[0] = ((int) (d + d4)) + 16;
        iArr[1] = ((int) (d2 + d4)) + 128;
        iArr[2] = ((int) (d3 + d4)) + 128;
        for (int i4 = 0; i4 < 3; i4++) {
            if (iArr[i4] < 16) {
                iArr[i4] = 16;
            } else if (i4 == 0) {
                if (iArr[i4] > 235) {
                    iArr[i4] = 235;
                }
            } else if (iArr[i4] > 240) {
                iArr[i4] = 240;
            }
        }
        return iArr;
    }
}
