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
public final class BundleMetadata extends GeneratedMessageLite<BundleMetadata, Builder> implements BundleMetadataOrBuilder {
    public static final int CREATE_TIME_FIELD_NUMBER = 2;
    public static final BundleMetadata DEFAULT_INSTANCE;
    public static final int ID_FIELD_NUMBER = 1;
    public static volatile Parser<BundleMetadata> PARSER = null;
    public static final int TOTAL_BYTES_FIELD_NUMBER = 5;
    public static final int TOTAL_DOCUMENTS_FIELD_NUMBER = 4;
    public static final int VERSION_FIELD_NUMBER = 3;
    public Timestamp createTime_;
    public String id_ = "";
    public long totalBytes_;
    public int totalDocuments_;
    public int version_;

    /* JADX INFO: renamed from: com.google.firestore.bundle.BundleMetadata$1, reason: invalid class name */
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
        BundleMetadata bundleMetadata = new BundleMetadata();
        DEFAULT_INSTANCE = bundleMetadata;
        GeneratedMessageLite.defaultInstanceMap.put(BundleMetadata.class, bundleMetadata);
    }

    private void clearCreateTime() {
        this.createTime_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearId() {
        this.id_ = DEFAULT_INSTANCE.getId();
    }

    private void clearTotalBytes() {
        this.totalBytes_ = 0L;
    }

    private void clearTotalDocuments() {
        this.totalDocuments_ = 0;
    }

    private void clearVersion() {
        this.version_ = 0;
    }

    public static BundleMetadata getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeCreateTime(Timestamp timestamp) {
        timestamp.getClass();
        Timestamp timestamp2 = this.createTime_;
        if (timestamp2 == null || timestamp2 == Timestamp.DEFAULT_INSTANCE) {
            this.createTime_ = timestamp;
        } else {
            this.createTime_ = Timestamp.newBuilder(timestamp2).mergeFrom(timestamp).buildPartial();
        }
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static BundleMetadata parseDelimitedFrom(InputStream inputStream) {
        return (BundleMetadata) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BundleMetadata parseFrom(ByteBuffer byteBuffer) {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<BundleMetadata> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCreateTime(Timestamp timestamp) {
        timestamp.getClass();
        this.createTime_ = timestamp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setId(String str) {
        str.getClass();
        this.id_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIdBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.id_ = byteString.toStringUtf8();
    }

    private void setTotalBytes(long j) {
        this.totalBytes_ = j;
    }

    private void setTotalDocuments(int i) {
        this.totalDocuments_ = i;
    }

    private void setVersion(int i) {
        this.version_ = i;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001Ȉ\u0002\t\u0003\u000b\u0004\u000b\u0005\u0003", new Object[]{"id_", "createTime_", "version_", "totalDocuments_", "totalBytes_"});
            case NEW_MUTABLE_INSTANCE:
                return new BundleMetadata();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<BundleMetadata> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (BundleMetadata.class) {
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

    @Override // com.google.firestore.bundle.BundleMetadataOrBuilder
    public Timestamp getCreateTime() {
        Timestamp timestamp = this.createTime_;
        return timestamp == null ? Timestamp.DEFAULT_INSTANCE : timestamp;
    }

    @Override // com.google.firestore.bundle.BundleMetadataOrBuilder
    public String getId() {
        return this.id_;
    }

    @Override // com.google.firestore.bundle.BundleMetadataOrBuilder
    public ByteString getIdBytes() {
        return ByteString.copyFromUtf8(this.id_);
    }

    @Override // com.google.firestore.bundle.BundleMetadataOrBuilder
    public long getTotalBytes() {
        return this.totalBytes_;
    }

    @Override // com.google.firestore.bundle.BundleMetadataOrBuilder
    public int getTotalDocuments() {
        return this.totalDocuments_;
    }

    @Override // com.google.firestore.bundle.BundleMetadataOrBuilder
    public int getVersion() {
        return this.version_;
    }

    @Override // com.google.firestore.bundle.BundleMetadataOrBuilder
    public boolean hasCreateTime() {
        return this.createTime_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BundleMetadata, Builder> implements BundleMetadataOrBuilder {
        public Builder() {
            super(BundleMetadata.DEFAULT_INSTANCE);
        }

        public Builder clearCreateTime() {
            copyOnWrite();
            ((BundleMetadata) this.instance).createTime_ = null;
            return this;
        }

        public Builder clearId() {
            copyOnWrite();
            ((BundleMetadata) this.instance).clearId();
            return this;
        }

        public Builder clearTotalBytes() {
            copyOnWrite();
            ((BundleMetadata) this.instance).totalBytes_ = 0L;
            return this;
        }

        public Builder clearTotalDocuments() {
            copyOnWrite();
            ((BundleMetadata) this.instance).totalDocuments_ = 0;
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((BundleMetadata) this.instance).version_ = 0;
            return this;
        }

        @Override // com.google.firestore.bundle.BundleMetadataOrBuilder
        public Timestamp getCreateTime() {
            return ((BundleMetadata) this.instance).getCreateTime();
        }

        @Override // com.google.firestore.bundle.BundleMetadataOrBuilder
        public String getId() {
            return ((BundleMetadata) this.instance).getId();
        }

        @Override // com.google.firestore.bundle.BundleMetadataOrBuilder
        public ByteString getIdBytes() {
            return ((BundleMetadata) this.instance).getIdBytes();
        }

        @Override // com.google.firestore.bundle.BundleMetadataOrBuilder
        public long getTotalBytes() {
            return ((BundleMetadata) this.instance).getTotalBytes();
        }

        @Override // com.google.firestore.bundle.BundleMetadataOrBuilder
        public int getTotalDocuments() {
            return ((BundleMetadata) this.instance).getTotalDocuments();
        }

        @Override // com.google.firestore.bundle.BundleMetadataOrBuilder
        public int getVersion() {
            return ((BundleMetadata) this.instance).getVersion();
        }

        @Override // com.google.firestore.bundle.BundleMetadataOrBuilder
        public boolean hasCreateTime() {
            return ((BundleMetadata) this.instance).hasCreateTime();
        }

        public Builder mergeCreateTime(Timestamp timestamp) {
            copyOnWrite();
            ((BundleMetadata) this.instance).mergeCreateTime(timestamp);
            return this;
        }

        public Builder setCreateTime(Timestamp timestamp) {
            copyOnWrite();
            ((BundleMetadata) this.instance).setCreateTime(timestamp);
            return this;
        }

        public Builder setId(String str) {
            copyOnWrite();
            ((BundleMetadata) this.instance).setId(str);
            return this;
        }

        public Builder setIdBytes(ByteString byteString) {
            copyOnWrite();
            ((BundleMetadata) this.instance).setIdBytes(byteString);
            return this;
        }

        public Builder setTotalBytes(long j) {
            copyOnWrite();
            ((BundleMetadata) this.instance).totalBytes_ = j;
            return this;
        }

        public Builder setTotalDocuments(int i) {
            copyOnWrite();
            ((BundleMetadata) this.instance).totalDocuments_ = i;
            return this;
        }

        public Builder setVersion(int i) {
            copyOnWrite();
            ((BundleMetadata) this.instance).version_ = i;
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(BundleMetadata.DEFAULT_INSTANCE);
        }

        public Builder setCreateTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((BundleMetadata) this.instance).setCreateTime(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(BundleMetadata bundleMetadata) {
        return DEFAULT_INSTANCE.createBuilder(bundleMetadata);
    }

    public static BundleMetadata parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (BundleMetadata) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BundleMetadata parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static BundleMetadata parseFrom(ByteString byteString) {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static BundleMetadata parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static BundleMetadata parseFrom(byte[] bArr) {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static BundleMetadata parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static BundleMetadata parseFrom(InputStream inputStream) {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BundleMetadata parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BundleMetadata parseFrom(CodedInputStream codedInputStream) {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static BundleMetadata parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (BundleMetadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
