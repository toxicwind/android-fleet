package okio;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
class w extends InputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ x f278a;

    w(x xVar) {
        this.f278a = xVar;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        x xVar = this.f278a;
        if (xVar.c) {
            throw new IOException("closed");
        }
        return (int) Math.min(xVar.f279a.b, 2147483647L);
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f278a.close();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        x xVar = this.f278a;
        if (xVar.c) {
            throw new IOException("closed");
        }
        h hVar = xVar.f279a;
        if (hVar.b == 0 && xVar.b.K(hVar, 8192L) == -1) {
            return -1;
        }
        return this.f278a.f279a.H() & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.f278a.c) {
            throw new IOException("closed");
        }
        E.b(bArr.length, i, i2);
        x xVar = this.f278a;
        h hVar = xVar.f279a;
        if (hVar.b == 0 && xVar.b.K(hVar, 8192L) == -1) {
            return -1;
        }
        return this.f278a.f279a.U(bArr, i, i2);
    }

    public String toString() {
        return this.f278a + ".inputStream()";
    }
}
