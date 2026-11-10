package com.google.common.math;

import com.google.common.annotations.GwtIncompatible;
import java.math.BigDecimal;
import java.math.RoundingMode;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public class BigDecimalMath {

    private static class BigDecimalToDoubleRounder extends ToDoubleRounder<BigDecimal> {
        public static final BigDecimalToDoubleRounder INSTANCE = new BigDecimalToDoubleRounder();

        @Override // com.google.common.math.ToDoubleRounder
        public BigDecimal minus(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
            return bigDecimal.subtract(bigDecimal2);
        }

        @Override // com.google.common.math.ToDoubleRounder
        public double roundToDoubleArbitrarily(BigDecimal bigDecimal) {
            return bigDecimal.doubleValue();
        }

        @Override // com.google.common.math.ToDoubleRounder
        public int sign(BigDecimal bigDecimal) {
            return bigDecimal.signum();
        }

        @Override // com.google.common.math.ToDoubleRounder
        public BigDecimal toX(double d2, RoundingMode roundingMode) {
            return new BigDecimal(d2);
        }
    }

    public static double roundToDouble(BigDecimal bigDecimal, RoundingMode roundingMode) {
        return BigDecimalToDoubleRounder.INSTANCE.roundToDouble(bigDecimal, roundingMode);
    }
}
