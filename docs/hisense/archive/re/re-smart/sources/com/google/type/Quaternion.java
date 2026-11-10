package com.google.type;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public final class Quaternion extends GeneratedMessageLite<Quaternion, Builder> implements QuaternionOrBuilder {
    public static final Quaternion DEFAULT_INSTANCE;
    public static volatile Parser<Quaternion> PARSER = null;
    public static final int W_FIELD_NUMBER = 4;
    public static final int X_FIELD_NUMBER = 1;
    public static final int Y_FIELD_NUMBER = 2;
    public static final int Z_FIELD_NUMBER = 3;
    public double w_;
    public double x_;
    public double y_;
    public double z_;

    /* JADX INFO: renamed from: com.google.type.Quaternion$1, reason: invalid class name */
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
        Quaternion quaternion = new Quaternion();
        DEFAULT_INSTANCE = quaternion;
        GeneratedMessageLite.defaultInstanceMap.put(Quaternion.class, quaternion);
    }

    private void clearW() {
        this.w_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    private void clearX() {
        this.x_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    private void clearY() {
        this.y_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    private void clearZ() {
        this.z_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public static Quaternion getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Quaternion parseDelimitedFrom(InputStream inputStream) {
        return (Quaternion) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Quaternion parseFrom(ByteBuffer byteBuffer) {
        return (Quaternion) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Quaternion> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void setW(double d2) {
        this.w_ = d2;
    }

    private void setX(double d2) {
        this.x_ = d2;
    }

    private void setY(double d2) {
        this.y_ = d2;
    }

    private void setZ(double d2) {
        this.z_ = d2;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u0000\u0002\u0000\u0003\u0000\u0004\u0000", new Object[]{"x_", "y_", "z_", "w_"});
            case NEW_MUTABLE_INSTANCE:
                return new Quaternion();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Quaternion> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Quaternion.class) {
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

    @Override // com.google.type.QuaternionOrBuilder
    public double getW() {
        return this.w_;
    }

    @Override // com.google.type.QuaternionOrBuilder
    public double getX() {
        return this.x_;
    }

    @Override // com.google.type.QuaternionOrBuilder
    public double getY() {
        return this.y_;
    }

    @Override // com.google.type.QuaternionOrBuilder
    public double getZ() {
        return this.z_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Quaternion, Builder> implements QuaternionOrBuilder {
        public Builder() {
            super(Quaternion.DEFAULT_INSTANCE);
        }

        public Builder clearW() {
            copyOnWrite();
            ((Quaternion) this.instance).w_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            return this;
        }

        public Builder clearX() {
            copyOnWrite();
            ((Quaternion) this.instance).x_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            return this;
        }

        public Builder clearY() {
            copyOnWrite();
            ((Quaternion) this.instance).y_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            return this;
        }

        public Builder clearZ() {
            copyOnWrite();
            ((Quaternion) this.instance).z_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            return this;
        }

        @Override // com.google.type.QuaternionOrBuilder
        public double getW() {
            return ((Quaternion) this.instance).getW();
        }

        @Override // com.google.type.QuaternionOrBuilder
        public double getX() {
            return ((Quaternion) this.instance).getX();
        }

        @Override // com.google.type.QuaternionOrBuilder
        public double getY() {
            return ((Quaternion) this.instance).getY();
        }

        @Override // com.google.type.QuaternionOrBuilder
        public double getZ() {
            return ((Quaternion) this.instance).getZ();
        }

        public Builder setW(double d2) {
            copyOnWrite();
            ((Quaternion) this.instance).w_ = d2;
            return this;
        }

        public Builder setX(double d2) {
            copyOnWrite();
            ((Quaternion) this.instance).x_ = d2;
            return this;
        }

        public Builder setY(double d2) {
            copyOnWrite();
            ((Quaternion) this.instance).y_ = d2;
            return this;
        }

        public Builder setZ(double d2) {
            copyOnWrite();
            ((Quaternion) this.instance).z_ = d2;
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Quaternion.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(Quaternion quaternion) {
        return DEFAULT_INSTANCE.createBuilder(quaternion);
    }

    public static Quaternion parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Quaternion) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Quaternion parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Quaternion) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Quaternion parseFrom(ByteString byteString) {
        return (Quaternion) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Quaternion parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Quaternion) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Quaternion parseFrom(byte[] bArr) {
        return (Quaternion) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Quaternion parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Quaternion) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Quaternion parseFrom(InputStream inputStream) {
        return (Quaternion) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Quaternion parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Quaternion) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Quaternion parseFrom(CodedInputStream codedInputStream) {
        return (Quaternion) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Quaternion parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Quaternion) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
