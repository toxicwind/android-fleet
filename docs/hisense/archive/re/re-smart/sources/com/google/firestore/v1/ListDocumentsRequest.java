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
public final class ListDocumentsRequest extends GeneratedMessageLite<ListDocumentsRequest, Builder> implements ListDocumentsRequestOrBuilder {
    public static final int COLLECTION_ID_FIELD_NUMBER = 2;
    public static final ListDocumentsRequest DEFAULT_INSTANCE;
    public static final int MASK_FIELD_NUMBER = 7;
    public static final int ORDER_BY_FIELD_NUMBER = 6;
    public static final int PAGE_SIZE_FIELD_NUMBER = 3;
    public static final int PAGE_TOKEN_FIELD_NUMBER = 4;
    public static final int PARENT_FIELD_NUMBER = 1;
    public static volatile Parser<ListDocumentsRequest> PARSER = null;
    public static final int READ_TIME_FIELD_NUMBER = 10;
    public static final int SHOW_MISSING_FIELD_NUMBER = 12;
    public static final int TRANSACTION_FIELD_NUMBER = 8;
    public Object consistencySelector_;
    public DocumentMask mask_;
    public int pageSize_;
    public boolean showMissing_;
    public int consistencySelectorCase_ = 0;
    public String parent_ = "";
    public String collectionId_ = "";
    public String pageToken_ = "";
    public String orderBy_ = "";

    /* JADX INFO: renamed from: com.google.firestore.v1.ListDocumentsRequest$1, reason: invalid class name */
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
        TRANSACTION(8),
        READ_TIME(10),
        CONSISTENCYSELECTOR_NOT_SET(0);

        public final int value;

        ConsistencySelectorCase(int i) {
            this.value = i;
        }

        public static ConsistencySelectorCase forNumber(int i) {
            if (i == 0) {
                return CONSISTENCYSELECTOR_NOT_SET;
            }
            if (i == 8) {
                return TRANSACTION;
            }
            if (i != 10) {
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

    static {
        ListDocumentsRequest listDocumentsRequest = new ListDocumentsRequest();
        DEFAULT_INSTANCE = listDocumentsRequest;
        GeneratedMessageLite.defaultInstanceMap.put(ListDocumentsRequest.class, listDocumentsRequest);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCollectionId() {
        this.collectionId_ = DEFAULT_INSTANCE.getCollectionId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearConsistencySelector() {
        this.consistencySelectorCase_ = 0;
        this.consistencySelector_ = null;
    }

    private void clearMask() {
        this.mask_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearOrderBy() {
        this.orderBy_ = DEFAULT_INSTANCE.getOrderBy();
    }

    private void clearPageSize() {
        this.pageSize_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPageToken() {
        this.pageToken_ = DEFAULT_INSTANCE.getPageToken();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearParent() {
        this.parent_ = DEFAULT_INSTANCE.getParent();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearReadTime() {
        if (this.consistencySelectorCase_ == 10) {
            this.consistencySelectorCase_ = 0;
            this.consistencySelector_ = null;
        }
    }

    private void clearShowMissing() {
        this.showMissing_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTransaction() {
        if (this.consistencySelectorCase_ == 8) {
            this.consistencySelectorCase_ = 0;
            this.consistencySelector_ = null;
        }
    }

    public static ListDocumentsRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeMask(DocumentMask documentMask) {
        documentMask.getClass();
        DocumentMask documentMask2 = this.mask_;
        if (documentMask2 == null || documentMask2 == DocumentMask.DEFAULT_INSTANCE) {
            this.mask_ = documentMask;
        } else {
            this.mask_ = DocumentMask.newBuilder(documentMask2).mergeFrom(documentMask).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeReadTime(Timestamp timestamp) {
        Object obj;
        timestamp.getClass();
        if (this.consistencySelectorCase_ != 10 || (obj = this.consistencySelector_) == Timestamp.DEFAULT_INSTANCE) {
            this.consistencySelector_ = timestamp;
        } else {
            this.consistencySelector_ = Timestamp.newBuilder((Timestamp) obj).mergeFrom(timestamp).buildPartial();
        }
        this.consistencySelectorCase_ = 10;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static ListDocumentsRequest parseDelimitedFrom(InputStream inputStream) {
        return (ListDocumentsRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ListDocumentsRequest parseFrom(ByteBuffer byteBuffer) {
        return (ListDocumentsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<ListDocumentsRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCollectionId(String str) {
        str.getClass();
        this.collectionId_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCollectionIdBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.collectionId_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMask(DocumentMask documentMask) {
        documentMask.getClass();
        this.mask_ = documentMask;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOrderBy(String str) {
        str.getClass();
        this.orderBy_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOrderByBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.orderBy_ = byteString.toStringUtf8();
    }

    private void setPageSize(int i) {
        this.pageSize_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPageToken(String str) {
        str.getClass();
        this.pageToken_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPageTokenBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.pageToken_ = byteString.toStringUtf8();
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
        this.consistencySelectorCase_ = 10;
    }

    private void setShowMissing(boolean z) {
        this.showMissing_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTransaction(ByteString byteString) {
        byteString.getClass();
        this.consistencySelectorCase_ = 8;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\t\u0001\u0000\u0001\f\t\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003\u0004\u0004Ȉ\u0006Ȉ\u0007\t\b=\u0000\n<\u0000\f\u0007", new Object[]{"consistencySelector_", "consistencySelectorCase_", "parent_", "collectionId_", "pageSize_", "pageToken_", "orderBy_", "mask_", Timestamp.class, "showMissing_"});
            case NEW_MUTABLE_INSTANCE:
                return new ListDocumentsRequest();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<ListDocumentsRequest> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (ListDocumentsRequest.class) {
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

    @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
    public String getCollectionId() {
        return this.collectionId_;
    }

    @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
    public ByteString getCollectionIdBytes() {
        return ByteString.copyFromUtf8(this.collectionId_);
    }

    @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
    public ConsistencySelectorCase getConsistencySelectorCase() {
        return ConsistencySelectorCase.forNumber(this.consistencySelectorCase_);
    }

    @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
    public DocumentMask getMask() {
        DocumentMask documentMask = this.mask_;
        return documentMask == null ? DocumentMask.DEFAULT_INSTANCE : documentMask;
    }

    @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
    public String getOrderBy() {
        return this.orderBy_;
    }

    @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
    public ByteString getOrderByBytes() {
        return ByteString.copyFromUtf8(this.orderBy_);
    }

    @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
    public int getPageSize() {
        return this.pageSize_;
    }

    @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
    public String getPageToken() {
        return this.pageToken_;
    }

    @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
    public ByteString getPageTokenBytes() {
        return ByteString.copyFromUtf8(this.pageToken_);
    }

    @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
    public String getParent() {
        return this.parent_;
    }

    @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
    public ByteString getParentBytes() {
        return ByteString.copyFromUtf8(this.parent_);
    }

    @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
    public Timestamp getReadTime() {
        return this.consistencySelectorCase_ == 10 ? (Timestamp) this.consistencySelector_ : Timestamp.DEFAULT_INSTANCE;
    }

    @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
    public boolean getShowMissing() {
        return this.showMissing_;
    }

    @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
    public ByteString getTransaction() {
        return this.consistencySelectorCase_ == 8 ? (ByteString) this.consistencySelector_ : ByteString.EMPTY;
    }

    @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
    public boolean hasMask() {
        return this.mask_ != null;
    }

    @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
    public boolean hasReadTime() {
        return this.consistencySelectorCase_ == 10;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ListDocumentsRequest, Builder> implements ListDocumentsRequestOrBuilder {
        public Builder() {
            super(ListDocumentsRequest.DEFAULT_INSTANCE);
        }

        public Builder clearCollectionId() {
            copyOnWrite();
            ((ListDocumentsRequest) this.instance).clearCollectionId();
            return this;
        }

        public Builder clearConsistencySelector() {
            copyOnWrite();
            ((ListDocumentsRequest) this.instance).clearConsistencySelector();
            return this;
        }

        public Builder clearMask() {
            copyOnWrite();
            ((ListDocumentsRequest) this.instance).mask_ = null;
            return this;
        }

        public Builder clearOrderBy() {
            copyOnWrite();
            ((ListDocumentsRequest) this.instance).clearOrderBy();
            return this;
        }

        public Builder clearPageSize() {
            copyOnWrite();
            ((ListDocumentsRequest) this.instance).pageSize_ = 0;
            return this;
        }

        public Builder clearPageToken() {
            copyOnWrite();
            ((ListDocumentsRequest) this.instance).clearPageToken();
            return this;
        }

        public Builder clearParent() {
            copyOnWrite();
            ((ListDocumentsRequest) this.instance).clearParent();
            return this;
        }

        public Builder clearReadTime() {
            copyOnWrite();
            ((ListDocumentsRequest) this.instance).clearReadTime();
            return this;
        }

        public Builder clearShowMissing() {
            copyOnWrite();
            ((ListDocumentsRequest) this.instance).showMissing_ = false;
            return this;
        }

        public Builder clearTransaction() {
            copyOnWrite();
            ((ListDocumentsRequest) this.instance).clearTransaction();
            return this;
        }

        @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
        public String getCollectionId() {
            return ((ListDocumentsRequest) this.instance).getCollectionId();
        }

        @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
        public ByteString getCollectionIdBytes() {
            return ((ListDocumentsRequest) this.instance).getCollectionIdBytes();
        }

        @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
        public ConsistencySelectorCase getConsistencySelectorCase() {
            return ((ListDocumentsRequest) this.instance).getConsistencySelectorCase();
        }

        @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
        public DocumentMask getMask() {
            return ((ListDocumentsRequest) this.instance).getMask();
        }

        @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
        public String getOrderBy() {
            return ((ListDocumentsRequest) this.instance).getOrderBy();
        }

        @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
        public ByteString getOrderByBytes() {
            return ((ListDocumentsRequest) this.instance).getOrderByBytes();
        }

        @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
        public int getPageSize() {
            return ((ListDocumentsRequest) this.instance).getPageSize();
        }

        @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
        public String getPageToken() {
            return ((ListDocumentsRequest) this.instance).getPageToken();
        }

        @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
        public ByteString getPageTokenBytes() {
            return ((ListDocumentsRequest) this.instance).getPageTokenBytes();
        }

        @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
        public String getParent() {
            return ((ListDocumentsRequest) this.instance).getParent();
        }

        @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
        public ByteString getParentBytes() {
            return ((ListDocumentsRequest) this.instance).getParentBytes();
        }

        @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
        public Timestamp getReadTime() {
            return ((ListDocumentsRequest) this.instance).getReadTime();
        }

        @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
        public boolean getShowMissing() {
            return ((ListDocumentsRequest) this.instance).getShowMissing();
        }

        @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
        public ByteString getTransaction() {
            return ((ListDocumentsRequest) this.instance).getTransaction();
        }

        @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
        public boolean hasMask() {
            return ((ListDocumentsRequest) this.instance).hasMask();
        }

        @Override // com.google.firestore.v1.ListDocumentsRequestOrBuilder
        public boolean hasReadTime() {
            return ((ListDocumentsRequest) this.instance).hasReadTime();
        }

        public Builder mergeMask(DocumentMask documentMask) {
            copyOnWrite();
            ((ListDocumentsRequest) this.instance).mergeMask(documentMask);
            return this;
        }

        public Builder mergeReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((ListDocumentsRequest) this.instance).mergeReadTime(timestamp);
            return this;
        }

        public Builder setCollectionId(String str) {
            copyOnWrite();
            ((ListDocumentsRequest) this.instance).setCollectionId(str);
            return this;
        }

        public Builder setCollectionIdBytes(ByteString byteString) {
            copyOnWrite();
            ((ListDocumentsRequest) this.instance).setCollectionIdBytes(byteString);
            return this;
        }

        public Builder setMask(DocumentMask documentMask) {
            copyOnWrite();
            ((ListDocumentsRequest) this.instance).setMask(documentMask);
            return this;
        }

        public Builder setOrderBy(String str) {
            copyOnWrite();
            ((ListDocumentsRequest) this.instance).setOrderBy(str);
            return this;
        }

        public Builder setOrderByBytes(ByteString byteString) {
            copyOnWrite();
            ((ListDocumentsRequest) this.instance).setOrderByBytes(byteString);
            return this;
        }

        public Builder setPageSize(int i) {
            copyOnWrite();
            ((ListDocumentsRequest) this.instance).pageSize_ = i;
            return this;
        }

        public Builder setPageToken(String str) {
            copyOnWrite();
            ((ListDocumentsRequest) this.instance).setPageToken(str);
            return this;
        }

        public Builder setPageTokenBytes(ByteString byteString) {
            copyOnWrite();
            ((ListDocumentsRequest) this.instance).setPageTokenBytes(byteString);
            return this;
        }

        public Builder setParent(String str) {
            copyOnWrite();
            ((ListDocumentsRequest) this.instance).setParent(str);
            return this;
        }

        public Builder setParentBytes(ByteString byteString) {
            copyOnWrite();
            ((ListDocumentsRequest) this.instance).setParentBytes(byteString);
            return this;
        }

        public Builder setReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((ListDocumentsRequest) this.instance).setReadTime(timestamp);
            return this;
        }

        public Builder setShowMissing(boolean z) {
            copyOnWrite();
            ((ListDocumentsRequest) this.instance).showMissing_ = z;
            return this;
        }

        public Builder setTransaction(ByteString byteString) {
            copyOnWrite();
            ((ListDocumentsRequest) this.instance).setTransaction(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(ListDocumentsRequest.DEFAULT_INSTANCE);
        }

        public Builder setMask(DocumentMask.Builder builder) {
            copyOnWrite();
            ((ListDocumentsRequest) this.instance).setMask(builder.build());
            return this;
        }

        public Builder setReadTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((ListDocumentsRequest) this.instance).setReadTime(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(ListDocumentsRequest listDocumentsRequest) {
        return DEFAULT_INSTANCE.createBuilder(listDocumentsRequest);
    }

    public static ListDocumentsRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ListDocumentsRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ListDocumentsRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (ListDocumentsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static ListDocumentsRequest parseFrom(ByteString byteString) {
        return (ListDocumentsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ListDocumentsRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (ListDocumentsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ListDocumentsRequest parseFrom(byte[] bArr) {
        return (ListDocumentsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ListDocumentsRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (ListDocumentsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ListDocumentsRequest parseFrom(InputStream inputStream) {
        return (ListDocumentsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ListDocumentsRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ListDocumentsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ListDocumentsRequest parseFrom(CodedInputStream codedInputStream) {
        return (ListDocumentsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ListDocumentsRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ListDocumentsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
