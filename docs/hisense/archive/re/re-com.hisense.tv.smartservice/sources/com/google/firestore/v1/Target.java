package com.google.firestore.v1;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class Target extends GeneratedMessageLite<Target, Builder> implements TargetOrBuilder {
    public static final Target DEFAULT_INSTANCE;
    public static final int DOCUMENTS_FIELD_NUMBER = 3;
    public static final int ONCE_FIELD_NUMBER = 6;
    public static volatile Parser<Target> PARSER = null;
    public static final int QUERY_FIELD_NUMBER = 2;
    public static final int READ_TIME_FIELD_NUMBER = 11;
    public static final int RESUME_TOKEN_FIELD_NUMBER = 4;
    public static final int TARGET_ID_FIELD_NUMBER = 5;
    public boolean once_;
    public Object resumeType_;
    public int targetId_;
    public Object targetType_;
    public int targetTypeCase_ = 0;
    public int resumeTypeCase_ = 0;

    /* JADX INFO: renamed from: com.google.firestore.v1.Target$1, reason: invalid class name */
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

    public static final class DocumentsTarget extends GeneratedMessageLite<DocumentsTarget, Builder> implements DocumentsTargetOrBuilder {
        public static final DocumentsTarget DEFAULT_INSTANCE;
        public static final int DOCUMENTS_FIELD_NUMBER = 2;
        public static volatile Parser<DocumentsTarget> PARSER;
        public Internal.ProtobufList<String> documents_ = GeneratedMessageLite.emptyProtobufList();

        static {
            DocumentsTarget documentsTarget = new DocumentsTarget();
            DEFAULT_INSTANCE = documentsTarget;
            GeneratedMessageLite.defaultInstanceMap.put(DocumentsTarget.class, documentsTarget);
        }

        public static /* synthetic */ void access$300(DocumentsTarget documentsTarget, Iterable iterable) {
            documentsTarget.ensureDocumentsIsMutable();
            AbstractMessageLite.Builder.addAll(iterable, (List) documentsTarget.documents_);
        }

        private void addAllDocuments(Iterable<String> iterable) {
            ensureDocumentsIsMutable();
            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.documents_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addDocuments(String str) {
            str.getClass();
            ensureDocumentsIsMutable();
            this.documents_.add(str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addDocumentsBytes(ByteString byteString) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            ensureDocumentsIsMutable();
            this.documents_.add(byteString.toStringUtf8());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDocuments() {
            this.documents_ = GeneratedMessageLite.emptyProtobufList();
        }

        private void ensureDocumentsIsMutable() {
            Internal.ProtobufList<String> protobufList = this.documents_;
            if (protobufList.isModifiable()) {
                return;
            }
            this.documents_ = GeneratedMessageLite.mutableCopy(protobufList);
        }

        public static DocumentsTarget getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static DocumentsTarget parseDelimitedFrom(InputStream inputStream) {
            return (DocumentsTarget) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DocumentsTarget parseFrom(ByteBuffer byteBuffer) {
            return (DocumentsTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<DocumentsTarget> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDocuments(int i, String str) {
            str.getClass();
            ensureDocumentsIsMutable();
            this.documents_.set(i, str);
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0002\u0002\u0001\u0000\u0001\u0000\u0002Ț", new Object[]{"documents_"});
                case NEW_MUTABLE_INSTANCE:
                    return new DocumentsTarget();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<DocumentsTarget> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (DocumentsTarget.class) {
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

        @Override // com.google.firestore.v1.Target.DocumentsTargetOrBuilder
        public String getDocuments(int i) {
            return this.documents_.get(i);
        }

        @Override // com.google.firestore.v1.Target.DocumentsTargetOrBuilder
        public ByteString getDocumentsBytes(int i) {
            return ByteString.copyFromUtf8(this.documents_.get(i));
        }

        @Override // com.google.firestore.v1.Target.DocumentsTargetOrBuilder
        public int getDocumentsCount() {
            return this.documents_.size();
        }

        @Override // com.google.firestore.v1.Target.DocumentsTargetOrBuilder
        public List<String> getDocumentsList() {
            return this.documents_;
        }

        public static final class Builder extends GeneratedMessageLite.Builder<DocumentsTarget, Builder> implements DocumentsTargetOrBuilder {
            public Builder() {
                super(DocumentsTarget.DEFAULT_INSTANCE);
            }

            public Builder addAllDocuments(Iterable<String> iterable) {
                copyOnWrite();
                DocumentsTarget.access$300((DocumentsTarget) this.instance, iterable);
                return this;
            }

            public Builder addDocuments(String str) {
                copyOnWrite();
                ((DocumentsTarget) this.instance).addDocuments(str);
                return this;
            }

            public Builder addDocumentsBytes(ByteString byteString) {
                copyOnWrite();
                ((DocumentsTarget) this.instance).addDocumentsBytes(byteString);
                return this;
            }

            public Builder clearDocuments() {
                copyOnWrite();
                ((DocumentsTarget) this.instance).clearDocuments();
                return this;
            }

            @Override // com.google.firestore.v1.Target.DocumentsTargetOrBuilder
            public String getDocuments(int i) {
                return ((DocumentsTarget) this.instance).getDocuments(i);
            }

            @Override // com.google.firestore.v1.Target.DocumentsTargetOrBuilder
            public ByteString getDocumentsBytes(int i) {
                return ((DocumentsTarget) this.instance).getDocumentsBytes(i);
            }

            @Override // com.google.firestore.v1.Target.DocumentsTargetOrBuilder
            public int getDocumentsCount() {
                return ((DocumentsTarget) this.instance).getDocumentsCount();
            }

            @Override // com.google.firestore.v1.Target.DocumentsTargetOrBuilder
            public List<String> getDocumentsList() {
                return Collections.unmodifiableList(((DocumentsTarget) this.instance).getDocumentsList());
            }

            public Builder setDocuments(int i, String str) {
                copyOnWrite();
                ((DocumentsTarget) this.instance).setDocuments(i, str);
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(DocumentsTarget.DEFAULT_INSTANCE);
            }
        }

        public static Builder newBuilder(DocumentsTarget documentsTarget) {
            return DEFAULT_INSTANCE.createBuilder(documentsTarget);
        }

        public static DocumentsTarget parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (DocumentsTarget) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static DocumentsTarget parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (DocumentsTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static DocumentsTarget parseFrom(ByteString byteString) {
            return (DocumentsTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static DocumentsTarget parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (DocumentsTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static DocumentsTarget parseFrom(byte[] bArr) {
            return (DocumentsTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static DocumentsTarget parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (DocumentsTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static DocumentsTarget parseFrom(InputStream inputStream) {
            return (DocumentsTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DocumentsTarget parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (DocumentsTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static DocumentsTarget parseFrom(CodedInputStream codedInputStream) {
            return (DocumentsTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static DocumentsTarget parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (DocumentsTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface DocumentsTargetOrBuilder extends MessageLiteOrBuilder {
        String getDocuments(int i);

        ByteString getDocumentsBytes(int i);

        int getDocumentsCount();

        List<String> getDocumentsList();
    }

    public static final class QueryTarget extends GeneratedMessageLite<QueryTarget, Builder> implements QueryTargetOrBuilder {
        public static final QueryTarget DEFAULT_INSTANCE;
        public static final int PARENT_FIELD_NUMBER = 1;
        public static volatile Parser<QueryTarget> PARSER = null;
        public static final int STRUCTURED_QUERY_FIELD_NUMBER = 2;
        public Object queryType_;
        public int queryTypeCase_ = 0;
        public String parent_ = "";

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
            QueryTarget queryTarget = new QueryTarget();
            DEFAULT_INSTANCE = queryTarget;
            GeneratedMessageLite.defaultInstanceMap.put(QueryTarget.class, queryTarget);
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
        public void clearStructuredQuery() {
            if (this.queryTypeCase_ == 2) {
                this.queryTypeCase_ = 0;
                this.queryType_ = null;
            }
        }

        public static QueryTarget getDefaultInstance() {
            return DEFAULT_INSTANCE;
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

        public static QueryTarget parseDelimitedFrom(InputStream inputStream) {
            return (QueryTarget) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static QueryTarget parseFrom(ByteBuffer byteBuffer) {
            return (QueryTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Parser<QueryTarget> parser() {
            return DEFAULT_INSTANCE.getParserForType();
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
        public void setStructuredQuery(StructuredQuery structuredQuery) {
            structuredQuery.getClass();
            this.queryType_ = structuredQuery;
            this.queryTypeCase_ = 2;
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
                    return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002<\u0000", new Object[]{"queryType_", "queryTypeCase_", "parent_", StructuredQuery.class});
                case NEW_MUTABLE_INSTANCE:
                    return new QueryTarget();
                case NEW_BUILDER:
                    return new Builder(anonymousClass1);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    Parser<QueryTarget> defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        synchronized (QueryTarget.class) {
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

        @Override // com.google.firestore.v1.Target.QueryTargetOrBuilder
        public String getParent() {
            return this.parent_;
        }

        @Override // com.google.firestore.v1.Target.QueryTargetOrBuilder
        public ByteString getParentBytes() {
            return ByteString.copyFromUtf8(this.parent_);
        }

        @Override // com.google.firestore.v1.Target.QueryTargetOrBuilder
        public QueryTypeCase getQueryTypeCase() {
            return QueryTypeCase.forNumber(this.queryTypeCase_);
        }

        @Override // com.google.firestore.v1.Target.QueryTargetOrBuilder
        public StructuredQuery getStructuredQuery() {
            return this.queryTypeCase_ == 2 ? (StructuredQuery) this.queryType_ : StructuredQuery.DEFAULT_INSTANCE;
        }

        @Override // com.google.firestore.v1.Target.QueryTargetOrBuilder
        public boolean hasStructuredQuery() {
            return this.queryTypeCase_ == 2;
        }

        public static final class Builder extends GeneratedMessageLite.Builder<QueryTarget, Builder> implements QueryTargetOrBuilder {
            public Builder() {
                super(QueryTarget.DEFAULT_INSTANCE);
            }

            public Builder clearParent() {
                copyOnWrite();
                ((QueryTarget) this.instance).clearParent();
                return this;
            }

            public Builder clearQueryType() {
                copyOnWrite();
                ((QueryTarget) this.instance).clearQueryType();
                return this;
            }

            public Builder clearStructuredQuery() {
                copyOnWrite();
                ((QueryTarget) this.instance).clearStructuredQuery();
                return this;
            }

            @Override // com.google.firestore.v1.Target.QueryTargetOrBuilder
            public String getParent() {
                return ((QueryTarget) this.instance).getParent();
            }

            @Override // com.google.firestore.v1.Target.QueryTargetOrBuilder
            public ByteString getParentBytes() {
                return ((QueryTarget) this.instance).getParentBytes();
            }

            @Override // com.google.firestore.v1.Target.QueryTargetOrBuilder
            public QueryTypeCase getQueryTypeCase() {
                return ((QueryTarget) this.instance).getQueryTypeCase();
            }

            @Override // com.google.firestore.v1.Target.QueryTargetOrBuilder
            public StructuredQuery getStructuredQuery() {
                return ((QueryTarget) this.instance).getStructuredQuery();
            }

            @Override // com.google.firestore.v1.Target.QueryTargetOrBuilder
            public boolean hasStructuredQuery() {
                return ((QueryTarget) this.instance).hasStructuredQuery();
            }

            public Builder mergeStructuredQuery(StructuredQuery structuredQuery) {
                copyOnWrite();
                ((QueryTarget) this.instance).mergeStructuredQuery(structuredQuery);
                return this;
            }

            public Builder setParent(String str) {
                copyOnWrite();
                ((QueryTarget) this.instance).setParent(str);
                return this;
            }

            public Builder setParentBytes(ByteString byteString) {
                copyOnWrite();
                ((QueryTarget) this.instance).setParentBytes(byteString);
                return this;
            }

            public Builder setStructuredQuery(StructuredQuery structuredQuery) {
                copyOnWrite();
                ((QueryTarget) this.instance).setStructuredQuery(structuredQuery);
                return this;
            }

            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                super(QueryTarget.DEFAULT_INSTANCE);
            }

            public Builder setStructuredQuery(StructuredQuery.Builder builder) {
                copyOnWrite();
                ((QueryTarget) this.instance).setStructuredQuery(builder.build());
                return this;
            }
        }

        public static Builder newBuilder(QueryTarget queryTarget) {
            return DEFAULT_INSTANCE.createBuilder(queryTarget);
        }

        public static QueryTarget parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (QueryTarget) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static QueryTarget parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (QueryTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static QueryTarget parseFrom(ByteString byteString) {
            return (QueryTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static QueryTarget parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (QueryTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static QueryTarget parseFrom(byte[] bArr) {
            return (QueryTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static QueryTarget parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (QueryTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static QueryTarget parseFrom(InputStream inputStream) {
            return (QueryTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static QueryTarget parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (QueryTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static QueryTarget parseFrom(CodedInputStream codedInputStream) {
            return (QueryTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static QueryTarget parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (QueryTarget) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }
    }

    public interface QueryTargetOrBuilder extends MessageLiteOrBuilder {
        String getParent();

        ByteString getParentBytes();

        QueryTarget.QueryTypeCase getQueryTypeCase();

        StructuredQuery getStructuredQuery();

        boolean hasStructuredQuery();
    }

    public enum ResumeTypeCase {
        RESUME_TOKEN(4),
        READ_TIME(11),
        RESUMETYPE_NOT_SET(0);

        public final int value;

        ResumeTypeCase(int i) {
            this.value = i;
        }

        public static ResumeTypeCase forNumber(int i) {
            if (i == 0) {
                return RESUMETYPE_NOT_SET;
            }
            if (i == 4) {
                return RESUME_TOKEN;
            }
            if (i != 11) {
                return null;
            }
            return READ_TIME;
        }

        public int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ResumeTypeCase valueOf(int i) {
            return forNumber(i);
        }
    }

    public enum TargetTypeCase {
        QUERY(2),
        DOCUMENTS(3),
        TARGETTYPE_NOT_SET(0);

        public final int value;

        TargetTypeCase(int i) {
            this.value = i;
        }

        public static TargetTypeCase forNumber(int i) {
            if (i == 0) {
                return TARGETTYPE_NOT_SET;
            }
            if (i == 2) {
                return QUERY;
            }
            if (i != 3) {
                return null;
            }
            return DOCUMENTS;
        }

        public int getNumber() {
            return this.value;
        }

        @Deprecated
        public static TargetTypeCase valueOf(int i) {
            return forNumber(i);
        }
    }

    static {
        Target target = new Target();
        DEFAULT_INSTANCE = target;
        GeneratedMessageLite.defaultInstanceMap.put(Target.class, target);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocuments() {
        if (this.targetTypeCase_ == 3) {
            this.targetTypeCase_ = 0;
            this.targetType_ = null;
        }
    }

    private void clearOnce() {
        this.once_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearQuery() {
        if (this.targetTypeCase_ == 2) {
            this.targetTypeCase_ = 0;
            this.targetType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearReadTime() {
        if (this.resumeTypeCase_ == 11) {
            this.resumeTypeCase_ = 0;
            this.resumeType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearResumeToken() {
        if (this.resumeTypeCase_ == 4) {
            this.resumeTypeCase_ = 0;
            this.resumeType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearResumeType() {
        this.resumeTypeCase_ = 0;
        this.resumeType_ = null;
    }

    private void clearTargetId() {
        this.targetId_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTargetType() {
        this.targetTypeCase_ = 0;
        this.targetType_ = null;
    }

    public static Target getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeDocuments(DocumentsTarget documentsTarget) {
        Object obj;
        documentsTarget.getClass();
        if (this.targetTypeCase_ != 3 || (obj = this.targetType_) == DocumentsTarget.DEFAULT_INSTANCE) {
            this.targetType_ = documentsTarget;
        } else {
            this.targetType_ = DocumentsTarget.newBuilder((DocumentsTarget) obj).mergeFrom(documentsTarget).buildPartial();
        }
        this.targetTypeCase_ = 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeQuery(QueryTarget queryTarget) {
        Object obj;
        queryTarget.getClass();
        if (this.targetTypeCase_ != 2 || (obj = this.targetType_) == QueryTarget.DEFAULT_INSTANCE) {
            this.targetType_ = queryTarget;
        } else {
            this.targetType_ = QueryTarget.newBuilder((QueryTarget) obj).mergeFrom(queryTarget).buildPartial();
        }
        this.targetTypeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeReadTime(Timestamp timestamp) {
        Object obj;
        timestamp.getClass();
        if (this.resumeTypeCase_ != 11 || (obj = this.resumeType_) == Timestamp.DEFAULT_INSTANCE) {
            this.resumeType_ = timestamp;
        } else {
            this.resumeType_ = Timestamp.newBuilder((Timestamp) obj).mergeFrom(timestamp).buildPartial();
        }
        this.resumeTypeCase_ = 11;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Target parseDelimitedFrom(InputStream inputStream) {
        return (Target) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Target parseFrom(ByteBuffer byteBuffer) {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Target> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocuments(DocumentsTarget documentsTarget) {
        documentsTarget.getClass();
        this.targetType_ = documentsTarget;
        this.targetTypeCase_ = 3;
    }

    private void setOnce(boolean z) {
        this.once_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setQuery(QueryTarget queryTarget) {
        queryTarget.getClass();
        this.targetType_ = queryTarget;
        this.targetTypeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReadTime(Timestamp timestamp) {
        timestamp.getClass();
        this.resumeType_ = timestamp;
        this.resumeTypeCase_ = 11;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResumeToken(ByteString byteString) {
        byteString.getClass();
        this.resumeTypeCase_ = 4;
        this.resumeType_ = byteString;
    }

    private void setTargetId(int i) {
        this.targetId_ = i;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0006\u0002\u0000\u0002\u000b\u0006\u0000\u0000\u0000\u0002<\u0000\u0003<\u0000\u0004=\u0001\u0005\u0004\u0006\u0007\u000b<\u0001", new Object[]{"targetType_", "targetTypeCase_", "resumeType_", "resumeTypeCase_", QueryTarget.class, DocumentsTarget.class, "targetId_", "once_", Timestamp.class});
            case NEW_MUTABLE_INSTANCE:
                return new Target();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Target> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Target.class) {
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

    @Override // com.google.firestore.v1.TargetOrBuilder
    public DocumentsTarget getDocuments() {
        return this.targetTypeCase_ == 3 ? (DocumentsTarget) this.targetType_ : DocumentsTarget.DEFAULT_INSTANCE;
    }

    @Override // com.google.firestore.v1.TargetOrBuilder
    public boolean getOnce() {
        return this.once_;
    }

    @Override // com.google.firestore.v1.TargetOrBuilder
    public QueryTarget getQuery() {
        return this.targetTypeCase_ == 2 ? (QueryTarget) this.targetType_ : QueryTarget.DEFAULT_INSTANCE;
    }

    @Override // com.google.firestore.v1.TargetOrBuilder
    public Timestamp getReadTime() {
        return this.resumeTypeCase_ == 11 ? (Timestamp) this.resumeType_ : Timestamp.DEFAULT_INSTANCE;
    }

    @Override // com.google.firestore.v1.TargetOrBuilder
    public ByteString getResumeToken() {
        return this.resumeTypeCase_ == 4 ? (ByteString) this.resumeType_ : ByteString.EMPTY;
    }

    @Override // com.google.firestore.v1.TargetOrBuilder
    public ResumeTypeCase getResumeTypeCase() {
        return ResumeTypeCase.forNumber(this.resumeTypeCase_);
    }

    @Override // com.google.firestore.v1.TargetOrBuilder
    public int getTargetId() {
        return this.targetId_;
    }

    @Override // com.google.firestore.v1.TargetOrBuilder
    public TargetTypeCase getTargetTypeCase() {
        return TargetTypeCase.forNumber(this.targetTypeCase_);
    }

    @Override // com.google.firestore.v1.TargetOrBuilder
    public boolean hasDocuments() {
        return this.targetTypeCase_ == 3;
    }

    @Override // com.google.firestore.v1.TargetOrBuilder
    public boolean hasQuery() {
        return this.targetTypeCase_ == 2;
    }

    @Override // com.google.firestore.v1.TargetOrBuilder
    public boolean hasReadTime() {
        return this.resumeTypeCase_ == 11;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Target, Builder> implements TargetOrBuilder {
        public Builder() {
            super(Target.DEFAULT_INSTANCE);
        }

        public Builder clearDocuments() {
            copyOnWrite();
            ((Target) this.instance).clearDocuments();
            return this;
        }

        public Builder clearOnce() {
            copyOnWrite();
            ((Target) this.instance).once_ = false;
            return this;
        }

        public Builder clearQuery() {
            copyOnWrite();
            ((Target) this.instance).clearQuery();
            return this;
        }

        public Builder clearReadTime() {
            copyOnWrite();
            ((Target) this.instance).clearReadTime();
            return this;
        }

        public Builder clearResumeToken() {
            copyOnWrite();
            ((Target) this.instance).clearResumeToken();
            return this;
        }

        public Builder clearResumeType() {
            copyOnWrite();
            ((Target) this.instance).clearResumeType();
            return this;
        }

        public Builder clearTargetId() {
            copyOnWrite();
            ((Target) this.instance).targetId_ = 0;
            return this;
        }

        public Builder clearTargetType() {
            copyOnWrite();
            ((Target) this.instance).clearTargetType();
            return this;
        }

        @Override // com.google.firestore.v1.TargetOrBuilder
        public DocumentsTarget getDocuments() {
            return ((Target) this.instance).getDocuments();
        }

        @Override // com.google.firestore.v1.TargetOrBuilder
        public boolean getOnce() {
            return ((Target) this.instance).getOnce();
        }

        @Override // com.google.firestore.v1.TargetOrBuilder
        public QueryTarget getQuery() {
            return ((Target) this.instance).getQuery();
        }

        @Override // com.google.firestore.v1.TargetOrBuilder
        public Timestamp getReadTime() {
            return ((Target) this.instance).getReadTime();
        }

        @Override // com.google.firestore.v1.TargetOrBuilder
        public ByteString getResumeToken() {
            return ((Target) this.instance).getResumeToken();
        }

        @Override // com.google.firestore.v1.TargetOrBuilder
        public ResumeTypeCase getResumeTypeCase() {
            return ((Target) this.instance).getResumeTypeCase();
        }

        @Override // com.google.firestore.v1.TargetOrBuilder
        public int getTargetId() {
            return ((Target) this.instance).getTargetId();
        }

        @Override // com.google.firestore.v1.TargetOrBuilder
        public TargetTypeCase getTargetTypeCase() {
            return ((Target) this.instance).getTargetTypeCase();
        }

        @Override // com.google.firestore.v1.TargetOrBuilder
        public boolean hasDocuments() {
            return ((Target) this.instance).hasDocuments();
        }

        @Override // com.google.firestore.v1.TargetOrBuilder
        public boolean hasQuery() {
            return ((Target) this.instance).hasQuery();
        }

        @Override // com.google.firestore.v1.TargetOrBuilder
        public boolean hasReadTime() {
            return ((Target) this.instance).hasReadTime();
        }

        public Builder mergeDocuments(DocumentsTarget documentsTarget) {
            copyOnWrite();
            ((Target) this.instance).mergeDocuments(documentsTarget);
            return this;
        }

        public Builder mergeQuery(QueryTarget queryTarget) {
            copyOnWrite();
            ((Target) this.instance).mergeQuery(queryTarget);
            return this;
        }

        public Builder mergeReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((Target) this.instance).mergeReadTime(timestamp);
            return this;
        }

        public Builder setDocuments(DocumentsTarget documentsTarget) {
            copyOnWrite();
            ((Target) this.instance).setDocuments(documentsTarget);
            return this;
        }

        public Builder setOnce(boolean z) {
            copyOnWrite();
            ((Target) this.instance).once_ = z;
            return this;
        }

        public Builder setQuery(QueryTarget queryTarget) {
            copyOnWrite();
            ((Target) this.instance).setQuery(queryTarget);
            return this;
        }

        public Builder setReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((Target) this.instance).setReadTime(timestamp);
            return this;
        }

        public Builder setResumeToken(ByteString byteString) {
            copyOnWrite();
            ((Target) this.instance).setResumeToken(byteString);
            return this;
        }

        public Builder setTargetId(int i) {
            copyOnWrite();
            ((Target) this.instance).targetId_ = i;
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Target.DEFAULT_INSTANCE);
        }

        public Builder setDocuments(DocumentsTarget.Builder builder) {
            copyOnWrite();
            ((Target) this.instance).setDocuments(builder.build());
            return this;
        }

        public Builder setQuery(QueryTarget.Builder builder) {
            copyOnWrite();
            ((Target) this.instance).setQuery(builder.build());
            return this;
        }

        public Builder setReadTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((Target) this.instance).setReadTime(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(Target target) {
        return DEFAULT_INSTANCE.createBuilder(target);
    }

    public static Target parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Target) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Target parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Target parseFrom(ByteString byteString) {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Target parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Target parseFrom(byte[] bArr) {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Target parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Target parseFrom(InputStream inputStream) {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Target parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Target parseFrom(CodedInputStream codedInputStream) {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Target parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
