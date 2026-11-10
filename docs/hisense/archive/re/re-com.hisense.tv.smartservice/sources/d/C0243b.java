package d;

import java.io.IOException;

/* JADX INFO: renamed from: d.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0243b implements C {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C f2095a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C0244c f2096b;

    public C0243b(C0244c c0244c, C c2) {
        this.f2096b = c0244c;
        this.f2095a = c2;
    }

    @Override // d.C, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f2096b.enter();
        try {
            try {
                this.f2095a.close();
                this.f2096b.exit(true);
            } catch (IOException e2) {
                throw this.f2096b.exit(e2);
            }
        } catch (Throwable th) {
            this.f2096b.exit(false);
            throw th;
        }
    }

    @Override // d.C
    public long read(g gVar, long j) throws IOException {
        this.f2096b.enter();
        try {
            try {
                long j2 = this.f2095a.read(gVar, j);
                this.f2096b.exit(true);
                return j2;
            } catch (IOException e2) {
                throw this.f2096b.exit(e2);
            }
        } catch (Throwable th) {
            this.f2096b.exit(false);
            throw th;
        }
    }

    @Override // d.C
    public E timeout() {
        return this.f2096b;
    }

    public String toString() {
        return b.a.a.a.a.a(b.a.a.a.a.a("AsyncTimeout.source("), this.f2095a, ")");
    }
}
