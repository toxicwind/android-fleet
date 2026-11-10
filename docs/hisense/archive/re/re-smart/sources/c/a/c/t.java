package c.a.c;

import c.a.b.C0149mb;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: classes2.dex */
public class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f1742a = Logger.getLogger(t.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c.a.c.a.j f1743b = c.a.c.a.j.f1646c;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static t f1744c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c.a.c.a.j f1745d;

    static {
        boolean z;
        ClassLoader classLoader = t.class.getClassLoader();
        try {
            classLoader.loadClass("com.android.org.conscrypt.OpenSSLSocketImpl");
        } catch (ClassNotFoundException e2) {
            f1742a.log(Level.FINE, "Unable to find Conscrypt. Skipping", (Throwable) e2);
            try {
                classLoader.loadClass("org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImpl");
            } catch (ClassNotFoundException e3) {
                f1742a.log(Level.FINE, "Unable to find any OpenSSLSocketImpl. Skipping", (Throwable) e3);
                z = false;
            }
        }
        z = true;
        f1744c = z ? new a(f1743b) : new t(f1743b);
    }

    @VisibleForTesting
    public t(c.a.c.a.j jVar) {
        Preconditions.checkNotNull(jVar, "platform");
        this.f1745d = jVar;
    }

    public void a(SSLSocket sSLSocket, String str, List<c.a.c.a.k> list) {
        this.f1745d.a(sSLSocket, str, list);
    }

    public String b(SSLSocket sSLSocket, String str, List<c.a.c.a.k> list) {
        if (list != null) {
            a(sSLSocket, str, list);
        }
        try {
            sSLSocket.startHandshake();
            String strA = a(sSLSocket);
            if (strA != null) {
                this.f1745d.a(sSLSocket);
                return strA;
            }
            throw new RuntimeException("TLS ALPN negotiation failed with protocols: " + list);
        } catch (Throwable th) {
            this.f1745d.a(sSLSocket);
            throw th;
        }
    }

    public String a(SSLSocket sSLSocket) {
        return this.f1745d.b(sSLSocket);
    }

    public static /* synthetic */ String[] a(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((c.a.c.a.k) it.next()).f1666f);
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    @VisibleForTesting
    public static boolean a(String str) {
        if (str.contains("_")) {
            return false;
        }
        try {
            URI uriA = C0149mb.a(str);
            Preconditions.checkArgument(uriA.getHost() != null, "No host in authority '%s'", str);
            Preconditions.checkArgument(uriA.getUserInfo() == null, "Userinfo must not be present on authority: '%s'", str);
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    @VisibleForTesting
    static final class a extends t {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final c.a.c.a.f<Socket> f1746e = new c.a.c.a.f<>(null, "setUseSessionTickets", Boolean.TYPE);

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final c.a.c.a.f<Socket> f1747f = new c.a.c.a.f<>(null, "setHostname", String.class);
        public static final c.a.c.a.f<Socket> g = new c.a.c.a.f<>(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
        public static final c.a.c.a.f<Socket> h = new c.a.c.a.f<>(null, "setAlpnProtocols", byte[].class);
        public static final c.a.c.a.f<Socket> i = new c.a.c.a.f<>(byte[].class, "getNpnSelectedProtocol", new Class[0]);
        public static final c.a.c.a.f<Socket> j = new c.a.c.a.f<>(null, "setNpnProtocols", byte[].class);
        public static final Method k;
        public static final Method l;
        public static final Method m;
        public static final Method n;
        public static final Method o;
        public static final Method p;
        public static final Constructor<?> q;

        static {
            Method method;
            Method method2;
            Method method3;
            Method method4;
            Method method5;
            Method method6;
            Constructor<?> constructor = null;
            try {
                method2 = SSLParameters.class.getMethod("setApplicationProtocols", String[].class);
                try {
                    method = SSLParameters.class.getMethod("getApplicationProtocols", new Class[0]);
                    try {
                        method3 = SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]);
                        try {
                            Class<?> cls = Class.forName("android.net.ssl.SSLSockets");
                            method4 = cls.getMethod("isSupportedSocket", SSLSocket.class);
                            try {
                                method5 = cls.getMethod("setUseSessionTickets", SSLSocket.class, Boolean.TYPE);
                            } catch (ClassNotFoundException e2) {
                                e = e2;
                                t.f1742a.log(Level.FINER, "Failed to find Android 10.0+ APIs", (Throwable) e);
                                method5 = null;
                            } catch (NoSuchMethodException e3) {
                                e = e3;
                                t.f1742a.log(Level.FINER, "Failed to find Android 10.0+ APIs", (Throwable) e);
                                method5 = null;
                            }
                        } catch (ClassNotFoundException e4) {
                            e = e4;
                            method4 = null;
                        } catch (NoSuchMethodException e5) {
                            e = e5;
                            method4 = null;
                        }
                    } catch (ClassNotFoundException e6) {
                        e = e6;
                        method3 = null;
                        method4 = method3;
                        t.f1742a.log(Level.FINER, "Failed to find Android 10.0+ APIs", (Throwable) e);
                        method5 = null;
                        m = method2;
                        n = method;
                        o = method3;
                        k = method4;
                        l = method5;
                        method6 = SSLParameters.class.getMethod("setServerNames", List.class);
                        constructor = Class.forName("javax.net.ssl.SNIHostName").getConstructor(String.class);
                        p = method6;
                        q = constructor;
                    } catch (NoSuchMethodException e7) {
                        e = e7;
                        method3 = null;
                        method4 = method3;
                        t.f1742a.log(Level.FINER, "Failed to find Android 10.0+ APIs", (Throwable) e);
                        method5 = null;
                        m = method2;
                        n = method;
                        o = method3;
                        k = method4;
                        l = method5;
                        method6 = SSLParameters.class.getMethod("setServerNames", List.class);
                        constructor = Class.forName("javax.net.ssl.SNIHostName").getConstructor(String.class);
                        p = method6;
                        q = constructor;
                    }
                } catch (ClassNotFoundException e8) {
                    e = e8;
                    method = null;
                    method3 = null;
                } catch (NoSuchMethodException e9) {
                    e = e9;
                    method = null;
                    method3 = null;
                }
            } catch (ClassNotFoundException e10) {
                e = e10;
                method = null;
                method2 = null;
                method3 = null;
            } catch (NoSuchMethodException e11) {
                e = e11;
                method = null;
                method2 = null;
                method3 = null;
            }
            m = method2;
            n = method;
            o = method3;
            k = method4;
            l = method5;
            try {
                method6 = SSLParameters.class.getMethod("setServerNames", List.class);
                try {
                    constructor = Class.forName("javax.net.ssl.SNIHostName").getConstructor(String.class);
                } catch (ClassNotFoundException e12) {
                    e = e12;
                    t.f1742a.log(Level.FINER, "Failed to find Android 7.0+ APIs", (Throwable) e);
                } catch (NoSuchMethodException e13) {
                    e = e13;
                    t.f1742a.log(Level.FINER, "Failed to find Android 7.0+ APIs", (Throwable) e);
                }
            } catch (ClassNotFoundException e14) {
                e = e14;
                method6 = null;
            } catch (NoSuchMethodException e15) {
                e = e15;
                method6 = null;
            }
            p = method6;
            q = constructor;
        }

        public a(c.a.c.a.j jVar) {
            super(jVar);
        }

        @Override // c.a.c.t
        public void a(SSLSocket sSLSocket, String str, List<c.a.c.a.k> list) {
            boolean z;
            String[] strArrA = t.a(list);
            SSLParameters sSLParameters = sSLSocket.getSSLParameters();
            if (str != null) {
                try {
                    try {
                        if (t.a(str)) {
                            if (k == null || !((Boolean) k.invoke(null, sSLSocket)).booleanValue()) {
                                f1746e.b(sSLSocket, true);
                            } else {
                                l.invoke(null, sSLSocket, true);
                            }
                            if (p == null || q == null) {
                                f1747f.b(sSLSocket, str);
                            } else {
                                p.invoke(sSLParameters, Collections.singletonList(q.newInstance(str)));
                            }
                        }
                    } catch (InvocationTargetException e2) {
                        throw new RuntimeException(e2);
                    }
                } catch (IllegalAccessException e3) {
                    throw new RuntimeException(e3);
                } catch (InstantiationException e4) {
                    throw new RuntimeException(e4);
                }
            }
            if (o != null) {
                try {
                    o.invoke(sSLSocket, new Object[0]);
                    m.invoke(sSLParameters, strArrA);
                    z = true;
                } catch (InvocationTargetException e5) {
                    if (!(e5.getTargetException() instanceof UnsupportedOperationException)) {
                        throw e5;
                    }
                    t.f1742a.log(Level.FINER, "setApplicationProtocol unsupported, will try old methods");
                    z = false;
                }
            } else {
                z = false;
            }
            sSLSocket.setSSLParameters(sSLParameters);
            if (z && n != null && Arrays.equals(strArrA, (String[]) n.invoke(sSLSocket.getSSLParameters(), new Object[0]))) {
                return;
            }
            Object[] objArr = {c.a.c.a.j.a(list)};
            if (this.f1745d.a() == c.a.c.a.j.e.ALPN_AND_NPN) {
                h.c(sSLSocket, objArr);
            }
            if (this.f1745d.a() == c.a.c.a.j.e.NONE) {
                throw new RuntimeException("We can not do TLS handshake on this Android version, please install the Google Play Services Dynamic Security Provider to use TLS");
            }
            j.c(sSLSocket, objArr);
        }

        /* JADX WARN: Code duplicated, block: B:19:0x0045 A[Catch: Exception -> 0x004e, TRY_LEAVE, TryCatch #0 {Exception -> 0x004e, blocks: (B:17:0x0039, B:19:0x0045), top: B:44:0x0039 }] */
        /* JADX WARN: Code duplicated, block: B:23:0x0058  */
        /* JADX WARN: Code duplicated, block: B:27:0x006e A[Catch: Exception -> 0x0077, TRY_LEAVE, TryCatch #2 {Exception -> 0x0077, blocks: (B:25:0x0062, B:27:0x006e), top: B:46:0x0062 }] */
        /* JADX WARN: Code duplicated, block: B:31:0x0081  */
        /* JADX WARN: Code duplicated, block: B:44:0x0039 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:46:0x0062 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // c.a.c.t
        public String b(SSLSocket sSLSocket, String str, List<c.a.c.a.k> list) {
            String strA;
            byte[] bArr;
            byte[] bArr2;
            Method method = o;
            if (method != null) {
                try {
                    strA = (String) method.invoke(sSLSocket, new Object[0]);
                } catch (IllegalAccessException e2) {
                    throw new RuntimeException(e2);
                } catch (InvocationTargetException e3) {
                    if (!(e3.getTargetException() instanceof UnsupportedOperationException)) {
                        throw new RuntimeException(e3);
                    }
                    t.f1742a.log(Level.FINER, "Socket unsupported for getApplicationProtocol, will try old methods");
                    if (this.f1745d.a() == c.a.c.a.j.e.ALPN_AND_NPN) {
                        try {
                            bArr = (byte[]) g.c(sSLSocket, new Object[0]);
                            if (bArr != null) {
                                strA = new String(bArr, c.a.c.a.m.f1674b);
                            } else if (this.f1745d.a() != c.a.c.a.j.e.NONE) {
                                try {
                                    bArr2 = (byte[]) i.c(sSLSocket, new Object[0]);
                                    if (bArr2 != null) {
                                        strA = new String(bArr2, c.a.c.a.m.f1674b);
                                    } else {
                                        strA = null;
                                    }
                                } catch (Exception e4) {
                                    t.f1742a.log(Level.FINE, "Failed calling getNpnSelectedProtocol()", (Throwable) e4);
                                }
                            } else {
                                strA = null;
                            }
                        } catch (Exception e5) {
                            t.f1742a.log(Level.FINE, "Failed calling getAlpnSelectedProtocol()", (Throwable) e5);
                        }
                    } else if (this.f1745d.a() != c.a.c.a.j.e.NONE) {
                        bArr2 = (byte[]) i.c(sSLSocket, new Object[0]);
                        if (bArr2 != null) {
                            strA = new String(bArr2, c.a.c.a.m.f1674b);
                        } else {
                            strA = null;
                        }
                    } else {
                        strA = null;
                    }
                }
            } else if (this.f1745d.a() == c.a.c.a.j.e.ALPN_AND_NPN) {
                bArr = (byte[]) g.c(sSLSocket, new Object[0]);
                if (bArr != null) {
                    strA = new String(bArr, c.a.c.a.m.f1674b);
                } else if (this.f1745d.a() != c.a.c.a.j.e.NONE) {
                    bArr2 = (byte[]) i.c(sSLSocket, new Object[0]);
                    if (bArr2 != null) {
                        strA = new String(bArr2, c.a.c.a.m.f1674b);
                    } else {
                        strA = null;
                    }
                } else {
                    strA = null;
                }
            } else if (this.f1745d.a() != c.a.c.a.j.e.NONE) {
                bArr2 = (byte[]) i.c(sSLSocket, new Object[0]);
                if (bArr2 != null) {
                    strA = new String(bArr2, c.a.c.a.m.f1674b);
                } else {
                    strA = null;
                }
            } else {
                strA = null;
            }
            if (strA == null) {
                if (list != null) {
                    a(sSLSocket, str, list);
                }
                try {
                    sSLSocket.startHandshake();
                    strA = a(sSLSocket);
                    if (strA == null) {
                        throw new RuntimeException("TLS ALPN negotiation failed with protocols: " + list);
                    }
                    this.f1745d.a(sSLSocket);
                } catch (Throwable th) {
                    this.f1745d.a(sSLSocket);
                    throw th;
                }
            }
            return strA;
        }

        @Override // c.a.c.t
        public String a(SSLSocket sSLSocket) {
            Method method = o;
            if (method != null) {
                try {
                    return (String) method.invoke(sSLSocket, new Object[0]);
                } catch (IllegalAccessException e2) {
                    throw new RuntimeException(e2);
                } catch (InvocationTargetException e3) {
                    if (e3.getTargetException() instanceof UnsupportedOperationException) {
                        t.f1742a.log(Level.FINER, "Socket unsupported for getApplicationProtocol, will try old methods");
                    } else {
                        throw new RuntimeException(e3);
                    }
                }
            }
            if (this.f1745d.a() == c.a.c.a.j.e.ALPN_AND_NPN) {
                try {
                    byte[] bArr = (byte[]) g.c(sSLSocket, new Object[0]);
                    if (bArr != null) {
                        return new String(bArr, c.a.c.a.m.f1674b);
                    }
                } catch (Exception e4) {
                    t.f1742a.log(Level.FINE, "Failed calling getAlpnSelectedProtocol()", (Throwable) e4);
                }
            }
            if (this.f1745d.a() == c.a.c.a.j.e.NONE) {
                return null;
            }
            try {
                byte[] bArr2 = (byte[]) i.c(sSLSocket, new Object[0]);
                if (bArr2 != null) {
                    return new String(bArr2, c.a.c.a.m.f1674b);
                }
                return null;
            } catch (Exception e5) {
                t.f1742a.log(Level.FINE, "Failed calling getNpnSelectedProtocol()", (Throwable) e5);
                return null;
            }
        }
    }
}
