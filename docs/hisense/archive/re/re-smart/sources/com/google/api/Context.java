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
public final class Context extends GeneratedMessageLite<Context, Builder> implements ContextOrBuilder {
    public static final Context DEFAULT_INSTANCE;
    public static volatile Parser<Context> PARSER = null;
    public static final int RULES_FIELD_NUMBER = 1;
    public Internal.ProtobufList<ContextRule> rules_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.api.Context$1, reason: invalid class name */
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
        Context context = new Context();
        DEFAULT_INSTANCE = context;
        GeneratedMessageLite.defaultInstanceMap.put(Context.class, context);
    }

    public static /* synthetic */ void access$400(Context context, Iterable iterable) {
        context.ensureRulesIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) context.rules_);
    }

    public static /* synthetic */ void access$600(Context context, int i) {
        context.ensureRulesIsMutable();
        context.rules_.remove(i);
    }

    private void addAllRules(Iterable<? extends ContextRule> iterable) {
        ensureRulesIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.rules_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addRules(ContextRule contextRule) {
        contextRule.getClass();
        ensureRulesIsMutable();
        this.rules_.add(contextRule);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearRules() {
        this.rules_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void ensureRulesIsMutable() {
        Internal.ProtobufList<ContextRule> protobufList = this.rules_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.rules_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static Context getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Context parseDelimitedFrom(InputStream inputStream) {
        return (Context) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Context parseFrom(ByteBuffer byteBuffer) {
        return (Context) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Context> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeRules(int i) {
        ensureRulesIsMutable();
        this.rules_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRules(int i, ContextRule contextRule) {
        contextRule.getClass();
        ensureRulesIsMutable();
        this.rules_.set(i, contextRule);
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"rules_", ContextRule.class});
            case NEW_MUTABLE_INSTANCE:
                return new Context();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Context> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Context.class) {
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

    @Override // com.google.api.ContextOrBuilder
    public ContextRule getRules(int i) {
        return this.rules_.get(i);
    }

    @Override // com.google.api.ContextOrBuilder
    public int getRulesCount() {
        return this.rules_.size();
    }

    @Override // com.google.api.ContextOrBuilder
    public List<ContextRule> getRulesList() {
        return this.rules_;
    }

    public ContextRuleOrBuilder getRulesOrBuilder(int i) {
        return this.rules_.get(i);
    }

    public List<? extends ContextRuleOrBuilder> getRulesOrBuilderList() {
        return this.rules_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Context, Builder> implements ContextOrBuilder {
        public Builder() {
            super(Context.DEFAULT_INSTANCE);
        }

        public Builder addAllRules(Iterable<? extends ContextRule> iterable) {
            copyOnWrite();
            Context.access$400((Context) this.instance, iterable);
            return this;
        }

        public Builder addRules(ContextRule contextRule) {
            copyOnWrite();
            ((Context) this.instance).addRules(contextRule);
            return this;
        }

        public Builder clearRules() {
            copyOnWrite();
            ((Context) this.instance).clearRules();
            return this;
        }

        @Override // com.google.api.ContextOrBuilder
        public ContextRule getRules(int i) {
            return ((Context) this.instance).getRules(i);
        }

        @Override // com.google.api.ContextOrBuilder
        public int getRulesCount() {
            return ((Context) this.instance).getRulesCount();
        }

        @Override // com.google.api.ContextOrBuilder
        public List<ContextRule> getRulesList() {
            return Collections.unmodifiableList(((Context) this.instance).getRulesList());
        }

        public Builder removeRules(int i) {
            copyOnWrite();
            Context.access$600((Context) this.instance, i);
            return this;
        }

        public Builder setRules(int i, ContextRule contextRule) {
            copyOnWrite();
            ((Context) this.instance).setRules(i, contextRule);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Context.DEFAULT_INSTANCE);
        }

        public Builder addRules(int i, ContextRule contextRule) {
            copyOnWrite();
            ((Context) this.instance).addRules(i, contextRule);
            return this;
        }

        public Builder setRules(int i, ContextRule.Builder builder) {
            copyOnWrite();
            ((Context) this.instance).setRules(i, builder.build());
            return this;
        }

        public Builder addRules(ContextRule.Builder builder) {
            copyOnWrite();
            ((Context) this.instance).addRules(builder.build());
            return this;
        }

        public Builder addRules(int i, ContextRule.Builder builder) {
            copyOnWrite();
            ((Context) this.instance).addRules(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(Context context) {
        return DEFAULT_INSTANCE.createBuilder(context);
    }

    public static Context parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Context) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Context parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Context) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Context parseFrom(ByteString byteString) {
        return (Context) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addRules(int i, ContextRule contextRule) {
        contextRule.getClass();
        ensureRulesIsMutable();
        this.rules_.add(i, contextRule);
    }

    public static Context parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Context) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Context parseFrom(byte[] bArr) {
        return (Context) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Context parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Context) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Context parseFrom(InputStream inputStream) {
        return (Context) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Context parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Context) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Context parseFrom(CodedInputStream codedInputStream) {
        return (Context) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Context parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Context) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
