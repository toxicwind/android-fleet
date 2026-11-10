package okio;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
class u extends OutputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ v f276a;

    u(v vVar) {
        this.f276a = vVar;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws Throwable {
        this.f276a.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
        v vVar = this.f276a;
        if (vVar.c) {
            return;
        }
        vVar.flush();
    }

    public String toString() {
        return this.f276a + ".outputStream()";
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        v vVar = this.f276a;
        if (vVar.c) {
            throw new IOException("closed");
        }
        vVar.f277a.k0((byte) i);
        this.f276a.F();
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        v vVar = this.f276a;
        if (vVar.c) {
            throw new IOException("closed");
        }
        vVar.f277a.i0(bArr, i, i2);
        this.f276a.F();
    }
}
