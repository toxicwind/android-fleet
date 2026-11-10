package d;

/* JADX INFO: loaded from: classes2.dex */
public abstract class l implements C {
    public final C delegate;

    public l(C c2) {
        if (c2 == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.delegate = c2;
    }

    @Override // d.C, java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    public final C delegate() {
        return this.delegate;
    }

    @Override // d.C
    public long read(g gVar, long j) {
        return this.delegate.read(gVar, j);
    }

    @Override // d.C
    public E timeout() {
        return this.delegate.timeout();
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.delegate.toString() + ")";
    }
}
