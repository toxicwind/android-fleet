package com.google.firestore.v1;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class CommitRequest extends GeneratedMessageLite<CommitRequest, Builder> implements CommitRequestOrBuilder {
    public static final int DATABASE_FIELD_NUMBER = 1;
    public static final CommitRequest DEFAULT_INSTANCE;
    public static volatile Parser<CommitRequest> PARSER = null;
    public static final int TRANSACTION_FIELD_NUMBER = 3;
    public static final int WRITES_FIELD_NUMBER = 2;
    public String database_ = "";
    public Internal.ProtobufList<Write> writes_ = GeneratedMessageLite.emptyProtobufList();
    public ByteString transaction_ = ByteString.EMPTY;

    /* JADX INFO: renamed from: com.google.firestore.v1.CommitRequest$1, reason: invalid class name */
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
        CommitRequest commitRequest = new CommitRequest();
        DEFAULT_INSTANCE = commitRequest;
        GeneratedMessageLite.defaultInstanceMap.put(CommitRequest.class, commitRequest);
    }

    public static /* synthetic */ void access$700(CommitRequest commitRequest, Iterable iterable) {
        commitRequest.ensureWritesIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) commitRequest.writes_);
    }

    public static /* synthetic */ void access$900(CommitRequest commitRequest, int i) {
        commitRequest.ensureWritesIsMutable();
        commitRequest.writes_.remove(i);
    }

    private void addAllWrites(Iterable<? extends Write> iterable) {
        ensureWritesIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.writes_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addWrites(Write write) {
        write.getClass();
        ensureWritesIsMutable();
        this.writes_.add(write);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDatabase() {
        this.database_ = DEFAULT_INSTANCE.getDatabase();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTransaction() {
        this.transaction_ = DEFAULT_INSTANCE.getTransaction();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearWrites() {
        this.writes_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void ensureWritesIsMutable() {
        Internal.ProtobufList<Write> protobufList = this.writes_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.writes_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static CommitRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static CommitRequest parseDelimitedFrom(InputStream inputStream) {
        return (CommitRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static CommitRequest parseFrom(ByteBuffer byteBuffer) {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<CommitRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeWrites(int i) {
        ensureWritesIsMutable();
        this.writes_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDatabase(String str) {
        str.getClass();
        this.database_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDatabaseBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.database_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTransaction(ByteString byteString) {
        byteString.getClass();
        this.transaction_ = byteString;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0001\u0000\u0001Ȉ\u0002\u001b\u0003\n", new Object[]{"database_", "writes_", Write.class, "transaction_"});
            case NEW_MUTABLE_INSTANCE:
                return new CommitRequest();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<CommitRequest> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (CommitRequest.class) {
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

    @Override // com.google.firestore.v1.CommitRequestOrBuilder
    public String getDatabase() {
        return this.database_;
    }

    @Override // com.google.firestore.v1.CommitRequestOrBuilder
    public ByteString getDatabaseBytes() {
        return ByteString.copyFromUtf8(this.database_);
    }

    @Override // com.google.firestore.v1.CommitRequestOrBuilder
    public ByteString getTransaction() {
        return this.transaction_;
    }

    @Override // com.google.firestore.v1.CommitRequestOrBuilder
    public Write getWrites(int i) {
        return this.writes_.get(i);
    }

    @Override // com.google.firestore.v1.CommitRequestOrBuilder
    public int getWritesCount() {
        return this.writes_.size();
    }

    @Override // com.google.firestore.v1.CommitRequestOrBuilder
    public List<Write> getWritesList() {
        return this.writes_;
    }

    public WriteOrBuilder getWritesOrBuilder(int i) {
        return this.writes_.get(i);
    }

    public List<? extends WriteOrBuilder> getWritesOrBuilderList() {
        return this.writes_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<CommitRequest, Builder> implements CommitRequestOrBuilder {
        public Builder() {
            super(CommitRequest.DEFAULT_INSTANCE);
        }

        public Builder addAllWrites(Iterable<? extends Write> iterable) {
            copyOnWrite();
            CommitRequest.access$700((CommitRequest) this.instance, iterable);
            return this;
        }

        public Builder addWrites(Write write) {
            copyOnWrite();
            ((CommitRequest) this.instance).addWrites(write);
            return this;
        }

        public Builder clearDatabase() {
            copyOnWrite();
            ((CommitRequest) this.instance).clearDatabase();
            return this;
        }

        public Builder clearTransaction() {
            copyOnWrite();
            ((CommitRequest) this.instance).clearTransaction();
            return this;
        }

        public Builder clearWrites() {
            copyOnWrite();
            ((CommitRequest) this.instance).clearWrites();
            return this;
        }

        @Override // com.google.firestore.v1.CommitRequestOrBuilder
        public String getDatabase() {
            return ((CommitRequest) this.instance).getDatabase();
        }

        @Override // com.google.firestore.v1.CommitRequestOrBuilder
        public ByteString getDatabaseBytes() {
            return ((CommitRequest) this.instance).getDatabaseBytes();
        }

        @Override // com.google.firestore.v1.CommitRequestOrBuilder
        public ByteString getTransaction() {
            return ((CommitRequest) this.instance).getTransaction();
        }

        @Override // com.google.firestore.v1.CommitRequestOrBuilder
        public Write getWrites(int i) {
            return ((CommitRequest) this.instance).getWrites(i);
        }

        @Override // com.google.firestore.v1.CommitRequestOrBuilder
        public int getWritesCount() {
            return ((CommitRequest) this.instance).getWritesCount();
        }

        @Override // com.google.firestore.v1.CommitRequestOrBuilder
        public List<Write> getWritesList() {
            return Collections.unmodifiableList(((CommitRequest) this.instance).getWritesList());
        }

        public Builder removeWrites(int i) {
            copyOnWrite();
            CommitRequest.access$900((CommitRequest) this.instance, i);
            return this;
        }

        public Builder setDatabase(String str) {
            copyOnWrite();
            ((CommitRequest) this.instance).setDatabase(str);
            return this;
        }

        public Builder setDatabaseBytes(ByteString byteString) {
            copyOnWrite();
            ((CommitRequest) this.instance).setDatabaseBytes(byteString);
            return this;
        }

        public Builder setTransaction(ByteString byteString) {
            copyOnWrite();
            ((CommitRequest) this.instance).setTransaction(byteString);
            return this;
        }

        public Builder setWrites(int i, Write write) {
            copyOnWrite();
            ((CommitRequest) this.instance).setWrites(i, write);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(CommitRequest.DEFAULT_INSTANCE);
        }

        public Builder addWrites(int i, Write write) {
            copyOnWrite();
            ((CommitRequest) this.instance).addWrites(i, write);
            return this;
        }

        public Builder setWrites(int i, Write.Builder builder) {
            copyOnWrite();
            ((CommitRequest) this.instance).setWrites(i, builder.build());
            return this;
        }

        public Builder addWrites(Write.Builder builder) {
            copyOnWrite();
            ((CommitRequest) this.instance).addWrites(builder.build());
            return this;
        }

        public Builder addWrites(int i, Write.Builder builder) {
            copyOnWrite();
            ((CommitRequest) this.instance).addWrites(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(CommitRequest commitRequest) {
        return DEFAULT_INSTANCE.createBuilder(commitRequest);
    }

    public static CommitRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (CommitRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static CommitRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static CommitRequest parseFrom(ByteString byteString) {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addWrites(int i, Write write) {
        write.getClass();
        ensureWritesIsMutable();
        this.writes_.add(i, write);
    }

    public static CommitRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static CommitRequest parseFrom(byte[] bArr) {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static CommitRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static CommitRequest parseFrom(InputStream inputStream) {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static CommitRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static CommitRequest parseFrom(CodedInputStream codedInputStream) {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static CommitRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (CommitRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
