package d;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes2.dex */
public class u extends OutputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ v f2130a;

    public u(v vVar) {
        this.f2130a = vVar;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws Throwable {
        v vVar = this.f2130a;
        if (vVar.f2133c) {
            return;
        }
        g gVar = vVar.f2131a;
        if (gVar.f2102c > 0) {
            vVar.f2132b.write(gVar, gVar.f2102c);
        }
        th = null;
        try {
            vVar.f2132b.close();
        } catch (Throwable th) {
            if (th == null) {
                th = th;
            }
        }
        vVar.f2133c = true;
        if (th == null) {
            return;
        }
        F.a(th);
        throw null;
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
        v vVar = this.f2130a;
        boolean z = vVar.f2133c;
        if (z) {
            return;
        }
        if (z) {
            throw new IllegalStateException("closed");
        }
        g gVar = vVar.f2131a;
        long j = gVar.f2102c;
        if (j > 0) {
            vVar.f2132b.write(gVar, j);
        }
        vVar.f2132b.flush();
    }

    public String toString() {
        return b.a.a.a.a.a(new StringBuilder(), this.f2130a, ".outputStream()");
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        v vVar = this.f2130a;
        if (vVar.f2133c) {
            throw new IOException("closed");
        }
        vVar.f2131a.writeByte((int) ((byte) i));
        this.f2130a.k();
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        v vVar = this.f2130a;
        if (!vVar.f2133c) {
            vVar.f2131a.write(bArr, i, i2);
            this.f2130a.k();
            return;
        }
        throw new IOException("closed");
    }
}
