package b.d.a.a.b;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final char[] f82a = "0123456789ABCDEF".toCharArray();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final byte[] f83b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f84c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f85d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f86e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int[] f87f;
    public static final int[] g;
    public static final int[] h;
    public static final int[] i;

    static {
        int i2;
        int length = f82a.length;
        f83b = new byte[length];
        for (int i3 = 0; i3 < length; i3++) {
            f83b[i3] = (byte) f82a[i3];
        }
        int[] iArr = new int[256];
        for (int i4 = 0; i4 < 32; i4++) {
            iArr[i4] = -1;
        }
        iArr[34] = 1;
        iArr[92] = 1;
        f84c = iArr;
        int[] iArr2 = f84c;
        int[] iArr3 = new int[iArr2.length];
        System.arraycopy(iArr2, 0, iArr3, 0, iArr3.length);
        for (int i5 = 128; i5 < 256; i5++) {
            if ((i5 & 224) == 192) {
                i2 = 2;
            } else if ((i5 & 240) == 224) {
                i2 = 3;
            } else {
                i2 = (i5 & 248) == 240 ? 4 : -1;
            }
            iArr3[i5] = i2;
        }
        f85d = iArr3;
        int[] iArr4 = new int[256];
        Arrays.fill(iArr4, -1);
        for (int i6 = 33; i6 < 256; i6++) {
            if (Character.isJavaIdentifierPart((char) i6)) {
                iArr4[i6] = 0;
            }
        }
        iArr4[64] = 0;
        iArr4[35] = 0;
        iArr4[42] = 0;
        iArr4[45] = 0;
        iArr4[43] = 0;
        f86e = iArr4;
        int[] iArr5 = new int[256];
        System.arraycopy(f86e, 0, iArr5, 0, iArr5.length);
        Arrays.fill(iArr5, 128, 128, 0);
        f87f = iArr5;
        int[] iArr6 = new int[256];
        System.arraycopy(f85d, 128, iArr6, 128, 128);
        Arrays.fill(iArr6, 0, 32, -1);
        iArr6[9] = 0;
        iArr6[10] = 10;
        iArr6[13] = 13;
        iArr6[42] = 42;
        g = iArr6;
        int[] iArr7 = new int[256];
        System.arraycopy(f85d, 128, iArr7, 128, 128);
        Arrays.fill(iArr7, 0, 32, -1);
        iArr7[32] = 1;
        iArr7[9] = 1;
        iArr7[10] = 10;
        iArr7[13] = 13;
        iArr7[47] = 47;
        iArr7[35] = 35;
        int[] iArr8 = new int[128];
        for (int i7 = 0; i7 < 32; i7++) {
            iArr8[i7] = -1;
        }
        iArr8[34] = 34;
        iArr8[92] = 92;
        iArr8[8] = 98;
        iArr8[9] = 116;
        iArr8[12] = 102;
        iArr8[10] = 110;
        iArr8[13] = 114;
        h = iArr8;
        i = new int[128];
        Arrays.fill(i, -1);
        for (int i8 = 0; i8 < 10; i8++) {
            i[i8 + 48] = i8;
        }
        for (int i9 = 0; i9 < 6; i9++) {
            int[] iArr9 = i;
            int i10 = i9 + 10;
            iArr9[i9 + 97] = i10;
            iArr9[i9 + 65] = i10;
        }
    }

    public static int[] a() {
        return g;
    }

    public static int a(int i2) {
        if (i2 > 127) {
            return -1;
        }
        return i[i2];
    }
}
