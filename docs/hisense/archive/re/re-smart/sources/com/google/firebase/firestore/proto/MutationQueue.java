package com.google.firebase.firestore.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class MutationQueue extends GeneratedMessageLite<MutationQueue, Builder> implements MutationQueueOrBuilder {
    public static final MutationQueue DEFAULT_INSTANCE;
    public static final int LAST_ACKNOWLEDGED_BATCH_ID_FIELD_NUMBER = 1;
    public static final int LAST_STREAM_TOKEN_FIELD_NUMBER = 2;
    public static volatile Parser<MutationQueue> PARSER;
    public int lastAcknowledgedBatchId_;
    public ByteString lastStreamToken_ = ByteString.EMPTY;

    /* JADX INFO: renamed from: com.google.firebase.firestore.proto.MutationQueue$1, reason: invalid class name */
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
        MutationQueue mutationQueue = new MutationQueue();
        DEFAULT_INSTANCE = mutationQueue;
        GeneratedMessageLite.defaultInstanceMap.put(MutationQueue.class, mutationQueue);
    }

    private void clearLastAcknowledgedBatchId() {
        this.lastAcknowledgedBatchId_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLastStreamToken() {
        this.lastStreamToken_ = DEFAULT_INSTANCE.getLastStreamToken();
    }

    public static MutationQueue getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static MutationQueue parseDelimitedFrom(InputStream inputStream) {
        return (MutationQueue) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static MutationQueue parseFrom(ByteBuffer byteBuffer) {
        return (MutationQueue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<MutationQueue> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void setLastAcknowledgedBatchId(int i) {
        this.lastAcknowledgedBatchId_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLastStreamToken(ByteString byteString) {
        byteString.getClass();
        this.lastStreamToken_ = byteString;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0004\u0002\n", new Object[]{"lastAcknowledgedBatchId_", "lastStreamToken_"});
            case NEW_MUTABLE_INSTANCE:
                return new MutationQueue();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<MutationQueue> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (MutationQueue.class) {
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

    @Override // com.google.firebase.firestore.proto.MutationQueueOrBuilder
    public int getLastAcknowledgedBatchId() {
        return this.lastAcknowledgedBatchId_;
    }

    @Override // com.google.firebase.firestore.proto.MutationQueueOrBuilder
    public ByteString getLastStreamToken() {
        return this.lastStreamToken_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<MutationQueue, Builder> implements MutationQueueOrBuilder {
        public Builder() {
            super(MutationQueue.DEFAULT_INSTANCE);
        }

        public Builder clearLastAcknowledgedBatchId() {
            copyOnWrite();
            ((MutationQueue) this.instance).lastAcknowledgedBatchId_ = 0;
            return this;
        }

        public Builder clearLastStreamToken() {
            copyOnWrite();
            ((MutationQueue) this.instance).clearLastStreamToken();
            return this;
        }

        @Override // com.google.firebase.firestore.proto.MutationQueueOrBuilder
        public int getLastAcknowledgedBatchId() {
            return ((MutationQueue) this.instance).getLastAcknowledgedBatchId();
        }

        @Override // com.google.firebase.firestore.proto.MutationQueueOrBuilder
        public ByteString getLastStreamToken() {
            return ((MutationQueue) this.instance).getLastStreamToken();
        }

        public Builder setLastAcknowledgedBatchId(int i) {
            copyOnWrite();
            ((MutationQueue) this.instance).lastAcknowledgedBatchId_ = i;
            return this;
        }

        public Builder setLastStreamToken(ByteString byteString) {
            copyOnWrite();
            ((MutationQueue) this.instance).setLastStreamToken(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(MutationQueue.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(MutationQueue mutationQueue) {
        return DEFAULT_INSTANCE.createBuilder(mutationQueue);
    }

    public static MutationQueue parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (MutationQueue) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static MutationQueue parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (MutationQueue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static MutationQueue parseFrom(ByteString byteString) {
        return (MutationQueue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static MutationQueue parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (MutationQueue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static MutationQueue parseFrom(byte[] bArr) {
        return (MutationQueue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static MutationQueue parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (MutationQueue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static MutationQueue parseFrom(InputStream inputStream) {
        return (MutationQueue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static MutationQueue parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (MutationQueue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static MutationQueue parseFrom(CodedInputStream codedInputStream) {
        return (MutationQueue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static MutationQueue parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (MutationQueue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
