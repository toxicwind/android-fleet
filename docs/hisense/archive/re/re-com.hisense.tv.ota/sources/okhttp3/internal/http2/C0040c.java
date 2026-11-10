package okhttp3.internal.http2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import okio.ByteString;

/* JADX INFO: renamed from: okhttp3.internal.http2.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0040c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List f237a;
    private final okio.j b;
    private final int c;
    private int d;
    C0039b[] e;
    int f;
    int g;
    int h;

    C0040c(int i, int i2, okio.B b) {
        this.f237a = new ArrayList();
        C0039b[] c0039bArr = new C0039b[8];
        this.e = c0039bArr;
        this.f = c0039bArr.length - 1;
        this.g = 0;
        this.h = 0;
        this.c = i;
        this.d = i2;
        this.b = okio.t.d(b);
    }

    C0040c(int i, okio.B b) {
        this(i, i, b);
    }

    private void a() {
        int i = this.d;
        int i2 = this.h;
        if (i < i2) {
            if (i == 0) {
                b();
            } else {
                d(i2 - i);
            }
        }
    }

    private void b() {
        Arrays.fill(this.e, (Object) null);
        this.f = this.e.length - 1;
        this.g = 0;
        this.h = 0;
    }

    private int c(int i) {
        return this.f + 1 + i;
    }

    private int d(int i) {
        int i2 = 0;
        if (i > 0) {
            int length = this.e.length;
            while (true) {
                length--;
                if (length < this.f || i <= 0) {
                    break;
                }
                C0039b[] c0039bArr = this.e;
                i -= c0039bArr[length].c;
                this.h -= c0039bArr[length].c;
                this.g--;
                i2++;
            }
            C0039b[] c0039bArr2 = this.e;
            int i3 = this.f;
            System.arraycopy(c0039bArr2, i3 + 1, c0039bArr2, i3 + 1 + i2, this.g);
            this.f += i2;
        }
        return i2;
    }

    private ByteString f(int i) throws IOException {
        C0039b c0039b;
        if (!h(i)) {
            int iC = c(i - C0042e.f239a.length);
            if (iC >= 0) {
                C0039b[] c0039bArr = this.e;
                if (iC < c0039bArr.length) {
                    c0039b = c0039bArr[iC];
                }
            }
            throw new IOException("Header index too large " + (i + 1));
        }
        c0039b = C0042e.f239a[i];
        return c0039b.f236a;
    }

    private void g(int i, C0039b c0039b) {
        this.f237a.add(c0039b);
        int i2 = c0039b.c;
        if (i != -1) {
            i2 -= this.e[c(i)].c;
        }
        int i3 = this.d;
        if (i2 > i3) {
            b();
            return;
        }
        int iD = d((this.h + i2) - i3);
        if (i == -1) {
            int i4 = this.g + 1;
            C0039b[] c0039bArr = this.e;
            if (i4 > c0039bArr.length) {
                C0039b[] c0039bArr2 = new C0039b[c0039bArr.length * 2];
                System.arraycopy(c0039bArr, 0, c0039bArr2, c0039bArr.length, c0039bArr.length);
                this.f = this.e.length - 1;
                this.e = c0039bArr2;
            }
            int i5 = this.f;
            this.f = i5 - 1;
            this.e[i5] = c0039b;
            this.g++;
        } else {
            this.e[i + c(i) + iD] = c0039b;
        }
        this.h += i2;
    }

    private boolean h(int i) {
        return i >= 0 && i <= C0042e.f239a.length - 1;
    }

    private int i() {
        return this.b.H() & 255;
    }

    private void l(int i) throws IOException {
        List list;
        C0039b c0039b;
        if (!h(i)) {
            int iC = c(i - C0042e.f239a.length);
            if (iC >= 0) {
                C0039b[] c0039bArr = this.e;
                if (iC < c0039bArr.length) {
                    list = this.f237a;
                    c0039b = c0039bArr[iC];
                }
            }
            throw new IOException("Header index too large " + (i + 1));
        }
        c0039b = C0042e.f239a[i];
        list = this.f237a;
        list.add(c0039b);
    }

    private void n(int i) {
        g(-1, new C0039b(f(i), j()));
    }

    private void o() throws IOException {
        ByteString byteStringJ = j();
        C0042e.a(byteStringJ);
        g(-1, new C0039b(byteStringJ, j()));
    }

    private void p(int i) throws IOException {
        this.f237a.add(new C0039b(f(i), j()));
    }

    private void q() throws IOException {
        ByteString byteStringJ = j();
        C0042e.a(byteStringJ);
        this.f237a.add(new C0039b(byteStringJ, j()));
    }

    public List e() {
        ArrayList arrayList = new ArrayList(this.f237a);
        this.f237a.clear();
        return arrayList;
    }

    ByteString j() {
        int i = i();
        boolean z = (i & 128) == 128;
        int iM = m(i, 127);
        return z ? ByteString.l(I.f().c(this.b.w(iM))) : this.b.d(iM);
    }

    void k() {
        while (!this.b.n()) {
            int iH = this.b.H() & 255;
            if (iH == 128) {
                throw new IOException("index == 0");
            }
            if ((iH & 128) == 128) {
                l(m(iH, 127) - 1);
            } else if (iH == 64) {
                o();
            } else if ((iH & 64) == 64) {
                n(m(iH, 63) - 1);
            } else if ((iH & 32) == 32) {
                int iM = m(iH, 31);
                this.d = iM;
                if (iM < 0 || iM > this.c) {
                    throw new IOException("Invalid dynamic table size update " + this.d);
                }
                a();
            } else if (iH == 16 || iH == 0) {
                q();
            } else {
                p(m(iH, 15) - 1);
            }
        }
    }

    int m(int i, int i2) {
        int i3 = i & i2;
        if (i3 < i2) {
            return i3;
        }
        int i4 = 0;
        while (true) {
            int i5 = i();
            if ((i5 & 128) == 0) {
                return i2 + (i5 << i4);
            }
            i2 += (i5 & 127) << i4;
            i4 += 7;
        }
    }
}
