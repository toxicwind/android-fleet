package okhttp3.internal.http1;

import b.a.a.a.a;
import com.google.firebase.storage.StreamDownloadTask;
import d.B;
import d.C;
import d.E;
import d.g;
import d.h;
import d.i;
import d.m;
import d.t;
import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.RealResponseBody;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;

/* JADX INFO: loaded from: classes2.dex */
public final class Http1Codec implements HttpCodec {
    public static final int HEADER_LIMIT = 262144;
    public static final int STATE_CLOSED = 6;
    public static final int STATE_IDLE = 0;
    public static final int STATE_OPEN_REQUEST_BODY = 1;
    public static final int STATE_OPEN_RESPONSE_BODY = 4;
    public static final int STATE_READING_RESPONSE_BODY = 5;
    public static final int STATE_READ_RESPONSE_HEADERS = 3;
    public static final int STATE_WRITING_REQUEST_BODY = 2;
    public final OkHttpClient client;
    public final h sink;
    public final i source;
    public final StreamAllocation streamAllocation;
    public int state = 0;
    public long headerLimit = StreamDownloadTask.PREFERRED_CHUNK_SIZE;

    private abstract class AbstractSource implements C {
        public long bytesRead;
        public boolean closed;
        public final m timeout;

        public AbstractSource() {
            this.timeout = new m(Http1Codec.this.source.timeout());
            this.bytesRead = 0L;
        }

        public final void endOfInput(boolean z, IOException iOException) {
            Http1Codec http1Codec = Http1Codec.this;
            int i = http1Codec.state;
            if (i == 6) {
                return;
            }
            if (i != 5) {
                StringBuilder sbA = a.a("state: ");
                sbA.append(Http1Codec.this.state);
                throw new IllegalStateException(sbA.toString());
            }
            http1Codec.detachTimeout(this.timeout);
            Http1Codec http1Codec2 = Http1Codec.this;
            http1Codec2.state = 6;
            StreamAllocation streamAllocation = http1Codec2.streamAllocation;
            if (streamAllocation != null) {
                streamAllocation.streamFinished(!z, http1Codec2, this.bytesRead, iOException);
            }
        }

        @Override // d.C
        public long read(g gVar, long j) throws IOException {
            try {
                long j2 = Http1Codec.this.source.read(gVar, j);
                if (j2 > 0) {
                    this.bytesRead += j2;
                }
                return j2;
            } catch (IOException e2) {
                endOfInput(false, e2);
                throw e2;
            }
        }

        @Override // d.C
        public E timeout() {
            return this.timeout;
        }
    }

    private final class ChunkedSink implements B {
        public boolean closed;
        public final m timeout;

        public ChunkedSink() {
            this.timeout = new m(Http1Codec.this.sink.timeout());
        }

        @Override // d.B, java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() {
            if (this.closed) {
                return;
            }
            this.closed = true;
            Http1Codec.this.sink.a("0\r\n\r\n");
            Http1Codec.this.detachTimeout(this.timeout);
            Http1Codec.this.state = 3;
        }

        @Override // d.B, java.io.Flushable
        public synchronized void flush() {
            if (this.closed) {
                return;
            }
            Http1Codec.this.sink.flush();
        }

        @Override // d.B
        public E timeout() {
            return this.timeout;
        }

        @Override // d.B
        public void write(g gVar, long j) {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            if (j == 0) {
                return;
            }
            Http1Codec.this.sink.f(j);
            Http1Codec.this.sink.a("\r\n");
            Http1Codec.this.sink.write(gVar, j);
            Http1Codec.this.sink.a("\r\n");
        }
    }

    private class ChunkedSource extends AbstractSource {
        public static final long NO_CHUNK_YET = -1;
        public long bytesRemainingInChunk;
        public boolean hasMoreChunks;
        public final HttpUrl url;

        public ChunkedSource(HttpUrl httpUrl) {
            super();
            this.bytesRemainingInChunk = -1L;
            this.hasMoreChunks = true;
            this.url = httpUrl;
        }

        private void readChunkSize() throws ProtocolException {
            if (this.bytesRemainingInChunk != -1) {
                Http1Codec.this.source.b();
            }
            try {
                this.bytesRemainingInChunk = Http1Codec.this.source.h();
                String strTrim = Http1Codec.this.source.b().trim();
                if (this.bytesRemainingInChunk < 0 || !(strTrim.isEmpty() || strTrim.startsWith(";"))) {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.bytesRemainingInChunk + strTrim + "\"");
                }
                if (this.bytesRemainingInChunk == 0) {
                    this.hasMoreChunks = false;
                    HttpHeaders.receiveHeaders(Http1Codec.this.client.cookieJar(), this.url, Http1Codec.this.readHeaders());
                    endOfInput(true, null);
                }
            } catch (NumberFormatException e2) {
                throw new ProtocolException(e2.getMessage());
            }
        }

        @Override // d.C, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.closed) {
                return;
            }
            if (this.hasMoreChunks && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                endOfInput(false, null);
            }
            this.closed = true;
        }

        @Override // okhttp3.internal.http1.Http1Codec.AbstractSource, d.C
        public long read(g gVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException(a.a("byteCount < 0: ", j));
            }
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            if (!this.hasMoreChunks) {
                return -1L;
            }
            long j2 = this.bytesRemainingInChunk;
            if (j2 == 0 || j2 == -1) {
                readChunkSize();
                if (!this.hasMoreChunks) {
                    return -1L;
                }
            }
            long j3 = super.read(gVar, Math.min(j, this.bytesRemainingInChunk));
            if (j3 != -1) {
                this.bytesRemainingInChunk -= j3;
                return j3;
            }
            ProtocolException protocolException = new ProtocolException("unexpected end of stream");
            endOfInput(false, protocolException);
            throw protocolException;
        }
    }

    private final class FixedLengthSink implements B {
        public long bytesRemaining;
        public boolean closed;
        public final m timeout;

        public FixedLengthSink(long j) {
            this.timeout = new m(Http1Codec.this.sink.timeout());
            this.bytesRemaining = j;
        }

        @Override // d.B, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws ProtocolException {
            if (this.closed) {
                return;
            }
            this.closed = true;
            if (this.bytesRemaining > 0) {
                throw new ProtocolException("unexpected end of stream");
            }
            Http1Codec.this.detachTimeout(this.timeout);
            Http1Codec.this.state = 3;
        }

        @Override // d.B, java.io.Flushable
        public void flush() {
            if (this.closed) {
                return;
            }
            Http1Codec.this.sink.flush();
        }

        @Override // d.B
        public E timeout() {
            return this.timeout;
        }

        @Override // d.B
        public void write(g gVar, long j) throws ProtocolException {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            Util.checkOffsetAndCount(gVar.f2102c, 0L, j);
            if (j <= this.bytesRemaining) {
                Http1Codec.this.sink.write(gVar, j);
                this.bytesRemaining -= j;
            } else {
                StringBuilder sbA = a.a("expected ");
                sbA.append(this.bytesRemaining);
                sbA.append(" bytes but received ");
                sbA.append(j);
                throw new ProtocolException(sbA.toString());
            }
        }
    }

    private class FixedLengthSource extends AbstractSource {
        public long bytesRemaining;

        public FixedLengthSource(long j) {
            super();
            this.bytesRemaining = j;
            if (this.bytesRemaining == 0) {
                endOfInput(true, null);
            }
        }

        @Override // d.C, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.closed) {
                return;
            }
            if (this.bytesRemaining != 0 && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                endOfInput(false, null);
            }
            this.closed = true;
        }

        @Override // okhttp3.internal.http1.Http1Codec.AbstractSource, d.C
        public long read(g gVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException(a.a("byteCount < 0: ", j));
            }
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            long j2 = this.bytesRemaining;
            if (j2 == 0) {
                return -1L;
            }
            long j3 = super.read(gVar, Math.min(j2, j));
            if (j3 == -1) {
                ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                endOfInput(false, protocolException);
                throw protocolException;
            }
            this.bytesRemaining -= j3;
            if (this.bytesRemaining == 0) {
                endOfInput(true, null);
            }
            return j3;
        }
    }

    private class UnknownLengthSource extends AbstractSource {
        public boolean inputExhausted;

        public UnknownLengthSource() {
            super();
        }

        @Override // d.C, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.closed) {
                return;
            }
            if (!this.inputExhausted) {
                endOfInput(false, null);
            }
            this.closed = true;
        }

        @Override // okhttp3.internal.http1.Http1Codec.AbstractSource, d.C
        public long read(g gVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException(a.a("byteCount < 0: ", j));
            }
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            if (this.inputExhausted) {
                return -1L;
            }
            long j2 = super.read(gVar, j);
            if (j2 != -1) {
                return j2;
            }
            this.inputExhausted = true;
            endOfInput(true, null);
            return -1L;
        }
    }

    public Http1Codec(OkHttpClient okHttpClient, StreamAllocation streamAllocation, i iVar, h hVar) {
        this.client = okHttpClient;
        this.streamAllocation = streamAllocation;
        this.source = iVar;
        this.sink = hVar;
    }

    private String readHeaderLine() {
        String strA = this.source.a(this.headerLimit);
        this.headerLimit -= (long) strA.length();
        return strA;
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void cancel() {
        RealConnection realConnectionConnection = this.streamAllocation.connection();
        if (realConnectionConnection != null) {
            realConnectionConnection.cancel();
        }
    }

    @Override // okhttp3.internal.http.HttpCodec
    public B createRequestBody(Request request, long j) {
        if ("chunked".equalsIgnoreCase(request.header(com.google.common.net.HttpHeaders.TRANSFER_ENCODING))) {
            return newChunkedSink();
        }
        if (j != -1) {
            return newFixedLengthSink(j);
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    public void detachTimeout(m mVar) {
        E e2 = mVar.f2114a;
        E e3 = E.NONE;
        if (e3 == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        mVar.f2114a = e3;
        e2.clearDeadline();
        e2.clearTimeout();
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void finishRequest() {
        this.sink.flush();
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void flushRequest() {
        this.sink.flush();
    }

    public boolean isClosed() {
        return this.state == 6;
    }

    public B newChunkedSink() {
        if (this.state == 1) {
            this.state = 2;
            return new ChunkedSink();
        }
        StringBuilder sbA = a.a("state: ");
        sbA.append(this.state);
        throw new IllegalStateException(sbA.toString());
    }

    public C newChunkedSource(HttpUrl httpUrl) {
        if (this.state == 4) {
            this.state = 5;
            return new ChunkedSource(httpUrl);
        }
        StringBuilder sbA = a.a("state: ");
        sbA.append(this.state);
        throw new IllegalStateException(sbA.toString());
    }

    public B newFixedLengthSink(long j) {
        if (this.state == 1) {
            this.state = 2;
            return new FixedLengthSink(j);
        }
        StringBuilder sbA = a.a("state: ");
        sbA.append(this.state);
        throw new IllegalStateException(sbA.toString());
    }

    public C newFixedLengthSource(long j) {
        if (this.state == 4) {
            this.state = 5;
            return new FixedLengthSource(j);
        }
        StringBuilder sbA = a.a("state: ");
        sbA.append(this.state);
        throw new IllegalStateException(sbA.toString());
    }

    public C newUnknownLengthSource() {
        if (this.state != 4) {
            StringBuilder sbA = a.a("state: ");
            sbA.append(this.state);
            throw new IllegalStateException(sbA.toString());
        }
        StreamAllocation streamAllocation = this.streamAllocation;
        if (streamAllocation == null) {
            throw new IllegalStateException("streamAllocation == null");
        }
        this.state = 5;
        streamAllocation.noNewStreams();
        return new UnknownLengthSource();
    }

    @Override // okhttp3.internal.http.HttpCodec
    public ResponseBody openResponseBody(Response response) {
        StreamAllocation streamAllocation = this.streamAllocation;
        streamAllocation.eventListener.responseBodyStart(streamAllocation.call);
        String strHeader = response.header("Content-Type");
        if (!HttpHeaders.hasBody(response)) {
            return new RealResponseBody(strHeader, 0L, t.a(newFixedLengthSource(0L)));
        }
        if ("chunked".equalsIgnoreCase(response.header(com.google.common.net.HttpHeaders.TRANSFER_ENCODING))) {
            return new RealResponseBody(strHeader, -1L, t.a(newChunkedSource(response.request().url())));
        }
        long jContentLength = HttpHeaders.contentLength(response);
        return jContentLength != -1 ? new RealResponseBody(strHeader, jContentLength, t.a(newFixedLengthSource(jContentLength))) : new RealResponseBody(strHeader, -1L, t.a(newUnknownLengthSource()));
    }

    public Headers readHeaders() {
        Headers.Builder builder = new Headers.Builder();
        while (true) {
            String headerLine = readHeaderLine();
            if (headerLine.length() == 0) {
                return builder.build();
            }
            Internal.instance.addLenient(builder, headerLine);
        }
    }

    @Override // okhttp3.internal.http.HttpCodec
    public Response.Builder readResponseHeaders(boolean z) throws IOException {
        int i = this.state;
        if (i != 1 && i != 3) {
            StringBuilder sbA = a.a("state: ");
            sbA.append(this.state);
            throw new IllegalStateException(sbA.toString());
        }
        try {
            StatusLine statusLine = StatusLine.parse(readHeaderLine());
            Response.Builder builderHeaders = new Response.Builder().protocol(statusLine.protocol).code(statusLine.code).message(statusLine.message).headers(readHeaders());
            if (z && statusLine.code == 100) {
                return null;
            }
            if (statusLine.code == 100) {
                this.state = 3;
                return builderHeaders;
            }
            this.state = 4;
            return builderHeaders;
        } catch (EOFException e2) {
            StringBuilder sbA2 = a.a("unexpected end of stream on ");
            sbA2.append(this.streamAllocation);
            IOException iOException = new IOException(sbA2.toString());
            iOException.initCause(e2);
            throw iOException;
        }
    }

    public void writeRequest(Headers headers, String str) {
        if (this.state != 0) {
            StringBuilder sbA = a.a("state: ");
            sbA.append(this.state);
            throw new IllegalStateException(sbA.toString());
        }
        this.sink.a(str).a("\r\n");
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            this.sink.a(headers.name(i)).a(": ").a(headers.value(i)).a("\r\n");
        }
        this.sink.a("\r\n");
        this.state = 1;
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void writeRequestHeaders(Request request) {
        writeRequest(request.headers(), RequestLine.get(request, this.streamAllocation.connection().route().proxy().type()));
    }
}
