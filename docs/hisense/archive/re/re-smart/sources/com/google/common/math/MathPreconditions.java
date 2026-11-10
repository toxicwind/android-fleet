package com.google.common.math;

import b.a.a.a.a;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.math.BigInteger;
import java.math.RoundingMode;

/* JADX INFO: loaded from: classes.dex */
@CanIgnoreReturnValue
@GwtCompatible
public final class MathPreconditions {
    public static void checkInRangeForRoundingInputs(boolean z, double d2, RoundingMode roundingMode) {
        if (z) {
            return;
        }
        String strValueOf = String.valueOf(roundingMode);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 83);
        sb.append("rounded value is out of range for input ");
        sb.append(d2);
        sb.append(" and rounding mode ");
        sb.append(strValueOf);
        throw new ArithmeticException(sb.toString());
    }

    public static void checkNoOverflow(boolean z, String str, int i, int i2) {
        if (z) {
            return;
        }
        StringBuilder sb = new StringBuilder(a.a((Object) str, 36));
        sb.append("overflow: ");
        sb.append(str);
        sb.append("(");
        sb.append(i);
        sb.append(", ");
        sb.append(i2);
        sb.append(")");
        throw new ArithmeticException(sb.toString());
    }

    public static int checkNonNegative(String str, int i) {
        if (i >= 0) {
            return i;
        }
        StringBuilder sb = new StringBuilder(a.a((Object) str, 27));
        sb.append(str);
        sb.append(" (");
        sb.append(i);
        sb.append(") must be >= 0");
        throw new IllegalArgumentException(sb.toString());
    }

    public static int checkPositive(String str, int i) {
        if (i > 0) {
            return i;
        }
        StringBuilder sb = new StringBuilder(a.a((Object) str, 26));
        sb.append(str);
        sb.append(" (");
        sb.append(i);
        sb.append(") must be > 0");
        throw new IllegalArgumentException(sb.toString());
    }

    public static void checkRoundingUnnecessary(boolean z) {
        if (!z) {
            throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
        }
    }

    public static void checkNoOverflow(boolean z, String str, long j, long j2) {
        if (z) {
            return;
        }
        StringBuilder sb = new StringBuilder(a.a((Object) str, 54));
        sb.append("overflow: ");
        sb.append(str);
        sb.append("(");
        sb.append(j);
        sb.append(", ");
        sb.append(j2);
        sb.append(")");
        throw new ArithmeticException(sb.toString());
    }

    public static long checkNonNegative(String str, long j) {
        if (j >= 0) {
            return j;
        }
        StringBuilder sb = new StringBuilder(a.a((Object) str, 36));
        sb.append(str);
        sb.append(" (");
        sb.append(j);
        sb.append(") must be >= 0");
        throw new IllegalArgumentException(sb.toString());
    }

    public static long checkPositive(String str, long j) {
        if (j > 0) {
            return j;
        }
        StringBuilder sb = new StringBuilder(a.a((Object) str, 35));
        sb.append(str);
        sb.append(" (");
        sb.append(j);
        sb.append(") must be > 0");
        throw new IllegalArgumentException(sb.toString());
    }

    public static BigInteger checkNonNegative(String str, BigInteger bigInteger) {
        if (bigInteger.signum() >= 0) {
            return bigInteger;
        }
        String strValueOf = String.valueOf(bigInteger);
        throw new IllegalArgumentException(a.b(strValueOf.length() + a.a((Object) str, 16), str, " (", strValueOf, ") must be >= 0"));
    }

    public static BigInteger checkPositive(String str, BigInteger bigInteger) {
        if (bigInteger.signum() > 0) {
            return bigInteger;
        }
        String strValueOf = String.valueOf(bigInteger);
        throw new IllegalArgumentException(a.b(strValueOf.length() + a.a((Object) str, 15), str, " (", strValueOf, ") must be > 0"));
    }

    public static double checkNonNegative(String str, double d2) {
        if (d2 >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return d2;
        }
        StringBuilder sb = new StringBuilder(a.a((Object) str, 40));
        sb.append(str);
        sb.append(" (");
        sb.append(d2);
        sb.append(") must be >= 0");
        throw new IllegalArgumentException(sb.toString());
    }
}
