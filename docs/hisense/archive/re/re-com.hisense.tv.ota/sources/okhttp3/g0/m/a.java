package okhttp3.g0.m;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.IDN;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.g0.e;
import okhttp3.g0.k.k;
import okio.j;
import okio.n;
import okio.t;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    private static final byte[] e = {42};
    private static final String[] f = new String[0];
    private static final String[] g = {"*"};
    private static final a h = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final AtomicBoolean f213a = new AtomicBoolean(false);
    private final CountDownLatch b = new CountDownLatch(1);
    private byte[] c;
    private byte[] d;

    private static String a(byte[] bArr, byte[][] bArr2, int i) {
        int i2;
        boolean z;
        int i3;
        int i4;
        int length = bArr.length;
        int i5 = 0;
        while (i5 < length) {
            int i6 = (i5 + length) / 2;
            while (i6 > -1 && bArr[i6] != 10) {
                i6--;
            }
            int i7 = i6 + 1;
            int i8 = 1;
            while (true) {
                i2 = i7 + i8;
                if (bArr[i2] == 10) {
                    break;
                }
                i8++;
            }
            int i9 = i2 - i7;
            int i10 = i;
            boolean z2 = false;
            int i11 = 0;
            int i12 = 0;
            while (true) {
                if (z2) {
                    i3 = 46;
                    z = false;
                } else {
                    z = z2;
                    i3 = bArr2[i10][i11] & 255;
                }
                i4 = i3 - (bArr[i7 + i12] & 255);
                if (i4 == 0) {
                    i12++;
                    i11++;
                    if (i12 == i9) {
                        break;
                    }
                    if (bArr2[i10].length != i11) {
                        z2 = z;
                    } else {
                        if (i10 == bArr2.length - 1) {
                            break;
                        }
                        i10++;
                        i11 = -1;
                        z2 = true;
                    }
                } else {
                    break;
                }
            }
            if (i4 >= 0) {
                if (i4 <= 0) {
                    int i13 = i9 - i12;
                    int length2 = bArr2[i10].length - i11;
                    while (true) {
                        i10++;
                        if (i10 >= bArr2.length) {
                            break;
                        }
                        length2 += bArr2[i10].length;
                    }
                    if (length2 >= i13) {
                        if (length2 <= i13) {
                            return new String(bArr, i7, i9, e.i);
                        }
                    }
                }
                i5 = i2 + 1;
            }
            length = i7 - 1;
        }
        return null;
    }

    private String[] b(String[] strArr) {
        String str;
        String strA;
        String strA2;
        String[] strArrSplit = f;
        if (this.f213a.get() || !this.f213a.compareAndSet(false, true)) {
            try {
                this.b.await();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        } else {
            f();
        }
        synchronized (this) {
            if (this.c == null) {
                throw new IllegalStateException("Unable to load publicsuffixes.gz resource from the classpath.");
            }
        }
        int length = strArr.length;
        byte[][] bArr = new byte[length][];
        for (int i = 0; i < strArr.length; i++) {
            bArr[i] = strArr[i].getBytes(e.i);
        }
        int i2 = 0;
        while (true) {
            str = null;
            if (i2 >= length) {
                strA = null;
                break;
            }
            strA = a(this.c, bArr, i2);
            if (strA != null) {
                break;
            }
            i2++;
        }
        if (length <= 1) {
            strA2 = null;
            break;
        }
        byte[][] bArr2 = (byte[][]) bArr.clone();
        int i3 = 0;
        while (true) {
            if (i3 >= bArr2.length - 1) {
                strA2 = null;
                break;
            }
            bArr2[i3] = e;
            strA2 = a(this.c, bArr2, i3);
            if (strA2 != null) {
                break;
            }
            i3++;
        }
        if (strA2 != null) {
            for (int i4 = 0; i4 < length - 1; i4++) {
                String strA3 = a(this.d, bArr, i4);
                if (strA3 != null) {
                    str = strA3;
                    break;
                }
            }
        }
        if (str != null) {
            return ("!" + str).split("\\.");
        }
        if (strA == null && strA2 == null) {
            return g;
        }
        String[] strArrSplit2 = strA != null ? strA.split("\\.") : strArrSplit;
        if (strA2 != null) {
            strArrSplit = strA2.split("\\.");
        }
        return strArrSplit2.length > strArrSplit.length ? strArrSplit2 : strArrSplit;
    }

    public static a c() {
        return h;
    }

    private void e() {
        InputStream resourceAsStream = a.class.getResourceAsStream("publicsuffixes.gz");
        if (resourceAsStream == null) {
            return;
        }
        j jVarD = t.d(new n(t.k(resourceAsStream)));
        try {
            byte[] bArr = new byte[jVarD.L()];
            jVarD.s(bArr);
            byte[] bArr2 = new byte[jVarD.L()];
            jVarD.s(bArr2);
            e.g(jVarD);
            synchronized (this) {
                this.c = bArr;
                this.d = bArr2;
            }
            this.b.countDown();
        } catch (Throwable th) {
            e.g(jVarD);
            throw th;
        }
    }

    private void f() {
        boolean z = false;
        while (true) {
            try {
                try {
                    e();
                    break;
                } catch (InterruptedIOException unused) {
                    Thread.interrupted();
                    z = true;
                } catch (IOException e2) {
                    k.l().t(5, "Failed to read public suffix list", e2);
                    if (z) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    return;
                }
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
    }

    public String d(String str) {
        if (str == null) {
            throw new NullPointerException("domain == null");
        }
        String[] strArrSplit = IDN.toUnicode(str).split("\\.");
        String[] strArrB = b(strArrSplit);
        if (strArrSplit.length == strArrB.length && strArrB[0].charAt(0) != '!') {
            return null;
        }
        char cCharAt = strArrB[0].charAt(0);
        int length = strArrSplit.length;
        int length2 = strArrB.length;
        if (cCharAt != '!') {
            length2++;
        }
        StringBuilder sb = new StringBuilder();
        String[] strArrSplit2 = str.split("\\.");
        for (int i = length - length2; i < strArrSplit2.length; i++) {
            sb.append(strArrSplit2[i]);
            sb.append('.');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
