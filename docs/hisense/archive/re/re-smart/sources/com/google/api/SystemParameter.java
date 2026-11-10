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
public final class SystemParameter extends GeneratedMessageLite<SystemParameter, Builder> implements SystemParameterOrBuilder {
    public static final SystemParameter DEFAULT_INSTANCE;
    public static final int HTTP_HEADER_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 1;
    public static volatile Parser<SystemParameter> PARSER = null;
    public static final int URL_QUERY_PARAMETER_FIELD_NUMBER = 3;
    public String name_ = "";
    public String httpHeader_ = "";
    public String urlQueryParameter_ = "";

    /* JADX INFO: renamed from: com.google.api.SystemParameter$1, reason: invalid class name */
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
        SystemParameter systemParameter = new SystemParameter();
        DEFAULT_INSTANCE = systemParameter;
        GeneratedMessageLite.defaultInstanceMap.put(SystemParameter.class, systemParameter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearHttpHeader() {
        this.httpHeader_ = DEFAULT_INSTANCE.getHttpHeader();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.name_ = DEFAULT_INSTANCE.getName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearUrlQueryParameter() {
        this.urlQueryParameter_ = DEFAULT_INSTANCE.getUrlQueryParameter();
    }

    public static SystemParameter getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static SystemParameter parseDelimitedFrom(InputStream inputStream) {
        return (SystemParameter) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static SystemParameter parseFrom(ByteBuffer byteBuffer) {
        return (SystemParameter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<SystemParameter> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHttpHeader(String str) {
        str.getClass();
        this.httpHeader_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHttpHeaderBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.httpHeader_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setName(String str) {
        str.getClass();
        this.name_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNameBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.name_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUrlQueryParameter(String str) {
        str.getClass();
        this.urlQueryParameter_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUrlQueryParameterBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.urlQueryParameter_ = byteString.toStringUtf8();
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ", new Object[]{"name_", "httpHeader_", "urlQueryParameter_"});
            case NEW_MUTABLE_INSTANCE:
                return new SystemParameter();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<SystemParameter> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (SystemParameter.class) {
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

    @Override // com.google.api.SystemParameterOrBuilder
    public String getHttpHeader() {
        return this.httpHeader_;
    }

    @Override // com.google.api.SystemParameterOrBuilder
    public ByteString getHttpHeaderBytes() {
        return ByteString.copyFromUtf8(this.httpHeader_);
    }

    @Override // com.google.api.SystemParameterOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.api.SystemParameterOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    @Override // com.google.api.SystemParameterOrBuilder
    public String getUrlQueryParameter() {
        return this.urlQueryParameter_;
    }

    @Override // com.google.api.SystemParameterOrBuilder
    public ByteString getUrlQueryParameterBytes() {
        return ByteString.copyFromUtf8(this.urlQueryParameter_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<SystemParameter, Builder> implements SystemParameterOrBuilder {
        public Builder() {
            super(SystemParameter.DEFAULT_INSTANCE);
        }

        public Builder clearHttpHeader() {
            copyOnWrite();
            ((SystemParameter) this.instance).clearHttpHeader();
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((SystemParameter) this.instance).clearName();
            return this;
        }

        public Builder clearUrlQueryParameter() {
            copyOnWrite();
            ((SystemParameter) this.instance).clearUrlQueryParameter();
            return this;
        }

        @Override // com.google.api.SystemParameterOrBuilder
        public String getHttpHeader() {
            return ((SystemParameter) this.instance).getHttpHeader();
        }

        @Override // com.google.api.SystemParameterOrBuilder
        public ByteString getHttpHeaderBytes() {
            return ((SystemParameter) this.instance).getHttpHeaderBytes();
        }

        @Override // com.google.api.SystemParameterOrBuilder
        public String getName() {
            return ((SystemParameter) this.instance).getName();
        }

        @Override // com.google.api.SystemParameterOrBuilder
        public ByteString getNameBytes() {
            return ((SystemParameter) this.instance).getNameBytes();
        }

        @Override // com.google.api.SystemParameterOrBuilder
        public String getUrlQueryParameter() {
            return ((SystemParameter) this.instance).getUrlQueryParameter();
        }

        @Override // com.google.api.SystemParameterOrBuilder
        public ByteString getUrlQueryParameterBytes() {
            return ((SystemParameter) this.instance).getUrlQueryParameterBytes();
        }

        public Builder setHttpHeader(String str) {
            copyOnWrite();
            ((SystemParameter) this.instance).setHttpHeader(str);
            return this;
        }

        public Builder setHttpHeaderBytes(ByteString byteString) {
            copyOnWrite();
            ((SystemParameter) this.instance).setHttpHeaderBytes(byteString);
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((SystemParameter) this.instance).setName(str);
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((SystemParameter) this.instance).setNameBytes(byteString);
            return this;
        }

        public Builder setUrlQueryParameter(String str) {
            copyOnWrite();
            ((SystemParameter) this.instance).setUrlQueryParameter(str);
            return this;
        }

        public Builder setUrlQueryParameterBytes(ByteString byteString) {
            copyOnWrite();
            ((SystemParameter) this.instance).setUrlQueryParameterBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(SystemParameter.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(SystemParameter systemParameter) {
        return DEFAULT_INSTANCE.createBuilder(systemParameter);
    }

    public static SystemParameter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (SystemParameter) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static SystemParameter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (SystemParameter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static SystemParameter parseFrom(ByteString byteString) {
        return (SystemParameter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static SystemParameter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (SystemParameter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static SystemParameter parseFrom(byte[] bArr) {
        return (SystemParameter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static SystemParameter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (SystemParameter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static SystemParameter parseFrom(InputStream inputStream) {
        return (SystemParameter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static SystemParameter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (SystemParameter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static SystemParameter parseFrom(CodedInputStream codedInputStream) {
        return (SystemParameter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static SystemParameter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (SystemParameter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
