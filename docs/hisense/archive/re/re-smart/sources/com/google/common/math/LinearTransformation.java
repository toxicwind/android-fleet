package com.google.common.math;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtIncompatible
public abstract class LinearTransformation {

    /* JADX INFO: Access modifiers changed from: private */
    static final class NaNLinearTransformation extends LinearTransformation {
        public static final NaNLinearTransformation INSTANCE = new NaNLinearTransformation();

        @Override // com.google.common.math.LinearTransformation
        public LinearTransformation inverse() {
            return this;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isHorizontal() {
            return false;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isVertical() {
            return false;
        }

        @Override // com.google.common.math.LinearTransformation
        public double slope() {
            return Double.NaN;
        }

        public String toString() {
            return "NaN";
        }

        @Override // com.google.common.math.LinearTransformation
        public double transform(double d2) {
            return Double.NaN;
        }
    }

    public static LinearTransformation forNaN() {
        return NaNLinearTransformation.INSTANCE;
    }

    public static LinearTransformation horizontal(double d2) {
        Preconditions.checkArgument(DoubleUtils.isFinite(d2));
        return new RegularLinearTransformation(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, d2);
    }

    public static LinearTransformationBuilder mapping(double d2, double d3) {
        Preconditions.checkArgument(DoubleUtils.isFinite(d2) && DoubleUtils.isFinite(d3));
        return new LinearTransformationBuilder(d2, d3, null);
    }

    public static LinearTransformation vertical(double d2) {
        Preconditions.checkArgument(DoubleUtils.isFinite(d2));
        return new VerticalLinearTransformation(d2);
    }

    public abstract LinearTransformation inverse();

    public abstract boolean isHorizontal();

    public abstract boolean isVertical();

    public abstract double slope();

    public abstract double transform(double d2);

    public static final class LinearTransformationBuilder {
        public final double x1;
        public final double y1;

        public LinearTransformationBuilder(double d2, double d3) {
            this.x1 = d2;
            this.y1 = d3;
        }

        public LinearTransformation and(double d2, double d3) {
            Preconditions.checkArgument(DoubleUtils.isFinite(d2) && DoubleUtils.isFinite(d3));
            double d4 = this.x1;
            if (d2 != d4) {
                return withSlope((d3 - this.y1) / (d2 - d4));
            }
            Preconditions.checkArgument(d3 != this.y1);
            return new VerticalLinearTransformation(this.x1);
        }

        public LinearTransformation withSlope(double d2) {
            Preconditions.checkArgument(!Double.isNaN(d2));
            return DoubleUtils.isFinite(d2) ? new RegularLinearTransformation(d2, this.y1 - (this.x1 * d2)) : new VerticalLinearTransformation(this.x1);
        }

        public /* synthetic */ LinearTransformationBuilder(double d2, double d3, AnonymousClass1 anonymousClass1) {
            this.x1 = d2;
            this.y1 = d3;
        }
    }

    private static final class VerticalLinearTransformation extends LinearTransformation {

        @LazyInit
        public LinearTransformation inverse;
        public final double x;

        public VerticalLinearTransformation(double d2) {
            this.x = d2;
            this.inverse = null;
        }

        private LinearTransformation createInverse() {
            return new RegularLinearTransformation(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, this.x, this);
        }

        @Override // com.google.common.math.LinearTransformation
        public LinearTransformation inverse() {
            LinearTransformation linearTransformation = this.inverse;
            if (linearTransformation != null) {
                return linearTransformation;
            }
            LinearTransformation linearTransformationCreateInverse = createInverse();
            this.inverse = linearTransformationCreateInverse;
            return linearTransformationCreateInverse;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isHorizontal() {
            return false;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isVertical() {
            return true;
        }

        @Override // com.google.common.math.LinearTransformation
        public double slope() {
            throw new IllegalStateException();
        }

        public String toString() {
            return String.format("x = %g", Double.valueOf(this.x));
        }

        @Override // com.google.common.math.LinearTransformation
        public double transform(double d2) {
            throw new IllegalStateException();
        }

        public VerticalLinearTransformation(double d2, LinearTransformation linearTransformation) {
            this.x = d2;
            this.inverse = linearTransformation;
        }
    }

    private static final class RegularLinearTransformation extends LinearTransformation {

        @LazyInit
        public LinearTransformation inverse;
        public final double slope;
        public final double yIntercept;

        public RegularLinearTransformation(double d2, double d3) {
            this.slope = d2;
            this.yIntercept = d3;
            this.inverse = null;
        }

        private LinearTransformation createInverse() {
            double d2 = this.slope;
            return d2 != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? new RegularLinearTransformation(1.0d / d2, (this.yIntercept * (-1.0d)) / d2, this) : new VerticalLinearTransformation(this.yIntercept, this);
        }

        @Override // com.google.common.math.LinearTransformation
        public LinearTransformation inverse() {
            LinearTransformation linearTransformation = this.inverse;
            if (linearTransformation != null) {
                return linearTransformation;
            }
            LinearTransformation linearTransformationCreateInverse = createInverse();
            this.inverse = linearTransformationCreateInverse;
            return linearTransformationCreateInverse;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isHorizontal() {
            return this.slope == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isVertical() {
            return false;
        }

        @Override // com.google.common.math.LinearTransformation
        public double slope() {
            return this.slope;
        }

        public String toString() {
            return String.format("y = %g * x + %g", Double.valueOf(this.slope), Double.valueOf(this.yIntercept));
        }

        @Override // com.google.common.math.LinearTransformation
        public double transform(double d2) {
            return (d2 * this.slope) + this.yIntercept;
        }

        public RegularLinearTransformation(double d2, double d3, LinearTransformation linearTransformation) {
            this.slope = d2;
            this.yIntercept = d3;
            this.inverse = linearTransformation;
        }
    }
}
