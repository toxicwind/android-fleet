package org.mozilla.classfile;

import j$.util.Map;
import java.util.HashMap;
import kotlin.UShort;

/* JADX INFO: loaded from: classes8.dex */
final class ConstantPool {
    static final byte CONSTANT_Class = 7;
    static final byte CONSTANT_Double = 6;
    static final byte CONSTANT_Fieldref = 9;
    static final byte CONSTANT_Float = 4;
    static final byte CONSTANT_Integer = 3;
    static final byte CONSTANT_InterfaceMethodref = 11;
    static final byte CONSTANT_InvokeDynamic = 18;
    static final byte CONSTANT_Long = 5;
    static final byte CONSTANT_MethodHandle = 15;
    static final byte CONSTANT_MethodType = 16;
    static final byte CONSTANT_Methodref = 10;
    static final byte CONSTANT_NameAndType = 12;
    static final byte CONSTANT_String = 8;
    static final byte CONSTANT_Utf8 = 1;
    private static final int ConstantPoolSize = 256;
    private static final int MAX_UTF_ENCODING_SIZE = 65535;
    private ClassFileWriter cfw;
    private final HashMap<Integer, Integer> itsStringConstHash = new HashMap<>();
    private final HashMap<String, Integer> itsUtf8Hash = new HashMap<>();
    private final HashMap<FieldOrMethodRef, Integer> itsFieldRefHash = new HashMap<>();
    private final HashMap<FieldOrMethodRef, Integer> itsMethodRefHash = new HashMap<>();
    private final HashMap<String, Integer> itsClassHash = new HashMap<>();
    private final HashMap<Object, Integer> itsConstantHash = new HashMap<>();
    private final HashMap<Integer, Object> itsConstantData = new HashMap<>();
    private final HashMap<Integer, Byte> itsPoolTypes = new HashMap<>();
    private int itsTopIndex = 1;
    private byte[] itsPool = new byte[256];
    private int itsTop = 0;

    ConstantPool(ClassFileWriter classFileWriter) {
        this.cfw = classFileWriter;
    }

    int write(byte[] bArr, int i) {
        int iPutInt16 = ClassFileWriter.putInt16((short) this.itsTopIndex, bArr, i);
        System.arraycopy(this.itsPool, 0, bArr, iPutInt16, this.itsTop);
        return iPutInt16 + this.itsTop;
    }

    int getWriteSize() {
        return this.itsTop + 2;
    }

    int addConstant(int i) {
        ensure(5);
        byte[] bArr = this.itsPool;
        int i2 = this.itsTop;
        int i3 = i2 + 1;
        this.itsTop = i3;
        bArr[i2] = 3;
        this.itsTop = ClassFileWriter.putInt32(i, bArr, i3);
        this.itsPoolTypes.put(Integer.valueOf(this.itsTopIndex), (byte) 3);
        int i4 = this.itsTopIndex;
        this.itsTopIndex = i4 + 1;
        return (short) i4;
    }

    int addConstant(long j) {
        ensure(9);
        byte[] bArr = this.itsPool;
        int i = this.itsTop;
        int i2 = i + 1;
        this.itsTop = i2;
        bArr[i] = 5;
        this.itsTop = ClassFileWriter.putInt64(j, bArr, i2);
        int i3 = this.itsTopIndex;
        this.itsTopIndex = i3 + 2;
        this.itsPoolTypes.put(Integer.valueOf(i3), (byte) 5);
        return i3;
    }

    int addConstant(float f) {
        ensure(5);
        byte[] bArr = this.itsPool;
        int i = this.itsTop;
        this.itsTop = i + 1;
        bArr[i] = 4;
        this.itsTop = ClassFileWriter.putInt32(Float.floatToIntBits(f), this.itsPool, this.itsTop);
        this.itsPoolTypes.put(Integer.valueOf(this.itsTopIndex), (byte) 4);
        int i2 = this.itsTopIndex;
        this.itsTopIndex = i2 + 1;
        return i2;
    }

    int addConstant(double d) {
        ensure(9);
        byte[] bArr = this.itsPool;
        int i = this.itsTop;
        this.itsTop = i + 1;
        bArr[i] = 6;
        this.itsTop = ClassFileWriter.putInt64(Double.doubleToLongBits(d), this.itsPool, this.itsTop);
        int i2 = this.itsTopIndex;
        this.itsTopIndex = i2 + 2;
        this.itsPoolTypes.put(Integer.valueOf(i2), (byte) 6);
        return i2;
    }

    int addConstant(String str) {
        int iAddUtf8 = addUtf8(str) & UShort.MAX_VALUE;
        int iIntValue = ((Integer) Map.EL.getOrDefault(this.itsStringConstHash, Integer.valueOf(iAddUtf8), -1)).intValue();
        if (iIntValue == -1) {
            iIntValue = this.itsTopIndex;
            this.itsTopIndex = iIntValue + 1;
            ensure(3);
            byte[] bArr = this.itsPool;
            int i = this.itsTop;
            int i2 = i + 1;
            this.itsTop = i2;
            bArr[i] = 8;
            this.itsTop = ClassFileWriter.putInt16(iAddUtf8, bArr, i2);
            this.itsStringConstHash.put(Integer.valueOf(iAddUtf8), Integer.valueOf(iIntValue));
        }
        this.itsPoolTypes.put(Integer.valueOf(iIntValue), (byte) 8);
        return iIntValue;
    }

    int addConstant(Object obj) {
        if ((obj instanceof Integer) || (obj instanceof Byte) || (obj instanceof Short)) {
            return addConstant(((Number) obj).intValue());
        }
        if (obj instanceof Character) {
            return addConstant((int) ((Character) obj).charValue());
        }
        if (obj instanceof Boolean) {
            return addConstant(((Boolean) obj).booleanValue() ? 1 : 0);
        }
        if (obj instanceof Float) {
            return addConstant(((Float) obj).floatValue());
        }
        if (obj instanceof Long) {
            return addConstant(((Long) obj).longValue());
        }
        if (obj instanceof Double) {
            return addConstant(((Double) obj).doubleValue());
        }
        if (obj instanceof String) {
            return addConstant((String) obj);
        }
        if (obj instanceof ClassFileWriter.MHandle) {
            return addMethodHandle((ClassFileWriter.MHandle) obj);
        }
        throw new IllegalArgumentException("value " + String.valueOf(obj));
    }

    boolean isUnderUtfEncodingLimit(String str) {
        int length = str.length();
        if (length * 3 <= 65535) {
            return true;
        }
        return length <= 65535 && length == getUtfEncodingLimit(str, 0, length);
    }

    int getUtfEncodingLimit(String str, int i, int i2) {
        int i3 = 65535;
        if ((i2 - i) * 3 > 65535) {
            while (i != i2) {
                char cCharAt = str.charAt(i);
                if (cCharAt == 0 || cCharAt > 127) {
                    i3 = cCharAt < 2047 ? i3 - 2 : i3 - 3;
                } else {
                    i3--;
                }
                if (i3 < 0) {
                    return i;
                }
                i++;
            }
        }
        return i2;
    }

    /* JADX WARN: Code duplicated, block: B:6:0x001d  */
    short addUtf8(String str) {
        boolean z;
        int iIntValue = ((Integer) Map.EL.getOrDefault(this.itsUtf8Hash, str, -1)).intValue();
        if (iIntValue == -1) {
            int length = str.length();
            if (length > 65535) {
                z = true;
            } else {
                ensure((length * 3) + 3);
                int i = this.itsTop;
                this.itsPool[i] = 1;
                int i2 = i + 3;
                char[] charBuffer = this.cfw.getCharBuffer(length);
                z = false;
                str.getChars(0, length, charBuffer, 0);
                for (int i3 = 0; i3 != length; i3++) {
                    char c = charBuffer[i3];
                    if (c != 0 && c <= 127) {
                        this.itsPool[i2] = (byte) c;
                        i2++;
                    } else {
                        byte[] bArr = this.itsPool;
                        if (c > 2047) {
                            bArr[i2] = (byte) ((c >> '\f') | 224);
                            int i4 = i2 + 2;
                            bArr[i2 + 1] = (byte) (((c >> 6) & 63) | 128);
                            i2 += 3;
                            bArr[i4] = (byte) ((c & '?') | 128);
                        } else {
                            int i5 = i2 + 1;
                            bArr[i2] = (byte) ((c >> 6) | 192);
                            i2 += 2;
                            bArr[i5] = (byte) ((c & '?') | 128);
                        }
                    }
                }
                int i6 = this.itsTop;
                int i7 = i2 - (i6 + 3);
                if (i7 > 65535) {
                    z = true;
                } else {
                    byte[] bArr2 = this.itsPool;
                    bArr2[i6 + 1] = (byte) (i7 >>> 8);
                    bArr2[i6 + 2] = (byte) i7;
                    this.itsTop = i2;
                    iIntValue = this.itsTopIndex;
                    this.itsTopIndex = iIntValue + 1;
                    this.itsUtf8Hash.put(str, Integer.valueOf(iIntValue));
                }
            }
            if (z) {
                throw new IllegalArgumentException("Too big string");
            }
        }
        setConstantData(iIntValue, str);
        this.itsPoolTypes.put(Integer.valueOf(iIntValue), (byte) 1);
        return (short) iIntValue;
    }

    private short addNameAndType(String str, String str2) {
        short sAddUtf8 = addUtf8(str);
        short sAddUtf9 = addUtf8(str2);
        ensure(5);
        byte[] bArr = this.itsPool;
        int i = this.itsTop;
        int i2 = i + 1;
        this.itsTop = i2;
        bArr[i] = 12;
        int iPutInt16 = ClassFileWriter.putInt16(sAddUtf8, bArr, i2);
        this.itsTop = iPutInt16;
        this.itsTop = ClassFileWriter.putInt16(sAddUtf9, this.itsPool, iPutInt16);
        this.itsPoolTypes.put(Integer.valueOf(this.itsTopIndex), (byte) 12);
        int i3 = this.itsTopIndex;
        this.itsTopIndex = i3 + 1;
        return (short) i3;
    }

    short addClass(String str) {
        String str2;
        int iIntValue = ((Integer) Map.EL.getOrDefault(this.itsClassHash, str, -1)).intValue();
        if (iIntValue == -1) {
            if (str.indexOf(46) > 0) {
                String slashedForm = ClassFileWriter.getSlashedForm(str);
                Integer num = (Integer) Map.EL.getOrDefault(this.itsClassHash, slashedForm, -1);
                int iIntValue2 = num.intValue();
                if (iIntValue2 != -1) {
                    this.itsClassHash.put(str, num);
                }
                str2 = slashedForm;
                iIntValue = iIntValue2;
            } else {
                str2 = str;
            }
            if (iIntValue == -1) {
                short sAddUtf8 = addUtf8(str2);
                ensure(3);
                byte[] bArr = this.itsPool;
                int i = this.itsTop;
                int i2 = i + 1;
                this.itsTop = i2;
                bArr[i] = 7;
                this.itsTop = ClassFileWriter.putInt16(sAddUtf8, bArr, i2);
                iIntValue = this.itsTopIndex;
                this.itsTopIndex = iIntValue + 1;
                this.itsClassHash.put(str2, Integer.valueOf(iIntValue));
                if (!str.equals(str2)) {
                    this.itsClassHash.put(str, Integer.valueOf(iIntValue));
                }
            }
        }
        setConstantData(iIntValue, str);
        this.itsPoolTypes.put(Integer.valueOf(iIntValue), (byte) 7);
        return (short) iIntValue;
    }

    short addFieldRef(String str, String str2, String str3) {
        FieldOrMethodRef fieldOrMethodRef = new FieldOrMethodRef(str, str2, str3);
        int iIntValue = ((Integer) Map.EL.getOrDefault(this.itsFieldRefHash, fieldOrMethodRef, -1)).intValue();
        if (iIntValue == -1) {
            short sAddNameAndType = addNameAndType(str2, str3);
            short sAddClass = addClass(str);
            ensure(5);
            byte[] bArr = this.itsPool;
            int i = this.itsTop;
            int i2 = i + 1;
            this.itsTop = i2;
            bArr[i] = 9;
            int iPutInt16 = ClassFileWriter.putInt16(sAddClass, bArr, i2);
            this.itsTop = iPutInt16;
            this.itsTop = ClassFileWriter.putInt16(sAddNameAndType, this.itsPool, iPutInt16);
            iIntValue = this.itsTopIndex;
            this.itsTopIndex = iIntValue + 1;
            this.itsFieldRefHash.put(fieldOrMethodRef, Integer.valueOf(iIntValue));
        }
        setConstantData(iIntValue, fieldOrMethodRef);
        this.itsPoolTypes.put(Integer.valueOf(iIntValue), (byte) 9);
        return (short) iIntValue;
    }

    short addMethodRef(String str, String str2, String str3) {
        FieldOrMethodRef fieldOrMethodRef = new FieldOrMethodRef(str, str2, str3);
        int iIntValue = ((Integer) Map.EL.getOrDefault(this.itsMethodRefHash, fieldOrMethodRef, -1)).intValue();
        if (iIntValue == -1) {
            short sAddNameAndType = addNameAndType(str2, str3);
            short sAddClass = addClass(str);
            ensure(5);
            byte[] bArr = this.itsPool;
            int i = this.itsTop;
            int i2 = i + 1;
            this.itsTop = i2;
            bArr[i] = 10;
            int iPutInt16 = ClassFileWriter.putInt16(sAddClass, bArr, i2);
            this.itsTop = iPutInt16;
            this.itsTop = ClassFileWriter.putInt16(sAddNameAndType, this.itsPool, iPutInt16);
            iIntValue = this.itsTopIndex;
            this.itsTopIndex = iIntValue + 1;
            this.itsMethodRefHash.put(fieldOrMethodRef, Integer.valueOf(iIntValue));
        }
        setConstantData(iIntValue, fieldOrMethodRef);
        this.itsPoolTypes.put(Integer.valueOf(iIntValue), (byte) 10);
        return (short) iIntValue;
    }

    short addInterfaceMethodRef(String str, String str2, String str3) {
        short sAddNameAndType = addNameAndType(str2, str3);
        short sAddClass = addClass(str);
        ensure(5);
        byte[] bArr = this.itsPool;
        int i = this.itsTop;
        int i2 = i + 1;
        this.itsTop = i2;
        bArr[i] = 11;
        int iPutInt16 = ClassFileWriter.putInt16(sAddClass, bArr, i2);
        this.itsTop = iPutInt16;
        this.itsTop = ClassFileWriter.putInt16(sAddNameAndType, this.itsPool, iPutInt16);
        setConstantData(this.itsTopIndex, new FieldOrMethodRef(str, str2, str3));
        this.itsPoolTypes.put(Integer.valueOf(this.itsTopIndex), (byte) 11);
        int i3 = this.itsTopIndex;
        this.itsTopIndex = i3 + 1;
        return (short) i3;
    }

    short addInvokeDynamic(String str, String str2, int i) {
        ConstantEntry constantEntry = new ConstantEntry(18, i, str, str2);
        int iIntValue = ((Integer) Map.EL.getOrDefault(this.itsConstantHash, constantEntry, -1)).intValue();
        if (iIntValue == -1) {
            short sAddNameAndType = addNameAndType(str, str2);
            ensure(5);
            byte[] bArr = this.itsPool;
            int i2 = this.itsTop;
            int i3 = i2 + 1;
            this.itsTop = i3;
            bArr[i2] = 18;
            int iPutInt16 = ClassFileWriter.putInt16(i, bArr, i3);
            this.itsTop = iPutInt16;
            this.itsTop = ClassFileWriter.putInt16(sAddNameAndType, this.itsPool, iPutInt16);
            iIntValue = this.itsTopIndex;
            this.itsTopIndex = iIntValue + 1;
            this.itsConstantHash.put(constantEntry, Integer.valueOf(iIntValue));
            setConstantData(iIntValue, str2);
            this.itsPoolTypes.put(Integer.valueOf(iIntValue), (byte) 18);
        }
        return (short) iIntValue;
    }

    short addMethodHandle(ClassFileWriter.MHandle mHandle) {
        short sAddMethodRef;
        int iIntValue = ((Integer) Map.EL.getOrDefault(this.itsConstantHash, mHandle, -1)).intValue();
        if (iIntValue == -1) {
            if (mHandle.tag <= 4) {
                sAddMethodRef = addFieldRef(mHandle.owner, mHandle.name, mHandle.desc);
            } else if (mHandle.tag == 9) {
                sAddMethodRef = addInterfaceMethodRef(mHandle.owner, mHandle.name, mHandle.desc);
            } else {
                sAddMethodRef = addMethodRef(mHandle.owner, mHandle.name, mHandle.desc);
            }
            ensure(4);
            byte[] bArr = this.itsPool;
            int i = this.itsTop;
            int i2 = i + 1;
            this.itsTop = i2;
            bArr[i] = 15;
            this.itsTop = i + 2;
            bArr[i2] = mHandle.tag;
            this.itsTop = ClassFileWriter.putInt16(sAddMethodRef, this.itsPool, this.itsTop);
            iIntValue = this.itsTopIndex;
            this.itsTopIndex = iIntValue + 1;
            this.itsConstantHash.put(mHandle, Integer.valueOf(iIntValue));
            this.itsPoolTypes.put(Integer.valueOf(iIntValue), (byte) 15);
        }
        return (short) iIntValue;
    }

    Object getConstantData(int i) {
        return this.itsConstantData.get(Integer.valueOf(i));
    }

    void setConstantData(int i, Object obj) {
        this.itsConstantData.put(Integer.valueOf(i), obj);
    }

    byte getConstantType(int i) {
        return ((Byte) Map.EL.getOrDefault(this.itsPoolTypes, Integer.valueOf(i), (byte) 0)).byteValue();
    }

    private void ensure(int i) {
        int i2 = this.itsTop;
        int i3 = i2 + i;
        byte[] bArr = this.itsPool;
        if (i3 > bArr.length) {
            int length = bArr.length * 2;
            if (i2 + i > length) {
                length = i2 + i;
            }
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 0, bArr2, 0, i2);
            this.itsPool = bArr2;
        }
    }
}
