package com.google.longrunning;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Duration;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class WaitOperationRequest extends GeneratedMessageLite<WaitOperationRequest, Builder> implements WaitOperationRequestOrBuilder {
    public static final WaitOperationRequest DEFAULT_INSTANCE;
    public static final int NAME_FIELD_NUMBER = 1;
    public static volatile Parser<WaitOperationRequest> PARSER = null;
    public static final int TIMEOUT_FIELD_NUMBER = 2;
    public String name_ = "";
    public Duration timeout_;

    /* JADX INFO: renamed from: com.google.longrunning.WaitOperationRequest$1, reason: invalid class name */
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
        WaitOperationRequest waitOperationRequest = new WaitOperationRequest();
        DEFAULT_INSTANCE = waitOperationRequest;
        GeneratedMessageLite.defaultInstanceMap.put(WaitOperationRequest.class, waitOperationRequest);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.name_ = DEFAULT_INSTANCE.getName();
    }

    private void clearTimeout() {
        this.timeout_ = null;
    }

    public static WaitOperationRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeTimeout(Duration duration) {
        duration.getClass();
        Duration duration2 = this.timeout_;
        if (duration2 == null || duration2 == Duration.DEFAULT_INSTANCE) {
            this.timeout_ = duration;
        } else {
            this.timeout_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
        }
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static WaitOperationRequest parseDelimitedFrom(InputStream inputStream) {
        return (WaitOperationRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static WaitOperationRequest parseFrom(ByteBuffer byteBuffer) {
        return (WaitOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<WaitOperationRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
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
    public void setTimeout(Duration duration) {
        duration.getClass();
        this.timeout_ = duration;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\t", new Object[]{"name_", "timeout_"});
            case NEW_MUTABLE_INSTANCE:
                return new WaitOperationRequest();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<WaitOperationRequest> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (WaitOperationRequest.class) {
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

    @Override // com.google.longrunning.WaitOperationRequestOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.longrunning.WaitOperationRequestOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    @Override // com.google.longrunning.WaitOperationRequestOrBuilder
    public Duration getTimeout() {
        Duration duration = this.timeout_;
        return duration == null ? Duration.DEFAULT_INSTANCE : duration;
    }

    @Override // com.google.longrunning.WaitOperationRequestOrBuilder
    public boolean hasTimeout() {
        return this.timeout_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<WaitOperationRequest, Builder> implements WaitOperationRequestOrBuilder {
        public Builder() {
            super(WaitOperationRequest.DEFAULT_INSTANCE);
        }

        public Builder clearName() {
            copyOnWrite();
            ((WaitOperationRequest) this.instance).clearName();
            return this;
        }

        public Builder clearTimeout() {
            copyOnWrite();
            ((WaitOperationRequest) this.instance).timeout_ = null;
            return this;
        }

        @Override // com.google.longrunning.WaitOperationRequestOrBuilder
        public String getName() {
            return ((WaitOperationRequest) this.instance).getName();
        }

        @Override // com.google.longrunning.WaitOperationRequestOrBuilder
        public ByteString getNameBytes() {
            return ((WaitOperationRequest) this.instance).getNameBytes();
        }

        @Override // com.google.longrunning.WaitOperationRequestOrBuilder
        public Duration getTimeout() {
            return ((WaitOperationRequest) this.instance).getTimeout();
        }

        @Override // com.google.longrunning.WaitOperationRequestOrBuilder
        public boolean hasTimeout() {
            return ((WaitOperationRequest) this.instance).hasTimeout();
        }

        public Builder mergeTimeout(Duration duration) {
            copyOnWrite();
            ((WaitOperationRequest) this.instance).mergeTimeout(duration);
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((WaitOperationRequest) this.instance).setName(str);
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((WaitOperationRequest) this.instance).setNameBytes(byteString);
            return this;
        }

        public Builder setTimeout(Duration duration) {
            copyOnWrite();
            ((WaitOperationRequest) this.instance).setTimeout(duration);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(WaitOperationRequest.DEFAULT_INSTANCE);
        }

        public Builder setTimeout(Duration.Builder builder) {
            copyOnWrite();
            ((WaitOperationRequest) this.instance).setTimeout(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(WaitOperationRequest waitOperationRequest) {
        return DEFAULT_INSTANCE.createBuilder(waitOperationRequest);
    }

    public static WaitOperationRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (WaitOperationRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static WaitOperationRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (WaitOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static WaitOperationRequest parseFrom(ByteString byteString) {
        return (WaitOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static WaitOperationRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (WaitOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static WaitOperationRequest parseFrom(byte[] bArr) {
        return (WaitOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static WaitOperationRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (WaitOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static WaitOperationRequest parseFrom(InputStream inputStream) {
        return (WaitOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static WaitOperationRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (WaitOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static WaitOperationRequest parseFrom(CodedInputStream codedInputStream) {
        return (WaitOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static WaitOperationRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (WaitOperationRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
