package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(serializable = true)
public final class UnsignedLong extends Number implements Comparable<UnsignedLong>, Serializable {
    public static final long UNSIGNED_MASK = Long.MAX_VALUE;
    public final long value;
    public static final UnsignedLong ZERO = new UnsignedLong(0);
    public static final UnsignedLong ONE = new UnsignedLong(1);
    public static final UnsignedLong MAX_VALUE = new UnsignedLong(-1);

    public UnsignedLong(long j) {
        this.value = j;
    }

    public static UnsignedLong fromLongBits(long j) {
        return new UnsignedLong(j);
    }

    @CanIgnoreReturnValue
    public static UnsignedLong valueOf(long j) {
        Preconditions.checkArgument(j >= 0, "value (%s) is outside the range for an unsigned long value", j);
        return new UnsignedLong(j);
    }

    public BigInteger bigIntegerValue() {
        BigInteger bigIntegerValueOf = BigInteger.valueOf(this.value & UNSIGNED_MASK);
        return this.value < 0 ? bigIntegerValueOf.setBit(63) : bigIntegerValueOf;
    }

    public UnsignedLong dividedBy(UnsignedLong unsignedLong) {
        long j = this.value;
        if (unsignedLong != null) {
            return new UnsignedLong(UnsignedLongs.divide(j, unsignedLong.value));
        }
        throw new NullPointerException();
    }

    @Override // java.lang.Number
    public double doubleValue() {
        long j = this.value;
        double d2 = UNSIGNED_MASK & j;
        return j < 0 ? d2 + 9.223372036854776E18d : d2;
    }

    public boolean equals(Object obj) {
        return (obj instanceof UnsignedLong) && this.value == ((UnsignedLong) obj).value;
    }

    @Override // java.lang.Number
    public float floatValue() {
        long j = this.value;
        float f2 = UNSIGNED_MASK & j;
        return j < 0 ? f2 + 9.223372E18f : f2;
    }

    public int hashCode() {
        return Longs.hashCode(this.value);
    }

    @Override // java.lang.Number
    public int intValue() {
        return (int) this.value;
    }

    @Override // java.lang.Number
    public long longValue() {
        return this.value;
    }

    public UnsignedLong minus(UnsignedLong unsignedLong) {
        long j = this.value;
        if (unsignedLong != null) {
            return new UnsignedLong(j - unsignedLong.value);
        }
        throw new NullPointerException();
    }

    public UnsignedLong mod(UnsignedLong unsignedLong) {
        long j = this.value;
        if (unsignedLong != null) {
            return new UnsignedLong(UnsignedLongs.remainder(j, unsignedLong.value));
        }
        throw new NullPointerException();
    }

    public UnsignedLong plus(UnsignedLong unsignedLong) {
        long j = this.value;
        if (unsignedLong != null) {
            return new UnsignedLong(j + unsignedLong.value);
        }
        throw new NullPointerException();
    }

    public UnsignedLong times(UnsignedLong unsignedLong) {
        long j = this.value;
        if (unsignedLong != null) {
            return new UnsignedLong(j * unsignedLong.value);
        }
        throw new NullPointerException();
    }

    public String toString() {
        return UnsignedLongs.toString(this.value);
    }

    @Override // java.lang.Comparable
    public int compareTo(UnsignedLong unsignedLong) {
        if (unsignedLong != null) {
            return UnsignedLongs.compare(this.value, unsignedLong.value);
        }
        throw new NullPointerException();
    }

    public String toString(int i) {
        return UnsignedLongs.toString(this.value, i);
    }

    @CanIgnoreReturnValue
    public static UnsignedLong valueOf(BigInteger bigInteger) {
        if (bigInteger != null) {
            Preconditions.checkArgument(bigInteger.signum() >= 0 && bigInteger.bitLength() <= 64, "value (%s) is outside the range for an unsigned long value", bigInteger);
            return new UnsignedLong(bigInteger.longValue());
        }
        throw new NullPointerException();
    }

    @CanIgnoreReturnValue
    public static UnsignedLong valueOf(String str) {
        return valueOf(str, 10);
    }

    @CanIgnoreReturnValue
    public static UnsignedLong valueOf(String str, int i) {
        return new UnsignedLong(UnsignedLongs.parseUnsignedLong(str, i));
    }
}
