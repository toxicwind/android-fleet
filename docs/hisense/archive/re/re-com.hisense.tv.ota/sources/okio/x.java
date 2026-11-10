package okio;

import java.io.EOFException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
final class x implements j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h f279a = new h();
    public final B b;
    boolean c;

    x(B b) {
        if (b == null) {
            throw new NullPointerException("source == null");
        }
        this.b = b;
    }

    @Override // okio.j
    public long A() throws EOFException {
        J(1L);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (!z(i2)) {
                break;
            }
            byte bR = this.f279a.R(i);
            if ((bR < 48 || bR > 57) && ((bR < 97 || bR > 102) && (bR < 65 || bR > 70))) {
                if (i != 0) {
                    break;
                }
                throw new NumberFormatException(String.format("Expected leading [0-9a-fA-F] character but was %#x", Byte.valueOf(bR)));
            }
            i = i2;
        }
        return this.f279a.A();
    }

    @Override // okio.j
    public String C(Charset charset) {
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        this.f279a.j0(this.b);
        return this.f279a.C(charset);
    }

    @Override // okio.j
    public InputStream D() {
        return new w(this);
    }

    @Override // okio.j
    public String E() {
        return l(Long.MAX_VALUE);
    }

    @Override // okio.j
    public byte H() throws EOFException {
        J(1L);
        return this.f279a.H();
    }

    @Override // okio.j
    public long I() throws EOFException {
        J(1L);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (!z(i2)) {
                break;
            }
            byte bR = this.f279a.R(i);
            if ((bR < 48 || bR > 57) && !(i == 0 && bR == 45)) {
                if (i != 0) {
                    break;
                }
                throw new NumberFormatException(String.format("Expected leading [0-9] or '-' character but was %#x", Byte.valueOf(bR)));
            }
            i = i2;
        }
        return this.f279a.I();
    }

    @Override // okio.j
    public void J(long j) throws EOFException {
        if (!z(j)) {
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
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        h hVar2 = this.f279a;
        if (hVar2.b == 0 && this.b.K(hVar2, 8192L) == -1) {
            return -1L;
        }
        return this.f279a.K(hVar, Math.min(j, this.f279a.b));
    }

    @Override // okio.j
    public int L() throws EOFException {
        J(4L);
        return this.f279a.L();
    }

    public long a(byte b, long j, long j2) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        if (j < 0 || j2 < j) {
            throw new IllegalArgumentException(String.format("fromIndex=%s toIndex=%s", Long.valueOf(j), Long.valueOf(j2)));
        }
        while (j < j2) {
            long jS = this.f279a.S(b, j, j2);
            if (jS != -1) {
                return jS;
            }
            h hVar = this.f279a;
            long j3 = hVar.b;
            if (j3 >= j2 || this.b.K(hVar, 8192L) == -1) {
                break;
            }
            j = Math.max(j, j3);
        }
        return -1L;
    }

    @Override // okio.B
    public D b() {
        return this.b.b();
    }

    @Override // okio.j, okio.i
    public h c() {
        return this.f279a;
    }

    @Override // okio.B, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.c) {
            return;
        }
        this.c = true;
        this.b.close();
        this.f279a.M();
    }

    @Override // okio.j
    public ByteString d(long j) throws EOFException {
        J(j);
        return this.f279a.d(j);
    }

    public boolean e(long j, ByteString byteString, int i, int i2) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        if (j < 0 || i < 0 || i2 < 0 || byteString.r() - i < i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            long j2 = ((long) i3) + j;
            if (!z(1 + j2) || this.f279a.R(j2) != byteString.i(i + i3)) {
                return false;
            }
        }
        return true;
    }

    @Override // okio.j
    public int i() throws EOFException {
        J(4L);
        return this.f279a.i();
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.c;
    }

    @Override // okio.j
    public String l(long j) throws EOFException {
        if (j < 0) {
            throw new IllegalArgumentException("limit < 0: " + j);
        }
        long j2 = j == Long.MAX_VALUE ? Long.MAX_VALUE : j + 1;
        long jA = a((byte) 10, 0L, j2);
        if (jA != -1) {
            return this.f279a.b0(jA);
        }
        if (j2 < Long.MAX_VALUE && z(j2) && this.f279a.R(j2 - 1) == 13 && z(1 + j2) && this.f279a.R(j2) == 10) {
            return this.f279a.b0(j2);
        }
        h hVar = new h();
        h hVar2 = this.f279a;
        hVar2.P(hVar, 0L, Math.min(32L, hVar2.c0()));
        throw new EOFException("\\n not found: limit=" + Math.min(this.f279a.c0(), j) + " content=" + hVar.W().j() + (char) 8230);
    }

    @Override // okio.j
    public void m(long j) throws EOFException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            h hVar = this.f279a;
            if (hVar.b == 0 && this.b.K(hVar, 8192L) == -1) {
                throw new EOFException();
            }
            long jMin = Math.min(j, this.f279a.c0());
            this.f279a.m(jMin);
            j -= jMin;
        }
    }

    @Override // okio.j
    public boolean n() {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        return this.f279a.n() && this.b.K(this.f279a, 8192L) == -1;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) {
        h hVar = this.f279a;
        if (hVar.b == 0 && this.b.K(hVar, 8192L) == -1) {
            return -1;
        }
        return this.f279a.read(byteBuffer);
    }

    @Override // okio.j
    public void s(byte[] bArr) throws EOFException {
        try {
            J(bArr.length);
            this.f279a.s(bArr);
        } catch (EOFException e) {
            int i = 0;
            while (true) {
                h hVar = this.f279a;
                long j = hVar.b;
                if (j <= 0) {
                    throw e;
                }
                int iU = hVar.U(bArr, i, (int) j);
                if (iU == -1) {
                    throw new AssertionError();
                }
                i += iU;
            }
        }
    }

    @Override // okio.j
    public short t() throws EOFException {
        J(2L);
        return this.f279a.t();
    }

    public String toString() {
        return "buffer(" + this.b + ")";
    }

    @Override // okio.j
    public long u(byte b) {
        return a(b, 0L, Long.MAX_VALUE);
    }

    @Override // okio.j
    public short v() throws EOFException {
        J(2L);
        return this.f279a.v();
    }

    @Override // okio.j
    public byte[] w(long j) throws EOFException {
        J(j);
        return this.f279a.w(j);
    }

    @Override // okio.j
    public boolean y(long j, ByteString byteString) {
        return e(j, byteString, 0, byteString.r());
    }

    @Override // okio.j
    public boolean z(long j) {
        h hVar;
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        do {
            hVar = this.f279a;
            if (hVar.b >= j) {
                return true;
            }
        } while (this.b.K(hVar, 8192L) != -1);
        return false;
    }
}
