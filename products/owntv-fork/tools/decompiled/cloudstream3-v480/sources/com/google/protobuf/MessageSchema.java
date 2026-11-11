package com.google.protobuf;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes6.dex */
@CheckReturnValue
final class MessageSchema<T> implements Schema<T> {
    private static final int CHECK_INITIALIZED_BIT = 1024;
    private static final int ENFORCE_UTF8_MASK = 536870912;
    private static final int FIELD_TYPE_MASK = 267386880;
    private static final int HAS_HAS_BIT = 4096;
    private static final int INTS_PER_FIELD = 3;
    private static final int LEGACY_ENUM_IS_CLOSED_BIT = 2048;
    private static final int LEGACY_ENUM_IS_CLOSED_MASK = Integer.MIN_VALUE;
    private static final int NO_PRESENCE_SENTINEL = 1048575;
    private static final int OFFSET_BITS = 20;
    private static final int OFFSET_MASK = 1048575;
    static final int ONEOF_TYPE_OFFSET = 51;
    private static final int REQUIRED_BIT = 256;
    private static final int REQUIRED_MASK = 268435456;
    private static final int UTF8_CHECK_BIT = 512;
    private final int[] buffer;
    private final int checkInitializedCount;
    private final MessageLite defaultInstance;
    private final ExtensionSchema<?> extensionSchema;
    private final boolean hasExtensions;
    private final int[] intArray;
    private final ListFieldSchema listFieldSchema;
    private final boolean lite;
    private final MapFieldSchema mapFieldSchema;
    private final int maxFieldNumber;
    private final int minFieldNumber;
    private final NewInstanceSchema newInstanceSchema;
    private final Object[] objects;
    private final int oneofFieldPositionsStart;
    private final UnknownFieldSchema<?, ?> unknownFieldSchema;
    private final boolean useCachedSizeField;
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final Unsafe UNSAFE = UnsafeUtil.getUnsafe();

    private static boolean isEnforceUtf8(int value) {
        return (value & 536870912) != 0;
    }

    private static boolean isLegacyEnumIsClosed(int value) {
        return (value & Integer.MIN_VALUE) != 0;
    }

    private static boolean isRequired(int value) {
        return (value & 268435456) != 0;
    }

    private static long offset(int value) {
        return value & 1048575;
    }

    private static int type(int value) {
        return (value & FIELD_TYPE_MASK) >>> 20;
    }

    private MessageSchema(int[] buffer, Object[] objects, int minFieldNumber, int maxFieldNumber, MessageLite defaultInstance, boolean useCachedSizeField, int[] intArray, int checkInitializedCount, int oneofFieldPositionsStart, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        this.buffer = buffer;
        this.objects = objects;
        this.minFieldNumber = minFieldNumber;
        this.maxFieldNumber = maxFieldNumber;
        this.lite = defaultInstance instanceof GeneratedMessageLite;
        this.hasExtensions = extensionSchema != null && extensionSchema.hasExtensions(defaultInstance);
        this.useCachedSizeField = useCachedSizeField;
        this.intArray = intArray;
        this.checkInitializedCount = checkInitializedCount;
        this.oneofFieldPositionsStart = oneofFieldPositionsStart;
        this.newInstanceSchema = newInstanceSchema;
        this.listFieldSchema = listFieldSchema;
        this.unknownFieldSchema = unknownFieldSchema;
        this.extensionSchema = extensionSchema;
        this.defaultInstance = defaultInstance;
        this.mapFieldSchema = mapFieldSchema;
    }

    static <T> MessageSchema<T> newSchema(Class<T> messageClass, MessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        if (messageInfo instanceof RawMessageInfo) {
            return newSchemaForRawMessageInfo((RawMessageInfo) messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
        }
        return newSchemaForMessageInfo((StructuralMessageInfo) messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    /* JADX WARN: Code duplicated, block: B:120:0x024a  */
    /* JADX WARN: Code duplicated, block: B:121:0x024d  */
    /* JADX WARN: Code duplicated, block: B:124:0x026d  */
    /* JADX WARN: Code duplicated, block: B:125:0x0270  */
    /* JADX WARN: Code duplicated, block: B:162:0x032a  */
    /* JADX WARN: Code duplicated, block: B:176:0x0378  */
    static <T> MessageSchema<T> newSchemaForRawMessageInfo(RawMessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        int i;
        int iCharAt;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int[] iArr;
        int i8;
        char cCharAt;
        int i9;
        int i10;
        char cCharAt2;
        int i11;
        char cCharAt3;
        int i12;
        char cCharAt4;
        int i13;
        char cCharAt5;
        int i14;
        char cCharAt6;
        int i15;
        char cCharAt7;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int iObjectFieldOffset;
        int i21;
        int iObjectFieldOffset2;
        int i22;
        java.lang.reflect.Field fieldReflectField;
        int i23;
        char cCharAt8;
        int i24;
        int i25;
        int i26;
        Object obj;
        java.lang.reflect.Field fieldReflectField2;
        int i27;
        Object obj2;
        java.lang.reflect.Field fieldReflectField3;
        int i28;
        char cCharAt9;
        int i29;
        char cCharAt10;
        int i30;
        char cCharAt11;
        int i31;
        char cCharAt12;
        String stringInfo = messageInfo.getStringInfo();
        int length = stringInfo.length();
        char c = 55296;
        if (stringInfo.charAt(0) >= 55296) {
            int i32 = 1;
            while (true) {
                i = i32 + 1;
                if (stringInfo.charAt(i32) < 55296) {
                    break;
                }
                i32 = i;
            }
        } else {
            i = 1;
        }
        int i33 = i + 1;
        int iCharAt2 = stringInfo.charAt(i);
        if (iCharAt2 >= 55296) {
            int i34 = iCharAt2 & 8191;
            int i35 = 13;
            while (true) {
                i31 = i33 + 1;
                cCharAt12 = stringInfo.charAt(i33);
                if (cCharAt12 < 55296) {
                    break;
                }
                i34 |= (cCharAt12 & 8191) << i35;
                i35 += 13;
                i33 = i31;
            }
            iCharAt2 = i34 | (cCharAt12 << i35);
            i33 = i31;
        }
        if (iCharAt2 == 0) {
            i3 = 0;
            i7 = 0;
            iCharAt = 0;
            i2 = 0;
            i6 = 0;
            i5 = 0;
            iArr = EMPTY_INT_ARRAY;
            i4 = 0;
        } else {
            int i36 = i33 + 1;
            int iCharAt3 = stringInfo.charAt(i33);
            if (iCharAt3 >= 55296) {
                int i37 = iCharAt3 & 8191;
                int i38 = 13;
                while (true) {
                    i15 = i36 + 1;
                    cCharAt7 = stringInfo.charAt(i36);
                    if (cCharAt7 < 55296) {
                        break;
                    }
                    i37 |= (cCharAt7 & 8191) << i38;
                    i38 += 13;
                    i36 = i15;
                }
                iCharAt3 = i37 | (cCharAt7 << i38);
                i36 = i15;
            }
            int i39 = i36 + 1;
            int iCharAt4 = stringInfo.charAt(i36);
            if (iCharAt4 >= 55296) {
                int i40 = iCharAt4 & 8191;
                int i41 = 13;
                while (true) {
                    i14 = i39 + 1;
                    cCharAt6 = stringInfo.charAt(i39);
                    if (cCharAt6 < 55296) {
                        break;
                    }
                    i40 |= (cCharAt6 & 8191) << i41;
                    i41 += 13;
                    i39 = i14;
                }
                iCharAt4 = i40 | (cCharAt6 << i41);
                i39 = i14;
            }
            int i42 = i39 + 1;
            int iCharAt5 = stringInfo.charAt(i39);
            if (iCharAt5 >= 55296) {
                int i43 = iCharAt5 & 8191;
                int i44 = 13;
                while (true) {
                    i13 = i42 + 1;
                    cCharAt5 = stringInfo.charAt(i42);
                    if (cCharAt5 < 55296) {
                        break;
                    }
                    i43 |= (cCharAt5 & 8191) << i44;
                    i44 += 13;
                    i42 = i13;
                }
                iCharAt5 = i43 | (cCharAt5 << i44);
                i42 = i13;
            }
            int i45 = i42 + 1;
            int iCharAt6 = stringInfo.charAt(i42);
            if (iCharAt6 >= 55296) {
                int i46 = iCharAt6 & 8191;
                int i47 = 13;
                while (true) {
                    i12 = i45 + 1;
                    cCharAt4 = stringInfo.charAt(i45);
                    if (cCharAt4 < 55296) {
                        break;
                    }
                    i46 |= (cCharAt4 & 8191) << i47;
                    i47 += 13;
                    i45 = i12;
                }
                iCharAt6 = i46 | (cCharAt4 << i47);
                i45 = i12;
            }
            int i48 = i45 + 1;
            iCharAt = stringInfo.charAt(i45);
            if (iCharAt >= 55296) {
                int i49 = iCharAt & 8191;
                int i50 = 13;
                while (true) {
                    i11 = i48 + 1;
                    cCharAt3 = stringInfo.charAt(i48);
                    if (cCharAt3 < 55296) {
                        break;
                    }
                    i49 |= (cCharAt3 & 8191) << i50;
                    i50 += 13;
                    i48 = i11;
                }
                iCharAt = i49 | (cCharAt3 << i50);
                i48 = i11;
            }
            int i51 = i48 + 1;
            int iCharAt7 = stringInfo.charAt(i48);
            if (iCharAt7 >= 55296) {
                int i52 = iCharAt7 & 8191;
                int i53 = 13;
                while (true) {
                    i10 = i51 + 1;
                    cCharAt2 = stringInfo.charAt(i51);
                    if (cCharAt2 < 55296) {
                        break;
                    }
                    i52 |= (cCharAt2 & 8191) << i53;
                    i53 += 13;
                    i51 = i10;
                }
                iCharAt7 = i52 | (cCharAt2 << i53);
                i51 = i10;
            }
            int i54 = i51 + 1;
            if (stringInfo.charAt(i51) >= 55296) {
                while (true) {
                    i9 = i54 + 1;
                    if (stringInfo.charAt(i54) < 55296) {
                        break;
                    }
                    i54 = i9;
                }
                i54 = i9;
            }
            int i55 = i54 + 1;
            int iCharAt8 = stringInfo.charAt(i54);
            if (iCharAt8 >= 55296) {
                int i56 = iCharAt8 & 8191;
                int i57 = 13;
                while (true) {
                    i8 = i55 + 1;
                    cCharAt = stringInfo.charAt(i55);
                    if (cCharAt < 55296) {
                        break;
                    }
                    i56 |= (cCharAt & 8191) << i57;
                    i57 += 13;
                    i55 = i8;
                }
                iCharAt8 = i56 | (cCharAt << i57);
                i55 = i8;
            }
            int[] iArr2 = new int[iCharAt8 + iCharAt7 + iCharAt3];
            int i58 = (iCharAt3 * 2) + iCharAt4;
            int i59 = iCharAt7;
            i2 = iCharAt5;
            i3 = i59;
            i4 = iCharAt3;
            i33 = i55;
            i5 = iCharAt8;
            i6 = iCharAt6;
            i7 = i58;
            iArr = iArr2;
        }
        Unsafe unsafe = UNSAFE;
        Object[] objects = messageInfo.getObjects();
        Class<?> cls = messageInfo.getDefaultInstance().getClass();
        int[] iArr3 = new int[iCharAt * 3];
        Object[] objArr = new Object[iCharAt * 2];
        int i60 = i5 + i3;
        int i61 = i60;
        int i62 = i5;
        int i63 = 0;
        int i64 = 0;
        while (i33 < length) {
            int i65 = i33 + 1;
            int iCharAt9 = stringInfo.charAt(i33);
            if (iCharAt9 >= c) {
                int i66 = iCharAt9 & 8191;
                int i67 = i65;
                int i68 = 13;
                while (true) {
                    i30 = i67 + 1;
                    cCharAt11 = stringInfo.charAt(i67);
                    if (cCharAt11 < c) {
                        break;
                    }
                    i66 |= (cCharAt11 & 8191) << i68;
                    i68 += 13;
                    i67 = i30;
                }
                iCharAt9 = i66 | (cCharAt11 << i68);
                i16 = i30;
            } else {
                i16 = i65;
            }
            int i69 = i16 + 1;
            int iCharAt10 = stringInfo.charAt(i16);
            if (iCharAt10 >= c) {
                int i70 = iCharAt10 & 8191;
                int i71 = i69;
                int i72 = 13;
                while (true) {
                    i29 = i71 + 1;
                    cCharAt10 = stringInfo.charAt(i71);
                    i17 = length;
                    if (cCharAt10 < 55296) {
                        break;
                    }
                    i70 |= (cCharAt10 & 8191) << i72;
                    i72 += 13;
                    i71 = i29;
                    length = i17;
                }
                iCharAt10 = i70 | (cCharAt10 << i72);
                i18 = i29;
            } else {
                i17 = length;
                i18 = i69;
            }
            int i73 = iCharAt10 & 255;
            int i74 = iCharAt9;
            if ((iCharAt10 & 1024) != 0) {
                iArr[i63] = i64;
                i63++;
            }
            int[] iArr4 = iArr3;
            if (i73 >= 51) {
                int i75 = i18 + 1;
                int iCharAt11 = stringInfo.charAt(i18);
                char c2 = 55296;
                if (iCharAt11 >= 55296) {
                    int i76 = iCharAt11 & 8191;
                    int i77 = 13;
                    while (true) {
                        i28 = i75 + 1;
                        cCharAt9 = stringInfo.charAt(i75);
                        if (cCharAt9 < c2) {
                            break;
                        }
                        i76 |= (cCharAt9 & 8191) << i77;
                        i77 += 13;
                        i75 = i28;
                        c2 = 55296;
                    }
                    iCharAt11 = i76 | (cCharAt9 << i77);
                    i75 = i28;
                }
                int i78 = i73 - 51;
                int i79 = iCharAt11;
                if (i78 == 9 || i78 == 17) {
                    i25 = i7 + 1;
                    objArr[((i64 / 3) * 2) + 1] = objects[i7];
                } else {
                    if (i78 == 12 && (messageInfo.getSyntax().equals(ProtoSyntax.PROTO2) || (iCharAt10 & 2048) != 0)) {
                        i25 = i7 + 1;
                        objArr[((i64 / 3) * 2) + 1] = objects[i7];
                    }
                    i26 = i79 * 2;
                    obj = objects[i26];
                    if (obj instanceof java.lang.reflect.Field) {
                        fieldReflectField2 = (java.lang.reflect.Field) obj;
                    } else {
                        fieldReflectField2 = reflectField(cls, (String) obj);
                        objects[i26] = fieldReflectField2;
                        iArr[i61] = i64;
                        i61++;
                    }
                    int i80 = i75;
                    int iObjectFieldOffset3 = (int) unsafe.objectFieldOffset(fieldReflectField2);
                    i27 = i26 + 1;
                    obj2 = objects[i27];
                    if (obj2 instanceof java.lang.reflect.Field) {
                        fieldReflectField3 = (java.lang.reflect.Field) obj2;
                    } else {
                        fieldReflectField3 = reflectField(cls, (String) obj2);
                        objects[i27] = fieldReflectField3;
                    }
                    iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldReflectField3);
                    i21 = i80;
                    iObjectFieldOffset = iObjectFieldOffset3;
                    i22 = 0;
                    i19 = i4;
                }
                i7 = i25;
                i26 = i79 * 2;
                obj = objects[i26];
                if (obj instanceof java.lang.reflect.Field) {
                    fieldReflectField2 = (java.lang.reflect.Field) obj;
                } else {
                    fieldReflectField2 = reflectField(cls, (String) obj);
                    objects[i26] = fieldReflectField2;
                    iArr[i61] = i64;
                    i61++;
                }
                int i81 = i75;
                int iObjectFieldOffset4 = (int) unsafe.objectFieldOffset(fieldReflectField2);
                i27 = i26 + 1;
                obj2 = objects[i27];
                if (obj2 instanceof java.lang.reflect.Field) {
                    fieldReflectField3 = (java.lang.reflect.Field) obj2;
                } else {
                    fieldReflectField3 = reflectField(cls, (String) obj2);
                    objects[i27] = fieldReflectField3;
                }
                iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldReflectField3);
                i21 = i81;
                iObjectFieldOffset = iObjectFieldOffset4;
                i22 = 0;
                i19 = i4;
            } else {
                int i82 = i7 + 1;
                java.lang.reflect.Field fieldReflectField4 = reflectField(cls, (String) objects[i7]);
                if (i73 == 9 || i73 == 17) {
                    i19 = i4;
                    objArr[((i64 / 3) * 2) + 1] = fieldReflectField4.getType();
                } else {
                    if (i73 == 27 || i73 == 49) {
                        i19 = i4;
                        i24 = i7 + 2;
                        objArr[((i64 / 3) * 2) + 1] = objects[i82];
                    } else if (i73 == 12 || i73 == 30 || i73 == 44) {
                        i19 = i4;
                        if (messageInfo.getSyntax() == ProtoSyntax.PROTO2 || (iCharAt10 & 2048) != 0) {
                            i24 = i7 + 2;
                            objArr[((i64 / 3) * 2) + 1] = objects[i82];
                        }
                        iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldReflectField4);
                        if ((iCharAt10 & 4096) != 0 || i73 > 17) {
                            i21 = i18;
                            i7 = i20;
                            iObjectFieldOffset2 = 1048575;
                            i22 = 0;
                        } else {
                            i21 = i18 + 1;
                            int iCharAt12 = stringInfo.charAt(i18);
                            if (iCharAt12 >= 55296) {
                                int i83 = iCharAt12 & 8191;
                                int i84 = 13;
                                while (true) {
                                    i23 = i21 + 1;
                                    cCharAt8 = stringInfo.charAt(i21);
                                    if (cCharAt8 < 55296) {
                                        break;
                                    }
                                    i83 |= (cCharAt8 & 8191) << i84;
                                    i84 += 13;
                                    i21 = i23;
                                }
                                iCharAt12 = i83 | (cCharAt8 << i84);
                                i21 = i23;
                            }
                            int i85 = (i19 * 2) + (iCharAt12 / 32);
                            Object obj3 = objects[i85];
                            if (obj3 instanceof java.lang.reflect.Field) {
                                fieldReflectField = (java.lang.reflect.Field) obj3;
                            } else {
                                fieldReflectField = reflectField(cls, (String) obj3);
                                objects[i85] = fieldReflectField;
                            }
                            int i86 = iCharAt12;
                            iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldReflectField);
                            i22 = i86 % 32;
                            i7 = i20;
                        }
                    } else {
                        if (i73 == 50) {
                            int i87 = i62 + 1;
                            iArr[i62] = i64;
                            int i88 = (i64 / 3) * 2;
                            int i89 = i7 + 2;
                            objArr[i88] = objects[i82];
                            if ((iCharAt10 & 2048) != 0) {
                                objArr[i88 + 1] = objects[i89];
                                i19 = i4;
                                i62 = i87;
                                i20 = i7 + 3;
                            } else {
                                i19 = i4;
                                i20 = i89;
                                i62 = i87;
                            }
                        } else {
                            i19 = i4;
                        }
                        iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldReflectField4);
                        if ((iCharAt10 & 4096) != 0) {
                            i21 = i18;
                            i7 = i20;
                            iObjectFieldOffset2 = 1048575;
                            i22 = 0;
                        } else {
                            i21 = i18;
                            i7 = i20;
                            iObjectFieldOffset2 = 1048575;
                            i22 = 0;
                        }
                    }
                    i20 = i24;
                    iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldReflectField4);
                    if ((iCharAt10 & 4096) != 0) {
                        i21 = i18;
                        i7 = i20;
                        iObjectFieldOffset2 = 1048575;
                        i22 = 0;
                    } else {
                        i21 = i18;
                        i7 = i20;
                        iObjectFieldOffset2 = 1048575;
                        i22 = 0;
                    }
                }
                i20 = i82;
                iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldReflectField4);
                if ((iCharAt10 & 4096) != 0) {
                    i21 = i18;
                    i7 = i20;
                    iObjectFieldOffset2 = 1048575;
                    i22 = 0;
                } else {
                    i21 = i18;
                    i7 = i20;
                    iObjectFieldOffset2 = 1048575;
                    i22 = 0;
                }
            }
            int i90 = i64 + 1;
            iArr4[i64] = i74;
            int i91 = i64 + 2;
            int i92 = i22;
            iArr4[i90] = ((iCharAt10 & 512) != 0 ? 536870912 : 0) | ((iCharAt10 & 256) != 0 ? 268435456 : 0) | ((iCharAt10 & 2048) != 0 ? Integer.MIN_VALUE : 0) | (i73 << 20) | iObjectFieldOffset;
            i64 += 3;
            iArr4[i91] = (i92 << 20) | iObjectFieldOffset2;
            i33 = i21;
            i4 = i19;
            length = i17;
            iArr3 = iArr4;
            stringInfo = stringInfo;
            c = 55296;
        }
        return new MessageSchema<>(iArr3, objArr, i2, i6, messageInfo.getDefaultInstance(), false, iArr, i5, i60, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    private static java.lang.reflect.Field reflectField(Class<?> messageClass, String fieldName) {
        try {
            return messageClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            java.lang.reflect.Field[] declaredFields = messageClass.getDeclaredFields();
            for (java.lang.reflect.Field field : declaredFields) {
                if (fieldName.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + fieldName + " for " + messageClass.getName() + " not found. Known fields are " + Arrays.toString(declaredFields), e);
        }
    }

    static <T> MessageSchema<T> newSchemaForMessageInfo(StructuralMessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        int fieldNumber;
        int fieldNumber2;
        int[] iArr;
        FieldInfo[] fields = messageInfo.getFields();
        if (fields.length == 0) {
            fieldNumber = 0;
            fieldNumber2 = 0;
        } else {
            fieldNumber = fields[0].getFieldNumber();
            fieldNumber2 = fields[fields.length - 1].getFieldNumber();
        }
        int length = fields.length;
        int[] iArr2 = new int[length * 3];
        Object[] objArr = new Object[length * 2];
        HashSet hashSet = new HashSet();
        int i = 0;
        int i2 = 0;
        for (FieldInfo fieldInfo : fields) {
            if (fieldInfo.getType() == FieldType.MAP) {
                i++;
            } else {
                OneofInfo oneof = fieldInfo.getOneof();
                if (oneof != null && !hashSet.add(Integer.valueOf(oneof.getId()))) {
                    i2++;
                }
            }
        }
        int[] iArr3 = i > 0 ? new int[i] : EMPTY_INT_ARRAY;
        int[] iArr4 = i2 > 0 ? new int[i2] : EMPTY_INT_ARRAY;
        hashSet.clear();
        int[] checkInitialized = messageInfo.getCheckInitialized();
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
            storeFieldData(fieldInfo2, iArr2, i4, objArr);
            FieldInfo[] fieldInfoArr = fields;
            if (i5 < checkInitialized.length && checkInitialized[i5] == fieldNumber3) {
                checkInitialized[i5] = i4;
                i5++;
            }
            if (fieldInfo2.getType() == FieldType.MAP) {
                iArr3[i6] = i4;
                i6++;
            } else {
                OneofInfo oneof2 = fieldInfo2.getOneof();
                if (oneof2 != null && !hashSet.add(Integer.valueOf(oneof2.getId()))) {
                    iArr4[i7] = i4;
                    i7++;
                }
            }
            i3++;
            i4 += 3;
            fields = fieldInfoArr;
        }
        int length2 = checkInitialized.length + iArr3.length + iArr4.length;
        if (length2 > 0) {
            iArr = new int[length2];
            System.arraycopy(checkInitialized, 0, iArr, 0, checkInitialized.length);
            System.arraycopy(iArr3, 0, iArr, checkInitialized.length, iArr3.length);
            System.arraycopy(iArr4, 0, iArr, checkInitialized.length + iArr3.length, iArr4.length);
        } else {
            iArr = EMPTY_INT_ARRAY;
        }
        return new MessageSchema<>(iArr2, objArr, fieldNumber, fieldNumber2, messageInfo.getDefaultInstance(), true, iArr, checkInitialized.length, checkInitialized.length + iArr3.length, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    /* JADX WARN: Code duplicated, block: B:21:0x007a  */
    /* JADX WARN: Code duplicated, block: B:22:0x007d  */
    /* JADX WARN: Code duplicated, block: B:25:0x0084  */
    /* JADX WARN: Code duplicated, block: B:28:0x009e  */
    /* JADX WARN: Code duplicated, block: B:30:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:32:0x00af  */
    /* JADX WARN: Code duplicated, block: B:34:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:36:0x00be A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:37:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:39:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:41:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:43:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:44:? A[RETURN, SYNTHETIC] */
    private static void storeFieldData(FieldInfo fi, int[] buffer, int bufferIndex, Object[] objects) {
        int iObjectFieldOffset;
        int iId;
        long jObjectFieldOffset;
        int iObjectFieldOffset2;
        int iNumberOfTrailingZeros;
        int i;
        Class<?> messageFieldClass;
        int i2;
        OneofInfo oneof = fi.getOneof();
        if (oneof != null) {
            iId = fi.getType().id() + 51;
            iObjectFieldOffset = (int) UnsafeUtil.objectFieldOffset(oneof.getValueField());
            jObjectFieldOffset = UnsafeUtil.objectFieldOffset(oneof.getCaseField());
        } else {
            FieldType type = fi.getType();
            iObjectFieldOffset = (int) UnsafeUtil.objectFieldOffset(fi.getField());
            iId = type.id();
            if (!type.isList() && !type.isMap()) {
                java.lang.reflect.Field presenceField = fi.getPresenceField();
                iObjectFieldOffset2 = presenceField == null ? 1048575 : (int) UnsafeUtil.objectFieldOffset(presenceField);
                iNumberOfTrailingZeros = Integer.numberOfTrailingZeros(fi.getPresenceMask());
            } else if (fi.getCachedSizeField() == null) {
                iObjectFieldOffset2 = 0;
                iNumberOfTrailingZeros = 0;
            } else {
                jObjectFieldOffset = UnsafeUtil.objectFieldOffset(fi.getCachedSizeField());
            }
            buffer[bufferIndex] = fi.getFieldNumber();
            int i3 = bufferIndex + 1;
            if (fi.isEnforceUtf8()) {
                i = 536870912;
            } else {
                i = 0;
            }
            buffer[i3] = (fi.isRequired() ? 268435456 : 0) | i | (iId << 20) | iObjectFieldOffset;
            buffer[bufferIndex + 2] = iObjectFieldOffset2 | (iNumberOfTrailingZeros << 20);
            messageFieldClass = fi.getMessageFieldClass();
            if (fi.getMapDefaultEntry() != null) {
                if (messageFieldClass != null) {
                    objects[((bufferIndex / 3) * 2) + 1] = messageFieldClass;
                    return;
                } else {
                    if (fi.getEnumVerifier() != null) {
                        objects[((bufferIndex / 3) * 2) + 1] = fi.getEnumVerifier();
                        return;
                    }
                    return;
                }
            }
            i2 = (bufferIndex / 3) * 2;
            objects[i2] = fi.getMapDefaultEntry();
            if (messageFieldClass != null) {
                objects[i2 + 1] = messageFieldClass;
            } else if (fi.getEnumVerifier() != null) {
                objects[i2 + 1] = fi.getEnumVerifier();
            }
        }
        iObjectFieldOffset2 = (int) jObjectFieldOffset;
        iNumberOfTrailingZeros = 0;
        buffer[bufferIndex] = fi.getFieldNumber();
        int i4 = bufferIndex + 1;
        if (fi.isEnforceUtf8()) {
            i = 536870912;
        } else {
            i = 0;
        }
        buffer[i4] = (fi.isRequired() ? 268435456 : 0) | i | (iId << 20) | iObjectFieldOffset;
        buffer[bufferIndex + 2] = iObjectFieldOffset2 | (iNumberOfTrailingZeros << 20);
        messageFieldClass = fi.getMessageFieldClass();
        if (fi.getMapDefaultEntry() != null) {
            if (messageFieldClass != null) {
                objects[((bufferIndex / 3) * 2) + 1] = messageFieldClass;
                return;
            } else {
                if (fi.getEnumVerifier() != null) {
                    objects[((bufferIndex / 3) * 2) + 1] = fi.getEnumVerifier();
                    return;
                }
                return;
            }
        }
        i2 = (bufferIndex / 3) * 2;
        objects[i2] = fi.getMapDefaultEntry();
        if (messageFieldClass != null) {
            objects[i2 + 1] = messageFieldClass;
        } else if (fi.getEnumVerifier() != null) {
            objects[i2 + 1] = fi.getEnumVerifier();
        }
    }

    @Override // com.google.protobuf.Schema
    public T newInstance() {
        return (T) this.newInstanceSchema.newInstance(this.defaultInstance);
    }

    @Override // com.google.protobuf.Schema
    public boolean equals(T message, T other) {
        MessageSchema<T> messageSchema;
        T t;
        T t2;
        int length = this.buffer.length;
        int i = 0;
        while (i < length) {
            int iTypeAndOffsetAt = this.typeAndOffsetAt(i);
            int iType = type(iTypeAndOffsetAt);
            if (iType <= 50 || iType >= 69) {
                long jOffset = offset(iTypeAndOffsetAt);
                messageSchema = this;
                t = message;
                t2 = other;
                if (!messageSchema.equalsAtPosition(t, t2, i, iType, jOffset)) {
                    return false;
                }
            } else {
                messageSchema = this;
                t = message;
                t2 = other;
            }
            i += 3;
            this = messageSchema;
            message = t;
            other = t2;
        }
        MessageSchema<T> messageSchema2 = this;
        T t3 = message;
        T t4 = other;
        int i2 = messageSchema2.oneofFieldPositionsStart;
        while (true) {
            int[] iArr = messageSchema2.intArray;
            if (i2 < iArr.length) {
                int i3 = iArr[i2];
                if (!messageSchema2.isOneofCaseEqual(t3, t4, i3)) {
                    return false;
                }
                if (!messageSchema2.isOneofPresent(t3, 0, i3)) {
                    long jOffset2 = offset(messageSchema2.typeAndOffsetAt(i3));
                    if (!SchemaUtil.safeEquals(UnsafeUtil.getObject(t3, jOffset2), UnsafeUtil.getObject(t4, jOffset2))) {
                        return false;
                    }
                }
                i2++;
            } else {
                if (!messageSchema2.unknownFieldSchema.getFromMessage(t3).equals(messageSchema2.unknownFieldSchema.getFromMessage(t4))) {
                    return false;
                }
                if (messageSchema2.hasExtensions) {
                    return messageSchema2.extensionSchema.getExtensions(t3).equals(messageSchema2.extensionSchema.getExtensions(t4));
                }
                return true;
            }
        }
    }

    private boolean equalsAtPosition(T message, T other, int pos, int type, long offset) {
        switch (type) {
            case 0:
                return arePresentForEquals(message, other, pos) && Double.doubleToLongBits(UnsafeUtil.getDouble(message, offset)) == Double.doubleToLongBits(UnsafeUtil.getDouble(other, offset));
            case 1:
                return arePresentForEquals(message, other, pos) && Float.floatToIntBits(UnsafeUtil.getFloat(message, offset)) == Float.floatToIntBits(UnsafeUtil.getFloat(other, offset));
            case 2:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset);
            case 3:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset);
            case 4:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset);
            case 5:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset);
            case 6:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset);
            case 7:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getBoolean(message, offset) == UnsafeUtil.getBoolean(other, offset);
            case 8:
                return arePresentForEquals(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset));
            case 9:
                return arePresentForEquals(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset));
            case 10:
                return arePresentForEquals(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset));
            case 11:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset);
            case 12:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset);
            case 13:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset);
            case 14:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset);
            case 15:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset);
            case 16:
                return arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset);
            case 17:
                return arePresentForEquals(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset));
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
                return SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset));
            case 50:
                return SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset));
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
                return isOneofCaseEqual(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset));
            default:
                return true;
        }
    }

    @Override // com.google.protobuf.Schema
    public int hashCode(T message) {
        int i;
        int length = this.buffer.length;
        int iHashCode = 0;
        for (int i2 = 0; i2 < length; i2 += 3) {
            int iTypeAndOffsetAt = typeAndOffsetAt(i2);
            int iType = type(iTypeAndOffsetAt);
            if (iType <= 50 || iType >= 69) {
                long jOffset = offset(iTypeAndOffsetAt);
                int iHashLong = 37;
                switch (iType) {
                    case 0:
                        i = iHashCode * 53;
                        iHashLong = Internal.hashLong(Double.doubleToLongBits(UnsafeUtil.getDouble(message, jOffset)));
                        iHashCode = i + iHashLong;
                        break;
                    case 1:
                        i = iHashCode * 53;
                        iHashLong = Float.floatToIntBits(UnsafeUtil.getFloat(message, jOffset));
                        iHashCode = i + iHashLong;
                        break;
                    case 2:
                        i = iHashCode * 53;
                        iHashLong = Internal.hashLong(UnsafeUtil.getLong(message, jOffset));
                        iHashCode = i + iHashLong;
                        break;
                    case 3:
                        i = iHashCode * 53;
                        iHashLong = Internal.hashLong(UnsafeUtil.getLong(message, jOffset));
                        iHashCode = i + iHashLong;
                        break;
                    case 4:
                        i = iHashCode * 53;
                        iHashLong = UnsafeUtil.getInt(message, jOffset);
                        iHashCode = i + iHashLong;
                        break;
                    case 5:
                        i = iHashCode * 53;
                        iHashLong = Internal.hashLong(UnsafeUtil.getLong(message, jOffset));
                        iHashCode = i + iHashLong;
                        break;
                    case 6:
                        i = iHashCode * 53;
                        iHashLong = UnsafeUtil.getInt(message, jOffset);
                        iHashCode = i + iHashLong;
                        break;
                    case 7:
                        i = iHashCode * 53;
                        iHashLong = Internal.hashBoolean(UnsafeUtil.getBoolean(message, jOffset));
                        iHashCode = i + iHashLong;
                        break;
                    case 8:
                        i = iHashCode * 53;
                        iHashLong = ((String) UnsafeUtil.getObject(message, jOffset)).hashCode();
                        iHashCode = i + iHashLong;
                        break;
                    case 9:
                        Object object = UnsafeUtil.getObject(message, jOffset);
                        if (object != null) {
                            iHashLong = object.hashCode();
                        }
                        i = iHashCode * 53;
                        iHashCode = i + iHashLong;
                        break;
                    case 10:
                        i = iHashCode * 53;
                        iHashLong = UnsafeUtil.getObject(message, jOffset).hashCode();
                        iHashCode = i + iHashLong;
                        break;
                    case 11:
                        i = iHashCode * 53;
                        iHashLong = UnsafeUtil.getInt(message, jOffset);
                        iHashCode = i + iHashLong;
                        break;
                    case 12:
                        i = iHashCode * 53;
                        iHashLong = UnsafeUtil.getInt(message, jOffset);
                        iHashCode = i + iHashLong;
                        break;
                    case 13:
                        i = iHashCode * 53;
                        iHashLong = UnsafeUtil.getInt(message, jOffset);
                        iHashCode = i + iHashLong;
                        break;
                    case 14:
                        i = iHashCode * 53;
                        iHashLong = Internal.hashLong(UnsafeUtil.getLong(message, jOffset));
                        iHashCode = i + iHashLong;
                        break;
                    case 15:
                        i = iHashCode * 53;
                        iHashLong = UnsafeUtil.getInt(message, jOffset);
                        iHashCode = i + iHashLong;
                        break;
                    case 16:
                        i = iHashCode * 53;
                        iHashLong = Internal.hashLong(UnsafeUtil.getLong(message, jOffset));
                        iHashCode = i + iHashLong;
                        break;
                    case 17:
                        Object object2 = UnsafeUtil.getObject(message, jOffset);
                        if (object2 != null) {
                            iHashLong = object2.hashCode();
                        }
                        i = iHashCode * 53;
                        iHashCode = i + iHashLong;
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
                        i = iHashCode * 53;
                        iHashLong = UnsafeUtil.getObject(message, jOffset).hashCode();
                        iHashCode = i + iHashLong;
                        break;
                    case 50:
                        i = iHashCode * 53;
                        iHashLong = UnsafeUtil.getObject(message, jOffset).hashCode();
                        iHashCode = i + iHashLong;
                        break;
                }
            }
        }
        int i3 = this.oneofFieldPositionsStart;
        while (true) {
            int[] iArr = this.intArray;
            if (i3 < iArr.length) {
                int i4 = iArr[i3];
                if (!isOneofPresent(message, 0, i4)) {
                    iHashCode = (iHashCode * 53) + UnsafeUtil.getObject(message, offset(typeAndOffsetAt(i4))).hashCode();
                }
                i3++;
            } else {
                int iHashCode2 = (iHashCode * 53) + this.unknownFieldSchema.getFromMessage(message).hashCode();
                return this.hasExtensions ? (iHashCode2 * 53) + this.extensionSchema.getExtensions(message).hashCode() : iHashCode2;
            }
        }
    }

    @Override // com.google.protobuf.Schema
    public void mergeFrom(T message, T other) {
        checkMutable(message);
        Internal.checkNotNull(other);
        for (int i = 0; i < this.buffer.length; i += 3) {
            mergeSingleField(message, other, i);
        }
        SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, message, other);
        if (this.hasExtensions) {
            SchemaUtil.mergeExtensions(this.extensionSchema, message, other);
        }
    }

    private void mergeSingleField(T message, T other, int pos) {
        int iTypeAndOffsetAt = typeAndOffsetAt(pos);
        long jOffset = offset(iTypeAndOffsetAt);
        int iNumberAt = numberAt(pos);
        switch (type(iTypeAndOffsetAt)) {
            case 0:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putDouble(message, jOffset, UnsafeUtil.getDouble(other, jOffset));
                    setFieldPresent(message, pos);
                }
                break;
            case 1:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putFloat(message, jOffset, UnsafeUtil.getFloat(other, jOffset));
                    setFieldPresent(message, pos);
                }
                break;
            case 2:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, jOffset, UnsafeUtil.getLong(other, jOffset));
                    setFieldPresent(message, pos);
                }
                break;
            case 3:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, jOffset, UnsafeUtil.getLong(other, jOffset));
                    setFieldPresent(message, pos);
                }
                break;
            case 4:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, jOffset, UnsafeUtil.getInt(other, jOffset));
                    setFieldPresent(message, pos);
                }
                break;
            case 5:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, jOffset, UnsafeUtil.getLong(other, jOffset));
                    setFieldPresent(message, pos);
                }
                break;
            case 6:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, jOffset, UnsafeUtil.getInt(other, jOffset));
                    setFieldPresent(message, pos);
                }
                break;
            case 7:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putBoolean(message, jOffset, UnsafeUtil.getBoolean(other, jOffset));
                    setFieldPresent(message, pos);
                }
                break;
            case 8:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putObject(message, jOffset, UnsafeUtil.getObject(other, jOffset));
                    setFieldPresent(message, pos);
                }
                break;
            case 9:
                mergeMessage(message, other, pos);
                break;
            case 10:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putObject(message, jOffset, UnsafeUtil.getObject(other, jOffset));
                    setFieldPresent(message, pos);
                }
                break;
            case 11:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, jOffset, UnsafeUtil.getInt(other, jOffset));
                    setFieldPresent(message, pos);
                }
                break;
            case 12:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, jOffset, UnsafeUtil.getInt(other, jOffset));
                    setFieldPresent(message, pos);
                }
                break;
            case 13:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, jOffset, UnsafeUtil.getInt(other, jOffset));
                    setFieldPresent(message, pos);
                }
                break;
            case 14:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, jOffset, UnsafeUtil.getLong(other, jOffset));
                    setFieldPresent(message, pos);
                }
                break;
            case 15:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, jOffset, UnsafeUtil.getInt(other, jOffset));
                    setFieldPresent(message, pos);
                }
                break;
            case 16:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, jOffset, UnsafeUtil.getLong(other, jOffset));
                    setFieldPresent(message, pos);
                }
                break;
            case 17:
                mergeMessage(message, other, pos);
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
                this.listFieldSchema.mergeListsAt(message, other, jOffset);
                break;
            case 50:
                SchemaUtil.mergeMap(this.mapFieldSchema, message, other, jOffset);
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
                if (isOneofPresent(other, iNumberAt, pos)) {
                    UnsafeUtil.putObject(message, jOffset, UnsafeUtil.getObject(other, jOffset));
                    setOneofPresent(message, iNumberAt, pos);
                }
                break;
            case 60:
                mergeOneofMessage(message, other, pos);
                break;
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
                if (isOneofPresent(other, iNumberAt, pos)) {
                    UnsafeUtil.putObject(message, jOffset, UnsafeUtil.getObject(other, jOffset));
                    setOneofPresent(message, iNumberAt, pos);
                }
                break;
            case 68:
                mergeOneofMessage(message, other, pos);
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void mergeMessage(T targetParent, T sourceParent, int pos) {
        if (isFieldPresent(sourceParent, pos)) {
            long jOffset = offset(typeAndOffsetAt(pos));
            Unsafe unsafe = UNSAFE;
            Object object = unsafe.getObject(sourceParent, jOffset);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + numberAt(pos) + " is present but null: " + sourceParent);
            }
            Schema messageFieldSchema = getMessageFieldSchema(pos);
            if (!isFieldPresent(targetParent, pos)) {
                if (!isMutable(object)) {
                    unsafe.putObject(targetParent, jOffset, object);
                } else {
                    Object objNewInstance = messageFieldSchema.newInstance();
                    messageFieldSchema.mergeFrom(objNewInstance, object);
                    unsafe.putObject(targetParent, jOffset, objNewInstance);
                }
                setFieldPresent(targetParent, pos);
                return;
            }
            Object object2 = unsafe.getObject(targetParent, jOffset);
            if (!isMutable(object2)) {
                Object objNewInstance2 = messageFieldSchema.newInstance();
                messageFieldSchema.mergeFrom(objNewInstance2, object2);
                unsafe.putObject(targetParent, jOffset, objNewInstance2);
                object2 = objNewInstance2;
            }
            messageFieldSchema.mergeFrom(object2, object);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void mergeOneofMessage(T targetParent, T sourceParent, int pos) {
        int iNumberAt = numberAt(pos);
        if (isOneofPresent(sourceParent, iNumberAt, pos)) {
            long jOffset = offset(typeAndOffsetAt(pos));
            Unsafe unsafe = UNSAFE;
            Object object = unsafe.getObject(sourceParent, jOffset);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + numberAt(pos) + " is present but null: " + sourceParent);
            }
            Schema messageFieldSchema = getMessageFieldSchema(pos);
            if (!isOneofPresent(targetParent, iNumberAt, pos)) {
                if (!isMutable(object)) {
                    unsafe.putObject(targetParent, jOffset, object);
                } else {
                    Object objNewInstance = messageFieldSchema.newInstance();
                    messageFieldSchema.mergeFrom(objNewInstance, object);
                    unsafe.putObject(targetParent, jOffset, objNewInstance);
                }
                setOneofPresent(targetParent, iNumberAt, pos);
                return;
            }
            Object object2 = unsafe.getObject(targetParent, jOffset);
            if (!isMutable(object2)) {
                Object objNewInstance2 = messageFieldSchema.newInstance();
                messageFieldSchema.mergeFrom(objNewInstance2, object2);
                unsafe.putObject(targetParent, jOffset, objNewInstance2);
                object2 = objNewInstance2;
            }
            messageFieldSchema.mergeFrom(object2, object);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:246:0x054b A[PHI: r0 r1
      0x054b: PHI (r0v5 com.google.protobuf.MessageSchema<T>) = 
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v27 com.google.protobuf.MessageSchema<T>)
      (r0v33 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
      (r0v1 com.google.protobuf.MessageSchema<T>)
     binds: [B:21:0x005a, B:244:0x0541, B:210:0x049a, B:242:0x0537, B:237:0x0521, B:186:0x0413, B:161:0x032d, B:155:0x0310, B:149:0x02f3, B:143:0x02d6, B:137:0x02b8, B:131:0x029a, B:125:0x027c, B:119:0x025e, B:113:0x0240, B:107:0x0222, B:101:0x0204, B:95:0x01e6, B:89:0x01c8, B:83:0x01aa, B:78:0x0176, B:75:0x016a, B:72:0x015a, B:69:0x014a, B:66:0x013a, B:63:0x012e, B:60:0x0122, B:57:0x0115, B:51:0x00f7, B:48:0x00e3, B:45:0x00d1, B:42:0x00c1, B:39:0x00b1, B:36:0x00a5, B:33:0x0099, B:30:0x0089, B:27:0x0079, B:24:0x0063] A[DONT_GENERATE, DONT_INLINE]
      0x054b: PHI (r1v2 T) = 
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v3 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
      (r1v1 T)
     binds: [B:21:0x005a, B:244:0x0541, B:210:0x049a, B:242:0x0537, B:237:0x0521, B:186:0x0413, B:161:0x032d, B:155:0x0310, B:149:0x02f3, B:143:0x02d6, B:137:0x02b8, B:131:0x029a, B:125:0x027c, B:119:0x025e, B:113:0x0240, B:107:0x0222, B:101:0x0204, B:95:0x01e6, B:89:0x01c8, B:83:0x01aa, B:78:0x0176, B:75:0x016a, B:72:0x015a, B:69:0x014a, B:66:0x013a, B:63:0x012e, B:60:0x0122, B:57:0x0115, B:51:0x00f7, B:48:0x00e3, B:45:0x00d1, B:42:0x00c1, B:39:0x00b1, B:36:0x00a5, B:33:0x0099, B:30:0x0089, B:27:0x0079, B:24:0x0063] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.google.protobuf.Schema
    public int getSerializedSize(T message) {
        int i;
        int iComputeDoubleSize;
        int iComputeFloatSize;
        int iComputeInt64Size;
        int iComputeSizeFixed64ListNoTag;
        int iComputeTagSize;
        int iComputeUInt32SizeNoTag;
        MessageSchema<T> messageSchema = this;
        T t = message;
        Unsafe unsafe = UNSAFE;
        int i2 = 1048575;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 1048575;
        while (i3 < messageSchema.buffer.length) {
            int iTypeAndOffsetAt = messageSchema.typeAndOffsetAt(i3);
            int iType = type(iTypeAndOffsetAt);
            int iNumberAt = messageSchema.numberAt(i3);
            int i7 = messageSchema.buffer[i3 + 2];
            int i8 = i7 & i2;
            if (iType <= 17) {
                if (i8 != i6) {
                    i4 = i8 == i2 ? 0 : unsafe.getInt(t, i8);
                    i6 = i8;
                }
                i = 1 << (i7 >>> 20);
            } else {
                i = 0;
            }
            int i9 = i5;
            long jOffset = offset(iTypeAndOffsetAt);
            if (iType < FieldType.DOUBLE_LIST_PACKED.id() || iType > FieldType.SINT64_LIST_PACKED.id()) {
                i8 = 0;
            }
            switch (iType) {
                case 0:
                    if (!messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        i5 = i9;
                    } else {
                        iComputeDoubleSize = CodedOutputStream.computeDoubleSize(iNumberAt, 0.0d);
                        i5 = i9 + iComputeDoubleSize;
                    }
                    break;
                case 1:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        iComputeFloatSize = CodedOutputStream.computeFloatSize(iNumberAt, 0.0f);
                        i5 = i9 + iComputeFloatSize;
                        messageSchema = this;
                        t = message;
                    }
                    messageSchema = this;
                    t = message;
                    i5 = i9;
                    break;
                case 2:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        iComputeInt64Size = CodedOutputStream.computeInt64Size(iNumberAt, unsafe.getLong(t, jOffset));
                        i5 = i9 + iComputeInt64Size;
                        messageSchema = this;
                    }
                    messageSchema = this;
                    i5 = i9;
                    break;
                case 3:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        iComputeInt64Size = CodedOutputStream.computeUInt64Size(iNumberAt, unsafe.getLong(t, jOffset));
                        i5 = i9 + iComputeInt64Size;
                        messageSchema = this;
                    }
                    messageSchema = this;
                    i5 = i9;
                    break;
                case 4:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        iComputeInt64Size = CodedOutputStream.computeInt32Size(iNumberAt, unsafe.getInt(t, jOffset));
                        i5 = i9 + iComputeInt64Size;
                        messageSchema = this;
                    }
                    messageSchema = this;
                    i5 = i9;
                    break;
                case 5:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        iComputeFloatSize = CodedOutputStream.computeFixed64Size(iNumberAt, 0L);
                        i5 = i9 + iComputeFloatSize;
                        messageSchema = this;
                        t = message;
                    }
                    messageSchema = this;
                    t = message;
                    i5 = i9;
                    break;
                case 6:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        iComputeFloatSize = CodedOutputStream.computeFixed32Size(iNumberAt, 0);
                        i5 = i9 + iComputeFloatSize;
                        messageSchema = this;
                        t = message;
                    }
                    messageSchema = this;
                    t = message;
                    i5 = i9;
                    break;
                case 7:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        iComputeFloatSize = CodedOutputStream.computeBoolSize(iNumberAt, true);
                        i5 = i9 + iComputeFloatSize;
                        messageSchema = this;
                        t = message;
                    }
                    messageSchema = this;
                    t = message;
                    i5 = i9;
                    break;
                case 8:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        Object object = unsafe.getObject(t, jOffset);
                        if (object instanceof ByteString) {
                            iComputeInt64Size = CodedOutputStream.computeBytesSize(iNumberAt, (ByteString) object);
                        } else {
                            iComputeInt64Size = CodedOutputStream.computeStringSize(iNumberAt, (String) object);
                        }
                        i5 = i9 + iComputeInt64Size;
                        messageSchema = this;
                    }
                    messageSchema = this;
                    i5 = i9;
                    break;
                case 9:
                    if (!messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        i5 = i9;
                    } else {
                        iComputeDoubleSize = SchemaUtil.computeSizeMessage(iNumberAt, unsafe.getObject(t, jOffset), messageSchema.getMessageFieldSchema(i3));
                        i5 = i9 + iComputeDoubleSize;
                    }
                    break;
                case 10:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        iComputeInt64Size = CodedOutputStream.computeBytesSize(iNumberAt, (ByteString) unsafe.getObject(t, jOffset));
                        i5 = i9 + iComputeInt64Size;
                        messageSchema = this;
                    }
                    messageSchema = this;
                    i5 = i9;
                    break;
                case 11:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        iComputeInt64Size = CodedOutputStream.computeUInt32Size(iNumberAt, unsafe.getInt(t, jOffset));
                        i5 = i9 + iComputeInt64Size;
                        messageSchema = this;
                    }
                    messageSchema = this;
                    i5 = i9;
                    break;
                case 12:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        iComputeInt64Size = CodedOutputStream.computeEnumSize(iNumberAt, unsafe.getInt(t, jOffset));
                        i5 = i9 + iComputeInt64Size;
                        messageSchema = this;
                    }
                    messageSchema = this;
                    i5 = i9;
                    break;
                case 13:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        iComputeFloatSize = CodedOutputStream.computeSFixed32Size(iNumberAt, 0);
                        i5 = i9 + iComputeFloatSize;
                        messageSchema = this;
                        t = message;
                    }
                    messageSchema = this;
                    t = message;
                    i5 = i9;
                    break;
                case 14:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        iComputeFloatSize = CodedOutputStream.computeSFixed64Size(iNumberAt, 0L);
                        i5 = i9 + iComputeFloatSize;
                        messageSchema = this;
                        t = message;
                    }
                    messageSchema = this;
                    t = message;
                    i5 = i9;
                    break;
                case 15:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        iComputeInt64Size = CodedOutputStream.computeSInt32Size(iNumberAt, unsafe.getInt(t, jOffset));
                        i5 = i9 + iComputeInt64Size;
                        messageSchema = this;
                    }
                    messageSchema = this;
                    i5 = i9;
                    break;
                case 16:
                    if (messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        iComputeInt64Size = CodedOutputStream.computeSInt64Size(iNumberAt, unsafe.getLong(t, jOffset));
                        i5 = i9 + iComputeInt64Size;
                        messageSchema = this;
                    }
                    messageSchema = this;
                    i5 = i9;
                    break;
                case 17:
                    if (!messageSchema.isFieldPresent(t, i3, i6, i4, i)) {
                        i5 = i9;
                    } else {
                        iComputeDoubleSize = SchemaUtil.computeGroupSize(iNumberAt, (MessageLite) unsafe.getObject(t, jOffset), messageSchema.getMessageFieldSchema(i3));
                        i5 = i9 + iComputeDoubleSize;
                    }
                    break;
                case 18:
                    iComputeDoubleSize = SchemaUtil.computeSizeFixed64List(iNumberAt, (List) unsafe.getObject(t, jOffset), false);
                    i5 = i9 + iComputeDoubleSize;
                    break;
                case 19:
                    iComputeDoubleSize = SchemaUtil.computeSizeFixed32List(iNumberAt, (List) unsafe.getObject(t, jOffset), false);
                    i5 = i9 + iComputeDoubleSize;
                    break;
                case 20:
                    iComputeDoubleSize = SchemaUtil.computeSizeInt64List(iNumberAt, (List) unsafe.getObject(t, jOffset), false);
                    i5 = i9 + iComputeDoubleSize;
                    break;
                case 21:
                    iComputeDoubleSize = SchemaUtil.computeSizeUInt64List(iNumberAt, (List) unsafe.getObject(t, jOffset), false);
                    i5 = i9 + iComputeDoubleSize;
                    break;
                case 22:
                    iComputeDoubleSize = SchemaUtil.computeSizeInt32List(iNumberAt, (List) unsafe.getObject(t, jOffset), false);
                    i5 = i9 + iComputeDoubleSize;
                    break;
                case 23:
                    iComputeDoubleSize = SchemaUtil.computeSizeFixed64List(iNumberAt, (List) unsafe.getObject(t, jOffset), false);
                    i5 = i9 + iComputeDoubleSize;
                    break;
                case 24:
                    iComputeDoubleSize = SchemaUtil.computeSizeFixed32List(iNumberAt, (List) unsafe.getObject(t, jOffset), false);
                    i5 = i9 + iComputeDoubleSize;
                    break;
                case 25:
                    iComputeDoubleSize = SchemaUtil.computeSizeBoolList(iNumberAt, (List) unsafe.getObject(t, jOffset), false);
                    i5 = i9 + iComputeDoubleSize;
                    break;
                case 26:
                    iComputeDoubleSize = SchemaUtil.computeSizeStringList(iNumberAt, (List) unsafe.getObject(t, jOffset));
                    i5 = i9 + iComputeDoubleSize;
                    break;
                case 27:
                    iComputeDoubleSize = SchemaUtil.computeSizeMessageList(iNumberAt, (List) unsafe.getObject(t, jOffset), messageSchema.getMessageFieldSchema(i3));
                    i5 = i9 + iComputeDoubleSize;
                    break;
                case 28:
                    iComputeDoubleSize = SchemaUtil.computeSizeByteStringList(iNumberAt, (List) unsafe.getObject(t, jOffset));
                    i5 = i9 + iComputeDoubleSize;
                    break;
                case 29:
                    iComputeDoubleSize = SchemaUtil.computeSizeUInt32List(iNumberAt, (List) unsafe.getObject(t, jOffset), false);
                    i5 = i9 + iComputeDoubleSize;
                    break;
                case 30:
                    iComputeDoubleSize = SchemaUtil.computeSizeEnumList(iNumberAt, (List) unsafe.getObject(t, jOffset), false);
                    i5 = i9 + iComputeDoubleSize;
                    break;
                case 31:
                    iComputeDoubleSize = SchemaUtil.computeSizeFixed32List(iNumberAt, (List) unsafe.getObject(t, jOffset), false);
                    i5 = i9 + iComputeDoubleSize;
                    break;
                case 32:
                    iComputeDoubleSize = SchemaUtil.computeSizeFixed64List(iNumberAt, (List) unsafe.getObject(t, jOffset), false);
                    i5 = i9 + iComputeDoubleSize;
                    break;
                case 33:
                    iComputeDoubleSize = SchemaUtil.computeSizeSInt32List(iNumberAt, (List) unsafe.getObject(t, jOffset), false);
                    i5 = i9 + iComputeDoubleSize;
                    break;
                case 34:
                    iComputeDoubleSize = SchemaUtil.computeSizeSInt64List(iNumberAt, (List) unsafe.getObject(t, jOffset), false);
                    i5 = i9 + iComputeDoubleSize;
                    break;
                case 35:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag <= 0) {
                        i5 = i9;
                    } else {
                        if (messageSchema.useCachedSizeField) {
                            unsafe.putInt(t, i8, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i5 = i9 + iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 36:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag <= 0) {
                        i5 = i9;
                    } else {
                        if (messageSchema.useCachedSizeField) {
                            unsafe.putInt(t, i8, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i5 = i9 + iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 37:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag <= 0) {
                        i5 = i9;
                    } else {
                        if (messageSchema.useCachedSizeField) {
                            unsafe.putInt(t, i8, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i5 = i9 + iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 38:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag <= 0) {
                        i5 = i9;
                    } else {
                        if (messageSchema.useCachedSizeField) {
                            unsafe.putInt(t, i8, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i5 = i9 + iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 39:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag <= 0) {
                        i5 = i9;
                    } else {
                        if (messageSchema.useCachedSizeField) {
                            unsafe.putInt(t, i8, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i5 = i9 + iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 40:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag <= 0) {
                        i5 = i9;
                    } else {
                        if (messageSchema.useCachedSizeField) {
                            unsafe.putInt(t, i8, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i5 = i9 + iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 41:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag <= 0) {
                        i5 = i9;
                    } else {
                        if (messageSchema.useCachedSizeField) {
                            unsafe.putInt(t, i8, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i5 = i9 + iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 42:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeBoolListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag <= 0) {
                        i5 = i9;
                    } else {
                        if (messageSchema.useCachedSizeField) {
                            unsafe.putInt(t, i8, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i5 = i9 + iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 43:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag <= 0) {
                        i5 = i9;
                    } else {
                        if (messageSchema.useCachedSizeField) {
                            unsafe.putInt(t, i8, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i5 = i9 + iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 44:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag <= 0) {
                        i5 = i9;
                    } else {
                        if (messageSchema.useCachedSizeField) {
                            unsafe.putInt(t, i8, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i5 = i9 + iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 45:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag <= 0) {
                        i5 = i9;
                    } else {
                        if (messageSchema.useCachedSizeField) {
                            unsafe.putInt(t, i8, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i5 = i9 + iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 46:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag <= 0) {
                        i5 = i9;
                    } else {
                        if (messageSchema.useCachedSizeField) {
                            unsafe.putInt(t, i8, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i5 = i9 + iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 47:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag <= 0) {
                        i5 = i9;
                    } else {
                        if (messageSchema.useCachedSizeField) {
                            unsafe.putInt(t, i8, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i5 = i9 + iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 48:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(t, jOffset));
                    if (iComputeSizeFixed64ListNoTag <= 0) {
                        i5 = i9;
                    } else {
                        if (messageSchema.useCachedSizeField) {
                            unsafe.putInt(t, i8, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeTagSize = CodedOutputStream.computeTagSize(iNumberAt);
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag);
                        i5 = i9 + iComputeTagSize + iComputeUInt32SizeNoTag + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 49:
                    iComputeDoubleSize = SchemaUtil.computeSizeGroupList(iNumberAt, (List) unsafe.getObject(t, jOffset), messageSchema.getMessageFieldSchema(i3));
                    i5 = i9 + iComputeDoubleSize;
                    break;
                case 50:
                    iComputeDoubleSize = messageSchema.mapFieldSchema.getSerializedSize(iNumberAt, unsafe.getObject(t, jOffset), messageSchema.getMapFieldDefaultEntry(i3));
                    i5 = i9 + iComputeDoubleSize;
                    break;
                case 51:
                    if (!messageSchema.isOneofPresent(t, iNumberAt, i3)) {
                        i5 = i9;
                    } else {
                        iComputeDoubleSize = CodedOutputStream.computeDoubleSize(iNumberAt, 0.0d);
                        i5 = i9 + iComputeDoubleSize;
                    }
                    break;
                case 52:
                    if (!messageSchema.isOneofPresent(t, iNumberAt, i3)) {
                        i5 = i9;
                    } else {
                        iComputeDoubleSize = CodedOutputStream.computeFloatSize(iNumberAt, 0.0f);
                        i5 = i9 + iComputeDoubleSize;
                    }
                    break;
                case 53:
                    if (!messageSchema.isOneofPresent(t, iNumberAt, i3)) {
                        i5 = i9;
                    } else {
                        iComputeDoubleSize = CodedOutputStream.computeInt64Size(iNumberAt, oneofLongAt(t, jOffset));
                        i5 = i9 + iComputeDoubleSize;
                    }
                    break;
                case 54:
                    if (!messageSchema.isOneofPresent(t, iNumberAt, i3)) {
                        i5 = i9;
                    } else {
                        iComputeDoubleSize = CodedOutputStream.computeUInt64Size(iNumberAt, oneofLongAt(t, jOffset));
                        i5 = i9 + iComputeDoubleSize;
                    }
                    break;
                case 55:
                    if (!messageSchema.isOneofPresent(t, iNumberAt, i3)) {
                        i5 = i9;
                    } else {
                        iComputeDoubleSize = CodedOutputStream.computeInt32Size(iNumberAt, oneofIntAt(t, jOffset));
                        i5 = i9 + iComputeDoubleSize;
                    }
                    break;
                case 56:
                    if (!messageSchema.isOneofPresent(t, iNumberAt, i3)) {
                        i5 = i9;
                    } else {
                        iComputeDoubleSize = CodedOutputStream.computeFixed64Size(iNumberAt, 0L);
                        i5 = i9 + iComputeDoubleSize;
                    }
                    break;
                case 57:
                    if (!messageSchema.isOneofPresent(t, iNumberAt, i3)) {
                        i5 = i9;
                    } else {
                        iComputeDoubleSize = CodedOutputStream.computeFixed32Size(iNumberAt, 0);
                        i5 = i9 + iComputeDoubleSize;
                    }
                    break;
                case 58:
                    if (!messageSchema.isOneofPresent(t, iNumberAt, i3)) {
                        i5 = i9;
                    } else {
                        iComputeDoubleSize = CodedOutputStream.computeBoolSize(iNumberAt, true);
                        i5 = i9 + iComputeDoubleSize;
                    }
                    break;
                case 59:
                    if (!messageSchema.isOneofPresent(t, iNumberAt, i3)) {
                        i5 = i9;
                    } else {
                        Object object2 = unsafe.getObject(t, jOffset);
                        if (object2 instanceof ByteString) {
                            iComputeDoubleSize = CodedOutputStream.computeBytesSize(iNumberAt, (ByteString) object2);
                        } else {
                            iComputeDoubleSize = CodedOutputStream.computeStringSize(iNumberAt, (String) object2);
                        }
                        i5 = i9 + iComputeDoubleSize;
                    }
                    break;
                case 60:
                    if (!messageSchema.isOneofPresent(t, iNumberAt, i3)) {
                        i5 = i9;
                    } else {
                        iComputeDoubleSize = SchemaUtil.computeSizeMessage(iNumberAt, unsafe.getObject(t, jOffset), messageSchema.getMessageFieldSchema(i3));
                        i5 = i9 + iComputeDoubleSize;
                    }
                    break;
                case 61:
                    if (!messageSchema.isOneofPresent(t, iNumberAt, i3)) {
                        i5 = i9;
                    } else {
                        iComputeDoubleSize = CodedOutputStream.computeBytesSize(iNumberAt, (ByteString) unsafe.getObject(t, jOffset));
                        i5 = i9 + iComputeDoubleSize;
                    }
                    break;
                case 62:
                    if (!messageSchema.isOneofPresent(t, iNumberAt, i3)) {
                        i5 = i9;
                    } else {
                        iComputeDoubleSize = CodedOutputStream.computeUInt32Size(iNumberAt, oneofIntAt(t, jOffset));
                        i5 = i9 + iComputeDoubleSize;
                    }
                    break;
                case 63:
                    if (!messageSchema.isOneofPresent(t, iNumberAt, i3)) {
                        i5 = i9;
                    } else {
                        iComputeDoubleSize = CodedOutputStream.computeEnumSize(iNumberAt, oneofIntAt(t, jOffset));
                        i5 = i9 + iComputeDoubleSize;
                    }
                    break;
                case 64:
                    if (!messageSchema.isOneofPresent(t, iNumberAt, i3)) {
                        i5 = i9;
                    } else {
                        iComputeDoubleSize = CodedOutputStream.computeSFixed32Size(iNumberAt, 0);
                        i5 = i9 + iComputeDoubleSize;
                    }
                    break;
                case 65:
                    if (!messageSchema.isOneofPresent(t, iNumberAt, i3)) {
                        i5 = i9;
                    } else {
                        iComputeDoubleSize = CodedOutputStream.computeSFixed64Size(iNumberAt, 0L);
                        i5 = i9 + iComputeDoubleSize;
                    }
                    break;
                case 66:
                    if (!messageSchema.isOneofPresent(t, iNumberAt, i3)) {
                        i5 = i9;
                    } else {
                        iComputeDoubleSize = CodedOutputStream.computeSInt32Size(iNumberAt, oneofIntAt(t, jOffset));
                        i5 = i9 + iComputeDoubleSize;
                    }
                    break;
                case 67:
                    if (!messageSchema.isOneofPresent(t, iNumberAt, i3)) {
                        i5 = i9;
                    } else {
                        iComputeDoubleSize = CodedOutputStream.computeSInt64Size(iNumberAt, oneofLongAt(t, jOffset));
                        i5 = i9 + iComputeDoubleSize;
                    }
                    break;
                case 68:
                    if (!messageSchema.isOneofPresent(t, iNumberAt, i3)) {
                        i5 = i9;
                    } else {
                        iComputeDoubleSize = SchemaUtil.computeGroupSize(iNumberAt, (MessageLite) unsafe.getObject(t, jOffset), messageSchema.getMessageFieldSchema(i3));
                        i5 = i9 + iComputeDoubleSize;
                    }
                    break;
                default:
                    i5 = i9;
                    break;
            }
            i3 += 3;
            i2 = 1048575;
        }
        int unknownFieldsSerializedSize = i5 + messageSchema.getUnknownFieldsSerializedSize(messageSchema.unknownFieldSchema, t);
        return messageSchema.hasExtensions ? unknownFieldsSerializedSize + messageSchema.extensionSchema.getExtensions(t).getSerializedSize() : unknownFieldsSerializedSize;
    }

    private <UT, UB> int getUnknownFieldsSerializedSize(UnknownFieldSchema<UT, UB> schema, T message) {
        return schema.getSerializedSize(schema.getFromMessage(message));
    }

    @Override // com.google.protobuf.Schema
    public void writeTo(T message, Writer writer) throws IOException {
        if (writer.fieldOrder() == Writer.FieldOrder.DESCENDING) {
            writeFieldsInDescendingOrder(message, writer);
        } else {
            writeFieldsInAscendingOrder(message, writer);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:7:0x0022  */
    private void writeFieldsInAscendingOrder(T message, Writer writer) throws IOException {
        Map.Entry<?, ?> entry;
        Iterator it;
        boolean z;
        int i;
        int i2;
        int i3;
        boolean z2;
        MessageSchema<T> messageSchema = this;
        if (messageSchema.hasExtensions) {
            FieldSet<T> extensions = messageSchema.extensionSchema.getExtensions(message);
            if (extensions.isEmpty()) {
                entry = null;
                it = null;
            } else {
                Iterator it2 = extensions.iterator();
                entry = (Map.Entry) it2.next();
                it = it2;
            }
        } else {
            entry = null;
            it = null;
        }
        int length = messageSchema.buffer.length;
        Unsafe unsafe = UNSAFE;
        int i4 = 1048575;
        int i5 = 0;
        int i6 = 0;
        while (i5 < length) {
            int iTypeAndOffsetAt = messageSchema.typeAndOffsetAt(i5);
            int iNumberAt = messageSchema.numberAt(i5);
            int iType = type(iTypeAndOffsetAt);
            if (iType <= 17) {
                int i7 = messageSchema.buffer[i5 + 2];
                z = true;
                int i8 = i7 & 1048575;
                if (i8 != i4) {
                    i6 = i8 == 1048575 ? 0 : unsafe.getInt(message, i8);
                    i4 = i8;
                }
                i = i4;
                i2 = i6;
                i3 = 1 << (i7 >>> 20);
            } else {
                z = true;
                i = i4;
                i2 = i6;
                i3 = 0;
            }
            while (entry != null && messageSchema.extensionSchema.extensionNumber(entry) <= iNumberAt) {
                messageSchema.extensionSchema.serializeExtension(writer, entry);
                entry = it.hasNext() ? (Map.Entry) it.next() : null;
            }
            long jOffset = offset(iTypeAndOffsetAt);
            switch (iType) {
                case 0:
                    if (messageSchema.isFieldPresent(message, i5, i, i2, i3)) {
                        writer.writeDouble(iNumberAt, doubleAt(message, jOffset));
                    }
                    break;
                case 1:
                    if (messageSchema.isFieldPresent(message, i5, i, i2, i3)) {
                        writer.writeFloat(iNumberAt, floatAt(message, jOffset));
                    }
                    messageSchema = this;
                    break;
                case 2:
                    if (messageSchema.isFieldPresent(message, i5, i, i2, i3)) {
                        writer.writeInt64(iNumberAt, unsafe.getLong(message, jOffset));
                    }
                    messageSchema = this;
                    break;
                case 3:
                    if (messageSchema.isFieldPresent(message, i5, i, i2, i3)) {
                        writer.writeUInt64(iNumberAt, unsafe.getLong(message, jOffset));
                    }
                    messageSchema = this;
                    break;
                case 4:
                    if (messageSchema.isFieldPresent(message, i5, i, i2, i3)) {
                        writer.writeInt32(iNumberAt, unsafe.getInt(message, jOffset));
                    }
                    messageSchema = this;
                    break;
                case 5:
                    if (messageSchema.isFieldPresent(message, i5, i, i2, i3)) {
                        writer.writeFixed64(iNumberAt, unsafe.getLong(message, jOffset));
                    }
                    messageSchema = this;
                    break;
                case 6:
                    if (messageSchema.isFieldPresent(message, i5, i, i2, i3)) {
                        writer.writeFixed32(iNumberAt, unsafe.getInt(message, jOffset));
                    }
                    messageSchema = this;
                    break;
                case 7:
                    if (messageSchema.isFieldPresent(message, i5, i, i2, i3)) {
                        writer.writeBool(iNumberAt, booleanAt(message, jOffset));
                    }
                    messageSchema = this;
                    break;
                case 8:
                    if (messageSchema.isFieldPresent(message, i5, i, i2, i3)) {
                        messageSchema.writeString(iNumberAt, unsafe.getObject(message, jOffset), writer);
                    }
                    break;
                case 9:
                    if (messageSchema.isFieldPresent(message, i5, i, i2, i3)) {
                        writer.writeMessage(iNumberAt, unsafe.getObject(message, jOffset), messageSchema.getMessageFieldSchema(i5));
                    }
                    break;
                case 10:
                    if (messageSchema.isFieldPresent(message, i5, i, i2, i3)) {
                        writer.writeBytes(iNumberAt, (ByteString) unsafe.getObject(message, jOffset));
                    }
                    messageSchema = this;
                    break;
                case 11:
                    if (messageSchema.isFieldPresent(message, i5, i, i2, i3)) {
                        writer.writeUInt32(iNumberAt, unsafe.getInt(message, jOffset));
                    }
                    messageSchema = this;
                    break;
                case 12:
                    if (messageSchema.isFieldPresent(message, i5, i, i2, i3)) {
                        writer.writeEnum(iNumberAt, unsafe.getInt(message, jOffset));
                    }
                    messageSchema = this;
                    break;
                case 13:
                    if (messageSchema.isFieldPresent(message, i5, i, i2, i3)) {
                        writer.writeSFixed32(iNumberAt, unsafe.getInt(message, jOffset));
                    }
                    messageSchema = this;
                    break;
                case 14:
                    if (messageSchema.isFieldPresent(message, i5, i, i2, i3)) {
                        writer.writeSFixed64(iNumberAt, unsafe.getLong(message, jOffset));
                    }
                    messageSchema = this;
                    break;
                case 15:
                    if (messageSchema.isFieldPresent(message, i5, i, i2, i3)) {
                        writer.writeSInt32(iNumberAt, unsafe.getInt(message, jOffset));
                    }
                    messageSchema = this;
                    break;
                case 16:
                    if (messageSchema.isFieldPresent(message, i5, i, i2, i3)) {
                        writer.writeSInt64(iNumberAt, unsafe.getLong(message, jOffset));
                    }
                    messageSchema = this;
                    break;
                case 17:
                    if (messageSchema.isFieldPresent(message, i5, i, i2, i3)) {
                        writer.writeGroup(iNumberAt, unsafe.getObject(message, jOffset), messageSchema.getMessageFieldSchema(i5));
                    }
                    break;
                case 18:
                    SchemaUtil.writeDoubleList(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, false);
                    break;
                case 19:
                    SchemaUtil.writeFloatList(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, false);
                    break;
                case 20:
                    SchemaUtil.writeInt64List(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, false);
                    break;
                case 21:
                    SchemaUtil.writeUInt64List(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, false);
                    break;
                case 22:
                    SchemaUtil.writeInt32List(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, false);
                    break;
                case 23:
                    SchemaUtil.writeFixed64List(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, false);
                    break;
                case 24:
                    SchemaUtil.writeFixed32List(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, false);
                    break;
                case 25:
                    SchemaUtil.writeBoolList(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, false);
                    break;
                case 26:
                    SchemaUtil.writeStringList(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer);
                    break;
                case 27:
                    SchemaUtil.writeMessageList(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, messageSchema.getMessageFieldSchema(i5));
                    break;
                case 28:
                    SchemaUtil.writeBytesList(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer);
                    break;
                case 29:
                    z2 = false;
                    SchemaUtil.writeUInt32List(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, false);
                    break;
                case 30:
                    z2 = false;
                    SchemaUtil.writeEnumList(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, false);
                    break;
                case 31:
                    z2 = false;
                    SchemaUtil.writeSFixed32List(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, false);
                    break;
                case 32:
                    z2 = false;
                    SchemaUtil.writeSFixed64List(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, false);
                    break;
                case 33:
                    z2 = false;
                    SchemaUtil.writeSInt32List(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, false);
                    break;
                case 34:
                    z2 = false;
                    SchemaUtil.writeSInt64List(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, false);
                    break;
                case 35:
                    SchemaUtil.writeDoubleList(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, z);
                    break;
                case 36:
                    SchemaUtil.writeFloatList(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, z);
                    break;
                case 37:
                    SchemaUtil.writeInt64List(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, z);
                    break;
                case 38:
                    SchemaUtil.writeUInt64List(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, z);
                    break;
                case 39:
                    SchemaUtil.writeInt32List(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, z);
                    break;
                case 40:
                    SchemaUtil.writeFixed64List(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, z);
                    break;
                case 41:
                    SchemaUtil.writeFixed32List(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, z);
                    break;
                case 42:
                    SchemaUtil.writeBoolList(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, z);
                    break;
                case 43:
                    SchemaUtil.writeUInt32List(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, z);
                    break;
                case 44:
                    SchemaUtil.writeEnumList(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, z);
                    break;
                case 45:
                    SchemaUtil.writeSFixed32List(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, z);
                    break;
                case 46:
                    SchemaUtil.writeSFixed64List(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, z);
                    break;
                case 47:
                    SchemaUtil.writeSInt32List(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, z);
                    break;
                case 48:
                    SchemaUtil.writeSInt64List(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, z);
                    break;
                case 49:
                    SchemaUtil.writeGroupList(messageSchema.numberAt(i5), (List) unsafe.getObject(message, jOffset), writer, messageSchema.getMessageFieldSchema(i5));
                    break;
                case 50:
                    messageSchema.writeMapHelper(writer, iNumberAt, unsafe.getObject(message, jOffset), i5);
                    break;
                case 51:
                    if (messageSchema.isOneofPresent(message, iNumberAt, i5)) {
                        writer.writeDouble(iNumberAt, oneofDoubleAt(message, jOffset));
                    }
                    break;
                case 52:
                    if (messageSchema.isOneofPresent(message, iNumberAt, i5)) {
                        writer.writeFloat(iNumberAt, oneofFloatAt(message, jOffset));
                    }
                    break;
                case 53:
                    if (messageSchema.isOneofPresent(message, iNumberAt, i5)) {
                        writer.writeInt64(iNumberAt, oneofLongAt(message, jOffset));
                    }
                    break;
                case 54:
                    if (messageSchema.isOneofPresent(message, iNumberAt, i5)) {
                        writer.writeUInt64(iNumberAt, oneofLongAt(message, jOffset));
                    }
                    break;
                case 55:
                    if (messageSchema.isOneofPresent(message, iNumberAt, i5)) {
                        writer.writeInt32(iNumberAt, oneofIntAt(message, jOffset));
                    }
                    break;
                case 56:
                    if (messageSchema.isOneofPresent(message, iNumberAt, i5)) {
                        writer.writeFixed64(iNumberAt, oneofLongAt(message, jOffset));
                    }
                    break;
                case 57:
                    if (messageSchema.isOneofPresent(message, iNumberAt, i5)) {
                        writer.writeFixed32(iNumberAt, oneofIntAt(message, jOffset));
                    }
                    break;
                case 58:
                    if (messageSchema.isOneofPresent(message, iNumberAt, i5)) {
                        writer.writeBool(iNumberAt, oneofBooleanAt(message, jOffset));
                    }
                    break;
                case 59:
                    if (messageSchema.isOneofPresent(message, iNumberAt, i5)) {
                        messageSchema.writeString(iNumberAt, unsafe.getObject(message, jOffset), writer);
                    }
                    break;
                case 60:
                    if (messageSchema.isOneofPresent(message, iNumberAt, i5)) {
                        writer.writeMessage(iNumberAt, unsafe.getObject(message, jOffset), messageSchema.getMessageFieldSchema(i5));
                    }
                    break;
                case 61:
                    if (messageSchema.isOneofPresent(message, iNumberAt, i5)) {
                        writer.writeBytes(iNumberAt, (ByteString) unsafe.getObject(message, jOffset));
                    }
                    break;
                case 62:
                    if (messageSchema.isOneofPresent(message, iNumberAt, i5)) {
                        writer.writeUInt32(iNumberAt, oneofIntAt(message, jOffset));
                    }
                    break;
                case 63:
                    if (messageSchema.isOneofPresent(message, iNumberAt, i5)) {
                        writer.writeEnum(iNumberAt, oneofIntAt(message, jOffset));
                    }
                    break;
                case 64:
                    if (messageSchema.isOneofPresent(message, iNumberAt, i5)) {
                        writer.writeSFixed32(iNumberAt, oneofIntAt(message, jOffset));
                    }
                    break;
                case 65:
                    if (messageSchema.isOneofPresent(message, iNumberAt, i5)) {
                        writer.writeSFixed64(iNumberAt, oneofLongAt(message, jOffset));
                    }
                    break;
                case 66:
                    if (messageSchema.isOneofPresent(message, iNumberAt, i5)) {
                        writer.writeSInt32(iNumberAt, oneofIntAt(message, jOffset));
                    }
                    break;
                case 67:
                    if (messageSchema.isOneofPresent(message, iNumberAt, i5)) {
                        writer.writeSInt64(iNumberAt, oneofLongAt(message, jOffset));
                    }
                    break;
                case 68:
                    if (messageSchema.isOneofPresent(message, iNumberAt, i5)) {
                        writer.writeGroup(iNumberAt, unsafe.getObject(message, jOffset), messageSchema.getMessageFieldSchema(i5));
                    }
                    break;
                default:
                    break;
            }
            i5 += 3;
            i6 = i2;
            i4 = i;
            entry = entry;
        }
        while (entry != null) {
            messageSchema.extensionSchema.serializeExtension(writer, entry);
            entry = it.hasNext() ? (Map.Entry) it.next() : null;
        }
        messageSchema.writeUnknownInMessageTo(messageSchema.unknownFieldSchema, message, writer);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0021  */
    private void writeFieldsInDescendingOrder(T message, Writer writer) throws IOException {
        Iterator itDescendingIterator;
        Map.Entry<?, ?> entry;
        writeUnknownInMessageTo(this.unknownFieldSchema, message, writer);
        if (this.hasExtensions) {
            FieldSet<T> extensions = this.extensionSchema.getExtensions(message);
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
            int iNumberAt = numberAt(length);
            while (entry != null && this.extensionSchema.extensionNumber(entry) > iNumberAt) {
                this.extensionSchema.serializeExtension(writer, entry);
                entry = itDescendingIterator.hasNext() ? (Map.Entry) itDescendingIterator.next() : null;
            }
            switch (type(iTypeAndOffsetAt)) {
                case 0:
                    if (isFieldPresent(message, length)) {
                        writer.writeDouble(iNumberAt, doubleAt(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 1:
                    if (isFieldPresent(message, length)) {
                        writer.writeFloat(iNumberAt, floatAt(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 2:
                    if (isFieldPresent(message, length)) {
                        writer.writeInt64(iNumberAt, longAt(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 3:
                    if (isFieldPresent(message, length)) {
                        writer.writeUInt64(iNumberAt, longAt(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 4:
                    if (isFieldPresent(message, length)) {
                        writer.writeInt32(iNumberAt, intAt(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 5:
                    if (isFieldPresent(message, length)) {
                        writer.writeFixed64(iNumberAt, longAt(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 6:
                    if (isFieldPresent(message, length)) {
                        writer.writeFixed32(iNumberAt, intAt(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 7:
                    if (isFieldPresent(message, length)) {
                        writer.writeBool(iNumberAt, booleanAt(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 8:
                    if (isFieldPresent(message, length)) {
                        writeString(iNumberAt, UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer);
                    }
                    break;
                case 9:
                    if (isFieldPresent(message, length)) {
                        writer.writeMessage(iNumberAt, UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), getMessageFieldSchema(length));
                    }
                    break;
                case 10:
                    if (isFieldPresent(message, length)) {
                        writer.writeBytes(iNumberAt, (ByteString) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 11:
                    if (isFieldPresent(message, length)) {
                        writer.writeUInt32(iNumberAt, intAt(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 12:
                    if (isFieldPresent(message, length)) {
                        writer.writeEnum(iNumberAt, intAt(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 13:
                    if (isFieldPresent(message, length)) {
                        writer.writeSFixed32(iNumberAt, intAt(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 14:
                    if (isFieldPresent(message, length)) {
                        writer.writeSFixed64(iNumberAt, longAt(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 15:
                    if (isFieldPresent(message, length)) {
                        writer.writeSInt32(iNumberAt, intAt(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 16:
                    if (isFieldPresent(message, length)) {
                        writer.writeSInt64(iNumberAt, longAt(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 17:
                    if (isFieldPresent(message, length)) {
                        writer.writeGroup(iNumberAt, UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), getMessageFieldSchema(length));
                    }
                    break;
                case 18:
                    SchemaUtil.writeDoubleList(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 19:
                    SchemaUtil.writeFloatList(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 20:
                    SchemaUtil.writeInt64List(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 21:
                    SchemaUtil.writeUInt64List(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 22:
                    SchemaUtil.writeInt32List(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 23:
                    SchemaUtil.writeFixed64List(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 24:
                    SchemaUtil.writeFixed32List(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 25:
                    SchemaUtil.writeBoolList(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 26:
                    SchemaUtil.writeStringList(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer);
                    break;
                case 27:
                    SchemaUtil.writeMessageList(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, getMessageFieldSchema(length));
                    break;
                case 28:
                    SchemaUtil.writeBytesList(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer);
                    break;
                case 29:
                    SchemaUtil.writeUInt32List(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 30:
                    SchemaUtil.writeEnumList(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 31:
                    SchemaUtil.writeSFixed32List(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 32:
                    SchemaUtil.writeSFixed64List(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 33:
                    SchemaUtil.writeSInt32List(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 34:
                    SchemaUtil.writeSInt64List(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 35:
                    SchemaUtil.writeDoubleList(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 36:
                    SchemaUtil.writeFloatList(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 37:
                    SchemaUtil.writeInt64List(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 38:
                    SchemaUtil.writeUInt64List(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 39:
                    SchemaUtil.writeInt32List(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 40:
                    SchemaUtil.writeFixed64List(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 41:
                    SchemaUtil.writeFixed32List(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 42:
                    SchemaUtil.writeBoolList(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 43:
                    SchemaUtil.writeUInt32List(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 44:
                    SchemaUtil.writeEnumList(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 45:
                    SchemaUtil.writeSFixed32List(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 46:
                    SchemaUtil.writeSFixed64List(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 47:
                    SchemaUtil.writeSInt32List(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 48:
                    SchemaUtil.writeSInt64List(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 49:
                    SchemaUtil.writeGroupList(iNumberAt, (List) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer, getMessageFieldSchema(length));
                    break;
                case 50:
                    writeMapHelper(writer, iNumberAt, UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), length);
                    break;
                case 51:
                    if (isOneofPresent(message, iNumberAt, length)) {
                        writer.writeDouble(iNumberAt, oneofDoubleAt(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 52:
                    if (isOneofPresent(message, iNumberAt, length)) {
                        writer.writeFloat(iNumberAt, oneofFloatAt(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 53:
                    if (isOneofPresent(message, iNumberAt, length)) {
                        writer.writeInt64(iNumberAt, oneofLongAt(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 54:
                    if (isOneofPresent(message, iNumberAt, length)) {
                        writer.writeUInt64(iNumberAt, oneofLongAt(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 55:
                    if (isOneofPresent(message, iNumberAt, length)) {
                        writer.writeInt32(iNumberAt, oneofIntAt(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 56:
                    if (isOneofPresent(message, iNumberAt, length)) {
                        writer.writeFixed64(iNumberAt, oneofLongAt(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 57:
                    if (isOneofPresent(message, iNumberAt, length)) {
                        writer.writeFixed32(iNumberAt, oneofIntAt(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 58:
                    if (isOneofPresent(message, iNumberAt, length)) {
                        writer.writeBool(iNumberAt, oneofBooleanAt(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 59:
                    if (isOneofPresent(message, iNumberAt, length)) {
                        writeString(iNumberAt, UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), writer);
                    }
                    break;
                case 60:
                    if (isOneofPresent(message, iNumberAt, length)) {
                        writer.writeMessage(iNumberAt, UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), getMessageFieldSchema(length));
                    }
                    break;
                case 61:
                    if (isOneofPresent(message, iNumberAt, length)) {
                        writer.writeBytes(iNumberAt, (ByteString) UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 62:
                    if (isOneofPresent(message, iNumberAt, length)) {
                        writer.writeUInt32(iNumberAt, oneofIntAt(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 63:
                    if (isOneofPresent(message, iNumberAt, length)) {
                        writer.writeEnum(iNumberAt, oneofIntAt(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 64:
                    if (isOneofPresent(message, iNumberAt, length)) {
                        writer.writeSFixed32(iNumberAt, oneofIntAt(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 65:
                    if (isOneofPresent(message, iNumberAt, length)) {
                        writer.writeSFixed64(iNumberAt, oneofLongAt(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 66:
                    if (isOneofPresent(message, iNumberAt, length)) {
                        writer.writeSInt32(iNumberAt, oneofIntAt(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 67:
                    if (isOneofPresent(message, iNumberAt, length)) {
                        writer.writeSInt64(iNumberAt, oneofLongAt(message, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 68:
                    if (isOneofPresent(message, iNumberAt, length)) {
                        writer.writeGroup(iNumberAt, UnsafeUtil.getObject(message, offset(iTypeAndOffsetAt)), getMessageFieldSchema(length));
                    }
                    break;
            }
        }
        while (entry != null) {
            this.extensionSchema.serializeExtension(writer, entry);
            entry = itDescendingIterator.hasNext() ? (Map.Entry) itDescendingIterator.next() : null;
        }
    }

    private <K, V> void writeMapHelper(Writer writer, int number, Object mapField, int pos) throws IOException {
        if (mapField != null) {
            writer.writeMap(number, this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(pos)), this.mapFieldSchema.forMapData(mapField));
        }
    }

    private <UT, UB> void writeUnknownInMessageTo(UnknownFieldSchema<UT, UB> schema, T message, Writer writer) throws IOException {
        schema.writeTo(schema.getFromMessage(message), writer);
    }

    @Override // com.google.protobuf.Schema
    public void mergeFrom(T message, Reader reader, ExtensionRegistryLite extensionRegistry) throws IOException {
        Internal.checkNotNull(extensionRegistry);
        checkMutable(message);
        mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, message, reader, extensionRegistry);
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 19721. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
        */
    private <UT, UB, ET extends com.google.protobuf.FieldSet.FieldDescriptorLite<ET>> void mergeFromHelper(com.google.protobuf.UnknownFieldSchema<UT, UB> r18, com.google.protobuf.ExtensionSchema<ET> r19, T r20, com.google.protobuf.Reader r21, com.google.protobuf.ExtensionRegistryLite r22) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1972
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.mergeFromHelper(com.google.protobuf.UnknownFieldSchema, com.google.protobuf.ExtensionSchema, java.lang.Object, com.google.protobuf.Reader, com.google.protobuf.ExtensionRegistryLite):void");
    }

    static UnknownFieldSetLite getMutableUnknownFields(Object message) {
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) message;
        UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
        if (unknownFieldSetLite != UnknownFieldSetLite.getDefaultInstance()) {
            return unknownFieldSetLite;
        }
        UnknownFieldSetLite unknownFieldSetLiteNewInstance = UnknownFieldSetLite.newInstance();
        generatedMessageLite.unknownFields = unknownFieldSetLiteNewInstance;
        return unknownFieldSetLiteNewInstance;
    }

    /* JADX INFO: renamed from: com.google.protobuf.MessageSchema$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            $SwitchMap$com$google$protobuf$WireFormat$FieldType = iArr;
            try {
                iArr[WireFormat.FieldType.BOOL.ordinal()] = 1;
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

    private int decodeMapEntryValue(byte[] data, int position, int limit, WireFormat.FieldType fieldType, Class<?> messageType, ArrayDecoders.Registers registers) throws IOException {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[fieldType.ordinal()]) {
            case 1:
                int iDecodeVarint64 = ArrayDecoders.decodeVarint64(data, position, registers);
                registers.object1 = Boolean.valueOf(registers.long1 != 0);
                return iDecodeVarint64;
            case 2:
                return ArrayDecoders.decodeBytes(data, position, registers);
            case 3:
                registers.object1 = Double.valueOf(ArrayDecoders.decodeDouble(data, position));
                return position + 8;
            case 4:
            case 5:
                registers.object1 = Integer.valueOf(ArrayDecoders.decodeFixed32(data, position));
                return position + 4;
            case 6:
            case 7:
                registers.object1 = Long.valueOf(ArrayDecoders.decodeFixed64(data, position));
                return position + 8;
            case 8:
                registers.object1 = Float.valueOf(ArrayDecoders.decodeFloat(data, position));
                return position + 4;
            case 9:
            case 10:
            case 11:
                int iDecodeVarint32 = ArrayDecoders.decodeVarint32(data, position, registers);
                registers.object1 = Integer.valueOf(registers.int1);
                return iDecodeVarint32;
            case 12:
            case 13:
                int iDecodeVarint65 = ArrayDecoders.decodeVarint64(data, position, registers);
                registers.object1 = Long.valueOf(registers.long1);
                return iDecodeVarint65;
            case 14:
                return ArrayDecoders.decodeMessageField(Protobuf.getInstance().schemaFor((Class) messageType), data, position, limit, registers);
            case 15:
                int iDecodeVarint33 = ArrayDecoders.decodeVarint32(data, position, registers);
                registers.object1 = Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1));
                return iDecodeVarint33;
            case 16:
                int iDecodeVarint66 = ArrayDecoders.decodeVarint64(data, position, registers);
                registers.object1 = Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1));
                return iDecodeVarint66;
            case 17:
                return ArrayDecoders.decodeStringRequireUtf8(data, position, registers);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <K, V> int decodeMapEntry(byte[] data, int position, int limit, MapEntryLite.Metadata<K, V> metadata, Map<K, V> target, ArrayDecoders.Registers registers) throws IOException {
        int iDecodeVarint32 = ArrayDecoders.decodeVarint32(data, position, registers);
        int i = registers.int1;
        if (i < 0 || i > limit - iDecodeVarint32) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        int i2 = iDecodeVarint32 + i;
        Object obj = metadata.defaultKey;
        Object obj2 = metadata.defaultValue;
        while (iDecodeVarint32 < i2) {
            int iDecodeVarint33 = iDecodeVarint32 + 1;
            int i3 = data[iDecodeVarint32];
            if (i3 < 0) {
                iDecodeVarint33 = ArrayDecoders.decodeVarint32(i3, data, iDecodeVarint33, registers);
                i3 = registers.int1;
            }
            int i4 = iDecodeVarint33;
            int i5 = i3 >>> 3;
            int i6 = i3 & 7;
            if (i5 == 1) {
                if (i6 == metadata.keyType.getWireType()) {
                    iDecodeVarint32 = decodeMapEntryValue(data, i4, limit, metadata.keyType, null, registers);
                    obj = registers.object1;
                } else {
                    iDecodeVarint32 = ArrayDecoders.skipField(i3, data, i4, limit, registers);
                }
            } else if (i5 == 2 && i6 == metadata.valueType.getWireType()) {
                iDecodeVarint32 = decodeMapEntryValue(data, i4, limit, metadata.valueType, metadata.defaultValue.getClass(), registers);
                obj2 = registers.object1;
            } else {
                iDecodeVarint32 = ArrayDecoders.skipField(i3, data, i4, limit, registers);
            }
        }
        if (iDecodeVarint32 != i2) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        target.put(obj, obj2);
        return i2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private int parseRepeatedField(T message, byte[] data, int position, int limit, int tag, int number, int wireType, int bufferPosition, long typeAndOffset, int fieldType, long fieldOffset, ArrayDecoders.Registers registers) throws IOException {
        int iDecodeVarint32List;
        Unsafe unsafe = UNSAFE;
        Internal.ProtobufList protobufListMutableCopyWithCapacity2 = (Internal.ProtobufList) unsafe.getObject(message, fieldOffset);
        if (!protobufListMutableCopyWithCapacity2.isModifiable()) {
            protobufListMutableCopyWithCapacity2 = protobufListMutableCopyWithCapacity2.mutableCopyWithCapacity2(protobufListMutableCopyWithCapacity2.size() * 2);
            unsafe.putObject(message, fieldOffset, protobufListMutableCopyWithCapacity2);
        }
        Internal.ProtobufList protobufList = protobufListMutableCopyWithCapacity2;
        switch (fieldType) {
            case 18:
            case 35:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedDoubleList(data, position, protobufList, registers);
                }
                if (wireType == 1) {
                    return ArrayDecoders.decodeDoubleList(tag, data, position, limit, protobufList, registers);
                }
                return position;
            case 19:
            case 36:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedFloatList(data, position, protobufList, registers);
                }
                if (wireType == 5) {
                    return ArrayDecoders.decodeFloatList(tag, data, position, limit, protobufList, registers);
                }
                return position;
            case 20:
            case 21:
            case 37:
            case 38:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedVarint64List(data, position, protobufList, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeVarint64List(tag, data, position, limit, protobufList, registers);
                }
                return position;
            case 22:
            case 29:
            case 39:
            case 43:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedVarint32List(data, position, protobufList, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeVarint32List(tag, data, position, limit, protobufList, registers);
                }
                return position;
            case 23:
            case 32:
            case 40:
            case 46:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedFixed64List(data, position, protobufList, registers);
                }
                if (wireType == 1) {
                    return ArrayDecoders.decodeFixed64List(tag, data, position, limit, protobufList, registers);
                }
                return position;
            case 24:
            case 31:
            case 41:
            case 45:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedFixed32List(data, position, protobufList, registers);
                }
                if (wireType == 5) {
                    return ArrayDecoders.decodeFixed32List(tag, data, position, limit, protobufList, registers);
                }
                return position;
            case 25:
            case 42:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedBoolList(data, position, protobufList, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeBoolList(tag, data, position, limit, protobufList, registers);
                }
                return position;
            case 26:
                if (wireType == 2) {
                    if ((typeAndOffset & 536870912) == 0) {
                        return ArrayDecoders.decodeStringList(tag, data, position, limit, protobufList, registers);
                    }
                    return ArrayDecoders.decodeStringListRequireUtf8(tag, data, position, limit, protobufList, registers);
                }
                return position;
            case 27:
                if (wireType == 2) {
                    return ArrayDecoders.decodeMessageList(getMessageFieldSchema(bufferPosition), tag, data, position, limit, protobufList, registers);
                }
                return position;
            case 28:
                if (wireType == 2) {
                    return ArrayDecoders.decodeBytesList(tag, data, position, limit, protobufList, registers);
                }
                return position;
            case 30:
            case 44:
                if (wireType != 2) {
                    if (wireType == 0) {
                        iDecodeVarint32List = ArrayDecoders.decodeVarint32List(tag, data, position, limit, protobufList, registers);
                    }
                    return position;
                }
                iDecodeVarint32List = ArrayDecoders.decodePackedVarint32List(data, position, protobufList, registers);
                SchemaUtil.filterUnknownEnumList((Object) message, number, (List<Integer>) protobufList, getEnumFieldVerifier(bufferPosition), (Object) null, (UnknownFieldSchema<UT, Object>) this.unknownFieldSchema);
                return iDecodeVarint32List;
            case 33:
            case 47:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedSInt32List(data, position, protobufList, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeSInt32List(tag, data, position, limit, protobufList, registers);
                }
                return position;
            case 34:
            case 48:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedSInt64List(data, position, protobufList, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeSInt64List(tag, data, position, limit, protobufList, registers);
                }
                return position;
            case 49:
                if (wireType == 3) {
                    return ArrayDecoders.decodeGroupList(getMessageFieldSchema(bufferPosition), tag, data, position, limit, protobufList, registers);
                }
                return position;
            default:
                return position;
        }
    }

    private <K, V> int parseMapField(T message, byte[] data, int position, int limit, int bufferPosition, long fieldOffset, ArrayDecoders.Registers registers) throws IOException {
        Unsafe unsafe = UNSAFE;
        Object mapFieldDefaultEntry = getMapFieldDefaultEntry(bufferPosition);
        Object object = unsafe.getObject(message, fieldOffset);
        if (this.mapFieldSchema.isImmutable(object)) {
            Object objNewMapField = this.mapFieldSchema.newMapField(mapFieldDefaultEntry);
            this.mapFieldSchema.mergeFrom(objNewMapField, object);
            unsafe.putObject(message, fieldOffset, objNewMapField);
            object = objNewMapField;
        }
        return decodeMapEntry(data, position, limit, this.mapFieldSchema.forMapMetadata(mapFieldDefaultEntry), this.mapFieldSchema.forMutableMapData(object), registers);
    }

    private int parseOneofField(T message, byte[] data, int position, int limit, int tag, int number, int wireType, int typeAndOffset, int fieldType, long fieldOffset, int bufferPosition, ArrayDecoders.Registers registers) throws IOException {
        Unsafe unsafe = UNSAFE;
        long j = this.buffer[bufferPosition + 2] & 1048575;
        switch (fieldType) {
            case 51:
                if (wireType != 1) {
                    return position;
                }
                unsafe.putObject(message, fieldOffset, Double.valueOf(ArrayDecoders.decodeDouble(data, position)));
                int i = position + 8;
                unsafe.putInt(message, j, number);
                return i;
            case 52:
                if (wireType != 5) {
                    return position;
                }
                unsafe.putObject(message, fieldOffset, Float.valueOf(ArrayDecoders.decodeFloat(data, position)));
                int i2 = position + 4;
                unsafe.putInt(message, j, number);
                return i2;
            case 53:
            case 54:
                if (wireType != 0) {
                    return position;
                }
                int iDecodeVarint64 = ArrayDecoders.decodeVarint64(data, position, registers);
                unsafe.putObject(message, fieldOffset, Long.valueOf(registers.long1));
                unsafe.putInt(message, j, number);
                return iDecodeVarint64;
            case 55:
            case 62:
                if (wireType != 0) {
                    return position;
                }
                int iDecodeVarint32 = ArrayDecoders.decodeVarint32(data, position, registers);
                unsafe.putObject(message, fieldOffset, Integer.valueOf(registers.int1));
                unsafe.putInt(message, j, number);
                return iDecodeVarint32;
            case 56:
            case 65:
                if (wireType != 1) {
                    return position;
                }
                unsafe.putObject(message, fieldOffset, Long.valueOf(ArrayDecoders.decodeFixed64(data, position)));
                int i3 = position + 8;
                unsafe.putInt(message, j, number);
                return i3;
            case 57:
            case 64:
                if (wireType != 5) {
                    return position;
                }
                unsafe.putObject(message, fieldOffset, Integer.valueOf(ArrayDecoders.decodeFixed32(data, position)));
                int i4 = position + 4;
                unsafe.putInt(message, j, number);
                return i4;
            case 58:
                if (wireType != 0) {
                    return position;
                }
                int iDecodeVarint65 = ArrayDecoders.decodeVarint64(data, position, registers);
                unsafe.putObject(message, fieldOffset, Boolean.valueOf(registers.long1 != 0));
                unsafe.putInt(message, j, number);
                return iDecodeVarint65;
            case 59:
                if (wireType != 2) {
                    return position;
                }
                int iDecodeVarint33 = ArrayDecoders.decodeVarint32(data, position, registers);
                int i5 = registers.int1;
                if (i5 == 0) {
                    unsafe.putObject(message, fieldOffset, "");
                } else {
                    if ((typeAndOffset & 536870912) != 0 && !Utf8.isValidUtf8(data, iDecodeVarint33, iDecodeVarint33 + i5)) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    unsafe.putObject(message, fieldOffset, new String(data, iDecodeVarint33, i5, StandardCharsets.UTF_8));
                    iDecodeVarint33 += i5;
                }
                unsafe.putInt(message, j, number);
                return iDecodeVarint33;
            case 60:
                if (wireType != 2) {
                    return position;
                }
                Object objMutableOneofMessageFieldForMerge = mutableOneofMessageFieldForMerge(message, number, bufferPosition);
                int iMergeMessageField = ArrayDecoders.mergeMessageField(objMutableOneofMessageFieldForMerge, getMessageFieldSchema(bufferPosition), data, position, limit, registers);
                storeOneofMessageField(message, number, bufferPosition, objMutableOneofMessageFieldForMerge);
                return iMergeMessageField;
            case 61:
                if (wireType != 2) {
                    return position;
                }
                int iDecodeBytes = ArrayDecoders.decodeBytes(data, position, registers);
                unsafe.putObject(message, fieldOffset, registers.object1);
                unsafe.putInt(message, j, number);
                return iDecodeBytes;
            case 63:
                if (wireType != 0) {
                    return position;
                }
                int iDecodeVarint34 = ArrayDecoders.decodeVarint32(data, position, registers);
                int i6 = registers.int1;
                Internal.EnumVerifier enumFieldVerifier = getEnumFieldVerifier(bufferPosition);
                if (enumFieldVerifier == null || enumFieldVerifier.isInRange(i6)) {
                    unsafe.putObject(message, fieldOffset, Integer.valueOf(i6));
                    unsafe.putInt(message, j, number);
                    return iDecodeVarint34;
                }
                getMutableUnknownFields(message).storeField(tag, Long.valueOf(i6));
                return iDecodeVarint34;
            case 66:
                if (wireType != 0) {
                    return position;
                }
                int iDecodeVarint35 = ArrayDecoders.decodeVarint32(data, position, registers);
                unsafe.putObject(message, fieldOffset, Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1)));
                unsafe.putInt(message, j, number);
                return iDecodeVarint35;
            case 67:
                if (wireType != 0) {
                    return position;
                }
                int iDecodeVarint66 = ArrayDecoders.decodeVarint64(data, position, registers);
                unsafe.putObject(message, fieldOffset, Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1)));
                unsafe.putInt(message, j, number);
                return iDecodeVarint66;
            case 68:
                if (wireType == 3) {
                    Object objMutableOneofMessageFieldForMerge2 = mutableOneofMessageFieldForMerge(message, number, bufferPosition);
                    int iMergeGroupField = ArrayDecoders.mergeGroupField(objMutableOneofMessageFieldForMerge2, getMessageFieldSchema(bufferPosition), data, position, limit, (tag & (-8)) | 4, registers);
                    storeOneofMessageField(message, number, bufferPosition, objMutableOneofMessageFieldForMerge2);
                    return iMergeGroupField;
                }
                break;
        }
        return position;
    }

    private Schema getMessageFieldSchema(int pos) {
        int i = (pos / 3) * 2;
        Schema schema = (Schema) this.objects[i];
        if (schema != null) {
            return schema;
        }
        Schema<T> schemaSchemaFor = Protobuf.getInstance().schemaFor((Class) this.objects[i + 1]);
        this.objects[i] = schemaSchemaFor;
        return schemaSchemaFor;
    }

    private Object getMapFieldDefaultEntry(int pos) {
        return this.objects[(pos / 3) * 2];
    }

    private Internal.EnumVerifier getEnumFieldVerifier(int pos) {
        return (Internal.EnumVerifier) this.objects[((pos / 3) * 2) + 1];
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 12381. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
        */
    int parseMessage(T r27, byte[] r28, int r29, int r30, int r31, com.google.protobuf.ArrayDecoders.Registers r32) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1238
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.parseMessage(java.lang.Object, byte[], int, int, int, com.google.protobuf.ArrayDecoders$Registers):int");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Object mutableMessageFieldForMerge(T message, int pos) {
        Schema messageFieldSchema = getMessageFieldSchema(pos);
        long jOffset = offset(typeAndOffsetAt(pos));
        if (!isFieldPresent(message, pos)) {
            return messageFieldSchema.newInstance();
        }
        Object object = UNSAFE.getObject(message, jOffset);
        if (isMutable(object)) {
            return object;
        }
        Object objNewInstance = messageFieldSchema.newInstance();
        if (object != null) {
            messageFieldSchema.mergeFrom(objNewInstance, object);
        }
        return objNewInstance;
    }

    private void storeMessageField(T message, int pos, Object field) {
        UNSAFE.putObject(message, offset(typeAndOffsetAt(pos)), field);
        setFieldPresent(message, pos);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Object mutableOneofMessageFieldForMerge(T message, int fieldNumber, int pos) {
        Schema messageFieldSchema = getMessageFieldSchema(pos);
        if (!isOneofPresent(message, fieldNumber, pos)) {
            return messageFieldSchema.newInstance();
        }
        Object object = UNSAFE.getObject(message, offset(typeAndOffsetAt(pos)));
        if (isMutable(object)) {
            return object;
        }
        Object objNewInstance = messageFieldSchema.newInstance();
        if (object != null) {
            messageFieldSchema.mergeFrom(objNewInstance, object);
        }
        return objNewInstance;
    }

    private void storeOneofMessageField(T message, int fieldNumber, int pos, Object field) {
        UNSAFE.putObject(message, offset(typeAndOffsetAt(pos)), field);
        setOneofPresent(message, fieldNumber, pos);
    }

    @Override // com.google.protobuf.Schema
    public void mergeFrom(T message, byte[] data, int position, int limit, ArrayDecoders.Registers registers) throws IOException {
        parseMessage(message, data, position, limit, 0, registers);
    }

    /* JADX WARN: Code duplicated, block: B:25:0x006a  */
    /* JADX WARN: Code duplicated, block: B:27:0x0070  */
    /* JADX WARN: Code duplicated, block: B:40:0x007d A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.protobuf.Schema
    public void makeImmutable(T message) {
        if (isMutable(message)) {
            if (message instanceof GeneratedMessageLite) {
                GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) message;
                generatedMessageLite.clearMemoizedSerializedSize();
                generatedMessageLite.clearMemoizedHashCode();
                generatedMessageLite.markImmutable();
            }
            int length = this.buffer.length;
            for (int i = 0; i < length; i += 3) {
                int iTypeAndOffsetAt = typeAndOffsetAt(i);
                long jOffset = offset(iTypeAndOffsetAt);
                int iType = type(iTypeAndOffsetAt);
                if (iType != 9) {
                    if (iType != 60 && iType != 68) {
                        switch (iType) {
                            case 17:
                                if (isFieldPresent(message, i)) {
                                    getMessageFieldSchema(i).makeImmutable(UNSAFE.getObject(message, jOffset));
                                }
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
                                this.listFieldSchema.makeImmutableListAt(message, jOffset);
                                break;
                            case 50:
                                Unsafe unsafe = UNSAFE;
                                Object object = unsafe.getObject(message, jOffset);
                                if (object != null) {
                                    unsafe.putObject(message, jOffset, this.mapFieldSchema.toImmutable(object));
                                }
                                break;
                        }
                    } else if (isOneofPresent(message, numberAt(i), i)) {
                        getMessageFieldSchema(i).makeImmutable(UNSAFE.getObject(message, jOffset));
                    }
                } else if (isFieldPresent(message, i)) {
                    getMessageFieldSchema(i).makeImmutable(UNSAFE.getObject(message, jOffset));
                }
            }
            this.unknownFieldSchema.makeImmutable(message);
            if (this.hasExtensions) {
                this.extensionSchema.makeImmutable(message);
            }
        }
    }

    private final <K, V> void mergeMap(Object message, int pos, Object mapDefaultEntry, ExtensionRegistryLite extensionRegistry, Reader reader) throws IOException {
        long jOffset = offset(typeAndOffsetAt(pos));
        Object object = UnsafeUtil.getObject(message, jOffset);
        MapFieldSchema mapFieldSchema = this.mapFieldSchema;
        if (object == null) {
            object = mapFieldSchema.newMapField(mapDefaultEntry);
            UnsafeUtil.putObject(message, jOffset, object);
        } else if (mapFieldSchema.isImmutable(object)) {
            Object objNewMapField = this.mapFieldSchema.newMapField(mapDefaultEntry);
            this.mapFieldSchema.mergeFrom(objNewMapField, object);
            UnsafeUtil.putObject(message, jOffset, objNewMapField);
            object = objNewMapField;
        }
        reader.readMap(this.mapFieldSchema.forMutableMapData(object), this.mapFieldSchema.forMapMetadata(mapDefaultEntry), extensionRegistry);
    }

    private <UT, UB> UB filterMapUnknownEnumValues(Object obj, int i, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema, Object obj2) {
        Internal.EnumVerifier enumFieldVerifier;
        int iNumberAt = numberAt(i);
        Object object = UnsafeUtil.getObject(obj, offset(typeAndOffsetAt(i)));
        return (object == null || (enumFieldVerifier = getEnumFieldVerifier(i)) == null) ? ub : (UB) filterUnknownEnumMap(i, iNumberAt, this.mapFieldSchema.forMutableMapData(object), enumFieldVerifier, ub, unknownFieldSchema, obj2);
    }

    private <K, V, UT, UB> UB filterUnknownEnumMap(int i, int i2, Map<K, V> map, Internal.EnumVerifier enumVerifier, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema, Object obj) {
        MapEntryLite.Metadata<?, ?> metadataForMapMetadata = this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!enumVerifier.isInRange(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = unknownFieldSchema.getBuilderFromMessage(obj);
                }
                ByteString.CodedBuilder codedBuilderNewCodedBuilder = ByteString.newCodedBuilder(MapEntryLite.computeSerializedSize(metadataForMapMetadata, next.getKey(), next.getValue()));
                try {
                    MapEntryLite.writeTo(codedBuilderNewCodedBuilder.getCodedOutput(), metadataForMapMetadata, next.getKey(), next.getValue());
                    unknownFieldSchema.addLengthDelimited(ub, i2, codedBuilderNewCodedBuilder.build());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    /* JADX WARN: Code duplicated, block: B:39:0x007c  */
    /* JADX WARN: Code duplicated, block: B:58:0x0082 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:63:0x0094 A[SYNTHETIC] */
    @Override // com.google.protobuf.Schema
    public final boolean isInitialized(T message) {
        int i;
        int i2;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i4 < this.checkInitializedCount) {
            int i6 = this.intArray[i4];
            int iTypeAndOffsetAt = this.typeAndOffsetAt(i6);
            int i7 = this.buffer[i6 + 2];
            int i8 = i7 & 1048575;
            int i9 = 1 << (i7 >>> 20);
            if (i8 != i3) {
                if (i8 != 1048575) {
                    i5 = UNSAFE.getInt(message, i8);
                }
                i2 = i5;
                i = i8;
            } else {
                i = i3;
                i2 = i5;
            }
            MessageSchema<T> messageSchema = this;
            T t = message;
            if (isRequired(iTypeAndOffsetAt) && !messageSchema.isFieldPresent(t, i6, i, i2, i9)) {
                return false;
            }
            int iType = type(iTypeAndOffsetAt);
            if (iType == 9 || iType == 17) {
                if (messageSchema.isFieldPresent(t, i6, i, i2, i9) && !isInitialized(t, iTypeAndOffsetAt, messageSchema.getMessageFieldSchema(i6))) {
                    return false;
                }
            } else if (iType == 27) {
                if (!messageSchema.isListInitialized(t, iTypeAndOffsetAt, i6)) {
                    return false;
                }
            } else if (iType == 60 || iType == 68) {
                if (messageSchema.isOneofPresent(t, messageSchema.numberAt(i6), i6) && !isInitialized(t, iTypeAndOffsetAt, messageSchema.getMessageFieldSchema(i6))) {
                    return false;
                }
            } else if (iType == 49) {
                if (!messageSchema.isListInitialized(t, iTypeAndOffsetAt, i6)) {
                    return false;
                }
            } else if (iType == 50 && !messageSchema.isMapInitialized(t, iTypeAndOffsetAt, i6)) {
                return false;
            }
            i4++;
            this = messageSchema;
            message = t;
            i3 = i;
            i5 = i2;
        }
        MessageSchema<T> messageSchema2 = this;
        return !messageSchema2.hasExtensions || messageSchema2.extensionSchema.getExtensions(message).isInitialized();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean isInitialized(Object message, int typeAndOffset, Schema schema) {
        return schema.isInitialized(UnsafeUtil.getObject(message, offset(typeAndOffset)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <N> boolean isListInitialized(Object message, int typeAndOffset, int pos) {
        List list = (List) UnsafeUtil.getObject(message, offset(typeAndOffset));
        if (list.isEmpty()) {
            return true;
        }
        Schema messageFieldSchema = getMessageFieldSchema(pos);
        for (int i = 0; i < list.size(); i++) {
            if (!messageFieldSchema.isInitialized(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5, types: [com.google.protobuf.Schema] */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    private boolean isMapInitialized(T t, int i, int i2) {
        Map<?, ?> mapForMapData = this.mapFieldSchema.forMapData(UnsafeUtil.getObject(t, offset(i)));
        if (mapForMapData.isEmpty()) {
            return true;
        }
        if (this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i2)).valueType.getJavaType() != WireFormat.JavaType.MESSAGE) {
            return true;
        }
        ?? SchemaFor = 0;
        for (Object obj : mapForMapData.values()) {
            if (SchemaFor == 0) {
                SchemaFor = SchemaFor;
                SchemaFor = Protobuf.getInstance().schemaFor((Class) obj.getClass());
            }
            SchemaFor = SchemaFor;
            if (!SchemaFor.isInitialized(obj)) {
                return false;
            }
        }
        return true;
    }

    private void writeString(int fieldNumber, Object value, Writer writer) throws IOException {
        if (value instanceof String) {
            writer.writeString(fieldNumber, (String) value);
        } else {
            writer.writeBytes(fieldNumber, (ByteString) value);
        }
    }

    private void readString(Object message, int typeAndOffset, Reader reader) throws IOException {
        if (isEnforceUtf8(typeAndOffset)) {
            UnsafeUtil.putObject(message, offset(typeAndOffset), reader.readStringRequireUtf8());
        } else if (this.lite) {
            UnsafeUtil.putObject(message, offset(typeAndOffset), reader.readString());
        } else {
            UnsafeUtil.putObject(message, offset(typeAndOffset), reader.readBytes());
        }
    }

    private void readStringList(Object message, int typeAndOffset, Reader reader) throws IOException {
        boolean zIsEnforceUtf8 = isEnforceUtf8(typeAndOffset);
        ListFieldSchema listFieldSchema = this.listFieldSchema;
        if (zIsEnforceUtf8) {
            reader.readStringListRequireUtf8(listFieldSchema.mutableListAt(message, offset(typeAndOffset)));
        } else {
            reader.readStringList(listFieldSchema.mutableListAt(message, offset(typeAndOffset)));
        }
    }

    private <E> void readMessageList(Object message, int typeAndOffset, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        reader.readMessageList(this.listFieldSchema.mutableListAt(message, offset(typeAndOffset)), schema, extensionRegistry);
    }

    private <E> void readGroupList(Object message, long offset, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        reader.readGroupList(this.listFieldSchema.mutableListAt(message, offset), schema, extensionRegistry);
    }

    private int numberAt(int pos) {
        return this.buffer[pos];
    }

    private int typeAndOffsetAt(int pos) {
        return this.buffer[pos + 1];
    }

    private int presenceMaskAndOffsetAt(int pos) {
        return this.buffer[pos + 2];
    }

    private static boolean isMutable(Object message) {
        if (message == null) {
            return false;
        }
        if (message instanceof GeneratedMessageLite) {
            return ((GeneratedMessageLite) message).isMutable();
        }
        return true;
    }

    private static void checkMutable(Object message) {
        if (isMutable(message)) {
            return;
        }
        throw new IllegalArgumentException("Mutating immutable message: " + message);
    }

    private static <T> double doubleAt(T message, long offset) {
        return UnsafeUtil.getDouble(message, offset);
    }

    private static <T> float floatAt(T message, long offset) {
        return UnsafeUtil.getFloat(message, offset);
    }

    private static <T> int intAt(T message, long offset) {
        return UnsafeUtil.getInt(message, offset);
    }

    private static <T> long longAt(T message, long offset) {
        return UnsafeUtil.getLong(message, offset);
    }

    private static <T> boolean booleanAt(T message, long offset) {
        return UnsafeUtil.getBoolean(message, offset);
    }

    private static <T> double oneofDoubleAt(T message, long offset) {
        return ((Double) UnsafeUtil.getObject(message, offset)).doubleValue();
    }

    private static <T> float oneofFloatAt(T message, long offset) {
        return ((Float) UnsafeUtil.getObject(message, offset)).floatValue();
    }

    private static <T> int oneofIntAt(T message, long offset) {
        return ((Integer) UnsafeUtil.getObject(message, offset)).intValue();
    }

    private static <T> long oneofLongAt(T message, long offset) {
        return ((Long) UnsafeUtil.getObject(message, offset)).longValue();
    }

    private static <T> boolean oneofBooleanAt(T message, long offset) {
        return ((Boolean) UnsafeUtil.getObject(message, offset)).booleanValue();
    }

    private boolean arePresentForEquals(T message, T other, int pos) {
        return isFieldPresent(message, pos) == isFieldPresent(other, pos);
    }

    private boolean isFieldPresent(T message, int pos, int presenceFieldOffset, int presenceField, int presenceMask) {
        if (presenceFieldOffset == 1048575) {
            return isFieldPresent(message, pos);
        }
        return (presenceField & presenceMask) != 0;
    }

    private boolean isFieldPresent(T message, int pos) {
        boolean zEquals;
        int iPresenceMaskAndOffsetAt = presenceMaskAndOffsetAt(pos);
        long j = 1048575 & iPresenceMaskAndOffsetAt;
        if (j != 1048575) {
            return ((1 << (iPresenceMaskAndOffsetAt >>> 20)) & UnsafeUtil.getInt(message, j)) != 0;
        }
        int iTypeAndOffsetAt = typeAndOffsetAt(pos);
        long jOffset = offset(iTypeAndOffsetAt);
        switch (type(iTypeAndOffsetAt)) {
            case 0:
                return Double.doubleToRawLongBits(UnsafeUtil.getDouble(message, jOffset)) != 0;
            case 1:
                return Float.floatToRawIntBits(UnsafeUtil.getFloat(message, jOffset)) != 0;
            case 2:
                return UnsafeUtil.getLong(message, jOffset) != 0;
            case 3:
                return UnsafeUtil.getLong(message, jOffset) != 0;
            case 4:
                return UnsafeUtil.getInt(message, jOffset) != 0;
            case 5:
                return UnsafeUtil.getLong(message, jOffset) != 0;
            case 6:
                return UnsafeUtil.getInt(message, jOffset) != 0;
            case 7:
                return UnsafeUtil.getBoolean(message, jOffset);
            case 8:
                Object object = UnsafeUtil.getObject(message, jOffset);
                if (object instanceof String) {
                    zEquals = ((String) object).isEmpty();
                } else if (object instanceof ByteString) {
                    zEquals = ByteString.EMPTY.equals(object);
                } else {
                    return throwIllegalArgumentException();
                }
                break;
            case 9:
                return UnsafeUtil.getObject(message, jOffset) != null;
            case 10:
                zEquals = ByteString.EMPTY.equals(UnsafeUtil.getObject(message, jOffset));
                break;
            case 11:
                return UnsafeUtil.getInt(message, jOffset) != 0;
            case 12:
                return UnsafeUtil.getInt(message, jOffset) != 0;
            case 13:
                return UnsafeUtil.getInt(message, jOffset) != 0;
            case 14:
                return UnsafeUtil.getLong(message, jOffset) != 0;
            case 15:
                return UnsafeUtil.getInt(message, jOffset) != 0;
            case 16:
                return UnsafeUtil.getLong(message, jOffset) != 0;
            case 17:
                return UnsafeUtil.getObject(message, jOffset) != null;
            default:
                return throwIllegalArgumentException();
        }
        return !zEquals;
    }

    private void setFieldPresent(T message, int pos) {
        int iPresenceMaskAndOffsetAt = presenceMaskAndOffsetAt(pos);
        long j = 1048575 & iPresenceMaskAndOffsetAt;
        if (j == 1048575) {
            return;
        }
        UnsafeUtil.putInt(message, j, (1 << (iPresenceMaskAndOffsetAt >>> 20)) | UnsafeUtil.getInt(message, j));
    }

    private boolean isOneofPresent(T message, int fieldNumber, int pos) {
        return UnsafeUtil.getInt(message, (long) (presenceMaskAndOffsetAt(pos) & 1048575)) == fieldNumber;
    }

    private boolean isOneofCaseEqual(T message, T other, int pos) {
        long jPresenceMaskAndOffsetAt = presenceMaskAndOffsetAt(pos) & 1048575;
        return UnsafeUtil.getInt(message, jPresenceMaskAndOffsetAt) == UnsafeUtil.getInt(other, jPresenceMaskAndOffsetAt);
    }

    private void setOneofPresent(T message, int fieldNumber, int pos) {
        UnsafeUtil.putInt(message, presenceMaskAndOffsetAt(pos) & 1048575, fieldNumber);
    }

    private int positionForFieldNumber(final int number) {
        if (number < this.minFieldNumber || number > this.maxFieldNumber) {
            return -1;
        }
        return slowPositionForFieldNumber(number, 0);
    }

    private int positionForFieldNumber(final int number, final int min) {
        if (number < this.minFieldNumber || number > this.maxFieldNumber) {
            return -1;
        }
        return slowPositionForFieldNumber(number, min);
    }

    private int slowPositionForFieldNumber(final int number, int min) {
        int length = (this.buffer.length / 3) - 1;
        while (min <= length) {
            int i = (length + min) >>> 1;
            int i2 = i * 3;
            int iNumberAt = numberAt(i2);
            if (number == iNumberAt) {
                return i2;
            }
            if (number < iNumberAt) {
                length = i - 1;
            } else {
                min = i + 1;
            }
        }
        return -1;
    }

    int getSchemaSize() {
        return this.buffer.length * 3;
    }

    @DoNotInline
    private boolean throwIllegalArgumentException() {
        throw new IllegalArgumentException();
    }
}
