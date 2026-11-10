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
public final class DocumentDelete extends GeneratedMessageLite<DocumentDelete, Builder> implements DocumentDeleteOrBuilder {
    public static final DocumentDelete DEFAULT_INSTANCE;
    public static final int DOCUMENT_FIELD_NUMBER = 1;
    public static volatile Parser<DocumentDelete> PARSER = null;
    public static final int READ_TIME_FIELD_NUMBER = 4;
    public static final int REMOVED_TARGET_IDS_FIELD_NUMBER = 6;
    public Timestamp readTime_;
    public int removedTargetIdsMemoizedSerializedSize = -1;
    public String document_ = "";
    public Internal.IntList removedTargetIds_ = GeneratedMessageLite.emptyIntList();

    /* JADX INFO: renamed from: com.google.firestore.v1.DocumentDelete$1, reason: invalid class name */
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
        DocumentDelete documentDelete = new DocumentDelete();
        DEFAULT_INSTANCE = documentDelete;
        GeneratedMessageLite.defaultInstanceMap.put(DocumentDelete.class, documentDelete);
    }

    public static /* synthetic */ void access$400(DocumentDelete documentDelete, int i, int i2) {
        documentDelete.ensureRemovedTargetIdsIsMutable();
        documentDelete.removedTargetIds_.setInt(i, i2);
    }

    public static /* synthetic */ void access$500(DocumentDelete documentDelete, int i) {
        documentDelete.ensureRemovedTargetIdsIsMutable();
        documentDelete.removedTargetIds_.addInt(i);
    }

    public static /* synthetic */ void access$600(DocumentDelete documentDelete, Iterable iterable) {
        documentDelete.ensureRemovedTargetIdsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) documentDelete.removedTargetIds_);
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

    public static DocumentDelete getDefaultInstance() {
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

    public static DocumentDelete parseDelimitedFrom(InputStream inputStream) {
        return (DocumentDelete) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DocumentDelete parseFrom(ByteBuffer byteBuffer) {
        return (DocumentDelete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<DocumentDelete> parser() {
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0006\u0003\u0000\u0001\u0000\u0001Ȉ\u0004\t\u0006'", new Object[]{"document_", "readTime_", "removedTargetIds_"});
            case NEW_MUTABLE_INSTANCE:
                return new DocumentDelete();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<DocumentDelete> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (DocumentDelete.class) {
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

    @Override // com.google.firestore.v1.DocumentDeleteOrBuilder
    public String getDocument() {
        return this.document_;
    }

    @Override // com.google.firestore.v1.DocumentDeleteOrBuilder
    public ByteString getDocumentBytes() {
        return ByteString.copyFromUtf8(this.document_);
    }

    @Override // com.google.firestore.v1.DocumentDeleteOrBuilder
    public Timestamp getReadTime() {
        Timestamp timestamp = this.readTime_;
        return timestamp == null ? Timestamp.DEFAULT_INSTANCE : timestamp;
    }

    @Override // com.google.firestore.v1.DocumentDeleteOrBuilder
    public int getRemovedTargetIds(int i) {
        return this.removedTargetIds_.getInt(i);
    }

    @Override // com.google.firestore.v1.DocumentDeleteOrBuilder
    public int getRemovedTargetIdsCount() {
        return this.removedTargetIds_.size();
    }

    @Override // com.google.firestore.v1.DocumentDeleteOrBuilder
    public List<Integer> getRemovedTargetIdsList() {
        return this.removedTargetIds_;
    }

    @Override // com.google.firestore.v1.DocumentDeleteOrBuilder
    public boolean hasReadTime() {
        return this.readTime_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DocumentDelete, Builder> implements DocumentDeleteOrBuilder {
        public Builder() {
            super(DocumentDelete.DEFAULT_INSTANCE);
        }

        public Builder addAllRemovedTargetIds(Iterable<? extends Integer> iterable) {
            copyOnWrite();
            DocumentDelete.access$600((DocumentDelete) this.instance, iterable);
            return this;
        }

        public Builder addRemovedTargetIds(int i) {
            copyOnWrite();
            DocumentDelete.access$500((DocumentDelete) this.instance, i);
            return this;
        }

        public Builder clearDocument() {
            copyOnWrite();
            ((DocumentDelete) this.instance).clearDocument();
            return this;
        }

        public Builder clearReadTime() {
            copyOnWrite();
            ((DocumentDelete) this.instance).readTime_ = null;
            return this;
        }

        public Builder clearRemovedTargetIds() {
            copyOnWrite();
            ((DocumentDelete) this.instance).clearRemovedTargetIds();
            return this;
        }

        @Override // com.google.firestore.v1.DocumentDeleteOrBuilder
        public String getDocument() {
            return ((DocumentDelete) this.instance).getDocument();
        }

        @Override // com.google.firestore.v1.DocumentDeleteOrBuilder
        public ByteString getDocumentBytes() {
            return ((DocumentDelete) this.instance).getDocumentBytes();
        }

        @Override // com.google.firestore.v1.DocumentDeleteOrBuilder
        public Timestamp getReadTime() {
            return ((DocumentDelete) this.instance).getReadTime();
        }

        @Override // com.google.firestore.v1.DocumentDeleteOrBuilder
        public int getRemovedTargetIds(int i) {
            return ((DocumentDelete) this.instance).getRemovedTargetIds(i);
        }

        @Override // com.google.firestore.v1.DocumentDeleteOrBuilder
        public int getRemovedTargetIdsCount() {
            return ((DocumentDelete) this.instance).getRemovedTargetIdsCount();
        }

        @Override // com.google.firestore.v1.DocumentDeleteOrBuilder
        public List<Integer> getRemovedTargetIdsList() {
            return Collections.unmodifiableList(((DocumentDelete) this.instance).getRemovedTargetIdsList());
        }

        @Override // com.google.firestore.v1.DocumentDeleteOrBuilder
        public boolean hasReadTime() {
            return ((DocumentDelete) this.instance).hasReadTime();
        }

        public Builder mergeReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((DocumentDelete) this.instance).mergeReadTime(timestamp);
            return this;
        }

        public Builder setDocument(String str) {
            copyOnWrite();
            ((DocumentDelete) this.instance).setDocument(str);
            return this;
        }

        public Builder setDocumentBytes(ByteString byteString) {
            copyOnWrite();
            ((DocumentDelete) this.instance).setDocumentBytes(byteString);
            return this;
        }

        public Builder setReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((DocumentDelete) this.instance).setReadTime(timestamp);
            return this;
        }

        public Builder setRemovedTargetIds(int i, int i2) {
            copyOnWrite();
            DocumentDelete.access$400((DocumentDelete) this.instance, i, i2);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(DocumentDelete.DEFAULT_INSTANCE);
        }

        public Builder setReadTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((DocumentDelete) this.instance).setReadTime(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(DocumentDelete documentDelete) {
        return DEFAULT_INSTANCE.createBuilder(documentDelete);
    }

    public static DocumentDelete parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (DocumentDelete) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DocumentDelete parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (DocumentDelete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static DocumentDelete parseFrom(ByteString byteString) {
        return (DocumentDelete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static DocumentDelete parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (DocumentDelete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static DocumentDelete parseFrom(byte[] bArr) {
        return (DocumentDelete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static DocumentDelete parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (DocumentDelete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static DocumentDelete parseFrom(InputStream inputStream) {
        return (DocumentDelete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DocumentDelete parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (DocumentDelete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DocumentDelete parseFrom(CodedInputStream codedInputStream) {
        return (DocumentDelete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static DocumentDelete parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (DocumentDelete) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
