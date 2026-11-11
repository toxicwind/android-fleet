package com.google.protobuf;

import androidx.media3.exoplayer.analytics.AnalyticsListener;
import com.google.common.base.Ascii;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* JADX INFO: loaded from: classes6.dex */
final class Utf8 {
    private static final long ASCII_MASK_LONG = -9187201950435737472L;
    static final int MAX_BYTES_PER_CHAR = 3;
    private static final int UNSAFE_COUNT_ASCII_THRESHOLD = 16;
    private static final Processor processor;

    static {
        Processor safeProcessor;
        if (!Android.isOnAndroidDevice() && UnsafeProcessor.isAvailable()) {
            safeProcessor = new UnsafeProcessor();
        } else {
            safeProcessor = new SafeProcessor();
        }
        processor = safeProcessor;
    }

    static boolean isValidUtf8(byte[] bytes) {
        return processor.isValidUtf8(bytes, 0, bytes.length);
    }

    static boolean isValidUtf8(byte[] bytes, int index, int limit) {
        return processor.isValidUtf8(bytes, index, limit);
    }

    static boolean isValidUtf8(ByteBuffer buffer) {
        int iPosition = buffer.position();
        int iRemaining = buffer.remaining() + iPosition;
        if (buffer.hasArray()) {
            int iArrayOffset = buffer.arrayOffset();
            return processor.isValidUtf8(buffer.array(), iPosition + iArrayOffset, iArrayOffset + iRemaining);
        }
        if (buffer.isDirect()) {
            return processor.isValidUtf8BufferDirect(buffer, iPosition, iRemaining);
        }
        return processor.isValidUtf8BufferDefault(buffer, iPosition, iRemaining);
    }

    private static class UnpairedSurrogateException extends Exception {
        UnpairedSurrogateException(int index, int length) {
            super("Unpaired surrogate at index " + index + " of " + length);
        }
    }

    static int encodedLength(String string) {
        int length = string.length();
        int i = 0;
        while (i < length && string.charAt(i) < 128) {
            i++;
        }
        int iEncodedLengthGeneral = length;
        while (i < length) {
            char cCharAt = string.charAt(i);
            if (cCharAt >= 2048) {
                try {
                    iEncodedLengthGeneral += encodedLengthGeneral(string, i);
                    break;
                } catch (UnpairedSurrogateException unused) {
                    return string.getBytes(StandardCharsets.UTF_8).length;
                }
            }
            iEncodedLengthGeneral += (127 - cCharAt) >>> 31;
            i++;
        }
        if (iEncodedLengthGeneral >= length) {
            return iEncodedLengthGeneral;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) iEncodedLengthGeneral) + 4294967296L));
    }

    private static int encodedLengthGeneral(String string, int start) throws UnpairedSurrogateException {
        int length = string.length();
        int i = 0;
        while (start < length) {
            char cCharAt = string.charAt(start);
            if (cCharAt < 2048) {
                i += (127 - cCharAt) >>> 31;
            } else {
                i += 2;
                if (55296 <= cCharAt && cCharAt <= 57343) {
                    if (Character.codePointAt(string, start) < 65536) {
                        throw new UnpairedSurrogateException(start, length);
                    }
                    start++;
                }
            }
            start++;
        }
        return i;
    }

    static int encode(String in, byte[] out, int offset, int length) {
        return processor.encodeUtf8(in, out, offset, length);
    }

    static String decodeUtf8(ByteBuffer buffer, int index, int size) throws InvalidProtocolBufferException {
        if (size == 0) {
            return "";
        }
        return processor.decodeUtf8(buffer, index, size);
    }

    static String decodeUtf8(byte[] bytes, int index, int size) throws InvalidProtocolBufferException {
        if (size == 0) {
            return "";
        }
        return processor.decodeUtf8(bytes, index, size);
    }

    static void encodeUtf8(String in, ByteBuffer out) {
        processor.encodeUtf8(in, out);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int estimateConsecutiveAscii(ByteBuffer buffer, int index, int limit) {
        int i = limit - 7;
        int i2 = index;
        while (i2 < i && (buffer.getLong(i2) & (-9187201950435737472L)) == 0) {
            i2 += 8;
        }
        return i2 - index;
    }

    static abstract class Processor {
        abstract String decodeUtf8(byte[] bytes, int index, int size) throws InvalidProtocolBufferException;

        abstract String decodeUtf8Direct(ByteBuffer buffer, int index, int size) throws InvalidProtocolBufferException;

        abstract int encodeUtf8(String in, byte[] out, int offset, int length);

        protected abstract void encodeUtf8Internal(String in, ByteBuffer out);

        abstract boolean isValidUtf8(byte[] bytes, int index, int limit);

        Processor() {
        }

        protected boolean isValidUtf8BufferDirect(ByteBuffer buffer, int index, int limit) {
            return isValidUtf8BufferDefault(buffer, index, limit);
        }

        protected boolean isValidUtf8BufferDefault(ByteBuffer buffer, int index, int limit) {
            int iEstimateConsecutiveAscii = index + Utf8.estimateConsecutiveAscii(buffer, index, limit);
            while (iEstimateConsecutiveAscii < limit) {
                int i = iEstimateConsecutiveAscii + 1;
                byte b = buffer.get(iEstimateConsecutiveAscii);
                if (b >= 0) {
                    iEstimateConsecutiveAscii = i;
                } else if (b < -32) {
                    if (i >= limit || b < -62 || buffer.get(i) > -65) {
                        return false;
                    }
                    iEstimateConsecutiveAscii += 2;
                } else {
                    if (b >= -16) {
                        if (i >= limit - 2) {
                            return false;
                        }
                        int i2 = iEstimateConsecutiveAscii + 2;
                        byte b2 = buffer.get(i);
                        if (b2 <= -65 && (((b << Ascii.FS) + (b2 + 112)) >> 30) == 0) {
                            int i3 = iEstimateConsecutiveAscii + 3;
                            if (buffer.get(i2) <= -65) {
                                iEstimateConsecutiveAscii += 4;
                                if (buffer.get(i3) > -65) {
                                }
                            }
                        }
                        return false;
                    }
                    if (i >= limit - 1) {
                        return false;
                    }
                    int i4 = iEstimateConsecutiveAscii + 2;
                    byte b3 = buffer.get(i);
                    if (b3 > -65 || ((b == -32 && b3 < -96) || ((b == -19 && b3 >= -96) || buffer.get(i4) > -65))) {
                        return false;
                    }
                    iEstimateConsecutiveAscii += 3;
                }
            }
            return true;
        }

        final String decodeUtf8(ByteBuffer buffer, int index, int size) throws InvalidProtocolBufferException {
            if (buffer.hasArray()) {
                return decodeUtf8(buffer.array(), buffer.arrayOffset() + index, size);
            }
            if (buffer.isDirect()) {
                return decodeUtf8Direct(buffer, index, size);
            }
            return decodeUtf8Default(buffer, index, size);
        }

        final String decodeUtf8Default(ByteBuffer buffer, int index, int size) throws InvalidProtocolBufferException {
            if ((index | size | ((buffer.limit() - index) - size)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(buffer.limit()), Integer.valueOf(index), Integer.valueOf(size)));
            }
            int i = index + size;
            char[] cArr = new char[size];
            int i2 = 0;
            while (index < i) {
                byte b = buffer.get(index);
                if (!DecodeUtil.isOneByte(b)) {
                    break;
                }
                index++;
                DecodeUtil.handleOneByte(b, cArr, i2);
                i2++;
            }
            int i3 = i2;
            while (index < i) {
                int i4 = index + 1;
                byte b2 = buffer.get(index);
                if (DecodeUtil.isOneByte(b2)) {
                    int i5 = i3 + 1;
                    DecodeUtil.handleOneByte(b2, cArr, i3);
                    while (i4 < i) {
                        byte b3 = buffer.get(i4);
                        if (!DecodeUtil.isOneByte(b3)) {
                            break;
                        }
                        i4++;
                        DecodeUtil.handleOneByte(b3, cArr, i5);
                        i5++;
                    }
                    i3 = i5;
                    index = i4;
                } else if (DecodeUtil.isTwoBytes(b2)) {
                    if (i4 >= i) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    index += 2;
                    DecodeUtil.handleTwoBytes(b2, buffer.get(i4), cArr, i3);
                    i3++;
                } else if (DecodeUtil.isThreeBytes(b2)) {
                    if (i4 >= i - 1) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    int i6 = index + 2;
                    index += 3;
                    DecodeUtil.handleThreeBytes(b2, buffer.get(i4), buffer.get(i6), cArr, i3);
                    i3++;
                } else {
                    if (i4 >= i - 2) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    byte b4 = buffer.get(i4);
                    int i7 = index + 3;
                    byte b5 = buffer.get(index + 2);
                    index += 4;
                    DecodeUtil.handleFourBytes(b2, b4, b5, buffer.get(i7), cArr, i3);
                    i3 += 2;
                }
            }
            return new String(cArr, 0, i3);
        }

        protected int encodeUtf8Naive(String in, byte[] out, int offset, int length) {
            byte[] bytes = in.getBytes(StandardCharsets.UTF_8);
            if (bytes.length - offset > length) {
                throw new ArrayIndexOutOfBoundsException("Not enough space in output buffer to encode UTF-8 string");
            }
            System.arraycopy(bytes, 0, out, offset, bytes.length);
            return offset + bytes.length;
        }

        protected void encodeUtf8Naive(String in, ByteBuffer out) {
            try {
                out.put(in.getBytes(StandardCharsets.UTF_8));
            } catch (BufferOverflowException unused) {
                throw new ArrayIndexOutOfBoundsException("Not enough space in output buffer to encode UTF-8 string");
            }
        }

        final void encodeUtf8(String in, ByteBuffer out) {
            if (out.hasArray()) {
                int iArrayOffset = out.arrayOffset();
                Java8Compatibility.position(out, Utf8.encode(in, out.array(), out.position() + iArrayOffset, out.remaining()) - iArrayOffset);
            } else {
                encodeUtf8Internal(in, out);
            }
        }
    }

    static final class SafeProcessor extends Processor {
        SafeProcessor() {
        }

        @Override // com.google.protobuf.Utf8.Processor
        public boolean isValidUtf8(byte[] bytes, int index, int limit) {
            while (index < limit && bytes[index] >= 0) {
                index++;
            }
            if (index >= limit) {
                return true;
            }
            return isValidUtf8NonAscii(bytes, index, limit);
        }

        @Override // com.google.protobuf.Utf8.Processor
        String decodeUtf8(byte[] bytes, int index, int size) throws InvalidProtocolBufferException {
            if ((index | size | ((bytes.length - index) - size)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bytes.length), Integer.valueOf(index), Integer.valueOf(size)));
            }
            int i = index + size;
            char[] cArr = new char[size];
            int i2 = 0;
            while (index < i) {
                byte b = bytes[index];
                if (!DecodeUtil.isOneByte(b)) {
                    break;
                }
                index++;
                DecodeUtil.handleOneByte(b, cArr, i2);
                i2++;
            }
            int i3 = i2;
            while (index < i) {
                int i4 = index + 1;
                byte b2 = bytes[index];
                if (DecodeUtil.isOneByte(b2)) {
                    int i5 = i3 + 1;
                    DecodeUtil.handleOneByte(b2, cArr, i3);
                    while (i4 < i) {
                        byte b3 = bytes[i4];
                        if (!DecodeUtil.isOneByte(b3)) {
                            break;
                        }
                        i4++;
                        DecodeUtil.handleOneByte(b3, cArr, i5);
                        i5++;
                    }
                    i3 = i5;
                    index = i4;
                } else if (DecodeUtil.isTwoBytes(b2)) {
                    if (i4 >= i) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    index += 2;
                    DecodeUtil.handleTwoBytes(b2, bytes[i4], cArr, i3);
                    i3++;
                } else if (DecodeUtil.isThreeBytes(b2)) {
                    if (i4 >= i - 1) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    int i6 = index + 2;
                    index += 3;
                    DecodeUtil.handleThreeBytes(b2, bytes[i4], bytes[i6], cArr, i3);
                    i3++;
                } else {
                    if (i4 >= i - 2) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    byte b4 = bytes[i4];
                    int i7 = index + 3;
                    byte b5 = bytes[index + 2];
                    index += 4;
                    DecodeUtil.handleFourBytes(b2, b4, b5, bytes[i7], cArr, i3);
                    i3 += 2;
                }
            }
            return new String(cArr, 0, i3);
        }

        @Override // com.google.protobuf.Utf8.Processor
        String decodeUtf8Direct(ByteBuffer buffer, int index, int size) throws InvalidProtocolBufferException {
            return decodeUtf8Default(buffer, index, size);
        }

        @Override // com.google.protobuf.Utf8.Processor
        int encodeUtf8(String in, byte[] out, int offset, int length) {
            int i;
            int i2;
            char cCharAt;
            int length2 = in.length();
            int i3 = offset + length;
            int i4 = 0;
            while (i4 < length2 && (i2 = i4 + offset) < i3 && (cCharAt = in.charAt(i4)) < 128) {
                out[i2] = (byte) cCharAt;
                i4++;
            }
            if (i4 == length2) {
                return offset + length2;
            }
            int i5 = offset + i4;
            while (i4 < length2) {
                char cCharAt2 = in.charAt(i4);
                if (cCharAt2 < 128 && i5 < i3) {
                    out[i5] = (byte) cCharAt2;
                    i5++;
                } else if (cCharAt2 < 2048 && i5 <= i3 - 2) {
                    int i6 = i5 + 1;
                    out[i5] = (byte) ((cCharAt2 >>> 6) | 960);
                    i5 += 2;
                    out[i6] = (byte) ((cCharAt2 & '?') | 128);
                } else {
                    if ((cCharAt2 >= 55296 && 57343 >= cCharAt2) || i5 > i3 - 3) {
                        if (i5 <= i3 - 4) {
                            i4++;
                            if (i4 != in.length()) {
                                char cCharAt3 = in.charAt(i4);
                                if (Character.isSurrogatePair(cCharAt2, cCharAt3)) {
                                    int codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                                    out[i5] = (byte) ((codePoint >>> 18) | 240);
                                    out[i5 + 1] = (byte) (((codePoint >>> 12) & 63) | 128);
                                    int i7 = i5 + 3;
                                    out[i5 + 2] = (byte) (((codePoint >>> 6) & 63) | 128);
                                    i5 += 4;
                                    out[i7] = (byte) ((codePoint & 63) | 128);
                                }
                            }
                            return encodeUtf8Naive(in, out, offset, length);
                        }
                        if (55296 <= cCharAt2 && cCharAt2 <= 57343 && ((i = i4 + 1) == in.length() || !Character.isSurrogatePair(cCharAt2, in.charAt(i)))) {
                            return encodeUtf8Naive(in, out, offset, length);
                        }
                        throw new ArrayIndexOutOfBoundsException("Not enough space in output buffer to encode UTF-8 string");
                    }
                    out[i5] = (byte) ((cCharAt2 >>> '\f') | 480);
                    int i8 = i5 + 2;
                    out[i5 + 1] = (byte) (((cCharAt2 >>> 6) & 63) | 128);
                    i5 += 3;
                    out[i8] = (byte) ((cCharAt2 & '?') | 128);
                }
                i4++;
            }
            return i5;
        }

        @Override // com.google.protobuf.Utf8.Processor
        protected void encodeUtf8Internal(String in, ByteBuffer out) {
            int length = in.length();
            int iPosition = out.position();
            int i = 0;
            while (i < length) {
                try {
                    char cCharAt = in.charAt(i);
                    if (cCharAt >= 128) {
                        break;
                    }
                    out.put(iPosition + i, (byte) cCharAt);
                    i++;
                } catch (IndexOutOfBoundsException unused) {
                    throw new ArrayIndexOutOfBoundsException("Not enough space in output buffer to encode UTF-8 string");
                }
            }
            if (i == length) {
                Java8Compatibility.position(out, iPosition + i);
                return;
            }
            int i2 = iPosition + i;
            while (i < length) {
                char cCharAt2 = in.charAt(i);
                if (cCharAt2 < 128) {
                    out.put(i2, (byte) cCharAt2);
                } else if (cCharAt2 < 2048) {
                    int i3 = i2 + 1;
                    out.put(i2, (byte) ((cCharAt2 >>> 6) | 192));
                    out.put(i3, (byte) ((cCharAt2 & '?') | 128));
                    i2 = i3;
                } else if (cCharAt2 < 55296 || 57343 < cCharAt2) {
                    int i4 = i2 + 1;
                    out.put(i2, (byte) ((cCharAt2 >>> '\f') | 224));
                    i2 += 2;
                    out.put(i4, (byte) (((cCharAt2 >>> 6) & 63) | 128));
                    out.put(i2, (byte) ((cCharAt2 & '?') | 128));
                } else {
                    i++;
                    if (i != length) {
                        char cCharAt3 = in.charAt(i);
                        if (Character.isSurrogatePair(cCharAt2, cCharAt3)) {
                            int codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                            out.put(i2, (byte) ((codePoint >>> 18) | 240));
                            int i5 = i2 + 2;
                            out.put(i2 + 1, (byte) (((codePoint >>> 12) & 63) | 128));
                            i2 += 3;
                            out.put(i5, (byte) (((codePoint >>> 6) & 63) | 128));
                            out.put(i2, (byte) ((codePoint & 63) | 128));
                        }
                    }
                    encodeUtf8Naive(in, out);
                    return;
                }
                i++;
                i2++;
            }
            Java8Compatibility.position(out, i2);
        }

        private static boolean isValidUtf8NonAscii(byte[] bytes, int index, int limit) {
            while (index < limit) {
                int i = index + 1;
                byte b = bytes[index];
                if (b < 0) {
                    if (b < -32) {
                        if (i < limit && b >= -62) {
                            index += 2;
                            if (bytes[i] > -65) {
                            }
                        }
                        return false;
                    }
                    if (b >= -16) {
                        if (i >= limit - 2) {
                            return false;
                        }
                        int i2 = index + 2;
                        byte b2 = bytes[i];
                        if (b2 <= -65 && (((b << Ascii.FS) + (b2 + 112)) >> 30) == 0) {
                            int i3 = index + 3;
                            if (bytes[i2] <= -65) {
                                index += 4;
                                if (bytes[i3] > -65) {
                                }
                            }
                        }
                        return false;
                    }
                    if (i >= limit - 1) {
                        return false;
                    }
                    int i4 = index + 2;
                    byte b3 = bytes[i];
                    if (b3 <= -65 && ((b != -32 || b3 >= -96) && (b != -19 || b3 < -96))) {
                        index += 3;
                        if (bytes[i4] > -65) {
                        }
                    }
                    return false;
                }
                index = i;
            }
            return true;
        }
    }

    static final class UnsafeProcessor extends Processor {
        UnsafeProcessor() {
        }

        static boolean isAvailable() {
            return UnsafeUtil.hasUnsafeArrayOperations() && UnsafeUtil.hasUnsafeByteBufferOperations();
        }

        @Override // com.google.protobuf.Utf8.Processor
        public boolean isValidUtf8(byte[] bytes, final int index, final int limit) {
            if ((index | limit | (bytes.length - limit)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("Array length=%d, index=%d, limit=%d", Integer.valueOf(bytes.length), Integer.valueOf(index), Integer.valueOf(limit)));
            }
            long j = index;
            int i = limit - index;
            int iUnsafeEstimateConsecutiveAscii = unsafeEstimateConsecutiveAscii(bytes, j, i);
            int i2 = i - iUnsafeEstimateConsecutiveAscii;
            long j2 = j + ((long) iUnsafeEstimateConsecutiveAscii);
            while (true) {
                byte b = 0;
                while (i2 > 0) {
                    long j3 = j2 + 1;
                    b = UnsafeUtil.getByte(bytes, j2);
                    if (b < 0) {
                        j2 = j3;
                        break;
                    }
                    i2--;
                    j2 = j3;
                }
                if (i2 == 0) {
                    return true;
                }
                int i3 = i2 - 1;
                if (b < -32) {
                    if (i3 == 0) {
                        return false;
                    }
                    i2 -= 2;
                    if (b >= -62) {
                        long j4 = 1 + j2;
                        if (UnsafeUtil.getByte(bytes, j2) <= -65) {
                            j2 = j4;
                        }
                    }
                    return false;
                }
                if (b >= -16) {
                    if (i3 < 3) {
                        return false;
                    }
                    i2 -= 4;
                    long j5 = 1 + j2;
                    byte b2 = UnsafeUtil.getByte(bytes, j2);
                    if (b2 <= -65 && (((b << Ascii.FS) + (b2 + 112)) >> 30) == 0) {
                        long j6 = j2 + 2;
                        if (UnsafeUtil.getByte(bytes, j5) <= -65) {
                            j2 += 3;
                            if (UnsafeUtil.getByte(bytes, j6) > -65) {
                            }
                        }
                    }
                    return false;
                }
                if (i3 < 2) {
                    return false;
                }
                i2 -= 3;
                long j7 = 1 + j2;
                byte b3 = UnsafeUtil.getByte(bytes, j2);
                if (b3 <= -65 && ((b != -32 || b3 >= -96) && (b != -19 || b3 < -96))) {
                    j2 += 2;
                    if (UnsafeUtil.getByte(bytes, j7) > -65) {
                    }
                }
                return false;
            }
        }

        @Override // com.google.protobuf.Utf8.Processor
        protected boolean isValidUtf8BufferDirect(ByteBuffer buffer, final int index, final int limit) {
            if (!buffer.isDirect()) {
                throw new IllegalArgumentException("ByteBuffer must be direct");
            }
            if ((index | limit | (buffer.limit() - limit)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(buffer.limit()), Integer.valueOf(index), Integer.valueOf(limit)));
            }
            long jAddressOffset = UnsafeUtil.addressOffset(buffer) + ((long) index);
            int i = limit - index;
            int iUnsafeEstimateConsecutiveAscii = unsafeEstimateConsecutiveAscii(jAddressOffset, i);
            long j = jAddressOffset + ((long) iUnsafeEstimateConsecutiveAscii);
            int i2 = i - iUnsafeEstimateConsecutiveAscii;
            while (true) {
                byte b = 0;
                while (i2 > 0) {
                    long j2 = j + 1;
                    b = UnsafeUtil.getByte(j);
                    if (b < 0) {
                        j = j2;
                        break;
                    }
                    i2--;
                    j = j2;
                }
                if (i2 == 0) {
                    return true;
                }
                int i3 = i2 - 1;
                if (b < -32) {
                    if (i3 == 0) {
                        return false;
                    }
                    i2 -= 2;
                    if (b >= -62) {
                        long j3 = 1 + j;
                        if (UnsafeUtil.getByte(j) <= -65) {
                            j = j3;
                        }
                    }
                    return false;
                }
                if (b >= -16) {
                    if (i3 < 3) {
                        return false;
                    }
                    i2 -= 4;
                    long j4 = 1 + j;
                    byte b2 = UnsafeUtil.getByte(j);
                    if (b2 <= -65 && (((b << Ascii.FS) + (b2 + 112)) >> 30) == 0) {
                        long j5 = 2 + j;
                        if (UnsafeUtil.getByte(j4) <= -65) {
                            j += 3;
                            if (UnsafeUtil.getByte(j5) > -65) {
                            }
                        }
                    }
                    return false;
                }
                if (i3 < 2) {
                    return false;
                }
                i2 -= 3;
                long j6 = 1 + j;
                byte b3 = UnsafeUtil.getByte(j);
                if (b3 <= -65 && ((b != -32 || b3 >= -96) && (b != -19 || b3 < -96))) {
                    j += 2;
                    if (UnsafeUtil.getByte(j6) > -65) {
                    }
                }
                return false;
            }
        }

        @Override // com.google.protobuf.Utf8.Processor
        String decodeUtf8(byte[] bytes, int index, int size) throws InvalidProtocolBufferException {
            String str = new String(bytes, index, size, StandardCharsets.UTF_8);
            if (str.indexOf(65533) >= 0 && !Arrays.equals(str.getBytes(StandardCharsets.UTF_8), Arrays.copyOfRange(bytes, index, size + index))) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            return str;
        }

        @Override // com.google.protobuf.Utf8.Processor
        String decodeUtf8Direct(ByteBuffer buffer, int index, int size) throws InvalidProtocolBufferException {
            int i;
            if ((index | size | ((buffer.limit() - index) - size)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(buffer.limit()), Integer.valueOf(index), Integer.valueOf(size)));
            }
            long jAddressOffset = UnsafeUtil.addressOffset(buffer) + ((long) index);
            long j = ((long) size) + jAddressOffset;
            char[] cArr = new char[size];
            int i2 = 0;
            while (jAddressOffset < j) {
                byte b = UnsafeUtil.getByte(jAddressOffset);
                if (!DecodeUtil.isOneByte(b)) {
                    break;
                }
                jAddressOffset++;
                DecodeUtil.handleOneByte(b, cArr, i2);
                i2++;
            }
            int i3 = i2;
            while (jAddressOffset < j) {
                long j2 = jAddressOffset + 1;
                byte b2 = UnsafeUtil.getByte(jAddressOffset);
                if (DecodeUtil.isOneByte(b2)) {
                    i = i3 + 1;
                    DecodeUtil.handleOneByte(b2, cArr, i3);
                    while (j2 < j) {
                        byte b3 = UnsafeUtil.getByte(j2);
                        if (!DecodeUtil.isOneByte(b3)) {
                            break;
                        }
                        j2++;
                        DecodeUtil.handleOneByte(b3, cArr, i);
                        i++;
                    }
                    jAddressOffset = j2;
                } else if (DecodeUtil.isTwoBytes(b2)) {
                    if (j2 >= j) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    jAddressOffset += 2;
                    DecodeUtil.handleTwoBytes(b2, UnsafeUtil.getByte(j2), cArr, i3);
                    i3++;
                } else if (DecodeUtil.isThreeBytes(b2)) {
                    if (j2 >= j - 1) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    long j3 = 2 + jAddressOffset;
                    jAddressOffset += 3;
                    i = i3 + 1;
                    DecodeUtil.handleThreeBytes(b2, UnsafeUtil.getByte(j2), UnsafeUtil.getByte(j3), cArr, i3);
                } else {
                    if (j2 >= j - 2) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    byte b4 = UnsafeUtil.getByte(j2);
                    long j4 = jAddressOffset + 3;
                    byte b5 = UnsafeUtil.getByte(2 + jAddressOffset);
                    jAddressOffset += 4;
                    DecodeUtil.handleFourBytes(b2, b4, b5, UnsafeUtil.getByte(j4), cArr, i3);
                    i3 += 2;
                }
                i3 = i;
            }
            return new String(cArr, 0, i3);
        }

        @Override // com.google.protobuf.Utf8.Processor
        int encodeUtf8(String in, byte[] out, int offset, int length) {
            return encodeUtf8Naive(in, out, offset, length);
        }

        @Override // com.google.protobuf.Utf8.Processor
        protected void encodeUtf8Internal(String in, ByteBuffer out) {
            encodeUtf8Naive(in, out);
        }

        private static int unsafeEstimateConsecutiveAscii(byte[] bytes, long offset, final int maxChars) {
            int i = 0;
            if (maxChars < 16) {
                return 0;
            }
            int i2 = 8 - (((int) offset) & 7);
            while (i < i2) {
                long j = 1 + offset;
                if (UnsafeUtil.getByte(bytes, offset) < 0) {
                    return i;
                }
                i++;
                offset = j;
            }
            while (true) {
                int i3 = i + 8;
                if (i3 > maxChars || (UnsafeUtil.getLong((Object) bytes, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + offset) & (-9187201950435737472L)) != 0) {
                    break;
                }
                offset += 8;
                i = i3;
            }
            while (i < maxChars) {
                long j2 = offset + 1;
                if (UnsafeUtil.getByte(bytes, offset) < 0) {
                    return i;
                }
                i++;
                offset = j2;
            }
            return maxChars;
        }

        private static int unsafeEstimateConsecutiveAscii(long address, final int maxChars) {
            if (maxChars < 16) {
                return 0;
            }
            int i = (int) ((-address) & 7);
            int i2 = i;
            while (i2 > 0) {
                long j = 1 + address;
                if (UnsafeUtil.getByte(address) < 0) {
                    return i - i2;
                }
                i2--;
                address = j;
            }
            int i3 = maxChars - i;
            while (i3 >= 8 && (UnsafeUtil.getLong(address) & (-9187201950435737472L)) == 0) {
                address += 8;
                i3 -= 8;
            }
            return maxChars - i3;
        }
    }

    private static class DecodeUtil {
        private static char highSurrogate(int codePoint) {
            return (char) ((codePoint >>> 10) + 55232);
        }

        private static boolean isNotTrailingByte(byte b) {
            return b > -65;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isOneByte(byte b) {
            return b >= 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isThreeBytes(byte b) {
            return b < -16;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isTwoBytes(byte b) {
            return b < -32;
        }

        private static char lowSurrogate(int codePoint) {
            return (char) ((codePoint & AnalyticsListener.EVENT_DRM_KEYS_LOADED) + 56320);
        }

        private static int trailingByteValue(byte b) {
            return b & 63;
        }

        private DecodeUtil() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void handleOneByte(byte byte1, char[] resultArr, int resultPos) {
            resultArr[resultPos] = (char) byte1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void handleTwoBytes(byte byte1, byte byte2, char[] resultArr, int resultPos) throws InvalidProtocolBufferException {
            if (byte1 < -62 || isNotTrailingByte(byte2)) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            resultArr[resultPos] = (char) (((byte1 & Ascii.US) << 6) | trailingByteValue(byte2));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void handleThreeBytes(byte byte1, byte byte2, byte byte3, char[] resultArr, int resultPos) throws InvalidProtocolBufferException {
            if (isNotTrailingByte(byte2) || ((byte1 == -32 && byte2 < -96) || ((byte1 == -19 && byte2 >= -96) || isNotTrailingByte(byte3)))) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            resultArr[resultPos] = (char) (((byte1 & Ascii.SI) << 12) | (trailingByteValue(byte2) << 6) | trailingByteValue(byte3));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void handleFourBytes(byte byte1, byte byte2, byte byte3, byte byte4, char[] resultArr, int resultPos) throws InvalidProtocolBufferException {
            if (isNotTrailingByte(byte2) || (((byte1 << Ascii.FS) + (byte2 + 112)) >> 30) != 0 || isNotTrailingByte(byte3) || isNotTrailingByte(byte4)) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            int iTrailingByteValue = ((byte1 & 7) << 18) | (trailingByteValue(byte2) << 12) | (trailingByteValue(byte3) << 6) | trailingByteValue(byte4);
            resultArr[resultPos] = highSurrogate(iTrailingByteValue);
            resultArr[resultPos + 1] = lowSurrogate(iTrailingByteValue);
        }
    }

    private Utf8() {
    }
}
