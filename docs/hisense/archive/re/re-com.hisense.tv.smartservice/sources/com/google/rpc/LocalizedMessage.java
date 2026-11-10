package com.google.rpc;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public final class LocalizedMessage extends GeneratedMessageLite<LocalizedMessage, Builder> implements LocalizedMessageOrBuilder {
    public static final LocalizedMessage DEFAULT_INSTANCE;
    public static final int LOCALE_FIELD_NUMBER = 1;
    public static final int MESSAGE_FIELD_NUMBER = 2;
    public static volatile Parser<LocalizedMessage> PARSER;
    public String locale_ = "";
    public String message_ = "";

    /* JADX INFO: renamed from: com.google.rpc.LocalizedMessage$1, reason: invalid class name */
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
        LocalizedMessage localizedMessage = new LocalizedMessage();
        DEFAULT_INSTANCE = localizedMessage;
        GeneratedMessageLite.defaultInstanceMap.put(LocalizedMessage.class, localizedMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearLocale() {
        this.locale_ = DEFAULT_INSTANCE.getLocale();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMessage() {
        this.message_ = DEFAULT_INSTANCE.getMessage();
    }

    public static LocalizedMessage getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static LocalizedMessage parseDelimitedFrom(InputStream inputStream) {
        return (LocalizedMessage) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static LocalizedMessage parseFrom(ByteBuffer byteBuffer) {
        return (LocalizedMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<LocalizedMessage> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLocale(String str) {
        str.getClass();
        this.locale_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLocaleBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.locale_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMessage(String str) {
        str.getClass();
        this.message_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMessageBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.message_ = byteString.toStringUtf8();
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"locale_", "message_"});
            case NEW_MUTABLE_INSTANCE:
                return new LocalizedMessage();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<LocalizedMessage> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (LocalizedMessage.class) {
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

    @Override // com.google.rpc.LocalizedMessageOrBuilder
    public String getLocale() {
        return this.locale_;
    }

    @Override // com.google.rpc.LocalizedMessageOrBuilder
    public ByteString getLocaleBytes() {
        return ByteString.copyFromUtf8(this.locale_);
    }

    @Override // com.google.rpc.LocalizedMessageOrBuilder
    public String getMessage() {
        return this.message_;
    }

    @Override // com.google.rpc.LocalizedMessageOrBuilder
    public ByteString getMessageBytes() {
        return ByteString.copyFromUtf8(this.message_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<LocalizedMessage, Builder> implements LocalizedMessageOrBuilder {
        public Builder() {
            super(LocalizedMessage.DEFAULT_INSTANCE);
        }

        public Builder clearLocale() {
            copyOnWrite();
            ((LocalizedMessage) this.instance).clearLocale();
            return this;
        }

        public Builder clearMessage() {
            copyOnWrite();
            ((LocalizedMessage) this.instance).clearMessage();
            return this;
        }

        @Override // com.google.rpc.LocalizedMessageOrBuilder
        public String getLocale() {
            return ((LocalizedMessage) this.instance).getLocale();
        }

        @Override // com.google.rpc.LocalizedMessageOrBuilder
        public ByteString getLocaleBytes() {
            return ((LocalizedMessage) this.instance).getLocaleBytes();
        }

        @Override // com.google.rpc.LocalizedMessageOrBuilder
        public String getMessage() {
            return ((LocalizedMessage) this.instance).getMessage();
        }

        @Override // com.google.rpc.LocalizedMessageOrBuilder
        public ByteString getMessageBytes() {
            return ((LocalizedMessage) this.instance).getMessageBytes();
        }

        public Builder setLocale(String str) {
            copyOnWrite();
            ((LocalizedMessage) this.instance).setLocale(str);
            return this;
        }

        public Builder setLocaleBytes(ByteString byteString) {
            copyOnWrite();
            ((LocalizedMessage) this.instance).setLocaleBytes(byteString);
            return this;
        }

        public Builder setMessage(String str) {
            copyOnWrite();
            ((LocalizedMessage) this.instance).setMessage(str);
            return this;
        }

        public Builder setMessageBytes(ByteString byteString) {
            copyOnWrite();
            ((LocalizedMessage) this.instance).setMessageBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(LocalizedMessage.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(LocalizedMessage localizedMessage) {
        return DEFAULT_INSTANCE.createBuilder(localizedMessage);
    }

    public static LocalizedMessage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (LocalizedMessage) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static LocalizedMessage parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (LocalizedMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static LocalizedMessage parseFrom(ByteString byteString) {
        return (LocalizedMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static LocalizedMessage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (LocalizedMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static LocalizedMessage parseFrom(byte[] bArr) {
        return (LocalizedMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static LocalizedMessage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (LocalizedMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static LocalizedMessage parseFrom(InputStream inputStream) {
        return (LocalizedMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static LocalizedMessage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (LocalizedMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static LocalizedMessage parseFrom(CodedInputStream codedInputStream) {
        return (LocalizedMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static LocalizedMessage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (LocalizedMessage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
