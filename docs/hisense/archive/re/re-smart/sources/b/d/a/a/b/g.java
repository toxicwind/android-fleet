package b.d.a.a.b;

import com.hmct.cloud.sdk.utils.Constants;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f104a = 1000000;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static int f105b = 1000000000;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static long f106c = 10000000000L;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static long f107d = 1000;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static long f108e = -2147483648L;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static long f109f = 2147483647L;
    public static final String g = String.valueOf(Long.MIN_VALUE);
    public static final char[] h = new char[4000];
    public static final char[] i = new char[4000];
    public static final byte[] j;

    static {
        int i2 = 0;
        int i3 = 0;
        while (i2 < 10) {
            char c2 = (char) (i2 + 48);
            char c3 = i2 == 0 ? (char) 0 : c2;
            int i4 = i3;
            int i5 = 0;
            while (i5 < 10) {
                char c4 = (char) (i5 + 48);
                char c5 = (i2 == 0 && i5 == 0) ? (char) 0 : c4;
                int i6 = i4;
                for (int i7 = 0; i7 < 10; i7++) {
                    char c6 = (char) (i7 + 48);
                    char[] cArr = h;
                    cArr[i6] = c3;
                    int i8 = i6 + 1;
                    cArr[i8] = c5;
                    int i9 = i6 + 2;
                    cArr[i9] = c6;
                    char[] cArr2 = i;
                    cArr2[i6] = c2;
                    cArr2[i8] = c4;
                    cArr2[i9] = c6;
                    i6 += 4;
                }
                i5++;
                i4 = i6;
            }
            i2++;
            i3 = i4;
        }
        j = new byte[4000];
        for (int i10 = 0; i10 < 4000; i10++) {
            j[i10] = (byte) i[i10];
        }
        new String[]{"0", "1", Constants.LANGUAGE_FRENCH, "3", "4", "5", "6", "7", "8", "9", "10"};
        new String[]{"-1", "-2", "-3", "-4", "-5", "-6", "-7", "-8", "-9", "-10"};
    }

    public static int a(long j2, char[] cArr, int i2) {
        if (j2 < 0) {
            if (j2 > f108e) {
                return c((int) j2, cArr, i2);
            }
            if (j2 == Long.MIN_VALUE) {
                int length = g.length();
                g.getChars(0, length, cArr, i2);
                return i2 + length;
            }
            cArr[i2] = '-';
            j2 = -j2;
            i2++;
        } else if (j2 <= f109f) {
            return c((int) j2, cArr, i2);
        }
        int iA = a(j2) + i2;
        int i3 = iA;
        while (j2 > f109f) {
            i3 -= 3;
            long j3 = f107d;
            long j4 = j2 / j3;
            a((int) (j2 - (j3 * j4)), cArr, i3);
            j2 = j4;
        }
        int i4 = (int) j2;
        while (i4 >= 1000) {
            i3 -= 3;
            int i5 = i4 / 1000;
            a(i4 - (i5 * 1000), cArr, i3);
            i4 = i5;
        }
        b(i4, cArr, i2);
        return iA;
    }

    public static int b(int i2, char[] cArr, int i3) {
        int i4 = i2 << 2;
        int i5 = i4 + 1;
        char c2 = h[i4];
        if (c2 != 0) {
            cArr[i3] = c2;
            i3++;
        }
        int i6 = i5 + 1;
        char c3 = h[i5];
        if (c3 != 0) {
            cArr[i3] = c3;
            i3++;
        }
        int i7 = i3 + 1;
        cArr[i3] = h[i6];
        return i7;
    }

    public static int c(int i2, char[] cArr, int i3) {
        int i4;
        if (i2 < 0) {
            if (i2 == Integer.MIN_VALUE) {
                return a(i2, cArr, i3);
            }
            cArr[i3] = '-';
            i2 = -i2;
            i3++;
        }
        if (i2 < f104a) {
            if (i2 >= 1000) {
                int i5 = i2 / 1000;
                return a(i2 - (i5 * 1000), cArr, b(i5, cArr, i3));
            }
            if (i2 >= 10) {
                return b(i2, cArr, i3);
            }
            int i6 = i3 + 1;
            cArr[i3] = (char) (i2 + 48);
            return i6;
        }
        boolean z = i2 >= f105b;
        if (z) {
            int i7 = f105b;
            i2 -= i7;
            if (i2 >= i7) {
                i2 -= i7;
                i4 = i3 + 1;
                cArr[i3] = '2';
            } else {
                i4 = i3 + 1;
                cArr[i3] = '1';
            }
        } else {
            i4 = i3;
        }
        int i8 = i2 / 1000;
        int i9 = i8 / 1000;
        return a(i2 - (i8 * 1000), cArr, a(i8 - (i9 * 1000), cArr, z ? a(i9, cArr, i4) : b(i9, cArr, i4)));
    }

    public static int b(int i2, byte[] bArr, int i3) {
        int i4 = i2 << 2;
        int i5 = i4 + 1;
        char c2 = h[i4];
        if (c2 != 0) {
            bArr[i3] = (byte) c2;
            i3++;
        }
        int i6 = i5 + 1;
        char c3 = h[i5];
        if (c3 != 0) {
            bArr[i3] = (byte) c3;
            i3++;
        }
        int i7 = i3 + 1;
        bArr[i3] = (byte) h[i6];
        return i7;
    }

    public static int a(long j2, byte[] bArr, int i2) {
        if (j2 < 0) {
            if (j2 > f108e) {
                return c((int) j2, bArr, i2);
            }
            if (j2 == Long.MIN_VALUE) {
                int length = g.length();
                int i3 = 0;
                while (i3 < length) {
                    bArr[i2] = (byte) g.charAt(i3);
                    i3++;
                    i2++;
                }
                return i2;
            }
            bArr[i2] = 45;
            j2 = -j2;
            i2++;
        } else if (j2 <= f109f) {
            return c((int) j2, bArr, i2);
        }
        int iA = a(j2) + i2;
        int i4 = iA;
        while (j2 > f109f) {
            i4 -= 3;
            long j3 = f107d;
            long j4 = j2 / j3;
            a((int) (j2 - (j3 * j4)), bArr, i4);
            j2 = j4;
        }
        int i5 = (int) j2;
        while (i5 >= 1000) {
            i4 -= 3;
            int i6 = i5 / 1000;
            a(i5 - (i6 * 1000), bArr, i4);
            i5 = i6;
        }
        b(i5, bArr, i2);
        return iA;
    }

    public static int c(int i2, byte[] bArr, int i3) {
        int i4;
        int iB;
        if (i2 < 0) {
            if (i2 == Integer.MIN_VALUE) {
                return a(i2, bArr, i3);
            }
            bArr[i3] = 45;
            i2 = -i2;
            i3++;
        }
        if (i2 < f104a) {
            if (i2 >= 1000) {
                int i5 = i2 / 1000;
                return a(i2 - (i5 * 1000), bArr, b(i5, bArr, i3));
            }
            if (i2 < 10) {
                int i6 = i3 + 1;
                bArr[i3] = (byte) (i2 + 48);
                return i6;
            }
            return b(i2, bArr, i3);
        }
        boolean z = i2 >= f105b;
        if (z) {
            int i7 = f105b;
            i2 -= i7;
            if (i2 >= i7) {
                i2 -= i7;
                i4 = i3 + 1;
                bArr[i3] = 50;
            } else {
                i4 = i3 + 1;
                bArr[i3] = 49;
            }
        } else {
            i4 = i3;
        }
        int i8 = i2 / 1000;
        int i9 = i2 - (i8 * 1000);
        int i10 = i8 / 1000;
        int i11 = i8 - (i10 * 1000);
        if (z) {
            iB = a(i10, bArr, i4);
        } else {
            iB = b(i10, bArr, i4);
        }
        return a(i9, bArr, a(i11, bArr, iB));
    }

    public static int a(int i2, char[] cArr, int i3) {
        int i4 = i2 << 2;
        int i5 = i3 + 1;
        char[] cArr2 = i;
        int i6 = i4 + 1;
        cArr[i3] = cArr2[i4];
        int i7 = i5 + 1;
        cArr[i5] = cArr2[i6];
        int i8 = i7 + 1;
        cArr[i7] = cArr2[i6 + 1];
        return i8;
    }

    public static int a(int i2, byte[] bArr, int i3) {
        int i4 = i2 << 2;
        int i5 = i3 + 1;
        byte[] bArr2 = j;
        int i6 = i4 + 1;
        bArr[i3] = bArr2[i4];
        int i7 = i5 + 1;
        bArr[i5] = bArr2[i6];
        int i8 = i7 + 1;
        bArr[i7] = bArr2[i6 + 1];
        return i8;
    }

    public static int a(long j2) {
        int i2 = 10;
        for (long j3 = f106c; j2 >= j3 && i2 != 19; j3 = (j3 << 1) + (j3 << 3)) {
            i2++;
        }
        return i2;
    }
}
