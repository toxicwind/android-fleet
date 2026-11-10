package com.google.firestore.bundle;

import com.google.firestore.v1.Document;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class BundleElement extends GeneratedMessageLite<BundleElement, Builder> implements BundleElementOrBuilder {
    public static final BundleElement DEFAULT_INSTANCE;
    public static final int DOCUMENT_FIELD_NUMBER = 4;
    public static final int DOCUMENT_METADATA_FIELD_NUMBER = 3;
    public static final int METADATA_FIELD_NUMBER = 1;
    public static final int NAMED_QUERY_FIELD_NUMBER = 2;
    public static volatile Parser<BundleElement> PARSER;
    public int elementTypeCase_ = 0;
    public Object elementType_;

    /* JADX INFO: renamed from: com.google.firestore.bundle.BundleElement$1, reason: invalid class name */
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

    public enum ElementTypeCase {
        METADATA(1),
        NAMED_QUERY(2),
        DOCUMENT_METADATA(3),
        DOCUMENT(4),
        ELEMENTTYPE_NOT_SET(0);

        public final int value;

        ElementTypeCase(int i) {
            this.value = i;
        }

        public static ElementTypeCase forNumber(int i) {
            if (i == 0) {
                return ELEMENTTYPE_NOT_SET;
            }
            if (i == 1) {
                return METADATA;
            }
            if (i == 2) {
                return NAMED_QUERY;
            }
            if (i == 3) {
                return DOCUMENT_METADATA;
            }
            if (i != 4) {
                return null;
            }
            return DOCUMENT;
        }

        public int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ElementTypeCase valueOf(int i) {
            return forNumber(i);
        }
    }

    static {
        BundleElement bundleElement = new BundleElement();
        DEFAULT_INSTANCE = bundleElement;
        GeneratedMessageLite.defaultInstanceMap.put(BundleElement.class, bundleElement);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocument() {
        if (this.elementTypeCase_ == 4) {
            this.elementTypeCase_ = 0;
            this.elementType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocumentMetadata() {
        if (this.elementTypeCase_ == 3) {
            this.elementTypeCase_ = 0;
            this.elementType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearElementType() {
        this.elementTypeCase_ = 0;
        this.elementType_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMetadata() {
        if (this.elementTypeCase_ == 1) {
            this.elementTypeCase_ = 0;
            this.elementType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNamedQuery() {
        if (this.elementTypeCase_ == 2) {
            this.elementTypeCase_ = 0;
            this.elementType_ = null;
        }
    }

    public static BundleElement getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeDocument(Document document) {
        Object obj;
        document.getClass();
        if (this.elementTypeCase_ != 4 || (obj = this.elementType_) == Document.DEFAULT_INSTANCE) {
            this.elementType_ = document;
        } else {
            this.elementType_ = Document.newBuilder((Document) obj).mergeFrom(document).buildPartial();
        }
        this.elementTypeCase_ = 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeDocumentMetadata(BundledDocumentMetadata bundledDocumentMetadata) {
        Object obj;
        bundledDocumentMetadata.getClass();
        if (this.elementTypeCase_ != 3 || (obj = this.elementType_) == BundledDocumentMetadata.DEFAULT_INSTANCE) {
            this.elementType_ = bundledDocumentMetadata;
        } else {
            this.elementType_ = BundledDocumentMetadata.newBuilder((BundledDocumentMetadata) obj).mergeFrom(bundledDocumentMetadata).buildPartial();
        }
        this.elementTypeCase_ = 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeMetadata(BundleMetadata bundleMetadata) {
        Object obj;
        bundleMetadata.getClass();
        if (this.elementTypeCase_ != 1 || (obj = this.elementType_) == BundleMetadata.DEFAULT_INSTANCE) {
            this.elementType_ = bundleMetadata;
        } else {
            this.elementType_ = BundleMetadata.newBuilder((BundleMetadata) obj).mergeFrom(bundleMetadata).buildPartial();
        }
        this.elementTypeCase_ = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeNamedQuery(NamedQuery namedQuery) {
        Object obj;
        namedQuery.getClass();
        if (this.elementTypeCase_ != 2 || (obj = this.elementType_) == NamedQuery.DEFAULT_INSTANCE) {
            this.elementType_ = namedQuery;
        } else {
            this.elementType_ = NamedQuery.newBuilder((NamedQuery) obj).mergeFrom(namedQuery).buildPartial();
        }
        this.elementTypeCase_ = 2;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static BundleElement parseDelimitedFrom(InputStream inputStream) {
        return (BundleElement) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BundleElement parseFrom(ByteBuffer byteBuffer) {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<BundleElement> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocument(Document document) {
        document.getClass();
        this.elementType_ = document;
        this.elementTypeCase_ = 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocumentMetadata(BundledDocumentMetadata bundledDocumentMetadata) {
        bundledDocumentMetadata.getClass();
        this.elementType_ = bundledDocumentMetadata;
        this.elementTypeCase_ = 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMetadata(BundleMetadata bundleMetadata) {
        bundleMetadata.getClass();
        this.elementType_ = bundleMetadata;
        this.elementTypeCase_ = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNamedQuery(NamedQuery namedQuery) {
        namedQuery.getClass();
        this.elementType_ = namedQuery;
        this.elementTypeCase_ = 2;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0001\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000\u0003<\u0000\u0004<\u0000", new Object[]{"elementType_", "elementTypeCase_", BundleMetadata.class, NamedQuery.class, BundledDocumentMetadata.class, Document.class});
            case NEW_MUTABLE_INSTANCE:
                return new BundleElement();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<BundleElement> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (BundleElement.class) {
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

    @Override // com.google.firestore.bundle.BundleElementOrBuilder
    public Document getDocument() {
        return this.elementTypeCase_ == 4 ? (Document) this.elementType_ : Document.DEFAULT_INSTANCE;
    }

    @Override // com.google.firestore.bundle.BundleElementOrBuilder
    public BundledDocumentMetadata getDocumentMetadata() {
        return this.elementTypeCase_ == 3 ? (BundledDocumentMetadata) this.elementType_ : BundledDocumentMetadata.DEFAULT_INSTANCE;
    }

    @Override // com.google.firestore.bundle.BundleElementOrBuilder
    public ElementTypeCase getElementTypeCase() {
        return ElementTypeCase.forNumber(this.elementTypeCase_);
    }

    @Override // com.google.firestore.bundle.BundleElementOrBuilder
    public BundleMetadata getMetadata() {
        return this.elementTypeCase_ == 1 ? (BundleMetadata) this.elementType_ : BundleMetadata.DEFAULT_INSTANCE;
    }

    @Override // com.google.firestore.bundle.BundleElementOrBuilder
    public NamedQuery getNamedQuery() {
        return this.elementTypeCase_ == 2 ? (NamedQuery) this.elementType_ : NamedQuery.DEFAULT_INSTANCE;
    }

    @Override // com.google.firestore.bundle.BundleElementOrBuilder
    public boolean hasDocument() {
        return this.elementTypeCase_ == 4;
    }

    @Override // com.google.firestore.bundle.BundleElementOrBuilder
    public boolean hasDocumentMetadata() {
        return this.elementTypeCase_ == 3;
    }

    @Override // com.google.firestore.bundle.BundleElementOrBuilder
    public boolean hasMetadata() {
        return this.elementTypeCase_ == 1;
    }

    @Override // com.google.firestore.bundle.BundleElementOrBuilder
    public boolean hasNamedQuery() {
        return this.elementTypeCase_ == 2;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BundleElement, Builder> implements BundleElementOrBuilder {
        public Builder() {
            super(BundleElement.DEFAULT_INSTANCE);
        }

        public Builder clearDocument() {
            copyOnWrite();
            ((BundleElement) this.instance).clearDocument();
            return this;
        }

        public Builder clearDocumentMetadata() {
            copyOnWrite();
            ((BundleElement) this.instance).clearDocumentMetadata();
            return this;
        }

        public Builder clearElementType() {
            copyOnWrite();
            ((BundleElement) this.instance).clearElementType();
            return this;
        }

        public Builder clearMetadata() {
            copyOnWrite();
            ((BundleElement) this.instance).clearMetadata();
            return this;
        }

        public Builder clearNamedQuery() {
            copyOnWrite();
            ((BundleElement) this.instance).clearNamedQuery();
            return this;
        }

        @Override // com.google.firestore.bundle.BundleElementOrBuilder
        public Document getDocument() {
            return ((BundleElement) this.instance).getDocument();
        }

        @Override // com.google.firestore.bundle.BundleElementOrBuilder
        public BundledDocumentMetadata getDocumentMetadata() {
            return ((BundleElement) this.instance).getDocumentMetadata();
        }

        @Override // com.google.firestore.bundle.BundleElementOrBuilder
        public ElementTypeCase getElementTypeCase() {
            return ((BundleElement) this.instance).getElementTypeCase();
        }

        @Override // com.google.firestore.bundle.BundleElementOrBuilder
        public BundleMetadata getMetadata() {
            return ((BundleElement) this.instance).getMetadata();
        }

        @Override // com.google.firestore.bundle.BundleElementOrBuilder
        public NamedQuery getNamedQuery() {
            return ((BundleElement) this.instance).getNamedQuery();
        }

        @Override // com.google.firestore.bundle.BundleElementOrBuilder
        public boolean hasDocument() {
            return ((BundleElement) this.instance).hasDocument();
        }

        @Override // com.google.firestore.bundle.BundleElementOrBuilder
        public boolean hasDocumentMetadata() {
            return ((BundleElement) this.instance).hasDocumentMetadata();
        }

        @Override // com.google.firestore.bundle.BundleElementOrBuilder
        public boolean hasMetadata() {
            return ((BundleElement) this.instance).hasMetadata();
        }

        @Override // com.google.firestore.bundle.BundleElementOrBuilder
        public boolean hasNamedQuery() {
            return ((BundleElement) this.instance).hasNamedQuery();
        }

        public Builder mergeDocument(Document document) {
            copyOnWrite();
            ((BundleElement) this.instance).mergeDocument(document);
            return this;
        }

        public Builder mergeDocumentMetadata(BundledDocumentMetadata bundledDocumentMetadata) {
            copyOnWrite();
            ((BundleElement) this.instance).mergeDocumentMetadata(bundledDocumentMetadata);
            return this;
        }

        public Builder mergeMetadata(BundleMetadata bundleMetadata) {
            copyOnWrite();
            ((BundleElement) this.instance).mergeMetadata(bundleMetadata);
            return this;
        }

        public Builder mergeNamedQuery(NamedQuery namedQuery) {
            copyOnWrite();
            ((BundleElement) this.instance).mergeNamedQuery(namedQuery);
            return this;
        }

        public Builder setDocument(Document document) {
            copyOnWrite();
            ((BundleElement) this.instance).setDocument(document);
            return this;
        }

        public Builder setDocumentMetadata(BundledDocumentMetadata bundledDocumentMetadata) {
            copyOnWrite();
            ((BundleElement) this.instance).setDocumentMetadata(bundledDocumentMetadata);
            return this;
        }

        public Builder setMetadata(BundleMetadata bundleMetadata) {
            copyOnWrite();
            ((BundleElement) this.instance).setMetadata(bundleMetadata);
            return this;
        }

        public Builder setNamedQuery(NamedQuery namedQuery) {
            copyOnWrite();
            ((BundleElement) this.instance).setNamedQuery(namedQuery);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(BundleElement.DEFAULT_INSTANCE);
        }

        public Builder setDocument(Document.Builder builder) {
            copyOnWrite();
            ((BundleElement) this.instance).setDocument(builder.build());
            return this;
        }

        public Builder setDocumentMetadata(BundledDocumentMetadata.Builder builder) {
            copyOnWrite();
            ((BundleElement) this.instance).setDocumentMetadata(builder.build());
            return this;
        }

        public Builder setMetadata(BundleMetadata.Builder builder) {
            copyOnWrite();
            ((BundleElement) this.instance).setMetadata(builder.build());
            return this;
        }

        public Builder setNamedQuery(NamedQuery.Builder builder) {
            copyOnWrite();
            ((BundleElement) this.instance).setNamedQuery(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(BundleElement bundleElement) {
        return DEFAULT_INSTANCE.createBuilder(bundleElement);
    }

    public static BundleElement parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (BundleElement) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BundleElement parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static BundleElement parseFrom(ByteString byteString) {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static BundleElement parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static BundleElement parseFrom(byte[] bArr) {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static BundleElement parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static BundleElement parseFrom(InputStream inputStream) {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BundleElement parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BundleElement parseFrom(CodedInputStream codedInputStream) {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static BundleElement parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (BundleElement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
