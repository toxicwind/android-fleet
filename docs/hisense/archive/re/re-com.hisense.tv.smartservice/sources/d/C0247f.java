package d;

import java.io.InputStream;

/* JADX INFO: renamed from: d.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0247f extends InputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ g f2099a;

    public C0247f(g gVar) {
        this.f2099a = gVar;
    }

    @Override // java.io.InputStream
    public int available() {
        return (int) Math.min(this.f2099a.f2102c, 2147483647L);
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // java.io.InputStream
    public int read() {
        g gVar = this.f2099a;
        if (gVar.f2102c > 0) {
            return gVar.readByte() & 255;
        }
        return -1;
    }

    public String toString() {
        return b.a.a.a.a.a(new StringBuilder(), this.f2099a, ".inputStream()");
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        return this.f2099a.a(bArr, i, i2);
    }
}
