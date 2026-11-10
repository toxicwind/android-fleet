package okio;

import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public final class h implements j, i, Cloneable, ByteChannel {
    private static final byte[] c = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    y f268a;
    long b;

    @Override // okio.j
    public long A() {
        int i;
        int i2;
        if (this.b == 0) {
            throw new IllegalStateException("size == 0");
        }
        int i3 = 0;
        boolean z = false;
        long j = 0;
        do {
            y yVar = this.f268a;
            byte[] bArr = yVar.f280a;
            int i4 = yVar.b;
            int i5 = yVar.c;
            while (i4 < i5) {
                byte b = bArr[i4];
                if (b < 48 || b > 57) {
                    if (b >= 97 && b <= 102) {
                        i = b - 97;
                    } else {
                        if (b < 65 || b > 70) {
                            if (i3 != 0) {
                                z = true;
                                break;
                            }
                            throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x" + Integer.toHexString(b));
                        }
                        i = b - 65;
                    }
                    i2 = i + 10;
                } else {
                    i2 = b - 48;
                }
                if (((-1152921504606846976L) & j) != 0) {
                    h hVar = new h();
                    hVar.m0(j);
                    hVar.k0(b);
                    throw new NumberFormatException("Number too large: " + hVar.Y());
                }
                j = (j << 4) | ((long) i2);
                i4++;
                i3++;
            }
            if (i4 == i5) {
                this.f268a = yVar.b();
                z.a(yVar);
            } else {
                yVar.b = i4;
            }
            if (z) {
                break;
            }
        } while (this.f268a != null);
        this.b -= (long) i3;
        return j;
    }

    @Override // okio.j
    public String C(Charset charset) {
        try {
            return X(this.b, charset);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // okio.j
    public InputStream D() {
        return new g(this);
    }

    @Override // okio.j
    public String E() {
        return l(Long.MAX_VALUE);
    }

    @Override // okio.i
    public /* bridge */ /* synthetic */ i F() {
        Q();
        return this;
    }

    @Override // okio.i
    public /* bridge */ /* synthetic */ i G(long j) {
        m0(j);
        return this;
    }

    @Override // okio.j
    public byte H() {
        long j = this.b;
        if (j == 0) {
            throw new IllegalStateException("size == 0");
        }
        y yVar = this.f268a;
        int i = yVar.b;
        int i2 = yVar.c;
        int i3 = i + 1;
        byte b = yVar.f280a[i];
        this.b = j - 1;
        if (i3 == i2) {
            this.f268a = yVar.b();
            z.a(yVar);
        } else {
            yVar.b = i3;
        }
        return b;
    }

    @Override // okio.j
    public long I() {
        byte b;
        long j = 0;
        if (this.b == 0) {
            throw new IllegalStateException("size == 0");
        }
        long j2 = -922337203685477580L;
        long j3 = -7;
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        loop0: while (true) {
            y yVar = this.f268a;
            byte[] bArr = yVar.f280a;
            int i2 = yVar.b;
            int i3 = yVar.c;
            while (i2 < i3) {
                b = bArr[i2];
                if (b >= 48 && b <= 57) {
                    int i4 = 48 - b;
                    if (j < j2 || (j == j2 && i4 < j3)) {
                        break loop0;
                    }
                    j = (j * 10) + ((long) i4);
                } else {
                    if (b != 45 || i != 0) {
                        if (i != 0) {
                            z2 = true;
                            break;
                        }
                        throw new NumberFormatException("Expected leading [0-9] or '-' character but was 0x" + Integer.toHexString(b));
                    }
                    j3--;
                    z = true;
                }
                i2++;
                i++;
                j2 = -922337203685477580L;
            }
            if (i2 == i3) {
                this.f268a = yVar.b();
                z.a(yVar);
            } else {
                yVar.b = i2;
            }
            if (z2 || this.f268a == null) {
                this.b -= (long) i;
                return z ? j : -j;
            }
            j2 = -922337203685477580L;
        }
        h hVar = new h();
        hVar.l0(j);
        hVar.k0(b);
        if (!z) {
            hVar.H();
        }
        throw new NumberFormatException("Number too large: " + hVar.Y());
    }

    @Override // okio.j
    public void J(long j) throws EOFException {
        if (this.b < j) {
            throw new EOFException();
        }
    }

    @Override // okio.B
    public long K(h hVar, long j) {
        if (hVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        long j2 = this.b;
        if (j2 == 0) {
            return -1L;
        }
        if (j > j2) {
            j = j2;
        }
        hVar.o(this, j);
        return j;
    }

    @Override // okio.j
    public int L() {
        long j = this.b;
        if (j < 4) {
            throw new IllegalStateException("size < 4: " + this.b);
        }
        y yVar = this.f268a;
        int i = yVar.b;
        int i2 = yVar.c;
        if (i2 - i < 4) {
            return (H() & 255) | ((H() & 255) << 24) | ((H() & 255) << 16) | ((H() & 255) << 8);
        }
        byte[] bArr = yVar.f280a;
        int i3 = i + 1;
        int i4 = i3 + 1;
        int i5 = ((bArr[i] & 255) << 24) | ((bArr[i3] & 255) << 16);
        int i6 = i4 + 1;
        int i7 = i5 | ((bArr[i4] & 255) << 8);
        int i8 = i6 + 1;
        int i9 = i7 | (bArr[i6] & 255);
        this.b = j - 4;
        if (i8 == i2) {
            this.f268a = yVar.b();
            z.a(yVar);
        } else {
            yVar.b = i8;
        }
        return i9;
    }

    public final void M() {
        try {
            m(this.b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    /* JADX INFO: renamed from: N, reason: merged with bridge method [inline-methods] */
    public h clone() {
        h hVar = new h();
        if (this.b == 0) {
            return hVar;
        }
        y yVarD = this.f268a.d();
        hVar.f268a = yVarD;
        yVarD.g = yVarD;
        yVarD.f = yVarD;
        y yVar = this.f268a;
        while (true) {
            yVar = yVar.f;
            if (yVar == this.f268a) {
                hVar.b = this.b;
                return hVar;
            }
            hVar.f268a.g.c(yVar.d());
        }
    }

    public final long O() {
        long j = this.b;
        if (j == 0) {
            return 0L;
        }
        y yVar = this.f268a.g;
        int i = yVar.c;
        return (i >= 8192 || !yVar.e) ? j : j - ((long) (i - yVar.b));
    }

    public final h P(h hVar, long j, long j2) {
        if (hVar == null) {
            throw new IllegalArgumentException("out == null");
        }
        E.b(this.b, j, j2);
        if (j2 == 0) {
            return this;
        }
        hVar.b += j2;
        y yVar = this.f268a;
        while (true) {
            int i = yVar.c;
            int i2 = yVar.b;
            if (j < i - i2) {
                break;
            }
            j -= (long) (i - i2);
            yVar = yVar.f;
        }
        while (j2 > 0) {
            y yVarD = yVar.d();
            int i3 = (int) (((long) yVarD.b) + j);
            yVarD.b = i3;
            yVarD.c = Math.min(i3 + ((int) j2), yVarD.c);
            y yVar2 = hVar.f268a;
            if (yVar2 == null) {
                yVarD.g = yVarD;
                yVarD.f = yVarD;
                hVar.f268a = yVarD;
            } else {
                yVar2.g.c(yVarD);
            }
            j2 -= (long) (yVarD.c - yVarD.b);
            yVar = yVar.f;
            j = 0;
        }
        return this;
    }

    public h Q() {
        return this;
    }

    public final byte R(long j) {
        int i;
        E.b(this.b, j, 1L);
        long j2 = this.b;
        if (j2 - j <= j) {
            long j3 = j - j2;
            y yVar = this.f268a;
            do {
                yVar = yVar.g;
                int i2 = yVar.c;
                i = yVar.b;
                j3 += (long) (i2 - i);
            } while (j3 < 0);
            return yVar.f280a[i + ((int) j3)];
        }
        y yVar2 = this.f268a;
        while (true) {
            int i3 = yVar2.c;
            int i4 = yVar2.b;
            long j4 = i3 - i4;
            if (j < j4) {
                return yVar2.f280a[i4 + ((int) j)];
            }
            j -= j4;
            yVar2 = yVar2.f;
        }
    }

    public long S(byte b, long j, long j2) {
        y yVar;
        long j3 = 0;
        if (j < 0 || j2 < j) {
            throw new IllegalArgumentException(String.format("size=%s fromIndex=%s toIndex=%s", Long.valueOf(this.b), Long.valueOf(j), Long.valueOf(j2)));
        }
        long j4 = this.b;
        if (j2 > j4) {
            j2 = j4;
        }
        if (j == j2 || (yVar = this.f268a) == null) {
            return -1L;
        }
        long j5 = this.b;
        if (j5 - j < j) {
            while (j5 > j) {
                yVar = yVar.g;
                j5 -= (long) (yVar.c - yVar.b);
            }
        } else {
            while (true) {
                long j6 = ((long) (yVar.c - yVar.b)) + j3;
                if (j6 >= j) {
                    break;
                }
                yVar = yVar.f;
                j3 = j6;
            }
            j5 = j3;
        }
        while (j5 < j2) {
            byte[] bArr = yVar.f280a;
            int iMin = (int) Math.min(yVar.c, (((long) yVar.b) + j2) - j5);
            for (int i = (int) ((((long) yVar.b) + j) - j5); i < iMin; i++) {
                if (bArr[i] == b) {
                    return ((long) (i - yVar.b)) + j5;
                }
            }
            j5 += (long) (yVar.c - yVar.b);
            yVar = yVar.f;
            j = j5;
        }
        return -1L;
    }

    public boolean T(long j, ByteString byteString, int i, int i2) {
        if (j < 0 || i < 0 || i2 < 0 || this.b - j < i2 || byteString.r() - i < i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (R(((long) i3) + j) != byteString.i(i + i3)) {
                return false;
            }
        }
        return true;
    }

    public int U(byte[] bArr, int i, int i2) {
        E.b(bArr.length, i, i2);
        y yVar = this.f268a;
        if (yVar == null) {
            return -1;
        }
        int iMin = Math.min(i2, yVar.c - yVar.b);
        System.arraycopy(yVar.f280a, yVar.b, bArr, i, iMin);
        int i3 = yVar.b + iMin;
        yVar.b = i3;
        this.b -= (long) iMin;
        if (i3 == yVar.c) {
            this.f268a = yVar.b();
            z.a(yVar);
        }
        return iMin;
    }

    public byte[] V() {
        try {
            return w(this.b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public ByteString W() {
        return new ByteString(V());
    }

    public String X(long j, Charset charset) {
        E.b(this.b, 0L, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        }
        if (j == 0) {
            return "";
        }
        y yVar = this.f268a;
        if (((long) yVar.b) + j > yVar.c) {
            return new String(w(j), charset);
        }
        String str = new String(yVar.f280a, yVar.b, (int) j, charset);
        int i = (int) (((long) yVar.b) + j);
        yVar.b = i;
        this.b -= j;
        if (i == yVar.c) {
            this.f268a = yVar.b();
            z.a(yVar);
        }
        return str;
    }

    public String Y() {
        try {
            return X(this.b, E.f262a);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public String Z(long j) {
        return X(j, E.f262a);
    }

    public int a0() throws EOFException {
        int i;
        int i2;
        int i3;
        if (this.b == 0) {
            throw new EOFException();
        }
        byte bR = R(0L);
        if ((bR & 128) == 0) {
            i = bR & 127;
            i3 = 0;
            i2 = 1;
        } else if ((bR & 224) == 192) {
            i = bR & 31;
            i2 = 2;
            i3 = 128;
        } else if ((bR & 240) == 224) {
            i = bR & 15;
            i2 = 3;
            i3 = 2048;
        } else {
            if ((bR & 248) != 240) {
                m(1L);
                return 65533;
            }
            i = bR & 7;
            i2 = 4;
            i3 = 65536;
        }
        long j = i2;
        if (this.b < j) {
            throw new EOFException("size < " + i2 + ": " + this.b + " (to read code point prefixed 0x" + Integer.toHexString(bR) + ")");
        }
        for (int i4 = 1; i4 < i2; i4++) {
            long j2 = i4;
            byte bR2 = R(j2);
            if ((bR2 & 192) != 128) {
                m(j2);
                return 65533;
            }
            i = (i << 6) | (bR2 & 63);
        }
        m(j);
        if (i > 1114111) {
            return 65533;
        }
        if ((i < 55296 || i > 57343) && i >= i3) {
            return i;
        }
        return 65533;
    }

    @Override // okio.B
    public D b() {
        return D.d;
    }

    String b0(long j) throws EOFException {
        if (j > 0) {
            long j2 = j - 1;
            if (R(j2) == 13) {
                String strZ = Z(j2);
                m(2L);
                return strZ;
            }
        }
        String strZ2 = Z(j);
        m(1L);
        return strZ2;
    }

    @Override // okio.j, okio.i
    public h c() {
        return this;
    }

    public final long c0() {
        return this.b;
    }

    @Override // okio.B, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // okio.j
    public ByteString d(long j) {
        return new ByteString(w(j));
    }

    public final ByteString d0() {
        long j = this.b;
        if (j <= 2147483647L) {
            return e0((int) j);
        }
        throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.b);
    }

    public final ByteString e0(int i) {
        return i == 0 ? ByteString.d : new SegmentedByteString(this, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        long j = this.b;
        if (j != hVar.b) {
            return false;
        }
        long j2 = 0;
        if (j == 0) {
            return true;
        }
        y yVar = this.f268a;
        y yVar2 = hVar.f268a;
        int i = yVar.b;
        int i2 = yVar2.b;
        while (j2 < this.b) {
            long jMin = Math.min(yVar.c - i, yVar2.c - i2);
            int i3 = 0;
            while (i3 < jMin) {
                int i4 = i + 1;
                int i5 = i2 + 1;
                if (yVar.f280a[i] != yVar2.f280a[i2]) {
                    return false;
                }
                i3++;
                i = i4;
                i2 = i5;
            }
            if (i == yVar.c) {
                yVar = yVar.f;
                i = yVar.b;
            }
            if (i2 == yVar2.c) {
                yVar2 = yVar2.f;
                i2 = yVar2.b;
            }
            j2 += jMin;
        }
        return true;
    }

    @Override // okio.i
    public /* bridge */ /* synthetic */ i f(String str) {
        r0(str);
        return this;
    }

    y f0(int i) {
        if (i < 1 || i > 8192) {
            throw new IllegalArgumentException();
        }
        y yVar = this.f268a;
        if (yVar == null) {
            y yVarB = z.b();
            this.f268a = yVarB;
            yVarB.g = yVarB;
            yVarB.f = yVarB;
            return yVarB;
        }
        y yVar2 = yVar.g;
        if (yVar2.c + i <= 8192 && yVar2.e) {
            return yVar2;
        }
        y yVarB2 = z.b();
        yVar2.c(yVarB2);
        return yVarB2;
    }

    @Override // okio.i, okio.A, java.io.Flushable
    public void flush() {
    }

    @Override // okio.i
    public /* bridge */ /* synthetic */ i g(byte[] bArr) {
        h0(bArr);
        return this;
    }

    public h g0(ByteString byteString) {
        if (byteString == null) {
            throw new IllegalArgumentException("byteString == null");
        }
        byteString.x(this);
        return this;
    }

    @Override // okio.i
    public /* bridge */ /* synthetic */ i h(int i) {
        n0(i);
        return this;
    }

    public h h0(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("source == null");
        }
        i0(bArr, 0, bArr.length);
        return this;
    }

    public int hashCode() {
        y yVar = this.f268a;
        if (yVar == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = yVar.c;
            for (int i3 = yVar.b; i3 < i2; i3++) {
                i = (i * 31) + yVar.f280a[i3];
            }
            yVar = yVar.f;
        } while (yVar != this.f268a);
        return i;
    }

    @Override // okio.j
    public int i() {
        return E.c(L());
    }

    public h i0(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = i2;
        E.b(bArr.length, i, j);
        int i3 = i2 + i;
        while (i < i3) {
            y yVarF0 = f0(1);
            int iMin = Math.min(i3 - i, 8192 - yVarF0.c);
            System.arraycopy(bArr, i, yVarF0.f280a, yVarF0.c, iMin);
            i += iMin;
            yVarF0.c += iMin;
        }
        this.b += j;
        return this;
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return true;
    }

    @Override // okio.i
    public /* bridge */ /* synthetic */ i j(byte[] bArr, int i, int i2) {
        i0(bArr, i, i2);
        return this;
    }

    public long j0(B b) {
        if (b == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long jK = b.K(this, 8192L);
            if (jK == -1) {
                return j;
            }
            j += jK;
        }
    }

    @Override // okio.i
    public /* bridge */ /* synthetic */ i k(long j) {
        l0(j);
        return this;
    }

    public h k0(int i) {
        y yVarF0 = f0(1);
        byte[] bArr = yVarF0.f280a;
        int i2 = yVarF0.c;
        yVarF0.c = i2 + 1;
        bArr[i2] = (byte) i;
        this.b++;
        return this;
    }

    @Override // okio.j
    public String l(long j) throws EOFException {
        if (j < 0) {
            throw new IllegalArgumentException("limit < 0: " + j);
        }
        long j2 = j != Long.MAX_VALUE ? j + 1 : Long.MAX_VALUE;
        long jS = S((byte) 10, 0L, j2);
        if (jS != -1) {
            return b0(jS);
        }
        if (j2 < c0() && R(j2 - 1) == 13 && R(j2) == 10) {
            return b0(j2);
        }
        h hVar = new h();
        P(hVar, 0L, Math.min(32L, c0()));
        throw new EOFException("\\n not found: limit=" + Math.min(c0(), j) + " content=" + hVar.W().j() + (char) 8230);
    }

    public h l0(long j) {
        if (j == 0) {
            k0(48);
            return this;
        }
        boolean z = false;
        int i = 1;
        if (j < 0) {
            j = -j;
            if (j < 0) {
                r0("-9223372036854775808");
                return this;
            }
            z = true;
        }
        if (j < 100000000) {
            if (j < 10000) {
                if (j >= 100) {
                    i = j < 1000 ? 3 : 4;
                } else if (j >= 10) {
                    i = 2;
                }
            } else if (j < 1000000) {
                i = j < 100000 ? 5 : 6;
            } else {
                i = j < 10000000 ? 7 : 8;
            }
        } else if (j < 1000000000000L) {
            if (j < 10000000000L) {
                i = j < 1000000000 ? 9 : 10;
            } else {
                i = j < 100000000000L ? 11 : 12;
            }
        } else if (j < 1000000000000000L) {
            if (j < 10000000000000L) {
                i = 13;
            } else {
                i = j < 100000000000000L ? 14 : 15;
            }
        } else if (j < 100000000000000000L) {
            i = j < 10000000000000000L ? 16 : 17;
        } else {
            i = j < 1000000000000000000L ? 18 : 19;
        }
        if (z) {
            i++;
        }
        y yVarF0 = f0(i);
        byte[] bArr = yVarF0.f280a;
        int i2 = yVarF0.c + i;
        while (j != 0) {
            i2--;
            bArr[i2] = c[(int) (j % 10)];
            j /= 10;
        }
        if (z) {
            bArr[i2 - 1] = 45;
        }
        yVarF0.c += i;
        this.b += (long) i;
        return this;
    }

    @Override // okio.j
    public void m(long j) throws EOFException {
        while (j > 0) {
            y yVar = this.f268a;
            if (yVar == null) {
                throw new EOFException();
            }
            int iMin = (int) Math.min(j, yVar.c - yVar.b);
            long j2 = iMin;
            this.b -= j2;
            j -= j2;
            y yVar2 = this.f268a;
            int i = yVar2.b + iMin;
            yVar2.b = i;
            if (i == yVar2.c) {
                this.f268a = yVar2.b();
                z.a(yVar2);
            }
        }
    }

    public h m0(long j) {
        if (j == 0) {
            k0(48);
            return this;
        }
        int iNumberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j)) / 4) + 1;
        y yVarF0 = f0(iNumberOfTrailingZeros);
        byte[] bArr = yVarF0.f280a;
        int i = yVarF0.c;
        for (int i2 = (i + iNumberOfTrailingZeros) - 1; i2 >= i; i2--) {
            bArr[i2] = c[(int) (15 & j)];
            j >>>= 4;
        }
        yVarF0.c += iNumberOfTrailingZeros;
        this.b += (long) iNumberOfTrailingZeros;
        return this;
    }

    @Override // okio.j
    public boolean n() {
        return this.b == 0;
    }

    public h n0(int i) {
        y yVarF0 = f0(4);
        byte[] bArr = yVarF0.f280a;
        int i2 = yVarF0.c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i >>> 16) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i >>> 8) & 255);
        bArr[i5] = (byte) (i & 255);
        yVarF0.c = i5 + 1;
        this.b += 4;
        return this;
    }

    @Override // okio.A
    public void o(h hVar, long j) {
        if (hVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (hVar == this) {
            throw new IllegalArgumentException("source == this");
        }
        E.b(hVar.b, 0L, j);
        while (j > 0) {
            y yVar = hVar.f268a;
            if (j < yVar.c - yVar.b) {
                y yVar2 = this.f268a;
                y yVar3 = yVar2 != null ? yVar2.g : null;
                if (yVar3 != null && yVar3.e) {
                    if ((((long) yVar3.c) + j) - ((long) (yVar3.d ? 0 : yVar3.b)) <= 8192) {
                        hVar.f268a.f(yVar3, (int) j);
                        hVar.b -= j;
                        this.b += j;
                        return;
                    }
                }
                hVar.f268a = hVar.f268a.e((int) j);
            }
            y yVar4 = hVar.f268a;
            long j2 = yVar4.c - yVar4.b;
            hVar.f268a = yVar4.b();
            y yVar5 = this.f268a;
            if (yVar5 == null) {
                this.f268a = yVar4;
                yVar4.g = yVar4;
                yVar4.f = yVar4;
            } else {
                yVar5.g.c(yVar4);
                yVar4.a();
            }
            hVar.b -= j2;
            this.b += j2;
            j -= j2;
        }
    }

    public h o0(int i) {
        y yVarF0 = f0(2);
        byte[] bArr = yVarF0.f280a;
        int i2 = yVarF0.c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        bArr[i3] = (byte) (i & 255);
        yVarF0.c = i3 + 1;
        this.b += 2;
        return this;
    }

    public h p0(String str, int i, int i2, Charset charset) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (i < 0) {
            throw new IllegalAccessError("beginIndex < 0: " + i);
        }
        if (i2 < i) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
        }
        if (i2 > str.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + i2 + " > " + str.length());
        }
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        if (charset.equals(E.f262a)) {
            s0(str, i, i2);
            return this;
        }
        byte[] bytes = str.substring(i, i2).getBytes(charset);
        i0(bytes, 0, bytes.length);
        return this;
    }

    @Override // okio.i
    public /* bridge */ /* synthetic */ i q(int i) {
        k0(i);
        return this;
    }

    public h q0(String str, Charset charset) {
        p0(str, 0, str.length(), charset);
        return this;
    }

    @Override // okio.i
    public OutputStream r() {
        return new f(this);
    }

    public h r0(String str) {
        s0(str, 0, str.length());
        return this;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) {
        y yVar = this.f268a;
        if (yVar == null) {
            return -1;
        }
        int iMin = Math.min(byteBuffer.remaining(), yVar.c - yVar.b);
        byteBuffer.put(yVar.f280a, yVar.b, iMin);
        int i = yVar.b + iMin;
        yVar.b = i;
        this.b -= (long) iMin;
        if (i == yVar.c) {
            this.f268a = yVar.b();
            z.a(yVar);
        }
        return iMin;
    }

    @Override // okio.j
    public void s(byte[] bArr) throws EOFException {
        int i = 0;
        while (i < bArr.length) {
            int iU = U(bArr, i, bArr.length - i);
            if (iU == -1) {
                throw new EOFException();
            }
            i += iU;
        }
    }

    public h s0(String str, int i, int i2) {
        int i3;
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (i < 0) {
            throw new IllegalArgumentException("beginIndex < 0: " + i);
        }
        if (i2 < i) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
        }
        if (i2 > str.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + i2 + " > " + str.length());
        }
        while (i < i2) {
            char cCharAt = str.charAt(i);
            if (cCharAt < 128) {
                y yVarF0 = f0(1);
                byte[] bArr = yVarF0.f280a;
                int i4 = yVarF0.c - i;
                int iMin = Math.min(i2, 8192 - i4);
                int i5 = i + 1;
                bArr[i + i4] = (byte) cCharAt;
                while (i5 < iMin) {
                    char cCharAt2 = str.charAt(i5);
                    if (cCharAt2 >= 128) {
                        break;
                    }
                    bArr[i5 + i4] = (byte) cCharAt2;
                    i5++;
                }
                int i6 = yVarF0.c;
                int i7 = (i4 + i5) - i6;
                yVarF0.c = i6 + i7;
                this.b += (long) i7;
                i = i5;
            } else {
                if (cCharAt < 2048) {
                    i3 = (cCharAt >> 6) | 192;
                } else if (cCharAt < 55296 || cCharAt > 57343) {
                    k0((cCharAt >> '\f') | 224);
                    i3 = ((cCharAt >> 6) & 63) | 128;
                } else {
                    int i8 = i + 1;
                    char cCharAt3 = i8 < i2 ? str.charAt(i8) : (char) 0;
                    if (cCharAt > 56319 || cCharAt3 < 56320 || cCharAt3 > 57343) {
                        k0(63);
                        i = i8;
                    } else {
                        int i9 = (((cCharAt & 10239) << 10) | (9215 & cCharAt3)) + 65536;
                        k0((i9 >> 18) | 240);
                        k0(((i9 >> 12) & 63) | 128);
                        k0(((i9 >> 6) & 63) | 128);
                        k0((i9 & 63) | 128);
                        i += 2;
                    }
                }
                k0(i3);
                k0((cCharAt & '?') | 128);
                i++;
            }
        }
        return this;
    }

    @Override // okio.j
    public short t() {
        long j = this.b;
        if (j < 2) {
            throw new IllegalStateException("size < 2: " + this.b);
        }
        y yVar = this.f268a;
        int i = yVar.b;
        int i2 = yVar.c;
        if (i2 - i < 2) {
            return (short) ((H() & 255) | ((H() & 255) << 8));
        }
        byte[] bArr = yVar.f280a;
        int i3 = i + 1;
        int i4 = i3 + 1;
        int i5 = ((bArr[i] & 255) << 8) | (bArr[i3] & 255);
        this.b = j - 2;
        if (i4 == i2) {
            this.f268a = yVar.b();
            z.a(yVar);
        } else {
            yVar.b = i4;
        }
        return (short) i5;
    }

    public h t0(int i) {
        int i2;
        int i3;
        if (i >= 128) {
            if (i < 2048) {
                i3 = (i >> 6) | 192;
            } else {
                if (i < 65536) {
                    if (i < 55296 || i > 57343) {
                        i2 = (i >> 12) | 224;
                    } else {
                        k0(63);
                    }
                } else {
                    if (i > 1114111) {
                        throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(i));
                    }
                    k0((i >> 18) | 240);
                    i2 = ((i >> 12) & 63) | 128;
                }
                k0(i2);
                i3 = ((i >> 6) & 63) | 128;
            }
            k0(i3);
            i = (i & 63) | 128;
            k0(i);
        } else {
            k0(i);
        }
        return this;
    }

    public String toString() {
        return d0().toString();
    }

    @Override // okio.j
    public long u(byte b) {
        return S(b, 0L, Long.MAX_VALUE);
    }

    @Override // okio.j
    public short v() {
        return E.d(t());
    }

    @Override // okio.j
    public byte[] w(long j) throws EOFException {
        E.b(this.b, 0L, j);
        if (j <= 2147483647L) {
            byte[] bArr = new byte[(int) j];
            s(bArr);
            return bArr;
        }
        throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            throw new IllegalArgumentException("source == null");
        }
        int iRemaining = byteBuffer.remaining();
        int i = iRemaining;
        while (i > 0) {
            y yVarF0 = f0(1);
            int iMin = Math.min(i, 8192 - yVarF0.c);
            byteBuffer.get(yVarF0.f280a, yVarF0.c, iMin);
            i -= iMin;
            yVarF0.c += iMin;
        }
        this.b += (long) iRemaining;
        return iRemaining;
    }

    @Override // okio.i
    public /* bridge */ /* synthetic */ i x(int i) {
        o0(i);
        return this;
    }

    @Override // okio.j
    public boolean y(long j, ByteString byteString) {
        return T(j, byteString, 0, byteString.r());
    }

    @Override // okio.j
    public boolean z(long j) {
        return this.b >= j;
    }
}
