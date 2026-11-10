package com.google.android.exoplayer.extractor.ogg;

import com.google.android.exoplayer.util.Assertions;

/* JADX INFO: loaded from: classes.dex */
final class VorbisBitArray {
    private int bitOffset;
    private int byteOffset;
    public final byte[] data;
    private int limit;

    public VorbisBitArray(byte[] bArr) {
        this(bArr, bArr.length);
    }

    public VorbisBitArray(byte[] bArr, int i) {
        this.data = bArr;
        this.limit = i * 8;
    }

    public boolean readBit() {
        return readBits(1) == 1;
    }

    public int readBits(int i) {
        int iMin;
        int i2;
        Assertions.checkState(getPosition() + i <= this.limit);
        if (i == 0) {
            return 0;
        }
        int i3 = this.bitOffset;
        if (i3 != 0) {
            iMin = Math.min(i, 8 - i3);
            byte[] bArr = this.data;
            int i4 = this.byteOffset;
            byte b = bArr[i4];
            int i5 = this.bitOffset;
            i2 = (255 >>> (8 - iMin)) & (b >>> i5);
            int i6 = i5 + iMin;
            this.bitOffset = i6;
            if (i6 == 8) {
                this.byteOffset = i4 + 1;
                this.bitOffset = 0;
            }
        } else {
            iMin = 0;
            i2 = 0;
        }
        int i7 = i - iMin;
        if (i7 > 7) {
            int i8 = i7 / 8;
            for (int i9 = 0; i9 < i8; i9++) {
                byte[] bArr2 = this.data;
                int i10 = this.byteOffset;
                this.byteOffset = i10 + 1;
                i2 = (int) (((long) i2) | ((((long) bArr2[i10]) & 255) << iMin));
                iMin += 8;
            }
        }
        if (i <= iMin) {
            return i2;
        }
        int i11 = i - iMin;
        int i12 = i2 | (((255 >>> (8 - i11)) & this.data[this.byteOffset]) << iMin);
        this.bitOffset += i11;
        return i12;
    }

    public void skipBits(int i) {
        Assertions.checkState(getPosition() + i <= this.limit);
        int i2 = this.byteOffset + (i / 8);
        this.byteOffset = i2;
        int i3 = this.bitOffset + (i % 8);
        this.bitOffset = i3;
        if (i3 > 7) {
            this.byteOffset = i2 + 1;
            this.bitOffset = i3 - 8;
        }
    }

    public int getPosition() {
        return (this.byteOffset * 8) + this.bitOffset;
    }
}
