package okhttp3;

import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: renamed from: okhttp3.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0029a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final M f174a;
    final D b;
    final SocketFactory c;
    final InterfaceC0031c d;
    final List e;
    final List f;
    final ProxySelector g;
    final Proxy h;
    final SSLSocketFactory i;
    final HostnameVerifier j;
    final C0057q k;

    public C0029a(String str, int i, D d, SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, C0057q c0057q, InterfaceC0031c interfaceC0031c, Proxy proxy, List list, List list2, ProxySelector proxySelector) {
        L l = new L();
        l.q(sSLSocketFactory != null ? "https" : "http");
        l.e(str);
        l.l(i);
        this.f174a = l.a();
        if (d == null) {
            throw new NullPointerException("dns == null");
        }
        this.b = d;
        if (socketFactory == null) {
            throw new NullPointerException("socketFactory == null");
        }
        this.c = socketFactory;
        if (interfaceC0031c == null) {
            throw new NullPointerException("proxyAuthenticator == null");
        }
        this.d = interfaceC0031c;
        if (list == null) {
            throw new NullPointerException("protocols == null");
        }
        this.e = okhttp3.g0.e.t(list);
        if (list2 == null) {
            throw new NullPointerException("connectionSpecs == null");
        }
        this.f = okhttp3.g0.e.t(list2);
        if (proxySelector == null) {
            throw new NullPointerException("proxySelector == null");
        }
        this.g = proxySelector;
        this.h = proxy;
        this.i = sSLSocketFactory;
        this.j = hostnameVerifier;
        this.k = c0057q;
    }

    public C0057q a() {
        return this.k;
    }

    public List b() {
        return this.f;
    }

    public D c() {
        return this.b;
    }

    boolean d(C0029a c0029a) {
        return this.b.equals(c0029a.b) && this.d.equals(c0029a.d) && this.e.equals(c0029a.e) && this.f.equals(c0029a.f) && this.g.equals(c0029a.g) && okhttp3.g0.e.q(this.h, c0029a.h) && okhttp3.g0.e.q(this.i, c0029a.i) && okhttp3.g0.e.q(this.j, c0029a.j) && okhttp3.g0.e.q(this.k, c0029a.k) && l().w() == c0029a.l().w();
    }

    public HostnameVerifier e() {
        return this.j;
    }

    public boolean equals(Object obj) {
        if (obj instanceof C0029a) {
            C0029a c0029a = (C0029a) obj;
            if (this.f174a.equals(c0029a.f174a) && d(c0029a)) {
                return true;
            }
        }
        return false;
    }

    public List f() {
        return this.e;
    }

    public Proxy g() {
        return this.h;
    }

    public InterfaceC0031c h() {
        return this.d;
    }

    public int hashCode() {
        int iHashCode = (((((((((((527 + this.f174a.hashCode()) * 31) + this.b.hashCode()) * 31) + this.d.hashCode()) * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + this.g.hashCode()) * 31;
        Proxy proxy = this.h;
        int iHashCode2 = (iHashCode + (proxy != null ? proxy.hashCode() : 0)) * 31;
        SSLSocketFactory sSLSocketFactory = this.i;
        int iHashCode3 = (iHashCode2 + (sSLSocketFactory != null ? sSLSocketFactory.hashCode() : 0)) * 31;
        HostnameVerifier hostnameVerifier = this.j;
        int iHashCode4 = (iHashCode3 + (hostnameVerifier != null ? hostnameVerifier.hashCode() : 0)) * 31;
        C0057q c0057q = this.k;
        return iHashCode4 + (c0057q != null ? c0057q.hashCode() : 0);
    }

    public ProxySelector i() {
        return this.g;
    }

    public SocketFactory j() {
        return this.c;
    }

    public SSLSocketFactory k() {
        return this.i;
    }

    public M l() {
        return this.f174a;
    }

    public String toString() {
        Object obj;
        StringBuilder sb = new StringBuilder();
        sb.append("Address{");
        sb.append(this.f174a.l());
        sb.append(":");
        sb.append(this.f174a.w());
        if (this.h != null) {
            sb.append(", proxy=");
            obj = this.h;
        } else {
            sb.append(", proxySelector=");
            obj = this.g;
        }
        sb.append(obj);
        sb.append("}");
        return sb.toString();
    }
}
