package c.a.c;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: classes2.dex */
public final class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @VisibleForTesting
    public static final List<c.a.c.a.k> f1749a = Collections.unmodifiableList(Arrays.asList(c.a.c.a.k.HTTP_2));

    public static SSLSocket a(SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, Socket socket, String str, int i, c.a.c.a.c cVar) throws IOException {
        String[] strArr;
        c.a.c.a.k kVar;
        Preconditions.checkNotNull(sSLSocketFactory, "sslSocketFactory");
        Preconditions.checkNotNull(socket, "socket");
        Preconditions.checkNotNull(cVar, "spec");
        SSLSocket sSLSocket = (SSLSocket) sSLSocketFactory.createSocket(socket, str, i, true);
        if (cVar.f1626d != null) {
            strArr = (String[]) c.a.c.a.m.a(String.class, cVar.f1626d, sSLSocket.getEnabledCipherSuites());
        } else {
            strArr = null;
        }
        String[] strArr2 = (String[]) c.a.c.a.m.a(String.class, cVar.f1627e, sSLSocket.getEnabledProtocols());
        c.a.c.a.c.a aVar = new c.a.c.a.c.a(cVar);
        if (!aVar.f1629a) {
            throw new IllegalStateException("no cipher suites for cleartext connections");
        }
        if (strArr == null) {
            aVar.f1630b = null;
        } else {
            aVar.f1630b = (String[]) strArr.clone();
        }
        if (!aVar.f1629a) {
            throw new IllegalStateException("no TLS versions for cleartext connections");
        }
        if (strArr2 == null) {
            aVar.f1631c = null;
        } else {
            aVar.f1631c = (String[]) strArr2.clone();
        }
        c.a.c.a.c cVarA = aVar.a();
        sSLSocket.setEnabledProtocols(cVarA.f1627e);
        String[] strArr3 = cVarA.f1626d;
        if (strArr3 != null) {
            sSLSocket.setEnabledCipherSuites(strArr3);
        }
        String strB = t.f1744c.b(sSLSocket, str, cVar.f1628f ? f1749a : null);
        List<c.a.c.a.k> list = f1749a;
        if (strB.equals(c.a.c.a.k.HTTP_1_0.f1666f)) {
            kVar = c.a.c.a.k.HTTP_1_0;
        } else if (strB.equals(c.a.c.a.k.HTTP_1_1.f1666f)) {
            kVar = c.a.c.a.k.HTTP_1_1;
        } else if (strB.equals(c.a.c.a.k.HTTP_2.f1666f)) {
            kVar = c.a.c.a.k.HTTP_2;
        } else {
            if (!strB.equals(c.a.c.a.k.SPDY_3.f1666f)) {
                throw new IOException(b.a.a.a.a.a("Unexpected protocol: ", strB));
            }
            kVar = c.a.c.a.k.SPDY_3;
        }
        boolean zContains = list.contains(kVar);
        StringBuilder sbA = b.a.a.a.a.a("Only ");
        sbA.append(f1749a);
        sbA.append(" are supported, but negotiated protocol is %s");
        Preconditions.checkState(zContains, sbA.toString(), strB);
        if (hostnameVerifier == null) {
            hostnameVerifier = c.a.c.a.e.f1639a;
        }
        if (hostnameVerifier.verify((str.startsWith("[") && str.endsWith("]")) ? str.substring(1, str.length() - 1) : str, sSLSocket.getSession())) {
            return sSLSocket;
        }
        throw new SSLPeerUnverifiedException(b.a.a.a.a.a("Cannot verify hostname: ", str));
    }
}
