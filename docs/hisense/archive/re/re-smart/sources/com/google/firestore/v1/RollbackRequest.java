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
public final class RollbackRequest extends GeneratedMessageLite<RollbackRequest, Builder> implements RollbackRequestOrBuilder {
    public static final int DATABASE_FIELD_NUMBER = 1;
    public static final RollbackRequest DEFAULT_INSTANCE;
    public static volatile Parser<RollbackRequest> PARSER = null;
    public static final int TRANSACTION_FIELD_NUMBER = 2;
    public String database_ = "";
    public ByteString transaction_ = ByteString.EMPTY;

    /* JADX INFO: renamed from: com.google.firestore.v1.RollbackRequest$1, reason: invalid class name */
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
        RollbackRequest rollbackRequest = new RollbackRequest();
        DEFAULT_INSTANCE = rollbackRequest;
        GeneratedMessageLite.defaultInstanceMap.put(RollbackRequest.class, rollbackRequest);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDatabase() {
        this.database_ = DEFAULT_INSTANCE.getDatabase();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTransaction() {
        this.transaction_ = DEFAULT_INSTANCE.getTransaction();
    }

    public static RollbackRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static RollbackRequest parseDelimitedFrom(InputStream inputStream) {
        return (RollbackRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RollbackRequest parseFrom(ByteBuffer byteBuffer) {
        return (RollbackRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<RollbackRequest> parser() {
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\n", new Object[]{"database_", "transaction_"});
            case NEW_MUTABLE_INSTANCE:
                return new RollbackRequest();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<RollbackRequest> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (RollbackRequest.class) {
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

    @Override // com.google.firestore.v1.RollbackRequestOrBuilder
    public String getDatabase() {
        return this.database_;
    }

    @Override // com.google.firestore.v1.RollbackRequestOrBuilder
    public ByteString getDatabaseBytes() {
        return ByteString.copyFromUtf8(this.database_);
    }

    @Override // com.google.firestore.v1.RollbackRequestOrBuilder
    public ByteString getTransaction() {
        return this.transaction_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<RollbackRequest, Builder> implements RollbackRequestOrBuilder {
        public Builder() {
            super(RollbackRequest.DEFAULT_INSTANCE);
        }

        public Builder clearDatabase() {
            copyOnWrite();
            ((RollbackRequest) this.instance).clearDatabase();
            return this;
        }

        public Builder clearTransaction() {
            copyOnWrite();
            ((RollbackRequest) this.instance).clearTransaction();
            return this;
        }

        @Override // com.google.firestore.v1.RollbackRequestOrBuilder
        public String getDatabase() {
            return ((RollbackRequest) this.instance).getDatabase();
        }

        @Override // com.google.firestore.v1.RollbackRequestOrBuilder
        public ByteString getDatabaseBytes() {
            return ((RollbackRequest) this.instance).getDatabaseBytes();
        }

        @Override // com.google.firestore.v1.RollbackRequestOrBuilder
        public ByteString getTransaction() {
            return ((RollbackRequest) this.instance).getTransaction();
        }

        public Builder setDatabase(String str) {
            copyOnWrite();
            ((RollbackRequest) this.instance).setDatabase(str);
            return this;
        }

        public Builder setDatabaseBytes(ByteString byteString) {
            copyOnWrite();
            ((RollbackRequest) this.instance).setDatabaseBytes(byteString);
            return this;
        }

        public Builder setTransaction(ByteString byteString) {
            copyOnWrite();
            ((RollbackRequest) this.instance).setTransaction(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(RollbackRequest.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(RollbackRequest rollbackRequest) {
        return DEFAULT_INSTANCE.createBuilder(rollbackRequest);
    }

    public static RollbackRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (RollbackRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RollbackRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (RollbackRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static RollbackRequest parseFrom(ByteString byteString) {
        return (RollbackRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static RollbackRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (RollbackRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static RollbackRequest parseFrom(byte[] bArr) {
        return (RollbackRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static RollbackRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (RollbackRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static RollbackRequest parseFrom(InputStream inputStream) {
        return (RollbackRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RollbackRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (RollbackRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RollbackRequest parseFrom(CodedInputStream codedInputStream) {
        return (RollbackRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static RollbackRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (RollbackRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
