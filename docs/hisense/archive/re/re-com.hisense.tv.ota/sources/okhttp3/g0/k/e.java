package okhttp3.g0.k;

import android.os.Build;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: classes.dex */
class e extends k {
    private final j c;
    private final j d;
    private final j e;
    private final j f;
    private final d g = d.b();

    e(Class cls, j jVar, j jVar2, j jVar3, j jVar4) {
        this.c = jVar;
        this.d = jVar2;
        this.e = jVar3;
        this.f = jVar4;
    }

    private boolean v(String str, Class cls, Object obj) {
        try {
            return ((Boolean) cls.getMethod("isCleartextTrafficPermitted", new Class[0]).invoke(obj, new Object[0])).booleanValue();
        } catch (NoSuchMethodException unused) {
            return super.r(str);
        }
    }

    private boolean w(String str, Class cls, Object obj) {
        try {
            return ((Boolean) cls.getMethod("isCleartextTrafficPermitted", String.class).invoke(obj, str)).booleanValue();
        } catch (NoSuchMethodException unused) {
            return v(str, cls, obj);
        }
    }

    public static k x() {
        Class<?> cls;
        j jVar;
        j jVar2;
        if (!k.q()) {
            return null;
        }
        try {
            try {
                cls = Class.forName("com.android.org.conscrypt.SSLParametersImpl");
            } catch (ClassNotFoundException unused) {
                cls = Class.forName("org.apache.harmony.xnet.provider.jsse.SSLParametersImpl");
            }
            Class<?> cls2 = cls;
            j jVar3 = new j(null, "setUseSessionTickets", Boolean.TYPE);
            j jVar4 = new j(null, "setHostname", String.class);
            if (z()) {
                j jVar5 = new j(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
                jVar2 = new j(null, "setAlpnProtocols", byte[].class);
                jVar = jVar5;
            } else {
                jVar = null;
                jVar2 = null;
            }
            return new e(cls2, jVar3, jVar4, jVar, jVar2);
        } catch (ClassNotFoundException unused2) {
            return null;
        }
    }

    static int y() {
        try {
            return Build.VERSION.SDK_INT;
        } catch (NoClassDefFoundError unused) {
            return 0;
        }
    }

    private static boolean z() {
        if (Security.getProvider("GMSCore_OpenSSL") != null) {
            return true;
        }
        try {
            Class.forName("android.net.Network");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    @Override // okhttp3.g0.k.k
    public okhttp3.g0.n.c c(X509TrustManager x509TrustManager) {
        try {
            Class<?> cls = Class.forName("android.net.http.X509TrustManagerExtensions");
            return new b(cls.getConstructor(X509TrustManager.class).newInstance(x509TrustManager), cls.getMethod("checkServerTrusted", X509Certificate[].class, String.class, String.class));
        } catch (Exception unused) {
            return super.c(x509TrustManager);
        }
    }

    @Override // okhttp3.g0.k.k
    public okhttp3.g0.n.e d(X509TrustManager x509TrustManager) {
        try {
            Method declaredMethod = x509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            declaredMethod.setAccessible(true);
            return new c(x509TrustManager, declaredMethod);
        } catch (NoSuchMethodException unused) {
            return super.d(x509TrustManager);
        }
    }

    @Override // okhttp3.g0.k.k
    public void g(SSLSocket sSLSocket, String str, List list) {
        if (str != null) {
            this.c.e(sSLSocket, Boolean.TRUE);
            this.d.e(sSLSocket, str);
        }
        j jVar = this.f;
        if (jVar == null || !jVar.g(sSLSocket)) {
            return;
        }
        this.f.f(sSLSocket, k.e(list));
    }

    @Override // okhttp3.g0.k.k
    public void h(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        try {
            socket.connect(inetSocketAddress, i);
        } catch (AssertionError e) {
            if (!okhttp3.g0.e.A(e)) {
                throw e;
            }
            throw new IOException(e);
        } catch (ClassCastException e2) {
            if (Build.VERSION.SDK_INT != 26) {
                throw e2;
            }
            IOException iOException = new IOException("Exception in connect");
            iOException.initCause(e2);
            throw iOException;
        } catch (SecurityException e3) {
            IOException iOException2 = new IOException("Exception in connect");
            iOException2.initCause(e3);
            throw iOException2;
        }
    }

    @Override // okhttp3.g0.k.k
    public SSLContext n() {
        int i = Build.VERSION.SDK_INT;
        if (i >= 16 && i < 22) {
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

    @Override // okhttp3.g0.k.k
    public String o(SSLSocket sSLSocket) {
        byte[] bArr;
        j jVar = this.e;
        if (jVar == null || !jVar.g(sSLSocket) || (bArr = (byte[]) this.e.f(sSLSocket, new Object[0])) == null) {
            return null;
        }
        return new String(bArr, okhttp3.g0.e.i);
    }

    @Override // okhttp3.g0.k.k
    public Object p(String str) {
        return this.g.a(str);
    }

    @Override // okhttp3.g0.k.k
    public boolean r(String str) {
        if (Build.VERSION.SDK_INT < 23) {
            return super.r(str);
        }
        try {
            Class<?> cls = Class.forName("android.security.NetworkSecurityPolicy");
            return w(str, cls, cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]));
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            return super.r(str);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw okhttp3.g0.e.b("unable to determine cleartext support", e);
        }
    }

    @Override // okhttp3.g0.k.k
    public void t(int i, String str, Throwable th) {
        int iMin;
        int i2 = i != 5 ? 3 : 5;
        if (th != null) {
            str = str + '\n' + Log.getStackTraceString(th);
        }
        int i3 = 0;
        int length = str.length();
        while (i3 < length) {
            int iIndexOf = str.indexOf(10, i3);
            if (iIndexOf == -1) {
                iIndexOf = length;
            }
            while (true) {
                iMin = Math.min(iIndexOf, i3 + 4000);
                Log.println(i2, "OkHttp", str.substring(i3, iMin));
                if (iMin >= iIndexOf) {
                    break;
                } else {
                    i3 = iMin;
                }
            }
            i3 = iMin + 1;
        }
    }

    @Override // okhttp3.g0.k.k
    public void u(String str, Object obj) {
        if (this.g.c(obj)) {
            return;
        }
        t(5, str, null);
    }
}
