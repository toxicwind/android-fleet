package okhttp3.internal.cache;

import d.B;
import d.g;
import d.k;
import java.io.EOFException;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class FaultHidingSink extends k {
    public boolean hasErrors;

    public FaultHidingSink(B b2) {
        super(b2);
    }

    @Override // d.k, d.B, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.hasErrors) {
            return;
        }
        try {
            this.delegate.close();
        } catch (IOException e2) {
            this.hasErrors = true;
            onException(e2);
        }
    }

    @Override // d.k, d.B, java.io.Flushable
    public void flush() {
        if (this.hasErrors) {
            return;
        }
        try {
            this.delegate.flush();
        } catch (IOException e2) {
            this.hasErrors = true;
            onException(e2);
        }
    }

    public void onException(IOException iOException) {
    }

    @Override // d.k, d.B
    public void write(g gVar, long j) throws EOFException {
        if (this.hasErrors) {
            gVar.skip(j);
            return;
        }
        try {
            this.delegate.write(gVar, j);
        } catch (IOException e2) {
            this.hasErrors = true;
            onException(e2);
        }
    }
}
