package com.google.rpc;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public final class RequestInfo extends GeneratedMessageLite<RequestInfo, Builder> implements RequestInfoOrBuilder {
    public static final RequestInfo DEFAULT_INSTANCE;
    public static volatile Parser<RequestInfo> PARSER = null;
    public static final int REQUEST_ID_FIELD_NUMBER = 1;
    public static final int SERVING_DATA_FIELD_NUMBER = 2;
    public String requestId_ = "";
    public String servingData_ = "";

    /* JADX INFO: renamed from: com.google.rpc.RequestInfo$1, reason: invalid class name */
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
        RequestInfo requestInfo = new RequestInfo();
        DEFAULT_INSTANCE = requestInfo;
        GeneratedMessageLite.defaultInstanceMap.put(RequestInfo.class, requestInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearRequestId() {
        this.requestId_ = DEFAULT_INSTANCE.getRequestId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearServingData() {
        this.servingData_ = DEFAULT_INSTANCE.getServingData();
    }

    public static RequestInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static RequestInfo parseDelimitedFrom(InputStream inputStream) {
        return (RequestInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RequestInfo parseFrom(ByteBuffer byteBuffer) {
        return (RequestInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<RequestInfo> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRequestId(String str) {
        str.getClass();
        this.requestId_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRequestIdBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.requestId_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setServingData(String str) {
        str.getClass();
        this.servingData_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setServingDataBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.servingData_ = byteString.toStringUtf8();
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"requestId_", "servingData_"});
            case NEW_MUTABLE_INSTANCE:
                return new RequestInfo();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<RequestInfo> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (RequestInfo.class) {
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

    @Override // com.google.rpc.RequestInfoOrBuilder
    public String getRequestId() {
        return this.requestId_;
    }

    @Override // com.google.rpc.RequestInfoOrBuilder
    public ByteString getRequestIdBytes() {
        return ByteString.copyFromUtf8(this.requestId_);
    }

    @Override // com.google.rpc.RequestInfoOrBuilder
    public String getServingData() {
        return this.servingData_;
    }

    @Override // com.google.rpc.RequestInfoOrBuilder
    public ByteString getServingDataBytes() {
        return ByteString.copyFromUtf8(this.servingData_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<RequestInfo, Builder> implements RequestInfoOrBuilder {
        public Builder() {
            super(RequestInfo.DEFAULT_INSTANCE);
        }

        public Builder clearRequestId() {
            copyOnWrite();
            ((RequestInfo) this.instance).clearRequestId();
            return this;
        }

        public Builder clearServingData() {
            copyOnWrite();
            ((RequestInfo) this.instance).clearServingData();
            return this;
        }

        @Override // com.google.rpc.RequestInfoOrBuilder
        public String getRequestId() {
            return ((RequestInfo) this.instance).getRequestId();
        }

        @Override // com.google.rpc.RequestInfoOrBuilder
        public ByteString getRequestIdBytes() {
            return ((RequestInfo) this.instance).getRequestIdBytes();
        }

        @Override // com.google.rpc.RequestInfoOrBuilder
        public String getServingData() {
            return ((RequestInfo) this.instance).getServingData();
        }

        @Override // com.google.rpc.RequestInfoOrBuilder
        public ByteString getServingDataBytes() {
            return ((RequestInfo) this.instance).getServingDataBytes();
        }

        public Builder setRequestId(String str) {
            copyOnWrite();
            ((RequestInfo) this.instance).setRequestId(str);
            return this;
        }

        public Builder setRequestIdBytes(ByteString byteString) {
            copyOnWrite();
            ((RequestInfo) this.instance).setRequestIdBytes(byteString);
            return this;
        }

        public Builder setServingData(String str) {
            copyOnWrite();
            ((RequestInfo) this.instance).setServingData(str);
            return this;
        }

        public Builder setServingDataBytes(ByteString byteString) {
            copyOnWrite();
            ((RequestInfo) this.instance).setServingDataBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(RequestInfo.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(RequestInfo requestInfo) {
        return DEFAULT_INSTANCE.createBuilder(requestInfo);
    }

    public static RequestInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (RequestInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RequestInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (RequestInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static RequestInfo parseFrom(ByteString byteString) {
        return (RequestInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static RequestInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (RequestInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static RequestInfo parseFrom(byte[] bArr) {
        return (RequestInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static RequestInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (RequestInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static RequestInfo parseFrom(InputStream inputStream) {
        return (RequestInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RequestInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (RequestInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RequestInfo parseFrom(CodedInputStream codedInputStream) {
        return (RequestInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static RequestInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (RequestInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
