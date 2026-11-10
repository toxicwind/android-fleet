package com.ju.lib.datacommunication.network.http.impl.interceptor;

import com.ju.lib.datacommunication.network.http.core.HiRequest;
import com.ju.lib.datacommunication.network.http.core.HttpStack;
import com.ju.lib.datacommunication.network.http.impl.AbsInterceptor;
import com.ju.lib.datacommunication.network.http.impl.HttpStackImpl;
import com.ju.lib.datacommunication.network.http.impl.InterceptorPriority;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: classes2.dex */
public class RetryInterceptor extends AbsInterceptor {
    public static final String TAG = "HTTP2.RetryInterceptor";
    public final HttpStack.BackoffRetry mBackoffRetry;

    public RetryInterceptor(HttpStack.BackoffRetry backoffRetry) {
        this.mBackoffRetry = backoffRetry;
    }

    private long backoffTimeMillis(int i) {
        HttpStack.BackoffRetry backoffRetry = this.mBackoffRetry;
        if (backoffRetry == null) {
            return 0L;
        }
        return backoffRetry.backoffTimeMillis(i);
    }

    private int getRetryTimes(Request request) {
        Object objTag = request.tag();
        if (!(objTag instanceof HttpStackImpl.RequestTag)) {
            return 0;
        }
        HiRequest hiRequest = ((HttpStackImpl.RequestTag) objTag).mRequest;
        if (hiRequest.getRetry() >= 0) {
            return hiRequest.getRetry();
        }
        return 0;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        int i;
        Response responseProceed;
        Request request = chain.request();
        int retryTimes = getRetryTimes(request);
        int i2 = 0;
        while (true) {
            try {
                try {
                    responseProceed = chain.proceed(request);
                    if ((responseProceed != null && responseProceed.isSuccessful()) || i2 >= retryTimes) {
                        break;
                    }
                    i = i2 + 1;
                    long jBackoffTimeMillis = backoffTimeMillis(i2);
                    if (jBackoffTimeMillis > 0 && i <= retryTimes) {
                        try {
                            HttpLog.w(TAG, "RetryInterceptor: currentTime = ", Integer.valueOf(i), ",times:", Integer.valueOf(retryTimes), ", url = ", request.url(), ",backoff=", Long.valueOf(jBackoffTimeMillis));
                            Thread.sleep(jBackoffTimeMillis);
                        } catch (InterruptedException unused) {
                        }
                    }
                    i2 = i;
                } catch (IOException e2) {
                    HttpLog.w(TAG, e2, "RetryInterceptor: currentTime = ", Integer.valueOf(i2), ", url = ", request.url());
                    if (i2 >= retryTimes) {
                        throw e2;
                    }
                    i = i2 + 1;
                    long jBackoffTimeMillis2 = backoffTimeMillis(i2);
                    if (jBackoffTimeMillis2 > 0 && i <= retryTimes) {
                        HttpLog.w(TAG, "RetryInterceptor: currentTime = ", Integer.valueOf(i), ",times:", Integer.valueOf(retryTimes), ", url = ", request.url(), ",backoff=", Long.valueOf(jBackoffTimeMillis2));
                        Thread.sleep(jBackoffTimeMillis2);
                    }
                }
            } catch (Throwable th) {
                int i3 = i2 + 1;
                long jBackoffTimeMillis3 = backoffTimeMillis(i2);
                if (jBackoffTimeMillis3 > 0 && i3 <= retryTimes) {
                    try {
                        HttpLog.w(TAG, "RetryInterceptor: currentTime = ", Integer.valueOf(i3), ",times:", Integer.valueOf(retryTimes), ", url = ", request.url(), ",backoff=", Long.valueOf(jBackoffTimeMillis3));
                        Thread.sleep(jBackoffTimeMillis3);
                    } catch (InterruptedException unused2) {
                    }
                }
                throw th;
            }
        }
        return responseProceed;
    }

    @Override // com.ju.lib.datacommunication.network.http.impl.AbsInterceptor
    public InterceptorPriority priority() {
        return InterceptorPriority.NORMAL;
    }
}
