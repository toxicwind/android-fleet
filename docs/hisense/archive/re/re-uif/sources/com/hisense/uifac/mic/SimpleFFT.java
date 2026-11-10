package com.hisense.uifac.mic;

import android.util.Log;
import org.jtransforms.fft.DoubleFFT_1D;

/* JADX INFO: loaded from: classes.dex */
public class SimpleFFT {
    private int mBlockSize;
    private int mChannels;
    private short[] mData;
    private short[] mRefData;
    private int mSampleRate;
    private double mFreq = 0.0d;
    private double mVolumeDB = 0.0d;
    private double mOneKiloHzDB = 0.0d;
    private double mOneKiloHzRef = 0.0d;

    public SimpleFFT(short[] sArr, short[] sArr2, int i, int i2) {
        this.mRefData = null;
        this.mData = sArr;
        this.mRefData = sArr2;
        this.mSampleRate = i;
        this.mChannels = i2;
        this.mBlockSize = Math.min(sArr.length, sArr2.length);
        Log.d("SimpleFFT", "mData : " + sArr.length);
        Log.d("SimpleFFT", "mRefData : " + sArr2.length);
        Log.d("SimpleFFT", "mSampleRate : " + this.mSampleRate);
        Log.d("SimpleFFT", "mChannels : " + this.mChannels);
        Log.d("SimpleFFT", "mBlockSize : " + this.mBlockSize);
    }

    private void computeFFT() {
        int i = this.mBlockSize;
        double[] dArr = new double[i * 2];
        int i2 = i / 2;
        double[] dArr2 = new double[i2];
        DoubleFFT_1D doubleFFT_1D = new DoubleFFT_1D(this.mBlockSize);
        computeRefFFT();
        for (int i3 = 0; i3 < this.mBlockSize; i3++) {
            dArr[i3] = this.mData[i3];
        }
        doubleFFT_1D.complexForward(dArr);
        int i4 = 0;
        int i5 = 0;
        double d = 0.0d;
        for (int i6 = 0; i6 < i2; i6++) {
            int i7 = i6 * 2;
            double d2 = dArr[i7] * dArr[i7];
            int i8 = i7 + 1;
            dArr2[i6] = Math.sqrt(d2 + (dArr[i8] * dArr[i8]));
            if (dArr2[i6] > d) {
                d = dArr2[i6];
                i4 = i6 / 2;
                i5 = i6;
            }
        }
        this.mFreq = (this.mSampleRate * i4) / (this.mBlockSize / this.mChannels);
        Log.d("SimpleFFT", String.format("maxIndex:%d, maxMag:%f freq:%f", Integer.valueOf(i4), Double.valueOf(d), Double.valueOf(this.mFreq)));
        int i9 = (((this.mBlockSize / this.mChannels) * 1000) / this.mSampleRate) * 2;
        Log.d("SimpleFFT", String.format("maxDataIndex = %d targetIndex = %d", Integer.valueOf(i5), Integer.valueOf(i9)));
        int i10 = i9 * 2;
        double d3 = dArr[i10] * dArr[i10];
        int i11 = i10 + 1;
        double dSqrt = Math.sqrt(d3 + (dArr[i11] * dArr[i11]));
        this.mOneKiloHzDB = dSqrt * dSqrt;
        if (i9 > 30 && i9 < i2 - 30) {
            for (int i12 = 1; i12 < 30; i12++) {
                int i13 = i9 - i12;
                double d4 = this.mOneKiloHzDB + (dArr2[i13] * dArr2[i13]);
                this.mOneKiloHzDB = d4;
                int i14 = i9 + i12;
                this.mOneKiloHzDB = d4 + (dArr2[i14] * dArr2[i14]);
            }
        }
        Log.d("SimpleFFT", String.format("mOneKiloHz : %.1f\tmOneKiloHzRef : %.1f", Double.valueOf(this.mOneKiloHzDB), Double.valueOf(this.mOneKiloHzRef)));
        double d5 = this.mOneKiloHzDB;
        double d6 = this.mOneKiloHzRef;
        if (d6 <= 0.0d) {
            d6 = 1.0d;
        }
        double dLog10 = Math.log10(d5 / d6) * 10.0d;
        this.mOneKiloHzDB = dLog10;
        Log.d("SimpleFFT", String.format("mOneKiloHzDB : %.1f", Double.valueOf(dLog10)));
    }

    private void computeRefFFT() {
        int i;
        double d = 0.0d;
        if (this.mOneKiloHzDB > 0.0d) {
            return;
        }
        int i2 = this.mBlockSize;
        double[] dArr = new double[i2 * 2];
        int i3 = i2 / 2;
        double[] dArr2 = new double[i3];
        DoubleFFT_1D doubleFFT_1D = new DoubleFFT_1D(this.mBlockSize);
        int i4 = 0;
        for (int i5 = 0; i5 < this.mBlockSize; i5++) {
            dArr[i5] = this.mRefData[i5];
        }
        doubleFFT_1D.complexForward(dArr);
        int i6 = 0;
        while (true) {
            if (i4 >= i3) {
                break;
            }
            int i7 = i4 * 2;
            double d2 = dArr[i7] * dArr[i7];
            int i8 = i7 + 1;
            dArr2[i4] = Math.sqrt(d2 + (dArr[i8] * dArr[i8]));
            if (dArr2[i4] > d) {
                d = dArr2[i4];
                i6 = i4;
            }
            i4++;
        }
        int i9 = i6 * 2;
        double d3 = dArr[i9] * dArr[i9];
        int i10 = i9 + 1;
        double dSqrt = Math.sqrt(d3 + (dArr[i10] * dArr[i10]));
        this.mOneKiloHzRef = dSqrt * dSqrt;
        if (i6 <= 30 || i6 >= i3 - 30) {
            return;
        }
        for (i = 1; i < 30; i++) {
            int i11 = i6 - i;
            double d4 = this.mOneKiloHzRef + (dArr2[i11] * dArr2[i11]);
            this.mOneKiloHzRef = d4;
            int i12 = i6 + i;
            this.mOneKiloHzRef = d4 + (dArr2[i12] * dArr2[i12]);
        }
    }

    public double getFrequency() {
        if (this.mVolumeDB != 0.0d) {
            return this.mFreq;
        }
        computeFFT();
        return this.mFreq;
    }

    public double getDB() {
        double d = this.mVolumeDB;
        double dAbs = 0.0d;
        if (d > 0.0d) {
            return d;
        }
        int i = 0;
        while (true) {
            int i2 = this.mBlockSize;
            if (i < i2) {
                dAbs += (double) Math.abs((int) this.mData[i]);
                i++;
            } else {
                double dLog10 = Math.log10((dAbs / ((double) i2)) / 32767.0d) * 20.0d;
                this.mVolumeDB = dLog10;
                Log.d("SimpleFFT", String.format("DB:%f", Double.valueOf(dLog10)));
                return this.mVolumeDB;
            }
        }
    }

    public double getOneKiloHzDB() {
        double d = this.mOneKiloHzDB;
        if (d != 0.0d) {
            return d;
        }
        computeFFT();
        return this.mOneKiloHzDB;
    }
}
