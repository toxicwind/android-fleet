package okhttp3;

import java.io.Closeable;
import java.io.InputStream;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public abstract class e0 implements Closeable {
    public static e0 M(P p, long j, okio.j jVar) {
        if (jVar != null) {
            return new d0(p, j, jVar);
        }
        throw new NullPointerException("source == null");
    }

    public static e0 N(P p, String str) {
        Charset charsetA = okhttp3.g0.e.i;
        if (p != null && (charsetA = p.a()) == null) {
            charsetA = okhttp3.g0.e.i;
            p = P.d(p + "; charset=utf-8");
        }
        okio.h hVar = new okio.h();
        hVar.q0(str, charsetA);
        return M(p, hVar.c0(), hVar);
    }

    public static e0 O(P p, byte[] bArr) {
        okio.h hVar = new okio.h();
        hVar.h0(bArr);
        return M(p, bArr.length, hVar);
    }

    private Charset e() {
        P pB = B();
        return pB != null ? pB.b(okhttp3.g0.e.i) : okhttp3.g0.e.i;
    }

    public abstract P B();

    public abstract okio.j P();

    public final String Q() {
        okio.j jVarP = P();
        try {
            return jVarP.C(okhttp3.g0.e.c(jVarP, e()));
        } finally {
            okhttp3.g0.e.g(jVarP);
        }
    }

    public final InputStream a() {
        return P().D();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        okhttp3.g0.e.g(P());
    }

    public abstract long p();
}
