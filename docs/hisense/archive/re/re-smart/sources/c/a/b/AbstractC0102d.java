package c.a.b;

/* JADX INFO: renamed from: c.a.b.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0102d implements Tc {
    public final void b(int i) {
        if (m() < i) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override // c.a.b.Tc, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // c.a.b.Tc
    public boolean markSupported() {
        return false;
    }

    @Override // c.a.b.Tc
    public void o() {
    }

    @Override // c.a.b.Tc
    public void reset() {
        throw new UnsupportedOperationException();
    }
}
