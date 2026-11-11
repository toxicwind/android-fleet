package org.conscrypt;

import javax.net.ssl.SSLSession;

/* JADX INFO: loaded from: classes8.dex */
public interface SSLClientSessionCache {
    byte[] getSessionData(String str, int i);

    void putSessionData(SSLSession sSLSession, byte[] bArr);
}
