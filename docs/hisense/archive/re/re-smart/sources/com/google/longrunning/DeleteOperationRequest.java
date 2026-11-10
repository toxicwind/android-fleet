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
public final class DeleteOperationRequest extends GeneratedMessageLite<DeleteOperationRequest, Builder> implements DeleteOperationRequestOrBuilder {
    public static final DeleteOperationRequest DEFAULT_INSTANCE;
    public static final int NAME_FIELD_NUMBER = 1;
    public static volatile Parser<DeleteOperationRequest> PARSER;
    public String name_ = "";

    /* JADX INFO: renamed from: com.google.longrunning.DeleteOperationRequest$1, reason: invalid class name */
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
        DeleteOperationRequest deleteOperationRequest = new DeleteOperationRequest();
        DEFAULT_INSTANCE = deleteOperationRequest;
        GeneratedMessageLite.defaultInstanceMap.put(DeleteOperationRequest.class, deleteOperationRequest);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.name_ = DEFAULT_INSTANCE.getName();
    }

    public static DeleteOperationRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static DeleteOperationRequest parseDelimitedFrom(InputStream inputStream) {
        return (DeleteOperationRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DeleteOperationRequest parseFrom(ByteBuffer byteBuffer) {
        return (DeleteOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<DeleteOperationRequest> parser() {
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
                return new DeleteOperationRequest();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<DeleteOperationRequest> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (DeleteOperationRequest.class) {
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

    @Override // com.google.longrunning.DeleteOperationRequestOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.longrunning.DeleteOperationRequestOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DeleteOperationRequest, Builder> implements DeleteOperationRequestOrBuilder {
        public Builder() {
            super(DeleteOperationRequest.DEFAULT_INSTANCE);
        }

        public Builder clearName() {
            copyOnWrite();
            ((DeleteOperationRequest) this.instance).clearName();
            return this;
        }

        @Override // com.google.longrunning.DeleteOperationRequestOrBuilder
        public String getName() {
            return ((DeleteOperationRequest) this.instance).getName();
        }

        @Override // com.google.longrunning.DeleteOperationRequestOrBuilder
        public ByteString getNameBytes() {
            return ((DeleteOperationRequest) this.instance).getNameBytes();
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((DeleteOperationRequest) this.instance).setName(str);
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((DeleteOperationRequest) this.instance).setNameBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(DeleteOperationRequest.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(DeleteOperationRequest deleteOperationRequest) {
        return DEFAULT_INSTANCE.createBuilder(deleteOperationRequest);
    }

    public static DeleteOperationRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (DeleteOperationRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DeleteOperationRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (DeleteOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static DeleteOperationRequest parseFrom(ByteString byteString) {
        return (DeleteOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static DeleteOperationRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (DeleteOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static DeleteOperationRequest parseFrom(byte[] bArr) {
        return (DeleteOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static DeleteOperationRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (DeleteOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static DeleteOperationRequest parseFrom(InputStream inputStream) {
        return (DeleteOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DeleteOperationRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (DeleteOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DeleteOperationRequest parseFrom(CodedInputStream codedInputStream) {
        return (DeleteOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static DeleteOperationRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (DeleteOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
