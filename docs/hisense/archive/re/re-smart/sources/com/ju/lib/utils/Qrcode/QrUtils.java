package com.ju.lib.utils.Qrcode;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import androidx.core.view.ViewCompat;
import b.a.a.a.a;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;
import java.util.Hashtable;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class QrUtils {

    static class QRUtilCodeWriter implements Writer {
        public static final int QUIET_ZONE_SIZE = 4;

        private int calculateScale(int i, int i2) {
            if (i >= i2) {
                return 0;
            }
            return i2 / i;
        }

        private BitMatrix renderResult(QRCode qRCode, int i, int i2, int i3) {
            ByteMatrix matrix = qRCode.getMatrix();
            if (matrix == null) {
                throw new IllegalStateException();
            }
            int width = matrix.getWidth();
            int height = matrix.getHeight();
            int i4 = i3 * 2;
            int i5 = width + i4;
            int i6 = i4 + height;
            int iMin = Math.min(i, i2);
            int iCalculateScale = calculateScale(i5, iMin);
            if (iCalculateScale > 0) {
                int i7 = iCalculateScale * i5;
                int i8 = (((iMin - i7) / 4) * i3) + i7;
                if (i == i2) {
                    i = i8;
                    i2 = i;
                } else if (i > i2) {
                    i = (i * i8) / i2;
                    i2 = i8;
                } else {
                    i2 = (i2 * i8) / i;
                    i = i8;
                }
            }
            int iMax = Math.max(i, i5);
            int iMax2 = Math.max(i2, i6);
            int iMin2 = Math.min(iMax / i5, iMax2 / i6);
            int i9 = (iMax - (width * iMin2)) / 2;
            int i10 = (iMax2 - (height * iMin2)) / 2;
            BitMatrix bitMatrix = new BitMatrix(iMax, iMax2);
            int i11 = 0;
            while (i11 < height) {
                int i12 = 0;
                int i13 = i9;
                while (i12 < width) {
                    if (matrix.get(i12, i11) == 1) {
                        bitMatrix.setRegion(i13, i10, iMin2, iMin2);
                    }
                    i12++;
                    i13 += iMin2;
                }
                i11++;
                i10 += iMin2;
            }
            return bitMatrix;
        }

        public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2) {
            return encode(str, barcodeFormat, i, i2, null);
        }

        public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) {
            if (str.isEmpty()) {
                throw new IllegalArgumentException("Found empty contents");
            }
            if (barcodeFormat != BarcodeFormat.QR_CODE) {
                throw new IllegalArgumentException(a.a("Can only encode QR_CODE, but got ", barcodeFormat));
            }
            if (i < 0 || i2 < 0) {
                throw new IllegalArgumentException("Requested dimensions are too small: " + i + 'x' + i2);
            }
            ErrorCorrectionLevel errorCorrectionLevelValueOf = ErrorCorrectionLevel.L;
            int i3 = 4;
            if (map != null) {
                if (map.containsKey(EncodeHintType.ERROR_CORRECTION)) {
                    errorCorrectionLevelValueOf = ErrorCorrectionLevel.valueOf(map.get(EncodeHintType.ERROR_CORRECTION).toString());
                }
                if (map.containsKey(EncodeHintType.MARGIN)) {
                    try {
                        i3 = Integer.parseInt(map.get(EncodeHintType.MARGIN).toString());
                    } catch (NumberFormatException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            return renderResult(Encoder.encode(str, errorCorrectionLevelValueOf, map), i, i2, i3);
        }
    }

    public static Bitmap addLogo(Bitmap bitmap, Bitmap bitmap2, int i) {
        if (bitmap == null) {
            return null;
        }
        if (bitmap2 == null) {
            return bitmap;
        }
        if (i == 0) {
            i = 4;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int width2 = bitmap2.getWidth();
        int height2 = bitmap2.getHeight();
        if (width == 0 || height == 0) {
            return null;
        }
        if (width2 == 0 || height2 == 0) {
            return bitmap;
        }
        float f2 = ((width * 1.0f) / i) / width2;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            canvas.scale(f2, f2, width / 2, height / 2);
            canvas.drawBitmap(bitmap2, (width - width2) / 2, (height - height2) / 2, (Paint) null);
            canvas.save();
            canvas.restore();
            return bitmapCreateBitmap;
        } catch (Exception e2) {
            e2.getStackTrace();
            return null;
        }
    }

    public static Bitmap bitMatrix2Bitmap(BitMatrix bitMatrix, int i, int i2, Bitmap bitmap, boolean z) {
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        int[] iArr = new int[width * height];
        for (int i3 = 0; i3 < width; i3++) {
            for (int i4 = 0; i4 < height; i4++) {
                iArr[(i4 * width) + i3] = bitMatrix.get(i3, i4) ? i : i2;
            }
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, z ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        bitmapCreateBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return bitmap != null ? addLogo(bitmapCreateBitmap, bitmap, 4) : bitmapCreateBitmap;
    }

    public static Bitmap createQRCode(String str, int i, int i2, int i3, int i4, Bitmap bitmap, int i5, boolean z) {
        if (str != null) {
            try {
                if (!"".equals(str) && str.length() >= 1) {
                    Hashtable hashtable = new Hashtable();
                    hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
                    if (i5 > 0) {
                        hashtable.put(EncodeHintType.MARGIN, Integer.valueOf(i5));
                    }
                    hashtable.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
                    return bitMatrix2Bitmap(new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, i, i2, hashtable), i3, i4, bitmap, z);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static Bitmap createQRCodeWithNormal(String str, int i, boolean z) {
        return createQRCodeWithNormal(str, i, z, null);
    }

    public static Bitmap createQRCodeWithPadding(String str, int i, int i2, int i3, boolean z) {
        return createQRCodeWithPadding(str, i, i2, i3, z, null);
    }

    public static Bitmap createQRCodeWithThin(String str, int i) {
        return createQRCodeWithThin(str, i, i, ViewCompat.MEASURED_STATE_MASK, -1, null);
    }

    public static Bitmap createQRCodeWithNormal(String str, int i, boolean z, Bitmap bitmap) {
        try {
            Hashtable hashtable = new Hashtable();
            hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
            return bitMatrix2Bitmap(new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, i, i, hashtable), ViewCompat.MEASURED_STATE_MASK, -1, bitmap, z);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Bitmap createQRCodeWithPadding(String str, int i, int i2, int i3, boolean z, Bitmap bitmap) {
        if (str != null) {
            try {
                if (!"".equals(str) && str.length() >= 1) {
                    Hashtable hashtable = new Hashtable();
                    hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
                    hashtable.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
                    BitMatrix bitMatrixEncode = new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, i, i2, hashtable);
                    int width = bitMatrixEncode.getWidth();
                    int height = bitMatrixEncode.getHeight();
                    int[] iArr = new int[width * height];
                    int i4 = 0;
                    boolean z2 = false;
                    int i5 = 0;
                    int i6 = 0;
                    while (i4 < i2) {
                        int i7 = i5;
                        boolean z3 = z2;
                        for (int i8 = 0; i8 < i; i8++) {
                            if (bitMatrixEncode.get(i8, i4)) {
                                if (!z3) {
                                    i6 = i4;
                                    i7 = i8;
                                    z3 = true;
                                }
                                iArr[(i4 * i) + i8] = -16777216;
                            } else {
                                iArr[(i4 * i) + i8] = -1;
                            }
                        }
                        i4++;
                        z2 = z3;
                        i5 = i7;
                    }
                    Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, z ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
                    bitmapCreateBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
                    if (bitmap != null) {
                        bitmapCreateBitmap = addLogo(bitmapCreateBitmap, bitmap, 6);
                    }
                    if (i5 <= i3) {
                        return bitmapCreateBitmap;
                    }
                    int i9 = i5 - i3;
                    int i10 = i6 - i3;
                    if (i9 >= 0 && i10 >= 0) {
                        return Bitmap.createBitmap(bitmapCreateBitmap, i9, i10, i - (i9 * 2), i2 - (i10 * 2));
                    }
                    return bitmapCreateBitmap;
                }
            } catch (WriterException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static Bitmap createQRCodeWithThin(String str, int i, int i2, Bitmap bitmap) {
        return createQRCodeWithThin(str, i, i2, ViewCompat.MEASURED_STATE_MASK, -1, bitmap);
    }

    public static Bitmap createQRCodeWithThin(String str, int i, int i2, int i3, int i4, Bitmap bitmap) {
        try {
            Hashtable hashtable = new Hashtable();
            hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hashtable.put(EncodeHintType.MARGIN, 1);
            hashtable.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            Bitmap bitmapBitMatrix2Bitmap = bitMatrix2Bitmap(new QRUtilCodeWriter().encode(str, BarcodeFormat.QR_CODE, i, i2, hashtable), i3, i4, bitmap, false);
            Log.i("utils", "bitmap=" + bitmapBitMatrix2Bitmap);
            return bitmapBitMatrix2Bitmap;
        } catch (Exception e2) {
            Log.i("utils", "e=" + e2);
            e2.printStackTrace();
            return null;
        }
    }
}
