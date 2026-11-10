package com.google.firebase.firestore.proto;

import com.google.firestore.v1.Write;
import com.google.firestore.v1.WriteOrBuilder;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class WriteBatch extends GeneratedMessageLite<WriteBatch, Builder> implements WriteBatchOrBuilder {
    public static final int BASE_WRITES_FIELD_NUMBER = 4;
    public static final int BATCH_ID_FIELD_NUMBER = 1;
    public static final WriteBatch DEFAULT_INSTANCE;
    public static final int LOCAL_WRITE_TIME_FIELD_NUMBER = 3;
    public static volatile Parser<WriteBatch> PARSER = null;
    public static final int WRITES_FIELD_NUMBER = 2;
    public int batchId_;
    public Timestamp localWriteTime_;
    public Internal.ProtobufList<Write> writes_ = GeneratedMessageLite.emptyProtobufList();
    public Internal.ProtobufList<Write> baseWrites_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.firebase.firestore.proto.WriteBatch$1, reason: invalid class name */
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
        WriteBatch writeBatch = new WriteBatch();
        DEFAULT_INSTANCE = writeBatch;
        GeneratedMessageLite.defaultInstanceMap.put(WriteBatch.class, writeBatch);
    }

    public static /* synthetic */ void access$1500(WriteBatch writeBatch, Iterable iterable) {
        writeBatch.ensureBaseWritesIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) writeBatch.baseWrites_);
    }

    public static /* synthetic */ void access$1700(WriteBatch writeBatch, int i) {
        writeBatch.ensureBaseWritesIsMutable();
        writeBatch.baseWrites_.remove(i);
    }

    public static /* synthetic */ void access$600(WriteBatch writeBatch, Iterable iterable) {
        writeBatch.ensureWritesIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) writeBatch.writes_);
    }

    public static /* synthetic */ void access$800(WriteBatch writeBatch, int i) {
        writeBatch.ensureWritesIsMutable();
        writeBatch.writes_.remove(i);
    }

    private void addAllBaseWrites(Iterable<? extends Write> iterable) {
        ensureBaseWritesIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.baseWrites_);
    }

    private void addAllWrites(Iterable<? extends Write> iterable) {
        ensureWritesIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.writes_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addBaseWrites(Write write) {
        write.getClass();
        ensureBaseWritesIsMutable();
        this.baseWrites_.add(write);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addWrites(Write write) {
        write.getClass();
        ensureWritesIsMutable();
        this.writes_.add(write);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearBaseWrites() {
        this.baseWrites_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void clearBatchId() {
        this.batchId_ = 0;
    }

    private void clearLocalWriteTime() {
        this.localWriteTime_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearWrites() {
        this.writes_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void ensureBaseWritesIsMutable() {
        Internal.ProtobufList<Write> protobufList = this.baseWrites_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.baseWrites_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    private void ensureWritesIsMutable() {
        Internal.ProtobufList<Write> protobufList = this.writes_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.writes_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static WriteBatch getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeLocalWriteTime(Timestamp timestamp) {
        timestamp.getClass();
        Timestamp timestamp2 = this.localWriteTime_;
        if (timestamp2 == null || timestamp2 == Timestamp.DEFAULT_INSTANCE) {
            this.localWriteTime_ = timestamp;
        } else {
            this.localWriteTime_ = Timestamp.newBuilder(timestamp2).mergeFrom(timestamp).buildPartial();
        }
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static WriteBatch parseDelimitedFrom(InputStream inputStream) {
        return (WriteBatch) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static WriteBatch parseFrom(ByteBuffer byteBuffer) {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<WriteBatch> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeBaseWrites(int i) {
        ensureBaseWritesIsMutable();
        this.baseWrites_.remove(i);
    }

    private void removeWrites(int i) {
        ensureWritesIsMutable();
        this.writes_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBaseWrites(int i, Write write) {
        write.getClass();
        ensureBaseWritesIsMutable();
        this.baseWrites_.set(i, write);
    }

    private void setBatchId(int i) {
        this.batchId_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLocalWriteTime(Timestamp timestamp) {
        timestamp.getClass();
        this.localWriteTime_ = timestamp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWrites(int i, Write write) {
        write.getClass();
        ensureWritesIsMutable();
        this.writes_.set(i, write);
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0002\u0000\u0001\u0004\u0002\u001b\u0003\t\u0004\u001b", new Object[]{"batchId_", "writes_", Write.class, "localWriteTime_", "baseWrites_", Write.class});
            case NEW_MUTABLE_INSTANCE:
                return new WriteBatch();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<WriteBatch> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (WriteBatch.class) {
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

    @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
    public Write getBaseWrites(int i) {
        return this.baseWrites_.get(i);
    }

    @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
    public int getBaseWritesCount() {
        return this.baseWrites_.size();
    }

    @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
    public List<Write> getBaseWritesList() {
        return this.baseWrites_;
    }

    public WriteOrBuilder getBaseWritesOrBuilder(int i) {
        return this.baseWrites_.get(i);
    }

    public List<? extends WriteOrBuilder> getBaseWritesOrBuilderList() {
        return this.baseWrites_;
    }

    @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
    public int getBatchId() {
        return this.batchId_;
    }

    @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
    public Timestamp getLocalWriteTime() {
        Timestamp timestamp = this.localWriteTime_;
        return timestamp == null ? Timestamp.DEFAULT_INSTANCE : timestamp;
    }

    @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
    public Write getWrites(int i) {
        return this.writes_.get(i);
    }

    @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
    public int getWritesCount() {
        return this.writes_.size();
    }

    @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
    public List<Write> getWritesList() {
        return this.writes_;
    }

    public WriteOrBuilder getWritesOrBuilder(int i) {
        return this.writes_.get(i);
    }

    public List<? extends WriteOrBuilder> getWritesOrBuilderList() {
        return this.writes_;
    }

    @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
    public boolean hasLocalWriteTime() {
        return this.localWriteTime_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<WriteBatch, Builder> implements WriteBatchOrBuilder {
        public Builder() {
            super(WriteBatch.DEFAULT_INSTANCE);
        }

        public Builder addAllBaseWrites(Iterable<? extends Write> iterable) {
            copyOnWrite();
            WriteBatch.access$1500((WriteBatch) this.instance, iterable);
            return this;
        }

        public Builder addAllWrites(Iterable<? extends Write> iterable) {
            copyOnWrite();
            WriteBatch.access$600((WriteBatch) this.instance, iterable);
            return this;
        }

        public Builder addBaseWrites(Write write) {
            copyOnWrite();
            ((WriteBatch) this.instance).addBaseWrites(write);
            return this;
        }

        public Builder addWrites(Write write) {
            copyOnWrite();
            ((WriteBatch) this.instance).addWrites(write);
            return this;
        }

        public Builder clearBaseWrites() {
            copyOnWrite();
            ((WriteBatch) this.instance).clearBaseWrites();
            return this;
        }

        public Builder clearBatchId() {
            copyOnWrite();
            ((WriteBatch) this.instance).batchId_ = 0;
            return this;
        }

        public Builder clearLocalWriteTime() {
            copyOnWrite();
            ((WriteBatch) this.instance).localWriteTime_ = null;
            return this;
        }

        public Builder clearWrites() {
            copyOnWrite();
            ((WriteBatch) this.instance).clearWrites();
            return this;
        }

        @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
        public Write getBaseWrites(int i) {
            return ((WriteBatch) this.instance).getBaseWrites(i);
        }

        @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
        public int getBaseWritesCount() {
            return ((WriteBatch) this.instance).getBaseWritesCount();
        }

        @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
        public List<Write> getBaseWritesList() {
            return Collections.unmodifiableList(((WriteBatch) this.instance).getBaseWritesList());
        }

        @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
        public int getBatchId() {
            return ((WriteBatch) this.instance).getBatchId();
        }

        @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
        public Timestamp getLocalWriteTime() {
            return ((WriteBatch) this.instance).getLocalWriteTime();
        }

        @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
        public Write getWrites(int i) {
            return ((WriteBatch) this.instance).getWrites(i);
        }

        @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
        public int getWritesCount() {
            return ((WriteBatch) this.instance).getWritesCount();
        }

        @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
        public List<Write> getWritesList() {
            return Collections.unmodifiableList(((WriteBatch) this.instance).getWritesList());
        }

        @Override // com.google.firebase.firestore.proto.WriteBatchOrBuilder
        public boolean hasLocalWriteTime() {
            return ((WriteBatch) this.instance).hasLocalWriteTime();
        }

        public Builder mergeLocalWriteTime(Timestamp timestamp) {
            copyOnWrite();
            ((WriteBatch) this.instance).mergeLocalWriteTime(timestamp);
            return this;
        }

        public Builder removeBaseWrites(int i) {
            copyOnWrite();
            WriteBatch.access$1700((WriteBatch) this.instance, i);
            return this;
        }

        public Builder removeWrites(int i) {
            copyOnWrite();
            WriteBatch.access$800((WriteBatch) this.instance, i);
            return this;
        }

        public Builder setBaseWrites(int i, Write write) {
            copyOnWrite();
            ((WriteBatch) this.instance).setBaseWrites(i, write);
            return this;
        }

        public Builder setBatchId(int i) {
            copyOnWrite();
            ((WriteBatch) this.instance).batchId_ = i;
            return this;
        }

        public Builder setLocalWriteTime(Timestamp timestamp) {
            copyOnWrite();
            ((WriteBatch) this.instance).setLocalWriteTime(timestamp);
            return this;
        }

        public Builder setWrites(int i, Write write) {
            copyOnWrite();
            ((WriteBatch) this.instance).setWrites(i, write);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(WriteBatch.DEFAULT_INSTANCE);
        }

        public Builder addBaseWrites(int i, Write write) {
            copyOnWrite();
            ((WriteBatch) this.instance).addBaseWrites(i, write);
            return this;
        }

        public Builder addWrites(int i, Write write) {
            copyOnWrite();
            ((WriteBatch) this.instance).addWrites(i, write);
            return this;
        }

        public Builder setBaseWrites(int i, Write.Builder builder) {
            copyOnWrite();
            ((WriteBatch) this.instance).setBaseWrites(i, builder.build());
            return this;
        }

        public Builder setLocalWriteTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((WriteBatch) this.instance).setLocalWriteTime(builder.build());
            return this;
        }

        public Builder setWrites(int i, Write.Builder builder) {
            copyOnWrite();
            ((WriteBatch) this.instance).setWrites(i, builder.build());
            return this;
        }

        public Builder addBaseWrites(Write.Builder builder) {
            copyOnWrite();
            ((WriteBatch) this.instance).addBaseWrites(builder.build());
            return this;
        }

        public Builder addWrites(Write.Builder builder) {
            copyOnWrite();
            ((WriteBatch) this.instance).addWrites(builder.build());
            return this;
        }

        public Builder addBaseWrites(int i, Write.Builder builder) {
            copyOnWrite();
            ((WriteBatch) this.instance).addBaseWrites(i, builder.build());
            return this;
        }

        public Builder addWrites(int i, Write.Builder builder) {
            copyOnWrite();
            ((WriteBatch) this.instance).addWrites(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(WriteBatch writeBatch) {
        return DEFAULT_INSTANCE.createBuilder(writeBatch);
    }

    public static WriteBatch parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (WriteBatch) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static WriteBatch parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static WriteBatch parseFrom(ByteString byteString) {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addBaseWrites(int i, Write write) {
        write.getClass();
        ensureBaseWritesIsMutable();
        this.baseWrites_.add(i, write);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addWrites(int i, Write write) {
        write.getClass();
        ensureWritesIsMutable();
        this.writes_.add(i, write);
    }

    public static WriteBatch parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static WriteBatch parseFrom(byte[] bArr) {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static WriteBatch parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static WriteBatch parseFrom(InputStream inputStream) {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static WriteBatch parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static WriteBatch parseFrom(CodedInputStream codedInputStream) {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static WriteBatch parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (WriteBatch) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
