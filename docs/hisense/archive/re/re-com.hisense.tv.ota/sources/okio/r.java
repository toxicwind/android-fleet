package okio;

import java.io.EOFException;

/* JADX INFO: loaded from: classes.dex */
final class r implements A {
    r() {
    }

    @Override // okio.A
    public D b() {
        return D.d;
    }

    @Override // okio.A, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // okio.A, java.io.Flushable
    public void flush() {
    }

    @Override // okio.A
    public void o(h hVar, long j) throws EOFException {
        hVar.m(j);
    }
}
