package okhttp3;

import java.net.InetSocketAddress;
import java.net.Proxy;

/* JADX INFO: loaded from: classes.dex */
public final class f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final C0029a f181a;
    final Proxy b;
    final InetSocketAddress c;

    public f0(C0029a c0029a, Proxy proxy, InetSocketAddress inetSocketAddress) {
        if (c0029a == null) {
            throw new NullPointerException("address == null");
        }
        if (proxy == null) {
            throw new NullPointerException("proxy == null");
        }
        if (inetSocketAddress == null) {
            throw new NullPointerException("inetSocketAddress == null");
        }
        this.f181a = c0029a;
        this.b = proxy;
        this.c = inetSocketAddress;
    }

    public C0029a a() {
        return this.f181a;
    }

    public Proxy b() {
        return this.b;
    }

    public boolean c() {
        return this.f181a.i != null && this.b.type() == Proxy.Type.HTTP;
    }

    public InetSocketAddress d() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (obj instanceof f0) {
            f0 f0Var = (f0) obj;
            if (f0Var.f181a.equals(this.f181a) && f0Var.b.equals(this.b) && f0Var.c.equals(this.c)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((527 + this.f181a.hashCode()) * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
    }

    public String toString() {
        return "Route{" + this.c + "}";
    }
}
