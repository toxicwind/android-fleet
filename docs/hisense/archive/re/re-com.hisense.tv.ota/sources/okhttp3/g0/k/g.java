package okhttp3.g0.k;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: classes.dex */
final class g extends k {
    final Method c;
    final Method d;

    g(Method method, Method method2) {
        this.c = method;
        this.d = method2;
    }

    public static g v() {
        try {
            return new g(SSLParameters.class.getMethod("setApplicationProtocols", String[].class), SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]));
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    @Override // okhttp3.g0.k.k
    public void g(SSLSocket sSLSocket, String str, List list) {
        try {
            SSLParameters sSLParameters = sSLSocket.getSSLParameters();
            List listB = k.b(list);
            this.c.invoke(sSLParameters, listB.toArray(new String[listB.size()]));
            sSLSocket.setSSLParameters(sSLParameters);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw okhttp3.g0.e.b("unable to set ssl parameters", e);
        }
    }

    @Override // okhttp3.g0.k.k
    public String o(SSLSocket sSLSocket) {
        try {
            String str = (String) this.d.invoke(sSLSocket, new Object[0]);
            if (str == null || str.equals("")) {
                return null;
            }
            return str;
        } catch (IllegalAccessException e) {
            throw okhttp3.g0.e.b("failed to get ALPN selected protocol", e);
        } catch (InvocationTargetException e2) {
            if (e2.getCause() instanceof UnsupportedOperationException) {
                return null;
            }
            throw okhttp3.g0.e.b("failed to get ALPN selected protocol", e2);
        }
    }
}
