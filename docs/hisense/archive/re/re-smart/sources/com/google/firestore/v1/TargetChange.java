package com.google.firestore.v1;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import com.google.rpc.Status;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class TargetChange extends GeneratedMessageLite<TargetChange, Builder> implements TargetChangeOrBuilder {
    public static final int CAUSE_FIELD_NUMBER = 3;
    public static final TargetChange DEFAULT_INSTANCE;
    public static volatile Parser<TargetChange> PARSER = null;
    public static final int READ_TIME_FIELD_NUMBER = 6;
    public static final int RESUME_TOKEN_FIELD_NUMBER = 4;
    public static final int TARGET_CHANGE_TYPE_FIELD_NUMBER = 1;
    public static final int TARGET_IDS_FIELD_NUMBER = 2;
    public Status cause_;
    public Timestamp readTime_;
    public int targetChangeType_;
    public int targetIdsMemoizedSerializedSize = -1;
    public Internal.IntList targetIds_ = GeneratedMessageLite.emptyIntList();
    public ByteString resumeToken_ = ByteString.EMPTY;

    /* JADX INFO: renamed from: com.google.firestore.v1.TargetChange$1, reason: invalid class name */
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

    public enum TargetChangeType implements Internal.EnumLite {
        NO_CHANGE(0),
        ADD(1),
        REMOVE(2),
        CURRENT(3),
        RESET(4),
        UNRECOGNIZED(-1);

        public static final int ADD_VALUE = 1;
        public static final int CURRENT_VALUE = 3;
        public static final int NO_CHANGE_VALUE = 0;
        public static final int REMOVE_VALUE = 2;
        public static final int RESET_VALUE = 4;
        public static final Internal.EnumLiteMap<TargetChangeType> internalValueMap = new Internal.EnumLiteMap<TargetChangeType>() { // from class: com.google.firestore.v1.TargetChange.TargetChangeType.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public TargetChangeType findValueByNumber(int i) {
                return TargetChangeType.forNumber(i);
            }
        };
        public final int value;

        private static final class TargetChangeTypeVerifier implements Internal.EnumVerifier {
            public static final Internal.EnumVerifier INSTANCE = new TargetChangeTypeVerifier();

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return TargetChangeType.forNumber(i) != null;
            }
        }

        TargetChangeType(int i) {
            this.value = i;
        }

        public static TargetChangeType forNumber(int i) {
            if (i == 0) {
                return NO_CHANGE;
            }
            if (i == 1) {
                return ADD;
            }
            if (i == 2) {
                return REMOVE;
            }
            if (i == 3) {
                return CURRENT;
            }
            if (i != 4) {
                return null;
            }
            return RESET;
        }

        public static Internal.EnumLiteMap<TargetChangeType> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return TargetChangeTypeVerifier.INSTANCE;
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Deprecated
        public static TargetChangeType valueOf(int i) {
            return forNumber(i);
        }
    }

    static {
        TargetChange targetChange = new TargetChange();
        DEFAULT_INSTANCE = targetChange;
        GeneratedMessageLite.defaultInstanceMap.put(TargetChange.class, targetChange);
    }

    public static /* synthetic */ void access$400(TargetChange targetChange, int i, int i2) {
        targetChange.ensureTargetIdsIsMutable();
        targetChange.targetIds_.setInt(i, i2);
    }

    public static /* synthetic */ void access$500(TargetChange targetChange, int i) {
        targetChange.ensureTargetIdsIsMutable();
        targetChange.targetIds_.addInt(i);
    }

    public static /* synthetic */ void access$600(TargetChange targetChange, Iterable iterable) {
        targetChange.ensureTargetIdsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) targetChange.targetIds_);
    }

    private void addAllTargetIds(Iterable<? extends Integer> iterable) {
        ensureTargetIdsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.targetIds_);
    }

    private void addTargetIds(int i) {
        ensureTargetIdsIsMutable();
        this.targetIds_.addInt(i);
    }

    private void clearCause() {
        this.cause_ = null;
    }

    private void clearReadTime() {
        this.readTime_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearResumeToken() {
        this.resumeToken_ = DEFAULT_INSTANCE.getResumeToken();
    }

    private void clearTargetChangeType() {
        this.targetChangeType_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTargetIds() {
        this.targetIds_ = GeneratedMessageLite.emptyIntList();
    }

    private void ensureTargetIdsIsMutable() {
        Internal.IntList intList = this.targetIds_;
        if (intList.isModifiable()) {
            return;
        }
        this.targetIds_ = GeneratedMessageLite.mutableCopy(intList);
    }

    public static TargetChange getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeCause(Status status) {
        status.getClass();
        Status status2 = this.cause_;
        if (status2 == null || status2 == Status.DEFAULT_INSTANCE) {
            this.cause_ = status;
        } else {
            this.cause_ = Status.newBuilder(status2).mergeFrom(status).buildPartial();
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

    public static TargetChange parseDelimitedFrom(InputStream inputStream) {
        return (TargetChange) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TargetChange parseFrom(ByteBuffer byteBuffer) {
        return (TargetChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<TargetChange> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCause(Status status) {
        status.getClass();
        this.cause_ = status;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReadTime(Timestamp timestamp) {
        timestamp.getClass();
        this.readTime_ = timestamp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResumeToken(ByteString byteString) {
        byteString.getClass();
        this.resumeToken_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTargetChangeType(TargetChangeType targetChangeType) {
        this.targetChangeType_ = targetChangeType.getNumber();
    }

    private void setTargetChangeTypeValue(int i) {
        this.targetChangeType_ = i;
    }

    private void setTargetIds(int i, int i2) {
        ensureTargetIdsIsMutable();
        this.targetIds_.setInt(i, i2);
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0000\u0001\u0006\u0005\u0000\u0001\u0000\u0001\f\u0002'\u0003\t\u0004\n\u0006\t", new Object[]{"targetChangeType_", "targetIds_", "cause_", "resumeToken_", "readTime_"});
            case NEW_MUTABLE_INSTANCE:
                return new TargetChange();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<TargetChange> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (TargetChange.class) {
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

    @Override // com.google.firestore.v1.TargetChangeOrBuilder
    public Status getCause() {
        Status status = this.cause_;
        return status == null ? Status.DEFAULT_INSTANCE : status;
    }

    @Override // com.google.firestore.v1.TargetChangeOrBuilder
    public Timestamp getReadTime() {
        Timestamp timestamp = this.readTime_;
        return timestamp == null ? Timestamp.DEFAULT_INSTANCE : timestamp;
    }

    @Override // com.google.firestore.v1.TargetChangeOrBuilder
    public ByteString getResumeToken() {
        return this.resumeToken_;
    }

    @Override // com.google.firestore.v1.TargetChangeOrBuilder
    public TargetChangeType getTargetChangeType() {
        TargetChangeType targetChangeTypeForNumber = TargetChangeType.forNumber(this.targetChangeType_);
        return targetChangeTypeForNumber == null ? TargetChangeType.UNRECOGNIZED : targetChangeTypeForNumber;
    }

    @Override // com.google.firestore.v1.TargetChangeOrBuilder
    public int getTargetChangeTypeValue() {
        return this.targetChangeType_;
    }

    @Override // com.google.firestore.v1.TargetChangeOrBuilder
    public int getTargetIds(int i) {
        return this.targetIds_.getInt(i);
    }

    @Override // com.google.firestore.v1.TargetChangeOrBuilder
    public int getTargetIdsCount() {
        return this.targetIds_.size();
    }

    @Override // com.google.firestore.v1.TargetChangeOrBuilder
    public List<Integer> getTargetIdsList() {
        return this.targetIds_;
    }

    @Override // com.google.firestore.v1.TargetChangeOrBuilder
    public boolean hasCause() {
        return this.cause_ != null;
    }

    @Override // com.google.firestore.v1.TargetChangeOrBuilder
    public boolean hasReadTime() {
        return this.readTime_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<TargetChange, Builder> implements TargetChangeOrBuilder {
        public Builder() {
            super(TargetChange.DEFAULT_INSTANCE);
        }

        public Builder addAllTargetIds(Iterable<? extends Integer> iterable) {
            copyOnWrite();
            TargetChange.access$600((TargetChange) this.instance, iterable);
            return this;
        }

        public Builder addTargetIds(int i) {
            copyOnWrite();
            TargetChange.access$500((TargetChange) this.instance, i);
            return this;
        }

        public Builder clearCause() {
            copyOnWrite();
            ((TargetChange) this.instance).cause_ = null;
            return this;
        }

        public Builder clearReadTime() {
            copyOnWrite();
            ((TargetChange) this.instance).readTime_ = null;
            return this;
        }

        public Builder clearResumeToken() {
            copyOnWrite();
            ((TargetChange) this.instance).clearResumeToken();
            return this;
        }

        public Builder clearTargetChangeType() {
            copyOnWrite();
            ((TargetChange) this.instance).targetChangeType_ = 0;
            return this;
        }

        public Builder clearTargetIds() {
            copyOnWrite();
            ((TargetChange) this.instance).clearTargetIds();
            return this;
        }

        @Override // com.google.firestore.v1.TargetChangeOrBuilder
        public Status getCause() {
            return ((TargetChange) this.instance).getCause();
        }

        @Override // com.google.firestore.v1.TargetChangeOrBuilder
        public Timestamp getReadTime() {
            return ((TargetChange) this.instance).getReadTime();
        }

        @Override // com.google.firestore.v1.TargetChangeOrBuilder
        public ByteString getResumeToken() {
            return ((TargetChange) this.instance).getResumeToken();
        }

        @Override // com.google.firestore.v1.TargetChangeOrBuilder
        public TargetChangeType getTargetChangeType() {
            return ((TargetChange) this.instance).getTargetChangeType();
        }

        @Override // com.google.firestore.v1.TargetChangeOrBuilder
        public int getTargetChangeTypeValue() {
            return ((TargetChange) this.instance).getTargetChangeTypeValue();
        }

        @Override // com.google.firestore.v1.TargetChangeOrBuilder
        public int getTargetIds(int i) {
            return ((TargetChange) this.instance).getTargetIds(i);
        }

        @Override // com.google.firestore.v1.TargetChangeOrBuilder
        public int getTargetIdsCount() {
            return ((TargetChange) this.instance).getTargetIdsCount();
        }

        @Override // com.google.firestore.v1.TargetChangeOrBuilder
        public List<Integer> getTargetIdsList() {
            return Collections.unmodifiableList(((TargetChange) this.instance).getTargetIdsList());
        }

        @Override // com.google.firestore.v1.TargetChangeOrBuilder
        public boolean hasCause() {
            return ((TargetChange) this.instance).hasCause();
        }

        @Override // com.google.firestore.v1.TargetChangeOrBuilder
        public boolean hasReadTime() {
            return ((TargetChange) this.instance).hasReadTime();
        }

        public Builder mergeCause(Status status) {
            copyOnWrite();
            ((TargetChange) this.instance).mergeCause(status);
            return this;
        }

        public Builder mergeReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((TargetChange) this.instance).mergeReadTime(timestamp);
            return this;
        }

        public Builder setCause(Status status) {
            copyOnWrite();
            ((TargetChange) this.instance).setCause(status);
            return this;
        }

        public Builder setReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((TargetChange) this.instance).setReadTime(timestamp);
            return this;
        }

        public Builder setResumeToken(ByteString byteString) {
            copyOnWrite();
            ((TargetChange) this.instance).setResumeToken(byteString);
            return this;
        }

        public Builder setTargetChangeType(TargetChangeType targetChangeType) {
            copyOnWrite();
            ((TargetChange) this.instance).setTargetChangeType(targetChangeType);
            return this;
        }

        public Builder setTargetChangeTypeValue(int i) {
            copyOnWrite();
            ((TargetChange) this.instance).targetChangeType_ = i;
            return this;
        }

        public Builder setTargetIds(int i, int i2) {
            copyOnWrite();
            TargetChange.access$400((TargetChange) this.instance, i, i2);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(TargetChange.DEFAULT_INSTANCE);
        }

        public Builder setCause(Status.Builder builder) {
            copyOnWrite();
            ((TargetChange) this.instance).setCause(builder.build());
            return this;
        }

        public Builder setReadTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((TargetChange) this.instance).setReadTime(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(TargetChange targetChange) {
        return DEFAULT_INSTANCE.createBuilder(targetChange);
    }

    public static TargetChange parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (TargetChange) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static TargetChange parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (TargetChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static TargetChange parseFrom(ByteString byteString) {
        return (TargetChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static TargetChange parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (TargetChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static TargetChange parseFrom(byte[] bArr) {
        return (TargetChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static TargetChange parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (TargetChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static TargetChange parseFrom(InputStream inputStream) {
        return (TargetChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TargetChange parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (TargetChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static TargetChange parseFrom(CodedInputStream codedInputStream) {
        return (TargetChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static TargetChange parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (TargetChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
