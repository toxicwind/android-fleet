package okhttp3.internal.http2;

import java.util.Arrays;
import java.util.List;
import okio.ByteString;

/* JADX INFO: renamed from: okhttp3.internal.http2.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0041d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final okio.h f238a;
    private final boolean b;
    private int c;
    private boolean d;
    int e;
    C0039b[] f;
    int g;
    int h;
    int i;

    C0041d(int i, boolean z, okio.h hVar) {
        this.c = Integer.MAX_VALUE;
        C0039b[] c0039bArr = new C0039b[8];
        this.f = c0039bArr;
        this.g = c0039bArr.length - 1;
        this.h = 0;
        this.i = 0;
        this.e = i;
        this.b = z;
        this.f238a = hVar;
    }

    C0041d(okio.h hVar) {
        this(4096, true, hVar);
    }

    private void a() {
        int i = this.e;
        int i2 = this.i;
        if (i < i2) {
            if (i == 0) {
                b();
            } else {
                c(i2 - i);
            }
        }
    }

    private void b() {
        Arrays.fill(this.f, (Object) null);
        this.g = this.f.length - 1;
        this.h = 0;
        this.i = 0;
    }

    private int c(int i) {
        int i2 = 0;
        if (i > 0) {
            int length = this.f.length;
            while (true) {
                length--;
                if (length < this.g || i <= 0) {
                    break;
                }
                C0039b[] c0039bArr = this.f;
                i -= c0039bArr[length].c;
                this.i -= c0039bArr[length].c;
                this.h--;
                i2++;
            }
            C0039b[] c0039bArr2 = this.f;
            int i3 = this.g;
            System.arraycopy(c0039bArr2, i3 + 1, c0039bArr2, i3 + 1 + i2, this.h);
            C0039b[] c0039bArr3 = this.f;
            int i4 = this.g;
            Arrays.fill(c0039bArr3, i4 + 1, i4 + 1 + i2, (Object) null);
            this.g += i2;
        }
        return i2;
    }

    private void d(C0039b c0039b) {
        int i = c0039b.c;
        int i2 = this.e;
        if (i > i2) {
            b();
            return;
        }
        c((this.i + i) - i2);
        int i3 = this.h + 1;
        C0039b[] c0039bArr = this.f;
        if (i3 > c0039bArr.length) {
            C0039b[] c0039bArr2 = new C0039b[c0039bArr.length * 2];
            System.arraycopy(c0039bArr, 0, c0039bArr2, c0039bArr.length, c0039bArr.length);
            this.g = this.f.length - 1;
            this.f = c0039bArr2;
        }
        int i4 = this.g;
        this.g = i4 - 1;
        this.f[i4] = c0039b;
        this.h++;
        this.i += i;
    }

    void e(int i) {
        int iMin = Math.min(i, 16384);
        int i2 = this.e;
        if (i2 == iMin) {
            return;
        }
        if (iMin < i2) {
            this.c = Math.min(this.c, iMin);
        }
        this.d = true;
        this.e = iMin;
        a();
    }

    void f(ByteString byteString) {
        int iR;
        int i;
        if (!this.b || I.f().e(byteString) >= byteString.r()) {
            iR = byteString.r();
            i = 0;
        } else {
            okio.h hVar = new okio.h();
            I.f().d(byteString, hVar);
            byteString = hVar.W();
            iR = byteString.r();
            i = 128;
        }
        h(iR, 127, i);
        this.f238a.g0(byteString);
    }

    /* JADX WARN: Code duplicated, block: B:22:0x006b  */
    /* JADX WARN: Code duplicated, block: B:26:0x0072  */
    /* JADX WARN: Code duplicated, block: B:28:0x007a  */
    /* JADX WARN: Code duplicated, block: B:30:0x0086  */
    /* JADX WARN: Code duplicated, block: B:33:0x009a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:34:0x009c  */
    /* JADX WARN: Code duplicated, block: B:37:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:38:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:40:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:42:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:55:0x0092 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:57:0x00a4 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:58:0x00a4 A[SYNTHETIC] */
    void g(List list) {
        int length;
        int length2;
        int i;
        int length3;
        if (this.d) {
            int i2 = this.c;
            if (i2 < this.e) {
                h(i2, 31, 32);
            }
            this.d = false;
            this.c = Integer.MAX_VALUE;
            h(this.e, 31, 32);
        }
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            C0039b c0039b = (C0039b) list.get(i3);
            ByteString byteStringU = c0039b.f236a.u();
            ByteString byteString = c0039b.b;
            Integer num = (Integer) C0042e.b.get(byteStringU);
            if (num != null) {
                length = num.intValue() + 1;
                if (length <= 1 || length >= 8) {
                    length2 = length;
                    length = -1;
                } else if (!okhttp3.g0.e.q(C0042e.f239a[length - 1].b, byteString)) {
                    if (okhttp3.g0.e.q(C0042e.f239a[length].b, byteString)) {
                        length2 = length;
                        length++;
                    } else {
                        length2 = length;
                        length = -1;
                    }
                }
                if (length == -1) {
                    length3 = this.f.length;
                    for (i = this.g + 1; i < length3; i++) {
                        if (!okhttp3.g0.e.q(this.f[i].f236a, byteStringU)) {
                            if (okhttp3.g0.e.q(this.f[i].b, byteString)) {
                                length = C0042e.f239a.length + (i - this.g);
                                break;
                            } else if (length2 == -1) {
                                length2 = (i - this.g) + C0042e.f239a.length;
                            }
                        }
                    }
                }
                if (length != -1) {
                    h(length, 127, 128);
                } else {
                    if (length2 == -1) {
                        this.f238a.k0(64);
                        f(byteStringU);
                    } else if (byteStringU.s(C0039b.d) || C0039b.i.equals(byteStringU)) {
                        h(length2, 63, 64);
                    } else {
                        h(length2, 15, 0);
                        f(byteString);
                    }
                    f(byteString);
                    d(c0039b);
                }
            } else {
                length = -1;
            }
            length2 = length;
            if (length == -1) {
                length3 = this.f.length;
                while (i < length3) {
                    if (!okhttp3.g0.e.q(this.f[i].f236a, byteStringU)) {
                        if (okhttp3.g0.e.q(this.f[i].b, byteString)) {
                            length = C0042e.f239a.length + (i - this.g);
                            break;
                        } else if (length2 == -1) {
                            length2 = (i - this.g) + C0042e.f239a.length;
                        }
                    }
                }
            }
            if (length != -1) {
                h(length, 127, 128);
            } else {
                if (length2 == -1) {
                    this.f238a.k0(64);
                    f(byteStringU);
                } else {
                    if (byteStringU.s(C0039b.d)) {
                    }
                    h(length2, 63, 64);
                }
                f(byteString);
                d(c0039b);
            }
        }
    }

    void h(int i, int i2, int i3) {
        int i4;
        okio.h hVar;
        if (i < i2) {
            hVar = this.f238a;
            i4 = i | i3;
        } else {
            this.f238a.k0(i3 | i2);
            i4 = i - i2;
            while (i4 >= 128) {
                this.f238a.k0(128 | (i4 & 127));
                i4 >>>= 7;
            }
            hVar = this.f238a;
        }
        hVar.k0(i4);
    }
}
