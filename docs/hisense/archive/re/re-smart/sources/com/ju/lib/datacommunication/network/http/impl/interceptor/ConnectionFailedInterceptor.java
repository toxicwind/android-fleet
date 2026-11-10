package com.ju.lib.datacommunication.network.http.impl.interceptor;

import com.ju.lib.datacommunication.network.http.core.HostInfo;
import com.ju.lib.datacommunication.network.http.core.HttpStack;
import com.ju.lib.datacommunication.network.http.impl.AbsInterceptor;
import com.ju.lib.datacommunication.network.http.impl.InterceptorPriority;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: classes2.dex */
public class ConnectionFailedInterceptor extends AbsInterceptor {
    public final HttpStack.Dns mDns;

    public ConnectionFailedInterceptor(HttpStack.Dns dns) {
        this.mDns = dns;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws SocketTimeoutException, UnknownHostException {
        HttpStack.Dns dns;
        Request request = chain.request();
        String strHost = request.url().host();
        if (AbsInterceptor.checkIP(strHost)) {
            return chain.proceed(request);
        }
        HostInfo hostInfo = this.mDns.parse(strHost);
        if (hostInfo == null || hostInfo.getInetAddress() == null || hostInfo.getInetAddress().isEmpty()) {
            throw new UnknownHostException(strHost);
        }
        try {
            return chain.proceed(request.newBuilder().header("X_REAL_HOST", hostInfo.getInetAddress().get(0).getHostAddress()).build());
        } catch (SocketTimeoutException e2) {
            if (e2.getMessage() != null && e2.getMessage().contains("failed to connect to") && (dns = this.mDns) != null) {
                dns.failed(request.url().host(), null);
            }
            throw e2;
        }
    }

    @Override // com.ju.lib.datacommunication.network.http.impl.AbsInterceptor
    public InterceptorPriority priority() {
        return InterceptorPriority.NORMAL;
    }
}
