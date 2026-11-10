package com.ju.lib.datacommunication.network.http.core;

import android.net.Uri;
import android.text.TextUtils;
import b.a.a.a.a;
import com.google.common.net.MediaType;
import com.ju.lib.datacommunication.network.http.core.signature.ISignature;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
public class HiRequest {
    public static final AtomicInteger ID = new AtomicInteger(0);
    public final Body mBody;
    public HttpStack.Cancelable mCancelable;
    public final HiHttpHeaders mHeaders;
    public final int mId;
    public final Method mMethod;
    public final int mRetry;
    public final ISignature mSignature;
    public final Object mTag;
    public final int mTagCode;
    public final String mUrl;
    public final ReplaceHost replaceHost;

    public static abstract class Body {
        public long contentLength() {
            return -1L;
        }

        public abstract String contentType();

        public abstract void writeTo(OutputStream outputStream);
    }

    public static class Builder {
        public Body mBody;
        public int mRetry;
        public ISignature mSignature;
        public Object mTag;
        public int mTagCode;
        public String mUrl;
        public ReplaceHost replaceHost;
        public Method mMethod = Method.GET;
        public HiHttpHeaders.Builder mHeaders = new HiHttpHeaders.Builder();

        private boolean permitsRequestBody(Method method) {
            return requiresRequestBody(method) || method.equals(Method.DELETE);
        }

        private boolean requiresRequestBody(Method method) {
            return method.equals(Method.POST) || method.equals(Method.PUT) || method.equals(Method.PATCH);
        }

        public Builder addHeader(String str, String str2) {
            HiHttpHeaders.Builder builder = this.mHeaders;
            if (str2 == null) {
                str2 = "";
            }
            builder.add(str, str2);
            return this;
        }

        public HiRequest build() {
            if (this.mUrl != null) {
                return new HiRequest(this);
            }
            throw new IllegalStateException("url == null");
        }

        public Builder delete(Body body) {
            return method(Method.DELETE, body);
        }

        public Builder get() {
            return method(Method.GET, null);
        }

        public Builder head() {
            return method(Method.HEAD, null);
        }

        public Builder header(String str, String str2) {
            HiHttpHeaders.Builder builder = this.mHeaders;
            if (str2 == null) {
                str2 = "";
            }
            builder.set(str, str2);
            return this;
        }

        public Builder method(Method method, Body body) {
            if (method == null) {
                throw new NullPointerException("method == null");
            }
            if (body != null && !permitsRequestBody(method)) {
                throw new IllegalArgumentException(a.a("method ", method, " must not have a request body."));
            }
            if (body == null && requiresRequestBody(method)) {
                throw new IllegalArgumentException(a.a("method ", method, " must have a request body."));
            }
            this.mMethod = method;
            this.mBody = body;
            return this;
        }

        public Builder patch(Body body) {
            return method(Method.PATCH, body);
        }

        public Builder post(Body body) {
            return method(Method.POST, body);
        }

        public Builder put(Body body) {
            return method(Method.PUT, body);
        }

        public Builder removeHeader(String str) {
            this.mHeaders.removeAll(str);
            return this;
        }

        public Builder replaceHost(String str, String str2) {
            this.replaceHost = new ReplaceHost(str, str2);
            return this;
        }

        public Builder retry(int i) {
            this.mRetry = i;
            return this;
        }

        public Builder signature(ISignature iSignature) {
            this.mSignature = iSignature;
            return this;
        }

        public Builder tag(Object obj) {
            this.mTag = obj;
            return this;
        }

        public Builder tagCode(int i) {
            this.mTagCode = i;
            return this;
        }

        public Builder url(String str) {
            return str == null ? this : url(Uri.parse(str));
        }

        public Builder delete() {
            return delete(HiRequest.createBody((String) null, new byte[0]));
        }

        public Builder url(String str, Map<String, String> map) {
            if (str == null) {
                return this;
            }
            Uri.Builder builderBuildUpon = Uri.parse(str).buildUpon();
            if (map == null) {
                return url(builderBuildUpon.build());
            }
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builderBuildUpon.appendQueryParameter(entry.getKey(), entry.getValue() == null ? "" : entry.getValue());
            }
            return url(builderBuildUpon.build());
        }

        public Builder url(Uri uri) {
            if (uri == null) {
                return this;
            }
            this.mUrl = uri.toString();
            return this;
        }
    }

    public enum Method {
        GET,
        HEAD,
        PUT,
        DELETE,
        POST,
        PATCH
    }

    public static class ReplaceHost {
        public final String host;
        public final String ip;

        public ReplaceHost(String str, String str2) {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                this.host = str;
                this.ip = str2;
                return;
            }
            throw new IllegalArgumentException("host = " + str + ", ip = " + str2);
        }
    }

    public static void checkOffsetAndCount(long j, long j2, long j3) {
        if (j2 < 0 || j3 < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0017  */
    public static Body createBody(String str, String str2) {
        Charset charsetForName;
        if (TextUtils.isEmpty(str)) {
            charsetForName = null;
        } else {
            String charset = getCharset(str);
            if (TextUtils.isEmpty(charset) || (charsetForName = Charset.forName(charset)) == null) {
                charsetForName = null;
            }
        }
        if (charsetForName == null) {
            charsetForName = Charset.forName("UTF-8");
            if (!TextUtils.isEmpty(str)) {
                str = a.a(str, "; charset=utf-8");
            }
        }
        return createBody(str, str2.getBytes(charsetForName));
    }

    public static String getCharset(String str) {
        int iIndexOf;
        int iIndexOf2 = str.indexOf(MediaType.CHARSET_ATTRIBUTE);
        if (iIndexOf2 < 0 || (iIndexOf = str.indexOf("=", iIndexOf2 + 7)) < 0) {
            return null;
        }
        int iIndexOf3 = str.indexOf(";", iIndexOf);
        if (iIndexOf3 < 0) {
            iIndexOf3 = str.length();
        }
        return str.substring(iIndexOf + 1, iIndexOf3).trim();
    }

    public Body getBody() {
        return this.mBody;
    }

    public HttpStack.Cancelable getCancelable() {
        return this.mCancelable;
    }

    public String getHeader(String str) {
        return this.mHeaders.get(str);
    }

    public HiHttpHeaders getHeaders() {
        return this.mHeaders;
    }

    public int getId() {
        return this.mId;
    }

    public Method getMethod() {
        return this.mMethod;
    }

    public ReplaceHost getReplaceHost() {
        return this.replaceHost;
    }

    public int getRetry() {
        return this.mRetry;
    }

    public ISignature getSignature() {
        return this.mSignature;
    }

    public Object getTag() {
        return this.mTag;
    }

    public int getTagCode() {
        return this.mTagCode;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void setCancelable(HttpStack.Cancelable cancelable) {
        this.mCancelable = cancelable;
    }

    public HiRequest(Builder builder) {
        this.mId = ID.getAndIncrement();
        this.mUrl = builder.mUrl;
        this.mMethod = builder.mMethod;
        this.mHeaders = builder.mHeaders.build();
        this.mBody = builder.mBody;
        this.mTagCode = builder.mTagCode;
        this.mTag = builder.mTag;
        this.mRetry = builder.mRetry;
        this.replaceHost = builder.replaceHost;
        this.mSignature = builder.mSignature;
    }

    public static Body createBody(String str, byte[] bArr) {
        return createBody(str, bArr, 0, bArr.length);
    }

    public static Body createBody(final String str, final byte[] bArr, final int i, final int i2) {
        if (bArr != null) {
            checkOffsetAndCount(bArr.length, i, i2);
            return new Body() { // from class: com.ju.lib.datacommunication.network.http.core.HiRequest.1
                @Override // com.ju.lib.datacommunication.network.http.core.HiRequest.Body
                public long contentLength() {
                    return i2;
                }

                @Override // com.ju.lib.datacommunication.network.http.core.HiRequest.Body
                public String contentType() {
                    return str;
                }

                @Override // com.ju.lib.datacommunication.network.http.core.HiRequest.Body
                public void writeTo(OutputStream outputStream) throws IOException {
                    outputStream.write(bArr, i, i2);
                }
            };
        }
        throw new NullPointerException("content == null");
    }

    public static Body createBody(final String str, final File file) {
        return new Body() { // from class: com.ju.lib.datacommunication.network.http.core.HiRequest.2
            @Override // com.ju.lib.datacommunication.network.http.core.HiRequest.Body
            public long contentLength() {
                return file.length();
            }

            @Override // com.ju.lib.datacommunication.network.http.core.HiRequest.Body
            public String contentType() {
                return str;
            }

            @Override // com.ju.lib.datacommunication.network.http.core.HiRequest.Body
            public void writeTo(OutputStream outputStream) throws Throwable {
                FileInputStream fileInputStream = null;
                try {
                    FileInputStream fileInputStream2 = new FileInputStream(file);
                    try {
                        byte[] bArr = new byte[4096];
                        while (true) {
                            int i = fileInputStream2.read(bArr);
                            if (i == -1) {
                                try {
                                    fileInputStream2.close();
                                    return;
                                } catch (IOException unused) {
                                    return;
                                }
                            }
                            outputStream.write(bArr, 0, i);
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream = fileInputStream2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException unused2) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        };
    }
}
