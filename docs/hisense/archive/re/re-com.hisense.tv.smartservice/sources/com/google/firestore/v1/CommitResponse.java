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
public final class CommitResponse extends GeneratedMessageLite<CommitResponse, Builder> implements CommitResponseOrBuilder {
    public static final int COMMIT_TIME_FIELD_NUMBER = 2;
    public static final CommitResponse DEFAULT_INSTANCE;
    public static volatile Parser<CommitResponse> PARSER = null;
    public static final int WRITE_RESULTS_FIELD_NUMBER = 1;
    public Timestamp commitTime_;
    public Internal.ProtobufList<WriteResult> writeResults_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.firestore.v1.CommitResponse$1, reason: invalid class name */
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
        CommitResponse commitResponse = new CommitResponse();
        DEFAULT_INSTANCE = commitResponse;
        GeneratedMessageLite.defaultInstanceMap.put(CommitResponse.class, commitResponse);
    }

    public static /* synthetic */ void access$400(CommitResponse commitResponse, Iterable iterable) {
        commitResponse.ensureWriteResultsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) commitResponse.writeResults_);
    }

    public static /* synthetic */ void access$600(CommitResponse commitResponse, int i) {
        commitResponse.ensureWriteResultsIsMutable();
        commitResponse.writeResults_.remove(i);
    }

    private void addAllWriteResults(Iterable<? extends WriteResult> iterable) {
        ensureWriteResultsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.writeResults_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addWriteResults(WriteResult writeResult) {
        writeResult.getClass();
        ensureWriteResultsIsMutable();
        this.writeResults_.add(writeResult);
    }

    private void clearCommitTime() {
        this.commitTime_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearWriteResults() {
        this.writeResults_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void ensureWriteResultsIsMutable() {
        Internal.ProtobufList<WriteResult> protobufList = this.writeResults_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.writeResults_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static CommitResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeCommitTime(Timestamp timestamp) {
        timestamp.getClass();
        Timestamp timestamp2 = this.commitTime_;
        if (timestamp2 == null || timestamp2 == Timestamp.DEFAULT_INSTANCE) {
            this.commitTime_ = timestamp;
        } else {
            this.commitTime_ = Timestamp.newBuilder(timestamp2).mergeFrom(timestamp).buildPartial();
        }
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static CommitResponse parseDelimitedFrom(InputStream inputStream) {
        return (CommitResponse) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static CommitResponse parseFrom(ByteBuffer byteBuffer) {
        return (CommitResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<CommitResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeWriteResults(int i) {
        ensureWriteResultsIsMutable();
        this.writeResults_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCommitTime(Timestamp timestamp) {
        timestamp.getClass();
        this.commitTime_ = timestamp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWriteResults(int i, WriteResult writeResult) {
        writeResult.getClass();
        ensureWriteResultsIsMutable();
        this.writeResults_.set(i, writeResult);
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002\t", new Object[]{"writeResults_", WriteResult.class, "commitTime_"});
            case NEW_MUTABLE_INSTANCE:
                return new CommitResponse();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<CommitResponse> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (CommitResponse.class) {
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

    @Override // com.google.firestore.v1.CommitResponseOrBuilder
    public Timestamp getCommitTime() {
        Timestamp timestamp = this.commitTime_;
        return timestamp == null ? Timestamp.DEFAULT_INSTANCE : timestamp;
    }

    @Override // com.google.firestore.v1.CommitResponseOrBuilder
    public WriteResult getWriteResults(int i) {
        return this.writeResults_.get(i);
    }

    @Override // com.google.firestore.v1.CommitResponseOrBuilder
    public int getWriteResultsCount() {
        return this.writeResults_.size();
    }

    @Override // com.google.firestore.v1.CommitResponseOrBuilder
    public List<WriteResult> getWriteResultsList() {
        return this.writeResults_;
    }

    public WriteResultOrBuilder getWriteResultsOrBuilder(int i) {
        return this.writeResults_.get(i);
    }

    public List<? extends WriteResultOrBuilder> getWriteResultsOrBuilderList() {
        return this.writeResults_;
    }

    @Override // com.google.firestore.v1.CommitResponseOrBuilder
    public boolean hasCommitTime() {
        return this.commitTime_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<CommitResponse, Builder> implements CommitResponseOrBuilder {
        public Builder() {
            super(CommitResponse.DEFAULT_INSTANCE);
        }

        public Builder addAllWriteResults(Iterable<? extends WriteResult> iterable) {
            copyOnWrite();
            CommitResponse.access$400((CommitResponse) this.instance, iterable);
            return this;
        }

        public Builder addWriteResults(WriteResult writeResult) {
            copyOnWrite();
            ((CommitResponse) this.instance).addWriteResults(writeResult);
            return this;
        }

        public Builder clearCommitTime() {
            copyOnWrite();
            ((CommitResponse) this.instance).commitTime_ = null;
            return this;
        }

        public Builder clearWriteResults() {
            copyOnWrite();
            ((CommitResponse) this.instance).clearWriteResults();
            return this;
        }

        @Override // com.google.firestore.v1.CommitResponseOrBuilder
        public Timestamp getCommitTime() {
            return ((CommitResponse) this.instance).getCommitTime();
        }

        @Override // com.google.firestore.v1.CommitResponseOrBuilder
        public WriteResult getWriteResults(int i) {
            return ((CommitResponse) this.instance).getWriteResults(i);
        }

        @Override // com.google.firestore.v1.CommitResponseOrBuilder
        public int getWriteResultsCount() {
            return ((CommitResponse) this.instance).getWriteResultsCount();
        }

        @Override // com.google.firestore.v1.CommitResponseOrBuilder
        public List<WriteResult> getWriteResultsList() {
            return Collections.unmodifiableList(((CommitResponse) this.instance).getWriteResultsList());
        }

        @Override // com.google.firestore.v1.CommitResponseOrBuilder
        public boolean hasCommitTime() {
            return ((CommitResponse) this.instance).hasCommitTime();
        }

        public Builder mergeCommitTime(Timestamp timestamp) {
            copyOnWrite();
            ((CommitResponse) this.instance).mergeCommitTime(timestamp);
            return this;
        }

        public Builder removeWriteResults(int i) {
            copyOnWrite();
            CommitResponse.access$600((CommitResponse) this.instance, i);
            return this;
        }

        public Builder setCommitTime(Timestamp timestamp) {
            copyOnWrite();
            ((CommitResponse) this.instance).setCommitTime(timestamp);
            return this;
        }

        public Builder setWriteResults(int i, WriteResult writeResult) {
            copyOnWrite();
            ((CommitResponse) this.instance).setWriteResults(i, writeResult);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(CommitResponse.DEFAULT_INSTANCE);
        }

        public Builder addWriteResults(int i, WriteResult writeResult) {
            copyOnWrite();
            ((CommitResponse) this.instance).addWriteResults(i, writeResult);
            return this;
        }

        public Builder setCommitTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((CommitResponse) this.instance).setCommitTime(builder.build());
            return this;
        }

        public Builder setWriteResults(int i, WriteResult.Builder builder) {
            copyOnWrite();
            ((CommitResponse) this.instance).setWriteResults(i, builder.build());
            return this;
        }

        public Builder addWriteResults(WriteResult.Builder builder) {
            copyOnWrite();
            ((CommitResponse) this.instance).addWriteResults(builder.build());
            return this;
        }

        public Builder addWriteResults(int i, WriteResult.Builder builder) {
            copyOnWrite();
            ((CommitResponse) this.instance).addWriteResults(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(CommitResponse commitResponse) {
        return DEFAULT_INSTANCE.createBuilder(commitResponse);
    }

    public static CommitResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (CommitResponse) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static CommitResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (CommitResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static CommitResponse parseFrom(ByteString byteString) {
        return (CommitResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addWriteResults(int i, WriteResult writeResult) {
        writeResult.getClass();
        ensureWriteResultsIsMutable();
        this.writeResults_.add(i, writeResult);
    }

    public static CommitResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (CommitResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static CommitResponse parseFrom(byte[] bArr) {
        return (CommitResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static CommitResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (CommitResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static CommitResponse parseFrom(InputStream inputStream) {
        return (CommitResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static CommitResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (CommitResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static CommitResponse parseFrom(CodedInputStream codedInputStream) {
        return (CommitResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static CommitResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (CommitResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
