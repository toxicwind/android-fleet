package okhttp3;

import b.a.a.a.a;
import d.g;
import d.i;
import d.j;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import okhttp3.internal.Util;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ResponseBody implements Closeable {
    public Reader reader;

    static final class BomAwareReader extends Reader {
        public final Charset charset;
        public boolean closed;
        public Reader delegate;
        public final i source;

        public BomAwareReader(i iVar, Charset charset) {
            this.source = iVar;
            this.charset = charset;
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.closed = true;
            Reader reader = this.delegate;
            if (reader != null) {
                reader.close();
            } else {
                this.source.close();
            }
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i, int i2) throws IOException {
            if (this.closed) {
                throw new IOException("Stream closed");
            }
            Reader reader = this.delegate;
            if (reader == null) {
                InputStreamReader inputStreamReader = new InputStreamReader(this.source.i(), Util.bomAwareCharset(this.source, this.charset));
                this.delegate = inputStreamReader;
                reader = inputStreamReader;
            }
            return reader.read(cArr, i, i2);
        }
    }

    private Charset charset() {
        MediaType mediaTypeContentType = contentType();
        return mediaTypeContentType != null ? mediaTypeContentType.charset(Util.UTF_8) : Util.UTF_8;
    }

    public static ResponseBody create(MediaType mediaType, String str) {
        Charset charset = Util.UTF_8;
        if (mediaType != null && (charset = mediaType.charset()) == null) {
            charset = Util.UTF_8;
            mediaType = MediaType.parse(mediaType + "; charset=utf-8");
        }
        g gVar = new g();
        gVar.a(str, 0, str.length(), charset);
        return create(mediaType, gVar.f2102c, gVar);
    }

    public final InputStream byteStream() {
        return source().i();
    }

    public final byte[] bytes() throws IOException {
        long jContentLength = contentLength();
        if (jContentLength > 2147483647L) {
            throw new IOException(a.a("Cannot buffer entire body for content length: ", jContentLength));
        }
        i iVarSource = source();
        try {
            byte[] bArrD = iVarSource.d();
            Util.closeQuietly(iVarSource);
            if (jContentLength == -1 || jContentLength == bArrD.length) {
                return bArrD;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Content-Length (");
            sb.append(jContentLength);
            sb.append(") and stream length (");
            throw new IOException(a.a(sb, bArrD.length, ") disagree"));
        } catch (Throwable th) {
            Util.closeQuietly(iVarSource);
            throw th;
        }
    }

    public final Reader charStream() {
        Reader reader = this.reader;
        if (reader != null) {
            return reader;
        }
        BomAwareReader bomAwareReader = new BomAwareReader(source(), charset());
        this.reader = bomAwareReader;
        return bomAwareReader;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Util.closeQuietly(source());
    }

    public abstract long contentLength();

    public abstract MediaType contentType();

    public abstract i source();

    public final String string() {
        i iVarSource = source();
        try {
            return iVarSource.a(Util.bomAwareCharset(iVarSource, charset()));
        } finally {
            Util.closeQuietly(iVarSource);
        }
    }

    public static ResponseBody create(MediaType mediaType, byte[] bArr) {
        g gVar = new g();
        gVar.write(bArr);
        return create(mediaType, bArr.length, gVar);
    }

    public static ResponseBody create(MediaType mediaType, j jVar) {
        g gVar = new g();
        gVar.a(jVar);
        return create(mediaType, jVar.g(), gVar);
    }

    public static ResponseBody create(final MediaType mediaType, final long j, final i iVar) {
        if (iVar != null) {
            return new ResponseBody() { // from class: okhttp3.ResponseBody.1
                @Override // okhttp3.ResponseBody
                public long contentLength() {
                    return j;
                }

                @Override // okhttp3.ResponseBody
                public MediaType contentType() {
                    return mediaType;
                }

                @Override // okhttp3.ResponseBody
                public i source() {
                    return iVar;
                }
            };
        }
        throw new NullPointerException("source == null");
    }
}
