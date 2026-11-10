package com.google.type;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FloatValue;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public final class Color extends GeneratedMessageLite<Color, Builder> implements ColorOrBuilder {
    public static final int ALPHA_FIELD_NUMBER = 4;
    public static final int BLUE_FIELD_NUMBER = 3;
    public static final Color DEFAULT_INSTANCE;
    public static final int GREEN_FIELD_NUMBER = 2;
    public static volatile Parser<Color> PARSER = null;
    public static final int RED_FIELD_NUMBER = 1;
    public FloatValue alpha_;
    public float blue_;
    public float green_;
    public float red_;

    /* JADX INFO: renamed from: com.google.type.Color$1, reason: invalid class name */
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
        Color color = new Color();
        DEFAULT_INSTANCE = color;
        GeneratedMessageLite.defaultInstanceMap.put(Color.class, color);
    }

    private void clearAlpha() {
        this.alpha_ = null;
    }

    private void clearBlue() {
        this.blue_ = 0.0f;
    }

    private void clearGreen() {
        this.green_ = 0.0f;
    }

    private void clearRed() {
        this.red_ = 0.0f;
    }

    public static Color getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeAlpha(FloatValue floatValue) {
        floatValue.getClass();
        FloatValue floatValue2 = this.alpha_;
        if (floatValue2 == null || floatValue2 == FloatValue.DEFAULT_INSTANCE) {
            this.alpha_ = floatValue;
        } else {
            this.alpha_ = FloatValue.newBuilder(floatValue2).mergeFrom(floatValue).buildPartial();
        }
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Color parseDelimitedFrom(InputStream inputStream) {
        return (Color) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Color parseFrom(ByteBuffer byteBuffer) {
        return (Color) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Color> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAlpha(FloatValue floatValue) {
        floatValue.getClass();
        this.alpha_ = floatValue;
    }

    private void setBlue(float f2) {
        this.blue_ = f2;
    }

    private void setGreen(float f2) {
        this.green_ = f2;
    }

    private void setRed(float f2) {
        this.red_ = f2;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u0001\u0002\u0001\u0003\u0001\u0004\t", new Object[]{"red_", "green_", "blue_", "alpha_"});
            case NEW_MUTABLE_INSTANCE:
                return new Color();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Color> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Color.class) {
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

    @Override // com.google.type.ColorOrBuilder
    public FloatValue getAlpha() {
        FloatValue floatValue = this.alpha_;
        return floatValue == null ? FloatValue.DEFAULT_INSTANCE : floatValue;
    }

    @Override // com.google.type.ColorOrBuilder
    public float getBlue() {
        return this.blue_;
    }

    @Override // com.google.type.ColorOrBuilder
    public float getGreen() {
        return this.green_;
    }

    @Override // com.google.type.ColorOrBuilder
    public float getRed() {
        return this.red_;
    }

    @Override // com.google.type.ColorOrBuilder
    public boolean hasAlpha() {
        return this.alpha_ != null;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Color, Builder> implements ColorOrBuilder {
        public Builder() {
            super(Color.DEFAULT_INSTANCE);
        }

        public Builder clearAlpha() {
            copyOnWrite();
            ((Color) this.instance).alpha_ = null;
            return this;
        }

        public Builder clearBlue() {
            copyOnWrite();
            ((Color) this.instance).blue_ = 0.0f;
            return this;
        }

        public Builder clearGreen() {
            copyOnWrite();
            ((Color) this.instance).green_ = 0.0f;
            return this;
        }

        public Builder clearRed() {
            copyOnWrite();
            ((Color) this.instance).red_ = 0.0f;
            return this;
        }

        @Override // com.google.type.ColorOrBuilder
        public FloatValue getAlpha() {
            return ((Color) this.instance).getAlpha();
        }

        @Override // com.google.type.ColorOrBuilder
        public float getBlue() {
            return ((Color) this.instance).getBlue();
        }

        @Override // com.google.type.ColorOrBuilder
        public float getGreen() {
            return ((Color) this.instance).getGreen();
        }

        @Override // com.google.type.ColorOrBuilder
        public float getRed() {
            return ((Color) this.instance).getRed();
        }

        @Override // com.google.type.ColorOrBuilder
        public boolean hasAlpha() {
            return ((Color) this.instance).hasAlpha();
        }

        public Builder mergeAlpha(FloatValue floatValue) {
            copyOnWrite();
            ((Color) this.instance).mergeAlpha(floatValue);
            return this;
        }

        public Builder setAlpha(FloatValue floatValue) {
            copyOnWrite();
            ((Color) this.instance).setAlpha(floatValue);
            return this;
        }

        public Builder setBlue(float f2) {
            copyOnWrite();
            ((Color) this.instance).blue_ = f2;
            return this;
        }

        public Builder setGreen(float f2) {
            copyOnWrite();
            ((Color) this.instance).green_ = f2;
            return this;
        }

        public Builder setRed(float f2) {
            copyOnWrite();
            ((Color) this.instance).red_ = f2;
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Color.DEFAULT_INSTANCE);
        }

        public Builder setAlpha(FloatValue.Builder builder) {
            copyOnWrite();
            ((Color) this.instance).setAlpha(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(Color color) {
        return DEFAULT_INSTANCE.createBuilder(color);
    }

    public static Color parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Color) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Color parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Color) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Color parseFrom(ByteString byteString) {
        return (Color) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Color parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Color) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Color parseFrom(byte[] bArr) {
        return (Color) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Color parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Color) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Color parseFrom(InputStream inputStream) {
        return (Color) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Color parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Color) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Color parseFrom(CodedInputStream codedInputStream) {
        return (Color) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Color parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Color) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
