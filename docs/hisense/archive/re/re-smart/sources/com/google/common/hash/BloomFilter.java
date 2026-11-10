package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.math.DoubleMath;
import com.google.common.primitives.SignedBytes;
import com.google.common.primitives.UnsignedBytes;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.RoundingMode;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
@Beta
public final class BloomFilter<T> implements Predicate<T>, Serializable {
    public final BloomFilterStrategies.LockFreeBitArray bits;
    public final Funnel<? super T> funnel;
    public final int numHashFunctions;
    public final Strategy strategy;

    private static class SerialForm<T> implements Serializable {
        public static final long serialVersionUID = 1;
        public final long[] data;
        public final Funnel<? super T> funnel;
        public final int numHashFunctions;
        public final Strategy strategy;

        public SerialForm(BloomFilter<T> bloomFilter) {
            this.data = BloomFilterStrategies.LockFreeBitArray.toPlainArray(bloomFilter.bits.data);
            this.numHashFunctions = bloomFilter.numHashFunctions;
            this.funnel = bloomFilter.funnel;
            this.strategy = bloomFilter.strategy;
        }

        public Object readResolve() {
            return new BloomFilter(new BloomFilterStrategies.LockFreeBitArray(this.data), this.numHashFunctions, this.funnel, this.strategy);
        }
    }

    interface Strategy extends Serializable {
        <T> boolean mightContain(T t, Funnel<? super T> funnel, int i, BloomFilterStrategies.LockFreeBitArray lockFreeBitArray);

        int ordinal();

        <T> boolean put(T t, Funnel<? super T> funnel, int i, BloomFilterStrategies.LockFreeBitArray lockFreeBitArray);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, int i, double d2) {
        return create(funnel, i, d2);
    }

    @VisibleForTesting
    public static long optimalNumOfBits(long j, double d2) {
        if (d2 == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            d2 = Double.MIN_VALUE;
        }
        return (long) ((Math.log(d2) * (-j)) / (Math.log(2.0d) * Math.log(2.0d)));
    }

    @VisibleForTesting
    public static int optimalNumOfHashFunctions(long j, long j2) {
        return Math.max(1, (int) Math.round(Math.log(2.0d) * (j2 / j)));
    }

    public static <T> BloomFilter<T> readFrom(InputStream inputStream, Funnel<? super T> funnel) throws IOException {
        byte b2;
        int i;
        Preconditions.checkNotNull(inputStream, "InputStream");
        Preconditions.checkNotNull(funnel, "Funnel");
        int i2 = -1;
        try {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            b2 = dataInputStream.readByte();
            try {
                i = dataInputStream.readByte() & 255;
                try {
                    i2 = dataInputStream.readInt();
                    BloomFilterStrategies bloomFilterStrategies = BloomFilterStrategies.values()[b2];
                    long[] jArr = new long[i2];
                    for (int i3 = 0; i3 < jArr.length; i3++) {
                        jArr[i3] = dataInputStream.readLong();
                    }
                    return new BloomFilter<>(new BloomFilterStrategies.LockFreeBitArray(jArr), i, funnel, bloomFilterStrategies);
                } catch (RuntimeException e2) {
                    e = e2;
                    StringBuilder sb = new StringBuilder(134);
                    sb.append("Unable to deserialize BloomFilter from InputStream. strategyOrdinal: ");
                    sb.append((int) b2);
                    sb.append(" numHashFunctions: ");
                    sb.append(i);
                    sb.append(" dataLength: ");
                    sb.append(i2);
                    throw new IOException(sb.toString(), e);
                }
            } catch (RuntimeException e3) {
                e = e3;
                i = -1;
            }
        } catch (RuntimeException e4) {
            e = e4;
            b2 = -1;
            i = -1;
        }
    }

    private Object writeReplace() {
        return new SerialForm(this);
    }

    @Override // com.google.common.base.Predicate
    @Deprecated
    public boolean apply(T t) {
        return mightContain(t);
    }

    public long approximateElementCount() {
        double dBitSize = this.bits.bitSize();
        return DoubleMath.roundToLong(((-Math.log1p(-(this.bits.bitCount() / dBitSize))) * dBitSize) / ((double) this.numHashFunctions), RoundingMode.HALF_UP);
    }

    @VisibleForTesting
    public long bitSize() {
        return this.bits.bitSize();
    }

    public BloomFilter<T> copy() {
        return new BloomFilter<>(this.bits.copy(), this.numHashFunctions, this.funnel, this.strategy);
    }

    @Override // com.google.common.base.Predicate
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BloomFilter)) {
            return false;
        }
        BloomFilter bloomFilter = (BloomFilter) obj;
        return this.numHashFunctions == bloomFilter.numHashFunctions && this.funnel.equals(bloomFilter.funnel) && this.bits.equals(bloomFilter.bits) && this.strategy.equals(bloomFilter.strategy);
    }

    public double expectedFpp() {
        return Math.pow(this.bits.bitCount() / bitSize(), this.numHashFunctions);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.numHashFunctions), this.funnel, this.strategy, this.bits});
    }

    public boolean isCompatible(BloomFilter<T> bloomFilter) {
        if (bloomFilter != null) {
            return this != bloomFilter && this.numHashFunctions == bloomFilter.numHashFunctions && bitSize() == bloomFilter.bitSize() && this.strategy.equals(bloomFilter.strategy) && this.funnel.equals(bloomFilter.funnel);
        }
        throw new NullPointerException();
    }

    public boolean mightContain(T t) {
        return this.strategy.mightContain(t, this.funnel, this.numHashFunctions, this.bits);
    }

    @CanIgnoreReturnValue
    public boolean put(T t) {
        return this.strategy.put(t, this.funnel, this.numHashFunctions, this.bits);
    }

    public void putAll(BloomFilter<T> bloomFilter) {
        if (bloomFilter == null) {
            throw new NullPointerException();
        }
        Preconditions.checkArgument(this != bloomFilter, "Cannot combine a BloomFilter with itself.");
        Preconditions.checkArgument(this.numHashFunctions == bloomFilter.numHashFunctions, "BloomFilters must have the same number of hash functions (%s != %s)", this.numHashFunctions, bloomFilter.numHashFunctions);
        Preconditions.checkArgument(bitSize() == bloomFilter.bitSize(), "BloomFilters must have the same size underlying bit arrays (%s != %s)", bitSize(), bloomFilter.bitSize());
        Preconditions.checkArgument(this.strategy.equals(bloomFilter.strategy), "BloomFilters must have equal strategies (%s != %s)", this.strategy, bloomFilter.strategy);
        Preconditions.checkArgument(this.funnel.equals(bloomFilter.funnel), "BloomFilters must have equal funnels (%s != %s)", this.funnel, bloomFilter.funnel);
        this.bits.putAll(bloomFilter.bits);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeByte(SignedBytes.checkedCast(this.strategy.ordinal()));
        dataOutputStream.writeByte(UnsignedBytes.checkedCast(this.numHashFunctions));
        dataOutputStream.writeInt(this.bits.data.length());
        for (int i = 0; i < this.bits.data.length(); i++) {
            dataOutputStream.writeLong(this.bits.data.get(i));
        }
    }

    public BloomFilter(BloomFilterStrategies.LockFreeBitArray lockFreeBitArray, int i, Funnel<? super T> funnel, Strategy strategy) {
        Preconditions.checkArgument(i > 0, "numHashFunctions (%s) must be > 0", i);
        Preconditions.checkArgument(i <= 255, "numHashFunctions (%s) must be <= 255", i);
        if (lockFreeBitArray == null) {
            throw new NullPointerException();
        }
        this.bits = lockFreeBitArray;
        this.numHashFunctions = i;
        if (funnel == null) {
            throw new NullPointerException();
        }
        this.funnel = funnel;
        if (strategy == null) {
            throw new NullPointerException();
        }
        this.strategy = strategy;
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, long j, double d2) {
        return create(funnel, j, d2, BloomFilterStrategies.MURMUR128_MITZ_64);
    }

    @VisibleForTesting
    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, long j, double d2, Strategy strategy) {
        if (funnel != null) {
            Preconditions.checkArgument(j >= 0, "Expected insertions (%s) must be >= 0", j);
            Preconditions.checkArgument(d2 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, "False positive probability (%s) must be > 0.0", Double.valueOf(d2));
            Preconditions.checkArgument(d2 < 1.0d, "False positive probability (%s) must be < 1.0", Double.valueOf(d2));
            if (strategy != null) {
                if (j == 0) {
                    j = 1;
                }
                long jOptimalNumOfBits = optimalNumOfBits(j, d2);
                try {
                    return new BloomFilter<>(new BloomFilterStrategies.LockFreeBitArray(jOptimalNumOfBits), optimalNumOfHashFunctions(j, jOptimalNumOfBits), funnel, strategy);
                } catch (IllegalArgumentException e2) {
                    StringBuilder sb = new StringBuilder(57);
                    sb.append("Could not create BloomFilter of ");
                    sb.append(jOptimalNumOfBits);
                    sb.append(" bits");
                    throw new IllegalArgumentException(sb.toString(), e2);
                }
            }
            throw new NullPointerException();
        }
        throw new NullPointerException();
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, int i) {
        return create(funnel, i);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, long j) {
        return create(funnel, j, 0.03d);
    }
}
