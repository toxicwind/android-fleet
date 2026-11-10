package okio;

import java.io.IOException;

/* JADX INFO: renamed from: okio.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class C0066a implements A {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ A f263a;
    final /* synthetic */ C0069d b;

    C0066a(C0069d c0069d, A a2) {
        this.b = c0069d;
        this.f263a = a2;
    }

    @Override // okio.A
    public D b() {
        return this.b;
    }

    @Override // okio.A, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.b.k();
        try {
            try {
                this.f263a.close();
                this.b.m(true);
            } catch (IOException e) {
                throw this.b.l(e);
            }
        } catch (Throwable th) {
            this.b.m(false);
            throw th;
        }
    }

    @Override // okio.A, java.io.Flushable
    public void flush() {
        this.b.k();
        try {
            try {
                this.f263a.flush();
                this.b.m(true);
            } catch (IOException e) {
                throw this.b.l(e);
            }
        } catch (Throwable th) {
            this.b.m(false);
            throw th;
        }
    }

    @Override // okio.A
    public void o(h hVar, long j) {
        E.b(hVar.b, 0L, j);
        while (true) {
            long j2 = 0;
            if (j <= 0) {
                return;
            }
            y yVar = hVar.f268a;
            while (j2 < 65536) {
                j2 += (long) (yVar.c - yVar.b);
                if (j2 >= j) {
                    j2 = j;
                    break;
                }
                yVar = yVar.f;
            }
            this.b.k();
            try {
                try {
                    this.f263a.o(hVar, j2);
                    j -= j2;
                    this.b.m(true);
                } catch (IOException e) {
                    throw this.b.l(e);
                }
            } catch (Throwable th) {
                this.b.m(false);
                throw th;
            }
        }
    }

    public String toString() {
        return "AsyncTimeout.sink(" + this.f263a + ")";
    }
}
