package com.google.android.exoplayer.extractor.mp4;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BitmapInfoHeader {
    public short biBitCount;
    public int biClrImportant;
    public int biClrUsed;
    public int biCompression;
    public int biHeight;
    public short biPlanes;
    public int biSize;
    public int biSizeImage;
    public int biWidth;
    public int biXPelsPerMeter;
    public int biYPelsPerMeter;
    public byte[] data;

    private static byte[] intToByteArray(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    private static byte[] shortToByteArray(short s) {
        return new byte[]{(byte) ((s >> 8) & 255), (byte) (s & 255)};
    }

    BitmapInfoHeader(int i, int i2, int i3, short s, short s2, int i4, int i5, int i6, int i7, int i8, int i9, byte[] bArr) {
        this.biSize = i;
        this.biWidth = i2;
        this.biHeight = i3;
        this.biPlanes = s;
        this.biBitCount = s2;
        this.biCompression = i4;
        this.biSizeImage = i5;
        this.biXPelsPerMeter = i6;
        this.biYPelsPerMeter = i7;
        this.biClrUsed = i8;
        this.biClrImportant = i9;
        this.data = bArr;
    }

    public List<byte[]> constructBitMapinfoHeader() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(mergeBytes(mergeBytes(mergeBytes(mergeBytes(mergeBytes(mergeBytes(mergeBytes(mergeBytes(mergeBytes(mergeBytes(mergeBytes(intToByteArray(this.biSize), intToByteArray(this.biWidth)), intToByteArray(this.biHeight)), shortToByteArray(this.biPlanes)), shortToByteArray(this.biBitCount)), intToByteArray(this.biCompression)), intToByteArray(this.biSizeImage)), intToByteArray(this.biXPelsPerMeter)), intToByteArray(this.biYPelsPerMeter)), intToByteArray(this.biClrUsed)), intToByteArray(this.biClrImportant)), this.data));
        return arrayList;
    }

    private static byte[] mergeBytes(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }
}
