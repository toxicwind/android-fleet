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
public final class Authentication extends GeneratedMessageLite<Authentication, Builder> implements AuthenticationOrBuilder {
    public static final Authentication DEFAULT_INSTANCE;
    public static volatile Parser<Authentication> PARSER = null;
    public static final int PROVIDERS_FIELD_NUMBER = 4;
    public static final int RULES_FIELD_NUMBER = 3;
    public Internal.ProtobufList<AuthenticationRule> rules_ = GeneratedMessageLite.emptyProtobufList();
    public Internal.ProtobufList<AuthProvider> providers_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.api.Authentication$1, reason: invalid class name */
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
        Authentication authentication = new Authentication();
        DEFAULT_INSTANCE = authentication;
        GeneratedMessageLite.defaultInstanceMap.put(Authentication.class, authentication);
    }

    public static /* synthetic */ void access$1000(Authentication authentication, Iterable iterable) {
        authentication.ensureProvidersIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) authentication.providers_);
    }

    public static /* synthetic */ void access$1200(Authentication authentication, int i) {
        authentication.ensureProvidersIsMutable();
        authentication.providers_.remove(i);
    }

    public static /* synthetic */ void access$400(Authentication authentication, Iterable iterable) {
        authentication.ensureRulesIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) authentication.rules_);
    }

    public static /* synthetic */ void access$600(Authentication authentication, int i) {
        authentication.ensureRulesIsMutable();
        authentication.rules_.remove(i);
    }

    private void addAllProviders(Iterable<? extends AuthProvider> iterable) {
        ensureProvidersIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.providers_);
    }

    private void addAllRules(Iterable<? extends AuthenticationRule> iterable) {
        ensureRulesIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.rules_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addProviders(AuthProvider authProvider) {
        authProvider.getClass();
        ensureProvidersIsMutable();
        this.providers_.add(authProvider);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addRules(AuthenticationRule authenticationRule) {
        authenticationRule.getClass();
        ensureRulesIsMutable();
        this.rules_.add(authenticationRule);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearProviders() {
        this.providers_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearRules() {
        this.rules_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void ensureProvidersIsMutable() {
        Internal.ProtobufList<AuthProvider> protobufList = this.providers_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.providers_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    private void ensureRulesIsMutable() {
        Internal.ProtobufList<AuthenticationRule> protobufList = this.rules_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.rules_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static Authentication getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Authentication parseDelimitedFrom(InputStream inputStream) {
        return (Authentication) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Authentication parseFrom(ByteBuffer byteBuffer) {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Authentication> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeProviders(int i) {
        ensureProvidersIsMutable();
        this.providers_.remove(i);
    }

    private void removeRules(int i) {
        ensureRulesIsMutable();
        this.rules_.remove(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProviders(int i, AuthProvider authProvider) {
        authProvider.getClass();
        ensureProvidersIsMutable();
        this.providers_.set(i, authProvider);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRules(int i, AuthenticationRule authenticationRule) {
        authenticationRule.getClass();
        ensureRulesIsMutable();
        this.rules_.set(i, authenticationRule);
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0003\u0004\u0002\u0000\u0002\u0000\u0003\u001b\u0004\u001b", new Object[]{"rules_", AuthenticationRule.class, "providers_", AuthProvider.class});
            case NEW_MUTABLE_INSTANCE:
                return new Authentication();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Authentication> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Authentication.class) {
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

    @Override // com.google.api.AuthenticationOrBuilder
    public AuthProvider getProviders(int i) {
        return this.providers_.get(i);
    }

    @Override // com.google.api.AuthenticationOrBuilder
    public int getProvidersCount() {
        return this.providers_.size();
    }

    @Override // com.google.api.AuthenticationOrBuilder
    public List<AuthProvider> getProvidersList() {
        return this.providers_;
    }

    public AuthProviderOrBuilder getProvidersOrBuilder(int i) {
        return this.providers_.get(i);
    }

    public List<? extends AuthProviderOrBuilder> getProvidersOrBuilderList() {
        return this.providers_;
    }

    @Override // com.google.api.AuthenticationOrBuilder
    public AuthenticationRule getRules(int i) {
        return this.rules_.get(i);
    }

    @Override // com.google.api.AuthenticationOrBuilder
    public int getRulesCount() {
        return this.rules_.size();
    }

    @Override // com.google.api.AuthenticationOrBuilder
    public List<AuthenticationRule> getRulesList() {
        return this.rules_;
    }

    public AuthenticationRuleOrBuilder getRulesOrBuilder(int i) {
        return this.rules_.get(i);
    }

    public List<? extends AuthenticationRuleOrBuilder> getRulesOrBuilderList() {
        return this.rules_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Authentication, Builder> implements AuthenticationOrBuilder {
        public Builder() {
            super(Authentication.DEFAULT_INSTANCE);
        }

        public Builder addAllProviders(Iterable<? extends AuthProvider> iterable) {
            copyOnWrite();
            Authentication.access$1000((Authentication) this.instance, iterable);
            return this;
        }

        public Builder addAllRules(Iterable<? extends AuthenticationRule> iterable) {
            copyOnWrite();
            Authentication.access$400((Authentication) this.instance, iterable);
            return this;
        }

        public Builder addProviders(AuthProvider authProvider) {
            copyOnWrite();
            ((Authentication) this.instance).addProviders(authProvider);
            return this;
        }

        public Builder addRules(AuthenticationRule authenticationRule) {
            copyOnWrite();
            ((Authentication) this.instance).addRules(authenticationRule);
            return this;
        }

        public Builder clearProviders() {
            copyOnWrite();
            ((Authentication) this.instance).clearProviders();
            return this;
        }

        public Builder clearRules() {
            copyOnWrite();
            ((Authentication) this.instance).clearRules();
            return this;
        }

        @Override // com.google.api.AuthenticationOrBuilder
        public AuthProvider getProviders(int i) {
            return ((Authentication) this.instance).getProviders(i);
        }

        @Override // com.google.api.AuthenticationOrBuilder
        public int getProvidersCount() {
            return ((Authentication) this.instance).getProvidersCount();
        }

        @Override // com.google.api.AuthenticationOrBuilder
        public List<AuthProvider> getProvidersList() {
            return Collections.unmodifiableList(((Authentication) this.instance).getProvidersList());
        }

        @Override // com.google.api.AuthenticationOrBuilder
        public AuthenticationRule getRules(int i) {
            return ((Authentication) this.instance).getRules(i);
        }

        @Override // com.google.api.AuthenticationOrBuilder
        public int getRulesCount() {
            return ((Authentication) this.instance).getRulesCount();
        }

        @Override // com.google.api.AuthenticationOrBuilder
        public List<AuthenticationRule> getRulesList() {
            return Collections.unmodifiableList(((Authentication) this.instance).getRulesList());
        }

        public Builder removeProviders(int i) {
            copyOnWrite();
            Authentication.access$1200((Authentication) this.instance, i);
            return this;
        }

        public Builder removeRules(int i) {
            copyOnWrite();
            Authentication.access$600((Authentication) this.instance, i);
            return this;
        }

        public Builder setProviders(int i, AuthProvider authProvider) {
            copyOnWrite();
            ((Authentication) this.instance).setProviders(i, authProvider);
            return this;
        }

        public Builder setRules(int i, AuthenticationRule authenticationRule) {
            copyOnWrite();
            ((Authentication) this.instance).setRules(i, authenticationRule);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Authentication.DEFAULT_INSTANCE);
        }

        public Builder addProviders(int i, AuthProvider authProvider) {
            copyOnWrite();
            ((Authentication) this.instance).addProviders(i, authProvider);
            return this;
        }

        public Builder addRules(int i, AuthenticationRule authenticationRule) {
            copyOnWrite();
            ((Authentication) this.instance).addRules(i, authenticationRule);
            return this;
        }

        public Builder setProviders(int i, AuthProvider.Builder builder) {
            copyOnWrite();
            ((Authentication) this.instance).setProviders(i, builder.build());
            return this;
        }

        public Builder setRules(int i, AuthenticationRule.Builder builder) {
            copyOnWrite();
            ((Authentication) this.instance).setRules(i, builder.build());
            return this;
        }

        public Builder addProviders(AuthProvider.Builder builder) {
            copyOnWrite();
            ((Authentication) this.instance).addProviders(builder.build());
            return this;
        }

        public Builder addRules(AuthenticationRule.Builder builder) {
            copyOnWrite();
            ((Authentication) this.instance).addRules(builder.build());
            return this;
        }

        public Builder addProviders(int i, AuthProvider.Builder builder) {
            copyOnWrite();
            ((Authentication) this.instance).addProviders(i, builder.build());
            return this;
        }

        public Builder addRules(int i, AuthenticationRule.Builder builder) {
            copyOnWrite();
            ((Authentication) this.instance).addRules(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(Authentication authentication) {
        return DEFAULT_INSTANCE.createBuilder(authentication);
    }

    public static Authentication parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Authentication) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Authentication parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Authentication parseFrom(ByteString byteString) {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addProviders(int i, AuthProvider authProvider) {
        authProvider.getClass();
        ensureProvidersIsMutable();
        this.providers_.add(i, authProvider);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addRules(int i, AuthenticationRule authenticationRule) {
        authenticationRule.getClass();
        ensureRulesIsMutable();
        this.rules_.add(i, authenticationRule);
    }

    public static Authentication parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Authentication parseFrom(byte[] bArr) {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Authentication parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Authentication parseFrom(InputStream inputStream) {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Authentication parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Authentication parseFrom(CodedInputStream codedInputStream) {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Authentication parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
