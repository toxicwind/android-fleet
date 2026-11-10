package d;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

/* JADX INFO: loaded from: classes2.dex */
public final class n implements C {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final i f2116b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Inflater f2117c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final o f2118d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2115a = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final CRC32 f2119e = new CRC32();

    public n(C c2) {
        if (c2 == null) {
            throw new IllegalArgumentException("source == null");
        }
        this.f2117c = new Inflater(true);
        this.f2116b = t.a(c2);
        this.f2118d = new o(this.f2116b, this.f2117c);
    }

    public final void a(g gVar, long j, long j2) {
        y yVar = gVar.f2101b;
        while (true) {
            int i = yVar.f2140c;
            int i2 = yVar.f2139b;
            if (j < i - i2) {
                break;
            }
            j -= (long) (i - i2);
            yVar = yVar.f2143f;
        }
        while (j2 > 0) {
            int i3 = (int) (((long) yVar.f2139b) + j);
            int iMin = (int) Math.min(yVar.f2140c - i3, j2);
            this.f2119e.update(yVar.f2138a, i3, iMin);
            j2 -= (long) iMin;
            yVar = yVar.f2143f;
            j = 0;
        }
    }

    @Override // d.C, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        o oVar = this.f2118d;
        if (oVar.f2123d) {
            return;
        }
        oVar.f2121b.end();
        oVar.f2123d = true;
        oVar.f2120a.close();
    }

    @Override // d.C
    public long read(g gVar, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException(b.a.a.a.a.a("byteCount < 0: ", j));
        }
        if (j == 0) {
            return 0L;
        }
        if (this.f2115a == 0) {
            this.f2116b.c(10L);
            byte bG = this.f2116b.a().g(3L);
            boolean z = ((bG >> 1) & 1) == 1;
            if (z) {
                a(this.f2116b.a(), 0L, 10L);
            }
            a("ID1ID2", 8075, this.f2116b.readShort());
            this.f2116b.skip(8L);
            if (((bG >> 2) & 1) == 1) {
                this.f2116b.c(2L);
                if (z) {
                    a(this.f2116b.a(), 0L, 2L);
                }
                long jC = this.f2116b.a().c();
                this.f2116b.c(jC);
                if (z) {
                    a(this.f2116b.a(), 0L, jC);
                }
                this.f2116b.skip(jC);
            }
            if (((bG >> 3) & 1) == 1) {
                long jA = this.f2116b.a((byte) 0);
                if (jA == -1) {
                    throw new EOFException();
                }
                if (z) {
                    a(this.f2116b.a(), 0L, jA + 1);
                }
                this.f2116b.skip(jA + 1);
            }
            if (((bG >> 4) & 1) == 1) {
                long jA2 = this.f2116b.a((byte) 0);
                if (jA2 == -1) {
                    throw new EOFException();
                }
                if (z) {
                    a(this.f2116b.a(), 0L, jA2 + 1);
                }
                this.f2116b.skip(jA2 + 1);
            }
            if (z) {
                a("FHCRC", this.f2116b.c(), (short) this.f2119e.getValue());
                this.f2119e.reset();
            }
            this.f2115a = 1;
        }
        if (this.f2115a == 1) {
            long j2 = gVar.f2102c;
            long j3 = this.f2118d.read(gVar, j);
            if (j3 != -1) {
                a(gVar, j2, j3);
                return j3;
            }
            this.f2115a = 2;
        }
        if (this.f2115a == 2) {
            a("CRC", this.f2116b.g(), (int) this.f2119e.getValue());
            a("ISIZE", this.f2116b.g(), (int) this.f2117c.getBytesWritten());
            this.f2115a = 3;
            if (!this.f2116b.e()) {
                throw new IOException("gzip finished without exhausting source");
            }
        }
        return -1L;
    }

    @Override // d.C
    public E timeout() {
        return this.f2116b.timeout();
    }

    public final void a(String str, int i, int i2) throws IOException {
        if (i2 != i) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", str, Integer.valueOf(i2), Integer.valueOf(i)));
        }
    }
}
