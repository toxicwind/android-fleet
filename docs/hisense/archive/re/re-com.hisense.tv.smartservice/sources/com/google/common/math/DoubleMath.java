package com.google.common.math;

import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Booleans;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true)
public final class DoubleMath {

    @VisibleForTesting
    public static final int MAX_FACTORIAL = 170;
    public static final double MAX_INT_AS_DOUBLE = 2.147483647E9d;
    public static final double MAX_LONG_AS_DOUBLE_PLUS_ONE = 9.223372036854776E18d;
    public static final double MIN_INT_AS_DOUBLE = -2.147483648E9d;
    public static final double MIN_LONG_AS_DOUBLE = -9.223372036854776E18d;
    public static final double LN_2 = Math.log(2.0d);

    @VisibleForTesting
    public static final double[] everySixteenthFactorial = {1.0d, 2.0922789888E13d, 2.631308369336935E35d, 1.2413915592536073E61d, 1.2688693218588417E89d, 7.156945704626381E118d, 9.916779348709496E149d, 1.974506857221074E182d, 3.856204823625804E215d, 5.5502938327393044E249d, 4.7147236359920616E284d};

    /* JADX INFO: renamed from: com.google.common.math.DoubleMath$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$java$math$RoundingMode = new int[RoundingMode.values().length];

        static {
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.UNNECESSARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.FLOOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.CEILING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.DOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.UP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_EVEN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_UP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_DOWN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    @CanIgnoreReturnValue
    @GwtIncompatible
    public static double checkFinite(double d2) {
        Preconditions.checkArgument(DoubleUtils.isFinite(d2));
        return d2;
    }

    public static double factorial(int i) {
        MathPreconditions.checkNonNegative(GoogleApiAvailabilityLight.TRACKING_SOURCE_NOTIFICATION, i);
        if (i > 170) {
            return Double.POSITIVE_INFINITY;
        }
        double d2 = 1.0d;
        int i2 = i & (-16);
        while (true) {
            i2++;
            if (i2 > i) {
                return d2 * everySixteenthFactorial[i >> 4];
            }
            d2 *= (double) i2;
        }
    }

    public static int fuzzyCompare(double d2, double d3, double d4) {
        if (fuzzyEquals(d2, d3, d4)) {
            return 0;
        }
        if (d2 < d3) {
            return -1;
        }
        if (d2 > d3) {
            return 1;
        }
        return Booleans.compare(Double.isNaN(d2), Double.isNaN(d3));
    }

    public static boolean fuzzyEquals(double d2, double d3, double d4) {
        MathPreconditions.checkNonNegative("tolerance", d4);
        return Math.copySign(d2 - d3, 1.0d) <= d4 || d2 == d3 || (Double.isNaN(d2) && Double.isNaN(d3));
    }

    @GwtIncompatible
    public static boolean isMathematicalInteger(double d2) {
        return DoubleUtils.isFinite(d2) && (d2 == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || 52 - Long.numberOfTrailingZeros(DoubleUtils.getSignificand(d2)) <= Math.getExponent(d2));
    }

    @GwtIncompatible
    public static boolean isPowerOfTwo(double d2) {
        if (d2 <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || !DoubleUtils.isFinite(d2)) {
            return false;
        }
        long significand = DoubleUtils.getSignificand(d2);
        return (significand & (significand - 1)) == 0;
    }

    public static double log2(double d2) {
        return Math.log(d2) / LN_2;
    }

    @GwtIncompatible
    @Deprecated
    public static double mean(double... dArr) {
        Preconditions.checkArgument(dArr.length > 0, "Cannot take mean of 0 values");
        double d2 = dArr[0];
        checkFinite(d2);
        long j = 1;
        for (int i = 1; i < dArr.length; i++) {
            checkFinite(dArr[i]);
            j++;
            d2 += (dArr[i] - d2) / j;
        }
        return d2;
    }

    @GwtIncompatible
    public static double roundIntermediate(double d2, RoundingMode roundingMode) {
        if (!DoubleUtils.isFinite(d2)) {
            throw new ArithmeticException("input is infinite or NaN");
        }
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isMathematicalInteger(d2));
                return d2;
            case 2:
                return (d2 >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || isMathematicalInteger(d2)) ? d2 : ((long) d2) - 1;
            case 3:
                return (d2 <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || isMathematicalInteger(d2)) ? d2 : ((long) d2) + 1;
            case 4:
                return d2;
            case 5:
                if (isMathematicalInteger(d2)) {
                    return d2;
                }
                return ((long) d2) + ((long) (d2 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? 1 : -1));
            case 6:
                return Math.rint(d2);
            case 7:
                double dRint = Math.rint(d2);
                return Math.abs(d2 - dRint) == 0.5d ? Math.copySign(0.5d, d2) + d2 : dRint;
            case 8:
                double dRint2 = Math.rint(d2);
                return Math.abs(d2 - dRint2) == 0.5d ? d2 : dRint2;
            default:
                throw new AssertionError();
        }
    }

    @GwtIncompatible
    public static BigInteger roundToBigInteger(double d2, RoundingMode roundingMode) {
        double dRoundIntermediate = roundIntermediate(d2, roundingMode);
        if (((-9.223372036854776E18d) - dRoundIntermediate < 1.0d) && (dRoundIntermediate < 9.223372036854776E18d)) {
            return BigInteger.valueOf((long) dRoundIntermediate);
        }
        BigInteger bigIntegerShiftLeft = BigInteger.valueOf(DoubleUtils.getSignificand(dRoundIntermediate)).shiftLeft(Math.getExponent(dRoundIntermediate) - 52);
        return dRoundIntermediate < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? bigIntegerShiftLeft.negate() : bigIntegerShiftLeft;
    }

    @GwtIncompatible
    public static int roundToInt(double d2, RoundingMode roundingMode) {
        double dRoundIntermediate = roundIntermediate(d2, roundingMode);
        MathPreconditions.checkInRangeForRoundingInputs((dRoundIntermediate > -2.147483649E9d) & (dRoundIntermediate < 2.147483648E9d), d2, roundingMode);
        return (int) dRoundIntermediate;
    }

    @GwtIncompatible
    public static long roundToLong(double d2, RoundingMode roundingMode) {
        double dRoundIntermediate = roundIntermediate(d2, roundingMode);
        MathPreconditions.checkInRangeForRoundingInputs(((-9.223372036854776E18d) - dRoundIntermediate < 1.0d) & (dRoundIntermediate < 9.223372036854776E18d), d2, roundingMode);
        return (long) dRoundIntermediate;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:29:0x006a  */
    /* JADX WARN: Code duplicated, block: B:31:? A[RETURN, SYNTHETIC] */
    @GwtIncompatible
    public static int log2(double d2, RoundingMode roundingMode) {
        boolean zIsPowerOfTwo;
        boolean z = false;
        Preconditions.checkArgument(d2 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && DoubleUtils.isFinite(d2), "x must be positive and finite");
        int exponent = Math.getExponent(d2);
        if (!DoubleUtils.isNormal(d2)) {
            return log2(d2 * 4.503599627370496E15d, roundingMode) - 52;
        }
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(d2));
                if (z) {
                    return exponent + 1;
                }
                return exponent;
            case 2:
                if (z) {
                    return exponent + 1;
                }
                return exponent;
            case 3:
                z = !isPowerOfTwo(d2);
                if (z) {
                    return exponent + 1;
                }
                return exponent;
            case 4:
                z = exponent < 0;
                zIsPowerOfTwo = isPowerOfTwo(d2);
                z &= !zIsPowerOfTwo;
                if (z) {
                    return exponent + 1;
                }
                return exponent;
            case 5:
                z = exponent >= 0;
                zIsPowerOfTwo = isPowerOfTwo(d2);
                z &= !zIsPowerOfTwo;
                if (z) {
                    return exponent + 1;
                }
                return exponent;
            case 6:
            case 7:
            case 8:
                double dScaleNormalize = DoubleUtils.scaleNormalize(d2);
                if (dScaleNormalize * dScaleNormalize > 2.0d) {
                    z = true;
                }
                if (z) {
                    return exponent + 1;
                }
                return exponent;
            default:
                throw new AssertionError();
        }
    }

    @Deprecated
    public static double mean(int... iArr) {
        Preconditions.checkArgument(iArr.length > 0, "Cannot take mean of 0 values");
        long j = 0;
        for (int i : iArr) {
            j += (long) i;
        }
        return j / ((double) iArr.length);
    }

    @Deprecated
    public static double mean(long... jArr) {
        Preconditions.checkArgument(jArr.length > 0, "Cannot take mean of 0 values");
        double d2 = jArr[0];
        long j = 1;
        for (int i = 1; i < jArr.length; i++) {
            j++;
            d2 += (jArr[i] - d2) / j;
        }
        return d2;
    }

    @GwtIncompatible
    @Deprecated
    public static double mean(Iterable<? extends Number> iterable) {
        return mean(iterable.iterator());
    }

    @GwtIncompatible
    @Deprecated
    public static double mean(Iterator<? extends Number> it) {
        Preconditions.checkArgument(it.hasNext(), "Cannot take mean of 0 values");
        double dDoubleValue = it.next().doubleValue();
        checkFinite(dDoubleValue);
        long j = 1;
        while (it.hasNext()) {
            double dDoubleValue2 = it.next().doubleValue();
            checkFinite(dDoubleValue2);
            j++;
            dDoubleValue += (dDoubleValue2 - dDoubleValue) / j;
        }
        return dDoubleValue;
    }
}
