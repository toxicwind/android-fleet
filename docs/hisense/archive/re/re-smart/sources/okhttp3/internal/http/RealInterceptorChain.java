package okhttp3.internal.http;

import b.a.a.a.a;
import com.ju.lib.datacommunication.network.http.dns.Tools;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.StreamAllocation;

/* JADX INFO: loaded from: classes2.dex */
public final class RealInterceptorChain implements Interceptor.Chain {
    public final Call call;
    public int calls;
    public final int connectTimeout;
    public final RealConnection connection;
    public final EventListener eventListener;
    public final HttpCodec httpCodec;
    public final int index;
    public final List<Interceptor> interceptors;
    public final int readTimeout;
    public final Request request;
    public final StreamAllocation streamAllocation;
    public final int writeTimeout;

    public RealInterceptorChain(List<Interceptor> list, StreamAllocation streamAllocation, HttpCodec httpCodec, RealConnection realConnection, int i, Request request, Call call, EventListener eventListener, int i2, int i3, int i4) {
        this.interceptors = list;
        this.connection = realConnection;
        this.streamAllocation = streamAllocation;
        this.httpCodec = httpCodec;
        this.index = i;
        this.request = request;
        this.call = call;
        this.eventListener = eventListener;
        this.connectTimeout = i2;
        this.readTimeout = i3;
        this.writeTimeout = i4;
    }

    @Override // okhttp3.Interceptor.Chain
    public Call call() {
        return this.call;
    }

    @Override // okhttp3.Interceptor.Chain
    public int connectTimeoutMillis() {
        return this.connectTimeout;
    }

    @Override // okhttp3.Interceptor.Chain
    public Connection connection() {
        return this.connection;
    }

    public EventListener eventListener() {
        return this.eventListener;
    }

    public HttpCodec httpStream() {
        return this.httpCodec;
    }

    @Override // okhttp3.Interceptor.Chain
    public Response proceed(Request request) {
        return proceed(request, this.streamAllocation, this.httpCodec, this.connection);
    }

    @Override // okhttp3.Interceptor.Chain
    public int readTimeoutMillis() {
        return this.readTimeout;
    }

    @Override // okhttp3.Interceptor.Chain
    public Request request() {
        return this.request;
    }

    public StreamAllocation streamAllocation() {
        return this.streamAllocation;
    }

    @Override // okhttp3.Interceptor.Chain
    public Interceptor.Chain withConnectTimeout(int i, TimeUnit timeUnit) {
        return new RealInterceptorChain(this.interceptors, this.streamAllocation, this.httpCodec, this.connection, this.index, this.request, this.call, this.eventListener, Util.checkDuration(Tools.KEY_TIMEOUT, i, timeUnit), this.readTimeout, this.writeTimeout);
    }

    @Override // okhttp3.Interceptor.Chain
    public Interceptor.Chain withReadTimeout(int i, TimeUnit timeUnit) {
        return new RealInterceptorChain(this.interceptors, this.streamAllocation, this.httpCodec, this.connection, this.index, this.request, this.call, this.eventListener, this.connectTimeout, Util.checkDuration(Tools.KEY_TIMEOUT, i, timeUnit), this.writeTimeout);
    }

    @Override // okhttp3.Interceptor.Chain
    public Interceptor.Chain withWriteTimeout(int i, TimeUnit timeUnit) {
        return new RealInterceptorChain(this.interceptors, this.streamAllocation, this.httpCodec, this.connection, this.index, this.request, this.call, this.eventListener, this.connectTimeout, this.readTimeout, Util.checkDuration(Tools.KEY_TIMEOUT, i, timeUnit));
    }

    @Override // okhttp3.Interceptor.Chain
    public int writeTimeoutMillis() {
        return this.writeTimeout;
    }

    public Response proceed(Request request, StreamAllocation streamAllocation, HttpCodec httpCodec, RealConnection realConnection) {
        if (this.index >= this.interceptors.size()) {
            throw new AssertionError();
        }
        this.calls++;
        if (this.httpCodec != null && !this.connection.supportsUrl(request.url())) {
            StringBuilder sbA = a.a("network interceptor ");
            sbA.append(this.interceptors.get(this.index - 1));
            sbA.append(" must retain the same host and port");
            throw new IllegalStateException(sbA.toString());
        }
        if (this.httpCodec != null && this.calls > 1) {
            StringBuilder sbA2 = a.a("network interceptor ");
            sbA2.append(this.interceptors.get(this.index - 1));
            sbA2.append(" must call proceed() exactly once");
            throw new IllegalStateException(sbA2.toString());
        }
        RealInterceptorChain realInterceptorChain = new RealInterceptorChain(this.interceptors, streamAllocation, httpCodec, realConnection, this.index + 1, request, this.call, this.eventListener, this.connectTimeout, this.readTimeout, this.writeTimeout);
        Interceptor interceptor = this.interceptors.get(this.index);
        Response responseIntercept = interceptor.intercept(realInterceptorChain);
        if (httpCodec != null && this.index + 1 < this.interceptors.size() && realInterceptorChain.calls != 1) {
            throw new IllegalStateException(a.a("network interceptor ", interceptor, " must call proceed() exactly once"));
        }
        if (responseIntercept == null) {
            throw new NullPointerException(a.a("interceptor ", interceptor, " returned null"));
        }
        if (responseIntercept.body() != null) {
            return responseIntercept;
        }
        throw new IllegalStateException(a.a("interceptor ", interceptor, " returned a response with no body"));
    }
}
