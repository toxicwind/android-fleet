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
public final class NoDocument extends GeneratedMessageLite<NoDocument, Builder> implements NoDocumentOrBuilder {
    public static final NoDocument DEFAULT_INSTANCE;
    public static final int NAME_FIELD_NUMBER = 1;
    public static volatile Parser<NoDocument> PARSER = null;
    public static final int READ_TIME_FIELD_NUMBER = 2;
    public String name_ = "";
    public Timestamp readTime_;

    /* JADX INFO: renamed from: com.google.firebase.firestore.proto.NoDocument$1, reason: invalid class name */
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
        NoDocument noDocument = new NoDocument();
        DEFAULT_INSTANCE = noDocument;
        GeneratedMessageLite.defaultInstanceMap.put(NoDocument.class, noDocument);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.name_ = DEFAULT_INSTANCE.getName();
    }

    private void clearReadTime() {
        this.readTime_ = null;
    }

    public static NoDocument getDefaultInstance() {
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

    public static NoDocument parseDelimitedFrom(InputStream inputStream) {
        return (NoDocument) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static NoDocument parseFrom(ByteBuffer byteBuffer) {
        return (NoDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<NoDocument> parser() {
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
    public void setReadTime(Timestamp timestamp) {
        timestamp.getClass();
        this.readTime_ = timestamp;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\t", new Object[]{"name_", "readTime_"});
            case NEW_MUTABLE_INSTANCE:
                return new NoDocument();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<NoDocument> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (NoDocument.class) {
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

    @Override // com.google.firebase.firestore.proto.NoDocumentOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.firebase.firestore.proto.NoDocumentOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    @Override // com.google.firebase.firestore.proto.NoDocumentOrBuilder
    public Timestamp getReadTime() {
        Timestamp timestamp = this.readTime_;
        return timestamp == null ? Timestamp.DEFAULT_INSTANCE : timestamp;
    }

    @Override // com.google.firebase.firestore.proto.NoDocumentOrBuilder
    public boolean hasReadTime() {
        return this.readTime_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NoDocument, Builder> implements NoDocumentOrBuilder {
        public Builder() {
            super(NoDocument.DEFAULT_INSTANCE);
        }

        public Builder clearName() {
            copyOnWrite();
            ((NoDocument) this.instance).clearName();
            return this;
        }

        public Builder clearReadTime() {
            copyOnWrite();
            ((NoDocument) this.instance).readTime_ = null;
            return this;
        }

        @Override // com.google.firebase.firestore.proto.NoDocumentOrBuilder
        public String getName() {
            return ((NoDocument) this.instance).getName();
        }

        @Override // com.google.firebase.firestore.proto.NoDocumentOrBuilder
        public ByteString getNameBytes() {
            return ((NoDocument) this.instance).getNameBytes();
        }

        @Override // com.google.firebase.firestore.proto.NoDocumentOrBuilder
        public Timestamp getReadTime() {
            return ((NoDocument) this.instance).getReadTime();
        }

        @Override // com.google.firebase.firestore.proto.NoDocumentOrBuilder
        public boolean hasReadTime() {
            return ((NoDocument) this.instance).hasReadTime();
        }

        public Builder mergeReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((NoDocument) this.instance).mergeReadTime(timestamp);
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((NoDocument) this.instance).setName(str);
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((NoDocument) this.instance).setNameBytes(byteString);
            return this;
        }

        public Builder setReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((NoDocument) this.instance).setReadTime(timestamp);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(NoDocument.DEFAULT_INSTANCE);
        }

        public Builder setReadTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((NoDocument) this.instance).setReadTime(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(NoDocument noDocument) {
        return DEFAULT_INSTANCE.createBuilder(noDocument);
    }

    public static NoDocument parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (NoDocument) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static NoDocument parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (NoDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static NoDocument parseFrom(ByteString byteString) {
        return (NoDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static NoDocument parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (NoDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static NoDocument parseFrom(byte[] bArr) {
        return (NoDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static NoDocument parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (NoDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static NoDocument parseFrom(InputStream inputStream) {
        return (NoDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static NoDocument parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (NoDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static NoDocument parseFrom(CodedInputStream codedInputStream) {
        return (NoDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static NoDocument parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (NoDocument) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
