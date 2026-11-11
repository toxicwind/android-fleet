package org.jsoup.helper;

import java.lang.reflect.Constructor;
import java.net.Proxy;
import org.jsoup.internal.SharedConstants;

/* JADX INFO: loaded from: classes8.dex */
class RequestDispatch {
    static Constructor<RequestExecutor> clientConstructor;

    RequestDispatch() {
    }

    static {
        try {
            clientConstructor = Class.forName("org.jsoup.helper.HttpClientExecutor").getConstructor(HttpConnection.Request.class, HttpConnection.Response.class);
        } catch (Exception unused) {
        }
    }

    static RequestExecutor get(HttpConnection.Request request, HttpConnection.Response response) {
        Constructor<RequestExecutor> constructor;
        boolean z = Boolean.parseBoolean(System.getProperty(SharedConstants.UseHttpClient, "true"));
        if (request.sslSocketFactory() != null) {
            z = false;
        }
        Proxy proxy = request.proxy();
        if (proxy != null && proxy.type() == Proxy.Type.SOCKS) {
            z = false;
        }
        if (z && (constructor = clientConstructor) != null) {
            try {
                return constructor.newInstance(request, response);
            } catch (Exception unused) {
                return new UrlConnectionExecutor(request, response);
            }
        }
        return new UrlConnectionExecutor(request, response);
    }
}
