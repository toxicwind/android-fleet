package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
import com.google.common.primitives.ImmutableLongArray;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLongArray;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public class AtomicDoubleArray implements Serializable {
    public static final long serialVersionUID = 0;
    public transient AtomicLongArray longs;

    public AtomicDoubleArray(int i) {
        this.longs = new AtomicLongArray(i);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        int i = objectInputStream.readInt();
        ImmutableLongArray.Builder builder = ImmutableLongArray.builder();
        for (int i2 = 0; i2 < i; i2++) {
            builder.add(Double.doubleToRawLongBits(objectInputStream.readDouble()));
        }
        this.longs = new AtomicLongArray(builder.build().toArray());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        int length = length();
        objectOutputStream.writeInt(length);
        for (int i = 0; i < length; i++) {
            objectOutputStream.writeDouble(get(i));
        }
    }

    @CanIgnoreReturnValue
    public double addAndGet(int i, double d2) {
        long j;
        double dLongBitsToDouble;
        do {
            j = this.longs.get(i);
            dLongBitsToDouble = Double.longBitsToDouble(j) + d2;
        } while (!this.longs.compareAndSet(i, j, Double.doubleToRawLongBits(dLongBitsToDouble)));
        return dLongBitsToDouble;
    }

    public final boolean compareAndSet(int i, double d2, double d3) {
        return this.longs.compareAndSet(i, Double.doubleToRawLongBits(d2), Double.doubleToRawLongBits(d3));
    }

    public final double get(int i) {
        return Double.longBitsToDouble(this.longs.get(i));
    }

    @CanIgnoreReturnValue
    public final double getAndAdd(int i, double d2) {
        long j;
        double dLongBitsToDouble;
        do {
            j = this.longs.get(i);
            dLongBitsToDouble = Double.longBitsToDouble(j);
        } while (!this.longs.compareAndSet(i, j, Double.doubleToRawLongBits(dLongBitsToDouble + d2)));
        return dLongBitsToDouble;
    }

    public final double getAndSet(int i, double d2) {
        return Double.longBitsToDouble(this.longs.getAndSet(i, Double.doubleToRawLongBits(d2)));
    }

    public final void lazySet(int i, double d2) {
        this.longs.lazySet(i, Double.doubleToRawLongBits(d2));
    }

    public final int length() {
        return this.longs.length();
    }

    public final void set(int i, double d2) {
        this.longs.set(i, Double.doubleToRawLongBits(d2));
    }

    public String toString() {
        int length = length() - 1;
        if (length == -1) {
            return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        }
        StringBuilder sb = new StringBuilder((length + 1) * 19);
        sb.append('[');
        int i = 0;
        while (true) {
            sb.append(Double.longBitsToDouble(this.longs.get(i)));
            if (i == length) {
                sb.append(']');
                return sb.toString();
            }
            sb.append(',');
            sb.append(Ascii.CASE_MASK);
            i++;
        }
    }

    public final boolean weakCompareAndSet(int i, double d2, double d3) {
        return this.longs.weakCompareAndSet(i, Double.doubleToRawLongBits(d2), Double.doubleToRawLongBits(d3));
    }

    public AtomicDoubleArray(double[] dArr) {
        int length = dArr.length;
        long[] jArr = new long[length];
        for (int i = 0; i < length; i++) {
            jArr[i] = Double.doubleToRawLongBits(dArr[i]);
        }
        this.longs = new AtomicLongArray(jArr);
    }
}
