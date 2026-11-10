package com.hmct.cloud.sdk.utils;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Struct {
    public static void arrayCopy(char[] cArr, f[] fVarArr) {
        arrayCopy(cArr, fVarArr, Math.min(cArr.length, fVarArr.length));
    }

    public static void arraySwitch(byte[] bArr, f[] fVarArr) {
        arraySwitch(bArr, fVarArr, Math.min(bArr.length, fVarArr.length));
    }

    public abstract void init();

    public static void arrayCopy(char[] cArr, f[] fVarArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            fVarArr[i2].a(cArr[i2]);
        }
    }

    public static void arraySwitch(byte[] bArr, f[] fVarArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            fVarArr[i2] = new f(bArr[i2]);
        }
        while (i < fVarArr.length) {
            fVarArr[i] = new f();
            i++;
        }
    }

    public static void arrayCopy(byte[] bArr, f[] fVarArr) {
        arrayCopy(bArr, fVarArr, Math.min(bArr.length, fVarArr.length));
    }

    public static void arrayCopy(byte[] bArr, f[] fVarArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            fVarArr[i2].a(bArr[i2]);
        }
    }

    public static void arrayCopy(char[] cArr, char[] cArr2) {
        arrayCopy(cArr, cArr2, Math.min(cArr.length, cArr2.length));
    }

    public static void arrayCopy(char[] cArr, char[] cArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            cArr2[i2] = cArr[i2];
        }
    }

    public static void arrayCopy(f[] fVarArr, byte[] bArr) {
        arrayCopy(fVarArr, bArr, Math.min(fVarArr.length, bArr.length));
    }

    public static void arrayCopy(f[] fVarArr, byte[] bArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = new byte[]{fVarArr[i2].b()}[0];
        }
    }

    public static void arrayCopy(f[] fVarArr, char[] cArr) {
        arrayCopy(fVarArr, cArr, Math.min(fVarArr.length, cArr.length));
    }

    public static void arrayCopy(f[] fVarArr, char[] cArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = (char) fVarArr[i2].a();
        }
    }

    public static void arrayCopy(char[] cArr, int i, byte[] bArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i + i4;
            if (i5 >= cArr.length) {
                return;
            }
            bArr[i2 + i4] = (byte) cArr[i5];
        }
    }

    public static void arrayCopy(byte[] bArr, int i, char[] cArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i + i4;
            if (i5 >= bArr.length) {
                return;
            }
            cArr[i2 + i4] = (char) bArr[i5];
        }
    }

    public static void arrayCopy(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i + i4;
            if (i5 >= bArr.length) {
                return;
            }
            bArr2[i2 + i4] = bArr[i5];
        }
    }

    public static void arrayCopy(f[] fVarArr, int i, byte[] bArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i + i4;
            if (i5 >= fVarArr.length) {
                return;
            }
            bArr[i2 + i4] = (byte) fVarArr[i5].a();
        }
    }

    public static void arrayCopy(byte[] bArr, int i, f[] fVarArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i + i4;
            if (i5 >= bArr.length) {
                return;
            }
            fVarArr[i2 + i4].a(bArr[i5]);
        }
    }
}
