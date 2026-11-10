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
public final class Control extends GeneratedMessageLite<Control, Builder> implements ControlOrBuilder {
    public static final Control DEFAULT_INSTANCE;
    public static final int ENVIRONMENT_FIELD_NUMBER = 1;
    public static volatile Parser<Control> PARSER;
    public String environment_ = "";

    /* JADX INFO: renamed from: com.google.api.Control$1, reason: invalid class name */
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
        Control control = new Control();
        DEFAULT_INSTANCE = control;
        GeneratedMessageLite.defaultInstanceMap.put(Control.class, control);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearEnvironment() {
        this.environment_ = DEFAULT_INSTANCE.getEnvironment();
    }

    public static Control getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Control parseDelimitedFrom(InputStream inputStream) {
        return (Control) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Control parseFrom(ByteBuffer byteBuffer) {
        return (Control) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Control> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEnvironment(String str) {
        str.getClass();
        this.environment_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEnvironmentBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.environment_ = byteString.toStringUtf8();
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", new Object[]{"environment_"});
            case NEW_MUTABLE_INSTANCE:
                return new Control();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Control> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Control.class) {
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

    @Override // com.google.api.ControlOrBuilder
    public String getEnvironment() {
        return this.environment_;
    }

    @Override // com.google.api.ControlOrBuilder
    public ByteString getEnvironmentBytes() {
        return ByteString.copyFromUtf8(this.environment_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Control, Builder> implements ControlOrBuilder {
        public Builder() {
            super(Control.DEFAULT_INSTANCE);
        }

        public Builder clearEnvironment() {
            copyOnWrite();
            ((Control) this.instance).clearEnvironment();
            return this;
        }

        @Override // com.google.api.ControlOrBuilder
        public String getEnvironment() {
            return ((Control) this.instance).getEnvironment();
        }

        @Override // com.google.api.ControlOrBuilder
        public ByteString getEnvironmentBytes() {
            return ((Control) this.instance).getEnvironmentBytes();
        }

        public Builder setEnvironment(String str) {
            copyOnWrite();
            ((Control) this.instance).setEnvironment(str);
            return this;
        }

        public Builder setEnvironmentBytes(ByteString byteString) {
            copyOnWrite();
            ((Control) this.instance).setEnvironmentBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Control.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(Control control) {
        return DEFAULT_INSTANCE.createBuilder(control);
    }

    public static Control parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Control) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Control parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Control) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Control parseFrom(ByteString byteString) {
        return (Control) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Control parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Control) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Control parseFrom(byte[] bArr) {
        return (Control) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Control parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Control) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Control parseFrom(InputStream inputStream) {
        return (Control) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Control parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Control) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Control parseFrom(CodedInputStream codedInputStream) {
        return (Control) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Control parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Control) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
