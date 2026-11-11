package org.mozilla.javascript.v8dtoa;

/* JADX INFO: loaded from: classes8.dex */
public final class DoubleConversion {
    private static final int kDenormalExponent = -1074;
    private static final int kExponentBias = 1075;
    private static final long kExponentMask = 9218868437227405312L;
    private static final long kHiddenBit = 4503599627370496L;
    private static final int kPhysicalSignificandSize = 52;
    private static final long kSignMask = Long.MIN_VALUE;
    private static final long kSignificandMask = 4503599627370495L;
    private static final int kSignificandSize = 53;

    private static boolean isDenormal(long j) {
        return (j & kExponentMask) == 0;
    }

    private static int sign(long j) {
        return (j & Long.MIN_VALUE) == 0 ? 1 : -1;
    }

    private DoubleConversion() {
    }

    private static int exponent(long j) {
        return isDenormal(j) ? kDenormalExponent : ((int) ((j & kExponentMask) >> 52)) - 1075;
    }

    private static long significand(long j) {
        long j2 = kSignificandMask & j;
        return !isDenormal(j) ? j2 + kHiddenBit : j2;
    }

    public static int doubleToInt32(double d) {
        int i = (int) d;
        if (i == d) {
            return i;
        }
        long jDoubleToLongBits = Double.doubleToLongBits(d);
        int iExponent = exponent(jDoubleToLongBits);
        if (iExponent <= -53 || iExponent > 31) {
            return 0;
        }
        long jSignificand = significand(jDoubleToLongBits);
        return sign(jDoubleToLongBits) * ((int) (iExponent < 0 ? jSignificand >> (-iExponent) : jSignificand << iExponent));
    }
}
