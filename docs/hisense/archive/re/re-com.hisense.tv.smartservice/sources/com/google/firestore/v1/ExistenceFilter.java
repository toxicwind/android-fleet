package com.google.firestore.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class ExistenceFilter extends GeneratedMessageLite<ExistenceFilter, Builder> implements ExistenceFilterOrBuilder {
    public static final int COUNT_FIELD_NUMBER = 2;
    public static final ExistenceFilter DEFAULT_INSTANCE;
    public static volatile Parser<ExistenceFilter> PARSER = null;
    public static final int TARGET_ID_FIELD_NUMBER = 1;
    public int count_;
    public int targetId_;

    /* JADX INFO: renamed from: com.google.firestore.v1.ExistenceFilter$1, reason: invalid class name */
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
        ExistenceFilter existenceFilter = new ExistenceFilter();
        DEFAULT_INSTANCE = existenceFilter;
        GeneratedMessageLite.defaultInstanceMap.put(ExistenceFilter.class, existenceFilter);
    }

    private void clearCount() {
        this.count_ = 0;
    }

    private void clearTargetId() {
        this.targetId_ = 0;
    }

    public static ExistenceFilter getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static ExistenceFilter parseDelimitedFrom(InputStream inputStream) {
        return (ExistenceFilter) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ExistenceFilter parseFrom(ByteBuffer byteBuffer) {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<ExistenceFilter> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void setCount(int i) {
        this.count_ = i;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0004\u0002\u0004", new Object[]{"targetId_", "count_"});
            case NEW_MUTABLE_INSTANCE:
                return new ExistenceFilter();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<ExistenceFilter> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (ExistenceFilter.class) {
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

    @Override // com.google.firestore.v1.ExistenceFilterOrBuilder
    public int getCount() {
        return this.count_;
    }

    @Override // com.google.firestore.v1.ExistenceFilterOrBuilder
    public int getTargetId() {
        return this.targetId_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ExistenceFilter, Builder> implements ExistenceFilterOrBuilder {
        public Builder() {
            super(ExistenceFilter.DEFAULT_INSTANCE);
        }

        public Builder clearCount() {
            copyOnWrite();
            ((ExistenceFilter) this.instance).count_ = 0;
            return this;
        }

        public Builder clearTargetId() {
            copyOnWrite();
            ((ExistenceFilter) this.instance).targetId_ = 0;
            return this;
        }

        @Override // com.google.firestore.v1.ExistenceFilterOrBuilder
        public int getCount() {
            return ((ExistenceFilter) this.instance).getCount();
        }

        @Override // com.google.firestore.v1.ExistenceFilterOrBuilder
        public int getTargetId() {
            return ((ExistenceFilter) this.instance).getTargetId();
        }

        public Builder setCount(int i) {
            copyOnWrite();
            ((ExistenceFilter) this.instance).count_ = i;
            return this;
        }

        public Builder setTargetId(int i) {
            copyOnWrite();
            ((ExistenceFilter) this.instance).targetId_ = i;
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(ExistenceFilter.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(ExistenceFilter existenceFilter) {
        return DEFAULT_INSTANCE.createBuilder(existenceFilter);
    }

    public static ExistenceFilter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ExistenceFilter) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ExistenceFilter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static ExistenceFilter parseFrom(ByteString byteString) {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ExistenceFilter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ExistenceFilter parseFrom(byte[] bArr) {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ExistenceFilter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ExistenceFilter parseFrom(InputStream inputStream) {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ExistenceFilter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ExistenceFilter parseFrom(CodedInputStream codedInputStream) {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ExistenceFilter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ExistenceFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
