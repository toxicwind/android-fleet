package okhttp3.g0.k;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import org.conscrypt.Conscrypt;

/* JADX INFO: loaded from: classes.dex */
public class f extends k {
    private f() {
    }

    public static f v() {
        try {
            Class.forName("org.conscrypt.Conscrypt");
            if (Conscrypt.isAvailable()) {
                return new f();
            }
            return null;
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    private Provider w() {
        return Conscrypt.newProviderBuilder().provideTrustManager().build();
    }

    @Override // okhttp3.g0.k.k
    public void f(SSLSocketFactory sSLSocketFactory) {
        if (Conscrypt.isConscrypt(sSLSocketFactory)) {
            Conscrypt.setUseEngineSocket(sSLSocketFactory, true);
        }
    }

    @Override // okhttp3.g0.k.k
    public void g(SSLSocket sSLSocket, String str, List list) {
        if (!Conscrypt.isConscrypt(sSLSocket)) {
            super.g(sSLSocket, str, list);
            return;
        }
        if (str != null) {
            Conscrypt.setUseSessionTickets(sSLSocket, true);
            Conscrypt.setHostname(sSLSocket, str);
        }
        Conscrypt.setApplicationProtocols(sSLSocket, (String[]) k.b(list).toArray(new String[0]));
    }

    @Override // okhttp3.g0.k.k
    public SSLContext n() {
        try {
            return SSLContext.getInstance("TLSv1.3", w());
        } catch (NoSuchAlgorithmException e) {
            try {
                return SSLContext.getInstance("TLS", w());
            } catch (NoSuchAlgorithmException unused) {
                throw new IllegalStateException("No TLS provider", e);
            }
        }
    }

    @Override // okhttp3.g0.k.k
    public String o(SSLSocket sSLSocket) {
        return Conscrypt.isConscrypt(sSLSocket) ? Conscrypt.getApplicationProtocol(sSLSocket) : super.o(sSLSocket);
    }
}
