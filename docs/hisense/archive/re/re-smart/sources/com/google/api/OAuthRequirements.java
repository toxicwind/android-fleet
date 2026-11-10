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
public final class OAuthRequirements extends GeneratedMessageLite<OAuthRequirements, Builder> implements OAuthRequirementsOrBuilder {
    public static final int CANONICAL_SCOPES_FIELD_NUMBER = 1;
    public static final OAuthRequirements DEFAULT_INSTANCE;
    public static volatile Parser<OAuthRequirements> PARSER;
    public String canonicalScopes_ = "";

    /* JADX INFO: renamed from: com.google.api.OAuthRequirements$1, reason: invalid class name */
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
        OAuthRequirements oAuthRequirements = new OAuthRequirements();
        DEFAULT_INSTANCE = oAuthRequirements;
        GeneratedMessageLite.defaultInstanceMap.put(OAuthRequirements.class, oAuthRequirements);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCanonicalScopes() {
        this.canonicalScopes_ = DEFAULT_INSTANCE.getCanonicalScopes();
    }

    public static OAuthRequirements getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static OAuthRequirements parseDelimitedFrom(InputStream inputStream) {
        return (OAuthRequirements) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static OAuthRequirements parseFrom(ByteBuffer byteBuffer) {
        return (OAuthRequirements) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<OAuthRequirements> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCanonicalScopes(String str) {
        str.getClass();
        this.canonicalScopes_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCanonicalScopesBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.canonicalScopes_ = byteString.toStringUtf8();
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", new Object[]{"canonicalScopes_"});
            case NEW_MUTABLE_INSTANCE:
                return new OAuthRequirements();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<OAuthRequirements> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (OAuthRequirements.class) {
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

    @Override // com.google.api.OAuthRequirementsOrBuilder
    public String getCanonicalScopes() {
        return this.canonicalScopes_;
    }

    @Override // com.google.api.OAuthRequirementsOrBuilder
    public ByteString getCanonicalScopesBytes() {
        return ByteString.copyFromUtf8(this.canonicalScopes_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<OAuthRequirements, Builder> implements OAuthRequirementsOrBuilder {
        public Builder() {
            super(OAuthRequirements.DEFAULT_INSTANCE);
        }

        public Builder clearCanonicalScopes() {
            copyOnWrite();
            ((OAuthRequirements) this.instance).clearCanonicalScopes();
            return this;
        }

        @Override // com.google.api.OAuthRequirementsOrBuilder
        public String getCanonicalScopes() {
            return ((OAuthRequirements) this.instance).getCanonicalScopes();
        }

        @Override // com.google.api.OAuthRequirementsOrBuilder
        public ByteString getCanonicalScopesBytes() {
            return ((OAuthRequirements) this.instance).getCanonicalScopesBytes();
        }

        public Builder setCanonicalScopes(String str) {
            copyOnWrite();
            ((OAuthRequirements) this.instance).setCanonicalScopes(str);
            return this;
        }

        public Builder setCanonicalScopesBytes(ByteString byteString) {
            copyOnWrite();
            ((OAuthRequirements) this.instance).setCanonicalScopesBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(OAuthRequirements.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(OAuthRequirements oAuthRequirements) {
        return DEFAULT_INSTANCE.createBuilder(oAuthRequirements);
    }

    public static OAuthRequirements parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (OAuthRequirements) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static OAuthRequirements parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (OAuthRequirements) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static OAuthRequirements parseFrom(ByteString byteString) {
        return (OAuthRequirements) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static OAuthRequirements parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (OAuthRequirements) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static OAuthRequirements parseFrom(byte[] bArr) {
        return (OAuthRequirements) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static OAuthRequirements parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (OAuthRequirements) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static OAuthRequirements parseFrom(InputStream inputStream) {
        return (OAuthRequirements) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static OAuthRequirements parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (OAuthRequirements) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static OAuthRequirements parseFrom(CodedInputStream codedInputStream) {
        return (OAuthRequirements) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static OAuthRequirements parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (OAuthRequirements) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
