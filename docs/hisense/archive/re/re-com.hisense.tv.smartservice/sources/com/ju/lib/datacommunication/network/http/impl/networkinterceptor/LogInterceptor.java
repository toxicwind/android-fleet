package com.ju.lib.datacommunication.network.http.impl.networkinterceptor;

import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/* JADX INFO: loaded from: classes2.dex */
public class LogInterceptor implements Interceptor {
    public static final String TAG = "HTTP2.LogInterceptor";
    public final HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() { // from class: com.ju.lib.datacommunication.network.http.impl.networkinterceptor.LogInterceptor.1
        @Override // okhttp3.logging.HttpLoggingInterceptor.Logger
        public void log(String str) {
            if (HttpLog.isHttpStackLogAble()) {
                HttpLog.i(LogInterceptor.TAG, str);
            }
        }
    });

    public LogInterceptor() {
        this.logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Object[] objArr = new Object[2];
        objArr[0] = "Connection: ";
        objArr[1] = chain.connection().route() != null ? chain.connection().route().toString() : "null";
        HttpLog.i(TAG, objArr);
        return this.logging.intercept(chain);
    }
}
