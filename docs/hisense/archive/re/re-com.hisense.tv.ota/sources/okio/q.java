package okio;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
final class q implements B {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ D f274a;
    final /* synthetic */ InputStream b;

    q(D d, InputStream inputStream) {
        this.f274a = d;
        this.b = inputStream;
    }

    @Override // okio.B
    public long K(h hVar, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (j == 0) {
            return 0L;
        }
        try {
            this.f274a.f();
            y yVarF0 = hVar.f0(1);
            int i = this.b.read(yVarF0.f280a, yVarF0.c, (int) Math.min(j, 8192 - yVarF0.c));
            if (i == -1) {
                return -1L;
            }
            yVarF0.c += i;
            long j2 = i;
            hVar.b += j2;
            return j2;
        } catch (AssertionError e) {
            if (t.e(e)) {
                throw new IOException(e);
            }
            throw e;
        }
    }

    @Override // okio.B
    public D b() {
        return this.f274a;
    }

    @Override // okio.B, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.b.close();
    }

    public String toString() {
        return "source(" + this.b + ")";
    }
}
