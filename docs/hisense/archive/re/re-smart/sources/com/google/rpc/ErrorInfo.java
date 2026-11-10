package com.google.rpc;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.Parser;
import com.google.protobuf.WireFormat;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class ErrorInfo extends GeneratedMessageLite<ErrorInfo, Builder> implements ErrorInfoOrBuilder {
    public static final ErrorInfo DEFAULT_INSTANCE;
    public static final int DOMAIN_FIELD_NUMBER = 2;
    public static final int METADATA_FIELD_NUMBER = 3;
    public static volatile Parser<ErrorInfo> PARSER = null;
    public static final int REASON_FIELD_NUMBER = 1;
    public MapFieldLite<String, String> metadata_ = MapFieldLite.EMPTY_MAP_FIELD;
    public String reason_ = "";
    public String domain_ = "";

    /* JADX INFO: renamed from: com.google.rpc.ErrorInfo$1, reason: invalid class name */
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

    private static final class MetadataDefaultEntryHolder {
        public static final MapEntryLite<String, String> defaultEntry;

        static {
            WireFormat.FieldType fieldType = WireFormat.FieldType.STRING;
            defaultEntry = new MapEntryLite<>(fieldType, "", fieldType, "");
        }
    }

    static {
        ErrorInfo errorInfo = new ErrorInfo();
        DEFAULT_INSTANCE = errorInfo;
        GeneratedMessageLite.defaultInstanceMap.put(ErrorInfo.class, errorInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDomain() {
        this.domain_ = DEFAULT_INSTANCE.getDomain();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearReason() {
        this.reason_ = DEFAULT_INSTANCE.getReason();
    }

    public static ErrorInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private Map<String, String> getMutableMetadataMap() {
        return internalGetMutableMetadata();
    }

    private MapFieldLite<String, String> internalGetMetadata() {
        return this.metadata_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapFieldLite<String, String> internalGetMutableMetadata() {
        if (!this.metadata_.isMutable()) {
            this.metadata_ = this.metadata_.mutableCopy();
        }
        return this.metadata_;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static ErrorInfo parseDelimitedFrom(InputStream inputStream) {
        return (ErrorInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ErrorInfo parseFrom(ByteBuffer byteBuffer) {
        return (ErrorInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<ErrorInfo> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDomain(String str) {
        str.getClass();
        this.domain_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDomainBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.domain_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReason(String str) {
        str.getClass();
        this.reason_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReasonBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.reason_ = byteString.toStringUtf8();
    }

    @Override // com.google.rpc.ErrorInfoOrBuilder
    public boolean containsMetadata(String str) {
        str.getClass();
        return this.metadata_.containsKey(str);
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0001\u0000\u0000\u0001Ȉ\u0002Ȉ\u00032", new Object[]{"reason_", "domain_", "metadata_", MetadataDefaultEntryHolder.defaultEntry});
            case NEW_MUTABLE_INSTANCE:
                return new ErrorInfo();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<ErrorInfo> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (ErrorInfo.class) {
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

    @Override // com.google.rpc.ErrorInfoOrBuilder
    public String getDomain() {
        return this.domain_;
    }

    @Override // com.google.rpc.ErrorInfoOrBuilder
    public ByteString getDomainBytes() {
        return ByteString.copyFromUtf8(this.domain_);
    }

    @Override // com.google.rpc.ErrorInfoOrBuilder
    @Deprecated
    public Map<String, String> getMetadata() {
        return getMetadataMap();
    }

    @Override // com.google.rpc.ErrorInfoOrBuilder
    public int getMetadataCount() {
        return this.metadata_.size();
    }

    @Override // com.google.rpc.ErrorInfoOrBuilder
    public Map<String, String> getMetadataMap() {
        return Collections.unmodifiableMap(this.metadata_);
    }

    @Override // com.google.rpc.ErrorInfoOrBuilder
    public String getMetadataOrDefault(String str, String str2) {
        str.getClass();
        MapFieldLite<String, String> mapFieldLite = this.metadata_;
        return mapFieldLite.containsKey(str) ? mapFieldLite.get(str) : str2;
    }

    @Override // com.google.rpc.ErrorInfoOrBuilder
    public String getMetadataOrThrow(String str) {
        str.getClass();
        MapFieldLite<String, String> mapFieldLite = this.metadata_;
        if (mapFieldLite.containsKey(str)) {
            return mapFieldLite.get(str);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.rpc.ErrorInfoOrBuilder
    public String getReason() {
        return this.reason_;
    }

    @Override // com.google.rpc.ErrorInfoOrBuilder
    public ByteString getReasonBytes() {
        return ByteString.copyFromUtf8(this.reason_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ErrorInfo, Builder> implements ErrorInfoOrBuilder {
        public Builder() {
            super(ErrorInfo.DEFAULT_INSTANCE);
        }

        public Builder clearDomain() {
            copyOnWrite();
            ((ErrorInfo) this.instance).clearDomain();
            return this;
        }

        public Builder clearMetadata() {
            copyOnWrite();
            ((ErrorInfo) this.instance).internalGetMutableMetadata().clear();
            return this;
        }

        public Builder clearReason() {
            copyOnWrite();
            ((ErrorInfo) this.instance).clearReason();
            return this;
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        public boolean containsMetadata(String str) {
            str.getClass();
            return ((ErrorInfo) this.instance).getMetadataMap().containsKey(str);
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        public String getDomain() {
            return ((ErrorInfo) this.instance).getDomain();
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        public ByteString getDomainBytes() {
            return ((ErrorInfo) this.instance).getDomainBytes();
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        @Deprecated
        public Map<String, String> getMetadata() {
            return getMetadataMap();
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        public int getMetadataCount() {
            return ((ErrorInfo) this.instance).getMetadataMap().size();
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        public Map<String, String> getMetadataMap() {
            return Collections.unmodifiableMap(((ErrorInfo) this.instance).getMetadataMap());
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        public String getMetadataOrDefault(String str, String str2) {
            str.getClass();
            Map<String, String> metadataMap = ((ErrorInfo) this.instance).getMetadataMap();
            return metadataMap.containsKey(str) ? metadataMap.get(str) : str2;
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        public String getMetadataOrThrow(String str) {
            str.getClass();
            Map<String, String> metadataMap = ((ErrorInfo) this.instance).getMetadataMap();
            if (metadataMap.containsKey(str)) {
                return metadataMap.get(str);
            }
            throw new IllegalArgumentException();
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        public String getReason() {
            return ((ErrorInfo) this.instance).getReason();
        }

        @Override // com.google.rpc.ErrorInfoOrBuilder
        public ByteString getReasonBytes() {
            return ((ErrorInfo) this.instance).getReasonBytes();
        }

        public Builder putAllMetadata(Map<String, String> map) {
            copyOnWrite();
            ((ErrorInfo) this.instance).internalGetMutableMetadata().putAll(map);
            return this;
        }

        public Builder putMetadata(String str, String str2) {
            str.getClass();
            str2.getClass();
            copyOnWrite();
            ((ErrorInfo) this.instance).internalGetMutableMetadata().put(str, str2);
            return this;
        }

        public Builder removeMetadata(String str) {
            str.getClass();
            copyOnWrite();
            ((ErrorInfo) this.instance).internalGetMutableMetadata().remove(str);
            return this;
        }

        public Builder setDomain(String str) {
            copyOnWrite();
            ((ErrorInfo) this.instance).setDomain(str);
            return this;
        }

        public Builder setDomainBytes(ByteString byteString) {
            copyOnWrite();
            ((ErrorInfo) this.instance).setDomainBytes(byteString);
            return this;
        }

        public Builder setReason(String str) {
            copyOnWrite();
            ((ErrorInfo) this.instance).setReason(str);
            return this;
        }

        public Builder setReasonBytes(ByteString byteString) {
            copyOnWrite();
            ((ErrorInfo) this.instance).setReasonBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(ErrorInfo.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(ErrorInfo errorInfo) {
        return DEFAULT_INSTANCE.createBuilder(errorInfo);
    }

    public static ErrorInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ErrorInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ErrorInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (ErrorInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static ErrorInfo parseFrom(ByteString byteString) {
        return (ErrorInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ErrorInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (ErrorInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ErrorInfo parseFrom(byte[] bArr) {
        return (ErrorInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ErrorInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (ErrorInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ErrorInfo parseFrom(InputStream inputStream) {
        return (ErrorInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ErrorInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ErrorInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ErrorInfo parseFrom(CodedInputStream codedInputStream) {
        return (ErrorInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ErrorInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ErrorInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
