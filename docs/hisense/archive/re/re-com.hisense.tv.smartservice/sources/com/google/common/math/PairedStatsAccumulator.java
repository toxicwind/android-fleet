package com.google.common.math;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Doubles;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtIncompatible
public final class PairedStatsAccumulator {
    public final StatsAccumulator xStats = new StatsAccumulator();
    public final StatsAccumulator yStats = new StatsAccumulator();
    public double sumOfProductsOfDeltas = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;

    public static double ensureInUnitRange(double d2) {
        return Doubles.constrainToRange(d2, -1.0d, 1.0d);
    }

    private double ensurePositive(double d2) {
        if (d2 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return d2;
        }
        return Double.MIN_VALUE;
    }

    public void add(double d2, double d3) {
        this.xStats.add(d2);
        if (!Doubles.isFinite(d2) || !Doubles.isFinite(d3)) {
            this.sumOfProductsOfDeltas = Double.NaN;
        } else if (this.xStats.count() > 1) {
            this.sumOfProductsOfDeltas = ((d3 - this.yStats.mean()) * (d2 - this.xStats.mean())) + this.sumOfProductsOfDeltas;
        }
        this.yStats.add(d3);
    }

    public void addAll(PairedStats pairedStats) {
        if (pairedStats.count() == 0) {
            return;
        }
        this.xStats.addAll(pairedStats.xStats());
        if (this.yStats.count() == 0) {
            this.sumOfProductsOfDeltas = pairedStats.sumOfProductsOfDeltas();
        } else {
            this.sumOfProductsOfDeltas = ((pairedStats.yStats().mean() - this.yStats.mean()) * (pairedStats.xStats().mean() - this.xStats.mean()) * pairedStats.count()) + pairedStats.sumOfProductsOfDeltas() + this.sumOfProductsOfDeltas;
        }
        this.yStats.addAll(pairedStats.yStats());
    }

    public long count() {
        return this.xStats.count();
    }

    public final LinearTransformation leastSquaresFit() {
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

    public final double pearsonsCorrelationCoefficient() {
        Preconditions.checkState(count() > 1);
        if (Double.isNaN(this.sumOfProductsOfDeltas)) {
            return Double.NaN;
        }
        double dSumOfSquaresOfDeltas = this.xStats.sumOfSquaresOfDeltas();
        double dSumOfSquaresOfDeltas2 = this.yStats.sumOfSquaresOfDeltas();
        Preconditions.checkState(dSumOfSquaresOfDeltas > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        Preconditions.checkState(dSumOfSquaresOfDeltas2 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        return ensureInUnitRange(this.sumOfProductsOfDeltas / Math.sqrt(ensurePositive(dSumOfSquaresOfDeltas * dSumOfSquaresOfDeltas2)));
    }

    public double populationCovariance() {
        Preconditions.checkState(count() != 0);
        return this.sumOfProductsOfDeltas / count();
    }

    public final double sampleCovariance() {
        Preconditions.checkState(count() > 1);
        return this.sumOfProductsOfDeltas / (count() - 1);
    }

    public PairedStats snapshot() {
        return new PairedStats(this.xStats.snapshot(), this.yStats.snapshot(), this.sumOfProductsOfDeltas);
    }

    public Stats xStats() {
        return this.xStats.snapshot();
    }

    public Stats yStats() {
        return this.yStats.snapshot();
    }
}
