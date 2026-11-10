package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

/* JADX INFO: loaded from: classes.dex */
public final class n implements B {
    private final j b;
    private final Inflater c;
    private final o d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f271a = 0;
    private final CRC32 e = new CRC32();

    public n(B b) {
        if (b == null) {
            throw new IllegalArgumentException("source == null");
        }
        this.c = new Inflater(true);
        j jVarD = t.d(b);
        this.b = jVarD;
        this.d = new o(jVarD, this.c);
    }

    private void B(h hVar, long j, long j2) {
        y yVar = hVar.f268a;
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
            int i3 = (int) (((long) yVar.b) + j);
            int iMin = (int) Math.min(yVar.c - i3, j2);
            this.e.update(yVar.f280a, i3, iMin);
            j2 -= (long) iMin;
            yVar = yVar.f;
            j = 0;
        }
    }

    private void a(String str, int i, int i2) throws IOException {
        if (i2 != i) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", str, Integer.valueOf(i2), Integer.valueOf(i)));
        }
    }

    private void e() throws IOException {
        this.b.J(10L);
        byte bR = this.b.c().R(3L);
        boolean z = ((bR >> 1) & 1) == 1;
        if (z) {
            B(this.b.c(), 0L, 10L);
        }
        a("ID1ID2", 8075, this.b.t());
        this.b.m(8L);
        if (((bR >> 2) & 1) == 1) {
            this.b.J(2L);
            if (z) {
                B(this.b.c(), 0L, 2L);
            }
            long jV = this.b.c().v();
            this.b.J(jV);
            if (z) {
                B(this.b.c(), 0L, jV);
            }
            this.b.m(jV);
        }
        if (((bR >> 3) & 1) == 1) {
            long jU = this.b.u((byte) 0);
            if (jU == -1) {
                throw new EOFException();
            }
            if (z) {
                B(this.b.c(), 0L, jU + 1);
            }
            this.b.m(jU + 1);
        }
        if (((bR >> 4) & 1) == 1) {
            long jU2 = this.b.u((byte) 0);
            if (jU2 == -1) {
                throw new EOFException();
            }
            if (z) {
                B(this.b.c(), 0L, jU2 + 1);
            }
            this.b.m(jU2 + 1);
        }
        if (z) {
            a("FHCRC", this.b.v(), (short) this.e.getValue());
            this.e.reset();
        }
    }

    private void p() throws IOException {
        a("CRC", this.b.i(), (int) this.e.getValue());
        a("ISIZE", this.b.i(), (int) this.c.getBytesWritten());
    }

    @Override // okio.B
    public long K(h hVar, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (j == 0) {
            return 0L;
        }
        if (this.f271a == 0) {
            e();
            this.f271a = 1;
        }
        if (this.f271a == 1) {
            long j2 = hVar.b;
            long jK = this.d.K(hVar, j);
            if (jK != -1) {
                B(hVar, j2, jK);
                return jK;
            }
            this.f271a = 2;
        }
        if (this.f271a == 2) {
            p();
            this.f271a = 3;
            if (!this.b.n()) {
                throw new IOException("gzip finished without exhausting source");
            }
        }
        return -1L;
    }

    @Override // okio.B
    public D b() {
        return this.b.b();
    }

    @Override // okio.B, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.d.close();
    }
}
