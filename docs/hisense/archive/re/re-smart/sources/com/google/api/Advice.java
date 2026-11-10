package com.google.api;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class Advice extends GeneratedMessageLite<Advice, Builder> implements AdviceOrBuilder {
    public static final Advice DEFAULT_INSTANCE;
    public static final int DESCRIPTION_FIELD_NUMBER = 2;
    public static volatile Parser<Advice> PARSER;
    public String description_ = "";

    /* JADX INFO: renamed from: com.google.api.Advice$1, reason: invalid class name */
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
        Advice advice = new Advice();
        DEFAULT_INSTANCE = advice;
        GeneratedMessageLite.defaultInstanceMap.put(Advice.class, advice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDescription() {
        this.description_ = DEFAULT_INSTANCE.getDescription();
    }

    public static Advice getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Advice parseDelimitedFrom(InputStream inputStream) {
        return (Advice) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Advice parseFrom(ByteBuffer byteBuffer) {
        return (Advice) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Advice> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDescription(String str) {
        str.getClass();
        this.description_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDescriptionBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.description_ = byteString.toStringUtf8();
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0002\u0002\u0001\u0000\u0000\u0000\u0002Ȉ", new Object[]{"description_"});
            case NEW_MUTABLE_INSTANCE:
                return new Advice();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Advice> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Advice.class) {
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

    @Override // com.google.api.AdviceOrBuilder
    public String getDescription() {
        return this.description_;
    }

    @Override // com.google.api.AdviceOrBuilder
    public ByteString getDescriptionBytes() {
        return ByteString.copyFromUtf8(this.description_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Advice, Builder> implements AdviceOrBuilder {
        public Builder() {
            super(Advice.DEFAULT_INSTANCE);
        }

        public Builder clearDescription() {
            copyOnWrite();
            ((Advice) this.instance).clearDescription();
            return this;
        }

        @Override // com.google.api.AdviceOrBuilder
        public String getDescription() {
            return ((Advice) this.instance).getDescription();
        }

        @Override // com.google.api.AdviceOrBuilder
        public ByteString getDescriptionBytes() {
            return ((Advice) this.instance).getDescriptionBytes();
        }

        public Builder setDescription(String str) {
            copyOnWrite();
            ((Advice) this.instance).setDescription(str);
            return this;
        }

        public Builder setDescriptionBytes(ByteString byteString) {
            copyOnWrite();
            ((Advice) this.instance).setDescriptionBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Advice.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(Advice advice) {
        return DEFAULT_INSTANCE.createBuilder(advice);
    }

    public static Advice parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Advice) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Advice parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Advice) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Advice parseFrom(ByteString byteString) {
        return (Advice) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Advice parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Advice) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Advice parseFrom(byte[] bArr) {
        return (Advice) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Advice parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Advice) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Advice parseFrom(InputStream inputStream) {
        return (Advice) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Advice parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Advice) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Advice parseFrom(CodedInputStream codedInputStream) {
        return (Advice) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Advice parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Advice) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
