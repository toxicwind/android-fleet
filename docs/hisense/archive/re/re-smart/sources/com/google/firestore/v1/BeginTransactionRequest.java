package com.google.firestore.v1;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class BeginTransactionRequest extends GeneratedMessageLite<BeginTransactionRequest, Builder> implements BeginTransactionRequestOrBuilder {
    public static final int DATABASE_FIELD_NUMBER = 1;
    public static final BeginTransactionRequest DEFAULT_INSTANCE;
    public static final int OPTIONS_FIELD_NUMBER = 2;
    public static volatile Parser<BeginTransactionRequest> PARSER;
    public String database_ = "";
    public TransactionOptions options_;

    /* JADX INFO: renamed from: com.google.firestore.v1.BeginTransactionRequest$1, reason: invalid class name */
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
        BeginTransactionRequest beginTransactionRequest = new BeginTransactionRequest();
        DEFAULT_INSTANCE = beginTransactionRequest;
        GeneratedMessageLite.defaultInstanceMap.put(BeginTransactionRequest.class, beginTransactionRequest);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDatabase() {
        this.database_ = DEFAULT_INSTANCE.getDatabase();
    }

    private void clearOptions() {
        this.options_ = null;
    }

    public static BeginTransactionRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeOptions(TransactionOptions transactionOptions) {
        transactionOptions.getClass();
        TransactionOptions transactionOptions2 = this.options_;
        if (transactionOptions2 == null || transactionOptions2 == TransactionOptions.DEFAULT_INSTANCE) {
            this.options_ = transactionOptions;
        } else {
            this.options_ = TransactionOptions.newBuilder(transactionOptions2).mergeFrom(transactionOptions).buildPartial();
        }
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static BeginTransactionRequest parseDelimitedFrom(InputStream inputStream) {
        return (BeginTransactionRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BeginTransactionRequest parseFrom(ByteBuffer byteBuffer) {
        return (BeginTransactionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<BeginTransactionRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDatabase(String str) {
        str.getClass();
        this.database_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDatabaseBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.database_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOptions(TransactionOptions transactionOptions) {
        transactionOptions.getClass();
        this.options_ = transactionOptions;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\t", new Object[]{"database_", "options_"});
            case NEW_MUTABLE_INSTANCE:
                return new BeginTransactionRequest();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<BeginTransactionRequest> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (BeginTransactionRequest.class) {
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

    @Override // com.google.firestore.v1.BeginTransactionRequestOrBuilder
    public String getDatabase() {
        return this.database_;
    }

    @Override // com.google.firestore.v1.BeginTransactionRequestOrBuilder
    public ByteString getDatabaseBytes() {
        return ByteString.copyFromUtf8(this.database_);
    }

    @Override // com.google.firestore.v1.BeginTransactionRequestOrBuilder
    public TransactionOptions getOptions() {
        TransactionOptions transactionOptions = this.options_;
        return transactionOptions == null ? TransactionOptions.DEFAULT_INSTANCE : transactionOptions;
    }

    @Override // com.google.firestore.v1.BeginTransactionRequestOrBuilder
    public boolean hasOptions() {
        return this.options_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BeginTransactionRequest, Builder> implements BeginTransactionRequestOrBuilder {
        public Builder() {
            super(BeginTransactionRequest.DEFAULT_INSTANCE);
        }

        public Builder clearDatabase() {
            copyOnWrite();
            ((BeginTransactionRequest) this.instance).clearDatabase();
            return this;
        }

        public Builder clearOptions() {
            copyOnWrite();
            ((BeginTransactionRequest) this.instance).options_ = null;
            return this;
        }

        @Override // com.google.firestore.v1.BeginTransactionRequestOrBuilder
        public String getDatabase() {
            return ((BeginTransactionRequest) this.instance).getDatabase();
        }

        @Override // com.google.firestore.v1.BeginTransactionRequestOrBuilder
        public ByteString getDatabaseBytes() {
            return ((BeginTransactionRequest) this.instance).getDatabaseBytes();
        }

        @Override // com.google.firestore.v1.BeginTransactionRequestOrBuilder
        public TransactionOptions getOptions() {
            return ((BeginTransactionRequest) this.instance).getOptions();
        }

        @Override // com.google.firestore.v1.BeginTransactionRequestOrBuilder
        public boolean hasOptions() {
            return ((BeginTransactionRequest) this.instance).hasOptions();
        }

        public Builder mergeOptions(TransactionOptions transactionOptions) {
            copyOnWrite();
            ((BeginTransactionRequest) this.instance).mergeOptions(transactionOptions);
            return this;
        }

        public Builder setDatabase(String str) {
            copyOnWrite();
            ((BeginTransactionRequest) this.instance).setDatabase(str);
            return this;
        }

        public Builder setDatabaseBytes(ByteString byteString) {
            copyOnWrite();
            ((BeginTransactionRequest) this.instance).setDatabaseBytes(byteString);
            return this;
        }

        public Builder setOptions(TransactionOptions transactionOptions) {
            copyOnWrite();
            ((BeginTransactionRequest) this.instance).setOptions(transactionOptions);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(BeginTransactionRequest.DEFAULT_INSTANCE);
        }

        public Builder setOptions(TransactionOptions.Builder builder) {
            copyOnWrite();
            ((BeginTransactionRequest) this.instance).setOptions(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(BeginTransactionRequest beginTransactionRequest) {
        return DEFAULT_INSTANCE.createBuilder(beginTransactionRequest);
    }

    public static BeginTransactionRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (BeginTransactionRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BeginTransactionRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (BeginTransactionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static BeginTransactionRequest parseFrom(ByteString byteString) {
        return (BeginTransactionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static BeginTransactionRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (BeginTransactionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static BeginTransactionRequest parseFrom(byte[] bArr) {
        return (BeginTransactionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static BeginTransactionRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (BeginTransactionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static BeginTransactionRequest parseFrom(InputStream inputStream) {
        return (BeginTransactionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BeginTransactionRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (BeginTransactionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BeginTransactionRequest parseFrom(CodedInputStream codedInputStream) {
        return (BeginTransactionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static BeginTransactionRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (BeginTransactionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
