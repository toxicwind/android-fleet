package com.google.firestore.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class TransactionOptions extends GeneratedMessageLite<TransactionOptions, Builder> implements TransactionOptionsOrBuilder {
    public static final TransactionOptions DEFAULT_INSTANCE;
    public static volatile Parser<TransactionOptions> PARSER = null;
    public static final int READ_ONLY_FIELD_NUMBER = 2;
    public static final int READ_WRITE_FIELD_NUMBER = 3;
    public int modeCase_ = 0;
    public Object mode_;

    /* JADX INFO: renamed from: com.google.firestore.v1.TransactionOptions$1, reason: invalid class name */
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

    public enum ModeCase {
        READ_ONLY(2),
        READ_WRITE(3),
        MODE_NOT_SET(0);

        public final int value;

        ModeCase(int i) {
            this.value = i;
        }

        public static ModeCase forNumber(int i) {
            if (i == 0) {
                return MODE_NOT_SET;
            }
            if (i == 2) {
                return READ_ONLY;
            }
            if (i != 3) {
                return null;
            }
            return READ_WRITE;
        }

        public int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ModeCase valueOf(int i) {
            return forNumber(i);
        }
    }

    public static final class ReadOnly extends GeneratedMessageLite<ReadOnly, Builder> implements ReadOnlyOrBuilder {
        public static final ReadOnly DEFAULT_INSTANCE;
        public static volatile Parser<ReadOnly> PARSER = null;
        public static final int READ_TIME_FIELD_NUMBER = 2;
        public int consistencySelectorCase_ = 0;
        public Object consistencySelector_;

        public enum ConsistencySelectorCase {
            READ_TIME(2),
            CONSISTENCYSELECTOR_NOT_SET(0);

            public final int value;

            ConsistencySelectorCase(int i) {
                this.value = i;
            }

            public static ConsistencySelectorCase forNumber(int i) {
                if (i == 0) {
                    return CONSISTENCYSELECTOR_NOT_SET;
                }
                if (i != 2) {
                    return null;
                }
                return READ_TIME;
            }

            public int getNumber() {
                return this.value;
            }

            @Deprecated
            public static ConsistencySelectorCase valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            ReadOnly readOnly = new ReadOnly();
            DEFAULT_INSTANCE = readOnly;
            GeneratedMessageLite.defaultInstanceMap.put(ReadOnly.class, readOnly);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearConsistencySelector() {
            this.consistencySelectorCase_ = 0;
            this.consistencySelector_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearReadTime() {
            if (this.consistencySelectorCase_ == 2) {
                this.consistencySelectorCase_ = 0;
                this.consistencySelector_ = null;
            }
        }

        public static ReadOnly getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeReadTime(Timestamp timestamp) {
            Object obj;
            timestamp.getClass();
            if (this.consistencySelectorCase_ != 2 || (obj = this.consistencySelector_) == Timestamp.DEFAULT_INSTANCE) {
                this.consistencySelector_ = timestamp;
            } else {
                this.consistencySelector_ = Timestamp.newBuilder((Timestamp) obj).mergeFrom(timestamp).buildPartial();
            }
            this.consistencySelectorCase_ = 2;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static ReadOnly parseDelimitedFrom(InputStream inputStream) {
            return (ReadOnly) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ReadOnly parseFrom(ByteBuffer byteBuffer) {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<ReadOnly> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setReadTime(Timestamp timestamp) {
            timestamp.getClass();
            this.consistencySelector_ = timestamp;
            this.consistencySelectorCase_ = 2;
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0001\u0000\u0002\u0002\u0001\u0000\u0000\u0000\u0002<\u0000", new Object[]{"consistencySelector_", "consistencySelectorCase_", Timestamp.class});
                case NEW_MUTABLE_INSTANCE:
                    return new ReadOnly();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<ReadOnly> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (ReadOnly.class) {
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

        @Override // com.google.firestore.v1.TransactionOptions.ReadOnlyOrBuilder
        public ConsistencySelectorCase getConsistencySelectorCase() {
            return ConsistencySelectorCase.forNumber(this.consistencySelectorCase_);
        }

        @Override // com.google.firestore.v1.TransactionOptions.ReadOnlyOrBuilder
        public Timestamp getReadTime() {
            return this.consistencySelectorCase_ == 2 ? (Timestamp) this.consistencySelector_ : Timestamp.DEFAULT_INSTANCE;
        }

        @Override // com.google.firestore.v1.TransactionOptions.ReadOnlyOrBuilder
        public boolean hasReadTime() {
            return this.consistencySelectorCase_ == 2;
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ReadOnly, Builder> implements ReadOnlyOrBuilder {
            public Builder() {
                super(ReadOnly.DEFAULT_INSTANCE);
            }

            public Builder clearConsistencySelector() {
                copyOnWrite();
                ((ReadOnly) this.instance).clearConsistencySelector();
                return this;
            }

            public Builder clearReadTime() {
                copyOnWrite();
                ((ReadOnly) this.instance).clearReadTime();
                return this;
            }

            @Override // com.google.firestore.v1.TransactionOptions.ReadOnlyOrBuilder
            public ConsistencySelectorCase getConsistencySelectorCase() {
                return ((ReadOnly) this.instance).getConsistencySelectorCase();
            }

            @Override // com.google.firestore.v1.TransactionOptions.ReadOnlyOrBuilder
            public Timestamp getReadTime() {
                return ((ReadOnly) this.instance).getReadTime();
            }

            @Override // com.google.firestore.v1.TransactionOptions.ReadOnlyOrBuilder
            public boolean hasReadTime() {
                return ((ReadOnly) this.instance).hasReadTime();
            }

            public Builder mergeReadTime(Timestamp timestamp) {
                copyOnWrite();
                ((ReadOnly) this.instance).mergeReadTime(timestamp);
                return this;
            }

            public Builder setReadTime(Timestamp timestamp) {
                copyOnWrite();
                ((ReadOnly) this.instance).setReadTime(timestamp);
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(ReadOnly.DEFAULT_INSTANCE);
            }

            public Builder setReadTime(Timestamp.Builder builder) {
                copyOnWrite();
                ((ReadOnly) this.instance).setReadTime(builder.build());
                return this;
            }
        }

        public static Builder newBuilder(ReadOnly readOnly) {
            return DEFAULT_INSTANCE.createBuilder(readOnly);
        }

        public static ReadOnly parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ReadOnly) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ReadOnly parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ReadOnly parseFrom(ByteString byteString) {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ReadOnly parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ReadOnly parseFrom(byte[] bArr) {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ReadOnly parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ReadOnly parseFrom(InputStream inputStream) {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ReadOnly parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ReadOnly parseFrom(CodedInputStream codedInputStream) {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ReadOnly parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ReadOnly) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface ReadOnlyOrBuilder extends MessageLiteOrBuilder {
        ReadOnly.ConsistencySelectorCase getConsistencySelectorCase();

        Timestamp getReadTime();

        boolean hasReadTime();
    }

    public static final class ReadWrite extends GeneratedMessageLite<ReadWrite, Builder> implements ReadWriteOrBuilder {
        public static final ReadWrite DEFAULT_INSTANCE;
        public static volatile Parser<ReadWrite> PARSER = null;
        public static final int RETRY_TRANSACTION_FIELD_NUMBER = 1;
        public ByteString retryTransaction_ = ByteString.EMPTY;

        static {
            ReadWrite readWrite = new ReadWrite();
            DEFAULT_INSTANCE = readWrite;
            GeneratedMessageLite.defaultInstanceMap.put(ReadWrite.class, readWrite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRetryTransaction() {
            this.retryTransaction_ = DEFAULT_INSTANCE.getRetryTransaction();
        }

        public static ReadWrite getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static ReadWrite parseDelimitedFrom(InputStream inputStream) {
            return (ReadWrite) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ReadWrite parseFrom(ByteBuffer byteBuffer) {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<ReadWrite> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRetryTransaction(ByteString byteString) {
            byteString.getClass();
            this.retryTransaction_ = byteString;
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\n", new Object[]{"retryTransaction_"});
                case NEW_MUTABLE_INSTANCE:
                    return new ReadWrite();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<ReadWrite> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (ReadWrite.class) {
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

        @Override // com.google.firestore.v1.TransactionOptions.ReadWriteOrBuilder
        public ByteString getRetryTransaction() {
            return this.retryTransaction_;
        }

        public static final class Builder extends GeneratedMessageLite.Builder<ReadWrite, Builder> implements ReadWriteOrBuilder {
            public Builder() {
                super(ReadWrite.DEFAULT_INSTANCE);
            }

            public Builder clearRetryTransaction() {
                copyOnWrite();
                ((ReadWrite) this.instance).clearRetryTransaction();
                return this;
            }

            @Override // com.google.firestore.v1.TransactionOptions.ReadWriteOrBuilder
            public ByteString getRetryTransaction() {
                return ((ReadWrite) this.instance).getRetryTransaction();
            }

            public Builder setRetryTransaction(ByteString byteString) {
                copyOnWrite();
                ((ReadWrite) this.instance).setRetryTransaction(byteString);
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(ReadWrite.DEFAULT_INSTANCE);
            }
        }

        public static Builder newBuilder(ReadWrite readWrite) {
            return DEFAULT_INSTANCE.createBuilder(readWrite);
        }

        public static ReadWrite parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ReadWrite) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ReadWrite parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static ReadWrite parseFrom(ByteString byteString) {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ReadWrite parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ReadWrite parseFrom(byte[] bArr) {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ReadWrite parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ReadWrite parseFrom(InputStream inputStream) {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ReadWrite parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ReadWrite parseFrom(CodedInputStream codedInputStream) {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ReadWrite parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (ReadWrite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface ReadWriteOrBuilder extends MessageLiteOrBuilder {
        ByteString getRetryTransaction();
    }

    static {
        TransactionOptions transactionOptions = new TransactionOptions();
        DEFAULT_INSTANCE = transactionOptions;
        GeneratedMessageLite.defaultInstanceMap.put(TransactionOptions.class, transactionOptions);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMode() {
        this.modeCase_ = 0;
        this.mode_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearReadOnly() {
        if (this.modeCase_ == 2) {
            this.modeCase_ = 0;
            this.mode_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearReadWrite() {
        if (this.modeCase_ == 3) {
            this.modeCase_ = 0;
            this.mode_ = null;
        }
    }

    public static TransactionOptions getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeReadOnly(ReadOnly readOnly) {
        Object obj;
        readOnly.getClass();
        if (this.modeCase_ != 2 || (obj = this.mode_) == ReadOnly.DEFAULT_INSTANCE) {
            this.mode_ = readOnly;
        } else {
            this.mode_ = ReadOnly.newBuilder((ReadOnly) obj).mergeFrom(readOnly).buildPartial();
        }
        this.modeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeReadWrite(ReadWrite readWrite) {
        Object obj;
        readWrite.getClass();
        if (this.modeCase_ != 3 || (obj = this.mode_) == ReadWrite.DEFAULT_INSTANCE) {
            this.mode_ = readWrite;
        } else {
            this.mode_ = ReadWrite.newBuilder((ReadWrite) obj).mergeFrom(readWrite).buildPartial();
        }
        this.modeCase_ = 3;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static TransactionOptions parseDelimitedFrom(InputStream inputStream) {
        return (TransactionOptions) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TransactionOptions parseFrom(ByteBuffer byteBuffer) {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<TransactionOptions> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReadOnly(ReadOnly readOnly) {
        readOnly.getClass();
        this.mode_ = readOnly;
        this.modeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReadWrite(ReadWrite readWrite) {
        readWrite.getClass();
        this.mode_ = readWrite;
        this.modeCase_ = 3;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002<\u0000\u0003<\u0000", new Object[]{"mode_", "modeCase_", ReadOnly.class, ReadWrite.class});
            case NEW_MUTABLE_INSTANCE:
                return new TransactionOptions();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<TransactionOptions> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (TransactionOptions.class) {
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

    @Override // com.google.firestore.v1.TransactionOptionsOrBuilder
    public ModeCase getModeCase() {
        return ModeCase.forNumber(this.modeCase_);
    }

    @Override // com.google.firestore.v1.TransactionOptionsOrBuilder
    public ReadOnly getReadOnly() {
        return this.modeCase_ == 2 ? (ReadOnly) this.mode_ : ReadOnly.DEFAULT_INSTANCE;
    }

    @Override // com.google.firestore.v1.TransactionOptionsOrBuilder
    public ReadWrite getReadWrite() {
        return this.modeCase_ == 3 ? (ReadWrite) this.mode_ : ReadWrite.DEFAULT_INSTANCE;
    }

    @Override // com.google.firestore.v1.TransactionOptionsOrBuilder
    public boolean hasReadOnly() {
        return this.modeCase_ == 2;
    }

    @Override // com.google.firestore.v1.TransactionOptionsOrBuilder
    public boolean hasReadWrite() {
        return this.modeCase_ == 3;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<TransactionOptions, Builder> implements TransactionOptionsOrBuilder {
        public Builder() {
            super(TransactionOptions.DEFAULT_INSTANCE);
        }

        public Builder clearMode() {
            copyOnWrite();
            ((TransactionOptions) this.instance).clearMode();
            return this;
        }

        public Builder clearReadOnly() {
            copyOnWrite();
            ((TransactionOptions) this.instance).clearReadOnly();
            return this;
        }

        public Builder clearReadWrite() {
            copyOnWrite();
            ((TransactionOptions) this.instance).clearReadWrite();
            return this;
        }

        @Override // com.google.firestore.v1.TransactionOptionsOrBuilder
        public ModeCase getModeCase() {
            return ((TransactionOptions) this.instance).getModeCase();
        }

        @Override // com.google.firestore.v1.TransactionOptionsOrBuilder
        public ReadOnly getReadOnly() {
            return ((TransactionOptions) this.instance).getReadOnly();
        }

        @Override // com.google.firestore.v1.TransactionOptionsOrBuilder
        public ReadWrite getReadWrite() {
            return ((TransactionOptions) this.instance).getReadWrite();
        }

        @Override // com.google.firestore.v1.TransactionOptionsOrBuilder
        public boolean hasReadOnly() {
            return ((TransactionOptions) this.instance).hasReadOnly();
        }

        @Override // com.google.firestore.v1.TransactionOptionsOrBuilder
        public boolean hasReadWrite() {
            return ((TransactionOptions) this.instance).hasReadWrite();
        }

        public Builder mergeReadOnly(ReadOnly readOnly) {
            copyOnWrite();
            ((TransactionOptions) this.instance).mergeReadOnly(readOnly);
            return this;
        }

        public Builder mergeReadWrite(ReadWrite readWrite) {
            copyOnWrite();
            ((TransactionOptions) this.instance).mergeReadWrite(readWrite);
            return this;
        }

        public Builder setReadOnly(ReadOnly readOnly) {
            copyOnWrite();
            ((TransactionOptions) this.instance).setReadOnly(readOnly);
            return this;
        }

        public Builder setReadWrite(ReadWrite readWrite) {
            copyOnWrite();
            ((TransactionOptions) this.instance).setReadWrite(readWrite);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(TransactionOptions.DEFAULT_INSTANCE);
        }

        public Builder setReadOnly(ReadOnly.Builder builder) {
            copyOnWrite();
            ((TransactionOptions) this.instance).setReadOnly(builder.build());
            return this;
        }

        public Builder setReadWrite(ReadWrite.Builder builder) {
            copyOnWrite();
            ((TransactionOptions) this.instance).setReadWrite(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(TransactionOptions transactionOptions) {
        return DEFAULT_INSTANCE.createBuilder(transactionOptions);
    }

    public static TransactionOptions parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (TransactionOptions) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static TransactionOptions parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static TransactionOptions parseFrom(ByteString byteString) {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static TransactionOptions parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static TransactionOptions parseFrom(byte[] bArr) {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static TransactionOptions parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static TransactionOptions parseFrom(InputStream inputStream) {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TransactionOptions parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static TransactionOptions parseFrom(CodedInputStream codedInputStream) {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static TransactionOptions parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (TransactionOptions) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
