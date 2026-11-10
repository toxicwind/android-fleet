package okhttp3.g0.k;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: classes.dex */
class i extends k {
    private final Method c;
    private final Method d;
    private final Method e;
    private final Class f;
    private final Class g;

    i(Method method, Method method2, Method method3, Class cls, Class cls2) {
        this.c = method;
        this.d = method2;
        this.e = method3;
        this.f = cls;
        this.g = cls2;
    }

    public static k v() {
        try {
            Class<?> cls = Class.forName("org.eclipse.jetty.alpn.ALPN");
            Class<?> cls2 = Class.forName("org.eclipse.jetty.alpn.ALPN$Provider");
            return new i(cls.getMethod("put", SSLSocket.class, cls2), cls.getMethod("get", SSLSocket.class), cls.getMethod("remove", SSLSocket.class), Class.forName("org.eclipse.jetty.alpn.ALPN$ClientProvider"), Class.forName("org.eclipse.jetty.alpn.ALPN$ServerProvider"));
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            return null;
        }
    }

    @Override // okhttp3.g0.k.k
    public void a(SSLSocket sSLSocket) {
        try {
            this.e.invoke(null, sSLSocket);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw okhttp3.g0.e.b("unable to remove alpn", e);
        }
    }

    @Override // okhttp3.g0.k.k
    public void g(SSLSocket sSLSocket, String str, List list) {
        try {
            this.c.invoke(null, sSLSocket, Proxy.newProxyInstance(k.class.getClassLoader(), new Class[]{this.f, this.g}, new h(k.b(list))));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw okhttp3.g0.e.b("unable to set alpn", e);
        }
    }

    @Override // okhttp3.g0.k.k
    public String o(SSLSocket sSLSocket) {
        try {
            h hVar = (h) Proxy.getInvocationHandler(this.d.invoke(null, sSLSocket));
            if (!hVar.b && hVar.c == null) {
                k.l().t(4, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", null);
                return null;
            }
            if (hVar.b) {
                return null;
            }
            return hVar.c;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw okhttp3.g0.e.b("unable to get selected protocol", e);
        }
    }
}
