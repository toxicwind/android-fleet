package b.d.a.a.c;

import b.d.a.a.l;
import b.d.a.a.m;
import com.google.common.net.InetAddresses;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: loaded from: classes.dex */
public final class i extends c {
    public static final char[] k = (char[]) b.d.a.a.b.a.f82a.clone();
    public final Writer l;
    public char[] m;
    public int n;
    public int o;
    public int p;
    public char[] q;
    public m r;

    public i(b.d.a.a.b.c cVar, int i, Writer writer) {
        super(cVar, i);
        this.n = 0;
        this.o = 0;
        this.l = writer;
        this.m = cVar.a();
        this.p = this.m.length;
    }

    @Override // b.d.a.a.d
    public void a(double d2) throws IOException {
        if (this.f64c || (a(b.d.a.a.d.a.QUOTE_NON_NUMERIC_NUMBERS) && (Double.isNaN(d2) || Double.isInfinite(d2)))) {
            c(String.valueOf(d2));
        } else {
            d("write number");
            f(String.valueOf(d2));
        }
    }

    @Override // b.d.a.a.d
    public void b(String str) throws IOException {
        int iA = this.f65d.a(str);
        if (iA == 4) {
            throw new b.d.a.a.c("Can not write a field name, expecting a value");
        }
        boolean z = iA == 1;
        l lVar = this.f144a;
        if (lVar != null) {
            if (z) {
                lVar.f(this);
            } else {
                lVar.d(this);
            }
            if (!a(b.d.a.a.d.a.QUOTE_FIELD_NAMES)) {
                e(str);
                return;
            }
            if (this.o >= this.p) {
                w();
            }
            char[] cArr = this.m;
            int i = this.o;
            this.o = i + 1;
            cArr[i] = '\"';
            e(str);
            if (this.o >= this.p) {
                w();
            }
            char[] cArr2 = this.m;
            int i2 = this.o;
            this.o = i2 + 1;
            cArr2[i2] = '\"';
            return;
        }
        if (this.o + 1 >= this.p) {
            w();
        }
        if (z) {
            char[] cArr3 = this.m;
            int i3 = this.o;
            this.o = i3 + 1;
            cArr3[i3] = ',';
        }
        if (!a(b.d.a.a.d.a.QUOTE_FIELD_NAMES)) {
            e(str);
            return;
        }
        char[] cArr4 = this.m;
        int i4 = this.o;
        this.o = i4 + 1;
        cArr4[i4] = '\"';
        e(str);
        if (this.o >= this.p) {
            w();
        }
        char[] cArr5 = this.m;
        int i5 = this.o;
        this.o = i5 + 1;
        cArr5[i5] = '\"';
    }

    @Override // b.d.a.a.d
    public void c(String str) throws IOException {
        d("write text value");
        if (str == null) {
            x();
            return;
        }
        if (this.o >= this.p) {
            w();
        }
        char[] cArr = this.m;
        int i = this.o;
        this.o = i + 1;
        cArr[i] = '\"';
        e(str);
        if (this.o >= this.p) {
            w();
        }
        char[] cArr2 = this.m;
        int i2 = this.o;
        this.o = i2 + 1;
        cArr2[i2] = '\"';
    }

    @Override // b.d.a.a.d, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.m != null && a(b.d.a.a.d.a.AUTO_CLOSE_JSON_CONTENT)) {
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
                        if (this.o >= this.p) {
                            w();
                        }
                        char[] cArr = this.m;
                        int i = this.o;
                        this.o = i + 1;
                        cArr[i] = '}';
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
                        if (this.o >= this.p) {
                            w();
                        }
                        char[] cArr2 = this.m;
                        int i2 = this.o;
                        this.o = i2 + 1;
                        cArr2[i2] = ']';
                    }
                    this.f65d = this.f65d.f140c;
                }
            }
        }
        w();
        if (this.l != null) {
            if (this.f135f.f90c || a(b.d.a.a.d.a.AUTO_CLOSE_TARGET)) {
                this.l.close();
            } else if (a(b.d.a.a.d.a.FLUSH_PASSED_TO_STREAM)) {
                this.l.flush();
            }
        }
        char[] cArr3 = this.m;
        if (cArr3 != null) {
            this.m = null;
            this.f135f.a(cArr3);
        }
    }

    public void d(String str) throws IOException {
        char c2;
        m mVar;
        if (this.f144a == null) {
            int iH = this.f65d.h();
            if (iH == 5) {
                throw new b.d.a.a.c(b.a.a.a.a.a("Can not ", str, ", expecting field name"));
            }
            if (iH == 1) {
                c2 = ',';
            } else {
                if (iH != 2) {
                    if (iH == 3 && (mVar = this.j) != null) {
                        f(((b.d.a.a.b.h) mVar).f110a);
                        return;
                    }
                    return;
                }
                c2 = InetAddresses.IPV6_DELIMITER;
            }
            if (this.o >= this.p) {
                w();
            }
            char[] cArr = this.m;
            int i = this.o;
            cArr[i] = c2;
            this.o = i + 1;
            return;
        }
        int iH2 = this.f65d.h();
        if (iH2 == 5) {
            throw new b.d.a.a.c(b.a.a.a.a.a("Can not ", str, ", expecting field name"));
        }
        if (iH2 == 0) {
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
        if (iH2 == 1) {
            this.f144a.c(this);
            return;
        }
        if (iH2 == 2) {
            this.f144a.g(this);
        } else if (iH2 == 3) {
            this.f144a.b(this);
        } else {
            p();
            throw null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:111:0x0190  */
    /* JADX WARN: Code duplicated, block: B:166:0x0197 A[SYNTHETIC] */
    public final void e(String str) throws IOException {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        char c2;
        char c3;
        int i8;
        char c4;
        int i9;
        int length = str.length();
        int i10 = this.p;
        int i11 = 1;
        if (length <= i10) {
            if (this.o + length > i10) {
                w();
            }
            str.getChars(0, length, this.m, this.o);
            if (this.i != null) {
                int i12 = this.o + length;
                int[] iArr = this.g;
                int i13 = this.h;
                if (i13 < 1) {
                    i13 = 65535;
                }
                int iMin = Math.min(iArr.length, i13 + 1);
                b.d.a.a.b.b bVar = this.i;
                while (this.o < i12) {
                    do {
                        char[] cArr = this.m;
                        int i14 = this.o;
                        char c5 = cArr[i14];
                        if (c5 < iMin) {
                            i6 = iArr[c5];
                            if (i6 == 0) {
                                i7 = i14 + 1;
                                this.o = i7;
                            }
                        } else {
                            if (c5 <= i13) {
                                throw null;
                            }
                            i6 = -1;
                        }
                        int i15 = this.o;
                        int i16 = this.n;
                        int i17 = i15 - i16;
                        if (i17 > 0) {
                            this.l.write(this.m, i16, i17);
                        }
                        this.o++;
                        a(c5, i6);
                    } while (i7 < i12);
                    return;
                }
                return;
            }
            int i18 = this.h;
            if (i18 == 0) {
                int i19 = this.o + length;
                int[] iArr2 = this.g;
                int length2 = iArr2.length;
                while (this.o < i19) {
                    do {
                        char[] cArr2 = this.m;
                        int i20 = this.o;
                        char c6 = cArr2[i20];
                        if (c6 >= length2 || iArr2[c6] == 0) {
                            i = this.o + 1;
                            this.o = i;
                        } else {
                            int i21 = this.n;
                            int i22 = i20 - i21;
                            if (i22 > 0) {
                                this.l.write(cArr2, i21, i22);
                            }
                            char[] cArr3 = this.m;
                            int i23 = this.o;
                            this.o = i23 + 1;
                            char c7 = cArr3[i23];
                            a(c7, iArr2[c7]);
                        }
                    } while (i < i19);
                    return;
                }
                return;
            }
            int i24 = this.o + length;
            int[] iArr3 = this.g;
            int iMin2 = Math.min(iArr3.length, i18 + 1);
            while (this.o < i24) {
                do {
                    char c8 = this.m[this.o];
                    if (c8 < iMin2) {
                        i2 = iArr3[c8];
                        if (i2 != 0) {
                            int i25 = this.o;
                            i3 = this.n;
                            i4 = i25 - i3;
                            if (i4 > 0) {
                                this.l.write(this.m, i3, i4);
                            }
                            this.o++;
                            a(c8, i2);
                        }
                        i5 = this.o + 1;
                        this.o = i5;
                    } else {
                        if (c8 > i18) {
                            i2 = -1;
                            int i26 = this.o;
                            i3 = this.n;
                            i4 = i26 - i3;
                            if (i4 > 0) {
                                this.l.write(this.m, i3, i4);
                            }
                            this.o++;
                            a(c8, i2);
                        }
                        i5 = this.o + 1;
                        this.o = i5;
                    }
                } while (i5 < i24);
                return;
            }
            return;
        }
        w();
        int length3 = str.length();
        int i27 = 0;
        while (true) {
            int i28 = this.p;
            if (i27 + i28 > length3) {
                i28 = length3 - i27;
            }
            int i29 = i28;
            int i30 = i27 + i29;
            str.getChars(i27, i30, this.m, 0);
            if (this.i != null) {
                int[] iArr4 = this.g;
                int i31 = this.h;
                int i32 = i31 < i11 ? 65535 : i31;
                int iMin3 = Math.min(iArr4.length, i32 + 1);
                b.d.a.a.b.b bVar2 = this.i;
                int i33 = 0;
                int iA = 0;
                while (i33 < i29) {
                    do {
                        c4 = this.m[i33];
                        if (c4 >= iMin3) {
                            if (c4 <= i32) {
                                throw null;
                            }
                            i9 = -1;
                            break;
                        } else {
                            i9 = iArr4[c4];
                            if (i9 != 0) {
                                break;
                            } else {
                                i33++;
                            }
                        }
                    } while (i33 < i29);
                    int i34 = i33 - iA;
                    if (i34 > 0) {
                        this.l.write(this.m, iA, i34);
                        if (i33 >= i29) {
                            break;
                        }
                    }
                    int i35 = i33 + 1;
                    iA = a(this.m, i35, i29, c4, i9);
                    i33 = i35;
                    iArr4 = iArr4;
                    iMin3 = iMin3;
                    i32 = i32;
                }
            } else {
                int i36 = this.h;
                if (i36 != 0) {
                    int[] iArr5 = this.g;
                    int iMin4 = Math.min(iArr5.length, i36 + 1);
                    int i37 = 0;
                    int i38 = 0;
                    int iA2 = 0;
                    while (i37 < i29) {
                        while (true) {
                            c3 = this.m[i37];
                            if (c3 >= iMin4) {
                                if (c3 > i36) {
                                    i8 = -1;
                                    break;
                                }
                            } else {
                                i38 = iArr5[c3];
                                if (i38 != 0) {
                                }
                                i8 = i38;
                                break;
                            }
                            i37++;
                            if (i37 >= i29) {
                                i8 = i38;
                                break;
                            }
                        }
                        int i39 = i37 - iA2;
                        if (i39 > 0) {
                            this.l.write(this.m, iA2, i39);
                            if (i37 >= i29) {
                                break;
                            }
                        }
                        int i40 = i37 + 1;
                        iA2 = a(this.m, i40, i29, c3, i8);
                        i38 = i8;
                        i37 = i40;
                    }
                } else {
                    int[] iArr6 = this.g;
                    int length4 = iArr6.length;
                    int i41 = 0;
                    int iA3 = 0;
                    while (i41 < i29) {
                        do {
                            c2 = this.m[i41];
                            if (c2 < length4 && iArr6[c2] != 0) {
                                break;
                            } else {
                                i41++;
                            }
                        } while (i41 < i29);
                        int i42 = i41 - iA3;
                        if (i42 > 0) {
                            this.l.write(this.m, iA3, i42);
                            if (i41 >= i29) {
                                break;
                            }
                        }
                        int i43 = i41 + 1;
                        iA3 = a(this.m, i43, i29, c2, iArr6[c2]);
                        i41 = i43;
                    }
                }
            }
            if (i30 >= length3) {
                return;
            }
            i27 = i30;
            i11 = 1;
        }
    }

    public void f(String str) throws IOException {
        int length = str.length();
        int i = this.p - this.o;
        if (i == 0) {
            w();
            i = this.p - this.o;
        }
        if (i >= length) {
            str.getChars(0, length, this.m, this.o);
            this.o += length;
            return;
        }
        int i2 = this.p;
        int i3 = this.o;
        int i4 = i2 - i3;
        str.getChars(0, i4, this.m, i3);
        this.o += i4;
        w();
        int length2 = str.length() - i4;
        while (true) {
            int i5 = this.p;
            if (length2 <= i5) {
                str.getChars(i4, i4 + length2, this.m, 0);
                this.n = 0;
                this.o = length2;
                return;
            } else {
                int i6 = i4 + i5;
                str.getChars(i4, i6, this.m, 0);
                this.n = 0;
                this.o = i5;
                w();
                length2 -= i5;
                i4 = i6;
            }
        }
    }

    @Override // java.io.Flushable
    public void flush() throws IOException {
        w();
        if (this.l == null || !a(b.d.a.a.d.a.FLUSH_PASSED_TO_STREAM)) {
            return;
        }
        this.l.flush();
    }

    @Override // b.d.a.a.d
    public void g(long j) throws IOException {
        d("write number");
        if (!this.f64c) {
            if (this.o + 21 >= this.p) {
                w();
            }
            this.o = b.d.a.a.b.g.a(j, this.m, this.o);
            return;
        }
        if (this.o + 23 >= this.p) {
            w();
        }
        char[] cArr = this.m;
        int i = this.o;
        this.o = i + 1;
        cArr[i] = '\"';
        this.o = b.d.a.a.b.g.a(j, cArr, this.o);
        char[] cArr2 = this.m;
        int i2 = this.o;
        this.o = i2 + 1;
        cArr2[i2] = '\"';
    }

    @Override // b.d.a.a.d
    public void q() throws IOException {
        if (!this.f65d.c()) {
            StringBuilder sbA = b.a.a.a.a.a("Current context not an ARRAY but ");
            sbA.append(this.f65d.b());
            throw new b.d.a.a.c(sbA.toString());
        }
        l lVar = this.f144a;
        if (lVar != null) {
            lVar.a(this, this.f65d.f221b + 1);
        } else {
            if (this.o >= this.p) {
                w();
            }
            char[] cArr = this.m;
            int i = this.o;
            this.o = i + 1;
            cArr[i] = ']';
        }
        this.f65d = this.f65d.f140c;
    }

    @Override // b.d.a.a.d
    public void r() throws IOException {
        if (!this.f65d.d()) {
            StringBuilder sbA = b.a.a.a.a.a("Current context not an object but ");
            sbA.append(this.f65d.b());
            throw new b.d.a.a.c(sbA.toString());
        }
        l lVar = this.f144a;
        if (lVar != null) {
            lVar.b(this, this.f65d.f221b + 1);
        } else {
            if (this.o >= this.p) {
                w();
            }
            char[] cArr = this.m;
            int i = this.o;
            this.o = i + 1;
            cArr[i] = '}';
        }
        this.f65d = this.f65d.f140c;
    }

    @Override // b.d.a.a.d
    public void s() throws IOException {
        d("write null value");
        x();
    }

    @Override // b.d.a.a.d
    public void t() throws IOException {
        d("start an array");
        this.f65d = this.f65d.f();
        l lVar = this.f144a;
        if (lVar != null) {
            lVar.h(this);
            return;
        }
        if (this.o >= this.p) {
            w();
        }
        char[] cArr = this.m;
        int i = this.o;
        this.o = i + 1;
        cArr[i] = '[';
    }

    @Override // b.d.a.a.d
    public void u() throws IOException {
        d("start an object");
        this.f65d = this.f65d.g();
        l lVar = this.f144a;
        if (lVar != null) {
            lVar.a(this);
            return;
        }
        if (this.o >= this.p) {
            w();
        }
        char[] cArr = this.m;
        int i = this.o;
        this.o = i + 1;
        cArr[i] = '{';
    }

    public final char[] v() {
        char[] cArr = {'\\', 0, '\\', 'u', '0', '0', 0, 0, '\\', 'u', 0, 0, 0, 0};
        this.q = cArr;
        return cArr;
    }

    public void w() throws IOException {
        int i = this.o;
        int i2 = this.n;
        int i3 = i - i2;
        if (i3 > 0) {
            this.n = 0;
            this.o = 0;
            this.l.write(this.m, i2, i3);
        }
    }

    public final void x() throws IOException {
        if (this.o + 4 >= this.p) {
            w();
        }
        int i = this.o;
        char[] cArr = this.m;
        cArr[i] = 'n';
        int i2 = i + 1;
        cArr[i2] = 'u';
        int i3 = i2 + 1;
        cArr[i3] = 'l';
        int i4 = i3 + 1;
        cArr[i4] = 'l';
        this.o = i4 + 1;
    }

    @Override // b.d.a.a.d
    public void a(float f2) throws IOException {
        if (!this.f64c && (!a(b.d.a.a.d.a.QUOTE_NON_NUMERIC_NUMBERS) || (!Float.isNaN(f2) && !Float.isInfinite(f2)))) {
            d("write number");
            f(String.valueOf(f2));
        } else {
            c(String.valueOf(f2));
        }
    }

    @Override // b.d.a.a.d
    public void a(boolean z) throws IOException {
        int i;
        d("write boolean value");
        if (this.o + 5 >= this.p) {
            w();
        }
        int i2 = this.o;
        char[] cArr = this.m;
        if (z) {
            cArr[i2] = 't';
            int i3 = i2 + 1;
            cArr[i3] = 'r';
            int i4 = i3 + 1;
            cArr[i4] = 'u';
            i = i4 + 1;
            cArr[i] = 'e';
        } else {
            cArr[i2] = 'f';
            int i5 = i2 + 1;
            cArr[i5] = 'a';
            int i6 = i5 + 1;
            cArr[i6] = 'l';
            int i7 = i6 + 1;
            cArr[i7] = 's';
            i = i7 + 1;
            cArr[i] = 'e';
        }
        this.o = i + 1;
    }

    public final void a(char c2, int i) throws IOException {
        int i2;
        if (i >= 0) {
            int i3 = this.o;
            if (i3 >= 2) {
                int i4 = i3 - 2;
                this.n = i4;
                char[] cArr = this.m;
                cArr[i4] = '\\';
                cArr[i4 + 1] = (char) i;
                return;
            }
            char[] cArrV = this.q;
            if (cArrV == null) {
                cArrV = v();
            }
            this.n = this.o;
            cArrV[1] = (char) i;
            this.l.write(cArrV, 0, 2);
            return;
        }
        if (i != -2) {
            int i5 = this.o;
            if (i5 >= 6) {
                char[] cArr2 = this.m;
                int i6 = i5 - 6;
                this.n = i6;
                cArr2[i6] = '\\';
                int i7 = i6 + 1;
                cArr2[i7] = 'u';
                if (c2 > 255) {
                    int i8 = (c2 >> '\b') & 255;
                    int i9 = i7 + 1;
                    char[] cArr3 = k;
                    cArr2[i9] = cArr3[i8 >> 4];
                    i2 = i9 + 1;
                    cArr2[i2] = cArr3[i8 & 15];
                    c2 = (char) (c2 & 255);
                } else {
                    int i10 = i7 + 1;
                    cArr2[i10] = '0';
                    i2 = i10 + 1;
                    cArr2[i2] = '0';
                }
                int i11 = i2 + 1;
                char[] cArr4 = k;
                cArr2[i11] = cArr4[c2 >> 4];
                cArr2[i11 + 1] = cArr4[c2 & 15];
                return;
            }
            char[] cArrV2 = this.q;
            if (cArrV2 == null) {
                cArrV2 = v();
            }
            this.n = this.o;
            if (c2 > 255) {
                int i12 = (c2 >> '\b') & 255;
                int i13 = c2 & 255;
                char[] cArr5 = k;
                cArrV2[10] = cArr5[i12 >> 4];
                cArrV2[11] = cArr5[i12 & 15];
                cArrV2[12] = cArr5[i13 >> 4];
                cArrV2[13] = cArr5[i13 & 15];
                this.l.write(cArrV2, 8, 6);
                return;
            }
            char[] cArr6 = k;
            cArrV2[6] = cArr6[c2 >> 4];
            cArrV2[7] = cArr6[c2 & 15];
            this.l.write(cArrV2, 2, 6);
            return;
        }
        m mVar = this.r;
        if (mVar != null) {
            String str = ((b.d.a.a.b.h) mVar).f110a;
            this.r = null;
            int length = str.length();
            int i14 = this.o;
            if (i14 >= length) {
                int i15 = i14 - length;
                this.n = i15;
                str.getChars(0, length, this.m, i15);
                return;
            } else {
                this.n = i14;
                this.l.write(str);
                return;
            }
        }
        b.d.a.a.b.b bVar = this.i;
        throw null;
    }

    @Override // b.d.a.a.d
    public void b(int i) throws IOException {
        d("write number");
        if (this.f64c) {
            if (this.o + 13 >= this.p) {
                w();
            }
            char[] cArr = this.m;
            int i2 = this.o;
            this.o = i2 + 1;
            cArr[i2] = '\"';
            this.o = b.d.a.a.b.g.c(i, cArr, this.o);
            char[] cArr2 = this.m;
            int i3 = this.o;
            this.o = i3 + 1;
            cArr2[i3] = '\"';
            return;
        }
        if (this.o + 11 >= this.p) {
            w();
        }
        this.o = b.d.a.a.b.g.c(i, this.m, this.o);
    }

    public final int a(char[] cArr, int i, int i2, char c2, int i3) throws IOException {
        int i4;
        if (i3 >= 0) {
            if (i > 1 && i < i2) {
                int i5 = i - 2;
                cArr[i5] = '\\';
                cArr[i5 + 1] = (char) i3;
                return i5;
            }
            char[] cArrV = this.q;
            if (cArrV == null) {
                cArrV = v();
            }
            cArrV[1] = (char) i3;
            this.l.write(cArrV, 0, 2);
            return i;
        }
        if (i3 == -2) {
            m mVar = this.r;
            if (mVar != null) {
                String str = ((b.d.a.a.b.h) mVar).f110a;
                this.r = null;
                int length = str.length();
                if (i >= length && i < i2) {
                    int i6 = i - length;
                    str.getChars(0, length, cArr, i6);
                    return i6;
                }
                this.l.write(str);
                return i;
            }
            b.d.a.a.b.b bVar = this.i;
            throw null;
        }
        if (i > 5 && i < i2) {
            int i7 = i - 6;
            int i8 = i7 + 1;
            cArr[i7] = '\\';
            int i9 = i8 + 1;
            cArr[i8] = 'u';
            if (c2 > 255) {
                int i10 = (c2 >> '\b') & 255;
                int i11 = i9 + 1;
                char[] cArr2 = k;
                cArr[i9] = cArr2[i10 >> 4];
                i4 = i11 + 1;
                cArr[i11] = cArr2[i10 & 15];
                c2 = (char) (c2 & 255);
            } else {
                int i12 = i9 + 1;
                cArr[i9] = '0';
                i4 = i12 + 1;
                cArr[i12] = '0';
            }
            int i13 = i4 + 1;
            char[] cArr3 = k;
            cArr[i4] = cArr3[c2 >> 4];
            cArr[i13] = cArr3[c2 & 15];
            return i13 - 5;
        }
        char[] cArrV2 = this.q;
        if (cArrV2 == null) {
            cArrV2 = v();
        }
        this.n = this.o;
        if (c2 > 255) {
            int i14 = (c2 >> '\b') & 255;
            int i15 = c2 & 255;
            char[] cArr4 = k;
            cArrV2[10] = cArr4[i14 >> 4];
            cArrV2[11] = cArr4[i14 & 15];
            cArrV2[12] = cArr4[i15 >> 4];
            cArrV2[13] = cArr4[i15 & 15];
            this.l.write(cArrV2, 8, 6);
            return i;
        }
        char[] cArr5 = k;
        cArrV2[6] = cArr5[c2 >> 4];
        cArrV2[7] = cArr5[c2 & 15];
        this.l.write(cArrV2, 2, 6);
        return i;
    }
}
