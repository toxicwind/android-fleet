package com.ju.lib.datacommunication.network.http.address;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class ByteUtils {
    public static String byteArr2Str(byte[] bArr, int i) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (int i2 : bArr) {
            while (i2 < 0) {
                i2 += 256;
            }
            if (i2 < 16) {
                stringBuffer.append("0");
            }
            stringBuffer.append(Integer.toString(i2, i));
        }
        return stringBuffer.toString();
    }

    public static byte[] byteArrayXor(byte[] bArr, byte[] bArr2) {
        int i;
        int i2;
        int length = bArr.length;
        int length2 = bArr2.length;
        if (length < length2) {
            i2 = length;
            i = length2;
        } else {
            i = length;
            i2 = length2;
        }
        byte[] bArr3 = new byte[i];
        if (i == length) {
            System.arraycopy(bArr, 0, bArr3, 0, length);
        } else {
            System.arraycopy(bArr2, 0, bArr3, 0, length2);
        }
        for (int i3 = 0; i3 < i2; i3++) {
            bArr3[i3] = (byte) (bArr[i3] ^ bArr2[i3]);
        }
        return bArr3;
    }

    public static void main(String[] strArr) {
    }

    public static byte[] string2ByteArray(String str) {
        int length = str.length();
        int i = length / 2;
        if (length % 2 != 0) {
            str = a.a("0", str);
            i++;
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int i3 = i2 + 1;
            int i4 = i3 * 2;
            int i5 = i4 - 1;
            bArr[i2] = uniteBytes(str.substring(i2 * 2, i5), str.substring(i5, i4));
            i2 = i3;
        }
        return bArr;
    }

    public static byte uniteBytes(String str, String str2) {
        return (byte) (((byte) (Byte.decode("0x" + str).byteValue() << 4)) ^ Byte.decode("0x" + str2).byteValue());
    }
}
