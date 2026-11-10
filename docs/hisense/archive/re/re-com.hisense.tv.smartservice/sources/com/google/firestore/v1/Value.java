package com.google.firestore.v1;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.NullValue;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import com.google.type.LatLng;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class Value extends GeneratedMessageLite<Value, Builder> implements ValueOrBuilder {
    public static final int ARRAY_VALUE_FIELD_NUMBER = 9;
    public static final int BOOLEAN_VALUE_FIELD_NUMBER = 1;
    public static final int BYTES_VALUE_FIELD_NUMBER = 18;
    public static final Value DEFAULT_INSTANCE;
    public static final int DOUBLE_VALUE_FIELD_NUMBER = 3;
    public static final int GEO_POINT_VALUE_FIELD_NUMBER = 8;
    public static final int INTEGER_VALUE_FIELD_NUMBER = 2;
    public static final int MAP_VALUE_FIELD_NUMBER = 6;
    public static final int NULL_VALUE_FIELD_NUMBER = 11;
    public static volatile Parser<Value> PARSER = null;
    public static final int REFERENCE_VALUE_FIELD_NUMBER = 5;
    public static final int STRING_VALUE_FIELD_NUMBER = 17;
    public static final int TIMESTAMP_VALUE_FIELD_NUMBER = 10;
    public int valueTypeCase_ = 0;
    public Object valueType_;

    /* JADX INFO: renamed from: com.google.firestore.v1.Value$1, reason: invalid class name */
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

    public enum ValueTypeCase {
        NULL_VALUE(11),
        BOOLEAN_VALUE(1),
        INTEGER_VALUE(2),
        DOUBLE_VALUE(3),
        TIMESTAMP_VALUE(10),
        STRING_VALUE(17),
        BYTES_VALUE(18),
        REFERENCE_VALUE(5),
        GEO_POINT_VALUE(8),
        ARRAY_VALUE(9),
        MAP_VALUE(6),
        VALUETYPE_NOT_SET(0);

        public final int value;

        ValueTypeCase(int i) {
            this.value = i;
        }

        public static ValueTypeCase forNumber(int i) {
            if (i == 0) {
                return VALUETYPE_NOT_SET;
            }
            if (i == 1) {
                return BOOLEAN_VALUE;
            }
            if (i == 2) {
                return INTEGER_VALUE;
            }
            if (i == 3) {
                return DOUBLE_VALUE;
            }
            if (i == 5) {
                return REFERENCE_VALUE;
            }
            if (i == 6) {
                return MAP_VALUE;
            }
            if (i == 17) {
                return STRING_VALUE;
            }
            if (i == 18) {
                return BYTES_VALUE;
            }
            switch (i) {
                case 8:
                    return GEO_POINT_VALUE;
                case 9:
                    return ARRAY_VALUE;
                case 10:
                    return TIMESTAMP_VALUE;
                case 11:
                    return NULL_VALUE;
                default:
                    return null;
            }
        }

        public int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ValueTypeCase valueOf(int i) {
            return forNumber(i);
        }
    }

    static {
        Value value = new Value();
        DEFAULT_INSTANCE = value;
        GeneratedMessageLite.defaultInstanceMap.put(Value.class, value);
    }

    public static /* synthetic */ void access$200(Value value, int i) {
        value.valueTypeCase_ = 11;
        value.valueType_ = Integer.valueOf(i);
    }

    public static /* synthetic */ void access$500(Value value, boolean z) {
        value.valueTypeCase_ = 1;
        value.valueType_ = Boolean.valueOf(z);
    }

    public static /* synthetic */ void access$700(Value value, long j) {
        value.valueTypeCase_ = 2;
        value.valueType_ = Long.valueOf(j);
    }

    public static /* synthetic */ void access$900(Value value, double d2) {
        value.valueTypeCase_ = 3;
        value.valueType_ = Double.valueOf(d2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearArrayValue() {
        if (this.valueTypeCase_ == 9) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearBooleanValue() {
        if (this.valueTypeCase_ == 1) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearBytesValue() {
        if (this.valueTypeCase_ == 18) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDoubleValue() {
        if (this.valueTypeCase_ == 3) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearGeoPointValue() {
        if (this.valueTypeCase_ == 8) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIntegerValue() {
        if (this.valueTypeCase_ == 2) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMapValue() {
        if (this.valueTypeCase_ == 6) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNullValue() {
        if (this.valueTypeCase_ == 11) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearReferenceValue() {
        if (this.valueTypeCase_ == 5) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearStringValue() {
        if (this.valueTypeCase_ == 17) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTimestampValue() {
        if (this.valueTypeCase_ == 10) {
            this.valueTypeCase_ = 0;
            this.valueType_ = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearValueType() {
        this.valueTypeCase_ = 0;
        this.valueType_ = null;
    }

    public static Value getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeArrayValue(ArrayValue arrayValue) {
        Object obj;
        arrayValue.getClass();
        if (this.valueTypeCase_ != 9 || (obj = this.valueType_) == ArrayValue.DEFAULT_INSTANCE) {
            this.valueType_ = arrayValue;
        } else {
            this.valueType_ = ArrayValue.newBuilder((ArrayValue) obj).mergeFrom(arrayValue).buildPartial();
        }
        this.valueTypeCase_ = 9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeGeoPointValue(LatLng latLng) {
        Object obj;
        latLng.getClass();
        if (this.valueTypeCase_ != 8 || (obj = this.valueType_) == LatLng.DEFAULT_INSTANCE) {
            this.valueType_ = latLng;
        } else {
            this.valueType_ = LatLng.newBuilder((LatLng) obj).mergeFrom(latLng).buildPartial();
        }
        this.valueTypeCase_ = 8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeMapValue(MapValue mapValue) {
        Object obj;
        mapValue.getClass();
        if (this.valueTypeCase_ != 6 || (obj = this.valueType_) == MapValue.DEFAULT_INSTANCE) {
            this.valueType_ = mapValue;
        } else {
            this.valueType_ = MapValue.newBuilder((MapValue) obj).mergeFrom(mapValue).buildPartial();
        }
        this.valueTypeCase_ = 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeTimestampValue(Timestamp timestamp) {
        Object obj;
        timestamp.getClass();
        if (this.valueTypeCase_ != 10 || (obj = this.valueType_) == Timestamp.DEFAULT_INSTANCE) {
            this.valueType_ = timestamp;
        } else {
            this.valueType_ = Timestamp.newBuilder((Timestamp) obj).mergeFrom(timestamp).buildPartial();
        }
        this.valueTypeCase_ = 10;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Value parseDelimitedFrom(InputStream inputStream) {
        return (Value) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Value parseFrom(ByteBuffer byteBuffer) {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser<Value> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setArrayValue(ArrayValue arrayValue) {
        arrayValue.getClass();
        this.valueType_ = arrayValue;
        this.valueTypeCase_ = 9;
    }

    private void setBooleanValue(boolean z) {
        this.valueTypeCase_ = 1;
        this.valueType_ = Boolean.valueOf(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBytesValue(ByteString byteString) {
        byteString.getClass();
        this.valueTypeCase_ = 18;
        this.valueType_ = byteString;
    }

    private void setDoubleValue(double d2) {
        this.valueTypeCase_ = 3;
        this.valueType_ = Double.valueOf(d2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setGeoPointValue(LatLng latLng) {
        latLng.getClass();
        this.valueType_ = latLng;
        this.valueTypeCase_ = 8;
    }

    private void setIntegerValue(long j) {
        this.valueTypeCase_ = 2;
        this.valueType_ = Long.valueOf(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMapValue(MapValue mapValue) {
        mapValue.getClass();
        this.valueType_ = mapValue;
        this.valueTypeCase_ = 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNullValue(NullValue nullValue) {
        this.valueType_ = Integer.valueOf(nullValue.getNumber());
        this.valueTypeCase_ = 11;
    }

    private void setNullValueValue(int i) {
        this.valueTypeCase_ = 11;
        this.valueType_ = Integer.valueOf(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReferenceValue(String str) {
        str.getClass();
        this.valueTypeCase_ = 5;
        this.valueType_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReferenceValueBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.valueType_ = byteString.toStringUtf8();
        this.valueTypeCase_ = 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStringValue(String str) {
        str.getClass();
        this.valueTypeCase_ = 17;
        this.valueType_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStringValueBytes(ByteString byteString) {
        AbstractMessageLite.checkByteStringIsUtf8(byteString);
        this.valueType_ = byteString.toStringUtf8();
        this.valueTypeCase_ = 17;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTimestampValue(Timestamp timestamp) {
        timestamp.getClass();
        this.valueType_ = timestamp;
        this.valueTypeCase_ = 10;
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
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u000b\u0001\u0000\u0001\u0012\u000b\u0000\u0000\u0000\u0001:\u0000\u00025\u0000\u00033\u0000\u0005Ȼ\u0000\u0006<\u0000\b<\u0000\t<\u0000\n<\u0000\u000b?\u0000\u0011Ȼ\u0000\u0012=\u0000", new Object[]{"valueType_", "valueTypeCase_", MapValue.class, LatLng.class, ArrayValue.class, Timestamp.class});
            case NEW_MUTABLE_INSTANCE:
                return new Value();
            case NEW_BUILDER:
                return new Builder(anonymousClass1);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                Parser<Value> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (Value.class) {
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

    @Override // com.google.firestore.v1.ValueOrBuilder
    public ArrayValue getArrayValue() {
        return this.valueTypeCase_ == 9 ? (ArrayValue) this.valueType_ : ArrayValue.DEFAULT_INSTANCE;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public boolean getBooleanValue() {
        if (this.valueTypeCase_ == 1) {
            return ((Boolean) this.valueType_).booleanValue();
        }
        return false;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public ByteString getBytesValue() {
        return this.valueTypeCase_ == 18 ? (ByteString) this.valueType_ : ByteString.EMPTY;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public double getDoubleValue() {
        return this.valueTypeCase_ == 3 ? ((Double) this.valueType_).doubleValue() : FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public LatLng getGeoPointValue() {
        return this.valueTypeCase_ == 8 ? (LatLng) this.valueType_ : LatLng.DEFAULT_INSTANCE;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public long getIntegerValue() {
        if (this.valueTypeCase_ == 2) {
            return ((Long) this.valueType_).longValue();
        }
        return 0L;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public MapValue getMapValue() {
        return this.valueTypeCase_ == 6 ? (MapValue) this.valueType_ : MapValue.DEFAULT_INSTANCE;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public NullValue getNullValue() {
        if (this.valueTypeCase_ != 11) {
            return NullValue.NULL_VALUE;
        }
        NullValue nullValueForNumber = NullValue.forNumber(((Integer) this.valueType_).intValue());
        return nullValueForNumber == null ? NullValue.UNRECOGNIZED : nullValueForNumber;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public int getNullValueValue() {
        if (this.valueTypeCase_ == 11) {
            return ((Integer) this.valueType_).intValue();
        }
        return 0;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public String getReferenceValue() {
        return this.valueTypeCase_ == 5 ? (String) this.valueType_ : "";
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public ByteString getReferenceValueBytes() {
        return ByteString.copyFromUtf8(this.valueTypeCase_ == 5 ? (String) this.valueType_ : "");
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public String getStringValue() {
        return this.valueTypeCase_ == 17 ? (String) this.valueType_ : "";
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public ByteString getStringValueBytes() {
        return ByteString.copyFromUtf8(this.valueTypeCase_ == 17 ? (String) this.valueType_ : "");
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public Timestamp getTimestampValue() {
        return this.valueTypeCase_ == 10 ? (Timestamp) this.valueType_ : Timestamp.DEFAULT_INSTANCE;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public ValueTypeCase getValueTypeCase() {
        return ValueTypeCase.forNumber(this.valueTypeCase_);
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public boolean hasArrayValue() {
        return this.valueTypeCase_ == 9;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public boolean hasGeoPointValue() {
        return this.valueTypeCase_ == 8;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public boolean hasMapValue() {
        return this.valueTypeCase_ == 6;
    }

    @Override // com.google.firestore.v1.ValueOrBuilder
    public boolean hasTimestampValue() {
        return this.valueTypeCase_ == 10;
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Value, Builder> implements ValueOrBuilder {
        public Builder() {
            super(Value.DEFAULT_INSTANCE);
        }

        public Builder clearArrayValue() {
            copyOnWrite();
            ((Value) this.instance).clearArrayValue();
            return this;
        }

        public Builder clearBooleanValue() {
            copyOnWrite();
            ((Value) this.instance).clearBooleanValue();
            return this;
        }

        public Builder clearBytesValue() {
            copyOnWrite();
            ((Value) this.instance).clearBytesValue();
            return this;
        }

        public Builder clearDoubleValue() {
            copyOnWrite();
            ((Value) this.instance).clearDoubleValue();
            return this;
        }

        public Builder clearGeoPointValue() {
            copyOnWrite();
            ((Value) this.instance).clearGeoPointValue();
            return this;
        }

        public Builder clearIntegerValue() {
            copyOnWrite();
            ((Value) this.instance).clearIntegerValue();
            return this;
        }

        public Builder clearMapValue() {
            copyOnWrite();
            ((Value) this.instance).clearMapValue();
            return this;
        }

        public Builder clearNullValue() {
            copyOnWrite();
            ((Value) this.instance).clearNullValue();
            return this;
        }

        public Builder clearReferenceValue() {
            copyOnWrite();
            ((Value) this.instance).clearReferenceValue();
            return this;
        }

        public Builder clearStringValue() {
            copyOnWrite();
            ((Value) this.instance).clearStringValue();
            return this;
        }

        public Builder clearTimestampValue() {
            copyOnWrite();
            ((Value) this.instance).clearTimestampValue();
            return this;
        }

        public Builder clearValueType() {
            copyOnWrite();
            ((Value) this.instance).clearValueType();
            return this;
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public ArrayValue getArrayValue() {
            return ((Value) this.instance).getArrayValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public boolean getBooleanValue() {
            return ((Value) this.instance).getBooleanValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public ByteString getBytesValue() {
            return ((Value) this.instance).getBytesValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public double getDoubleValue() {
            return ((Value) this.instance).getDoubleValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public LatLng getGeoPointValue() {
            return ((Value) this.instance).getGeoPointValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public long getIntegerValue() {
            return ((Value) this.instance).getIntegerValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public MapValue getMapValue() {
            return ((Value) this.instance).getMapValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public NullValue getNullValue() {
            return ((Value) this.instance).getNullValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public int getNullValueValue() {
            return ((Value) this.instance).getNullValueValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public String getReferenceValue() {
            return ((Value) this.instance).getReferenceValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public ByteString getReferenceValueBytes() {
            return ((Value) this.instance).getReferenceValueBytes();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public String getStringValue() {
            return ((Value) this.instance).getStringValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public ByteString getStringValueBytes() {
            return ((Value) this.instance).getStringValueBytes();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public Timestamp getTimestampValue() {
            return ((Value) this.instance).getTimestampValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public ValueTypeCase getValueTypeCase() {
            return ((Value) this.instance).getValueTypeCase();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public boolean hasArrayValue() {
            return ((Value) this.instance).hasArrayValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public boolean hasGeoPointValue() {
            return ((Value) this.instance).hasGeoPointValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public boolean hasMapValue() {
            return ((Value) this.instance).hasMapValue();
        }

        @Override // com.google.firestore.v1.ValueOrBuilder
        public boolean hasTimestampValue() {
            return ((Value) this.instance).hasTimestampValue();
        }

        public Builder mergeArrayValue(ArrayValue arrayValue) {
            copyOnWrite();
            ((Value) this.instance).mergeArrayValue(arrayValue);
            return this;
        }

        public Builder mergeGeoPointValue(LatLng latLng) {
            copyOnWrite();
            ((Value) this.instance).mergeGeoPointValue(latLng);
            return this;
        }

        public Builder mergeMapValue(MapValue mapValue) {
            copyOnWrite();
            ((Value) this.instance).mergeMapValue(mapValue);
            return this;
        }

        public Builder mergeTimestampValue(Timestamp timestamp) {
            copyOnWrite();
            ((Value) this.instance).mergeTimestampValue(timestamp);
            return this;
        }

        public Builder setArrayValue(ArrayValue arrayValue) {
            copyOnWrite();
            ((Value) this.instance).setArrayValue(arrayValue);
            return this;
        }

        public Builder setBooleanValue(boolean z) {
            copyOnWrite();
            Value.access$500((Value) this.instance, z);
            return this;
        }

        public Builder setBytesValue(ByteString byteString) {
            copyOnWrite();
            ((Value) this.instance).setBytesValue(byteString);
            return this;
        }

        public Builder setDoubleValue(double d2) {
            copyOnWrite();
            Value.access$900((Value) this.instance, d2);
            return this;
        }

        public Builder setGeoPointValue(LatLng latLng) {
            copyOnWrite();
            ((Value) this.instance).setGeoPointValue(latLng);
            return this;
        }

        public Builder setIntegerValue(long j) {
            copyOnWrite();
            Value.access$700((Value) this.instance, j);
            return this;
        }

        public Builder setMapValue(MapValue mapValue) {
            copyOnWrite();
            ((Value) this.instance).setMapValue(mapValue);
            return this;
        }

        public Builder setNullValue(NullValue nullValue) {
            copyOnWrite();
            ((Value) this.instance).setNullValue(nullValue);
            return this;
        }

        public Builder setNullValueValue(int i) {
            copyOnWrite();
            Value.access$200((Value) this.instance, i);
            return this;
        }

        public Builder setReferenceValue(String str) {
            copyOnWrite();
            ((Value) this.instance).setReferenceValue(str);
            return this;
        }

        public Builder setReferenceValueBytes(ByteString byteString) {
            copyOnWrite();
            ((Value) this.instance).setReferenceValueBytes(byteString);
            return this;
        }

        public Builder setStringValue(String str) {
            copyOnWrite();
            ((Value) this.instance).setStringValue(str);
            return this;
        }

        public Builder setStringValueBytes(ByteString byteString) {
            copyOnWrite();
            ((Value) this.instance).setStringValueBytes(byteString);
            return this;
        }

        public Builder setTimestampValue(Timestamp timestamp) {
            copyOnWrite();
            ((Value) this.instance).setTimestampValue(timestamp);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            super(Value.DEFAULT_INSTANCE);
        }

        public Builder setArrayValue(ArrayValue.Builder builder) {
            copyOnWrite();
            ((Value) this.instance).setArrayValue(builder.build());
            return this;
        }

        public Builder setGeoPointValue(LatLng.Builder builder) {
            copyOnWrite();
            ((Value) this.instance).setGeoPointValue(builder.build());
            return this;
        }

        public Builder setMapValue(MapValue.Builder builder) {
            copyOnWrite();
            ((Value) this.instance).setMapValue(builder.build());
            return this;
        }

        public Builder setTimestampValue(Timestamp.Builder builder) {
            copyOnWrite();
            ((Value) this.instance).setTimestampValue(builder.build());
            return this;
        }
    }

    public static Builder newBuilder(Value value) {
        return DEFAULT_INSTANCE.createBuilder(value);
    }

    public static Value parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Value) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Value parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Value parseFrom(ByteString byteString) {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Value parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Value parseFrom(byte[] bArr) {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Value parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Value parseFrom(InputStream inputStream) {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Value parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Value parseFrom(CodedInputStream codedInputStream) {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Value parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
