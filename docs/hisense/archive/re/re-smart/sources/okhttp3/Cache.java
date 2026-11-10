package okhttp3;

import com.google.common.base.Ascii;
import com.google.firebase.storage.network.NetworkRequest;
import d.B;
import d.C;
import d.C0247f;
import d.g;
import d.h;
import d.i;
import d.j;
import d.k;
import d.l;
import d.t;
import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheRequest;
import okhttp3.internal.cache.CacheStrategy;
import okhttp3.internal.cache.DiskLruCache;
import okhttp3.internal.cache.InternalCache;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.StatusLine;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;

/* JADX INFO: loaded from: classes2.dex */
public final class Cache implements Closeable, Flushable {
    public static final int ENTRY_BODY = 1;
    public static final int ENTRY_COUNT = 2;
    public static final int ENTRY_METADATA = 0;
    public static final int VERSION = 201105;
    public final DiskLruCache cache;
    public int hitCount;
    public final InternalCache internalCache;
    public int networkCount;
    public int requestCount;
    public int writeAbortCount;
    public int writeSuccessCount;

    private final class CacheRequestImpl implements CacheRequest {
        public B body;
        public B cacheOut;
        public boolean done;
        public final DiskLruCache.Editor editor;

        public CacheRequestImpl(final DiskLruCache.Editor editor) {
            this.editor = editor;
            this.cacheOut = editor.newSink(1);
            this.body = new k(this.cacheOut) { // from class: okhttp3.Cache.CacheRequestImpl.1
                @Override // d.k, d.B, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                    synchronized (Cache.this) {
                        if (CacheRequestImpl.this.done) {
                            return;
                        }
                        CacheRequestImpl.this.done = true;
                        Cache.this.writeSuccessCount++;
                        this.delegate.close();
                        editor.commit();
                    }
                }
            };
        }

        @Override // okhttp3.internal.cache.CacheRequest
        public void abort() {
            synchronized (Cache.this) {
                if (this.done) {
                    return;
                }
                this.done = true;
                Cache.this.writeAbortCount++;
                Util.closeQuietly(this.cacheOut);
                try {
                    this.editor.abort();
                } catch (IOException unused) {
                }
            }
        }

        @Override // okhttp3.internal.cache.CacheRequest
        public B body() {
            return this.body;
        }
    }

    private static class CacheResponseBody extends ResponseBody {
        public final i bodySource;
        public final String contentLength;
        public final String contentType;
        public final DiskLruCache.Snapshot snapshot;

        public CacheResponseBody(final DiskLruCache.Snapshot snapshot, String str, String str2) {
            this.snapshot = snapshot;
            this.contentType = str;
            this.contentLength = str2;
            this.bodySource = t.a(new l(snapshot.getSource(1)) { // from class: okhttp3.Cache.CacheResponseBody.1
                @Override // d.l, d.C, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                    snapshot.close();
                    this.delegate.close();
                }
            });
        }

        @Override // okhttp3.ResponseBody
        public long contentLength() {
            try {
                if (this.contentLength != null) {
                    return Long.parseLong(this.contentLength);
                }
                return -1L;
            } catch (NumberFormatException unused) {
                return -1L;
            }
        }

        @Override // okhttp3.ResponseBody
        public MediaType contentType() {
            String str = this.contentType;
            if (str != null) {
                return MediaType.parse(str);
            }
            return null;
        }

        @Override // okhttp3.ResponseBody
        public i source() {
            return this.bodySource;
        }
    }

    public Cache(File file, long j) {
        this(file, j, FileSystem.SYSTEM);
    }

    private void abortQuietly(DiskLruCache.Editor editor) {
        if (editor != null) {
            try {
                editor.abort();
            } catch (IOException unused) {
            }
        }
    }

    public static String key(HttpUrl httpUrl) {
        return j.d(httpUrl.toString()).d().b();
    }

    public static int readInt(i iVar) throws IOException {
        try {
            long jF = iVar.f();
            String strB = iVar.b();
            if (jF >= 0 && jF <= 2147483647L && strB.isEmpty()) {
                return (int) jF;
            }
            throw new IOException("expected an int but was \"" + jF + strB + "\"");
        } catch (NumberFormatException e2) {
            throw new IOException(e2.getMessage());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.cache.close();
    }

    public void delete() {
        this.cache.delete();
    }

    public File directory() {
        return this.cache.getDirectory();
    }

    public void evictAll() {
        this.cache.evictAll();
    }

    @Override // java.io.Flushable
    public void flush() {
        this.cache.flush();
    }

    public Response get(Request request) {
        try {
            DiskLruCache.Snapshot snapshot = this.cache.get(key(request.url()));
            if (snapshot == null) {
                return null;
            }
            try {
                Entry entry = new Entry(snapshot.getSource(0));
                Response response = entry.response(snapshot);
                if (entry.matches(request, response)) {
                    return response;
                }
                Util.closeQuietly(response.body());
                return null;
            } catch (IOException unused) {
                Util.closeQuietly(snapshot);
                return null;
            }
        } catch (IOException unused2) {
        }
    }

    public synchronized int hitCount() {
        return this.hitCount;
    }

    public void initialize() {
        this.cache.initialize();
    }

    public boolean isClosed() {
        return this.cache.isClosed();
    }

    public long maxSize() {
        return this.cache.getMaxSize();
    }

    public synchronized int networkCount() {
        return this.networkCount;
    }

    public CacheRequest put(Response response) {
        DiskLruCache.Editor editorEdit;
        String strMethod = response.request().method();
        if (HttpMethod.invalidatesCache(response.request().method())) {
            try {
                remove(response.request());
            } catch (IOException unused) {
            }
            return null;
        }
        if (!strMethod.equals(NetworkRequest.GET) || HttpHeaders.hasVaryAll(response)) {
            return null;
        }
        Entry entry = new Entry(response);
        try {
            editorEdit = this.cache.edit(key(response.request().url()));
            if (editorEdit == null) {
                return null;
            }
            try {
                entry.writeTo(editorEdit);
                return new CacheRequestImpl(editorEdit);
            } catch (IOException unused2) {
                if (editorEdit != null) {
                    try {
                        editorEdit.abort();
                    } catch (IOException unused3) {
                    }
                }
                return null;
            }
        } catch (IOException unused4) {
            editorEdit = null;
        }
    }

    public void remove(Request request) {
        this.cache.remove(key(request.url()));
    }

    public synchronized int requestCount() {
        return this.requestCount;
    }

    public long size() {
        return this.cache.size();
    }

    public synchronized void trackConditionalCacheHit() {
        this.hitCount++;
    }

    public synchronized void trackResponse(CacheStrategy cacheStrategy) {
        this.requestCount++;
        if (cacheStrategy.networkRequest != null) {
            this.networkCount++;
        } else if (cacheStrategy.cacheResponse != null) {
            this.hitCount++;
        }
    }

    public void update(Response response, Response response2) {
        DiskLruCache.Editor editorEdit;
        Entry entry = new Entry(response2);
        try {
            editorEdit = ((CacheResponseBody) response.body()).snapshot.edit();
            if (editorEdit != null) {
                try {
                    entry.writeTo(editorEdit);
                    editorEdit.commit();
                } catch (IOException unused) {
                    if (editorEdit != null) {
                        try {
                            editorEdit.abort();
                        } catch (IOException unused2) {
                        }
                    }
                }
            }
        } catch (IOException unused3) {
            editorEdit = null;
        }
    }

    public Iterator<String> urls() {
        return new Iterator<String>() { // from class: okhttp3.Cache.2
            public boolean canRemove;
            public final Iterator<DiskLruCache.Snapshot> delegate;
            public String nextUrl;

            {
                this.delegate = Cache.this.cache.snapshots();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.nextUrl != null) {
                    return true;
                }
                this.canRemove = false;
                while (this.delegate.hasNext()) {
                    DiskLruCache.Snapshot next = this.delegate.next();
                    try {
                        this.nextUrl = t.a(next.getSource(0)).b();
                        next.close();
                        return true;
                    } catch (IOException unused) {
                        next.close();
                    } catch (Throwable th) {
                        next.close();
                        throw th;
                    }
                }
                return false;
            }

            @Override // java.util.Iterator
            public void remove() {
                if (!this.canRemove) {
                    throw new IllegalStateException("remove() before next()");
                }
                this.delegate.remove();
            }

            @Override // java.util.Iterator
            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                String str = this.nextUrl;
                this.nextUrl = null;
                this.canRemove = true;
                return str;
            }
        };
    }

    public synchronized int writeAbortCount() {
        return this.writeAbortCount;
    }

    public synchronized int writeSuccessCount() {
        return this.writeSuccessCount;
    }

    public Cache(File file, long j, FileSystem fileSystem) {
        this.internalCache = new InternalCache() { // from class: okhttp3.Cache.1
            @Override // okhttp3.internal.cache.InternalCache
            public Response get(Request request) {
                return Cache.this.get(request);
            }

            @Override // okhttp3.internal.cache.InternalCache
            public CacheRequest put(Response response) {
                return Cache.this.put(response);
            }

            @Override // okhttp3.internal.cache.InternalCache
            public void remove(Request request) {
                Cache.this.remove(request);
            }

            @Override // okhttp3.internal.cache.InternalCache
            public void trackConditionalCacheHit() {
                Cache.this.trackConditionalCacheHit();
            }

            @Override // okhttp3.internal.cache.InternalCache
            public void trackResponse(CacheStrategy cacheStrategy) {
                Cache.this.trackResponse(cacheStrategy);
            }

            @Override // okhttp3.internal.cache.InternalCache
            public void update(Response response, Response response2) {
                Cache.this.update(response, response2);
            }
        };
        this.cache = DiskLruCache.create(fileSystem, file, VERSION, 2, j);
    }

    private static final class Entry {
        public final int code;
        public final Handshake handshake;
        public final String message;
        public final Protocol protocol;
        public final long receivedResponseMillis;
        public final String requestMethod;
        public final Headers responseHeaders;
        public final long sentRequestMillis;
        public final String url;
        public final Headers varyHeaders;
        public static final String SENT_MILLIS = Platform.PLATFORM.getPrefix() + "-Sent-Millis";
        public static final String RECEIVED_MILLIS = Platform.PLATFORM.getPrefix() + "-Received-Millis";

        public Entry(C c2) {
            try {
                i iVarA = t.a(c2);
                this.url = iVarA.b();
                this.requestMethod = iVarA.b();
                Headers.Builder builder = new Headers.Builder();
                int i = Cache.readInt(iVarA);
                for (int i2 = 0; i2 < i; i2++) {
                    builder.addLenient(iVarA.b());
                }
                this.varyHeaders = builder.build();
                StatusLine statusLine = StatusLine.parse(iVarA.b());
                this.protocol = statusLine.protocol;
                this.code = statusLine.code;
                this.message = statusLine.message;
                Headers.Builder builder2 = new Headers.Builder();
                int i3 = Cache.readInt(iVarA);
                for (int i4 = 0; i4 < i3; i4++) {
                    builder2.addLenient(iVarA.b());
                }
                String str = builder2.get(SENT_MILLIS);
                String str2 = builder2.get(RECEIVED_MILLIS);
                builder2.removeAll(SENT_MILLIS);
                builder2.removeAll(RECEIVED_MILLIS);
                this.sentRequestMillis = str != null ? Long.parseLong(str) : 0L;
                this.receivedResponseMillis = str2 != null ? Long.parseLong(str2) : 0L;
                this.responseHeaders = builder2.build();
                if (isHttps()) {
                    String strB = iVarA.b();
                    if (strB.length() > 0) {
                        throw new IOException("expected \"\" but was \"" + strB + "\"");
                    }
                    this.handshake = Handshake.get(!iVarA.e() ? TlsVersion.forJavaName(iVarA.b()) : TlsVersion.SSL_3_0, CipherSuite.forJavaName(iVarA.b()), readCertificateList(iVarA), readCertificateList(iVarA));
                } else {
                    this.handshake = null;
                }
                c2.close();
            } catch (Throwable th) {
                c2.close();
                throw th;
            }
        }

        private boolean isHttps() {
            return this.url.startsWith("https://");
        }

        private List<Certificate> readCertificateList(i iVar) throws IOException {
            int i = Cache.readInt(iVar);
            if (i == -1) {
                return Collections.emptyList();
            }
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                ArrayList arrayList = new ArrayList(i);
                for (int i2 = 0; i2 < i; i2++) {
                    String strB = iVar.b();
                    g gVar = new g();
                    gVar.a(j.a(strB));
                    arrayList.add(certificateFactory.generateCertificate(new C0247f(gVar)));
                }
                return arrayList;
            } catch (CertificateException e2) {
                throw new IOException(e2.getMessage());
            }
        }

        private void writeCertList(h hVar, List<Certificate> list) throws IOException {
            try {
                hVar.e(list.size()).writeByte(10);
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    hVar.a(j.a(list.get(i).getEncoded()).a()).writeByte(10);
                }
            } catch (CertificateEncodingException e2) {
                throw new IOException(e2.getMessage());
            }
        }

        public boolean matches(Request request, Response response) {
            return this.url.equals(request.url().toString()) && this.requestMethod.equals(request.method()) && HttpHeaders.varyMatches(response, this.varyHeaders, request);
        }

        public Response response(DiskLruCache.Snapshot snapshot) {
            String str = this.responseHeaders.get("Content-Type");
            String str2 = this.responseHeaders.get("Content-Length");
            return new Response.Builder().request(new Request.Builder().url(this.url).method(this.requestMethod, null).headers(this.varyHeaders).build()).protocol(this.protocol).code(this.code).message(this.message).headers(this.responseHeaders).body(new CacheResponseBody(snapshot, str, str2)).handshake(this.handshake).sentRequestAtMillis(this.sentRequestMillis).receivedResponseAtMillis(this.receivedResponseMillis).build();
        }

        public void writeTo(DiskLruCache.Editor editor) throws IOException {
            h hVarA = t.a(editor.newSink(0));
            hVarA.a(this.url).writeByte(10);
            hVarA.a(this.requestMethod).writeByte(10);
            hVarA.e(this.varyHeaders.size()).writeByte(10);
            int size = this.varyHeaders.size();
            for (int i = 0; i < size; i++) {
                hVarA.a(this.varyHeaders.name(i)).a(": ").a(this.varyHeaders.value(i)).writeByte(10);
            }
            Protocol protocol = this.protocol;
            int i2 = this.code;
            String str = this.message;
            StringBuilder sb = new StringBuilder();
            sb.append(protocol == Protocol.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1");
            sb.append(Ascii.CASE_MASK);
            sb.append(i2);
            if (str != null) {
                sb.append(Ascii.CASE_MASK);
                sb.append(str);
            }
            hVarA.a(sb.toString()).writeByte(10);
            hVarA.e(this.responseHeaders.size() + 2).writeByte(10);
            int size2 = this.responseHeaders.size();
            for (int i3 = 0; i3 < size2; i3++) {
                hVarA.a(this.responseHeaders.name(i3)).a(": ").a(this.responseHeaders.value(i3)).writeByte(10);
            }
            hVarA.a(SENT_MILLIS).a(": ").e(this.sentRequestMillis).writeByte(10);
            hVarA.a(RECEIVED_MILLIS).a(": ").e(this.receivedResponseMillis).writeByte(10);
            if (isHttps()) {
                hVarA.writeByte(10);
                hVarA.a(this.handshake.cipherSuite().javaName()).writeByte(10);
                writeCertList(hVarA, this.handshake.peerCertificates());
                writeCertList(hVarA, this.handshake.localCertificates());
                hVarA.a(this.handshake.tlsVersion().javaName()).writeByte(10);
            }
            hVarA.close();
        }

        public Entry(Response response) {
            this.url = response.request().url().toString();
            this.varyHeaders = HttpHeaders.varyHeaders(response);
            this.requestMethod = response.request().method();
            this.protocol = response.protocol();
            this.code = response.code();
            this.message = response.message();
            this.responseHeaders = response.headers();
            this.handshake = response.handshake();
            this.sentRequestMillis = response.sentRequestAtMillis();
            this.receivedResponseMillis = response.receivedResponseAtMillis();
        }
    }
}
