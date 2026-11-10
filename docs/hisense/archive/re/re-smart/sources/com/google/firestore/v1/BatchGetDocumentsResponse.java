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
public final class BatchGetDocumentsResponse extends GeneratedMessageLite<BatchGetDocumentsResponse, Builder> implements BatchGetDocumentsResponseOrBuilder {
    public static final BatchGetDocumentsResponse DEFAULT_INSTANCE;
    public static final int FOUND_FIELD_NUMBER = 1;
    public static final int MISSING_FIELD_NUMBER = 2;
    public static volatile Parser<BatchGetDocumentsResponse> PARSER = null;
    public static final int READ_TIME_FIELD_NUMBER = 4;
    public static final int TRANSACTION_FIELD_NUMBER = 3;
    public Timestamp readTime_;
    public Object result_;
    public int resultCase_ = 0;
    public ByteString transaction_ = ByteString.EMPTY;

    /* JADX INFO: renamed from: com.google.firestore.v1.BatchGetDocumentsResponse$1, reason: invalid class name */
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

    public enum ResultCase {
        FOUND(1),
        MISSING(2),
        RESULT_NOT_SET(0);

        public final int value;

        ResultCase(int i) {
            this.value = i;
        }

        public static ResultCase forNumber(int i) {
            if (i == 0) {
                return RESULT_NOT_SET;
            }
            if (i == 1) {
                return FOUND;
            }
            if (i != 2) {
                return null;
            }
            return MISSING;
        }

        public int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ResultCase valueOf(int i) {
            return forNumber(i);
        }
    }

    static {
        BatchGetDocumentsResponse batchGetDocumentsResponse = new BatchGetDocumentsResponse();
        DEFAULT_INSTANCE = batchGetDocumentsResponse;
        GeneratedMessageLite.defaultInstanceMap.put(BatchGetDocumentsResponse.class, batchGetDocumentsResponse);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearFound() {
        if (this.resultCase_ == 1) {
            this.resultCase_ = 0;
            this.result_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMissing() {
        if (this.resultCase_ == 2) {
            this.resultCase_ = 0;
            this.result_ = null;
        }
    }

    private void clearReadTime() {
        this.readTime_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearResult() {
        this.resultCase_ = 0;
        this.result_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTransaction() {
        this.transaction_ = DEFAULT_INSTANCE.getTransaction();
    }

    public static BatchGetDocumentsResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeFound(Document document) {
        Object obj;
        document.getClass();
        if (this.resultCase_ != 1 || (obj = this.result_) == Document.DEFAULT_INSTANCE) {
            this.result_ = document;
        } else {
            this.result_ = Document.newBuilder((Document) obj).mergeFrom(document).buildPartial();
        }
        this.resultCase_ = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeReadTime(Timestamp timestamp) {
        timestamp.getClass();
        Timestamp timestamp2 = this.readTime_;
        if (timestamp2 == null || timestamp2 == Timestamp.DEFAULT_INSTANCE) {
            this.readTime_ = timestamp;
        } else {
            this.readTime_ = Timestamp.newBuilder(timestamp2).mergeFrom(timestamp).buildPartial();
        }
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static BatchGetDocumentsResponse parseDelimitedFrom(InputStream inputStream) {
        return (BatchGetDocumentsResponse) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BatchGetDocumentsResponse parseFrom(ByteBuffer byteBuffer) {
        return (BatchGetDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<BatchGetDocumentsResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFound(Document document) {
        document.getClass();
        this.result_ = document;
        this.resultCase_ = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMissing(String str) {
        str.getClass();
        this.resultCase_ = 2;
        this.result_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMissingBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.result_ = byteString.toStringUtf8();
        this.resultCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReadTime(Timestamp timestamp) {
        timestamp.getClass();
        this.readTime_ = timestamp;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0001\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001<\u0000\u0002Ȼ\u0000\u0003\n\u0004\t", new Object[]{"result_", "resultCase_", Document.class, "transaction_", "readTime_"});
            case NEW_MUTABLE_INSTANCE:
                return new BatchGetDocumentsResponse();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<BatchGetDocumentsResponse> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (BatchGetDocumentsResponse.class) {
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

    @Override // com.google.firestore.v1.BatchGetDocumentsResponseOrBuilder
    public Document getFound() {
        return this.resultCase_ == 1 ? (Document) this.result_ : Document.DEFAULT_INSTANCE;
    }

    @Override // com.google.firestore.v1.BatchGetDocumentsResponseOrBuilder
    public String getMissing() {
        return this.resultCase_ == 2 ? (String) this.result_ : "";
    }

    @Override // com.google.firestore.v1.BatchGetDocumentsResponseOrBuilder
    public ByteString getMissingBytes() {
        return ByteString.copyFromUtf8(this.resultCase_ == 2 ? (String) this.result_ : "");
    }

    @Override // com.google.firestore.v1.BatchGetDocumentsResponseOrBuilder
    public Timestamp getReadTime() {
        Timestamp timestamp = this.readTime_;
        return timestamp == null ? Timestamp.DEFAULT_INSTANCE : timestamp;
    }

    @Override // com.google.firestore.v1.BatchGetDocumentsResponseOrBuilder
    public ResultCase getResultCase() {
        return ResultCase.forNumber(this.resultCase_);
    }

    @Override // com.google.firestore.v1.BatchGetDocumentsResponseOrBuilder
    public ByteString getTransaction() {
        return this.transaction_;
    }

    @Override // com.google.firestore.v1.BatchGetDocumentsResponseOrBuilder
    public boolean hasFound() {
        return this.resultCase_ == 1;
    }

    @Override // com.google.firestore.v1.BatchGetDocumentsResponseOrBuilder
    public boolean hasReadTime() {
        return this.readTime_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BatchGetDocumentsResponse, Builder> implements BatchGetDocumentsResponseOrBuilder {
        public Builder() {
            super(BatchGetDocumentsResponse.DEFAULT_INSTANCE);
        }

        public Builder clearFound() {
            copyOnWrite();
            ((BatchGetDocumentsResponse) this.instance).clearFound();
            return this;
        }

        public Builder clearMissing() {
            copyOnWrite();
            ((BatchGetDocumentsResponse) this.instance).clearMissing();
            return this;
        }

        public Builder clearReadTime() {
            copyOnWrite();
            ((BatchGetDocumentsResponse) this.instance).readTime_ = null;
            return this;
        }

        public Builder clearResult() {
            copyOnWrite();
            ((BatchGetDocumentsResponse) this.instance).clearResult();
            return this;
        }

        public Builder clearTransaction() {
            copyOnWrite();
            ((BatchGetDocumentsResponse) this.instance).clearTransaction();
            return this;
        }

        @Override // com.google.firestore.v1.BatchGetDocumentsResponseOrBuilder
        public Document getFound() {
            return ((BatchGetDocumentsResponse) this.instance).getFound();
        }

        @Override // com.google.firestore.v1.BatchGetDocumentsResponseOrBuilder
        public String getMissing() {
            return ((BatchGetDocumentsResponse) this.instance).getMissing();
        }

        @Override // com.google.firestore.v1.BatchGetDocumentsResponseOrBuilder
        public ByteString getMissingBytes() {
            return ((BatchGetDocumentsResponse) this.instance).getMissingBytes();
        }

        @Override // com.google.firestore.v1.BatchGetDocumentsResponseOrBuilder
        public Timestamp getReadTime() {
            return ((BatchGetDocumentsResponse) this.instance).getReadTime();
        }

        @Override // com.google.firestore.v1.BatchGetDocumentsResponseOrBuilder
        public ResultCase getResultCase() {
            return ((BatchGetDocumentsResponse) this.instance).getResultCase();
        }

        @Override // com.google.firestore.v1.BatchGetDocumentsResponseOrBuilder
        public ByteString getTransaction() {
            return ((BatchGetDocumentsResponse) this.instance).getTransaction();
        }

        @Override // com.google.firestore.v1.BatchGetDocumentsResponseOrBuilder
        public boolean hasFound() {
            return ((BatchGetDocumentsResponse) this.instance).hasFound();
        }

        @Override // com.google.firestore.v1.BatchGetDocumentsResponseOrBuilder
        public boolean hasReadTime() {
            return ((BatchGetDocumentsResponse) this.instance).hasReadTime();
        }

        public Builder mergeFound(Document document) {
            copyOnWrite();
            ((BatchGetDocumentsResponse) this.instance).mergeFound(document);
            return this;
        }

        public Builder mergeReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((BatchGetDocumentsResponse) this.instance).mergeReadTime(timestamp);
            return this;
        }

        public Builder setFound(Document document) {
            copyOnWrite();
            ((BatchGetDocumentsResponse) this.instance).setFound(document);
            return this;
        }

        public Builder setMissing(String str) {
            copyOnWrite();
            ((BatchGetDocumentsResponse) this.instance).setMissing(str);
            return this;
        }

        public Builder setMissingBytes(ByteString byteString) {
            copyOnWrite();
            ((BatchGetDocumentsResponse) this.instance).setMissingBytes(byteString);
            return this;
        }

        public Builder setReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((BatchGetDocumentsResponse) this.instance).setReadTime(timestamp);
            return this;
        }

        public Builder setTransaction(ByteString byteString) {
            copyOnWrite();
            ((BatchGetDocumentsResponse) this.instance).setTransaction(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(BatchGetDocumentsResponse.DEFAULT_INSTANCE);
        }

        public Builder setFound(Document.Builder builder) {
            copyOnWrite();
            ((BatchGetDocumentsResponse) this.instance).setFound(builder.build());
            return this;
        }

        public Builder setReadTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((BatchGetDocumentsResponse) this.instance).setReadTime(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(BatchGetDocumentsResponse batchGetDocumentsResponse) {
        return DEFAULT_INSTANCE.createBuilder(batchGetDocumentsResponse);
    }

    public static BatchGetDocumentsResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (BatchGetDocumentsResponse) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BatchGetDocumentsResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (BatchGetDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static BatchGetDocumentsResponse parseFrom(ByteString byteString) {
        return (BatchGetDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static BatchGetDocumentsResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (BatchGetDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static BatchGetDocumentsResponse parseFrom(byte[] bArr) {
        return (BatchGetDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static BatchGetDocumentsResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (BatchGetDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static BatchGetDocumentsResponse parseFrom(InputStream inputStream) {
        return (BatchGetDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BatchGetDocumentsResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (BatchGetDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BatchGetDocumentsResponse parseFrom(CodedInputStream codedInputStream) {
        return (BatchGetDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static BatchGetDocumentsResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (BatchGetDocumentsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
