package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true)
public final class UnsignedInteger extends Number implements Comparable<UnsignedInteger> {
    public final int value;
    public static final UnsignedInteger ZERO = new UnsignedInteger(0);
    public static final UnsignedInteger ONE = new UnsignedInteger(1);
    public static final UnsignedInteger MAX_VALUE = new UnsignedInteger(-1);

    public UnsignedInteger(int i) {
        this.value = i & (-1);
    }

    public static UnsignedInteger fromIntBits(int i) {
        return new UnsignedInteger(i);
    }

    public static UnsignedInteger valueOf(long j) {
        Preconditions.checkArgument((4294967295L & j) == j, "value (%s) is outside the range for an unsigned integer value", j);
        return new UnsignedInteger((int) j);
    }

    public BigInteger bigIntegerValue() {
        return BigInteger.valueOf(longValue());
    }

    public UnsignedInteger dividedBy(UnsignedInteger unsignedInteger) {
        int i = this.value;
        if (unsignedInteger != null) {
            return new UnsignedInteger(UnsignedInts.divide(i, unsignedInteger.value));
        }
        throw new NullPointerException();
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return longValue();
    }

    public boolean equals(Object obj) {
        return (obj instanceof UnsignedInteger) && this.value == ((UnsignedInteger) obj).value;
    }

    @Override // java.lang.Number
    public float floatValue() {
        return longValue();
    }

    public int hashCode() {
        return this.value;
    }

    @Override // java.lang.Number
    public int intValue() {
        return this.value;
    }

    @Override // java.lang.Number
    public long longValue() {
        return UnsignedInts.toLong(this.value);
    }

    public UnsignedInteger minus(UnsignedInteger unsignedInteger) {
        int i = this.value;
        if (unsignedInteger != null) {
            return new UnsignedInteger(i - unsignedInteger.value);
        }
        throw new NullPointerException();
    }

    public UnsignedInteger mod(UnsignedInteger unsignedInteger) {
        int i = this.value;
        if (unsignedInteger != null) {
            return new UnsignedInteger(UnsignedInts.remainder(i, unsignedInteger.value));
        }
        throw new NullPointerException();
    }

    public UnsignedInteger plus(UnsignedInteger unsignedInteger) {
        int i = this.value;
        if (unsignedInteger != null) {
            return new UnsignedInteger(i + unsignedInteger.value);
        }
        throw new NullPointerException();
    }

    @GwtIncompatible
    public UnsignedInteger times(UnsignedInteger unsignedInteger) {
        int i = this.value;
        if (unsignedInteger != null) {
            return new UnsignedInteger(i * unsignedInteger.value);
        }
        throw new NullPointerException();
    }

    public String toString() {
        return toString(10);
    }

    @Override // java.lang.Comparable
    public int compareTo(UnsignedInteger unsignedInteger) {
        if (unsignedInteger != null) {
            return UnsignedInts.compare(this.value, unsignedInteger.value);
        }
        throw new NullPointerException();
    }

    public String toString(int i) {
        return UnsignedInts.toString(this.value, i);
    }

    public static UnsignedInteger valueOf(BigInteger bigInteger) {
        if (bigInteger != null) {
            Preconditions.checkArgument(bigInteger.signum() >= 0 && bigInteger.bitLength() <= 32, "value (%s) is outside the range for an unsigned integer value", bigInteger);
            return new UnsignedInteger(bigInteger.intValue());
        }
        throw new NullPointerException();
    }

    public static UnsignedInteger valueOf(String str) {
        return valueOf(str, 10);
    }

    public static UnsignedInteger valueOf(String str, int i) {
        return new UnsignedInteger(UnsignedInts.parseUnsignedInt(str, i));
    }
}
