package com.google.firestore.v1;

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
public final class WriteResult extends GeneratedMessageLite<WriteResult, Builder> implements WriteResultOrBuilder {
    public static final WriteResult DEFAULT_INSTANCE;
    public static volatile Parser<WriteResult> PARSER = null;
    public static final int TRANSFORM_RESULTS_FIELD_NUMBER = 2;
    public static final int UPDATE_TIME_FIELD_NUMBER = 1;
    public Internal.ProtobufList<Value> transformResults_ = GeneratedMessageLite.emptyProtobufList();
    public Timestamp updateTime_;

    /* JADX INFO: renamed from: com.google.firestore.v1.WriteResult$1, reason: invalid class name */
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
        WriteResult writeResult = new WriteResult();
        DEFAULT_INSTANCE = writeResult;
        GeneratedMessageLite.defaultInstanceMap.put(WriteResult.class, writeResult);
    }

    public static /* synthetic */ void access$700(WriteResult writeResult, Iterable iterable) {
        writeResult.ensureTransformResultsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) writeResult.transformResults_);
    }

    public static /* synthetic */ void access$900(WriteResult writeResult, int i) {
        writeResult.ensureTransformResultsIsMutable();
        writeResult.transformResults_.remove(i);
    }

    private void addAllTransformResults(Iterable<? extends Value> iterable) {
        ensureTransformResultsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.transformResults_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addTransformResults(Value value) {
        value.getClass();
        ensureTransformResultsIsMutable();
        this.transformResults_.add(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTransformResults() {
        this.transformResults_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void clearUpdateTime() {
        this.updateTime_ = null;
    }

    private void ensureTransformResultsIsMutable() {
        Internal.ProtobufList<Value> protobufList = this.transformResults_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.transformResults_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static WriteResult getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeUpdateTime(Timestamp timestamp) {
        timestamp.getClass();
        Timestamp timestamp2 = this.updateTime_;
        if (timestamp2 == null || timestamp2 == Timestamp.DEFAULT_INSTANCE) {
            this.updateTime_ = timestamp;
        } else {
            this.updateTime_ = Timestamp.newBuilder(timestamp2).mergeFrom(timestamp).buildPartial();
        }
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static WriteResult parseDelimitedFrom(InputStream inputStream) {
        return (WriteResult) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static WriteResult parseFrom(ByteBuffer byteBuffer) {
        return (WriteResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<WriteResult> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeTransformResults(int i) {
        ensureTransformResultsIsMutable();
        this.transformResults_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTransformResults(int i, Value value) {
        value.getClass();
        ensureTransformResultsIsMutable();
        this.transformResults_.set(i, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUpdateTime(Timestamp timestamp) {
        timestamp.getClass();
        this.updateTime_ = timestamp;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\t\u0002\u001b", new Object[]{"updateTime_", "transformResults_", Value.class});
            case NEW_MUTABLE_INSTANCE:
                return new WriteResult();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<WriteResult> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (WriteResult.class) {
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

    @Override // com.google.firestore.v1.WriteResultOrBuilder
    public Value getTransformResults(int i) {
        return this.transformResults_.get(i);
    }

    @Override // com.google.firestore.v1.WriteResultOrBuilder
    public int getTransformResultsCount() {
        return this.transformResults_.size();
    }

    @Override // com.google.firestore.v1.WriteResultOrBuilder
    public List<Value> getTransformResultsList() {
        return this.transformResults_;
    }

    public ValueOrBuilder getTransformResultsOrBuilder(int i) {
        return this.transformResults_.get(i);
    }

    public List<? extends ValueOrBuilder> getTransformResultsOrBuilderList() {
        return this.transformResults_;
    }

    @Override // com.google.firestore.v1.WriteResultOrBuilder
    public Timestamp getUpdateTime() {
        Timestamp timestamp = this.updateTime_;
        return timestamp == null ? Timestamp.DEFAULT_INSTANCE : timestamp;
    }

    @Override // com.google.firestore.v1.WriteResultOrBuilder
    public boolean hasUpdateTime() {
        return this.updateTime_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<WriteResult, Builder> implements WriteResultOrBuilder {
        public Builder() {
            super(WriteResult.DEFAULT_INSTANCE);
        }

        public Builder addAllTransformResults(Iterable<? extends Value> iterable) {
            copyOnWrite();
            WriteResult.access$700((WriteResult) this.instance, iterable);
            return this;
        }

        public Builder addTransformResults(Value value) {
            copyOnWrite();
            ((WriteResult) this.instance).addTransformResults(value);
            return this;
        }

        public Builder clearTransformResults() {
            copyOnWrite();
            ((WriteResult) this.instance).clearTransformResults();
            return this;
        }

        public Builder clearUpdateTime() {
            copyOnWrite();
            ((WriteResult) this.instance).updateTime_ = null;
            return this;
        }

        @Override // com.google.firestore.v1.WriteResultOrBuilder
        public Value getTransformResults(int i) {
            return ((WriteResult) this.instance).getTransformResults(i);
        }

        @Override // com.google.firestore.v1.WriteResultOrBuilder
        public int getTransformResultsCount() {
            return ((WriteResult) this.instance).getTransformResultsCount();
        }

        @Override // com.google.firestore.v1.WriteResultOrBuilder
        public List<Value> getTransformResultsList() {
            return Collections.unmodifiableList(((WriteResult) this.instance).getTransformResultsList());
        }

        @Override // com.google.firestore.v1.WriteResultOrBuilder
        public Timestamp getUpdateTime() {
            return ((WriteResult) this.instance).getUpdateTime();
        }

        @Override // com.google.firestore.v1.WriteResultOrBuilder
        public boolean hasUpdateTime() {
            return ((WriteResult) this.instance).hasUpdateTime();
        }

        public Builder mergeUpdateTime(Timestamp timestamp) {
            copyOnWrite();
            ((WriteResult) this.instance).mergeUpdateTime(timestamp);
            return this;
        }

        public Builder removeTransformResults(int i) {
            copyOnWrite();
            WriteResult.access$900((WriteResult) this.instance, i);
            return this;
        }

        public Builder setTransformResults(int i, Value value) {
            copyOnWrite();
            ((WriteResult) this.instance).setTransformResults(i, value);
            return this;
        }

        public Builder setUpdateTime(Timestamp timestamp) {
            copyOnWrite();
            ((WriteResult) this.instance).setUpdateTime(timestamp);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(WriteResult.DEFAULT_INSTANCE);
        }

        public Builder addTransformResults(int i, Value value) {
            copyOnWrite();
            ((WriteResult) this.instance).addTransformResults(i, value);
            return this;
        }

        public Builder setTransformResults(int i, Value.Builder builder) {
            copyOnWrite();
            ((WriteResult) this.instance).setTransformResults(i, builder.build());
            return this;
        }

        public Builder setUpdateTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((WriteResult) this.instance).setUpdateTime(builder.build());
            return this;
        }

        public Builder addTransformResults(Value.Builder builder) {
            copyOnWrite();
            ((WriteResult) this.instance).addTransformResults(builder.build());
            return this;
        }

        public Builder addTransformResults(int i, Value.Builder builder) {
            copyOnWrite();
            ((WriteResult) this.instance).addTransformResults(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(WriteResult writeResult) {
        return DEFAULT_INSTANCE.createBuilder(writeResult);
    }

    public static WriteResult parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (WriteResult) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static WriteResult parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (WriteResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static WriteResult parseFrom(ByteString byteString) {
        return (WriteResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addTransformResults(int i, Value value) {
        value.getClass();
        ensureTransformResultsIsMutable();
        this.transformResults_.add(i, value);
    }

    public static WriteResult parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (WriteResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static WriteResult parseFrom(byte[] bArr) {
        return (WriteResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static WriteResult parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (WriteResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static WriteResult parseFrom(InputStream inputStream) {
        return (WriteResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static WriteResult parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (WriteResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static WriteResult parseFrom(CodedInputStream codedInputStream) {
        return (WriteResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static WriteResult parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (WriteResult) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
