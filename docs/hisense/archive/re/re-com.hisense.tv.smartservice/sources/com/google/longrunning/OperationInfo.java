package com.google.longrunning;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class OperationInfo extends GeneratedMessageLite<OperationInfo, Builder> implements OperationInfoOrBuilder {
    public static final OperationInfo DEFAULT_INSTANCE;
    public static final int METADATA_TYPE_FIELD_NUMBER = 2;
    public static volatile Parser<OperationInfo> PARSER = null;
    public static final int RESPONSE_TYPE_FIELD_NUMBER = 1;
    public String responseType_ = "";
    public String metadataType_ = "";

    /* JADX INFO: renamed from: com.google.longrunning.OperationInfo$1, reason: invalid class name */
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
        OperationInfo operationInfo = new OperationInfo();
        DEFAULT_INSTANCE = operationInfo;
        GeneratedMessageLite.defaultInstanceMap.put(OperationInfo.class, operationInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMetadataType() {
        this.metadataType_ = DEFAULT_INSTANCE.getMetadataType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearResponseType() {
        this.responseType_ = DEFAULT_INSTANCE.getResponseType();
    }

    public static OperationInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static OperationInfo parseDelimitedFrom(InputStream inputStream) {
        return (OperationInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static OperationInfo parseFrom(ByteBuffer byteBuffer) {
        return (OperationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<OperationInfo> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMetadataType(String str) {
        str.getClass();
        this.metadataType_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMetadataTypeBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.metadataType_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResponseType(String str) {
        str.getClass();
        this.responseType_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResponseTypeBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.responseType_ = byteString.toStringUtf8();
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"responseType_", "metadataType_"});
            case NEW_MUTABLE_INSTANCE:
                return new OperationInfo();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<OperationInfo> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (OperationInfo.class) {
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

    @Override // com.google.longrunning.OperationInfoOrBuilder
    public String getMetadataType() {
        return this.metadataType_;
    }

    @Override // com.google.longrunning.OperationInfoOrBuilder
    public ByteString getMetadataTypeBytes() {
        return ByteString.copyFromUtf8(this.metadataType_);
    }

    @Override // com.google.longrunning.OperationInfoOrBuilder
    public String getResponseType() {
        return this.responseType_;
    }

    @Override // com.google.longrunning.OperationInfoOrBuilder
    public ByteString getResponseTypeBytes() {
        return ByteString.copyFromUtf8(this.responseType_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<OperationInfo, Builder> implements OperationInfoOrBuilder {
        public Builder() {
            super(OperationInfo.DEFAULT_INSTANCE);
        }

        public Builder clearMetadataType() {
            copyOnWrite();
            ((OperationInfo) this.instance).clearMetadataType();
            return this;
        }

        public Builder clearResponseType() {
            copyOnWrite();
            ((OperationInfo) this.instance).clearResponseType();
            return this;
        }

        @Override // com.google.longrunning.OperationInfoOrBuilder
        public String getMetadataType() {
            return ((OperationInfo) this.instance).getMetadataType();
        }

        @Override // com.google.longrunning.OperationInfoOrBuilder
        public ByteString getMetadataTypeBytes() {
            return ((OperationInfo) this.instance).getMetadataTypeBytes();
        }

        @Override // com.google.longrunning.OperationInfoOrBuilder
        public String getResponseType() {
            return ((OperationInfo) this.instance).getResponseType();
        }

        @Override // com.google.longrunning.OperationInfoOrBuilder
        public ByteString getResponseTypeBytes() {
            return ((OperationInfo) this.instance).getResponseTypeBytes();
        }

        public Builder setMetadataType(String str) {
            copyOnWrite();
            ((OperationInfo) this.instance).setMetadataType(str);
            return this;
        }

        public Builder setMetadataTypeBytes(ByteString byteString) {
            copyOnWrite();
            ((OperationInfo) this.instance).setMetadataTypeBytes(byteString);
            return this;
        }

        public Builder setResponseType(String str) {
            copyOnWrite();
            ((OperationInfo) this.instance).setResponseType(str);
            return this;
        }

        public Builder setResponseTypeBytes(ByteString byteString) {
            copyOnWrite();
            ((OperationInfo) this.instance).setResponseTypeBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(OperationInfo.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(OperationInfo operationInfo) {
        return DEFAULT_INSTANCE.createBuilder(operationInfo);
    }

    public static OperationInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (OperationInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static OperationInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (OperationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static OperationInfo parseFrom(ByteString byteString) {
        return (OperationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static OperationInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (OperationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static OperationInfo parseFrom(byte[] bArr) {
        return (OperationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static OperationInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (OperationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static OperationInfo parseFrom(InputStream inputStream) {
        return (OperationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static OperationInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (OperationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static OperationInfo parseFrom(CodedInputStream codedInputStream) {
        return (OperationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static OperationInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (OperationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
