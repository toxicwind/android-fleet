package com.google.firestore.v1;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class DocumentRemove extends GeneratedMessageLite<DocumentRemove, Builder> implements DocumentRemoveOrBuilder {
    public static final DocumentRemove DEFAULT_INSTANCE;
    public static final int DOCUMENT_FIELD_NUMBER = 1;
    public static volatile Parser<DocumentRemove> PARSER = null;
    public static final int READ_TIME_FIELD_NUMBER = 4;
    public static final int REMOVED_TARGET_IDS_FIELD_NUMBER = 2;
    public Timestamp readTime_;
    public int removedTargetIdsMemoizedSerializedSize = -1;
    public String document_ = "";
    public Internal.IntList removedTargetIds_ = GeneratedMessageLite.emptyIntList();

    /* JADX INFO: renamed from: com.google.firestore.v1.DocumentRemove$1, reason: invalid class name */
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
        DocumentRemove documentRemove = new DocumentRemove();
        DEFAULT_INSTANCE = documentRemove;
        GeneratedMessageLite.defaultInstanceMap.put(DocumentRemove.class, documentRemove);
    }

    public static /* synthetic */ void access$400(DocumentRemove documentRemove, int i, int i2) {
        documentRemove.ensureRemovedTargetIdsIsMutable();
        documentRemove.removedTargetIds_.setInt(i, i2);
    }

    public static /* synthetic */ void access$500(DocumentRemove documentRemove, int i) {
        documentRemove.ensureRemovedTargetIdsIsMutable();
        documentRemove.removedTargetIds_.addInt(i);
    }

    public static /* synthetic */ void access$600(DocumentRemove documentRemove, Iterable iterable) {
        documentRemove.ensureRemovedTargetIdsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) documentRemove.removedTargetIds_);
    }

    private void addAllRemovedTargetIds(Iterable<? extends Integer> iterable) {
        ensureRemovedTargetIdsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.removedTargetIds_);
    }

    private void addRemovedTargetIds(int i) {
        ensureRemovedTargetIdsIsMutable();
        this.removedTargetIds_.addInt(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocument() {
        this.document_ = DEFAULT_INSTANCE.getDocument();
    }

    private void clearReadTime() {
        this.readTime_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearRemovedTargetIds() {
        this.removedTargetIds_ = GeneratedMessageLite.emptyIntList();
    }

    private void ensureRemovedTargetIdsIsMutable() {
        Internal.IntList intList = this.removedTargetIds_;
        if (intList.isModifiable()) {
            return;
        }
        this.removedTargetIds_ = GeneratedMessageLite.mutableCopy(intList);
    }

    public static DocumentRemove getDefaultInstance() {
        return DEFAULT_INSTANCE;
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

    public static DocumentRemove parseDelimitedFrom(InputStream inputStream) {
        return (DocumentRemove) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DocumentRemove parseFrom(ByteBuffer byteBuffer) {
        return (DocumentRemove) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<DocumentRemove> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocument(String str) {
        str.getClass();
        this.document_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocumentBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.document_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReadTime(Timestamp timestamp) {
        timestamp.getClass();
        this.readTime_ = timestamp;
    }

    private void setRemovedTargetIds(int i, int i2) {
        ensureRemovedTargetIdsIsMutable();
        this.removedTargetIds_.setInt(i, i2);
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0004\u0003\u0000\u0001\u0000\u0001Ȉ\u0002'\u0004\t", new Object[]{"document_", "removedTargetIds_", "readTime_"});
            case NEW_MUTABLE_INSTANCE:
                return new DocumentRemove();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<DocumentRemove> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (DocumentRemove.class) {
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

    @Override // com.google.firestore.v1.DocumentRemoveOrBuilder
    public String getDocument() {
        return this.document_;
    }

    @Override // com.google.firestore.v1.DocumentRemoveOrBuilder
    public ByteString getDocumentBytes() {
        return ByteString.copyFromUtf8(this.document_);
    }

    @Override // com.google.firestore.v1.DocumentRemoveOrBuilder
    public Timestamp getReadTime() {
        Timestamp timestamp = this.readTime_;
        return timestamp == null ? Timestamp.DEFAULT_INSTANCE : timestamp;
    }

    @Override // com.google.firestore.v1.DocumentRemoveOrBuilder
    public int getRemovedTargetIds(int i) {
        return this.removedTargetIds_.getInt(i);
    }

    @Override // com.google.firestore.v1.DocumentRemoveOrBuilder
    public int getRemovedTargetIdsCount() {
        return this.removedTargetIds_.size();
    }

    @Override // com.google.firestore.v1.DocumentRemoveOrBuilder
    public List<Integer> getRemovedTargetIdsList() {
        return this.removedTargetIds_;
    }

    @Override // com.google.firestore.v1.DocumentRemoveOrBuilder
    public boolean hasReadTime() {
        return this.readTime_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DocumentRemove, Builder> implements DocumentRemoveOrBuilder {
        public Builder() {
            super(DocumentRemove.DEFAULT_INSTANCE);
        }

        public Builder addAllRemovedTargetIds(Iterable<? extends Integer> iterable) {
            copyOnWrite();
            DocumentRemove.access$600((DocumentRemove) this.instance, iterable);
            return this;
        }

        public Builder addRemovedTargetIds(int i) {
            copyOnWrite();
            DocumentRemove.access$500((DocumentRemove) this.instance, i);
            return this;
        }

        public Builder clearDocument() {
            copyOnWrite();
            ((DocumentRemove) this.instance).clearDocument();
            return this;
        }

        public Builder clearReadTime() {
            copyOnWrite();
            ((DocumentRemove) this.instance).readTime_ = null;
            return this;
        }

        public Builder clearRemovedTargetIds() {
            copyOnWrite();
            ((DocumentRemove) this.instance).clearRemovedTargetIds();
            return this;
        }

        @Override // com.google.firestore.v1.DocumentRemoveOrBuilder
        public String getDocument() {
            return ((DocumentRemove) this.instance).getDocument();
        }

        @Override // com.google.firestore.v1.DocumentRemoveOrBuilder
        public ByteString getDocumentBytes() {
            return ((DocumentRemove) this.instance).getDocumentBytes();
        }

        @Override // com.google.firestore.v1.DocumentRemoveOrBuilder
        public Timestamp getReadTime() {
            return ((DocumentRemove) this.instance).getReadTime();
        }

        @Override // com.google.firestore.v1.DocumentRemoveOrBuilder
        public int getRemovedTargetIds(int i) {
            return ((DocumentRemove) this.instance).getRemovedTargetIds(i);
        }

        @Override // com.google.firestore.v1.DocumentRemoveOrBuilder
        public int getRemovedTargetIdsCount() {
            return ((DocumentRemove) this.instance).getRemovedTargetIdsCount();
        }

        @Override // com.google.firestore.v1.DocumentRemoveOrBuilder
        public List<Integer> getRemovedTargetIdsList() {
            return Collections.unmodifiableList(((DocumentRemove) this.instance).getRemovedTargetIdsList());
        }

        @Override // com.google.firestore.v1.DocumentRemoveOrBuilder
        public boolean hasReadTime() {
            return ((DocumentRemove) this.instance).hasReadTime();
        }

        public Builder mergeReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((DocumentRemove) this.instance).mergeReadTime(timestamp);
            return this;
        }

        public Builder setDocument(String str) {
            copyOnWrite();
            ((DocumentRemove) this.instance).setDocument(str);
            return this;
        }

        public Builder setDocumentBytes(ByteString byteString) {
            copyOnWrite();
            ((DocumentRemove) this.instance).setDocumentBytes(byteString);
            return this;
        }

        public Builder setReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((DocumentRemove) this.instance).setReadTime(timestamp);
            return this;
        }

        public Builder setRemovedTargetIds(int i, int i2) {
            copyOnWrite();
            DocumentRemove.access$400((DocumentRemove) this.instance, i, i2);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(DocumentRemove.DEFAULT_INSTANCE);
        }

        public Builder setReadTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((DocumentRemove) this.instance).setReadTime(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(DocumentRemove documentRemove) {
        return DEFAULT_INSTANCE.createBuilder(documentRemove);
    }

    public static DocumentRemove parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (DocumentRemove) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DocumentRemove parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (DocumentRemove) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static DocumentRemove parseFrom(ByteString byteString) {
        return (DocumentRemove) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static DocumentRemove parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (DocumentRemove) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static DocumentRemove parseFrom(byte[] bArr) {
        return (DocumentRemove) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static DocumentRemove parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (DocumentRemove) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static DocumentRemove parseFrom(InputStream inputStream) {
        return (DocumentRemove) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DocumentRemove parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (DocumentRemove) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DocumentRemove parseFrom(CodedInputStream codedInputStream) {
        return (DocumentRemove) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static DocumentRemove parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (DocumentRemove) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
