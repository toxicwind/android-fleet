package com.google.type;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public final class TimeZone extends GeneratedMessageLite<TimeZone, Builder> implements TimeZoneOrBuilder {
    public static final TimeZone DEFAULT_INSTANCE;
    public static final int ID_FIELD_NUMBER = 1;
    public static volatile Parser<TimeZone> PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 2;
    public String id_ = "";
    public String version_ = "";

    /* JADX INFO: renamed from: com.google.type.TimeZone$1, reason: invalid class name */
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
        TimeZone timeZone = new TimeZone();
        DEFAULT_INSTANCE = timeZone;
        GeneratedMessageLite.defaultInstanceMap.put(TimeZone.class, timeZone);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearId() {
        this.id_ = DEFAULT_INSTANCE.getId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearVersion() {
        this.version_ = DEFAULT_INSTANCE.getVersion();
    }

    public static TimeZone getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static TimeZone parseDelimitedFrom(InputStream inputStream) {
        return (TimeZone) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TimeZone parseFrom(ByteBuffer byteBuffer) {
        return (TimeZone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<TimeZone> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setId(String str) {
        str.getClass();
        this.id_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIdBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.id_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVersion(String str) {
        str.getClass();
        this.version_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVersionBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.version_ = byteString.toStringUtf8();
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"id_", "version_"});
            case NEW_MUTABLE_INSTANCE:
                return new TimeZone();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<TimeZone> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (TimeZone.class) {
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

    @Override // com.google.type.TimeZoneOrBuilder
    public String getId() {
        return this.id_;
    }

    @Override // com.google.type.TimeZoneOrBuilder
    public ByteString getIdBytes() {
        return ByteString.copyFromUtf8(this.id_);
    }

    @Override // com.google.type.TimeZoneOrBuilder
    public String getVersion() {
        return this.version_;
    }

    @Override // com.google.type.TimeZoneOrBuilder
    public ByteString getVersionBytes() {
        return ByteString.copyFromUtf8(this.version_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<TimeZone, Builder> implements TimeZoneOrBuilder {
        public Builder() {
            super(TimeZone.DEFAULT_INSTANCE);
        }

        public Builder clearId() {
            copyOnWrite();
            ((TimeZone) this.instance).clearId();
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((TimeZone) this.instance).clearVersion();
            return this;
        }

        @Override // com.google.type.TimeZoneOrBuilder
        public String getId() {
            return ((TimeZone) this.instance).getId();
        }

        @Override // com.google.type.TimeZoneOrBuilder
        public ByteString getIdBytes() {
            return ((TimeZone) this.instance).getIdBytes();
        }

        @Override // com.google.type.TimeZoneOrBuilder
        public String getVersion() {
            return ((TimeZone) this.instance).getVersion();
        }

        @Override // com.google.type.TimeZoneOrBuilder
        public ByteString getVersionBytes() {
            return ((TimeZone) this.instance).getVersionBytes();
        }

        public Builder setId(String str) {
            copyOnWrite();
            ((TimeZone) this.instance).setId(str);
            return this;
        }

        public Builder setIdBytes(ByteString byteString) {
            copyOnWrite();
            ((TimeZone) this.instance).setIdBytes(byteString);
            return this;
        }

        public Builder setVersion(String str) {
            copyOnWrite();
            ((TimeZone) this.instance).setVersion(str);
            return this;
        }

        public Builder setVersionBytes(ByteString byteString) {
            copyOnWrite();
            ((TimeZone) this.instance).setVersionBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(TimeZone.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(TimeZone timeZone) {
        return DEFAULT_INSTANCE.createBuilder(timeZone);
    }

    public static TimeZone parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (TimeZone) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static TimeZone parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (TimeZone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static TimeZone parseFrom(ByteString byteString) {
        return (TimeZone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static TimeZone parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (TimeZone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static TimeZone parseFrom(byte[] bArr) {
        return (TimeZone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static TimeZone parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (TimeZone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static TimeZone parseFrom(InputStream inputStream) {
        return (TimeZone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TimeZone parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (TimeZone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static TimeZone parseFrom(CodedInputStream codedInputStream) {
        return (TimeZone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static TimeZone parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (TimeZone) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
