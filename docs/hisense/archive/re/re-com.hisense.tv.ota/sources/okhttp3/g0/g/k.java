package okhttp3.g0.g;

import java.io.Closeable;
import okio.B;

/* JADX INFO: loaded from: classes.dex */
public final class k implements Closeable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f193a;
    private final long b;
    private final B[] c;
    final /* synthetic */ l d;

    k(l lVar, String str, long j, B[] bArr, long[] jArr) {
        this.d = lVar;
        this.f193a = str;
        this.b = j;
        this.c = bArr;
    }

    public i a() {
        return this.d.N(this.f193a, this.b);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        for (B b : this.c) {
            okhttp3.g0.e.g(b);
        }
    }

    public B e(int i) {
        return this.c[i];
    }
}
