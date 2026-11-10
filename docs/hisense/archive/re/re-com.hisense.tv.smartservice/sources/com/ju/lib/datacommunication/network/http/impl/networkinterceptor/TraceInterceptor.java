package com.ju.lib.datacommunication.network.http.impl.networkinterceptor;

import com.ju.lib.datacommunication.network.http.core.HiHttpHeaders;
import com.ju.lib.datacommunication.network.http.core.HiResponse;
import com.ju.lib.datacommunication.network.http.impl.HttpStackImpl;
import java.io.IOException;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: classes2.dex */
public class TraceInterceptor implements Interceptor {
    private HiHttpHeaders change(Headers headers) {
        HiHttpHeaders.Builder builder = new HiHttpHeaders.Builder();
        for (int i = 0; i < headers.size(); i++) {
            builder.add(headers.name(i), headers.value(i));
        }
        return builder.build();
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        HiResponse.Trace next;
        Request request = chain.request();
        Object objTag = request.tag();
        if (objTag instanceof HttpStackImpl.RequestTag) {
            HttpStackImpl.RequestTag requestTag = (HttpStackImpl.RequestTag) objTag;
            HiResponse.Trace next2 = requestTag.mTrace;
            if (next2 == null) {
                next = new HiResponse.Trace();
                requestTag.mTrace = next;
            } else {
                while (next2.getNext() != null) {
                    next2 = next2.getNext();
                }
                next2.setNext(new HiResponse.Trace());
                next = next2.getNext();
            }
            if (chain.connection().route() != null) {
                next.setAddress(chain.connection().route().socketAddress());
            }
            next.setUrl(request.url().toString());
            next.setRequestHeaders(change(request.headers()));
            next.setRequestTimestamp(System.currentTimeMillis());
        } else {
            next = null;
        }
        try {
            Response responseProceed = chain.proceed(request);
            if (next != null) {
                next.setResponseTimestamp(System.currentTimeMillis());
                next.setSuccess(true);
                next.setCode(responseProceed.code());
                next.setResponseHeaders(change(responseProceed.headers()));
            }
            return responseProceed;
        } catch (IOException e2) {
            if (next != null) {
                next.setResponseTimestamp(System.currentTimeMillis());
                next.setSuccess(false);
                next.setException(e2);
            }
            throw e2;
        } catch (RuntimeException e3) {
            if (next != null) {
                next.setResponseTimestamp(System.currentTimeMillis());
                next.setSuccess(false);
                next.setException(new IOException(e3));
            }
            throw e3;
        }
    }
}
