package com.google.cloud.audit;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class RequestMetadata extends GeneratedMessageLite<RequestMetadata, Builder> implements RequestMetadataOrBuilder {
    public static final int CALLER_IP_FIELD_NUMBER = 1;
    public static final int CALLER_SUPPLIED_USER_AGENT_FIELD_NUMBER = 2;
    public static final RequestMetadata DEFAULT_INSTANCE;
    public static volatile Parser<RequestMetadata> PARSER;
    public String callerIp_ = "";
    public String callerSuppliedUserAgent_ = "";

    /* JADX INFO: renamed from: com.google.cloud.audit.RequestMetadata$1, reason: invalid class name */
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
        RequestMetadata requestMetadata = new RequestMetadata();
        DEFAULT_INSTANCE = requestMetadata;
        GeneratedMessageLite.defaultInstanceMap.put(RequestMetadata.class, requestMetadata);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCallerIp() {
        this.callerIp_ = DEFAULT_INSTANCE.getCallerIp();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCallerSuppliedUserAgent() {
        this.callerSuppliedUserAgent_ = DEFAULT_INSTANCE.getCallerSuppliedUserAgent();
    }

    public static RequestMetadata getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static RequestMetadata parseDelimitedFrom(InputStream inputStream) {
        return (RequestMetadata) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RequestMetadata parseFrom(ByteBuffer byteBuffer) {
        return (RequestMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<RequestMetadata> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCallerIp(String str) {
        str.getClass();
        this.callerIp_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCallerIpBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.callerIp_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCallerSuppliedUserAgent(String str) {
        str.getClass();
        this.callerSuppliedUserAgent_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCallerSuppliedUserAgentBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.callerSuppliedUserAgent_ = byteString.toStringUtf8();
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"callerIp_", "callerSuppliedUserAgent_"});
            case NEW_MUTABLE_INSTANCE:
                return new RequestMetadata();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<RequestMetadata> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (RequestMetadata.class) {
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

    @Override // com.google.cloud.audit.RequestMetadataOrBuilder
    public String getCallerIp() {
        return this.callerIp_;
    }

    @Override // com.google.cloud.audit.RequestMetadataOrBuilder
    public ByteString getCallerIpBytes() {
        return ByteString.copyFromUtf8(this.callerIp_);
    }

    @Override // com.google.cloud.audit.RequestMetadataOrBuilder
    public String getCallerSuppliedUserAgent() {
        return this.callerSuppliedUserAgent_;
    }

    @Override // com.google.cloud.audit.RequestMetadataOrBuilder
    public ByteString getCallerSuppliedUserAgentBytes() {
        return ByteString.copyFromUtf8(this.callerSuppliedUserAgent_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<RequestMetadata, Builder> implements RequestMetadataOrBuilder {
        public Builder() {
            super(RequestMetadata.DEFAULT_INSTANCE);
        }

        public Builder clearCallerIp() {
            copyOnWrite();
            ((RequestMetadata) this.instance).clearCallerIp();
            return this;
        }

        public Builder clearCallerSuppliedUserAgent() {
            copyOnWrite();
            ((RequestMetadata) this.instance).clearCallerSuppliedUserAgent();
            return this;
        }

        @Override // com.google.cloud.audit.RequestMetadataOrBuilder
        public String getCallerIp() {
            return ((RequestMetadata) this.instance).getCallerIp();
        }

        @Override // com.google.cloud.audit.RequestMetadataOrBuilder
        public ByteString getCallerIpBytes() {
            return ((RequestMetadata) this.instance).getCallerIpBytes();
        }

        @Override // com.google.cloud.audit.RequestMetadataOrBuilder
        public String getCallerSuppliedUserAgent() {
            return ((RequestMetadata) this.instance).getCallerSuppliedUserAgent();
        }

        @Override // com.google.cloud.audit.RequestMetadataOrBuilder
        public ByteString getCallerSuppliedUserAgentBytes() {
            return ((RequestMetadata) this.instance).getCallerSuppliedUserAgentBytes();
        }

        public Builder setCallerIp(String str) {
            copyOnWrite();
            ((RequestMetadata) this.instance).setCallerIp(str);
            return this;
        }

        public Builder setCallerIpBytes(ByteString byteString) {
            copyOnWrite();
            ((RequestMetadata) this.instance).setCallerIpBytes(byteString);
            return this;
        }

        public Builder setCallerSuppliedUserAgent(String str) {
            copyOnWrite();
            ((RequestMetadata) this.instance).setCallerSuppliedUserAgent(str);
            return this;
        }

        public Builder setCallerSuppliedUserAgentBytes(ByteString byteString) {
            copyOnWrite();
            ((RequestMetadata) this.instance).setCallerSuppliedUserAgentBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(RequestMetadata.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(RequestMetadata requestMetadata) {
        return DEFAULT_INSTANCE.createBuilder(requestMetadata);
    }

    public static RequestMetadata parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (RequestMetadata) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RequestMetadata parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (RequestMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static RequestMetadata parseFrom(ByteString byteString) {
        return (RequestMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static RequestMetadata parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (RequestMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static RequestMetadata parseFrom(byte[] bArr) {
        return (RequestMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static RequestMetadata parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (RequestMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static RequestMetadata parseFrom(InputStream inputStream) {
        return (RequestMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RequestMetadata parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (RequestMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RequestMetadata parseFrom(CodedInputStream codedInputStream) {
        return (RequestMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static RequestMetadata parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (RequestMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
