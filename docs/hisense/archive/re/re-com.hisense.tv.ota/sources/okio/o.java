package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* JADX INFO: loaded from: classes.dex */
public final class o implements B {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final j f272a;
    private final Inflater b;
    private int c;
    private boolean d;

    o(j jVar, Inflater inflater) {
        if (jVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (inflater == null) {
            throw new IllegalArgumentException("inflater == null");
        }
        this.f272a = jVar;
        this.b = inflater;
    }

    private void e() {
        int i = this.c;
        if (i == 0) {
            return;
        }
        int remaining = i - this.b.getRemaining();
        this.c -= remaining;
        this.f272a.m(remaining);
    }

    @Override // okio.B
    public long K(h hVar, long j) throws IOException {
        boolean zA;
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.d) {
            throw new IllegalStateException("closed");
        }
        if (j == 0) {
            return 0L;
        }
        do {
            zA = a();
            try {
                y yVarF0 = hVar.f0(1);
                int iInflate = this.b.inflate(yVarF0.f280a, yVarF0.c, (int) Math.min(j, 8192 - yVarF0.c));
                if (iInflate > 0) {
                    yVarF0.c += iInflate;
                    long j2 = iInflate;
                    hVar.b += j2;
                    return j2;
                }
                if (!this.b.finished() && !this.b.needsDictionary()) {
                }
                e();
                if (yVarF0.b != yVarF0.c) {
                    return -1L;
                }
                hVar.f268a = yVarF0.b();
                z.a(yVarF0);
                return -1L;
            } catch (DataFormatException e) {
                throw new IOException(e);
            }
        } while (!zA);
        throw new EOFException("source exhausted prematurely");
    }

    public final boolean a() {
        if (!this.b.needsInput()) {
            return false;
        }
        e();
        if (this.b.getRemaining() != 0) {
            throw new IllegalStateException("?");
        }
        if (this.f272a.n()) {
            return true;
        }
        y yVar = this.f272a.c().f268a;
        int i = yVar.c;
        int i2 = yVar.b;
        int i3 = i - i2;
        this.c = i3;
        this.b.setInput(yVar.f280a, i2, i3);
        return false;
    }

    @Override // okio.B
    public D b() {
        return this.f272a.b();
    }

    @Override // okio.B, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.d) {
            return;
        }
        this.b.end();
        this.d = true;
        this.f272a.close();
    }
}
