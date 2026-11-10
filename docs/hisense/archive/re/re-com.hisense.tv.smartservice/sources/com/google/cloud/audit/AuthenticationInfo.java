package com.google.cloud.audit;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class AuthenticationInfo extends GeneratedMessageLite<AuthenticationInfo, Builder> implements AuthenticationInfoOrBuilder {
    public static final AuthenticationInfo DEFAULT_INSTANCE;
    public static volatile Parser<AuthenticationInfo> PARSER = null;
    public static final int PRINCIPAL_EMAIL_FIELD_NUMBER = 1;
    public String principalEmail_ = "";

    /* JADX INFO: renamed from: com.google.cloud.audit.AuthenticationInfo$1, reason: invalid class name */
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
        AuthenticationInfo authenticationInfo = new AuthenticationInfo();
        DEFAULT_INSTANCE = authenticationInfo;
        GeneratedMessageLite.defaultInstanceMap.put(AuthenticationInfo.class, authenticationInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPrincipalEmail() {
        this.principalEmail_ = DEFAULT_INSTANCE.getPrincipalEmail();
    }

    public static AuthenticationInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static AuthenticationInfo parseDelimitedFrom(InputStream inputStream) {
        return (AuthenticationInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AuthenticationInfo parseFrom(ByteBuffer byteBuffer) {
        return (AuthenticationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<AuthenticationInfo> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPrincipalEmail(String str) {
        str.getClass();
        this.principalEmail_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPrincipalEmailBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.principalEmail_ = byteString.toStringUtf8();
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", new Object[]{"principalEmail_"});
            case NEW_MUTABLE_INSTANCE:
                return new AuthenticationInfo();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<AuthenticationInfo> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (AuthenticationInfo.class) {
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

    @Override // com.google.cloud.audit.AuthenticationInfoOrBuilder
    public String getPrincipalEmail() {
        return this.principalEmail_;
    }

    @Override // com.google.cloud.audit.AuthenticationInfoOrBuilder
    public ByteString getPrincipalEmailBytes() {
        return ByteString.copyFromUtf8(this.principalEmail_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AuthenticationInfo, Builder> implements AuthenticationInfoOrBuilder {
        public Builder() {
            super(AuthenticationInfo.DEFAULT_INSTANCE);
        }

        public Builder clearPrincipalEmail() {
            copyOnWrite();
            ((AuthenticationInfo) this.instance).clearPrincipalEmail();
            return this;
        }

        @Override // com.google.cloud.audit.AuthenticationInfoOrBuilder
        public String getPrincipalEmail() {
            return ((AuthenticationInfo) this.instance).getPrincipalEmail();
        }

        @Override // com.google.cloud.audit.AuthenticationInfoOrBuilder
        public ByteString getPrincipalEmailBytes() {
            return ((AuthenticationInfo) this.instance).getPrincipalEmailBytes();
        }

        public Builder setPrincipalEmail(String str) {
            copyOnWrite();
            ((AuthenticationInfo) this.instance).setPrincipalEmail(str);
            return this;
        }

        public Builder setPrincipalEmailBytes(ByteString byteString) {
            copyOnWrite();
            ((AuthenticationInfo) this.instance).setPrincipalEmailBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(AuthenticationInfo.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(AuthenticationInfo authenticationInfo) {
        return DEFAULT_INSTANCE.createBuilder(authenticationInfo);
    }

    public static AuthenticationInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (AuthenticationInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AuthenticationInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (AuthenticationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static AuthenticationInfo parseFrom(ByteString byteString) {
        return (AuthenticationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static AuthenticationInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (AuthenticationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static AuthenticationInfo parseFrom(byte[] bArr) {
        return (AuthenticationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static AuthenticationInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (AuthenticationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static AuthenticationInfo parseFrom(InputStream inputStream) {
        return (AuthenticationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AuthenticationInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (AuthenticationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AuthenticationInfo parseFrom(CodedInputStream codedInputStream) {
        return (AuthenticationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static AuthenticationInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (AuthenticationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
