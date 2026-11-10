package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.common.primitives.UnsignedLong;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.lang.Comparable;
import java.math.BigInteger;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public abstract class DiscreteDomain<C extends Comparable> {
    public final boolean supportsFastOffset;

    private static final class BigIntegerDomain extends DiscreteDomain<BigInteger> implements Serializable {
        public static final long serialVersionUID = 0;
        public static final BigIntegerDomain INSTANCE = new BigIntegerDomain();
        public static final BigInteger MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);
        public static final BigInteger MAX_LONG = BigInteger.valueOf(UnsignedLong.UNSIGNED_MASK);

        public BigIntegerDomain() {
            super(true, null);
        }

        private Object readResolve() {
            return INSTANCE;
        }

        public String toString() {
            return "DiscreteDomain.bigIntegers()";
        }

        @Override // com.google.common.collect.DiscreteDomain
        public long distance(BigInteger bigInteger, BigInteger bigInteger2) {
            return bigInteger2.subtract(bigInteger).max(MIN_LONG).min(MAX_LONG).longValue();
        }

        @Override // com.google.common.collect.DiscreteDomain
        public BigInteger next(BigInteger bigInteger) {
            return bigInteger.add(BigInteger.ONE);
        }

        @Override // com.google.common.collect.DiscreteDomain
        public BigInteger offset(BigInteger bigInteger, long j) {
            CollectPreconditions.checkNonnegative(j, "distance");
            return bigInteger.add(BigInteger.valueOf(j));
        }

        @Override // com.google.common.collect.DiscreteDomain
        public BigInteger previous(BigInteger bigInteger) {
            return bigInteger.subtract(BigInteger.ONE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class IntegerDomain extends DiscreteDomain<Integer> implements Serializable {
        public static final IntegerDomain INSTANCE = new IntegerDomain();
        public static final long serialVersionUID = 0;

        public IntegerDomain() {
            super(true, null);
        }

        private Object readResolve() {
            return INSTANCE;
        }

        public String toString() {
            return "DiscreteDomain.integers()";
        }

        @Override // com.google.common.collect.DiscreteDomain
        public long distance(Integer num, Integer num2) {
            return ((long) num2.intValue()) - ((long) num.intValue());
        }

        @Override // com.google.common.collect.DiscreteDomain
        public Integer maxValue() {
            return Integer.MAX_VALUE;
        }

        @Override // com.google.common.collect.DiscreteDomain
        public Integer minValue() {
            return Integer.MIN_VALUE;
        }

        @Override // com.google.common.collect.DiscreteDomain
        public Integer next(Integer num) {
            int iIntValue = num.intValue();
            if (iIntValue == Integer.MAX_VALUE) {
                return null;
            }
            return Integer.valueOf(iIntValue + 1);
        }

        @Override // com.google.common.collect.DiscreteDomain
        public Integer offset(Integer num, long j) {
            CollectPreconditions.checkNonnegative(j, "distance");
            return Integer.valueOf(Ints.checkedCast(num.longValue() + j));
        }

        @Override // com.google.common.collect.DiscreteDomain
        public Integer previous(Integer num) {
            int iIntValue = num.intValue();
            if (iIntValue == Integer.MIN_VALUE) {
                return null;
            }
            return Integer.valueOf(iIntValue - 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class LongDomain extends DiscreteDomain<Long> implements Serializable {
        public static final LongDomain INSTANCE = new LongDomain();
        public static final long serialVersionUID = 0;

        public LongDomain() {
            super(true, null);
        }

        private Object readResolve() {
            return INSTANCE;
        }

        public String toString() {
            return "DiscreteDomain.longs()";
        }

        @Override // com.google.common.collect.DiscreteDomain
        public long distance(Long l, Long l2) {
            long jLongValue = l2.longValue() - l.longValue();
            if (l2.longValue() > l.longValue() && jLongValue < 0) {
                return UnsignedLong.UNSIGNED_MASK;
            }
            if (l2.longValue() >= l.longValue() || jLongValue <= 0) {
                return jLongValue;
            }
            return Long.MIN_VALUE;
        }

        @Override // com.google.common.collect.DiscreteDomain
        public Long maxValue() {
            return Long.valueOf(UnsignedLong.UNSIGNED_MASK);
        }

        @Override // com.google.common.collect.DiscreteDomain
        public Long minValue() {
            return Long.MIN_VALUE;
        }

        @Override // com.google.common.collect.DiscreteDomain
        public Long next(Long l) {
            long jLongValue = l.longValue();
            if (jLongValue == UnsignedLong.UNSIGNED_MASK) {
                return null;
            }
            return Long.valueOf(jLongValue + 1);
        }

        @Override // com.google.common.collect.DiscreteDomain
        public Long offset(Long l, long j) {
            CollectPreconditions.checkNonnegative(j, "distance");
            long jLongValue = l.longValue() + j;
            if (jLongValue < 0) {
                Preconditions.checkArgument(l.longValue() < 0, "overflow");
            }
            return Long.valueOf(jLongValue);
        }

        @Override // com.google.common.collect.DiscreteDomain
        public Long previous(Long l) {
            long jLongValue = l.longValue();
            if (jLongValue == Long.MIN_VALUE) {
                return null;
            }
            return Long.valueOf(jLongValue - 1);
        }
    }

    public DiscreteDomain() {
        this.supportsFastOffset = false;
    }

    public static DiscreteDomain<BigInteger> bigIntegers() {
        return BigIntegerDomain.INSTANCE;
    }

    public static DiscreteDomain<Integer> integers() {
        return IntegerDomain.INSTANCE;
    }

    public static DiscreteDomain<Long> longs() {
        return LongDomain.INSTANCE;
    }

    public abstract long distance(C c2, C c3);

    @CanIgnoreReturnValue
    public C maxValue() {
        throw new NoSuchElementException();
    }

    @CanIgnoreReturnValue
    public C minValue() {
        throw new NoSuchElementException();
    }

    public abstract C next(C c2);

    public C offset(C c2, long j) {
        CollectPreconditions.checkNonnegative(j, "distance");
        for (long j2 = 0; j2 < j; j2++) {
            c2 = (C) next(c2);
        }
        return c2;
    }

    public abstract C previous(C c2);

    public DiscreteDomain(boolean z) {
        this.supportsFastOffset = z;
    }

    public /* synthetic */ DiscreteDomain(boolean z, AnonymousClass1 anonymousClass1) {
        this.supportsFastOffset = z;
    }
}
