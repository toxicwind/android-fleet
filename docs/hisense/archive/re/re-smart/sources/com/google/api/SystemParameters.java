package com.google.api;

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
public final class SystemParameters extends GeneratedMessageLite<SystemParameters, Builder> implements SystemParametersOrBuilder {
    public static final SystemParameters DEFAULT_INSTANCE;
    public static volatile Parser<SystemParameters> PARSER = null;
    public static final int RULES_FIELD_NUMBER = 1;
    public Internal.ProtobufList<SystemParameterRule> rules_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.api.SystemParameters$1, reason: invalid class name */
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
        SystemParameters systemParameters = new SystemParameters();
        DEFAULT_INSTANCE = systemParameters;
        GeneratedMessageLite.defaultInstanceMap.put(SystemParameters.class, systemParameters);
    }

    public static /* synthetic */ void access$400(SystemParameters systemParameters, Iterable iterable) {
        systemParameters.ensureRulesIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) systemParameters.rules_);
    }

    public static /* synthetic */ void access$600(SystemParameters systemParameters, int i) {
        systemParameters.ensureRulesIsMutable();
        systemParameters.rules_.remove(i);
    }

    private void addAllRules(Iterable<? extends SystemParameterRule> iterable) {
        ensureRulesIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.rules_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addRules(SystemParameterRule systemParameterRule) {
        systemParameterRule.getClass();
        ensureRulesIsMutable();
        this.rules_.add(systemParameterRule);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearRules() {
        this.rules_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void ensureRulesIsMutable() {
        Internal.ProtobufList<SystemParameterRule> protobufList = this.rules_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.rules_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static SystemParameters getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static SystemParameters parseDelimitedFrom(InputStream inputStream) {
        return (SystemParameters) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static SystemParameters parseFrom(ByteBuffer byteBuffer) {
        return (SystemParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<SystemParameters> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeRules(int i) {
        ensureRulesIsMutable();
        this.rules_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRules(int i, SystemParameterRule systemParameterRule) {
        systemParameterRule.getClass();
        ensureRulesIsMutable();
        this.rules_.set(i, systemParameterRule);
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"rules_", SystemParameterRule.class});
            case NEW_MUTABLE_INSTANCE:
                return new SystemParameters();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<SystemParameters> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (SystemParameters.class) {
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

    @Override // com.google.api.SystemParametersOrBuilder
    public SystemParameterRule getRules(int i) {
        return this.rules_.get(i);
    }

    @Override // com.google.api.SystemParametersOrBuilder
    public int getRulesCount() {
        return this.rules_.size();
    }

    @Override // com.google.api.SystemParametersOrBuilder
    public List<SystemParameterRule> getRulesList() {
        return this.rules_;
    }

    public SystemParameterRuleOrBuilder getRulesOrBuilder(int i) {
        return this.rules_.get(i);
    }

    public List<? extends SystemParameterRuleOrBuilder> getRulesOrBuilderList() {
        return this.rules_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<SystemParameters, Builder> implements SystemParametersOrBuilder {
        public Builder() {
            super(SystemParameters.DEFAULT_INSTANCE);
        }

        public Builder addAllRules(Iterable<? extends SystemParameterRule> iterable) {
            copyOnWrite();
            SystemParameters.access$400((SystemParameters) this.instance, iterable);
            return this;
        }

        public Builder addRules(SystemParameterRule systemParameterRule) {
            copyOnWrite();
            ((SystemParameters) this.instance).addRules(systemParameterRule);
            return this;
        }

        public Builder clearRules() {
            copyOnWrite();
            ((SystemParameters) this.instance).clearRules();
            return this;
        }

        @Override // com.google.api.SystemParametersOrBuilder
        public SystemParameterRule getRules(int i) {
            return ((SystemParameters) this.instance).getRules(i);
        }

        @Override // com.google.api.SystemParametersOrBuilder
        public int getRulesCount() {
            return ((SystemParameters) this.instance).getRulesCount();
        }

        @Override // com.google.api.SystemParametersOrBuilder
        public List<SystemParameterRule> getRulesList() {
            return Collections.unmodifiableList(((SystemParameters) this.instance).getRulesList());
        }

        public Builder removeRules(int i) {
            copyOnWrite();
            SystemParameters.access$600((SystemParameters) this.instance, i);
            return this;
        }

        public Builder setRules(int i, SystemParameterRule systemParameterRule) {
            copyOnWrite();
            ((SystemParameters) this.instance).setRules(i, systemParameterRule);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(SystemParameters.DEFAULT_INSTANCE);
        }

        public Builder addRules(int i, SystemParameterRule systemParameterRule) {
            copyOnWrite();
            ((SystemParameters) this.instance).addRules(i, systemParameterRule);
            return this;
        }

        public Builder setRules(int i, SystemParameterRule.Builder builder) {
            copyOnWrite();
            ((SystemParameters) this.instance).setRules(i, builder.build());
            return this;
        }

        public Builder addRules(SystemParameterRule.Builder builder) {
            copyOnWrite();
            ((SystemParameters) this.instance).addRules(builder.build());
            return this;
        }

        public Builder addRules(int i, SystemParameterRule.Builder builder) {
            copyOnWrite();
            ((SystemParameters) this.instance).addRules(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(SystemParameters systemParameters) {
        return DEFAULT_INSTANCE.createBuilder(systemParameters);
    }

    public static SystemParameters parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (SystemParameters) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static SystemParameters parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (SystemParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static SystemParameters parseFrom(ByteString byteString) {
        return (SystemParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addRules(int i, SystemParameterRule systemParameterRule) {
        systemParameterRule.getClass();
        ensureRulesIsMutable();
        this.rules_.add(i, systemParameterRule);
    }

    public static SystemParameters parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (SystemParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static SystemParameters parseFrom(byte[] bArr) {
        return (SystemParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static SystemParameters parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (SystemParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static SystemParameters parseFrom(InputStream inputStream) {
        return (SystemParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static SystemParameters parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (SystemParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static SystemParameters parseFrom(CodedInputStream codedInputStream) {
        return (SystemParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static SystemParameters parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (SystemParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
