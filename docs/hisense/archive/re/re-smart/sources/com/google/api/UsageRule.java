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
public final class UsageRule extends GeneratedMessageLite<UsageRule, Builder> implements UsageRuleOrBuilder {
    public static final int ALLOW_UNREGISTERED_CALLS_FIELD_NUMBER = 2;
    public static final UsageRule DEFAULT_INSTANCE;
    public static volatile Parser<UsageRule> PARSER = null;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    public static final int SKIP_SERVICE_CONTROL_FIELD_NUMBER = 3;
    public boolean allowUnregisteredCalls_;
    public String selector_ = "";
    public boolean skipServiceControl_;

    /* JADX INFO: renamed from: com.google.api.UsageRule$1, reason: invalid class name */
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
        UsageRule usageRule = new UsageRule();
        DEFAULT_INSTANCE = usageRule;
        GeneratedMessageLite.defaultInstanceMap.put(UsageRule.class, usageRule);
    }

    private void clearAllowUnregisteredCalls() {
        this.allowUnregisteredCalls_ = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSelector() {
        this.selector_ = DEFAULT_INSTANCE.getSelector();
    }

    private void clearSkipServiceControl() {
        this.skipServiceControl_ = false;
    }

    public static UsageRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static UsageRule parseDelimitedFrom(InputStream inputStream) {
        return (UsageRule) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static UsageRule parseFrom(ByteBuffer byteBuffer) {
        return (UsageRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<UsageRule> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void setAllowUnregisteredCalls(boolean z) {
        this.allowUnregisteredCalls_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSelector(String str) {
        str.getClass();
        this.selector_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSelectorBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.selector_ = byteString.toStringUtf8();
    }

    private void setSkipServiceControl(boolean z) {
        this.skipServiceControl_ = z;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\u0007\u0003\u0007", new Object[]{"selector_", "allowUnregisteredCalls_", "skipServiceControl_"});
            case NEW_MUTABLE_INSTANCE:
                return new UsageRule();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<UsageRule> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (UsageRule.class) {
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

    @Override // com.google.api.UsageRuleOrBuilder
    public boolean getAllowUnregisteredCalls() {
        return this.allowUnregisteredCalls_;
    }

    @Override // com.google.api.UsageRuleOrBuilder
    public String getSelector() {
        return this.selector_;
    }

    @Override // com.google.api.UsageRuleOrBuilder
    public ByteString getSelectorBytes() {
        return ByteString.copyFromUtf8(this.selector_);
    }

    @Override // com.google.api.UsageRuleOrBuilder
    public boolean getSkipServiceControl() {
        return this.skipServiceControl_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UsageRule, Builder> implements UsageRuleOrBuilder {
        public Builder() {
            super(UsageRule.DEFAULT_INSTANCE);
        }

        public Builder clearAllowUnregisteredCalls() {
            copyOnWrite();
            ((UsageRule) this.instance).allowUnregisteredCalls_ = false;
            return this;
        }

        public Builder clearSelector() {
            copyOnWrite();
            ((UsageRule) this.instance).clearSelector();
            return this;
        }

        public Builder clearSkipServiceControl() {
            copyOnWrite();
            ((UsageRule) this.instance).skipServiceControl_ = false;
            return this;
        }

        @Override // com.google.api.UsageRuleOrBuilder
        public boolean getAllowUnregisteredCalls() {
            return ((UsageRule) this.instance).getAllowUnregisteredCalls();
        }

        @Override // com.google.api.UsageRuleOrBuilder
        public String getSelector() {
            return ((UsageRule) this.instance).getSelector();
        }

        @Override // com.google.api.UsageRuleOrBuilder
        public ByteString getSelectorBytes() {
            return ((UsageRule) this.instance).getSelectorBytes();
        }

        @Override // com.google.api.UsageRuleOrBuilder
        public boolean getSkipServiceControl() {
            return ((UsageRule) this.instance).getSkipServiceControl();
        }

        public Builder setAllowUnregisteredCalls(boolean z) {
            copyOnWrite();
            ((UsageRule) this.instance).allowUnregisteredCalls_ = z;
            return this;
        }

        public Builder setSelector(String str) {
            copyOnWrite();
            ((UsageRule) this.instance).setSelector(str);
            return this;
        }

        public Builder setSelectorBytes(ByteString byteString) {
            copyOnWrite();
            ((UsageRule) this.instance).setSelectorBytes(byteString);
            return this;
        }

        public Builder setSkipServiceControl(boolean z) {
            copyOnWrite();
            ((UsageRule) this.instance).skipServiceControl_ = z;
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(UsageRule.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(UsageRule usageRule) {
        return DEFAULT_INSTANCE.createBuilder(usageRule);
    }

    public static UsageRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (UsageRule) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static UsageRule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (UsageRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static UsageRule parseFrom(ByteString byteString) {
        return (UsageRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static UsageRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (UsageRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static UsageRule parseFrom(byte[] bArr) {
        return (UsageRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static UsageRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (UsageRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static UsageRule parseFrom(InputStream inputStream) {
        return (UsageRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static UsageRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (UsageRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static UsageRule parseFrom(CodedInputStream codedInputStream) {
        return (UsageRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static UsageRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (UsageRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
