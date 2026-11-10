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
public final class SystemParameterRule extends GeneratedMessageLite<SystemParameterRule, Builder> implements SystemParameterRuleOrBuilder {
    public static final SystemParameterRule DEFAULT_INSTANCE;
    public static final int PARAMETERS_FIELD_NUMBER = 2;
    public static volatile Parser<SystemParameterRule> PARSER = null;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    public String selector_ = "";
    public Internal.ProtobufList<SystemParameter> parameters_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.api.SystemParameterRule$1, reason: invalid class name */
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
        SystemParameterRule systemParameterRule = new SystemParameterRule();
        DEFAULT_INSTANCE = systemParameterRule;
        GeneratedMessageLite.defaultInstanceMap.put(SystemParameterRule.class, systemParameterRule);
    }

    public static /* synthetic */ void access$700(SystemParameterRule systemParameterRule, Iterable iterable) {
        systemParameterRule.ensureParametersIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) systemParameterRule.parameters_);
    }

    public static /* synthetic */ void access$900(SystemParameterRule systemParameterRule, int i) {
        systemParameterRule.ensureParametersIsMutable();
        systemParameterRule.parameters_.remove(i);
    }

    private void addAllParameters(Iterable<? extends SystemParameter> iterable) {
        ensureParametersIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.parameters_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addParameters(SystemParameter systemParameter) {
        systemParameter.getClass();
        ensureParametersIsMutable();
        this.parameters_.add(systemParameter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearParameters() {
        this.parameters_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSelector() {
        this.selector_ = DEFAULT_INSTANCE.getSelector();
    }

    private void ensureParametersIsMutable() {
        Internal.ProtobufList<SystemParameter> protobufList = this.parameters_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.parameters_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static SystemParameterRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static SystemParameterRule parseDelimitedFrom(InputStream inputStream) {
        return (SystemParameterRule) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static SystemParameterRule parseFrom(ByteBuffer byteBuffer) {
        return (SystemParameterRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<SystemParameterRule> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeParameters(int i) {
        ensureParametersIsMutable();
        this.parameters_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setParameters(int i, SystemParameter systemParameter) {
        systemParameter.getClass();
        ensureParametersIsMutable();
        this.parameters_.set(i, systemParameter);
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

    @Override // com.google.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        AnonymousClass1 anonymousClass1 = null;
        switch (methodToInvoke) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ȉ\u0002\u001b", new Object[]{"selector_", "parameters_", SystemParameter.class});
            case NEW_MUTABLE_INSTANCE:
                return new SystemParameterRule();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<SystemParameterRule> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (SystemParameterRule.class) {
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

    @Override // com.google.api.SystemParameterRuleOrBuilder
    public SystemParameter getParameters(int i) {
        return this.parameters_.get(i);
    }

    @Override // com.google.api.SystemParameterRuleOrBuilder
    public int getParametersCount() {
        return this.parameters_.size();
    }

    @Override // com.google.api.SystemParameterRuleOrBuilder
    public List<SystemParameter> getParametersList() {
        return this.parameters_;
    }

    public SystemParameterOrBuilder getParametersOrBuilder(int i) {
        return this.parameters_.get(i);
    }

    public List<? extends SystemParameterOrBuilder> getParametersOrBuilderList() {
        return this.parameters_;
    }

    @Override // com.google.api.SystemParameterRuleOrBuilder
    public String getSelector() {
        return this.selector_;
    }

    @Override // com.google.api.SystemParameterRuleOrBuilder
    public ByteString getSelectorBytes() {
        return ByteString.copyFromUtf8(this.selector_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<SystemParameterRule, Builder> implements SystemParameterRuleOrBuilder {
        public Builder() {
            super(SystemParameterRule.DEFAULT_INSTANCE);
        }

        public Builder addAllParameters(Iterable<? extends SystemParameter> iterable) {
            copyOnWrite();
            SystemParameterRule.access$700((SystemParameterRule) this.instance, iterable);
            return this;
        }

        public Builder addParameters(SystemParameter systemParameter) {
            copyOnWrite();
            ((SystemParameterRule) this.instance).addParameters(systemParameter);
            return this;
        }

        public Builder clearParameters() {
            copyOnWrite();
            ((SystemParameterRule) this.instance).clearParameters();
            return this;
        }

        public Builder clearSelector() {
            copyOnWrite();
            ((SystemParameterRule) this.instance).clearSelector();
            return this;
        }

        @Override // com.google.api.SystemParameterRuleOrBuilder
        public SystemParameter getParameters(int i) {
            return ((SystemParameterRule) this.instance).getParameters(i);
        }

        @Override // com.google.api.SystemParameterRuleOrBuilder
        public int getParametersCount() {
            return ((SystemParameterRule) this.instance).getParametersCount();
        }

        @Override // com.google.api.SystemParameterRuleOrBuilder
        public List<SystemParameter> getParametersList() {
            return Collections.unmodifiableList(((SystemParameterRule) this.instance).getParametersList());
        }

        @Override // com.google.api.SystemParameterRuleOrBuilder
        public String getSelector() {
            return ((SystemParameterRule) this.instance).getSelector();
        }

        @Override // com.google.api.SystemParameterRuleOrBuilder
        public ByteString getSelectorBytes() {
            return ((SystemParameterRule) this.instance).getSelectorBytes();
        }

        public Builder removeParameters(int i) {
            copyOnWrite();
            SystemParameterRule.access$900((SystemParameterRule) this.instance, i);
            return this;
        }

        public Builder setParameters(int i, SystemParameter systemParameter) {
            copyOnWrite();
            ((SystemParameterRule) this.instance).setParameters(i, systemParameter);
            return this;
        }

        public Builder setSelector(String str) {
            copyOnWrite();
            ((SystemParameterRule) this.instance).setSelector(str);
            return this;
        }

        public Builder setSelectorBytes(ByteString byteString) {
            copyOnWrite();
            ((SystemParameterRule) this.instance).setSelectorBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(SystemParameterRule.DEFAULT_INSTANCE);
        }

        public Builder addParameters(int i, SystemParameter systemParameter) {
            copyOnWrite();
            ((SystemParameterRule) this.instance).addParameters(i, systemParameter);
            return this;
        }

        public Builder setParameters(int i, SystemParameter.Builder builder) {
            copyOnWrite();
            ((SystemParameterRule) this.instance).setParameters(i, builder.build());
            return this;
        }

        public Builder addParameters(SystemParameter.Builder builder) {
            copyOnWrite();
            ((SystemParameterRule) this.instance).addParameters(builder.build());
            return this;
        }

        public Builder addParameters(int i, SystemParameter.Builder builder) {
            copyOnWrite();
            ((SystemParameterRule) this.instance).addParameters(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(SystemParameterRule systemParameterRule) {
        return DEFAULT_INSTANCE.createBuilder(systemParameterRule);
    }

    public static SystemParameterRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (SystemParameterRule) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static SystemParameterRule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (SystemParameterRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static SystemParameterRule parseFrom(ByteString byteString) {
        return (SystemParameterRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addParameters(int i, SystemParameter systemParameter) {
        systemParameter.getClass();
        ensureParametersIsMutable();
        this.parameters_.add(i, systemParameter);
    }

    public static SystemParameterRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (SystemParameterRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static SystemParameterRule parseFrom(byte[] bArr) {
        return (SystemParameterRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static SystemParameterRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (SystemParameterRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static SystemParameterRule parseFrom(InputStream inputStream) {
        return (SystemParameterRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static SystemParameterRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (SystemParameterRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static SystemParameterRule parseFrom(CodedInputStream codedInputStream) {
        return (SystemParameterRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static SystemParameterRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (SystemParameterRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
