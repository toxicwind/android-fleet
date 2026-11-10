package okhttp3;

import java.net.Proxy;
import java.net.ProxySelector;
import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: classes.dex */
public class T implements Cloneable {
    static final List C = okhttp3.g0.e.u(Protocol.HTTP_2, Protocol.HTTP_1_1);
    static final List D = okhttp3.g0.e.u(C0063x.g, C0063x.h);
    final int A;
    final int B;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final B f168a;
    final Proxy b;
    final List c;
    final List d;
    final List e;
    final List f;
    final G g;
    final ProxySelector h;
    final A i;
    final C0050j j;
    final okhttp3.g0.g.n k;
    final SocketFactory l;
    final SSLSocketFactory m;
    final okhttp3.g0.n.c n;
    final HostnameVerifier o;
    final C0057q p;
    final InterfaceC0031c q;
    final InterfaceC0031c r;
    final C0061v s;
    final D t;
    final boolean u;
    final boolean v;
    final boolean w;
    final int x;
    final int y;
    final int z;

    static {
        okhttp3.g0.a.f182a = new Q();
    }

    public T() {
        this(new S());
    }

    T(S s) {
        boolean z;
        okhttp3.g0.n.c cVarB;
        this.f168a = s.f167a;
        this.b = s.b;
        this.c = s.c;
        this.d = s.d;
        this.e = okhttp3.g0.e.t(s.e);
        this.f = okhttp3.g0.e.t(s.f);
        this.g = s.g;
        this.h = s.h;
        this.i = s.i;
        this.j = s.j;
        this.k = s.k;
        this.l = s.l;
        Iterator it = this.d.iterator();
        loop0: while (true) {
            z = false;
            while (true) {
                if (!it.hasNext()) {
                    break loop0;
                } else {
                    z = (z || ((C0063x) it.next()).d()) ? true : z;
                }
            }
        }
        if (s.m == null && z) {
            X509TrustManager x509TrustManagerC = okhttp3.g0.e.C();
            this.m = v(x509TrustManagerC);
            cVarB = okhttp3.g0.n.c.b(x509TrustManagerC);
        } else {
            this.m = s.m;
            cVarB = s.n;
        }
        this.n = cVarB;
        if (this.m != null) {
            okhttp3.g0.k.k.l().f(this.m);
        }
        this.o = s.o;
        this.p = s.p.f(this.n);
        this.q = s.q;
        this.r = s.r;
        this.s = s.s;
        this.t = s.t;
        this.u = s.u;
        this.v = s.v;
        this.w = s.w;
        this.x = s.x;
        this.y = s.y;
        this.z = s.z;
        this.A = s.A;
        this.B = s.B;
        if (this.e.contains(null)) {
            throw new IllegalStateException("Null interceptor: " + this.e);
        }
        if (this.f.contains(null)) {
            throw new IllegalStateException("Null network interceptor: " + this.f);
        }
    }

    private static SSLSocketFactory v(X509TrustManager x509TrustManager) {
        try {
            SSLContext sSLContextN = okhttp3.g0.k.k.l().n();
            sSLContextN.init(null, new TrustManager[]{x509TrustManager}, null);
            return sSLContextN.getSocketFactory();
        } catch (GeneralSecurityException e) {
            throw okhttp3.g0.e.b("No System TLS", e);
        }
    }

    public ProxySelector A() {
        return this.h;
    }

    public int C() {
        return this.z;
    }

    public boolean D() {
        return this.w;
    }

    public SocketFactory E() {
        return this.l;
    }

    public SSLSocketFactory F() {
        return this.m;
    }

    public int G() {
        return this.A;
    }

    public InterfaceC0031c b() {
        return this.r;
    }

    public int c() {
        return this.x;
    }

    public C0057q d() {
        return this.p;
    }

    public int f() {
        return this.y;
    }

    public C0061v g() {
        return this.s;
    }

    public List h() {
        return this.d;
    }

    public A i() {
        return this.i;
    }

    public B j() {
        return this.f168a;
    }

    public D k() {
        return this.t;
    }

    public G l() {
        return this.g;
    }

    public boolean m() {
        return this.v;
    }

    public boolean n() {
        return this.u;
    }

    public HostnameVerifier o() {
        return this.o;
    }

    public List q() {
        return this.e;
    }

    okhttp3.g0.g.n r() {
        C0050j c0050j = this.j;
        return c0050j != null ? c0050j.f246a : this.k;
    }

    public List s() {
        return this.f;
    }

    public S t() {
        return new S(this);
    }

    public InterfaceC0053m u(Y y) {
        return W.h(this, y, false);
    }

    public int w() {
        return this.B;
    }

    public List x() {
        return this.c;
    }

    public Proxy y() {
        return this.b;
    }

    public InterfaceC0031c z() {
        return this.q;
    }
}
