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
public final class AuthenticationRule extends GeneratedMessageLite<AuthenticationRule, Builder> implements AuthenticationRuleOrBuilder {
    public static final int ALLOW_WITHOUT_CREDENTIAL_FIELD_NUMBER = 5;
    public static final AuthenticationRule DEFAULT_INSTANCE;
    public static final int OAUTH_FIELD_NUMBER = 2;
    public static volatile Parser<AuthenticationRule> PARSER = null;
    public static final int REQUIREMENTS_FIELD_NUMBER = 7;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    public boolean allowWithoutCredential_;
    public OAuthRequirements oauth_;
    public String selector_ = "";
    public Internal.ProtobufList<AuthRequirement> requirements_ = GeneratedMessageLite.emptyProtobufList();

    /* JADX INFO: renamed from: com.google.api.AuthenticationRule$1, reason: invalid class name */
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
        AuthenticationRule authenticationRule = new AuthenticationRule();
        DEFAULT_INSTANCE = authenticationRule;
        GeneratedMessageLite.defaultInstanceMap.put(AuthenticationRule.class, authenticationRule);
    }

    public static /* synthetic */ void access$1200(AuthenticationRule authenticationRule, Iterable iterable) {
        authenticationRule.ensureRequirementsIsMutable();
        AbstractMessageLite.Builder.addAll(iterable, (List) authenticationRule.requirements_);
    }

    public static /* synthetic */ void access$1400(AuthenticationRule authenticationRule, int i) {
        authenticationRule.ensureRequirementsIsMutable();
        authenticationRule.requirements_.remove(i);
    }

    private void addAllRequirements(Iterable<? extends AuthRequirement> iterable) {
        ensureRequirementsIsMutable();
        AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.requirements_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addRequirements(AuthRequirement authRequirement) {
        authRequirement.getClass();
        ensureRequirementsIsMutable();
        this.requirements_.add(authRequirement);
    }

    private void clearAllowWithoutCredential() {
        this.allowWithoutCredential_ = false;
    }

    private void clearOauth() {
        this.oauth_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearRequirements() {
        this.requirements_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSelector() {
        this.selector_ = DEFAULT_INSTANCE.getSelector();
    }

    private void ensureRequirementsIsMutable() {
        Internal.ProtobufList<AuthRequirement> protobufList = this.requirements_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.requirements_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    public static AuthenticationRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeOauth(OAuthRequirements oAuthRequirements) {
        oAuthRequirements.getClass();
        OAuthRequirements oAuthRequirements2 = this.oauth_;
        if (oAuthRequirements2 == null || oAuthRequirements2 == OAuthRequirements.DEFAULT_INSTANCE) {
            this.oauth_ = oAuthRequirements;
        } else {
            this.oauth_ = OAuthRequirements.newBuilder(oAuthRequirements2).mergeFrom(oAuthRequirements).buildPartial();
        }
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static AuthenticationRule parseDelimitedFrom(InputStream inputStream) {
        return (AuthenticationRule) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AuthenticationRule parseFrom(ByteBuffer byteBuffer) {
        return (AuthenticationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<AuthenticationRule> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void removeRequirements(int i) {
        ensureRequirementsIsMutable();
        this.requirements_.remove(i);
    }

    private void setAllowWithoutCredential(boolean z) {
        this.allowWithoutCredential_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOauth(OAuthRequirements oAuthRequirements) {
        oAuthRequirements.getClass();
        this.oauth_ = oAuthRequirements;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRequirements(int i, AuthRequirement authRequirement) {
        authRequirement.getClass();
        ensureRequirementsIsMutable();
        this.requirements_.set(i, authRequirement);
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0007\u0004\u0000\u0001\u0000\u0001Ȉ\u0002\t\u0005\u0007\u0007\u001b", new Object[]{"selector_", "oauth_", "allowWithoutCredential_", "requirements_", AuthRequirement.class});
            case NEW_MUTABLE_INSTANCE:
                return new AuthenticationRule();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<AuthenticationRule> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (AuthenticationRule.class) {
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

    @Override // com.google.api.AuthenticationRuleOrBuilder
    public boolean getAllowWithoutCredential() {
        return this.allowWithoutCredential_;
    }

    @Override // com.google.api.AuthenticationRuleOrBuilder
    public OAuthRequirements getOauth() {
        OAuthRequirements oAuthRequirements = this.oauth_;
        return oAuthRequirements == null ? OAuthRequirements.DEFAULT_INSTANCE : oAuthRequirements;
    }

    @Override // com.google.api.AuthenticationRuleOrBuilder
    public AuthRequirement getRequirements(int i) {
        return this.requirements_.get(i);
    }

    @Override // com.google.api.AuthenticationRuleOrBuilder
    public int getRequirementsCount() {
        return this.requirements_.size();
    }

    @Override // com.google.api.AuthenticationRuleOrBuilder
    public List<AuthRequirement> getRequirementsList() {
        return this.requirements_;
    }

    public AuthRequirementOrBuilder getRequirementsOrBuilder(int i) {
        return this.requirements_.get(i);
    }

    public List<? extends AuthRequirementOrBuilder> getRequirementsOrBuilderList() {
        return this.requirements_;
    }

    @Override // com.google.api.AuthenticationRuleOrBuilder
    public String getSelector() {
        return this.selector_;
    }

    @Override // com.google.api.AuthenticationRuleOrBuilder
    public ByteString getSelectorBytes() {
        return ByteString.copyFromUtf8(this.selector_);
    }

    @Override // com.google.api.AuthenticationRuleOrBuilder
    public boolean hasOauth() {
        return this.oauth_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AuthenticationRule, Builder> implements AuthenticationRuleOrBuilder {
        public Builder() {
            super(AuthenticationRule.DEFAULT_INSTANCE);
        }

        public Builder addAllRequirements(Iterable<? extends AuthRequirement> iterable) {
            copyOnWrite();
            AuthenticationRule.access$1200((AuthenticationRule) this.instance, iterable);
            return this;
        }

        public Builder addRequirements(AuthRequirement authRequirement) {
            copyOnWrite();
            ((AuthenticationRule) this.instance).addRequirements(authRequirement);
            return this;
        }

        public Builder clearAllowWithoutCredential() {
            copyOnWrite();
            ((AuthenticationRule) this.instance).allowWithoutCredential_ = false;
            return this;
        }

        public Builder clearOauth() {
            copyOnWrite();
            ((AuthenticationRule) this.instance).oauth_ = null;
            return this;
        }

        public Builder clearRequirements() {
            copyOnWrite();
            ((AuthenticationRule) this.instance).clearRequirements();
            return this;
        }

        public Builder clearSelector() {
            copyOnWrite();
            ((AuthenticationRule) this.instance).clearSelector();
            return this;
        }

        @Override // com.google.api.AuthenticationRuleOrBuilder
        public boolean getAllowWithoutCredential() {
            return ((AuthenticationRule) this.instance).getAllowWithoutCredential();
        }

        @Override // com.google.api.AuthenticationRuleOrBuilder
        public OAuthRequirements getOauth() {
            return ((AuthenticationRule) this.instance).getOauth();
        }

        @Override // com.google.api.AuthenticationRuleOrBuilder
        public AuthRequirement getRequirements(int i) {
            return ((AuthenticationRule) this.instance).getRequirements(i);
        }

        @Override // com.google.api.AuthenticationRuleOrBuilder
        public int getRequirementsCount() {
            return ((AuthenticationRule) this.instance).getRequirementsCount();
        }

        @Override // com.google.api.AuthenticationRuleOrBuilder
        public List<AuthRequirement> getRequirementsList() {
            return Collections.unmodifiableList(((AuthenticationRule) this.instance).getRequirementsList());
        }

        @Override // com.google.api.AuthenticationRuleOrBuilder
        public String getSelector() {
            return ((AuthenticationRule) this.instance).getSelector();
        }

        @Override // com.google.api.AuthenticationRuleOrBuilder
        public ByteString getSelectorBytes() {
            return ((AuthenticationRule) this.instance).getSelectorBytes();
        }

        @Override // com.google.api.AuthenticationRuleOrBuilder
        public boolean hasOauth() {
            return ((AuthenticationRule) this.instance).hasOauth();
        }

        public Builder mergeOauth(OAuthRequirements oAuthRequirements) {
            copyOnWrite();
            ((AuthenticationRule) this.instance).mergeOauth(oAuthRequirements);
            return this;
        }

        public Builder removeRequirements(int i) {
            copyOnWrite();
            AuthenticationRule.access$1400((AuthenticationRule) this.instance, i);
            return this;
        }

        public Builder setAllowWithoutCredential(boolean z) {
            copyOnWrite();
            ((AuthenticationRule) this.instance).allowWithoutCredential_ = z;
            return this;
        }

        public Builder setOauth(OAuthRequirements oAuthRequirements) {
            copyOnWrite();
            ((AuthenticationRule) this.instance).setOauth(oAuthRequirements);
            return this;
        }

        public Builder setRequirements(int i, AuthRequirement authRequirement) {
            copyOnWrite();
            ((AuthenticationRule) this.instance).setRequirements(i, authRequirement);
            return this;
        }

        public Builder setSelector(String str) {
            copyOnWrite();
            ((AuthenticationRule) this.instance).setSelector(str);
            return this;
        }

        public Builder setSelectorBytes(ByteString byteString) {
            copyOnWrite();
            ((AuthenticationRule) this.instance).setSelectorBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(AuthenticationRule.DEFAULT_INSTANCE);
        }

        public Builder addRequirements(int i, AuthRequirement authRequirement) {
            copyOnWrite();
            ((AuthenticationRule) this.instance).addRequirements(i, authRequirement);
            return this;
        }

        public Builder setOauth(OAuthRequirements.Builder builder) {
            copyOnWrite();
            ((AuthenticationRule) this.instance).setOauth(builder.build());
            return this;
        }

        public Builder setRequirements(int i, AuthRequirement.Builder builder) {
            copyOnWrite();
            ((AuthenticationRule) this.instance).setRequirements(i, builder.build());
            return this;
        }

        public Builder addRequirements(AuthRequirement.Builder builder) {
            copyOnWrite();
            ((AuthenticationRule) this.instance).addRequirements(builder.build());
            return this;
        }

        public Builder addRequirements(int i, AuthRequirement.Builder builder) {
            copyOnWrite();
            ((AuthenticationRule) this.instance).addRequirements(i, builder.build());
            return this;
        }
    }

    public static Builder newBuilder(AuthenticationRule authenticationRule) {
        return DEFAULT_INSTANCE.createBuilder(authenticationRule);
    }

    public static AuthenticationRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (AuthenticationRule) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AuthenticationRule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (AuthenticationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static AuthenticationRule parseFrom(ByteString byteString) {
        return (AuthenticationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addRequirements(int i, AuthRequirement authRequirement) {
        authRequirement.getClass();
        ensureRequirementsIsMutable();
        this.requirements_.add(i, authRequirement);
    }

    public static AuthenticationRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (AuthenticationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static AuthenticationRule parseFrom(byte[] bArr) {
        return (AuthenticationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static AuthenticationRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (AuthenticationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static AuthenticationRule parseFrom(InputStream inputStream) {
        return (AuthenticationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AuthenticationRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (AuthenticationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AuthenticationRule parseFrom(CodedInputStream codedInputStream) {
        return (AuthenticationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static AuthenticationRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (AuthenticationRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
