package okhttp3.internal.connection;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.SSLSocket;
import okhttp3.C0063x;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List f220a;
    private int b = 0;
    private boolean c;
    private boolean d;

    public b(List list) {
        this.f220a = list;
    }

    private boolean c(SSLSocket sSLSocket) {
        for (int i = this.b; i < this.f220a.size(); i++) {
            if (((C0063x) this.f220a.get(i)).c(sSLSocket)) {
                return true;
            }
        }
        return false;
    }

    public C0063x a(SSLSocket sSLSocket) throws UnknownServiceException {
        C0063x c0063x;
        int i = this.b;
        int size = this.f220a.size();
        while (true) {
            if (i >= size) {
                c0063x = null;
                break;
            }
            c0063x = (C0063x) this.f220a.get(i);
            i++;
            if (c0063x.c(sSLSocket)) {
                this.b = i;
                break;
            }
        }
        if (c0063x != null) {
            this.c = c(sSLSocket);
            okhttp3.g0.a.f182a.c(c0063x, sSLSocket, this.d);
            return c0063x;
        }
        throw new UnknownServiceException("Unable to find acceptable protocols. isFallback=" + this.d + ", modes=" + this.f220a + ", supported protocols=" + Arrays.toString(sSLSocket.getEnabledProtocols()));
    }

    public boolean b(IOException iOException) {
        this.d = true;
        if (!this.c || (iOException instanceof ProtocolException) || (iOException instanceof InterruptedIOException)) {
            return false;
        }
        boolean z = iOException instanceof SSLHandshakeException;
        if ((z && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) {
            return false;
        }
        return z || (iOException instanceof SSLProtocolException) || (iOException instanceof SSLException);
    }
}
