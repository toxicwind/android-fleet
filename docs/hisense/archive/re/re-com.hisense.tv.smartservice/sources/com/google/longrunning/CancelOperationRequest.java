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
public final class CancelOperationRequest extends GeneratedMessageLite<CancelOperationRequest, Builder> implements CancelOperationRequestOrBuilder {
    public static final CancelOperationRequest DEFAULT_INSTANCE;
    public static final int NAME_FIELD_NUMBER = 1;
    public static volatile Parser<CancelOperationRequest> PARSER;
    public String name_ = "";

    /* JADX INFO: renamed from: com.google.longrunning.CancelOperationRequest$1, reason: invalid class name */
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
        CancelOperationRequest cancelOperationRequest = new CancelOperationRequest();
        DEFAULT_INSTANCE = cancelOperationRequest;
        GeneratedMessageLite.defaultInstanceMap.put(CancelOperationRequest.class, cancelOperationRequest);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.name_ = DEFAULT_INSTANCE.getName();
    }

    public static CancelOperationRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static CancelOperationRequest parseDelimitedFrom(InputStream inputStream) {
        return (CancelOperationRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static CancelOperationRequest parseFrom(ByteBuffer byteBuffer) {
        return (CancelOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<CancelOperationRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setName(String str) {
        str.getClass();
        this.name_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNameBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.name_ = byteString.toStringUtf8();
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", new Object[]{"name_"});
            case NEW_MUTABLE_INSTANCE:
                return new CancelOperationRequest();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<CancelOperationRequest> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (CancelOperationRequest.class) {
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

    @Override // com.google.longrunning.CancelOperationRequestOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.longrunning.CancelOperationRequestOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<CancelOperationRequest, Builder> implements CancelOperationRequestOrBuilder {
        public Builder() {
            super(CancelOperationRequest.DEFAULT_INSTANCE);
        }

        public Builder clearName() {
            copyOnWrite();
            ((CancelOperationRequest) this.instance).clearName();
            return this;
        }

        @Override // com.google.longrunning.CancelOperationRequestOrBuilder
        public String getName() {
            return ((CancelOperationRequest) this.instance).getName();
        }

        @Override // com.google.longrunning.CancelOperationRequestOrBuilder
        public ByteString getNameBytes() {
            return ((CancelOperationRequest) this.instance).getNameBytes();
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((CancelOperationRequest) this.instance).setName(str);
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((CancelOperationRequest) this.instance).setNameBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(CancelOperationRequest.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(CancelOperationRequest cancelOperationRequest) {
        return DEFAULT_INSTANCE.createBuilder(cancelOperationRequest);
    }

    public static CancelOperationRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (CancelOperationRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static CancelOperationRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (CancelOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static CancelOperationRequest parseFrom(ByteString byteString) {
        return (CancelOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static CancelOperationRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (CancelOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static CancelOperationRequest parseFrom(byte[] bArr) {
        return (CancelOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static CancelOperationRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (CancelOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static CancelOperationRequest parseFrom(InputStream inputStream) {
        return (CancelOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static CancelOperationRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (CancelOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static CancelOperationRequest parseFrom(CodedInputStream codedInputStream) {
        return (CancelOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static CancelOperationRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (CancelOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
