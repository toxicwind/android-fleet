package com.google.firestore.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class RunQueryResponse extends GeneratedMessageLite<RunQueryResponse, Builder> implements RunQueryResponseOrBuilder {
    public static final RunQueryResponse DEFAULT_INSTANCE;
    public static final int DOCUMENT_FIELD_NUMBER = 1;
    public static volatile Parser<RunQueryResponse> PARSER = null;
    public static final int READ_TIME_FIELD_NUMBER = 3;
    public static final int SKIPPED_RESULTS_FIELD_NUMBER = 4;
    public static final int TRANSACTION_FIELD_NUMBER = 2;
    public Document document_;
    public Timestamp readTime_;
    public int skippedResults_;
    public ByteString transaction_ = ByteString.EMPTY;

    /* JADX INFO: renamed from: com.google.firestore.v1.RunQueryResponse$1, reason: invalid class name */
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
        RunQueryResponse runQueryResponse = new RunQueryResponse();
        DEFAULT_INSTANCE = runQueryResponse;
        GeneratedMessageLite.defaultInstanceMap.put(RunQueryResponse.class, runQueryResponse);
    }

    private void clearDocument() {
        this.document_ = null;
    }

    private void clearReadTime() {
        this.readTime_ = null;
    }

    private void clearSkippedResults() {
        this.skippedResults_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTransaction() {
        this.transaction_ = DEFAULT_INSTANCE.getTransaction();
    }

    public static RunQueryResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeDocument(Document document) {
        document.getClass();
        Document document2 = this.document_;
        if (document2 == null || document2 == Document.DEFAULT_INSTANCE) {
            this.document_ = document;
        } else {
            this.document_ = Document.newBuilder(document2).mergeFrom(document).buildPartial();
        }
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

    public static RunQueryResponse parseDelimitedFrom(InputStream inputStream) {
        return (RunQueryResponse) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RunQueryResponse parseFrom(ByteBuffer byteBuffer) {
        return (RunQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<RunQueryResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocument(Document document) {
        document.getClass();
        this.document_ = document;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReadTime(Timestamp timestamp) {
        timestamp.getClass();
        this.readTime_ = timestamp;
    }

    private void setSkippedResults(int i) {
        this.skippedResults_ = i;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\t\u0002\n\u0003\t\u0004\u0004", new Object[]{"document_", "transaction_", "readTime_", "skippedResults_"});
            case NEW_MUTABLE_INSTANCE:
                return new RunQueryResponse();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<RunQueryResponse> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (RunQueryResponse.class) {
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

    @Override // com.google.firestore.v1.RunQueryResponseOrBuilder
    public Document getDocument() {
        Document document = this.document_;
        return document == null ? Document.DEFAULT_INSTANCE : document;
    }

    @Override // com.google.firestore.v1.RunQueryResponseOrBuilder
    public Timestamp getReadTime() {
        Timestamp timestamp = this.readTime_;
        return timestamp == null ? Timestamp.DEFAULT_INSTANCE : timestamp;
    }

    @Override // com.google.firestore.v1.RunQueryResponseOrBuilder
    public int getSkippedResults() {
        return this.skippedResults_;
    }

    @Override // com.google.firestore.v1.RunQueryResponseOrBuilder
    public ByteString getTransaction() {
        return this.transaction_;
    }

    @Override // com.google.firestore.v1.RunQueryResponseOrBuilder
    public boolean hasDocument() {
        return this.document_ != null;
    }

    @Override // com.google.firestore.v1.RunQueryResponseOrBuilder
    public boolean hasReadTime() {
        return this.readTime_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<RunQueryResponse, Builder> implements RunQueryResponseOrBuilder {
        public Builder() {
            super(RunQueryResponse.DEFAULT_INSTANCE);
        }

        public Builder clearDocument() {
            copyOnWrite();
            ((RunQueryResponse) this.instance).document_ = null;
            return this;
        }

        public Builder clearReadTime() {
            copyOnWrite();
            ((RunQueryResponse) this.instance).readTime_ = null;
            return this;
        }

        public Builder clearSkippedResults() {
            copyOnWrite();
            ((RunQueryResponse) this.instance).skippedResults_ = 0;
            return this;
        }

        public Builder clearTransaction() {
            copyOnWrite();
            ((RunQueryResponse) this.instance).clearTransaction();
            return this;
        }

        @Override // com.google.firestore.v1.RunQueryResponseOrBuilder
        public Document getDocument() {
            return ((RunQueryResponse) this.instance).getDocument();
        }

        @Override // com.google.firestore.v1.RunQueryResponseOrBuilder
        public Timestamp getReadTime() {
            return ((RunQueryResponse) this.instance).getReadTime();
        }

        @Override // com.google.firestore.v1.RunQueryResponseOrBuilder
        public int getSkippedResults() {
            return ((RunQueryResponse) this.instance).getSkippedResults();
        }

        @Override // com.google.firestore.v1.RunQueryResponseOrBuilder
        public ByteString getTransaction() {
            return ((RunQueryResponse) this.instance).getTransaction();
        }

        @Override // com.google.firestore.v1.RunQueryResponseOrBuilder
        public boolean hasDocument() {
            return ((RunQueryResponse) this.instance).hasDocument();
        }

        @Override // com.google.firestore.v1.RunQueryResponseOrBuilder
        public boolean hasReadTime() {
            return ((RunQueryResponse) this.instance).hasReadTime();
        }

        public Builder mergeDocument(Document document) {
            copyOnWrite();
            ((RunQueryResponse) this.instance).mergeDocument(document);
            return this;
        }

        public Builder mergeReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((RunQueryResponse) this.instance).mergeReadTime(timestamp);
            return this;
        }

        public Builder setDocument(Document document) {
            copyOnWrite();
            ((RunQueryResponse) this.instance).setDocument(document);
            return this;
        }

        public Builder setReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((RunQueryResponse) this.instance).setReadTime(timestamp);
            return this;
        }

        public Builder setSkippedResults(int i) {
            copyOnWrite();
            ((RunQueryResponse) this.instance).skippedResults_ = i;
            return this;
        }

        public Builder setTransaction(ByteString byteString) {
            copyOnWrite();
            ((RunQueryResponse) this.instance).setTransaction(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(RunQueryResponse.DEFAULT_INSTANCE);
        }

        public Builder setDocument(Document.Builder builder) {
            copyOnWrite();
            ((RunQueryResponse) this.instance).setDocument(builder.build());
            return this;
        }

        public Builder setReadTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((RunQueryResponse) this.instance).setReadTime(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(RunQueryResponse runQueryResponse) {
        return DEFAULT_INSTANCE.createBuilder(runQueryResponse);
    }

    public static RunQueryResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (RunQueryResponse) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RunQueryResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (RunQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static RunQueryResponse parseFrom(ByteString byteString) {
        return (RunQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static RunQueryResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (RunQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static RunQueryResponse parseFrom(byte[] bArr) {
        return (RunQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static RunQueryResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (RunQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static RunQueryResponse parseFrom(InputStream inputStream) {
        return (RunQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RunQueryResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (RunQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RunQueryResponse parseFrom(CodedInputStream codedInputStream) {
        return (RunQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static RunQueryResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (RunQueryResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
