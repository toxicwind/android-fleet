package d;

import java.io.IOException;

/* JADX INFO: renamed from: d.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0242a implements B {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ B f2093a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C0244c f2094b;

    public C0242a(C0244c c0244c, B b2) {
        this.f2094b = c0244c;
        this.f2093a = b2;
    }

    @Override // d.B, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f2094b.enter();
        try {
            try {
                this.f2093a.close();
                this.f2094b.exit(true);
            } catch (IOException e2) {
                throw this.f2094b.exit(e2);
            }
        } catch (Throwable th) {
            this.f2094b.exit(false);
            throw th;
        }
    }

    @Override // d.B, java.io.Flushable
    public void flush() throws IOException {
        this.f2094b.enter();
        try {
            try {
                this.f2093a.flush();
                this.f2094b.exit(true);
            } catch (IOException e2) {
                throw this.f2094b.exit(e2);
            }
        } catch (Throwable th) {
            this.f2094b.exit(false);
            throw th;
        }
    }

    @Override // d.B
    public E timeout() {
        return this.f2094b;
    }

    public String toString() {
        return b.a.a.a.a.a(b.a.a.a.a.a("AsyncTimeout.sink("), this.f2093a, ")");
    }

    @Override // d.B
    public void write(g gVar, long j) throws IOException {
        F.a(gVar.f2102c, 0L, j);
        while (true) {
            long j2 = 0;
            if (j <= 0) {
                return;
            }
            y yVar = gVar.f2101b;
            while (j2 < 65536) {
                j2 += (long) (yVar.f2140c - yVar.f2139b);
                if (j2 >= j) {
                    j2 = j;
                    break;
                }
                yVar = yVar.f2143f;
            }
            this.f2094b.enter();
            try {
                try {
                    this.f2093a.write(gVar, j2);
                    j -= j2;
                    this.f2094b.exit(true);
                } catch (IOException e2) {
                    throw this.f2094b.exit(e2);
                }
            } catch (Throwable th) {
                this.f2094b.exit(false);
                throw th;
            }
        }
    }
}
