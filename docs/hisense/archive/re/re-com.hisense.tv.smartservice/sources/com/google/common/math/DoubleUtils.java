package com.google.common.math;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public final class DoubleUtils {
    public static final int EXPONENT_BIAS = 1023;
    public static final long EXPONENT_MASK = 9218868437227405312L;
    public static final long IMPLICIT_BIT = 4503599627370496L;

    @VisibleForTesting
    public static final long ONE_BITS = 4607182418800017408L;
    public static final int SIGNIFICAND_BITS = 52;
    public static final long SIGNIFICAND_MASK = 4503599627370495L;
    public static final long SIGN_MASK = Long.MIN_VALUE;

    public static double bigToDouble(BigInteger bigInteger) {
        BigInteger bigIntegerAbs = bigInteger.abs();
        boolean z = true;
        int iBitLength = bigIntegerAbs.bitLength() - 1;
        if (iBitLength < 63) {
            return bigInteger.longValue();
        }
        if (iBitLength > 1023) {
            return ((double) bigInteger.signum()) * Double.POSITIVE_INFINITY;
        }
        int i = (iBitLength - 52) - 1;
        long jLongValue = bigIntegerAbs.shiftRight(i).longValue();
        long j = (jLongValue >> 1) & SIGNIFICAND_MASK;
        if ((jLongValue & 1) == 0 || ((j & 1) == 0 && bigIntegerAbs.getLowestSetBit() >= i)) {
            z = false;
        }
        if (z) {
            j++;
        }
        return Double.longBitsToDouble(((((long) (iBitLength + 1023)) << 52) + j) | (((long) bigInteger.signum()) & Long.MIN_VALUE));
    }

    public static double ensureNonNegative(double d2) {
        Preconditions.checkArgument(!Double.isNaN(d2));
        return Math.max(d2, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    }

    public static long getSignificand(double d2) {
        Preconditions.checkArgument(isFinite(d2), "not a normal value");
        int exponent = Math.getExponent(d2);
        long jDoubleToRawLongBits = Double.doubleToRawLongBits(d2) & SIGNIFICAND_MASK;
        return exponent == -1023 ? jDoubleToRawLongBits << 1 : jDoubleToRawLongBits | IMPLICIT_BIT;
    }

    public static boolean isFinite(double d2) {
        return Math.getExponent(d2) <= 1023;
    }

    public static boolean isNormal(double d2) {
        return Math.getExponent(d2) >= -1022;
    }

    public static double nextDown(double d2) {
        return -Math.nextUp(-d2);
    }

    public static double scaleNormalize(double d2) {
        return Double.longBitsToDouble((Double.doubleToRawLongBits(d2) & SIGNIFICAND_MASK) | ONE_BITS);
    }
}
