package okhttp3.internal.http2;

import com.google.common.net.HttpHeaders;
import d.B;
import d.C;
import d.g;
import d.j;
import d.l;
import d.t;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.RealResponseBody;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;

/* JADX INFO: loaded from: classes2.dex */
public final class Http2Codec implements HttpCodec {
    public static final String TE = "te";
    public final Interceptor.Chain chain;
    public final Http2Connection connection;
    public final Protocol protocol;
    public Http2Stream stream;
    public final StreamAllocation streamAllocation;
    public static final String CONNECTION = "connection";
    public static final String HOST = "host";
    public static final String KEEP_ALIVE = "keep-alive";
    public static final String PROXY_CONNECTION = "proxy-connection";
    public static final String TRANSFER_ENCODING = "transfer-encoding";
    public static final String ENCODING = "encoding";
    public static final String UPGRADE = "upgrade";
    public static final List<String> HTTP_2_SKIPPED_REQUEST_HEADERS = Util.immutableList(CONNECTION, HOST, KEEP_ALIVE, PROXY_CONNECTION, "te", TRANSFER_ENCODING, ENCODING, UPGRADE, Header.TARGET_METHOD_UTF8, Header.TARGET_PATH_UTF8, Header.TARGET_SCHEME_UTF8, Header.TARGET_AUTHORITY_UTF8);
    public static final List<String> HTTP_2_SKIPPED_RESPONSE_HEADERS = Util.immutableList(CONNECTION, HOST, KEEP_ALIVE, PROXY_CONNECTION, "te", TRANSFER_ENCODING, ENCODING, UPGRADE);

    class StreamFinishingSource extends l {
        public long bytesRead;
        public boolean completed;

        public StreamFinishingSource(C c2) {
            super(c2);
            this.completed = false;
            this.bytesRead = 0L;
        }

        private void endOfInput(IOException iOException) {
            if (this.completed) {
                return;
            }
            this.completed = true;
            Http2Codec http2Codec = Http2Codec.this;
            http2Codec.streamAllocation.streamFinished(false, http2Codec, this.bytesRead, iOException);
        }

        @Override // d.l, d.C, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.delegate.close();
            endOfInput(null);
        }

        @Override // d.l, d.C
        public long read(g gVar, long j) throws IOException {
            try {
                long j2 = delegate().read(gVar, j);
                if (j2 > 0) {
                    this.bytesRead += j2;
                }
                return j2;
            } catch (IOException e2) {
                endOfInput(e2);
                throw e2;
            }
        }
    }

    public Http2Codec(OkHttpClient okHttpClient, Interceptor.Chain chain, StreamAllocation streamAllocation, Http2Connection http2Connection) {
        this.chain = chain;
        this.streamAllocation = streamAllocation;
        this.connection = http2Connection;
        this.protocol = okHttpClient.protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE) ? Protocol.H2_PRIOR_KNOWLEDGE : Protocol.HTTP_2;
    }

    public static List<Header> http2HeadersList(Request request) {
        Headers headers = request.headers();
        ArrayList arrayList = new ArrayList(headers.size() + 4);
        arrayList.add(new Header(Header.TARGET_METHOD, request.method()));
        arrayList.add(new Header(Header.TARGET_PATH, RequestLine.requestPath(request.url())));
        String strHeader = request.header(HttpHeaders.HOST);
        if (strHeader != null) {
            arrayList.add(new Header(Header.TARGET_AUTHORITY, strHeader));
        }
        arrayList.add(new Header(Header.TARGET_SCHEME, request.url().scheme()));
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            j jVarD = j.d(headers.name(i).toLowerCase(Locale.US));
            if (!HTTP_2_SKIPPED_REQUEST_HEADERS.contains(jVarD.j())) {
                arrayList.add(new Header(jVarD, headers.value(i)));
            }
        }
        return arrayList;
    }

    public static Response.Builder readHttp2HeadersList(Headers headers, Protocol protocol) throws ProtocolException {
        Headers.Builder builder = new Headers.Builder();
        int size = headers.size();
        StatusLine statusLine = null;
        for (int i = 0; i < size; i++) {
            String strName = headers.name(i);
            String strValue = headers.value(i);
            if (strName.equals(Header.RESPONSE_STATUS_UTF8)) {
                statusLine = StatusLine.parse("HTTP/1.1 " + strValue);
            } else if (!HTTP_2_SKIPPED_RESPONSE_HEADERS.contains(strName)) {
                Internal.instance.addLenient(builder, strName, strValue);
            }
        }
        if (statusLine != null) {
            return new Response.Builder().protocol(protocol).code(statusLine.code).message(statusLine.message).headers(builder.build());
        }
        throw new ProtocolException("Expected ':status' header not present");
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void cancel() {
        Http2Stream http2Stream = this.stream;
        if (http2Stream != null) {
            http2Stream.closeLater(ErrorCode.CANCEL);
        }
    }

    @Override // okhttp3.internal.http.HttpCodec
    public B createRequestBody(Request request, long j) {
        return this.stream.getSink();
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void finishRequest() {
        this.stream.getSink().close();
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void flushRequest() {
        this.connection.flush();
    }

    @Override // okhttp3.internal.http.HttpCodec
    public ResponseBody openResponseBody(Response response) {
        StreamAllocation streamAllocation = this.streamAllocation;
        streamAllocation.eventListener.responseBodyStart(streamAllocation.call);
        return new RealResponseBody(response.header("Content-Type"), okhttp3.internal.http.HttpHeaders.contentLength(response), t.a(new StreamFinishingSource(this.stream.getSource())));
    }

    @Override // okhttp3.internal.http.HttpCodec
    public Response.Builder readResponseHeaders(boolean z) throws ProtocolException {
        Response.Builder http2HeadersList = readHttp2HeadersList(this.stream.takeHeaders(), this.protocol);
        if (z && Internal.instance.code(http2HeadersList) == 100) {
            return null;
        }
        return http2HeadersList;
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void writeRequestHeaders(Request request) {
        if (this.stream != null) {
            return;
        }
        this.stream = this.connection.newStream(http2HeadersList(request), request.body() != null);
        this.stream.readTimeout().timeout(this.chain.readTimeoutMillis(), TimeUnit.MILLISECONDS);
        this.stream.writeTimeout().timeout(this.chain.writeTimeoutMillis(), TimeUnit.MILLISECONDS);
    }
}
