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
public final class ResourceReference extends GeneratedMessageLite<ResourceReference, Builder> implements ResourceReferenceOrBuilder {
    public static final int CHILD_TYPE_FIELD_NUMBER = 2;
    public static final ResourceReference DEFAULT_INSTANCE;
    public static volatile Parser<ResourceReference> PARSER = null;
    public static final int TYPE_FIELD_NUMBER = 1;
    public String type_ = "";
    public String childType_ = "";

    /* JADX INFO: renamed from: com.google.api.ResourceReference$1, reason: invalid class name */
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
        ResourceReference resourceReference = new ResourceReference();
        DEFAULT_INSTANCE = resourceReference;
        GeneratedMessageLite.defaultInstanceMap.put(ResourceReference.class, resourceReference);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearChildType() {
        this.childType_ = DEFAULT_INSTANCE.getChildType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearType() {
        this.type_ = DEFAULT_INSTANCE.getType();
    }

    public static ResourceReference getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static ResourceReference parseDelimitedFrom(InputStream inputStream) {
        return (ResourceReference) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ResourceReference parseFrom(ByteBuffer byteBuffer) {
        return (ResourceReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<ResourceReference> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setChildType(String str) {
        str.getClass();
        this.childType_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setChildTypeBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.childType_ = byteString.toStringUtf8();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setType(String str) {
        str.getClass();
        this.type_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTypeBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.type_ = byteString.toStringUtf8();
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"type_", "childType_"});
            case NEW_MUTABLE_INSTANCE:
                return new ResourceReference();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<ResourceReference> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (ResourceReference.class) {
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

    @Override // com.google.api.ResourceReferenceOrBuilder
    public String getChildType() {
        return this.childType_;
    }

    @Override // com.google.api.ResourceReferenceOrBuilder
    public ByteString getChildTypeBytes() {
        return ByteString.copyFromUtf8(this.childType_);
    }

    @Override // com.google.api.ResourceReferenceOrBuilder
    public String getType() {
        return this.type_;
    }

    @Override // com.google.api.ResourceReferenceOrBuilder
    public ByteString getTypeBytes() {
        return ByteString.copyFromUtf8(this.type_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ResourceReference, Builder> implements ResourceReferenceOrBuilder {
        public Builder() {
            super(ResourceReference.DEFAULT_INSTANCE);
        }

        public Builder clearChildType() {
            copyOnWrite();
            ((ResourceReference) this.instance).clearChildType();
            return this;
        }

        public Builder clearType() {
            copyOnWrite();
            ((ResourceReference) this.instance).clearType();
            return this;
        }

        @Override // com.google.api.ResourceReferenceOrBuilder
        public String getChildType() {
            return ((ResourceReference) this.instance).getChildType();
        }

        @Override // com.google.api.ResourceReferenceOrBuilder
        public ByteString getChildTypeBytes() {
            return ((ResourceReference) this.instance).getChildTypeBytes();
        }

        @Override // com.google.api.ResourceReferenceOrBuilder
        public String getType() {
            return ((ResourceReference) this.instance).getType();
        }

        @Override // com.google.api.ResourceReferenceOrBuilder
        public ByteString getTypeBytes() {
            return ((ResourceReference) this.instance).getTypeBytes();
        }

        public Builder setChildType(String str) {
            copyOnWrite();
            ((ResourceReference) this.instance).setChildType(str);
            return this;
        }

        public Builder setChildTypeBytes(ByteString byteString) {
            copyOnWrite();
            ((ResourceReference) this.instance).setChildTypeBytes(byteString);
            return this;
        }

        public Builder setType(String str) {
            copyOnWrite();
            ((ResourceReference) this.instance).setType(str);
            return this;
        }

        public Builder setTypeBytes(ByteString byteString) {
            copyOnWrite();
            ((ResourceReference) this.instance).setTypeBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(ResourceReference.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(ResourceReference resourceReference) {
        return DEFAULT_INSTANCE.createBuilder(resourceReference);
    }

    public static ResourceReference parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ResourceReference) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ResourceReference parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (ResourceReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static ResourceReference parseFrom(ByteString byteString) {
        return (ResourceReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ResourceReference parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (ResourceReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ResourceReference parseFrom(byte[] bArr) {
        return (ResourceReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ResourceReference parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (ResourceReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ResourceReference parseFrom(InputStream inputStream) {
        return (ResourceReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ResourceReference parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ResourceReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ResourceReference parseFrom(CodedInputStream codedInputStream) {
        return (ResourceReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ResourceReference parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (ResourceReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
