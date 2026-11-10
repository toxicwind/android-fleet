package com.google.firestore.v1;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class RunQueryRequest extends GeneratedMessageLite<RunQueryRequest, Builder> implements RunQueryRequestOrBuilder {
    public static final RunQueryRequest DEFAULT_INSTANCE;
    public static final int NEW_TRANSACTION_FIELD_NUMBER = 6;
    public static final int PARENT_FIELD_NUMBER = 1;
    public static volatile Parser<RunQueryRequest> PARSER = null;
    public static final int READ_TIME_FIELD_NUMBER = 7;
    public static final int STRUCTURED_QUERY_FIELD_NUMBER = 2;
    public static final int TRANSACTION_FIELD_NUMBER = 5;
    public Object consistencySelector_;
    public Object queryType_;
    public int queryTypeCase_ = 0;
    public int consistencySelectorCase_ = 0;
    public String parent_ = "";

    /* JADX INFO: renamed from: com.google.firestore.v1.RunQueryRequest$1, reason: invalid class name */
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

    public enum ConsistencySelectorCase {
        TRANSACTION(5),
        NEW_TRANSACTION(6),
        READ_TIME(7),
        CONSISTENCYSELECTOR_NOT_SET(0);

        public final int value;

        ConsistencySelectorCase(int i) {
            this.value = i;
        }

        public static ConsistencySelectorCase forNumber(int i) {
            if (i == 0) {
                return CONSISTENCYSELECTOR_NOT_SET;
            }
            if (i == 5) {
                return TRANSACTION;
            }
            if (i == 6) {
                return NEW_TRANSACTION;
            }
            if (i != 7) {
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

    public enum QueryTypeCase {
        STRUCTURED_QUERY(2),
        QUERYTYPE_NOT_SET(0);

        public final int value;

        QueryTypeCase(int i) {
            this.value = i;
        }

        public static QueryTypeCase forNumber(int i) {
            if (i == 0) {
                return QUERYTYPE_NOT_SET;
            }
            if (i != 2) {
                return null;
            }
            return STRUCTURED_QUERY;
        }

        public int getNumber() {
            return this.value;
        }

        @Deprecated
        public static QueryTypeCase valueOf(int i) {
            return forNumber(i);
        }
    }

    static {
        RunQueryRequest runQueryRequest = new RunQueryRequest();
        DEFAULT_INSTANCE = runQueryRequest;
        GeneratedMessageLite.defaultInstanceMap.put(RunQueryRequest.class, runQueryRequest);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearConsistencySelector() {
        this.consistencySelectorCase_ = 0;
        this.consistencySelector_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNewTransaction() {
        if (this.consistencySelectorCase_ == 6) {
            this.consistencySelectorCase_ = 0;
            this.consistencySelector_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearParent() {
        this.parent_ = DEFAULT_INSTANCE.getParent();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearQueryType() {
        this.queryTypeCase_ = 0;
        this.queryType_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearReadTime() {
        if (this.consistencySelectorCase_ == 7) {
            this.consistencySelectorCase_ = 0;
            this.consistencySelector_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearStructuredQuery() {
        if (this.queryTypeCase_ == 2) {
            this.queryTypeCase_ = 0;
            this.queryType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTransaction() {
        if (this.consistencySelectorCase_ == 5) {
            this.consistencySelectorCase_ = 0;
            this.consistencySelector_ = null;
        }
    }

    public static RunQueryRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeNewTransaction(TransactionOptions transactionOptions) {
        Object obj;
        transactionOptions.getClass();
        if (this.consistencySelectorCase_ != 6 || (obj = this.consistencySelector_) == TransactionOptions.DEFAULT_INSTANCE) {
            this.consistencySelector_ = transactionOptions;
        } else {
            this.consistencySelector_ = TransactionOptions.newBuilder((TransactionOptions) obj).mergeFrom(transactionOptions).buildPartial();
        }
        this.consistencySelectorCase_ = 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeReadTime(Timestamp timestamp) {
        Object obj;
        timestamp.getClass();
        if (this.consistencySelectorCase_ != 7 || (obj = this.consistencySelector_) == Timestamp.DEFAULT_INSTANCE) {
            this.consistencySelector_ = timestamp;
        } else {
            this.consistencySelector_ = Timestamp.newBuilder((Timestamp) obj).mergeFrom(timestamp).buildPartial();
        }
        this.consistencySelectorCase_ = 7;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeStructuredQuery(StructuredQuery structuredQuery) {
        Object obj;
        structuredQuery.getClass();
        if (this.queryTypeCase_ != 2 || (obj = this.queryType_) == StructuredQuery.DEFAULT_INSTANCE) {
            this.queryType_ = structuredQuery;
        } else {
            this.queryType_ = StructuredQuery.newBuilder((StructuredQuery) obj).mergeFrom(structuredQuery).buildPartial();
        }
        this.queryTypeCase_ = 2;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static RunQueryRequest parseDelimitedFrom(InputStream inputStream) {
        return (RunQueryRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RunQueryRequest parseFrom(ByteBuffer byteBuffer) {
        return (RunQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<RunQueryRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNewTransaction(TransactionOptions transactionOptions) {
        transactionOptions.getClass();
        this.consistencySelector_ = transactionOptions;
        this.consistencySelectorCase_ = 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setParent(String str) {
        str.getClass();
        this.parent_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setParentBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.parent_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReadTime(Timestamp timestamp) {
        timestamp.getClass();
        this.consistencySelector_ = timestamp;
        this.consistencySelectorCase_ = 7;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStructuredQuery(StructuredQuery structuredQuery) {
        structuredQuery.getClass();
        this.queryType_ = structuredQuery;
        this.queryTypeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTransaction(ByteString byteString) {
        byteString.getClass();
        this.consistencySelectorCase_ = 5;
        this.consistencySelector_ = byteString;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0002\u0000\u0001\u0007\u0005\u0000\u0000\u0000\u0001Ȉ\u0002<\u0000\u0005=\u0001\u0006<\u0001\u0007<\u0001", new Object[]{"queryType_", "queryTypeCase_", "consistencySelector_", "consistencySelectorCase_", "parent_", StructuredQuery.class, TransactionOptions.class, Timestamp.class});
            case NEW_MUTABLE_INSTANCE:
                return new RunQueryRequest();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<RunQueryRequest> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (RunQueryRequest.class) {
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

    @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
    public ConsistencySelectorCase getConsistencySelectorCase() {
        return ConsistencySelectorCase.forNumber(this.consistencySelectorCase_);
    }

    @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
    public TransactionOptions getNewTransaction() {
        return this.consistencySelectorCase_ == 6 ? (TransactionOptions) this.consistencySelector_ : TransactionOptions.DEFAULT_INSTANCE;
    }

    @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
    public String getParent() {
        return this.parent_;
    }

    @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
    public ByteString getParentBytes() {
        return ByteString.copyFromUtf8(this.parent_);
    }

    @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
    public QueryTypeCase getQueryTypeCase() {
        return QueryTypeCase.forNumber(this.queryTypeCase_);
    }

    @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
    public Timestamp getReadTime() {
        return this.consistencySelectorCase_ == 7 ? (Timestamp) this.consistencySelector_ : Timestamp.DEFAULT_INSTANCE;
    }

    @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
    public StructuredQuery getStructuredQuery() {
        return this.queryTypeCase_ == 2 ? (StructuredQuery) this.queryType_ : StructuredQuery.DEFAULT_INSTANCE;
    }

    @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
    public ByteString getTransaction() {
        return this.consistencySelectorCase_ == 5 ? (ByteString) this.consistencySelector_ : ByteString.EMPTY;
    }

    @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
    public boolean hasNewTransaction() {
        return this.consistencySelectorCase_ == 6;
    }

    @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
    public boolean hasReadTime() {
        return this.consistencySelectorCase_ == 7;
    }

    @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
    public boolean hasStructuredQuery() {
        return this.queryTypeCase_ == 2;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<RunQueryRequest, Builder> implements RunQueryRequestOrBuilder {
        public Builder() {
            super(RunQueryRequest.DEFAULT_INSTANCE);
        }

        public Builder clearConsistencySelector() {
            copyOnWrite();
            ((RunQueryRequest) this.instance).clearConsistencySelector();
            return this;
        }

        public Builder clearNewTransaction() {
            copyOnWrite();
            ((RunQueryRequest) this.instance).clearNewTransaction();
            return this;
        }

        public Builder clearParent() {
            copyOnWrite();
            ((RunQueryRequest) this.instance).clearParent();
            return this;
        }

        public Builder clearQueryType() {
            copyOnWrite();
            ((RunQueryRequest) this.instance).clearQueryType();
            return this;
        }

        public Builder clearReadTime() {
            copyOnWrite();
            ((RunQueryRequest) this.instance).clearReadTime();
            return this;
        }

        public Builder clearStructuredQuery() {
            copyOnWrite();
            ((RunQueryRequest) this.instance).clearStructuredQuery();
            return this;
        }

        public Builder clearTransaction() {
            copyOnWrite();
            ((RunQueryRequest) this.instance).clearTransaction();
            return this;
        }

        @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
        public ConsistencySelectorCase getConsistencySelectorCase() {
            return ((RunQueryRequest) this.instance).getConsistencySelectorCase();
        }

        @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
        public TransactionOptions getNewTransaction() {
            return ((RunQueryRequest) this.instance).getNewTransaction();
        }

        @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
        public String getParent() {
            return ((RunQueryRequest) this.instance).getParent();
        }

        @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
        public ByteString getParentBytes() {
            return ((RunQueryRequest) this.instance).getParentBytes();
        }

        @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
        public QueryTypeCase getQueryTypeCase() {
            return ((RunQueryRequest) this.instance).getQueryTypeCase();
        }

        @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
        public Timestamp getReadTime() {
            return ((RunQueryRequest) this.instance).getReadTime();
        }

        @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
        public StructuredQuery getStructuredQuery() {
            return ((RunQueryRequest) this.instance).getStructuredQuery();
        }

        @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
        public ByteString getTransaction() {
            return ((RunQueryRequest) this.instance).getTransaction();
        }

        @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
        public boolean hasNewTransaction() {
            return ((RunQueryRequest) this.instance).hasNewTransaction();
        }

        @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
        public boolean hasReadTime() {
            return ((RunQueryRequest) this.instance).hasReadTime();
        }

        @Override // com.google.firestore.v1.RunQueryRequestOrBuilder
        public boolean hasStructuredQuery() {
            return ((RunQueryRequest) this.instance).hasStructuredQuery();
        }

        public Builder mergeNewTransaction(TransactionOptions transactionOptions) {
            copyOnWrite();
            ((RunQueryRequest) this.instance).mergeNewTransaction(transactionOptions);
            return this;
        }

        public Builder mergeReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((RunQueryRequest) this.instance).mergeReadTime(timestamp);
            return this;
        }

        public Builder mergeStructuredQuery(StructuredQuery structuredQuery) {
            copyOnWrite();
            ((RunQueryRequest) this.instance).mergeStructuredQuery(structuredQuery);
            return this;
        }

        public Builder setNewTransaction(TransactionOptions transactionOptions) {
            copyOnWrite();
            ((RunQueryRequest) this.instance).setNewTransaction(transactionOptions);
            return this;
        }

        public Builder setParent(String str) {
            copyOnWrite();
            ((RunQueryRequest) this.instance).setParent(str);
            return this;
        }

        public Builder setParentBytes(ByteString byteString) {
            copyOnWrite();
            ((RunQueryRequest) this.instance).setParentBytes(byteString);
            return this;
        }

        public Builder setReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((RunQueryRequest) this.instance).setReadTime(timestamp);
            return this;
        }

        public Builder setStructuredQuery(StructuredQuery structuredQuery) {
            copyOnWrite();
            ((RunQueryRequest) this.instance).setStructuredQuery(structuredQuery);
            return this;
        }

        public Builder setTransaction(ByteString byteString) {
            copyOnWrite();
            ((RunQueryRequest) this.instance).setTransaction(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(RunQueryRequest.DEFAULT_INSTANCE);
        }

        public Builder setNewTransaction(TransactionOptions.Builder builder) {
            copyOnWrite();
            ((RunQueryRequest) this.instance).setNewTransaction(builder.build());
            return this;
        }

        public Builder setReadTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((RunQueryRequest) this.instance).setReadTime(builder.build());
            return this;
        }

        public Builder setStructuredQuery(StructuredQuery.Builder builder) {
            copyOnWrite();
            ((RunQueryRequest) this.instance).setStructuredQuery(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(RunQueryRequest runQueryRequest) {
        return DEFAULT_INSTANCE.createBuilder(runQueryRequest);
    }

    public static RunQueryRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (RunQueryRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RunQueryRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (RunQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static RunQueryRequest parseFrom(ByteString byteString) {
        return (RunQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static RunQueryRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (RunQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static RunQueryRequest parseFrom(byte[] bArr) {
        return (RunQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static RunQueryRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (RunQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static RunQueryRequest parseFrom(InputStream inputStream) {
        return (RunQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RunQueryRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (RunQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RunQueryRequest parseFrom(CodedInputStream codedInputStream) {
        return (RunQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static RunQueryRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (RunQueryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
