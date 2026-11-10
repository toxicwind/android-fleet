package com.hisense.tv.ota.W;

import android.util.Log;
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

/* JADX INFO: loaded from: classes.dex */
public class b extends SSLSocketFactory {
    public static final b e = new b();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private SSLSocketFactory f26a;
    private String[] b = null;
    private String[] c = null;
    private X509TrustManager d;

    private b() {
        this.d = null;
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            X509TrustManager x509TrustManagerC = okhttp3.g0.e.C();
            this.d = x509TrustManagerC;
            sSLContext.init(null, new X509TrustManager[]{x509TrustManagerC}, null);
            this.f26a = sSLContext.getSocketFactory();
            b();
        } catch (GeneralSecurityException e2) {
            Log.e("CustomSSLSocketFactory", e2.getMessage());
        }
    }

    private void b() {
        try {
            SSLSocket sSLSocket = (SSLSocket) SSLSocketFactory.getDefault().createSocket();
            if (sSLSocket != null) {
                try {
                    LinkedList linkedList = new LinkedList();
                    for (String str : sSLSocket.getSupportedProtocols()) {
                        if (!str.toUpperCase().contains("SSL") && !str.toUpperCase().equals("TLSV1") && !str.toUpperCase().contains("TLSV1.1")) {
                            linkedList.add(str);
                        }
                    }
                    Log.e("CustomSSLSocketFactory", "protocols:" + linkedList);
                    this.b = (String[]) linkedList.toArray(new String[linkedList.size()]);
                    List listAsList = Arrays.asList("TLS_AES_128_GCM_SHA256", "TLS_AES_256_GCM_SHA384", "TLS_AES_128_CCM_SHA256", "TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384", "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", "TLS_ECDHE_ECDSA_WITH_AES_128_CCM", "TLS_ECDHE_ECDSA_WITH_AES_256_CCM");
                    List listAsList2 = Arrays.asList(sSLSocket.getSupportedCipherSuites());
                    HashSet hashSet = new HashSet(listAsList);
                    hashSet.retainAll(listAsList2);
                    if (hashSet.size() == 0) {
                        hashSet.addAll(new HashSet(Arrays.asList(sSLSocket.getEnabledCipherSuites())));
                    }
                    Log.e("CustomSSLSocketFactory", "preferredCiphers:" + hashSet);
                    this.c = (String[]) hashSet.toArray(new String[0]);
                } catch (Throwable th) {
                    if (sSLSocket != null) {
                        try {
                            sSLSocket.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            }
            if (sSLSocket != null) {
                sSLSocket.close();
            }
        } catch (IOException e2) {
            Log.e("CustomSSLSocketFactory", e2.getMessage());
        }
    }

    private void c(SSLSocket sSLSocket) {
        String[] strArr = this.b;
        if (strArr != null) {
            sSLSocket.setEnabledProtocols(strArr);
        }
        sSLSocket.setEnabledCipherSuites(this.c);
    }

    public X509TrustManager a() {
        return this.d;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) {
        Socket socketCreateSocket = this.f26a.createSocket(str, i);
        if (socketCreateSocket instanceof SSLSocket) {
            c((SSLSocket) socketCreateSocket);
        }
        return socketCreateSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) {
        Socket socketCreateSocket = this.f26a.createSocket(str, i, inetAddress, i2);
        if (socketCreateSocket instanceof SSLSocket) {
            c((SSLSocket) socketCreateSocket);
        }
        return socketCreateSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i) {
        Socket socketCreateSocket = this.f26a.createSocket(inetAddress, i);
        if (socketCreateSocket instanceof SSLSocket) {
            c((SSLSocket) socketCreateSocket);
        }
        return socketCreateSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) {
        Socket socketCreateSocket = this.f26a.createSocket(inetAddress, i, inetAddress2, i2);
        if (socketCreateSocket instanceof SSLSocket) {
            c((SSLSocket) socketCreateSocket);
        }
        return socketCreateSocket;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        Socket socketCreateSocket = this.f26a.createSocket(socket, str, i, z);
        if (socketCreateSocket instanceof SSLSocket) {
            c((SSLSocket) socketCreateSocket);
        }
        return socketCreateSocket;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return this.c;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return this.c;
    }
}
