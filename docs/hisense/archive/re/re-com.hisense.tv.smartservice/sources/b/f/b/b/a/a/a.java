package b.f.b.b.a.a;

import android.util.Log;
import com.mediatek.twoworlds.tv.common.MtkTvConfigTypeBase;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.internal.Util;

/* JADX INFO: loaded from: classes2.dex */
public class a extends SSLSocketFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SSLSocketFactory f673a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String[] f674b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String[] f675c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public X509TrustManager f676d;

    public a() {
        this.f676d = null;
        try {
            SSLContext sSLContext = SSLContext.getInstance(MtkTvConfigTypeBase.S3166_CFG_COUNT_TLS);
            this.f676d = Util.platformTrustManager();
            sSLContext.init(null, new X509TrustManager[]{this.f676d}, null);
            this.f673a = sSLContext.getSocketFactory();
            a();
        } catch (GeneralSecurityException unused) {
            throw new AssertionError();
        }
    }

    public final void a() {
        try {
            SSLSocket sSLSocket = (SSLSocket) SSLSocketFactory.getDefault().createSocket();
            Throwable th = null;
            try {
                if (sSLSocket != null) {
                    LinkedList linkedList = new LinkedList();
                    for (String str : sSLSocket.getSupportedProtocols()) {
                        if (!str.toUpperCase().contains("SSL") && !str.toUpperCase().equals("TLSV1") && !str.toUpperCase().contains("TLSV1.1")) {
                            linkedList.add(str);
                        }
                    }
                    Log.d("SpecialSSLSocketFactory", "protocols:" + linkedList);
                    this.f674b = (String[]) linkedList.toArray(new String[linkedList.size()]);
                    List listAsList = Arrays.asList("TLS_AES_128_GCM_SHA256", "TLS_AES_256_GCM_SHA384", "TLS_AES_128_CCM_SHA256", "TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384", "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", "TLS_ECDHE_ECDSA_WITH_AES_128_CCM", "TLS_ECDHE_ECDSA_WITH_AES_256_CCM");
                    List listAsList2 = Arrays.asList(sSLSocket.getSupportedCipherSuites());
                    HashSet hashSet = new HashSet(listAsList);
                    hashSet.retainAll(listAsList2);
                    if (hashSet.size() == 0) {
                        hashSet.addAll(new HashSet(Arrays.asList(sSLSocket.getEnabledCipherSuites())));
                    }
                    this.f675c = (String[]) hashSet.toArray(new String[0]);
                }
                if (sSLSocket != null) {
                    sSLSocket.close();
                }
            } catch (Throwable th2) {
                if (0 != 0) {
                    try {
                        sSLSocket.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                } else {
                    sSLSocket.close();
                }
                throw th2;
            }
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        Socket socketCreateSocket = this.f673a.createSocket(socket, str, i, z);
        if (socketCreateSocket instanceof SSLSocket) {
            a((SSLSocket) socketCreateSocket);
        }
        return socketCreateSocket;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return this.f675c;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return this.f675c;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) {
        Socket socketCreateSocket = this.f673a.createSocket(str, i);
        if (socketCreateSocket instanceof SSLSocket) {
            a((SSLSocket) socketCreateSocket);
        }
        return socketCreateSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) {
        Socket socketCreateSocket = this.f673a.createSocket(str, i, inetAddress, i2);
        if (socketCreateSocket instanceof SSLSocket) {
            a((SSLSocket) socketCreateSocket);
        }
        return socketCreateSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i) {
        Socket socketCreateSocket = this.f673a.createSocket(inetAddress, i);
        if (socketCreateSocket instanceof SSLSocket) {
            a((SSLSocket) socketCreateSocket);
        }
        return socketCreateSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) {
        Socket socketCreateSocket = this.f673a.createSocket(inetAddress, i, inetAddress2, i2);
        if (socketCreateSocket instanceof SSLSocket) {
            a((SSLSocket) socketCreateSocket);
        }
        return socketCreateSocket;
    }

    public final void a(SSLSocket sSLSocket) {
        String[] strArr = this.f674b;
        if (strArr != null) {
            sSLSocket.setEnabledProtocols(strArr);
        }
        sSLSocket.setEnabledCipherSuites(this.f675c);
    }
}
