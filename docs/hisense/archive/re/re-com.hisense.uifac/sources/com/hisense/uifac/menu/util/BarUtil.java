package com.hisense.uifac.menu.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class BarUtil {
    public static Bitmap generateBarcode(String str, int i, int i2) {
        try {
            BitMatrix bitMatrixEncode = new MultiFormatWriter().encode(str, BarcodeFormat.CODE_128, i, 1);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitMatrixEncode.getWidth(), i2, Bitmap.Config.RGB_565);
            for (int i3 = 0; i3 < bitMatrixEncode.getWidth(); i3++) {
                int[] iArr = new int[i2];
                Arrays.fill(iArr, bitMatrixEncode.get(i3, 0) ? -16777216 : -1);
                bitmapCreateBitmap.setPixels(iArr, 0, 1, i3, 0, 1, i2);
            }
            return bitmapCreateBitmap;
        } catch (Exception unused) {
            System.out.println("error generating barcode");
            return null;
        }
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int i) {
        if (bitmap == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate(i);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
        if (bitmapCreateBitmap != bitmap && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return bitmapCreateBitmap;
    }
}
