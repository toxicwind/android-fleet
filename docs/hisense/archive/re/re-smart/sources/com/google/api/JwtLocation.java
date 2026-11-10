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
public final class JwtLocation extends GeneratedMessageLite<JwtLocation, Builder> implements JwtLocationOrBuilder {
    public static final JwtLocation DEFAULT_INSTANCE;
    public static final int HEADER_FIELD_NUMBER = 1;
    public static volatile Parser<JwtLocation> PARSER = null;
    public static final int QUERY_FIELD_NUMBER = 2;
    public static final int VALUE_PREFIX_FIELD_NUMBER = 3;
    public Object in_;
    public int inCase_ = 0;
    public String valuePrefix_ = "";

    /* JADX INFO: renamed from: com.google.api.JwtLocation$1, reason: invalid class name */
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

    public enum InCase {
        HEADER(1),
        QUERY(2),
        IN_NOT_SET(0);

        public final int value;

        InCase(int i) {
            this.value = i;
        }

        public static InCase forNumber(int i) {
            if (i == 0) {
                return IN_NOT_SET;
            }
            if (i == 1) {
                return HEADER;
            }
            if (i != 2) {
                return null;
            }
            return QUERY;
        }

        public int getNumber() {
            return this.value;
        }

        @Deprecated
        public static InCase valueOf(int i) {
            return forNumber(i);
        }
    }

    static {
        JwtLocation jwtLocation = new JwtLocation();
        DEFAULT_INSTANCE = jwtLocation;
        GeneratedMessageLite.defaultInstanceMap.put(JwtLocation.class, jwtLocation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearHeader() {
        if (this.inCase_ == 1) {
            this.inCase_ = 0;
            this.in_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIn() {
        this.inCase_ = 0;
        this.in_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearQuery() {
        if (this.inCase_ == 2) {
            this.inCase_ = 0;
            this.in_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearValuePrefix() {
        this.valuePrefix_ = DEFAULT_INSTANCE.getValuePrefix();
    }

    public static JwtLocation getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static JwtLocation parseDelimitedFrom(InputStream inputStream) {
        return (JwtLocation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static JwtLocation parseFrom(ByteBuffer byteBuffer) {
        return (JwtLocation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<JwtLocation> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHeader(String str) {
        str.getClass();
        this.inCase_ = 1;
        this.in_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHeaderBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.in_ = byteString.toStringUtf8();
        this.inCase_ = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setQuery(String str) {
        str.getClass();
        this.inCase_ = 2;
        this.in_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setQueryBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.in_ = byteString.toStringUtf8();
        this.inCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setValuePrefix(String str) {
        str.getClass();
        this.valuePrefix_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setValuePrefixBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.valuePrefix_ = byteString.toStringUtf8();
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0001\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȼ\u0000\u0002Ȼ\u0000\u0003Ȉ", new Object[]{"in_", "inCase_", "valuePrefix_"});
            case NEW_MUTABLE_INSTANCE:
                return new JwtLocation();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<JwtLocation> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (JwtLocation.class) {
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

    @Override // com.google.api.JwtLocationOrBuilder
    public String getHeader() {
        return this.inCase_ == 1 ? (String) this.in_ : "";
    }

    @Override // com.google.api.JwtLocationOrBuilder
    public ByteString getHeaderBytes() {
        return ByteString.copyFromUtf8(this.inCase_ == 1 ? (String) this.in_ : "");
    }

    @Override // com.google.api.JwtLocationOrBuilder
    public InCase getInCase() {
        return InCase.forNumber(this.inCase_);
    }

    @Override // com.google.api.JwtLocationOrBuilder
    public String getQuery() {
        return this.inCase_ == 2 ? (String) this.in_ : "";
    }

    @Override // com.google.api.JwtLocationOrBuilder
    public ByteString getQueryBytes() {
        return ByteString.copyFromUtf8(this.inCase_ == 2 ? (String) this.in_ : "");
    }

    @Override // com.google.api.JwtLocationOrBuilder
    public String getValuePrefix() {
        return this.valuePrefix_;
    }

    @Override // com.google.api.JwtLocationOrBuilder
    public ByteString getValuePrefixBytes() {
        return ByteString.copyFromUtf8(this.valuePrefix_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<JwtLocation, Builder> implements JwtLocationOrBuilder {
        public Builder() {
            super(JwtLocation.DEFAULT_INSTANCE);
        }

        public Builder clearHeader() {
            copyOnWrite();
            ((JwtLocation) this.instance).clearHeader();
            return this;
        }

        public Builder clearIn() {
            copyOnWrite();
            ((JwtLocation) this.instance).clearIn();
            return this;
        }

        public Builder clearQuery() {
            copyOnWrite();
            ((JwtLocation) this.instance).clearQuery();
            return this;
        }

        public Builder clearValuePrefix() {
            copyOnWrite();
            ((JwtLocation) this.instance).clearValuePrefix();
            return this;
        }

        @Override // com.google.api.JwtLocationOrBuilder
        public String getHeader() {
            return ((JwtLocation) this.instance).getHeader();
        }

        @Override // com.google.api.JwtLocationOrBuilder
        public ByteString getHeaderBytes() {
            return ((JwtLocation) this.instance).getHeaderBytes();
        }

        @Override // com.google.api.JwtLocationOrBuilder
        public InCase getInCase() {
            return ((JwtLocation) this.instance).getInCase();
        }

        @Override // com.google.api.JwtLocationOrBuilder
        public String getQuery() {
            return ((JwtLocation) this.instance).getQuery();
        }

        @Override // com.google.api.JwtLocationOrBuilder
        public ByteString getQueryBytes() {
            return ((JwtLocation) this.instance).getQueryBytes();
        }

        @Override // com.google.api.JwtLocationOrBuilder
        public String getValuePrefix() {
            return ((JwtLocation) this.instance).getValuePrefix();
        }

        @Override // com.google.api.JwtLocationOrBuilder
        public ByteString getValuePrefixBytes() {
            return ((JwtLocation) this.instance).getValuePrefixBytes();
        }

        public Builder setHeader(String str) {
            copyOnWrite();
            ((JwtLocation) this.instance).setHeader(str);
            return this;
        }

        public Builder setHeaderBytes(ByteString byteString) {
            copyOnWrite();
            ((JwtLocation) this.instance).setHeaderBytes(byteString);
            return this;
        }

        public Builder setQuery(String str) {
            copyOnWrite();
            ((JwtLocation) this.instance).setQuery(str);
            return this;
        }

        public Builder setQueryBytes(ByteString byteString) {
            copyOnWrite();
            ((JwtLocation) this.instance).setQueryBytes(byteString);
            return this;
        }

        public Builder setValuePrefix(String str) {
            copyOnWrite();
            ((JwtLocation) this.instance).setValuePrefix(str);
            return this;
        }

        public Builder setValuePrefixBytes(ByteString byteString) {
            copyOnWrite();
            ((JwtLocation) this.instance).setValuePrefixBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(JwtLocation.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(JwtLocation jwtLocation) {
        return DEFAULT_INSTANCE.createBuilder(jwtLocation);
    }

    public static JwtLocation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (JwtLocation) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static JwtLocation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (JwtLocation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static JwtLocation parseFrom(ByteString byteString) {
        return (JwtLocation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static JwtLocation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (JwtLocation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static JwtLocation parseFrom(byte[] bArr) {
        return (JwtLocation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static JwtLocation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (JwtLocation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static JwtLocation parseFrom(InputStream inputStream) {
        return (JwtLocation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static JwtLocation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (JwtLocation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static JwtLocation parseFrom(CodedInputStream codedInputStream) {
        return (JwtLocation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static JwtLocation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (JwtLocation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
