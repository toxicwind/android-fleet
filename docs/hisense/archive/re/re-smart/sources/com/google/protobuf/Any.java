package com.google.protobuf;

import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public final class Any extends GeneratedMessageLite<Any, Builder> implements AnyOrBuilder {
    public static final Any DEFAULT_INSTANCE;
    public static volatile Parser<Any> PARSER = null;
    public static final int TYPE_URL_FIELD_NUMBER = 1;
    public static final int VALUE_FIELD_NUMBER = 2;
    public String typeUrl_ = "";
    public ByteString value_ = ByteString.EMPTY;

    /* JADX INFO: renamed from: com.google.protobuf.Any$1, reason: invalid class name */
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
        Any any = new Any();
        DEFAULT_INSTANCE = any;
        GeneratedMessageLite.defaultInstanceMap.put(Any.class, any);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTypeUrl() {
        this.typeUrl_ = DEFAULT_INSTANCE.getTypeUrl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearValue() {
        this.value_ = DEFAULT_INSTANCE.getValue();
    }

    public static Any getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Any parseDelimitedFrom(InputStream inputStream) {
        return (Any) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Any parseFrom(ByteBuffer byteBuffer) {
        return (Any) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Any> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTypeUrl(String str) {
        str.getClass();
        this.typeUrl_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTypeUrlBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.typeUrl_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setValue(ByteString byteString) {
        byteString.getClass();
        this.value_ = byteString;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\n", new Object[]{"typeUrl_", "value_"});
            case NEW_MUTABLE_INSTANCE:
                return new Any();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Any> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Any.class) {
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

    @Override // com.google.protobuf.AnyOrBuilder
    public String getTypeUrl() {
        return this.typeUrl_;
    }

    @Override // com.google.protobuf.AnyOrBuilder
    public ByteString getTypeUrlBytes() {
        return ByteString.copyFromUtf8(this.typeUrl_);
    }

    @Override // com.google.protobuf.AnyOrBuilder
    public ByteString getValue() {
        return this.value_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Any, Builder> implements AnyOrBuilder {
        public Builder() {
            super(Any.DEFAULT_INSTANCE);
        }

        public Builder clearTypeUrl() {
            copyOnWrite();
            ((Any) this.instance).clearTypeUrl();
            return this;
        }

        public Builder clearValue() {
            copyOnWrite();
            ((Any) this.instance).clearValue();
            return this;
        }

        @Override // com.google.protobuf.AnyOrBuilder
        public String getTypeUrl() {
            return ((Any) this.instance).getTypeUrl();
        }

        @Override // com.google.protobuf.AnyOrBuilder
        public ByteString getTypeUrlBytes() {
            return ((Any) this.instance).getTypeUrlBytes();
        }

        @Override // com.google.protobuf.AnyOrBuilder
        public ByteString getValue() {
            return ((Any) this.instance).getValue();
        }

        public Builder setTypeUrl(String str) {
            copyOnWrite();
            ((Any) this.instance).setTypeUrl(str);
            return this;
        }

        public Builder setTypeUrlBytes(ByteString byteString) {
            copyOnWrite();
            ((Any) this.instance).setTypeUrlBytes(byteString);
            return this;
        }

        public Builder setValue(ByteString byteString) {
            copyOnWrite();
            ((Any) this.instance).setValue(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Any.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(Any any) {
        return DEFAULT_INSTANCE.createBuilder(any);
    }

    public static Any parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Any) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Any parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Any) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Any parseFrom(ByteString byteString) {
        return (Any) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Any parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Any) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Any parseFrom(byte[] bArr) {
        return (Any) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Any parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Any) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Any parseFrom(InputStream inputStream) {
        return (Any) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Any parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Any) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Any parseFrom(CodedInputStream codedInputStream) {
        return (Any) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Any parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Any) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
