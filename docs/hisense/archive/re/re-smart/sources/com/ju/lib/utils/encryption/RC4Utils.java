package com.ju.lib.utils.encryption;

import android.text.TextUtils;
import com.google.common.base.Ascii;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes2.dex */
public class RC4Utils {
    public static char[] MAP1 = new char[64];
    public static byte[] MAP2 = new byte[128];
    public static final String TAG = "RC4Utils";

    static {
        char c2 = 'A';
        int i = 0;
        while (c2 <= 'Z') {
            MAP1[i] = c2;
            c2 = (char) (c2 + 1);
            i++;
        }
        char c3 = 'a';
        while (c3 <= 'z') {
            MAP1[i] = c3;
            c3 = (char) (c3 + 1);
            i++;
        }
        char c4 = '0';
        while (c4 <= '9') {
            MAP1[i] = c4;
            c4 = (char) (c4 + 1);
            i++;
        }
        char[] cArr = MAP1;
        cArr[i] = '+';
        cArr[i + 1] = '/';
        for (int i2 = 0; i2 < 128; i2++) {
            MAP2[i2] = -1;
        }
        for (int i3 = 0; i3 < 64; i3++) {
            MAP2[MAP1[i3]] = (byte) i3;
        }
    }

    public static byte[] _base(byte[] bArr, String str) {
        byte[] bArr_init = _init(str);
        byte[] bArr2 = new byte[bArr.length];
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            i = (i + 1) & 255;
            i2 = ((bArr_init[i] & 255) + i2) & 255;
            byte b2 = bArr_init[i];
            bArr_init[i] = bArr_init[i2];
            bArr_init[i2] = b2;
            bArr2[i3] = (byte) (bArr_init[((bArr_init[i] & 255) + (bArr_init[i2] & 255)) & 255] ^ bArr[i3]);
        }
        return bArr2;
    }

    public static byte[] _decode(String str) {
        return _decode(str.toCharArray());
    }

    public static byte[] _init(String str) {
        byte[] bytes;
        try {
            bytes = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            bytes = null;
        }
        byte[] bArr = new byte[256];
        for (int i = 0; i < 256; i++) {
            bArr[i] = (byte) i;
        }
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        int length = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < 256; i3++) {
            i2 = ((bytes[length] & 255) + (bArr[i3] & 255) + i2) & 255;
            byte b2 = bArr[i3];
            bArr[i3] = bArr[i2];
            bArr[i2] = b2;
            length = (length + 1) % bytes.length;
        }
        return bArr;
    }

    public static String decode(String str) {
        return decode(str, "jhxddup");
    }

    public static int[] decodeOneStep(int i, int i2, int i3, int i4) {
        byte[] bArr = MAP2;
        byte b2 = bArr[i];
        byte b3 = bArr[i2];
        byte b4 = bArr[i3];
        byte b5 = bArr[i4];
        if (b2 < 0 || b3 < 0 || b4 < 0 || b5 < 0) {
            throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
        }
        return new int[]{(b2 << 2) | (b3 >>> 4), ((b3 & Ascii.SI) << 4) | (b4 >>> 2), ((b4 & 3) << 6) | b5};
    }

    public static byte[] _decode(char[] cArr) {
        return _decode(cArr, 0, cArr.length);
    }

    public static String decode(String str, String str2) {
        try {
            return TextUtils.isEmpty(str) ? str : new String(_base(_decode(str.toCharArray()), str2), Charset.defaultCharset());
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static byte[] _decode(char[] cArr, int i, int i2) {
        int i3;
        char c2;
        if (i2 % 4 == 0) {
            while (i2 > 0 && cArr[(i + i2) - 1] == '=') {
                i2--;
            }
            int i4 = (i2 * 3) / 4;
            byte[] bArr = new byte[i4];
            int i5 = i2 + i;
            int i6 = 0;
            while (i < i5) {
                int i7 = i + 1;
                char c3 = cArr[i];
                int i8 = i7 + 1;
                char c4 = cArr[i7];
                char c5 = 'A';
                if (i8 < i5) {
                    i3 = i8 + 1;
                    c2 = cArr[i8];
                } else {
                    i3 = i8;
                    c2 = 'A';
                }
                if (i3 < i5) {
                    int i9 = i3 + 1;
                    char c6 = cArr[i3];
                    i3 = i9;
                    c5 = c6;
                }
                if (c3 <= 127 && c4 <= 127 && c2 <= 127 && c5 <= 127) {
                    int[] iArrDecodeOneStep = decodeOneStep(c3, c4, c2, c5);
                    int i10 = i6 + 1;
                    bArr[i6] = (byte) iArrDecodeOneStep[0];
                    if (i10 < i4) {
                        i6 = i10 + 1;
                        bArr[i10] = (byte) iArrDecodeOneStep[1];
                    } else {
                        i6 = i10;
                    }
                    if (i6 < i4) {
                        bArr[i6] = (byte) iArrDecodeOneStep[2];
                        i6++;
                    }
                    i = i3;
                } else {
                    throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
                }
            }
            return bArr;
        }
        throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4.");
    }
}
