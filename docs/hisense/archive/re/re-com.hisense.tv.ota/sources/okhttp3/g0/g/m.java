package okhttp3.g0.g;

import java.io.EOFException;
import java.io.IOException;
import okio.A;

/* JADX INFO: loaded from: classes.dex */
class m extends okio.k {
    private boolean b;

    m(A a2) {
        super(a2);
    }

    protected abstract void a(IOException iOException);

    @Override // okio.k, okio.A, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.b) {
            return;
        }
        try {
            super.close();
        } catch (IOException e) {
            this.b = true;
            a(e);
        }
    }

    @Override // okio.k, okio.A, java.io.Flushable
    public void flush() {
        if (this.b) {
            return;
        }
        try {
            super.flush();
        } catch (IOException e) {
            this.b = true;
            a(e);
        }
    }

    @Override // okio.k, okio.A
    public void o(okio.h hVar, long j) throws EOFException {
        if (this.b) {
            hVar.m(j);
            return;
        }
        try {
            super.o(hVar, j);
        } catch (IOException e) {
            this.b = true;
            a(e);
        }
    }
}
