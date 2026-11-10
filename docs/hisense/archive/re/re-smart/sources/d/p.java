package d;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes2.dex */
public final class p implements B {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ E f2124a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ OutputStream f2125b;

    public p(E e2, OutputStream outputStream) {
        this.f2124a = e2;
        this.f2125b = outputStream;
    }

    @Override // d.B, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f2125b.close();
    }

    @Override // d.B, java.io.Flushable
    public void flush() throws IOException {
        this.f2125b.flush();
    }

    @Override // d.B
    public E timeout() {
        return this.f2124a;
    }

    public String toString() {
        return b.a.a.a.a.a(b.a.a.a.a.a("sink("), this.f2125b, ")");
    }

    @Override // d.B
    public void write(g gVar, long j) throws IOException {
        F.a(gVar.f2102c, 0L, j);
        while (j > 0) {
            this.f2124a.throwIfReached();
            y yVar = gVar.f2101b;
            int iMin = (int) Math.min(j, yVar.f2140c - yVar.f2139b);
            this.f2125b.write(yVar.f2138a, yVar.f2139b, iMin);
            yVar.f2139b += iMin;
            long j2 = iMin;
            j -= j2;
            gVar.f2102c -= j2;
            if (yVar.f2139b == yVar.f2140c) {
                gVar.f2101b = yVar.a();
                z.a(yVar);
            }
        }
    }
}
