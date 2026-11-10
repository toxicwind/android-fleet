package com.google.protobuf;

import b.a.a.a.a;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes2.dex */
public final class MessageSchema<T> implements Schema<T> {
    public static final int ENFORCE_UTF8_MASK = 536870912;
    public static final int FIELD_TYPE_MASK = 267386880;
    public static final int INTS_PER_FIELD = 3;
    public static final int NO_PRESENCE_SENTINEL = 1048575;
    public static final int OFFSET_BITS = 20;
    public static final int OFFSET_MASK = 1048575;
    public static final int ONEOF_TYPE_OFFSET = 51;
    public static final int REQUIRED_MASK = 268435456;
    public final int[] buffer;
    public final int checkInitializedCount;
    public final MessageLite defaultInstance;
    public final ExtensionSchema<?> extensionSchema;
    public final boolean hasExtensions;
    public final int[] intArray;
    public final ListFieldSchema listFieldSchema;
    public final boolean lite;
    public final MapFieldSchema mapFieldSchema;
    public final int maxFieldNumber;
    public final int minFieldNumber;
    public final NewInstanceSchema newInstanceSchema;
    public final Object[] objects;
    public final boolean proto3;
    public final int repeatedFieldOffsetStart;
    public final UnknownFieldSchema<?, ?> unknownFieldSchema;
    public final boolean useCachedSizeField;
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    public static final Unsafe UNSAFE = UnsafeUtil.getUnsafe();

    /* JADX INFO: renamed from: com.google.protobuf.MessageSchema$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType = new int[WireFormat.FieldType.values().length];

        static {
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    public MessageSchema(int[] iArr, Object[] objArr, int i, int i2, MessageLite messageLite, boolean z, boolean z2, int[] iArr2, int i3, int i4, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        this.buffer = iArr;
        this.objects = objArr;
        this.minFieldNumber = i;
        this.maxFieldNumber = i2;
        this.lite = messageLite instanceof GeneratedMessageLite;
        this.proto3 = z;
        this.hasExtensions = extensionSchema != null && extensionSchema.hasExtensions(messageLite);
        this.useCachedSizeField = z2;
        this.intArray = iArr2;
        this.checkInitializedCount = i3;
        this.repeatedFieldOffsetStart = i4;
        this.newInstanceSchema = newInstanceSchema;
        this.listFieldSchema = listFieldSchema;
        this.unknownFieldSchema = unknownFieldSchema;
        this.extensionSchema = extensionSchema;
        this.defaultInstance = messageLite;
        this.mapFieldSchema = mapFieldSchema;
    }

    private boolean arePresentForEquals(T t, T t2, int i) {
        return isFieldPresent(t, i) == isFieldPresent(t2, i);
    }

    public static <T> boolean booleanAt(T t, long j) {
        return UnsafeUtil.getBoolean(t, j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <K, V> int decodeMapEntry(byte[] bArr, int i, int i2, MapEntryLite.Metadata<K, V> metadata, Map<K, V> map, ArrayDecoders.Registers registers) throws InvalidProtocolBufferException {
        int iDecodeVarint32;
        int iDecodeVarint33 = ArrayDecoders.decodeVarint32(bArr, i, registers);
        int i3 = registers.int1;
        if (i3 < 0 || i3 > i2 - iDecodeVarint33) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        int i4 = iDecodeVarint33 + i3;
        Object obj = metadata.defaultKey;
        Object obj2 = metadata.defaultValue;
        while (iDecodeVarint33 < i4) {
            int i5 = iDecodeVarint33 + 1;
            int i6 = bArr[iDecodeVarint33];
            if (i6 < 0) {
                iDecodeVarint32 = ArrayDecoders.decodeVarint32(i6, bArr, i5, registers);
                i6 = registers.int1;
            } else {
                iDecodeVarint32 = i5;
            }
            int i7 = i6 >>> 3;
            int i8 = i6 & 7;
            if (i7 != 1) {
                if (i7 == 2 && i8 == metadata.valueType.getWireType()) {
                    iDecodeVarint33 = decodeMapEntryValue(bArr, iDecodeVarint32, i2, metadata.valueType, metadata.defaultValue.getClass(), registers);
                    obj2 = registers.object1;
                } else {
                    iDecodeVarint33 = ArrayDecoders.skipField(i6, bArr, iDecodeVarint32, i2, registers);
                }
            } else if (i8 == metadata.keyType.getWireType()) {
                iDecodeVarint33 = decodeMapEntryValue(bArr, iDecodeVarint32, i2, metadata.keyType, null, registers);
                obj = registers.object1;
            } else {
                iDecodeVarint33 = ArrayDecoders.skipField(i6, bArr, iDecodeVarint32, i2, registers);
            }
        }
        if (iDecodeVarint33 != i4) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        map.put(obj, obj2);
        return i4;
    }

    private int decodeMapEntryValue(byte[] bArr, int i, int i2, WireFormat.FieldType fieldType, Class<?> cls, ArrayDecoders.Registers registers) {
        switch (fieldType.ordinal()) {
            case 0:
                registers.object1 = Double.valueOf(ArrayDecoders.decodeDouble(bArr, i));
                return i + 8;
            case 1:
                registers.object1 = Float.valueOf(ArrayDecoders.decodeFloat(bArr, i));
                return i + 4;
            case 2:
            case 3:
                int iDecodeVarint64 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                registers.object1 = Long.valueOf(registers.long1);
                return iDecodeVarint64;
            case 4:
            case 12:
            case 13:
                int iDecodeVarint32 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                registers.object1 = Integer.valueOf(registers.int1);
                return iDecodeVarint32;
            case 5:
            case 15:
                registers.object1 = Long.valueOf(ArrayDecoders.decodeFixed64(bArr, i));
                return i + 8;
            case 6:
            case 14:
                registers.object1 = Integer.valueOf(ArrayDecoders.decodeFixed32(bArr, i));
                return i + 4;
            case 7:
                int iDecodeVarint65 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                registers.object1 = Boolean.valueOf(registers.long1 != 0);
                return iDecodeVarint65;
            case 8:
                return ArrayDecoders.decodeStringRequireUtf8(bArr, i, registers);
            case 9:
            default:
                throw new RuntimeException("unsupported field type.");
            case 10:
                return ArrayDecoders.decodeMessageField(Protobuf.INSTANCE.schemaFor((Class) cls), bArr, i, i2, registers);
            case 11:
                return ArrayDecoders.decodeBytes(bArr, i, registers);
            case 16:
                int iDecodeVarint33 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                registers.object1 = Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1));
                return iDecodeVarint33;
            case 17:
                int iDecodeVarint66 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                registers.object1 = Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1));
                return iDecodeVarint66;
        }
    }

    public static <T> double doubleAt(T t, long j) {
        return UnsafeUtil.getDouble(t, j);
    }

    private final <UT, UB> UB filterMapUnknownEnumValues(Object obj, int i, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema) {
        Internal.EnumVerifier enumVerifier;
        int[] iArr = this.buffer;
        int i2 = iArr[i];
        Object object = UnsafeUtil.getObject(obj, offset(iArr[i + 1]));
        return (object == null || (enumVerifier = (Internal.EnumVerifier) this.objects[((i / 3) * 2) + 1]) == null) ? ub : (UB) filterUnknownEnumMap(i, i2, this.mapFieldSchema.forMutableMapData(object), enumVerifier, ub, unknownFieldSchema);
    }

    private final <K, V, UT, UB> UB filterUnknownEnumMap(int i, int i2, Map<K, V> map, Internal.EnumVerifier enumVerifier, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema) {
        MapEntryLite.Metadata<?, ?> metadataForMapMetadata = this.mapFieldSchema.forMapMetadata(this.objects[(i / 3) * 2]);
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!enumVerifier.isInRange(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = unknownFieldSchema.newBuilder();
                }
                ByteString.CodedBuilder codedBuilderNewCodedBuilder = ByteString.newCodedBuilder(MapEntryLite.computeSerializedSize(metadataForMapMetadata, next.getKey(), next.getValue()));
                CodedOutputStream codedOutput = codedBuilderNewCodedBuilder.getCodedOutput();
                try {
                    K key = next.getKey();
                    V value = next.getValue();
                    FieldSet.writeElement(codedOutput, metadataForMapMetadata.keyType, 1, key);
                    FieldSet.writeElement(codedOutput, metadataForMapMetadata.valueType, 2, value);
                    unknownFieldSchema.addLengthDelimited(ub, i2, codedBuilderNewCodedBuilder.build());
                    it.remove();
                } catch (IOException e2) {
                    throw new RuntimeException(e2);
                }
            }
        }
        return ub;
    }

    public static <T> float floatAt(T t, long j) {
        return UnsafeUtil.getFloat(t, j);
    }

    private Internal.EnumVerifier getEnumFieldVerifier(int i) {
        return (Internal.EnumVerifier) this.objects[((i / 3) * 2) + 1];
    }

    private Object getMapFieldDefaultEntry(int i) {
        return this.objects[(i / 3) * 2];
    }

    private Schema getMessageFieldSchema(int i) {
        int i2 = (i / 3) * 2;
        Object[] objArr = this.objects;
        Schema schema = (Schema) objArr[i2];
        if (schema != null) {
            return schema;
        }
        Schema<T> schemaSchemaFor = Protobuf.INSTANCE.schemaFor((Class) objArr[i2 + 1]);
        this.objects[i2] = schemaSchemaFor;
        return schemaSchemaFor;
    }

    public static UnknownFieldSetLite getMutableUnknownFields(Object obj) {
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) obj;
        UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
        if (unknownFieldSetLite != UnknownFieldSetLite.DEFAULT_INSTANCE) {
            return unknownFieldSetLite;
        }
        UnknownFieldSetLite unknownFieldSetLite2 = new UnknownFieldSetLite();
        generatedMessageLite.unknownFields = unknownFieldSetLite2;
        return unknownFieldSetLite2;
    }

    private int getSerializedSizeProto2(T t) {
        int i;
        int i2;
        int iComputeDoubleSize;
        int iComputeBoolSize;
        int iComputeSizeFixed64ListNoTag;
        int iComputeTagSize;
        int iComputeUInt32SizeNoTag;
        Unsafe unsafe = UNSAFE;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 1048575;
        while (i4 < this.buffer.length) {
            int iTypeAndOffsetAt = typeAndOffsetAt(i4);
            int i8 = this.buffer[i4];
            int iType = type(iTypeAndOffsetAt);
            if (iType <= 17) {
                i = this.buffer[i4 + 2];
                int i9 = i3 & i;
                i2 = 1 << (i >>> 20);
                if (i9 != i7) {
                    i6 = unsafe.getInt(t, i9);
                } else {
                    i9 = i7;
                }
                i7 = i9;
            } else {
                i = (!this.useCachedSizeField || iType < FieldType.DOUBLE_LIST_PACKED.id() || iType > FieldType.SINT64_LIST_PACKED.id()) ? 0 : i3 & this.buffer[i4 + 2];
                i2 = 0;
            }
            long jOffset = offset(iTypeAndOffsetAt);
            int i10 = i7;
            int i11 = i6;
            switch (iType) {
                case 0:
                    if ((i11 & i2) != 0) {
                        iComputeDoubleSize = CodedOutputStream.computeDoubleSize(i8, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        i5 = iComputeDoubleSize + i5;
                    }
                    break;
                case 1:
                    if ((i11 & i2) != 0) {
                        iComputeDoubleSize = CodedOutputStream.computeFloatSize(i8, 0.0f);
                        i5 = iComputeDoubleSize + i5;
                    }
                    break;
                case 2:
                    if ((i11 & i2) != 0) {
                        iComputeDoubleSize = CodedOutputStream.computeInt64Size(i8, unsafe.getLong(t, jOffset));
                        i5 = iComputeDoubleSize + i5;
                    }
                    break;
                case 3:
                    if ((i11 & i2) != 0) {
                        iComputeDoubleSize = CodedOutputStream.computeUInt64Size(i8, unsafe.getLong(t, jOffset));
                        i5 = iComputeDoubleSize + i5;
                    }
                    break;
                case 4:
                    if ((i11 & i2) != 0) {
                        iComputeDoubleSize = CodedOutputStream.computeInt32Size(i8, unsafe.getInt(t, jOffset));
                        i5 = iComputeDoubleSize + i5;
                    }
                    break;
                case 5:
                    if ((i11 & i2) != 0) {
                        iComputeDoubleSize = CodedOutputStream.computeFixed64Size(i8, 0L);
                        i5 = iComputeDoubleSize + i5;
                    }
                    break;
                case 6:
                    if ((i11 & i2) != 0) {
                        iComputeDoubleSize = CodedOutputStream.computeFixed32Size(i8, 0);
                        i5 = iComputeDoubleSize + i5;
                    }
                    break;
                case 7:
                    if ((i11 & i2) != 0) {
                        iComputeBoolSize = CodedOutputStream.computeBoolSize(i8, true);
                        i5 += iComputeBoolSize;
                    }
                    break;
                case 8:
                    if ((i11 & i2) != 0) {
                        Object object = unsafe.getObject(t, jOffset);
                        iComputeBoolSize = object instanceof ByteString ? CodedOutputStream.computeBytesSize(i8, (ByteString) object) : CodedOutputStream.computeStringSize(i8, (String) object);
                        i5 += iComputeBoolSize;
                    }
                    break;
                case 9:
                    if ((i11 & i2) != 0) {
                        iComputeBoolSize = SchemaUtil.computeSizeMessage(i8, unsafe.getObject(t, jOffset), getMessageFieldSchema(i4));
                        i5 += iComputeBoolSize;
                    }
                    break;
                case 10:
                    if ((i11 & i2) != 0) {
                        iComputeBoolSize = CodedOutputStream.computeBytesSize(i8, (ByteString) unsafe.getObject(t, jOffset));
                        i5 += iComputeBoolSize;
                    }
                    break;
                case 11:
                    if ((i11 & i2) != 0) {
                        iComputeBoolSize = CodedOutputStream.computeUInt32Size(i8, unsafe.getInt(t, jOffset));
                        i5 += iComputeBoolSize;
                    }
                    break;
                case 12:
                    if ((i11 & i2) != 0) {
                        iComputeBoolSize = CodedOutputStream.computeEnumSize(i8, unsafe.getInt(t, jOffset));
                        i5 += iComputeBoolSize;
                    }
                    break;
                case 13:
                    if ((i11 & i2) != 0) {
                        iComputeBoolSize = CodedOutputStream.computeSFixed32Size(i8, 0);
                        i5 += iComputeBoolSize;
                    }
                    break;
                case 14:
                    if ((i11 & i2) != 0) {
                        iComputeBoolSize = CodedOutputStream.computeSFixed64Size(i8, 0L);
                        i5 += iComputeBoolSize;
                    }
                    break;
                case 15:
                    if ((i11 & i2) != 0) {
                        iComputeBoolSize = CodedOutputStream.computeSInt32Size(i8, unsafe.getInt(t, jOffset));
                        i5 += iComputeBoolSize;
                    }
                    break;
                case 16:
                    if ((i11 & i2) != 0) {
                        iComputeBoolSize = CodedOutputStream.computeSInt64Size(i8, unsafe.getLong(t, jOffset));
                        i5 += iComputeBoolSize;
                    }
                    break;
                case 17:
                    if ((i11 & i2) != 0) {
                        iComputeBoolSize = CodedOutputStream.computeGroupSize(i8, (MessageLite) unsafe.getObject(t, jOffset), getMessageFieldSchema(i4));
                        i5 += iComputeBoolSize;
                    }
                    break;
                case 18:
                    iComputeBoolSize = SchemaUtil.computeSizeFixed64List(i8, (List) unsafe.getObject(t, jOffset), false);
                    i5 += iComputeBoolSize;
                    break;
                case 19:
                    iComputeDoubleSize = SchemaUtil.computeSizeFixed32List(i8, (List) unsafe.getObject(t, jOffset), false);
                    i5 = iComputeDoubleSize + i5;
                    break;
                case 20:
                    iComputeDoubleSize = SchemaUtil.computeSizeInt64List(i8, (List) unsafe.getObject(t, jOffset), false);
                    i5 = iComputeDoubleSize + i5;
                    break;
                case 21:
                    iComputeDoubleSize = SchemaUtil.computeSizeUInt64List(i8, (List) unsafe.getObject(t, jOffset), false);
                    i5 = iComputeDoubleSize + i5;
                    break;
                case 22:
                    iComputeDoubleSize = SchemaUtil.computeSizeInt32List(i8, (List) unsafe.getObject(t, jOffset), false);
                    i5 = iComputeDoubleSize + i5;
                    break;
                case 23:
                    iComputeDoubleSize = SchemaUtil.computeSizeFixed64List(i8, (List) unsafe.getObject(t, jOffset), false);
                    i5 = iComputeDoubleSize + i5;
                    break;
                case 24:
                    iComputeDoubleSize = SchemaUtil.computeSizeFixed32List(i8, (List) unsafe.getObject(t, jOffset), false);
                    i5 = iComputeDoubleSize + i5;
                    break;
                case 25:
                    iComputeDoubleSize = SchemaUtil.computeSizeBoolList(i8, (List) unsafe.getObject(t, jOffset), false);
                    i5 = iComputeDoubleSize + i5;
                    break;
                case 26:
                    iComputeBoolSize = SchemaUtil.computeSizeStringList(i8, (List) unsafe.getObject(t, jOffset));
                    i5 += iComputeBoolSize;
                    break;
                case 27:
                    iComputeBoolSize = SchemaUtil.computeSizeMessageList(i8, (List) unsafe.getObject(t, jOffset), getMessageFieldSchema(i4));
                    i5 += iComputeBoolSize;
                    break;
                case 28:
                    iComputeBoolSize = SchemaUtil.computeSizeByteStringList(i8, (List) unsafe.getObject(t, jOffset));
                    i5 += iComputeBoolSize;
                    break;
                case 29:
                    iComputeBoolSize = SchemaUtil.computeSizeUInt32List(i8, (List) unsafe.getObject(t, jOffset), false);
                    i5 += iComputeBoolSize;
                    break;
                case 30:
                    iComputeDoubleSize = SchemaUtil.computeSizeEnumList(i8, (List) unsafe.getObject(t, jOffset), false);
                    i5 = iComputeDoubleSize + i5;
                    break;
                case 31:
                    iComputeDoubleSize = SchemaUtil.computeSizeFixed32List(i8, (List) unsafe.getObject(t, jOffset), false);
                    i5 = iComputeDoubleSize + i5;
                    break;
                case 32:
                    iComputeDoubleSize = SchemaUtil.computeSizeFixed64List(i8, (List) unsafe.getObject(t, jOffset), false);
                    i5 = iComputeDoubleSize + i5;
                    break;
                case 33:
                    iComputeDoubleSize = SchemaUtil.computeSizeSInt32List(i8, (List) unsafe.getObject(t, jOffset), false);
                    i5 = iComputeDoubleSize + i5;
                    break;
                case 34:
                    iComputeDoubleSize = SchemaUtil.computeSizeSInt64List(i8, (List) unsafe.getObject(t, jOffset), false);
                    i5 = iComputeDoubleSize + i5;
                    break;
                case 35:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(i8);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i5 += iComputeUInt32SizeNoTag + iComputeTagSize + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 36:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(i8);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i5 += iComputeUInt32SizeNoTag + iComputeTagSize + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 37:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(i8);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i5 += iComputeUInt32SizeNoTag + iComputeTagSize + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 38:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(i8);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i5 += iComputeUInt32SizeNoTag + iComputeTagSize + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 39:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(i8);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i5 += iComputeUInt32SizeNoTag + iComputeTagSize + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 40:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(i8);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i5 += iComputeUInt32SizeNoTag + iComputeTagSize + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 41:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(i8);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i5 += iComputeUInt32SizeNoTag + iComputeTagSize + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 42:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeBoolListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(i8);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i5 += iComputeUInt32SizeNoTag + iComputeTagSize + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 43:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(i8);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i5 += iComputeUInt32SizeNoTag + iComputeTagSize + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 44:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(i8);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i5 += iComputeUInt32SizeNoTag + iComputeTagSize + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 45:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(i8);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i5 += iComputeUInt32SizeNoTag + iComputeTagSize + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 46:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(i8);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i5 += iComputeUInt32SizeNoTag + iComputeTagSize + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 47:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(i8);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i5 += iComputeUInt32SizeNoTag + iComputeTagSize + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 48:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(i8);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i5 += iComputeUInt32SizeNoTag + iComputeTagSize + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 49:
                    iComputeBoolSize = SchemaUtil.computeSizeGroupList(i8, (List) unsafe.getObject(t, jOffset), getMessageFieldSchema(i4));
                    i5 += iComputeBoolSize;
                    break;
                case 50:
                    iComputeBoolSize = this.mapFieldSchema.getSerializedSize(i8, unsafe.getObject(t, jOffset), getMapFieldDefaultEntry(i4));
                    i5 += iComputeBoolSize;
                    break;
                case 51:
                    if (isOneofPresent(t, i8, i4)) {
                        iComputeBoolSize = CodedOutputStream.computeDoubleSize(i8, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        i5 += iComputeBoolSize;
                    }
                    break;
                case 52:
                    if (isOneofPresent(t, i8, i4)) {
                        iComputeBoolSize = CodedOutputStream.computeFloatSize(i8, 0.0f);
                        i5 += iComputeBoolSize;
                    }
                    break;
                case 53:
                    if (isOneofPresent(t, i8, i4)) {
                        iComputeBoolSize = CodedOutputStream.computeInt64Size(i8, oneofLongAt(t, jOffset));
                        i5 += iComputeBoolSize;
                    }
                    break;
                case 54:
                    if (isOneofPresent(t, i8, i4)) {
                        iComputeBoolSize = CodedOutputStream.computeUInt64Size(i8, oneofLongAt(t, jOffset));
                        i5 += iComputeBoolSize;
                    }
                    break;
                case 55:
                    if (isOneofPresent(t, i8, i4)) {
                        iComputeBoolSize = CodedOutputStream.computeInt32Size(i8, oneofIntAt(t, jOffset));
                        i5 += iComputeBoolSize;
                    }
                    break;
                case 56:
                    if (isOneofPresent(t, i8, i4)) {
                        iComputeBoolSize = CodedOutputStream.computeFixed64Size(i8, 0L);
                        i5 += iComputeBoolSize;
                    }
                    break;
                case 57:
                    if (isOneofPresent(t, i8, i4)) {
                        iComputeBoolSize = CodedOutputStream.computeFixed32Size(i8, 0);
                        i5 += iComputeBoolSize;
                    }
                    break;
                case 58:
                    if (isOneofPresent(t, i8, i4)) {
                        iComputeBoolSize = CodedOutputStream.computeBoolSize(i8, true);
                        i5 += iComputeBoolSize;
                    }
                    break;
                case 59:
                    if (isOneofPresent(t, i8, i4)) {
                        Object object2 = unsafe.getObject(t, jOffset);
                        iComputeBoolSize = object2 instanceof ByteString ? CodedOutputStream.computeBytesSize(i8, (ByteString) object2) : CodedOutputStream.computeStringSize(i8, (String) object2);
                        i5 += iComputeBoolSize;
                    }
                    break;
                case 60:
                    if (isOneofPresent(t, i8, i4)) {
                        iComputeBoolSize = SchemaUtil.computeSizeMessage(i8, unsafe.getObject(t, jOffset), getMessageFieldSchema(i4));
                        i5 += iComputeBoolSize;
                    }
                    break;
                case 61:
                    if (isOneofPresent(t, i8, i4)) {
                        iComputeBoolSize = CodedOutputStream.computeBytesSize(i8, (ByteString) unsafe.getObject(t, jOffset));
                        i5 += iComputeBoolSize;
                    }
                    break;
                case 62:
                    if (isOneofPresent(t, i8, i4)) {
                        iComputeBoolSize = CodedOutputStream.computeUInt32Size(i8, oneofIntAt(t, jOffset));
                        i5 += iComputeBoolSize;
                    }
                    break;
                case 63:
                    if (isOneofPresent(t, i8, i4)) {
                        iComputeBoolSize = CodedOutputStream.computeEnumSize(i8, oneofIntAt(t, jOffset));
                        i5 += iComputeBoolSize;
                    }
                    break;
                case 64:
                    if (isOneofPresent(t, i8, i4)) {
                        iComputeBoolSize = CodedOutputStream.computeSFixed32Size(i8, 0);
                        i5 += iComputeBoolSize;
                    }
                    break;
                case 65:
                    if (isOneofPresent(t, i8, i4)) {
                        iComputeBoolSize = CodedOutputStream.computeSFixed64Size(i8, 0L);
                        i5 += iComputeBoolSize;
                    }
                    break;
                case 66:
                    if (isOneofPresent(t, i8, i4)) {
                        iComputeBoolSize = CodedOutputStream.computeSInt32Size(i8, oneofIntAt(t, jOffset));
                        i5 += iComputeBoolSize;
                    }
                    break;
                case 67:
                    if (isOneofPresent(t, i8, i4)) {
                        iComputeBoolSize = CodedOutputStream.computeSInt64Size(i8, oneofLongAt(t, jOffset));
                        i5 += iComputeBoolSize;
                    }
                    break;
                case 68:
                    if (isOneofPresent(t, i8, i4)) {
                        iComputeBoolSize = CodedOutputStream.computeGroupSize(i8, (MessageLite) unsafe.getObject(t, jOffset), getMessageFieldSchema(i4));
                        i5 += iComputeBoolSize;
                    }
                    break;
            }
            i4 += 3;
            i3 = 1048575;
            i6 = i11;
            i7 = i10;
        }
        UnknownFieldSchema<?, ?> unknownFieldSchema = this.unknownFieldSchema;
        int serializedSize = unknownFieldSchema.getSerializedSize(unknownFieldSchema.getFromMessage(t)) + i5;
        return this.hasExtensions ? serializedSize + this.extensionSchema.getExtensions(t).getSerializedSize() : serializedSize;
    }

    private int getSerializedSizeProto3(T t) {
        int iComputeDoubleSize;
        int iComputeSizeFixed64ListNoTag;
        int iComputeTagSize;
        int iComputeUInt32SizeNoTag;
        Unsafe unsafe = UNSAFE;
        int i = 0;
        for (int i2 = 0; i2 < this.buffer.length; i2 += 3) {
            int iTypeAndOffsetAt = typeAndOffsetAt(i2);
            int iType = type(iTypeAndOffsetAt);
            int i3 = this.buffer[i2];
            long jOffset = offset(iTypeAndOffsetAt);
            int i4 = (iType < FieldType.DOUBLE_LIST_PACKED.id() || iType > FieldType.SINT64_LIST_PACKED.id()) ? 0 : this.buffer[i2 + 2] & 1048575;
            switch (iType) {
                case 0:
                    if (isFieldPresent(t, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeDoubleSize(i3, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 1:
                    if (isFieldPresent(t, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeFloatSize(i3, 0.0f);
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 2:
                    if (isFieldPresent(t, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeInt64Size(i3, UnsafeUtil.getLong(t, jOffset));
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 3:
                    if (isFieldPresent(t, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeUInt64Size(i3, UnsafeUtil.getLong(t, jOffset));
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 4:
                    if (isFieldPresent(t, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeInt32Size(i3, UnsafeUtil.getInt(t, jOffset));
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 5:
                    if (isFieldPresent(t, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeFixed64Size(i3, 0L);
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 6:
                    if (isFieldPresent(t, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeFixed32Size(i3, 0);
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 7:
                    if (isFieldPresent(t, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeBoolSize(i3, true);
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 8:
                    if (isFieldPresent(t, i2)) {
                        Object object = UnsafeUtil.getObject(t, jOffset);
                        iComputeDoubleSize = object instanceof ByteString ? CodedOutputStream.computeBytesSize(i3, (ByteString) object) : CodedOutputStream.computeStringSize(i3, (String) object);
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 9:
                    if (isFieldPresent(t, i2)) {
                        iComputeDoubleSize = SchemaUtil.computeSizeMessage(i3, UnsafeUtil.getObject(t, jOffset), getMessageFieldSchema(i2));
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 10:
                    if (isFieldPresent(t, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeBytesSize(i3, (ByteString) UnsafeUtil.getObject(t, jOffset));
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 11:
                    if (isFieldPresent(t, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeUInt32Size(i3, UnsafeUtil.getInt(t, jOffset));
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 12:
                    if (isFieldPresent(t, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeEnumSize(i3, UnsafeUtil.getInt(t, jOffset));
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 13:
                    if (isFieldPresent(t, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeSFixed32Size(i3, 0);
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 14:
                    if (isFieldPresent(t, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeSFixed64Size(i3, 0L);
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 15:
                    if (isFieldPresent(t, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeSInt32Size(i3, UnsafeUtil.getInt(t, jOffset));
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 16:
                    if (isFieldPresent(t, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeSInt64Size(i3, UnsafeUtil.getLong(t, jOffset));
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 17:
                    if (isFieldPresent(t, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeGroupSize(i3, (MessageLite) UnsafeUtil.getObject(t, jOffset), getMessageFieldSchema(i2));
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 18:
                    iComputeDoubleSize = SchemaUtil.computeSizeFixed64List(i3, listAt(t, jOffset), false);
                    i = iComputeDoubleSize + i;
                    break;
                case 19:
                    iComputeDoubleSize = SchemaUtil.computeSizeFixed32List(i3, listAt(t, jOffset), false);
                    i = iComputeDoubleSize + i;
                    break;
                case 20:
                    iComputeDoubleSize = SchemaUtil.computeSizeInt64List(i3, listAt(t, jOffset), false);
                    i = iComputeDoubleSize + i;
                    break;
                case 21:
                    iComputeDoubleSize = SchemaUtil.computeSizeUInt64List(i3, listAt(t, jOffset), false);
                    i = iComputeDoubleSize + i;
                    break;
                case 22:
                    iComputeDoubleSize = SchemaUtil.computeSizeInt32List(i3, listAt(t, jOffset), false);
                    i = iComputeDoubleSize + i;
                    break;
                case 23:
                    iComputeDoubleSize = SchemaUtil.computeSizeFixed64List(i3, listAt(t, jOffset), false);
                    i = iComputeDoubleSize + i;
                    break;
                case 24:
                    iComputeDoubleSize = SchemaUtil.computeSizeFixed32List(i3, listAt(t, jOffset), false);
                    i = iComputeDoubleSize + i;
                    break;
                case 25:
                    iComputeDoubleSize = SchemaUtil.computeSizeBoolList(i3, listAt(t, jOffset), false);
                    i = iComputeDoubleSize + i;
                    break;
                case 26:
                    iComputeDoubleSize = SchemaUtil.computeSizeStringList(i3, listAt(t, jOffset));
                    i = iComputeDoubleSize + i;
                    break;
                case 27:
                    iComputeDoubleSize = SchemaUtil.computeSizeMessageList(i3, listAt(t, jOffset), getMessageFieldSchema(i2));
                    i = iComputeDoubleSize + i;
                    break;
                case 28:
                    iComputeDoubleSize = SchemaUtil.computeSizeByteStringList(i3, listAt(t, jOffset));
                    i = iComputeDoubleSize + i;
                    break;
                case 29:
                    iComputeDoubleSize = SchemaUtil.computeSizeUInt32List(i3, listAt(t, jOffset), false);
                    i = iComputeDoubleSize + i;
                    break;
                case 30:
                    iComputeDoubleSize = SchemaUtil.computeSizeEnumList(i3, listAt(t, jOffset), false);
                    i = iComputeDoubleSize + i;
                    break;
                case 31:
                    iComputeDoubleSize = SchemaUtil.computeSizeFixed32List(i3, listAt(t, jOffset), false);
                    i = iComputeDoubleSize + i;
                    break;
                case 32:
                    iComputeDoubleSize = SchemaUtil.computeSizeFixed64List(i3, listAt(t, jOffset), false);
                    i = iComputeDoubleSize + i;
                    break;
                case 33:
                    iComputeDoubleSize = SchemaUtil.computeSizeSInt32List(i3, listAt(t, jOffset), false);
                    i = iComputeDoubleSize + i;
                    break;
                case 34:
                    iComputeDoubleSize = SchemaUtil.computeSizeSInt64List(i3, listAt(t, jOffset), false);
                    i = iComputeDoubleSize + i;
                    break;
                case 35:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i4, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(i3);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i = iComputeUInt32SizeNoTag + iComputeTagSize + iComputeSizeFixed64ListNoTag + i;
                    }
                    break;
                case 36:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i4, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(i3);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i = iComputeUInt32SizeNoTag + iComputeTagSize + iComputeSizeFixed64ListNoTag + i;
                    }
                    break;
                case 37:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i4, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(i3);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i = iComputeUInt32SizeNoTag + iComputeTagSize + iComputeSizeFixed64ListNoTag + i;
                    }
                    break;
                case 38:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i4, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(i3);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i = iComputeUInt32SizeNoTag + iComputeTagSize + iComputeSizeFixed64ListNoTag + i;
                    }
                    break;
                case 39:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i4, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(i3);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i = iComputeUInt32SizeNoTag + iComputeTagSize + iComputeSizeFixed64ListNoTag + i;
                    }
                    break;
                case 40:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i4, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(i3);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i = iComputeUInt32SizeNoTag + iComputeTagSize + iComputeSizeFixed64ListNoTag + i;
                    }
                    break;
                case 41:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i4, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(i3);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i = iComputeUInt32SizeNoTag + iComputeTagSize + iComputeSizeFixed64ListNoTag + i;
                    }
                    break;
                case 42:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeBoolListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i4, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(i3);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i = iComputeUInt32SizeNoTag + iComputeTagSize + iComputeSizeFixed64ListNoTag + i;
                    }
                    break;
                case 43:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i4, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(i3);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i = iComputeUInt32SizeNoTag + iComputeTagSize + iComputeSizeFixed64ListNoTag + i;
                    }
                    break;
                case 44:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i4, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(i3);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i = iComputeUInt32SizeNoTag + iComputeTagSize + iComputeSizeFixed64ListNoTag + i;
                    }
                    break;
                case 45:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i4, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(i3);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i = iComputeUInt32SizeNoTag + iComputeTagSize + iComputeSizeFixed64ListNoTag + i;
                    }
                    break;
                case 46:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i4, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(i3);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i = iComputeUInt32SizeNoTag + iComputeTagSize + iComputeSizeFixed64ListNoTag + i;
                    }
                    break;
                case 47:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i4, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(i3);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i = iComputeUInt32SizeNoTag + iComputeTagSize + iComputeSizeFixed64ListNoTag + i;
                    }
                    break;
                case 48:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t, i4, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(i3);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i = iComputeUInt32SizeNoTag + iComputeTagSize + iComputeSizeFixed64ListNoTag + i;
                    }
                    break;
                case 49:
                    iComputeDoubleSize = SchemaUtil.computeSizeGroupList(i3, listAt(t, jOffset), getMessageFieldSchema(i2));
                    i = iComputeDoubleSize + i;
                    break;
                case 50:
                    iComputeDoubleSize = this.mapFieldSchema.getSerializedSize(i3, UnsafeUtil.getObject(t, jOffset), getMapFieldDefaultEntry(i2));
                    i = iComputeDoubleSize + i;
                    break;
                case 51:
                    if (isOneofPresent(t, i3, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeDoubleSize(i3, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 52:
                    if (isOneofPresent(t, i3, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeFloatSize(i3, 0.0f);
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 53:
                    if (isOneofPresent(t, i3, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeInt64Size(i3, oneofLongAt(t, jOffset));
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 54:
                    if (isOneofPresent(t, i3, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeUInt64Size(i3, oneofLongAt(t, jOffset));
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 55:
                    if (isOneofPresent(t, i3, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeInt32Size(i3, oneofIntAt(t, jOffset));
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 56:
                    if (isOneofPresent(t, i3, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeFixed64Size(i3, 0L);
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 57:
                    if (isOneofPresent(t, i3, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeFixed32Size(i3, 0);
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 58:
                    if (isOneofPresent(t, i3, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeBoolSize(i3, true);
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 59:
                    if (isOneofPresent(t, i3, i2)) {
                        Object object2 = UnsafeUtil.getObject(t, jOffset);
                        iComputeDoubleSize = object2 instanceof ByteString ? CodedOutputStream.computeBytesSize(i3, (ByteString) object2) : CodedOutputStream.computeStringSize(i3, (String) object2);
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 60:
                    if (isOneofPresent(t, i3, i2)) {
                        iComputeDoubleSize = SchemaUtil.computeSizeMessage(i3, UnsafeUtil.getObject(t, jOffset), getMessageFieldSchema(i2));
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 61:
                    if (isOneofPresent(t, i3, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeBytesSize(i3, (ByteString) UnsafeUtil.getObject(t, jOffset));
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 62:
                    if (isOneofPresent(t, i3, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeUInt32Size(i3, oneofIntAt(t, jOffset));
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 63:
                    if (isOneofPresent(t, i3, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeEnumSize(i3, oneofIntAt(t, jOffset));
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 64:
                    if (isOneofPresent(t, i3, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeSFixed32Size(i3, 0);
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 65:
                    if (isOneofPresent(t, i3, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeSFixed64Size(i3, 0L);
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 66:
                    if (isOneofPresent(t, i3, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeSInt32Size(i3, oneofIntAt(t, jOffset));
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 67:
                    if (isOneofPresent(t, i3, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeSInt64Size(i3, oneofLongAt(t, jOffset));
                        i = iComputeDoubleSize + i;
                    }
                    break;
                case 68:
                    if (isOneofPresent(t, i3, i2)) {
                        iComputeDoubleSize = CodedOutputStream.computeGroupSize(i3, (MessageLite) UnsafeUtil.getObject(t, jOffset), getMessageFieldSchema(i2));
                        i = iComputeDoubleSize + i;
                    }
                    break;
            }
        }
        UnknownFieldSchema<?, ?> unknownFieldSchema = this.unknownFieldSchema;
        return unknownFieldSchema.getSerializedSize(unknownFieldSchema.getFromMessage(t)) + i;
    }

    private <UT, UB> int getUnknownFieldsSerializedSize(UnknownFieldSchema<UT, UB> unknownFieldSchema, T t) {
        return unknownFieldSchema.getSerializedSize(unknownFieldSchema.getFromMessage(t));
    }

    public static <T> int intAt(T t, long j) {
        return UnsafeUtil.getInt(t, j);
    }

    public static boolean isEnforceUtf8(int i) {
        return (i & ENFORCE_UTF8_MASK) != 0;
    }

    private boolean isFieldPresent(T t, int i) {
        int[] iArr = this.buffer;
        int i2 = iArr[i + 2];
        long j = 1048575 & i2;
        if (j != 1048575) {
            return (UnsafeUtil.getInt(t, j) & (1 << (i2 >>> 20))) != 0;
        }
        int i3 = iArr[i + 1];
        long jOffset = offset(i3);
        switch (type(i3)) {
            case 0:
                return UnsafeUtil.getDouble(t, jOffset) != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            case 1:
                return UnsafeUtil.getFloat(t, jOffset) != 0.0f;
            case 2:
                return UnsafeUtil.getLong(t, jOffset) != 0;
            case 3:
                return UnsafeUtil.getLong(t, jOffset) != 0;
            case 4:
                return UnsafeUtil.getInt(t, jOffset) != 0;
            case 5:
                return UnsafeUtil.getLong(t, jOffset) != 0;
            case 6:
                return UnsafeUtil.getInt(t, jOffset) != 0;
            case 7:
                return UnsafeUtil.getBoolean(t, jOffset);
            case 8:
                Object object = UnsafeUtil.getObject(t, jOffset);
                if (object instanceof String) {
                    return !((String) object).isEmpty();
                }
                if (object instanceof ByteString) {
                    return !ByteString.EMPTY.equals(object);
                }
                throw new IllegalArgumentException();
            case 9:
                return UnsafeUtil.getObject(t, jOffset) != null;
            case 10:
                return !ByteString.EMPTY.equals(UnsafeUtil.getObject(t, jOffset));
            case 11:
                return UnsafeUtil.getInt(t, jOffset) != 0;
            case 12:
                return UnsafeUtil.getInt(t, jOffset) != 0;
            case 13:
                return UnsafeUtil.getInt(t, jOffset) != 0;
            case 14:
                return UnsafeUtil.getLong(t, jOffset) != 0;
            case 15:
                return UnsafeUtil.getInt(t, jOffset) != 0;
            case 16:
                return UnsafeUtil.getLong(t, jOffset) != 0;
            case 17:
                return UnsafeUtil.getObject(t, jOffset) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <N> boolean isListInitialized(Object obj, int i, int i2) {
        List list = (List) UnsafeUtil.getObject(obj, offset(i));
        if (list.isEmpty()) {
            return true;
        }
        Schema messageFieldSchema = getMessageFieldSchema(i2);
        for (int i3 = 0; i3 < list.size(); i3++) {
            if (!messageFieldSchema.isInitialized(list.get(i3))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9, types: [com.google.protobuf.Schema] */
    private boolean isMapInitialized(T t, int i, int i2) {
        Map<?, ?> mapForMapData = this.mapFieldSchema.forMapData(UnsafeUtil.getObject(t, offset(i)));
        if (mapForMapData.isEmpty()) {
            return true;
        }
        if (this.mapFieldSchema.forMapMetadata(this.objects[(i2 / 3) * 2]).valueType.getJavaType() != WireFormat.JavaType.MESSAGE) {
            return true;
        }
        ?? SchemaFor = 0;
        for (Object obj : mapForMapData.values()) {
            if (SchemaFor == 0) {
                SchemaFor = SchemaFor;
                SchemaFor = Protobuf.INSTANCE.schemaFor((Class) obj.getClass());
            }
            SchemaFor = SchemaFor;
            if (!SchemaFor.isInitialized(obj)) {
                return false;
            }
        }
        return true;
    }

    private boolean isOneofCaseEqual(T t, T t2, int i) {
        long j = this.buffer[i + 2] & 1048575;
        return UnsafeUtil.getInt(t, j) == UnsafeUtil.getInt(t2, j);
    }

    private boolean isOneofPresent(T t, int i, int i2) {
        return UnsafeUtil.getInt(t, (long) (this.buffer[i2 + 2] & 1048575)) == i;
    }

    public static boolean isRequired(int i) {
        return (i & REQUIRED_MASK) != 0;
    }

    public static List<?> listAt(Object obj, long j) {
        return (List) UnsafeUtil.getObject(obj, j);
    }

    public static <T> long longAt(T t, long j) {
        return UnsafeUtil.getLong(t, j);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <UT, UB, ET extends FieldSet.FieldDescriptorLite<ET>> void mergeFromHelper(UnknownFieldSchema<UT, UB> unknownFieldSchema, ExtensionSchema<ET> extensionSchema, T t, Reader reader, ExtensionRegistryLite extensionRegistryLite) {
        Object objFilterMapUnknownEnumValues = null;
        Object mutableExtensions = null;
        while (true) {
            try {
                int fieldNumber = reader.getFieldNumber();
                int iPositionForFieldNumber = positionForFieldNumber(fieldNumber);
                if (iPositionForFieldNumber >= 0) {
                    int iTypeAndOffsetAt = typeAndOffsetAt(iPositionForFieldNumber);
                    try {
                        switch (type(iTypeAndOffsetAt)) {
                            case 0:
                                UnsafeUtil.putDouble(t, offset(iTypeAndOffsetAt), reader.readDouble());
                                setFieldPresent(t, iPositionForFieldNumber);
                                break;
                            case 1:
                                UnsafeUtil.putFloat(t, offset(iTypeAndOffsetAt), reader.readFloat());
                                setFieldPresent(t, iPositionForFieldNumber);
                                break;
                            case 2:
                                UnsafeUtil.putLong(t, offset(iTypeAndOffsetAt), reader.readInt64());
                                setFieldPresent(t, iPositionForFieldNumber);
                                break;
                            case 3:
                                UnsafeUtil.putLong(t, offset(iTypeAndOffsetAt), reader.readUInt64());
                                setFieldPresent(t, iPositionForFieldNumber);
                                break;
                            case 4:
                                UnsafeUtil.putInt(t, offset(iTypeAndOffsetAt), reader.readInt32());
                                setFieldPresent(t, iPositionForFieldNumber);
                                break;
                            case 5:
                                UnsafeUtil.putLong(t, offset(iTypeAndOffsetAt), reader.readFixed64());
                                setFieldPresent(t, iPositionForFieldNumber);
                                break;
                            case 6:
                                UnsafeUtil.putInt(t, offset(iTypeAndOffsetAt), reader.readFixed32());
                                setFieldPresent(t, iPositionForFieldNumber);
                                break;
                            case 7:
                                UnsafeUtil.putBoolean(t, offset(iTypeAndOffsetAt), reader.readBool());
                                setFieldPresent(t, iPositionForFieldNumber);
                                break;
                            case 8:
                                readString(t, iTypeAndOffsetAt, reader);
                                setFieldPresent(t, iPositionForFieldNumber);
                                break;
                            case 9:
                                if (isFieldPresent(t, iPositionForFieldNumber)) {
                                    UnsafeUtil.putObject(t, offset(iTypeAndOffsetAt), Internal.mergeMessage(UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), reader.readMessageBySchemaWithCheck(getMessageFieldSchema(iPositionForFieldNumber), extensionRegistryLite)));
                                } else {
                                    UnsafeUtil.putObject(t, offset(iTypeAndOffsetAt), reader.readMessageBySchemaWithCheck(getMessageFieldSchema(iPositionForFieldNumber), extensionRegistryLite));
                                    setFieldPresent(t, iPositionForFieldNumber);
                                }
                                break;
                            case 10:
                                UnsafeUtil.putObject(t, offset(iTypeAndOffsetAt), reader.readBytes());
                                setFieldPresent(t, iPositionForFieldNumber);
                                break;
                            case 11:
                                UnsafeUtil.putInt(t, offset(iTypeAndOffsetAt), reader.readUInt32());
                                setFieldPresent(t, iPositionForFieldNumber);
                                break;
                            case 12:
                                int i = reader.readEnum();
                                Internal.EnumVerifier enumFieldVerifier = getEnumFieldVerifier(iPositionForFieldNumber);
                                if (enumFieldVerifier == null || enumFieldVerifier.isInRange(i)) {
                                    UnsafeUtil.putInt(t, offset(iTypeAndOffsetAt), i);
                                    setFieldPresent(t, iPositionForFieldNumber);
                                } else {
                                    objFilterMapUnknownEnumValues = SchemaUtil.storeUnknownEnum(fieldNumber, i, objFilterMapUnknownEnumValues, unknownFieldSchema);
                                }
                                break;
                            case 13:
                                UnsafeUtil.putInt(t, offset(iTypeAndOffsetAt), reader.readSFixed32());
                                setFieldPresent(t, iPositionForFieldNumber);
                                break;
                            case 14:
                                UnsafeUtil.putLong(t, offset(iTypeAndOffsetAt), reader.readSFixed64());
                                setFieldPresent(t, iPositionForFieldNumber);
                                break;
                            case 15:
                                UnsafeUtil.putInt(t, offset(iTypeAndOffsetAt), reader.readSInt32());
                                setFieldPresent(t, iPositionForFieldNumber);
                                break;
                            case 16:
                                UnsafeUtil.putLong(t, offset(iTypeAndOffsetAt), reader.readSInt64());
                                setFieldPresent(t, iPositionForFieldNumber);
                                break;
                            case 17:
                                if (isFieldPresent(t, iPositionForFieldNumber)) {
                                    UnsafeUtil.putObject(t, offset(iTypeAndOffsetAt), Internal.mergeMessage(UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), reader.readGroupBySchemaWithCheck(getMessageFieldSchema(iPositionForFieldNumber), extensionRegistryLite)));
                                } else {
                                    UnsafeUtil.putObject(t, offset(iTypeAndOffsetAt), reader.readGroupBySchemaWithCheck(getMessageFieldSchema(iPositionForFieldNumber), extensionRegistryLite));
                                    setFieldPresent(t, iPositionForFieldNumber);
                                }
                                break;
                            case 18:
                                reader.readDoubleList(this.listFieldSchema.mutableListAt(t, offset(iTypeAndOffsetAt)));
                                break;
                            case 19:
                                reader.readFloatList(this.listFieldSchema.mutableListAt(t, offset(iTypeAndOffsetAt)));
                                break;
                            case 20:
                                reader.readInt64List(this.listFieldSchema.mutableListAt(t, offset(iTypeAndOffsetAt)));
                                break;
                            case 21:
                                reader.readUInt64List(this.listFieldSchema.mutableListAt(t, offset(iTypeAndOffsetAt)));
                                break;
                            case 22:
                                reader.readInt32List(this.listFieldSchema.mutableListAt(t, offset(iTypeAndOffsetAt)));
                                break;
                            case 23:
                                reader.readFixed64List(this.listFieldSchema.mutableListAt(t, offset(iTypeAndOffsetAt)));
                                break;
                            case 24:
                                reader.readFixed32List(this.listFieldSchema.mutableListAt(t, offset(iTypeAndOffsetAt)));
                                break;
                            case 25:
                                reader.readBoolList(this.listFieldSchema.mutableListAt(t, offset(iTypeAndOffsetAt)));
                                break;
                            case 26:
                                readStringList(t, iTypeAndOffsetAt, reader);
                                break;
                            case 27:
                                readMessageList(t, iTypeAndOffsetAt, reader, getMessageFieldSchema(iPositionForFieldNumber), extensionRegistryLite);
                                break;
                            case 28:
                                reader.readBytesList(this.listFieldSchema.mutableListAt(t, offset(iTypeAndOffsetAt)));
                                break;
                            case 29:
                                reader.readUInt32List(this.listFieldSchema.mutableListAt(t, offset(iTypeAndOffsetAt)));
                                break;
                            case 30:
                                List<Integer> listMutableListAt = this.listFieldSchema.mutableListAt(t, offset(iTypeAndOffsetAt));
                                reader.readEnumList(listMutableListAt);
                                objFilterMapUnknownEnumValues = SchemaUtil.filterUnknownEnumList(fieldNumber, listMutableListAt, getEnumFieldVerifier(iPositionForFieldNumber), objFilterMapUnknownEnumValues, unknownFieldSchema);
                                break;
                            case 31:
                                reader.readSFixed32List(this.listFieldSchema.mutableListAt(t, offset(iTypeAndOffsetAt)));
                                break;
                            case 32:
                                reader.readSFixed64List(this.listFieldSchema.mutableListAt(t, offset(iTypeAndOffsetAt)));
                                break;
                            case 33:
                                reader.readSInt32List(this.listFieldSchema.mutableListAt(t, offset(iTypeAndOffsetAt)));
                                break;
                            case 34:
                                reader.readSInt64List(this.listFieldSchema.mutableListAt(t, offset(iTypeAndOffsetAt)));
                                break;
                            case 35:
                                reader.readDoubleList(this.listFieldSchema.mutableListAt(t, offset(iTypeAndOffsetAt)));
                                break;
                            case 36:
                                reader.readFloatList(this.listFieldSchema.mutableListAt(t, offset(iTypeAndOffsetAt)));
                                break;
                            case 37:
                                reader.readInt64List(this.listFieldSchema.mutableListAt(t, offset(iTypeAndOffsetAt)));
                                break;
                            case 38:
                                reader.readUInt64List(this.listFieldSchema.mutableListAt(t, offset(iTypeAndOffsetAt)));
                                break;
                            case 39:
                                reader.readInt32List(this.listFieldSchema.mutableListAt(t, offset(iTypeAndOffsetAt)));
                                break;
                            case 40:
                                reader.readFixed64List(this.listFieldSchema.mutableListAt(t, offset(iTypeAndOffsetAt)));
                                break;
                            case 41:
                                reader.readFixed32List(this.listFieldSchema.mutableListAt(t, offset(iTypeAndOffsetAt)));
                                break;
                            case 42:
                                reader.readBoolList(this.listFieldSchema.mutableListAt(t, offset(iTypeAndOffsetAt)));
                                break;
                            case 43:
                                reader.readUInt32List(this.listFieldSchema.mutableListAt(t, offset(iTypeAndOffsetAt)));
                                break;
                            case 44:
                                List<Integer> listMutableListAt2 = this.listFieldSchema.mutableListAt(t, offset(iTypeAndOffsetAt));
                                reader.readEnumList(listMutableListAt2);
                                objFilterMapUnknownEnumValues = SchemaUtil.filterUnknownEnumList(fieldNumber, listMutableListAt2, getEnumFieldVerifier(iPositionForFieldNumber), objFilterMapUnknownEnumValues, unknownFieldSchema);
                                break;
                            case 45:
                                reader.readSFixed32List(this.listFieldSchema.mutableListAt(t, offset(iTypeAndOffsetAt)));
                                break;
                            case 46:
                                reader.readSFixed64List(this.listFieldSchema.mutableListAt(t, offset(iTypeAndOffsetAt)));
                                break;
                            case 47:
                                reader.readSInt32List(this.listFieldSchema.mutableListAt(t, offset(iTypeAndOffsetAt)));
                                break;
                            case 48:
                                reader.readSInt64List(this.listFieldSchema.mutableListAt(t, offset(iTypeAndOffsetAt)));
                                break;
                            case 49:
                                readGroupList(t, offset(iTypeAndOffsetAt), reader, getMessageFieldSchema(iPositionForFieldNumber), extensionRegistryLite);
                                break;
                            case 50:
                                mergeMap(t, iPositionForFieldNumber, getMapFieldDefaultEntry(iPositionForFieldNumber), extensionRegistryLite, reader);
                                break;
                            case 51:
                                UnsafeUtil.putObject(t, offset(iTypeAndOffsetAt), Double.valueOf(reader.readDouble()));
                                setOneofPresent(t, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 52:
                                UnsafeUtil.putObject(t, offset(iTypeAndOffsetAt), Float.valueOf(reader.readFloat()));
                                setOneofPresent(t, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 53:
                                UnsafeUtil.putObject(t, offset(iTypeAndOffsetAt), Long.valueOf(reader.readInt64()));
                                setOneofPresent(t, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 54:
                                UnsafeUtil.putObject(t, offset(iTypeAndOffsetAt), Long.valueOf(reader.readUInt64()));
                                setOneofPresent(t, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 55:
                                UnsafeUtil.putObject(t, offset(iTypeAndOffsetAt), Integer.valueOf(reader.readInt32()));
                                setOneofPresent(t, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 56:
                                UnsafeUtil.putObject(t, offset(iTypeAndOffsetAt), Long.valueOf(reader.readFixed64()));
                                setOneofPresent(t, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 57:
                                UnsafeUtil.putObject(t, offset(iTypeAndOffsetAt), Integer.valueOf(reader.readFixed32()));
                                setOneofPresent(t, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 58:
                                UnsafeUtil.putObject(t, offset(iTypeAndOffsetAt), Boolean.valueOf(reader.readBool()));
                                setOneofPresent(t, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 59:
                                readString(t, iTypeAndOffsetAt, reader);
                                setOneofPresent(t, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 60:
                                if (isOneofPresent(t, fieldNumber, iPositionForFieldNumber)) {
                                    UnsafeUtil.putObject(t, offset(iTypeAndOffsetAt), Internal.mergeMessage(UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), reader.readMessageBySchemaWithCheck(getMessageFieldSchema(iPositionForFieldNumber), extensionRegistryLite)));
                                } else {
                                    UnsafeUtil.putObject(t, offset(iTypeAndOffsetAt), reader.readMessageBySchemaWithCheck(getMessageFieldSchema(iPositionForFieldNumber), extensionRegistryLite));
                                    setFieldPresent(t, iPositionForFieldNumber);
                                }
                                setOneofPresent(t, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 61:
                                UnsafeUtil.putObject(t, offset(iTypeAndOffsetAt), reader.readBytes());
                                setOneofPresent(t, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 62:
                                UnsafeUtil.putObject(t, offset(iTypeAndOffsetAt), Integer.valueOf(reader.readUInt32()));
                                setOneofPresent(t, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 63:
                                int i2 = reader.readEnum();
                                Internal.EnumVerifier enumFieldVerifier2 = getEnumFieldVerifier(iPositionForFieldNumber);
                                if (enumFieldVerifier2 == null || enumFieldVerifier2.isInRange(i2)) {
                                    UnsafeUtil.putObject(t, offset(iTypeAndOffsetAt), Integer.valueOf(i2));
                                    setOneofPresent(t, fieldNumber, iPositionForFieldNumber);
                                } else {
                                    objFilterMapUnknownEnumValues = SchemaUtil.storeUnknownEnum(fieldNumber, i2, objFilterMapUnknownEnumValues, unknownFieldSchema);
                                }
                                break;
                            case 64:
                                UnsafeUtil.putObject(t, offset(iTypeAndOffsetAt), Integer.valueOf(reader.readSFixed32()));
                                setOneofPresent(t, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 65:
                                UnsafeUtil.putObject(t, offset(iTypeAndOffsetAt), Long.valueOf(reader.readSFixed64()));
                                setOneofPresent(t, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 66:
                                UnsafeUtil.putObject(t, offset(iTypeAndOffsetAt), Integer.valueOf(reader.readSInt32()));
                                setOneofPresent(t, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 67:
                                UnsafeUtil.putObject(t, offset(iTypeAndOffsetAt), Long.valueOf(reader.readSInt64()));
                                setOneofPresent(t, fieldNumber, iPositionForFieldNumber);
                                break;
                            case 68:
                                UnsafeUtil.putObject(t, offset(iTypeAndOffsetAt), reader.readGroupBySchemaWithCheck(getMessageFieldSchema(iPositionForFieldNumber), extensionRegistryLite));
                                setOneofPresent(t, fieldNumber, iPositionForFieldNumber);
                                break;
                            default:
                                if (objFilterMapUnknownEnumValues == null) {
                                    objFilterMapUnknownEnumValues = unknownFieldSchema.newBuilder();
                                }
                                if (!unknownFieldSchema.mergeOneFieldFrom(objFilterMapUnknownEnumValues, reader)) {
                                    for (int i3 = this.checkInitializedCount; i3 < this.repeatedFieldOffsetStart; i3++) {
                                        objFilterMapUnknownEnumValues = filterMapUnknownEnumValues(t, this.intArray[i3], objFilterMapUnknownEnumValues, unknownFieldSchema);
                                    }
                                    if (objFilterMapUnknownEnumValues != null) {
                                        unknownFieldSchema.setBuilderToMessage(t, objFilterMapUnknownEnumValues);
                                        return;
                                    }
                                    return;
                                }
                                break;
                                break;
                        }
                    } catch (InvalidProtocolBufferException.InvalidWireTypeException unused) {
                        if (!unknownFieldSchema.shouldDiscardUnknownFields(reader)) {
                            if (objFilterMapUnknownEnumValues == null) {
                                objFilterMapUnknownEnumValues = unknownFieldSchema.getBuilderFromMessage(t);
                            }
                            if (!unknownFieldSchema.mergeOneFieldFrom(objFilterMapUnknownEnumValues, reader)) {
                                for (int i4 = this.checkInitializedCount; i4 < this.repeatedFieldOffsetStart; i4++) {
                                    objFilterMapUnknownEnumValues = filterMapUnknownEnumValues(t, this.intArray[i4], objFilterMapUnknownEnumValues, unknownFieldSchema);
                                }
                                if (objFilterMapUnknownEnumValues != null) {
                                    unknownFieldSchema.setBuilderToMessage(t, objFilterMapUnknownEnumValues);
                                    return;
                                }
                                return;
                            }
                        } else if (!reader.skipField()) {
                            for (int i5 = this.checkInitializedCount; i5 < this.repeatedFieldOffsetStart; i5++) {
                                objFilterMapUnknownEnumValues = filterMapUnknownEnumValues(t, this.intArray[i5], objFilterMapUnknownEnumValues, unknownFieldSchema);
                            }
                            if (objFilterMapUnknownEnumValues != null) {
                                unknownFieldSchema.setBuilderToMessage(t, objFilterMapUnknownEnumValues);
                                return;
                            }
                            return;
                        }
                    }
                } else {
                    if (fieldNumber == Integer.MAX_VALUE) {
                        for (int i6 = this.checkInitializedCount; i6 < this.repeatedFieldOffsetStart; i6++) {
                            objFilterMapUnknownEnumValues = filterMapUnknownEnumValues(t, this.intArray[i6], objFilterMapUnknownEnumValues, unknownFieldSchema);
                        }
                        if (objFilterMapUnknownEnumValues != null) {
                            unknownFieldSchema.setBuilderToMessage(t, objFilterMapUnknownEnumValues);
                            return;
                        }
                        return;
                    }
                    Object objFindExtensionByNumber = !this.hasExtensions ? null : extensionSchema.findExtensionByNumber(extensionRegistryLite, this.defaultInstance, fieldNumber);
                    if (objFindExtensionByNumber != null) {
                        if (mutableExtensions == null) {
                            mutableExtensions = extensionSchema.getMutableExtensions(t);
                        }
                        objFilterMapUnknownEnumValues = extensionSchema.parseExtension(reader, objFindExtensionByNumber, extensionRegistryLite, mutableExtensions, objFilterMapUnknownEnumValues, unknownFieldSchema);
                    } else if (!unknownFieldSchema.shouldDiscardUnknownFields(reader)) {
                        if (objFilterMapUnknownEnumValues == null) {
                            objFilterMapUnknownEnumValues = unknownFieldSchema.getBuilderFromMessage(t);
                        }
                        if (unknownFieldSchema.mergeOneFieldFrom(objFilterMapUnknownEnumValues, reader)) {
                        }
                    } else if (reader.skipField()) {
                    }
                }
            } catch (Throwable th) {
                for (int i7 = this.checkInitializedCount; i7 < this.repeatedFieldOffsetStart; i7++) {
                    objFilterMapUnknownEnumValues = filterMapUnknownEnumValues(t, this.intArray[i7], objFilterMapUnknownEnumValues, unknownFieldSchema);
                }
                if (objFilterMapUnknownEnumValues != null) {
                    unknownFieldSchema.setBuilderToMessage(t, objFilterMapUnknownEnumValues);
                }
                throw th;
            }
        }
        for (int i8 = this.checkInitializedCount; i8 < this.repeatedFieldOffsetStart; i8++) {
            objFilterMapUnknownEnumValues = filterMapUnknownEnumValues(t, this.intArray[i8], objFilterMapUnknownEnumValues, unknownFieldSchema);
        }
        if (objFilterMapUnknownEnumValues != null) {
            unknownFieldSchema.setBuilderToMessage(t, objFilterMapUnknownEnumValues);
        }
    }

    private final <K, V> void mergeMap(Object obj, int i, Object obj2, ExtensionRegistryLite extensionRegistryLite, Reader reader) {
        long jOffset = offset(this.buffer[i + 1]);
        Object object = UnsafeUtil.getObject(obj, jOffset);
        if (object == null) {
            object = this.mapFieldSchema.newMapField(obj2);
            UnsafeUtil.MEMORY_ACCESSOR.putObject(obj, jOffset, object);
        } else if (this.mapFieldSchema.isImmutable(object)) {
            Object objNewMapField = this.mapFieldSchema.newMapField(obj2);
            this.mapFieldSchema.mergeFrom(objNewMapField, object);
            UnsafeUtil.MEMORY_ACCESSOR.putObject(obj, jOffset, objNewMapField);
            object = objNewMapField;
        }
        reader.readMap(this.mapFieldSchema.forMutableMapData(object), this.mapFieldSchema.forMapMetadata(obj2), extensionRegistryLite);
    }

    private void mergeMessage(T t, T t2, int i) {
        long jOffset = offset(this.buffer[i + 1]);
        if (isFieldPresent(t2, i)) {
            Object object = UnsafeUtil.getObject(t, jOffset);
            Object object2 = UnsafeUtil.getObject(t2, jOffset);
            if (object != null && object2 != null) {
                UnsafeUtil.MEMORY_ACCESSOR.putObject(t, jOffset, Internal.mergeMessage(object, object2));
                setFieldPresent(t, i);
            } else if (object2 != null) {
                UnsafeUtil.MEMORY_ACCESSOR.putObject(t, jOffset, object2);
                setFieldPresent(t, i);
            }
        }
    }

    private void mergeOneofMessage(T t, T t2, int i) {
        int[] iArr = this.buffer;
        int i2 = iArr[i + 1];
        int i3 = iArr[i];
        long jOffset = offset(i2);
        if (isOneofPresent(t2, i3, i)) {
            Object object = isOneofPresent(t, i3, i) ? UnsafeUtil.getObject(t, jOffset) : null;
            Object object2 = UnsafeUtil.getObject(t2, jOffset);
            if (object != null && object2 != null) {
                UnsafeUtil.MEMORY_ACCESSOR.putObject(t, jOffset, Internal.mergeMessage(object, object2));
                setOneofPresent(t, i3, i);
            } else if (object2 != null) {
                UnsafeUtil.MEMORY_ACCESSOR.putObject(t, jOffset, object2);
                setOneofPresent(t, i3, i);
            }
        }
    }

    private void mergeSingleField(T t, T t2, int i) {
        int i2 = this.buffer[i + 1];
        long jOffset = offset(i2);
        int i3 = this.buffer[i];
        switch (type(i2)) {
            case 0:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putDouble(t, jOffset, UnsafeUtil.getDouble(t2, jOffset));
                    setFieldPresent(t, i);
                }
                break;
            case 1:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.MEMORY_ACCESSOR.putFloat(t, jOffset, UnsafeUtil.getFloat(t2, jOffset));
                    setFieldPresent(t, i);
                }
                break;
            case 2:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putLong(t, jOffset, UnsafeUtil.getLong(t2, jOffset));
                    setFieldPresent(t, i);
                }
                break;
            case 3:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putLong(t, jOffset, UnsafeUtil.getLong(t2, jOffset));
                    setFieldPresent(t, i);
                }
                break;
            case 4:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.MEMORY_ACCESSOR.putInt(t, jOffset, UnsafeUtil.getInt(t2, jOffset));
                    setFieldPresent(t, i);
                }
                break;
            case 5:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putLong(t, jOffset, UnsafeUtil.getLong(t2, jOffset));
                    setFieldPresent(t, i);
                }
                break;
            case 6:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.MEMORY_ACCESSOR.putInt(t, jOffset, UnsafeUtil.getInt(t2, jOffset));
                    setFieldPresent(t, i);
                }
                break;
            case 7:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.MEMORY_ACCESSOR.putBoolean(t, jOffset, UnsafeUtil.getBoolean(t2, jOffset));
                    setFieldPresent(t, i);
                }
                break;
            case 8:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.MEMORY_ACCESSOR.putObject(t, jOffset, UnsafeUtil.getObject(t2, jOffset));
                    setFieldPresent(t, i);
                }
                break;
            case 9:
                mergeMessage(t, t2, i);
                break;
            case 10:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.MEMORY_ACCESSOR.putObject(t, jOffset, UnsafeUtil.getObject(t2, jOffset));
                    setFieldPresent(t, i);
                }
                break;
            case 11:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.MEMORY_ACCESSOR.putInt(t, jOffset, UnsafeUtil.getInt(t2, jOffset));
                    setFieldPresent(t, i);
                }
                break;
            case 12:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.MEMORY_ACCESSOR.putInt(t, jOffset, UnsafeUtil.getInt(t2, jOffset));
                    setFieldPresent(t, i);
                }
                break;
            case 13:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.MEMORY_ACCESSOR.putInt(t, jOffset, UnsafeUtil.getInt(t2, jOffset));
                    setFieldPresent(t, i);
                }
                break;
            case 14:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putLong(t, jOffset, UnsafeUtil.getLong(t2, jOffset));
                    setFieldPresent(t, i);
                }
                break;
            case 15:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.MEMORY_ACCESSOR.putInt(t, jOffset, UnsafeUtil.getInt(t2, jOffset));
                    setFieldPresent(t, i);
                }
                break;
            case 16:
                if (isFieldPresent(t2, i)) {
                    UnsafeUtil.putLong(t, jOffset, UnsafeUtil.getLong(t2, jOffset));
                    setFieldPresent(t, i);
                }
                break;
            case 17:
                mergeMessage(t, t2, i);
                break;
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                this.listFieldSchema.mergeListsAt(t, t2, jOffset);
                break;
            case 50:
                SchemaUtil.mergeMap(this.mapFieldSchema, t, t2, jOffset);
                break;
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
                if (isOneofPresent(t2, i3, i)) {
                    UnsafeUtil.MEMORY_ACCESSOR.putObject(t, jOffset, UnsafeUtil.getObject(t2, jOffset));
                    setOneofPresent(t, i3, i);
                }
                break;
            case 60:
                mergeOneofMessage(t, t2, i);
                break;
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
                if (isOneofPresent(t2, i3, i)) {
                    UnsafeUtil.MEMORY_ACCESSOR.putObject(t, jOffset, UnsafeUtil.getObject(t2, jOffset));
                    setOneofPresent(t, i3, i);
                }
                break;
            case 68:
                mergeOneofMessage(t, t2, i);
                break;
        }
    }

    public static <T> MessageSchema<T> newSchema(Class<T> cls, MessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        return messageInfo instanceof RawMessageInfo ? newSchemaForRawMessageInfo((RawMessageInfo) messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema) : newSchemaForMessageInfo((StructuralMessageInfo) messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    public static <T> MessageSchema<T> newSchemaForMessageInfo(StructuralMessageInfo structuralMessageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        int fieldNumber;
        int fieldNumber2;
        boolean z = structuralMessageInfo.getSyntax() == ProtoSyntax.PROTO3;
        FieldInfo[] fields = structuralMessageInfo.getFields();
        if (fields.length == 0) {
            fieldNumber = 0;
            fieldNumber2 = 0;
        } else {
            fieldNumber = fields[0].getFieldNumber();
            fieldNumber2 = fields[fields.length - 1].getFieldNumber();
        }
        int length = fields.length;
        int[] iArr = new int[length * 3];
        Object[] objArr = new Object[length * 2];
        int i = 0;
        int i2 = 0;
        for (FieldInfo fieldInfo : fields) {
            if (fieldInfo.getType() == FieldType.MAP) {
                i++;
            } else if (fieldInfo.getType().id() >= 18 && fieldInfo.getType().id() <= 49) {
                i2++;
            }
        }
        int[] iArr2 = i > 0 ? new int[i] : null;
        int[] iArr3 = i2 > 0 ? new int[i2] : null;
        int[] checkInitialized = structuralMessageInfo.getCheckInitialized();
        if (checkInitialized == null) {
            checkInitialized = EMPTY_INT_ARRAY;
        }
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i3 < fields.length) {
            FieldInfo fieldInfo2 = fields[i3];
            int fieldNumber3 = fieldInfo2.getFieldNumber();
            storeFieldData(fieldInfo2, iArr, i4, objArr);
            if (i5 < checkInitialized.length && checkInitialized[i5] == fieldNumber3) {
                checkInitialized[i5] = i4;
                i5++;
            }
            if (fieldInfo2.getType() == FieldType.MAP) {
                iArr2[i6] = i4;
                i6++;
            } else {
                if (fieldInfo2.getType().id() >= 18 && fieldInfo2.getType().id() <= 49) {
                    iArr3[i7] = (int) UnsafeUtil.objectFieldOffset(fieldInfo2.getField());
                    i7++;
                }
                i3++;
                i4 += 3;
            }
            i3++;
            i4 += 3;
        }
        if (iArr2 == null) {
            iArr2 = EMPTY_INT_ARRAY;
        }
        if (iArr3 == null) {
            iArr3 = EMPTY_INT_ARRAY;
        }
        int[] iArr4 = new int[checkInitialized.length + iArr2.length + iArr3.length];
        System.arraycopy(checkInitialized, 0, iArr4, 0, checkInitialized.length);
        System.arraycopy(iArr2, 0, iArr4, checkInitialized.length, iArr2.length);
        System.arraycopy(iArr3, 0, iArr4, checkInitialized.length + iArr2.length, iArr3.length);
        return new MessageSchema<>(iArr, objArr, fieldNumber, fieldNumber2, structuralMessageInfo.getDefaultInstance(), z, true, iArr4, checkInitialized.length, checkInitialized.length + iArr2.length, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    /* JADX WARN: Code duplicated, block: B:158:0x02ff  */
    /* JADX WARN: Code duplicated, block: B:164:0x032c  */
    /* JADX WARN: Code duplicated, block: B:165:0x032f  */
    /* JADX WARN: Code duplicated, block: B:182:0x037d  */
    public static <T> MessageSchema<T> newSchemaForRawMessageInfo(RawMessageInfo rawMessageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        int i;
        int i2;
        int i3;
        int i4;
        int iCharAt;
        int i5;
        int iCharAt2;
        int i6;
        int i7;
        int i8;
        int[] iArr;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        char cCharAt;
        int i15;
        char cCharAt2;
        char cCharAt3;
        char cCharAt4;
        char cCharAt5;
        char cCharAt6;
        char cCharAt7;
        int i16;
        char cCharAt8;
        int i17;
        int i18;
        boolean z;
        int i19;
        int iObjectFieldOffset;
        boolean z2;
        int iObjectFieldOffset2;
        int i20;
        int i21;
        java.lang.reflect.Field fieldReflectField;
        char cCharAt9;
        int i22;
        java.lang.reflect.Field fieldReflectField2;
        java.lang.reflect.Field fieldReflectField3;
        int i23;
        char cCharAt10;
        int i24;
        char cCharAt11;
        int i25;
        char cCharAt12;
        int i26;
        char cCharAt13;
        boolean z3 = rawMessageInfo.getSyntax() == ProtoSyntax.PROTO3;
        String stringInfo = rawMessageInfo.getStringInfo();
        int length = stringInfo.length();
        char c2 = 55296;
        if (stringInfo.charAt(0) >= 55296) {
            int i27 = 1;
            while (true) {
                i = i27 + 1;
                if (stringInfo.charAt(i27) < 55296) {
                    break;
                }
                i27 = i;
            }
        } else {
            i = 1;
        }
        int i28 = i + 1;
        int iCharAt3 = stringInfo.charAt(i);
        if (iCharAt3 >= 55296) {
            int i29 = iCharAt3 & MtkTvScanDvbsBase.SB_DVBS_PID_INVALID_VALUE;
            int i30 = 13;
            while (true) {
                i26 = i28 + 1;
                cCharAt13 = stringInfo.charAt(i28);
                if (cCharAt13 < 55296) {
                    break;
                }
                i29 |= (cCharAt13 & 8191) << i30;
                i30 += 13;
                i28 = i26;
            }
            iCharAt3 = i29 | (cCharAt13 << i30);
            i28 = i26;
        }
        if (iCharAt3 == 0) {
            i10 = 0;
            i13 = 0;
            iCharAt = 0;
            iCharAt2 = 0;
            i12 = 0;
            i8 = i28;
            iArr = EMPTY_INT_ARRAY;
            i11 = 0;
            i9 = 0;
        } else {
            int i31 = i28 + 1;
            int iCharAt4 = stringInfo.charAt(i28);
            if (iCharAt4 >= 55296) {
                int i32 = iCharAt4 & MtkTvScanDvbsBase.SB_DVBS_PID_INVALID_VALUE;
                int i33 = 13;
                while (true) {
                    i16 = i31 + 1;
                    cCharAt8 = stringInfo.charAt(i31);
                    if (cCharAt8 < 55296) {
                        break;
                    }
                    i32 |= (cCharAt8 & 8191) << i33;
                    i33 += 13;
                    i31 = i16;
                }
                iCharAt4 = i32 | (cCharAt8 << i33);
                i31 = i16;
            }
            int i34 = i31 + 1;
            int iCharAt5 = stringInfo.charAt(i31);
            if (iCharAt5 >= 55296) {
                int i35 = iCharAt5 & MtkTvScanDvbsBase.SB_DVBS_PID_INVALID_VALUE;
                int i36 = 13;
                while (true) {
                    i2 = i34 + 1;
                    cCharAt7 = stringInfo.charAt(i34);
                    if (cCharAt7 < 55296) {
                        break;
                    }
                    i35 |= (cCharAt7 & 8191) << i36;
                    i36 += 13;
                    i34 = i2;
                }
                iCharAt5 = i35 | (cCharAt7 << i36);
            } else {
                i2 = i34;
            }
            int i37 = i2 + 1;
            char cCharAt14 = stringInfo.charAt(i2);
            if (cCharAt14 >= 55296) {
                int i38 = cCharAt14 & 8191;
                int i39 = 13;
                while (true) {
                    i3 = i37 + 1;
                    cCharAt6 = stringInfo.charAt(i37);
                    if (cCharAt6 < 55296) {
                        break;
                    }
                    i38 |= (cCharAt6 & 8191) << i39;
                    i39 += 13;
                    i37 = i3;
                }
                i4 = (cCharAt6 << i39) | i38;
            } else {
                i3 = i37;
                i4 = cCharAt14;
            }
            int i40 = i3 + 1;
            iCharAt = stringInfo.charAt(i3);
            if (iCharAt >= 55296) {
                int i41 = iCharAt & MtkTvScanDvbsBase.SB_DVBS_PID_INVALID_VALUE;
                int i42 = 13;
                while (true) {
                    i5 = i40 + 1;
                    cCharAt5 = stringInfo.charAt(i40);
                    if (cCharAt5 < 55296) {
                        break;
                    }
                    i41 |= (cCharAt5 & 8191) << i42;
                    i42 += 13;
                    i40 = i5;
                }
                iCharAt = (cCharAt5 << i42) | i41;
            } else {
                i5 = i40;
            }
            int i43 = i5 + 1;
            iCharAt2 = stringInfo.charAt(i5);
            if (iCharAt2 >= 55296) {
                int i44 = iCharAt2 & MtkTvScanDvbsBase.SB_DVBS_PID_INVALID_VALUE;
                int i45 = 13;
                while (true) {
                    i6 = i43 + 1;
                    cCharAt4 = stringInfo.charAt(i43);
                    if (cCharAt4 < 55296) {
                        break;
                    }
                    i44 |= (cCharAt4 & 8191) << i45;
                    i45 += 13;
                    i43 = i6;
                }
                iCharAt2 = (cCharAt4 << i45) | i44;
            } else {
                i6 = i43;
            }
            int i46 = i6 + 1;
            int iCharAt6 = stringInfo.charAt(i6);
            if (iCharAt6 >= 55296) {
                int i47 = iCharAt6 & MtkTvScanDvbsBase.SB_DVBS_PID_INVALID_VALUE;
                int i48 = 13;
                while (true) {
                    i7 = i46 + 1;
                    cCharAt3 = stringInfo.charAt(i46);
                    if (cCharAt3 < 55296) {
                        break;
                    }
                    i47 |= (cCharAt3 & 8191) << i48;
                    i48 += 13;
                    i46 = i7;
                }
                iCharAt6 = (cCharAt3 << i48) | i47;
            } else {
                i7 = i46;
            }
            int i49 = i7 + 1;
            int iCharAt7 = stringInfo.charAt(i7);
            if (iCharAt7 >= 55296) {
                int i50 = iCharAt7 & MtkTvScanDvbsBase.SB_DVBS_PID_INVALID_VALUE;
                int i51 = 13;
                while (true) {
                    i15 = i49 + 1;
                    cCharAt2 = stringInfo.charAt(i49);
                    if (cCharAt2 < 55296) {
                        break;
                    }
                    i50 |= (cCharAt2 & 8191) << i51;
                    i51 += 13;
                    i49 = i15;
                }
                iCharAt7 = i50 | (cCharAt2 << i51);
                i49 = i15;
            }
            i8 = i49 + 1;
            int iCharAt8 = stringInfo.charAt(i49);
            if (iCharAt8 >= 55296) {
                int i52 = iCharAt8 & MtkTvScanDvbsBase.SB_DVBS_PID_INVALID_VALUE;
                int i53 = 13;
                while (true) {
                    i14 = i8 + 1;
                    cCharAt = stringInfo.charAt(i8);
                    if (cCharAt < 55296) {
                        break;
                    }
                    i52 |= (cCharAt & 8191) << i53;
                    i53 += 13;
                    i8 = i14;
                }
                iCharAt8 = i52 | (cCharAt << i53);
                i8 = i14;
            }
            iArr = new int[iCharAt8 + iCharAt6 + iCharAt7];
            i9 = (iCharAt4 * 2) + iCharAt5;
            int i54 = i4;
            i10 = iCharAt4;
            i11 = iCharAt6;
            i12 = iCharAt8;
            i13 = i54;
        }
        Unsafe unsafe = UNSAFE;
        Object[] objects = rawMessageInfo.getObjects();
        Class<?> cls = rawMessageInfo.getDefaultInstance().getClass();
        int[] iArr2 = new int[iCharAt2 * 3];
        Object[] objArr = new Object[iCharAt2 * 2];
        int i55 = i12 + i11;
        int i56 = i12;
        int i57 = i55;
        int i58 = 0;
        int i59 = 0;
        while (i8 < length) {
            int i60 = i8 + 1;
            int iCharAt9 = stringInfo.charAt(i8);
            if (iCharAt9 >= c2) {
                int i61 = 13;
                int i62 = iCharAt9 & MtkTvScanDvbsBase.SB_DVBS_PID_INVALID_VALUE;
                int i63 = i60;
                while (true) {
                    i25 = i63 + 1;
                    cCharAt12 = stringInfo.charAt(i63);
                    if (cCharAt12 < c2) {
                        break;
                    }
                    i62 |= (cCharAt12 & 8191) << i61;
                    i61 += 13;
                    i63 = i25;
                }
                iCharAt9 = i62 | (cCharAt12 << i61);
                i17 = i25;
            } else {
                i17 = i60;
            }
            int i64 = i17 + 1;
            int iCharAt10 = stringInfo.charAt(i17);
            int i65 = length;
            char c3 = 55296;
            if (iCharAt10 >= 55296) {
                int i66 = 13;
                int i67 = iCharAt10 & MtkTvScanDvbsBase.SB_DVBS_PID_INVALID_VALUE;
                int i68 = i64;
                while (true) {
                    i24 = i68 + 1;
                    cCharAt11 = stringInfo.charAt(i68);
                    if (cCharAt11 < c3) {
                        break;
                    }
                    i67 |= (cCharAt11 & 8191) << i66;
                    i66 += 13;
                    i68 = i24;
                    c3 = 55296;
                }
                iCharAt10 = i67 | (cCharAt11 << i66);
                i18 = i24;
            } else {
                i18 = i64;
            }
            int i69 = i12;
            int i70 = iCharAt10 & 255;
            int i71 = iCharAt;
            if ((iCharAt10 & 1024) != 0) {
                iArr[i58] = i59;
                i58++;
            }
            int i72 = i58;
            if (i70 >= 51) {
                int i73 = i18 + 1;
                int iCharAt11 = stringInfo.charAt(i18);
                char c4 = 55296;
                if (iCharAt11 >= 55296) {
                    int i74 = iCharAt11 & MtkTvScanDvbsBase.SB_DVBS_PID_INVALID_VALUE;
                    int i75 = 13;
                    while (true) {
                        i23 = i73 + 1;
                        cCharAt10 = stringInfo.charAt(i73);
                        if (cCharAt10 < c4) {
                            break;
                        }
                        i74 |= (cCharAt10 & 8191) << i75;
                        i75 += 13;
                        i73 = i23;
                        c4 = 55296;
                    }
                    iCharAt11 = i74 | (cCharAt10 << i75);
                    i73 = i23;
                }
                int i76 = i70 - 51;
                int i77 = i73;
                if (i76 == 9 || i76 == 17) {
                    i22 = i9 + 1;
                    objArr[((i59 / 3) * 2) + 1] = objects[i9];
                } else if (i76 != 12 || z3) {
                    i22 = i9;
                } else {
                    i22 = i9 + 1;
                    objArr[((i59 / 3) * 2) + 1] = objects[i9];
                }
                int i78 = iCharAt11 * 2;
                Object obj = objects[i78];
                if (obj instanceof java.lang.reflect.Field) {
                    fieldReflectField2 = (java.lang.reflect.Field) obj;
                } else {
                    fieldReflectField2 = reflectField(cls, (String) obj);
                    objects[i78] = fieldReflectField2;
                }
                int iObjectFieldOffset3 = (int) unsafe.objectFieldOffset(fieldReflectField2);
                int i79 = i78 + 1;
                Object obj2 = objects[i79];
                if (obj2 instanceof java.lang.reflect.Field) {
                    fieldReflectField3 = (java.lang.reflect.Field) obj2;
                } else {
                    fieldReflectField3 = reflectField(cls, (String) obj2);
                    objects[i79] = fieldReflectField3;
                }
                iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldReflectField3);
                i9 = i22;
                i20 = i77;
                iObjectFieldOffset = iObjectFieldOffset3;
                cls = cls;
                i21 = 0;
            } else {
                int i80 = i9 + 1;
                java.lang.reflect.Field fieldReflectField4 = reflectField(cls, (String) objects[i9]);
                if (i70 == 9 || i70 == 17) {
                    z = true;
                    objArr[((i59 / 3) * 2) + 1] = fieldReflectField4.getType();
                } else {
                    if (i70 == 27 || i70 == 49) {
                        z = true;
                        i19 = i80 + 1;
                        objArr[((i59 / 3) * 2) + 1] = objects[i80];
                    } else if (i70 == 12 || i70 == 30 || i70 == 44) {
                        if (z3) {
                            z = true;
                        } else {
                            z = true;
                            i19 = i80 + 1;
                            objArr[((i59 / 3) * 2) + 1] = objects[i80];
                        }
                    } else if (i70 == 50) {
                        int i81 = i56 + 1;
                        iArr[i56] = i59;
                        int i82 = (i59 / 3) * 2;
                        i19 = i80 + 1;
                        objArr[i82] = objects[i80];
                        if ((iCharAt10 & 2048) != 0) {
                            objArr[i82 + 1] = objects[i19];
                            i19++;
                        }
                        i56 = i81;
                        z = true;
                    } else {
                        z = true;
                    }
                    iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldReflectField4);
                    if ((iCharAt10 & 4096) == 4096) {
                        z2 = z;
                    } else {
                        z2 = false;
                    }
                    if (z2 || i70 > 17) {
                        iObjectFieldOffset2 = 1048575;
                        i20 = i18;
                        i21 = 0;
                    } else {
                        int i83 = i18 + 1;
                        int iCharAt12 = stringInfo.charAt(i18);
                        if (iCharAt12 >= 55296) {
                            int i84 = iCharAt12 & MtkTvScanDvbsBase.SB_DVBS_PID_INVALID_VALUE;
                            int i85 = 13;
                            while (true) {
                                i20 = i83 + 1;
                                cCharAt9 = stringInfo.charAt(i83);
                                if (cCharAt9 < 55296) {
                                    break;
                                }
                                i84 |= (cCharAt9 & 8191) << i85;
                                i85 += 13;
                                i83 = i20;
                            }
                            iCharAt12 = i84 | (cCharAt9 << i85);
                        } else {
                            i20 = i83;
                        }
                        int i86 = (iCharAt12 / 32) + (i10 * 2);
                        Object obj3 = objects[i86];
                        if (obj3 instanceof java.lang.reflect.Field) {
                            fieldReflectField = (java.lang.reflect.Field) obj3;
                        } else {
                            fieldReflectField = reflectField(cls, (String) obj3);
                            objects[i86] = fieldReflectField;
                        }
                        iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldReflectField);
                        i21 = iCharAt12 % 32;
                    }
                    if (i70 >= 18 && i70 <= 49) {
                        iArr[i57] = iObjectFieldOffset;
                        i57++;
                    }
                    i9 = i19;
                }
                i19 = i80;
                iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldReflectField4);
                if ((iCharAt10 & 4096) == 4096) {
                    z2 = z;
                } else {
                    z2 = false;
                }
                if (z2) {
                    iObjectFieldOffset2 = 1048575;
                    i20 = i18;
                    i21 = 0;
                } else {
                    iObjectFieldOffset2 = 1048575;
                    i20 = i18;
                    i21 = 0;
                }
                if (i70 >= 18) {
                    iArr[i57] = iObjectFieldOffset;
                    i57++;
                }
                i9 = i19;
            }
            int i87 = i59 + 1;
            iArr2[i59] = iCharAt9;
            int i88 = i87 + 1;
            String str = stringInfo;
            iArr2[i87] = ((iCharAt10 & 512) != 0 ? ENFORCE_UTF8_MASK : 0) | ((iCharAt10 & 256) != 0 ? REQUIRED_MASK : 0) | (i70 << 20) | iObjectFieldOffset;
            int i89 = i88 + 1;
            iArr2[i88] = (i21 << 20) | iObjectFieldOffset2;
            cls = cls;
            i12 = i69;
            length = i65;
            iCharAt = i71;
            i8 = i20;
            i58 = i72;
            iArr2 = iArr2;
            c2 = 55296;
            i59 = i89;
            stringInfo = str;
        }
        return new MessageSchema<>(iArr2, objArr, i13, iCharAt, rawMessageInfo.getDefaultInstance(), z3, false, iArr, i12, i55, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    private int numberAt(int i) {
        return this.buffer[i];
    }

    public static long offset(int i) {
        return i & 1048575;
    }

    public static <T> boolean oneofBooleanAt(T t, long j) {
        return ((Boolean) UnsafeUtil.getObject(t, j)).booleanValue();
    }

    public static <T> double oneofDoubleAt(T t, long j) {
        return ((Double) UnsafeUtil.getObject(t, j)).doubleValue();
    }

    public static <T> float oneofFloatAt(T t, long j) {
        return ((Float) UnsafeUtil.getObject(t, j)).floatValue();
    }

    public static <T> int oneofIntAt(T t, long j) {
        return ((Integer) UnsafeUtil.getObject(t, j)).intValue();
    }

    public static <T> long oneofLongAt(T t, long j) {
        return ((Long) UnsafeUtil.getObject(t, j)).longValue();
    }

    private <K, V> int parseMapField(T t, byte[] bArr, int i, int i2, int i3, long j, ArrayDecoders.Registers registers) {
        Unsafe unsafe = UNSAFE;
        Object obj = this.objects[(i3 / 3) * 2];
        Object object = unsafe.getObject(t, j);
        if (this.mapFieldSchema.isImmutable(object)) {
            Object objNewMapField = this.mapFieldSchema.newMapField(obj);
            this.mapFieldSchema.mergeFrom(objNewMapField, object);
            unsafe.putObject(t, j, objNewMapField);
            object = objNewMapField;
        }
        return decodeMapEntry(bArr, i, i2, this.mapFieldSchema.forMapMetadata(obj), this.mapFieldSchema.forMutableMapData(object), registers);
    }

    private int parseOneofField(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, ArrayDecoders.Registers registers) throws InvalidProtocolBufferException {
        Unsafe unsafe = UNSAFE;
        long j2 = this.buffer[i8 + 2] & 1048575;
        switch (i7) {
            case 51:
                if (i5 != 1) {
                    return i;
                }
                unsafe.putObject(t, j, Double.valueOf(ArrayDecoders.decodeDouble(bArr, i)));
                int i9 = i + 8;
                unsafe.putInt(t, j2, i4);
                return i9;
            case 52:
                if (i5 != 5) {
                    return i;
                }
                unsafe.putObject(t, j, Float.valueOf(ArrayDecoders.decodeFloat(bArr, i)));
                int i10 = i + 4;
                unsafe.putInt(t, j2, i4);
                return i10;
            case 53:
            case 54:
                if (i5 != 0) {
                    return i;
                }
                int iDecodeVarint64 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                unsafe.putObject(t, j, Long.valueOf(registers.long1));
                unsafe.putInt(t, j2, i4);
                return iDecodeVarint64;
            case 55:
            case 62:
                if (i5 != 0) {
                    return i;
                }
                int iDecodeVarint32 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                unsafe.putObject(t, j, Integer.valueOf(registers.int1));
                unsafe.putInt(t, j2, i4);
                return iDecodeVarint32;
            case 56:
            case 65:
                if (i5 != 1) {
                    return i;
                }
                unsafe.putObject(t, j, Long.valueOf(ArrayDecoders.decodeFixed64(bArr, i)));
                int i11 = i + 8;
                unsafe.putInt(t, j2, i4);
                return i11;
            case 57:
            case 64:
                if (i5 != 5) {
                    return i;
                }
                unsafe.putObject(t, j, Integer.valueOf(ArrayDecoders.decodeFixed32(bArr, i)));
                int i12 = i + 4;
                unsafe.putInt(t, j2, i4);
                return i12;
            case 58:
                if (i5 != 0) {
                    return i;
                }
                int iDecodeVarint65 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                unsafe.putObject(t, j, Boolean.valueOf(registers.long1 != 0));
                unsafe.putInt(t, j2, i4);
                return iDecodeVarint65;
            case 59:
                if (i5 != 2) {
                    return i;
                }
                int iDecodeVarint33 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                int i13 = registers.int1;
                if (i13 == 0) {
                    unsafe.putObject(t, j, "");
                } else {
                    if ((i6 & ENFORCE_UTF8_MASK) != 0 && !Utf8.isValidUtf8(bArr, iDecodeVarint33, iDecodeVarint33 + i13)) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    unsafe.putObject(t, j, new String(bArr, iDecodeVarint33, i13, Internal.UTF_8));
                    iDecodeVarint33 += i13;
                }
                unsafe.putInt(t, j2, i4);
                return iDecodeVarint33;
            case 60:
                if (i5 != 2) {
                    return i;
                }
                int iDecodeMessageField = ArrayDecoders.decodeMessageField(getMessageFieldSchema(i8), bArr, i, i2, registers);
                Object object = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                if (object == null) {
                    unsafe.putObject(t, j, registers.object1);
                } else {
                    unsafe.putObject(t, j, Internal.mergeMessage(object, registers.object1));
                }
                unsafe.putInt(t, j2, i4);
                return iDecodeMessageField;
            case 61:
                if (i5 != 2) {
                    return i;
                }
                int iDecodeBytes = ArrayDecoders.decodeBytes(bArr, i, registers);
                unsafe.putObject(t, j, registers.object1);
                unsafe.putInt(t, j2, i4);
                return iDecodeBytes;
            case 63:
                if (i5 != 0) {
                    return i;
                }
                int iDecodeVarint34 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                int i14 = registers.int1;
                Internal.EnumVerifier enumVerifier = (Internal.EnumVerifier) this.objects[((i8 / 3) * 2) + 1];
                if (enumVerifier == null || enumVerifier.isInRange(i14)) {
                    unsafe.putObject(t, j, Integer.valueOf(i14));
                    unsafe.putInt(t, j2, i4);
                } else {
                    getMutableUnknownFields(t).storeField(i3, Long.valueOf(i14));
                }
                return iDecodeVarint34;
            case 66:
                if (i5 != 0) {
                    return i;
                }
                int iDecodeVarint35 = ArrayDecoders.decodeVarint32(bArr, i, registers);
                unsafe.putObject(t, j, Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1)));
                unsafe.putInt(t, j2, i4);
                return iDecodeVarint35;
            case 67:
                if (i5 != 0) {
                    return i;
                }
                int iDecodeVarint66 = ArrayDecoders.decodeVarint64(bArr, i, registers);
                unsafe.putObject(t, j, Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1)));
                unsafe.putInt(t, j2, i4);
                return iDecodeVarint66;
            case 68:
                if (i5 != 3) {
                    return i;
                }
                int iDecodeGroupField = ArrayDecoders.decodeGroupField(getMessageFieldSchema(i8), bArr, i, i2, (i3 & (-8)) | 4, registers);
                Object object2 = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                if (object2 == null) {
                    unsafe.putObject(t, j, registers.object1);
                } else {
                    unsafe.putObject(t, j, Internal.mergeMessage(object2, registers.object1));
                }
                unsafe.putInt(t, j2, i4);
                return iDecodeGroupField;
            default:
                return i;
        }
    }

    /* JADX WARN: Code duplicated, block: B:104:0x029c A[PHI: r0 r18 r20 r24 r25 r28
      0x029c: PHI (r0v19 int) = (r0v14 int), (r0v17 int), (r0v21 int) binds: [B:115:0x0306, B:110:0x02e3, B:103:0x029a] A[DONT_GENERATE, DONT_INLINE]
      0x029c: PHI (r18v6 int) = (r18v4 int), (r18v4 int), (r18v7 int) binds: [B:115:0x0306, B:110:0x02e3, B:103:0x029a] A[DONT_GENERATE, DONT_INLINE]
      0x029c: PHI (r20v4 int) = (r20v2 int), (r20v2 int), (r20v5 int) binds: [B:115:0x0306, B:110:0x02e3, B:103:0x029a] A[DONT_GENERATE, DONT_INLINE]
      0x029c: PHI (r24v3 int) = (r24v1 int), (r24v1 int), (r24v4 int) binds: [B:115:0x0306, B:110:0x02e3, B:103:0x029a] A[DONT_GENERATE, DONT_INLINE]
      0x029c: PHI (r25v2 int) = (r25v0 int), (r25v0 int), (r25v3 int) binds: [B:115:0x0306, B:110:0x02e3, B:103:0x029a] A[DONT_GENERATE, DONT_INLINE]
      0x029c: PHI (r28v6 sun.misc.Unsafe) = (r28v4 sun.misc.Unsafe), (r28v4 sun.misc.Unsafe), (r28v7 sun.misc.Unsafe) binds: [B:115:0x0306, B:110:0x02e3, B:103:0x029a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:117:0x0309 A[PHI: r0 r18 r20 r24 r25 r28
      0x0309: PHI (r0v18 int) = (r0v14 int), (r0v17 int), (r0v21 int) binds: [B:115:0x0306, B:110:0x02e3, B:103:0x029a] A[DONT_GENERATE, DONT_INLINE]
      0x0309: PHI (r18v5 int) = (r18v4 int), (r18v4 int), (r18v7 int) binds: [B:115:0x0306, B:110:0x02e3, B:103:0x029a] A[DONT_GENERATE, DONT_INLINE]
      0x0309: PHI (r20v3 int) = (r20v2 int), (r20v2 int), (r20v5 int) binds: [B:115:0x0306, B:110:0x02e3, B:103:0x029a] A[DONT_GENERATE, DONT_INLINE]
      0x0309: PHI (r24v2 int) = (r24v1 int), (r24v1 int), (r24v4 int) binds: [B:115:0x0306, B:110:0x02e3, B:103:0x029a] A[DONT_GENERATE, DONT_INLINE]
      0x0309: PHI (r25v1 int) = (r25v0 int), (r25v0 int), (r25v3 int) binds: [B:115:0x0306, B:110:0x02e3, B:103:0x029a] A[DONT_GENERATE, DONT_INLINE]
      0x0309: PHI (r28v5 sun.misc.Unsafe) = (r28v4 sun.misc.Unsafe), (r28v4 sun.misc.Unsafe), (r28v7 sun.misc.Unsafe) binds: [B:115:0x0306, B:110:0x02e3, B:103:0x029a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Failed to find 'out' block for switch in B:27:0x009b. Please report as an issue. */
    private int parseProto3Message(T t, byte[] bArr, int i, int i2, ArrayDecoders.Registers registers) throws InvalidProtocolBufferException {
        int i3;
        int iDecodeVarint32;
        int i4;
        int i5;
        Unsafe unsafe;
        int i6;
        int i7;
        int i8;
        Unsafe unsafe2;
        int i9;
        int i10;
        int i11;
        int iDecodeVarint64;
        int i12;
        Unsafe unsafe3;
        this = this;
        t = t;
        bArr = bArr;
        i2 = i2;
        registers = registers;
        Unsafe unsafe4 = UNSAFE;
        int i13 = -1;
        int iDecodeUnknownField = i;
        int i14 = -1;
        int i15 = 0;
        int i16 = 0;
        int i17 = 1048575;
        while (iDecodeUnknownField < i2) {
            int i18 = iDecodeUnknownField + 1;
            byte b2 = bArr[iDecodeUnknownField];
            if (b2 < 0) {
                iDecodeVarint32 = ArrayDecoders.decodeVarint32(b2, bArr, i18, registers);
                i3 = registers.int1;
            } else {
                i3 = b2;
                iDecodeVarint32 = i18;
            }
            int i19 = i3 >>> 3;
            int i20 = i3 & 7;
            int iPositionForFieldNumber = i19 > i14 ? this.positionForFieldNumber(i19, i15 / 3) : this.positionForFieldNumber(i19);
            if (iPositionForFieldNumber == i13) {
                i4 = iDecodeVarint32;
                i5 = i19;
                unsafe = unsafe4;
                i6 = i13;
                i7 = 0;
            } else {
                int i21 = this.buffer[iPositionForFieldNumber + 1];
                int iType = type(i21);
                Unsafe unsafe5 = unsafe4;
                long jOffset = offset(i21);
                if (iType <= 17) {
                    int i22 = this.buffer[iPositionForFieldNumber + 2];
                    int i23 = 1 << (i22 >>> 20);
                    int i24 = i22 & 1048575;
                    if (i24 != i17) {
                        if (i17 != 1048575) {
                            long j = i17;
                            unsafe3 = unsafe5;
                            unsafe3.putInt(t, j, i16);
                        } else {
                            unsafe3 = unsafe5;
                        }
                        if (i24 != 1048575) {
                            i16 = unsafe3.getInt(t, i24);
                        }
                        unsafe2 = unsafe3;
                        i17 = i24;
                    } else {
                        i21 = i21;
                        iPositionForFieldNumber = iPositionForFieldNumber;
                        unsafe2 = unsafe5;
                    }
                    switch (iType) {
                        case 0:
                            i8 = iPositionForFieldNumber;
                            i5 = i19;
                            if (i20 != 1) {
                                i4 = iDecodeVarint32;
                                unsafe = unsafe2;
                                i7 = i8;
                                i6 = -1;
                            } else {
                                UnsafeUtil.putDouble(t, jOffset, ArrayDecoders.decodeDouble(bArr, iDecodeVarint32));
                                iDecodeUnknownField = iDecodeVarint32 + 8;
                                i16 |= i23;
                                unsafe4 = unsafe2;
                                i15 = i8;
                                i14 = i5;
                                i13 = -1;
                                i2 = i2;
                            }
                            break;
                        case 1:
                            i8 = iPositionForFieldNumber;
                            i5 = i19;
                            if (i20 != 5) {
                                i4 = iDecodeVarint32;
                                unsafe = unsafe2;
                                i7 = i8;
                                i6 = -1;
                            } else {
                                UnsafeUtil.MEMORY_ACCESSOR.putFloat(t, jOffset, ArrayDecoders.decodeFloat(bArr, iDecodeVarint32));
                                iDecodeUnknownField = iDecodeVarint32 + 4;
                                i16 |= i23;
                                unsafe4 = unsafe2;
                                i15 = i8;
                                i14 = i5;
                                i13 = -1;
                                i2 = i2;
                            }
                            break;
                        case 2:
                        case 3:
                            i8 = iPositionForFieldNumber;
                            i5 = i19;
                            if (i20 != 0) {
                                i4 = iDecodeVarint32;
                                unsafe = unsafe2;
                                i7 = i8;
                                i6 = -1;
                            } else {
                                iDecodeVarint64 = ArrayDecoders.decodeVarint64(bArr, iDecodeVarint32, registers);
                                unsafe2.putLong(t, jOffset, registers.long1);
                                i16 |= i23;
                                unsafe4 = unsafe2;
                                i15 = i8;
                                iDecodeUnknownField = iDecodeVarint64;
                                i14 = i5;
                                i13 = -1;
                                i2 = i2;
                            }
                            break;
                        case 4:
                        case 11:
                            i8 = iPositionForFieldNumber;
                            i5 = i19;
                            if (i20 != 0) {
                                i4 = iDecodeVarint32;
                                unsafe = unsafe2;
                                i7 = i8;
                                i6 = -1;
                            } else {
                                iDecodeUnknownField = ArrayDecoders.decodeVarint32(bArr, iDecodeVarint32, registers);
                                unsafe2.putInt(t, jOffset, registers.int1);
                                i16 |= i23;
                                unsafe4 = unsafe2;
                                i15 = i8;
                                i14 = i5;
                                i13 = -1;
                                i2 = i2;
                            }
                            break;
                        case 5:
                        case 14:
                            i8 = iPositionForFieldNumber;
                            i5 = i19;
                            if (i20 != 1) {
                                i4 = iDecodeVarint32;
                                unsafe = unsafe2;
                                i7 = i8;
                                i6 = -1;
                            } else {
                                unsafe2.putLong(t, jOffset, ArrayDecoders.decodeFixed64(bArr, iDecodeVarint32));
                                iDecodeUnknownField = iDecodeVarint32 + 8;
                                i16 |= i23;
                                unsafe4 = unsafe2;
                                i15 = i8;
                                i14 = i5;
                                i13 = -1;
                                i2 = i2;
                            }
                            break;
                        case 6:
                        case 13:
                            i12 = i2;
                            i8 = iPositionForFieldNumber;
                            i5 = i19;
                            if (i20 != 5) {
                                i4 = iDecodeVarint32;
                                unsafe = unsafe2;
                                i7 = i8;
                                i6 = -1;
                            } else {
                                unsafe2.putInt(t, jOffset, ArrayDecoders.decodeFixed32(bArr, iDecodeVarint32));
                                iDecodeUnknownField = iDecodeVarint32 + 4;
                                i16 |= i23;
                                unsafe4 = unsafe2;
                                i14 = i5;
                                i13 = -1;
                                int i25 = i8;
                                i2 = i12;
                                i15 = i25;
                            }
                            break;
                        case 7:
                            i12 = i2;
                            i8 = iPositionForFieldNumber;
                            i5 = i19;
                            if (i20 != 0) {
                                i4 = iDecodeVarint32;
                                unsafe = unsafe2;
                                i7 = i8;
                                i6 = -1;
                            } else {
                                iDecodeUnknownField = ArrayDecoders.decodeVarint64(bArr, iDecodeVarint32, registers);
                                UnsafeUtil.MEMORY_ACCESSOR.putBoolean(t, jOffset, registers.long1 != 0);
                                i16 |= i23;
                                unsafe4 = unsafe2;
                                i14 = i5;
                                i13 = -1;
                                int i26 = i8;
                                i2 = i12;
                                i15 = i26;
                            }
                            break;
                        case 8:
                            i12 = i2;
                            i8 = iPositionForFieldNumber;
                            i5 = i19;
                            if (i20 != 2) {
                                i4 = iDecodeVarint32;
                                unsafe = unsafe2;
                                i7 = i8;
                                i6 = -1;
                            } else {
                                iDecodeUnknownField = (i21 & ENFORCE_UTF8_MASK) == 0 ? ArrayDecoders.decodeString(bArr, iDecodeVarint32, registers) : ArrayDecoders.decodeStringRequireUtf8(bArr, iDecodeVarint32, registers);
                                unsafe2.putObject(t, jOffset, registers.object1);
                                i16 |= i23;
                                unsafe4 = unsafe2;
                                i14 = i5;
                                i13 = -1;
                                int i27 = i8;
                                i2 = i12;
                                i15 = i27;
                            }
                            break;
                        case 9:
                            i8 = iPositionForFieldNumber;
                            i5 = i19;
                            if (i20 != 2) {
                                i4 = iDecodeVarint32;
                                unsafe = unsafe2;
                                i7 = i8;
                                i6 = -1;
                            } else {
                                i12 = i2;
                                iDecodeUnknownField = ArrayDecoders.decodeMessageField(this.getMessageFieldSchema(i8), bArr, iDecodeVarint32, i12, registers);
                                Object object = unsafe2.getObject(t, jOffset);
                                if (object == null) {
                                    unsafe2.putObject(t, jOffset, registers.object1);
                                } else {
                                    unsafe2.putObject(t, jOffset, Internal.mergeMessage(object, registers.object1));
                                }
                                i16 |= i23;
                                unsafe4 = unsafe2;
                                i14 = i5;
                                i13 = -1;
                                int i28 = i8;
                                i2 = i12;
                                i15 = i28;
                            }
                            break;
                        case 10:
                            i8 = iPositionForFieldNumber;
                            i5 = i19;
                            if (i20 != 2) {
                                i4 = iDecodeVarint32;
                                unsafe = unsafe2;
                                i7 = i8;
                                i6 = -1;
                            } else {
                                iDecodeUnknownField = ArrayDecoders.decodeBytes(bArr, iDecodeVarint32, registers);
                                unsafe2.putObject(t, jOffset, registers.object1);
                                i16 |= i23;
                                unsafe4 = unsafe2;
                                i15 = i8;
                                i14 = i5;
                                i13 = -1;
                                i2 = i2;
                            }
                            break;
                        case 12:
                            i8 = iPositionForFieldNumber;
                            i5 = i19;
                            if (i20 != 0) {
                                i4 = iDecodeVarint32;
                                unsafe = unsafe2;
                                i7 = i8;
                                i6 = -1;
                            } else {
                                iDecodeUnknownField = ArrayDecoders.decodeVarint32(bArr, iDecodeVarint32, registers);
                                unsafe2.putInt(t, jOffset, registers.int1);
                                i16 |= i23;
                                unsafe4 = unsafe2;
                                i15 = i8;
                                i14 = i5;
                                i13 = -1;
                                i2 = i2;
                            }
                            break;
                        case 15:
                            i8 = iPositionForFieldNumber;
                            i5 = i19;
                            if (i20 != 0) {
                                i4 = iDecodeVarint32;
                                unsafe = unsafe2;
                                i7 = i8;
                                i6 = -1;
                            } else {
                                iDecodeUnknownField = ArrayDecoders.decodeVarint32(bArr, iDecodeVarint32, registers);
                                unsafe2.putInt(t, jOffset, CodedInputStream.decodeZigZag32(registers.int1));
                                i16 |= i23;
                                unsafe4 = unsafe2;
                                i15 = i8;
                                i14 = i5;
                                i13 = -1;
                                i2 = i2;
                            }
                            break;
                        case 16:
                            if (i20 != 0) {
                                i8 = iPositionForFieldNumber;
                                i5 = i19;
                                i4 = iDecodeVarint32;
                                unsafe = unsafe2;
                                i7 = i8;
                                i6 = -1;
                            } else {
                                iDecodeVarint64 = ArrayDecoders.decodeVarint64(bArr, iDecodeVarint32, registers);
                                i8 = iPositionForFieldNumber;
                                i5 = i19;
                                unsafe2.putLong(t, jOffset, CodedInputStream.decodeZigZag64(registers.long1));
                                i16 |= i23;
                                unsafe4 = unsafe2;
                                i15 = i8;
                                iDecodeUnknownField = iDecodeVarint64;
                                i14 = i5;
                                i13 = -1;
                                i2 = i2;
                            }
                            break;
                        default:
                            i8 = iPositionForFieldNumber;
                            i5 = i19;
                            i4 = iDecodeVarint32;
                            unsafe = unsafe2;
                            i7 = i8;
                            i6 = -1;
                            break;
                    }
                } else {
                    i5 = i19;
                    i8 = iPositionForFieldNumber;
                    unsafe2 = unsafe5;
                    if (iType != 27) {
                        if (iType <= 49) {
                            int i29 = iDecodeVarint32;
                            i10 = i16;
                            i11 = i17;
                            unsafe = unsafe2;
                            i6 = -1;
                            i7 = i8;
                            iDecodeUnknownField = parseRepeatedField(t, bArr, iDecodeVarint32, i2, i3, i5, i20, i8, i21, iType, jOffset, registers);
                            if (iDecodeUnknownField != i29) {
                                i13 = i6;
                                i14 = i5;
                                i15 = i7;
                                i16 = i10;
                                i17 = i11;
                            } else {
                                i4 = iDecodeUnknownField;
                                i16 = i10;
                                i17 = i11;
                            }
                        } else {
                            i9 = iDecodeVarint32;
                            i10 = i16;
                            i11 = i17;
                            unsafe = unsafe2;
                            i7 = i8;
                            i6 = -1;
                            if (iType == 50) {
                                if (i20 == 2) {
                                    iDecodeUnknownField = parseMapField(t, bArr, i9, i2, i7, jOffset, registers);
                                    if (iDecodeUnknownField != i9) {
                                        i13 = i6;
                                        i14 = i5;
                                        i15 = i7;
                                        i16 = i10;
                                        i17 = i11;
                                    } else {
                                        i4 = iDecodeUnknownField;
                                    }
                                }
                                i16 = i10;
                                i17 = i11;
                            } else {
                                iDecodeUnknownField = parseOneofField(t, bArr, i9, i2, i3, i5, i20, i21, iType, jOffset, i7, registers);
                                if (iDecodeUnknownField != i9) {
                                    i13 = i6;
                                    i14 = i5;
                                    i15 = i7;
                                    i16 = i10;
                                    i17 = i11;
                                } else {
                                    i4 = iDecodeUnknownField;
                                    i16 = i10;
                                    i17 = i11;
                                }
                            }
                        }
                        unsafe4 = unsafe;
                    } else if (i20 == 2) {
                        Internal.ProtobufList protobufListMutableCopyWithCapacity2 = (Internal.ProtobufList) unsafe2.getObject(t, jOffset);
                        if (!protobufListMutableCopyWithCapacity2.isModifiable()) {
                            int size = protobufListMutableCopyWithCapacity2.size();
                            protobufListMutableCopyWithCapacity2 = protobufListMutableCopyWithCapacity2.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
                            unsafe2.putObject(t, jOffset, protobufListMutableCopyWithCapacity2);
                        }
                        iDecodeUnknownField = ArrayDecoders.decodeMessageList(this.getMessageFieldSchema(i8), i3, bArr, iDecodeVarint32, i2, protobufListMutableCopyWithCapacity2, registers);
                        i16 = i16;
                        unsafe4 = unsafe2;
                        i15 = i8;
                        i14 = i5;
                        i13 = -1;
                        i2 = i2;
                    } else {
                        i9 = iDecodeVarint32;
                        i10 = i16;
                        i11 = i17;
                        unsafe = unsafe2;
                        i7 = i8;
                        i6 = -1;
                    }
                    i4 = i9;
                    i16 = i10;
                    i17 = i11;
                }
            }
            iDecodeUnknownField = ArrayDecoders.decodeUnknownField(i3, bArr, i4, i2, getMutableUnknownFields(t), registers);
            i13 = i6;
            i14 = i5;
            i15 = i7;
            unsafe4 = unsafe;
        }
        int i30 = i16;
        Unsafe unsafe6 = unsafe4;
        if (i17 != 1048575) {
            unsafe6.putInt(t, i17, i30);
        }
        if (iDecodeUnknownField == i2) {
            return iDecodeUnknownField;
        }
        throw InvalidProtocolBufferException.parseFailure();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private int parseRepeatedField(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, ArrayDecoders.Registers registers) throws InvalidProtocolBufferException {
        int iDecodeVarint32List;
        Internal.ProtobufList protobufListMutableCopyWithCapacity2 = (Internal.ProtobufList) UNSAFE.getObject(t, j2);
        if (!protobufListMutableCopyWithCapacity2.isModifiable()) {
            int size = protobufListMutableCopyWithCapacity2.size();
            protobufListMutableCopyWithCapacity2 = protobufListMutableCopyWithCapacity2.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
            UNSAFE.putObject(t, j2, protobufListMutableCopyWithCapacity2);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedDoubleList(bArr, i, protobufListMutableCopyWithCapacity2, registers);
                }
                return i5 == 1 ? ArrayDecoders.decodeDoubleList(i3, bArr, i, i2, protobufListMutableCopyWithCapacity2, registers) : i;
            case 19:
            case 36:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedFloatList(bArr, i, protobufListMutableCopyWithCapacity2, registers);
                }
                return i5 == 5 ? ArrayDecoders.decodeFloatList(i3, bArr, i, i2, protobufListMutableCopyWithCapacity2, registers) : i;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedVarint64List(bArr, i, protobufListMutableCopyWithCapacity2, registers);
                }
                return i5 == 0 ? ArrayDecoders.decodeVarint64List(i3, bArr, i, i2, protobufListMutableCopyWithCapacity2, registers) : i;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedVarint32List(bArr, i, protobufListMutableCopyWithCapacity2, registers);
                }
                return i5 == 0 ? ArrayDecoders.decodeVarint32List(i3, bArr, i, i2, protobufListMutableCopyWithCapacity2, registers) : i;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedFixed64List(bArr, i, protobufListMutableCopyWithCapacity2, registers);
                }
                return i5 == 1 ? ArrayDecoders.decodeFixed64List(i3, bArr, i, i2, protobufListMutableCopyWithCapacity2, registers) : i;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedFixed32List(bArr, i, protobufListMutableCopyWithCapacity2, registers);
                }
                return i5 == 5 ? ArrayDecoders.decodeFixed32List(i3, bArr, i, i2, protobufListMutableCopyWithCapacity2, registers) : i;
            case 25:
            case 42:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedBoolList(bArr, i, protobufListMutableCopyWithCapacity2, registers);
                }
                return i5 == 0 ? ArrayDecoders.decodeBoolList(i3, bArr, i, i2, protobufListMutableCopyWithCapacity2, registers) : i;
            case 26:
                if (i5 == 2) {
                    return (j & 536870912) == 0 ? ArrayDecoders.decodeStringList(i3, bArr, i, i2, protobufListMutableCopyWithCapacity2, registers) : ArrayDecoders.decodeStringListRequireUtf8(i3, bArr, i, i2, protobufListMutableCopyWithCapacity2, registers);
                }
                return i;
            case 27:
                return i5 == 2 ? ArrayDecoders.decodeMessageList(getMessageFieldSchema(i6), i3, bArr, i, i2, protobufListMutableCopyWithCapacity2, registers) : i;
            case 28:
                return i5 == 2 ? ArrayDecoders.decodeBytesList(i3, bArr, i, i2, protobufListMutableCopyWithCapacity2, registers) : i;
            case 30:
            case 44:
                if (i5 == 2) {
                    iDecodeVarint32List = ArrayDecoders.decodePackedVarint32List(bArr, i, protobufListMutableCopyWithCapacity2, registers);
                } else {
                    if (i5 != 0) {
                        return i;
                    }
                    iDecodeVarint32List = ArrayDecoders.decodeVarint32List(i3, bArr, i, i2, protobufListMutableCopyWithCapacity2, registers);
                }
                GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) t;
                UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
                if (unknownFieldSetLite == UnknownFieldSetLite.DEFAULT_INSTANCE) {
                    unknownFieldSetLite = null;
                }
                UnknownFieldSetLite unknownFieldSetLite2 = (UnknownFieldSetLite) SchemaUtil.filterUnknownEnumList(i4, (List<Integer>) protobufListMutableCopyWithCapacity2, (Internal.EnumVerifier) this.objects[((i6 / 3) * 2) + 1], unknownFieldSetLite, (UnknownFieldSchema<UT, UnknownFieldSetLite>) this.unknownFieldSchema);
                if (unknownFieldSetLite2 != null) {
                    generatedMessageLite.unknownFields = unknownFieldSetLite2;
                }
                return iDecodeVarint32List;
            case 33:
            case 47:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedSInt32List(bArr, i, protobufListMutableCopyWithCapacity2, registers);
                }
                return i5 == 0 ? ArrayDecoders.decodeSInt32List(i3, bArr, i, i2, protobufListMutableCopyWithCapacity2, registers) : i;
            case 34:
            case 48:
                if (i5 == 2) {
                    return ArrayDecoders.decodePackedSInt64List(bArr, i, protobufListMutableCopyWithCapacity2, registers);
                }
                return i5 == 0 ? ArrayDecoders.decodeSInt64List(i3, bArr, i, i2, protobufListMutableCopyWithCapacity2, registers) : i;
            case 49:
                return i5 == 3 ? ArrayDecoders.decodeGroupList(getMessageFieldSchema(i6), i3, bArr, i, i2, protobufListMutableCopyWithCapacity2, registers) : i;
            default:
                return i;
        }
    }

    private int positionForFieldNumber(int i) {
        if (i < this.minFieldNumber || i > this.maxFieldNumber) {
            return -1;
        }
        return slowPositionForFieldNumber(i, 0);
    }

    private int presenceMaskAndOffsetAt(int i) {
        return this.buffer[i + 2];
    }

    private <E> void readGroupList(Object obj, long j, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistryLite) {
        reader.readGroupList(this.listFieldSchema.mutableListAt(obj, j), schema, extensionRegistryLite);
    }

    private <E> void readMessageList(Object obj, int i, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistryLite) {
        reader.readMessageList(this.listFieldSchema.mutableListAt(obj, offset(i)), schema, extensionRegistryLite);
    }

    private void readString(Object obj, int i, Reader reader) {
        if (isEnforceUtf8(i)) {
            UnsafeUtil.MEMORY_ACCESSOR.putObject(obj, offset(i), reader.readStringRequireUtf8());
        } else if (this.lite) {
            UnsafeUtil.MEMORY_ACCESSOR.putObject(obj, offset(i), reader.readString());
        } else {
            UnsafeUtil.MEMORY_ACCESSOR.putObject(obj, offset(i), reader.readBytes());
        }
    }

    private void readStringList(Object obj, int i, Reader reader) {
        if (isEnforceUtf8(i)) {
            reader.readStringListRequireUtf8(this.listFieldSchema.mutableListAt(obj, offset(i)));
        } else {
            reader.readStringList(this.listFieldSchema.mutableListAt(obj, offset(i)));
        }
    }

    public static java.lang.reflect.Field reflectField(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            java.lang.reflect.Field[] declaredFields = cls.getDeclaredFields();
            for (java.lang.reflect.Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            StringBuilder sbB = a.b("Field ", str, " for ");
            sbB.append(cls.getName());
            sbB.append(" not found. Known fields are ");
            sbB.append(Arrays.toString(declaredFields));
            throw new RuntimeException(sbB.toString());
        }
    }

    private void setFieldPresent(T t, int i) {
        int i2 = this.buffer[i + 2];
        long j = 1048575 & i2;
        if (j == 1048575) {
            return;
        }
        UnsafeUtil.MEMORY_ACCESSOR.putInt(t, j, (1 << (i2 >>> 20)) | UnsafeUtil.getInt(t, j));
    }

    private void setOneofPresent(T t, int i, int i2) {
        UnsafeUtil.MEMORY_ACCESSOR.putInt(t, this.buffer[i2 + 2] & 1048575, i);
    }

    private int slowPositionForFieldNumber(int i, int i2) {
        int length = (this.buffer.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.buffer[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    /* JADX WARN: Code duplicated, block: B:21:0x007a  */
    /* JADX WARN: Code duplicated, block: B:22:0x007d  */
    /* JADX WARN: Code duplicated, block: B:25:0x0084  */
    /* JADX WARN: Code duplicated, block: B:28:0x009e  */
    /* JADX WARN: Code duplicated, block: B:30:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:31:0x00af  */
    /* JADX WARN: Code duplicated, block: B:33:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:34:0x00be A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:35:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:36:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:38:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:41:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:44:? A[RETURN, SYNTHETIC] */
    public static void storeFieldData(FieldInfo fieldInfo, int[] iArr, int i, Object[] objArr) {
        int iObjectFieldOffset;
        int iId;
        long jObjectFieldOffset;
        int iObjectFieldOffset2;
        int iNumberOfTrailingZeros;
        int i2;
        Class<?> messageFieldClass;
        int i3;
        OneofInfo oneof = fieldInfo.getOneof();
        if (oneof == null) {
            FieldType type = fieldInfo.getType();
            iObjectFieldOffset = (int) UnsafeUtil.objectFieldOffset(fieldInfo.getField());
            iId = type.id();
            if (!type.isList() && !type.isMap()) {
                java.lang.reflect.Field presenceField = fieldInfo.getPresenceField();
                iObjectFieldOffset2 = presenceField == null ? 1048575 : (int) UnsafeUtil.objectFieldOffset(presenceField);
                iNumberOfTrailingZeros = Integer.numberOfTrailingZeros(fieldInfo.getPresenceMask());
            } else if (fieldInfo.getCachedSizeField() == null) {
                iObjectFieldOffset2 = 0;
                iNumberOfTrailingZeros = 0;
            } else {
                jObjectFieldOffset = UnsafeUtil.objectFieldOffset(fieldInfo.getCachedSizeField());
            }
            iArr[i] = fieldInfo.getFieldNumber();
            int i4 = i + 1;
            if (fieldInfo.isEnforceUtf8()) {
                i2 = ENFORCE_UTF8_MASK;
            } else {
                i2 = 0;
            }
            iArr[i4] = (fieldInfo.isRequired() ? REQUIRED_MASK : 0) | i2 | (iId << 20) | iObjectFieldOffset;
            iArr[i + 2] = iObjectFieldOffset2 | (iNumberOfTrailingZeros << 20);
            messageFieldClass = fieldInfo.getMessageFieldClass();
            if (fieldInfo.getMapDefaultEntry() != null) {
                if (messageFieldClass != null) {
                    objArr[((i / 3) * 2) + 1] = messageFieldClass;
                    return;
                } else {
                    if (fieldInfo.getEnumVerifier() != null) {
                        objArr[((i / 3) * 2) + 1] = fieldInfo.getEnumVerifier();
                        return;
                    }
                    return;
                }
            }
            i3 = (i / 3) * 2;
            objArr[i3] = fieldInfo.getMapDefaultEntry();
            if (messageFieldClass != null) {
                objArr[i3 + 1] = messageFieldClass;
            } else if (fieldInfo.getEnumVerifier() != null) {
                objArr[i3 + 1] = fieldInfo.getEnumVerifier();
            }
        }
        iId = fieldInfo.getType().id() + 51;
        iObjectFieldOffset = (int) UnsafeUtil.objectFieldOffset(oneof.getValueField());
        jObjectFieldOffset = UnsafeUtil.objectFieldOffset(oneof.getCaseField());
        iObjectFieldOffset2 = (int) jObjectFieldOffset;
        iNumberOfTrailingZeros = 0;
        iArr[i] = fieldInfo.getFieldNumber();
        int i5 = i + 1;
        if (fieldInfo.isEnforceUtf8()) {
            i2 = ENFORCE_UTF8_MASK;
        } else {
            i2 = 0;
        }
        iArr[i5] = (fieldInfo.isRequired() ? REQUIRED_MASK : 0) | i2 | (iId << 20) | iObjectFieldOffset;
        iArr[i + 2] = iObjectFieldOffset2 | (iNumberOfTrailingZeros << 20);
        messageFieldClass = fieldInfo.getMessageFieldClass();
        if (fieldInfo.getMapDefaultEntry() != null) {
            if (messageFieldClass != null) {
                objArr[((i / 3) * 2) + 1] = messageFieldClass;
                return;
            } else {
                if (fieldInfo.getEnumVerifier() != null) {
                    objArr[((i / 3) * 2) + 1] = fieldInfo.getEnumVerifier();
                    return;
                }
                return;
            }
        }
        i3 = (i / 3) * 2;
        objArr[i3] = fieldInfo.getMapDefaultEntry();
        if (messageFieldClass != null) {
            objArr[i3 + 1] = messageFieldClass;
        } else if (fieldInfo.getEnumVerifier() != null) {
            objArr[i3 + 1] = fieldInfo.getEnumVerifier();
        }
    }

    public static int type(int i) {
        return (i & FIELD_TYPE_MASK) >>> 20;
    }

    private int typeAndOffsetAt(int i) {
        return this.buffer[i + 1];
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:7:0x0021  */
    private void writeFieldsInAscendingOrderProto2(T t, Writer writer) {
        Iterator it;
        Map.Entry<?, ?> entry;
        int i;
        boolean z;
        if (this.hasExtensions) {
            FieldSet<T> extensions = this.extensionSchema.getExtensions(t);
            if (extensions.isEmpty()) {
                it = null;
                entry = null;
            } else {
                it = extensions.iterator();
                entry = (Map.Entry) it.next();
            }
        } else {
            it = null;
            entry = null;
        }
        int length = this.buffer.length;
        Unsafe unsafe = UNSAFE;
        int i2 = 1048575;
        Map.Entry<?, ?> entry2 = entry;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i4 < length) {
            int iTypeAndOffsetAt = typeAndOffsetAt(i4);
            int i6 = this.buffer[i4];
            int iType = type(iTypeAndOffsetAt);
            if (iType <= 17) {
                int i7 = this.buffer[i4 + 2];
                int i8 = i7 & i2;
                if (i8 != i3) {
                    i5 = unsafe.getInt(t, i8);
                } else {
                    i8 = i3;
                }
                i3 = i8;
                entry2 = entry2;
                i = 1 << (i7 >>> 20);
            } else {
                i = 0;
            }
            while (entry2 != null && this.extensionSchema.extensionNumber(entry2) <= i6) {
                this.extensionSchema.serializeExtension(writer, entry2);
                entry2 = it.hasNext() ? (Map.Entry) it.next() : null;
            }
            int i9 = i;
            long jOffset = offset(iTypeAndOffsetAt);
            switch (iType) {
                case 0:
                    if ((i5 & i9) != 0) {
                        writer.writeDouble(i6, UnsafeUtil.getDouble(t, jOffset));
                    }
                    break;
                case 1:
                    if ((i5 & i9) != 0) {
                        writer.writeFloat(i6, UnsafeUtil.getFloat(t, jOffset));
                    }
                    break;
                case 2:
                    if ((i5 & i9) != 0) {
                        writer.writeInt64(i6, unsafe.getLong(t, jOffset));
                    }
                    break;
                case 3:
                    if ((i5 & i9) != 0) {
                        writer.writeUInt64(i6, unsafe.getLong(t, jOffset));
                    }
                    break;
                case 4:
                    if ((i5 & i9) != 0) {
                        writer.writeInt32(i6, unsafe.getInt(t, jOffset));
                    }
                    break;
                case 5:
                    if ((i5 & i9) != 0) {
                        writer.writeFixed64(i6, unsafe.getLong(t, jOffset));
                    }
                    break;
                case 6:
                    if ((i5 & i9) != 0) {
                        writer.writeFixed32(i6, unsafe.getInt(t, jOffset));
                    }
                    break;
                case 7:
                    if ((i5 & i9) != 0) {
                        writer.writeBool(i6, UnsafeUtil.getBoolean(t, jOffset));
                    }
                    break;
                case 8:
                    if ((i5 & i9) != 0) {
                        writeString(i6, unsafe.getObject(t, jOffset), writer);
                    }
                    break;
                case 9:
                    if ((i5 & i9) != 0) {
                        writer.writeMessage(i6, unsafe.getObject(t, jOffset), getMessageFieldSchema(i4));
                    }
                    break;
                case 10:
                    if ((i5 & i9) != 0) {
                        writer.writeBytes(i6, (ByteString) unsafe.getObject(t, jOffset));
                    }
                    break;
                case 11:
                    if ((i5 & i9) != 0) {
                        writer.writeUInt32(i6, unsafe.getInt(t, jOffset));
                    }
                    break;
                case 12:
                    if ((i5 & i9) != 0) {
                        writer.writeEnum(i6, unsafe.getInt(t, jOffset));
                    }
                    break;
                case 13:
                    if ((i5 & i9) != 0) {
                        writer.writeSFixed32(i6, unsafe.getInt(t, jOffset));
                    }
                    break;
                case 14:
                    if ((i5 & i9) != 0) {
                        writer.writeSFixed64(i6, unsafe.getLong(t, jOffset));
                    }
                    break;
                case 15:
                    if ((i5 & i9) != 0) {
                        writer.writeSInt32(i6, unsafe.getInt(t, jOffset));
                    }
                    break;
                case 16:
                    if ((i5 & i9) != 0) {
                        writer.writeSInt64(i6, unsafe.getLong(t, jOffset));
                    }
                    break;
                case 17:
                    if ((i5 & i9) != 0) {
                        writer.writeGroup(i6, unsafe.getObject(t, jOffset), getMessageFieldSchema(i4));
                    }
                    break;
                case 18:
                    SchemaUtil.writeDoubleList(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, false);
                    break;
                case 19:
                    SchemaUtil.writeFloatList(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, false);
                    break;
                case 20:
                    SchemaUtil.writeInt64List(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, false);
                    break;
                case 21:
                    SchemaUtil.writeUInt64List(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, false);
                    break;
                case 22:
                    SchemaUtil.writeInt32List(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, false);
                    break;
                case 23:
                    SchemaUtil.writeFixed64List(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, false);
                    break;
                case 24:
                    SchemaUtil.writeFixed32List(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, false);
                    break;
                case 25:
                    SchemaUtil.writeBoolList(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, false);
                    break;
                case 26:
                    SchemaUtil.writeStringList(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer);
                    break;
                case 27:
                    SchemaUtil.writeMessageList(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, getMessageFieldSchema(i4));
                    break;
                case 28:
                    SchemaUtil.writeBytesList(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer);
                    break;
                case 29:
                    z = false;
                    SchemaUtil.writeUInt32List(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, false);
                    break;
                case 30:
                    z = false;
                    SchemaUtil.writeEnumList(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, false);
                    break;
                case 31:
                    z = false;
                    SchemaUtil.writeSFixed32List(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, false);
                    break;
                case 32:
                    z = false;
                    SchemaUtil.writeSFixed64List(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, false);
                    break;
                case 33:
                    z = false;
                    SchemaUtil.writeSInt32List(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, false);
                    break;
                case 34:
                    z = false;
                    SchemaUtil.writeSInt64List(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, false);
                    break;
                case 35:
                    SchemaUtil.writeDoubleList(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, true);
                    break;
                case 36:
                    SchemaUtil.writeFloatList(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, true);
                    break;
                case 37:
                    SchemaUtil.writeInt64List(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, true);
                    break;
                case 38:
                    SchemaUtil.writeUInt64List(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, true);
                    break;
                case 39:
                    SchemaUtil.writeInt32List(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, true);
                    break;
                case 40:
                    SchemaUtil.writeFixed64List(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, true);
                    break;
                case 41:
                    SchemaUtil.writeFixed32List(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, true);
                    break;
                case 42:
                    SchemaUtil.writeBoolList(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, true);
                    break;
                case 43:
                    SchemaUtil.writeUInt32List(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, true);
                    break;
                case 44:
                    SchemaUtil.writeEnumList(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, true);
                    break;
                case 45:
                    SchemaUtil.writeSFixed32List(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, true);
                    break;
                case 46:
                    SchemaUtil.writeSFixed64List(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, true);
                    break;
                case 47:
                    SchemaUtil.writeSInt32List(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, true);
                    break;
                case 48:
                    SchemaUtil.writeSInt64List(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, true);
                    break;
                case 49:
                    SchemaUtil.writeGroupList(this.buffer[i4], (List) unsafe.getObject(t, jOffset), writer, getMessageFieldSchema(i4));
                    break;
                case 50:
                    writeMapHelper(writer, i6, unsafe.getObject(t, jOffset), i4);
                    break;
                case 51:
                    if (isOneofPresent(t, i6, i4)) {
                        writer.writeDouble(i6, oneofDoubleAt(t, jOffset));
                    }
                    break;
                case 52:
                    if (isOneofPresent(t, i6, i4)) {
                        writer.writeFloat(i6, oneofFloatAt(t, jOffset));
                    }
                    break;
                case 53:
                    if (isOneofPresent(t, i6, i4)) {
                        writer.writeInt64(i6, oneofLongAt(t, jOffset));
                    }
                    break;
                case 54:
                    if (isOneofPresent(t, i6, i4)) {
                        writer.writeUInt64(i6, oneofLongAt(t, jOffset));
                    }
                    break;
                case 55:
                    if (isOneofPresent(t, i6, i4)) {
                        writer.writeInt32(i6, oneofIntAt(t, jOffset));
                    }
                    break;
                case 56:
                    if (isOneofPresent(t, i6, i4)) {
                        writer.writeFixed64(i6, oneofLongAt(t, jOffset));
                    }
                    break;
                case 57:
                    if (isOneofPresent(t, i6, i4)) {
                        writer.writeFixed32(i6, oneofIntAt(t, jOffset));
                    }
                    break;
                case 58:
                    if (isOneofPresent(t, i6, i4)) {
                        writer.writeBool(i6, oneofBooleanAt(t, jOffset));
                    }
                    break;
                case 59:
                    if (isOneofPresent(t, i6, i4)) {
                        writeString(i6, unsafe.getObject(t, jOffset), writer);
                    }
                    break;
                case 60:
                    if (isOneofPresent(t, i6, i4)) {
                        writer.writeMessage(i6, unsafe.getObject(t, jOffset), getMessageFieldSchema(i4));
                    }
                    break;
                case 61:
                    if (isOneofPresent(t, i6, i4)) {
                        writer.writeBytes(i6, (ByteString) unsafe.getObject(t, jOffset));
                    }
                    break;
                case 62:
                    if (isOneofPresent(t, i6, i4)) {
                        writer.writeUInt32(i6, oneofIntAt(t, jOffset));
                    }
                    break;
                case 63:
                    if (isOneofPresent(t, i6, i4)) {
                        writer.writeEnum(i6, oneofIntAt(t, jOffset));
                    }
                    break;
                case 64:
                    if (isOneofPresent(t, i6, i4)) {
                        writer.writeSFixed32(i6, oneofIntAt(t, jOffset));
                    }
                    break;
                case 65:
                    if (isOneofPresent(t, i6, i4)) {
                        writer.writeSFixed64(i6, oneofLongAt(t, jOffset));
                    }
                    break;
                case 66:
                    if (isOneofPresent(t, i6, i4)) {
                        writer.writeSInt32(i6, oneofIntAt(t, jOffset));
                    }
                    break;
                case 67:
                    if (isOneofPresent(t, i6, i4)) {
                        writer.writeSInt64(i6, oneofLongAt(t, jOffset));
                    }
                    break;
                case 68:
                    if (isOneofPresent(t, i6, i4)) {
                        writer.writeGroup(i6, unsafe.getObject(t, jOffset), getMessageFieldSchema(i4));
                    }
                    break;
                default:
                    break;
            }
            i4 += 3;
            i2 = 1048575;
        }
        Map.Entry<?, ?> entry3 = entry2;
        while (entry3 != null) {
            this.extensionSchema.serializeExtension(writer, entry3);
            entry3 = it.hasNext() ? (Map.Entry) it.next() : null;
        }
        UnknownFieldSchema<?, ?> unknownFieldSchema = this.unknownFieldSchema;
        unknownFieldSchema.writeTo(unknownFieldSchema.getFromMessage(t), writer);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x001c  */
    private void writeFieldsInAscendingOrderProto3(T t, Writer writer) {
        Iterator it;
        Map.Entry<?, ?> entry;
        if (this.hasExtensions) {
            FieldSet<T> extensions = this.extensionSchema.getExtensions(t);
            if (extensions.isEmpty()) {
                it = null;
                entry = null;
            } else {
                it = extensions.iterator();
                entry = (Map.Entry) it.next();
            }
        } else {
            it = null;
            entry = null;
        }
        int length = this.buffer.length;
        Map.Entry<?, ?> entry2 = entry;
        for (int i = 0; i < length; i += 3) {
            int iTypeAndOffsetAt = typeAndOffsetAt(i);
            int i2 = this.buffer[i];
            while (entry2 != null && this.extensionSchema.extensionNumber(entry2) <= i2) {
                this.extensionSchema.serializeExtension(writer, entry2);
                entry2 = it.hasNext() ? (Map.Entry) it.next() : null;
            }
            switch (type(iTypeAndOffsetAt)) {
                case 0:
                    if (isFieldPresent(t, i)) {
                        writer.writeDouble(i2, UnsafeUtil.getDouble(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 1:
                    if (isFieldPresent(t, i)) {
                        writer.writeFloat(i2, UnsafeUtil.getFloat(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 2:
                    if (isFieldPresent(t, i)) {
                        writer.writeInt64(i2, UnsafeUtil.getLong(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 3:
                    if (isFieldPresent(t, i)) {
                        writer.writeUInt64(i2, UnsafeUtil.getLong(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 4:
                    if (isFieldPresent(t, i)) {
                        writer.writeInt32(i2, UnsafeUtil.getInt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 5:
                    if (isFieldPresent(t, i)) {
                        writer.writeFixed64(i2, UnsafeUtil.getLong(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 6:
                    if (isFieldPresent(t, i)) {
                        writer.writeFixed32(i2, UnsafeUtil.getInt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 7:
                    if (isFieldPresent(t, i)) {
                        writer.writeBool(i2, UnsafeUtil.getBoolean(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 8:
                    if (isFieldPresent(t, i)) {
                        writeString(i2, UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer);
                    }
                    break;
                case 9:
                    if (isFieldPresent(t, i)) {
                        writer.writeMessage(i2, UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), getMessageFieldSchema(i));
                    }
                    break;
                case 10:
                    if (isFieldPresent(t, i)) {
                        writer.writeBytes(i2, (ByteString) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 11:
                    if (isFieldPresent(t, i)) {
                        writer.writeUInt32(i2, UnsafeUtil.getInt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 12:
                    if (isFieldPresent(t, i)) {
                        writer.writeEnum(i2, UnsafeUtil.getInt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 13:
                    if (isFieldPresent(t, i)) {
                        writer.writeSFixed32(i2, UnsafeUtil.getInt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 14:
                    if (isFieldPresent(t, i)) {
                        writer.writeSFixed64(i2, UnsafeUtil.getLong(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 15:
                    if (isFieldPresent(t, i)) {
                        writer.writeSInt32(i2, UnsafeUtil.getInt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 16:
                    if (isFieldPresent(t, i)) {
                        writer.writeSInt64(i2, UnsafeUtil.getLong(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 17:
                    if (isFieldPresent(t, i)) {
                        writer.writeGroup(i2, UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), getMessageFieldSchema(i));
                    }
                    break;
                case 18:
                    SchemaUtil.writeDoubleList(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 19:
                    SchemaUtil.writeFloatList(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 20:
                    SchemaUtil.writeInt64List(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 21:
                    SchemaUtil.writeUInt64List(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 22:
                    SchemaUtil.writeInt32List(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 23:
                    SchemaUtil.writeFixed64List(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 24:
                    SchemaUtil.writeFixed32List(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 25:
                    SchemaUtil.writeBoolList(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 26:
                    SchemaUtil.writeStringList(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer);
                    break;
                case 27:
                    SchemaUtil.writeMessageList(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, getMessageFieldSchema(i));
                    break;
                case 28:
                    SchemaUtil.writeBytesList(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer);
                    break;
                case 29:
                    SchemaUtil.writeUInt32List(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 30:
                    SchemaUtil.writeEnumList(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 31:
                    SchemaUtil.writeSFixed32List(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 32:
                    SchemaUtil.writeSFixed64List(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 33:
                    SchemaUtil.writeSInt32List(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 34:
                    SchemaUtil.writeSInt64List(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 35:
                    SchemaUtil.writeDoubleList(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 36:
                    SchemaUtil.writeFloatList(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 37:
                    SchemaUtil.writeInt64List(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 38:
                    SchemaUtil.writeUInt64List(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 39:
                    SchemaUtil.writeInt32List(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 40:
                    SchemaUtil.writeFixed64List(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 41:
                    SchemaUtil.writeFixed32List(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 42:
                    SchemaUtil.writeBoolList(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 43:
                    SchemaUtil.writeUInt32List(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 44:
                    SchemaUtil.writeEnumList(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 45:
                    SchemaUtil.writeSFixed32List(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 46:
                    SchemaUtil.writeSFixed64List(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 47:
                    SchemaUtil.writeSInt32List(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 48:
                    SchemaUtil.writeSInt64List(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 49:
                    SchemaUtil.writeGroupList(this.buffer[i], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, getMessageFieldSchema(i));
                    break;
                case 50:
                    writeMapHelper(writer, i2, UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), i);
                    break;
                case 51:
                    if (isOneofPresent(t, i2, i)) {
                        writer.writeDouble(i2, oneofDoubleAt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 52:
                    if (isOneofPresent(t, i2, i)) {
                        writer.writeFloat(i2, oneofFloatAt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 53:
                    if (isOneofPresent(t, i2, i)) {
                        writer.writeInt64(i2, oneofLongAt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 54:
                    if (isOneofPresent(t, i2, i)) {
                        writer.writeUInt64(i2, oneofLongAt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 55:
                    if (isOneofPresent(t, i2, i)) {
                        writer.writeInt32(i2, oneofIntAt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 56:
                    if (isOneofPresent(t, i2, i)) {
                        writer.writeFixed64(i2, oneofLongAt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 57:
                    if (isOneofPresent(t, i2, i)) {
                        writer.writeFixed32(i2, oneofIntAt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 58:
                    if (isOneofPresent(t, i2, i)) {
                        writer.writeBool(i2, oneofBooleanAt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 59:
                    if (isOneofPresent(t, i2, i)) {
                        writeString(i2, UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer);
                    }
                    break;
                case 60:
                    if (isOneofPresent(t, i2, i)) {
                        writer.writeMessage(i2, UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), getMessageFieldSchema(i));
                    }
                    break;
                case 61:
                    if (isOneofPresent(t, i2, i)) {
                        writer.writeBytes(i2, (ByteString) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 62:
                    if (isOneofPresent(t, i2, i)) {
                        writer.writeUInt32(i2, oneofIntAt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 63:
                    if (isOneofPresent(t, i2, i)) {
                        writer.writeEnum(i2, oneofIntAt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 64:
                    if (isOneofPresent(t, i2, i)) {
                        writer.writeSFixed32(i2, oneofIntAt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 65:
                    if (isOneofPresent(t, i2, i)) {
                        writer.writeSFixed64(i2, oneofLongAt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 66:
                    if (isOneofPresent(t, i2, i)) {
                        writer.writeSInt32(i2, oneofIntAt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 67:
                    if (isOneofPresent(t, i2, i)) {
                        writer.writeSInt64(i2, oneofLongAt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 68:
                    if (isOneofPresent(t, i2, i)) {
                        writer.writeGroup(i2, UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), getMessageFieldSchema(i));
                    }
                    break;
            }
        }
        while (entry2 != null) {
            this.extensionSchema.serializeExtension(writer, entry2);
            entry2 = it.hasNext() ? (Map.Entry) it.next() : null;
        }
        UnknownFieldSchema<?, ?> unknownFieldSchema = this.unknownFieldSchema;
        unknownFieldSchema.writeTo(unknownFieldSchema.getFromMessage(t), writer);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0025  */
    private void writeFieldsInDescendingOrder(T t, Writer writer) {
        Iterator itDescendingIterator;
        Map.Entry<?, ?> entry;
        UnknownFieldSchema<?, ?> unknownFieldSchema = this.unknownFieldSchema;
        unknownFieldSchema.writeTo(unknownFieldSchema.getFromMessage(t), writer);
        if (this.hasExtensions) {
            FieldSet<T> extensions = this.extensionSchema.getExtensions(t);
            if (extensions.isEmpty()) {
                itDescendingIterator = null;
                entry = null;
            } else {
                itDescendingIterator = extensions.descendingIterator();
                entry = (Map.Entry) itDescendingIterator.next();
            }
        } else {
            itDescendingIterator = null;
            entry = null;
        }
        for (int length = this.buffer.length - 3; length >= 0; length -= 3) {
            int iTypeAndOffsetAt = typeAndOffsetAt(length);
            int i = this.buffer[length];
            while (entry != null && this.extensionSchema.extensionNumber(entry) > i) {
                this.extensionSchema.serializeExtension(writer, entry);
                entry = itDescendingIterator.hasNext() ? (Map.Entry) itDescendingIterator.next() : null;
            }
            switch (type(iTypeAndOffsetAt)) {
                case 0:
                    if (isFieldPresent(t, length)) {
                        writer.writeDouble(i, UnsafeUtil.getDouble(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 1:
                    if (isFieldPresent(t, length)) {
                        writer.writeFloat(i, UnsafeUtil.getFloat(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 2:
                    if (isFieldPresent(t, length)) {
                        writer.writeInt64(i, UnsafeUtil.getLong(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 3:
                    if (isFieldPresent(t, length)) {
                        writer.writeUInt64(i, UnsafeUtil.getLong(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 4:
                    if (isFieldPresent(t, length)) {
                        writer.writeInt32(i, UnsafeUtil.getInt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 5:
                    if (isFieldPresent(t, length)) {
                        writer.writeFixed64(i, UnsafeUtil.getLong(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 6:
                    if (isFieldPresent(t, length)) {
                        writer.writeFixed32(i, UnsafeUtil.getInt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 7:
                    if (isFieldPresent(t, length)) {
                        writer.writeBool(i, UnsafeUtil.getBoolean(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 8:
                    if (isFieldPresent(t, length)) {
                        writeString(i, UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer);
                    }
                    break;
                case 9:
                    if (isFieldPresent(t, length)) {
                        writer.writeMessage(i, UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), getMessageFieldSchema(length));
                    }
                    break;
                case 10:
                    if (isFieldPresent(t, length)) {
                        writer.writeBytes(i, (ByteString) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 11:
                    if (isFieldPresent(t, length)) {
                        writer.writeUInt32(i, UnsafeUtil.getInt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 12:
                    if (isFieldPresent(t, length)) {
                        writer.writeEnum(i, UnsafeUtil.getInt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 13:
                    if (isFieldPresent(t, length)) {
                        writer.writeSFixed32(i, UnsafeUtil.getInt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 14:
                    if (isFieldPresent(t, length)) {
                        writer.writeSFixed64(i, UnsafeUtil.getLong(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 15:
                    if (isFieldPresent(t, length)) {
                        writer.writeSInt32(i, UnsafeUtil.getInt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 16:
                    if (isFieldPresent(t, length)) {
                        writer.writeSInt64(i, UnsafeUtil.getLong(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 17:
                    if (isFieldPresent(t, length)) {
                        writer.writeGroup(i, UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), getMessageFieldSchema(length));
                    }
                    break;
                case 18:
                    SchemaUtil.writeDoubleList(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 19:
                    SchemaUtil.writeFloatList(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 20:
                    SchemaUtil.writeInt64List(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 21:
                    SchemaUtil.writeUInt64List(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 22:
                    SchemaUtil.writeInt32List(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 23:
                    SchemaUtil.writeFixed64List(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 24:
                    SchemaUtil.writeFixed32List(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 25:
                    SchemaUtil.writeBoolList(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 26:
                    SchemaUtil.writeStringList(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer);
                    break;
                case 27:
                    SchemaUtil.writeMessageList(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, getMessageFieldSchema(length));
                    break;
                case 28:
                    SchemaUtil.writeBytesList(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer);
                    break;
                case 29:
                    SchemaUtil.writeUInt32List(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 30:
                    SchemaUtil.writeEnumList(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 31:
                    SchemaUtil.writeSFixed32List(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 32:
                    SchemaUtil.writeSFixed64List(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 33:
                    SchemaUtil.writeSInt32List(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 34:
                    SchemaUtil.writeSInt64List(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 35:
                    SchemaUtil.writeDoubleList(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 36:
                    SchemaUtil.writeFloatList(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 37:
                    SchemaUtil.writeInt64List(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 38:
                    SchemaUtil.writeUInt64List(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 39:
                    SchemaUtil.writeInt32List(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 40:
                    SchemaUtil.writeFixed64List(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 41:
                    SchemaUtil.writeFixed32List(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 42:
                    SchemaUtil.writeBoolList(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 43:
                    SchemaUtil.writeUInt32List(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 44:
                    SchemaUtil.writeEnumList(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 45:
                    SchemaUtil.writeSFixed32List(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 46:
                    SchemaUtil.writeSFixed64List(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 47:
                    SchemaUtil.writeSInt32List(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 48:
                    SchemaUtil.writeSInt64List(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 49:
                    SchemaUtil.writeGroupList(this.buffer[length], (List) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer, getMessageFieldSchema(length));
                    break;
                case 50:
                    writeMapHelper(writer, i, UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), length);
                    break;
                case 51:
                    if (isOneofPresent(t, i, length)) {
                        writer.writeDouble(i, oneofDoubleAt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 52:
                    if (isOneofPresent(t, i, length)) {
                        writer.writeFloat(i, oneofFloatAt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 53:
                    if (isOneofPresent(t, i, length)) {
                        writer.writeInt64(i, oneofLongAt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 54:
                    if (isOneofPresent(t, i, length)) {
                        writer.writeUInt64(i, oneofLongAt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 55:
                    if (isOneofPresent(t, i, length)) {
                        writer.writeInt32(i, oneofIntAt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 56:
                    if (isOneofPresent(t, i, length)) {
                        writer.writeFixed64(i, oneofLongAt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 57:
                    if (isOneofPresent(t, i, length)) {
                        writer.writeFixed32(i, oneofIntAt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 58:
                    if (isOneofPresent(t, i, length)) {
                        writer.writeBool(i, oneofBooleanAt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 59:
                    if (isOneofPresent(t, i, length)) {
                        writeString(i, UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), writer);
                    }
                    break;
                case 60:
                    if (isOneofPresent(t, i, length)) {
                        writer.writeMessage(i, UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), getMessageFieldSchema(length));
                    }
                    break;
                case 61:
                    if (isOneofPresent(t, i, length)) {
                        writer.writeBytes(i, (ByteString) UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 62:
                    if (isOneofPresent(t, i, length)) {
                        writer.writeUInt32(i, oneofIntAt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 63:
                    if (isOneofPresent(t, i, length)) {
                        writer.writeEnum(i, oneofIntAt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 64:
                    if (isOneofPresent(t, i, length)) {
                        writer.writeSFixed32(i, oneofIntAt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 65:
                    if (isOneofPresent(t, i, length)) {
                        writer.writeSFixed64(i, oneofLongAt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 66:
                    if (isOneofPresent(t, i, length)) {
                        writer.writeSInt32(i, oneofIntAt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 67:
                    if (isOneofPresent(t, i, length)) {
                        writer.writeSInt64(i, oneofLongAt(t, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 68:
                    if (isOneofPresent(t, i, length)) {
                        writer.writeGroup(i, UnsafeUtil.getObject(t, offset(iTypeAndOffsetAt)), getMessageFieldSchema(length));
                    }
                    break;
            }
        }
        while (entry != null) {
            this.extensionSchema.serializeExtension(writer, entry);
            entry = itDescendingIterator.hasNext() ? (Map.Entry) itDescendingIterator.next() : null;
        }
    }

    private <K, V> void writeMapHelper(Writer writer, int i, Object obj, int i2) {
        if (obj != null) {
            writer.writeMap(i, this.mapFieldSchema.forMapMetadata(this.objects[(i2 / 3) * 2]), this.mapFieldSchema.forMapData(obj));
        }
    }

    private void writeString(int i, Object obj, Writer writer) {
        if (obj instanceof String) {
            writer.writeString(i, (String) obj);
        } else {
            writer.writeBytes(i, (ByteString) obj);
        }
    }

    private <UT, UB> void writeUnknownInMessageTo(UnknownFieldSchema<UT, UB> unknownFieldSchema, T t, Writer writer) {
        unknownFieldSchema.writeTo(unknownFieldSchema.getFromMessage(t), writer);
    }

    @Override // com.google.protobuf.Schema
    public boolean equals(T t, T t2) {
        int length = this.buffer.length;
        for (int i = 0; i < length; i += 3) {
            if (!equals(t, t2, i)) {
                return false;
            }
        }
        if (!this.unknownFieldSchema.getFromMessage(t).equals(this.unknownFieldSchema.getFromMessage(t2))) {
            return false;
        }
        if (this.hasExtensions) {
            return this.extensionSchema.getExtensions(t).equals(this.extensionSchema.getExtensions(t2));
        }
        return true;
    }

    public int getSchemaSize() {
        return this.buffer.length * 3;
    }

    @Override // com.google.protobuf.Schema
    public int getSerializedSize(T t) {
        return this.proto3 ? getSerializedSizeProto3(t) : getSerializedSizeProto2(t);
    }

    @Override // com.google.protobuf.Schema
    public int hashCode(T t) {
        int i;
        int iHashLong;
        int length = this.buffer.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int iTypeAndOffsetAt = typeAndOffsetAt(i3);
            int i4 = this.buffer[i3];
            long jOffset = offset(iTypeAndOffsetAt);
            int iHashCode = 37;
            switch (type(iTypeAndOffsetAt)) {
                case 0:
                    i = i2 * 53;
                    iHashLong = Internal.hashLong(Double.doubleToLongBits(UnsafeUtil.getDouble(t, jOffset)));
                    i2 = iHashLong + i;
                    break;
                case 1:
                    i = i2 * 53;
                    iHashLong = Float.floatToIntBits(UnsafeUtil.getFloat(t, jOffset));
                    i2 = iHashLong + i;
                    break;
                case 2:
                    i = i2 * 53;
                    iHashLong = Internal.hashLong(UnsafeUtil.getLong(t, jOffset));
                    i2 = iHashLong + i;
                    break;
                case 3:
                    i = i2 * 53;
                    iHashLong = Internal.hashLong(UnsafeUtil.getLong(t, jOffset));
                    i2 = iHashLong + i;
                    break;
                case 4:
                    i = i2 * 53;
                    iHashLong = UnsafeUtil.getInt(t, jOffset);
                    i2 = iHashLong + i;
                    break;
                case 5:
                    i = i2 * 53;
                    iHashLong = Internal.hashLong(UnsafeUtil.getLong(t, jOffset));
                    i2 = iHashLong + i;
                    break;
                case 6:
                    i = i2 * 53;
                    iHashLong = UnsafeUtil.getInt(t, jOffset);
                    i2 = iHashLong + i;
                    break;
                case 7:
                    i = i2 * 53;
                    iHashLong = Internal.hashBoolean(UnsafeUtil.getBoolean(t, jOffset));
                    i2 = iHashLong + i;
                    break;
                case 8:
                    i = i2 * 53;
                    iHashLong = ((String) UnsafeUtil.getObject(t, jOffset)).hashCode();
                    i2 = iHashLong + i;
                    break;
                case 9:
                    Object object = UnsafeUtil.getObject(t, jOffset);
                    if (object != null) {
                        iHashCode = object.hashCode();
                    }
                    i2 = (i2 * 53) + iHashCode;
                    break;
                case 10:
                    i = i2 * 53;
                    iHashLong = UnsafeUtil.getObject(t, jOffset).hashCode();
                    i2 = iHashLong + i;
                    break;
                case 11:
                    i = i2 * 53;
                    iHashLong = UnsafeUtil.getInt(t, jOffset);
                    i2 = iHashLong + i;
                    break;
                case 12:
                    i = i2 * 53;
                    iHashLong = UnsafeUtil.getInt(t, jOffset);
                    i2 = iHashLong + i;
                    break;
                case 13:
                    i = i2 * 53;
                    iHashLong = UnsafeUtil.getInt(t, jOffset);
                    i2 = iHashLong + i;
                    break;
                case 14:
                    i = i2 * 53;
                    iHashLong = Internal.hashLong(UnsafeUtil.getLong(t, jOffset));
                    i2 = iHashLong + i;
                    break;
                case 15:
                    i = i2 * 53;
                    iHashLong = UnsafeUtil.getInt(t, jOffset);
                    i2 = iHashLong + i;
                    break;
                case 16:
                    i = i2 * 53;
                    iHashLong = Internal.hashLong(UnsafeUtil.getLong(t, jOffset));
                    i2 = iHashLong + i;
                    break;
                case 17:
                    Object object2 = UnsafeUtil.getObject(t, jOffset);
                    if (object2 != null) {
                        iHashCode = object2.hashCode();
                    }
                    i2 = (i2 * 53) + iHashCode;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i = i2 * 53;
                    iHashLong = UnsafeUtil.getObject(t, jOffset).hashCode();
                    i2 = iHashLong + i;
                    break;
                case 50:
                    i = i2 * 53;
                    iHashLong = UnsafeUtil.getObject(t, jOffset).hashCode();
                    i2 = iHashLong + i;
                    break;
                case 51:
                    if (isOneofPresent(t, i4, i3)) {
                        i = i2 * 53;
                        iHashLong = Internal.hashLong(Double.doubleToLongBits(oneofDoubleAt(t, jOffset)));
                        i2 = iHashLong + i;
                    }
                    break;
                case 52:
                    if (isOneofPresent(t, i4, i3)) {
                        i = i2 * 53;
                        iHashLong = Float.floatToIntBits(oneofFloatAt(t, jOffset));
                        i2 = iHashLong + i;
                    }
                    break;
                case 53:
                    if (isOneofPresent(t, i4, i3)) {
                        i = i2 * 53;
                        iHashLong = Internal.hashLong(oneofLongAt(t, jOffset));
                        i2 = iHashLong + i;
                    }
                    break;
                case 54:
                    if (isOneofPresent(t, i4, i3)) {
                        i = i2 * 53;
                        iHashLong = Internal.hashLong(oneofLongAt(t, jOffset));
                        i2 = iHashLong + i;
                    }
                    break;
                case 55:
                    if (isOneofPresent(t, i4, i3)) {
                        i = i2 * 53;
                        iHashLong = oneofIntAt(t, jOffset);
                        i2 = iHashLong + i;
                    }
                    break;
                case 56:
                    if (isOneofPresent(t, i4, i3)) {
                        i = i2 * 53;
                        iHashLong = Internal.hashLong(oneofLongAt(t, jOffset));
                        i2 = iHashLong + i;
                    }
                    break;
                case 57:
                    if (isOneofPresent(t, i4, i3)) {
                        i = i2 * 53;
                        iHashLong = oneofIntAt(t, jOffset);
                        i2 = iHashLong + i;
                    }
                    break;
                case 58:
                    if (isOneofPresent(t, i4, i3)) {
                        i = i2 * 53;
                        iHashLong = Internal.hashBoolean(oneofBooleanAt(t, jOffset));
                        i2 = iHashLong + i;
                    }
                    break;
                case 59:
                    if (isOneofPresent(t, i4, i3)) {
                        i = i2 * 53;
                        iHashLong = ((String) UnsafeUtil.getObject(t, jOffset)).hashCode();
                        i2 = iHashLong + i;
                    }
                    break;
                case 60:
                    if (isOneofPresent(t, i4, i3)) {
                        i = i2 * 53;
                        iHashLong = UnsafeUtil.getObject(t, jOffset).hashCode();
                        i2 = iHashLong + i;
                    }
                    break;
                case 61:
                    if (isOneofPresent(t, i4, i3)) {
                        i = i2 * 53;
                        iHashLong = UnsafeUtil.getObject(t, jOffset).hashCode();
                        i2 = iHashLong + i;
                    }
                    break;
                case 62:
                    if (isOneofPresent(t, i4, i3)) {
                        i = i2 * 53;
                        iHashLong = oneofIntAt(t, jOffset);
                        i2 = iHashLong + i;
                    }
                    break;
                case 63:
                    if (isOneofPresent(t, i4, i3)) {
                        i = i2 * 53;
                        iHashLong = oneofIntAt(t, jOffset);
                        i2 = iHashLong + i;
                    }
                    break;
                case 64:
                    if (isOneofPresent(t, i4, i3)) {
                        i = i2 * 53;
                        iHashLong = oneofIntAt(t, jOffset);
                        i2 = iHashLong + i;
                    }
                    break;
                case 65:
                    if (isOneofPresent(t, i4, i3)) {
                        i = i2 * 53;
                        iHashLong = Internal.hashLong(oneofLongAt(t, jOffset));
                        i2 = iHashLong + i;
                    }
                    break;
                case 66:
                    if (isOneofPresent(t, i4, i3)) {
                        i = i2 * 53;
                        iHashLong = oneofIntAt(t, jOffset);
                        i2 = iHashLong + i;
                    }
                    break;
                case 67:
                    if (isOneofPresent(t, i4, i3)) {
                        i = i2 * 53;
                        iHashLong = Internal.hashLong(oneofLongAt(t, jOffset));
                        i2 = iHashLong + i;
                    }
                    break;
                case 68:
                    if (isOneofPresent(t, i4, i3)) {
                        i = i2 * 53;
                        iHashLong = UnsafeUtil.getObject(t, jOffset).hashCode();
                        i2 = iHashLong + i;
                    }
                    break;
            }
        }
        int iHashCode2 = this.unknownFieldSchema.getFromMessage(t).hashCode() + (i2 * 53);
        return this.hasExtensions ? (iHashCode2 * 53) + this.extensionSchema.getExtensions(t).hashCode() : iHashCode2;
    }

    /* JADX WARN: Code duplicated, block: B:40:0x008c  */
    /* JADX WARN: Code duplicated, block: B:59:0x0092 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:64:0x00ad A[SYNTHETIC] */
    @Override // com.google.protobuf.Schema
    public final boolean isInitialized(T t) {
        int i;
        int i2;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i5 < this.checkInitializedCount) {
            int i6 = this.intArray[i5];
            int i7 = this.buffer[i6];
            int iTypeAndOffsetAt = typeAndOffsetAt(i6);
            int i8 = this.buffer[i6 + 2];
            int i9 = i8 & 1048575;
            int i10 = 1 << (i8 >>> 20);
            if (i9 != i3) {
                i2 = i9 != 1048575 ? UNSAFE.getInt(t, i9) : i4;
                i = i9;
            } else {
                i = i3;
                i2 = i4;
            }
            if (isRequired(iTypeAndOffsetAt) && !isFieldPresent(t, i6, i, i2, i10)) {
                return false;
            }
            int iType = type(iTypeAndOffsetAt);
            if (iType == 9 || iType == 17) {
                if (isFieldPresent(t, i6, i, i2, i10) && !isInitialized(t, iTypeAndOffsetAt, getMessageFieldSchema(i6))) {
                    return false;
                }
            } else if (iType == 27) {
                if (!isListInitialized(t, iTypeAndOffsetAt, i6)) {
                    return false;
                }
            } else if (iType == 60 || iType == 68) {
                if (isOneofPresent(t, i7, i6) && !isInitialized(t, iTypeAndOffsetAt, getMessageFieldSchema(i6))) {
                    return false;
                }
            } else if (iType != 49) {
                if (iType == 50 && !isMapInitialized(t, iTypeAndOffsetAt, i6)) {
                    return false;
                }
            } else if (!isListInitialized(t, iTypeAndOffsetAt, i6)) {
                return false;
            }
            i5++;
            i3 = i;
            i4 = i2;
        }
        return !this.hasExtensions || this.extensionSchema.getExtensions(t).isInitialized();
    }

    @Override // com.google.protobuf.Schema
    public void makeImmutable(T t) {
        int i;
        int i2 = this.checkInitializedCount;
        while (true) {
            i = this.repeatedFieldOffsetStart;
            if (i2 >= i) {
                break;
            }
            long jOffset = offset(typeAndOffsetAt(this.intArray[i2]));
            Object object = UnsafeUtil.getObject(t, jOffset);
            if (object != null) {
                UnsafeUtil.MEMORY_ACCESSOR.putObject(t, jOffset, this.mapFieldSchema.toImmutable(object));
            }
            i2++;
        }
        int length = this.intArray.length;
        while (i < length) {
            this.listFieldSchema.makeImmutableListAt(t, this.intArray[i]);
            i++;
        }
        this.unknownFieldSchema.makeImmutable(t);
        if (this.hasExtensions) {
            this.extensionSchema.makeImmutable(t);
        }
    }

    @Override // com.google.protobuf.Schema
    public void mergeFrom(T t, T t2) {
        if (t2 == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < this.buffer.length; i += 3) {
            mergeSingleField(t, t2, i);
        }
        SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, t, t2);
        if (this.hasExtensions) {
            SchemaUtil.mergeExtensions(this.extensionSchema, t, t2);
        }
    }

    @Override // com.google.protobuf.Schema
    public T newInstance() {
        return (T) this.newInstanceSchema.newInstance(this.defaultInstance);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:25:0x0093. Please report as an issue. */
    public int parseProto2Message(T t, byte[] bArr, int i, int i2, int i3, ArrayDecoders.Registers registers) {
        Unsafe unsafe;
        MessageSchema<T> messageSchema;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        T t2;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        ArrayDecoders.Registers registers2;
        int i14;
        int i15;
        int i16;
        T t3;
        T t4;
        int i17;
        T t5;
        int i18;
        int i19;
        MessageSchema<T> messageSchema2 = this;
        T t6 = t;
        byte[] bArr2 = bArr;
        i2 = i2;
        int i20 = i3;
        ArrayDecoders.Registers registers3 = registers;
        Unsafe unsafe2 = UNSAFE;
        int iDecodeUnknownField = i;
        int i21 = 0;
        int i22 = 0;
        int i23 = 0;
        int i24 = -1;
        int i25 = 1048575;
        while (true) {
            if (iDecodeUnknownField < i2) {
                int i26 = iDecodeUnknownField + 1;
                byte b2 = bArr2[iDecodeUnknownField];
                if (b2 < 0) {
                    int iDecodeVarint32 = ArrayDecoders.decodeVarint32(b2, bArr2, i26, registers3);
                    i9 = registers3.int1;
                    i26 = iDecodeVarint32;
                } else {
                    i9 = b2;
                }
                int i27 = i9 >>> 3;
                int i28 = i9 & 7;
                int iPositionForFieldNumber = i27 > i24 ? messageSchema2.positionForFieldNumber(i27, i21 / 3) : messageSchema2.positionForFieldNumber(i27);
                if (iPositionForFieldNumber == -1) {
                    i10 = i27;
                    i4 = i26;
                    i5 = i9;
                    i11 = i23;
                    i12 = i25;
                    unsafe = unsafe2;
                    i3 = i20;
                    i13 = 0;
                } else {
                    int i29 = messageSchema2.buffer[iPositionForFieldNumber + 1];
                    int iType = type(i29);
                    long jOffset = offset(i29);
                    int i30 = i9;
                    if (iType <= 17) {
                        int i31 = messageSchema2.buffer[iPositionForFieldNumber + 2];
                        int i32 = 1 << (i31 >>> 20);
                        int i33 = i31 & 1048575;
                        if (i33 != i25) {
                            if (i25 != 1048575) {
                                unsafe2.putInt(t6, i25, i23);
                            }
                            i14 = unsafe2.getInt(t6, i33);
                            i12 = i33;
                        } else {
                            iPositionForFieldNumber = iPositionForFieldNumber;
                            i12 = i25;
                            i14 = i23;
                        }
                        switch (iType) {
                            case 0:
                                t3 = t6;
                                i15 = iPositionForFieldNumber;
                                i10 = i27;
                                bArr2 = bArr;
                                i16 = i30;
                                if (i28 == 1) {
                                    UnsafeUtil.putDouble(t3, jOffset, ArrayDecoders.decodeDouble(bArr2, i26));
                                    iDecodeUnknownField = i26 + 8;
                                    i23 = i14 | i32;
                                    t6 = t3;
                                    i21 = i15;
                                    i22 = i16;
                                    i24 = i10;
                                    i25 = i12;
                                    i20 = i3;
                                } else {
                                    i4 = i26;
                                    i11 = i14;
                                    i13 = i15;
                                    unsafe = unsafe2;
                                    i5 = i16;
                                    i3 = i3;
                                }
                                break;
                            case 1:
                                t3 = t6;
                                i15 = iPositionForFieldNumber;
                                i10 = i27;
                                bArr2 = bArr;
                                i16 = i30;
                                if (i28 == 5) {
                                    UnsafeUtil.MEMORY_ACCESSOR.putFloat(t3, jOffset, ArrayDecoders.decodeFloat(bArr2, i26));
                                    iDecodeUnknownField = i26 + 4;
                                    i23 = i14 | i32;
                                    t6 = t3;
                                    i21 = i15;
                                    i22 = i16;
                                    i24 = i10;
                                    i25 = i12;
                                    i20 = i3;
                                } else {
                                    i4 = i26;
                                    i11 = i14;
                                    i13 = i15;
                                    unsafe = unsafe2;
                                    i5 = i16;
                                    i3 = i3;
                                }
                                break;
                            case 2:
                            case 3:
                                T t7 = t6;
                                i15 = iPositionForFieldNumber;
                                i10 = i27;
                                bArr2 = bArr;
                                i16 = i30;
                                if (i28 == 0) {
                                    int iDecodeVarint64 = ArrayDecoders.decodeVarint64(bArr2, i26, registers3);
                                    t4 = t7;
                                    unsafe2.putLong(t, jOffset, registers3.long1);
                                    i23 = i14 | i32;
                                    iDecodeUnknownField = iDecodeVarint64;
                                    i21 = i15;
                                    i22 = i16;
                                    t6 = t4;
                                    i24 = i10;
                                    i25 = i12;
                                    i2 = i2;
                                    i20 = i3;
                                } else {
                                    i4 = i26;
                                    i11 = i14;
                                    i13 = i15;
                                    unsafe = unsafe2;
                                    i5 = i16;
                                    i3 = i3;
                                }
                                break;
                            case 4:
                            case 11:
                                t3 = t6;
                                i15 = iPositionForFieldNumber;
                                i10 = i27;
                                bArr2 = bArr;
                                i16 = i30;
                                if (i28 == 0) {
                                    iDecodeUnknownField = ArrayDecoders.decodeVarint32(bArr2, i26, registers3);
                                    unsafe2.putInt(t3, jOffset, registers3.int1);
                                    i23 = i14 | i32;
                                    t6 = t3;
                                    i21 = i15;
                                    i22 = i16;
                                    i24 = i10;
                                    i25 = i12;
                                    i20 = i3;
                                } else {
                                    i4 = i26;
                                    i11 = i14;
                                    i13 = i15;
                                    unsafe = unsafe2;
                                    i5 = i16;
                                    i3 = i3;
                                }
                                break;
                            case 5:
                            case 14:
                                T t8 = t6;
                                i15 = iPositionForFieldNumber;
                                i10 = i27;
                                bArr2 = bArr;
                                i16 = i30;
                                if (i28 == 1) {
                                    t4 = t8;
                                    unsafe2.putLong(t, jOffset, ArrayDecoders.decodeFixed64(bArr2, i26));
                                    iDecodeUnknownField = i26 + 8;
                                    i23 = i14 | i32;
                                    i21 = i15;
                                    i22 = i16;
                                    t6 = t4;
                                    i24 = i10;
                                    i25 = i12;
                                    i2 = i2;
                                    i20 = i3;
                                } else {
                                    i4 = i26;
                                    i11 = i14;
                                    i13 = i15;
                                    unsafe = unsafe2;
                                    i5 = i16;
                                    i3 = i3;
                                }
                                break;
                            case 6:
                            case 13:
                                i17 = i2;
                                t5 = t6;
                                i15 = iPositionForFieldNumber;
                                i10 = i27;
                                bArr2 = bArr;
                                i16 = i30;
                                if (i28 == 5) {
                                    unsafe2.putInt(t5, jOffset, ArrayDecoders.decodeFixed32(bArr2, i26));
                                    iDecodeUnknownField = i26 + 4;
                                    int i34 = i14 | i32;
                                    t6 = t5;
                                    i2 = i17;
                                    i21 = i15;
                                    i22 = i16;
                                    i25 = i12;
                                    i20 = i3;
                                    i23 = i34;
                                    i24 = i10;
                                } else {
                                    i4 = i26;
                                    i11 = i14;
                                    i13 = i15;
                                    unsafe = unsafe2;
                                    i5 = i16;
                                    i3 = i3;
                                }
                                break;
                            case 7:
                                i17 = i2;
                                t5 = t6;
                                i15 = iPositionForFieldNumber;
                                i10 = i27;
                                bArr2 = bArr;
                                i16 = i30;
                                if (i28 == 0) {
                                    iDecodeUnknownField = ArrayDecoders.decodeVarint64(bArr2, i26, registers3);
                                    UnsafeUtil.MEMORY_ACCESSOR.putBoolean(t5, jOffset, registers3.long1 != 0);
                                    int i35 = i14 | i32;
                                    t6 = t5;
                                    i2 = i17;
                                    i21 = i15;
                                    i22 = i16;
                                    i25 = i12;
                                    i20 = i3;
                                    i23 = i35;
                                    i24 = i10;
                                } else {
                                    i4 = i26;
                                    i11 = i14;
                                    i13 = i15;
                                    unsafe = unsafe2;
                                    i5 = i16;
                                    i3 = i3;
                                }
                                break;
                            case 8:
                                i17 = i2;
                                t5 = t6;
                                i15 = iPositionForFieldNumber;
                                i10 = i27;
                                bArr2 = bArr;
                                i16 = i30;
                                if (i28 == 2) {
                                    iDecodeUnknownField = (i29 & ENFORCE_UTF8_MASK) == 0 ? ArrayDecoders.decodeString(bArr2, i26, registers3) : ArrayDecoders.decodeStringRequireUtf8(bArr2, i26, registers3);
                                    unsafe2.putObject(t5, jOffset, registers3.object1);
                                    int i36 = i14 | i32;
                                    t6 = t5;
                                    i2 = i17;
                                    i21 = i15;
                                    i22 = i16;
                                    i25 = i12;
                                    i20 = i3;
                                    i23 = i36;
                                    i24 = i10;
                                } else {
                                    i4 = i26;
                                    i11 = i14;
                                    i13 = i15;
                                    unsafe = unsafe2;
                                    i5 = i16;
                                    i3 = i3;
                                }
                                break;
                            case 9:
                                t5 = t6;
                                i15 = iPositionForFieldNumber;
                                i10 = i27;
                                i16 = i30;
                                bArr2 = bArr;
                                if (i28 == 2) {
                                    i17 = i2;
                                    iDecodeUnknownField = ArrayDecoders.decodeMessageField(messageSchema2.getMessageFieldSchema(i15), bArr2, i26, i17, registers3);
                                    if ((i14 & i32) == 0) {
                                        unsafe2.putObject(t5, jOffset, registers3.object1);
                                    } else {
                                        unsafe2.putObject(t5, jOffset, Internal.mergeMessage(unsafe2.getObject(t5, jOffset), registers3.object1));
                                    }
                                    int i37 = i14 | i32;
                                    t6 = t5;
                                    i2 = i17;
                                    i21 = i15;
                                    i22 = i16;
                                    i25 = i12;
                                    i20 = i3;
                                    i23 = i37;
                                    i24 = i10;
                                } else {
                                    i4 = i26;
                                    i11 = i14;
                                    i13 = i15;
                                    unsafe = unsafe2;
                                    i5 = i16;
                                    i3 = i3;
                                }
                                break;
                            case 10:
                                t3 = t6;
                                i15 = iPositionForFieldNumber;
                                i10 = i27;
                                i16 = i30;
                                bArr2 = bArr;
                                if (i28 == 2) {
                                    iDecodeUnknownField = ArrayDecoders.decodeBytes(bArr2, i26, registers3);
                                    unsafe2.putObject(t3, jOffset, registers3.object1);
                                    i23 = i14 | i32;
                                    t6 = t3;
                                    i21 = i15;
                                    i22 = i16;
                                    i24 = i10;
                                    i25 = i12;
                                    i20 = i3;
                                } else {
                                    i4 = i26;
                                    i11 = i14;
                                    i13 = i15;
                                    unsafe = unsafe2;
                                    i5 = i16;
                                    i3 = i3;
                                }
                                break;
                            case 12:
                                t3 = t6;
                                i15 = iPositionForFieldNumber;
                                i10 = i27;
                                i16 = i30;
                                bArr2 = bArr;
                                if (i28 == 0) {
                                    iDecodeUnknownField = ArrayDecoders.decodeVarint32(bArr2, i26, registers3);
                                    int i38 = registers3.int1;
                                    Internal.EnumVerifier enumFieldVerifier = messageSchema2.getEnumFieldVerifier(i15);
                                    if (enumFieldVerifier == null || enumFieldVerifier.isInRange(i38)) {
                                        unsafe2.putInt(t3, jOffset, i38);
                                        i23 = i14 | i32;
                                        t6 = t3;
                                    } else {
                                        getMutableUnknownFields(t).storeField(i16, Long.valueOf(i38));
                                        t6 = t3;
                                        i23 = i14;
                                    }
                                    i21 = i15;
                                    i22 = i16;
                                    i24 = i10;
                                    i25 = i12;
                                    i20 = i3;
                                } else {
                                    i4 = i26;
                                    i11 = i14;
                                    i13 = i15;
                                    unsafe = unsafe2;
                                    i5 = i16;
                                    i3 = i3;
                                }
                                break;
                            case 15:
                                i15 = iPositionForFieldNumber;
                                i16 = i30;
                                bArr2 = bArr;
                                i10 = i27;
                                if (i28 == 0) {
                                    iDecodeUnknownField = ArrayDecoders.decodeVarint32(bArr2, i26, registers3);
                                    t3 = t;
                                    unsafe2.putInt(t3, jOffset, CodedInputStream.decodeZigZag32(registers3.int1));
                                    i23 = i14 | i32;
                                    t6 = t3;
                                    i21 = i15;
                                    i22 = i16;
                                    i24 = i10;
                                    i25 = i12;
                                    i20 = i3;
                                } else {
                                    i4 = i26;
                                    i11 = i14;
                                    i13 = i15;
                                    unsafe = unsafe2;
                                    i5 = i16;
                                    i3 = i3;
                                }
                                break;
                            case 16:
                                i15 = iPositionForFieldNumber;
                                i10 = i27;
                                if (i28 == 0) {
                                    bArr2 = bArr;
                                    int iDecodeVarint65 = ArrayDecoders.decodeVarint64(bArr2, i26, registers3);
                                    i16 = i30;
                                    unsafe2.putLong(t, jOffset, CodedInputStream.decodeZigZag64(registers3.long1));
                                    i23 = i14 | i32;
                                    t6 = t;
                                    iDecodeUnknownField = iDecodeVarint65;
                                    i21 = i15;
                                    i22 = i16;
                                    i24 = i10;
                                    i25 = i12;
                                    i20 = i3;
                                } else {
                                    i16 = i30;
                                    i4 = i26;
                                    i11 = i14;
                                    i13 = i15;
                                    unsafe = unsafe2;
                                    i5 = i16;
                                    i3 = i3;
                                }
                                break;
                            case 17:
                                if (i28 == 3) {
                                    int i39 = iPositionForFieldNumber;
                                    iDecodeUnknownField = ArrayDecoders.decodeGroupField(messageSchema2.getMessageFieldSchema(i39), bArr, i26, i2, (i27 << 3) | 4, registers);
                                    if ((i14 & i32) == 0) {
                                        unsafe2.putObject(t6, jOffset, registers3.object1);
                                    } else {
                                        unsafe2.putObject(t6, jOffset, Internal.mergeMessage(unsafe2.getObject(t6, jOffset), registers3.object1));
                                    }
                                    i23 = i14 | i32;
                                    bArr2 = bArr;
                                    i2 = i2;
                                    i20 = i3;
                                    i22 = i30;
                                    i21 = i39;
                                    i24 = i27;
                                    i25 = i12;
                                } else {
                                    i15 = iPositionForFieldNumber;
                                    i10 = i27;
                                    i16 = i30;
                                    i4 = i26;
                                    i11 = i14;
                                    i13 = i15;
                                    unsafe = unsafe2;
                                    i5 = i16;
                                    i3 = i3;
                                }
                                break;
                            default:
                                i15 = iPositionForFieldNumber;
                                i16 = i30;
                                i10 = i27;
                                i4 = i26;
                                i11 = i14;
                                i13 = i15;
                                unsafe = unsafe2;
                                i5 = i16;
                                i3 = i3;
                                break;
                        }
                    } else {
                        i10 = i27;
                        T t9 = t6;
                        bArr2 = bArr;
                        if (iType != 27) {
                            i13 = iPositionForFieldNumber;
                            i11 = i23;
                            i12 = i25;
                            if (iType <= 49) {
                                int i40 = i26;
                                unsafe = unsafe2;
                                iDecodeUnknownField = parseRepeatedField(t, bArr, i26, i2, i30, i10, i28, i13, i29, iType, jOffset, registers);
                                if (iDecodeUnknownField != i40) {
                                    i19 = i30;
                                    messageSchema2 = this;
                                    t6 = t;
                                    bArr2 = bArr;
                                    i2 = i2;
                                    i20 = i3;
                                    registers3 = registers;
                                    i24 = i10;
                                    i22 = i19;
                                    i21 = i13;
                                    i23 = i11;
                                    i25 = i12;
                                    unsafe2 = unsafe;
                                } else {
                                    i19 = i30;
                                    i4 = iDecodeUnknownField;
                                    i5 = i19;
                                }
                            } else {
                                i18 = i26;
                                unsafe = unsafe2;
                                if (iType != 50) {
                                    iDecodeUnknownField = parseOneofField(t, bArr, i18, i2, i19, i10, i28, i29, iType, jOffset, i13, registers);
                                    if (iDecodeUnknownField != i18) {
                                        i19 = i30;
                                        messageSchema2 = this;
                                        t6 = t;
                                        bArr2 = bArr;
                                        i2 = i2;
                                        i20 = i3;
                                        registers3 = registers;
                                        i24 = i10;
                                        i22 = i19;
                                        i21 = i13;
                                        i23 = i11;
                                        i25 = i12;
                                        unsafe2 = unsafe;
                                    } else {
                                        i19 = i30;
                                        i4 = iDecodeUnknownField;
                                        i5 = i19;
                                    }
                                } else if (i28 == 2) {
                                    iDecodeUnknownField = parseMapField(t, bArr, i18, i2, i13, jOffset, registers);
                                    if (iDecodeUnknownField != i18) {
                                        i19 = i30;
                                        messageSchema2 = this;
                                        t6 = t;
                                        bArr2 = bArr;
                                        i2 = i2;
                                        i20 = i3;
                                        registers3 = registers;
                                        i24 = i10;
                                        i22 = i19;
                                        i21 = i13;
                                        i23 = i11;
                                        i25 = i12;
                                        unsafe2 = unsafe;
                                    } else {
                                        i19 = i30;
                                        i4 = iDecodeUnknownField;
                                        i5 = i19;
                                    }
                                } else {
                                    i19 = i30;
                                }
                            }
                        } else if (i28 == 2) {
                            Internal.ProtobufList protobufListMutableCopyWithCapacity2 = (Internal.ProtobufList) unsafe2.getObject(t9, jOffset);
                            if (!protobufListMutableCopyWithCapacity2.isModifiable()) {
                                int size = protobufListMutableCopyWithCapacity2.size();
                                protobufListMutableCopyWithCapacity2 = protobufListMutableCopyWithCapacity2.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
                                unsafe2.putObject(t9, jOffset, protobufListMutableCopyWithCapacity2);
                            }
                            i12 = i25;
                            iDecodeUnknownField = ArrayDecoders.decodeMessageList(messageSchema2.getMessageFieldSchema(iPositionForFieldNumber), i30, bArr, i26, i2, protobufListMutableCopyWithCapacity2, registers);
                            t6 = t;
                            i2 = i2;
                            i22 = i30;
                            i24 = i10;
                            i21 = iPositionForFieldNumber;
                            i23 = i23;
                            i25 = i12;
                            i20 = i3;
                        } else {
                            i13 = iPositionForFieldNumber;
                            i11 = i23;
                            i12 = i25;
                            i18 = i26;
                            unsafe = unsafe2;
                            i19 = i30;
                        }
                        i4 = i18;
                        i5 = i19;
                    }
                }
                if (i5 != i3 || i3 == 0) {
                    if (this.hasExtensions) {
                        registers2 = registers;
                        if (registers2.extensionRegistry != ExtensionRegistryLite.getEmptyRegistry()) {
                            iDecodeUnknownField = ArrayDecoders.decodeExtensionOrUnknownField(i5, bArr, i4, i2, t, this.defaultInstance, this.unknownFieldSchema, registers);
                        }
                        t6 = t;
                        bArr2 = bArr;
                        i2 = i2;
                        i22 = i5;
                        messageSchema2 = this;
                        registers3 = registers2;
                        i24 = i10;
                        i21 = i13;
                        i23 = i11;
                        i25 = i12;
                        unsafe2 = unsafe;
                        i20 = i3;
                    } else {
                        registers2 = registers;
                    }
                    iDecodeUnknownField = ArrayDecoders.decodeUnknownField(i5, bArr, i4, i2, getMutableUnknownFields(t), registers);
                    t6 = t;
                    bArr2 = bArr;
                    i2 = i2;
                    i22 = i5;
                    messageSchema2 = this;
                    registers3 = registers2;
                    i24 = i10;
                    i21 = i13;
                    i23 = i11;
                    i25 = i12;
                    unsafe2 = unsafe;
                    i20 = i3;
                } else {
                    i8 = 1048575;
                    messageSchema = this;
                    i6 = i11;
                    i7 = i12;
                }
            } else {
                unsafe = unsafe2;
                i3 = i20;
                messageSchema = messageSchema2;
                i4 = iDecodeUnknownField;
                i5 = i22;
                i6 = i23;
                i7 = i25;
                i8 = 1048575;
            }
        }
        if (i7 != i8) {
            long j = i7;
            t2 = t;
            unsafe.putInt(t2, j, i6);
        } else {
            t2 = t;
        }
        UnknownFieldSetLite unknownFieldSetLite = null;
        for (int i41 = messageSchema.checkInitializedCount; i41 < messageSchema.repeatedFieldOffsetStart; i41++) {
            unknownFieldSetLite = (UnknownFieldSetLite) messageSchema.filterMapUnknownEnumValues(t2, messageSchema.intArray[i41], unknownFieldSetLite, messageSchema.unknownFieldSchema);
        }
        if (unknownFieldSetLite != null) {
            messageSchema.unknownFieldSchema.setBuilderToMessage(t2, unknownFieldSetLite);
        }
        if (i3 == 0) {
            if (i4 != i2) {
                throw InvalidProtocolBufferException.parseFailure();
            }
        } else if (i4 > i2 || i5 != i3) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        return i4;
    }

    @Override // com.google.protobuf.Schema
    public void writeTo(T t, Writer writer) {
        if (writer.fieldOrder() == Writer.FieldOrder.DESCENDING) {
            writeFieldsInDescendingOrder(t, writer);
        } else if (this.proto3) {
            writeFieldsInAscendingOrderProto3(t, writer);
        } else {
            writeFieldsInAscendingOrderProto2(t, writer);
        }
    }

    private int positionForFieldNumber(int i, int i2) {
        if (i < this.minFieldNumber || i > this.maxFieldNumber) {
            return -1;
        }
        return slowPositionForFieldNumber(i, i2);
    }

    @Override // com.google.protobuf.Schema
    public void mergeFrom(T t, Reader reader, ExtensionRegistryLite extensionRegistryLite) {
        if (extensionRegistryLite != null) {
            mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, t, reader, extensionRegistryLite);
            return;
        }
        throw new NullPointerException();
    }

    @Override // com.google.protobuf.Schema
    public void mergeFrom(T t, byte[] bArr, int i, int i2, ArrayDecoders.Registers registers) throws InvalidProtocolBufferException {
        if (this.proto3) {
            parseProto3Message(t, bArr, i, i2, registers);
        } else {
            parseProto2Message(t, bArr, i, i2, 0, registers);
        }
    }

    private boolean equals(T t, T t2, int i) {
        int i2 = this.buffer[i + 1];
        long jOffset = offset(i2);
        switch (type(i2)) {
            case 0:
                return arePresentForEquals(t, t2, i) && Double.doubleToLongBits(UnsafeUtil.getDouble(t, jOffset)) == Double.doubleToLongBits(UnsafeUtil.getDouble(t2, jOffset));
            case 1:
                return arePresentForEquals(t, t2, i) && Float.floatToIntBits(UnsafeUtil.getFloat(t, jOffset)) == Float.floatToIntBits(UnsafeUtil.getFloat(t2, jOffset));
            case 2:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getLong(t, jOffset) == UnsafeUtil.getLong(t2, jOffset);
            case 3:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getLong(t, jOffset) == UnsafeUtil.getLong(t2, jOffset);
            case 4:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getInt(t, jOffset) == UnsafeUtil.getInt(t2, jOffset);
            case 5:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getLong(t, jOffset) == UnsafeUtil.getLong(t2, jOffset);
            case 6:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getInt(t, jOffset) == UnsafeUtil.getInt(t2, jOffset);
            case 7:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getBoolean(t, jOffset) == UnsafeUtil.getBoolean(t2, jOffset);
            case 8:
                return arePresentForEquals(t, t2, i) && SchemaUtil.safeEquals(UnsafeUtil.getObject(t, jOffset), UnsafeUtil.getObject(t2, jOffset));
            case 9:
                return arePresentForEquals(t, t2, i) && SchemaUtil.safeEquals(UnsafeUtil.getObject(t, jOffset), UnsafeUtil.getObject(t2, jOffset));
            case 10:
                return arePresentForEquals(t, t2, i) && SchemaUtil.safeEquals(UnsafeUtil.getObject(t, jOffset), UnsafeUtil.getObject(t2, jOffset));
            case 11:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getInt(t, jOffset) == UnsafeUtil.getInt(t2, jOffset);
            case 12:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getInt(t, jOffset) == UnsafeUtil.getInt(t2, jOffset);
            case 13:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getInt(t, jOffset) == UnsafeUtil.getInt(t2, jOffset);
            case 14:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getLong(t, jOffset) == UnsafeUtil.getLong(t2, jOffset);
            case 15:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getInt(t, jOffset) == UnsafeUtil.getInt(t2, jOffset);
            case 16:
                return arePresentForEquals(t, t2, i) && UnsafeUtil.getLong(t, jOffset) == UnsafeUtil.getLong(t2, jOffset);
            case 17:
                return arePresentForEquals(t, t2, i) && SchemaUtil.safeEquals(UnsafeUtil.getObject(t, jOffset), UnsafeUtil.getObject(t2, jOffset));
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                return SchemaUtil.safeEquals(UnsafeUtil.getObject(t, jOffset), UnsafeUtil.getObject(t2, jOffset));
            case 50:
                return SchemaUtil.safeEquals(UnsafeUtil.getObject(t, jOffset), UnsafeUtil.getObject(t2, jOffset));
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
                return isOneofCaseEqual(t, t2, i) && SchemaUtil.safeEquals(UnsafeUtil.getObject(t, jOffset), UnsafeUtil.getObject(t2, jOffset));
            default:
                return true;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean isInitialized(Object obj, int i, Schema schema) {
        return schema.isInitialized(UnsafeUtil.getObject(obj, offset(i)));
    }

    private boolean isFieldPresent(T t, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return isFieldPresent(t, i);
        }
        return (i3 & i4) != 0;
    }
}
