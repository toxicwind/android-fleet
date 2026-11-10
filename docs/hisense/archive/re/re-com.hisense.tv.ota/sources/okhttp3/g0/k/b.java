package okhttp3.g0.k;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;

/* JADX INFO: loaded from: classes.dex */
final class b extends okhttp3.g0.n.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f207a;
    private final Method b;

    b(Object obj, Method method) {
        this.f207a = obj;
        this.b = method;
    }

    @Override // okhttp3.g0.n.c
    public List a(List list, String str) throws SSLPeerUnverifiedException {
        try {
            return (List) this.b.invoke(this.f207a, (X509Certificate[]) list.toArray(new X509Certificate[list.size()]), "RSA", str);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e2) {
            SSLPeerUnverifiedException sSLPeerUnverifiedException = new SSLPeerUnverifiedException(e2.getMessage());
            sSLPeerUnverifiedException.initCause(e2);
            throw sSLPeerUnverifiedException;
        }
    }

    public boolean equals(Object obj) {
        return obj instanceof b;
    }

    public int hashCode() {
        return 0;
    }
}
