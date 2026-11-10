package com.google.common.math;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtIncompatible
public final class PairedStats implements Serializable {
    public static final int BYTES = 88;
    public static final long serialVersionUID = 0;
    public final double sumOfProductsOfDeltas;
    public final Stats xStats;
    public final Stats yStats;

    public PairedStats(Stats stats, Stats stats2, double d2) {
        this.xStats = stats;
        this.yStats = stats2;
        this.sumOfProductsOfDeltas = d2;
    }

    public static double ensureInUnitRange(double d2) {
        if (d2 >= 1.0d) {
            return 1.0d;
        }
        if (d2 <= -1.0d) {
            return -1.0d;
        }
        return d2;
    }

    public static double ensurePositive(double d2) {
        if (d2 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return d2;
        }
        return Double.MIN_VALUE;
    }

    public static PairedStats fromByteArray(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException();
        }
        Preconditions.checkArgument(bArr.length == 88, "Expected PairedStats.BYTES = %s, got %s", 88, bArr.length);
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        return new PairedStats(Stats.readFrom(byteBufferOrder), Stats.readFrom(byteBufferOrder), byteBufferOrder.getDouble());
    }

    public long count() {
        return this.xStats.count();
    }

    public boolean equals(Object obj) {
        if (obj == null || PairedStats.class != obj.getClass()) {
            return false;
        }
        PairedStats pairedStats = (PairedStats) obj;
        return this.xStats.equals(pairedStats.xStats) && this.yStats.equals(pairedStats.yStats) && Double.doubleToLongBits(this.sumOfProductsOfDeltas) == Double.doubleToLongBits(pairedStats.sumOfProductsOfDeltas);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.xStats, this.yStats, Double.valueOf(this.sumOfProductsOfDeltas)});
    }

    public LinearTransformation leastSquaresFit() {
        Preconditions.checkState(count() > 1);
        if (Double.isNaN(this.sumOfProductsOfDeltas)) {
            return LinearTransformation.NaNLinearTransformation.INSTANCE;
        }
        double dSumOfSquaresOfDeltas = this.xStats.sumOfSquaresOfDeltas();
        if (dSumOfSquaresOfDeltas > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return this.yStats.sumOfSquaresOfDeltas() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? LinearTransformation.mapping(this.xStats.mean(), this.yStats.mean()).withSlope(this.sumOfProductsOfDeltas / dSumOfSquaresOfDeltas) : LinearTransformation.horizontal(this.yStats.mean());
        }
        Preconditions.checkState(this.yStats.sumOfSquaresOfDeltas() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        return LinearTransformation.vertical(this.xStats.mean());
    }

    public double pearsonsCorrelationCoefficient() {
        Preconditions.checkState(count() > 1);
        if (Double.isNaN(this.sumOfProductsOfDeltas)) {
            return Double.NaN;
        }
        double dSumOfSquaresOfDeltas = xStats().sumOfSquaresOfDeltas();
        double dSumOfSquaresOfDeltas2 = yStats().sumOfSquaresOfDeltas();
        Preconditions.checkState(dSumOfSquaresOfDeltas > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        Preconditions.checkState(dSumOfSquaresOfDeltas2 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        return ensureInUnitRange(this.sumOfProductsOfDeltas / Math.sqrt(ensurePositive(dSumOfSquaresOfDeltas * dSumOfSquaresOfDeltas2)));
    }

    public double populationCovariance() {
        Preconditions.checkState(count() != 0);
        return this.sumOfProductsOfDeltas / count();
    }

    public double sampleCovariance() {
        Preconditions.checkState(count() > 1);
        return this.sumOfProductsOfDeltas / (count() - 1);
    }

    public double sumOfProductsOfDeltas() {
        return this.sumOfProductsOfDeltas;
    }

    public byte[] toByteArray() {
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(88).order(ByteOrder.LITTLE_ENDIAN);
        this.xStats.writeTo(byteBufferOrder);
        this.yStats.writeTo(byteBufferOrder);
        byteBufferOrder.putDouble(this.sumOfProductsOfDeltas);
        return byteBufferOrder.array();
    }

    public String toString() {
        return count() > 0 ? MoreObjects.toStringHelper(this).add("xStats", this.xStats).add("yStats", this.yStats).add("populationCovariance", populationCovariance()).toString() : MoreObjects.toStringHelper(this).add("xStats", this.xStats).add("yStats", this.yStats).toString();
    }

    public Stats xStats() {
        return this.xStats;
    }

    public Stats yStats() {
        return this.yStats;
    }
}
