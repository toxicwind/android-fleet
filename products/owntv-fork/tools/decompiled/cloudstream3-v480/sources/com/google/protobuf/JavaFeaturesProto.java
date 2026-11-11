package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes6.dex */
public final class JavaFeaturesProto {
    public static final int JAVA_FIELD_NUMBER = 1001;
    public static final GeneratedMessageLite.GeneratedExtension<DescriptorProtos.FeatureSet, JavaFeatures> java_ = GeneratedMessageLite.newSingularGeneratedExtension(DescriptorProtos.FeatureSet.getDefaultInstance(), JavaFeatures.getDefaultInstance(), JavaFeatures.getDefaultInstance(), null, 1001, WireFormat.FieldType.MESSAGE, JavaFeatures.class);

    public interface JavaFeaturesOrBuilder extends MessageLiteOrBuilder {
        boolean getLargeEnum();

        boolean getLegacyClosedEnum();

        JavaFeatures.NestInFileClassFeature.NestInFileClass getNestInFileClass();

        boolean getUseOldOuterClassnameDefault();

        JavaFeatures.Utf8Validation getUtf8Validation();

        boolean hasLargeEnum();

        boolean hasLegacyClosedEnum();

        boolean hasNestInFileClass();

        boolean hasUseOldOuterClassnameDefault();

        boolean hasUtf8Validation();
    }

    private JavaFeaturesProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
        registry.add((GeneratedMessageLite.GeneratedExtension<?, ?>) java_);
    }

    public static final class JavaFeatures extends GeneratedMessageLite<JavaFeatures, Builder> implements JavaFeaturesOrBuilder {
        private static final JavaFeatures DEFAULT_INSTANCE;
        public static final int LARGE_ENUM_FIELD_NUMBER = 3;
        public static final int LEGACY_CLOSED_ENUM_FIELD_NUMBER = 1;
        public static final int NEST_IN_FILE_CLASS_FIELD_NUMBER = 5;
        private static volatile Parser<JavaFeatures> PARSER = null;
        public static final int USE_OLD_OUTER_CLASSNAME_DEFAULT_FIELD_NUMBER = 4;
        public static final int UTF8_VALIDATION_FIELD_NUMBER = 2;
        private int bitField0_;
        private boolean largeEnum_;
        private boolean legacyClosedEnum_;
        private int nestInFileClass_;
        private boolean useOldOuterClassnameDefault_;
        private int utf8Validation_;

        public interface NestInFileClassFeatureOrBuilder extends MessageLiteOrBuilder {
        }

        private JavaFeatures() {
        }

        public enum Utf8Validation implements Internal.EnumLite {
            UTF8_VALIDATION_UNKNOWN(0),
            DEFAULT(1),
            VERIFY(2);

            public static final int DEFAULT_VALUE = 1;
            public static final int UTF8_VALIDATION_UNKNOWN_VALUE = 0;
            public static final int VERIFY_VALUE = 2;
            private static final Internal.EnumLiteMap<Utf8Validation> internalValueMap = new Internal.EnumLiteMap<Utf8Validation>() { // from class: com.google.protobuf.JavaFeaturesProto.JavaFeatures.Utf8Validation.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public Utf8Validation findValueByNumber(int number) {
                    return Utf8Validation.forNumber(number);
                }
            };
            private final int value;

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static Utf8Validation valueOf(int value) {
                return forNumber(value);
            }

            public static Utf8Validation forNumber(int value) {
                if (value == 0) {
                    return UTF8_VALIDATION_UNKNOWN;
                }
                if (value == 1) {
                    return DEFAULT;
                }
                if (value != 2) {
                    return null;
                }
                return VERIFY;
            }

            public static Internal.EnumLiteMap<Utf8Validation> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return Utf8ValidationVerifier.INSTANCE;
            }

            private static final class Utf8ValidationVerifier implements Internal.EnumVerifier {
                static final Internal.EnumVerifier INSTANCE = new Utf8ValidationVerifier();

                private Utf8ValidationVerifier() {
                }

                @Override // com.google.protobuf.Internal.EnumVerifier
                public boolean isInRange(int number) {
                    return Utf8Validation.forNumber(number) != null;
                }
            }

            Utf8Validation(int value) {
                this.value = value;
            }
        }

        public static final class NestInFileClassFeature extends GeneratedMessageLite<NestInFileClassFeature, Builder> implements NestInFileClassFeatureOrBuilder {
            private static final NestInFileClassFeature DEFAULT_INSTANCE;
            private static volatile Parser<NestInFileClassFeature> PARSER;

            private NestInFileClassFeature() {
            }

            public enum NestInFileClass implements Internal.EnumLite {
                NEST_IN_FILE_CLASS_UNKNOWN(0),
                NO(1),
                YES(2),
                LEGACY(3);

                public static final int LEGACY_VALUE = 3;
                public static final int NEST_IN_FILE_CLASS_UNKNOWN_VALUE = 0;
                public static final int NO_VALUE = 1;
                public static final int YES_VALUE = 2;
                private static final Internal.EnumLiteMap<NestInFileClass> internalValueMap = new Internal.EnumLiteMap<NestInFileClass>() { // from class: com.google.protobuf.JavaFeaturesProto.JavaFeatures.NestInFileClassFeature.NestInFileClass.1
                    @Override // com.google.protobuf.Internal.EnumLiteMap
                    public NestInFileClass findValueByNumber(int number) {
                        return NestInFileClass.forNumber(number);
                    }
                };
                private final int value;

                @Override // com.google.protobuf.Internal.EnumLite
                public final int getNumber() {
                    return this.value;
                }

                @Deprecated
                public static NestInFileClass valueOf(int value) {
                    return forNumber(value);
                }

                public static NestInFileClass forNumber(int value) {
                    if (value == 0) {
                        return NEST_IN_FILE_CLASS_UNKNOWN;
                    }
                    if (value == 1) {
                        return NO;
                    }
                    if (value == 2) {
                        return YES;
                    }
                    if (value != 3) {
                        return null;
                    }
                    return LEGACY;
                }

                public static Internal.EnumLiteMap<NestInFileClass> internalGetValueMap() {
                    return internalValueMap;
                }

                public static Internal.EnumVerifier internalGetVerifier() {
                    return NestInFileClassVerifier.INSTANCE;
                }

                private static final class NestInFileClassVerifier implements Internal.EnumVerifier {
                    static final Internal.EnumVerifier INSTANCE = new NestInFileClassVerifier();

                    private NestInFileClassVerifier() {
                    }

                    @Override // com.google.protobuf.Internal.EnumVerifier
                    public boolean isInRange(int number) {
                        return NestInFileClass.forNumber(number) != null;
                    }
                }

                NestInFileClass(int value) {
                    this.value = value;
                }
            }

            public static NestInFileClassFeature parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
                return (NestInFileClassFeature) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static NestInFileClassFeature parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (NestInFileClassFeature) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static NestInFileClassFeature parseFrom(ByteString data) throws InvalidProtocolBufferException {
                return (NestInFileClassFeature) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static NestInFileClassFeature parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (NestInFileClassFeature) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static NestInFileClassFeature parseFrom(byte[] data) throws InvalidProtocolBufferException {
                return (NestInFileClassFeature) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
            }

            public static NestInFileClassFeature parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return (NestInFileClassFeature) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
            }

            public static NestInFileClassFeature parseFrom(InputStream input) throws IOException {
                return (NestInFileClassFeature) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static NestInFileClassFeature parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (NestInFileClassFeature) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static NestInFileClassFeature parseDelimitedFrom(InputStream input) throws IOException {
                return (NestInFileClassFeature) parseDelimitedFrom(DEFAULT_INSTANCE, input);
            }

            public static NestInFileClassFeature parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (NestInFileClassFeature) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static NestInFileClassFeature parseFrom(CodedInputStream input) throws IOException {
                return (NestInFileClassFeature) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
            }

            public static NestInFileClassFeature parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                return (NestInFileClassFeature) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static Builder newBuilder(NestInFileClassFeature prototype) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
            }

            public static final class Builder extends GeneratedMessageLite.Builder<NestInFileClassFeature, Builder> implements NestInFileClassFeatureOrBuilder {
                /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                    this();
                }

                private Builder() {
                    super(NestInFileClassFeature.DEFAULT_INSTANCE);
                }
            }

            @Override // com.google.protobuf.GeneratedMessageLite
            protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
                Parser defaultInstanceBasedParser;
                int i = AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()];
                AnonymousClass1 anonymousClass1 = null;
                switch (i) {
                    case 1:
                        return new NestInFileClassFeature();
                    case 2:
                        return new Builder(anonymousClass1);
                    case 3:
                        return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0000", null);
                    case 4:
                        return DEFAULT_INSTANCE;
                    case 5:
                        Parser<NestInFileClassFeature> parser = PARSER;
                        if (parser != null) {
                            return parser;
                        }
                        synchronized (NestInFileClassFeature.class) {
                            defaultInstanceBasedParser = PARSER;
                            if (defaultInstanceBasedParser == null) {
                                defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                PARSER = defaultInstanceBasedParser;
                            }
                            break;
                        }
                        return defaultInstanceBasedParser;
                    case 6:
                        return (byte) 1;
                    default:
                        throw null;
                }
            }

            static {
                NestInFileClassFeature nestInFileClassFeature = new NestInFileClassFeature();
                DEFAULT_INSTANCE = nestInFileClassFeature;
                GeneratedMessageLite.registerDefaultInstance(NestInFileClassFeature.class, nestInFileClassFeature);
            }

            public static NestInFileClassFeature getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<NestInFileClassFeature> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }
        }

        @Override // com.google.protobuf.JavaFeaturesProto.JavaFeaturesOrBuilder
        public boolean hasLegacyClosedEnum() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.protobuf.JavaFeaturesProto.JavaFeaturesOrBuilder
        public boolean getLegacyClosedEnum() {
            return this.legacyClosedEnum_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLegacyClosedEnum(boolean value) {
            this.bitField0_ |= 1;
            this.legacyClosedEnum_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLegacyClosedEnum() {
            this.bitField0_ &= -2;
            this.legacyClosedEnum_ = false;
        }

        @Override // com.google.protobuf.JavaFeaturesProto.JavaFeaturesOrBuilder
        public boolean hasUtf8Validation() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.protobuf.JavaFeaturesProto.JavaFeaturesOrBuilder
        public Utf8Validation getUtf8Validation() {
            Utf8Validation utf8ValidationForNumber = Utf8Validation.forNumber(this.utf8Validation_);
            return utf8ValidationForNumber == null ? Utf8Validation.UTF8_VALIDATION_UNKNOWN : utf8ValidationForNumber;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUtf8Validation(Utf8Validation value) {
            this.utf8Validation_ = value.getNumber();
            this.bitField0_ |= 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUtf8Validation() {
            this.bitField0_ &= -3;
            this.utf8Validation_ = 0;
        }

        @Override // com.google.protobuf.JavaFeaturesProto.JavaFeaturesOrBuilder
        public boolean hasLargeEnum() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.google.protobuf.JavaFeaturesProto.JavaFeaturesOrBuilder
        public boolean getLargeEnum() {
            return this.largeEnum_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLargeEnum(boolean value) {
            this.bitField0_ |= 4;
            this.largeEnum_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLargeEnum() {
            this.bitField0_ &= -5;
            this.largeEnum_ = false;
        }

        @Override // com.google.protobuf.JavaFeaturesProto.JavaFeaturesOrBuilder
        public boolean hasUseOldOuterClassnameDefault() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.google.protobuf.JavaFeaturesProto.JavaFeaturesOrBuilder
        public boolean getUseOldOuterClassnameDefault() {
            return this.useOldOuterClassnameDefault_;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUseOldOuterClassnameDefault(boolean value) {
            this.bitField0_ |= 8;
            this.useOldOuterClassnameDefault_ = value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUseOldOuterClassnameDefault() {
            this.bitField0_ &= -9;
            this.useOldOuterClassnameDefault_ = false;
        }

        @Override // com.google.protobuf.JavaFeaturesProto.JavaFeaturesOrBuilder
        public boolean hasNestInFileClass() {
            return (this.bitField0_ & 16) != 0;
        }

        @Override // com.google.protobuf.JavaFeaturesProto.JavaFeaturesOrBuilder
        public NestInFileClassFeature.NestInFileClass getNestInFileClass() {
            NestInFileClassFeature.NestInFileClass nestInFileClassForNumber = NestInFileClassFeature.NestInFileClass.forNumber(this.nestInFileClass_);
            return nestInFileClassForNumber == null ? NestInFileClassFeature.NestInFileClass.NEST_IN_FILE_CLASS_UNKNOWN : nestInFileClassForNumber;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNestInFileClass(NestInFileClassFeature.NestInFileClass value) {
            this.nestInFileClass_ = value.getNumber();
            this.bitField0_ |= 16;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNestInFileClass() {
            this.bitField0_ &= -17;
            this.nestInFileClass_ = 0;
        }

        public static JavaFeatures parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (JavaFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static JavaFeatures parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (JavaFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static JavaFeatures parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (JavaFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static JavaFeatures parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (JavaFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static JavaFeatures parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (JavaFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static JavaFeatures parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (JavaFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static JavaFeatures parseFrom(InputStream input) throws IOException {
            return (JavaFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static JavaFeatures parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (JavaFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static JavaFeatures parseDelimitedFrom(InputStream input) throws IOException {
            return (JavaFeatures) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static JavaFeatures parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (JavaFeatures) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static JavaFeatures parseFrom(CodedInputStream input) throws IOException {
            return (JavaFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static JavaFeatures parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (JavaFeatures) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(JavaFeatures prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<JavaFeatures, Builder> implements JavaFeaturesOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(JavaFeatures.DEFAULT_INSTANCE);
            }

            @Override // com.google.protobuf.JavaFeaturesProto.JavaFeaturesOrBuilder
            public boolean hasLegacyClosedEnum() {
                return ((JavaFeatures) this.instance).hasLegacyClosedEnum();
            }

            @Override // com.google.protobuf.JavaFeaturesProto.JavaFeaturesOrBuilder
            public boolean getLegacyClosedEnum() {
                return ((JavaFeatures) this.instance).getLegacyClosedEnum();
            }

            public Builder setLegacyClosedEnum(boolean value) {
                copyOnWrite();
                ((JavaFeatures) this.instance).setLegacyClosedEnum(value);
                return this;
            }

            public Builder clearLegacyClosedEnum() {
                copyOnWrite();
                ((JavaFeatures) this.instance).clearLegacyClosedEnum();
                return this;
            }

            @Override // com.google.protobuf.JavaFeaturesProto.JavaFeaturesOrBuilder
            public boolean hasUtf8Validation() {
                return ((JavaFeatures) this.instance).hasUtf8Validation();
            }

            @Override // com.google.protobuf.JavaFeaturesProto.JavaFeaturesOrBuilder
            public Utf8Validation getUtf8Validation() {
                return ((JavaFeatures) this.instance).getUtf8Validation();
            }

            public Builder setUtf8Validation(Utf8Validation value) {
                copyOnWrite();
                ((JavaFeatures) this.instance).setUtf8Validation(value);
                return this;
            }

            public Builder clearUtf8Validation() {
                copyOnWrite();
                ((JavaFeatures) this.instance).clearUtf8Validation();
                return this;
            }

            @Override // com.google.protobuf.JavaFeaturesProto.JavaFeaturesOrBuilder
            public boolean hasLargeEnum() {
                return ((JavaFeatures) this.instance).hasLargeEnum();
            }

            @Override // com.google.protobuf.JavaFeaturesProto.JavaFeaturesOrBuilder
            public boolean getLargeEnum() {
                return ((JavaFeatures) this.instance).getLargeEnum();
            }

            public Builder setLargeEnum(boolean value) {
                copyOnWrite();
                ((JavaFeatures) this.instance).setLargeEnum(value);
                return this;
            }

            public Builder clearLargeEnum() {
                copyOnWrite();
                ((JavaFeatures) this.instance).clearLargeEnum();
                return this;
            }

            @Override // com.google.protobuf.JavaFeaturesProto.JavaFeaturesOrBuilder
            public boolean hasUseOldOuterClassnameDefault() {
                return ((JavaFeatures) this.instance).hasUseOldOuterClassnameDefault();
            }

            @Override // com.google.protobuf.JavaFeaturesProto.JavaFeaturesOrBuilder
            public boolean getUseOldOuterClassnameDefault() {
                return ((JavaFeatures) this.instance).getUseOldOuterClassnameDefault();
            }

            public Builder setUseOldOuterClassnameDefault(boolean value) {
                copyOnWrite();
                ((JavaFeatures) this.instance).setUseOldOuterClassnameDefault(value);
                return this;
            }

            public Builder clearUseOldOuterClassnameDefault() {
                copyOnWrite();
                ((JavaFeatures) this.instance).clearUseOldOuterClassnameDefault();
                return this;
            }

            @Override // com.google.protobuf.JavaFeaturesProto.JavaFeaturesOrBuilder
            public boolean hasNestInFileClass() {
                return ((JavaFeatures) this.instance).hasNestInFileClass();
            }

            @Override // com.google.protobuf.JavaFeaturesProto.JavaFeaturesOrBuilder
            public NestInFileClassFeature.NestInFileClass getNestInFileClass() {
                return ((JavaFeatures) this.instance).getNestInFileClass();
            }

            public Builder setNestInFileClass(NestInFileClassFeature.NestInFileClass value) {
                copyOnWrite();
                ((JavaFeatures) this.instance).setNestInFileClass(value);
                return this;
            }

            public Builder clearNestInFileClass() {
                copyOnWrite();
                ((JavaFeatures) this.instance).clearNestInFileClass();
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            Parser defaultInstanceBasedParser;
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
                case 1:
                    return new JavaFeatures();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဇ\u0000\u0002᠌\u0001\u0003ဇ\u0002\u0004ဇ\u0003\u0005᠌\u0004", new Object[]{"bitField0_", "legacyClosedEnum_", "utf8Validation_", Utf8Validation.internalGetVerifier(), "largeEnum_", "useOldOuterClassnameDefault_", "nestInFileClass_", NestInFileClassFeature.NestInFileClass.internalGetVerifier()});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<JavaFeatures> parser = PARSER;
                    if (parser != null) {
                        return parser;
                    }
                    synchronized (JavaFeatures.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                    return defaultInstanceBasedParser;
                case 6:
                    return (byte) 1;
                default:
                    throw null;
            }
        }

        static {
            JavaFeatures javaFeatures = new JavaFeatures();
            DEFAULT_INSTANCE = javaFeatures;
            GeneratedMessageLite.registerDefaultInstance(JavaFeatures.class, javaFeatures);
        }

        public static JavaFeatures getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<JavaFeatures> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* JADX INFO: renamed from: com.google.protobuf.JavaFeaturesProto$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
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
}
