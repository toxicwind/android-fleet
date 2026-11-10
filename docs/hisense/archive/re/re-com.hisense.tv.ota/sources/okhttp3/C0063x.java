package okhttp3;

import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;

/* JADX INFO: renamed from: okhttp3.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0063x {
    private static final C0058s[] e = {C0058s.q, C0058s.r, C0058s.s, C0058s.t, C0058s.u, C0058s.k, C0058s.m, C0058s.l, C0058s.n, C0058s.p, C0058s.o};
    private static final C0058s[] f = {C0058s.q, C0058s.r, C0058s.s, C0058s.t, C0058s.u, C0058s.k, C0058s.m, C0058s.l, C0058s.n, C0058s.p, C0058s.o, C0058s.i, C0058s.j, C0058s.g, C0058s.h, C0058s.e, C0058s.f, C0058s.d};
    public static final C0063x g;
    public static final C0063x h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final boolean f258a;
    final boolean b;
    final String[] c;
    final String[] d;

    static {
        C0062w c0062w = new C0062w(true);
        c0062w.c(e);
        c0062w.f(TlsVersion.TLS_1_3, TlsVersion.TLS_1_2);
        c0062w.d(true);
        c0062w.a();
        C0062w c0062w2 = new C0062w(true);
        c0062w2.c(f);
        c0062w2.f(TlsVersion.TLS_1_3, TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0);
        c0062w2.d(true);
        g = c0062w2.a();
        C0062w c0062w3 = new C0062w(true);
        c0062w3.c(f);
        c0062w3.f(TlsVersion.TLS_1_0);
        c0062w3.d(true);
        c0062w3.a();
        h = new C0062w(false).a();
    }

    C0063x(C0062w c0062w) {
        this.f258a = c0062w.f257a;
        this.c = c0062w.b;
        this.d = c0062w.c;
        this.b = c0062w.d;
    }

    private C0063x e(SSLSocket sSLSocket, boolean z) {
        String[] strArrZ = this.c != null ? okhttp3.g0.e.z(C0058s.b, sSLSocket.getEnabledCipherSuites(), this.c) : sSLSocket.getEnabledCipherSuites();
        String[] strArrZ2 = this.d != null ? okhttp3.g0.e.z(okhttp3.g0.e.o, sSLSocket.getEnabledProtocols(), this.d) : sSLSocket.getEnabledProtocols();
        String[] supportedCipherSuites = sSLSocket.getSupportedCipherSuites();
        int iW = okhttp3.g0.e.w(C0058s.b, supportedCipherSuites, "TLS_FALLBACK_SCSV");
        if (z && iW != -1) {
            strArrZ = okhttp3.g0.e.i(strArrZ, supportedCipherSuites[iW]);
        }
        C0062w c0062w = new C0062w(this);
        c0062w.b(strArrZ);
        c0062w.e(strArrZ2);
        return c0062w.a();
    }

    void a(SSLSocket sSLSocket, boolean z) {
        C0063x c0063xE = e(sSLSocket, z);
        String[] strArr = c0063xE.d;
        if (strArr != null) {
            sSLSocket.setEnabledProtocols(strArr);
        }
        String[] strArr2 = c0063xE.c;
        if (strArr2 != null) {
            sSLSocket.setEnabledCipherSuites(strArr2);
        }
    }

    public List b() {
        String[] strArr = this.c;
        if (strArr != null) {
            return C0058s.b(strArr);
        }
        return null;
    }

    public boolean c(SSLSocket sSLSocket) {
        if (!this.f258a) {
            return false;
        }
        String[] strArr = this.d;
        if (strArr != null && !okhttp3.g0.e.B(okhttp3.g0.e.o, strArr, sSLSocket.getEnabledProtocols())) {
            return false;
        }
        String[] strArr2 = this.c;
        return strArr2 == null || okhttp3.g0.e.B(C0058s.b, strArr2, sSLSocket.getEnabledCipherSuites());
    }

    public boolean d() {
        return this.f258a;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C0063x)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        C0063x c0063x = (C0063x) obj;
        boolean z = this.f258a;
        if (z != c0063x.f258a) {
            return false;
        }
        return !z || (Arrays.equals(this.c, c0063x.c) && Arrays.equals(this.d, c0063x.d) && this.b == c0063x.b);
    }

    public boolean f() {
        return this.b;
    }

    public List g() {
        String[] strArr = this.d;
        if (strArr != null) {
            return TlsVersion.b(strArr);
        }
        return null;
    }

    public int hashCode() {
        if (this.f258a) {
            return ((((527 + Arrays.hashCode(this.c)) * 31) + Arrays.hashCode(this.d)) * 31) + (!this.b ? 1 : 0);
        }
        return 17;
    }

    public String toString() {
        if (!this.f258a) {
            return "ConnectionSpec()";
        }
        return "ConnectionSpec(cipherSuites=" + (this.c != null ? b().toString() : "[all enabled]") + ", tlsVersions=" + (this.d != null ? g().toString() : "[all enabled]") + ", supportsTlsExtensions=" + this.b + ")";
    }
}
