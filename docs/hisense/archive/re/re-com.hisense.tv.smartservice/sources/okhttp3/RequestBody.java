package okhttp3;

import d.C;
import d.h;
import d.j;
import d.t;
import java.io.File;
import java.nio.charset.Charset;
import okhttp3.internal.Util;

/* JADX INFO: loaded from: classes2.dex */
public abstract class RequestBody {
    public static RequestBody create(MediaType mediaType, String str) {
        Charset charset = Util.UTF_8;
        if (mediaType != null && (charset = mediaType.charset()) == null) {
            charset = Util.UTF_8;
            mediaType = MediaType.parse(mediaType + "; charset=utf-8");
        }
        return create(mediaType, str.getBytes(charset));
    }

    public long contentLength() {
        return -1L;
    }

    public abstract MediaType contentType();

    public abstract void writeTo(h hVar);

    public static RequestBody create(final MediaType mediaType, final j jVar) {
        return new RequestBody() { // from class: okhttp3.RequestBody.1
            @Override // okhttp3.RequestBody
            public long contentLength() {
                return jVar.g();
            }

            @Override // okhttp3.RequestBody
            public MediaType contentType() {
                return mediaType;
            }

            @Override // okhttp3.RequestBody
            public void writeTo(h hVar) {
                hVar.a(jVar);
            }
        };
    }

    public static RequestBody create(MediaType mediaType, byte[] bArr) {
        return create(mediaType, bArr, 0, bArr.length);
    }

    public static RequestBody create(final MediaType mediaType, final byte[] bArr, final int i, final int i2) {
        if (bArr != null) {
            Util.checkOffsetAndCount(bArr.length, i, i2);
            return new RequestBody() { // from class: okhttp3.RequestBody.2
                @Override // okhttp3.RequestBody
                public long contentLength() {
                    return i2;
                }

                @Override // okhttp3.RequestBody
                public MediaType contentType() {
                    return mediaType;
                }

                @Override // okhttp3.RequestBody
                public void writeTo(h hVar) {
                    hVar.write(bArr, i, i2);
                }
            };
        }
        throw new NullPointerException("content == null");
    }

    public static RequestBody create(final MediaType mediaType, final File file) {
        if (file != null) {
            return new RequestBody() { // from class: okhttp3.RequestBody.3
                @Override // okhttp3.RequestBody
                public long contentLength() {
                    return file.length();
                }

                @Override // okhttp3.RequestBody
                public MediaType contentType() {
                    return mediaType;
                }

                @Override // okhttp3.RequestBody
                public void writeTo(h hVar) {
                    C c2 = null;
                    try {
                        c2 = t.c(file);
                        hVar.a(c2);
                    } finally {
                        Util.closeQuietly(c2);
                    }
                }
            };
        }
        throw new NullPointerException("file == null");
    }
}
