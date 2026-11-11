package org.jsoup.helper;

import j$.util.Objects;
import j$.util.function.BiConsumer$CC;
import j$.util.function.Function$CC;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import javax.net.ssl.HttpsURLConnection;
import org.jsoup.Connection;

/* JADX INFO: loaded from: classes8.dex */
class UrlConnectionExecutor extends RequestExecutor {
    HttpURLConnection conn;

    UrlConnectionExecutor(HttpConnection.Request request, HttpConnection.Response response) {
        super(request, response);
    }

    @Override // org.jsoup.helper.RequestExecutor
    HttpConnection.Response execute() throws IOException {
        try {
            HttpURLConnection httpURLConnectionCreateConnection = createConnection(this.req);
            this.conn = httpURLConnectionCreateConnection;
            httpURLConnectionCreateConnection.connect();
            if (this.conn.getDoOutput()) {
                try {
                    OutputStream outputStream = this.conn.getOutputStream();
                    try {
                        HttpConnection.Response.writePost(this.req, outputStream);
                        if (outputStream != null) {
                            outputStream.close();
                        }
                    } catch (Throwable th) {
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        }
                        throw th;
                    }
                } catch (IOException e) {
                    this.conn.disconnect();
                    throw e;
                }
            }
            HttpConnection.Response response = new HttpConnection.Response(this.req);
            response.executor = this;
            response.method = Connection.Method.valueOf(this.conn.getRequestMethod());
            response.url = this.conn.getURL();
            response.statusCode = this.conn.getResponseCode();
            response.statusMessage = this.conn.getResponseMessage();
            if (response.statusMessage == null) {
                response.statusMessage = "";
            }
            response.contentType = this.conn.getContentType();
            response.contentLength = this.conn.getContentLength();
            response.prepareResponse(createHeaderMap(this.conn), this.prevRes);
            return response;
        } catch (IOException e2) {
            safeClose();
            throw e2;
        }
    }

    @Override // org.jsoup.helper.RequestExecutor
    InputStream responseBody() throws IOException {
        HttpURLConnection httpURLConnection = this.conn;
        if (httpURLConnection == null) {
            throw new IllegalStateException("Not yet executed");
        }
        InputStream errorStream = httpURLConnection.getErrorStream();
        HttpURLConnection httpURLConnection2 = this.conn;
        return errorStream != null ? httpURLConnection2.getErrorStream() : httpURLConnection2.getInputStream();
    }

    @Override // org.jsoup.helper.RequestExecutor
    void safeClose() {
        HttpURLConnection httpURLConnection = this.conn;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
            this.conn = null;
        }
    }

    private static HttpURLConnection createConnection(HttpConnection.Request request) throws IOException {
        URLConnection uRLConnectionOpenConnection;
        Proxy proxy = request.proxy();
        if (proxy == null) {
            uRLConnectionOpenConnection = request.url().openConnection();
        } else {
            uRLConnectionOpenConnection = request.url().openConnection(proxy);
        }
        final HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
        httpURLConnection.setRequestMethod(request.method().name());
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setConnectTimeout(request.timeout());
        httpURLConnection.setReadTimeout(request.timeout() / 2);
        if (httpURLConnection instanceof HttpsURLConnection) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
            if (request.sslContext != null) {
                httpsURLConnection.setSSLSocketFactory(request.sslContext.getSocketFactory());
            } else if (request.sslSocketFactory() != null) {
                httpsURLConnection.setSSLSocketFactory(request.sslSocketFactory());
            }
        }
        if (request.authenticator != null) {
            AuthenticationHandler.handler.enable(request.authenticator, httpURLConnection);
        }
        if (request.method().hasBody()) {
            httpURLConnection.setDoOutput(true);
        }
        Objects.requireNonNull(httpURLConnection);
        CookieUtil.applyCookiesToRequest(request, new BiConsumer() { // from class: org.jsoup.helper.UrlConnectionExecutor$$ExternalSyntheticLambda0
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                httpURLConnection.addRequestProperty((String) obj, (String) obj2);
            }

            public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            }
        });
        for (Map.Entry entry : request.multiHeaders().entrySet()) {
            Iterator it = ((List) entry.getValue()).iterator();
            while (it.hasNext()) {
                httpURLConnection.addRequestProperty((String) entry.getKey(), (String) it.next());
            }
        }
        return httpURLConnection;
    }

    private static LinkedHashMap<String, List<String>> createHeaderMap(HttpURLConnection httpURLConnection) {
        LinkedHashMap<String, List<String>> linkedHashMap = new LinkedHashMap<>();
        int i = 0;
        while (true) {
            String headerFieldKey = httpURLConnection.getHeaderFieldKey(i);
            String headerField = httpURLConnection.getHeaderField(i);
            if (headerFieldKey == null && headerField == null) {
                return linkedHashMap;
            }
            i++;
            if (headerFieldKey != null && headerField != null) {
                ((List) j$.util.Map.EL.computeIfAbsent(linkedHashMap, headerFieldKey, new Function() { // from class: org.jsoup.helper.UrlConnectionExecutor$$ExternalSyntheticLambda1
                    @Override // java.util.function.Function
                    public /* synthetic */ Function andThen(Function function) {
                        return Function$CC.$default$andThen(this, function);
                    }

                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return UrlConnectionExecutor.lambda$createHeaderMap$0((String) obj);
                    }

                    public /* synthetic */ Function compose(Function function) {
                        return Function$CC.$default$compose(this, function);
                    }
                })).add(headerField);
            }
        }
    }

    static /* synthetic */ List lambda$createHeaderMap$0(String str) {
        return new ArrayList();
    }
}
