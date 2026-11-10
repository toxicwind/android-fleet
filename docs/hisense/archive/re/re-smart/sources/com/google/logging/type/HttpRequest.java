package com.google.logging.type;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Duration;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class HttpRequest extends GeneratedMessageLite<HttpRequest, Builder> implements HttpRequestOrBuilder {
    public static final int CACHE_FILL_BYTES_FIELD_NUMBER = 12;
    public static final int CACHE_HIT_FIELD_NUMBER = 9;
    public static final int CACHE_LOOKUP_FIELD_NUMBER = 11;
    public static final int CACHE_VALIDATED_WITH_ORIGIN_SERVER_FIELD_NUMBER = 10;
    public static final HttpRequest DEFAULT_INSTANCE;
    public static final int LATENCY_FIELD_NUMBER = 14;
    public static volatile Parser<HttpRequest> PARSER = null;
    public static final int PROTOCOL_FIELD_NUMBER = 15;
    public static final int REFERER_FIELD_NUMBER = 8;
    public static final int REMOTE_IP_FIELD_NUMBER = 7;
    public static final int REQUEST_METHOD_FIELD_NUMBER = 1;
    public static final int REQUEST_SIZE_FIELD_NUMBER = 3;
    public static final int REQUEST_URL_FIELD_NUMBER = 2;
    public static final int RESPONSE_SIZE_FIELD_NUMBER = 5;
    public static final int SERVER_IP_FIELD_NUMBER = 13;
    public static final int STATUS_FIELD_NUMBER = 4;
    public static final int USER_AGENT_FIELD_NUMBER = 6;
    public long cacheFillBytes_;
    public boolean cacheHit_;
    public boolean cacheLookup_;
    public boolean cacheValidatedWithOriginServer_;
    public Duration latency_;
    public long requestSize_;
    public long responseSize_;
    public int status_;
    public String requestMethod_ = "";
    public String requestUrl_ = "";
    public String userAgent_ = "";
    public String remoteIp_ = "";
    public String serverIp_ = "";
    public String referer_ = "";
    public String protocol_ = "";

    /* JADX INFO: renamed from: com.google.logging.type.HttpRequest$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = new int[GeneratedMessageLite.MethodToInvoke.values().length];

        static {
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    static {
        HttpRequest httpRequest = new HttpRequest();
        DEFAULT_INSTANCE = httpRequest;
        GeneratedMessageLite.defaultInstanceMap.put(HttpRequest.class, httpRequest);
    }

    private void clearCacheFillBytes() {
        this.cacheFillBytes_ = 0L;
    }

    private void clearCacheHit() {
        this.cacheHit_ = false;
    }

    private void clearCacheLookup() {
        this.cacheLookup_ = false;
    }

    private void clearCacheValidatedWithOriginServer() {
        this.cacheValidatedWithOriginServer_ = false;
    }

    private void clearLatency() {
        this.latency_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearProtocol() {
        this.protocol_ = DEFAULT_INSTANCE.getProtocol();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearReferer() {
        this.referer_ = DEFAULT_INSTANCE.getReferer();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearRemoteIp() {
        this.remoteIp_ = DEFAULT_INSTANCE.getRemoteIp();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearRequestMethod() {
        this.requestMethod_ = DEFAULT_INSTANCE.getRequestMethod();
    }

    private void clearRequestSize() {
        this.requestSize_ = 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearRequestUrl() {
        this.requestUrl_ = DEFAULT_INSTANCE.getRequestUrl();
    }

    private void clearResponseSize() {
        this.responseSize_ = 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearServerIp() {
        this.serverIp_ = DEFAULT_INSTANCE.getServerIp();
    }

    private void clearStatus() {
        this.status_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearUserAgent() {
        this.userAgent_ = DEFAULT_INSTANCE.getUserAgent();
    }

    public static HttpRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeLatency(Duration duration) {
        duration.getClass();
        Duration duration2 = this.latency_;
        if (duration2 == null || duration2 == Duration.DEFAULT_INSTANCE) {
            this.latency_ = duration;
        } else {
            this.latency_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
        }
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static HttpRequest parseDelimitedFrom(InputStream inputStream) {
        return (HttpRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static HttpRequest parseFrom(ByteBuffer byteBuffer) {
        return (HttpRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<HttpRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void setCacheFillBytes(long j) {
        this.cacheFillBytes_ = j;
    }

    private void setCacheHit(boolean z) {
        this.cacheHit_ = z;
    }

    private void setCacheLookup(boolean z) {
        this.cacheLookup_ = z;
    }

    private void setCacheValidatedWithOriginServer(boolean z) {
        this.cacheValidatedWithOriginServer_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLatency(Duration duration) {
        duration.getClass();
        this.latency_ = duration;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProtocol(String str) {
        str.getClass();
        this.protocol_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProtocolBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.protocol_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReferer(String str) {
        str.getClass();
        this.referer_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRefererBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.referer_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRemoteIp(String str) {
        str.getClass();
        this.remoteIp_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRemoteIpBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.remoteIp_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRequestMethod(String str) {
        str.getClass();
        this.requestMethod_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRequestMethodBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.requestMethod_ = byteString.toStringUtf8();
    }

    private void setRequestSize(long j) {
        this.requestSize_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRequestUrl(String str) {
        str.getClass();
        this.requestUrl_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRequestUrlBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.requestUrl_ = byteString.toStringUtf8();
    }

    private void setResponseSize(long j) {
        this.responseSize_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setServerIp(String str) {
        str.getClass();
        this.serverIp_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setServerIpBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.serverIp_ = byteString.toStringUtf8();
    }

    private void setStatus(int i) {
        this.status_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUserAgent(String str) {
        str.getClass();
        this.userAgent_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUserAgentBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.userAgent_ = byteString.toStringUtf8();
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        AnonymousClass1 anonymousClass1 = null;
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u000f\u0000\u0000\u0001\u000f\u000f\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003\u0002\u0004\u0004\u0005\u0002\u0006Ȉ\u0007Ȉ\bȈ\t\u0007\n\u0007\u000b\u0007\f\u0002\rȈ\u000e\t\u000fȈ", new Object[]{"requestMethod_", "requestUrl_", "requestSize_", "status_", "responseSize_", "userAgent_", "remoteIp_", "referer_", "cacheHit_", "cacheValidatedWithOriginServer_", "cacheLookup_", "cacheFillBytes_", "serverIp_", "latency_", "protocol_"});
            case NEW_MUTABLE_INSTANCE:
                return new HttpRequest();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<HttpRequest> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (HttpRequest.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                }
                return defaultInstanceBasedParser;
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public long getCacheFillBytes() {
        return this.cacheFillBytes_;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public boolean getCacheHit() {
        return this.cacheHit_;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public boolean getCacheLookup() {
        return this.cacheLookup_;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public boolean getCacheValidatedWithOriginServer() {
        return this.cacheValidatedWithOriginServer_;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public Duration getLatency() {
        Duration duration = this.latency_;
        return duration == null ? Duration.DEFAULT_INSTANCE : duration;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public String getProtocol() {
        return this.protocol_;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public ByteString getProtocolBytes() {
        return ByteString.copyFromUtf8(this.protocol_);
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public String getReferer() {
        return this.referer_;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public ByteString getRefererBytes() {
        return ByteString.copyFromUtf8(this.referer_);
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public String getRemoteIp() {
        return this.remoteIp_;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public ByteString getRemoteIpBytes() {
        return ByteString.copyFromUtf8(this.remoteIp_);
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public String getRequestMethod() {
        return this.requestMethod_;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public ByteString getRequestMethodBytes() {
        return ByteString.copyFromUtf8(this.requestMethod_);
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public long getRequestSize() {
        return this.requestSize_;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public String getRequestUrl() {
        return this.requestUrl_;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public ByteString getRequestUrlBytes() {
        return ByteString.copyFromUtf8(this.requestUrl_);
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public long getResponseSize() {
        return this.responseSize_;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public String getServerIp() {
        return this.serverIp_;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public ByteString getServerIpBytes() {
        return ByteString.copyFromUtf8(this.serverIp_);
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public int getStatus() {
        return this.status_;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public String getUserAgent() {
        return this.userAgent_;
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public ByteString getUserAgentBytes() {
        return ByteString.copyFromUtf8(this.userAgent_);
    }

    @Override // com.google.logging.type.HttpRequestOrBuilder
    public boolean hasLatency() {
        return this.latency_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<HttpRequest, Builder> implements HttpRequestOrBuilder {
        public Builder() {
            super(HttpRequest.DEFAULT_INSTANCE);
        }

        public Builder clearCacheFillBytes() {
            copyOnWrite();
            ((HttpRequest) this.instance).cacheFillBytes_ = 0L;
            return this;
        }

        public Builder clearCacheHit() {
            copyOnWrite();
            ((HttpRequest) this.instance).cacheHit_ = false;
            return this;
        }

        public Builder clearCacheLookup() {
            copyOnWrite();
            ((HttpRequest) this.instance).cacheLookup_ = false;
            return this;
        }

        public Builder clearCacheValidatedWithOriginServer() {
            copyOnWrite();
            ((HttpRequest) this.instance).cacheValidatedWithOriginServer_ = false;
            return this;
        }

        public Builder clearLatency() {
            copyOnWrite();
            ((HttpRequest) this.instance).latency_ = null;
            return this;
        }

        public Builder clearProtocol() {
            copyOnWrite();
            ((HttpRequest) this.instance).clearProtocol();
            return this;
        }

        public Builder clearReferer() {
            copyOnWrite();
            ((HttpRequest) this.instance).clearReferer();
            return this;
        }

        public Builder clearRemoteIp() {
            copyOnWrite();
            ((HttpRequest) this.instance).clearRemoteIp();
            return this;
        }

        public Builder clearRequestMethod() {
            copyOnWrite();
            ((HttpRequest) this.instance).clearRequestMethod();
            return this;
        }

        public Builder clearRequestSize() {
            copyOnWrite();
            ((HttpRequest) this.instance).requestSize_ = 0L;
            return this;
        }

        public Builder clearRequestUrl() {
            copyOnWrite();
            ((HttpRequest) this.instance).clearRequestUrl();
            return this;
        }

        public Builder clearResponseSize() {
            copyOnWrite();
            ((HttpRequest) this.instance).responseSize_ = 0L;
            return this;
        }

        public Builder clearServerIp() {
            copyOnWrite();
            ((HttpRequest) this.instance).clearServerIp();
            return this;
        }

        public Builder clearStatus() {
            copyOnWrite();
            ((HttpRequest) this.instance).status_ = 0;
            return this;
        }

        public Builder clearUserAgent() {
            copyOnWrite();
            ((HttpRequest) this.instance).clearUserAgent();
            return this;
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public long getCacheFillBytes() {
            return ((HttpRequest) this.instance).getCacheFillBytes();
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public boolean getCacheHit() {
            return ((HttpRequest) this.instance).getCacheHit();
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public boolean getCacheLookup() {
            return ((HttpRequest) this.instance).getCacheLookup();
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public boolean getCacheValidatedWithOriginServer() {
            return ((HttpRequest) this.instance).getCacheValidatedWithOriginServer();
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public Duration getLatency() {
            return ((HttpRequest) this.instance).getLatency();
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public String getProtocol() {
            return ((HttpRequest) this.instance).getProtocol();
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public ByteString getProtocolBytes() {
            return ((HttpRequest) this.instance).getProtocolBytes();
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public String getReferer() {
            return ((HttpRequest) this.instance).getReferer();
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public ByteString getRefererBytes() {
            return ((HttpRequest) this.instance).getRefererBytes();
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public String getRemoteIp() {
            return ((HttpRequest) this.instance).getRemoteIp();
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public ByteString getRemoteIpBytes() {
            return ((HttpRequest) this.instance).getRemoteIpBytes();
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public String getRequestMethod() {
            return ((HttpRequest) this.instance).getRequestMethod();
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public ByteString getRequestMethodBytes() {
            return ((HttpRequest) this.instance).getRequestMethodBytes();
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public long getRequestSize() {
            return ((HttpRequest) this.instance).getRequestSize();
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public String getRequestUrl() {
            return ((HttpRequest) this.instance).getRequestUrl();
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public ByteString getRequestUrlBytes() {
            return ((HttpRequest) this.instance).getRequestUrlBytes();
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public long getResponseSize() {
            return ((HttpRequest) this.instance).getResponseSize();
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public String getServerIp() {
            return ((HttpRequest) this.instance).getServerIp();
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public ByteString getServerIpBytes() {
            return ((HttpRequest) this.instance).getServerIpBytes();
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public int getStatus() {
            return ((HttpRequest) this.instance).getStatus();
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public String getUserAgent() {
            return ((HttpRequest) this.instance).getUserAgent();
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public ByteString getUserAgentBytes() {
            return ((HttpRequest) this.instance).getUserAgentBytes();
        }

        @Override // com.google.logging.type.HttpRequestOrBuilder
        public boolean hasLatency() {
            return ((HttpRequest) this.instance).hasLatency();
        }

        public Builder mergeLatency(Duration duration) {
            copyOnWrite();
            ((HttpRequest) this.instance).mergeLatency(duration);
            return this;
        }

        public Builder setCacheFillBytes(long j) {
            copyOnWrite();
            ((HttpRequest) this.instance).cacheFillBytes_ = j;
            return this;
        }

        public Builder setCacheHit(boolean z) {
            copyOnWrite();
            ((HttpRequest) this.instance).cacheHit_ = z;
            return this;
        }

        public Builder setCacheLookup(boolean z) {
            copyOnWrite();
            ((HttpRequest) this.instance).cacheLookup_ = z;
            return this;
        }

        public Builder setCacheValidatedWithOriginServer(boolean z) {
            copyOnWrite();
            ((HttpRequest) this.instance).cacheValidatedWithOriginServer_ = z;
            return this;
        }

        public Builder setLatency(Duration duration) {
            copyOnWrite();
            ((HttpRequest) this.instance).setLatency(duration);
            return this;
        }

        public Builder setProtocol(String str) {
            copyOnWrite();
            ((HttpRequest) this.instance).setProtocol(str);
            return this;
        }

        public Builder setProtocolBytes(ByteString byteString) {
            copyOnWrite();
            ((HttpRequest) this.instance).setProtocolBytes(byteString);
            return this;
        }

        public Builder setReferer(String str) {
            copyOnWrite();
            ((HttpRequest) this.instance).setReferer(str);
            return this;
        }

        public Builder setRefererBytes(ByteString byteString) {
            copyOnWrite();
            ((HttpRequest) this.instance).setRefererBytes(byteString);
            return this;
        }

        public Builder setRemoteIp(String str) {
            copyOnWrite();
            ((HttpRequest) this.instance).setRemoteIp(str);
            return this;
        }

        public Builder setRemoteIpBytes(ByteString byteString) {
            copyOnWrite();
            ((HttpRequest) this.instance).setRemoteIpBytes(byteString);
            return this;
        }

        public Builder setRequestMethod(String str) {
            copyOnWrite();
            ((HttpRequest) this.instance).setRequestMethod(str);
            return this;
        }

        public Builder setRequestMethodBytes(ByteString byteString) {
            copyOnWrite();
            ((HttpRequest) this.instance).setRequestMethodBytes(byteString);
            return this;
        }

        public Builder setRequestSize(long j) {
            copyOnWrite();
            ((HttpRequest) this.instance).requestSize_ = j;
            return this;
        }

        public Builder setRequestUrl(String str) {
            copyOnWrite();
            ((HttpRequest) this.instance).setRequestUrl(str);
            return this;
        }

        public Builder setRequestUrlBytes(ByteString byteString) {
            copyOnWrite();
            ((HttpRequest) this.instance).setRequestUrlBytes(byteString);
            return this;
        }

        public Builder setResponseSize(long j) {
            copyOnWrite();
            ((HttpRequest) this.instance).responseSize_ = j;
            return this;
        }

        public Builder setServerIp(String str) {
            copyOnWrite();
            ((HttpRequest) this.instance).setServerIp(str);
            return this;
        }

        public Builder setServerIpBytes(ByteString byteString) {
            copyOnWrite();
            ((HttpRequest) this.instance).setServerIpBytes(byteString);
            return this;
        }

        public Builder setStatus(int i) {
            copyOnWrite();
            ((HttpRequest) this.instance).status_ = i;
            return this;
        }

        public Builder setUserAgent(String str) {
            copyOnWrite();
            ((HttpRequest) this.instance).setUserAgent(str);
            return this;
        }

        public Builder setUserAgentBytes(ByteString byteString) {
            copyOnWrite();
            ((HttpRequest) this.instance).setUserAgentBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(HttpRequest.DEFAULT_INSTANCE);
        }

        public Builder setLatency(Duration.Builder builder) {
            copyOnWrite();
            ((HttpRequest) this.instance).setLatency(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(HttpRequest httpRequest) {
        return DEFAULT_INSTANCE.createBuilder(httpRequest);
    }

    public static HttpRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (HttpRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static HttpRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (HttpRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static HttpRequest parseFrom(ByteString byteString) {
        return (HttpRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static HttpRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (HttpRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static HttpRequest parseFrom(byte[] bArr) {
        return (HttpRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static HttpRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (HttpRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static HttpRequest parseFrom(InputStream inputStream) {
        return (HttpRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static HttpRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (HttpRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static HttpRequest parseFrom(CodedInputStream codedInputStream) {
        return (HttpRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static HttpRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (HttpRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
