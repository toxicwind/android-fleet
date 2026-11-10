package d;

/* JADX INFO: loaded from: classes2.dex */
public abstract class k implements B {
    public final B delegate;

    public k(B b2) {
        if (b2 == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.delegate = b2;
    }

    @Override // d.B, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.delegate.close();
    }

    public final B delegate() {
        return this.delegate;
    }

    @Override // d.B, java.io.Flushable
    public void flush() {
        this.delegate.flush();
    }

    @Override // d.B
    public E timeout() {
        return this.delegate.timeout();
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.delegate.toString() + ")";
    }

    @Override // d.B
    public void write(g gVar, long j) {
        this.delegate.write(gVar, j);
    }
}
