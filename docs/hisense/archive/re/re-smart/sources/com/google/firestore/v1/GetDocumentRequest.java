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
public final class GetDocumentRequest extends GeneratedMessageLite<GetDocumentRequest, Builder> implements GetDocumentRequestOrBuilder {
    public static final GetDocumentRequest DEFAULT_INSTANCE;
    public static final int MASK_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    public static volatile Parser<GetDocumentRequest> PARSER = null;
    public static final int READ_TIME_FIELD_NUMBER = 5;
    public static final int TRANSACTION_FIELD_NUMBER = 3;
    public Object consistencySelector_;
    public DocumentMask mask_;
    public int consistencySelectorCase_ = 0;
    public String name_ = "";

    /* JADX INFO: renamed from: com.google.firestore.v1.GetDocumentRequest$1, reason: invalid class name */
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
        TRANSACTION(3),
        READ_TIME(5),
        CONSISTENCYSELECTOR_NOT_SET(0);

        public final int value;

        ConsistencySelectorCase(int i) {
            this.value = i;
        }

        public static ConsistencySelectorCase forNumber(int i) {
            if (i == 0) {
                return CONSISTENCYSELECTOR_NOT_SET;
            }
            if (i == 3) {
                return TRANSACTION;
            }
            if (i != 5) {
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
        GetDocumentRequest getDocumentRequest = new GetDocumentRequest();
        DEFAULT_INSTANCE = getDocumentRequest;
        GeneratedMessageLite.defaultInstanceMap.put(GetDocumentRequest.class, getDocumentRequest);
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
    public void clearName() {
        this.name_ = DEFAULT_INSTANCE.getName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearReadTime() {
        if (this.consistencySelectorCase_ == 5) {
            this.consistencySelectorCase_ = 0;
            this.consistencySelector_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTransaction() {
        if (this.consistencySelectorCase_ == 3) {
            this.consistencySelectorCase_ = 0;
            this.consistencySelector_ = null;
        }
    }

    public static GetDocumentRequest getDefaultInstance() {
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
        if (this.consistencySelectorCase_ != 5 || (obj = this.consistencySelector_) == Timestamp.DEFAULT_INSTANCE) {
            this.consistencySelector_ = timestamp;
        } else {
            this.consistencySelector_ = Timestamp.newBuilder((Timestamp) obj).mergeFrom(timestamp).buildPartial();
        }
        this.consistencySelectorCase_ = 5;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static GetDocumentRequest parseDelimitedFrom(InputStream inputStream) {
        return (GetDocumentRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static GetDocumentRequest parseFrom(ByteBuffer byteBuffer) {
        return (GetDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<GetDocumentRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMask(DocumentMask documentMask) {
        documentMask.getClass();
        this.mask_ = documentMask;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setName(String str) {
        str.getClass();
        this.name_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNameBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.name_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReadTime(Timestamp timestamp) {
        timestamp.getClass();
        this.consistencySelector_ = timestamp;
        this.consistencySelectorCase_ = 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTransaction(ByteString byteString) {
        byteString.getClass();
        this.consistencySelectorCase_ = 3;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0001\u0000\u0001\u0005\u0004\u0000\u0000\u0000\u0001Ȉ\u0002\t\u0003=\u0000\u0005<\u0000", new Object[]{"consistencySelector_", "consistencySelectorCase_", "name_", "mask_", Timestamp.class});
            case NEW_MUTABLE_INSTANCE:
                return new GetDocumentRequest();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<GetDocumentRequest> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (GetDocumentRequest.class) {
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

    @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
    public ConsistencySelectorCase getConsistencySelectorCase() {
        return ConsistencySelectorCase.forNumber(this.consistencySelectorCase_);
    }

    @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
    public DocumentMask getMask() {
        DocumentMask documentMask = this.mask_;
        return documentMask == null ? DocumentMask.DEFAULT_INSTANCE : documentMask;
    }

    @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
    public Timestamp getReadTime() {
        return this.consistencySelectorCase_ == 5 ? (Timestamp) this.consistencySelector_ : Timestamp.DEFAULT_INSTANCE;
    }

    @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
    public ByteString getTransaction() {
        return this.consistencySelectorCase_ == 3 ? (ByteString) this.consistencySelector_ : ByteString.EMPTY;
    }

    @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
    public boolean hasMask() {
        return this.mask_ != null;
    }

    @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
    public boolean hasReadTime() {
        return this.consistencySelectorCase_ == 5;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<GetDocumentRequest, Builder> implements GetDocumentRequestOrBuilder {
        public Builder() {
            super(GetDocumentRequest.DEFAULT_INSTANCE);
        }

        public Builder clearConsistencySelector() {
            copyOnWrite();
            ((GetDocumentRequest) this.instance).clearConsistencySelector();
            return this;
        }

        public Builder clearMask() {
            copyOnWrite();
            ((GetDocumentRequest) this.instance).mask_ = null;
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((GetDocumentRequest) this.instance).clearName();
            return this;
        }

        public Builder clearReadTime() {
            copyOnWrite();
            ((GetDocumentRequest) this.instance).clearReadTime();
            return this;
        }

        public Builder clearTransaction() {
            copyOnWrite();
            ((GetDocumentRequest) this.instance).clearTransaction();
            return this;
        }

        @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
        public ConsistencySelectorCase getConsistencySelectorCase() {
            return ((GetDocumentRequest) this.instance).getConsistencySelectorCase();
        }

        @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
        public DocumentMask getMask() {
            return ((GetDocumentRequest) this.instance).getMask();
        }

        @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
        public String getName() {
            return ((GetDocumentRequest) this.instance).getName();
        }

        @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
        public ByteString getNameBytes() {
            return ((GetDocumentRequest) this.instance).getNameBytes();
        }

        @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
        public Timestamp getReadTime() {
            return ((GetDocumentRequest) this.instance).getReadTime();
        }

        @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
        public ByteString getTransaction() {
            return ((GetDocumentRequest) this.instance).getTransaction();
        }

        @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
        public boolean hasMask() {
            return ((GetDocumentRequest) this.instance).hasMask();
        }

        @Override // com.google.firestore.v1.GetDocumentRequestOrBuilder
        public boolean hasReadTime() {
            return ((GetDocumentRequest) this.instance).hasReadTime();
        }

        public Builder mergeMask(DocumentMask documentMask) {
            copyOnWrite();
            ((GetDocumentRequest) this.instance).mergeMask(documentMask);
            return this;
        }

        public Builder mergeReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((GetDocumentRequest) this.instance).mergeReadTime(timestamp);
            return this;
        }

        public Builder setMask(DocumentMask documentMask) {
            copyOnWrite();
            ((GetDocumentRequest) this.instance).setMask(documentMask);
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((GetDocumentRequest) this.instance).setName(str);
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((GetDocumentRequest) this.instance).setNameBytes(byteString);
            return this;
        }

        public Builder setReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((GetDocumentRequest) this.instance).setReadTime(timestamp);
            return this;
        }

        public Builder setTransaction(ByteString byteString) {
            copyOnWrite();
            ((GetDocumentRequest) this.instance).setTransaction(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(GetDocumentRequest.DEFAULT_INSTANCE);
        }

        public Builder setMask(DocumentMask.Builder builder) {
            copyOnWrite();
            ((GetDocumentRequest) this.instance).setMask(builder.build());
            return this;
        }

        public Builder setReadTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((GetDocumentRequest) this.instance).setReadTime(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(GetDocumentRequest getDocumentRequest) {
        return DEFAULT_INSTANCE.createBuilder(getDocumentRequest);
    }

    public static GetDocumentRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (GetDocumentRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static GetDocumentRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (GetDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static GetDocumentRequest parseFrom(ByteString byteString) {
        return (GetDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static GetDocumentRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (GetDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static GetDocumentRequest parseFrom(byte[] bArr) {
        return (GetDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static GetDocumentRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (GetDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static GetDocumentRequest parseFrom(InputStream inputStream) {
        return (GetDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static GetDocumentRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (GetDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static GetDocumentRequest parseFrom(CodedInputStream codedInputStream) {
        return (GetDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static GetDocumentRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (GetDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
