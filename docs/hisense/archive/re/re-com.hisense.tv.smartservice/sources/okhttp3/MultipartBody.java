package okhttp3;

import b.a.a.a.a;
import com.google.common.base.Ascii;
import com.google.common.net.HttpHeaders;
import com.ju.lib.datacommunication.network.http.core.request.HiMultipartBody;
import d.g;
import d.h;
import d.j;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import okhttp3.internal.Util;

/* JADX INFO: loaded from: classes2.dex */
public final class MultipartBody extends RequestBody {
    public final j boundary;
    public long contentLength = -1;
    public final MediaType contentType;
    public final MediaType originalType;
    public final List<Part> parts;
    public static final MediaType MIXED = MediaType.get(HiMultipartBody.MIXED);
    public static final MediaType ALTERNATIVE = MediaType.get(HiMultipartBody.ALTERNATIVE);
    public static final MediaType DIGEST = MediaType.get(HiMultipartBody.DIGEST);
    public static final MediaType PARALLEL = MediaType.get(HiMultipartBody.PARALLEL);
    public static final MediaType FORM = MediaType.get(HiMultipartBody.FORM);
    public static final byte[] COLONSPACE = {58, 32};
    public static final byte[] CRLF = {Ascii.CR, 10};
    public static final byte[] DASHDASH = {45, 45};

    public static final class Builder {
        public final j boundary;
        public final List<Part> parts;
        public MediaType type;

        public Builder() {
            this(UUID.randomUUID().toString());
        }

        public Builder addFormDataPart(String str, String str2) {
            return addPart(Part.createFormData(str, str2));
        }

        public Builder addPart(RequestBody requestBody) {
            return addPart(Part.create(requestBody));
        }

        public MultipartBody build() {
            if (this.parts.isEmpty()) {
                throw new IllegalStateException("Multipart body must have at least one part.");
            }
            return new MultipartBody(this.boundary, this.type, this.parts);
        }

        public Builder setType(MediaType mediaType) {
            if (mediaType == null) {
                throw new NullPointerException("type == null");
            }
            if (!mediaType.type().equals("multipart")) {
                throw new IllegalArgumentException(a.a("multipart != ", mediaType));
            }
            this.type = mediaType;
            return this;
        }

        public Builder(String str) {
            this.type = MultipartBody.MIXED;
            this.parts = new ArrayList();
            this.boundary = j.d(str);
        }

        public Builder addFormDataPart(String str, String str2, RequestBody requestBody) {
            return addPart(Part.createFormData(str, str2, requestBody));
        }

        public Builder addPart(Headers headers, RequestBody requestBody) {
            return addPart(Part.create(headers, requestBody));
        }

        public Builder addPart(Part part) {
            if (part != null) {
                this.parts.add(part);
                return this;
            }
            throw new NullPointerException("part == null");
        }
    }

    public static final class Part {
        public final RequestBody body;
        public final Headers headers;

        public Part(Headers headers, RequestBody requestBody) {
            this.headers = headers;
            this.body = requestBody;
        }

        public static Part create(RequestBody requestBody) {
            return create(null, requestBody);
        }

        public static Part createFormData(String str, String str2) {
            return createFormData(str, null, RequestBody.create((MediaType) null, str2));
        }

        public RequestBody body() {
            return this.body;
        }

        public Headers headers() {
            return this.headers;
        }

        public static Part create(Headers headers, RequestBody requestBody) {
            if (requestBody == null) {
                throw new NullPointerException("body == null");
            }
            if (headers != null && headers.get("Content-Type") != null) {
                throw new IllegalArgumentException("Unexpected header: Content-Type");
            }
            if (headers == null || headers.get("Content-Length") == null) {
                return new Part(headers, requestBody);
            }
            throw new IllegalArgumentException("Unexpected header: Content-Length");
        }

        public static Part createFormData(String str, String str2, RequestBody requestBody) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            StringBuilder sb = new StringBuilder("form-data; name=");
            MultipartBody.appendQuotedString(sb, str);
            if (str2 != null) {
                sb.append("; filename=");
                MultipartBody.appendQuotedString(sb, str2);
            }
            return create(new Headers.Builder().addUnsafeNonAscii(HttpHeaders.CONTENT_DISPOSITION, sb.toString()).build(), requestBody);
        }
    }

    public MultipartBody(j jVar, MediaType mediaType, List<Part> list) {
        this.boundary = jVar;
        this.originalType = mediaType;
        this.contentType = MediaType.get(mediaType + "; boundary=" + jVar.j());
        this.parts = Util.immutableList(list);
    }

    public static StringBuilder appendQuotedString(StringBuilder sb, String str) {
        sb.append('\"');
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\n') {
                sb.append("%0A");
            } else if (cCharAt == '\r') {
                sb.append("%0D");
            } else if (cCharAt != '\"') {
                sb.append(cCharAt);
            } else {
                sb.append("%22");
            }
        }
        sb.append('\"');
        return sb;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private long writeOrCountBytes(h hVar, boolean z) {
        g gVar;
        if (z) {
            hVar = new g();
            gVar = hVar;
        } else {
            gVar = 0;
        }
        int size = this.parts.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            Part part = this.parts.get(i);
            Headers headers = part.headers;
            RequestBody requestBody = part.body;
            hVar.write(DASHDASH);
            hVar.a(this.boundary);
            hVar.write(CRLF);
            if (headers != null) {
                int size2 = headers.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    hVar.a(headers.name(i2)).write(COLONSPACE).a(headers.value(i2)).write(CRLF);
                }
            }
            MediaType mediaTypeContentType = requestBody.contentType();
            if (mediaTypeContentType != null) {
                hVar.a("Content-Type: ").a(mediaTypeContentType.toString()).write(CRLF);
            }
            long jContentLength = requestBody.contentLength();
            if (jContentLength != -1) {
                hVar.a("Content-Length: ").e(jContentLength).write(CRLF);
            } else if (z) {
                gVar.clear();
                return -1L;
            }
            hVar.write(CRLF);
            if (z) {
                j += jContentLength;
            } else {
                requestBody.writeTo(hVar);
            }
            hVar.write(CRLF);
        }
        hVar.write(DASHDASH);
        hVar.a(this.boundary);
        hVar.write(DASHDASH);
        hVar.write(CRLF);
        if (!z) {
            return j;
        }
        long j2 = j + gVar.f2102c;
        gVar.clear();
        return j2;
    }

    public String boundary() {
        return this.boundary.j();
    }

    @Override // okhttp3.RequestBody
    public long contentLength() {
        long j = this.contentLength;
        if (j != -1) {
            return j;
        }
        long jWriteOrCountBytes = writeOrCountBytes(null, true);
        this.contentLength = jWriteOrCountBytes;
        return jWriteOrCountBytes;
    }

    @Override // okhttp3.RequestBody
    public MediaType contentType() {
        return this.contentType;
    }

    public Part part(int i) {
        return this.parts.get(i);
    }

    public List<Part> parts() {
        return this.parts;
    }

    public int size() {
        return this.parts.size();
    }

    public MediaType type() {
        return this.originalType;
    }

    @Override // okhttp3.RequestBody
    public void writeTo(h hVar) {
        writeOrCountBytes(hVar, false);
    }
}
