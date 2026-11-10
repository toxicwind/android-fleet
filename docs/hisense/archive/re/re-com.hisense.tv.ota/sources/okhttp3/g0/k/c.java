package okhttp3.g0.k;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: classes.dex */
final class c implements okhttp3.g0.n.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final X509TrustManager f208a;
    private final Method b;

    c(X509TrustManager x509TrustManager, Method method) {
        this.b = method;
        this.f208a = x509TrustManager;
    }

    @Override // okhttp3.g0.n.e
    public X509Certificate a(X509Certificate x509Certificate) {
        try {
            TrustAnchor trustAnchor = (TrustAnchor) this.b.invoke(this.f208a, x509Certificate);
            if (trustAnchor != null) {
                return trustAnchor.getTrustedCert();
            }
            return null;
        } catch (IllegalAccessException e) {
            throw okhttp3.g0.e.b("unable to get issues and signature", e);
        } catch (InvocationTargetException unused) {
            return null;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return this.f208a.equals(cVar.f208a) && this.b.equals(cVar.b);
    }

    public int hashCode() {
        return this.f208a.hashCode() + (this.b.hashCode() * 31);
    }
}
