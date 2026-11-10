package com.google.firestore.bundle;

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
public final class NamedQuery extends GeneratedMessageLite<NamedQuery, Builder> implements NamedQueryOrBuilder {
    public static final int BUNDLED_QUERY_FIELD_NUMBER = 2;
    public static final NamedQuery DEFAULT_INSTANCE;
    public static final int NAME_FIELD_NUMBER = 1;
    public static volatile Parser<NamedQuery> PARSER = null;
    public static final int READ_TIME_FIELD_NUMBER = 3;
    public BundledQuery bundledQuery_;
    public String name_ = "";
    public Timestamp readTime_;

    /* JADX INFO: renamed from: com.google.firestore.bundle.NamedQuery$1, reason: invalid class name */
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
        NamedQuery namedQuery = new NamedQuery();
        DEFAULT_INSTANCE = namedQuery;
        GeneratedMessageLite.defaultInstanceMap.put(NamedQuery.class, namedQuery);
    }

    private void clearBundledQuery() {
        this.bundledQuery_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.name_ = DEFAULT_INSTANCE.getName();
    }

    private void clearReadTime() {
        this.readTime_ = null;
    }

    public static NamedQuery getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeBundledQuery(BundledQuery bundledQuery) {
        bundledQuery.getClass();
        BundledQuery bundledQuery2 = this.bundledQuery_;
        if (bundledQuery2 == null || bundledQuery2 == BundledQuery.DEFAULT_INSTANCE) {
            this.bundledQuery_ = bundledQuery;
        } else {
            this.bundledQuery_ = BundledQuery.newBuilder(bundledQuery2).mergeFrom(bundledQuery).buildPartial();
        }
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

    public static NamedQuery parseDelimitedFrom(InputStream inputStream) {
        return (NamedQuery) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static NamedQuery parseFrom(ByteBuffer byteBuffer) {
        return (NamedQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<NamedQuery> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBundledQuery(BundledQuery bundledQuery) {
        bundledQuery.getClass();
        this.bundledQuery_ = bundledQuery;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\t\u0003\t", new Object[]{"name_", "bundledQuery_", "readTime_"});
            case NEW_MUTABLE_INSTANCE:
                return new NamedQuery();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<NamedQuery> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (NamedQuery.class) {
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

    @Override // com.google.firestore.bundle.NamedQueryOrBuilder
    public BundledQuery getBundledQuery() {
        BundledQuery bundledQuery = this.bundledQuery_;
        return bundledQuery == null ? BundledQuery.DEFAULT_INSTANCE : bundledQuery;
    }

    @Override // com.google.firestore.bundle.NamedQueryOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.firestore.bundle.NamedQueryOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    @Override // com.google.firestore.bundle.NamedQueryOrBuilder
    public Timestamp getReadTime() {
        Timestamp timestamp = this.readTime_;
        return timestamp == null ? Timestamp.DEFAULT_INSTANCE : timestamp;
    }

    @Override // com.google.firestore.bundle.NamedQueryOrBuilder
    public boolean hasBundledQuery() {
        return this.bundledQuery_ != null;
    }

    @Override // com.google.firestore.bundle.NamedQueryOrBuilder
    public boolean hasReadTime() {
        return this.readTime_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NamedQuery, Builder> implements NamedQueryOrBuilder {
        public Builder() {
            super(NamedQuery.DEFAULT_INSTANCE);
        }

        public Builder clearBundledQuery() {
            copyOnWrite();
            ((NamedQuery) this.instance).bundledQuery_ = null;
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((NamedQuery) this.instance).clearName();
            return this;
        }

        public Builder clearReadTime() {
            copyOnWrite();
            ((NamedQuery) this.instance).readTime_ = null;
            return this;
        }

        @Override // com.google.firestore.bundle.NamedQueryOrBuilder
        public BundledQuery getBundledQuery() {
            return ((NamedQuery) this.instance).getBundledQuery();
        }

        @Override // com.google.firestore.bundle.NamedQueryOrBuilder
        public String getName() {
            return ((NamedQuery) this.instance).getName();
        }

        @Override // com.google.firestore.bundle.NamedQueryOrBuilder
        public ByteString getNameBytes() {
            return ((NamedQuery) this.instance).getNameBytes();
        }

        @Override // com.google.firestore.bundle.NamedQueryOrBuilder
        public Timestamp getReadTime() {
            return ((NamedQuery) this.instance).getReadTime();
        }

        @Override // com.google.firestore.bundle.NamedQueryOrBuilder
        public boolean hasBundledQuery() {
            return ((NamedQuery) this.instance).hasBundledQuery();
        }

        @Override // com.google.firestore.bundle.NamedQueryOrBuilder
        public boolean hasReadTime() {
            return ((NamedQuery) this.instance).hasReadTime();
        }

        public Builder mergeBundledQuery(BundledQuery bundledQuery) {
            copyOnWrite();
            ((NamedQuery) this.instance).mergeBundledQuery(bundledQuery);
            return this;
        }

        public Builder mergeReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((NamedQuery) this.instance).mergeReadTime(timestamp);
            return this;
        }

        public Builder setBundledQuery(BundledQuery bundledQuery) {
            copyOnWrite();
            ((NamedQuery) this.instance).setBundledQuery(bundledQuery);
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((NamedQuery) this.instance).setName(str);
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((NamedQuery) this.instance).setNameBytes(byteString);
            return this;
        }

        public Builder setReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((NamedQuery) this.instance).setReadTime(timestamp);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(NamedQuery.DEFAULT_INSTANCE);
        }

        public Builder setBundledQuery(BundledQuery.Builder builder) {
            copyOnWrite();
            ((NamedQuery) this.instance).setBundledQuery(builder.build());
            return this;
        }

        public Builder setReadTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((NamedQuery) this.instance).setReadTime(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(NamedQuery namedQuery) {
        return DEFAULT_INSTANCE.createBuilder(namedQuery);
    }

    public static NamedQuery parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (NamedQuery) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static NamedQuery parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (NamedQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static NamedQuery parseFrom(ByteString byteString) {
        return (NamedQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static NamedQuery parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (NamedQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static NamedQuery parseFrom(byte[] bArr) {
        return (NamedQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static NamedQuery parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (NamedQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static NamedQuery parseFrom(InputStream inputStream) {
        return (NamedQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static NamedQuery parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (NamedQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static NamedQuery parseFrom(CodedInputStream codedInputStream) {
        return (NamedQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static NamedQuery parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (NamedQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
