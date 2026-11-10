package com.ju.lib.datacommunication.network.http.impl.networkinterceptor;

import com.google.common.net.HttpHeaders;
import com.ju.lib.datacommunication.network.http.core.HiRequest;
import com.ju.lib.datacommunication.network.http.impl.AbsInterceptor;
import com.ju.lib.datacommunication.network.http.impl.HttpStackImpl;
import com.ju.lib.datacommunication.network.http.impl.InterceptorPriority;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: classes2.dex */
public class HeadHostReplaceInterceptor extends AbsInterceptor {
    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Request request = chain.request();
        Object objTag = request.tag();
        if (objTag instanceof HttpStackImpl.RequestTag) {
            HiRequest.ReplaceHost replaceHost = ((HttpStackImpl.RequestTag) objTag).mRequest.getReplaceHost();
            if (replaceHost == null || !request.url().host().equals(replaceHost.ip)) {
                String strHeader = request.header(HttpHeaders.HOST);
                if (!AbsInterceptor.checkIP(request.url().host()) && !request.url().host().equals(strHeader)) {
                    request = request.newBuilder().header(HttpHeaders.HOST, request.url().host()).build();
                }
            } else {
                request = request.newBuilder().header(HttpHeaders.HOST, replaceHost.host).build();
            }
        }
        return chain.proceed(request);
    }

    @Override // com.ju.lib.datacommunication.network.http.impl.AbsInterceptor
    public InterceptorPriority priority() {
        return InterceptorPriority.NORMAL;
    }
}
