package kotlinx.datetime.internal;

import androidx.collection.SieveCacheKt;
import androidx.media3.exoplayer.upstream.CmcdData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.random.RandomKt;
import kotlin.ranges.LongProgression;
import kotlin.ranges.LongRange;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: math.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\u0018\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002H\u0002\u001a\u0018\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002H\u0000\u001a \u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0002H\u0000\u001a\u0011\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0002H\u0082\b\u001a\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0002H\u0082\b\u001a\u0019\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0001H\u0082\b\u001a(\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0002H\u0000\u001a \u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0002H\u0000\u001a\u0016\u0010\u001a\u001a\u00020\u0002*\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001dH\u0000\u001a\u001d\u0010\u001e\u001a\u0004\u0018\u00010\u0002*\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001dH\u0000¢\u0006\u0002\u0010\u001f\u001a\u0014\u0010 \u001a\u00020!*\u00020\u001b2\u0006\u0010\u000e\u001a\u00020\u0002H\u0000\"\u0014\u0010\u0016\u001a\u00020\u0017X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0018\u0010\"\u001a\u00020\u0001*\u00020\u001b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$¨\u0006%"}, d2 = {"clampToInt", "", "", "safeMultiplyOrZero", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "safeMultiplyOrClamp", "multiplyAndDivide", "Lkotlinx/datetime/internal/DivRemResult;", "c", "low", "x", "high", "indexBit", ES6Iterator.VALUE_PROPERTY, "bit", "multiplyAddAndDivide", "d", "n", "r", CmcdData.OBJECT_TYPE_MANIFEST, "multiplyAndAdd", "POWERS_OF_TEN", "", "getPOWERS_OF_TEN", "()[I", "randomUnsafe", "Lkotlin/ranges/LongProgression;", "random", "Lkotlin/random/Random;", "randomUnsafeOrNull", "(Lkotlin/ranges/LongProgression;Lkotlin/random/Random;)Ljava/lang/Long;", "containsUnsafe", "", "sizeUnsafe", "getSizeUnsafe", "(Lkotlin/ranges/LongProgression;)I", "kotlinx-datetime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class MathKt {
    private static final int[] POWERS_OF_TEN = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};

    public static final int clampToInt(long j) {
        if (j > SieveCacheKt.NodeLinkMask) {
            return Integer.MAX_VALUE;
        }
        if (j < SieveCacheKt.NodeMetaAndPreviousMask) {
            return Integer.MIN_VALUE;
        }
        return (int) j;
    }

    private static final long high(long j) {
        return (j >> 32) & 4294967295L;
    }

    private static final long indexBit(long j, int i) {
        return (j >> i) & 1;
    }

    private static final long low(long j) {
        return j & 4294967295L;
    }

    private static final long safeMultiplyOrZero(long j, long j2) {
        if (j2 == -1) {
            if (j == Long.MIN_VALUE) {
                return 0L;
            }
            return -j;
        }
        if (j2 == 1) {
            return j;
        }
        long j3 = j * j2;
        if (j3 / j2 != j) {
            return 0L;
        }
        return j3;
    }

    public static final long safeMultiplyOrClamp(long j, long j2) {
        if (j2 == -1) {
            if (j == Long.MIN_VALUE) {
                return Long.MAX_VALUE;
            }
            return -j;
        }
        if (j2 == 1) {
            return j;
        }
        long j3 = j * j2;
        if (j3 / j2 != j) {
            return ((j > 0L ? 1 : (j == 0L ? 0 : -1)) > 0) == (j2 > 0) ? Long.MAX_VALUE : Long.MIN_VALUE;
        }
        return j3;
    }

    public static final DivRemResult multiplyAndDivide(long j, long j2, long j3) {
        if (j == 0 || j2 == 0) {
            return new DivRemResult(0L, 0L);
        }
        long jSafeMultiplyOrZero = safeMultiplyOrZero(j, j2);
        if (jSafeMultiplyOrZero != 0) {
            return new DivRemResult(jSafeMultiplyOrZero / j3, jSafeMultiplyOrZero % j3);
        }
        if (j2 == j3) {
            return new DivRemResult(j, 0L);
        }
        if (j == j3) {
            return new DivRemResult(j2, 0L);
        }
        long j4 = j >= 0 ? 0L : -1L;
        long j5 = j2 >= 0 ? 0L : -1L;
        long j6 = j & 4294967295L;
        long j7 = (j >> 32) & 4294967295L;
        long j8 = j2 & 4294967295L;
        long j9 = (j2 >> 32) & 4294967295L;
        long j10 = (j4 * j9) + (j7 * j5);
        long j11 = (j4 * j8) + (j7 * j9) + (j5 * j6);
        long j12 = j7 * j8;
        long j13 = j9 * j6;
        long j14 = j6 * j8;
        long j15 = j14 & 4294967295L;
        long j16 = (j12 & 4294967295L) + (j13 & 4294967295L) + ((j14 >> 32) & 4294967295L);
        long j17 = j16 & 4294967295L;
        long j18 = ((j16 >> 32) & 4294967295L) + (j11 & 4294967295L) + ((j12 >> 32) & 4294967295L) + ((j13 >> 32) & 4294967295L);
        long j19 = (j17 << 32) | j15;
        long j20 = (j18 & 4294967295L) | (((((j18 >> 32) & 4294967295L) + ((j11 >> 32) & 4294967295L)) + (j10 & 4294967295L)) << 32);
        int i = ((j20 >> 63) & 1) == 1 ? -1 : 1;
        if (i == -1) {
            j19 = (~j19) + 1;
            j20 = ~j20;
            if (j19 == 0) {
                j20++;
            }
        }
        int i2 = 127;
        long j21 = 0;
        long j22 = 0;
        while (-1 < i2) {
            j22 = (j22 << 1) | ((i2 < 64 ? j19 >> i2 : j20 >> (i2 - 64)) & 1);
            if (j22 >= j3 || j22 < 0) {
                j22 -= j3;
                if (i2 >= 63) {
                    throw new ArithmeticException("The result of a multiplication followed by division overflows a long");
                }
                j21 |= 1 << i2;
            }
            i2--;
        }
        long j23 = i;
        return new DivRemResult(j21 * j23, j23 * j22);
    }

    public static final long multiplyAddAndDivide(long j, long j2, long j3, long j4) {
        if (j > 0 && j3 < 0) {
            j--;
            j3 += j2;
        } else if (j < 0 && j3 > 0) {
            j++;
            j3 -= j2;
        }
        long j5 = j;
        if (j5 == 0) {
            return j3 / j4;
        }
        DivRemResult divRemResultMultiplyAndDivide = multiplyAndDivide(j5, j2, j4);
        return MathJvmKt.safeAdd(divRemResultMultiplyAndDivide.getQ(), MathJvmKt.safeAdd(j3 / j4, MathJvmKt.safeAdd(j3 % j4, divRemResultMultiplyAndDivide.getR()) / j4));
    }

    public static final long multiplyAndAdd(long j, long j2, long j3) {
        if (j > 0 && j3 < 0) {
            j--;
            j3 += j2;
        } else if (j < 0 && j3 > 0) {
            j++;
            j3 -= j2;
        }
        return MathJvmKt.safeAdd(MathJvmKt.safeMultiply(j, j2), j3);
    }

    public static final int[] getPOWERS_OF_TEN() {
        return POWERS_OF_TEN;
    }

    public static /* synthetic */ long randomUnsafe$default(LongProgression longProgression, Random random, int i, Object obj) {
        if ((i & 1) != 0) {
            random = Random.INSTANCE;
        }
        return randomUnsafe(longProgression, random);
    }

    public static final long randomUnsafe(LongProgression longProgression, Random random) {
        Intrinsics.checkNotNullParameter(longProgression, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        return (RandomKt.nextLong(random, new LongRange(0L, (longProgression.getLast() - longProgression.getFirst()) / longProgression.getStep())) * longProgression.getStep()) + longProgression.getFirst();
    }

    public static /* synthetic */ Long randomUnsafeOrNull$default(LongProgression longProgression, Random random, int i, Object obj) {
        if ((i & 1) != 0) {
            random = Random.INSTANCE;
        }
        return randomUnsafeOrNull(longProgression, random);
    }

    public static final Long randomUnsafeOrNull(LongProgression longProgression, Random random) {
        Intrinsics.checkNotNullParameter(longProgression, "<this>");
        Intrinsics.checkNotNullParameter(random, "random");
        if (longProgression.isEmpty()) {
            return null;
        }
        return Long.valueOf(randomUnsafe(longProgression, random));
    }

    public static final boolean containsUnsafe(LongProgression longProgression, long j) {
        Intrinsics.checkNotNullParameter(longProgression, "<this>");
        LongRange longRange = longProgression.getStep() > 0 ? new LongRange(longProgression.getFirst(), longProgression.getLast()) : new LongRange(longProgression.getLast(), longProgression.getFirst());
        return j <= longRange.getLast() && longRange.getFirst() <= j && (j - longProgression.getFirst()) % longProgression.getStep() == 0;
    }

    public static final int getSizeUnsafe(LongProgression longProgression) {
        Intrinsics.checkNotNullParameter(longProgression, "<this>");
        if (longProgression.isEmpty()) {
            return 0;
        }
        try {
            return clampToInt((MathJvmKt.safeAdd(longProgression.getLast(), -longProgression.getFirst()) / longProgression.getStep()) + 1);
        } catch (ArithmeticException unused) {
            return Integer.MAX_VALUE;
        }
    }
}
