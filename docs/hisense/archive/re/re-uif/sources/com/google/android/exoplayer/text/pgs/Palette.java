package com.google.android.exoplayer.text.pgs;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class Palette {
    private final byte[] a;
    private final byte[] b;
    private final byte[] cb;
    private final byte[] cr;
    private final byte[] g;
    private final byte[] r;
    private final int size;
    private final boolean useBT601;
    private final byte[] y;

    public Palette(int i, boolean z) {
        this.size = i;
        this.useBT601 = z;
        this.r = new byte[i];
        this.g = new byte[i];
        this.b = new byte[i];
        this.a = new byte[i];
        this.y = new byte[i];
        this.cb = new byte[i];
        this.cr = new byte[i];
        int[] iArrRGB2YCbCr = ColorSpaceUtils.RGB2YCbCr(0, 0, 0, z);
        for (int i2 = 0; i2 < i; i2++) {
            this.a[i2] = 0;
            this.r[i2] = 0;
            this.g[i2] = 0;
            this.b[i2] = 0;
            this.y[i2] = (byte) iArrRGB2YCbCr[0];
            this.cb[i2] = (byte) iArrRGB2YCbCr[1];
            this.cr[i2] = (byte) iArrRGB2YCbCr[2];
        }
    }

    public int getARGB(int i) {
        return (this.b[i] & 255) | ((this.a[i] & 255) << 24) | ((this.r[i] & 255) << 16) | ((this.g[i] & 255) << 8);
    }

    public void setYCbCr(int i, int i2, int i3, int i4) {
        this.y[i] = (byte) i2;
        this.cb[i] = (byte) i3;
        this.cr[i] = (byte) i4;
        int[] iArrYCbCr2RGB = ColorSpaceUtils.YCbCr2RGB(i2, i3, i4, this.useBT601);
        this.r[i] = (byte) iArrYCbCr2RGB[0];
        this.g[i] = (byte) iArrYCbCr2RGB[1];
        this.b[i] = (byte) iArrYCbCr2RGB[2];
    }

    public void setAlpha(int i, int i2) {
        this.a[i] = (byte) i2;
    }

    public int getAlpha(int i) {
        return this.a[i] & 255;
    }

    public byte[] getAlpha() {
        byte[] bArr = this.a;
        return Arrays.copyOf(bArr, bArr.length);
    }

    public byte[] getY() {
        byte[] bArr = this.y;
        return Arrays.copyOf(bArr, bArr.length);
    }

    public int getSize() {
        return this.size;
    }

    public int getIndexOfMostTransparentPaletteEntry() {
        int i = 256;
        int i2 = 0;
        for (int i3 = 0; i3 < this.size; i3++) {
            byte[] bArr = this.a;
            if ((bArr[i3] & 255) < i) {
                int i4 = bArr[i3] & 255;
                if (i4 == 0) {
                    return i3;
                }
                i = i4;
                i2 = i3;
            }
        }
        return i2;
    }
}
