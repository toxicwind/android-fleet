package okio;

/* JADX INFO: loaded from: classes.dex */
public abstract class l implements B {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final B f270a;

    public l(B b) {
        if (b == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f270a = b;
    }

    @Override // okio.B
    public long K(h hVar, long j) {
        return this.f270a.K(hVar, j);
    }

    public final B a() {
        return this.f270a;
    }

    @Override // okio.B
    public D b() {
        return this.f270a.b();
    }

    @Override // okio.B, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f270a.close();
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.f270a.toString() + ")";
    }
}
