package d;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public class w extends InputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ x f2134a;

    public w(x xVar) {
        this.f2134a = xVar;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        x xVar = this.f2134a;
        if (xVar.f2137c) {
            throw new IOException("closed");
        }
        return (int) Math.min(xVar.f2135a.f2102c, 2147483647L);
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        x xVar = this.f2134a;
        if (xVar.f2137c) {
            return;
        }
        xVar.f2137c = true;
        xVar.f2136b.close();
        xVar.f2135a.clear();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        x xVar = this.f2134a;
        if (xVar.f2137c) {
            throw new IOException("closed");
        }
        g gVar = xVar.f2135a;
        if (gVar.f2102c == 0 && xVar.f2136b.read(gVar, 8192L) == -1) {
            return -1;
        }
        return this.f2134a.f2135a.readByte() & 255;
    }

    public String toString() {
        return b.a.a.a.a.a(new StringBuilder(), this.f2134a, ".inputStream()");
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (!this.f2134a.f2137c) {
            F.a(bArr.length, i, i2);
            x xVar = this.f2134a;
            g gVar = xVar.f2135a;
            if (gVar.f2102c == 0 && xVar.f2136b.read(gVar, 8192L) == -1) {
                return -1;
            }
            return this.f2134a.f2135a.a(bArr, i, i2);
        }
        throw new IOException("closed");
    }
}
