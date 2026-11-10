package okio;

/* JADX INFO: loaded from: classes.dex */
public abstract class k implements A {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final A f269a;

    public k(A a2) {
        if (a2 == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f269a = a2;
    }

    @Override // okio.A
    public D b() {
        return this.f269a.b();
    }

    @Override // okio.A, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f269a.close();
    }

    @Override // okio.A, java.io.Flushable
    public void flush() {
        this.f269a.flush();
    }

    @Override // okio.A
    public void o(h hVar, long j) {
        this.f269a.o(hVar, j);
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.f269a.toString() + ")";
    }
}
