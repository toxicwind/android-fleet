package okhttp3.g0.h;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.C0029a;
import okhttp3.C0057q;
import okhttp3.H;
import okhttp3.InterfaceC0053m;
import okhttp3.M;
import okhttp3.N;
import okhttp3.O;
import okhttp3.T;
import okhttp3.X;
import okhttp3.Y;
import okhttp3.b0;
import okhttp3.c0;
import okhttp3.f0;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.http2.ConnectionShutdownException;

/* JADX INFO: loaded from: classes.dex */
public final class l implements O {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final T f200a;
    private volatile okhttp3.internal.connection.h b;
    private Object c;
    private volatile boolean d;

    public l(T t, boolean z) {
        this.f200a = t;
    }

    private C0029a c(M m) {
        SSLSocketFactory sSLSocketFactory;
        HostnameVerifier hostnameVerifierO;
        C0057q c0057qD;
        if (m.m()) {
            SSLSocketFactory sSLSocketFactoryF = this.f200a.F();
            hostnameVerifierO = this.f200a.o();
            sSLSocketFactory = sSLSocketFactoryF;
            c0057qD = this.f200a.d();
        } else {
            sSLSocketFactory = null;
            hostnameVerifierO = null;
            c0057qD = null;
        }
        return new C0029a(m.l(), m.w(), this.f200a.k(), this.f200a.E(), sSLSocketFactory, hostnameVerifierO, c0057qD, this.f200a.z(), this.f200a.y(), this.f200a.x(), this.f200a.h(), this.f200a.A());
    }

    private Y d(c0 c0Var, f0 f0Var) throws ProtocolException {
        String strM;
        M mA;
        if (c0Var == null) {
            throw new IllegalStateException();
        }
        int iP = c0Var.p();
        String strG = c0Var.W().g();
        if (iP == 307 || iP == 308) {
            if (!strG.equals("GET") && !strG.equals("HEAD")) {
                return null;
            }
        } else {
            if (iP == 401) {
                return this.f200a.b().a(f0Var, c0Var);
            }
            if (iP == 503) {
                if ((c0Var.T() == null || c0Var.T().p() != 503) && i(c0Var, Integer.MAX_VALUE) == 0) {
                    return c0Var.W();
                }
                return null;
            }
            if (iP == 407) {
                if (f0Var.b().type() == Proxy.Type.HTTP) {
                    return this.f200a.z().a(f0Var, c0Var);
                }
                throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
            }
            if (iP == 408) {
                if (!this.f200a.D()) {
                    return null;
                }
                c0Var.W().a();
                if ((c0Var.T() == null || c0Var.T().p() != 408) && i(c0Var, 0) <= 0) {
                    return c0Var.W();
                }
                return null;
            }
            switch (iP) {
                case 300:
                case 301:
                case 302:
                case 303:
                    break;
                default:
                    return null;
            }
        }
        if (!this.f200a.m() || (strM = c0Var.M("Location")) == null || (mA = c0Var.W().k().A(strM)) == null) {
            return null;
        }
        if (!mA.B().equals(c0Var.W().k().B()) && !this.f200a.n()) {
            return null;
        }
        X xH = c0Var.W().h();
        if (h.b(strG)) {
            boolean zD = h.d(strG);
            if (h.c(strG)) {
                xH.h("GET", null);
            } else {
                xH.h(strG, zD ? c0Var.W().a() : null);
            }
            if (!zD) {
                xH.l("Transfer-Encoding");
                xH.l("Content-Length");
                xH.l("Content-Type");
            }
        }
        if (!j(c0Var, mA)) {
            xH.l("Authorization");
        }
        xH.p(mA);
        return xH.b();
    }

    private boolean f(IOException iOException, boolean z) {
        if (iOException instanceof ProtocolException) {
            return false;
        }
        if (iOException instanceof InterruptedIOException) {
            return (iOException instanceof SocketTimeoutException) && !z;
        }
        return (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) ? false : true;
    }

    private boolean g(IOException iOException, okhttp3.internal.connection.h hVar, boolean z, Y y) {
        hVar.q(iOException);
        if (this.f200a.D()) {
            return !(z && h(iOException, y)) && f(iOException, z) && hVar.h();
        }
        return false;
    }

    private boolean h(IOException iOException, Y y) {
        y.a();
        return iOException instanceof FileNotFoundException;
    }

    private int i(c0 c0Var, int i) {
        String strM = c0Var.M("Retry-After");
        if (strM == null) {
            return i;
        }
        if (strM.matches("\\d+")) {
            return Integer.valueOf(strM).intValue();
        }
        return Integer.MAX_VALUE;
    }

    private boolean j(c0 c0Var, M m) {
        M mK = c0Var.W().k();
        return mK.l().equals(m.l()) && mK.w() == m.w() && mK.B().equals(m.B());
    }

    @Override // okhttp3.O
    public c0 a(N n) throws IOException {
        Y yA = n.a();
        i iVar = (i) n;
        InterfaceC0053m interfaceC0053mG = iVar.g();
        H h = iVar.h();
        okhttp3.internal.connection.h hVar = new okhttp3.internal.connection.h(this.f200a.g(), c(yA.k()), interfaceC0053mG, h, this.c);
        this.b = hVar;
        int i = 0;
        c0 c0Var = null;
        while (!this.d) {
            try {
                try {
                    c0 c0VarJ = iVar.j(yA, hVar, null, null);
                    if (c0Var != null) {
                        b0 b0VarS = c0VarJ.S();
                        b0 b0VarS2 = c0Var.S();
                        b0VarS2.b(null);
                        b0VarS.m(b0VarS2.c());
                        c0VarJ = b0VarS.c();
                    }
                    try {
                        Y yD = d(c0VarJ, hVar.o());
                        if (yD == null) {
                            hVar.k();
                            return c0VarJ;
                        }
                        okhttp3.g0.e.g(c0VarJ.a());
                        int i2 = i + 1;
                        if (i2 > 20) {
                            hVar.k();
                            throw new ProtocolException("Too many follow-up requests: " + i2);
                        }
                        yD.a();
                        if (!j(c0VarJ, yD.k())) {
                            hVar.k();
                            hVar = new okhttp3.internal.connection.h(this.f200a.g(), c(yD.k()), interfaceC0053mG, h, this.c);
                            this.b = hVar;
                        } else if (hVar.c() != null) {
                            throw new IllegalStateException("Closing the body of " + c0VarJ + " didn't close its backing stream. Bad interceptor?");
                        }
                        c0Var = c0VarJ;
                        yA = yD;
                        i = i2;
                    } catch (IOException e) {
                        hVar.k();
                        throw e;
                    }
                } catch (Throwable th) {
                    hVar.q(null);
                    hVar.k();
                    throw th;
                }
            } catch (IOException e2) {
                if (!g(e2, hVar, !(e2 instanceof ConnectionShutdownException), yA)) {
                    throw e2;
                }
            } catch (RouteException e3) {
                if (!g(e3.c(), hVar, false, yA)) {
                    throw e3.b();
                }
            }
        }
        hVar.k();
        throw new IOException("Canceled");
    }

    public void b() {
        this.d = true;
        okhttp3.internal.connection.h hVar = this.b;
        if (hVar != null) {
            hVar.b();
        }
    }

    public boolean e() {
        return this.d;
    }

    public void k(Object obj) {
        this.c = obj;
    }
}
