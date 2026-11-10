package com.google.protobuf;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

/* JADX INFO: loaded from: classes2.dex */
public final class ByteBufferWriter {
    public static final float BUFFER_REALLOCATION_THRESHOLD = 0.5f;
    public static final int MAX_CACHED_BUFFER_SIZE = 16384;
    public static final int MIN_CACHED_BUFFER_SIZE = 1024;
    public static final ThreadLocal<SoftReference<byte[]>> BUFFER = new ThreadLocal<>();
    public static final Class<?> FILE_OUTPUT_STREAM_CLASS = safeGetClass("java.io.FileOutputStream");
    public static final long CHANNEL_FIELD_OFFSET = getChannelFieldOffset(FILE_OUTPUT_STREAM_CLASS);

    public static void clearCachedBuffer() {
        BUFFER.set(null);
    }

    public static byte[] getBuffer() {
        SoftReference<byte[]> softReference = BUFFER.get();
        if (softReference == null) {
            return null;
        }
        return softReference.get();
    }

    public static long getChannelFieldOffset(Class<?> cls) {
        if (cls == null) {
            return -1L;
        }
        try {
            if (UnsafeUtil.HAS_UNSAFE_ARRAY_OPERATIONS) {
                return UnsafeUtil.objectFieldOffset(cls.getDeclaredField("channel"));
            }
            return -1L;
        } catch (Throwable unused) {
            return -1L;
        }
    }

    public static byte[] getOrCreateBuffer(int i) {
        int iMax = Math.max(i, 1024);
        byte[] buffer = getBuffer();
        if (buffer == null || needToReallocate(iMax, buffer.length)) {
            buffer = new byte[iMax];
            if (iMax <= 16384) {
                setBuffer(buffer);
            }
        }
        return buffer;
    }

    public static boolean needToReallocate(int i, int i2) {
        return i2 < i && ((float) i2) < ((float) i) * 0.5f;
    }

    public static Class<?> safeGetClass(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static void setBuffer(byte[] bArr) {
        BUFFER.set(new SoftReference<>(bArr));
    }

    public static void write(ByteBuffer byteBuffer, OutputStream outputStream) {
        int iPosition = byteBuffer.position();
        try {
            if (byteBuffer.hasArray()) {
                outputStream.write(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
            } else if (!writeToChannel(byteBuffer, outputStream)) {
                byte[] orCreateBuffer = getOrCreateBuffer(byteBuffer.remaining());
                while (byteBuffer.hasRemaining()) {
                    int iMin = Math.min(byteBuffer.remaining(), orCreateBuffer.length);
                    byteBuffer.get(orCreateBuffer, 0, iMin);
                    outputStream.write(orCreateBuffer, 0, iMin);
                }
            }
        } finally {
            byteBuffer.position(iPosition);
        }
    }

    public static boolean writeToChannel(ByteBuffer byteBuffer, OutputStream outputStream) throws IOException {
        WritableByteChannel writableByteChannel;
        if (CHANNEL_FIELD_OFFSET < 0 || !FILE_OUTPUT_STREAM_CLASS.isInstance(outputStream)) {
            return false;
        }
        try {
            writableByteChannel = (WritableByteChannel) UnsafeUtil.getObject(outputStream, CHANNEL_FIELD_OFFSET);
        } catch (ClassCastException unused) {
            writableByteChannel = null;
        }
        if (writableByteChannel == null) {
            return false;
        }
        writableByteChannel.write(byteBuffer);
        return true;
    }
}
