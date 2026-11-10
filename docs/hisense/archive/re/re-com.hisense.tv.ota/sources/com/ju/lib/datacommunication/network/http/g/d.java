package com.ju.lib.datacommunication.network.http.g;

/* JADX INFO: loaded from: classes.dex */
public class d {
    public static byte[] a(String str) {
        int length = str.length();
        int i = length / 2;
        if (length % 2 != 0) {
            str = "0" + str;
            i++;
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int i3 = i2 + 1;
            int i4 = i3 * 2;
            int i5 = i4 - 1;
            bArr[i2] = b(str.substring(i2 * 2, i5), str.substring(i5, i4));
            i2 = i3;
        }
        return bArr;
    }

    private static byte b(String str, String str2) {
        return (byte) (((byte) (Byte.decode("0x" + str).byteValue() << 4)) ^ Byte.decode("0x" + str2).byteValue());
    }
}
