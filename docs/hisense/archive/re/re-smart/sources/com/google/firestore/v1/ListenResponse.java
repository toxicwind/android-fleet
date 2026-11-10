package com.google.firestore.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class ListenResponse extends GeneratedMessageLite<ListenResponse, Builder> implements ListenResponseOrBuilder {
    public static final ListenResponse DEFAULT_INSTANCE;
    public static final int DOCUMENT_CHANGE_FIELD_NUMBER = 3;
    public static final int DOCUMENT_DELETE_FIELD_NUMBER = 4;
    public static final int DOCUMENT_REMOVE_FIELD_NUMBER = 6;
    public static final int FILTER_FIELD_NUMBER = 5;
    public static volatile Parser<ListenResponse> PARSER = null;
    public static final int TARGET_CHANGE_FIELD_NUMBER = 2;
    public int responseTypeCase_ = 0;
    public Object responseType_;

    /* JADX INFO: renamed from: com.google.firestore.v1.ListenResponse$1, reason: invalid class name */
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

    public enum ResponseTypeCase {
        TARGET_CHANGE(2),
        DOCUMENT_CHANGE(3),
        DOCUMENT_DELETE(4),
        DOCUMENT_REMOVE(6),
        FILTER(5),
        RESPONSETYPE_NOT_SET(0);

        public final int value;

        ResponseTypeCase(int i) {
            this.value = i;
        }

        public static ResponseTypeCase forNumber(int i) {
            if (i == 0) {
                return RESPONSETYPE_NOT_SET;
            }
            if (i == 2) {
                return TARGET_CHANGE;
            }
            if (i == 3) {
                return DOCUMENT_CHANGE;
            }
            if (i == 4) {
                return DOCUMENT_DELETE;
            }
            if (i == 5) {
                return FILTER;
            }
            if (i != 6) {
                return null;
            }
            return DOCUMENT_REMOVE;
        }

        public int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ResponseTypeCase valueOf(int i) {
            return forNumber(i);
        }
    }

    static {
        ListenResponse listenResponse = new ListenResponse();
        DEFAULT_INSTANCE = listenResponse;
        GeneratedMessageLite.defaultInstanceMap.put(ListenResponse.class, listenResponse);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocumentChange() {
        if (this.responseTypeCase_ == 3) {
            this.responseTypeCase_ = 0;
            this.responseType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocumentDelete() {
        if (this.responseTypeCase_ == 4) {
            this.responseTypeCase_ = 0;
            this.responseType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocumentRemove() {
        if (this.responseTypeCase_ == 6) {
            this.responseTypeCase_ = 0;
            this.responseType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearFilter() {
        if (this.responseTypeCase_ == 5) {
            this.responseTypeCase_ = 0;
            this.responseType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearResponseType() {
        this.responseTypeCase_ = 0;
        this.responseType_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTargetChange() {
        if (this.responseTypeCase_ == 2) {
            this.responseTypeCase_ = 0;
            this.responseType_ = null;
        }
    }

    public static ListenResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeDocumentChange(DocumentChange documentChange) {
        Object obj;
        documentChange.getClass();
        if (this.responseTypeCase_ != 3 || (obj = this.responseType_) == DocumentChange.DEFAULT_INSTANCE) {
            this.responseType_ = documentChange;
        } else {
            this.responseType_ = DocumentChange.newBuilder((DocumentChange) obj).mergeFrom(documentChange).buildPartial();
        }
        this.responseTypeCase_ = 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeDocumentDelete(DocumentDelete documentDelete) {
        Object obj;
        documentDelete.getClass();
        if (this.responseTypeCase_ != 4 || (obj = this.responseType_) == DocumentDelete.DEFAULT_INSTANCE) {
            this.responseType_ = documentDelete;
        } else {
            this.responseType_ = DocumentDelete.newBuilder((DocumentDelete) obj).mergeFrom(documentDelete).buildPartial();
        }
        this.responseTypeCase_ = 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeDocumentRemove(DocumentRemove documentRemove) {
        Object obj;
        documentRemove.getClass();
        if (this.responseTypeCase_ != 6 || (obj = this.responseType_) == DocumentRemove.DEFAULT_INSTANCE) {
            this.responseType_ = documentRemove;
        } else {
            this.responseType_ = DocumentRemove.newBuilder((DocumentRemove) obj).mergeFrom(documentRemove).buildPartial();
        }
        this.responseTypeCase_ = 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeFilter(ExistenceFilter existenceFilter) {
        Object obj;
        existenceFilter.getClass();
        if (this.responseTypeCase_ != 5 || (obj = this.responseType_) == ExistenceFilter.DEFAULT_INSTANCE) {
            this.responseType_ = existenceFilter;
        } else {
            this.responseType_ = ExistenceFilter.newBuilder((ExistenceFilter) obj).mergeFrom(existenceFilter).buildPartial();
        }
        this.responseTypeCase_ = 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeTargetChange(TargetChange targetChange) {
        Object obj;
        targetChange.getClass();
        if (this.responseTypeCase_ != 2 || (obj = this.responseType_) == TargetChange.DEFAULT_INSTANCE) {
            this.responseType_ = targetChange;
        } else {
            this.responseType_ = TargetChange.newBuilder((TargetChange) obj).mergeFrom(targetChange).buildPartial();
        }
        this.responseTypeCase_ = 2;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static ListenResponse parseDelimitedFrom(InputStream inputStream) {
        return (ListenResponse) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ListenResponse parseFrom(ByteBuffer byteBuffer) {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<ListenResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocumentChange(DocumentChange documentChange) {
        documentChange.getClass();
        this.responseType_ = documentChange;
        this.responseTypeCase_ = 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocumentDelete(DocumentDelete documentDelete) {
        documentDelete.getClass();
        this.responseType_ = documentDelete;
        this.responseTypeCase_ = 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocumentRemove(DocumentRemove documentRemove) {
        documentRemove.getClass();
        this.responseType_ = documentRemove;
        this.responseTypeCase_ = 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFilter(ExistenceFilter existenceFilter) {
        existenceFilter.getClass();
        this.responseType_ = existenceFilter;
        this.responseTypeCase_ = 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTargetChange(TargetChange targetChange) {
        targetChange.getClass();
        this.responseType_ = targetChange;
        this.responseTypeCase_ = 2;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0001\u0000\u0002\u0006\u0005\u0000\u0000\u0000\u0002<\u0000\u0003<\u0000\u0004<\u0000\u0005<\u0000\u0006<\u0000", new Object[]{"responseType_", "responseTypeCase_", TargetChange.class, DocumentChange.class, DocumentDelete.class, ExistenceFilter.class, DocumentRemove.class});
            case NEW_MUTABLE_INSTANCE:
                return new ListenResponse();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<ListenResponse> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (ListenResponse.class) {
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

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public DocumentChange getDocumentChange() {
        return this.responseTypeCase_ == 3 ? (DocumentChange) this.responseType_ : DocumentChange.DEFAULT_INSTANCE;
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public DocumentDelete getDocumentDelete() {
        return this.responseTypeCase_ == 4 ? (DocumentDelete) this.responseType_ : DocumentDelete.DEFAULT_INSTANCE;
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public DocumentRemove getDocumentRemove() {
        return this.responseTypeCase_ == 6 ? (DocumentRemove) this.responseType_ : DocumentRemove.DEFAULT_INSTANCE;
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public ExistenceFilter getFilter() {
        return this.responseTypeCase_ == 5 ? (ExistenceFilter) this.responseType_ : ExistenceFilter.DEFAULT_INSTANCE;
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public ResponseTypeCase getResponseTypeCase() {
        return ResponseTypeCase.forNumber(this.responseTypeCase_);
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public TargetChange getTargetChange() {
        return this.responseTypeCase_ == 2 ? (TargetChange) this.responseType_ : TargetChange.DEFAULT_INSTANCE;
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public boolean hasDocumentChange() {
        return this.responseTypeCase_ == 3;
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public boolean hasDocumentDelete() {
        return this.responseTypeCase_ == 4;
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public boolean hasDocumentRemove() {
        return this.responseTypeCase_ == 6;
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public boolean hasFilter() {
        return this.responseTypeCase_ == 5;
    }

    @Override // com.google.firestore.v1.ListenResponseOrBuilder
    public boolean hasTargetChange() {
        return this.responseTypeCase_ == 2;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ListenResponse, Builder> implements ListenResponseOrBuilder {
        public Builder() {
            super(ListenResponse.DEFAULT_INSTANCE);
        }

        public Builder clearDocumentChange() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearDocumentChange();
            return this;
        }

        public Builder clearDocumentDelete() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearDocumentDelete();
            return this;
        }

        public Builder clearDocumentRemove() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearDocumentRemove();
            return this;
        }

        public Builder clearFilter() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearFilter();
            return this;
        }

        public Builder clearResponseType() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearResponseType();
            return this;
        }

        public Builder clearTargetChange() {
            copyOnWrite();
            ((ListenResponse) this.instance).clearTargetChange();
            return this;
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public DocumentChange getDocumentChange() {
            return ((ListenResponse) this.instance).getDocumentChange();
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public DocumentDelete getDocumentDelete() {
            return ((ListenResponse) this.instance).getDocumentDelete();
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public DocumentRemove getDocumentRemove() {
            return ((ListenResponse) this.instance).getDocumentRemove();
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public ExistenceFilter getFilter() {
            return ((ListenResponse) this.instance).getFilter();
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public ResponseTypeCase getResponseTypeCase() {
            return ((ListenResponse) this.instance).getResponseTypeCase();
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public TargetChange getTargetChange() {
            return ((ListenResponse) this.instance).getTargetChange();
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public boolean hasDocumentChange() {
            return ((ListenResponse) this.instance).hasDocumentChange();
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public boolean hasDocumentDelete() {
            return ((ListenResponse) this.instance).hasDocumentDelete();
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public boolean hasDocumentRemove() {
            return ((ListenResponse) this.instance).hasDocumentRemove();
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public boolean hasFilter() {
            return ((ListenResponse) this.instance).hasFilter();
        }

        @Override // com.google.firestore.v1.ListenResponseOrBuilder
        public boolean hasTargetChange() {
            return ((ListenResponse) this.instance).hasTargetChange();
        }

        public Builder mergeDocumentChange(DocumentChange documentChange) {
            copyOnWrite();
            ((ListenResponse) this.instance).mergeDocumentChange(documentChange);
            return this;
        }

        public Builder mergeDocumentDelete(DocumentDelete documentDelete) {
            copyOnWrite();
            ((ListenResponse) this.instance).mergeDocumentDelete(documentDelete);
            return this;
        }

        public Builder mergeDocumentRemove(DocumentRemove documentRemove) {
            copyOnWrite();
            ((ListenResponse) this.instance).mergeDocumentRemove(documentRemove);
            return this;
        }

        public Builder mergeFilter(ExistenceFilter existenceFilter) {
            copyOnWrite();
            ((ListenResponse) this.instance).mergeFilter(existenceFilter);
            return this;
        }

        public Builder mergeTargetChange(TargetChange targetChange) {
            copyOnWrite();
            ((ListenResponse) this.instance).mergeTargetChange(targetChange);
            return this;
        }

        public Builder setDocumentChange(DocumentChange documentChange) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentChange(documentChange);
            return this;
        }

        public Builder setDocumentDelete(DocumentDelete documentDelete) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentDelete(documentDelete);
            return this;
        }

        public Builder setDocumentRemove(DocumentRemove documentRemove) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentRemove(documentRemove);
            return this;
        }

        public Builder setFilter(ExistenceFilter existenceFilter) {
            copyOnWrite();
            ((ListenResponse) this.instance).setFilter(existenceFilter);
            return this;
        }

        public Builder setTargetChange(TargetChange targetChange) {
            copyOnWrite();
            ((ListenResponse) this.instance).setTargetChange(targetChange);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(ListenResponse.DEFAULT_INSTANCE);
        }

        public Builder setDocumentChange(DocumentChange.Builder builder) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentChange(builder.build());
            return this;
        }

        public Builder setDocumentDelete(DocumentDelete.Builder builder) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentDelete(builder.build());
            return this;
        }

        public Builder setDocumentRemove(DocumentRemove.Builder builder) {
            copyOnWrite();
            ((ListenResponse) this.instance).setDocumentRemove(builder.build());
            return this;
        }

        public Builder setFilter(ExistenceFilter.Builder builder) {
            copyOnWrite();
            ((ListenResponse) this.instance).setFilter(builder.build());
            return this;
        }

        public Builder setTargetChange(TargetChange.Builder builder) {
            copyOnWrite();
            ((ListenResponse) this.instance).setTargetChange(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(ListenResponse listenResponse) {
        return DEFAULT_INSTANCE.createBuilder(listenResponse);
    }

    public static ListenResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ListenResponse) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ListenResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static ListenResponse parseFrom(ByteString byteString) {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ListenResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ListenResponse parseFrom(byte[] bArr) {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ListenResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ListenResponse parseFrom(InputStream inputStream) {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ListenResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ListenResponse parseFrom(CodedInputStream codedInputStream) {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ListenResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ListenResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
