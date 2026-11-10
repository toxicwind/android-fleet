package b.d.a.a.c;

import b.d.a.a.l;
import b.d.a.a.m;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class g extends c {
    public static final byte[] k = (byte[]) b.d.a.a.b.a.f83b.clone();
    public static final byte[] l = {110, 117, 108, 108};
    public static final byte[] m = {116, 114, 117, 101};
    public static final byte[] n = {102, 97, 108, 115, 101};
    public final OutputStream o;
    public byte[] p;
    public int q;
    public final int r;
    public final int s;
    public char[] t;
    public final int u;
    public boolean v;
    public boolean w;

    public g(b.d.a.a.b.c cVar, int i, OutputStream outputStream) {
        super(cVar, i);
        this.q = 0;
        this.o = outputStream;
        this.v = true;
        cVar.a((Object) cVar.f93f);
        byte[] bArrA = cVar.f91d.a(1);
        cVar.f93f = bArrA;
        this.p = bArrA;
        this.r = this.p.length;
        this.s = this.r >> 3;
        this.t = cVar.a();
        this.u = this.t.length;
        if (a(b.d.a.a.d.a.ESCAPE_NON_ASCII)) {
            this.h = 127;
        }
        this.w = !((b.d.a.a.d.a.QUOTE_FIELD_NAMES.m & i) != 0);
    }

    @Override // b.d.a.a.d
    public void a(double d2) throws IOException {
        if (this.f64c || ((Double.isNaN(d2) || Double.isInfinite(d2)) && a(b.d.a.a.d.a.QUOTE_NON_NUMERIC_NUMBERS))) {
            c(String.valueOf(d2));
        } else {
            d("write number");
            e(String.valueOf(d2));
        }
    }

    @Override // b.d.a.a.d
    public void b(String str) throws IOException {
        if (this.f144a != null) {
            int iA = this.f65d.a(str);
            if (iA == 4) {
                throw new b.d.a.a.c("Can not write a field name, expecting a value");
            }
            if (iA == 1) {
                this.f144a.f(this);
            } else {
                this.f144a.d(this);
            }
            if (this.w) {
                a(str, false);
                return;
            }
            int length = str.length();
            if (length > this.u) {
                a(str, true);
                return;
            }
            if (this.q >= this.r) {
                v();
            }
            byte[] bArr = this.p;
            int i = this.q;
            this.q = i + 1;
            bArr[i] = 34;
            str.getChars(0, length, this.t, 0);
            if (length <= this.s) {
                if (this.q + length > this.r) {
                    v();
                }
                a(this.t, 0, length);
            } else {
                b(this.t, 0, length);
            }
            if (this.q >= this.r) {
                v();
            }
            byte[] bArr2 = this.p;
            int i2 = this.q;
            this.q = i2 + 1;
            bArr2[i2] = 34;
            return;
        }
        int iA2 = this.f65d.a(str);
        if (iA2 == 4) {
            throw new b.d.a.a.c("Can not write a field name, expecting a value");
        }
        if (iA2 == 1) {
            if (this.q >= this.r) {
                v();
            }
            byte[] bArr3 = this.p;
            int i3 = this.q;
            this.q = i3 + 1;
            bArr3[i3] = 44;
        }
        if (this.w) {
            a(str, false);
            return;
        }
        int length2 = str.length();
        if (length2 > this.u) {
            a(str, true);
            return;
        }
        if (this.q >= this.r) {
            v();
        }
        byte[] bArr4 = this.p;
        int i4 = this.q;
        this.q = i4 + 1;
        bArr4[i4] = 34;
        str.getChars(0, length2, this.t, 0);
        if (length2 <= this.s) {
            if (this.q + length2 > this.r) {
                v();
            }
            a(this.t, 0, length2);
        } else {
            b(this.t, 0, length2);
        }
        if (this.q >= this.r) {
            v();
        }
        byte[] bArr5 = this.p;
        int i5 = this.q;
        this.q = i5 + 1;
        bArr5[i5] = 34;
    }

    @Override // b.d.a.a.d
    public void c(String str) throws IOException {
        d("write text value");
        if (str == null) {
            w();
            return;
        }
        int length = str.length();
        if (length > this.u) {
            a(str, true);
            return;
        }
        str.getChars(0, length, this.t, 0);
        if (length <= this.s) {
            if (this.q + length >= this.r) {
                v();
            }
            byte[] bArr = this.p;
            int i = this.q;
            this.q = i + 1;
            bArr[i] = 34;
            a(this.t, 0, length);
            if (this.q >= this.r) {
                v();
            }
            byte[] bArr2 = this.p;
            int i2 = this.q;
            this.q = i2 + 1;
            bArr2[i2] = 34;
            return;
        }
        char[] cArr = this.t;
        if (this.q >= this.r) {
            v();
        }
        byte[] bArr3 = this.p;
        int i3 = this.q;
        this.q = i3 + 1;
        bArr3[i3] = 34;
        b(this.t, 0, length);
        if (this.q >= this.r) {
            v();
        }
        byte[] bArr4 = this.p;
        int i4 = this.q;
        this.q = i4 + 1;
        bArr4[i4] = 34;
    }

    @Override // b.d.a.a.d, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.p != null && a(b.d.a.a.d.a.AUTO_CLOSE_JSON_CONTENT)) {
            while (true) {
                e eVar = this.f65d;
                if (!eVar.c()) {
                    if (!eVar.d()) {
                        break;
                    }
                    if (!this.f65d.d()) {
                        StringBuilder sbA = b.a.a.a.a.a("Current context not an object but ");
                        sbA.append(this.f65d.b());
                        throw new b.d.a.a.c(sbA.toString());
                    }
                    l lVar = this.f144a;
                    if (lVar != null) {
                        lVar.b(this, this.f65d.f221b + 1);
                    } else {
                        if (this.q >= this.r) {
                            v();
                        }
                        byte[] bArr = this.p;
                        int i = this.q;
                        this.q = i + 1;
                        bArr[i] = 125;
                    }
                    this.f65d = this.f65d.f140c;
                } else {
                    if (!this.f65d.c()) {
                        StringBuilder sbA2 = b.a.a.a.a.a("Current context not an ARRAY but ");
                        sbA2.append(this.f65d.b());
                        throw new b.d.a.a.c(sbA2.toString());
                    }
                    l lVar2 = this.f144a;
                    if (lVar2 != null) {
                        lVar2.a(this, this.f65d.f221b + 1);
                    } else {
                        if (this.q >= this.r) {
                            v();
                        }
                        byte[] bArr2 = this.p;
                        int i2 = this.q;
                        this.q = i2 + 1;
                        bArr2[i2] = 93;
                    }
                    this.f65d = this.f65d.f140c;
                }
            }
        }
        v();
        if (this.o != null) {
            if (this.f135f.f90c || a(b.d.a.a.d.a.AUTO_CLOSE_TARGET)) {
                this.o.close();
            } else if (a(b.d.a.a.d.a.FLUSH_PASSED_TO_STREAM)) {
                this.o.flush();
            }
        }
        byte[] bArr3 = this.p;
        if (bArr3 != null && this.v) {
            this.p = null;
            this.f135f.b(bArr3);
        }
        char[] cArr = this.t;
        if (cArr != null) {
            this.t = null;
            this.f135f.a(cArr);
        }
    }

    public final void d(String str) throws IOException {
        byte b2;
        m mVar;
        int iH = this.f65d.h();
        if (iH == 5) {
            throw new b.d.a.a.c(b.a.a.a.a.a("Can not ", str, ", expecting field name"));
        }
        l lVar = this.f144a;
        if (lVar != null) {
            if (iH == 0) {
                if (this.f65d.c()) {
                    this.f144a.e(this);
                    return;
                } else {
                    if (this.f65d.d()) {
                        this.f144a.d(this);
                        return;
                    }
                    return;
                }
            }
            if (iH == 1) {
                lVar.c(this);
                return;
            }
            if (iH == 2) {
                lVar.g(this);
                return;
            } else if (iH == 3) {
                lVar.b(this);
                return;
            } else {
                p();
                throw null;
            }
        }
        if (iH == 1) {
            b2 = 44;
        } else {
            if (iH != 2) {
                if (iH == 3 && (mVar = this.j) != null) {
                    byte[] bArrA = ((b.d.a.a.b.h) mVar).a();
                    if (bArrA.length > 0) {
                        int length = bArrA.length;
                        if (this.q + length > this.r) {
                            v();
                            if (length > 512) {
                                this.o.write(bArrA, 0, length);
                                return;
                            }
                        }
                        System.arraycopy(bArrA, 0, this.p, this.q, length);
                        this.q += length;
                        return;
                    }
                    return;
                }
                return;
            }
            b2 = 58;
        }
        if (this.q >= this.r) {
            v();
        }
        byte[] bArr = this.p;
        int i = this.q;
        bArr[i] = b2;
        this.q = i + 1;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x007a A[LOOP:4: B:30:0x007a->B:58:?, LOOP_START, PHI: r6
      0x007a: PHI (r6v4 int) = (r6v3 int), (r6v12 int) binds: [B:29:0x0078, B:58:?] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:36:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:43:0x00b7 A[EDGE_INSN: B:43:0x00b7->B:38:0x00b7 BREAK  A[LOOP:3: B:29:0x0078->B:54:0x0078], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:52:0x00a3 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:53:0x0086 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:58:? A[LOOP:4: B:30:0x007a->B:58:?, LOOP_END, SYNTHETIC] */
    public void e(String str) throws IOException {
        int i;
        int iA;
        char c2;
        int i2;
        char c3;
        int length = str.length();
        int i3 = 0;
        while (length > 0) {
            char[] cArr = this.t;
            int length2 = cArr.length;
            if (length < length2) {
                length2 = length;
            }
            int i4 = i3 + length2;
            str.getChars(i3, i4, cArr, 0);
            int i5 = length2 + length2 + length2;
            int i6 = this.q + i5;
            int i7 = this.r;
            if (i6 <= i7) {
                i = length2 + 0;
                iA = 0;
                while (iA < i) {
                    while (true) {
                        c2 = cArr[iA];
                        if (c2 > 127) {
                            break;
                        }
                        byte[] bArr = this.p;
                        int i8 = this.q;
                        this.q = i8 + 1;
                        bArr[i8] = (byte) c2;
                        iA++;
                        if (iA >= i) {
                            break;
                        }
                    }
                    i2 = iA + 1;
                    c3 = cArr[iA];
                    if (c3 < 2048) {
                        byte[] bArr2 = this.p;
                        int i9 = this.q;
                        this.q = i9 + 1;
                        bArr2[i9] = (byte) ((c3 >> 6) | 192);
                        int i10 = this.q;
                        this.q = i10 + 1;
                        bArr2[i10] = (byte) ((c3 & '?') | 128);
                        iA = i2;
                    } else {
                        iA = a(c3, cArr, i2, i);
                    }
                }
            } else if (i7 < i5) {
                byte[] bArr3 = this.p;
                int iA2 = 0;
                while (iA2 < length2) {
                    while (true) {
                        char c4 = cArr[iA2];
                        if (c4 >= 128) {
                            break;
                        }
                        if (this.q >= i7) {
                            v();
                        }
                        int i11 = this.q;
                        this.q = i11 + 1;
                        bArr3[i11] = (byte) c4;
                        iA2++;
                        if (iA2 >= length2) {
                            break;
                        }
                    }
                    if (this.q + 3 >= this.r) {
                        v();
                    }
                    int i12 = iA2 + 1;
                    char c5 = cArr[iA2];
                    if (c5 < 2048) {
                        int i13 = this.q;
                        this.q = i13 + 1;
                        bArr3[i13] = (byte) ((c5 >> 6) | 192);
                        int i14 = this.q;
                        this.q = i14 + 1;
                        bArr3[i14] = (byte) ((c5 & '?') | 128);
                        iA2 = i12;
                    } else {
                        iA2 = a(c5, cArr, i12, length2);
                    }
                }
            } else {
                v();
                i = length2 + 0;
                iA = 0;
                while (iA < i) {
                    while (true) {
                        c2 = cArr[iA];
                        if (c2 > 127) {
                            break;
                        }
                        byte[] bArr4 = this.p;
                        int i15 = this.q;
                        this.q = i15 + 1;
                        bArr4[i15] = (byte) c2;
                        iA++;
                        if (iA >= i) {
                            break;
                            break;
                        }
                    }
                    i2 = iA + 1;
                    c3 = cArr[iA];
                    if (c3 < 2048) {
                        byte[] bArr5 = this.p;
                        int i16 = this.q;
                        this.q = i16 + 1;
                        bArr5[i16] = (byte) ((c3 >> 6) | 192);
                        int i17 = this.q;
                        this.q = i17 + 1;
                        bArr5[i17] = (byte) ((c3 & '?') | 128);
                        iA = i2;
                    } else {
                        iA = a(c3, cArr, i2, i);
                    }
                }
            }
            length -= length2;
            i3 = i4;
        }
    }

    @Override // java.io.Flushable
    public void flush() throws IOException {
        v();
        if (this.o == null || !a(b.d.a.a.d.a.FLUSH_PASSED_TO_STREAM)) {
            return;
        }
        this.o.flush();
    }

    @Override // b.d.a.a.d
    public void g(long j) throws IOException {
        d("write number");
        if (!this.f64c) {
            if (this.q + 21 >= this.r) {
                v();
            }
            this.q = b.d.a.a.b.g.a(j, this.p, this.q);
            return;
        }
        if (this.q + 23 >= this.r) {
            v();
        }
        byte[] bArr = this.p;
        int i = this.q;
        this.q = i + 1;
        bArr[i] = 34;
        this.q = b.d.a.a.b.g.a(j, bArr, this.q);
        byte[] bArr2 = this.p;
        int i2 = this.q;
        this.q = i2 + 1;
        bArr2[i2] = 34;
    }

    @Override // b.d.a.a.d
    public final void q() throws IOException {
        if (!this.f65d.c()) {
            StringBuilder sbA = b.a.a.a.a.a("Current context not an ARRAY but ");
            sbA.append(this.f65d.b());
            throw new b.d.a.a.c(sbA.toString());
        }
        l lVar = this.f144a;
        if (lVar != null) {
            lVar.a(this, this.f65d.f221b + 1);
        } else {
            if (this.q >= this.r) {
                v();
            }
            byte[] bArr = this.p;
            int i = this.q;
            this.q = i + 1;
            bArr[i] = 93;
        }
        this.f65d = this.f65d.f140c;
    }

    @Override // b.d.a.a.d
    public final void r() throws IOException {
        if (!this.f65d.d()) {
            StringBuilder sbA = b.a.a.a.a.a("Current context not an object but ");
            sbA.append(this.f65d.b());
            throw new b.d.a.a.c(sbA.toString());
        }
        l lVar = this.f144a;
        if (lVar != null) {
            lVar.b(this, this.f65d.f221b + 1);
        } else {
            if (this.q >= this.r) {
                v();
            }
            byte[] bArr = this.p;
            int i = this.q;
            this.q = i + 1;
            bArr[i] = 125;
        }
        this.f65d = this.f65d.f140c;
    }

    @Override // b.d.a.a.d
    public void s() throws IOException {
        d("write null value");
        w();
    }

    @Override // b.d.a.a.d
    public final void t() throws IOException {
        d("start an array");
        this.f65d = this.f65d.f();
        l lVar = this.f144a;
        if (lVar != null) {
            lVar.h(this);
            return;
        }
        if (this.q >= this.r) {
            v();
        }
        byte[] bArr = this.p;
        int i = this.q;
        this.q = i + 1;
        bArr[i] = 91;
    }

    @Override // b.d.a.a.d
    public final void u() throws IOException {
        d("start an object");
        this.f65d = this.f65d.g();
        l lVar = this.f144a;
        if (lVar != null) {
            lVar.a(this);
            return;
        }
        if (this.q >= this.r) {
            v();
        }
        byte[] bArr = this.p;
        int i = this.q;
        this.q = i + 1;
        bArr[i] = 123;
    }

    public final void v() throws IOException {
        int i = this.q;
        if (i > 0) {
            this.q = 0;
            this.o.write(this.p, 0, i);
        }
    }

    public final void w() throws IOException {
        if (this.q + 4 >= this.r) {
            v();
        }
        System.arraycopy(l, 0, this.p, this.q, 4);
        this.q += 4;
    }

    @Override // b.d.a.a.d
    public void a(float f2) throws IOException {
        if (!this.f64c && ((!Float.isNaN(f2) && !Float.isInfinite(f2)) || !a(b.d.a.a.d.a.QUOTE_NON_NUMERIC_NUMBERS))) {
            d("write number");
            e(String.valueOf(f2));
        } else {
            c(String.valueOf(f2));
        }
    }

    @Override // b.d.a.a.d
    public void a(boolean z) throws IOException {
        d("write boolean value");
        if (this.q + 5 >= this.r) {
            v();
        }
        byte[] bArr = z ? m : n;
        int length = bArr.length;
        System.arraycopy(bArr, 0, this.p, this.q, length);
        this.q += length;
    }

    public final void a(String str, boolean z) throws IOException {
        if (z) {
            if (this.q >= this.r) {
                v();
            }
            byte[] bArr = this.p;
            int i = this.q;
            this.q = i + 1;
            bArr[i] = 34;
        }
        int length = str.length();
        char[] cArr = this.t;
        int i2 = 0;
        while (length > 0) {
            int iMin = Math.min(this.s, length);
            int i3 = i2 + iMin;
            str.getChars(i2, i3, cArr, 0);
            if (this.q + iMin > this.r) {
                v();
            }
            a(cArr, 0, iMin);
            length -= iMin;
            i2 = i3;
        }
        if (z) {
            if (this.q >= this.r) {
                v();
            }
            byte[] bArr2 = this.p;
            int i4 = this.q;
            this.q = i4 + 1;
            bArr2[i4] = 34;
        }
    }

    public final void a(char[] cArr, int i, int i2) throws IOException {
        int iA;
        int iA2;
        int iB;
        char c2;
        int i3 = i2 + i;
        int i4 = this.q;
        byte[] bArr = this.p;
        int[] iArr = this.g;
        while (i < i3 && (c2 = cArr[i]) <= 127 && iArr[c2] == 0) {
            bArr[i4] = (byte) c2;
            i++;
            i4++;
        }
        this.q = i4;
        if (i < i3) {
            if (this.i != null) {
                if (((i3 - i) * 6) + this.q > this.r) {
                    v();
                }
                int i5 = this.q;
                byte[] bArr2 = this.p;
                int[] iArr2 = this.g;
                int i6 = this.h;
                if (i6 <= 0) {
                    i6 = 65535;
                }
                b.d.a.a.b.b bVar = this.i;
                while (i < i3) {
                    i++;
                    char c3 = cArr[i];
                    if (c3 <= 127) {
                        if (iArr2[c3] == 0) {
                            bArr2[i5] = (byte) c3;
                            i5++;
                        } else {
                            int i7 = iArr2[c3];
                            if (i7 > 0) {
                                int i8 = i5 + 1;
                                bArr2[i5] = 92;
                                i5 = i8 + 1;
                                bArr2[i8] = (byte) i7;
                            } else if (i7 != -2) {
                                iB = b(c3, i5);
                            } else {
                                throw null;
                            }
                        }
                    } else if (c3 > i6) {
                        iB = b(c3, i5);
                    } else {
                        throw null;
                    }
                    i5 = iB;
                }
                this.q = i5;
                return;
            }
            if (this.h == 0) {
                if (((i3 - i) * 6) + this.q > this.r) {
                    v();
                }
                int i9 = this.q;
                byte[] bArr3 = this.p;
                int[] iArr3 = this.g;
                while (i < i3) {
                    i++;
                    char c4 = cArr[i];
                    if (c4 <= 127) {
                        if (iArr3[c4] == 0) {
                            bArr3[i9] = (byte) c4;
                            i9++;
                        } else {
                            int i10 = iArr3[c4];
                            if (i10 > 0) {
                                int i11 = i9 + 1;
                                bArr3[i9] = 92;
                                i9 = i11 + 1;
                                bArr3[i11] = (byte) i10;
                            } else {
                                iA2 = b(c4, i9);
                                i9 = iA2;
                            }
                        }
                    } else if (c4 <= 2047) {
                        int i12 = i9 + 1;
                        bArr3[i9] = (byte) ((c4 >> 6) | 192);
                        i9 = i12 + 1;
                        bArr3[i12] = (byte) ((c4 & '?') | 128);
                    } else {
                        iA2 = a(c4, i9);
                        i9 = iA2;
                    }
                }
                this.q = i9;
                return;
            }
            if (((i3 - i) * 6) + this.q > this.r) {
                v();
            }
            int i13 = this.q;
            byte[] bArr4 = this.p;
            int[] iArr4 = this.g;
            int i14 = this.h;
            while (i < i3) {
                i++;
                char c5 = cArr[i];
                if (c5 > 127) {
                    if (c5 > i14) {
                        iA = b(c5, i13);
                    } else if (c5 <= 2047) {
                        int i15 = i13 + 1;
                        bArr4[i13] = (byte) ((c5 >> 6) | 192);
                        i13 = i15 + 1;
                        bArr4[i15] = (byte) ((c5 & '?') | 128);
                    } else {
                        iA = a(c5, i13);
                    }
                    i13 = iA;
                } else if (iArr4[c5] == 0) {
                    bArr4[i13] = (byte) c5;
                    i13++;
                } else {
                    int i16 = iArr4[c5];
                    if (i16 > 0) {
                        int i17 = i13 + 1;
                        bArr4[i13] = 92;
                        i13 = i17 + 1;
                        bArr4[i17] = (byte) i16;
                    } else {
                        iA = b(c5, i13);
                        i13 = iA;
                    }
                }
            }
            this.q = i13;
        }
    }

    @Override // b.d.a.a.d
    public void b(int i) throws IOException {
        d("write number");
        if (this.q + 11 >= this.r) {
            v();
        }
        if (this.f64c) {
            if (this.q + 13 >= this.r) {
                v();
            }
            byte[] bArr = this.p;
            int i2 = this.q;
            this.q = i2 + 1;
            bArr[i2] = 34;
            this.q = b.d.a.a.b.g.c(i, bArr, this.q);
            byte[] bArr2 = this.p;
            int i3 = this.q;
            this.q = i3 + 1;
            bArr2[i3] = 34;
            return;
        }
        this.q = b.d.a.a.b.g.c(i, this.p, this.q);
    }

    public final void b(char[] cArr, int i, int i2) throws IOException {
        do {
            int iMin = Math.min(this.s, i2);
            if (this.q + iMin > this.r) {
                v();
            }
            a(cArr, i, iMin);
            i += iMin;
            i2 -= iMin;
        } while (i2 > 0);
    }

    public final int b(int i, int i2) {
        int i3;
        byte[] bArr = this.p;
        int i4 = i2 + 1;
        bArr[i2] = 92;
        int i5 = i4 + 1;
        bArr[i4] = 117;
        if (i > 255) {
            int i6 = 255 & (i >> 8);
            int i7 = i5 + 1;
            byte[] bArr2 = k;
            bArr[i5] = bArr2[i6 >> 4];
            i3 = i7 + 1;
            bArr[i7] = bArr2[i6 & 15];
            i &= 255;
        } else {
            int i8 = i5 + 1;
            bArr[i5] = 48;
            i3 = i8 + 1;
            bArr[i8] = 48;
        }
        int i9 = i3 + 1;
        byte[] bArr3 = k;
        bArr[i3] = bArr3[i >> 4];
        int i10 = i9 + 1;
        bArr[i9] = bArr3[i & 15];
        return i10;
    }

    public final int a(int i, char[] cArr, int i2, int i3) throws IOException {
        if (i < 55296 || i > 57343) {
            byte[] bArr = this.p;
            int i4 = this.q;
            this.q = i4 + 1;
            bArr[i4] = (byte) ((i >> 12) | 224);
            int i5 = this.q;
            this.q = i5 + 1;
            bArr[i5] = (byte) (((i >> 6) & 63) | 128);
            int i6 = this.q;
            this.q = i6 + 1;
            bArr[i6] = (byte) ((i & 63) | 128);
            return i2;
        }
        if (i2 < i3 && cArr != null) {
            char c2 = cArr[i2];
            if (c2 >= 56320 && c2 <= 57343) {
                int i7 = (c2 - 56320) + ((i - 55296) << 10) + 65536;
                if (this.q + 4 > this.r) {
                    v();
                }
                byte[] bArr2 = this.p;
                int i8 = this.q;
                this.q = i8 + 1;
                bArr2[i8] = (byte) ((i7 >> 18) | 240);
                int i9 = this.q;
                this.q = i9 + 1;
                bArr2[i9] = (byte) (((i7 >> 12) & 63) | 128);
                int i10 = this.q;
                this.q = i10 + 1;
                bArr2[i10] = (byte) (((i7 >> 6) & 63) | 128);
                int i11 = this.q;
                this.q = i11 + 1;
                bArr2[i11] = (byte) ((i7 & 63) | 128);
                return i2 + 1;
            }
            StringBuilder sbA = b.a.a.a.a.a("Incomplete surrogate pair: first char 0x");
            sbA.append(Integer.toHexString(i));
            sbA.append(", second 0x");
            sbA.append(Integer.toHexString(c2));
            throw new b.d.a.a.c(sbA.toString());
        }
        throw new b.d.a.a.c("Split surrogate on writeRaw() input (last character)");
    }

    public final int a(int i, int i2) {
        byte[] bArr = this.p;
        if (i >= 55296 && i <= 57343) {
            int i3 = i2 + 1;
            bArr[i2] = 92;
            int i4 = i3 + 1;
            bArr[i3] = 117;
            int i5 = i4 + 1;
            byte[] bArr2 = k;
            bArr[i4] = bArr2[(i >> 12) & 15];
            int i6 = i5 + 1;
            bArr[i5] = bArr2[(i >> 8) & 15];
            int i7 = i6 + 1;
            bArr[i6] = bArr2[(i >> 4) & 15];
            int i8 = i7 + 1;
            bArr[i7] = bArr2[i & 15];
            return i8;
        }
        int i9 = i2 + 1;
        bArr[i2] = (byte) ((i >> 12) | 224);
        int i10 = i9 + 1;
        bArr[i9] = (byte) (((i >> 6) & 63) | 128);
        int i11 = i10 + 1;
        bArr[i10] = (byte) ((i & 63) | 128);
        return i11;
    }
}
