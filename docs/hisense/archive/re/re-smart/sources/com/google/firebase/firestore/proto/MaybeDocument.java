package com.google.firebase.firestore.proto;

import com.google.firestore.v1.Document;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class MaybeDocument extends GeneratedMessageLite<MaybeDocument, Builder> implements MaybeDocumentOrBuilder {
    public static final MaybeDocument DEFAULT_INSTANCE;
    public static final int DOCUMENT_FIELD_NUMBER = 2;
    public static final int HAS_COMMITTED_MUTATIONS_FIELD_NUMBER = 4;
    public static final int NO_DOCUMENT_FIELD_NUMBER = 1;
    public static volatile Parser<MaybeDocument> PARSER = null;
    public static final int UNKNOWN_DOCUMENT_FIELD_NUMBER = 3;
    public int documentTypeCase_ = 0;
    public Object documentType_;
    public boolean hasCommittedMutations_;

    /* JADX INFO: renamed from: com.google.firebase.firestore.proto.MaybeDocument$1, reason: invalid class name */
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

    public enum DocumentTypeCase {
        NO_DOCUMENT(1),
        DOCUMENT(2),
        UNKNOWN_DOCUMENT(3),
        DOCUMENTTYPE_NOT_SET(0);

        public final int value;

        DocumentTypeCase(int i) {
            this.value = i;
        }

        public static DocumentTypeCase forNumber(int i) {
            if (i == 0) {
                return DOCUMENTTYPE_NOT_SET;
            }
            if (i == 1) {
                return NO_DOCUMENT;
            }
            if (i == 2) {
                return DOCUMENT;
            }
            if (i != 3) {
                return null;
            }
            return UNKNOWN_DOCUMENT;
        }

        public int getNumber() {
            return this.value;
        }

        @Deprecated
        public static DocumentTypeCase valueOf(int i) {
            return forNumber(i);
        }
    }

    static {
        MaybeDocument maybeDocument = new MaybeDocument();
        DEFAULT_INSTANCE = maybeDocument;
        GeneratedMessageLite.defaultInstanceMap.put(MaybeDocument.class, maybeDocument);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocument() {
        if (this.documentTypeCase_ == 2) {
            this.documentTypeCase_ = 0;
            this.documentType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocumentType() {
        this.documentTypeCase_ = 0;
        this.documentType_ = null;
    }

    private void clearHasCommittedMutations() {
        this.hasCommittedMutations_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNoDocument() {
        if (this.documentTypeCase_ == 1) {
            this.documentTypeCase_ = 0;
            this.documentType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearUnknownDocument() {
        if (this.documentTypeCase_ == 3) {
            this.documentTypeCase_ = 0;
            this.documentType_ = null;
        }
    }

    public static MaybeDocument getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeDocument(Document document) {
        Object obj;
        document.getClass();
        if (this.documentTypeCase_ != 2 || (obj = this.documentType_) == Document.DEFAULT_INSTANCE) {
            this.documentType_ = document;
        } else {
            this.documentType_ = Document.newBuilder((Document) obj).mergeFrom(document).buildPartial();
        }
        this.documentTypeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeNoDocument(NoDocument noDocument) {
        Object obj;
        noDocument.getClass();
        if (this.documentTypeCase_ != 1 || (obj = this.documentType_) == NoDocument.DEFAULT_INSTANCE) {
            this.documentType_ = noDocument;
        } else {
            this.documentType_ = NoDocument.newBuilder((NoDocument) obj).mergeFrom(noDocument).buildPartial();
        }
        this.documentTypeCase_ = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeUnknownDocument(UnknownDocument unknownDocument) {
        Object obj;
        unknownDocument.getClass();
        if (this.documentTypeCase_ != 3 || (obj = this.documentType_) == UnknownDocument.DEFAULT_INSTANCE) {
            this.documentType_ = unknownDocument;
        } else {
            this.documentType_ = UnknownDocument.newBuilder((UnknownDocument) obj).mergeFrom(unknownDocument).buildPartial();
        }
        this.documentTypeCase_ = 3;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static MaybeDocument parseDelimitedFrom(InputStream inputStream) {
        return (MaybeDocument) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static MaybeDocument parseFrom(ByteBuffer byteBuffer) {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<MaybeDocument> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocument(Document document) {
        document.getClass();
        this.documentType_ = document;
        this.documentTypeCase_ = 2;
    }

    private void setHasCommittedMutations(boolean z) {
        this.hasCommittedMutations_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNoDocument(NoDocument noDocument) {
        noDocument.getClass();
        this.documentType_ = noDocument;
        this.documentTypeCase_ = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUnknownDocument(UnknownDocument unknownDocument) {
        unknownDocument.getClass();
        this.documentType_ = unknownDocument;
        this.documentTypeCase_ = 3;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0001\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000\u0003<\u0000\u0004\u0007", new Object[]{"documentType_", "documentTypeCase_", NoDocument.class, Document.class, UnknownDocument.class, "hasCommittedMutations_"});
            case NEW_MUTABLE_INSTANCE:
                return new MaybeDocument();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<MaybeDocument> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (MaybeDocument.class) {
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

    @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
    public Document getDocument() {
        return this.documentTypeCase_ == 2 ? (Document) this.documentType_ : Document.DEFAULT_INSTANCE;
    }

    @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
    public DocumentTypeCase getDocumentTypeCase() {
        return DocumentTypeCase.forNumber(this.documentTypeCase_);
    }

    @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
    public boolean getHasCommittedMutations() {
        return this.hasCommittedMutations_;
    }

    @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
    public NoDocument getNoDocument() {
        return this.documentTypeCase_ == 1 ? (NoDocument) this.documentType_ : NoDocument.DEFAULT_INSTANCE;
    }

    @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
    public UnknownDocument getUnknownDocument() {
        return this.documentTypeCase_ == 3 ? (UnknownDocument) this.documentType_ : UnknownDocument.DEFAULT_INSTANCE;
    }

    @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
    public boolean hasDocument() {
        return this.documentTypeCase_ == 2;
    }

    @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
    public boolean hasNoDocument() {
        return this.documentTypeCase_ == 1;
    }

    @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
    public boolean hasUnknownDocument() {
        return this.documentTypeCase_ == 3;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<MaybeDocument, Builder> implements MaybeDocumentOrBuilder {
        public Builder() {
            super(MaybeDocument.DEFAULT_INSTANCE);
        }

        public Builder clearDocument() {
            copyOnWrite();
            ((MaybeDocument) this.instance).clearDocument();
            return this;
        }

        public Builder clearDocumentType() {
            copyOnWrite();
            ((MaybeDocument) this.instance).clearDocumentType();
            return this;
        }

        public Builder clearHasCommittedMutations() {
            copyOnWrite();
            ((MaybeDocument) this.instance).hasCommittedMutations_ = false;
            return this;
        }

        public Builder clearNoDocument() {
            copyOnWrite();
            ((MaybeDocument) this.instance).clearNoDocument();
            return this;
        }

        public Builder clearUnknownDocument() {
            copyOnWrite();
            ((MaybeDocument) this.instance).clearUnknownDocument();
            return this;
        }

        @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
        public Document getDocument() {
            return ((MaybeDocument) this.instance).getDocument();
        }

        @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
        public DocumentTypeCase getDocumentTypeCase() {
            return ((MaybeDocument) this.instance).getDocumentTypeCase();
        }

        @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
        public boolean getHasCommittedMutations() {
            return ((MaybeDocument) this.instance).getHasCommittedMutations();
        }

        @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
        public NoDocument getNoDocument() {
            return ((MaybeDocument) this.instance).getNoDocument();
        }

        @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
        public UnknownDocument getUnknownDocument() {
            return ((MaybeDocument) this.instance).getUnknownDocument();
        }

        @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
        public boolean hasDocument() {
            return ((MaybeDocument) this.instance).hasDocument();
        }

        @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
        public boolean hasNoDocument() {
            return ((MaybeDocument) this.instance).hasNoDocument();
        }

        @Override // com.google.firebase.firestore.proto.MaybeDocumentOrBuilder
        public boolean hasUnknownDocument() {
            return ((MaybeDocument) this.instance).hasUnknownDocument();
        }

        public Builder mergeDocument(Document document) {
            copyOnWrite();
            ((MaybeDocument) this.instance).mergeDocument(document);
            return this;
        }

        public Builder mergeNoDocument(NoDocument noDocument) {
            copyOnWrite();
            ((MaybeDocument) this.instance).mergeNoDocument(noDocument);
            return this;
        }

        public Builder mergeUnknownDocument(UnknownDocument unknownDocument) {
            copyOnWrite();
            ((MaybeDocument) this.instance).mergeUnknownDocument(unknownDocument);
            return this;
        }

        public Builder setDocument(Document document) {
            copyOnWrite();
            ((MaybeDocument) this.instance).setDocument(document);
            return this;
        }

        public Builder setHasCommittedMutations(boolean z) {
            copyOnWrite();
            ((MaybeDocument) this.instance).hasCommittedMutations_ = z;
            return this;
        }

        public Builder setNoDocument(NoDocument noDocument) {
            copyOnWrite();
            ((MaybeDocument) this.instance).setNoDocument(noDocument);
            return this;
        }

        public Builder setUnknownDocument(UnknownDocument unknownDocument) {
            copyOnWrite();
            ((MaybeDocument) this.instance).setUnknownDocument(unknownDocument);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(MaybeDocument.DEFAULT_INSTANCE);
        }

        public Builder setDocument(Document.Builder builder) {
            copyOnWrite();
            ((MaybeDocument) this.instance).setDocument(builder.build());
            return this;
        }

        public Builder setNoDocument(NoDocument.Builder builder) {
            copyOnWrite();
            ((MaybeDocument) this.instance).setNoDocument(builder.build());
            return this;
        }

        public Builder setUnknownDocument(UnknownDocument.Builder builder) {
            copyOnWrite();
            ((MaybeDocument) this.instance).setUnknownDocument(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(MaybeDocument maybeDocument) {
        return DEFAULT_INSTANCE.createBuilder(maybeDocument);
    }

    public static MaybeDocument parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (MaybeDocument) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static MaybeDocument parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static MaybeDocument parseFrom(ByteString byteString) {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static MaybeDocument parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static MaybeDocument parseFrom(byte[] bArr) {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static MaybeDocument parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static MaybeDocument parseFrom(InputStream inputStream) {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static MaybeDocument parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static MaybeDocument parseFrom(CodedInputStream codedInputStream) {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static MaybeDocument parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (MaybeDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
