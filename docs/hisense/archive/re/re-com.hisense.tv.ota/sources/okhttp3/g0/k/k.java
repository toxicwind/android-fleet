package okhttp3.g0.k;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Protocol;
import okhttp3.T;

/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final k f212a = k();
    private static final Logger b = Logger.getLogger(T.class.getName());

    public static List b(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Protocol protocol = (Protocol) list.get(i);
            if (protocol != Protocol.HTTP_1_0) {
                arrayList.add(protocol.toString());
            }
        }
        return arrayList;
    }

    static byte[] e(List list) {
        okio.h hVar = new okio.h();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Protocol protocol = (Protocol) list.get(i);
            if (protocol != Protocol.HTTP_1_0) {
                hVar.k0(protocol.toString().length());
                hVar.r0(protocol.toString());
            }
        }
        return hVar.V();
    }

    private static k i() {
        k kVarX = a.x();
        if (kVarX != null) {
            return kVarX;
        }
        k kVarX2 = e.x();
        if (kVarX2 != null) {
            return kVarX2;
        }
        throw new NullPointerException("No platform found on Android");
    }

    private static k j() {
        f fVarV;
        if (s() && (fVarV = f.v()) != null) {
            return fVarV;
        }
        g gVarV = g.v();
        if (gVarV != null) {
            return gVarV;
        }
        k kVarV = i.v();
        return kVarV != null ? kVarV : new k();
    }

    private static k k() {
        return q() ? i() : j();
    }

    public static k l() {
        return f212a;
    }

    public static boolean q() {
        return "Dalvik".equals(System.getProperty("java.vm.name"));
    }

    public static boolean s() {
        if ("conscrypt".equals(System.getProperty("okhttp.platform"))) {
            return true;
        }
        return "Conscrypt".equals(Security.getProviders()[0].getName());
    }

    public void a(SSLSocket sSLSocket) {
    }

    public okhttp3.g0.n.c c(X509TrustManager x509TrustManager) {
        return new okhttp3.g0.n.a(d(x509TrustManager));
    }

    public okhttp3.g0.n.e d(X509TrustManager x509TrustManager) {
        return new okhttp3.g0.n.b(x509TrustManager.getAcceptedIssuers());
    }

    public void f(SSLSocketFactory sSLSocketFactory) {
    }

    public void g(SSLSocket sSLSocket, String str, List list) {
    }

    public void h(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        socket.connect(inetSocketAddress, i);
    }

    public String m() {
        return "OkHttp";
    }

    public SSLContext n() {
        if ("1.7".equals(System.getProperty("java.specification.version"))) {
            try {
                return SSLContext.getInstance("TLSv1.2");
            } catch (NoSuchAlgorithmException unused) {
            }
        }
        try {
            return SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No TLS provider", e);
        }
    }

    public String o(SSLSocket sSLSocket) {
        return null;
    }

    public Object p(String str) {
        if (b.isLoggable(Level.FINE)) {
            return new Throwable(str);
        }
        return null;
    }

    public boolean r(String str) {
        return true;
    }

    public void t(int i, String str, Throwable th) {
        b.log(i == 5 ? Level.WARNING : Level.INFO, str, th);
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    public void u(String str, Object obj) {
        if (obj == null) {
            str = str + " To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);";
        }
        t(5, str, (Throwable) obj);
    }
}
