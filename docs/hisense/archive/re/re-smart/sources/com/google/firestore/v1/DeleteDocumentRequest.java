package com.google.firestore.v1;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class DeleteDocumentRequest extends GeneratedMessageLite<DeleteDocumentRequest, Builder> implements DeleteDocumentRequestOrBuilder {
    public static final int CURRENT_DOCUMENT_FIELD_NUMBER = 2;
    public static final DeleteDocumentRequest DEFAULT_INSTANCE;
    public static final int NAME_FIELD_NUMBER = 1;
    public static volatile Parser<DeleteDocumentRequest> PARSER;
    public Precondition currentDocument_;
    public String name_ = "";

    /* JADX INFO: renamed from: com.google.firestore.v1.DeleteDocumentRequest$1, reason: invalid class name */
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
        DeleteDocumentRequest deleteDocumentRequest = new DeleteDocumentRequest();
        DEFAULT_INSTANCE = deleteDocumentRequest;
        GeneratedMessageLite.defaultInstanceMap.put(DeleteDocumentRequest.class, deleteDocumentRequest);
    }

    private void clearCurrentDocument() {
        this.currentDocument_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.name_ = DEFAULT_INSTANCE.getName();
    }

    public static DeleteDocumentRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeCurrentDocument(Precondition precondition) {
        precondition.getClass();
        Precondition precondition2 = this.currentDocument_;
        if (precondition2 == null || precondition2 == Precondition.DEFAULT_INSTANCE) {
            this.currentDocument_ = precondition;
        } else {
            this.currentDocument_ = Precondition.newBuilder(precondition2).mergeFrom(precondition).buildPartial();
        }
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static DeleteDocumentRequest parseDelimitedFrom(InputStream inputStream) {
        return (DeleteDocumentRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DeleteDocumentRequest parseFrom(ByteBuffer byteBuffer) {
        return (DeleteDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<DeleteDocumentRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrentDocument(Precondition precondition) {
        precondition.getClass();
        this.currentDocument_ = precondition;
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

    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        AnonymousClass1 anonymousClass1 = null;
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\t", new Object[]{"name_", "currentDocument_"});
            case NEW_MUTABLE_INSTANCE:
                return new DeleteDocumentRequest();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<DeleteDocumentRequest> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (DeleteDocumentRequest.class) {
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

    @Override // com.google.firestore.v1.DeleteDocumentRequestOrBuilder
    public Precondition getCurrentDocument() {
        Precondition precondition = this.currentDocument_;
        return precondition == null ? Precondition.DEFAULT_INSTANCE : precondition;
    }

    @Override // com.google.firestore.v1.DeleteDocumentRequestOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.firestore.v1.DeleteDocumentRequestOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    @Override // com.google.firestore.v1.DeleteDocumentRequestOrBuilder
    public boolean hasCurrentDocument() {
        return this.currentDocument_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DeleteDocumentRequest, Builder> implements DeleteDocumentRequestOrBuilder {
        public Builder() {
            super(DeleteDocumentRequest.DEFAULT_INSTANCE);
        }

        public Builder clearCurrentDocument() {
            copyOnWrite();
            ((DeleteDocumentRequest) this.instance).currentDocument_ = null;
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((DeleteDocumentRequest) this.instance).clearName();
            return this;
        }

        @Override // com.google.firestore.v1.DeleteDocumentRequestOrBuilder
        public Precondition getCurrentDocument() {
            return ((DeleteDocumentRequest) this.instance).getCurrentDocument();
        }

        @Override // com.google.firestore.v1.DeleteDocumentRequestOrBuilder
        public String getName() {
            return ((DeleteDocumentRequest) this.instance).getName();
        }

        @Override // com.google.firestore.v1.DeleteDocumentRequestOrBuilder
        public ByteString getNameBytes() {
            return ((DeleteDocumentRequest) this.instance).getNameBytes();
        }

        @Override // com.google.firestore.v1.DeleteDocumentRequestOrBuilder
        public boolean hasCurrentDocument() {
            return ((DeleteDocumentRequest) this.instance).hasCurrentDocument();
        }

        public Builder mergeCurrentDocument(Precondition precondition) {
            copyOnWrite();
            ((DeleteDocumentRequest) this.instance).mergeCurrentDocument(precondition);
            return this;
        }

        public Builder setCurrentDocument(Precondition precondition) {
            copyOnWrite();
            ((DeleteDocumentRequest) this.instance).setCurrentDocument(precondition);
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((DeleteDocumentRequest) this.instance).setName(str);
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((DeleteDocumentRequest) this.instance).setNameBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(DeleteDocumentRequest.DEFAULT_INSTANCE);
        }

        public Builder setCurrentDocument(Precondition.Builder builder) {
            copyOnWrite();
            ((DeleteDocumentRequest) this.instance).setCurrentDocument(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(DeleteDocumentRequest deleteDocumentRequest) {
        return DEFAULT_INSTANCE.createBuilder(deleteDocumentRequest);
    }

    public static DeleteDocumentRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (DeleteDocumentRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DeleteDocumentRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (DeleteDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static DeleteDocumentRequest parseFrom(ByteString byteString) {
        return (DeleteDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static DeleteDocumentRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (DeleteDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static DeleteDocumentRequest parseFrom(byte[] bArr) {
        return (DeleteDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static DeleteDocumentRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (DeleteDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static DeleteDocumentRequest parseFrom(InputStream inputStream) {
        return (DeleteDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DeleteDocumentRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (DeleteDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DeleteDocumentRequest parseFrom(CodedInputStream codedInputStream) {
        return (DeleteDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static DeleteDocumentRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (DeleteDocumentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
