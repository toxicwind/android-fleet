package c.a.c.a;

import com.google.android.gms.security.ProviderInstaller;
import com.mediatek.twoworlds.tv.common.MtkTvConfigTypeBase;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;
import java.security.AccessController;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivilegedActionException;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: classes2.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f1644a = Logger.getLogger(j.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String[] f1645b = {"com.google.android.gms.org.conscrypt.OpenSSLProvider", "org.conscrypt.OpenSSLProvider", "com.android.org.conscrypt.OpenSSLProvider", "org.apache.harmony.xnet.provider.jsse.OpenSSLProvider", "com.google.android.libraries.stitch.sslguard.SslGuardProvider"};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final j f1646c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Provider f1647d;

    private static class a extends j {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final f<Socket> f1648e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final f<Socket> f1649f;
        public final f<Socket> g;
        public final f<Socket> h;
        public final e i;

        public a(f<Socket> fVar, f<Socket> fVar2, Method method, Method method2, f<Socket> fVar3, f<Socket> fVar4, Provider provider, e eVar) {
            super(provider);
            this.f1648e = fVar;
            this.f1649f = fVar2;
            this.g = fVar3;
            this.h = fVar4;
            this.i = eVar;
        }

        @Override // c.a.c.a.j
        public e a() {
            return this.i;
        }

        @Override // c.a.c.a.j
        public String b(SSLSocket sSLSocket) {
            byte[] bArr;
            if (this.g.a(sSLSocket) && (bArr = (byte[]) this.g.c(sSLSocket, new Object[0])) != null) {
                return new String(bArr, m.f1674b);
            }
            return null;
        }

        @Override // c.a.c.a.j
        public void a(SSLSocket sSLSocket, String str, List<k> list) {
            if (str != null) {
                this.f1648e.b(sSLSocket, true);
                this.f1649f.b(sSLSocket, str);
            }
            if (this.h.a(sSLSocket)) {
                this.h.c(sSLSocket, j.a(list));
            }
        }
    }

    private static class b extends j {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final Method f1650e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final Method f1651f;

        public /* synthetic */ b(Provider provider, Method method, Method method2, g gVar) {
            super(provider);
            this.f1650e = method;
            this.f1651f = method2;
        }

        @Override // c.a.c.a.j
        public e a() {
            return e.ALPN_AND_NPN;
        }

        @Override // c.a.c.a.j
        public String b(SSLSocket sSLSocket) {
            try {
                return (String) this.f1651f.invoke(sSLSocket, new Object[0]);
            } catch (IllegalAccessException e2) {
                throw new RuntimeException(e2);
            } catch (InvocationTargetException e3) {
                throw new RuntimeException(e3);
            }
        }

        @Override // c.a.c.a.j
        public void a(SSLSocket sSLSocket, String str, List<k> list) {
            SSLParameters sSLParameters = sSLSocket.getSSLParameters();
            ArrayList arrayList = new ArrayList(list.size());
            for (k kVar : list) {
                if (kVar != k.HTTP_1_0) {
                    arrayList.add(kVar.f1666f);
                }
            }
            try {
                this.f1650e.invoke(sSLParameters, arrayList.toArray(new String[arrayList.size()]));
                sSLSocket.setSSLParameters(sSLParameters);
            } catch (IllegalAccessException e2) {
                throw new RuntimeException(e2);
            } catch (InvocationTargetException e3) {
                throw new RuntimeException(e3);
            }
        }
    }

    private static class c extends j {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final Method f1652e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final Method f1653f;
        public final Method g;
        public final Class<?> h;
        public final Class<?> i;

        public c(Method method, Method method2, Method method3, Class<?> cls, Class<?> cls2, Provider provider) {
            super(provider);
            this.f1652e = method;
            this.f1653f = method2;
            this.g = method3;
            this.h = cls;
            this.i = cls2;
        }

        @Override // c.a.c.a.j
        public e a() {
            return e.ALPN_AND_NPN;
        }

        @Override // c.a.c.a.j
        public String b(SSLSocket sSLSocket) {
            try {
                d dVar = (d) Proxy.getInvocationHandler(this.f1653f.invoke(null, sSLSocket));
                if (!dVar.f1655b && dVar.f1656c == null) {
                    j.f1644a.log(Level.INFO, "ALPN callback dropped: SPDY and HTTP/2 are disabled. Is alpn-boot on the boot class path?");
                    return null;
                }
                if (dVar.f1655b) {
                    return null;
                }
                return dVar.f1656c;
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            } catch (InvocationTargetException unused2) {
                throw new AssertionError();
            }
        }

        @Override // c.a.c.a.j
        public void a(SSLSocket sSLSocket, String str, List<k> list) {
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i = 0; i < size; i++) {
                k kVar = list.get(i);
                if (kVar != k.HTTP_1_0) {
                    arrayList.add(kVar.f1666f);
                }
            }
            try {
                this.f1652e.invoke(null, sSLSocket, Proxy.newProxyInstance(j.class.getClassLoader(), new Class[]{this.h, this.i}, new d(arrayList)));
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            } catch (InvocationTargetException e3) {
                throw new AssertionError(e3);
            }
        }

        @Override // c.a.c.a.j
        public void a(SSLSocket sSLSocket) {
            try {
                this.g.invoke(null, sSLSocket);
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            } catch (InvocationTargetException e2) {
                j.f1644a.log(Level.FINE, "Failed to remove SSLSocket from Jetty ALPN", (Throwable) e2);
            }
        }
    }

    private static class d implements InvocationHandler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<String> f1654a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1655b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f1656c;

        public d(List<String> list) {
            this.f1654a = list;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            String name = method.getName();
            Class<?> returnType = method.getReturnType();
            if (objArr == null) {
                objArr = m.f1673a;
            }
            if (name.equals("supports") && Boolean.TYPE == returnType) {
                return true;
            }
            if (name.equals("unsupported") && Void.TYPE == returnType) {
                this.f1655b = true;
                return null;
            }
            if (name.equals("protocols") && objArr.length == 0) {
                return this.f1654a;
            }
            if ((!name.equals("selectProtocol") && !name.equals("select")) || String.class != returnType || objArr.length != 1 || !(objArr[0] instanceof List)) {
                if ((!name.equals("protocolSelected") && !name.equals("selected")) || objArr.length != 1) {
                    return method.invoke(this, objArr);
                }
                this.f1656c = (String) objArr[0];
                return null;
            }
            List list = (List) objArr[0];
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (this.f1654a.contains(list.get(i))) {
                    String str = (String) list.get(i);
                    this.f1656c = str;
                    return str;
                }
            }
            String str2 = this.f1654a.get(0);
            this.f1656c = str2;
            return str2;
        }
    }

    public enum e {
        ALPN_AND_NPN,
        NPN,
        NONE
    }

    /* JADX WARN: Multi-variable type inference failed */
    static {
        Method method;
        Provider provider;
        j jVar;
        Method method2;
        Method method3;
        e eVar;
        Object[] objArr;
        Object[] objArr2;
        Provider[] providers = Security.getProviders();
        int length = providers.length;
        int i = 0;
        loop0: while (true) {
            method = null;
            objArr2 = 0;
            if (i >= length) {
                f1644a.log(Level.WARNING, "Unable to find Conscrypt");
                provider = null;
                break;
            }
            Provider provider2 = providers[i];
            for (String str : f1645b) {
                if (str.equals(provider2.getClass().getName())) {
                    f1644a.log(Level.FINE, "Found registered provider {0}", str);
                    provider = provider2;
                    break loop0;
                }
            }
            i++;
        }
        boolean z = true;
        if (provider != null) {
            f fVar = new f(null, "setUseSessionTickets", Boolean.TYPE);
            f fVar2 = new f(null, "setHostname", String.class);
            f fVar3 = new f(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
            f fVar4 = new f(null, "setAlpnProtocols", byte[].class);
            try {
                Class<?> cls = Class.forName("android.net.TrafficStats");
                Method method4 = cls.getMethod("tagSocket", Socket.class);
                try {
                    method = cls.getMethod("untagSocket", Socket.class);
                } catch (ClassNotFoundException | NoSuchMethodException unused) {
                }
                method2 = method4;
                method3 = method;
            } catch (ClassNotFoundException | NoSuchMethodException unused2) {
                method2 = null;
                method3 = null;
            }
            if (provider.getName().equals(ProviderInstaller.PROVIDER_NAME) || provider.getName().equals("Conscrypt") || provider.getName().equals("Ssl_Guard")) {
                eVar = e.ALPN_AND_NPN;
            } else {
                try {
                    j.class.getClassLoader().loadClass("android.net.Network");
                    objArr = true;
                } catch (ClassNotFoundException e2) {
                    f1644a.log(Level.FINE, "Can't find class", (Throwable) e2);
                    objArr = false;
                }
                if (objArr == true) {
                    eVar = e.ALPN_AND_NPN;
                } else {
                    try {
                        j.class.getClassLoader().loadClass("android.app.ActivityOptions");
                    } catch (ClassNotFoundException e3) {
                        f1644a.log(Level.FINE, "Can't find class", (Throwable) e3);
                        z = false;
                    }
                    eVar = z ? e.NPN : e.NONE;
                }
            }
            jVar = new a(fVar, fVar2, method2, method3, fVar3, fVar4, provider, eVar);
        } else {
            try {
                Provider provider3 = SSLContext.getDefault().getProvider();
                try {
                    SSLContext sSLContext = SSLContext.getInstance(MtkTvConfigTypeBase.S3166_CFG_COUNT_TLS, provider3);
                    sSLContext.init(null, null, null);
                    ((Method) AccessController.doPrivileged(new g())).invoke(sSLContext.createSSLEngine(), new Object[0]);
                    jVar = new b(provider3, (Method) AccessController.doPrivileged(new h()), (Method) AccessController.doPrivileged(new i()), objArr2 == true ? 1 : 0);
                } catch (IllegalAccessException | InvocationTargetException | KeyManagementException | NoSuchAlgorithmException | PrivilegedActionException unused3) {
                    try {
                        Class<?> cls2 = Class.forName("org.eclipse.jetty.alpn.ALPN");
                        jVar = new c(cls2.getMethod("put", SSLSocket.class, Class.forName("org.eclipse.jetty.alpn.ALPN$Provider")), cls2.getMethod("get", SSLSocket.class), cls2.getMethod("remove", SSLSocket.class), Class.forName("org.eclipse.jetty.alpn.ALPN$ClientProvider"), Class.forName("org.eclipse.jetty.alpn.ALPN$ServerProvider"), provider3);
                    } catch (ClassNotFoundException | NoSuchMethodException unused4) {
                        jVar = new j(provider3);
                    }
                }
            } catch (NoSuchAlgorithmException e4) {
                throw new RuntimeException(e4);
            }
        }
        f1646c = jVar;
    }

    public j(Provider provider) {
        this.f1647d = provider;
    }

    public e a() {
        return e.NONE;
    }

    public void a(SSLSocket sSLSocket) {
    }

    public void a(SSLSocket sSLSocket, String str, List<k> list) {
    }

    public String b(SSLSocket sSLSocket) {
        return null;
    }

    public static byte[] a(List<k> list) {
        d.g gVar = new d.g();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            k kVar = list.get(i);
            if (kVar != k.HTTP_1_0) {
                gVar.writeByte(kVar.f1666f.length());
                gVar.a(kVar.f1666f);
            }
        }
        return gVar.d();
    }
}
