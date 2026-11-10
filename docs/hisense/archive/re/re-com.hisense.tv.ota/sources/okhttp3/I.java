package okhttp3;

import java.io.IOException;
import java.security.cert.Certificate;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

/* JADX INFO: loaded from: classes.dex */
public final class I {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final TlsVersion f160a;
    private final C0058s b;
    private final List c;
    private final List d;

    private I(TlsVersion tlsVersion, C0058s c0058s, List list, List list2) {
        this.f160a = tlsVersion;
        this.b = c0058s;
        this.c = list;
        this.d = list2;
    }

    public static I b(SSLSession sSLSession) throws IOException {
        Certificate[] peerCertificates;
        String cipherSuite = sSLSession.getCipherSuite();
        if (cipherSuite == null) {
            throw new IllegalStateException("cipherSuite == null");
        }
        if ("SSL_NULL_WITH_NULL_NULL".equals(cipherSuite)) {
            throw new IOException("cipherSuite == SSL_NULL_WITH_NULL_NULL");
        }
        C0058s c0058sA = C0058s.a(cipherSuite);
        String protocol = sSLSession.getProtocol();
        if (protocol == null) {
            throw new IllegalStateException("tlsVersion == null");
        }
        if ("NONE".equals(protocol)) {
            throw new IOException("tlsVersion == NONE");
        }
        TlsVersion tlsVersionA = TlsVersion.a(protocol);
        try {
            peerCertificates = sSLSession.getPeerCertificates();
        } catch (SSLPeerUnverifiedException unused) {
            peerCertificates = null;
        }
        List listU = peerCertificates != null ? okhttp3.g0.e.u(peerCertificates) : Collections.emptyList();
        Certificate[] localCertificates = sSLSession.getLocalCertificates();
        return new I(tlsVersionA, c0058sA, listU, localCertificates != null ? okhttp3.g0.e.u(localCertificates) : Collections.emptyList());
    }

    public static I c(TlsVersion tlsVersion, C0058s c0058s, List list, List list2) {
        if (tlsVersion == null) {
            throw new NullPointerException("tlsVersion == null");
        }
        if (c0058s != null) {
            return new I(tlsVersion, c0058s, okhttp3.g0.e.t(list), okhttp3.g0.e.t(list2));
        }
        throw new NullPointerException("cipherSuite == null");
    }

    public C0058s a() {
        return this.b;
    }

    public List d() {
        return this.d;
    }

    public List e() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof I)) {
            return false;
        }
        I i = (I) obj;
        return this.f160a.equals(i.f160a) && this.b.equals(i.b) && this.c.equals(i.c) && this.d.equals(i.d);
    }

    public TlsVersion f() {
        return this.f160a;
    }

    public int hashCode() {
        return ((((((527 + this.f160a.hashCode()) * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode();
    }
}
