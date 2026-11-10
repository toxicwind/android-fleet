package d;

import java.io.OutputStream;

/* JADX INFO: renamed from: d.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0246e extends OutputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ g f2098a;

    public C0246e(g gVar) {
        this.f2098a = gVar;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
    }

    public String toString() {
        return b.a.a.a.a.a(new StringBuilder(), this.f2098a, ".outputStream()");
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        this.f2098a.writeByte((int) ((byte) i));
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        this.f2098a.write(bArr, i, i2);
    }
}
