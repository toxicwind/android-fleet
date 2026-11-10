package com.google.android.exoplayer.text.pgs;

import android.graphics.Bitmap;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import java.nio.IntBuffer;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class BitmapBuffer {
    private Bitmap bmp;
    private final byte[] buffer;
    private final int height;
    private Palette pal;
    private final int width;

    public BitmapBuffer(int i, int i2) {
        this.width = i;
        this.height = i2;
        this.buffer = new byte[i * i2];
    }

    public BitmapBuffer(int i, int i2, byte b) {
        this(i, i2);
        fillWithColorIndexValue(b);
    }

    public void setPal(Palette palette) {
        this.pal = palette;
    }

    private void fillWithColorIndexValue(byte b) {
        for (int i = 0; i < this.width * this.height; i++) {
            this.buffer[i] = b;
        }
    }

    public int getPrimaryColorIndex(byte[] bArr, int i, byte[] bArr2) {
        int[] iArrCreateHistogram = createHistogram(bArr.length);
        weightHistogramWithAlphaAndLumaValues(iArrCreateHistogram, bArr, i, bArr2);
        return getColorIndexWithTheHighestValue(iArrCreateHistogram);
    }

    private int[] createHistogram(int i) {
        int[] iArr = new int[i];
        Arrays.fill(iArr, 0);
        for (byte b : this.buffer) {
            int i2 = b & 255;
            iArr[i2] = iArr[i2] + 1;
        }
        return iArr;
    }

    private void weightHistogramWithAlphaAndLumaValues(int[] iArr, byte[] bArr, int i, byte[] bArr2) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            int i3 = bArr[i2] & 255;
            if (i3 < i) {
                i3 = 0;
            }
            iArr[i2] = ((iArr[i2] * i3) + 128) / 256;
            iArr[i2] = ((iArr[i2] * (bArr2[i2] & 255)) + 128) / 256;
        }
    }

    private int getColorIndexWithTheHighestValue(int[] iArr) {
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            if (iArr[i3] > i2) {
                i2 = iArr[i3];
                i = i3;
            }
        }
        return i;
    }

    public byte[] getInternalBuffer() {
        return this.buffer;
    }

    public Bitmap getBitmap() {
        if (this.bmp == null) {
            this.bmp = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888);
            IntBuffer intBufferAllocate = IntBuffer.allocate(this.width * this.height);
            MtkDebugLogUtil.log("PGSParserLog", "rgbs = " + this.pal.getSize(), 16);
            int i = 0;
            while (true) {
                byte[] bArr = this.buffer;
                if (i >= bArr.length) {
                    break;
                }
                intBufferAllocate.put(this.pal.getARGB(bArr[i] & 255));
                i++;
            }
            intBufferAllocate.rewind();
            this.bmp.copyPixelsFromBuffer(intBufferAllocate);
        }
        return this.bmp;
    }
}
