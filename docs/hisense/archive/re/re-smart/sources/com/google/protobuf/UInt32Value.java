package com.google.protobuf;

import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public final class UInt32Value extends GeneratedMessageLite<UInt32Value, Builder> implements UInt32ValueOrBuilder {
    public static final UInt32Value DEFAULT_INSTANCE;
    public static volatile Parser<UInt32Value> PARSER = null;
    public static final int VALUE_FIELD_NUMBER = 1;
    public int value_;

    /* JADX INFO: renamed from: com.google.protobuf.UInt32Value$1, reason: invalid class name */
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
        UInt32Value uInt32Value = new UInt32Value();
        DEFAULT_INSTANCE = uInt32Value;
        GeneratedMessageLite.defaultInstanceMap.put(UInt32Value.class, uInt32Value);
    }

    private void clearValue() {
        this.value_ = 0;
    }

    public static UInt32Value getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static UInt32Value of(int i) {
        return newBuilder().setValue(i).build();
    }

    public static UInt32Value parseDelimitedFrom(InputStream inputStream) {
        return (UInt32Value) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static UInt32Value parseFrom(ByteBuffer byteBuffer) {
        return (UInt32Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<UInt32Value> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void setValue(int i) {
        this.value_ = i;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", new Object[]{"value_"});
            case NEW_MUTABLE_INSTANCE:
                return new UInt32Value();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<UInt32Value> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (UInt32Value.class) {
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

    @Override // com.google.protobuf.UInt32ValueOrBuilder
    public int getValue() {
        return this.value_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UInt32Value, Builder> implements UInt32ValueOrBuilder {
        public Builder() {
            super(UInt32Value.DEFAULT_INSTANCE);
        }

        public Builder clearValue() {
            copyOnWrite();
            ((UInt32Value) this.instance).value_ = 0;
            return this;
        }

        @Override // com.google.protobuf.UInt32ValueOrBuilder
        public int getValue() {
            return ((UInt32Value) this.instance).getValue();
        }

        public Builder setValue(int i) {
            copyOnWrite();
            ((UInt32Value) this.instance).value_ = i;
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(UInt32Value.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(UInt32Value uInt32Value) {
        return DEFAULT_INSTANCE.createBuilder(uInt32Value);
    }

    public static UInt32Value parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (UInt32Value) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static UInt32Value parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (UInt32Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static UInt32Value parseFrom(ByteString byteString) {
        return (UInt32Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static UInt32Value parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (UInt32Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static UInt32Value parseFrom(byte[] bArr) {
        return (UInt32Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static UInt32Value parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (UInt32Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static UInt32Value parseFrom(InputStream inputStream) {
        return (UInt32Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static UInt32Value parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (UInt32Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static UInt32Value parseFrom(CodedInputStream codedInputStream) {
        return (UInt32Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static UInt32Value parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (UInt32Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
