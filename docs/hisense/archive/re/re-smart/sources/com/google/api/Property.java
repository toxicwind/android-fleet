package com.google.api;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class Property extends GeneratedMessageLite<Property, Builder> implements PropertyOrBuilder {
    public static final Property DEFAULT_INSTANCE;
    public static final int DESCRIPTION_FIELD_NUMBER = 3;
    public static final int NAME_FIELD_NUMBER = 1;
    public static volatile Parser<Property> PARSER = null;
    public static final int TYPE_FIELD_NUMBER = 2;
    public int type_;
    public String name_ = "";
    public String description_ = "";

    /* JADX INFO: renamed from: com.google.api.Property$1, reason: invalid class name */
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

    public enum PropertyType implements Internal.EnumLite {
        UNSPECIFIED(0),
        INT64(1),
        BOOL(2),
        STRING(3),
        DOUBLE(4),
        UNRECOGNIZED(-1);

        public static final int BOOL_VALUE = 2;
        public static final int DOUBLE_VALUE = 4;
        public static final int INT64_VALUE = 1;
        public static final int STRING_VALUE = 3;
        public static final int UNSPECIFIED_VALUE = 0;
        public static final Internal.EnumLiteMap<PropertyType> internalValueMap = new Internal.EnumLiteMap<PropertyType>() { // from class: com.google.api.Property.PropertyType.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public PropertyType findValueByNumber(int i) {
                return PropertyType.forNumber(i);
            }
        };
        public final int value;

        private static final class PropertyTypeVerifier implements Internal.EnumVerifier {
            public static final Internal.EnumVerifier INSTANCE = new PropertyTypeVerifier();

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return PropertyType.forNumber(i) != null;
            }
        }

        PropertyType(int i) {
            this.value = i;
        }

        public static PropertyType forNumber(int i) {
            if (i == 0) {
                return UNSPECIFIED;
            }
            if (i == 1) {
                return INT64;
            }
            if (i == 2) {
                return BOOL;
            }
            if (i == 3) {
                return STRING;
            }
            if (i != 4) {
                return null;
            }
            return DOUBLE;
        }

        public static Internal.EnumLiteMap<PropertyType> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return PropertyTypeVerifier.INSTANCE;
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Deprecated
        public static PropertyType valueOf(int i) {
            return forNumber(i);
        }
    }

    static {
        Property property = new Property();
        DEFAULT_INSTANCE = property;
        GeneratedMessageLite.defaultInstanceMap.put(Property.class, property);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDescription() {
        this.description_ = DEFAULT_INSTANCE.getDescription();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.name_ = DEFAULT_INSTANCE.getName();
    }

    private void clearType() {
        this.type_ = 0;
    }

    public static Property getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Property parseDelimitedFrom(InputStream inputStream) {
        return (Property) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Property parseFrom(ByteBuffer byteBuffer) {
        return (Property) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Property> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDescription(String str) {
        str.getClass();
        this.description_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDescriptionBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.description_ = byteString.toStringUtf8();
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
    public void setType(PropertyType propertyType) {
        this.type_ = propertyType.getNumber();
    }

    private void setTypeValue(int i) {
        this.type_ = i;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\f\u0003Ȉ", new Object[]{"name_", "type_", "description_"});
            case NEW_MUTABLE_INSTANCE:
                return new Property();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Property> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Property.class) {
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

    @Override // com.google.api.PropertyOrBuilder
    public String getDescription() {
        return this.description_;
    }

    @Override // com.google.api.PropertyOrBuilder
    public ByteString getDescriptionBytes() {
        return ByteString.copyFromUtf8(this.description_);
    }

    @Override // com.google.api.PropertyOrBuilder
    public String getName() {
        return this.name_;
    }

    @Override // com.google.api.PropertyOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    @Override // com.google.api.PropertyOrBuilder
    public PropertyType getType() {
        PropertyType propertyTypeForNumber = PropertyType.forNumber(this.type_);
        return propertyTypeForNumber == null ? PropertyType.UNRECOGNIZED : propertyTypeForNumber;
    }

    @Override // com.google.api.PropertyOrBuilder
    public int getTypeValue() {
        return this.type_;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Property, Builder> implements PropertyOrBuilder {
        public Builder() {
            super(Property.DEFAULT_INSTANCE);
        }

        public Builder clearDescription() {
            copyOnWrite();
            ((Property) this.instance).clearDescription();
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((Property) this.instance).clearName();
            return this;
        }

        public Builder clearType() {
            copyOnWrite();
            ((Property) this.instance).type_ = 0;
            return this;
        }

        @Override // com.google.api.PropertyOrBuilder
        public String getDescription() {
            return ((Property) this.instance).getDescription();
        }

        @Override // com.google.api.PropertyOrBuilder
        public ByteString getDescriptionBytes() {
            return ((Property) this.instance).getDescriptionBytes();
        }

        @Override // com.google.api.PropertyOrBuilder
        public String getName() {
            return ((Property) this.instance).getName();
        }

        @Override // com.google.api.PropertyOrBuilder
        public ByteString getNameBytes() {
            return ((Property) this.instance).getNameBytes();
        }

        @Override // com.google.api.PropertyOrBuilder
        public PropertyType getType() {
            return ((Property) this.instance).getType();
        }

        @Override // com.google.api.PropertyOrBuilder
        public int getTypeValue() {
            return ((Property) this.instance).getTypeValue();
        }

        public Builder setDescription(String str) {
            copyOnWrite();
            ((Property) this.instance).setDescription(str);
            return this;
        }

        public Builder setDescriptionBytes(ByteString byteString) {
            copyOnWrite();
            ((Property) this.instance).setDescriptionBytes(byteString);
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((Property) this.instance).setName(str);
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((Property) this.instance).setNameBytes(byteString);
            return this;
        }

        public Builder setType(PropertyType propertyType) {
            copyOnWrite();
            ((Property) this.instance).setType(propertyType);
            return this;
        }

        public Builder setTypeValue(int i) {
            copyOnWrite();
            ((Property) this.instance).type_ = i;
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Property.DEFAULT_INSTANCE);
        }
    }

    public static Builder newBuilder(Property property) {
        return DEFAULT_INSTANCE.createBuilder(property);
    }

    public static Property parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Property) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Property parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Property) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Property parseFrom(ByteString byteString) {
        return (Property) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Property parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Property) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Property parseFrom(byte[] bArr) {
        return (Property) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Property parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Property) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Property parseFrom(InputStream inputStream) {
        return (Property) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Property parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Property) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Property parseFrom(CodedInputStream codedInputStream) {
        return (Property) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Property parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Property) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
