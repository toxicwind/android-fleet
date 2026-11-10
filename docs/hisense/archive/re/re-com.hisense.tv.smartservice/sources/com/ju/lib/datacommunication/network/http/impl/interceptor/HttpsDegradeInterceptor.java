package com.ju.lib.datacommunication.network.http.impl.interceptor;

import com.ju.lib.datacommunication.network.http.impl.AbsInterceptor;
import com.ju.lib.datacommunication.network.http.impl.InterceptorPriority;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: classes2.dex */
public class HttpsDegradeInterceptor extends AbsInterceptor {
    public static final String TAG = "HTTP2.HttpsReplaceInterceptor";
    public final long localTime = 1531989526;

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Request request = chain.request();
        HttpUrl httpUrlUrl = request.url();
        if (!httpUrlUrl.scheme().equals("https") || System.currentTimeMillis() / 1000 >= 1531989526) {
            return chain.proceed(request);
        }
        Request requestBuild = request.newBuilder().url(httpUrlUrl.newBuilder().scheme("http").port(80).build()).build();
        HttpLog.d(TAG, "newRequest.url() ", requestBuild.url());
        return chain.proceed(requestBuild);
    }

    @Override // com.ju.lib.datacommunication.network.http.impl.AbsInterceptor
    public InterceptorPriority priority() {
        return InterceptorPriority.HTTPS_DEGRADE;
    }
}
