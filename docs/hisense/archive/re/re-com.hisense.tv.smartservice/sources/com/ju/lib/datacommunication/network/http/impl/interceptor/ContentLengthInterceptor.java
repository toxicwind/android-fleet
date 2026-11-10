package com.ju.lib.datacommunication.network.http.impl.interceptor;

import android.text.TextUtils;
import b.a.a.a.a;
import com.google.common.net.HttpHeaders;
import com.ju.lib.datacommunication.network.http.Version;
import com.ju.lib.datacommunication.network.http.impl.AbsInterceptor;
import com.ju.lib.datacommunication.network.http.impl.InterceptorPriority;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* JADX INFO: loaded from: classes2.dex */
public class ContentLengthInterceptor extends AbsInterceptor {
    public static final String TAG = "HTTP2.ContentLengthInterceptor";

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Request request = chain.request();
        String strHeader = request.header("Content-Length");
        RequestBody requestBodyBody = request.body();
        if (requestBodyBody != null && TextUtils.isEmpty(strHeader)) {
            long jContentLength = requestBodyBody.contentLength();
            HttpLog.d(TAG, a.a("body.length = ", jContentLength));
            request = request.newBuilder().addHeader("Content-Length", String.valueOf(jContentLength)).build();
        }
        if (TextUtils.isEmpty(request.header(HttpHeaders.USER_AGENT))) {
            request = request.newBuilder().addHeader(HttpHeaders.USER_AGENT, Version.UserAgent).build();
        }
        return chain.proceed(request);
    }

    @Override // com.ju.lib.datacommunication.network.http.impl.AbsInterceptor
    public InterceptorPriority priority() {
        return InterceptorPriority.SIGNATURE;
    }
}
