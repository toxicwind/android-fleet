package okhttp3.internal.connection;

import java.io.IOException;
import java.net.ConnectException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownServiceException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import okhttp3.C0029a;
import okhttp3.C0057q;
import okhttp3.C0061v;
import okhttp3.C0063x;
import okhttp3.H;
import okhttp3.I;
import okhttp3.InterfaceC0053m;
import okhttp3.InterfaceC0059t;
import okhttp3.M;
import okhttp3.N;
import okhttp3.Protocol;
import okhttp3.T;
import okhttp3.X;
import okhttp3.Y;
import okhttp3.b0;
import okhttp3.c0;
import okhttp3.f0;
import okhttp3.g0.k.k;
import okhttp3.internal.http2.C0045h;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.F;
import okhttp3.internal.http2.p;
import okhttp3.internal.http2.s;
import okhttp3.internal.http2.y;
import okio.B;
import okio.i;
import okio.j;
import okio.t;

/* JADX INFO: loaded from: classes.dex */
public final class c extends s implements InterfaceC0059t {
    private final C0061v b;
    private final f0 c;
    private Socket d;
    private Socket e;
    private I f;
    private Protocol g;
    private y h;
    private j i;
    private i j;
    public boolean k;
    public int l;
    public int m = 1;
    public final List n = new ArrayList();
    public long o = Long.MAX_VALUE;

    public c(C0061v c0061v, f0 f0Var) {
        this.b = c0061v;
        this.c = f0Var;
    }

    private void g(int i, int i2, InterfaceC0053m interfaceC0053m, H h) throws IOException {
        Proxy proxyB = this.c.b();
        this.d = (proxyB.type() == Proxy.Type.DIRECT || proxyB.type() == Proxy.Type.HTTP) ? this.c.a().j().createSocket() : new Socket(proxyB);
        h.f(interfaceC0053m, this.c.d(), proxyB);
        this.d.setSoTimeout(i2);
        try {
            k.l().h(this.d, this.c.d(), i);
            try {
                this.i = t.d(t.m(this.d));
                this.j = t.c(t.i(this.d));
            } catch (NullPointerException e) {
                if ("throw with null exception".equals(e.getMessage())) {
                    throw new IOException(e);
                }
            }
        } catch (ConnectException e2) {
            ConnectException connectException = new ConnectException("Failed to connect to " + this.c.d());
            connectException.initCause(e2);
            throw connectException;
        }
    }

    private void h(b bVar) throws Throwable {
        C0029a c0029aA = this.c.a();
        SSLSocket sSLSocket = null;
        try {
            try {
                SSLSocket sSLSocket2 = (SSLSocket) c0029aA.k().createSocket(this.d, c0029aA.l().l(), c0029aA.l().w(), true);
                try {
                    C0063x c0063xA = bVar.a(sSLSocket2);
                    if (c0063xA.f()) {
                        k.l().g(sSLSocket2, c0029aA.l().l(), c0029aA.f());
                    }
                    sSLSocket2.startHandshake();
                    SSLSession session = sSLSocket2.getSession();
                    I iB = I.b(session);
                    if (c0029aA.e().verify(c0029aA.l().l(), session)) {
                        c0029aA.a().a(c0029aA.l().l(), iB.e());
                        String strO = c0063xA.f() ? k.l().o(sSLSocket2) : null;
                        this.e = sSLSocket2;
                        this.i = t.d(t.m(sSLSocket2));
                        this.j = t.c(t.i(this.e));
                        this.f = iB;
                        this.g = strO != null ? Protocol.a(strO) : Protocol.HTTP_1_1;
                        if (sSLSocket2 != null) {
                            k.l().a(sSLSocket2);
                            return;
                        }
                        return;
                    }
                    List listE = iB.e();
                    if (listE.isEmpty()) {
                        throw new SSLPeerUnverifiedException("Hostname " + c0029aA.l().l() + " not verified (no certificates)");
                    }
                    X509Certificate x509Certificate = (X509Certificate) listE.get(0);
                    throw new SSLPeerUnverifiedException("Hostname " + c0029aA.l().l() + " not verified:\n    certificate: " + C0057q.c(x509Certificate) + "\n    DN: " + x509Certificate.getSubjectDN().getName() + "\n    subjectAltNames: " + okhttp3.g0.n.d.a(x509Certificate));
                } catch (AssertionError e) {
                    e = e;
                    if (!okhttp3.g0.e.A(e)) {
                        throw e;
                    }
                    throw new IOException(e);
                } catch (Throwable th) {
                    th = th;
                    sSLSocket = sSLSocket2;
                    if (sSLSocket != null) {
                        k.l().a(sSLSocket);
                    }
                    okhttp3.g0.e.h(sSLSocket);
                    throw th;
                }
            } catch (AssertionError e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private void i(int i, int i2, int i3, InterfaceC0053m interfaceC0053m, H h) throws IOException {
        Y yK = k();
        M mK = yK.k();
        for (int i4 = 0; i4 < 21; i4++) {
            g(i, i2, interfaceC0053m, h);
            yK = j(i2, i3, yK, mK);
            if (yK == null) {
                return;
            }
            okhttp3.g0.e.h(this.d);
            this.d = null;
            this.j = null;
            this.i = null;
            h.d(interfaceC0053m, this.c.d(), this.c.b(), null);
        }
    }

    private Y j(int i, int i2, Y y, M m) throws IOException {
        String str = "CONNECT " + okhttp3.g0.e.s(m, true) + " HTTP/1.1";
        while (true) {
            okhttp3.g0.i.h hVar = new okhttp3.g0.i.h(null, null, this.i, this.j);
            this.i.b().g(i, TimeUnit.MILLISECONDS);
            this.j.b().g(i2, TimeUnit.MILLISECONDS);
            hVar.o(y.e(), str);
            hVar.b();
            b0 b0VarA = hVar.a(false);
            b0VarA.p(y);
            c0 c0VarC = b0VarA.c();
            long jB = okhttp3.g0.h.g.b(c0VarC);
            if (jB == -1) {
                jB = 0;
            }
            B bK = hVar.k(jB);
            okhttp3.g0.e.D(bK, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            bK.close();
            int iP = c0VarC.p();
            if (iP == 200) {
                if (this.i.c().n() && this.j.c().n()) {
                    return null;
                }
                throw new IOException("TLS tunnel buffered too many bytes!");
            }
            if (iP != 407) {
                throw new IOException("Unexpected response code for CONNECT: " + c0VarC.p());
            }
            Y yA = this.c.a().h().a(this.c, c0VarC);
            if (yA == null) {
                throw new IOException("Failed to authenticate with proxy");
            }
            if ("close".equalsIgnoreCase(c0VarC.M("Connection"))) {
                return yA;
            }
            y = yA;
        }
    }

    private Y k() {
        X x = new X();
        x.p(this.c.a().l());
        x.h("CONNECT", null);
        x.f("Host", okhttp3.g0.e.s(this.c.a().l(), true));
        x.f("Proxy-Connection", "Keep-Alive");
        x.f("User-Agent", okhttp3.g0.f.a());
        Y yB = x.b();
        b0 b0Var = new b0();
        b0Var.p(yB);
        b0Var.n(Protocol.HTTP_1_1);
        b0Var.g(407);
        b0Var.k("Preemptive Authenticate");
        b0Var.b(okhttp3.g0.e.c);
        b0Var.q(-1L);
        b0Var.o(-1L);
        b0Var.i("Proxy-Authenticate", "OkHttp-Preemptive");
        Y yA = this.c.a().h().a(this.c, b0Var.c());
        return yA != null ? yA : yB;
    }

    private void l(b bVar, int i, InterfaceC0053m interfaceC0053m, H h) throws Throwable {
        if (this.c.a().k() != null) {
            h.u(interfaceC0053m);
            h(bVar);
            h.t(interfaceC0053m, this.f);
            if (this.g == Protocol.HTTP_2) {
                s(i);
                return;
            }
            return;
        }
        if (!this.c.a().f().contains(Protocol.H2_PRIOR_KNOWLEDGE)) {
            this.e = this.d;
            this.g = Protocol.HTTP_1_1;
        } else {
            this.e = this.d;
            this.g = Protocol.H2_PRIOR_KNOWLEDGE;
            s(i);
        }
    }

    private void s(int i) throws SocketException {
        this.e.setSoTimeout(0);
        p pVar = new p(true);
        pVar.d(this.e, this.c.a().l().l(), this.i, this.j);
        pVar.b(this);
        pVar.c(i);
        y yVarA = pVar.a();
        this.h = yVarA;
        yVarA.j0();
    }

    @Override // okhttp3.InterfaceC0059t
    public f0 a() {
        return this.c;
    }

    @Override // okhttp3.InterfaceC0059t
    public Protocol b() {
        return this.g;
    }

    @Override // okhttp3.internal.http2.s
    public void c(y yVar) {
        synchronized (this.b) {
            this.m = yVar.X();
        }
    }

    @Override // okhttp3.internal.http2.s
    public void d(F f) {
        f.f(ErrorCode.REFUSED_STREAM);
    }

    public void e() {
        okhttp3.g0.e.h(this.d);
    }

    /* JADX WARN: Code duplicated, block: B:55:0x012f  */
    /* JADX WARN: Code duplicated, block: B:56:0x0135  */
    /* JADX WARN: Code duplicated, block: B:58:0x013a  */
    /* JADX WARN: Code duplicated, block: B:76:0x0142 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:77:0x0142 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:78:? A[LOOP:0: B:66:0x0088->B:78:?, LOOP_END, SYNTHETIC] */
    public void f(int i, int i2, int i3, int i4, boolean z, InterfaceC0053m interfaceC0053m, H h) throws Throwable {
        if (this.g != null) {
            throw new IllegalStateException("already connected");
        }
        List listB = this.c.a().b();
        b bVar = new b(listB);
        if (this.c.a().k() == null) {
            if (!listB.contains(C0063x.h)) {
                throw new RouteException(new UnknownServiceException("CLEARTEXT communication not enabled for client"));
            }
            String strL = this.c.a().l().l();
            if (!k.l().r(strL)) {
                throw new RouteException(new UnknownServiceException("CLEARTEXT communication to " + strL + " not permitted by network security policy"));
            }
        } else if (this.c.a().f().contains(Protocol.H2_PRIOR_KNOWLEDGE)) {
            throw new RouteException(new UnknownServiceException("H2_PRIOR_KNOWLEDGE cannot be used with HTTPS"));
        }
        RouteException routeException = null;
        while (true) {
            try {
                if (this.c.c()) {
                    i(i, i2, i3, interfaceC0053m, h);
                    if (this.d == null) {
                        break;
                    }
                } else {
                    try {
                        g(i, i2, interfaceC0053m, h);
                    } catch (IOException e) {
                        e = e;
                        okhttp3.g0.e.h(this.e);
                        okhttp3.g0.e.h(this.d);
                        this.e = null;
                        this.d = null;
                        this.i = null;
                        this.j = null;
                        this.f = null;
                        this.g = null;
                        this.h = null;
                        h.e(interfaceC0053m, this.c.d(), this.c.b(), null, e);
                        if (routeException == null) {
                            routeException = new RouteException(e);
                        } else {
                            routeException.a(e);
                        }
                        if (z) {
                            throw routeException;
                        }
                        if (bVar.b(e)) {
                            throw routeException;
                        }
                    }
                }
                try {
                    l(bVar, i4, interfaceC0053m, h);
                    h.d(interfaceC0053m, this.c.d(), this.c.b(), this.g);
                    break;
                } catch (IOException e2) {
                    e = e2;
                    okhttp3.g0.e.h(this.e);
                    okhttp3.g0.e.h(this.d);
                    this.e = null;
                    this.d = null;
                    this.i = null;
                    this.j = null;
                    this.f = null;
                    this.g = null;
                    this.h = null;
                    h.e(interfaceC0053m, this.c.d(), this.c.b(), null, e);
                    if (routeException == null) {
                        routeException = new RouteException(e);
                    } else {
                        routeException.a(e);
                    }
                    if (z) {
                        throw routeException;
                    }
                    if (bVar.b(e)) {
                        throw routeException;
                    }
                }
            } catch (IOException e3) {
                e = e3;
            }
        }
        if (this.c.c() && this.d == null) {
            throw new RouteException(new ProtocolException("Too many tunnel connections attempted: 21"));
        }
        if (this.h != null) {
            synchronized (this.b) {
                this.m = this.h.X();
            }
        }
    }

    public I m() {
        return this.f;
    }

    public boolean n(C0029a c0029a, f0 f0Var) {
        if (this.n.size() >= this.m || this.k || !okhttp3.g0.a.f182a.g(this.c.a(), c0029a)) {
            return false;
        }
        if (c0029a.l().l().equals(a().a().l().l())) {
            return true;
        }
        if (this.h == null || f0Var == null || f0Var.b().type() != Proxy.Type.DIRECT || this.c.b().type() != Proxy.Type.DIRECT || !this.c.d().equals(f0Var.d()) || f0Var.a().e() != okhttp3.g0.n.d.f216a || !t(c0029a.l())) {
            return false;
        }
        try {
            c0029a.a().a(c0029a.l().l(), m().e());
            return true;
        } catch (SSLPeerUnverifiedException unused) {
            return false;
        }
    }

    public boolean o(boolean z) {
        if (this.e.isClosed() || this.e.isInputShutdown() || this.e.isOutputShutdown()) {
            return false;
        }
        y yVar = this.h;
        if (yVar != null) {
            return yVar.W(System.nanoTime());
        }
        if (z) {
            try {
                int soTimeout = this.e.getSoTimeout();
                try {
                    this.e.setSoTimeout(1);
                    if (this.i.n()) {
                        this.e.setSoTimeout(soTimeout);
                        return false;
                    }
                    this.e.setSoTimeout(soTimeout);
                    return true;
                } catch (Throwable th) {
                    this.e.setSoTimeout(soTimeout);
                    throw th;
                }
            } catch (SocketTimeoutException unused) {
            } catch (IOException unused2) {
                return false;
            }
        }
        return true;
    }

    public boolean p() {
        return this.h != null;
    }

    public okhttp3.g0.h.d q(T t, N n, h hVar) throws SocketException {
        if (this.h != null) {
            return new C0045h(t, n, hVar, this.h);
        }
        this.e.setSoTimeout(n.c());
        this.i.b().g(n.c(), TimeUnit.MILLISECONDS);
        this.j.b().g(n.b(), TimeUnit.MILLISECONDS);
        return new okhttp3.g0.i.h(t, hVar, this.i, this.j);
    }

    public Socket r() {
        return this.e;
    }

    public boolean t(M m) {
        if (m.w() != this.c.a().l().w()) {
            return false;
        }
        if (m.l().equals(this.c.a().l().l())) {
            return true;
        }
        return this.f != null && okhttp3.g0.n.d.f216a.c(m.l(), (X509Certificate) this.f.e().get(0));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Connection{");
        sb.append(this.c.a().l().l());
        sb.append(":");
        sb.append(this.c.a().l().w());
        sb.append(", proxy=");
        sb.append(this.c.b());
        sb.append(" hostAddress=");
        sb.append(this.c.d());
        sb.append(" cipherSuite=");
        I i = this.f;
        sb.append(i != null ? i.a() : "none");
        sb.append(" protocol=");
        sb.append(this.g);
        sb.append('}');
        return sb.toString();
    }
}
