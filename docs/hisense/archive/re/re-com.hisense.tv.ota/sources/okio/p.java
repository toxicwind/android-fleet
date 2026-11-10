package okio;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
final class p implements A {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ D f273a;
    final /* synthetic */ OutputStream b;

    p(D d, OutputStream outputStream) {
        this.f273a = d;
        this.b = outputStream;
    }

    @Override // okio.A
    public D b() {
        return this.f273a;
    }

    @Override // okio.A, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.b.close();
    }

    @Override // okio.A, java.io.Flushable
    public void flush() throws IOException {
        this.b.flush();
    }

    @Override // okio.A
    public void o(h hVar, long j) throws IOException {
        E.b(hVar.b, 0L, j);
        while (j > 0) {
            this.f273a.f();
            y yVar = hVar.f268a;
            int iMin = (int) Math.min(j, yVar.c - yVar.b);
            this.b.write(yVar.f280a, yVar.b, iMin);
            int i = yVar.b + iMin;
            yVar.b = i;
            long j2 = iMin;
            j -= j2;
            hVar.b -= j2;
            if (i == yVar.c) {
                hVar.f268a = yVar.b();
                z.a(yVar);
            }
        }
    }

    public String toString() {
        return "sink(" + this.b + ")";
    }
}
