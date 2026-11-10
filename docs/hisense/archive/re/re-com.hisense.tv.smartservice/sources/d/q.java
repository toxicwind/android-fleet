package d;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public final class q implements C {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ E f2126a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ InputStream f2127b;

    public q(E e2, InputStream inputStream) {
        this.f2126a = e2;
        this.f2127b = inputStream;
    }

    @Override // d.C, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f2127b.close();
    }

    @Override // d.C
    public long read(g gVar, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException(b.a.a.a.a.a("byteCount < 0: ", j));
        }
        if (j == 0) {
            return 0L;
        }
        try {
            this.f2126a.throwIfReached();
            y yVarA = gVar.a(1);
            int i = this.f2127b.read(yVarA.f2138a, yVarA.f2140c, (int) Math.min(j, 8192 - yVarA.f2140c));
            if (i != -1) {
                yVarA.f2140c += i;
                long j2 = i;
                gVar.f2102c += j2;
                return j2;
            }
            if (yVarA.f2139b != yVarA.f2140c) {
                return -1L;
            }
            gVar.f2101b = yVarA.a();
            z.a(yVarA);
            return -1L;
        } catch (AssertionError e2) {
            if (t.a(e2)) {
                throw new IOException(e2);
            }
            throw e2;
        }
    }

    @Override // d.C
    public E timeout() {
        return this.f2126a;
    }

    public String toString() {
        return b.a.a.a.a.a(b.a.a.a.a.a("source("), this.f2127b, ")");
    }
}
