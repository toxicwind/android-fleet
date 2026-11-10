package com.google.firestore.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class BeginTransactionResponse extends GeneratedMessageLite<BeginTransactionResponse, Builder> implements BeginTransactionResponseOrBuilder {
    public static final BeginTransactionResponse DEFAULT_INSTANCE;
    public static volatile Parser<BeginTransactionResponse> PARSER = null;
    public static final int TRANSACTION_FIELD_NUMBER = 1;
    public ByteString transaction_ = ByteString.EMPTY;

    /* JADX INFO: renamed from: com.google.firestore.v1.BeginTransactionResponse$1, reason: invalid class name */
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
        BeginTransactionResponse beginTransactionResponse = new BeginTransactionResponse();
        DEFAULT_INSTANCE = beginTransactionResponse;
        GeneratedMessageLite.defaultInstanceMap.put(BeginTransactionResponse.class, beginTransactionResponse);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTransaction() {
        this.transaction_ = DEFAULT_INSTANCE.getTransaction();
    }

    public static BeginTransactionResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static BeginTransactionResponse parseDelimitedFrom(InputStream inputStream) {
        return (BeginTransactionResponse) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BeginTransactionResponse parseFrom(ByteBuffer byteBuffer) {
        return (BeginTransactionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<BeginTransactionResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTransaction(ByteString byteString) {
        byteString.getClass();
        this.transaction_ = byteString;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\n", new Object[]{"transaction_"});
            case NEW_MUTABLE_INSTANCE:
                return new BeginTransactionResponse();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<BeginTransactionResponse> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (BeginTransactionResponse.class) {
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

    @Override // com.google.firestore.v1.BeginTransactionResponseOrBuilder
    public ByteString getTransaction() {
        return this.transaction_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BeginTransactionResponse, Builder> implements BeginTransactionResponseOrBuilder {
        public Builder() {
            super(BeginTransactionResponse.DEFAULT_INSTANCE);
        }

        public Builder clearTransaction() {
            copyOnWrite();
            ((BeginTransactionResponse) this.instance).clearTransaction();
            return this;
        }

        @Override // com.google.firestore.v1.BeginTransactionResponseOrBuilder
        public ByteString getTransaction() {
            return ((BeginTransactionResponse) this.instance).getTransaction();
        }

        public Builder setTransaction(ByteString byteString) {
            copyOnWrite();
            ((BeginTransactionResponse) this.instance).setTransaction(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(BeginTransactionResponse.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(BeginTransactionResponse beginTransactionResponse) {
        return DEFAULT_INSTANCE.createBuilder(beginTransactionResponse);
    }

    public static BeginTransactionResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (BeginTransactionResponse) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BeginTransactionResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (BeginTransactionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static BeginTransactionResponse parseFrom(ByteString byteString) {
        return (BeginTransactionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static BeginTransactionResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (BeginTransactionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static BeginTransactionResponse parseFrom(byte[] bArr) {
        return (BeginTransactionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static BeginTransactionResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (BeginTransactionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static BeginTransactionResponse parseFrom(InputStream inputStream) {
        return (BeginTransactionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BeginTransactionResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (BeginTransactionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BeginTransactionResponse parseFrom(CodedInputStream codedInputStream) {
        return (BeginTransactionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static BeginTransactionResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (BeginTransactionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
