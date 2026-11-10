package okio;

import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
class g extends InputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ h f267a;

    g(h hVar) {
        this.f267a = hVar;
    }

    @Override // java.io.InputStream
    public int available() {
        return (int) Math.min(this.f267a.b, 2147483647L);
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // java.io.InputStream
    public int read() {
        h hVar = this.f267a;
        if (hVar.b > 0) {
            return hVar.H() & 255;
        }
        return -1;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        return this.f267a.U(bArr, i, i2);
    }

    public String toString() {
        return this.f267a + ".inputStream()";
    }
}
