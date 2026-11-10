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
public final class TargetGlobal extends GeneratedMessageLite<TargetGlobal, Builder> implements TargetGlobalOrBuilder {
    public static final TargetGlobal DEFAULT_INSTANCE;
    public static final int HIGHEST_LISTEN_SEQUENCE_NUMBER_FIELD_NUMBER = 2;
    public static final int HIGHEST_TARGET_ID_FIELD_NUMBER = 1;
    public static final int LAST_REMOTE_SNAPSHOT_VERSION_FIELD_NUMBER = 3;
    public static volatile Parser<TargetGlobal> PARSER = null;
    public static final int TARGET_COUNT_FIELD_NUMBER = 4;
    public long highestListenSequenceNumber_;
    public int highestTargetId_;
    public Timestamp lastRemoteSnapshotVersion_;
    public int targetCount_;

    /* JADX INFO: renamed from: com.google.firebase.firestore.proto.TargetGlobal$1, reason: invalid class name */
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
        TargetGlobal targetGlobal = new TargetGlobal();
        DEFAULT_INSTANCE = targetGlobal;
        GeneratedMessageLite.defaultInstanceMap.put(TargetGlobal.class, targetGlobal);
    }

    private void clearHighestListenSequenceNumber() {
        this.highestListenSequenceNumber_ = 0L;
    }

    private void clearHighestTargetId() {
        this.highestTargetId_ = 0;
    }

    private void clearLastRemoteSnapshotVersion() {
        this.lastRemoteSnapshotVersion_ = null;
    }

    private void clearTargetCount() {
        this.targetCount_ = 0;
    }

    public static TargetGlobal getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeLastRemoteSnapshotVersion(Timestamp timestamp) {
        timestamp.getClass();
        Timestamp timestamp2 = this.lastRemoteSnapshotVersion_;
        if (timestamp2 == null || timestamp2 == Timestamp.DEFAULT_INSTANCE) {
            this.lastRemoteSnapshotVersion_ = timestamp;
        } else {
            this.lastRemoteSnapshotVersion_ = Timestamp.newBuilder(timestamp2).mergeFrom(timestamp).buildPartial();
        }
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static TargetGlobal parseDelimitedFrom(InputStream inputStream) {
        return (TargetGlobal) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TargetGlobal parseFrom(ByteBuffer byteBuffer) {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<TargetGlobal> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void setHighestListenSequenceNumber(long j) {
        this.highestListenSequenceNumber_ = j;
    }

    private void setHighestTargetId(int i) {
        this.highestTargetId_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLastRemoteSnapshotVersion(Timestamp timestamp) {
        timestamp.getClass();
        this.lastRemoteSnapshotVersion_ = timestamp;
    }

    private void setTargetCount(int i) {
        this.targetCount_ = i;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u0004\u0002\u0002\u0003\t\u0004\u0004", new Object[]{"highestTargetId_", "highestListenSequenceNumber_", "lastRemoteSnapshotVersion_", "targetCount_"});
            case NEW_MUTABLE_INSTANCE:
                return new TargetGlobal();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<TargetGlobal> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (TargetGlobal.class) {
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

    @Override // com.google.firebase.firestore.proto.TargetGlobalOrBuilder
    public long getHighestListenSequenceNumber() {
        return this.highestListenSequenceNumber_;
    }

    @Override // com.google.firebase.firestore.proto.TargetGlobalOrBuilder
    public int getHighestTargetId() {
        return this.highestTargetId_;
    }

    @Override // com.google.firebase.firestore.proto.TargetGlobalOrBuilder
    public Timestamp getLastRemoteSnapshotVersion() {
        Timestamp timestamp = this.lastRemoteSnapshotVersion_;
        return timestamp == null ? Timestamp.DEFAULT_INSTANCE : timestamp;
    }

    @Override // com.google.firebase.firestore.proto.TargetGlobalOrBuilder
    public int getTargetCount() {
        return this.targetCount_;
    }

    @Override // com.google.firebase.firestore.proto.TargetGlobalOrBuilder
    public boolean hasLastRemoteSnapshotVersion() {
        return this.lastRemoteSnapshotVersion_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<TargetGlobal, Builder> implements TargetGlobalOrBuilder {
        public Builder() {
            super(TargetGlobal.DEFAULT_INSTANCE);
        }

        public Builder clearHighestListenSequenceNumber() {
            copyOnWrite();
            ((TargetGlobal) this.instance).highestListenSequenceNumber_ = 0L;
            return this;
        }

        public Builder clearHighestTargetId() {
            copyOnWrite();
            ((TargetGlobal) this.instance).highestTargetId_ = 0;
            return this;
        }

        public Builder clearLastRemoteSnapshotVersion() {
            copyOnWrite();
            ((TargetGlobal) this.instance).lastRemoteSnapshotVersion_ = null;
            return this;
        }

        public Builder clearTargetCount() {
            copyOnWrite();
            ((TargetGlobal) this.instance).targetCount_ = 0;
            return this;
        }

        @Override // com.google.firebase.firestore.proto.TargetGlobalOrBuilder
        public long getHighestListenSequenceNumber() {
            return ((TargetGlobal) this.instance).getHighestListenSequenceNumber();
        }

        @Override // com.google.firebase.firestore.proto.TargetGlobalOrBuilder
        public int getHighestTargetId() {
            return ((TargetGlobal) this.instance).getHighestTargetId();
        }

        @Override // com.google.firebase.firestore.proto.TargetGlobalOrBuilder
        public Timestamp getLastRemoteSnapshotVersion() {
            return ((TargetGlobal) this.instance).getLastRemoteSnapshotVersion();
        }

        @Override // com.google.firebase.firestore.proto.TargetGlobalOrBuilder
        public int getTargetCount() {
            return ((TargetGlobal) this.instance).getTargetCount();
        }

        @Override // com.google.firebase.firestore.proto.TargetGlobalOrBuilder
        public boolean hasLastRemoteSnapshotVersion() {
            return ((TargetGlobal) this.instance).hasLastRemoteSnapshotVersion();
        }

        public Builder mergeLastRemoteSnapshotVersion(Timestamp timestamp) {
            copyOnWrite();
            ((TargetGlobal) this.instance).mergeLastRemoteSnapshotVersion(timestamp);
            return this;
        }

        public Builder setHighestListenSequenceNumber(long j) {
            copyOnWrite();
            ((TargetGlobal) this.instance).highestListenSequenceNumber_ = j;
            return this;
        }

        public Builder setHighestTargetId(int i) {
            copyOnWrite();
            ((TargetGlobal) this.instance).highestTargetId_ = i;
            return this;
        }

        public Builder setLastRemoteSnapshotVersion(Timestamp timestamp) {
            copyOnWrite();
            ((TargetGlobal) this.instance).setLastRemoteSnapshotVersion(timestamp);
            return this;
        }

        public Builder setTargetCount(int i) {
            copyOnWrite();
            ((TargetGlobal) this.instance).targetCount_ = i;
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(TargetGlobal.DEFAULT_INSTANCE);
        }

        public Builder setLastRemoteSnapshotVersion(Timestamp.Builder builder) {
            copyOnWrite();
            ((TargetGlobal) this.instance).setLastRemoteSnapshotVersion(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(TargetGlobal targetGlobal) {
        return DEFAULT_INSTANCE.createBuilder(targetGlobal);
    }

    public static TargetGlobal parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (TargetGlobal) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static TargetGlobal parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static TargetGlobal parseFrom(ByteString byteString) {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static TargetGlobal parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static TargetGlobal parseFrom(byte[] bArr) {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static TargetGlobal parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static TargetGlobal parseFrom(InputStream inputStream) {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TargetGlobal parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static TargetGlobal parseFrom(CodedInputStream codedInputStream) {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static TargetGlobal parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (TargetGlobal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
