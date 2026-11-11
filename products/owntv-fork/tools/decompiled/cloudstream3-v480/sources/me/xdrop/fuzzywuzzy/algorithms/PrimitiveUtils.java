package me.xdrop.fuzzywuzzy.algorithms;

/* JADX INFO: loaded from: classes5.dex */
final class PrimitiveUtils {
    PrimitiveUtils() {
    }

    static double max(double... dArr) {
        if (dArr.length == 0) {
            return 0.0d;
        }
        double d = dArr[0];
        for (double d2 : dArr) {
            if (d2 > d) {
                d = d2;
            }
        }
        return d;
    }

    static int max(int... iArr) {
        if (iArr.length == 0) {
            return 0;
        }
        int i = iArr[0];
        for (int i2 : iArr) {
            if (i2 > i) {
                i = i2;
            }
        }
        return i;
    }
}
