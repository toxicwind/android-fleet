package com.google.type;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public final class Fraction extends GeneratedMessageLite<Fraction, Builder> implements FractionOrBuilder {
    public static final Fraction DEFAULT_INSTANCE;
    public static final int DENOMINATOR_FIELD_NUMBER = 2;
    public static final int NUMERATOR_FIELD_NUMBER = 1;
    public static volatile Parser<Fraction> PARSER;
    public long denominator_;
    public long numerator_;

    /* JADX INFO: renamed from: com.google.type.Fraction$1, reason: invalid class name */
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
        Fraction fraction = new Fraction();
        DEFAULT_INSTANCE = fraction;
        GeneratedMessageLite.defaultInstanceMap.put(Fraction.class, fraction);
    }

    private void clearDenominator() {
        this.denominator_ = 0L;
    }

    private void clearNumerator() {
        this.numerator_ = 0L;
    }

    public static Fraction getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Fraction parseDelimitedFrom(InputStream inputStream) {
        return (Fraction) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Fraction parseFrom(ByteBuffer byteBuffer) {
        return (Fraction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Fraction> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    private void setDenominator(long j) {
        this.denominator_ = j;
    }

    private void setNumerator(long j) {
        this.numerator_ = j;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0002\u0002\u0002", new Object[]{"numerator_", "denominator_"});
            case NEW_MUTABLE_INSTANCE:
                return new Fraction();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Fraction> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Fraction.class) {
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

    @Override // com.google.type.FractionOrBuilder
    public long getDenominator() {
        return this.denominator_;
    }

    @Override // com.google.type.FractionOrBuilder
    public long getNumerator() {
        return this.numerator_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Fraction, Builder> implements FractionOrBuilder {
        public Builder() {
            super(Fraction.DEFAULT_INSTANCE);
        }

        public Builder clearDenominator() {
            copyOnWrite();
            ((Fraction) this.instance).denominator_ = 0L;
            return this;
        }

        public Builder clearNumerator() {
            copyOnWrite();
            ((Fraction) this.instance).numerator_ = 0L;
            return this;
        }

        @Override // com.google.type.FractionOrBuilder
        public long getDenominator() {
            return ((Fraction) this.instance).getDenominator();
        }

        @Override // com.google.type.FractionOrBuilder
        public long getNumerator() {
            return ((Fraction) this.instance).getNumerator();
        }

        public Builder setDenominator(long j) {
            copyOnWrite();
            ((Fraction) this.instance).denominator_ = j;
            return this;
        }

        public Builder setNumerator(long j) {
            copyOnWrite();
            ((Fraction) this.instance).numerator_ = j;
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Fraction.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(Fraction fraction) {
        return DEFAULT_INSTANCE.createBuilder(fraction);
    }

    public static Fraction parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Fraction) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Fraction parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Fraction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Fraction parseFrom(ByteString byteString) {
        return (Fraction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Fraction parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Fraction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Fraction parseFrom(byte[] bArr) {
        return (Fraction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Fraction parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Fraction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Fraction parseFrom(InputStream inputStream) {
        return (Fraction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Fraction parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Fraction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Fraction parseFrom(CodedInputStream codedInputStream) {
        return (Fraction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Fraction parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Fraction) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
