package com.google.firebase.firestore.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class Target extends GeneratedMessageLite<Target, Builder> implements TargetOrBuilder {
    public static final Target DEFAULT_INSTANCE;
    public static final int DOCUMENTS_FIELD_NUMBER = 6;
    public static final int LAST_LIMBO_FREE_SNAPSHOT_VERSION_FIELD_NUMBER = 7;
    public static final int LAST_LISTEN_SEQUENCE_NUMBER_FIELD_NUMBER = 4;
    public static volatile Parser<Target> PARSER = null;
    public static final int QUERY_FIELD_NUMBER = 5;
    public static final int RESUME_TOKEN_FIELD_NUMBER = 3;
    public static final int SNAPSHOT_VERSION_FIELD_NUMBER = 2;
    public static final int TARGET_ID_FIELD_NUMBER = 1;
    public Timestamp lastLimboFreeSnapshotVersion_;
    public long lastListenSequenceNumber_;
    public Timestamp snapshotVersion_;
    public int targetId_;
    public Object targetType_;
    public int targetTypeCase_ = 0;
    public ByteString resumeToken_ = ByteString.EMPTY;

    /* JADX INFO: renamed from: com.google.firebase.firestore.proto.Target$1, reason: invalid class name */
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

    public enum TargetTypeCase {
        QUERY(5),
        DOCUMENTS(6),
        TARGETTYPE_NOT_SET(0);

        public final int value;

        TargetTypeCase(int i) {
            this.value = i;
        }

        public static TargetTypeCase forNumber(int i) {
            if (i == 0) {
                return TARGETTYPE_NOT_SET;
            }
            if (i == 5) {
                return QUERY;
            }
            if (i != 6) {
                return null;
            }
            return DOCUMENTS;
        }

        public int getNumber() {
            return this.value;
        }

        @Deprecated
        public static TargetTypeCase valueOf(int i) {
            return forNumber(i);
        }
    }

    static {
        Target target = new Target();
        DEFAULT_INSTANCE = target;
        GeneratedMessageLite.defaultInstanceMap.put(Target.class, target);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDocuments() {
        if (this.targetTypeCase_ == 6) {
            this.targetTypeCase_ = 0;
            this.targetType_ = null;
        }
    }

    private void clearLastLimboFreeSnapshotVersion() {
        this.lastLimboFreeSnapshotVersion_ = null;
    }

    private void clearLastListenSequenceNumber() {
        this.lastListenSequenceNumber_ = 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearQuery() {
        if (this.targetTypeCase_ == 5) {
            this.targetTypeCase_ = 0;
            this.targetType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearResumeToken() {
        this.resumeToken_ = DEFAULT_INSTANCE.getResumeToken();
    }

    private void clearSnapshotVersion() {
        this.snapshotVersion_ = null;
    }

    private void clearTargetId() {
        this.targetId_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTargetType() {
        this.targetTypeCase_ = 0;
        this.targetType_ = null;
    }

    public static Target getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeDocuments(com.google.firestore.v1.Target.DocumentsTarget documentsTarget) {
        Object obj;
        documentsTarget.getClass();
        if (this.targetTypeCase_ != 6 || (obj = this.targetType_) == com.google.firestore.v1.Target.DocumentsTarget.DEFAULT_INSTANCE) {
            this.targetType_ = documentsTarget;
        } else {
            this.targetType_ = com.google.firestore.v1.Target.DocumentsTarget.newBuilder((com.google.firestore.v1.Target.DocumentsTarget) obj).mergeFrom(documentsTarget).buildPartial();
        }
        this.targetTypeCase_ = 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeLastLimboFreeSnapshotVersion(Timestamp timestamp) {
        timestamp.getClass();
        Timestamp timestamp2 = this.lastLimboFreeSnapshotVersion_;
        if (timestamp2 == null || timestamp2 == Timestamp.DEFAULT_INSTANCE) {
            this.lastLimboFreeSnapshotVersion_ = timestamp;
        } else {
            this.lastLimboFreeSnapshotVersion_ = Timestamp.newBuilder(timestamp2).mergeFrom(timestamp).buildPartial();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeQuery(com.google.firestore.v1.Target.QueryTarget queryTarget) {
        Object obj;
        queryTarget.getClass();
        if (this.targetTypeCase_ != 5 || (obj = this.targetType_) == com.google.firestore.v1.Target.QueryTarget.DEFAULT_INSTANCE) {
            this.targetType_ = queryTarget;
        } else {
            this.targetType_ = com.google.firestore.v1.Target.QueryTarget.newBuilder((com.google.firestore.v1.Target.QueryTarget) obj).mergeFrom(queryTarget).buildPartial();
        }
        this.targetTypeCase_ = 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeSnapshotVersion(Timestamp timestamp) {
        timestamp.getClass();
        Timestamp timestamp2 = this.snapshotVersion_;
        if (timestamp2 == null || timestamp2 == Timestamp.DEFAULT_INSTANCE) {
            this.snapshotVersion_ = timestamp;
        } else {
            this.snapshotVersion_ = Timestamp.newBuilder(timestamp2).mergeFrom(timestamp).buildPartial();
        }
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Target parseDelimitedFrom(InputStream inputStream) {
        return (Target) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Target parseFrom(ByteBuffer byteBuffer) {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Target> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDocuments(com.google.firestore.v1.Target.DocumentsTarget documentsTarget) {
        documentsTarget.getClass();
        this.targetType_ = documentsTarget;
        this.targetTypeCase_ = 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLastLimboFreeSnapshotVersion(Timestamp timestamp) {
        timestamp.getClass();
        this.lastLimboFreeSnapshotVersion_ = timestamp;
    }

    private void setLastListenSequenceNumber(long j) {
        this.lastListenSequenceNumber_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setQuery(com.google.firestore.v1.Target.QueryTarget queryTarget) {
        queryTarget.getClass();
        this.targetType_ = queryTarget;
        this.targetTypeCase_ = 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResumeToken(ByteString byteString) {
        byteString.getClass();
        this.resumeToken_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSnapshotVersion(Timestamp timestamp) {
        timestamp.getClass();
        this.snapshotVersion_ = timestamp;
    }

    private void setTargetId(int i) {
        this.targetId_ = i;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0007\u0001\u0000\u0001\u0007\u0007\u0000\u0000\u0000\u0001\u0004\u0002\t\u0003\n\u0004\u0002\u0005<\u0000\u0006<\u0000\u0007\t", new Object[]{"targetType_", "targetTypeCase_", "targetId_", "snapshotVersion_", "resumeToken_", "lastListenSequenceNumber_", com.google.firestore.v1.Target.QueryTarget.class, com.google.firestore.v1.Target.DocumentsTarget.class, "lastLimboFreeSnapshotVersion_"});
            case NEW_MUTABLE_INSTANCE:
                return new Target();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Target> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Target.class) {
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

    @Override // com.google.firebase.firestore.proto.TargetOrBuilder
    public com.google.firestore.v1.Target.DocumentsTarget getDocuments() {
        return this.targetTypeCase_ == 6 ? (com.google.firestore.v1.Target.DocumentsTarget) this.targetType_ : com.google.firestore.v1.Target.DocumentsTarget.DEFAULT_INSTANCE;
    }

    @Override // com.google.firebase.firestore.proto.TargetOrBuilder
    public Timestamp getLastLimboFreeSnapshotVersion() {
        Timestamp timestamp = this.lastLimboFreeSnapshotVersion_;
        return timestamp == null ? Timestamp.DEFAULT_INSTANCE : timestamp;
    }

    @Override // com.google.firebase.firestore.proto.TargetOrBuilder
    public long getLastListenSequenceNumber() {
        return this.lastListenSequenceNumber_;
    }

    @Override // com.google.firebase.firestore.proto.TargetOrBuilder
    public com.google.firestore.v1.Target.QueryTarget getQuery() {
        return this.targetTypeCase_ == 5 ? (com.google.firestore.v1.Target.QueryTarget) this.targetType_ : com.google.firestore.v1.Target.QueryTarget.DEFAULT_INSTANCE;
    }

    @Override // com.google.firebase.firestore.proto.TargetOrBuilder
    public ByteString getResumeToken() {
        return this.resumeToken_;
    }

    @Override // com.google.firebase.firestore.proto.TargetOrBuilder
    public Timestamp getSnapshotVersion() {
        Timestamp timestamp = this.snapshotVersion_;
        return timestamp == null ? Timestamp.DEFAULT_INSTANCE : timestamp;
    }

    @Override // com.google.firebase.firestore.proto.TargetOrBuilder
    public int getTargetId() {
        return this.targetId_;
    }

    @Override // com.google.firebase.firestore.proto.TargetOrBuilder
    public TargetTypeCase getTargetTypeCase() {
        return TargetTypeCase.forNumber(this.targetTypeCase_);
    }

    @Override // com.google.firebase.firestore.proto.TargetOrBuilder
    public boolean hasDocuments() {
        return this.targetTypeCase_ == 6;
    }

    @Override // com.google.firebase.firestore.proto.TargetOrBuilder
    public boolean hasLastLimboFreeSnapshotVersion() {
        return this.lastLimboFreeSnapshotVersion_ != null;
    }

    @Override // com.google.firebase.firestore.proto.TargetOrBuilder
    public boolean hasQuery() {
        return this.targetTypeCase_ == 5;
    }

    @Override // com.google.firebase.firestore.proto.TargetOrBuilder
    public boolean hasSnapshotVersion() {
        return this.snapshotVersion_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Target, Builder> implements TargetOrBuilder {
        public Builder() {
            super(Target.DEFAULT_INSTANCE);
        }

        public Builder clearDocuments() {
            copyOnWrite();
            ((Target) this.instance).clearDocuments();
            return this;
        }

        public Builder clearLastLimboFreeSnapshotVersion() {
            copyOnWrite();
            ((Target) this.instance).lastLimboFreeSnapshotVersion_ = null;
            return this;
        }

        public Builder clearLastListenSequenceNumber() {
            copyOnWrite();
            ((Target) this.instance).lastListenSequenceNumber_ = 0L;
            return this;
        }

        public Builder clearQuery() {
            copyOnWrite();
            ((Target) this.instance).clearQuery();
            return this;
        }

        public Builder clearResumeToken() {
            copyOnWrite();
            ((Target) this.instance).clearResumeToken();
            return this;
        }

        public Builder clearSnapshotVersion() {
            copyOnWrite();
            ((Target) this.instance).snapshotVersion_ = null;
            return this;
        }

        public Builder clearTargetId() {
            copyOnWrite();
            ((Target) this.instance).targetId_ = 0;
            return this;
        }

        public Builder clearTargetType() {
            copyOnWrite();
            ((Target) this.instance).clearTargetType();
            return this;
        }

        @Override // com.google.firebase.firestore.proto.TargetOrBuilder
        public com.google.firestore.v1.Target.DocumentsTarget getDocuments() {
            return ((Target) this.instance).getDocuments();
        }

        @Override // com.google.firebase.firestore.proto.TargetOrBuilder
        public Timestamp getLastLimboFreeSnapshotVersion() {
            return ((Target) this.instance).getLastLimboFreeSnapshotVersion();
        }

        @Override // com.google.firebase.firestore.proto.TargetOrBuilder
        public long getLastListenSequenceNumber() {
            return ((Target) this.instance).getLastListenSequenceNumber();
        }

        @Override // com.google.firebase.firestore.proto.TargetOrBuilder
        public com.google.firestore.v1.Target.QueryTarget getQuery() {
            return ((Target) this.instance).getQuery();
        }

        @Override // com.google.firebase.firestore.proto.TargetOrBuilder
        public ByteString getResumeToken() {
            return ((Target) this.instance).getResumeToken();
        }

        @Override // com.google.firebase.firestore.proto.TargetOrBuilder
        public Timestamp getSnapshotVersion() {
            return ((Target) this.instance).getSnapshotVersion();
        }

        @Override // com.google.firebase.firestore.proto.TargetOrBuilder
        public int getTargetId() {
            return ((Target) this.instance).getTargetId();
        }

        @Override // com.google.firebase.firestore.proto.TargetOrBuilder
        public TargetTypeCase getTargetTypeCase() {
            return ((Target) this.instance).getTargetTypeCase();
        }

        @Override // com.google.firebase.firestore.proto.TargetOrBuilder
        public boolean hasDocuments() {
            return ((Target) this.instance).hasDocuments();
        }

        @Override // com.google.firebase.firestore.proto.TargetOrBuilder
        public boolean hasLastLimboFreeSnapshotVersion() {
            return ((Target) this.instance).hasLastLimboFreeSnapshotVersion();
        }

        @Override // com.google.firebase.firestore.proto.TargetOrBuilder
        public boolean hasQuery() {
            return ((Target) this.instance).hasQuery();
        }

        @Override // com.google.firebase.firestore.proto.TargetOrBuilder
        public boolean hasSnapshotVersion() {
            return ((Target) this.instance).hasSnapshotVersion();
        }

        public Builder mergeDocuments(com.google.firestore.v1.Target.DocumentsTarget documentsTarget) {
            copyOnWrite();
            ((Target) this.instance).mergeDocuments(documentsTarget);
            return this;
        }

        public Builder mergeLastLimboFreeSnapshotVersion(Timestamp timestamp) {
            copyOnWrite();
            ((Target) this.instance).mergeLastLimboFreeSnapshotVersion(timestamp);
            return this;
        }

        public Builder mergeQuery(com.google.firestore.v1.Target.QueryTarget queryTarget) {
            copyOnWrite();
            ((Target) this.instance).mergeQuery(queryTarget);
            return this;
        }

        public Builder mergeSnapshotVersion(Timestamp timestamp) {
            copyOnWrite();
            ((Target) this.instance).mergeSnapshotVersion(timestamp);
            return this;
        }

        public Builder setDocuments(com.google.firestore.v1.Target.DocumentsTarget documentsTarget) {
            copyOnWrite();
            ((Target) this.instance).setDocuments(documentsTarget);
            return this;
        }

        public Builder setLastLimboFreeSnapshotVersion(Timestamp timestamp) {
            copyOnWrite();
            ((Target) this.instance).setLastLimboFreeSnapshotVersion(timestamp);
            return this;
        }

        public Builder setLastListenSequenceNumber(long j) {
            copyOnWrite();
            ((Target) this.instance).lastListenSequenceNumber_ = j;
            return this;
        }

        public Builder setQuery(com.google.firestore.v1.Target.QueryTarget queryTarget) {
            copyOnWrite();
            ((Target) this.instance).setQuery(queryTarget);
            return this;
        }

        public Builder setResumeToken(ByteString byteString) {
            copyOnWrite();
            ((Target) this.instance).setResumeToken(byteString);
            return this;
        }

        public Builder setSnapshotVersion(Timestamp timestamp) {
            copyOnWrite();
            ((Target) this.instance).setSnapshotVersion(timestamp);
            return this;
        }

        public Builder setTargetId(int i) {
            copyOnWrite();
            ((Target) this.instance).targetId_ = i;
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Target.DEFAULT_INSTANCE);
        }

        public Builder setDocuments(com.google.firestore.v1.Target.DocumentsTarget.Builder builder) {
            copyOnWrite();
            ((Target) this.instance).setDocuments(builder.build());
            return this;
        }

        public Builder setLastLimboFreeSnapshotVersion(Timestamp.Builder builder) {
            copyOnWrite();
            ((Target) this.instance).setLastLimboFreeSnapshotVersion(builder.build());
            return this;
        }

        public Builder setQuery(com.google.firestore.v1.Target.QueryTarget.Builder builder) {
            copyOnWrite();
            ((Target) this.instance).setQuery(builder.build());
            return this;
        }

        public Builder setSnapshotVersion(Timestamp.Builder builder) {
            copyOnWrite();
            ((Target) this.instance).setSnapshotVersion(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(Target target) {
        return DEFAULT_INSTANCE.createBuilder(target);
    }

    public static Target parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Target) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Target parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Target parseFrom(ByteString byteString) {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Target parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Target parseFrom(byte[] bArr) {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Target parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Target parseFrom(InputStream inputStream) {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Target parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Target parseFrom(CodedInputStream codedInputStream) {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Target parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Target) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
