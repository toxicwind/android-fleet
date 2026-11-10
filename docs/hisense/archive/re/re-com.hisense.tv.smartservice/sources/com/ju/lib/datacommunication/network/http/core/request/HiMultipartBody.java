package com.ju.lib.datacommunication.network.http.core.request;

import b.a.a.a.a;
import com.google.common.base.Ascii;
import com.google.common.net.HttpHeaders;
import com.ju.lib.datacommunication.network.http.core.HiHttpHeaders;
import com.ju.lib.datacommunication.network.http.core.HiRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public class HiMultipartBody extends HiRequest.Body {
    public static final String ALTERNATIVE = "multipart/alternative";
    public static final byte[] COLONSPACE = {58, 32};
    public static final byte[] CRLF = {Ascii.CR, 10};
    public static final byte[] DASHDASH = {45, 45};
    public static final String DIGEST = "multipart/digest";
    public static final String FORM = "multipart/form-data";
    public static final String MIXED = "multipart/mixed";
    public static final String PARALLEL = "multipart/parallel";
    public static final String STREAM = "application/octet-stream";
    public final String boundary;
    public long contentLength;
    public final String contentType;
    public final List<Part> parts;

    public static final class Builder {
        public final String boundary;
        public final List<Part> parts;
        public String type;

        public Builder() {
            this(UUID.randomUUID().toString());
        }

        public Builder addFormDataPart(String str, String str2) {
            return addPart(Part.createFormData(str, str2));
        }

        public Builder addPart(HiRequest.Body body) {
            return addPart(Part.create(body));
        }

        public HiMultipartBody build() {
            if (this.parts.isEmpty()) {
                throw new IllegalStateException("Multipart body must have at least one part.");
            }
            return new HiMultipartBody(this.boundary, this.type, this.parts);
        }

        public Builder setType(String str) {
            if (str == null) {
                throw new NullPointerException("type == null");
            }
            if (!str.startsWith("multipart") && !str.equals("application/octet-stream")) {
                throw new IllegalArgumentException(a.a("multipart != ", str, "or not oct"));
            }
            this.type = str;
            return this;
        }

        public Builder(String str) {
            this.type = HiMultipartBody.MIXED;
            this.parts = new ArrayList();
            this.boundary = str;
        }

        public Builder addFormDataPart(String str, String str2, HiRequest.Body body) {
            return addPart(Part.createFormData(str, str2, body));
        }

        public Builder addPart(HiHttpHeaders hiHttpHeaders, HiRequest.Body body) {
            return addPart(Part.create(hiHttpHeaders, body));
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
        public final HiRequest.Body body;
        public final HiHttpHeaders headers;

        public Part(HiHttpHeaders hiHttpHeaders, HiRequest.Body body) {
            this.headers = hiHttpHeaders;
            this.body = body;
        }

        public static Part create(HiRequest.Body body) {
            return create(null, body);
        }

        public static Part createFormData(String str, String str2) {
            return createFormData(str, null, HiRequest.createBody((String) null, str2));
        }

        public HiRequest.Body body() {
            return this.body;
        }

        public HiHttpHeaders headers() {
            return this.headers;
        }

        public static Part create(HiHttpHeaders hiHttpHeaders, HiRequest.Body body) {
            if (body == null) {
                throw new NullPointerException("body == null");
            }
            if (hiHttpHeaders != null && hiHttpHeaders.get("Content-Type") != null) {
                throw new IllegalArgumentException("Unexpected header: Content-Type");
            }
            if (hiHttpHeaders == null || hiHttpHeaders.get("Content-Length") == null) {
                return new Part(hiHttpHeaders, body);
            }
            throw new IllegalArgumentException("Unexpected header: Content-Length");
        }

        public static Part createFormData(String str, String str2, HiRequest.Body body) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            StringBuilder sb = new StringBuilder("form-data; name=");
            HiMultipartBody.appendQuotedString(sb, str);
            if (str2 != null) {
                sb.append("; filename=");
                HiMultipartBody.appendQuotedString(sb, str2);
            }
            return create(new HiHttpHeaders.Builder().add(HttpHeaders.CONTENT_DISPOSITION, sb.toString()).build(), body);
        }
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
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r0v2 */
    private long writeOrCountBytes(OutputStream outputStream, boolean z) throws IOException {
        ?? r0;
        if (z) {
            outputStream = new ByteArrayOutputStream();
            r0 = outputStream;
        } else {
            r0 = 0;
        }
        int size = this.parts.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            Part part = this.parts.get(i);
            HiHttpHeaders hiHttpHeaders = part.headers;
            HiRequest.Body body = part.body;
            outputStream.write(DASHDASH);
            outputStream.write(this.boundary.getBytes("UTF-8"));
            outputStream.write(CRLF);
            if (hiHttpHeaders != null) {
                int size2 = hiHttpHeaders.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    outputStream.write(hiHttpHeaders.getName(i2).getBytes("UTF-8"));
                    outputStream.write(COLONSPACE);
                    outputStream.write(hiHttpHeaders.getValue(i2).getBytes("UTF-8"));
                    outputStream.write(CRLF);
                }
            }
            String strContentType = body.contentType();
            if (strContentType != null) {
                outputStream.write(("Content-Type: " + strContentType).getBytes("UTF-8"));
                outputStream.write(CRLF);
            }
            long jContentLength = body.contentLength();
            if (jContentLength != -1) {
                outputStream.write(("Content-Length: " + jContentLength).getBytes("UTF-8"));
                outputStream.write(CRLF);
            } else if (z) {
                return -1L;
            }
            outputStream.write(CRLF);
            if (z) {
                j += jContentLength;
            } else {
                body.writeTo(outputStream);
            }
            outputStream.write(CRLF);
        }
        outputStream.write(DASHDASH);
        outputStream.write(this.boundary.getBytes("UTF-8"));
        outputStream.write(DASHDASH);
        outputStream.write(CRLF);
        return z ? j + ((long) r0.size()) : j;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r0v2 */
    private long writeOrCountBytesForStream(OutputStream outputStream, boolean z) {
        ?? r0;
        if (z) {
            outputStream = new ByteArrayOutputStream();
            r0 = outputStream;
        } else {
            r0 = 0;
        }
        int size = this.parts.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            HiRequest.Body body = this.parts.get(i).body;
            long jContentLength = body.contentLength();
            if (jContentLength == -1 && z) {
                return -1L;
            }
            if (z) {
                j += jContentLength;
            } else {
                body.writeTo(outputStream);
            }
        }
        return z ? j + ((long) r0.size()) : j;
    }

    @Override // com.ju.lib.datacommunication.network.http.core.HiRequest.Body
    public long contentLength() {
        long j = this.contentLength;
        if (j != -1) {
            return j;
        }
        long jWriteOrCountBytesForStream = "application/octet-stream".equals(this.contentType) ? writeOrCountBytesForStream(null, true) : writeOrCountBytes(null, true);
        this.contentLength = jWriteOrCountBytesForStream;
        return jWriteOrCountBytesForStream;
    }

    @Override // com.ju.lib.datacommunication.network.http.core.HiRequest.Body
    public String contentType() {
        return this.contentType;
    }

    @Override // com.ju.lib.datacommunication.network.http.core.HiRequest.Body
    public void writeTo(OutputStream outputStream) throws IOException {
        if ("application/octet-stream".equals(this.contentType)) {
            writeOrCountBytesForStream(outputStream, false);
        } else {
            writeOrCountBytes(outputStream, false);
        }
    }

    public HiMultipartBody(String str, String str2, List<Part> list) {
        this.contentLength = -1L;
        this.boundary = str;
        if ("application/octet-stream".equals(str2)) {
            this.contentType = str2;
        } else {
            this.contentType = a.a(str2, "; boundary=", str);
        }
        this.parts = new ArrayList(list);
    }
}
