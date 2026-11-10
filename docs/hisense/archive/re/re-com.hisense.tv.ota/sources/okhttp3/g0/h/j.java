package okhttp3.g0.h;

import okhttp3.P;
import okhttp3.e0;

/* JADX INFO: loaded from: classes.dex */
public final class j extends e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f199a;
    private final long b;
    private final okio.j c;

    public j(String str, long j, okio.j jVar) {
        this.f199a = str;
        this.b = j;
        this.c = jVar;
    }

    @Override // okhttp3.e0
    public P B() {
        String str = this.f199a;
        if (str != null) {
            return P.d(str);
        }
        return null;
    }

    @Override // okhttp3.e0
    public okio.j P() {
        return this.c;
    }

    @Override // okhttp3.e0
    public long p() {
        return this.b;
    }
}
