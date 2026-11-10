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
public final class AuthRequirement extends GeneratedMessageLite<AuthRequirement, Builder> implements AuthRequirementOrBuilder {
    public static final int AUDIENCES_FIELD_NUMBER = 2;
    public static final AuthRequirement DEFAULT_INSTANCE;
    public static volatile Parser<AuthRequirement> PARSER = null;
    public static final int PROVIDER_ID_FIELD_NUMBER = 1;
    public String providerId_ = "";
    public String audiences_ = "";

    /* JADX INFO: renamed from: com.google.api.AuthRequirement$1, reason: invalid class name */
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
        AuthRequirement authRequirement = new AuthRequirement();
        DEFAULT_INSTANCE = authRequirement;
        GeneratedMessageLite.defaultInstanceMap.put(AuthRequirement.class, authRequirement);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAudiences() {
        this.audiences_ = DEFAULT_INSTANCE.getAudiences();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearProviderId() {
        this.providerId_ = DEFAULT_INSTANCE.getProviderId();
    }

    public static AuthRequirement getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static AuthRequirement parseDelimitedFrom(InputStream inputStream) {
        return (AuthRequirement) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AuthRequirement parseFrom(ByteBuffer byteBuffer) {
        return (AuthRequirement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<AuthRequirement> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAudiences(String str) {
        str.getClass();
        this.audiences_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAudiencesBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.audiences_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProviderId(String str) {
        str.getClass();
        this.providerId_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProviderIdBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.providerId_ = byteString.toStringUtf8();
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"providerId_", "audiences_"});
            case NEW_MUTABLE_INSTANCE:
                return new AuthRequirement();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<AuthRequirement> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (AuthRequirement.class) {
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

    @Override // com.google.api.AuthRequirementOrBuilder
    public String getAudiences() {
        return this.audiences_;
    }

    @Override // com.google.api.AuthRequirementOrBuilder
    public ByteString getAudiencesBytes() {
        return ByteString.copyFromUtf8(this.audiences_);
    }

    @Override // com.google.api.AuthRequirementOrBuilder
    public String getProviderId() {
        return this.providerId_;
    }

    @Override // com.google.api.AuthRequirementOrBuilder
    public ByteString getProviderIdBytes() {
        return ByteString.copyFromUtf8(this.providerId_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AuthRequirement, Builder> implements AuthRequirementOrBuilder {
        public Builder() {
            super(AuthRequirement.DEFAULT_INSTANCE);
        }

        public Builder clearAudiences() {
            copyOnWrite();
            ((AuthRequirement) this.instance).clearAudiences();
            return this;
        }

        public Builder clearProviderId() {
            copyOnWrite();
            ((AuthRequirement) this.instance).clearProviderId();
            return this;
        }

        @Override // com.google.api.AuthRequirementOrBuilder
        public String getAudiences() {
            return ((AuthRequirement) this.instance).getAudiences();
        }

        @Override // com.google.api.AuthRequirementOrBuilder
        public ByteString getAudiencesBytes() {
            return ((AuthRequirement) this.instance).getAudiencesBytes();
        }

        @Override // com.google.api.AuthRequirementOrBuilder
        public String getProviderId() {
            return ((AuthRequirement) this.instance).getProviderId();
        }

        @Override // com.google.api.AuthRequirementOrBuilder
        public ByteString getProviderIdBytes() {
            return ((AuthRequirement) this.instance).getProviderIdBytes();
        }

        public Builder setAudiences(String str) {
            copyOnWrite();
            ((AuthRequirement) this.instance).setAudiences(str);
            return this;
        }

        public Builder setAudiencesBytes(ByteString byteString) {
            copyOnWrite();
            ((AuthRequirement) this.instance).setAudiencesBytes(byteString);
            return this;
        }

        public Builder setProviderId(String str) {
            copyOnWrite();
            ((AuthRequirement) this.instance).setProviderId(str);
            return this;
        }

        public Builder setProviderIdBytes(ByteString byteString) {
            copyOnWrite();
            ((AuthRequirement) this.instance).setProviderIdBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(AuthRequirement.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(AuthRequirement authRequirement) {
        return DEFAULT_INSTANCE.createBuilder(authRequirement);
    }

    public static AuthRequirement parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (AuthRequirement) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AuthRequirement parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (AuthRequirement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static AuthRequirement parseFrom(ByteString byteString) {
        return (AuthRequirement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static AuthRequirement parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (AuthRequirement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static AuthRequirement parseFrom(byte[] bArr) {
        return (AuthRequirement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static AuthRequirement parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (AuthRequirement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static AuthRequirement parseFrom(InputStream inputStream) {
        return (AuthRequirement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AuthRequirement parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (AuthRequirement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AuthRequirement parseFrom(CodedInputStream codedInputStream) {
        return (AuthRequirement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static AuthRequirement parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (AuthRequirement) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
