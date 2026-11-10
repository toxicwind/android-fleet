package com.google.protobuf;

import b.a.a.a.a;
import com.google.common.base.Ascii;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public final class Utf8 {
    public static final long ASCII_MASK_LONG = -9187201950435737472L;
    public static final int COMPLETE = 0;
    public static final int MALFORMED = -1;
    public static final int MAX_BYTES_PER_CHAR = 3;
    public static final int UNSAFE_COUNT_ASCII_THRESHOLD = 16;
    public static final Processor processor;

    private static class DecodeUtil {
        public static /* synthetic */ void access$500(byte b2, char[] cArr, int i) {
            cArr[i] = (char) b2;
        }

        public static void handleFourBytes(byte b2, byte b3, byte b4, byte b5, char[] cArr, int i) throws InvalidProtocolBufferException {
            if (!isNotTrailingByte(b3)) {
                if ((((b3 + 112) + (b2 << Ascii.FS)) >> 30) == 0 && !isNotTrailingByte(b4) && !isNotTrailingByte(b5)) {
                    int i2 = ((b2 & 7) << 18) | ((b3 & 63) << 12) | ((b4 & 63) << 6) | (b5 & 63);
                    cArr[i] = highSurrogate(i2);
                    cArr[i + 1] = lowSurrogate(i2);
                    return;
                }
            }
            throw InvalidProtocolBufferException.invalidUtf8();
        }

        public static void handleOneByte(byte b2, char[] cArr, int i) {
            cArr[i] = (char) b2;
        }

        public static void handleThreeBytes(byte b2, byte b3, byte b4, char[] cArr, int i) throws InvalidProtocolBufferException {
            if (isNotTrailingByte(b3) || ((b2 == -32 && b3 < -96) || ((b2 == -19 && b3 >= -96) || isNotTrailingByte(b4)))) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            cArr[i] = (char) (((b2 & Ascii.SI) << 12) | ((b3 & 63) << 6) | (b4 & 63));
        }

        public static void handleTwoBytes(byte b2, byte b3, char[] cArr, int i) throws InvalidProtocolBufferException {
            if (b2 < -62 || isNotTrailingByte(b3)) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            cArr[i] = (char) (((b2 & Ascii.US) << 6) | (b3 & 63));
        }

        public static char highSurrogate(int i) {
            return (char) ((i >>> 10) + 55232);
        }

        public static boolean isNotTrailingByte(byte b2) {
            return b2 > -65;
        }

        public static boolean isOneByte(byte b2) {
            return b2 >= 0;
        }

        public static boolean isThreeBytes(byte b2) {
            return b2 < -16;
        }

        public static boolean isTwoBytes(byte b2) {
            return b2 < -32;
        }

        public static char lowSurrogate(int i) {
            return (char) ((i & 1023) + 56320);
        }

        public static int trailingByteValue(byte b2) {
            return b2 & 63;
        }
    }

    static abstract class Processor {
        public static int partialIsValidUtf8(ByteBuffer byteBuffer, int i, int i2) {
            int iEstimateConsecutiveAscii = i + Utf8.estimateConsecutiveAscii(byteBuffer, i, i2);
            while (iEstimateConsecutiveAscii < i2) {
                int i3 = iEstimateConsecutiveAscii + 1;
                byte b2 = byteBuffer.get(iEstimateConsecutiveAscii);
                if (b2 < 0) {
                    if (b2 < -32) {
                        if (i3 >= i2) {
                            return b2;
                        }
                        if (b2 < -62 || byteBuffer.get(i3) > -65) {
                            return -1;
                        }
                        i3++;
                    } else {
                        if (b2 >= -16) {
                            if (i3 >= i2 - 2) {
                                return Utf8.incompleteStateFor(byteBuffer, b2, i3, i2 - i3);
                            }
                            int i4 = i3 + 1;
                            byte b3 = byteBuffer.get(i3);
                            if (b3 <= -65) {
                                if ((((b3 + 112) + (b2 << Ascii.FS)) >> 30) == 0) {
                                    int i5 = i4 + 1;
                                    if (byteBuffer.get(i4) <= -65) {
                                        i3 = i5 + 1;
                                        if (byteBuffer.get(i5) > -65) {
                                        }
                                    }
                                }
                            }
                            return -1;
                        }
                        if (i3 >= i2 - 1) {
                            return Utf8.incompleteStateFor(byteBuffer, b2, i3, i2 - i3);
                        }
                        int i6 = i3 + 1;
                        byte b4 = byteBuffer.get(i3);
                        if (b4 > -65 || ((b2 == -32 && b4 < -96) || ((b2 == -19 && b4 >= -96) || byteBuffer.get(i6) > -65))) {
                            return -1;
                        }
                        iEstimateConsecutiveAscii = i6 + 1;
                    }
                }
                iEstimateConsecutiveAscii = i3;
            }
            return 0;
        }

        public final String decodeUtf8(ByteBuffer byteBuffer, int i, int i2) {
            if (byteBuffer.hasArray()) {
                return decodeUtf8(byteBuffer.array(), byteBuffer.arrayOffset() + i, i2);
            }
            return byteBuffer.isDirect() ? decodeUtf8Direct(byteBuffer, i, i2) : decodeUtf8Default(byteBuffer, i, i2);
        }

        public abstract String decodeUtf8(byte[] bArr, int i, int i2);

        public final String decodeUtf8Default(ByteBuffer byteBuffer, int i, int i2) throws InvalidProtocolBufferException {
            if ((i | i2 | ((byteBuffer.limit() - i) - i2)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i), Integer.valueOf(i2)));
            }
            int i3 = i + i2;
            char[] cArr = new char[i2];
            int i4 = 0;
            while (i < i3) {
                byte b2 = byteBuffer.get(i);
                if (!DecodeUtil.isOneByte(b2)) {
                    break;
                }
                i++;
                cArr[i4] = (char) b2;
                i4++;
            }
            int i5 = i4;
            while (i < i3) {
                int i6 = i + 1;
                byte b3 = byteBuffer.get(i);
                if (DecodeUtil.isOneByte(b3)) {
                    int i7 = i5 + 1;
                    cArr[i5] = (char) b3;
                    while (i6 < i3) {
                        byte b4 = byteBuffer.get(i6);
                        if (!DecodeUtil.isOneByte(b4)) {
                            break;
                        }
                        i6++;
                        cArr[i7] = (char) b4;
                        i7++;
                    }
                    i = i6;
                    i5 = i7;
                } else if (DecodeUtil.isTwoBytes(b3)) {
                    if (i6 >= i3) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    DecodeUtil.handleTwoBytes(b3, byteBuffer.get(i6), cArr, i5);
                    i = i6 + 1;
                    i5++;
                } else if (DecodeUtil.isThreeBytes(b3)) {
                    if (i6 >= i3 - 1) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    int i8 = i6 + 1;
                    DecodeUtil.handleThreeBytes(b3, byteBuffer.get(i6), byteBuffer.get(i8), cArr, i5);
                    i = i8 + 1;
                    i5++;
                } else {
                    if (i6 >= i3 - 2) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    int i9 = i6 + 1;
                    byte b5 = byteBuffer.get(i6);
                    int i10 = i9 + 1;
                    DecodeUtil.handleFourBytes(b3, b5, byteBuffer.get(i9), byteBuffer.get(i10), cArr, i5);
                    i = i10 + 1;
                    i5 = i5 + 1 + 1;
                }
            }
            return new String(cArr, 0, i5);
        }

        public abstract String decodeUtf8Direct(ByteBuffer byteBuffer, int i, int i2);

        public abstract int encodeUtf8(CharSequence charSequence, byte[] bArr, int i, int i2);

        public final void encodeUtf8(CharSequence charSequence, ByteBuffer byteBuffer) {
            if (byteBuffer.hasArray()) {
                int iArrayOffset = byteBuffer.arrayOffset();
                byteBuffer.position(Utf8.encode(charSequence, byteBuffer.array(), byteBuffer.position() + iArrayOffset, byteBuffer.remaining()) - iArrayOffset);
            } else if (byteBuffer.isDirect()) {
                encodeUtf8Direct(charSequence, byteBuffer);
            } else {
                encodeUtf8Default(charSequence, byteBuffer);
            }
        }

        public final void encodeUtf8Default(CharSequence charSequence, ByteBuffer byteBuffer) {
            int length = charSequence.length();
            int iPosition = byteBuffer.position();
            int i = 0;
            while (i < length) {
                try {
                    char cCharAt = charSequence.charAt(i);
                    if (cCharAt >= 128) {
                        break;
                    }
                    byteBuffer.put(iPosition + i, (byte) cCharAt);
                    i++;
                } catch (IndexOutOfBoundsException unused) {
                }
            }
            if (i == length) {
                byteBuffer.position(iPosition + i);
                return;
            }
            iPosition += i;
            while (i < length) {
                char cCharAt2 = charSequence.charAt(i);
                if (cCharAt2 < 128) {
                    byteBuffer.put(iPosition, (byte) cCharAt2);
                } else if (cCharAt2 < 2048) {
                    int i2 = iPosition + 1;
                    try {
                        byteBuffer.put(iPosition, (byte) ((cCharAt2 >>> 6) | 192));
                        byteBuffer.put(i2, (byte) ((cCharAt2 & '?') | 128));
                        iPosition = i2;
                    } catch (IndexOutOfBoundsException unused2) {
                        iPosition = i2;
                    }
                } else {
                    if (cCharAt2 >= 55296 && 57343 >= cCharAt2) {
                        int i3 = i + 1;
                        if (i3 != length) {
                            try {
                                char cCharAt3 = charSequence.charAt(i3);
                                if (Character.isSurrogatePair(cCharAt2, cCharAt3)) {
                                    int codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                                    int i4 = iPosition + 1;
                                    try {
                                        byteBuffer.put(iPosition, (byte) ((codePoint >>> 18) | 240));
                                        iPosition = i4 + 1;
                                        byteBuffer.put(i4, (byte) (((codePoint >>> 12) & 63) | 128));
                                        i4 = iPosition + 1;
                                        byteBuffer.put(iPosition, (byte) (((codePoint >>> 6) & 63) | 128));
                                        byteBuffer.put(i4, (byte) ((codePoint & 63) | 128));
                                        iPosition = i4;
                                        i = i3;
                                    } catch (IndexOutOfBoundsException unused3) {
                                        iPosition = i4;
                                    }
                                } else {
                                    i = i3;
                                }
                            } catch (IndexOutOfBoundsException unused4) {
                            }
                            i = i3;
                            int iMax = Math.max(i, (iPosition - byteBuffer.position()) + 1) + byteBuffer.position();
                            StringBuilder sbA = a.a("Failed writing ");
                            sbA.append(charSequence.charAt(i));
                            sbA.append(" at index ");
                            sbA.append(iMax);
                            throw new ArrayIndexOutOfBoundsException(sbA.toString());
                        }
                        throw new UnpairedSurrogateException(i, length);
                    }
                    int i5 = iPosition + 1;
                    byteBuffer.put(iPosition, (byte) ((cCharAt2 >>> '\f') | 224));
                    iPosition = i5 + 1;
                    byteBuffer.put(i5, (byte) (((cCharAt2 >>> 6) & 63) | 128));
                    byteBuffer.put(iPosition, (byte) ((cCharAt2 & '?') | 128));
                }
                i++;
                iPosition++;
            }
            byteBuffer.position(iPosition);
        }

        public abstract void encodeUtf8Direct(CharSequence charSequence, ByteBuffer byteBuffer);

        public final boolean isValidUtf8(byte[] bArr, int i, int i2) {
            return partialIsValidUtf8(0, bArr, i, i2) == 0;
        }

        public abstract int partialIsValidUtf8(int i, byte[] bArr, int i2, int i3);

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0017, code lost:
        
            if (r8.get(r9) > (-65)) goto L11;
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x0048, code lost:
        
            if (r8.get(r9) > (-65)) goto L30;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x0086, code lost:
        
            if (r8.get(r7) > (-65)) goto L50;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final int partialIsValidUtf8Default(int r7, java.nio.ByteBuffer r8, int r9, int r10) {
            /*
                r6 = this;
                if (r7 == 0) goto L89
                if (r9 < r10) goto L5
                return r7
            L5:
                byte r0 = (byte) r7
                r1 = -32
                r2 = -1
                r3 = -65
                if (r0 >= r1) goto L1a
                r7 = -62
                if (r0 < r7) goto L19
                int r7 = r9 + 1
                byte r9 = r8.get(r9)
                if (r9 <= r3) goto L8a
            L19:
                return r2
            L1a:
                r4 = -16
                if (r0 >= r4) goto L4b
                int r7 = r7 >> 8
                int r7 = ~r7
                byte r7 = (byte) r7
                if (r7 != 0) goto L34
                int r7 = r9 + 1
                byte r9 = r8.get(r9)
                if (r7 < r10) goto L31
                int r7 = com.google.protobuf.Utf8.incompleteStateFor(r0, r9)
                return r7
            L31:
                r5 = r9
                r9 = r7
                r7 = r5
            L34:
                if (r7 > r3) goto L4a
                r4 = -96
                if (r0 != r1) goto L3c
                if (r7 < r4) goto L4a
            L3c:
                r1 = -19
                if (r0 != r1) goto L42
                if (r7 >= r4) goto L4a
            L42:
                int r7 = r9 + 1
                byte r9 = r8.get(r9)
                if (r9 <= r3) goto L8a
            L4a:
                return r2
            L4b:
                int r1 = r7 >> 8
                int r1 = ~r1
                byte r1 = (byte) r1
                r4 = 0
                if (r1 != 0) goto L5f
                int r7 = r9 + 1
                byte r1 = r8.get(r9)
                if (r7 < r10) goto L63
                int r7 = com.google.protobuf.Utf8.incompleteStateFor(r0, r1)
                return r7
            L5f:
                int r7 = r7 >> 16
                byte r4 = (byte) r7
                r7 = r9
            L63:
                if (r4 != 0) goto L73
                int r9 = r7 + 1
                byte r4 = r8.get(r7)
                if (r9 < r10) goto L72
                int r7 = com.google.protobuf.Utf8.incompleteStateFor(r0, r1, r4)
                return r7
            L72:
                r7 = r9
            L73:
                if (r1 > r3) goto L88
                int r9 = r0 << 28
                int r1 = r1 + 112
                int r1 = r1 + r9
                int r9 = r1 >> 30
                if (r9 != 0) goto L88
                if (r4 > r3) goto L88
                int r9 = r7 + 1
                byte r7 = r8.get(r7)
                if (r7 <= r3) goto L89
            L88:
                return r2
            L89:
                r7 = r9
            L8a:
                int r7 = partialIsValidUtf8(r8, r7, r10)
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.Processor.partialIsValidUtf8Default(int, java.nio.ByteBuffer, int, int):int");
        }

        public abstract int partialIsValidUtf8Direct(int i, ByteBuffer byteBuffer, int i2, int i3);

        public final boolean isValidUtf8(ByteBuffer byteBuffer, int i, int i2) {
            return partialIsValidUtf8(0, byteBuffer, i, i2) == 0;
        }

        public final int partialIsValidUtf8(int i, ByteBuffer byteBuffer, int i2, int i3) {
            if (byteBuffer.hasArray()) {
                int iArrayOffset = byteBuffer.arrayOffset();
                return partialIsValidUtf8(i, byteBuffer.array(), i2 + iArrayOffset, iArrayOffset + i3);
            }
            if (byteBuffer.isDirect()) {
                return partialIsValidUtf8Direct(i, byteBuffer, i2, i3);
            }
            return partialIsValidUtf8Default(i, byteBuffer, i2, i3);
        }
    }

    static class UnpairedSurrogateException extends IllegalArgumentException {
        public UnpairedSurrogateException(int i, int i2) {
            super("Unpaired surrogate at index " + i + " of " + i2);
        }
    }

    static final class UnsafeProcessor extends Processor {
        public static boolean isAvailable() {
            return UnsafeUtil.HAS_UNSAFE_ARRAY_OPERATIONS && UnsafeUtil.HAS_UNSAFE_BYTEBUFFER_OPERATIONS;
        }

        public static int unsafeEstimateConsecutiveAscii(byte[] bArr, long j, int i) {
            int i2 = 0;
            if (i < 16) {
                return 0;
            }
            while (i2 < i) {
                long j2 = 1 + j;
                if (UnsafeUtil.getByte(bArr, j) < 0) {
                    return i2;
                }
                i2++;
                j = j2;
            }
            return i;
        }

        public static int unsafeIncompleteStateFor(byte[] bArr, int i, long j, int i2) {
            if (i2 == 0) {
                return Utf8.incompleteStateFor(i);
            }
            if (i2 == 1) {
                return Utf8.incompleteStateFor(i, UnsafeUtil.getByte(bArr, j));
            }
            if (i2 == 2) {
                return Utf8.incompleteStateFor(i, UnsafeUtil.getByte(bArr, j), UnsafeUtil.getByte(bArr, j + 1));
            }
            throw new AssertionError();
        }

        @Override // com.google.protobuf.Utf8.Processor
        public String decodeUtf8(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException {
            if ((i | i2 | ((bArr.length - i) - i2)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
            }
            int i3 = i + i2;
            char[] cArr = new char[i2];
            int i4 = 0;
            while (i < i3) {
                byte b2 = UnsafeUtil.getByte(bArr, i);
                if (!DecodeUtil.isOneByte(b2)) {
                    break;
                }
                i++;
                cArr[i4] = (char) b2;
                i4++;
            }
            int i5 = i4;
            while (i < i3) {
                int i6 = i + 1;
                byte b3 = UnsafeUtil.getByte(bArr, i);
                if (DecodeUtil.isOneByte(b3)) {
                    int i7 = i5 + 1;
                    cArr[i5] = (char) b3;
                    while (i6 < i3) {
                        byte b4 = UnsafeUtil.getByte(bArr, i6);
                        if (!DecodeUtil.isOneByte(b4)) {
                            break;
                        }
                        i6++;
                        cArr[i7] = (char) b4;
                        i7++;
                    }
                    i = i6;
                    i5 = i7;
                } else if (DecodeUtil.isTwoBytes(b3)) {
                    if (i6 >= i3) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    DecodeUtil.handleTwoBytes(b3, UnsafeUtil.getByte(bArr, i6), cArr, i5);
                    i = i6 + 1;
                    i5++;
                } else if (DecodeUtil.isThreeBytes(b3)) {
                    if (i6 >= i3 - 1) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    int i8 = i6 + 1;
                    DecodeUtil.handleThreeBytes(b3, UnsafeUtil.getByte(bArr, i6), UnsafeUtil.getByte(bArr, i8), cArr, i5);
                    i = i8 + 1;
                    i5++;
                } else {
                    if (i6 >= i3 - 2) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    int i9 = i6 + 1;
                    byte b5 = UnsafeUtil.getByte(bArr, i6);
                    int i10 = i9 + 1;
                    DecodeUtil.handleFourBytes(b3, b5, UnsafeUtil.getByte(bArr, i9), UnsafeUtil.getByte(bArr, i10), cArr, i5);
                    i = i10 + 1;
                    i5 = i5 + 1 + 1;
                }
            }
            return new String(cArr, 0, i5);
        }

        @Override // com.google.protobuf.Utf8.Processor
        public String decodeUtf8Direct(ByteBuffer byteBuffer, int i, int i2) throws InvalidProtocolBufferException {
            if ((i | i2 | ((byteBuffer.limit() - i) - i2)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i), Integer.valueOf(i2)));
            }
            long jAddressOffset = UnsafeUtil.addressOffset(byteBuffer) + ((long) i);
            long j = ((long) i2) + jAddressOffset;
            char[] cArr = new char[i2];
            int i3 = 0;
            while (jAddressOffset < j) {
                byte b2 = UnsafeUtil.getByte(jAddressOffset);
                if (!DecodeUtil.isOneByte(b2)) {
                    break;
                }
                jAddressOffset++;
                cArr[i3] = (char) b2;
                i3++;
            }
            while (true) {
                int i4 = i3;
                while (jAddressOffset < j) {
                    long j2 = jAddressOffset + 1;
                    byte b3 = UnsafeUtil.getByte(jAddressOffset);
                    if (DecodeUtil.isOneByte(b3)) {
                        int i5 = i4 + 1;
                        cArr[i4] = (char) b3;
                        while (j2 < j) {
                            byte b4 = UnsafeUtil.getByte(j2);
                            if (!DecodeUtil.isOneByte(b4)) {
                                break;
                            }
                            j2++;
                            cArr[i5] = (char) b4;
                            i5++;
                        }
                        i4 = i5;
                        jAddressOffset = j2;
                    } else if (DecodeUtil.isTwoBytes(b3)) {
                        if (j2 >= j) {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                        jAddressOffset = j2 + 1;
                        DecodeUtil.handleTwoBytes(b3, UnsafeUtil.getByte(j2), cArr, i4);
                        i4++;
                    } else if (DecodeUtil.isThreeBytes(b3)) {
                        if (j2 >= j - 1) {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                        long j3 = j2 + 1;
                        DecodeUtil.handleThreeBytes(b3, UnsafeUtil.getByte(j2), UnsafeUtil.getByte(j3), cArr, i4);
                        i4++;
                        jAddressOffset = j3 + 1;
                    } else {
                        if (j2 >= j - 2) {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                        long j4 = j2 + 1;
                        byte b5 = UnsafeUtil.getByte(j2);
                        long j5 = j4 + 1;
                        byte b6 = UnsafeUtil.getByte(j4);
                        jAddressOffset = j5 + 1;
                        DecodeUtil.handleFourBytes(b3, b5, b6, UnsafeUtil.getByte(j5), cArr, i4);
                        i3 = i4 + 1 + 1;
                    }
                }
                return new String(cArr, 0, i4);
            }
        }

        @Override // com.google.protobuf.Utf8.Processor
        public int encodeUtf8(CharSequence charSequence, byte[] bArr, int i, int i2) {
            char c2;
            long j;
            long j2;
            int i3;
            char cCharAt;
            long j3 = i;
            long j4 = ((long) i2) + j3;
            int length = charSequence.length();
            if (length > i2 || bArr.length - i2 < i) {
                StringBuilder sbA = a.a("Failed writing ");
                sbA.append(charSequence.charAt(length - 1));
                sbA.append(" at index ");
                sbA.append(i + i2);
                throw new ArrayIndexOutOfBoundsException(sbA.toString());
            }
            int i4 = 0;
            while (true) {
                c2 = 128;
                j = 1;
                if (i4 >= length || (cCharAt = charSequence.charAt(i4)) >= 128) {
                    break;
                }
                UnsafeUtil.putByte(bArr, j3, (byte) cCharAt);
                i4++;
                j3 = 1 + j3;
            }
            if (i4 == length) {
                return (int) j3;
            }
            while (i4 < length) {
                char cCharAt2 = charSequence.charAt(i4);
                if (cCharAt2 >= c2 || j3 >= j4) {
                    if (cCharAt2 < 2048 && j3 <= j4 - 2) {
                        long j5 = j3 + j;
                        UnsafeUtil.putByte(bArr, j3, (byte) ((cCharAt2 >>> 6) | 960));
                        j2 = j5 + j;
                        UnsafeUtil.putByte(bArr, j5, (byte) ((cCharAt2 & '?') | 128));
                    } else {
                        if ((cCharAt2 >= 55296 && 57343 >= cCharAt2) || j3 > j4 - 3) {
                            if (j3 > j4 - 4) {
                                if (55296 <= cCharAt2 && cCharAt2 <= 57343 && ((i3 = i4 + 1) == length || !Character.isSurrogatePair(cCharAt2, charSequence.charAt(i3)))) {
                                    throw new UnpairedSurrogateException(i4, length);
                                }
                                throw new ArrayIndexOutOfBoundsException("Failed writing " + cCharAt2 + " at index " + j3);
                            }
                            int i5 = i4 + 1;
                            if (i5 != length) {
                                char cCharAt3 = charSequence.charAt(i5);
                                if (Character.isSurrogatePair(cCharAt2, cCharAt3)) {
                                    int codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                                    long j6 = j3 + 1;
                                    UnsafeUtil.putByte(bArr, j3, (byte) ((codePoint >>> 18) | 240));
                                    long j7 = 1 + j6;
                                    UnsafeUtil.putByte(bArr, j6, (byte) (((codePoint >>> 12) & 63) | 128));
                                    long j8 = 1 + j7;
                                    UnsafeUtil.putByte(bArr, j7, (byte) (((codePoint >>> 6) & 63) | 128));
                                    j = 1;
                                    UnsafeUtil.putByte(bArr, j8, (byte) ((codePoint & 63) | 128));
                                    i4 = i5;
                                    c2 = 128;
                                    j3 = j8 + 1;
                                } else {
                                    i4 = i5;
                                }
                            }
                            throw new UnpairedSurrogateException(i4 - 1, length);
                        }
                        long j9 = j3 + j;
                        UnsafeUtil.putByte(bArr, j3, (byte) ((cCharAt2 >>> '\f') | 480));
                        long j10 = j + j9;
                        UnsafeUtil.putByte(bArr, j9, (byte) (((cCharAt2 >>> 6) & 63) | 128));
                        j2 = 1 + j10;
                        UnsafeUtil.putByte(bArr, j10, (byte) ((cCharAt2 & '?') | 128));
                        j = 1;
                    }
                    long j11 = j2;
                    c2 = 128;
                    j3 = j11;
                } else {
                    UnsafeUtil.putByte(bArr, j3, (byte) cCharAt2);
                    j3 += j;
                }
                i4++;
            }
            return (int) j3;
        }

        @Override // com.google.protobuf.Utf8.Processor
        public void encodeUtf8Direct(CharSequence charSequence, ByteBuffer byteBuffer) {
            char c2;
            long j;
            long j2;
            int i;
            char cCharAt;
            long jAddressOffset = UnsafeUtil.addressOffset(byteBuffer);
            long jPosition = ((long) byteBuffer.position()) + jAddressOffset;
            long jLimit = ((long) byteBuffer.limit()) + jAddressOffset;
            int length = charSequence.length();
            if (length > jLimit - jPosition) {
                StringBuilder sbA = a.a("Failed writing ");
                sbA.append(charSequence.charAt(length - 1));
                sbA.append(" at index ");
                sbA.append(byteBuffer.limit());
                throw new ArrayIndexOutOfBoundsException(sbA.toString());
            }
            int i2 = 0;
            while (true) {
                c2 = 128;
                if (i2 >= length || (cCharAt = charSequence.charAt(i2)) >= 128) {
                    break;
                }
                UnsafeUtil.MEMORY_ACCESSOR.putByte(jPosition, (byte) cCharAt);
                i2++;
                jPosition++;
            }
            if (i2 == length) {
                byteBuffer.position((int) (jPosition - jAddressOffset));
                return;
            }
            while (i2 < length) {
                char cCharAt2 = charSequence.charAt(i2);
                if (cCharAt2 >= c2 || jPosition >= jLimit) {
                    if (cCharAt2 >= 2048 || jPosition > jLimit - 2) {
                        j = jAddressOffset;
                        if ((cCharAt2 >= 55296 && 57343 >= cCharAt2) || jPosition > jLimit - 3) {
                            if (jPosition > jLimit - 4) {
                                if (55296 <= cCharAt2 && cCharAt2 <= 57343 && ((i = i2 + 1) == length || !Character.isSurrogatePair(cCharAt2, charSequence.charAt(i)))) {
                                    throw new UnpairedSurrogateException(i2, length);
                                }
                                throw new ArrayIndexOutOfBoundsException("Failed writing " + cCharAt2 + " at index " + jPosition);
                            }
                            int i3 = i2 + 1;
                            if (i3 != length) {
                                char cCharAt3 = charSequence.charAt(i3);
                                if (Character.isSurrogatePair(cCharAt2, cCharAt3)) {
                                    int codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                                    long j3 = jPosition + 1;
                                    UnsafeUtil.MEMORY_ACCESSOR.putByte(jPosition, (byte) ((codePoint >>> 18) | 240));
                                    long j4 = j3 + 1;
                                    UnsafeUtil.MEMORY_ACCESSOR.putByte(j3, (byte) (((codePoint >>> 12) & 63) | 128));
                                    long j5 = j4 + 1;
                                    UnsafeUtil.MEMORY_ACCESSOR.putByte(j4, (byte) (((codePoint >>> 6) & 63) | 128));
                                    UnsafeUtil.MEMORY_ACCESSOR.putByte(j5, (byte) ((codePoint & 63) | 128));
                                    jPosition = j5 + 1;
                                    c2 = 128;
                                    i2 = i3;
                                }
                            } else {
                                i3 = i2;
                            }
                            throw new UnpairedSurrogateException(i3 - 1, length);
                        }
                        long j6 = jPosition + 1;
                        UnsafeUtil.MEMORY_ACCESSOR.putByte(jPosition, (byte) ((cCharAt2 >>> '\f') | 480));
                        long j7 = j6 + 1;
                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j6, (byte) (((cCharAt2 >>> 6) & 63) | 128));
                        j2 = j7 + 1;
                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j7, (byte) ((cCharAt2 & '?') | 128));
                    } else {
                        j = jAddressOffset;
                        long j8 = jPosition + 1;
                        UnsafeUtil.MEMORY_ACCESSOR.putByte(jPosition, (byte) ((cCharAt2 >>> 6) | 960));
                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j8, (byte) ((cCharAt2 & '?') | 128));
                        j2 = j8 + 1;
                    }
                    jPosition = j2;
                    c2 = 128;
                } else {
                    UnsafeUtil.MEMORY_ACCESSOR.putByte(jPosition, (byte) cCharAt2);
                    c2 = 128;
                    jPosition++;
                    j = jAddressOffset;
                }
                i2++;
                jAddressOffset = j;
                jLimit = jLimit;
            }
            byteBuffer.position((int) (jPosition - jAddressOffset));
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0025, code lost:
        
            if (com.google.protobuf.UnsafeUtil.getByte(r13, r2) > (-65)) goto L15;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0056, code lost:
        
            if (com.google.protobuf.UnsafeUtil.getByte(r13, r2) > (-65)) goto L34;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x0098, code lost:
        
            if (com.google.protobuf.UnsafeUtil.getByte(r13, r2) > (-65)) goto L55;
         */
        @Override // com.google.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int partialIsValidUtf8(int r12, byte[] r13, int r14, int r15) {
            /*
                r11 = this;
                r0 = r14 | r15
                int r1 = r13.length
                int r1 = r1 - r15
                r0 = r0 | r1
                r1 = 0
                if (r0 < 0) goto La3
                long r2 = (long) r14
                long r14 = (long) r15
                if (r12 == 0) goto L9b
                int r0 = (r2 > r14 ? 1 : (r2 == r14 ? 0 : -1))
                if (r0 < 0) goto L11
                return r12
            L11:
                byte r0 = (byte) r12
                r4 = -32
                r5 = -1
                r6 = -65
                r7 = 1
                if (r0 >= r4) goto L28
                r12 = -62
                if (r0 < r12) goto L27
                long r0 = r2 + r7
                byte r12 = com.google.protobuf.UnsafeUtil.getByte(r13, r2)
                if (r12 <= r6) goto L9c
            L27:
                return r5
            L28:
                r9 = -16
                if (r0 >= r9) goto L59
                int r12 = r12 >> 8
                int r12 = ~r12
                byte r12 = (byte) r12
                if (r12 != 0) goto L42
                long r9 = r2 + r7
                byte r12 = com.google.protobuf.UnsafeUtil.getByte(r13, r2)
                int r1 = (r9 > r14 ? 1 : (r9 == r14 ? 0 : -1))
                if (r1 < 0) goto L41
                int r12 = com.google.protobuf.Utf8.incompleteStateFor(r0, r12)
                return r12
            L41:
                r2 = r9
            L42:
                if (r12 > r6) goto L58
                r1 = -96
                if (r0 != r4) goto L4a
                if (r12 < r1) goto L58
            L4a:
                r4 = -19
                if (r0 != r4) goto L50
                if (r12 >= r1) goto L58
            L50:
                long r0 = r2 + r7
                byte r12 = com.google.protobuf.UnsafeUtil.getByte(r13, r2)
                if (r12 <= r6) goto L9c
            L58:
                return r5
            L59:
                int r4 = r12 >> 8
                int r4 = ~r4
                byte r4 = (byte) r4
                if (r4 != 0) goto L70
                long r9 = r2 + r7
                byte r4 = com.google.protobuf.UnsafeUtil.getByte(r13, r2)
                int r12 = (r9 > r14 ? 1 : (r9 == r14 ? 0 : -1))
                if (r12 < 0) goto L6e
                int r12 = com.google.protobuf.Utf8.incompleteStateFor(r0, r4)
                return r12
            L6e:
                r2 = r9
                goto L73
            L70:
                int r12 = r12 >> 16
                byte r1 = (byte) r12
            L73:
                if (r1 != 0) goto L85
                long r9 = r2 + r7
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r13, r2)
                int r12 = (r9 > r14 ? 1 : (r9 == r14 ? 0 : -1))
                if (r12 < 0) goto L84
                int r12 = com.google.protobuf.Utf8.incompleteStateFor(r0, r4, r1)
                return r12
            L84:
                r2 = r9
            L85:
                if (r4 > r6) goto L9a
                int r12 = r0 << 28
                int r4 = r4 + 112
                int r4 = r4 + r12
                int r12 = r4 >> 30
                if (r12 != 0) goto L9a
                if (r1 > r6) goto L9a
                long r0 = r2 + r7
                byte r12 = com.google.protobuf.UnsafeUtil.getByte(r13, r2)
                if (r12 <= r6) goto L9c
            L9a:
                return r5
            L9b:
                r0 = r2
            L9c:
                long r14 = r14 - r0
                int r12 = (int) r14
                int r12 = partialIsValidUtf8(r13, r0, r12)
                return r12
            La3:
                java.lang.ArrayIndexOutOfBoundsException r12 = new java.lang.ArrayIndexOutOfBoundsException
                r0 = 3
                java.lang.Object[] r0 = new java.lang.Object[r0]
                int r13 = r13.length
                java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
                r0[r1] = r13
                r13 = 1
                java.lang.Integer r14 = java.lang.Integer.valueOf(r14)
                r0[r13] = r14
                r13 = 2
                java.lang.Integer r14 = java.lang.Integer.valueOf(r15)
                r0[r13] = r14
                java.lang.String r13 = "Array length=%d, index=%d, limit=%d"
                java.lang.String r13 = java.lang.String.format(r13, r0)
                r12.<init>(r13)
                throw r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.UnsafeProcessor.partialIsValidUtf8(int, byte[], int, int):int");
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x002f, code lost:
        
            if (com.google.protobuf.UnsafeUtil.getByte(r2) > (-65)) goto L15;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0060, code lost:
        
            if (com.google.protobuf.UnsafeUtil.getByte(r2) > (-65)) goto L34;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x00a2, code lost:
        
            if (com.google.protobuf.UnsafeUtil.getByte(r2) > (-65)) goto L55;
         */
        @Override // com.google.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int partialIsValidUtf8Direct(int r11, java.nio.ByteBuffer r12, int r13, int r14) {
            /*
                Method dump skipped, instruction units count: 212
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.UnsafeProcessor.partialIsValidUtf8Direct(int, java.nio.ByteBuffer, int, int):int");
        }

        public static int unsafeEstimateConsecutiveAscii(long j, int i) {
            if (i < 16) {
                return 0;
            }
            int i2 = 8 - (((int) j) & 7);
            long j2 = j;
            int i3 = i2;
            while (i3 > 0) {
                long j3 = 1 + j2;
                if (UnsafeUtil.getByte(j2) < 0) {
                    return i2 - i3;
                }
                i3--;
                j2 = j3;
            }
            int i4 = i - i2;
            while (i4 >= 8 && (UnsafeUtil.getLong(j2) & Utf8.ASCII_MASK_LONG) == 0) {
                j2 += 8;
                i4 -= 8;
            }
            return i - i4;
        }

        public static int unsafeIncompleteStateFor(long j, int i, int i2) {
            if (i2 == 0) {
                return Utf8.incompleteStateFor(i);
            }
            if (i2 == 1) {
                return Utf8.incompleteStateFor(i, UnsafeUtil.getByte(j));
            }
            if (i2 == 2) {
                return Utf8.incompleteStateFor(i, UnsafeUtil.getByte(j), UnsafeUtil.getByte(j + 1));
            }
            throw new AssertionError();
        }

        public static int partialIsValidUtf8(byte[] bArr, long j, int i) {
            long j2;
            int iUnsafeEstimateConsecutiveAscii = unsafeEstimateConsecutiveAscii(bArr, j, i);
            int i2 = i - iUnsafeEstimateConsecutiveAscii;
            long j3 = j + ((long) iUnsafeEstimateConsecutiveAscii);
            while (true) {
                byte b2 = 0;
                while (i2 > 0) {
                    long j4 = j3 + 1;
                    b2 = UnsafeUtil.getByte(bArr, j3);
                    if (b2 < 0) {
                        j3 = j4;
                        break;
                    }
                    i2--;
                    j3 = j4;
                }
                if (i2 == 0) {
                    return 0;
                }
                int i3 = i2 - 1;
                if (b2 < -32) {
                    if (i3 == 0) {
                        return b2;
                    }
                    i2 = i3 - 1;
                    if (b2 >= -62) {
                        j2 = 1 + j3;
                        if (UnsafeUtil.getByte(bArr, j3) > -65) {
                        }
                    }
                    return -1;
                }
                if (b2 < -16) {
                    if (i3 < 2) {
                        return unsafeIncompleteStateFor(bArr, b2, j3, i3);
                    }
                    i2 = i3 - 2;
                    long j5 = j3 + 1;
                    byte b3 = UnsafeUtil.getByte(bArr, j3);
                    if (b3 <= -65 && ((b2 != -32 || b3 >= -96) && (b2 != -19 || b3 < -96))) {
                        j2 = 1 + j5;
                        if (UnsafeUtil.getByte(bArr, j5) > -65) {
                        }
                    }
                    return -1;
                }
                if (i3 < 3) {
                    return unsafeIncompleteStateFor(bArr, b2, j3, i3);
                }
                i2 = i3 - 3;
                long j6 = j3 + 1;
                byte b4 = UnsafeUtil.getByte(bArr, j3);
                if (b4 <= -65) {
                    if ((((b4 + 112) + (b2 << Ascii.FS)) >> 30) == 0) {
                        long j7 = j6 + 1;
                        if (UnsafeUtil.getByte(bArr, j6) <= -65) {
                            j2 = 1 + j7;
                            if (UnsafeUtil.getByte(bArr, j7) > -65) {
                            }
                        }
                    }
                }
                return -1;
                j3 = j2;
            }
        }

        public static int partialIsValidUtf8(long j, int i) {
            long j2;
            int iUnsafeEstimateConsecutiveAscii = unsafeEstimateConsecutiveAscii(j, i);
            long j3 = j + ((long) iUnsafeEstimateConsecutiveAscii);
            int i2 = i - iUnsafeEstimateConsecutiveAscii;
            while (true) {
                byte b2 = 0;
                while (i2 > 0) {
                    long j4 = j3 + 1;
                    b2 = UnsafeUtil.getByte(j3);
                    if (b2 < 0) {
                        j3 = j4;
                        break;
                    }
                    i2--;
                    j3 = j4;
                }
                if (i2 == 0) {
                    return 0;
                }
                int i3 = i2 - 1;
                if (b2 < -32) {
                    if (i3 == 0) {
                        return b2;
                    }
                    i2 = i3 - 1;
                    if (b2 >= -62) {
                        j2 = 1 + j3;
                        if (UnsafeUtil.getByte(j3) > -65) {
                        }
                    }
                    return -1;
                }
                if (b2 < -16) {
                    if (i3 < 2) {
                        return unsafeIncompleteStateFor(j3, b2, i3);
                    }
                    i2 = i3 - 2;
                    long j5 = j3 + 1;
                    byte b3 = UnsafeUtil.getByte(j3);
                    if (b3 <= -65 && ((b2 != -32 || b3 >= -96) && (b2 != -19 || b3 < -96))) {
                        j2 = 1 + j5;
                        if (UnsafeUtil.getByte(j5) > -65) {
                        }
                    }
                    return -1;
                }
                if (i3 < 3) {
                    return unsafeIncompleteStateFor(j3, b2, i3);
                }
                i2 = i3 - 3;
                long j6 = j3 + 1;
                byte b4 = UnsafeUtil.getByte(j3);
                if (b4 <= -65) {
                    if ((((b4 + 112) + (b2 << Ascii.FS)) >> 30) == 0) {
                        long j7 = j6 + 1;
                        if (UnsafeUtil.getByte(j6) <= -65) {
                            j2 = 1 + j7;
                            if (UnsafeUtil.getByte(j7) > -65) {
                            }
                        }
                    }
                }
                return -1;
                j3 = j2;
            }
        }
    }

    static {
        processor = (!UnsafeProcessor.isAvailable() || Android.isOnAndroidDevice()) ? new SafeProcessor() : new UnsafeProcessor();
    }

    public static String decodeUtf8(ByteBuffer byteBuffer, int i, int i2) {
        return processor.decodeUtf8(byteBuffer, i, i2);
    }

    public static int encode(CharSequence charSequence, byte[] bArr, int i, int i2) {
        return processor.encodeUtf8(charSequence, bArr, i, i2);
    }

    public static void encodeUtf8(CharSequence charSequence, ByteBuffer byteBuffer) {
        processor.encodeUtf8(charSequence, byteBuffer);
    }

    public static int encodedLength(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length && charSequence.charAt(i) < 128) {
            i++;
        }
        int iEncodedLengthGeneral = length;
        while (i < length) {
            char cCharAt = charSequence.charAt(i);
            if (cCharAt >= 2048) {
                iEncodedLengthGeneral += encodedLengthGeneral(charSequence, i);
                break;
            }
            iEncodedLengthGeneral += (127 - cCharAt) >>> 31;
            i++;
        }
        if (iEncodedLengthGeneral >= length) {
            return iEncodedLengthGeneral;
        }
        StringBuilder sbA = a.a("UTF-8 length does not fit in int: ");
        sbA.append(((long) iEncodedLengthGeneral) + 4294967296L);
        throw new IllegalArgumentException(sbA.toString());
    }

    public static int encodedLengthGeneral(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        while (i < length) {
            char cCharAt = charSequence.charAt(i);
            if (cCharAt < 2048) {
                i2 += (127 - cCharAt) >>> 31;
            } else {
                i2 += 2;
                if (55296 <= cCharAt && cCharAt <= 57343) {
                    if (Character.codePointAt(charSequence, i) < 65536) {
                        throw new UnpairedSurrogateException(i, length);
                    }
                    i++;
                }
            }
            i++;
        }
        return i2;
    }

    public static int estimateConsecutiveAscii(ByteBuffer byteBuffer, int i, int i2) {
        int i3 = i2 - 7;
        int i4 = i;
        while (i4 < i3 && (byteBuffer.getLong(i4) & ASCII_MASK_LONG) == 0) {
            i4 += 8;
        }
        return i4 - i;
    }

    public static int incompleteStateFor(int i) {
        if (i > -12) {
            return -1;
        }
        return i;
    }

    public static int incompleteStateFor(int i, int i2) {
        if (i > -12 || i2 > -65) {
            return -1;
        }
        return i ^ (i2 << 8);
    }

    public static int incompleteStateFor(int i, int i2, int i3) {
        if (i > -12 || i2 > -65 || i3 > -65) {
            return -1;
        }
        return (i ^ (i2 << 8)) ^ (i3 << 16);
    }

    public static int incompleteStateFor(byte[] bArr, int i, int i2) {
        byte b2 = bArr[i - 1];
        int i3 = i2 - i;
        if (i3 == 0) {
            return incompleteStateFor(b2);
        }
        if (i3 == 1) {
            return incompleteStateFor(b2, bArr[i]);
        }
        if (i3 == 2) {
            return incompleteStateFor(b2, bArr[i], bArr[i + 1]);
        }
        throw new AssertionError();
    }

    public static boolean isValidUtf8(byte[] bArr) {
        return processor.isValidUtf8(bArr, 0, bArr.length);
    }

    public static int partialIsValidUtf8(int i, byte[] bArr, int i2, int i3) {
        return processor.partialIsValidUtf8(i, bArr, i2, i3);
    }

    public static String decodeUtf8(byte[] bArr, int i, int i2) {
        return processor.decodeUtf8(bArr, i, i2);
    }

    public static boolean isValidUtf8(byte[] bArr, int i, int i2) {
        return processor.isValidUtf8(bArr, i, i2);
    }

    public static int partialIsValidUtf8(int i, ByteBuffer byteBuffer, int i2, int i3) {
        return processor.partialIsValidUtf8(i, byteBuffer, i2, i3);
    }

    public static boolean isValidUtf8(ByteBuffer byteBuffer) {
        return processor.isValidUtf8(byteBuffer, byteBuffer.position(), byteBuffer.remaining());
    }

    public static int incompleteStateFor(ByteBuffer byteBuffer, int i, int i2, int i3) {
        if (i3 == 0) {
            return incompleteStateFor(i);
        }
        if (i3 == 1) {
            return incompleteStateFor(i, byteBuffer.get(i2));
        }
        if (i3 == 2) {
            return incompleteStateFor(i, byteBuffer.get(i2), byteBuffer.get(i2 + 1));
        }
        throw new AssertionError();
    }

    static final class SafeProcessor extends Processor {
        public static int partialIsValidUtf8NonAscii(byte[] bArr, int i, int i2) {
            while (i < i2) {
                int i3 = i + 1;
                byte b2 = bArr[i];
                if (b2 < 0) {
                    if (b2 < -32) {
                        if (i3 >= i2) {
                            return b2;
                        }
                        if (b2 >= -62) {
                            i = i3 + 1;
                            if (bArr[i3] > -65) {
                            }
                        }
                        return -1;
                    }
                    if (b2 < -16) {
                        if (i3 >= i2 - 1) {
                            return Utf8.incompleteStateFor(bArr, i3, i2);
                        }
                        int i4 = i3 + 1;
                        byte b3 = bArr[i3];
                        if (b3 <= -65 && ((b2 != -32 || b3 >= -96) && (b2 != -19 || b3 < -96))) {
                            i = i4 + 1;
                            if (bArr[i4] > -65) {
                            }
                        }
                        return -1;
                    }
                    if (i3 >= i2 - 2) {
                        return Utf8.incompleteStateFor(bArr, i3, i2);
                    }
                    int i5 = i3 + 1;
                    byte b4 = bArr[i3];
                    if (b4 <= -65) {
                        if ((((b4 + 112) + (b2 << Ascii.FS)) >> 30) == 0) {
                            int i6 = i5 + 1;
                            if (bArr[i5] <= -65) {
                                i3 = i6 + 1;
                                if (bArr[i6] > -65) {
                                }
                            }
                        }
                    }
                    return -1;
                }
                i = i3;
            }
            return 0;
        }

        @Override // com.google.protobuf.Utf8.Processor
        public String decodeUtf8(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException {
            if ((i | i2 | ((bArr.length - i) - i2)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
            }
            int i3 = i + i2;
            char[] cArr = new char[i2];
            int i4 = 0;
            while (i < i3) {
                byte b2 = bArr[i];
                if (!DecodeUtil.isOneByte(b2)) {
                    break;
                }
                i++;
                cArr[i4] = (char) b2;
                i4++;
            }
            int i5 = i4;
            while (i < i3) {
                int i6 = i + 1;
                byte b3 = bArr[i];
                if (DecodeUtil.isOneByte(b3)) {
                    int i7 = i5 + 1;
                    cArr[i5] = (char) b3;
                    while (i6 < i3) {
                        byte b4 = bArr[i6];
                        if (!DecodeUtil.isOneByte(b4)) {
                            break;
                        }
                        i6++;
                        cArr[i7] = (char) b4;
                        i7++;
                    }
                    i = i6;
                    i5 = i7;
                } else if (DecodeUtil.isTwoBytes(b3)) {
                    if (i6 >= i3) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    DecodeUtil.handleTwoBytes(b3, bArr[i6], cArr, i5);
                    i = i6 + 1;
                    i5++;
                } else if (DecodeUtil.isThreeBytes(b3)) {
                    if (i6 >= i3 - 1) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    int i8 = i6 + 1;
                    DecodeUtil.handleThreeBytes(b3, bArr[i6], bArr[i8], cArr, i5);
                    i = i8 + 1;
                    i5++;
                } else {
                    if (i6 >= i3 - 2) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    int i9 = i6 + 1;
                    byte b5 = bArr[i6];
                    int i10 = i9 + 1;
                    DecodeUtil.handleFourBytes(b3, b5, bArr[i9], bArr[i10], cArr, i5);
                    i = i10 + 1;
                    i5 = i5 + 1 + 1;
                }
            }
            return new String(cArr, 0, i5);
        }

        @Override // com.google.protobuf.Utf8.Processor
        public String decodeUtf8Direct(ByteBuffer byteBuffer, int i, int i2) {
            return decodeUtf8Default(byteBuffer, i, i2);
        }

        @Override // com.google.protobuf.Utf8.Processor
        public int encodeUtf8(CharSequence charSequence, byte[] bArr, int i, int i2) {
            int i3;
            int i4;
            int i5;
            char cCharAt;
            int length = charSequence.length();
            int i6 = i2 + i;
            int i7 = 0;
            while (i7 < length && (i5 = i7 + i) < i6 && (cCharAt = charSequence.charAt(i7)) < 128) {
                bArr[i5] = (byte) cCharAt;
                i7++;
            }
            if (i7 == length) {
                return i + length;
            }
            int i8 = i + i7;
            while (i7 < length) {
                char cCharAt2 = charSequence.charAt(i7);
                if (cCharAt2 >= 128 || i8 >= i6) {
                    if (cCharAt2 < 2048 && i8 <= i6 - 2) {
                        int i9 = i8 + 1;
                        bArr[i8] = (byte) ((cCharAt2 >>> 6) | 960);
                        i8 = i9 + 1;
                        bArr[i9] = (byte) ((cCharAt2 & '?') | 128);
                    } else {
                        if ((cCharAt2 >= 55296 && 57343 >= cCharAt2) || i8 > i6 - 3) {
                            if (i8 > i6 - 4) {
                                if (55296 <= cCharAt2 && cCharAt2 <= 57343 && ((i4 = i7 + 1) == charSequence.length() || !Character.isSurrogatePair(cCharAt2, charSequence.charAt(i4)))) {
                                    throw new UnpairedSurrogateException(i7, length);
                                }
                                throw new ArrayIndexOutOfBoundsException("Failed writing " + cCharAt2 + " at index " + i8);
                            }
                            int i10 = i7 + 1;
                            if (i10 != charSequence.length()) {
                                char cCharAt3 = charSequence.charAt(i10);
                                if (Character.isSurrogatePair(cCharAt2, cCharAt3)) {
                                    int codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                                    int i11 = i8 + 1;
                                    bArr[i8] = (byte) ((codePoint >>> 18) | 240);
                                    int i12 = i11 + 1;
                                    bArr[i11] = (byte) (((codePoint >>> 12) & 63) | 128);
                                    int i13 = i12 + 1;
                                    bArr[i12] = (byte) (((codePoint >>> 6) & 63) | 128);
                                    i8 = i13 + 1;
                                    bArr[i13] = (byte) ((codePoint & 63) | 128);
                                    i7 = i10;
                                } else {
                                    i7 = i10;
                                }
                            }
                            throw new UnpairedSurrogateException(i7 - 1, length);
                        }
                        int i14 = i8 + 1;
                        bArr[i8] = (byte) ((cCharAt2 >>> '\f') | 480);
                        int i15 = i14 + 1;
                        bArr[i14] = (byte) (((cCharAt2 >>> 6) & 63) | 128);
                        i3 = i15 + 1;
                        bArr[i15] = (byte) ((cCharAt2 & '?') | 128);
                    }
                    i7++;
                } else {
                    i3 = i8 + 1;
                    bArr[i8] = (byte) cCharAt2;
                }
                i8 = i3;
                i7++;
            }
            return i8;
        }

        @Override // com.google.protobuf.Utf8.Processor
        public void encodeUtf8Direct(CharSequence charSequence, ByteBuffer byteBuffer) {
            encodeUtf8Default(charSequence, byteBuffer);
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0015, code lost:
        
            if (r8[r9] > (-65)) goto L11;
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x0042, code lost:
        
            if (r8[r9] > (-65)) goto L30;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x007a, code lost:
        
            if (r8[r7] > (-65)) goto L50;
         */
        @Override // com.google.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int partialIsValidUtf8(int r7, byte[] r8, int r9, int r10) {
            /*
                r6 = this;
                if (r7 == 0) goto L7d
                if (r9 < r10) goto L5
                return r7
            L5:
                byte r0 = (byte) r7
                r1 = -32
                r2 = -1
                r3 = -65
                if (r0 >= r1) goto L18
                r7 = -62
                if (r0 < r7) goto L17
                int r7 = r9 + 1
                r9 = r8[r9]
                if (r9 <= r3) goto L7e
            L17:
                return r2
            L18:
                r4 = -16
                if (r0 >= r4) goto L45
                int r7 = r7 >> 8
                int r7 = ~r7
                byte r7 = (byte) r7
                if (r7 != 0) goto L30
                int r7 = r9 + 1
                r9 = r8[r9]
                if (r7 < r10) goto L2d
                int r7 = com.google.protobuf.Utf8.incompleteStateFor(r0, r9)
                return r7
            L2d:
                r5 = r9
                r9 = r7
                r7 = r5
            L30:
                if (r7 > r3) goto L44
                r4 = -96
                if (r0 != r1) goto L38
                if (r7 < r4) goto L44
            L38:
                r1 = -19
                if (r0 != r1) goto L3e
                if (r7 >= r4) goto L44
            L3e:
                int r7 = r9 + 1
                r9 = r8[r9]
                if (r9 <= r3) goto L7e
            L44:
                return r2
            L45:
                int r1 = r7 >> 8
                int r1 = ~r1
                byte r1 = (byte) r1
                r4 = 0
                if (r1 != 0) goto L57
                int r7 = r9 + 1
                r1 = r8[r9]
                if (r7 < r10) goto L5b
                int r7 = com.google.protobuf.Utf8.incompleteStateFor(r0, r1)
                return r7
            L57:
                int r7 = r7 >> 16
                byte r4 = (byte) r7
                r7 = r9
            L5b:
                if (r4 != 0) goto L69
                int r9 = r7 + 1
                r4 = r8[r7]
                if (r9 < r10) goto L68
                int r7 = com.google.protobuf.Utf8.incompleteStateFor(r0, r1, r4)
                return r7
            L68:
                r7 = r9
            L69:
                if (r1 > r3) goto L7c
                int r9 = r0 << 28
                int r1 = r1 + 112
                int r1 = r1 + r9
                int r9 = r1 >> 30
                if (r9 != 0) goto L7c
                if (r4 > r3) goto L7c
                int r9 = r7 + 1
                r7 = r8[r7]
                if (r7 <= r3) goto L7d
            L7c:
                return r2
            L7d:
                r7 = r9
            L7e:
                int r7 = partialIsValidUtf8(r8, r7, r10)
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.SafeProcessor.partialIsValidUtf8(int, byte[], int, int):int");
        }

        @Override // com.google.protobuf.Utf8.Processor
        public int partialIsValidUtf8Direct(int i, ByteBuffer byteBuffer, int i2, int i3) {
            return partialIsValidUtf8Default(i, byteBuffer, i2, i3);
        }

        public static int partialIsValidUtf8(byte[] bArr, int i, int i2) {
            while (i < i2 && bArr[i] >= 0) {
                i++;
            }
            if (i >= i2) {
                return 0;
            }
            return partialIsValidUtf8NonAscii(bArr, i, i2);
        }
    }
}
