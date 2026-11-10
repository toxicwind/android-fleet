package com.google.protobuf;

import com.ju.lib.datacommunication.network.http.address.AddressResponse;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes2.dex */
public final class UnsafeUtil {
    public static final boolean IS_BIG_ENDIAN;
    public static final int STRIDE = 8;
    public static final int STRIDE_ALIGNMENT_MASK = 7;
    public static final Unsafe UNSAFE = getUnsafe();
    public static final Class<?> MEMORY_CLASS = Android.MEMORY_CLASS;
    public static final boolean IS_ANDROID_64 = determineAndroidSupportByAddressSize(Long.TYPE);
    public static final boolean IS_ANDROID_32 = determineAndroidSupportByAddressSize(Integer.TYPE);
    public static final MemoryAccessor MEMORY_ACCESSOR = getMemoryAccessor();
    public static final boolean HAS_UNSAFE_BYTEBUFFER_OPERATIONS = supportsUnsafeByteBufferOperations();
    public static final boolean HAS_UNSAFE_ARRAY_OPERATIONS = supportsUnsafeArrayOperations();
    public static final long BYTE_ARRAY_BASE_OFFSET = arrayBaseOffset(byte[].class);
    public static final long BOOLEAN_ARRAY_BASE_OFFSET = arrayBaseOffset(boolean[].class);
    public static final long BOOLEAN_ARRAY_INDEX_SCALE = arrayIndexScale(boolean[].class);
    public static final long INT_ARRAY_BASE_OFFSET = arrayBaseOffset(int[].class);
    public static final long INT_ARRAY_INDEX_SCALE = arrayIndexScale(int[].class);
    public static final long LONG_ARRAY_BASE_OFFSET = arrayBaseOffset(long[].class);
    public static final long LONG_ARRAY_INDEX_SCALE = arrayIndexScale(long[].class);
    public static final long FLOAT_ARRAY_BASE_OFFSET = arrayBaseOffset(float[].class);
    public static final long FLOAT_ARRAY_INDEX_SCALE = arrayIndexScale(float[].class);
    public static final long DOUBLE_ARRAY_BASE_OFFSET = arrayBaseOffset(double[].class);
    public static final long DOUBLE_ARRAY_INDEX_SCALE = arrayIndexScale(double[].class);
    public static final long OBJECT_ARRAY_BASE_OFFSET = arrayBaseOffset(Object[].class);
    public static final long OBJECT_ARRAY_INDEX_SCALE = arrayIndexScale(Object[].class);
    public static final long BUFFER_ADDRESS_OFFSET = fieldOffset(bufferAddressField());
    public static final int BYTE_ARRAY_ALIGNMENT = (int) (BYTE_ARRAY_BASE_OFFSET & 7);

    private static final class Android32MemoryAccessor extends MemoryAccessor {
        public static final long SMALL_ADDRESS_MASK = -1;

        public Android32MemoryAccessor(Unsafe unsafe) {
            super(unsafe);
        }

        public static int smallAddress(long j) {
            return (int) (j & (-1));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void copyMemory(long j, byte[] bArr, long j2, long j3) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public boolean getBoolean(Object obj, long j) {
            return UnsafeUtil.IS_BIG_ENDIAN ? UnsafeUtil.getBooleanBigEndian(obj, j) : UnsafeUtil.getBooleanLittleEndian(obj, j);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public byte getByte(Object obj, long j) {
            return UnsafeUtil.IS_BIG_ENDIAN ? UnsafeUtil.getByteBigEndian(obj, j) : UnsafeUtil.getByteLittleEndian(obj, j);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public double getDouble(Object obj, long j) {
            return Double.longBitsToDouble(getLong(obj, j));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public float getFloat(Object obj, long j) {
            return Float.intBitsToFloat(getInt(obj, j));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public int getInt(long j) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public long getLong(long j) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public Object getStaticObject(java.lang.reflect.Field field) {
            try {
                return field.get(null);
            } catch (IllegalAccessException unused) {
                return null;
            }
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putBoolean(Object obj, long j, boolean z) {
            if (UnsafeUtil.IS_BIG_ENDIAN) {
                UnsafeUtil.putByteBigEndian(obj, j, z ? (byte) 1 : (byte) 0);
            } else {
                UnsafeUtil.putByteLittleEndian(obj, j, z ? (byte) 1 : (byte) 0);
            }
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putByte(Object obj, long j, byte b2) {
            if (UnsafeUtil.IS_BIG_ENDIAN) {
                UnsafeUtil.putByteBigEndian(obj, j, b2);
            } else {
                UnsafeUtil.putByteLittleEndian(obj, j, b2);
            }
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putDouble(Object obj, long j, double d2) {
            putLong(obj, j, Double.doubleToLongBits(d2));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putFloat(Object obj, long j, float f2) {
            putInt(obj, j, Float.floatToIntBits(f2));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putInt(long j, int i) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putLong(long j, long j2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public boolean supportsUnsafeByteBufferOperations() {
            return false;
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void copyMemory(byte[] bArr, long j, long j2, long j3) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public byte getByte(long j) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putByte(long j, byte b2) {
            throw new UnsupportedOperationException();
        }
    }

    private static final class Android64MemoryAccessor extends MemoryAccessor {
        public Android64MemoryAccessor(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void copyMemory(long j, byte[] bArr, long j2, long j3) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public boolean getBoolean(Object obj, long j) {
            return UnsafeUtil.IS_BIG_ENDIAN ? UnsafeUtil.getBooleanBigEndian(obj, j) : UnsafeUtil.getBooleanLittleEndian(obj, j);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public byte getByte(Object obj, long j) {
            return UnsafeUtil.IS_BIG_ENDIAN ? UnsafeUtil.getByteBigEndian(obj, j) : UnsafeUtil.getByteLittleEndian(obj, j);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public double getDouble(Object obj, long j) {
            return Double.longBitsToDouble(getLong(obj, j));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public float getFloat(Object obj, long j) {
            return Float.intBitsToFloat(getInt(obj, j));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public int getInt(long j) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public long getLong(long j) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public Object getStaticObject(java.lang.reflect.Field field) {
            try {
                return field.get(null);
            } catch (IllegalAccessException unused) {
                return null;
            }
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putBoolean(Object obj, long j, boolean z) {
            if (UnsafeUtil.IS_BIG_ENDIAN) {
                UnsafeUtil.putByteBigEndian(obj, j, z ? (byte) 1 : (byte) 0);
            } else {
                UnsafeUtil.putByteLittleEndian(obj, j, z ? (byte) 1 : (byte) 0);
            }
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putByte(Object obj, long j, byte b2) {
            if (UnsafeUtil.IS_BIG_ENDIAN) {
                UnsafeUtil.putByteBigEndian(obj, j, b2);
            } else {
                UnsafeUtil.putByteLittleEndian(obj, j, b2);
            }
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putDouble(Object obj, long j, double d2) {
            putLong(obj, j, Double.doubleToLongBits(d2));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putFloat(Object obj, long j, float f2) {
            putInt(obj, j, Float.floatToIntBits(f2));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putInt(long j, int i) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putLong(long j, long j2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public boolean supportsUnsafeByteBufferOperations() {
            return false;
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void copyMemory(byte[] bArr, long j, long j2, long j3) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public byte getByte(long j) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putByte(long j, byte b2) {
            throw new UnsupportedOperationException();
        }
    }

    private static final class JvmMemoryAccessor extends MemoryAccessor {
        public JvmMemoryAccessor(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void copyMemory(long j, byte[] bArr, long j2, long j3) {
            this.unsafe.copyMemory((Object) null, j, bArr, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + j2, j3);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public boolean getBoolean(Object obj, long j) {
            return this.unsafe.getBoolean(obj, j);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public byte getByte(Object obj, long j) {
            return this.unsafe.getByte(obj, j);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public double getDouble(Object obj, long j) {
            return this.unsafe.getDouble(obj, j);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public float getFloat(Object obj, long j) {
            return this.unsafe.getFloat(obj, j);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public int getInt(long j) {
            return this.unsafe.getInt(j);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public long getLong(long j) {
            return this.unsafe.getLong(j);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public Object getStaticObject(java.lang.reflect.Field field) {
            return getObject(this.unsafe.staticFieldBase(field), this.unsafe.staticFieldOffset(field));
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putBoolean(Object obj, long j, boolean z) {
            this.unsafe.putBoolean(obj, j, z);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putByte(Object obj, long j, byte b2) {
            this.unsafe.putByte(obj, j, b2);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putDouble(Object obj, long j, double d2) {
            this.unsafe.putDouble(obj, j, d2);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putFloat(Object obj, long j, float f2) {
            this.unsafe.putFloat(obj, j, f2);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putInt(long j, int i) {
            this.unsafe.putInt(j, i);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putLong(long j, long j2) {
            this.unsafe.putLong(j, j2);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public boolean supportsUnsafeArrayOperations() {
            if (!super.supportsUnsafeArrayOperations()) {
                return false;
            }
            try {
                Class<?> cls = this.unsafe.getClass();
                cls.getMethod("getByte", Object.class, Long.TYPE);
                cls.getMethod("putByte", Object.class, Long.TYPE, Byte.TYPE);
                cls.getMethod("getBoolean", Object.class, Long.TYPE);
                cls.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE);
                cls.getMethod("getFloat", Object.class, Long.TYPE);
                cls.getMethod("putFloat", Object.class, Long.TYPE, Float.TYPE);
                cls.getMethod("getDouble", Object.class, Long.TYPE);
                cls.getMethod("putDouble", Object.class, Long.TYPE, Double.TYPE);
                return true;
            } catch (Throwable th) {
                UnsafeUtil.logMissingMethod(th);
                return false;
            }
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public boolean supportsUnsafeByteBufferOperations() {
            if (!super.supportsUnsafeByteBufferOperations()) {
                return false;
            }
            try {
                Class<?> cls = this.unsafe.getClass();
                cls.getMethod("getByte", Long.TYPE);
                cls.getMethod("putByte", Long.TYPE, Byte.TYPE);
                cls.getMethod("getInt", Long.TYPE);
                cls.getMethod("putInt", Long.TYPE, Integer.TYPE);
                cls.getMethod("getLong", Long.TYPE);
                cls.getMethod("putLong", Long.TYPE, Long.TYPE);
                cls.getMethod("copyMemory", Long.TYPE, Long.TYPE, Long.TYPE);
                cls.getMethod("copyMemory", Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE);
                return true;
            } catch (Throwable th) {
                UnsafeUtil.logMissingMethod(th);
                return false;
            }
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void copyMemory(byte[] bArr, long j, long j2, long j3) {
            this.unsafe.copyMemory(bArr, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + j, (Object) null, j2, j3);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public byte getByte(long j) {
            return this.unsafe.getByte(j);
        }

        @Override // com.google.protobuf.UnsafeUtil.MemoryAccessor
        public void putByte(long j, byte b2) {
            this.unsafe.putByte(j, b2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static abstract class MemoryAccessor {
        public Unsafe unsafe;

        public MemoryAccessor(Unsafe unsafe) {
            this.unsafe = unsafe;
        }

        public final int arrayBaseOffset(Class<?> cls) {
            return this.unsafe.arrayBaseOffset(cls);
        }

        public final int arrayIndexScale(Class<?> cls) {
            return this.unsafe.arrayIndexScale(cls);
        }

        public abstract void copyMemory(long j, byte[] bArr, long j2, long j3);

        public abstract void copyMemory(byte[] bArr, long j, long j2, long j3);

        public abstract boolean getBoolean(Object obj, long j);

        public abstract byte getByte(long j);

        public abstract byte getByte(Object obj, long j);

        public abstract double getDouble(Object obj, long j);

        public abstract float getFloat(Object obj, long j);

        public abstract int getInt(long j);

        public final int getInt(Object obj, long j) {
            return this.unsafe.getInt(obj, j);
        }

        public abstract long getLong(long j);

        public final long getLong(Object obj, long j) {
            return this.unsafe.getLong(obj, j);
        }

        public final Object getObject(Object obj, long j) {
            return this.unsafe.getObject(obj, j);
        }

        public abstract Object getStaticObject(java.lang.reflect.Field field);

        public final long objectFieldOffset(java.lang.reflect.Field field) {
            return this.unsafe.objectFieldOffset(field);
        }

        public abstract void putBoolean(Object obj, long j, boolean z);

        public abstract void putByte(long j, byte b2);

        public abstract void putByte(Object obj, long j, byte b2);

        public abstract void putDouble(Object obj, long j, double d2);

        public abstract void putFloat(Object obj, long j, float f2);

        public abstract void putInt(long j, int i);

        public final void putInt(Object obj, long j, int i) {
            this.unsafe.putInt(obj, j, i);
        }

        public abstract void putLong(long j, long j2);

        public final void putLong(Object obj, long j, long j2) {
            this.unsafe.putLong(obj, j, j2);
        }

        public final void putObject(Object obj, long j, Object obj2) {
            this.unsafe.putObject(obj, j, obj2);
        }

        public boolean supportsUnsafeArrayOperations() {
            Unsafe unsafe = this.unsafe;
            if (unsafe == null) {
                return false;
            }
            try {
                Class<?> cls = unsafe.getClass();
                cls.getMethod("objectFieldOffset", java.lang.reflect.Field.class);
                cls.getMethod("arrayBaseOffset", Class.class);
                cls.getMethod("arrayIndexScale", Class.class);
                cls.getMethod("getInt", Object.class, Long.TYPE);
                cls.getMethod("putInt", Object.class, Long.TYPE, Integer.TYPE);
                cls.getMethod("getLong", Object.class, Long.TYPE);
                cls.getMethod("putLong", Object.class, Long.TYPE, Long.TYPE);
                cls.getMethod("getObject", Object.class, Long.TYPE);
                cls.getMethod("putObject", Object.class, Long.TYPE, Object.class);
                return true;
            } catch (Throwable th) {
                UnsafeUtil.logMissingMethod(th);
                return false;
            }
        }

        public boolean supportsUnsafeByteBufferOperations() {
            Unsafe unsafe = this.unsafe;
            if (unsafe == null) {
                return false;
            }
            try {
                Class<?> cls = unsafe.getClass();
                cls.getMethod("objectFieldOffset", java.lang.reflect.Field.class);
                cls.getMethod("getLong", Object.class, Long.TYPE);
                return UnsafeUtil.bufferAddressField() != null;
            } catch (Throwable th) {
                UnsafeUtil.logMissingMethod(th);
                return false;
            }
        }
    }

    static {
        IS_BIG_ENDIAN = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    }

    public static long addressOffset(ByteBuffer byteBuffer) {
        return MEMORY_ACCESSOR.getLong(byteBuffer, BUFFER_ADDRESS_OFFSET);
    }

    public static <T> T allocateInstance(Class<T> cls) {
        try {
            return (T) UNSAFE.allocateInstance(cls);
        } catch (InstantiationException e2) {
            throw new IllegalStateException(e2);
        }
    }

    public static int arrayBaseOffset(Class<?> cls) {
        if (HAS_UNSAFE_ARRAY_OPERATIONS) {
            return MEMORY_ACCESSOR.arrayBaseOffset(cls);
        }
        return -1;
    }

    public static int arrayIndexScale(Class<?> cls) {
        if (HAS_UNSAFE_ARRAY_OPERATIONS) {
            return MEMORY_ACCESSOR.arrayIndexScale(cls);
        }
        return -1;
    }

    public static java.lang.reflect.Field bufferAddressField() {
        java.lang.reflect.Field field;
        if (Android.isOnAndroidDevice() && (field = field(Buffer.class, "effectiveDirectAddress")) != null) {
            return field;
        }
        java.lang.reflect.Field field2 = field(Buffer.class, AddressResponse.sAddress);
        if (field2 == null || field2.getType() != Long.TYPE) {
            return null;
        }
        return field2;
    }

    public static void copyMemory(byte[] bArr, long j, long j2, long j3) {
        MEMORY_ACCESSOR.copyMemory(bArr, j, j2, j3);
    }

    public static boolean determineAndroidSupportByAddressSize(Class<?> cls) {
        if (!Android.isOnAndroidDevice()) {
            return false;
        }
        try {
            Class<?> cls2 = MEMORY_CLASS;
            cls2.getMethod("peekLong", cls, Boolean.TYPE);
            cls2.getMethod("pokeLong", cls, Long.TYPE, Boolean.TYPE);
            cls2.getMethod("pokeInt", cls, Integer.TYPE, Boolean.TYPE);
            cls2.getMethod("peekInt", cls, Boolean.TYPE);
            cls2.getMethod("pokeByte", cls, Byte.TYPE);
            cls2.getMethod("peekByte", cls);
            cls2.getMethod("pokeByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
            cls2.getMethod("peekByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static java.lang.reflect.Field field(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static long fieldOffset(java.lang.reflect.Field field) {
        MemoryAccessor memoryAccessor;
        if (field == null || (memoryAccessor = MEMORY_ACCESSOR) == null) {
            return -1L;
        }
        return memoryAccessor.objectFieldOffset(field);
    }

    public static int firstDifferingByteIndexNativeEndian(long j, long j2) {
        return (IS_BIG_ENDIAN ? Long.numberOfLeadingZeros(j ^ j2) : Long.numberOfTrailingZeros(j ^ j2)) >> 3;
    }

    public static boolean getBoolean(Object obj, long j) {
        return MEMORY_ACCESSOR.getBoolean(obj, j);
    }

    public static boolean getBooleanBigEndian(Object obj, long j) {
        return getByteBigEndian(obj, j) != 0;
    }

    public static boolean getBooleanLittleEndian(Object obj, long j) {
        return getByteLittleEndian(obj, j) != 0;
    }

    public static byte getByte(Object obj, long j) {
        return MEMORY_ACCESSOR.getByte(obj, j);
    }

    public static byte getByteBigEndian(Object obj, long j) {
        return (byte) ((getInt(obj, (-4) & j) >>> ((int) (((~j) & 3) << 3))) & 255);
    }

    public static byte getByteLittleEndian(Object obj, long j) {
        return (byte) ((getInt(obj, (-4) & j) >>> ((int) ((j & 3) << 3))) & 255);
    }

    public static double getDouble(Object obj, long j) {
        return MEMORY_ACCESSOR.getDouble(obj, j);
    }

    public static float getFloat(Object obj, long j) {
        return MEMORY_ACCESSOR.getFloat(obj, j);
    }

    public static int getInt(Object obj, long j) {
        return MEMORY_ACCESSOR.getInt(obj, j);
    }

    public static long getLong(Object obj, long j) {
        return MEMORY_ACCESSOR.getLong(obj, j);
    }

    public static MemoryAccessor getMemoryAccessor() {
        if (UNSAFE == null) {
            return null;
        }
        if (!Android.isOnAndroidDevice()) {
            return new JvmMemoryAccessor(UNSAFE);
        }
        if (IS_ANDROID_64) {
            return new Android64MemoryAccessor(UNSAFE);
        }
        if (IS_ANDROID_32) {
            return new Android32MemoryAccessor(UNSAFE);
        }
        return null;
    }

    public static Object getObject(Object obj, long j) {
        return MEMORY_ACCESSOR.getObject(obj, j);
    }

    public static Object getStaticObject(java.lang.reflect.Field field) {
        return MEMORY_ACCESSOR.getStaticObject(field);
    }

    public static Unsafe getUnsafe() {
        try {
            return (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() { // from class: com.google.protobuf.UnsafeUtil.1
                @Override // java.security.PrivilegedExceptionAction
                public Unsafe run() throws IllegalAccessException {
                    for (java.lang.reflect.Field field : Unsafe.class.getDeclaredFields()) {
                        field.setAccessible(true);
                        Object obj = field.get(null);
                        if (Unsafe.class.isInstance(obj)) {
                            return (Unsafe) Unsafe.class.cast(obj);
                        }
                    }
                    return null;
                }
            });
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean hasUnsafeArrayOperations() {
        return HAS_UNSAFE_ARRAY_OPERATIONS;
    }

    public static boolean hasUnsafeByteBufferOperations() {
        return HAS_UNSAFE_BYTEBUFFER_OPERATIONS;
    }

    public static boolean isAndroid64() {
        return IS_ANDROID_64;
    }

    public static void logMissingMethod(Throwable th) {
        Logger.getLogger(UnsafeUtil.class.getName()).log(Level.WARNING, "platform method missing - proto runtime falling back to safer methods: " + th);
    }

    public static int mismatch(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        if (i < 0 || i2 < 0 || i3 < 0 || i + i3 > bArr.length || i2 + i3 > bArr2.length) {
            throw new IndexOutOfBoundsException();
        }
        int i4 = 0;
        if (HAS_UNSAFE_ARRAY_OPERATIONS) {
            for (int i5 = (BYTE_ARRAY_ALIGNMENT + i) & 7; i4 < i3 && (i5 & 7) != 0; i5++) {
                if (bArr[i + i4] != bArr2[i2 + i4]) {
                    return i4;
                }
                i4++;
            }
            int i6 = ((i3 - i4) & (-8)) + i4;
            while (i4 < i6) {
                long j = i4;
                long j2 = getLong((Object) bArr, BYTE_ARRAY_BASE_OFFSET + ((long) i) + j);
                long j3 = getLong((Object) bArr2, BYTE_ARRAY_BASE_OFFSET + ((long) i2) + j);
                if (j2 != j3) {
                    return firstDifferingByteIndexNativeEndian(j2, j3) + i4;
                }
                i4 += 8;
            }
        }
        while (i4 < i3) {
            if (bArr[i + i4] != bArr2[i2 + i4]) {
                return i4;
            }
            i4++;
        }
        return -1;
    }

    public static long objectFieldOffset(java.lang.reflect.Field field) {
        return MEMORY_ACCESSOR.objectFieldOffset(field);
    }

    public static void putBoolean(Object obj, long j, boolean z) {
        MEMORY_ACCESSOR.putBoolean(obj, j, z);
    }

    public static void putBooleanBigEndian(Object obj, long j, boolean z) {
        putByteBigEndian(obj, j, z ? (byte) 1 : (byte) 0);
    }

    public static void putBooleanLittleEndian(Object obj, long j, boolean z) {
        putByteLittleEndian(obj, j, z ? (byte) 1 : (byte) 0);
    }

    public static void putByte(Object obj, long j, byte b2) {
        MEMORY_ACCESSOR.putByte(obj, j, b2);
    }

    public static void putByteBigEndian(Object obj, long j, byte b2) {
        long j2 = (-4) & j;
        int i = getInt(obj, j2);
        int i2 = ((~((int) j)) & 3) << 3;
        MEMORY_ACCESSOR.putInt(obj, j2, ((255 & b2) << i2) | (i & (~(255 << i2))));
    }

    public static void putByteLittleEndian(Object obj, long j, byte b2) {
        long j2 = (-4) & j;
        int i = (((int) j) & 3) << 3;
        MEMORY_ACCESSOR.putInt(obj, j2, ((255 & b2) << i) | (getInt(obj, j2) & (~(255 << i))));
    }

    public static void putDouble(Object obj, long j, double d2) {
        MEMORY_ACCESSOR.putDouble(obj, j, d2);
    }

    public static void putFloat(Object obj, long j, float f2) {
        MEMORY_ACCESSOR.putFloat(obj, j, f2);
    }

    public static void putInt(Object obj, long j, int i) {
        MEMORY_ACCESSOR.putInt(obj, j, i);
    }

    public static void putLong(Object obj, long j, long j2) {
        MEMORY_ACCESSOR.putLong(obj, j, j2);
    }

    public static void putObject(Object obj, long j, Object obj2) {
        MEMORY_ACCESSOR.putObject(obj, j, obj2);
    }

    public static boolean supportsUnsafeArrayOperations() {
        MemoryAccessor memoryAccessor = MEMORY_ACCESSOR;
        if (memoryAccessor == null) {
            return false;
        }
        return memoryAccessor.supportsUnsafeArrayOperations();
    }

    public static boolean supportsUnsafeByteBufferOperations() {
        MemoryAccessor memoryAccessor = MEMORY_ACCESSOR;
        if (memoryAccessor == null) {
            return false;
        }
        return memoryAccessor.supportsUnsafeByteBufferOperations();
    }

    public static void copyMemory(long j, byte[] bArr, long j2, long j3) {
        MEMORY_ACCESSOR.copyMemory(j, bArr, j2, j3);
    }

    public static boolean getBoolean(boolean[] zArr, long j) {
        return MEMORY_ACCESSOR.getBoolean(zArr, (j * BOOLEAN_ARRAY_INDEX_SCALE) + BOOLEAN_ARRAY_BASE_OFFSET);
    }

    public static byte getByte(byte[] bArr, long j) {
        return MEMORY_ACCESSOR.getByte(bArr, BYTE_ARRAY_BASE_OFFSET + j);
    }

    public static double getDouble(double[] dArr, long j) {
        return MEMORY_ACCESSOR.getDouble(dArr, (j * DOUBLE_ARRAY_INDEX_SCALE) + DOUBLE_ARRAY_BASE_OFFSET);
    }

    public static float getFloat(float[] fArr, long j) {
        return MEMORY_ACCESSOR.getFloat(fArr, (j * FLOAT_ARRAY_INDEX_SCALE) + FLOAT_ARRAY_BASE_OFFSET);
    }

    public static int getInt(int[] iArr, long j) {
        return MEMORY_ACCESSOR.getInt(iArr, (j * INT_ARRAY_INDEX_SCALE) + INT_ARRAY_BASE_OFFSET);
    }

    public static long getLong(long[] jArr, long j) {
        return MEMORY_ACCESSOR.getLong(jArr, (j * LONG_ARRAY_INDEX_SCALE) + LONG_ARRAY_BASE_OFFSET);
    }

    public static Object getObject(Object[] objArr, long j) {
        return MEMORY_ACCESSOR.getObject(objArr, (j * OBJECT_ARRAY_INDEX_SCALE) + OBJECT_ARRAY_BASE_OFFSET);
    }

    public static void putBoolean(boolean[] zArr, long j, boolean z) {
        MEMORY_ACCESSOR.putBoolean(zArr, (j * BOOLEAN_ARRAY_INDEX_SCALE) + BOOLEAN_ARRAY_BASE_OFFSET, z);
    }

    public static void putByte(byte[] bArr, long j, byte b2) {
        MEMORY_ACCESSOR.putByte(bArr, BYTE_ARRAY_BASE_OFFSET + j, b2);
    }

    public static void putDouble(double[] dArr, long j, double d2) {
        MEMORY_ACCESSOR.putDouble(dArr, (j * DOUBLE_ARRAY_INDEX_SCALE) + DOUBLE_ARRAY_BASE_OFFSET, d2);
    }

    public static void putFloat(float[] fArr, long j, float f2) {
        MEMORY_ACCESSOR.putFloat(fArr, (j * FLOAT_ARRAY_INDEX_SCALE) + FLOAT_ARRAY_BASE_OFFSET, f2);
    }

    public static void putInt(int[] iArr, long j, int i) {
        MEMORY_ACCESSOR.putInt(iArr, (j * INT_ARRAY_INDEX_SCALE) + INT_ARRAY_BASE_OFFSET, i);
    }

    public static void putLong(long[] jArr, long j, long j2) {
        MEMORY_ACCESSOR.putLong(jArr, (j * LONG_ARRAY_INDEX_SCALE) + LONG_ARRAY_BASE_OFFSET, j2);
    }

    public static void putObject(Object[] objArr, long j, Object obj) {
        MEMORY_ACCESSOR.putObject(objArr, (j * OBJECT_ARRAY_INDEX_SCALE) + OBJECT_ARRAY_BASE_OFFSET, obj);
    }

    public static void copyMemory(byte[] bArr, long j, byte[] bArr2, long j2, long j3) {
        System.arraycopy(bArr, (int) j, bArr2, (int) j2, (int) j3);
    }

    public static byte getByte(long j) {
        return MEMORY_ACCESSOR.getByte(j);
    }

    public static int getInt(long j) {
        return MEMORY_ACCESSOR.getInt(j);
    }

    public static long getLong(long j) {
        return MEMORY_ACCESSOR.getLong(j);
    }

    public static void putByte(long j, byte b2) {
        MEMORY_ACCESSOR.putByte(j, b2);
    }

    public static void putInt(long j, int i) {
        MEMORY_ACCESSOR.putInt(j, i);
    }

    public static void putLong(long j, long j2) {
        MEMORY_ACCESSOR.putLong(j, j2);
    }
}
