package okhttp3;

import java.net.Proxy;
import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: classes.dex */
public final class S {
    int A;
    int B;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    B f167a;
    Proxy b;
    List c;
    List d;
    final List e;
    final List f;
    G g;
    ProxySelector h;
    A i;
    C0050j j;
    okhttp3.g0.g.n k;
    SocketFactory l;
    SSLSocketFactory m;
    okhttp3.g0.n.c n;
    HostnameVerifier o;
    C0057q p;
    InterfaceC0031c q;
    InterfaceC0031c r;
    C0061v s;
    D t;
    boolean u;
    boolean v;
    boolean w;
    int x;
    int y;
    int z;

    public S() {
        this.e = new ArrayList();
        this.f = new ArrayList();
        this.f167a = new B();
        this.c = T.C;
        this.d = T.D;
        this.g = H.k(H.f159a);
        ProxySelector proxySelector = ProxySelector.getDefault();
        this.h = proxySelector;
        if (proxySelector == null) {
            this.h = new okhttp3.g0.l.a();
        }
        this.i = A.f155a;
        this.l = SocketFactory.getDefault();
        this.o = okhttp3.g0.n.d.f216a;
        this.p = C0057q.c;
        InterfaceC0031c interfaceC0031c = InterfaceC0031c.f176a;
        this.q = interfaceC0031c;
        this.r = interfaceC0031c;
        this.s = new C0061v();
        this.t = D.f157a;
        this.u = true;
        this.v = true;
        this.w = true;
        this.x = 0;
        this.y = 10000;
        this.z = 10000;
        this.A = 10000;
        this.B = 0;
    }

    S(T t) {
        this.e = new ArrayList();
        this.f = new ArrayList();
        this.f167a = t.f168a;
        this.b = t.b;
        this.c = t.c;
        this.d = t.d;
        this.e.addAll(t.e);
        this.f.addAll(t.f);
        this.g = t.g;
        this.h = t.h;
        this.i = t.i;
        this.k = t.k;
        this.j = t.j;
        this.l = t.l;
        this.m = t.m;
        this.n = t.n;
        this.o = t.o;
        this.p = t.p;
        this.q = t.q;
        this.r = t.r;
        this.s = t.s;
        this.t = t.t;
        this.u = t.u;
        this.v = t.v;
        this.w = t.w;
        this.x = t.x;
        this.y = t.y;
        this.z = t.z;
        this.A = t.A;
        this.B = t.B;
    }

    public S a(O o) {
        if (o == null) {
            throw new IllegalArgumentException("interceptor == null");
        }
        this.f.add(o);
        return this;
    }

    public T b() {
        return new T(this);
    }

    public S c(C0050j c0050j) {
        this.j = c0050j;
        this.k = null;
        return this;
    }

    public S d(long j, TimeUnit timeUnit) {
        this.y = okhttp3.g0.e.e("timeout", j, timeUnit);
        return this;
    }

    public S e(D d) {
        if (d == null) {
            throw new NullPointerException("dns == null");
        }
        this.t = d;
        return this;
    }

    public S f(HostnameVerifier hostnameVerifier) {
        if (hostnameVerifier == null) {
            throw new NullPointerException("hostnameVerifier == null");
        }
        this.o = hostnameVerifier;
        return this;
    }

    public List g() {
        return this.e;
    }

    public List h() {
        return this.f;
    }

    public S i(long j, TimeUnit timeUnit) {
        this.z = okhttp3.g0.e.e("timeout", j, timeUnit);
        return this;
    }

    public S j(SSLSocketFactory sSLSocketFactory, X509TrustManager x509TrustManager) {
        if (sSLSocketFactory == null) {
            throw new NullPointerException("sslSocketFactory == null");
        }
        if (x509TrustManager == null) {
            throw new NullPointerException("trustManager == null");
        }
        this.m = sSLSocketFactory;
        this.n = okhttp3.g0.n.c.b(x509TrustManager);
        return this;
    }
}
