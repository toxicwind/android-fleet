package okio;

import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
class f extends OutputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ h f266a;

    f(h hVar) {
        this.f266a = hVar;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
    }

    public String toString() {
        return this.f266a + ".outputStream()";
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        this.f266a.k0((byte) i);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        this.f266a.i0(bArr, i, i2);
    }
}
