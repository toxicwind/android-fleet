package org.jtransforms.fft;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.util.FastMath;
import org.jtransforms.utils.CommonUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.DoubleLargeArray;
import pl.edu.icm.jlargearrays.LargeArray;
import pl.edu.icm.jlargearrays.LargeArrayUtils;
import pl.edu.icm.jlargearrays.LongLargeArray;

/* JADX INFO: loaded from: classes.dex */
public final class DoubleFFT_1D {
    private static final int[] factors = {4, 2, 3, 5};
    private double[] bk1;
    private DoubleLargeArray bk1l;
    private double[] bk2;
    private DoubleLargeArray bk2l;
    private int[] ip;
    private LongLargeArray ipl;
    private int n;
    private int nBluestein;
    private long nBluesteinl;
    private int nc;
    private long ncl;
    private long nl;
    private int nw;
    private long nwl;
    private Plans plan;
    private boolean useLargeArrays;
    private double[] w;
    private DoubleLargeArray wl;
    private double[] wtable;
    private double[] wtable_r;
    private DoubleLargeArray wtable_rl;
    private DoubleLargeArray wtablel;

    private enum Plans {
        SPLIT_RADIX,
        MIXED_RADIX,
        BLUESTEIN
    }

    public DoubleFFT_1D(long j) {
        int[] iArr = factors;
        if (j < 1) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        boolean z = CommonUtils.isUseLargeArrays() || j * 2 > ((long) LargeArray.getMaxSizeOf32bitArray());
        this.useLargeArrays = z;
        this.n = (int) j;
        this.nl = j;
        if (!z) {
            if (CommonUtils.isPowerOf2(j)) {
                this.plan = Plans.SPLIT_RADIX;
                int[] iArr2 = new int[((int) FastMath.ceil((1 << (((int) (FastMath.log(j + 0.5d) / FastMath.log(2.0d))) / 2)) + 2)) + 2];
                this.ip = iArr2;
                int i = this.n;
                double[] dArr = new double[i];
                this.w = dArr;
                int i2 = (i * 2) >> 2;
                this.nw = i2;
                CommonUtils.makewt(i2, iArr2, dArr);
                int i3 = this.n >> 2;
                this.nc = i3;
                CommonUtils.makect(i3, this.w, this.nw, this.ip);
                return;
            }
            if (CommonUtils.getReminder(j, iArr) >= 211) {
                this.plan = Plans.BLUESTEIN;
                int iNextPow2 = CommonUtils.nextPow2((this.n * 2) - 1);
                this.nBluestein = iNextPow2;
                this.bk1 = new double[iNextPow2 * 2];
                this.bk2 = new double[iNextPow2 * 2];
                int[] iArr3 = new int[((int) FastMath.ceil((1 << (((int) (FastMath.log(((double) iNextPow2) + 0.5d) / FastMath.log(2.0d))) / 2)) + 2)) + 2];
                this.ip = iArr3;
                int i4 = this.nBluestein;
                double[] dArr2 = new double[i4];
                this.w = dArr2;
                int i5 = (i4 * 2) >> 2;
                this.nw = i5;
                CommonUtils.makewt(i5, iArr3, dArr2);
                int i6 = this.nBluestein >> 2;
                this.nc = i6;
                CommonUtils.makect(i6, this.w, this.nw, this.ip);
                bluesteini();
                return;
            }
            this.plan = Plans.MIXED_RADIX;
            int i7 = this.n;
            this.wtable = new double[(i7 * 4) + 15];
            this.wtable_r = new double[(i7 * 2) + 15];
            cffti();
            rffti();
            return;
        }
        if (CommonUtils.isPowerOf2(j)) {
            this.plan = Plans.SPLIT_RADIX;
            this.ipl = new LongLargeArray(((long) FastMath.ceil((1 << ((int) (((long) (FastMath.log(this.nl + 0.5d) / FastMath.log(2.0d))) / 2))) + 2)) + 2);
            DoubleLargeArray doubleLargeArray = new DoubleLargeArray(this.nl);
            this.wl = doubleLargeArray;
            long j2 = (this.nl * 2) >> 2;
            this.nwl = j2;
            CommonUtils.makewt(j2, this.ipl, doubleLargeArray);
            long j3 = this.nl >> 2;
            this.ncl = j3;
            CommonUtils.makect(j3, this.wl, this.nwl, this.ipl);
            return;
        }
        if (CommonUtils.getReminder(this.nl, iArr) >= 211) {
            this.plan = Plans.BLUESTEIN;
            this.nBluesteinl = CommonUtils.nextPow2((this.nl * 2) - 1);
            this.bk1l = new DoubleLargeArray(this.nBluesteinl * 2);
            this.bk2l = new DoubleLargeArray(this.nBluesteinl * 2);
            this.ipl = new LongLargeArray(((long) FastMath.ceil((1 << ((int) (((long) (FastMath.log(this.nBluesteinl + 0.5d) / FastMath.log(2.0d))) / 2))) + 2)) + 2);
            DoubleLargeArray doubleLargeArray2 = new DoubleLargeArray(this.nBluesteinl);
            this.wl = doubleLargeArray2;
            long j4 = (this.nBluesteinl * 2) >> 2;
            this.nwl = j4;
            CommonUtils.makewt(j4, this.ipl, doubleLargeArray2);
            long j5 = this.nBluesteinl >> 2;
            this.ncl = j5;
            CommonUtils.makect(j5, this.wl, this.nwl, this.ipl);
            bluesteinil();
            return;
        }
        this.plan = Plans.MIXED_RADIX;
        this.wtablel = new DoubleLargeArray((this.nl * 4) + 15);
        this.wtable_rl = new DoubleLargeArray((this.nl * 2) + 15);
        cfftil();
        rfftil();
    }

    public void complexForward(double[] dArr) {
        complexForward(dArr, 0);
    }

    public void complexForward(double[] dArr, int i) {
        if (this.useLargeArrays) {
            complexForward(new DoubleLargeArray(dArr), i);
            return;
        }
        if (this.n == 1) {
            return;
        }
        int i2 = AnonymousClass29.$SwitchMap$org$jtransforms$fft$DoubleFFT_1D$Plans[this.plan.ordinal()];
        if (i2 == 1) {
            CommonUtils.cftbsub(this.n * 2, dArr, i, this.ip, this.nw, this.w);
        } else if (i2 == 2) {
            cfftf(dArr, i, -1);
        } else {
            if (i2 != 3) {
                return;
            }
            bluestein_complex(dArr, i, -1);
        }
    }

    /* JADX INFO: renamed from: org.jtransforms.fft.DoubleFFT_1D$29, reason: invalid class name */
    static /* synthetic */ class AnonymousClass29 {
        static final /* synthetic */ int[] $SwitchMap$org$jtransforms$fft$DoubleFFT_1D$Plans;

        static {
            int[] iArr = new int[Plans.values().length];
            $SwitchMap$org$jtransforms$fft$DoubleFFT_1D$Plans = iArr;
            try {
                iArr[Plans.SPLIT_RADIX.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jtransforms$fft$DoubleFFT_1D$Plans[Plans.MIXED_RADIX.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jtransforms$fft$DoubleFFT_1D$Plans[Plans.BLUESTEIN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public void complexForward(DoubleLargeArray doubleLargeArray, long j) {
        if (!this.useLargeArrays) {
            if (!doubleLargeArray.isLarge() && !doubleLargeArray.isConstant() && j < 2147483647L) {
                complexForward(doubleLargeArray.getData(), (int) j);
                return;
            }
            throw new IllegalArgumentException("The data array is too big.");
        }
        if (this.nl == 1) {
            return;
        }
        int i = AnonymousClass29.$SwitchMap$org$jtransforms$fft$DoubleFFT_1D$Plans[this.plan.ordinal()];
        if (i == 1) {
            CommonUtils.cftbsub(2 * this.nl, doubleLargeArray, j, this.ipl, this.nwl, this.wl);
        } else if (i == 2) {
            cfftf(doubleLargeArray, j, -1);
        } else {
            if (i != 3) {
                return;
            }
            bluestein_complex(doubleLargeArray, j, -1);
        }
    }

    final void cffti() {
        int i;
        int i2 = this.n;
        if (i2 == 1) {
            return;
        }
        int i3 = i2 * 2;
        int i4 = i2 * 4;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        loop0: while (true) {
            i5++;
            i = 2;
            i6 = i5 <= 4 ? factors[i5 - 1] : i6 + 2;
            while (true) {
                int i8 = i2 / i6;
                if (i2 - (i6 * i8) != 0) {
                    break;
                }
                i7++;
                this.wtable[i7 + 1 + i4] = i6;
                if (i6 == 2 && i7 != 1) {
                    for (int i9 = 2; i9 <= i7; i9++) {
                        int i10 = (i7 - i9) + 2 + i4;
                        double[] dArr = this.wtable;
                        dArr[i10 + 1] = dArr[i10];
                    }
                    this.wtable[i4 + 2] = 2.0d;
                }
                if (i8 == 1) {
                    break loop0;
                } else {
                    i2 = i8;
                }
            }
        }
        double[] dArr2 = this.wtable;
        int i11 = this.n;
        dArr2[i4] = i11;
        dArr2[i4 + 1] = i7;
        double d = 6.283185307179586d / ((double) i11);
        int i12 = 1;
        int i13 = 1;
        int i14 = 1;
        while (i12 <= i7) {
            i12++;
            int i15 = (int) this.wtable[i12 + i4];
            int i16 = i13 * i15;
            int i17 = this.n / i16;
            int i18 = i17 + i17 + i;
            int i19 = i15 - 1;
            int i20 = 1;
            int i21 = 0;
            while (i20 <= i19) {
                double[] dArr3 = this.wtable;
                dArr3[(i14 - 1) + i3] = 1.0d;
                int i22 = i14 + i3;
                double d2 = 0.0d;
                dArr3[i22] = 0.0d;
                int i23 = i21 + i13;
                int i24 = i12;
                int i25 = i19;
                double d3 = ((double) i23) * d;
                int i26 = 4;
                while (i26 <= i18) {
                    i14 += 2;
                    d2 += 1.0d;
                    double d4 = d2 * d3;
                    int i27 = i14 + i3;
                    this.wtable[i27 - 1] = FastMath.cos(d4);
                    this.wtable[i27] = FastMath.sin(d4);
                    i26 += 2;
                    d3 = d3;
                }
                if (i15 > 5) {
                    int i28 = i14 + i3;
                    double[] dArr4 = this.wtable;
                    dArr4[i22 - 1] = dArr4[i28 - 1];
                    dArr4[i22] = dArr4[i28];
                }
                i20++;
                i12 = i24;
                i19 = i25;
                i = 2;
                i21 = i23;
            }
            i13 = i16;
        }
    }

    final void cfftil() {
        long j;
        long j2;
        long j3;
        long j4 = this.nl;
        long j5 = 1;
        if (j4 == 1) {
            return;
        }
        long j6 = 2;
        long j7 = j4 * 2;
        long j8 = 4;
        long j9 = j4 * 4;
        long j10 = 0;
        long j11 = 0;
        long j12 = 0;
        long j13 = 0;
        loop0: while (true) {
            j11 += j5;
            long j14 = j11 <= j8 ? factors[(int) (j11 - j5)] : j12 + j6;
            while (true) {
                long j15 = j4 / j14;
                if (j4 - (j14 * j15) != j10) {
                    break;
                }
                j = j13 + j5;
                long j16 = j11;
                this.wtablel.setDouble(j + j5 + j9, j14);
                j2 = 1;
                if (j14 == j6 && j != 1) {
                    long j17 = j6;
                    while (j17 <= j) {
                        long j18 = (j - j17) + j6 + j9;
                        DoubleLargeArray doubleLargeArray = this.wtablel;
                        doubleLargeArray.setDouble(j18 + 1, doubleLargeArray.getDouble(j18));
                        j17++;
                        j6 = 2;
                    }
                    this.wtablel.setDouble(j9 + 2, 2.0d);
                }
                if (j15 == 1) {
                    break loop0;
                }
                j5 = 1;
                j4 = j15;
                j11 = j16;
                j6 = 2;
                j13 = j;
                j10 = 0;
            }
            j12 = j14;
            j8 = 4;
        }
        this.wtablel.setDouble(j9, this.nl);
        this.wtablel.setDouble(j9 + 1, j);
        double d = 6.283185307179586d / this.nl;
        long j19 = 1;
        long j20 = 1;
        long j21 = 1;
        while (j19 <= j) {
            long j22 = j19 + j2;
            long j23 = j;
            long j24 = (long) this.wtablel.getDouble(j22 + j9);
            long j25 = j21 * j24;
            long j26 = this.nl / j25;
            long j27 = j26 + j26 + 2;
            long j28 = j24 - j2;
            long j29 = j2;
            long j30 = 0;
            while (j29 <= j28) {
                long j31 = j9;
                long j32 = j24;
                long j33 = j27;
                this.wtablel.setDouble((j20 - j2) + j7, 1.0d);
                long j34 = j20 + j7;
                this.wtablel.setDouble(j34, 0.0d);
                long j35 = j30 + j21;
                double d2 = j35 * d;
                double d3 = 0.0d;
                long j36 = 4;
                while (j36 <= j33) {
                    long j37 = j20 + 2;
                    d3 += 1.0d;
                    double d4 = d3 * d2;
                    long j38 = j35;
                    long j39 = j37 + j7;
                    this.wtablel.setDouble(j39 - 1, FastMath.cos(d4));
                    this.wtablel.setDouble(j39, FastMath.sin(d4));
                    j36 += 2;
                    j35 = j38;
                    d = d;
                    d2 = d2;
                    j20 = j37;
                }
                long j40 = j35;
                double d5 = d;
                if (j32 > 5) {
                    long j41 = j20 + j7;
                    DoubleLargeArray doubleLargeArray2 = this.wtablel;
                    j3 = 1;
                    doubleLargeArray2.setDouble(j34 - 1, doubleLargeArray2.getDouble(j41 - 1));
                    DoubleLargeArray doubleLargeArray3 = this.wtablel;
                    doubleLargeArray3.setDouble(j34, doubleLargeArray3.getDouble(j41));
                } else {
                    j3 = 1;
                }
                j29 += j3;
                j7 = j7;
                j20 = j20;
                j9 = j31;
                j24 = j32;
                j30 = j40;
                j2 = j3;
                j27 = j33;
                d = d5;
            }
            j21 = j25;
            j19 = j22;
            j = j23;
            d = d;
        }
    }

    void rffti() {
        int i = this.n;
        int i2 = 1;
        if (i == 1) {
            return;
        }
        int i3 = i * 2;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        loop0: while (true) {
            i4++;
            i5 = i4 <= 4 ? factors[i4 - 1] : i5 + 2;
            while (true) {
                int i7 = i / i5;
                if (i - (i5 * i7) != 0) {
                    break;
                }
                i6++;
                this.wtable_r[i6 + 1 + i3] = i5;
                if (i5 == 2 && i6 != 1) {
                    for (int i8 = 2; i8 <= i6; i8++) {
                        int i9 = (i6 - i8) + 2 + i3;
                        double[] dArr = this.wtable_r;
                        dArr[i9 + 1] = dArr[i9];
                    }
                    this.wtable_r[i3 + 2] = 2.0d;
                }
                if (i7 == 1) {
                    break loop0;
                } else {
                    i = i7;
                }
            }
        }
        double[] dArr2 = this.wtable_r;
        int i10 = this.n;
        dArr2[i3] = i10;
        dArr2[i3 + 1] = i6;
        double d = 6.283185307179586d / ((double) i10);
        int i11 = i6 - 1;
        if (i11 == 0) {
            return;
        }
        int i12 = 1;
        int i13 = 1;
        int i14 = 0;
        while (i12 <= i11) {
            i12++;
            int i15 = (int) this.wtable_r[i12 + i3];
            int i16 = i13 * i15;
            int i17 = this.n / i16;
            int i18 = i15 - i2;
            int i19 = i2;
            int i20 = 0;
            while (i19 <= i18) {
                i20 += i13;
                int i21 = i13;
                double d2 = ((double) i20) * d;
                double d3 = 0.0d;
                int i22 = i14;
                int i23 = 3;
                while (i23 <= i17) {
                    i22 += 2;
                    d3 += 1.0d;
                    double d4 = d3 * d2;
                    int i24 = i22 + this.n;
                    this.wtable_r[i24 - 2] = FastMath.cos(d4);
                    this.wtable_r[i24 - 1] = FastMath.sin(d4);
                    i23 += 2;
                    i12 = i12;
                }
                i14 += i17;
                i19++;
                i13 = i21;
                i2 = 1;
            }
            i13 = i16;
        }
    }

    void rfftil() {
        long j;
        long j2;
        long j3 = this.nl;
        long j4 = 1;
        if (j3 == 1) {
            return;
        }
        long j5 = 2;
        long j6 = j3 * 2;
        long j7 = 0;
        long j8 = 0;
        long j9 = 0;
        loop0: while (true) {
            j7 += j4;
            j8 = j7 <= 4 ? factors[(int) (j7 - j4)] : j8 + j5;
            while (true) {
                long j10 = j3 / j8;
                if (j3 - (j8 * j10) != 0) {
                    break;
                }
                j = j9 + j4;
                long j11 = j7;
                this.wtable_rl.setDouble(j + j4 + j6, j8);
                j2 = 1;
                if (j8 == j5 && j != 1) {
                    long j12 = j5;
                    while (j12 <= j) {
                        long j13 = (j - j12) + j5 + j6;
                        DoubleLargeArray doubleLargeArray = this.wtable_rl;
                        doubleLargeArray.setDouble(j13 + 1, doubleLargeArray.getDouble(j13));
                        j12++;
                        j5 = 2;
                    }
                    this.wtable_rl.setDouble(j6 + 2, 2.0d);
                }
                if (j10 == 1) {
                    break loop0;
                }
                j9 = j;
                j3 = j10;
                j7 = j11;
                j4 = 1;
                j5 = 2;
            }
        }
        this.wtable_rl.setDouble(j6, this.nl);
        this.wtable_rl.setDouble(j6 + 1, j);
        double d = 6.283185307179586d / this.nl;
        long j14 = j - 1;
        if (j14 == 0) {
            return;
        }
        long j15 = 0;
        long j16 = 1;
        long j17 = 1;
        while (j16 <= j14) {
            j16 += j2;
            long j18 = (long) this.wtable_rl.getDouble(j16 + j6);
            long j19 = j17 * j18;
            long j20 = j14;
            long j21 = this.nl / j19;
            long j22 = j18 - j2;
            long j23 = 0;
            long j24 = 1;
            while (j24 <= j22) {
                long j25 = j6;
                long j26 = j23 + j17;
                long j27 = j16;
                double d2 = j26 * d;
                double d3 = 0.0d;
                long j28 = 3;
                long j29 = j15;
                while (j28 <= j21) {
                    j29 += 2;
                    d3 += 1.0d;
                    double d4 = d3 * d2;
                    double d5 = d;
                    long j30 = j29 + this.nl;
                    this.wtable_rl.setDouble(j30 - 2, FastMath.cos(d4));
                    this.wtable_rl.setDouble(j30 - 1, FastMath.sin(d4));
                    j28 += 2;
                    d = d5;
                    j26 = j26;
                    d2 = d2;
                    j17 = j17;
                }
                long j31 = j26;
                j15 += j21;
                j24++;
                j16 = j27;
                j6 = j25;
                j23 = j31;
                j17 = j17;
            }
            j17 = j19;
            j14 = j20;
            j2 = 1;
        }
    }

    private void bluesteini() {
        double d = 3.141592653589793d / ((double) this.n);
        double[] dArr = this.bk1;
        dArr[0] = 1.0d;
        dArr[1] = 0.0d;
        int i = 0;
        int i2 = 1;
        while (true) {
            int i3 = this.n;
            if (i2 >= i3) {
                break;
            }
            int i4 = i2 * 2;
            i += i4 - 1;
            if (i >= i3 * 2) {
                i -= i3 * 2;
            }
            double d2 = ((double) i) * d;
            this.bk1[i4] = FastMath.cos(d2);
            this.bk1[i4 + 1] = FastMath.sin(d2);
            i2++;
        }
        double d3 = 1.0d / ((double) this.nBluestein);
        double[] dArr2 = this.bk2;
        double[] dArr3 = this.bk1;
        dArr2[0] = dArr3[0] * d3;
        dArr2[1] = dArr3[1] * d3;
        for (int i5 = 2; i5 < this.n * 2; i5 += 2) {
            double[] dArr4 = this.bk2;
            double[] dArr5 = this.bk1;
            dArr4[i5] = dArr5[i5] * d3;
            int i6 = i5 + 1;
            dArr4[i6] = dArr5[i6] * d3;
            int i7 = this.nBluestein;
            dArr4[(i7 * 2) - i5] = dArr4[i5];
            dArr4[((i7 * 2) - i5) + 1] = dArr4[i6];
        }
        CommonUtils.cftbsub(this.nBluestein * 2, this.bk2, 0, this.ip, this.nw, this.w);
    }

    private void bluesteinil() {
        double d = 3.141592653589793d / this.nl;
        this.bk1l.setDouble(0L, 1.0d);
        this.bk1l.setDouble(1L, 0.0d);
        int i = 1;
        long j = 0;
        while (true) {
            long j2 = i;
            long j3 = this.nl;
            if (j2 >= j3) {
                break;
            }
            int i2 = i * 2;
            j += (long) (i2 - 1);
            if (j >= j3 * 2) {
                j -= j3 * 2;
            }
            double d2 = j * d;
            this.bk1l.setDouble(i2, FastMath.cos(d2));
            this.bk1l.setDouble(i2 + 1, FastMath.sin(d2));
            i++;
        }
        double d3 = 1.0d / this.nBluesteinl;
        this.bk2l.setDouble(0L, this.bk1l.getDouble(0L) * d3);
        this.bk2l.setDouble(1L, this.bk1l.getDouble(1L) * d3);
        int i3 = 2;
        while (true) {
            long j4 = i3;
            if (j4 < this.nl * 2) {
                this.bk2l.setDouble(j4, this.bk1l.getDouble(j4) * d3);
                long j5 = i3 + 1;
                this.bk2l.setDouble(j5, this.bk1l.getDouble(j5) * d3);
                DoubleLargeArray doubleLargeArray = this.bk2l;
                doubleLargeArray.setDouble((this.nBluesteinl * 2) - j4, doubleLargeArray.getDouble(j4));
                DoubleLargeArray doubleLargeArray2 = this.bk2l;
                doubleLargeArray2.setDouble(((this.nBluesteinl * 2) - j4) + 1, doubleLargeArray2.getDouble(j5));
                i3 += 2;
            } else {
                CommonUtils.cftbsub(this.nBluesteinl * 2, this.bk2l, 0L, this.ipl, this.nwl, this.wl);
                return;
            }
        }
    }

    private void bluestein_complex(final double[] dArr, final int i, final int i2) {
        final double[] dArr2 = new double[this.nBluestein * 2];
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        int i3 = 0;
        if (numberOfThreads > 1 && this.n >= CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
            int i4 = (numberOfThreads < 4 || ((long) this.n) < CommonUtils.getThreadsBeginN_1D_FFT_4Threads()) ? 2 : 4;
            Future[] futureArr = new Future[i4];
            int i5 = this.n / i4;
            int i6 = 0;
            while (i6 < i4) {
                final int i7 = i6 * i5;
                final int i8 = i6 == i4 + (-1) ? this.n : i7 + i5;
                int i9 = i6;
                futureArr[i9] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_1D.5
                    @Override // java.lang.Runnable
                    public void run() {
                        if (i2 > 0) {
                            for (int i10 = i7; i10 < i8; i10++) {
                                int i11 = i10 * 2;
                                int i12 = i11 + 1;
                                int i13 = i;
                                int i14 = i13 + i11;
                                int i15 = i13 + i12;
                                dArr2[i11] = (dArr[i14] * DoubleFFT_1D.this.bk1[i11]) - (dArr[i15] * DoubleFFT_1D.this.bk1[i12]);
                                dArr2[i12] = (dArr[i14] * DoubleFFT_1D.this.bk1[i12]) + (dArr[i15] * DoubleFFT_1D.this.bk1[i11]);
                            }
                            return;
                        }
                        for (int i16 = i7; i16 < i8; i16++) {
                            int i17 = i16 * 2;
                            int i18 = i17 + 1;
                            int i19 = i;
                            int i20 = i19 + i17;
                            int i21 = i19 + i18;
                            dArr2[i17] = (dArr[i20] * DoubleFFT_1D.this.bk1[i17]) + (dArr[i21] * DoubleFFT_1D.this.bk1[i18]);
                            dArr2[i18] = ((-dArr[i20]) * DoubleFFT_1D.this.bk1[i18]) + (dArr[i21] * DoubleFFT_1D.this.bk1[i17]);
                        }
                    }
                });
                i6 = i9 + 1;
            }
            String str = null;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleFFT_1D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleFFT_1D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            CommonUtils.cftbsub(this.nBluestein * 2, dArr2, 0, this.ip, this.nw, this.w);
            int i10 = this.nBluestein / i4;
            int i11 = 0;
            while (i11 < i4) {
                final int i12 = i11 * i10;
                final int i13 = i11 == i4 + (-1) ? this.nBluestein : i12 + i10;
                futureArr[i11] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_1D.6
                    @Override // java.lang.Runnable
                    public void run() {
                        if (i2 > 0) {
                            for (int i14 = i12; i14 < i13; i14++) {
                                int i15 = i14 * 2;
                                int i16 = i15 + 1;
                                double d = ((-dArr2[i15]) * DoubleFFT_1D.this.bk2[i16]) + (dArr2[i16] * DoubleFFT_1D.this.bk2[i15]);
                                double[] dArr3 = dArr2;
                                dArr3[i15] = (dArr3[i15] * DoubleFFT_1D.this.bk2[i15]) + (dArr2[i16] * DoubleFFT_1D.this.bk2[i16]);
                                dArr2[i16] = d;
                            }
                            return;
                        }
                        for (int i17 = i12; i17 < i13; i17++) {
                            int i18 = i17 * 2;
                            int i19 = i18 + 1;
                            double d2 = (dArr2[i18] * DoubleFFT_1D.this.bk2[i19]) + (dArr2[i19] * DoubleFFT_1D.this.bk2[i18]);
                            double[] dArr4 = dArr2;
                            dArr4[i18] = (dArr4[i18] * DoubleFFT_1D.this.bk2[i18]) - (dArr2[i19] * DoubleFFT_1D.this.bk2[i19]);
                            dArr2[i19] = d2;
                        }
                    }
                });
                i11++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException e3) {
                Logger.getLogger(DoubleFFT_1D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(DoubleFFT_1D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
            }
            CommonUtils.cftfsub(this.nBluestein * 2, dArr2, 0, this.ip, this.nw, this.w);
            int i14 = this.n / i4;
            while (i3 < i4) {
                final int i15 = i3 * i14;
                final int i16 = i3 == i4 + (-1) ? this.n : i15 + i14;
                futureArr[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_1D.7
                    @Override // java.lang.Runnable
                    public void run() {
                        if (i2 > 0) {
                            for (int i17 = i15; i17 < i16; i17++) {
                                int i18 = i17 * 2;
                                int i19 = i18 + 1;
                                int i20 = i;
                                dArr[i20 + i18] = (DoubleFFT_1D.this.bk1[i18] * dArr2[i18]) - (DoubleFFT_1D.this.bk1[i19] * dArr2[i19]);
                                dArr[i20 + i19] = (DoubleFFT_1D.this.bk1[i19] * dArr2[i18]) + (DoubleFFT_1D.this.bk1[i18] * dArr2[i19]);
                            }
                            return;
                        }
                        for (int i21 = i15; i21 < i16; i21++) {
                            int i22 = i21 * 2;
                            int i23 = i22 + 1;
                            int i24 = i;
                            dArr[i24 + i22] = (DoubleFFT_1D.this.bk1[i22] * dArr2[i22]) + (DoubleFFT_1D.this.bk1[i23] * dArr2[i23]);
                            dArr[i24 + i23] = ((-DoubleFFT_1D.this.bk1[i23]) * dArr2[i22]) + (DoubleFFT_1D.this.bk1[i22] * dArr2[i23]);
                        }
                    }
                });
                i3++;
                str = str;
                i4 = i4;
            }
            String str2 = str;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
                return;
            } catch (InterruptedException e5) {
                Logger.getLogger(DoubleFFT_1D.class.getName()).log(Level.SEVERE, str2, (Throwable) e5);
                return;
            } catch (ExecutionException e6) {
                Logger.getLogger(DoubleFFT_1D.class.getName()).log(Level.SEVERE, str2, (Throwable) e6);
                return;
            }
        }
        if (i2 > 0) {
            for (int i17 = 0; i17 < this.n; i17++) {
                int i18 = i17 * 2;
                int i19 = i18 + 1;
                int i20 = i + i18;
                int i21 = i + i19;
                double d = dArr[i20];
                double[] dArr3 = this.bk1;
                dArr2[i18] = (d * dArr3[i18]) - (dArr[i21] * dArr3[i19]);
                dArr2[i19] = (dArr[i20] * dArr3[i19]) + (dArr[i21] * dArr3[i18]);
            }
        } else {
            for (int i22 = 0; i22 < this.n; i22++) {
                int i23 = i22 * 2;
                int i24 = i23 + 1;
                int i25 = i + i23;
                int i26 = i + i24;
                double d2 = dArr[i25];
                double[] dArr4 = this.bk1;
                dArr2[i23] = (d2 * dArr4[i23]) + (dArr[i26] * dArr4[i24]);
                dArr2[i24] = ((-dArr[i25]) * dArr4[i24]) + (dArr[i26] * dArr4[i23]);
            }
        }
        CommonUtils.cftbsub(this.nBluestein * 2, dArr2, 0, this.ip, this.nw, this.w);
        if (i2 > 0) {
            for (int i27 = 0; i27 < this.nBluestein; i27++) {
                int i28 = i27 * 2;
                int i29 = i28 + 1;
                double d3 = -dArr2[i28];
                double[] dArr5 = this.bk2;
                double d4 = (d3 * dArr5[i29]) + (dArr2[i29] * dArr5[i28]);
                dArr2[i28] = (dArr2[i28] * dArr5[i28]) + (dArr2[i29] * dArr5[i29]);
                dArr2[i29] = d4;
            }
        } else {
            for (int i30 = 0; i30 < this.nBluestein; i30++) {
                int i31 = i30 * 2;
                int i32 = i31 + 1;
                double d5 = dArr2[i31];
                double[] dArr6 = this.bk2;
                double d6 = (d5 * dArr6[i32]) + (dArr2[i32] * dArr6[i31]);
                dArr2[i31] = (dArr2[i31] * dArr6[i31]) - (dArr2[i32] * dArr6[i32]);
                dArr2[i32] = d6;
            }
        }
        CommonUtils.cftfsub(this.nBluestein * 2, dArr2, 0, this.ip, this.nw, this.w);
        if (i2 > 0) {
            while (i3 < this.n) {
                int i33 = i3 * 2;
                int i34 = i33 + 1;
                double[] dArr7 = this.bk1;
                dArr[i + i33] = (dArr7[i33] * dArr2[i33]) - (dArr7[i34] * dArr2[i34]);
                dArr[i + i34] = (dArr7[i34] * dArr2[i33]) + (dArr7[i33] * dArr2[i34]);
                i3++;
            }
            return;
        }
        while (i3 < this.n) {
            int i35 = i3 * 2;
            int i36 = i35 + 1;
            double[] dArr8 = this.bk1;
            dArr[i + i35] = (dArr8[i35] * dArr2[i35]) + (dArr8[i36] * dArr2[i36]);
            dArr[i + i36] = ((-dArr8[i36]) * dArr2[i35]) + (dArr8[i35] * dArr2[i36]);
            i3++;
        }
    }

    private void bluestein_complex(final DoubleLargeArray doubleLargeArray, final long j, final int i) {
        DoubleLargeArray doubleLargeArray2;
        final DoubleLargeArray doubleLargeArray3 = new DoubleLargeArray(this.nBluesteinl * 2);
        int numberOfThreads = ConcurrencyUtils.getNumberOfThreads();
        if (numberOfThreads > 1 && this.nl > CommonUtils.getThreadsBeginN_1D_FFT_2Threads()) {
            int i2 = (numberOfThreads < 4 || this.nl <= CommonUtils.getThreadsBeginN_1D_FFT_4Threads()) ? 2 : 4;
            Future[] futureArr = new Future[i2];
            long j2 = i2;
            long j3 = this.nl / j2;
            int i3 = 0;
            while (i3 < i2) {
                final long j4 = ((long) i3) * j3;
                final long j5 = i3 == i2 + (-1) ? this.nl : j4 + j3;
                Future[] futureArr2 = futureArr;
                futureArr2[i3] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_1D.8
                    @Override // java.lang.Runnable
                    public void run() {
                        long j6 = 2;
                        long j7 = 1;
                        if (i > 0) {
                            long j8 = j4;
                            while (j8 < j5) {
                                long j9 = j8 * j6;
                                long j10 = j9 + j7;
                                long j11 = j;
                                long j12 = j11 + j9;
                                long j13 = j11 + j10;
                                doubleLargeArray3.setDouble(j9, (doubleLargeArray.getDouble(j12) * DoubleFFT_1D.this.bk1l.getDouble(j9)) - (doubleLargeArray.getDouble(j13) * DoubleFFT_1D.this.bk1l.getDouble(j10)));
                                doubleLargeArray3.setDouble(j10, (doubleLargeArray.getDouble(j12) * DoubleFFT_1D.this.bk1l.getDouble(j10)) + (doubleLargeArray.getDouble(j13) * DoubleFFT_1D.this.bk1l.getDouble(j9)));
                                j8++;
                                j7 = 1;
                                j6 = 2;
                            }
                            return;
                        }
                        long j14 = 1;
                        for (long j15 = j4; j15 < j5; j15++) {
                            long j16 = j15 * 2;
                            long j17 = j16 + j14;
                            long j18 = j;
                            long j19 = j18 + j16;
                            long j20 = j18 + j17;
                            doubleLargeArray3.setDouble(j16, (doubleLargeArray.getDouble(j19) * DoubleFFT_1D.this.bk1l.getDouble(j16)) + (doubleLargeArray.getDouble(j20) * DoubleFFT_1D.this.bk1l.getDouble(j17)));
                            doubleLargeArray3.setDouble(j17, ((-doubleLargeArray.getDouble(j19)) * DoubleFFT_1D.this.bk1l.getDouble(j17)) + (doubleLargeArray.getDouble(j20) * DoubleFFT_1D.this.bk1l.getDouble(j16)));
                            j14 = 1;
                        }
                    }
                });
                i3++;
                i2 = i2;
                j2 = j2;
                futureArr = futureArr2;
            }
            long j6 = j2;
            Future[] futureArr3 = futureArr;
            int i4 = i2;
            String str = null;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e) {
                Logger.getLogger(DoubleFFT_1D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e);
            } catch (ExecutionException e2) {
                Logger.getLogger(DoubleFFT_1D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e2);
            }
            CommonUtils.cftbsub(this.nBluesteinl * 2, doubleLargeArray3, 0L, this.ipl, this.nwl, this.wl);
            long j7 = this.nBluesteinl / j6;
            int i5 = 0;
            while (i5 < i4) {
                final long j8 = ((long) i5) * j7;
                final long j9 = i5 == i4 + (-1) ? this.nBluesteinl : j8 + j7;
                futureArr3[i5] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_1D.9
                    @Override // java.lang.Runnable
                    public void run() {
                        long j10 = 2;
                        long j11 = 1;
                        if (i > 0) {
                            long j12 = j8;
                            while (j12 < j9) {
                                long j13 = j12 * j10;
                                long j14 = j13 + j11;
                                double d = ((-doubleLargeArray3.getDouble(j13)) * DoubleFFT_1D.this.bk2l.getDouble(j14)) + (doubleLargeArray3.getDouble(j14) * DoubleFFT_1D.this.bk2l.getDouble(j13));
                                DoubleLargeArray doubleLargeArray4 = doubleLargeArray3;
                                doubleLargeArray4.setDouble(j13, (doubleLargeArray4.getDouble(j13) * DoubleFFT_1D.this.bk2l.getDouble(j13)) + (doubleLargeArray3.getDouble(j14) * DoubleFFT_1D.this.bk2l.getDouble(j14)));
                                doubleLargeArray3.setDouble(j14, d);
                                j12++;
                                j11 = 1;
                                j10 = 2;
                            }
                            return;
                        }
                        long j15 = 1;
                        for (long j16 = j8; j16 < j9; j16++) {
                            long j17 = j16 * 2;
                            long j18 = j17 + j15;
                            double d2 = (doubleLargeArray3.getDouble(j17) * DoubleFFT_1D.this.bk2l.getDouble(j18)) + (doubleLargeArray3.getDouble(j18) * DoubleFFT_1D.this.bk2l.getDouble(j17));
                            DoubleLargeArray doubleLargeArray5 = doubleLargeArray3;
                            doubleLargeArray5.setDouble(j17, (doubleLargeArray5.getDouble(j17) * DoubleFFT_1D.this.bk2l.getDouble(j17)) - (doubleLargeArray3.getDouble(j18) * DoubleFFT_1D.this.bk2l.getDouble(j18)));
                            doubleLargeArray3.setDouble(j18, d2);
                            j15 = 1;
                        }
                    }
                });
                i5++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
            } catch (InterruptedException e3) {
                Logger.getLogger(DoubleFFT_1D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e3);
            } catch (ExecutionException e4) {
                Logger.getLogger(DoubleFFT_1D.class.getName()).log(Level.SEVERE, (String) null, (Throwable) e4);
            }
            CommonUtils.cftfsub(this.nBluesteinl * 2, doubleLargeArray3, 0L, this.ipl, this.nwl, this.wl);
            long j10 = this.nl / j6;
            int i6 = 0;
            while (i6 < i4) {
                final long j11 = ((long) i6) * j10;
                final long j12 = i6 == i4 + (-1) ? this.nl : j11 + j10;
                futureArr3[i6] = ConcurrencyUtils.submit(new Runnable() { // from class: org.jtransforms.fft.DoubleFFT_1D.10
                    @Override // java.lang.Runnable
                    public void run() {
                        long j13 = 2;
                        long j14 = 1;
                        if (i > 0) {
                            long j15 = j11;
                            while (j15 < j12) {
                                long j16 = j15 * j13;
                                long j17 = j16 + j14;
                                long j18 = j;
                                doubleLargeArray.setDouble(j18 + j16, (DoubleFFT_1D.this.bk1l.getDouble(j16) * doubleLargeArray3.getDouble(j16)) - (DoubleFFT_1D.this.bk1l.getDouble(j17) * doubleLargeArray3.getDouble(j17)));
                                doubleLargeArray.setDouble(j18 + j17, (DoubleFFT_1D.this.bk1l.getDouble(j17) * doubleLargeArray3.getDouble(j16)) + (DoubleFFT_1D.this.bk1l.getDouble(j16) * doubleLargeArray3.getDouble(j17)));
                                j15++;
                                j14 = 1;
                                j13 = 2;
                            }
                            return;
                        }
                        long j19 = 1;
                        for (long j20 = j11; j20 < j12; j20++) {
                            long j21 = j20 * 2;
                            long j22 = j21 + j19;
                            long j23 = j;
                            doubleLargeArray.setDouble(j23 + j21, (DoubleFFT_1D.this.bk1l.getDouble(j21) * doubleLargeArray3.getDouble(j21)) + (DoubleFFT_1D.this.bk1l.getDouble(j22) * doubleLargeArray3.getDouble(j22)));
                            doubleLargeArray.setDouble(j23 + j22, ((-DoubleFFT_1D.this.bk1l.getDouble(j22)) * doubleLargeArray3.getDouble(j21)) + (DoubleFFT_1D.this.bk1l.getDouble(j21) * doubleLargeArray3.getDouble(j22)));
                            j19 = 1;
                        }
                    }
                });
                i6++;
                str = str;
                i4 = i4;
            }
            String str2 = str;
            try {
                ConcurrencyUtils.waitForCompletion(futureArr3);
                return;
            } catch (InterruptedException e5) {
                Logger.getLogger(DoubleFFT_1D.class.getName()).log(Level.SEVERE, str2, (Throwable) e5);
                return;
            } catch (ExecutionException e6) {
                Logger.getLogger(DoubleFFT_1D.class.getName()).log(Level.SEVERE, str2, (Throwable) e6);
                return;
            }
        }
        long j13 = 1;
        if (i > 0) {
            long j14 = 0;
            while (j14 < this.nl) {
                long j15 = j14 * 2;
                long j16 = j15 + j13;
                long j17 = j + j15;
                long j18 = j + j16;
                doubleLargeArray3.setDouble(j15, (doubleLargeArray.getDouble(j17) * this.bk1l.getDouble(j15)) - (doubleLargeArray.getDouble(j18) * this.bk1l.getDouble(j16)));
                doubleLargeArray3.setDouble(j16, (doubleLargeArray.getDouble(j17) * this.bk1l.getDouble(j16)) + (doubleLargeArray.getDouble(j18) * this.bk1l.getDouble(j15)));
                j14++;
                j13 = 1;
            }
            doubleLargeArray2 = doubleLargeArray;
        } else {
            doubleLargeArray2 = doubleLargeArray;
            long j19 = 1;
            for (long j20 = 0; j20 < this.nl; j20++) {
                long j21 = j20 * 2;
                long j22 = j21 + j19;
                long j23 = j + j21;
                long j24 = j + j22;
                doubleLargeArray3.setDouble(j21, (doubleLargeArray2.getDouble(j23) * this.bk1l.getDouble(j21)) + (doubleLargeArray2.getDouble(j24) * this.bk1l.getDouble(j22)));
                doubleLargeArray3.setDouble(j22, ((-doubleLargeArray2.getDouble(j23)) * this.bk1l.getDouble(j22)) + (doubleLargeArray2.getDouble(j24) * this.bk1l.getDouble(j21)));
                j19 = 1;
            }
        }
        CommonUtils.cftbsub(this.nBluesteinl * 2, doubleLargeArray3, 0L, this.ipl, this.nwl, this.wl);
        if (i > 0) {
            for (long j25 = 0; j25 < this.nBluesteinl; j25++) {
                long j26 = j25 * 2;
                long j27 = j26 + 1;
                double d = ((-doubleLargeArray3.getDouble(j26)) * this.bk2l.getDouble(j27)) + (doubleLargeArray3.getDouble(j27) * this.bk2l.getDouble(j26));
                doubleLargeArray3.setDouble(j26, (doubleLargeArray3.getDouble(j26) * this.bk2l.getDouble(j26)) + (doubleLargeArray3.getDouble(j27) * this.bk2l.getDouble(j27)));
                doubleLargeArray3.setDouble(j27, d);
            }
        } else {
            long j28 = 1;
            for (long j29 = 0; j29 < this.nBluesteinl; j29++) {
                long j30 = j29 * 2;
                long j31 = j30 + j28;
                double d2 = (doubleLargeArray3.getDouble(j30) * this.bk2l.getDouble(j31)) + (doubleLargeArray3.getDouble(j31) * this.bk2l.getDouble(j30));
                doubleLargeArray3.setDouble(j30, (doubleLargeArray3.getDouble(j30) * this.bk2l.getDouble(j30)) - (doubleLargeArray3.getDouble(j31) * this.bk2l.getDouble(j31)));
                doubleLargeArray3.setDouble(j31, d2);
                j28 = 1;
            }
        }
        CommonUtils.cftfsub(this.nBluesteinl * 2, doubleLargeArray3, 0L, this.ipl, this.nwl, this.wl);
        if (i > 0) {
            for (long j32 = 0; j32 < this.nl; j32++) {
                long j33 = j32 * 2;
                long j34 = j33 + 1;
                doubleLargeArray2.setDouble(j + j33, (this.bk1l.getDouble(j33) * doubleLargeArray3.getDouble(j33)) - (this.bk1l.getDouble(j34) * doubleLargeArray3.getDouble(j34)));
                doubleLargeArray2.setDouble(j + j34, (this.bk1l.getDouble(j34) * doubleLargeArray3.getDouble(j33)) + (this.bk1l.getDouble(j33) * doubleLargeArray3.getDouble(j34)));
            }
            return;
        }
        long j35 = 1;
        for (long j36 = 0; j36 < this.nl; j36++) {
            long j37 = j36 * 2;
            long j38 = j37 + j35;
            doubleLargeArray2.setDouble(j + j37, (this.bk1l.getDouble(j37) * doubleLargeArray3.getDouble(j37)) + (this.bk1l.getDouble(j38) * doubleLargeArray3.getDouble(j38)));
            doubleLargeArray2.setDouble(j + j38, ((-this.bk1l.getDouble(j38)) * doubleLargeArray3.getDouble(j37)) + (this.bk1l.getDouble(j37) * doubleLargeArray3.getDouble(j38)));
            j35 = 1;
        }
    }

    void cfftf(double[] dArr, int i, int i2) {
        int i3;
        double[] dArr2;
        int i4 = this.n;
        int i5 = i4 * 2;
        double[] dArr3 = new double[i5];
        int i6 = 4;
        int i7 = i4 * 4;
        int i8 = 0;
        int[] iArr = {0};
        int i9 = (int) this.wtable[i7 + 1];
        int i10 = 2;
        int i11 = 2;
        int i12 = 0;
        int i13 = 1;
        int i14 = i5;
        while (i11 <= i9 + 1) {
            int i15 = (int) this.wtable[i11 + i7];
            int i16 = i15 * i13;
            int i17 = this.n / i16;
            int i18 = i17 + i17;
            int i19 = i18 * i13;
            if (i15 == i10) {
                i3 = i15;
                i11 = i11;
                i10 = i10;
                i9 = i9;
                i8 = i8;
                i6 = i6;
                dArr2 = dArr3;
                if (i12 == 0) {
                    passf2(i18, i13, dArr, i, dArr2, 0, i14, i2);
                } else {
                    passf2(i18, i13, dArr2, 0, dArr, i, i14, i2);
                }
            } else if (i15 == 3) {
                i3 = i15;
                i11 = i11;
                i10 = i10;
                i9 = i9;
                i8 = i8;
                i6 = i6;
                dArr2 = dArr3;
                if (i12 == 0) {
                    passf3(i18, i13, dArr, i, dArr2, 0, i14, i2);
                } else {
                    passf3(i18, i13, dArr2, 0, dArr, i, i14, i2);
                }
            } else if (i15 == i6) {
                i3 = i15;
                i11 = i11;
                i10 = i10;
                i9 = i9;
                i8 = i8;
                i6 = i6;
                dArr2 = dArr3;
                if (i12 == 0) {
                    passf4(i18, i13, dArr, i, dArr2, 0, i14, i2);
                } else {
                    passf4(i18, i13, dArr2, 0, dArr, i, i14, i2);
                }
            } else if (i15 != 5) {
                if (i12 == 0) {
                    i3 = i15;
                    dArr2 = dArr3;
                    passfg(iArr, i18, i15, i13, i19, dArr, i, dArr3, 0, i14, i2);
                } else {
                    i3 = i15;
                    dArr2 = dArr3;
                    passfg(iArr, i18, i3, i13, i19, dArr2, 0, dArr, i, i14, i2);
                }
                if (iArr[i8] != 0) {
                }
                i14 += (i3 - 1) * i18;
                i11++;
                i8 = i8;
                i13 = i16;
                i6 = i6;
                i10 = i10;
                i9 = i9;
                dArr3 = dArr2;
            } else {
                i3 = i15;
                i11 = i11;
                i10 = i10;
                i9 = i9;
                i8 = i8;
                i6 = i6;
                dArr2 = dArr3;
                if (i12 == 0) {
                    passf5(i18, i13, dArr, i, dArr2, 0, i14, i2);
                } else {
                    passf5(i18, i13, dArr2, 0, dArr, i, i14, i2);
                }
            }
            i12 = 1 - i12;
            i14 += (i3 - 1) * i18;
            i11++;
            i8 = i8;
            i13 = i16;
            i6 = i6;
            i10 = i10;
            i9 = i9;
            dArr3 = dArr2;
        }
        int i20 = i8;
        double[] dArr4 = dArr3;
        if (i12 == 0) {
            return;
        }
        System.arraycopy(dArr4, i20, dArr, i, i5);
    }

    void cfftf(DoubleLargeArray doubleLargeArray, long j, int i) {
        DoubleLargeArray doubleLargeArray2;
        int i2;
        int[] iArr;
        DoubleFFT_1D doubleFFT_1D = this;
        int i3 = i;
        long j2 = doubleFFT_1D.nl * 2;
        DoubleLargeArray doubleLargeArray3 = new DoubleLargeArray(j2);
        long j3 = doubleFFT_1D.nl * 4;
        int[] iArr2 = {0};
        long j4 = (long) doubleFFT_1D.wtablel.getDouble(j3 + 1);
        long j5 = 2;
        long j6 = j2;
        long j7 = 1;
        long j8 = 0;
        while (j5 <= j4 + 1) {
            int i4 = (int) doubleFFT_1D.wtablel.getDouble(j5 + j3);
            long j9 = i4;
            long j10 = j9 * j7;
            long j11 = doubleFFT_1D.nl / j10;
            long j12 = j11 + j11;
            long j13 = j12 * j7;
            if (i4 == 2) {
                i4 = i4;
                j4 = j4;
                j2 = j2;
                doubleLargeArray2 = doubleLargeArray3;
                i2 = i3;
                iArr = iArr2;
                if (j8 == 0) {
                    passf2(j12, j7, doubleLargeArray, j, doubleLargeArray2, 0L, j6, i2);
                } else {
                    passf2(j12, j7, doubleLargeArray2, 0L, doubleLargeArray, j, j6, i2);
                }
            } else if (i4 == 3) {
                i4 = i4;
                j4 = j4;
                j2 = j2;
                doubleLargeArray2 = doubleLargeArray3;
                i2 = i3;
                iArr = iArr2;
                if (j8 == 0) {
                    passf3(j12, j7, doubleLargeArray, j, doubleLargeArray2, 0L, j6, i2);
                } else {
                    passf3(j12, j7, doubleLargeArray2, 0L, doubleLargeArray, j, j6, i2);
                }
            } else if (i4 != 4) {
                if (i4 != 5) {
                    if (j8 == 0) {
                        doubleLargeArray2 = doubleLargeArray3;
                        iArr = iArr2;
                        passfg(iArr2, j12, j9, j7, j13, doubleLargeArray, j, doubleLargeArray2, 0L, j6, i3);
                    } else {
                        doubleLargeArray2 = doubleLargeArray3;
                        iArr = iArr2;
                        passfg(iArr, j12, j9, j7, j13, doubleLargeArray3, 0L, doubleLargeArray, j, j6, i);
                    }
                    if (iArr[0] != 0) {
                        j8 = 1 - j8;
                    }
                    i2 = i;
                } else {
                    i4 = i4;
                    j4 = j4;
                    j2 = j2;
                    doubleLargeArray2 = doubleLargeArray3;
                    iArr = iArr2;
                    if (j8 == 0) {
                        i2 = i;
                        passf5(j12, j7, doubleLargeArray, j, doubleLargeArray2, 0L, j6, i2);
                    } else {
                        i2 = i;
                        passf5(j12, j7, doubleLargeArray2, 0L, doubleLargeArray, j, j6, i2);
                    }
                }
                j6 += ((long) (i4 - 1)) * j12;
                j5++;
                doubleFFT_1D = this;
                i3 = i2;
                j7 = j10;
                j4 = j4;
                j2 = j2;
                doubleLargeArray3 = doubleLargeArray2;
                iArr2 = iArr;
            } else {
                i4 = i4;
                j4 = j4;
                j2 = j2;
                doubleLargeArray2 = doubleLargeArray3;
                i2 = i3;
                iArr = iArr2;
                if (j8 == 0) {
                    passf4(j12, j7, doubleLargeArray, j, doubleLargeArray2, 0L, j6, i);
                } else {
                    passf4(j12, j7, doubleLargeArray2, 0L, doubleLargeArray, j, j6, i);
                }
            }
            j8 = 1 - j8;
            j6 += ((long) (i4 - 1)) * j12;
            j5++;
            doubleFFT_1D = this;
            i3 = i2;
            j7 = j10;
            j4 = j4;
            j2 = j2;
            doubleLargeArray3 = doubleLargeArray2;
            iArr2 = iArr;
        }
        long j14 = j2;
        DoubleLargeArray doubleLargeArray4 = doubleLargeArray3;
        if (j8 == 0) {
            return;
        }
        LargeArrayUtils.arraycopy(doubleLargeArray4, 0L, doubleLargeArray, j, j14);
    }

    void passf2(int i, int i2, double[] dArr, int i3, double[] dArr2, int i4, int i5, int i6) {
        int i7 = i;
        int i8 = i2;
        int i9 = i7 * i8;
        if (i7 <= 2) {
            for (int i10 = 0; i10 < i8; i10++) {
                int i11 = i10 * i7;
                int i12 = i3 + (i11 * 2);
                int i13 = i12 + i7;
                double d = dArr[i12];
                double d2 = dArr[i12 + 1];
                double d3 = dArr[i13];
                double d4 = dArr[i13 + 1];
                int i14 = i4 + i11;
                int i15 = i14 + i9;
                dArr2[i14] = d + d3;
                dArr2[i14 + 1] = d2 + d4;
                dArr2[i15] = d - d3;
                dArr2[i15 + 1] = d2 - d4;
            }
            return;
        }
        int i16 = 0;
        while (i16 < i8) {
            int i17 = 0;
            while (i17 < i7 - 1) {
                int i18 = i16 * i7;
                int i19 = i3 + i17 + (i18 * 2);
                int i20 = i19 + i7;
                double d5 = dArr[i19];
                double d6 = dArr[i19 + 1];
                double d7 = dArr[i20];
                double d8 = dArr[i20 + 1];
                int i21 = i17 + i5;
                double[] dArr3 = this.wtable;
                double d9 = dArr3[i21];
                double d10 = ((double) i6) * dArr3[i21 + 1];
                double d11 = d5 - d7;
                double d12 = d6 - d8;
                int i22 = i4 + i17 + i18;
                int i23 = i22 + i9;
                dArr2[i22] = d5 + d7;
                dArr2[i22 + 1] = d6 + d8;
                dArr2[i23] = (d9 * d11) - (d10 * d12);
                dArr2[i23 + 1] = (d9 * d12) + (d10 * d11);
                i17 += 2;
                i7 = i;
                i16 = i16;
            }
            i16++;
            i7 = i;
            i8 = i2;
        }
    }

    void passf2(long j, long j2, DoubleLargeArray doubleLargeArray, long j3, DoubleLargeArray doubleLargeArray2, long j4, long j5, long j6) {
        DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
        DoubleLargeArray doubleLargeArray4 = doubleLargeArray2;
        long j7 = j * j2;
        long j8 = 2;
        long j9 = 1;
        if (j <= 2) {
            long j10 = 0;
            while (j10 < j2) {
                long j11 = j10 * j;
                long j12 = j3 + (j11 * j8);
                long j13 = j12 + j;
                double d = doubleLargeArray3.getDouble(j12);
                double d2 = doubleLargeArray3.getDouble(j12 + j9);
                double d3 = doubleLargeArray3.getDouble(j13);
                double d4 = doubleLargeArray3.getDouble(j13 + j9);
                long j14 = j4 + j11;
                long j15 = j14 + j7;
                doubleLargeArray4.setDouble(j14, d + d3);
                doubleLargeArray4.setDouble(j14 + 1, d2 + d4);
                doubleLargeArray4.setDouble(j15, d - d3);
                doubleLargeArray4.setDouble(j15 + 1, d2 - d4);
                j10++;
                j9 = 1;
                j7 = j7;
                j8 = 2;
            }
            return;
        }
        long j16 = 1;
        long j17 = 0;
        while (j17 < j2) {
            long j18 = 0;
            while (j18 < j - j16) {
                long j19 = j17 * j;
                long j20 = j3 + j18 + (j19 * 2);
                long j21 = j20 + j;
                double d5 = doubleLargeArray3.getDouble(j20);
                double d6 = doubleLargeArray3.getDouble(j20 + j16);
                double d7 = doubleLargeArray3.getDouble(j21);
                double d8 = doubleLargeArray3.getDouble(j21 + j16);
                long j22 = j18 + j5;
                double d9 = this.wtablel.getDouble(j22);
                long j23 = j17;
                double d10 = j6 * this.wtablel.getDouble(j22 + 1);
                double d11 = d5 - d7;
                double d12 = d6 - d8;
                long j24 = j4 + j18 + j19;
                long j25 = j24 + j7;
                doubleLargeArray2.setDouble(j24, d5 + d7);
                doubleLargeArray2.setDouble(j24 + 1, d6 + d8);
                doubleLargeArray2.setDouble(j25, (d9 * d11) - (d10 * d12));
                doubleLargeArray2.setDouble(j25 + 1, (d9 * d12) + (d10 * d11));
                doubleLargeArray3 = doubleLargeArray;
                doubleLargeArray4 = doubleLargeArray2;
                j17 = j23;
                j18 += 2;
                j16 = 1;
            }
            j17 += j16;
            doubleLargeArray3 = doubleLargeArray;
            doubleLargeArray4 = doubleLargeArray4;
        }
    }

    void passf3(int i, int i2, double[] dArr, int i3, double[] dArr2, int i4, int i5, int i6) {
        int i7 = i5 + i;
        int i8 = i2 * i;
        double d = 0.8660254037844387d;
        double d2 = -0.5d;
        int i9 = 2;
        int i10 = 1;
        if (i == 2) {
            int i11 = 1;
            while (i11 <= i2) {
                int i12 = i3 + (((i11 * 3) - 2) * i);
                int i13 = i12 + i;
                int i14 = i12 - i;
                double d3 = dArr[i12];
                double d4 = dArr[i12 + 1];
                double d5 = dArr[i13];
                double d6 = dArr[i13 + 1];
                double d7 = dArr[i14];
                double d8 = dArr[i14 + 1];
                double d9 = d3 + d5;
                double d10 = d7 + (d9 * d2);
                double d11 = d4 + d6;
                double d12 = d8 + (d11 * d2);
                double d13 = ((double) i6) * 0.8660254037844387d;
                double d14 = (d3 - d5) * d13;
                double d15 = d13 * (d4 - d6);
                int i15 = i4 + ((i11 - 1) * i);
                int i16 = i15 + i8;
                int i17 = i16 + i8;
                dArr2[i15] = dArr[i14] + d9;
                dArr2[i15 + 1] = d8 + d11;
                dArr2[i16] = d10 - d15;
                dArr2[i16 + 1] = d12 + d14;
                dArr2[i17] = d10 + d15;
                dArr2[i17 + 1] = d12 - d14;
                i11++;
                d2 = -0.5d;
            }
            return;
        }
        int i18 = 1;
        while (i18 <= i2) {
            int i19 = i3 + (((i18 * 3) - i9) * i);
            int i20 = i4 + ((i18 - 1) * i);
            int i21 = 0;
            while (i21 < i - 1) {
                int i22 = i21 + i19;
                int i23 = i22 + i;
                int i24 = i22 - i;
                double d16 = dArr[i22];
                double d17 = dArr[i22 + i10];
                double d18 = dArr[i23];
                double d19 = dArr[i23 + i10];
                double d20 = dArr[i24];
                double d21 = dArr[i24 + i10];
                double d22 = d16 + d18;
                double d23 = d20 + (d22 * (-0.5d));
                double d24 = d17 + d19;
                double d25 = d21 + (d24 * (-0.5d));
                double d26 = i6;
                double d27 = d26 * d;
                double d28 = (d16 - d18) * d27;
                double d29 = d27 * (d17 - d19);
                double d30 = d23 - d29;
                double d31 = d23 + d29;
                double d32 = d25 + d28;
                double d33 = d25 - d28;
                int i25 = i21 + i5;
                int i26 = i21 + i7;
                double[] dArr3 = this.wtable;
                double d34 = dArr3[i25];
                double d35 = dArr3[i25 + 1] * d26;
                double d36 = dArr3[i26];
                double d37 = d26 * dArr3[i26 + 1];
                int i27 = i21 + i20;
                int i28 = i27 + i8;
                int i29 = i28 + i8;
                dArr2[i27] = d20 + d22;
                dArr2[i27 + 1] = d21 + d24;
                dArr2[i28] = (d34 * d30) - (d35 * d32);
                dArr2[i28 + 1] = (d34 * d32) + (d35 * d30);
                dArr2[i29] = (d36 * d31) - (d37 * d33);
                dArr2[i29 + 1] = (d36 * d33) + (d37 * d31);
                i21 += 2;
                i10 = 1;
                d = 0.8660254037844387d;
            }
            i18++;
            d = 0.8660254037844387d;
            i9 = 2;
        }
    }

    void passf3(long j, long j2, DoubleLargeArray doubleLargeArray, long j3, DoubleLargeArray doubleLargeArray2, long j4, long j5, long j6) {
        DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
        DoubleLargeArray doubleLargeArray4 = doubleLargeArray2;
        long j7 = j5 + j;
        long j8 = j2 * j;
        long j9 = 2;
        long j10 = 3;
        if (j == 2) {
            long j11 = 1;
            while (j11 <= j2) {
                long j12 = j3 + (((j11 * j10) - j9) * j);
                long j13 = j12 + j;
                long j14 = j12 - j;
                double d = doubleLargeArray3.getDouble(j12);
                double d2 = doubleLargeArray3.getDouble(j12 + 1);
                double d3 = doubleLargeArray3.getDouble(j13);
                double d4 = doubleLargeArray3.getDouble(j13 + 1);
                double d5 = doubleLargeArray3.getDouble(j14);
                long j15 = j8;
                double d6 = doubleLargeArray3.getDouble(j14 + 1);
                double d7 = d + d3;
                double d8 = d5 + (d7 * (-0.5d));
                double d9 = d2 + d4;
                double d10 = d6 + (d9 * (-0.5d));
                double d11 = j6 * 0.8660254037844387d;
                double d12 = (d - d3) * d11;
                double d13 = d11 * (d2 - d4);
                long j16 = j4 + ((j11 - 1) * j);
                long j17 = j16 + j15;
                long j18 = j17 + j15;
                doubleLargeArray4.setDouble(j16, doubleLargeArray3.getDouble(j14) + d7);
                doubleLargeArray4.setDouble(j16 + 1, d6 + d9);
                doubleLargeArray4.setDouble(j17, d8 - d13);
                doubleLargeArray4.setDouble(j17 + 1, d10 + d12);
                doubleLargeArray4.setDouble(j18, d8 + d13);
                doubleLargeArray4.setDouble(j18 + 1, d10 - d12);
                j11++;
                j8 = j15;
                j9 = 2;
                j10 = 3;
            }
            return;
        }
        long j19 = 1;
        while (j19 <= j2) {
            long j20 = j3 + (((j19 * 3) - 2) * j);
            long j21 = j4 + ((j19 - 1) * j);
            long j22 = 0;
            while (j22 < j - 1) {
                long j23 = j22 + j20;
                long j24 = j20;
                long j25 = j23 + j;
                long j26 = j19;
                long j27 = j23 - j;
                double d14 = doubleLargeArray3.getDouble(j23);
                double d15 = doubleLargeArray3.getDouble(j23 + 1);
                double d16 = doubleLargeArray3.getDouble(j25);
                double d17 = doubleLargeArray3.getDouble(j25 + 1);
                double d18 = doubleLargeArray3.getDouble(j27);
                double d19 = doubleLargeArray3.getDouble(j27 + 1);
                double d20 = d14 + d16;
                double d21 = d18 + (d20 * (-0.5d));
                double d22 = d15 + d17;
                double d23 = d19 + (d22 * (-0.5d));
                double d24 = j6;
                double d25 = d24 * 0.8660254037844387d;
                double d26 = (d14 - d16) * d25;
                double d27 = d25 * (d15 - d17);
                double d28 = d21 - d27;
                double d29 = d21 + d27;
                double d30 = d23 + d26;
                double d31 = d23 - d26;
                long j28 = j22 + j5;
                long j29 = j22 + j7;
                double d32 = this.wtablel.getDouble(j28);
                double d33 = this.wtablel.getDouble(j28 + 1) * d24;
                double d34 = this.wtablel.getDouble(j29);
                double d35 = d24 * this.wtablel.getDouble(j29 + 1);
                long j30 = j22 + j21;
                long j31 = j7;
                long j32 = j30 + j8;
                long j33 = j21;
                long j34 = j32 + j8;
                doubleLargeArray2.setDouble(j30, d18 + d20);
                doubleLargeArray2.setDouble(j30 + 1, d19 + d22);
                doubleLargeArray2.setDouble(j32, (d32 * d28) - (d33 * d30));
                doubleLargeArray2.setDouble(j32 + 1, (d32 * d30) + (d33 * d28));
                doubleLargeArray2.setDouble(j34, (d34 * d29) - (d35 * d31));
                doubleLargeArray2.setDouble(j34 + 1, (d34 * d31) + (d35 * d29));
                j22 += 2;
                doubleLargeArray3 = doubleLargeArray;
                doubleLargeArray4 = doubleLargeArray2;
                j20 = j24;
                j19 = j26;
                j7 = j31;
                j21 = j33;
            }
            j19++;
            doubleLargeArray3 = doubleLargeArray;
            doubleLargeArray4 = doubleLargeArray4;
        }
    }

    void passf4(int i, int i2, double[] dArr, int i3, double[] dArr2, int i4, int i5, int i6) {
        int i7 = i;
        int i8 = i2;
        int i9 = i6;
        int i10 = i5 + i7;
        int i11 = i10 + i7;
        int i12 = i8 * i7;
        if (i7 == 2) {
            int i13 = 0;
            while (i13 < i8) {
                int i14 = i13 * i7;
                int i15 = i3 + (i14 * 4) + 1;
                int i16 = i15 + i7;
                int i17 = i16 + i7;
                int i18 = i17 + i7;
                double d = dArr[i15 - 1];
                double d2 = dArr[i15];
                double d3 = dArr[i16 - 1];
                double d4 = dArr[i16];
                double d5 = dArr[i17 - 1];
                double d6 = dArr[i17];
                double d7 = dArr[i18 - 1];
                double d8 = dArr[i18];
                double d9 = d2 - d6;
                double d10 = d2 + d6;
                double d11 = d8 - d4;
                double d12 = d4 + d8;
                double d13 = d - d5;
                double d14 = d + d5;
                double d15 = d3 - d7;
                double d16 = d3 + d7;
                int i19 = i4 + i14;
                int i20 = i19 + i12;
                int i21 = i20 + i12;
                int i22 = i21 + i12;
                dArr2[i19] = d14 + d16;
                dArr2[i19 + 1] = d10 + d12;
                double d17 = i9;
                double d18 = d11 * d17;
                dArr2[i20] = d13 + d18;
                double d19 = d17 * d15;
                dArr2[i20 + 1] = d9 + d19;
                dArr2[i21] = d14 - d16;
                dArr2[i21 + 1] = d10 - d12;
                dArr2[i22] = d13 - d18;
                dArr2[i22 + 1] = d9 - d19;
                i13++;
                i7 = i;
                i8 = i2;
            }
            return;
        }
        int i23 = 0;
        while (i23 < i2) {
            int i24 = i23 * i;
            int i25 = i3 + 1 + (i24 * 4);
            int i26 = 0;
            while (i26 < i - 1) {
                int i27 = i26 + i25;
                int i28 = i27 + i;
                int i29 = i28 + i;
                int i30 = i29 + i;
                double d20 = dArr[i27 - 1];
                double d21 = dArr[i27];
                double d22 = dArr[i28 - 1];
                double d23 = dArr[i28];
                double d24 = dArr[i29 - 1];
                double d25 = dArr[i29];
                double d26 = dArr[i30 - 1];
                double d27 = dArr[i30];
                double d28 = d21 - d25;
                double d29 = d21 + d25;
                double d30 = d23 + d27;
                double d31 = d27 - d23;
                double d32 = d20 - d24;
                double d33 = d20 + d24;
                double d34 = d22 - d26;
                double d35 = d22 + d26;
                double d36 = d33 - d35;
                double d37 = d29 - d30;
                double d38 = i9;
                double d39 = d31 * d38;
                double d40 = d32 + d39;
                double d41 = d32 - d39;
                double d42 = d34 * d38;
                double d43 = d28 + d42;
                double d44 = d28 - d42;
                int i31 = i26 + i5;
                int i32 = i26 + i10;
                int i33 = i26 + i11;
                double[] dArr3 = this.wtable;
                double d45 = dArr3[i31];
                double d46 = dArr3[i31 + 1] * d38;
                double d47 = dArr3[i32];
                double d48 = dArr3[i32 + 1] * d38;
                double d49 = dArr3[i33];
                double d50 = d38 * dArr3[i33 + 1];
                int i34 = i4 + i26 + i24;
                int i35 = i34 + i12;
                int i36 = i35 + i12;
                int i37 = i36 + i12;
                dArr2[i34] = d33 + d35;
                dArr2[i34 + 1] = d29 + d30;
                dArr2[i35] = (d45 * d40) - (d46 * d43);
                dArr2[i35 + 1] = (d45 * d43) + (d46 * d40);
                dArr2[i36] = (d47 * d36) - (d48 * d37);
                dArr2[i36 + 1] = (d47 * d37) + (d48 * d36);
                dArr2[i37] = (d49 * d41) - (d50 * d44);
                dArr2[i37 + 1] = (d49 * d44) + (d50 * d41);
                i26 += 2;
                i9 = i6;
                i25 = i25;
            }
            i23++;
            i9 = i6;
        }
    }

    void passf4(long j, long j2, DoubleLargeArray doubleLargeArray, long j3, DoubleLargeArray doubleLargeArray2, long j4, long j5, int i) {
        DoubleLargeArray doubleLargeArray3 = doubleLargeArray2;
        long j6 = j5 + j;
        long j7 = j6 + j;
        long j8 = j2 * j;
        long j9 = 2;
        long j10 = 4;
        if (j == 2) {
            long j11 = 0;
            while (j11 < j2) {
                long j12 = j11 * j;
                long j13 = j3 + (j12 * j10) + 1;
                long j14 = j13 + j;
                long j15 = j14 + j;
                long j16 = j15 + j;
                long j17 = j8;
                double d = doubleLargeArray.getDouble(j13 - 1);
                double d2 = doubleLargeArray.getDouble(j13);
                double d3 = doubleLargeArray.getDouble(j14 - 1);
                double d4 = doubleLargeArray.getDouble(j14);
                double d5 = doubleLargeArray.getDouble(j15 - 1);
                double d6 = doubleLargeArray.getDouble(j15);
                double d7 = doubleLargeArray.getDouble(j16 - 1);
                double d8 = doubleLargeArray.getDouble(j16);
                double d9 = d2 - d6;
                double d10 = d2 + d6;
                double d11 = d8 - d4;
                double d12 = d4 + d8;
                double d13 = d - d5;
                double d14 = d + d5;
                double d15 = d3 - d7;
                double d16 = d3 + d7;
                long j18 = j4 + j12;
                long j19 = j18 + j17;
                long j20 = j19 + j17;
                long j21 = j20 + j17;
                doubleLargeArray2.setDouble(j18, d14 + d16);
                doubleLargeArray2.setDouble(j18 + 1, d10 + d12);
                double d17 = i;
                double d18 = d11 * d17;
                doubleLargeArray2.setDouble(j19, d13 + d18);
                double d19 = d17 * d15;
                doubleLargeArray2.setDouble(j19 + 1, d9 + d19);
                doubleLargeArray2.setDouble(j20, d14 - d16);
                doubleLargeArray2.setDouble(j20 + 1, d10 - d12);
                doubleLargeArray2.setDouble(j21, d13 - d18);
                doubleLargeArray2.setDouble(j21 + 1, d9 - d19);
                j11++;
                j8 = j17;
                j10 = 4;
            }
            return;
        }
        long j22 = 0;
        while (j22 < j2) {
            long j23 = j22 * j;
            long j24 = j3 + 1 + (j23 * 4);
            long j25 = 0;
            while (j25 < j - 1) {
                long j26 = j25 + j24;
                long j27 = j26 + j;
                long j28 = j22;
                long j29 = j27 + j;
                long j30 = j29 + j;
                long j31 = j23;
                double d20 = doubleLargeArray.getDouble(j26 - 1);
                double d21 = doubleLargeArray.getDouble(j26);
                long j32 = j7;
                double d22 = doubleLargeArray.getDouble(j27 - 1);
                double d23 = doubleLargeArray.getDouble(j27);
                long j33 = j6;
                double d24 = doubleLargeArray.getDouble(j29 - 1);
                double d25 = doubleLargeArray.getDouble(j29);
                double d26 = doubleLargeArray.getDouble(j30 - 1);
                double d27 = doubleLargeArray.getDouble(j30);
                double d28 = d21 - d25;
                double d29 = d21 + d25;
                double d30 = d23 + d27;
                double d31 = d27 - d23;
                double d32 = d20 - d24;
                double d33 = d20 + d24;
                double d34 = d22 - d26;
                double d35 = d22 + d26;
                double d36 = d33 - d35;
                double d37 = d29 - d30;
                double d38 = i;
                double d39 = d31 * d38;
                double d40 = d32 + d39;
                double d41 = d32 - d39;
                double d42 = d34 * d38;
                double d43 = d28 + d42;
                double d44 = d28 - d42;
                long j34 = j25 + j5;
                long j35 = j25 + j33;
                long j36 = j25 + j32;
                double d45 = this.wtablel.getDouble(j34);
                double d46 = this.wtablel.getDouble(j34 + 1) * d38;
                double d47 = this.wtablel.getDouble(j35);
                double d48 = this.wtablel.getDouble(j35 + 1) * d38;
                double d49 = this.wtablel.getDouble(j36);
                double d50 = d38 * this.wtablel.getDouble(j36 + 1);
                long j37 = j4 + j25 + j31;
                long j38 = j37 + j8;
                long j39 = j38 + j8;
                long j40 = j39 + j8;
                doubleLargeArray2.setDouble(j37, d33 + d35);
                doubleLargeArray2.setDouble(j37 + 1, d29 + d30);
                doubleLargeArray2.setDouble(j38, (d45 * d40) - (d46 * d43));
                doubleLargeArray2.setDouble(j38 + 1, (d45 * d43) + (d46 * d40));
                doubleLargeArray2.setDouble(j39, (d47 * d36) - (d48 * d37));
                doubleLargeArray2.setDouble(j39 + 1, (d47 * d37) + (d48 * d36));
                doubleLargeArray2.setDouble(j40, (d49 * d41) - (d50 * d44));
                doubleLargeArray2.setDouble(j40 + 1, (d49 * d44) + (d50 * d41));
                j25 += 2;
                j9 = 2;
                doubleLargeArray3 = doubleLargeArray2;
                j22 = j28;
                j23 = j31;
                j7 = j32;
                j6 = j33;
            }
            j22++;
            doubleLargeArray3 = doubleLargeArray3;
            j6 = j6;
        }
    }

    void passf5(int i, int i2, double[] dArr, int i3, double[] dArr2, int i4, int i5, int i6) {
        int i7 = i5 + i;
        int i8 = i7 + i;
        int i9 = i8 + i;
        int i10 = i2 * i;
        double d = 0.9510565162951535d;
        double d2 = -0.8090169943749473d;
        double d3 = 0.30901699437494745d;
        if (i == 2) {
            int i11 = 1;
            while (i11 <= i2) {
                int i12 = i3 + (((i11 * 5) - 4) * i) + 1;
                int i13 = i12 + i;
                int i14 = i12 - i;
                int i15 = i13 + i;
                int i16 = i15 + i;
                double d4 = dArr[i12 - 1];
                double d5 = dArr[i12];
                double d6 = dArr[i13 - 1];
                double d7 = dArr[i13];
                double d8 = dArr[i14 - 1];
                double d9 = dArr[i14];
                double d10 = dArr[i15 - 1];
                double d11 = dArr[i15];
                double d12 = dArr[i16 - 1];
                double d13 = dArr[i16];
                double d14 = d5 - d13;
                double d15 = d5 + d13;
                double d16 = d7 - d11;
                double d17 = d7 + d11;
                double d18 = d4 - d12;
                double d19 = d4 + d12;
                double d20 = d6 - d10;
                double d21 = d6 + d10;
                double d22 = d8 + (d19 * 0.30901699437494745d) + (d21 * d2);
                double d23 = d9 + (d15 * 0.30901699437494745d) + (d17 * d2);
                double d24 = d8 + (d19 * d2) + (d21 * 0.30901699437494745d);
                double d25 = d9 + (d15 * d2) + (d17 * 0.30901699437494745d);
                double d26 = i6;
                double d27 = ((d18 * 0.9510565162951535d) + (d20 * 0.5877852522924732d)) * d26;
                double d28 = ((d14 * 0.9510565162951535d) + (d16 * 0.5877852522924732d)) * d26;
                double d29 = ((d18 * 0.5877852522924732d) - (d20 * 0.9510565162951535d)) * d26;
                double d30 = d26 * ((d14 * 0.5877852522924732d) - (d16 * 0.9510565162951535d));
                int i17 = i4 + ((i11 - 1) * i);
                int i18 = i17 + i10;
                int i19 = i18 + i10;
                int i20 = i19 + i10;
                int i21 = i20 + i10;
                dArr2[i17] = d8 + d19 + d21;
                dArr2[i17 + 1] = d9 + d15 + d17;
                dArr2[i18] = d22 - d28;
                dArr2[i18 + 1] = d23 + d27;
                dArr2[i19] = d24 - d30;
                dArr2[i19 + 1] = d25 + d29;
                dArr2[i20] = d24 + d30;
                dArr2[i20 + 1] = d25 - d29;
                dArr2[i21] = d22 + d28;
                dArr2[i21 + 1] = d23 - d27;
                i11++;
                d2 = -0.8090169943749473d;
            }
            return;
        }
        int i22 = 1;
        while (i22 <= i2) {
            int i23 = i3 + 1 + (((i22 * 5) - 4) * i);
            int i24 = i4 + ((i22 - 1) * i);
            int i25 = 0;
            while (i25 < i - 1) {
                int i26 = i25 + i23;
                int i27 = i26 + i;
                int i28 = i26 - i;
                int i29 = i27 + i;
                int i30 = i29 + i;
                double d31 = dArr[i26 - 1];
                double d32 = dArr[i26];
                double d33 = dArr[i27 - 1];
                double d34 = dArr[i27];
                double d35 = dArr[i28 - 1];
                double d36 = dArr[i28];
                double d37 = dArr[i29 - 1];
                double d38 = dArr[i29];
                double d39 = dArr[i30 - 1];
                double d40 = dArr[i30];
                double d41 = d32 - d40;
                double d42 = d32 + d40;
                double d43 = d34 - d38;
                double d44 = d34 + d38;
                double d45 = d31 - d39;
                double d46 = d31 + d39;
                double d47 = d33 - d37;
                double d48 = d33 + d37;
                double d49 = d35 + (d46 * d3) + (d48 * (-0.8090169943749473d));
                double d50 = d36 + (d42 * d3) + (d44 * (-0.8090169943749473d));
                double d51 = d35 + (d46 * (-0.8090169943749473d)) + (d48 * d3);
                double d52 = d36 + (d42 * (-0.8090169943749473d)) + (d44 * d3);
                double d53 = i6;
                double d54 = ((d45 * d) + (d47 * 0.5877852522924732d)) * d53;
                double d55 = ((d41 * d) + (d43 * 0.5877852522924732d)) * d53;
                double d56 = ((d45 * 0.5877852522924732d) - (d47 * d)) * d53;
                double d57 = ((d41 * 0.5877852522924732d) - (d43 * d)) * d53;
                double d58 = d51 - d57;
                double d59 = d51 + d57;
                double d60 = d52 + d56;
                double d61 = d52 - d56;
                double d62 = d49 + d55;
                double d63 = d49 - d55;
                double d64 = d50 - d54;
                double d65 = d50 + d54;
                int i31 = i25 + i5;
                int i32 = i25 + i7;
                int i33 = i25 + i8;
                int i34 = i25 + i9;
                double[] dArr3 = this.wtable;
                double d66 = dArr3[i31];
                double d67 = dArr3[i31 + 1] * d53;
                double d68 = dArr3[i32];
                double d69 = dArr3[i32 + 1] * d53;
                double d70 = dArr3[i33];
                double d71 = dArr3[i33 + 1] * d53;
                double d72 = dArr3[i34];
                double d73 = d53 * dArr3[i34 + 1];
                int i35 = i25 + i24;
                int i36 = i35 + i10;
                int i37 = i36 + i10;
                int i38 = i37 + i10;
                int i39 = i38 + i10;
                dArr2[i35] = d35 + d46 + d48;
                dArr2[i35 + 1] = d36 + d42 + d44;
                dArr2[i36] = (d66 * d63) - (d67 * d65);
                dArr2[i36 + 1] = (d66 * d65) + (d67 * d63);
                dArr2[i37] = (d68 * d58) - (d69 * d60);
                dArr2[i37 + 1] = (d68 * d60) + (d69 * d58);
                dArr2[i38] = (d70 * d59) - (d71 * d61);
                dArr2[i38 + 1] = (d70 * d61) + (d71 * d59);
                dArr2[i39] = (d72 * d62) - (d73 * d64);
                dArr2[i39 + 1] = (d72 * d64) + (d73 * d62);
                i25 += 2;
                d = 0.9510565162951535d;
                d3 = 0.30901699437494745d;
            }
            i22++;
            d = 0.9510565162951535d;
            d3 = 0.30901699437494745d;
        }
    }

    void passf5(long j, long j2, DoubleLargeArray doubleLargeArray, long j3, DoubleLargeArray doubleLargeArray2, long j4, long j5, long j6) {
        DoubleFFT_1D doubleFFT_1D = this;
        long j7 = j5 + j;
        long j8 = j7 + j;
        long j9 = j8 + j;
        long j10 = j2 * j;
        long j11 = 2;
        if (j == 2) {
            long j12 = 1;
            while (j12 <= j2) {
                long j13 = j3 + (((j12 * 5) - 4) * j) + 1;
                long j14 = j13 + j;
                long j15 = j13 - j;
                long j16 = j10;
                long j17 = j14 + j;
                long j18 = j12;
                long j19 = j17 + j;
                double d = doubleLargeArray.getDouble(j13 - 1);
                double d2 = doubleLargeArray.getDouble(j13);
                double d3 = doubleLargeArray.getDouble(j14 - 1);
                double d4 = doubleLargeArray.getDouble(j14);
                double d5 = doubleLargeArray.getDouble(j15 - 1);
                double d6 = doubleLargeArray.getDouble(j15);
                double d7 = doubleLargeArray.getDouble(j17 - 1);
                double d8 = doubleLargeArray.getDouble(j17);
                double d9 = doubleLargeArray.getDouble(j19 - 1);
                double d10 = doubleLargeArray.getDouble(j19);
                double d11 = d2 - d10;
                double d12 = d2 + d10;
                double d13 = d4 - d8;
                double d14 = d4 + d8;
                double d15 = d - d9;
                double d16 = d + d9;
                double d17 = d3 - d7;
                double d18 = d3 + d7;
                double d19 = d5 + (d16 * 0.30901699437494745d) + (d18 * (-0.8090169943749473d));
                double d20 = d6 + (d12 * 0.30901699437494745d) + (d14 * (-0.8090169943749473d));
                double d21 = d5 + (d16 * (-0.8090169943749473d)) + (d18 * 0.30901699437494745d);
                double d22 = d6 + (d12 * (-0.8090169943749473d)) + (d14 * 0.30901699437494745d);
                double d23 = j6;
                double d24 = ((d15 * 0.9510565162951535d) + (d17 * 0.5877852522924732d)) * d23;
                double d25 = ((d11 * 0.9510565162951535d) + (d13 * 0.5877852522924732d)) * d23;
                double d26 = ((d15 * 0.5877852522924732d) - (d17 * 0.9510565162951535d)) * d23;
                double d27 = d23 * ((d11 * 0.5877852522924732d) - (d13 * 0.9510565162951535d));
                long j20 = j4 + ((j18 - 1) * j);
                long j21 = j20 + j16;
                long j22 = j21 + j16;
                long j23 = j22 + j16;
                long j24 = j23 + j16;
                doubleLargeArray2.setDouble(j20, d5 + d16 + d18);
                doubleLargeArray2.setDouble(j20 + 1, d6 + d12 + d14);
                doubleLargeArray2.setDouble(j21, d19 - d25);
                doubleLargeArray2.setDouble(j21 + 1, d20 + d24);
                doubleLargeArray2.setDouble(j22, d21 - d27);
                doubleLargeArray2.setDouble(j22 + 1, d22 + d26);
                doubleLargeArray2.setDouble(j23, d21 + d27);
                doubleLargeArray2.setDouble(j23 + 1, d22 - d26);
                doubleLargeArray2.setDouble(j24, d19 + d25);
                doubleLargeArray2.setDouble(j24 + 1, d20 - d24);
                j12 = j18 + 1;
                j10 = j16;
            }
            return;
        }
        DoubleLargeArray doubleLargeArray3 = doubleLargeArray2;
        long j25 = 1;
        while (j25 <= j2) {
            long j26 = j3 + 1 + (((j25 * 5) - 4) * j);
            long j27 = j4 + ((j25 - 1) * j);
            long j28 = 0;
            while (j28 < j - 1) {
                long j29 = j28 + j26;
                long j30 = j26;
                long j31 = j29 + j;
                long j32 = j25;
                long j33 = j29 - j;
                long j34 = j27;
                long j35 = j31 + j;
                long j36 = j9;
                long j37 = j35 + j;
                long j38 = j8;
                long j39 = j7;
                double d28 = doubleLargeArray.getDouble(j29 - 1);
                double d29 = doubleLargeArray.getDouble(j29);
                double d30 = doubleLargeArray.getDouble(j31 - 1);
                double d31 = doubleLargeArray.getDouble(j31);
                double d32 = doubleLargeArray.getDouble(j33 - 1);
                double d33 = doubleLargeArray.getDouble(j33);
                double d34 = doubleLargeArray.getDouble(j35 - 1);
                double d35 = doubleLargeArray.getDouble(j35);
                double d36 = doubleLargeArray.getDouble(j37 - 1);
                double d37 = doubleLargeArray.getDouble(j37);
                double d38 = d29 - d37;
                double d39 = d29 + d37;
                double d40 = d31 - d35;
                double d41 = d31 + d35;
                double d42 = d28 - d36;
                double d43 = d28 + d36;
                double d44 = d30 - d34;
                double d45 = d30 + d34;
                double d46 = d32 + (d43 * 0.30901699437494745d) + (d45 * (-0.8090169943749473d));
                double d47 = d33 + (d39 * 0.30901699437494745d) + (d41 * (-0.8090169943749473d));
                double d48 = d32 + (d43 * (-0.8090169943749473d)) + (d45 * 0.30901699437494745d);
                double d49 = d33 + (d39 * (-0.8090169943749473d)) + (d41 * 0.30901699437494745d);
                double d50 = j6;
                double d51 = ((d42 * 0.9510565162951535d) + (d44 * 0.5877852522924732d)) * d50;
                double d52 = ((d38 * 0.9510565162951535d) + (d40 * 0.5877852522924732d)) * d50;
                double d53 = ((d42 * 0.5877852522924732d) - (d44 * 0.9510565162951535d)) * d50;
                double d54 = ((d38 * 0.5877852522924732d) - (d40 * 0.9510565162951535d)) * d50;
                double d55 = d48 - d54;
                double d56 = d48 + d54;
                double d57 = d49 + d53;
                double d58 = d49 - d53;
                double d59 = d46 + d52;
                double d60 = d46 - d52;
                double d61 = d47 - d51;
                double d62 = d47 + d51;
                long j40 = j28 + j5;
                long j41 = j28 + j39;
                long j42 = j28 + j38;
                long j43 = j28 + j36;
                double d63 = this.wtablel.getDouble(j40);
                double d64 = this.wtablel.getDouble(j40 + 1) * d50;
                double d65 = this.wtablel.getDouble(j41);
                double d66 = this.wtablel.getDouble(j41 + 1) * d50;
                double d67 = this.wtablel.getDouble(j42);
                double d68 = this.wtablel.getDouble(j42 + 1) * d50;
                double d69 = this.wtablel.getDouble(j43);
                double d70 = d50 * this.wtablel.getDouble(j43 + 1);
                long j44 = j28 + j34;
                long j45 = j44 + j10;
                long j46 = j45 + j10;
                long j47 = j46 + j10;
                long j48 = j47 + j10;
                doubleLargeArray2.setDouble(j44, d32 + d43 + d45);
                doubleLargeArray2.setDouble(j44 + 1, d33 + d39 + d41);
                doubleLargeArray2.setDouble(j45, (d63 * d60) - (d64 * d62));
                doubleLargeArray2.setDouble(j45 + 1, (d63 * d62) + (d64 * d60));
                doubleLargeArray2.setDouble(j46, (d65 * d55) - (d66 * d57));
                doubleLargeArray2.setDouble(j46 + 1, (d65 * d57) + (d66 * d55));
                doubleLargeArray2.setDouble(j47, (d67 * d56) - (d68 * d58));
                doubleLargeArray2.setDouble(j47 + 1, (d67 * d58) + (d68 * d56));
                doubleLargeArray2.setDouble(j48, (d69 * d59) - (d70 * d61));
                doubleLargeArray2.setDouble(j48 + 1, (d69 * d61) + (d70 * d59));
                j28 += 2;
                doubleLargeArray3 = doubleLargeArray2;
                j11 = 2;
                doubleFFT_1D = this;
                j26 = j30;
                j25 = j32;
                j27 = j34;
                j9 = j36;
                j8 = j38;
                j7 = j39;
            }
            j25++;
            doubleFFT_1D = doubleFFT_1D;
            j7 = j7;
        }
    }

    void passfg(int[] iArr, int i, int i2, int i3, int i4, double[] dArr, int i5, double[] dArr2, int i6, int i7, int i8) {
        int i9;
        int i10;
        int i11 = i;
        int i12 = i2;
        int i13 = i11 / 2;
        int i14 = (i12 + 1) / 2;
        int i15 = i12 * i11;
        if (i11 >= i3) {
            for (int i16 = 1; i16 < i14; i16++) {
                int i17 = i16 * i11;
                int i18 = (i12 - i16) * i11;
                for (int i19 = 0; i19 < i3; i19++) {
                    int i20 = i19 * i11;
                    int i21 = i20 + (i17 * i3);
                    int i22 = i20 + (i18 * i3);
                    int i23 = i20 * i12;
                    for (int i24 = 0; i24 < i11; i24++) {
                        int i25 = i6 + i24;
                        int i26 = i5 + i24;
                        double d = dArr[i26 + i17 + i23];
                        double d2 = dArr[i26 + i18 + i23];
                        dArr2[i25 + i21] = d + d2;
                        dArr2[i25 + i22] = d - d2;
                    }
                }
            }
            int i27 = 0;
            while (i27 < i3) {
                int i28 = i27 * i11;
                int i29 = i28 * i12;
                int i30 = i13;
                for (int i31 = 0; i31 < i11; i31++) {
                    dArr2[i6 + i31 + i28] = dArr[i5 + i31 + i29];
                }
                i27++;
                i13 = i30;
            }
            i9 = i13;
            i10 = i15;
        } else {
            i9 = i13;
            int i32 = 1;
            while (i32 < i14) {
                int i33 = i12 - i32;
                int i34 = i32 * i3 * i11;
                int i35 = i33 * i3 * i11;
                int i36 = i32 * i11;
                int i37 = i33 * i11;
                int i38 = i15;
                for (int i39 = 0; i39 < i11; i39++) {
                    for (int i40 = 0; i40 < i3; i40++) {
                        int i41 = i40 * i11;
                        int i42 = i41 * i12;
                        int i43 = i5 + i39;
                        double d3 = dArr[i43 + i36 + i42];
                        double d4 = dArr[i43 + i37 + i42];
                        int i44 = i6 + i39 + i41;
                        dArr2[i44 + i34] = d3 + d4;
                        dArr2[i44 + i35] = d3 - d4;
                    }
                }
                i32++;
                i15 = i38;
            }
            i10 = i15;
            for (int i45 = 0; i45 < i11; i45++) {
                for (int i46 = 0; i46 < i3; i46++) {
                    int i47 = i46 * i11;
                    dArr2[i6 + i45 + i47] = dArr[i5 + i45 + (i47 * i12)];
                }
            }
        }
        int i48 = 2 - i11;
        int i49 = (i12 - 1) * i4;
        int i50 = i48;
        int i51 = 1;
        int i52 = 0;
        while (i51 < i14) {
            int i53 = i50 + i11;
            int i54 = i51 * i4;
            int i55 = (i12 - i51) * i4;
            int i56 = i53 + i7;
            int i57 = i48;
            double[] dArr3 = this.wtable;
            double d5 = dArr3[i56 - 2];
            double d6 = i8;
            double d7 = dArr3[i56 - 1] * d6;
            for (int i58 = 0; i58 < i4; i58++) {
                int i59 = i5 + i58;
                int i60 = i6 + i58;
                dArr[i59 + i54] = dArr2[i60] + (dArr2[i60 + i4] * d5);
                dArr[i59 + i55] = dArr2[i60 + i49] * d7;
            }
            i52 += i11;
            int i61 = i53;
            int i62 = 2;
            while (i62 < i14) {
                double d8 = d6;
                int i63 = i2 - i62;
                int i64 = i49;
                int i65 = i61 + i52;
                int i66 = i52;
                int i67 = i10;
                if (i65 > i67) {
                    i65 -= i67;
                }
                int i68 = i65 + i7;
                int i69 = i65;
                double[] dArr4 = this.wtable;
                double d9 = dArr4[i68 - 2];
                double d10 = dArr4[i68 - 1] * d8;
                int i70 = i62 * i4;
                int i71 = i63 * i4;
                i10 = i67;
                for (int i72 = 0; i72 < i4; i72++) {
                    int i73 = i5 + i72;
                    int i74 = i6 + i72;
                    int i75 = i73 + i54;
                    dArr[i75] = dArr[i75] + (dArr2[i74 + i70] * d9);
                    int i76 = i73 + i55;
                    dArr[i76] = dArr[i76] + (dArr2[i74 + i71] * d10);
                }
                i62++;
                i52 = i66;
                d6 = d8;
                i49 = i64;
                i61 = i69;
            }
            i12 = i2;
            i51++;
            i48 = i57;
            i50 = i53;
        }
        int i77 = i48;
        for (int i78 = 1; i78 < i14; i78++) {
            int i79 = i78 * i4;
            for (int i80 = 0; i80 < i4; i80++) {
                int i81 = i6 + i80;
                dArr2[i81] = dArr2[i81] + dArr2[i81 + i79];
            }
        }
        for (int i82 = 1; i82 < i14; i82++) {
            int i83 = i82 * i4;
            int i84 = (i12 - i82) * i4;
            for (int i85 = 1; i85 < i4; i85 += 2) {
                int i86 = i6 + i85;
                int i87 = i5 + i85;
                int i88 = i87 + i83;
                int i89 = i87 + i84;
                double d11 = dArr[i88 - 1];
                double d12 = dArr[i88];
                double d13 = dArr[i89 - 1];
                double d14 = dArr[i89];
                int i90 = i86 + i83;
                int i91 = i86 + i84;
                dArr2[i90 - 1] = d11 - d14;
                dArr2[i91 - 1] = d11 + d14;
                dArr2[i90] = d12 + d13;
                dArr2[i91] = d12 - d13;
            }
        }
        iArr[0] = 1;
        if (i11 == 2) {
            return;
        }
        iArr[0] = 0;
        System.arraycopy(dArr2, i6, dArr, i5, i4);
        int i92 = i3 * i11;
        for (int i93 = 1; i93 < i12; i93++) {
            int i94 = i93 * i92;
            for (int i95 = 0; i95 < i3; i95++) {
                int i96 = i95 * i11;
                int i97 = i6 + i96 + i94;
                int i98 = i96 + i5 + i94;
                dArr[i98] = dArr2[i97];
                dArr[i98 + 1] = dArr2[i97 + 1];
            }
        }
        if (i9 <= i3) {
            int i99 = 0;
            int i100 = 1;
            while (i100 < i12) {
                i99 += 2;
                int i101 = i100 * i3 * i11;
                int i102 = 3;
                while (i102 < i11) {
                    int i103 = i99 + 2;
                    int i104 = (i103 + i7) - 1;
                    double[] dArr5 = this.wtable;
                    double d15 = dArr5[i104 - 1];
                    int i105 = i100;
                    double d16 = ((double) i8) * dArr5[i104];
                    int i106 = i5 + i102;
                    int i107 = i6 + i102;
                    for (int i108 = 0; i108 < i3; i108++) {
                        int i109 = (i108 * i11) + i101;
                        int i110 = i106 + i109;
                        int i111 = i107 + i109;
                        double d17 = dArr2[i111 - 1];
                        double d18 = dArr2[i111];
                        dArr[i110 - 1] = (d15 * d17) - (d16 * d18);
                        dArr[i110] = (d18 * d15) + (d17 * d16);
                    }
                    i102 += 2;
                    i99 = i103;
                    i100 = i105;
                }
                i100++;
            }
            return;
        }
        int i112 = 1;
        int i113 = i77;
        while (i112 < i12) {
            i113 += i11;
            int i114 = i112 * i3 * i11;
            int i115 = 0;
            while (i115 < i3) {
                int i116 = (i115 * i11) + i114;
                int i117 = i113;
                int i118 = 3;
                while (i118 < i11) {
                    i117 += 2;
                    int i119 = (i117 - 1) + i7;
                    double[] dArr6 = this.wtable;
                    double d19 = dArr6[i119 - 1];
                    double d20 = ((double) i8) * dArr6[i119];
                    int i120 = i5 + i118 + i116;
                    int i121 = i6 + i118 + i116;
                    double d21 = dArr2[i121 - 1];
                    double d22 = dArr2[i121];
                    dArr[i120 - 1] = (d19 * d21) - (d20 * d22);
                    dArr[i120] = (d19 * d22) + (d20 * d21);
                    i118 += 2;
                    i11 = i;
                    i114 = i114;
                }
                i115++;
                i11 = i;
            }
            i112++;
            i11 = i;
            i12 = i2;
        }
    }

    void passfg(int[] iArr, long j, long j2, long j3, long j4, DoubleLargeArray doubleLargeArray, long j5, DoubleLargeArray doubleLargeArray2, long j6, long j7, long j8) {
        long j9;
        long j10;
        DoubleLargeArray doubleLargeArray3 = doubleLargeArray;
        DoubleLargeArray doubleLargeArray4 = doubleLargeArray2;
        long j11 = j / 2;
        long j12 = (j2 + 1) / 2;
        long j13 = j2 * j;
        if (j >= j3) {
            for (long j14 = 1; j14 < j12; j14++) {
                long j15 = j14 * j;
                long j16 = (j2 - j14) * j;
                for (long j17 = 0; j17 < j3; j17++) {
                    long j18 = j17 * j;
                    long j19 = j18 + (j15 * j3);
                    long j20 = j18 + (j16 * j3);
                    long j21 = j18 * j2;
                    long j22 = 0;
                    while (j22 < j) {
                        long j23 = j6 + j22;
                        long j24 = j5 + j22;
                        double d = doubleLargeArray3.getDouble(j24 + j15 + j21);
                        long j25 = j16;
                        double d2 = doubleLargeArray3.getDouble(j24 + j16 + j21);
                        doubleLargeArray4.setDouble(j23 + j19, d + d2);
                        doubleLargeArray4.setDouble(j23 + j20, d - d2);
                        j22++;
                        j13 = j13;
                        j16 = j25;
                    }
                }
            }
            j9 = j13;
            for (long j26 = 0; j26 < j3; j26++) {
                long j27 = j26 * j;
                long j28 = j27 * j2;
                long j29 = 0;
                while (j29 < j) {
                    doubleLargeArray4.setDouble(j6 + j29 + j27, doubleLargeArray3.getDouble(j5 + j29 + j28));
                    j29++;
                    j27 = j27;
                }
            }
            j10 = j12;
        } else {
            j9 = j13;
            for (long j30 = 1; j30 < j12; j30++) {
                long j31 = j2 - j30;
                long j32 = j30 * j3 * j;
                long j33 = j31 * j3 * j;
                long j34 = j30 * j;
                long j35 = j31 * j;
                for (long j36 = 0; j36 < j; j36++) {
                    long j37 = 0;
                    while (j37 < j3) {
                        long j38 = j37 * j;
                        long j39 = j38 * j2;
                        long j40 = j5 + j36;
                        long j41 = j34;
                        double d3 = doubleLargeArray3.getDouble(j40 + j34 + j39);
                        long j42 = j35;
                        double d4 = doubleLargeArray3.getDouble(j40 + j35 + j39);
                        long j43 = j6 + j36 + j38;
                        doubleLargeArray4.setDouble(j43 + j32, d3 + d4);
                        doubleLargeArray4.setDouble(j43 + j33, d3 - d4);
                        j37++;
                        j12 = j12;
                        j32 = j32;
                        j35 = j42;
                        j34 = j41;
                    }
                }
            }
            j10 = j12;
            for (long j44 = 0; j44 < j; j44++) {
                for (long j45 = 0; j45 < j3; j45++) {
                    long j46 = j45 * j;
                    doubleLargeArray4.setDouble(j6 + j44 + j46, doubleLargeArray3.getDouble(j5 + j44 + (j46 * j2)));
                }
            }
        }
        long j47 = 2 - j;
        long j48 = (j2 - 1) * j4;
        long j49 = j47;
        long j50 = 1;
        long j51 = 0;
        while (j50 < j10) {
            long j52 = j49 + j;
            long j53 = j50 * j4;
            long j54 = (j2 - j50) * j4;
            long j55 = j52 + j7;
            long j56 = j47;
            double d5 = this.wtablel.getDouble(j55 - 2);
            long j57 = j50;
            double d6 = j8;
            long j58 = j11;
            double d7 = this.wtablel.getDouble(j55 - 1) * d6;
            long j59 = 0;
            while (j59 < j4) {
                long j60 = j5 + j59;
                double d8 = d6;
                long j61 = j6 + j59;
                double d9 = d7;
                doubleLargeArray3.setDouble(j60 + j53, doubleLargeArray4.getDouble(j61) + (doubleLargeArray4.getDouble(j61 + j4) * d5));
                doubleLargeArray3.setDouble(j60 + j54, d9 * doubleLargeArray4.getDouble(j61 + j48));
                j59++;
                d6 = d8;
                j51 = j51;
                d7 = d9;
            }
            double d10 = d6;
            j51 += j;
            long j62 = j52;
            long j63 = 2;
            while (j63 < j10) {
                long j64 = j2 - j63;
                long j65 = j62 + j51;
                if (j65 > j9) {
                    j65 -= j9;
                }
                long j66 = j65 + j7;
                long j67 = j48;
                long j68 = j65;
                double d11 = this.wtablel.getDouble(j66 - 2);
                long j69 = j51;
                double d12 = this.wtablel.getDouble(j66 - 1) * d10;
                long j70 = j63 * j4;
                long j71 = j64 * j4;
                long j72 = 0;
                while (j72 < j4) {
                    long j73 = j5 + j72;
                    long j74 = j6 + j72;
                    long j75 = j63;
                    long j76 = j73 + j53;
                    double d13 = d12;
                    doubleLargeArray3.setDouble(j76, doubleLargeArray3.getDouble(j76) + (doubleLargeArray4.getDouble(j74 + j70) * d11));
                    long j77 = j73 + j54;
                    doubleLargeArray3.setDouble(j77, doubleLargeArray3.getDouble(j77) + (doubleLargeArray4.getDouble(j74 + j71) * d13));
                    j72++;
                    d11 = d11;
                    j63 = j75;
                    d12 = d13;
                }
                j63++;
                j48 = j67;
                j62 = j68;
                j51 = j69;
            }
            j50 = j57 + 1;
            j49 = j52;
            j47 = j56;
            j11 = j58;
        }
        long j78 = j47;
        long j79 = j11;
        for (long j80 = 1; j80 < j10; j80++) {
            long j81 = j80 * j4;
            for (long j82 = 0; j82 < j4; j82++) {
                long j83 = j6 + j82;
                doubleLargeArray4.setDouble(j83, doubleLargeArray4.getDouble(j83) + doubleLargeArray4.getDouble(j83 + j81));
            }
        }
        long j84 = 1;
        while (j84 < j10) {
            long j85 = j84 * j4;
            long j86 = (j2 - j84) * j4;
            long j87 = 1;
            while (j87 < j4) {
                long j88 = j6 + j87;
                long j89 = j5 + j87;
                long j90 = j84;
                long j91 = j89 + j85;
                long j92 = j89 + j86;
                long j93 = j87;
                double d14 = doubleLargeArray3.getDouble(j91 - 1);
                double d15 = doubleLargeArray3.getDouble(j91);
                double d16 = doubleLargeArray3.getDouble(j92 - 1);
                double d17 = doubleLargeArray3.getDouble(j92);
                long j94 = j88 + j85;
                long j95 = j88 + j86;
                doubleLargeArray4.setDouble(j94 - 1, d14 - d17);
                doubleLargeArray4.setDouble(j95 - 1, d14 + d17);
                doubleLargeArray4.setDouble(j94, d15 + d16);
                doubleLargeArray4.setDouble(j95, d15 - d16);
                j87 = j93 + 2;
                doubleLargeArray3 = doubleLargeArray;
                j84 = j90;
                j86 = j86;
                j85 = j85;
            }
            doubleLargeArray3 = doubleLargeArray;
            j84++;
        }
        iArr[0] = 1;
        if (j == 2) {
            return;
        }
        iArr[0] = 0;
        DoubleFFT_1D doubleFFT_1D = this;
        LargeArrayUtils.arraycopy(doubleLargeArray2, j6, doubleLargeArray, j5, j4);
        long j96 = j3 * j;
        for (long j97 = 1; j97 < j2; j97++) {
            long j98 = j97 * j96;
            long j99 = 0;
            while (j99 < j3) {
                long j100 = j99 * j;
                long j101 = j6 + j100 + j98;
                long j102 = j5 + j100 + j98;
                doubleLargeArray.setDouble(j102, doubleLargeArray4.getDouble(j101));
                doubleLargeArray.setDouble(j102 + 1, doubleLargeArray4.getDouble(j101 + 1));
                j99++;
                j96 = j96;
            }
        }
        if (j79 <= j3) {
            long j103 = 1;
            long j104 = 0;
            while (j103 < j2) {
                long j105 = 2;
                j104 += 2;
                long j106 = j103 * j3 * j;
                long j107 = 3;
                while (j107 < j) {
                    long j108 = j104 + j105;
                    long j109 = (j108 + j7) - 1;
                    double d18 = doubleFFT_1D.wtablel.getDouble(j109 - 1);
                    long j110 = j103;
                    double d19 = j8 * doubleFFT_1D.wtablel.getDouble(j109);
                    long j111 = j5 + j107;
                    long j112 = j6 + j107;
                    long j113 = 0;
                    while (j113 < j3) {
                        long j114 = (j113 * j) + j106;
                        long j115 = j106;
                        long j116 = j111 + j114;
                        long j117 = j111;
                        long j118 = j112 + j114;
                        double d20 = doubleLargeArray4.getDouble(j118 - 1);
                        double d21 = doubleLargeArray4.getDouble(j118);
                        doubleLargeArray.setDouble(j116 - 1, (d18 * d20) - (d19 * d21));
                        doubleLargeArray.setDouble(j116, (d21 * d18) + (d20 * d19));
                        j113++;
                        j106 = j115;
                        j111 = j117;
                    }
                    j107 += 2;
                    j105 = 2;
                    j104 = j108;
                    j103 = j110;
                }
                j103++;
            }
            return;
        }
        long j119 = 1;
        long j120 = j78;
        while (j119 < j2) {
            j120 += j;
            long j121 = j119 * j3 * j;
            long j122 = 0;
            while (j122 < j3) {
                long j123 = (j122 * j) + j121;
                long j124 = j120;
                long j125 = 3;
                while (j125 < j) {
                    long j126 = j124 + 2;
                    long j127 = j121;
                    long j128 = (j126 - 1) + j7;
                    long j129 = j120;
                    double d22 = doubleFFT_1D.wtablel.getDouble(j128 - 1);
                    long j130 = j119;
                    long j131 = j122;
                    double d23 = j8 * doubleFFT_1D.wtablel.getDouble(j128);
                    long j132 = j5 + j125 + j123;
                    long j133 = j6 + j125 + j123;
                    long j134 = j123;
                    double d24 = doubleLargeArray4.getDouble(j133 - 1);
                    double d25 = doubleLargeArray4.getDouble(j133);
                    doubleLargeArray.setDouble(j132 - 1, (d22 * d24) - (d23 * d25));
                    doubleLargeArray.setDouble(j132, (d22 * d25) + (d23 * d24));
                    j125 += 2;
                    doubleFFT_1D = this;
                    doubleLargeArray4 = doubleLargeArray2;
                    j119 = j130;
                    j120 = j129;
                    j121 = j127;
                    j124 = j126;
                    j122 = j131;
                    j123 = j134;
                }
                j122++;
                doubleFFT_1D = this;
                doubleLargeArray4 = doubleLargeArray2;
                j119 = j119;
            }
            doubleFFT_1D = this;
            doubleLargeArray4 = doubleLargeArray2;
            j119++;
        }
    }
}
