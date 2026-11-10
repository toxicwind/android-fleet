package com.ju.lib.datacommunication.network.http.core;

import b.a.a.a.a;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;

/* JADX INFO: loaded from: classes2.dex */
public class HiResponse {
    public final Body mBody;
    public final int mCode;
    public final HiHttpHeaders mHeaders;
    public final String mMessage;
    public final HiRequest mRequest;
    public final Trace mTrace;

    public static abstract class Body {
        public abstract byte[] bytes();

        public abstract long contentLength();

        public abstract InputStream getInputStream();

        public abstract String string();
    }

    public static class Builder {
        public Body mBody;
        public int mCode = -1;
        public HiHttpHeaders.Builder mHeaders = new HiHttpHeaders.Builder();
        public String mMessage;
        public HiRequest mRequest;
        public Trace mTrace;

        public Builder addHeader(String str, String str2) {
            this.mHeaders.add(str, str2);
            return this;
        }

        public Builder body(Body body) {
            this.mBody = body;
            return this;
        }

        public HiResponse build() {
            if (this.mCode >= 0) {
                return new HiResponse(this);
            }
            StringBuilder sbA = a.a("code < 0: ");
            sbA.append(this.mCode);
            throw new IllegalStateException(sbA.toString());
        }

        public Builder code(int i) {
            this.mCode = i;
            return this;
        }

        public Builder header(String str, String str2) {
            this.mHeaders.set(str, str2);
            return this;
        }

        public Builder message(String str) {
            this.mMessage = str;
            return this;
        }

        public Builder removeHeader(String str) {
            this.mHeaders.removeAll(str);
            return this;
        }

        public Builder request(HiRequest hiRequest) {
            this.mRequest = hiRequest;
            return this;
        }

        public Builder trace(Trace trace) {
            this.mTrace = trace;
            return this;
        }
    }

    public static final class Trace {
        public InetSocketAddress mAddress;
        public int mCode;
        public IOException mException;
        public boolean mIsSuccess;
        public Trace mNext;
        public HiHttpHeaders mRequestHeaders;
        public long mRequestTimestamp;
        public HiHttpHeaders mResponseHeaders;
        public long mResponseTimestamp;
        public String mUrl;

        public InetSocketAddress getAddress() {
            return this.mAddress;
        }

        public int getCode() {
            return this.mCode;
        }

        public IOException getException() {
            return this.mException;
        }

        public Trace getNext() {
            return this.mNext;
        }

        public HiHttpHeaders getRequestHeaders() {
            return this.mRequestHeaders;
        }

        public long getRequestTimestamp() {
            return this.mRequestTimestamp;
        }

        public HiHttpHeaders getResponseHeaders() {
            return this.mResponseHeaders;
        }

        public long getResponseTimestamp() {
            return this.mResponseTimestamp;
        }

        public String getUrl() {
            return this.mUrl;
        }

        public boolean isSuccess() {
            return this.mIsSuccess;
        }

        public void setAddress(InetSocketAddress inetSocketAddress) {
            this.mAddress = inetSocketAddress;
        }

        public void setCode(int i) {
            this.mCode = i;
        }

        public void setException(IOException iOException) {
            this.mException = iOException;
        }

        public void setNext(Trace trace) {
            this.mNext = trace;
        }

        public void setRequestHeaders(HiHttpHeaders hiHttpHeaders) {
            this.mRequestHeaders = hiHttpHeaders;
        }

        public void setRequestTimestamp(long j) {
            this.mRequestTimestamp = j;
        }

        public void setResponseHeaders(HiHttpHeaders hiHttpHeaders) {
            this.mResponseHeaders = hiHttpHeaders;
        }

        public void setResponseTimestamp(long j) {
            this.mResponseTimestamp = j;
        }

        public void setSuccess(boolean z) {
            this.mIsSuccess = z;
        }

        public void setUrl(String str) {
            this.mUrl = str;
        }
    }

    public Body getBody() {
        return this.mBody;
    }

    public int getCode() {
        return this.mCode;
    }

    public HiHttpHeaders getHeaders() {
        return this.mHeaders;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public HiRequest getRequest() {
        return this.mRequest;
    }

    public Trace getTrace() {
        return this.mTrace;
    }

    public boolean isSuccessful() {
        int i = this.mCode;
        return i >= 200 && i < 300;
    }

    public HiResponse(Builder builder) {
        this.mRequest = builder.mRequest;
        this.mCode = builder.mCode;
        this.mMessage = builder.mMessage;
        this.mHeaders = builder.mHeaders.build();
        this.mBody = builder.mBody;
        this.mTrace = builder.mTrace;
    }
}
