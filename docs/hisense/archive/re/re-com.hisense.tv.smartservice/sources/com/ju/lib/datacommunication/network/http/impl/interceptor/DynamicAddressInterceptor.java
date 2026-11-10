package com.ju.lib.datacommunication.network.http.impl.interceptor;

import com.google.common.net.HttpHeaders;
import com.ju.lib.datacommunication.network.http.address.DynamicAddressUseCase;
import com.ju.lib.datacommunication.network.http.impl.AbsInterceptor;
import com.ju.lib.datacommunication.network.http.impl.InterceptorPriority;
import java.net.URL;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: classes2.dex */
public class DynamicAddressInterceptor extends AbsInterceptor {
    public static final String TAG = "HTTP2.DynamicAddressInterceptor";
    public final DynamicAddressUseCase mDynamicAddress;

    public DynamicAddressInterceptor(DynamicAddressUseCase dynamicAddressUseCase) {
        this.mDynamicAddress = dynamicAddressUseCase;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        String randomHost;
        Request request = chain.request();
        HttpUrl httpUrlUrl = request.url();
        if (!AbsInterceptor.checkIP(httpUrlUrl.host())) {
            try {
                String str = httpUrlUrl.scheme() + "://" + httpUrlUrl.host();
                if (this.mDynamicAddress != null && (randomHost = this.mDynamicAddress.getRandomHost(str)) != null && !randomHost.isEmpty() && !str.equals(randomHost)) {
                    if (!randomHost.contains("http://") && !randomHost.contains("https://")) {
                        randomHost = httpUrlUrl.scheme() + "://" + randomHost;
                    }
                    URL url = new URL(randomHost);
                    HttpUrl.Builder builderHost = httpUrlUrl.newBuilder().scheme(url.getProtocol()).host(url.getHost());
                    if (url.getPort() != -1) {
                        builderHost.port(url.getPort());
                    }
                    request = request.newBuilder().url(builderHost.build()).header(HttpHeaders.HOST, url.getHost()).build();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return chain.proceed(request);
    }

    @Override // com.ju.lib.datacommunication.network.http.impl.AbsInterceptor
    public InterceptorPriority priority() {
        return InterceptorPriority.DYNAMIC_DOMAIN_SELECT;
    }
}
