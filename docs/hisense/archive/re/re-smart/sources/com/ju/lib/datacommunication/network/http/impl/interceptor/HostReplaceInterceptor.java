package com.ju.lib.datacommunication.network.http.impl.interceptor;

import com.google.common.net.HttpHeaders;
import com.ju.lib.datacommunication.network.http.impl.AbsHostReplaceInterceptor;
import com.ju.lib.datacommunication.network.http.impl.InterceptorPriority;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: classes2.dex */
public class HostReplaceInterceptor extends AbsHostReplaceInterceptor {
    public static final String TAG = "HTTP2.HostReplaceInterceptor";

    public HostReplaceInterceptor(String str) {
        super(str);
    }

    @Override // com.ju.lib.datacommunication.network.http.impl.AbsHostReplaceInterceptor
    public String getTag() {
        return TAG;
    }

    @Override // com.ju.lib.datacommunication.network.http.impl.AbsHostReplaceInterceptor
    public Response interceptCustomImp(Interceptor.Chain chain) {
        Request request = chain.request();
        try {
            HttpUrl httpUrlUrl = request.url();
            String strHost = httpUrlUrl.host();
            if (isWhiteListHost(strHost)) {
                return chain.proceed(request);
            }
            if (!needChangedHost(strHost)) {
                return chain.proceed(request);
            }
            HttpUrl httpUrlBuild = httpUrlUrl.newBuilder().host(createNewHost(strHost)).build();
            request = request.newBuilder().url(httpUrlBuild).header(HttpHeaders.HOST, httpUrlBuild.host()).build();
            HttpLog.i(getTag(), "intercept(), url = ", httpUrlBuild);
            return chain.proceed(request);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.ju.lib.datacommunication.network.http.impl.AbsInterceptor
    public InterceptorPriority priority() {
        return InterceptorPriority.THIRD_PARTY_DOMAIN_ADAPT;
    }
}
