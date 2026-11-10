package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class Code128Writer extends OneDimensionalCodeWriter {
    @Override // com.google.zxing.oned.OneDimensionalCodeWriter, com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat != BarcodeFormat.CODE_128) {
            throw new IllegalArgumentException("Can only encode CODE_128, but got " + barcodeFormat);
        }
        return super.encode(str, barcodeFormat, i, i2, map);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    public boolean[] encode(String str) {
        int length = str.length();
        if (length < 1 || length > 80) {
            throw new IllegalArgumentException("Contents length should be between 1 and 80 characters, but got " + length);
        }
        int iAppendPattern = 0;
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt < ' ' || cCharAt > '~') {
                switch (cCharAt) {
                    case 241:
                    case 242:
                    case 243:
                    case 244:
                        break;
                    default:
                        throw new IllegalArgumentException("Bad character in input: " + cCharAt);
                }
            }
        }
        ArrayList<int[]> arrayList = new ArrayList();
        int i2 = 1;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 < length) {
            int iCharAt = 100;
            int i6 = isDigits(str, i3, i5 == 99 ? 2 : 4) ? 99 : 100;
            if (i6 == i5) {
                if (i5 == 100) {
                    iCharAt = str.charAt(i3) - ' ';
                } else {
                    switch (str.charAt(i3)) {
                        case 241:
                            iCharAt = 102;
                            break;
                        case 242:
                            iCharAt = 97;
                            break;
                        case 243:
                            iCharAt = 96;
                            break;
                        case 244:
                            break;
                        default:
                            int i7 = i3 + 2;
                            iCharAt = Integer.parseInt(str.substring(i3, i7));
                            i3 = i7;
                            break;
                    }
                }
                i3++;
            } else {
                iCharAt = i5 == 0 ? i6 == 100 ? 104 : MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_DRM_NOT_SUPPORT : i6;
                i5 = i6;
            }
            arrayList.add(Code128Reader.CODE_PATTERNS[iCharAt]);
            i4 += iCharAt * i2;
            if (i3 != 0) {
                i2++;
            }
        }
        arrayList.add(Code128Reader.CODE_PATTERNS[i4 % 103]);
        arrayList.add(Code128Reader.CODE_PATTERNS[106]);
        int i8 = 0;
        for (int[] iArr : arrayList) {
            for (int i9 : iArr) {
                i8 += i9;
            }
        }
        boolean[] zArr = new boolean[i8];
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            iAppendPattern += OneDimensionalCodeWriter.appendPattern(zArr, iAppendPattern, (int[]) it.next(), true);
        }
        return zArr;
    }

    private static boolean isDigits(CharSequence charSequence, int i, int i2) {
        int i3 = i2 + i;
        int length = charSequence.length();
        while (i < i3 && i < length) {
            char cCharAt = charSequence.charAt(i);
            if (cCharAt < '0' || cCharAt > '9') {
                if (cCharAt != 241) {
                    return false;
                }
                i3++;
            }
            i++;
        }
        return i3 <= length;
    }
}
