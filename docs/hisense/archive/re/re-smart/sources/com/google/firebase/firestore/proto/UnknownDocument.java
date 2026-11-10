package com.google.firebase.firestore.proto;

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
public final class UnknownDocument extends GeneratedMessageLite<UnknownDocument, Builder> implements UnknownDocumentOrBuilder {
    public static final UnknownDocument DEFAULT_INSTANCE;
    public static final int NAME_FIELD_NUMBER = 1;
    public static volatile Parser<UnknownDocument> PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 2;
    public String name_ = "";
    public Timestamp version_;

    /* JADX INFO: renamed from: com.google.firebase.firestore.proto.UnknownDocument$1, reason: invalid class name */
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
        UnknownDocument unknownDocument = new UnknownDocument();
        DEFAULT_INSTANCE = unknownDocument;
        GeneratedMessageLite.defaultInstanceMap.put(UnknownDocument.class, unknownDocument);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.name_ = DEFAULT_INSTANCE.getName();
    }

    private void clearVersion() {
        this.version_ = null;
    }

    public static UnknownDocument getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeVersion(Timestamp timestamp) {
        timestamp.getClass();
        Timestamp timestamp2 = this.version_;
        if (timestamp2 == null || timestamp2 == Timestamp.DEFAULT_INSTANCE) {
            this.version_ = timestamp;
        } else {
            this.version_ = Timestamp.newBuilder(timestamp2).mergeFrom(timestamp).buildPartial();
        }
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static UnknownDocument parseDelimitedFrom(InputStream inputStream) {
        return (UnknownDocument) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static UnknownDocument parseFrom(ByteBuffer byteBuffer) {
        return (UnknownDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<UnknownDocument> parser() {
        return DEFAULT_INSTANCE.getParserForType();
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
    public void setVersion(Timestamp timestamp) {
        timestamp.getClass();
        this.version_ = timestamp;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\t", new Object[]{"name_", "version_"});
            case NEW_MUTABLE_INSTANCE:
                return new UnknownDocument();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<UnknownDocument> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (UnknownDocument.class) {
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

    @Override // com.google.firebase.firestore.proto.UnknownDocumentOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.firebase.firestore.proto.UnknownDocumentOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    @Override // com.google.firebase.firestore.proto.UnknownDocumentOrBuilder
    public Timestamp getVersion() {
        Timestamp timestamp = this.version_;
        return timestamp == null ? Timestamp.DEFAULT_INSTANCE : timestamp;
    }

    @Override // com.google.firebase.firestore.proto.UnknownDocumentOrBuilder
    public boolean hasVersion() {
        return this.version_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UnknownDocument, Builder> implements UnknownDocumentOrBuilder {
        public Builder() {
            super(UnknownDocument.DEFAULT_INSTANCE);
        }

        public Builder clearName() {
            copyOnWrite();
            ((UnknownDocument) this.instance).clearName();
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((UnknownDocument) this.instance).version_ = null;
            return this;
        }

        @Override // com.google.firebase.firestore.proto.UnknownDocumentOrBuilder
        public String getName() {
            return ((UnknownDocument) this.instance).getName();
        }

        @Override // com.google.firebase.firestore.proto.UnknownDocumentOrBuilder
        public ByteString getNameBytes() {
            return ((UnknownDocument) this.instance).getNameBytes();
        }

        @Override // com.google.firebase.firestore.proto.UnknownDocumentOrBuilder
        public Timestamp getVersion() {
            return ((UnknownDocument) this.instance).getVersion();
        }

        @Override // com.google.firebase.firestore.proto.UnknownDocumentOrBuilder
        public boolean hasVersion() {
            return ((UnknownDocument) this.instance).hasVersion();
        }

        public Builder mergeVersion(Timestamp timestamp) {
            copyOnWrite();
            ((UnknownDocument) this.instance).mergeVersion(timestamp);
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((UnknownDocument) this.instance).setName(str);
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((UnknownDocument) this.instance).setNameBytes(byteString);
            return this;
        }

        public Builder setVersion(Timestamp timestamp) {
            copyOnWrite();
            ((UnknownDocument) this.instance).setVersion(timestamp);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(UnknownDocument.DEFAULT_INSTANCE);
        }

        public Builder setVersion(Timestamp.Builder builder) {
            copyOnWrite();
            ((UnknownDocument) this.instance).setVersion(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(UnknownDocument unknownDocument) {
        return DEFAULT_INSTANCE.createBuilder(unknownDocument);
    }

    public static UnknownDocument parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (UnknownDocument) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static UnknownDocument parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (UnknownDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static UnknownDocument parseFrom(ByteString byteString) {
        return (UnknownDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static UnknownDocument parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (UnknownDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static UnknownDocument parseFrom(byte[] bArr) {
        return (UnknownDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static UnknownDocument parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (UnknownDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static UnknownDocument parseFrom(InputStream inputStream) {
        return (UnknownDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static UnknownDocument parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (UnknownDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static UnknownDocument parseFrom(CodedInputStream codedInputStream) {
        return (UnknownDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static UnknownDocument parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (UnknownDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
