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
public final class Backend extends GeneratedMessageLite<Backend, Builder> implements BackendOrBuilder {
    public static final Backend DEFAULT_INSTANCE;
    public static volatile Parser<Backend> PARSER = null;
    public static final int RULES_FIELD_NUMBER = 1;
    public Internal.ProtobufList<BackendRule> rules_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.api.Backend$1, reason: invalid class name */
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
        Backend backend = new Backend();
        DEFAULT_INSTANCE = backend;
        GeneratedMessageLite.defaultInstanceMap.put(Backend.class, backend);
    }

    public static /* synthetic */ void access$400(Backend backend, Iterable iterable) {
        backend.ensureRulesIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) backend.rules_);
    }

    public static /* synthetic */ void access$600(Backend backend, int i) {
        backend.ensureRulesIsMutable();
        backend.rules_.remove(i);
    }

    private void addAllRules(Iterable<? extends BackendRule> iterable) {
        ensureRulesIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.rules_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addRules(BackendRule backendRule) {
        backendRule.getClass();
        ensureRulesIsMutable();
        this.rules_.add(backendRule);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearRules() {
        this.rules_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void ensureRulesIsMutable() {
        Internal.ProtobufList<BackendRule> protobufList = this.rules_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.rules_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static Backend getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Backend parseDelimitedFrom(InputStream inputStream) {
        return (Backend) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Backend parseFrom(ByteBuffer byteBuffer) {
        return (Backend) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Backend> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeRules(int i) {
        ensureRulesIsMutable();
        this.rules_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRules(int i, BackendRule backendRule) {
        backendRule.getClass();
        ensureRulesIsMutable();
        this.rules_.set(i, backendRule);
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"rules_", BackendRule.class});
            case NEW_MUTABLE_INSTANCE:
                return new Backend();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Backend> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Backend.class) {
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

    @Override // com.google.api.BackendOrBuilder
    public BackendRule getRules(int i) {
        return this.rules_.get(i);
    }

    @Override // com.google.api.BackendOrBuilder
    public int getRulesCount() {
        return this.rules_.size();
    }

    @Override // com.google.api.BackendOrBuilder
    public List<BackendRule> getRulesList() {
        return this.rules_;
    }

    public BackendRuleOrBuilder getRulesOrBuilder(int i) {
        return this.rules_.get(i);
    }

    public List<? extends BackendRuleOrBuilder> getRulesOrBuilderList() {
        return this.rules_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Backend, Builder> implements BackendOrBuilder {
        public Builder() {
            super(Backend.DEFAULT_INSTANCE);
        }

        public Builder addAllRules(Iterable<? extends BackendRule> iterable) {
            copyOnWrite();
            Backend.access$400((Backend) this.instance, iterable);
            return this;
        }

        public Builder addRules(BackendRule backendRule) {
            copyOnWrite();
            ((Backend) this.instance).addRules(backendRule);
            return this;
        }

        public Builder clearRules() {
            copyOnWrite();
            ((Backend) this.instance).clearRules();
            return this;
        }

        @Override // com.google.api.BackendOrBuilder
        public BackendRule getRules(int i) {
            return ((Backend) this.instance).getRules(i);
        }

        @Override // com.google.api.BackendOrBuilder
        public int getRulesCount() {
            return ((Backend) this.instance).getRulesCount();
        }

        @Override // com.google.api.BackendOrBuilder
        public List<BackendRule> getRulesList() {
            return Collections.unmodifiableList(((Backend) this.instance).getRulesList());
        }

        public Builder removeRules(int i) {
            copyOnWrite();
            Backend.access$600((Backend) this.instance, i);
            return this;
        }

        public Builder setRules(int i, BackendRule backendRule) {
            copyOnWrite();
            ((Backend) this.instance).setRules(i, backendRule);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Backend.DEFAULT_INSTANCE);
        }

        public Builder addRules(int i, BackendRule backendRule) {
            copyOnWrite();
            ((Backend) this.instance).addRules(i, backendRule);
            return this;
        }

        public Builder setRules(int i, BackendRule.Builder builder) {
            copyOnWrite();
            ((Backend) this.instance).setRules(i, builder.build());
            return this;
        }

        public Builder addRules(BackendRule.Builder builder) {
            copyOnWrite();
            ((Backend) this.instance).addRules(builder.build());
            return this;
        }

        public Builder addRules(int i, BackendRule.Builder builder) {
            copyOnWrite();
            ((Backend) this.instance).addRules(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(Backend backend) {
        return DEFAULT_INSTANCE.createBuilder(backend);
    }

    public static Backend parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Backend) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Backend parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Backend) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Backend parseFrom(ByteString byteString) {
        return (Backend) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addRules(int i, BackendRule backendRule) {
        backendRule.getClass();
        ensureRulesIsMutable();
        this.rules_.add(i, backendRule);
    }

    public static Backend parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Backend) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Backend parseFrom(byte[] bArr) {
        return (Backend) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Backend parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Backend) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Backend parseFrom(InputStream inputStream) {
        return (Backend) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Backend parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Backend) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Backend parseFrom(CodedInputStream codedInputStream) {
        return (Backend) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Backend parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Backend) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
