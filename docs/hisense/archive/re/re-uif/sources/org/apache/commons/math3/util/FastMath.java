package org.apache.commons.math3.util;

/* JADX INFO: loaded from: classes.dex */
public class FastMath {
    private static final double[] COSINE_TABLE_A;
    private static final double[] COSINE_TABLE_B;
    private static final double[] EIGHTHS;
    private static final double[][] LN_HI_PREC_COEF;
    private static final double[][] LN_QUICK_COEF;
    private static final long[] PI_O_4_BITS;
    private static final long[] RECIP_2PI;
    private static final double[] SINE_TABLE_A;
    private static final double[] SINE_TABLE_B;

    public static double floor(double d) {
        if (d != d || d >= 4.503599627370496E15d || d <= -4.503599627370496E15d) {
            return d;
        }
        long j = (long) d;
        if (d < 0.0d && j != d) {
            j--;
        }
        return j == 0 ? d * j : j;
    }

    public static long min(long j, long j2) {
        return j <= j2 ? j : j2;
    }

    private static double polyCosine(double d) {
        double d2 = d * d;
        return ((((((2.479773539153719E-5d * d2) - 0.0013888888689039883d) * d2) + 0.041666666666621166d) * d2) - 0.49999999999999994d) * d2;
    }

    private static double polySine(double d) {
        double d2 = d * d;
        return ((((((2.7553817452272217E-6d * d2) - 1.9841269659586505E-4d) * d2) + 0.008333333333329196d) * d2) - 0.16666666666666666d) * d2 * d;
    }

    static {
        StrictMath.log(Double.MAX_VALUE);
        LN_QUICK_COEF = new double[][]{new double[]{1.0d, 5.669184079525E-24d}, new double[]{-0.25d, -0.25d}, new double[]{0.3333333134651184d, 1.986821492305628E-8d}, new double[]{-0.25d, -6.663542893624021E-14d}, new double[]{0.19999998807907104d, 1.1921056801463227E-8d}, new double[]{-0.1666666567325592d, -7.800414592973399E-9d}, new double[]{0.1428571343421936d, 5.650007086920087E-9d}, new double[]{-0.12502530217170715d, -7.44321345601866E-11d}, new double[]{0.11113807559013367d, 9.219544613762692E-9d}};
        LN_HI_PREC_COEF = new double[][]{new double[]{1.0d, -6.032174644509064E-23d}, new double[]{-0.25d, -0.25d}, new double[]{0.3333333134651184d, 1.9868161777724352E-8d}, new double[]{-0.2499999701976776d, -2.957007209750105E-8d}, new double[]{0.19999954104423523d, 1.5830993332061267E-10d}, new double[]{-0.16624879837036133d, -2.6033824355191673E-8d}};
        SINE_TABLE_A = new double[]{0.0d, 0.1246747374534607d, 0.24740394949913025d, 0.366272509098053d, 0.4794255495071411d, 0.5850973129272461d, 0.6816387176513672d, 0.7675435543060303d, 0.8414709568023682d, 0.902267575263977d, 0.9489846229553223d, 0.9808930158615112d, 0.9974949359893799d, 0.9985313415527344d};
        SINE_TABLE_B = new double[]{0.0d, -4.068233003401932E-9d, 9.755392680573412E-9d, 1.9987994582857286E-8d, -1.0902938113007961E-8d, -3.9986783938944604E-8d, 4.23719669792332E-8d, -5.207000323380292E-8d, 2.800552834259E-8d, 1.883511811213715E-8d, -3.5997360512765566E-9d, 4.116164446561962E-8d, 5.0614674548127384E-8d, -1.0129027912496858E-9d};
        COSINE_TABLE_A = new double[]{1.0d, 0.9921976327896118d, 0.9689123630523682d, 0.9305076599121094d, 0.8775825500488281d, 0.8109631538391113d, 0.7316888570785522d, 0.6409968137741089d, 0.5403022766113281d, 0.4311765432357788d, 0.3153223395347595d, 0.19454771280288696d, 0.07073719799518585d, -0.05417713522911072d};
        COSINE_TABLE_B = new double[]{0.0d, 3.4439717236742845E-8d, 5.865827662008209E-8d, -3.7999795083850525E-8d, 1.184154459111628E-8d, -3.43338934259355E-8d, 1.1795268640216787E-8d, 4.438921624363781E-8d, 2.925681159240093E-8d, -2.6437112632041807E-8d, 2.2860509143963117E-8d, -4.813899778443457E-9d, 3.6725170580355583E-9d, 2.0217439756338078E-10d};
        RECIP_2PI = new long[]{2935890503282001226L, 9154082963658192752L, 3952090531849364496L, 9193070505571053912L, 7910884519577875640L, 113236205062349959L, 4577762542105553359L, -5034868814120038111L, 4208363204685324176L, 5648769086999809661L, 2819561105158720014L, -4035746434778044925L, -302932621132653753L, -2644281811660520851L, -3183605296591799669L, 6722166367014452318L, -3512299194304650054L, -7278142539171889152L};
        PI_O_4_BITS = new long[]{-3958705157555305932L, -4267615245585081135L};
        EIGHTHS = new double[]{0.0d, 0.125d, 0.25d, 0.375d, 0.5d, 0.625d, 0.75d, 0.875d, 1.0d, 1.125d, 1.25d, 1.375d, 1.5d, 1.625d};
    }

    public static double log(double d) {
        return log(d, null);
    }

    private static double log(double d, double[] dArr) {
        double d2;
        double d3;
        if (d == 0.0d) {
            return Double.NEGATIVE_INFINITY;
        }
        long jDoubleToRawLongBits = Double.doubleToRawLongBits(d);
        if (((Long.MIN_VALUE & jDoubleToRawLongBits) != 0 || d != d) && d != 0.0d) {
            if (dArr != null) {
                dArr[0] = Double.NaN;
            }
            return Double.NaN;
        }
        if (d == Double.POSITIVE_INFINITY) {
            if (dArr != null) {
                dArr[0] = Double.POSITIVE_INFINITY;
            }
            return Double.POSITIVE_INFINITY;
        }
        int i = ((int) (jDoubleToRawLongBits >> 52)) - 1023;
        if ((9218868437227405312L & jDoubleToRawLongBits) == 0) {
            if (d == 0.0d) {
                if (dArr != null) {
                    dArr[0] = Double.NEGATIVE_INFINITY;
                }
                return Double.NEGATIVE_INFINITY;
            }
            jDoubleToRawLongBits <<= 1;
            while ((4503599627370496L & jDoubleToRawLongBits) == 0) {
                i--;
                jDoubleToRawLongBits <<= 1;
            }
        }
        if ((i == -1 || i == 0) && d < 1.01d && d > 0.99d && dArr == null) {
            double d4 = d - 1.0d;
            double d5 = d4 * 1.073741824E9d;
            double d6 = (d4 + d5) - d5;
            double d7 = d4 - d6;
            double[][] dArr2 = LN_QUICK_COEF;
            double[] dArr3 = dArr2[dArr2.length - 1];
            double d8 = dArr3[0];
            double d9 = dArr3[1];
            for (int length = dArr2.length - 2; length >= 0; length--) {
                double d10 = d8 * d6;
                double d11 = (d8 * d7) + (d9 * d6) + (d9 * d7);
                double d12 = d10 * 1.073741824E9d;
                double d13 = (d10 + d12) - d12;
                double d14 = (d10 - d13) + d11;
                double[] dArr4 = LN_QUICK_COEF[length];
                double d15 = d13 + dArr4[0];
                double d16 = d15 * 1.073741824E9d;
                d8 = (d15 + d16) - d16;
                d9 = (d15 - d8) + d14 + dArr4[1];
            }
            double d17 = d8 * d6;
            double d18 = (d8 * d7) + (d6 * d9) + (d9 * d7);
            double d19 = 1.073741824E9d * d17;
            double d20 = (d17 + d19) - d19;
            return d20 + (d17 - d20) + d18;
        }
        long j = 4499201580859392L & jDoubleToRawLongBits;
        double[] dArr5 = lnMant.LN_MANT[(int) (j >> 42)];
        double d21 = jDoubleToRawLongBits & 4398046511103L;
        double d22 = j + 4.503599627370496E15d;
        double d23 = d21 / d22;
        if (dArr != null) {
            double d24 = d23 * 1.073741824E9d;
            double d25 = (d23 + d24) - d24;
            double d26 = d23 - d25;
            double d27 = d26 + (((d21 - (d25 * d22)) - (d26 * d22)) / d22);
            double[][] dArr6 = LN_HI_PREC_COEF;
            double[] dArr7 = dArr6[dArr6.length - 1];
            double d28 = dArr7[0];
            double d29 = dArr7[1];
            for (int length2 = dArr6.length - 2; length2 >= 0; length2--) {
                double d30 = d28 * d25;
                double d31 = (d28 * d27) + (d29 * d25) + (d29 * d27);
                double d32 = d30 * 1.073741824E9d;
                double d33 = (d30 + d32) - d32;
                double d34 = (d30 - d33) + d31;
                double[] dArr8 = LN_HI_PREC_COEF[length2];
                double d35 = d33 + dArr8[0];
                double d36 = d34 + dArr8[1];
                double d37 = d35 * 1.073741824E9d;
                d28 = (d35 + d37) - d37;
                d29 = (d35 - d28) + d36;
            }
            double d38 = d28 * d25;
            double d39 = (d28 * d27) + (d25 * d29) + (d29 * d27);
            d2 = d38 + d39;
            d3 = -((d2 - d38) - d39);
        } else {
            d2 = (((((((((((-0.16624882440418567d) * d23) + 0.19999954120254515d) * d23) - 0.2499999997677497d) * d23) + 0.3333333333332802d) * d23) - 0.5d) * d23) + 1.0d) * d23;
            d3 = 0.0d;
        }
        double d40 = i;
        double d41 = 0.6931470632553101d * d40;
        double d42 = dArr5[0] + d41;
        double d43 = d42 + d2;
        double d44 = (-((d42 - d41) - dArr5[0])) + 0.0d + (-((d43 - d42) - d2));
        double d45 = d40 * 1.1730463525082348E-7d;
        double d46 = d43 + d45;
        double d47 = d44 + (-((d46 - d43) - d45));
        double d48 = dArr5[1] + d46;
        double d49 = d47 + (-((d48 - d46) - dArr5[1]));
        double d50 = d48 + d3;
        double d51 = d49 + (-((d50 - d48) - d3));
        if (dArr != null) {
            dArr[0] = d50;
            dArr[1] = d51;
        }
        return d50 + d51;
    }

    private static double sinQ(double d, double d2) {
        int i = (int) ((8.0d * d) + 0.5d);
        double d3 = d - EIGHTHS[i];
        double d4 = SINE_TABLE_A[i];
        double d5 = SINE_TABLE_B[i];
        double d6 = COSINE_TABLE_A[i];
        double d7 = COSINE_TABLE_B[i];
        double dPolySine = polySine(d3);
        double dPolyCosine = polyCosine(d3);
        double d8 = 1.073741824E9d * d3;
        double d9 = (d3 + d8) - d8;
        double d10 = dPolySine + (d3 - d9);
        double d11 = d4 + 0.0d;
        double d12 = d6 * d9;
        double d13 = d11 + d12;
        double d14 = (-((d11 - 0.0d) - d4)) + 0.0d + (-((d13 - d11) - d12)) + (d4 * dPolyCosine) + (d6 * d10) + d5 + (d7 * d9) + (d5 * dPolyCosine) + (d7 * d10);
        if (d2 != 0.0d) {
            double d15 = (((d6 + d7) * (dPolyCosine + 1.0d)) - ((d4 + d5) * (d9 + d10))) * d2;
            double d16 = d13 + d15;
            d14 += -((d16 - d13) - d15);
            d13 = d16;
        }
        return d13 + d14;
    }

    private static double cosQ(double d, double d2) {
        double d3 = 1.5707963267948966d - d;
        return sinQ(d3, (-((d3 - 1.5707963267948966d) + d)) + (6.123233995736766E-17d - d2));
    }

    private static void reducePayneHanek(double d, double[] dArr) {
        long j;
        long j2;
        long j3;
        long jDoubleToRawLongBits = Double.doubleToRawLongBits(d);
        int i = (((int) ((jDoubleToRawLongBits >> 52) & 2047)) - 1023) + 1;
        long j4 = ((jDoubleToRawLongBits & 4503599627370495L) | 4503599627370496L) << 11;
        int i2 = i >> 6;
        int i3 = i - (i2 << 6);
        if (i3 != 0) {
            long j5 = i2 == 0 ? 0L : RECIP_2PI[i2 - 1] << i3;
            long[] jArr = RECIP_2PI;
            int i4 = 64 - i3;
            j = j5 | (jArr[i2] >>> i4);
            int i5 = i2 + 1;
            j2 = (jArr[i2] << i3) | (jArr[i5] >>> i4);
            j3 = (jArr[i5] << i3) | (jArr[i2 + 2] >>> i4);
        } else {
            j = i2 == 0 ? 0L : RECIP_2PI[i2 - 1];
            long[] jArr2 = RECIP_2PI;
            j2 = jArr2[i2];
            j3 = jArr2[i2 + 1];
        }
        long j6 = j4 >>> 32;
        long j7 = j4 & 4294967295L;
        long j8 = j2 >>> 32;
        long j9 = j2 & 4294967295L;
        long j10 = j6 * j8;
        long j11 = j7 * j9;
        long j12 = j8 * j7;
        long j13 = j9 * j6;
        long j14 = j11 + (j13 << 32);
        long j15 = j10 + (j13 >>> 32);
        boolean z = (j11 & Long.MIN_VALUE) != 0;
        boolean z2 = (j13 & 2147483648L) != 0;
        long j16 = j14 & Long.MIN_VALUE;
        boolean z3 = j16 != 0;
        if ((z && z2) || ((z || z2) && !z3)) {
            j15++;
        }
        boolean z4 = j16 != 0;
        boolean z5 = (j12 & 2147483648L) != 0;
        long j17 = j14 + (j12 << 32);
        long j18 = j15 + (j12 >>> 32);
        long j19 = j17 & Long.MIN_VALUE;
        boolean z6 = j19 != 0;
        if ((z4 && z5) || ((z4 || z5) && !z6)) {
            j18++;
        }
        long j20 = j3 >>> 32;
        long j21 = (j6 * j20) + (((j20 * j7) + ((j3 & 4294967295L) * j6)) >>> 32);
        boolean z7 = j19 != 0;
        boolean z8 = (j21 & Long.MIN_VALUE) != 0;
        long j22 = j17 + j21;
        boolean z9 = (j22 & Long.MIN_VALUE) != 0;
        if ((z7 && z8) || ((z7 || z8) && !z9)) {
            j18++;
        }
        long j23 = j >>> 32;
        long j24 = j & 4294967295L;
        long j25 = j18 + (j7 * j24) + (((j7 * j23) + (j6 * j24)) << 32);
        int i6 = (int) (j25 >>> 62);
        long j26 = (j25 << 2) | (j22 >>> 62);
        long j27 = j22 << 2;
        long j28 = j26 >>> 32;
        long j29 = j26 & 4294967295L;
        long[] jArr3 = PI_O_4_BITS;
        long j30 = jArr3[0] >>> 32;
        long j31 = jArr3[0] & 4294967295L;
        long j32 = j28 * j30;
        long j33 = j29 * j31;
        long j34 = j30 * j29;
        long j35 = j31 * j28;
        long j36 = j33 + (j35 << 32);
        long j37 = j32 + (j35 >>> 32);
        boolean z10 = (j33 & Long.MIN_VALUE) != 0;
        boolean z11 = (j35 & 2147483648L) != 0;
        long j38 = j36 & Long.MIN_VALUE;
        boolean z12 = j38 != 0;
        if ((z10 && z11) || ((z10 || z11) && !z12)) {
            j37++;
        }
        boolean z13 = j38 != 0;
        boolean z14 = (j34 & 2147483648L) != 0;
        long j39 = j36 + (j34 << 32);
        long j40 = j37 + (j34 >>> 32);
        long j41 = j39 & Long.MIN_VALUE;
        boolean z15 = j41 != 0;
        if ((z13 && z14) || ((z13 || z14) && !z15)) {
            j40++;
        }
        long[] jArr4 = PI_O_4_BITS;
        long j42 = jArr4[1] >>> 32;
        long j43 = (j28 * j42) + (((j29 * j42) + (j28 * (jArr4[1] & 4294967295L))) >>> 32);
        boolean z16 = j41 != 0;
        boolean z17 = (j43 & Long.MIN_VALUE) != 0;
        long j44 = j39 + j43;
        long j45 = j44 & Long.MIN_VALUE;
        boolean z18 = j45 != 0;
        if ((z16 && z17) || ((z16 || z17) && !z18)) {
            j40++;
        }
        long j46 = j27 >>> 32;
        long[] jArr5 = PI_O_4_BITS;
        long j47 = jArr5[0] >>> 32;
        long j48 = (j46 * j47) + ((((j27 & 4294967295L) * j47) + (j46 * (jArr5[0] & 4294967295L))) >>> 32);
        boolean z19 = j45 != 0;
        boolean z20 = (j48 & Long.MIN_VALUE) != 0;
        long j49 = j44 + j48;
        boolean z21 = (j49 & Long.MIN_VALUE) != 0;
        if ((z19 && z20) || ((z19 || z20) && !z21)) {
            j40++;
        }
        double d2 = (j40 >>> 12) / 4.503599627370496E15d;
        double d3 = ((((j40 & 4095) << 40) + (j49 >>> 24)) / 4.503599627370496E15d) / 4.503599627370496E15d;
        double d4 = d2 + d3;
        dArr[0] = i6;
        dArr[1] = d4 * 2.0d;
        dArr[2] = (-((d4 - d2) - d3)) * 2.0d;
    }

    public static double sin(double d) {
        double remA;
        boolean z;
        double dSinQ;
        double remB = 0.0d;
        int k = 0;
        if (d < 0.0d) {
            remA = -d;
            z = true;
        } else {
            remA = d;
            z = false;
        }
        if (remA == 0.0d) {
            return Double.doubleToRawLongBits(d) < 0 ? -0.0d : 0.0d;
        }
        if (remA != remA || remA == Double.POSITIVE_INFINITY) {
            return Double.NaN;
        }
        if (remA > 3294198.0d) {
            double[] dArr = new double[3];
            reducePayneHanek(remA, dArr);
            k = ((int) dArr[0]) & 3;
            remA = dArr[1];
            remB = dArr[2];
        } else if (remA > 1.5707963267948966d) {
            CodyWaite codyWaite = new CodyWaite(remA);
            k = codyWaite.getK() & 3;
            remA = codyWaite.getRemA();
            remB = codyWaite.getRemB();
        }
        if (z) {
            k ^= 2;
        }
        if (k == 0) {
            return sinQ(remA, remB);
        }
        if (k == 1) {
            return cosQ(remA, remB);
        }
        if (k == 2) {
            dSinQ = sinQ(remA, remB);
        } else {
            if (k != 3) {
                return Double.NaN;
            }
            dSinQ = cosQ(remA, remB);
        }
        return -dSinQ;
    }

    public static double cos(double d) {
        double dSinQ;
        double remB = 0.0d;
        if (d < 0.0d) {
            d = -d;
        }
        if (d != d || d == Double.POSITIVE_INFINITY) {
            return Double.NaN;
        }
        int k = 0;
        if (d > 3294198.0d) {
            double[] dArr = new double[3];
            reducePayneHanek(d, dArr);
            k = ((int) dArr[0]) & 3;
            d = dArr[1];
            remB = dArr[2];
        } else if (d > 1.5707963267948966d) {
            CodyWaite codyWaite = new CodyWaite(d);
            k = codyWaite.getK() & 3;
            d = codyWaite.getRemA();
            remB = codyWaite.getRemB();
        }
        if (k == 0) {
            return cosQ(d, remB);
        }
        if (k == 1) {
            dSinQ = sinQ(d, remB);
        } else {
            if (k != 2) {
                if (k != 3) {
                    return Double.NaN;
                }
                return sinQ(d, remB);
            }
            dSinQ = cosQ(d, remB);
        }
        return -dSinQ;
    }

    public static double ceil(double d) {
        if (d != d) {
            return d;
        }
        double dFloor = floor(d);
        if (dFloor == d) {
            return dFloor;
        }
        double d2 = dFloor + 1.0d;
        return d2 == 0.0d ? d * d2 : d2;
    }

    private static class lnMant {
        private static final double[][] LN_MANT = FastMathLiteralArrays.loadLnMant();
    }

    private static class CodyWaite {
        private final int finalK;
        private final double finalRemA;
        private final double finalRemB;

        CodyWaite(double d) {
            int i = (int) (0.6366197723675814d * d);
            while (true) {
                double d2 = -i;
                double d3 = 1.570796251296997d * d2;
                double d4 = d + d3;
                double d5 = 7.549789948768648E-8d * d2;
                double d6 = d5 + d4;
                double d7 = (-((d4 - d) - d3)) + (-((d6 - d4) - d5));
                double d8 = d2 * 6.123233995736766E-17d;
                double d9 = d8 + d6;
                double d10 = d7 + (-((d9 - d6) - d8));
                if (d9 > 0.0d) {
                    this.finalK = i;
                    this.finalRemA = d9;
                    this.finalRemB = d10;
                    return;
                }
                i--;
            }
        }

        int getK() {
            return this.finalK;
        }

        double getRemA() {
            return this.finalRemA;
        }

        double getRemB() {
            return this.finalRemB;
        }
    }
}
