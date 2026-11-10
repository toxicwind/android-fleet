package com.google.firestore.v1;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class DocumentChange extends GeneratedMessageLite<DocumentChange, Builder> implements DocumentChangeOrBuilder {
    public static final DocumentChange DEFAULT_INSTANCE;
    public static final int DOCUMENT_FIELD_NUMBER = 1;
    public static volatile Parser<DocumentChange> PARSER = null;
    public static final int REMOVED_TARGET_IDS_FIELD_NUMBER = 6;
    public static final int TARGET_IDS_FIELD_NUMBER = 5;
    public Document document_;
    public int targetIdsMemoizedSerializedSize = -1;
    public int removedTargetIdsMemoizedSerializedSize = -1;
    public Internal.IntList targetIds_ = GeneratedMessageLite.emptyIntList();
    public Internal.IntList removedTargetIds_ = GeneratedMessageLite.emptyIntList();

    /* JADX INFO: renamed from: com.google.firestore.v1.DocumentChange$1, reason: invalid class name */
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
        DocumentChange documentChange = new DocumentChange();
        DEFAULT_INSTANCE = documentChange;
        GeneratedMessageLite.defaultInstanceMap.put(DocumentChange.class, documentChange);
    }

    public static /* synthetic */ void access$1000(DocumentChange documentChange, Iterable iterable) {
        documentChange.ensureRemovedTargetIdsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) documentChange.removedTargetIds_);
    }

    public static /* synthetic */ void access$400(DocumentChange documentChange, int i, int i2) {
        documentChange.ensureTargetIdsIsMutable();
        documentChange.targetIds_.setInt(i, i2);
    }

    public static /* synthetic */ void access$500(DocumentChange documentChange, int i) {
        documentChange.ensureTargetIdsIsMutable();
        documentChange.targetIds_.addInt(i);
    }

    public static /* synthetic */ void access$600(DocumentChange documentChange, Iterable iterable) {
        documentChange.ensureTargetIdsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) documentChange.targetIds_);
    }

    public static /* synthetic */ void access$800(DocumentChange documentChange, int i, int i2) {
        documentChange.ensureRemovedTargetIdsIsMutable();
        documentChange.removedTargetIds_.setInt(i, i2);
    }

    public static /* synthetic */ void access$900(DocumentChange documentChange, int i) {
        documentChange.ensureRemovedTargetIdsIsMutable();
        documentChange.removedTargetIds_.addInt(i);
    }

    private void addAllRemovedTargetIds(Iterable<? extends Integer> iterable) {
        ensureRemovedTargetIdsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.removedTargetIds_);
    }

    private void addAllTargetIds(Iterable<? extends Integer> iterable) {
        ensureTargetIdsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.targetIds_);
    }

    private void addRemovedTargetIds(int i) {
        ensureRemovedTargetIdsIsMutable();
        this.removedTargetIds_.addInt(i);
    }

    private void addTargetIds(int i) {
        ensureTargetIdsIsMutable();
        this.targetIds_.addInt(i);
    }

    private void clearDocument() {
        this.document_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearRemovedTargetIds() {
        this.removedTargetIds_ = GeneratedMessageLite.emptyIntList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTargetIds() {
        this.targetIds_ = GeneratedMessageLite.emptyIntList();
    }

    private void ensureRemovedTargetIdsIsMutable() {
        Internal.IntList intList = this.removedTargetIds_;
        if (intList.isModifiable()) {
            return;
        }
        this.removedTargetIds_ = GeneratedMessageLite.mutableCopy(intList);
    }

    private void ensureTargetIdsIsMutable() {
        Internal.IntList intList = this.targetIds_;
        if (intList.isModifiable()) {
            return;
        }
        this.targetIds_ = GeneratedMessageLite.mutableCopy(intList);
    }

    public static DocumentChange getDefaultInstance() {
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

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static DocumentChange parseDelimitedFrom(InputStream inputStream) {
        return (DocumentChange) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DocumentChange parseFrom(ByteBuffer byteBuffer) {
        return (DocumentChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<DocumentChange> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocument(Document document) {
        document.getClass();
        this.document_ = document;
    }

    private void setRemovedTargetIds(int i, int i2) {
        ensureRemovedTargetIdsIsMutable();
        this.removedTargetIds_.setInt(i, i2);
    }

    private void setTargetIds(int i, int i2) {
        ensureTargetIdsIsMutable();
        this.targetIds_.setInt(i, i2);
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0006\u0003\u0000\u0002\u0000\u0001\t\u0005'\u0006'", new Object[]{"document_", "targetIds_", "removedTargetIds_"});
            case NEW_MUTABLE_INSTANCE:
                return new DocumentChange();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<DocumentChange> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (DocumentChange.class) {
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

    @Override // com.google.firestore.v1.DocumentChangeOrBuilder
    public Document getDocument() {
        Document document = this.document_;
        return document == null ? Document.DEFAULT_INSTANCE : document;
    }

    @Override // com.google.firestore.v1.DocumentChangeOrBuilder
    public int getRemovedTargetIds(int i) {
        return this.removedTargetIds_.getInt(i);
    }

    @Override // com.google.firestore.v1.DocumentChangeOrBuilder
    public int getRemovedTargetIdsCount() {
        return this.removedTargetIds_.size();
    }

    @Override // com.google.firestore.v1.DocumentChangeOrBuilder
    public List<Integer> getRemovedTargetIdsList() {
        return this.removedTargetIds_;
    }

    @Override // com.google.firestore.v1.DocumentChangeOrBuilder
    public int getTargetIds(int i) {
        return this.targetIds_.getInt(i);
    }

    @Override // com.google.firestore.v1.DocumentChangeOrBuilder
    public int getTargetIdsCount() {
        return this.targetIds_.size();
    }

    @Override // com.google.firestore.v1.DocumentChangeOrBuilder
    public List<Integer> getTargetIdsList() {
        return this.targetIds_;
    }

    @Override // com.google.firestore.v1.DocumentChangeOrBuilder
    public boolean hasDocument() {
        return this.document_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DocumentChange, Builder> implements DocumentChangeOrBuilder {
        public Builder() {
            super(DocumentChange.DEFAULT_INSTANCE);
        }

        public Builder addAllRemovedTargetIds(Iterable<? extends Integer> iterable) {
            copyOnWrite();
            DocumentChange.access$1000((DocumentChange) this.instance, iterable);
            return this;
        }

        public Builder addAllTargetIds(Iterable<? extends Integer> iterable) {
            copyOnWrite();
            DocumentChange.access$600((DocumentChange) this.instance, iterable);
            return this;
        }

        public Builder addRemovedTargetIds(int i) {
            copyOnWrite();
            DocumentChange.access$900((DocumentChange) this.instance, i);
            return this;
        }

        public Builder addTargetIds(int i) {
            copyOnWrite();
            DocumentChange.access$500((DocumentChange) this.instance, i);
            return this;
        }

        public Builder clearDocument() {
            copyOnWrite();
            ((DocumentChange) this.instance).document_ = null;
            return this;
        }

        public Builder clearRemovedTargetIds() {
            copyOnWrite();
            ((DocumentChange) this.instance).clearRemovedTargetIds();
            return this;
        }

        public Builder clearTargetIds() {
            copyOnWrite();
            ((DocumentChange) this.instance).clearTargetIds();
            return this;
        }

        @Override // com.google.firestore.v1.DocumentChangeOrBuilder
        public Document getDocument() {
            return ((DocumentChange) this.instance).getDocument();
        }

        @Override // com.google.firestore.v1.DocumentChangeOrBuilder
        public int getRemovedTargetIds(int i) {
            return ((DocumentChange) this.instance).getRemovedTargetIds(i);
        }

        @Override // com.google.firestore.v1.DocumentChangeOrBuilder
        public int getRemovedTargetIdsCount() {
            return ((DocumentChange) this.instance).getRemovedTargetIdsCount();
        }

        @Override // com.google.firestore.v1.DocumentChangeOrBuilder
        public List<Integer> getRemovedTargetIdsList() {
            return Collections.unmodifiableList(((DocumentChange) this.instance).getRemovedTargetIdsList());
        }

        @Override // com.google.firestore.v1.DocumentChangeOrBuilder
        public int getTargetIds(int i) {
            return ((DocumentChange) this.instance).getTargetIds(i);
        }

        @Override // com.google.firestore.v1.DocumentChangeOrBuilder
        public int getTargetIdsCount() {
            return ((DocumentChange) this.instance).getTargetIdsCount();
        }

        @Override // com.google.firestore.v1.DocumentChangeOrBuilder
        public List<Integer> getTargetIdsList() {
            return Collections.unmodifiableList(((DocumentChange) this.instance).getTargetIdsList());
        }

        @Override // com.google.firestore.v1.DocumentChangeOrBuilder
        public boolean hasDocument() {
            return ((DocumentChange) this.instance).hasDocument();
        }

        public Builder mergeDocument(Document document) {
            copyOnWrite();
            ((DocumentChange) this.instance).mergeDocument(document);
            return this;
        }

        public Builder setDocument(Document document) {
            copyOnWrite();
            ((DocumentChange) this.instance).setDocument(document);
            return this;
        }

        public Builder setRemovedTargetIds(int i, int i2) {
            copyOnWrite();
            DocumentChange.access$800((DocumentChange) this.instance, i, i2);
            return this;
        }

        public Builder setTargetIds(int i, int i2) {
            copyOnWrite();
            DocumentChange.access$400((DocumentChange) this.instance, i, i2);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(DocumentChange.DEFAULT_INSTANCE);
        }

        public Builder setDocument(Document.Builder builder) {
            copyOnWrite();
            ((DocumentChange) this.instance).setDocument(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(DocumentChange documentChange) {
        return DEFAULT_INSTANCE.createBuilder(documentChange);
    }

    public static DocumentChange parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (DocumentChange) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DocumentChange parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (DocumentChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static DocumentChange parseFrom(ByteString byteString) {
        return (DocumentChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static DocumentChange parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (DocumentChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static DocumentChange parseFrom(byte[] bArr) {
        return (DocumentChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static DocumentChange parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (DocumentChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static DocumentChange parseFrom(InputStream inputStream) {
        return (DocumentChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DocumentChange parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (DocumentChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DocumentChange parseFrom(CodedInputStream codedInputStream) {
        return (DocumentChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static DocumentChange parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (DocumentChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
