package com.google.api;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class BackendRule extends GeneratedMessageLite<BackendRule, Builder> implements BackendRuleOrBuilder {
    public static final int ADDRESS_FIELD_NUMBER = 2;
    public static final int DEADLINE_FIELD_NUMBER = 3;
    public static final BackendRule DEFAULT_INSTANCE;
    public static final int DISABLE_AUTH_FIELD_NUMBER = 8;
    public static final int JWT_AUDIENCE_FIELD_NUMBER = 7;
    public static final int MIN_DEADLINE_FIELD_NUMBER = 4;
    public static final int OPERATION_DEADLINE_FIELD_NUMBER = 5;
    public static volatile Parser<BackendRule> PARSER = null;
    public static final int PATH_TRANSLATION_FIELD_NUMBER = 6;
    public static final int PROTOCOL_FIELD_NUMBER = 9;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    public Object authentication_;
    public double deadline_;
    public double minDeadline_;
    public double operationDeadline_;
    public int pathTranslation_;
    public int authenticationCase_ = 0;
    public String selector_ = "";
    public String address_ = "";
    public String protocol_ = "";

    /* JADX INFO: renamed from: com.google.api.BackendRule$1, reason: invalid class name */
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

    public enum AuthenticationCase {
        JWT_AUDIENCE(7),
        DISABLE_AUTH(8),
        AUTHENTICATION_NOT_SET(0);

        public final int value;

        AuthenticationCase(int i) {
            this.value = i;
        }

        public static AuthenticationCase forNumber(int i) {
            if (i == 0) {
                return AUTHENTICATION_NOT_SET;
            }
            if (i == 7) {
                return JWT_AUDIENCE;
            }
            if (i != 8) {
                return null;
            }
            return DISABLE_AUTH;
        }

        public int getNumber() {
            return this.value;
        }

        @Deprecated
        public static AuthenticationCase valueOf(int i) {
            return forNumber(i);
        }
    }

    public enum PathTranslation implements Internal.EnumLite {
        PATH_TRANSLATION_UNSPECIFIED(0),
        CONSTANT_ADDRESS(1),
        APPEND_PATH_TO_ADDRESS(2),
        UNRECOGNIZED(-1);

        public static final int APPEND_PATH_TO_ADDRESS_VALUE = 2;
        public static final int CONSTANT_ADDRESS_VALUE = 1;
        public static final int PATH_TRANSLATION_UNSPECIFIED_VALUE = 0;
        public static final Internal.EnumLiteMap<PathTranslation> internalValueMap = new Internal.EnumLiteMap<PathTranslation>() { // from class: com.google.api.BackendRule.PathTranslation.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public PathTranslation findValueByNumber(int i) {
                return PathTranslation.forNumber(i);
            }
        };
        public final int value;

        private static final class PathTranslationVerifier implements Internal.EnumVerifier {
            public static final Internal.EnumVerifier INSTANCE = new PathTranslationVerifier();

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return PathTranslation.forNumber(i) != null;
            }
        }

        PathTranslation(int i) {
            this.value = i;
        }

        public static PathTranslation forNumber(int i) {
            if (i == 0) {
                return PATH_TRANSLATION_UNSPECIFIED;
            }
            if (i == 1) {
                return CONSTANT_ADDRESS;
            }
            if (i != 2) {
                return null;
            }
            return APPEND_PATH_TO_ADDRESS;
        }

        public static Internal.EnumLiteMap<PathTranslation> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return PathTranslationVerifier.INSTANCE;
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Deprecated
        public static PathTranslation valueOf(int i) {
            return forNumber(i);
        }
    }

    static {
        BackendRule backendRule = new BackendRule();
        DEFAULT_INSTANCE = backendRule;
        GeneratedMessageLite.defaultInstanceMap.put(BackendRule.class, backendRule);
    }

    public static /* synthetic */ void access$2000(BackendRule backendRule, boolean z) {
        backendRule.authenticationCase_ = 8;
        backendRule.authentication_ = Boolean.valueOf(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAddress() {
        this.address_ = DEFAULT_INSTANCE.getAddress();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAuthentication() {
        this.authenticationCase_ = 0;
        this.authentication_ = null;
    }

    private void clearDeadline() {
        this.deadline_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDisableAuth() {
        if (this.authenticationCase_ == 8) {
            this.authenticationCase_ = 0;
            this.authentication_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearJwtAudience() {
        if (this.authenticationCase_ == 7) {
            this.authenticationCase_ = 0;
            this.authentication_ = null;
        }
    }

    private void clearMinDeadline() {
        this.minDeadline_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    private void clearOperationDeadline() {
        this.operationDeadline_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    private void clearPathTranslation() {
        this.pathTranslation_ = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearProtocol() {
        this.protocol_ = DEFAULT_INSTANCE.getProtocol();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSelector() {
        this.selector_ = DEFAULT_INSTANCE.getSelector();
    }

    public static BackendRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static BackendRule parseDelimitedFrom(InputStream inputStream) {
        return (BackendRule) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BackendRule parseFrom(ByteBuffer byteBuffer) {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<BackendRule> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAddress(String str) {
        str.getClass();
        this.address_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAddressBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.address_ = byteString.toStringUtf8();
    }

    private void setDeadline(double d2) {
        this.deadline_ = d2;
    }

    private void setDisableAuth(boolean z) {
        this.authenticationCase_ = 8;
        this.authentication_ = Boolean.valueOf(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setJwtAudience(String str) {
        str.getClass();
        this.authenticationCase_ = 7;
        this.authentication_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setJwtAudienceBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.authentication_ = byteString.toStringUtf8();
        this.authenticationCase_ = 7;
    }

    private void setMinDeadline(double d2) {
        this.minDeadline_ = d2;
    }

    private void setOperationDeadline(double d2) {
        this.operationDeadline_ = d2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPathTranslation(PathTranslation pathTranslation) {
        this.pathTranslation_ = pathTranslation.getNumber();
    }

    private void setPathTranslationValue(int i) {
        this.pathTranslation_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProtocol(String str) {
        str.getClass();
        this.protocol_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProtocolBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.protocol_ = byteString.toStringUtf8();
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\t\u0001\u0000\u0001\t\t\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003\u0000\u0004\u0000\u0005\u0000\u0006\f\u0007Ȼ\u0000\b:\u0000\tȈ", new Object[]{"authentication_", "authenticationCase_", "selector_", "address_", "deadline_", "minDeadline_", "operationDeadline_", "pathTranslation_", "protocol_"});
            case NEW_MUTABLE_INSTANCE:
                return new BackendRule();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<BackendRule> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (BackendRule.class) {
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

    @Override // com.google.api.BackendRuleOrBuilder
    public String getAddress() {
        return this.address_;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public ByteString getAddressBytes() {
        return ByteString.copyFromUtf8(this.address_);
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public AuthenticationCase getAuthenticationCase() {
        return AuthenticationCase.forNumber(this.authenticationCase_);
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public double getDeadline() {
        return this.deadline_;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public boolean getDisableAuth() {
        if (this.authenticationCase_ == 8) {
            return ((Boolean) this.authentication_).booleanValue();
        }
        return false;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public String getJwtAudience() {
        return this.authenticationCase_ == 7 ? (String) this.authentication_ : "";
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public ByteString getJwtAudienceBytes() {
        return ByteString.copyFromUtf8(this.authenticationCase_ == 7 ? (String) this.authentication_ : "");
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public double getMinDeadline() {
        return this.minDeadline_;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public double getOperationDeadline() {
        return this.operationDeadline_;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public PathTranslation getPathTranslation() {
        PathTranslation pathTranslationForNumber = PathTranslation.forNumber(this.pathTranslation_);
        return pathTranslationForNumber == null ? PathTranslation.UNRECOGNIZED : pathTranslationForNumber;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public int getPathTranslationValue() {
        return this.pathTranslation_;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public String getProtocol() {
        return this.protocol_;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public ByteString getProtocolBytes() {
        return ByteString.copyFromUtf8(this.protocol_);
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public String getSelector() {
        return this.selector_;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public ByteString getSelectorBytes() {
        return ByteString.copyFromUtf8(this.selector_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BackendRule, Builder> implements BackendRuleOrBuilder {
        public Builder() {
            super(BackendRule.DEFAULT_INSTANCE);
        }

        public Builder clearAddress() {
            copyOnWrite();
            ((BackendRule) this.instance).clearAddress();
            return this;
        }

        public Builder clearAuthentication() {
            copyOnWrite();
            ((BackendRule) this.instance).clearAuthentication();
            return this;
        }

        public Builder clearDeadline() {
            copyOnWrite();
            ((BackendRule) this.instance).deadline_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            return this;
        }

        public Builder clearDisableAuth() {
            copyOnWrite();
            ((BackendRule) this.instance).clearDisableAuth();
            return this;
        }

        public Builder clearJwtAudience() {
            copyOnWrite();
            ((BackendRule) this.instance).clearJwtAudience();
            return this;
        }

        public Builder clearMinDeadline() {
            copyOnWrite();
            ((BackendRule) this.instance).minDeadline_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            return this;
        }

        public Builder clearOperationDeadline() {
            copyOnWrite();
            ((BackendRule) this.instance).operationDeadline_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            return this;
        }

        public Builder clearPathTranslation() {
            copyOnWrite();
            ((BackendRule) this.instance).pathTranslation_ = 0;
            return this;
        }

        public Builder clearProtocol() {
            copyOnWrite();
            ((BackendRule) this.instance).clearProtocol();
            return this;
        }

        public Builder clearSelector() {
            copyOnWrite();
            ((BackendRule) this.instance).clearSelector();
            return this;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public String getAddress() {
            return ((BackendRule) this.instance).getAddress();
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public ByteString getAddressBytes() {
            return ((BackendRule) this.instance).getAddressBytes();
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public AuthenticationCase getAuthenticationCase() {
            return ((BackendRule) this.instance).getAuthenticationCase();
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public double getDeadline() {
            return ((BackendRule) this.instance).getDeadline();
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public boolean getDisableAuth() {
            return ((BackendRule) this.instance).getDisableAuth();
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public String getJwtAudience() {
            return ((BackendRule) this.instance).getJwtAudience();
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public ByteString getJwtAudienceBytes() {
            return ((BackendRule) this.instance).getJwtAudienceBytes();
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public double getMinDeadline() {
            return ((BackendRule) this.instance).getMinDeadline();
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public double getOperationDeadline() {
            return ((BackendRule) this.instance).getOperationDeadline();
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public PathTranslation getPathTranslation() {
            return ((BackendRule) this.instance).getPathTranslation();
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public int getPathTranslationValue() {
            return ((BackendRule) this.instance).getPathTranslationValue();
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public String getProtocol() {
            return ((BackendRule) this.instance).getProtocol();
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public ByteString getProtocolBytes() {
            return ((BackendRule) this.instance).getProtocolBytes();
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public String getSelector() {
            return ((BackendRule) this.instance).getSelector();
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public ByteString getSelectorBytes() {
            return ((BackendRule) this.instance).getSelectorBytes();
        }

        public Builder setAddress(String str) {
            copyOnWrite();
            ((BackendRule) this.instance).setAddress(str);
            return this;
        }

        public Builder setAddressBytes(ByteString byteString) {
            copyOnWrite();
            ((BackendRule) this.instance).setAddressBytes(byteString);
            return this;
        }

        public Builder setDeadline(double d2) {
            copyOnWrite();
            ((BackendRule) this.instance).deadline_ = d2;
            return this;
        }

        public Builder setDisableAuth(boolean z) {
            copyOnWrite();
            BackendRule.access$2000((BackendRule) this.instance, z);
            return this;
        }

        public Builder setJwtAudience(String str) {
            copyOnWrite();
            ((BackendRule) this.instance).setJwtAudience(str);
            return this;
        }

        public Builder setJwtAudienceBytes(ByteString byteString) {
            copyOnWrite();
            ((BackendRule) this.instance).setJwtAudienceBytes(byteString);
            return this;
        }

        public Builder setMinDeadline(double d2) {
            copyOnWrite();
            ((BackendRule) this.instance).minDeadline_ = d2;
            return this;
        }

        public Builder setOperationDeadline(double d2) {
            copyOnWrite();
            ((BackendRule) this.instance).operationDeadline_ = d2;
            return this;
        }

        public Builder setPathTranslation(PathTranslation pathTranslation) {
            copyOnWrite();
            ((BackendRule) this.instance).setPathTranslation(pathTranslation);
            return this;
        }

        public Builder setPathTranslationValue(int i) {
            copyOnWrite();
            ((BackendRule) this.instance).pathTranslation_ = i;
            return this;
        }

        public Builder setProtocol(String str) {
            copyOnWrite();
            ((BackendRule) this.instance).setProtocol(str);
            return this;
        }

        public Builder setProtocolBytes(ByteString byteString) {
            copyOnWrite();
            ((BackendRule) this.instance).setProtocolBytes(byteString);
            return this;
        }

        public Builder setSelector(String str) {
            copyOnWrite();
            ((BackendRule) this.instance).setSelector(str);
            return this;
        }

        public Builder setSelectorBytes(ByteString byteString) {
            copyOnWrite();
            ((BackendRule) this.instance).setSelectorBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(BackendRule.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(BackendRule backendRule) {
        return DEFAULT_INSTANCE.createBuilder(backendRule);
    }

    public static BackendRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (BackendRule) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BackendRule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static BackendRule parseFrom(ByteString byteString) {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static BackendRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static BackendRule parseFrom(byte[] bArr) {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static BackendRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static BackendRule parseFrom(InputStream inputStream) {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BackendRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BackendRule parseFrom(CodedInputStream codedInputStream) {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static BackendRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (BackendRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
