package com.google.protobuf;

import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public final class Mixin extends GeneratedMessageLite<Mixin, Builder> implements MixinOrBuilder {
    public static final Mixin DEFAULT_INSTANCE;
    public static final int NAME_FIELD_NUMBER = 1;
    public static volatile Parser<Mixin> PARSER = null;
    public static final int ROOT_FIELD_NUMBER = 2;
    public String name_ = "";
    public String root_ = "";

    /* JADX INFO: renamed from: com.google.protobuf.Mixin$1, reason: invalid class name */
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
        Mixin mixin = new Mixin();
        DEFAULT_INSTANCE = mixin;
        GeneratedMessageLite.defaultInstanceMap.put(Mixin.class, mixin);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.name_ = DEFAULT_INSTANCE.getName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearRoot() {
        this.root_ = DEFAULT_INSTANCE.getRoot();
    }

    public static Mixin getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Mixin parseDelimitedFrom(InputStream inputStream) {
        return (Mixin) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Mixin parseFrom(ByteBuffer byteBuffer) {
        return (Mixin) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Mixin> parser() {
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
    public void setRoot(String str) {
        str.getClass();
        this.root_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRootBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.root_ = byteString.toStringUtf8();
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"name_", "root_"});
            case NEW_MUTABLE_INSTANCE:
                return new Mixin();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Mixin> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Mixin.class) {
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

    @Override // com.google.protobuf.MixinOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.protobuf.MixinOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    @Override // com.google.protobuf.MixinOrBuilder
    public String getRoot() {
        return this.root_;
    }

    @Override // com.google.protobuf.MixinOrBuilder
    public ByteString getRootBytes() {
        return ByteString.copyFromUtf8(this.root_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Mixin, Builder> implements MixinOrBuilder {
        public Builder() {
            super(Mixin.DEFAULT_INSTANCE);
        }

        public Builder clearName() {
            copyOnWrite();
            ((Mixin) this.instance).clearName();
            return this;
        }

        public Builder clearRoot() {
            copyOnWrite();
            ((Mixin) this.instance).clearRoot();
            return this;
        }

        @Override // com.google.protobuf.MixinOrBuilder
        public String getName() {
            return ((Mixin) this.instance).getName();
        }

        @Override // com.google.protobuf.MixinOrBuilder
        public ByteString getNameBytes() {
            return ((Mixin) this.instance).getNameBytes();
        }

        @Override // com.google.protobuf.MixinOrBuilder
        public String getRoot() {
            return ((Mixin) this.instance).getRoot();
        }

        @Override // com.google.protobuf.MixinOrBuilder
        public ByteString getRootBytes() {
            return ((Mixin) this.instance).getRootBytes();
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((Mixin) this.instance).setName(str);
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((Mixin) this.instance).setNameBytes(byteString);
            return this;
        }

        public Builder setRoot(String str) {
            copyOnWrite();
            ((Mixin) this.instance).setRoot(str);
            return this;
        }

        public Builder setRootBytes(ByteString byteString) {
            copyOnWrite();
            ((Mixin) this.instance).setRootBytes(byteString);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Mixin.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(Mixin mixin) {
        return DEFAULT_INSTANCE.createBuilder(mixin);
    }

    public static Mixin parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Mixin) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Mixin parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Mixin) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Mixin parseFrom(ByteString byteString) {
        return (Mixin) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Mixin parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Mixin) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Mixin parseFrom(byte[] bArr) {
        return (Mixin) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Mixin parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Mixin) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Mixin parseFrom(InputStream inputStream) {
        return (Mixin) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Mixin parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Mixin) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Mixin parseFrom(CodedInputStream codedInputStream) {
        return (Mixin) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Mixin parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Mixin) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
