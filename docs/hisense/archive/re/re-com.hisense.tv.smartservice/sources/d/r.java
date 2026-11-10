package d;

import java.io.EOFException;

/* JADX INFO: loaded from: classes2.dex */
public final class r implements B {
    @Override // d.B, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // d.B, java.io.Flushable
    public void flush() {
    }

    @Override // d.B
    public E timeout() {
        return E.NONE;
    }

    @Override // d.B
    public void write(g gVar, long j) throws EOFException {
        gVar.skip(j);
    }
}
