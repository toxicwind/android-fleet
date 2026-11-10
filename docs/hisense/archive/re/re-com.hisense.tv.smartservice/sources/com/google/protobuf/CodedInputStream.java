package com.google.protobuf;

import b.a.a.a.a;
import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class CodedInputStream {
    public static final int DEFAULT_BUFFER_SIZE = 4096;
    public static final int DEFAULT_RECURSION_LIMIT = 100;
    public static final int DEFAULT_SIZE_LIMIT = Integer.MAX_VALUE;
    public int recursionDepth;
    public int recursionLimit;
    public boolean shouldDiscardUnknownFields;
    public int sizeLimit;
    public CodedInputStreamReader wrapper;

    public static int decodeZigZag32(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public static long decodeZigZag64(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    public static CodedInputStream newInstance(InputStream inputStream) {
        return newInstance(inputStream, 4096);
    }

    public static int readRawVarint32(int i, InputStream inputStream) throws IOException {
        if ((i & 128) == 0) {
            return i;
        }
        int i2 = i & 127;
        int i3 = 7;
        while (i3 < 32) {
            int i4 = inputStream.read();
            if (i4 == -1) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            i2 |= (i4 & 127) << i3;
            if ((i4 & 128) == 0) {
                return i2;
            }
            i3 += 7;
        }
        while (i3 < 64) {
            int i5 = inputStream.read();
            if (i5 == -1) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            if ((i5 & 128) == 0) {
                return i2;
            }
            i3 += 7;
        }
        throw InvalidProtocolBufferException.malformedVarint();
    }

    public abstract void checkLastTagWas(int i);

    public final void discardUnknownFields() {
        this.shouldDiscardUnknownFields = true;
    }

    public abstract void enableAliasing(boolean z);

    public abstract int getBytesUntilLimit();

    public abstract int getLastTag();

    public abstract int getTotalBytesRead();

    public abstract boolean isAtEnd();

    public abstract void popLimit(int i);

    public abstract int pushLimit(int i);

    public abstract boolean readBool();

    public abstract byte[] readByteArray();

    public abstract ByteBuffer readByteBuffer();

    public abstract ByteString readBytes();

    public abstract double readDouble();

    public abstract int readEnum();

    public abstract int readFixed32();

    public abstract long readFixed64();

    public abstract float readFloat();

    public abstract <T extends MessageLite> T readGroup(int i, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite);

    public abstract void readGroup(int i, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite);

    public abstract int readInt32();

    public abstract long readInt64();

    public abstract <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite);

    public abstract void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite);

    public abstract byte readRawByte();

    public abstract byte[] readRawBytes(int i);

    public abstract int readRawLittleEndian32();

    public abstract long readRawLittleEndian64();

    public abstract int readRawVarint32();

    public abstract long readRawVarint64();

    public abstract long readRawVarint64SlowPath();

    public abstract int readSFixed32();

    public abstract long readSFixed64();

    public abstract int readSInt32();

    public abstract long readSInt64();

    public abstract String readString();

    public abstract String readStringRequireUtf8();

    public abstract int readTag();

    public abstract int readUInt32();

    public abstract long readUInt64();

    @Deprecated
    public abstract void readUnknownGroup(int i, MessageLite.Builder builder);

    public abstract void resetSizeCounter();

    public final int setRecursionLimit(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(a.a("Recursion limit cannot be negative: ", i));
        }
        int i2 = this.recursionLimit;
        this.recursionLimit = i;
        return i2;
    }

    public final int setSizeLimit(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(a.a("Size limit cannot be negative: ", i));
        }
        int i2 = this.sizeLimit;
        this.sizeLimit = i;
        return i2;
    }

    public final boolean shouldDiscardUnknownFields() {
        return this.shouldDiscardUnknownFields;
    }

    public abstract boolean skipField(int i);

    @Deprecated
    public abstract boolean skipField(int i, CodedOutputStream codedOutputStream);

    public abstract void skipMessage();

    public abstract void skipMessage(CodedOutputStream codedOutputStream);

    public abstract void skipRawBytes(int i);

    public final void unsetDiscardUnknownFields() {
        this.shouldDiscardUnknownFields = false;
    }

    private static final class ArrayDecoder extends CodedInputStream {
        public final byte[] buffer;
        public int bufferSizeAfterLimit;
        public int currentLimit;
        public boolean enableAliasing;
        public final boolean immutable;
        public int lastTag;
        public int limit;
        public int pos;
        public int startPos;

        public ArrayDecoder(byte[] bArr, int i, int i2, boolean z) {
            this.currentLimit = Integer.MAX_VALUE;
            this.buffer = bArr;
            this.limit = i2 + i;
            this.pos = i;
            this.startPos = this.pos;
            this.immutable = z;
        }

        private void recomputeBufferSizeAfterLimit() {
            this.limit += this.bufferSizeAfterLimit;
            int i = this.limit;
            int i2 = i - this.startPos;
            int i3 = this.currentLimit;
            if (i2 <= i3) {
                this.bufferSizeAfterLimit = 0;
            } else {
                this.bufferSizeAfterLimit = i2 - i3;
                this.limit = i - this.bufferSizeAfterLimit;
            }
        }

        private void skipRawVarint() throws InvalidProtocolBufferException {
            if (this.limit - this.pos >= 10) {
                skipRawVarintFastPath();
            } else {
                skipRawVarintSlowPath();
            }
        }

        private void skipRawVarintFastPath() throws InvalidProtocolBufferException {
            for (int i = 0; i < 10; i++) {
                byte[] bArr = this.buffer;
                int i2 = this.pos;
                this.pos = i2 + 1;
                if (bArr[i2] >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void skipRawVarintSlowPath() throws InvalidProtocolBufferException {
            for (int i = 0; i < 10; i++) {
                if (readRawByte() >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        @Override // com.google.protobuf.CodedInputStream
        public void checkLastTagWas(int i) throws InvalidProtocolBufferException {
            if (this.lastTag != i) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        @Override // com.google.protobuf.CodedInputStream
        public void enableAliasing(boolean z) {
            this.enableAliasing = z;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getBytesUntilLimit() {
            int i = this.currentLimit;
            if (i == Integer.MAX_VALUE) {
                return -1;
            }
            return i - getTotalBytesRead();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getLastTag() {
            return this.lastTag;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getTotalBytesRead() {
            return this.pos - this.startPos;
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean isAtEnd() {
            return this.pos == this.limit;
        }

        @Override // com.google.protobuf.CodedInputStream
        public void popLimit(int i) {
            this.currentLimit = i;
            recomputeBufferSizeAfterLimit();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int pushLimit(int i) throws InvalidProtocolBufferException {
            if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int totalBytesRead = getTotalBytesRead() + i;
            if (totalBytesRead < 0) {
                throw InvalidProtocolBufferException.parseFailure();
            }
            int i2 = this.currentLimit;
            if (totalBytesRead > i2) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.currentLimit = totalBytesRead;
            recomputeBufferSizeAfterLimit();
            return i2;
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean readBool() {
            return readRawVarint64() != 0;
        }

        @Override // com.google.protobuf.CodedInputStream
        public byte[] readByteArray() {
            return readRawBytes(readRawVarint32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public ByteBuffer readByteBuffer() throws InvalidProtocolBufferException {
            ByteBuffer byteBufferWrap;
            int rawVarint32 = readRawVarint32();
            if (rawVarint32 > 0) {
                int i = this.limit;
                int i2 = this.pos;
                if (rawVarint32 <= i - i2) {
                    if (this.immutable || !this.enableAliasing) {
                        byte[] bArr = this.buffer;
                        int i3 = this.pos;
                        byteBufferWrap = ByteBuffer.wrap(Arrays.copyOfRange(bArr, i3, i3 + rawVarint32));
                    } else {
                        byteBufferWrap = ByteBuffer.wrap(this.buffer, i2, rawVarint32).slice();
                    }
                    this.pos += rawVarint32;
                    return byteBufferWrap;
                }
            }
            if (rawVarint32 == 0) {
                return Internal.EMPTY_BYTE_BUFFER;
            }
            if (rawVarint32 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.protobuf.CodedInputStream
        public ByteString readBytes() {
            int rawVarint32 = readRawVarint32();
            if (rawVarint32 > 0) {
                int i = this.limit;
                int i2 = this.pos;
                if (rawVarint32 <= i - i2) {
                    ByteString byteStringWrap = (this.immutable && this.enableAliasing) ? ByteString.wrap(this.buffer, i2, rawVarint32) : ByteString.copyFrom(this.buffer, this.pos, rawVarint32);
                    this.pos += rawVarint32;
                    return byteStringWrap;
                }
            }
            return rawVarint32 == 0 ? ByteString.EMPTY : ByteString.wrap(readRawBytes(rawVarint32));
        }

        @Override // com.google.protobuf.CodedInputStream
        public double readDouble() {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readEnum() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readFixed32() {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readFixed64() {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public float readFloat() {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public void readGroup(int i, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            int i2 = this.recursionDepth;
            if (i2 >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            this.recursionDepth = i2 + 1;
            builder.mergeFrom(this, extensionRegistryLite);
            checkLastTagWas((i << 3) | 4);
            this.recursionDepth--;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readInt32() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readInt64() {
            return readRawVarint64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (this.recursionDepth >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            int iPushLimit = pushLimit(rawVarint32);
            this.recursionDepth++;
            builder.mergeFrom(this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            popLimit(iPushLimit);
        }

        @Override // com.google.protobuf.CodedInputStream
        public byte readRawByte() throws InvalidProtocolBufferException {
            int i = this.pos;
            if (i == this.limit) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            byte[] bArr = this.buffer;
            this.pos = i + 1;
            return bArr[i];
        }

        @Override // com.google.protobuf.CodedInputStream
        public byte[] readRawBytes(int i) throws InvalidProtocolBufferException {
            if (i > 0) {
                int i2 = this.limit;
                int i3 = this.pos;
                if (i <= i2 - i3) {
                    this.pos = i + i3;
                    return Arrays.copyOfRange(this.buffer, i3, this.pos);
                }
            }
            if (i > 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            if (i == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readRawLittleEndian32() throws InvalidProtocolBufferException {
            int i = this.pos;
            if (this.limit - i < 4) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            byte[] bArr = this.buffer;
            this.pos = i + 4;
            return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readRawLittleEndian64() throws InvalidProtocolBufferException {
            int i = this.pos;
            if (this.limit - i < 8) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            byte[] bArr = this.buffer;
            this.pos = i + 8;
            return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x0068, code lost:
        
            if (r2[r3] < 0) goto L34;
         */
        @Override // com.google.protobuf.CodedInputStream
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int readRawVarint32() {
            /*
                r5 = this;
                int r0 = r5.pos
                int r1 = r5.limit
                if (r1 != r0) goto L7
                goto L6a
            L7:
                byte[] r2 = r5.buffer
                int r3 = r0 + 1
                r0 = r2[r0]
                if (r0 < 0) goto L12
                r5.pos = r3
                return r0
            L12:
                int r1 = r1 - r3
                r4 = 9
                if (r1 >= r4) goto L18
                goto L6a
            L18:
                int r1 = r3 + 1
                r3 = r2[r3]
                int r3 = r3 << 7
                r0 = r0 ^ r3
                if (r0 >= 0) goto L24
                r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
                goto L70
            L24:
                int r3 = r1 + 1
                r1 = r2[r1]
                int r1 = r1 << 14
                r0 = r0 ^ r1
                if (r0 < 0) goto L31
                r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            L2f:
                r1 = r3
                goto L70
            L31:
                int r1 = r3 + 1
                r3 = r2[r3]
                int r3 = r3 << 21
                r0 = r0 ^ r3
                if (r0 >= 0) goto L3f
                r2 = -2080896(0xffffffffffe03f80, float:NaN)
                r0 = r0 ^ r2
                goto L70
            L3f:
                int r3 = r1 + 1
                r1 = r2[r1]
                int r4 = r1 << 28
                r0 = r0 ^ r4
                r4 = 266354560(0xfe03f80, float:2.2112565E-29)
                r0 = r0 ^ r4
                if (r1 >= 0) goto L2f
                int r1 = r3 + 1
                r3 = r2[r3]
                if (r3 >= 0) goto L70
                int r3 = r1 + 1
                r1 = r2[r1]
                if (r1 >= 0) goto L2f
                int r1 = r3 + 1
                r3 = r2[r3]
                if (r3 >= 0) goto L70
                int r3 = r1 + 1
                r1 = r2[r1]
                if (r1 >= 0) goto L2f
                int r1 = r3 + 1
                r2 = r2[r3]
                if (r2 >= 0) goto L70
            L6a:
                long r0 = r5.readRawVarint64SlowPath()
                int r0 = (int) r0
                return r0
            L70:
                r5.pos = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.ArrayDecoder.readRawVarint32():int");
        }

        /* JADX WARN: Code restructure failed: missing block: B:39:0x00b4, code lost:
        
            if (r2[r0] < 0) goto L40;
         */
        @Override // com.google.protobuf.CodedInputStream
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public long readRawVarint64() {
            /*
                r11 = this;
                int r0 = r11.pos
                int r1 = r11.limit
                if (r1 != r0) goto L8
                goto Lb6
            L8:
                byte[] r2 = r11.buffer
                int r3 = r0 + 1
                r0 = r2[r0]
                if (r0 < 0) goto L14
                r11.pos = r3
                long r0 = (long) r0
                return r0
            L14:
                int r1 = r1 - r3
                r4 = 9
                if (r1 >= r4) goto L1b
                goto Lb6
            L1b:
                int r1 = r3 + 1
                r3 = r2[r3]
                int r3 = r3 << 7
                r0 = r0 ^ r3
                if (r0 >= 0) goto L29
                r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
            L26:
                long r2 = (long) r0
                goto Lbd
            L29:
                int r3 = r1 + 1
                r1 = r2[r1]
                int r1 = r1 << 14
                r0 = r0 ^ r1
                if (r0 < 0) goto L3a
                r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
                long r0 = (long) r0
                r9 = r0
                r1 = r3
                r2 = r9
                goto Lbd
            L3a:
                int r1 = r3 + 1
                r3 = r2[r3]
                int r3 = r3 << 21
                r0 = r0 ^ r3
                if (r0 >= 0) goto L48
                r2 = -2080896(0xffffffffffe03f80, float:NaN)
                r0 = r0 ^ r2
                goto L26
            L48:
                long r3 = (long) r0
                int r0 = r1 + 1
                r1 = r2[r1]
                long r5 = (long) r1
                r1 = 28
                long r5 = r5 << r1
                long r3 = r3 ^ r5
                r5 = 0
                int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r1 < 0) goto L5f
                r1 = 266354560(0xfe03f80, double:1.315966377E-315)
            L5b:
                long r2 = r3 ^ r1
                r1 = r0
                goto Lbd
            L5f:
                int r1 = r0 + 1
                r0 = r2[r0]
                long r7 = (long) r0
                r0 = 35
                long r7 = r7 << r0
                long r3 = r3 ^ r7
                int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r0 >= 0) goto L74
                r5 = -34093383808(0xfffffff80fe03f80, double:NaN)
            L71:
                long r2 = r3 ^ r5
                goto Lbd
            L74:
                int r0 = r1 + 1
                r1 = r2[r1]
                long r7 = (long) r1
                r1 = 42
                long r7 = r7 << r1
                long r3 = r3 ^ r7
                int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r1 < 0) goto L87
                r1 = 4363953127296(0x3f80fe03f80, double:2.1560793202584E-311)
                goto L5b
            L87:
                int r1 = r0 + 1
                r0 = r2[r0]
                long r7 = (long) r0
                r0 = 49
                long r7 = r7 << r0
                long r3 = r3 ^ r7
                int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r0 >= 0) goto L9a
                r5 = -558586000294016(0xfffe03f80fe03f80, double:NaN)
                goto L71
            L9a:
                int r0 = r1 + 1
                r1 = r2[r1]
                long r7 = (long) r1
                r1 = 56
                long r7 = r7 << r1
                long r3 = r3 ^ r7
                r7 = 71499008037633920(0xfe03f80fe03f80, double:6.838959413692434E-304)
                long r3 = r3 ^ r7
                int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r1 >= 0) goto Lbb
                int r1 = r0 + 1
                r0 = r2[r0]
                long r7 = (long) r0
                int r0 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
                if (r0 >= 0) goto Lbc
            Lb6:
                long r0 = r11.readRawVarint64SlowPath()
                return r0
            Lbb:
                r1 = r0
            Lbc:
                r2 = r3
            Lbd:
                r11.pos = r1
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.ArrayDecoder.readRawVarint64():long");
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readRawVarint64SlowPath() throws InvalidProtocolBufferException {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                byte rawByte = readRawByte();
                j |= ((long) (rawByte & Ascii.DEL)) << i;
                if ((rawByte & UnsignedBytes.MAX_POWER_OF_TWO) == 0) {
                    return j;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readSFixed32() {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readSFixed64() {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readSInt32() {
            return CodedInputStream.decodeZigZag32(readRawVarint32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readSInt64() {
            return CodedInputStream.decodeZigZag64(readRawVarint64());
        }

        @Override // com.google.protobuf.CodedInputStream
        public String readString() throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (rawVarint32 > 0) {
                int i = this.limit;
                int i2 = this.pos;
                if (rawVarint32 <= i - i2) {
                    String str = new String(this.buffer, i2, rawVarint32, Internal.UTF_8);
                    this.pos += rawVarint32;
                    return str;
                }
            }
            if (rawVarint32 == 0) {
                return "";
            }
            if (rawVarint32 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.protobuf.CodedInputStream
        public String readStringRequireUtf8() throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (rawVarint32 > 0) {
                int i = this.limit;
                int i2 = this.pos;
                if (rawVarint32 <= i - i2) {
                    String strDecodeUtf8 = Utf8.decodeUtf8(this.buffer, i2, rawVarint32);
                    this.pos += rawVarint32;
                    return strDecodeUtf8;
                }
            }
            if (rawVarint32 == 0) {
                return "";
            }
            if (rawVarint32 <= 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readTag() throws InvalidProtocolBufferException {
            if (isAtEnd()) {
                this.lastTag = 0;
                return 0;
            }
            this.lastTag = readRawVarint32();
            int i = this.lastTag;
            if ((i >>> 3) != 0) {
                return i;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readUInt32() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readUInt64() {
            return readRawVarint64();
        }

        @Override // com.google.protobuf.CodedInputStream
        @Deprecated
        public void readUnknownGroup(int i, MessageLite.Builder builder) throws InvalidProtocolBufferException {
            readGroup(i, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.protobuf.CodedInputStream
        public void resetSizeCounter() {
            this.startPos = this.pos;
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean skipField(int i) throws InvalidProtocolBufferException {
            int i2 = i & 7;
            if (i2 == 0) {
                skipRawVarint();
                return true;
            }
            if (i2 == 1) {
                skipRawBytes(8);
                return true;
            }
            if (i2 == 2) {
                skipRawBytes(readRawVarint32());
                return true;
            }
            if (i2 == 3) {
                skipMessage();
                checkLastTagWas(((i >>> 3) << 3) | 4);
                return true;
            }
            if (i2 == 4) {
                return false;
            }
            if (i2 != 5) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            skipRawBytes(4);
            return true;
        }

        @Override // com.google.protobuf.CodedInputStream
        public void skipMessage() throws InvalidProtocolBufferException {
            int tag;
            do {
                tag = readTag();
                if (tag == 0) {
                    return;
                }
            } while (skipField(tag));
        }

        @Override // com.google.protobuf.CodedInputStream
        public void skipRawBytes(int i) throws InvalidProtocolBufferException {
            if (i >= 0) {
                int i2 = this.limit;
                int i3 = this.pos;
                if (i <= i2 - i3) {
                    this.pos = i3 + i;
                    return;
                }
            }
            if (i >= 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        @Override // com.google.protobuf.CodedInputStream
        public void skipMessage(CodedOutputStream codedOutputStream) throws InvalidProtocolBufferException {
            int tag;
            do {
                tag = readTag();
                if (tag == 0) {
                    return;
                }
            } while (skipField(tag, codedOutputStream));
        }

        @Override // com.google.protobuf.CodedInputStream
        public <T extends MessageLite> T readGroup(int i, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            int i2 = this.recursionDepth;
            if (i2 < this.recursionLimit) {
                this.recursionDepth = i2 + 1;
                T partialFrom = parser.parsePartialFrom(this, extensionRegistryLite);
                checkLastTagWas((i << 3) | 4);
                this.recursionDepth--;
                return partialFrom;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean skipField(int i, CodedOutputStream codedOutputStream) throws InvalidProtocolBufferException {
            int i2 = i & 7;
            if (i2 == 0) {
                long int64 = readInt64();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeUInt64NoTag(int64);
                return true;
            }
            if (i2 == 1) {
                long rawLittleEndian64 = readRawLittleEndian64();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeFixed64NoTag(rawLittleEndian64);
                return true;
            }
            if (i2 == 2) {
                ByteString bytes = readBytes();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeBytesNoTag(bytes);
                return true;
            }
            if (i2 == 3) {
                codedOutputStream.writeRawVarint32(i);
                skipMessage(codedOutputStream);
                int i3 = ((i >>> 3) << 3) | 4;
                checkLastTagWas(i3);
                codedOutputStream.writeRawVarint32(i3);
                return true;
            }
            if (i2 == 4) {
                return false;
            }
            if (i2 == 5) {
                int rawLittleEndian32 = readRawLittleEndian32();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeFixed32NoTag(rawLittleEndian32);
                return true;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }

        @Override // com.google.protobuf.CodedInputStream
        public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (this.recursionDepth < this.recursionLimit) {
                int iPushLimit = pushLimit(rawVarint32);
                this.recursionDepth++;
                T partialFrom = parser.parsePartialFrom(this, extensionRegistryLite);
                checkLastTagWas(0);
                this.recursionDepth--;
                popLimit(iPushLimit);
                return partialFrom;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }
    }

    private static final class IterableDirectByteBufferDecoder extends CodedInputStream {
        public int bufferSizeAfterCurrentLimit;
        public long currentAddress;
        public ByteBuffer currentByteBuffer;
        public long currentByteBufferLimit;
        public long currentByteBufferPos;
        public long currentByteBufferStartPos;
        public int currentLimit;
        public boolean enableAliasing;
        public boolean immutable;
        public Iterable<ByteBuffer> input;
        public Iterator<ByteBuffer> iterator;
        public int lastTag;
        public int startOffset;
        public int totalBufferSize;
        public int totalBytesRead;

        public IterableDirectByteBufferDecoder(Iterable<ByteBuffer> iterable, int i, boolean z) {
            this.currentLimit = Integer.MAX_VALUE;
            this.totalBufferSize = i;
            this.input = iterable;
            this.iterator = this.input.iterator();
            this.immutable = z;
            this.totalBytesRead = 0;
            this.startOffset = 0;
            if (i != 0) {
                tryGetNextByteBuffer();
                return;
            }
            this.currentByteBuffer = Internal.EMPTY_BYTE_BUFFER;
            this.currentByteBufferPos = 0L;
            this.currentByteBufferStartPos = 0L;
            this.currentByteBufferLimit = 0L;
            this.currentAddress = 0L;
        }

        private long currentRemaining() {
            return this.currentByteBufferLimit - this.currentByteBufferPos;
        }

        private void getNextByteBuffer() throws InvalidProtocolBufferException {
            if (!this.iterator.hasNext()) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            tryGetNextByteBuffer();
        }

        private void readRawBytesTo(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException {
            if (i2 < 0 || i2 > remaining()) {
                if (i2 > 0) {
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
                if (i2 != 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                return;
            }
            int i3 = i2;
            while (i3 > 0) {
                if (currentRemaining() == 0) {
                    getNextByteBuffer();
                }
                int iMin = Math.min(i3, (int) currentRemaining());
                long j = iMin;
                UnsafeUtil.copyMemory(this.currentByteBufferPos, bArr, (i2 - i3) + i, j);
                i3 -= iMin;
                this.currentByteBufferPos += j;
            }
        }

        private void recomputeBufferSizeAfterLimit() {
            this.totalBufferSize += this.bufferSizeAfterCurrentLimit;
            int i = this.totalBufferSize;
            int i2 = i - this.startOffset;
            int i3 = this.currentLimit;
            if (i2 <= i3) {
                this.bufferSizeAfterCurrentLimit = 0;
            } else {
                this.bufferSizeAfterCurrentLimit = i2 - i3;
                this.totalBufferSize = i - this.bufferSizeAfterCurrentLimit;
            }
        }

        private int remaining() {
            return (int) ((((long) (this.totalBufferSize - this.totalBytesRead)) - this.currentByteBufferPos) + this.currentByteBufferStartPos);
        }

        private void skipRawVarint() throws InvalidProtocolBufferException {
            for (int i = 0; i < 10; i++) {
                if (readRawByte() >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private ByteBuffer slice(int i, int i2) {
            int iPosition = this.currentByteBuffer.position();
            int iLimit = this.currentByteBuffer.limit();
            try {
                try {
                    this.currentByteBuffer.position(i);
                    this.currentByteBuffer.limit(i2);
                    ByteBuffer byteBufferSlice = this.currentByteBuffer.slice();
                    this.currentByteBuffer.position(iPosition);
                    this.currentByteBuffer.limit(iLimit);
                    return byteBufferSlice;
                } catch (IllegalArgumentException unused) {
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
            } catch (Throwable th) {
                this.currentByteBuffer.position(iPosition);
                this.currentByteBuffer.limit(iLimit);
                throw th;
            }
        }

        private void tryGetNextByteBuffer() {
            this.currentByteBuffer = this.iterator.next();
            this.totalBytesRead += (int) (this.currentByteBufferPos - this.currentByteBufferStartPos);
            this.currentByteBufferPos = this.currentByteBuffer.position();
            this.currentByteBufferStartPos = this.currentByteBufferPos;
            this.currentByteBufferLimit = this.currentByteBuffer.limit();
            this.currentAddress = UnsafeUtil.addressOffset(this.currentByteBuffer);
            long j = this.currentByteBufferPos;
            long j2 = this.currentAddress;
            this.currentByteBufferPos = j + j2;
            this.currentByteBufferStartPos += j2;
            this.currentByteBufferLimit += j2;
        }

        @Override // com.google.protobuf.CodedInputStream
        public void checkLastTagWas(int i) throws InvalidProtocolBufferException {
            if (this.lastTag != i) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        @Override // com.google.protobuf.CodedInputStream
        public void enableAliasing(boolean z) {
            this.enableAliasing = z;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getBytesUntilLimit() {
            int i = this.currentLimit;
            if (i == Integer.MAX_VALUE) {
                return -1;
            }
            return i - getTotalBytesRead();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getLastTag() {
            return this.lastTag;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getTotalBytesRead() {
            return (int) ((((long) (this.totalBytesRead - this.startOffset)) + this.currentByteBufferPos) - this.currentByteBufferStartPos);
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean isAtEnd() {
            return (((long) this.totalBytesRead) + this.currentByteBufferPos) - this.currentByteBufferStartPos == ((long) this.totalBufferSize);
        }

        @Override // com.google.protobuf.CodedInputStream
        public void popLimit(int i) {
            this.currentLimit = i;
            recomputeBufferSizeAfterLimit();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int pushLimit(int i) throws InvalidProtocolBufferException {
            if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int totalBytesRead = getTotalBytesRead() + i;
            int i2 = this.currentLimit;
            if (totalBytesRead > i2) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.currentLimit = totalBytesRead;
            recomputeBufferSizeAfterLimit();
            return i2;
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean readBool() {
            return readRawVarint64() != 0;
        }

        @Override // com.google.protobuf.CodedInputStream
        public byte[] readByteArray() {
            return readRawBytes(readRawVarint32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public ByteBuffer readByteBuffer() throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (rawVarint32 > 0) {
                long j = rawVarint32;
                if (j <= currentRemaining()) {
                    if (this.immutable || !this.enableAliasing) {
                        byte[] bArr = new byte[rawVarint32];
                        UnsafeUtil.copyMemory(this.currentByteBufferPos, bArr, 0L, j);
                        this.currentByteBufferPos += j;
                        return ByteBuffer.wrap(bArr);
                    }
                    this.currentByteBufferPos += j;
                    long j2 = this.currentByteBufferPos;
                    long j3 = this.currentAddress;
                    return slice((int) ((j2 - j3) - j), (int) (j2 - j3));
                }
            }
            if (rawVarint32 > 0 && rawVarint32 <= remaining()) {
                byte[] bArr2 = new byte[rawVarint32];
                readRawBytesTo(bArr2, 0, rawVarint32);
                return ByteBuffer.wrap(bArr2);
            }
            if (rawVarint32 == 0) {
                return Internal.EMPTY_BYTE_BUFFER;
            }
            if (rawVarint32 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.protobuf.CodedInputStream
        public ByteString readBytes() throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (rawVarint32 > 0) {
                long j = rawVarint32;
                long j2 = this.currentByteBufferLimit;
                long j3 = this.currentByteBufferPos;
                if (j <= j2 - j3) {
                    if (this.immutable && this.enableAliasing) {
                        int i = (int) (j3 - this.currentAddress);
                        ByteString byteStringWrap = ByteString.wrap(slice(i, rawVarint32 + i));
                        this.currentByteBufferPos += j;
                        return byteStringWrap;
                    }
                    byte[] bArr = new byte[rawVarint32];
                    UnsafeUtil.copyMemory(this.currentByteBufferPos, bArr, 0L, j);
                    this.currentByteBufferPos += j;
                    return ByteString.wrap(bArr);
                }
            }
            if (rawVarint32 <= 0 || rawVarint32 > remaining()) {
                if (rawVarint32 == 0) {
                    return ByteString.EMPTY;
                }
                if (rawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            if (!this.immutable || !this.enableAliasing) {
                byte[] bArr2 = new byte[rawVarint32];
                readRawBytesTo(bArr2, 0, rawVarint32);
                return ByteString.wrap(bArr2);
            }
            ArrayList arrayList = new ArrayList();
            while (rawVarint32 > 0) {
                if (currentRemaining() == 0) {
                    getNextByteBuffer();
                }
                int iMin = Math.min(rawVarint32, (int) currentRemaining());
                int i2 = (int) (this.currentByteBufferPos - this.currentAddress);
                arrayList.add(ByteString.wrap(slice(i2, i2 + iMin)));
                rawVarint32 -= iMin;
                this.currentByteBufferPos += (long) iMin;
            }
            return ByteString.copyFrom(arrayList);
        }

        @Override // com.google.protobuf.CodedInputStream
        public double readDouble() {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readEnum() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readFixed32() {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readFixed64() {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public float readFloat() {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public void readGroup(int i, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            int i2 = this.recursionDepth;
            if (i2 >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            this.recursionDepth = i2 + 1;
            builder.mergeFrom(this, extensionRegistryLite);
            checkLastTagWas((i << 3) | 4);
            this.recursionDepth--;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readInt32() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readInt64() {
            return readRawVarint64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (this.recursionDepth >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            int iPushLimit = pushLimit(rawVarint32);
            this.recursionDepth++;
            builder.mergeFrom(this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            popLimit(iPushLimit);
        }

        @Override // com.google.protobuf.CodedInputStream
        public byte readRawByte() throws InvalidProtocolBufferException {
            if (currentRemaining() == 0) {
                getNextByteBuffer();
            }
            long j = this.currentByteBufferPos;
            this.currentByteBufferPos = 1 + j;
            return UnsafeUtil.getByte(j);
        }

        @Override // com.google.protobuf.CodedInputStream
        public byte[] readRawBytes(int i) throws InvalidProtocolBufferException {
            if (i >= 0) {
                long j = i;
                if (j <= currentRemaining()) {
                    byte[] bArr = new byte[i];
                    UnsafeUtil.copyMemory(this.currentByteBufferPos, bArr, 0L, j);
                    this.currentByteBufferPos += j;
                    return bArr;
                }
            }
            if (i >= 0 && i <= remaining()) {
                byte[] bArr2 = new byte[i];
                readRawBytesTo(bArr2, 0, i);
                return bArr2;
            }
            if (i > 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            if (i == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readRawLittleEndian32() {
            if (currentRemaining() < 4) {
                return (readRawByte() & 255) | ((readRawByte() & 255) << 8) | ((readRawByte() & 255) << 16) | ((readRawByte() & 255) << 24);
            }
            long j = this.currentByteBufferPos;
            this.currentByteBufferPos = 4 + j;
            return ((UnsafeUtil.getByte(j + 3) & 255) << 24) | (UnsafeUtil.getByte(j) & 255) | ((UnsafeUtil.getByte(1 + j) & 255) << 8) | ((UnsafeUtil.getByte(2 + j) & 255) << 16);
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readRawLittleEndian64() throws InvalidProtocolBufferException {
            long rawByte;
            byte rawByte2;
            if (currentRemaining() >= 8) {
                long j = this.currentByteBufferPos;
                this.currentByteBufferPos = 8 + j;
                rawByte = (((long) UnsafeUtil.getByte(j)) & 255) | ((((long) UnsafeUtil.getByte(1 + j)) & 255) << 8) | ((((long) UnsafeUtil.getByte(2 + j)) & 255) << 16) | ((((long) UnsafeUtil.getByte(3 + j)) & 255) << 24) | ((((long) UnsafeUtil.getByte(4 + j)) & 255) << 32) | ((((long) UnsafeUtil.getByte(5 + j)) & 255) << 40) | ((((long) UnsafeUtil.getByte(6 + j)) & 255) << 48);
                rawByte2 = UnsafeUtil.getByte(j + 7);
            } else {
                rawByte = (((long) readRawByte()) & 255) | ((((long) readRawByte()) & 255) << 8) | ((((long) readRawByte()) & 255) << 16) | ((((long) readRawByte()) & 255) << 24) | ((((long) readRawByte()) & 255) << 32) | ((((long) readRawByte()) & 255) << 40) | ((((long) readRawByte()) & 255) << 48);
                rawByte2 = readRawByte();
            }
            return ((((long) rawByte2) & 255) << 56) | rawByte;
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x0088, code lost:
        
            if (com.google.protobuf.UnsafeUtil.getByte(r4) < 0) goto L34;
         */
        @Override // com.google.protobuf.CodedInputStream
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int readRawVarint32() {
            /*
                r10 = this;
                long r0 = r10.currentByteBufferPos
                long r2 = r10.currentByteBufferLimit
                int r2 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
                if (r2 != 0) goto La
                goto L8a
            La:
                r2 = 1
                long r4 = r0 + r2
                byte r0 = com.google.protobuf.UnsafeUtil.getByte(r0)
                if (r0 < 0) goto L1a
                long r4 = r10.currentByteBufferPos
                long r4 = r4 + r2
                r10.currentByteBufferPos = r4
                return r0
            L1a:
                long r6 = r10.currentByteBufferLimit
                long r8 = r10.currentByteBufferPos
                long r6 = r6 - r8
                r8 = 10
                int r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r1 >= 0) goto L26
                goto L8a
            L26:
                long r6 = r4 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r4)
                int r1 = r1 << 7
                r0 = r0 ^ r1
                if (r0 >= 0) goto L34
                r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
                goto L90
            L34:
                long r4 = r6 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r6)
                int r1 = r1 << 14
                r0 = r0 ^ r1
                if (r0 < 0) goto L43
                r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            L41:
                r6 = r4
                goto L90
            L43:
                long r6 = r4 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r4)
                int r1 = r1 << 21
                r0 = r0 ^ r1
                if (r0 >= 0) goto L53
                r1 = -2080896(0xffffffffffe03f80, float:NaN)
                r0 = r0 ^ r1
                goto L90
            L53:
                long r4 = r6 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r6)
                int r6 = r1 << 28
                r0 = r0 ^ r6
                r6 = 266354560(0xfe03f80, float:2.2112565E-29)
                r0 = r0 ^ r6
                if (r1 >= 0) goto L41
                long r6 = r4 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r4)
                if (r1 >= 0) goto L90
                long r4 = r6 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r6)
                if (r1 >= 0) goto L41
                long r6 = r4 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r4)
                if (r1 >= 0) goto L90
                long r4 = r6 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r6)
                if (r1 >= 0) goto L41
                long r6 = r4 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r4)
                if (r1 >= 0) goto L90
            L8a:
                long r0 = r10.readRawVarint64SlowPath()
                int r0 = (int) r0
                return r0
            L90:
                r10.currentByteBufferPos = r6
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.IterableDirectByteBufferDecoder.readRawVarint32():int");
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readRawVarint64() {
            long j;
            long j2;
            long j3;
            long j4;
            int i;
            long j5 = this.currentByteBufferPos;
            if (this.currentByteBufferLimit != j5) {
                long j6 = j5 + 1;
                byte b2 = UnsafeUtil.getByte(j5);
                if (b2 >= 0) {
                    this.currentByteBufferPos++;
                    return b2;
                }
                if (this.currentByteBufferLimit - this.currentByteBufferPos >= 10) {
                    long j7 = j6 + 1;
                    int i2 = b2 ^ (UnsafeUtil.getByte(j6) << 7);
                    if (i2 >= 0) {
                        j = j7 + 1;
                        int i3 = i2 ^ (UnsafeUtil.getByte(j7) << Ascii.SO);
                        if (i3 >= 0) {
                            j2 = i3 ^ 16256;
                        } else {
                            j7 = j + 1;
                            int i4 = i3 ^ (UnsafeUtil.getByte(j) << Ascii.NAK);
                            if (i4 < 0) {
                                i = i4 ^ (-2080896);
                            } else {
                                j = j7 + 1;
                                long j8 = ((long) i4) ^ (((long) UnsafeUtil.getByte(j7)) << 28);
                                if (j8 >= 0) {
                                    j4 = 266354560;
                                } else {
                                    long j9 = j + 1;
                                    long j10 = j8 ^ (((long) UnsafeUtil.getByte(j)) << 35);
                                    if (j10 < 0) {
                                        j3 = -34093383808L;
                                    } else {
                                        j = j9 + 1;
                                        j8 = j10 ^ (((long) UnsafeUtil.getByte(j9)) << 42);
                                        if (j8 >= 0) {
                                            j4 = 4363953127296L;
                                        } else {
                                            j9 = j + 1;
                                            j10 = j8 ^ (((long) UnsafeUtil.getByte(j)) << 49);
                                            if (j10 < 0) {
                                                j3 = -558586000294016L;
                                            } else {
                                                j = j9 + 1;
                                                j2 = (j10 ^ (((long) UnsafeUtil.getByte(j9)) << 56)) ^ 71499008037633920L;
                                                if (j2 < 0) {
                                                    long j11 = 1 + j;
                                                    if (UnsafeUtil.getByte(j) >= 0) {
                                                        j = j11;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    j2 = j10 ^ j3;
                                    j = j9;
                                }
                                j2 = j8 ^ j4;
                            }
                        }
                        this.currentByteBufferPos = j;
                        return j2;
                    }
                    i = i2 ^ (-128);
                    j2 = i;
                    j = j7;
                    this.currentByteBufferPos = j;
                    return j2;
                }
            }
            return readRawVarint64SlowPath();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readRawVarint64SlowPath() throws InvalidProtocolBufferException {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                byte rawByte = readRawByte();
                j |= ((long) (rawByte & Ascii.DEL)) << i;
                if ((rawByte & UnsignedBytes.MAX_POWER_OF_TWO) == 0) {
                    return j;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readSFixed32() {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readSFixed64() {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readSInt32() {
            return CodedInputStream.decodeZigZag32(readRawVarint32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readSInt64() {
            return CodedInputStream.decodeZigZag64(readRawVarint64());
        }

        @Override // com.google.protobuf.CodedInputStream
        public String readString() throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (rawVarint32 > 0) {
                long j = rawVarint32;
                long j2 = this.currentByteBufferLimit;
                long j3 = this.currentByteBufferPos;
                if (j <= j2 - j3) {
                    byte[] bArr = new byte[rawVarint32];
                    UnsafeUtil.copyMemory(j3, bArr, 0L, j);
                    String str = new String(bArr, Internal.UTF_8);
                    this.currentByteBufferPos += j;
                    return str;
                }
            }
            if (rawVarint32 > 0 && rawVarint32 <= remaining()) {
                byte[] bArr2 = new byte[rawVarint32];
                readRawBytesTo(bArr2, 0, rawVarint32);
                return new String(bArr2, Internal.UTF_8);
            }
            if (rawVarint32 == 0) {
                return "";
            }
            if (rawVarint32 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.protobuf.CodedInputStream
        public String readStringRequireUtf8() throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (rawVarint32 > 0) {
                long j = rawVarint32;
                long j2 = this.currentByteBufferLimit;
                long j3 = this.currentByteBufferPos;
                if (j <= j2 - j3) {
                    String strDecodeUtf8 = Utf8.decodeUtf8(this.currentByteBuffer, (int) (j3 - this.currentByteBufferStartPos), rawVarint32);
                    this.currentByteBufferPos += j;
                    return strDecodeUtf8;
                }
            }
            if (rawVarint32 >= 0 && rawVarint32 <= remaining()) {
                byte[] bArr = new byte[rawVarint32];
                readRawBytesTo(bArr, 0, rawVarint32);
                return Utf8.decodeUtf8(bArr, 0, rawVarint32);
            }
            if (rawVarint32 == 0) {
                return "";
            }
            if (rawVarint32 <= 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readTag() throws InvalidProtocolBufferException {
            if (isAtEnd()) {
                this.lastTag = 0;
                return 0;
            }
            this.lastTag = readRawVarint32();
            int i = this.lastTag;
            if ((i >>> 3) != 0) {
                return i;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readUInt32() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readUInt64() {
            return readRawVarint64();
        }

        @Override // com.google.protobuf.CodedInputStream
        @Deprecated
        public void readUnknownGroup(int i, MessageLite.Builder builder) throws InvalidProtocolBufferException {
            readGroup(i, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.protobuf.CodedInputStream
        public void resetSizeCounter() {
            this.startOffset = (int) ((((long) this.totalBytesRead) + this.currentByteBufferPos) - this.currentByteBufferStartPos);
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean skipField(int i) throws InvalidProtocolBufferException {
            int i2 = i & 7;
            if (i2 == 0) {
                skipRawVarint();
                return true;
            }
            if (i2 == 1) {
                skipRawBytes(8);
                return true;
            }
            if (i2 == 2) {
                skipRawBytes(readRawVarint32());
                return true;
            }
            if (i2 == 3) {
                skipMessage();
                checkLastTagWas(((i >>> 3) << 3) | 4);
                return true;
            }
            if (i2 == 4) {
                return false;
            }
            if (i2 != 5) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            skipRawBytes(4);
            return true;
        }

        @Override // com.google.protobuf.CodedInputStream
        public void skipMessage() throws InvalidProtocolBufferException {
            int tag;
            do {
                tag = readTag();
                if (tag == 0) {
                    return;
                }
            } while (skipField(tag));
        }

        @Override // com.google.protobuf.CodedInputStream
        public void skipRawBytes(int i) throws InvalidProtocolBufferException {
            if (i < 0 || i > (((long) (this.totalBufferSize - this.totalBytesRead)) - this.currentByteBufferPos) + this.currentByteBufferStartPos) {
                if (i >= 0) {
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
                throw InvalidProtocolBufferException.negativeSize();
            }
            while (i > 0) {
                if (currentRemaining() == 0) {
                    getNextByteBuffer();
                }
                int iMin = Math.min(i, (int) currentRemaining());
                i -= iMin;
                this.currentByteBufferPos += (long) iMin;
            }
        }

        @Override // com.google.protobuf.CodedInputStream
        public void skipMessage(CodedOutputStream codedOutputStream) throws InvalidProtocolBufferException {
            int tag;
            do {
                tag = readTag();
                if (tag == 0) {
                    return;
                }
            } while (skipField(tag, codedOutputStream));
        }

        @Override // com.google.protobuf.CodedInputStream
        public <T extends MessageLite> T readGroup(int i, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            int i2 = this.recursionDepth;
            if (i2 < this.recursionLimit) {
                this.recursionDepth = i2 + 1;
                T partialFrom = parser.parsePartialFrom(this, extensionRegistryLite);
                checkLastTagWas((i << 3) | 4);
                this.recursionDepth--;
                return partialFrom;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean skipField(int i, CodedOutputStream codedOutputStream) throws InvalidProtocolBufferException {
            int i2 = i & 7;
            if (i2 == 0) {
                long int64 = readInt64();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeUInt64NoTag(int64);
                return true;
            }
            if (i2 == 1) {
                long rawLittleEndian64 = readRawLittleEndian64();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeFixed64NoTag(rawLittleEndian64);
                return true;
            }
            if (i2 == 2) {
                ByteString bytes = readBytes();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeBytesNoTag(bytes);
                return true;
            }
            if (i2 == 3) {
                codedOutputStream.writeRawVarint32(i);
                skipMessage(codedOutputStream);
                int i3 = ((i >>> 3) << 3) | 4;
                checkLastTagWas(i3);
                codedOutputStream.writeRawVarint32(i3);
                return true;
            }
            if (i2 == 4) {
                return false;
            }
            if (i2 == 5) {
                int rawLittleEndian32 = readRawLittleEndian32();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeFixed32NoTag(rawLittleEndian32);
                return true;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }

        @Override // com.google.protobuf.CodedInputStream
        public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (this.recursionDepth < this.recursionLimit) {
                int iPushLimit = pushLimit(rawVarint32);
                this.recursionDepth++;
                T partialFrom = parser.parsePartialFrom(this, extensionRegistryLite);
                checkLastTagWas(0);
                this.recursionDepth--;
                popLimit(iPushLimit);
                return partialFrom;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }
    }

    private static final class StreamDecoder extends CodedInputStream {
        public final byte[] buffer;
        public int bufferSize;
        public int bufferSizeAfterLimit;
        public int currentLimit;
        public final InputStream input;
        public int lastTag;
        public int pos;
        public RefillCallback refillCallback;
        public int totalBytesRetired;

        private interface RefillCallback {
            void onRefill();
        }

        private class SkippedDataSink implements RefillCallback {
            public ByteArrayOutputStream byteArrayStream;
            public int lastPos;

            public SkippedDataSink() {
                this.lastPos = StreamDecoder.this.pos;
            }

            public ByteBuffer getSkippedData() {
                ByteArrayOutputStream byteArrayOutputStream = this.byteArrayStream;
                if (byteArrayOutputStream != null) {
                    StreamDecoder streamDecoder = StreamDecoder.this;
                    byteArrayOutputStream.write(streamDecoder.buffer, this.lastPos, streamDecoder.pos);
                    return ByteBuffer.wrap(this.byteArrayStream.toByteArray());
                }
                StreamDecoder streamDecoder2 = StreamDecoder.this;
                byte[] bArr = streamDecoder2.buffer;
                int i = this.lastPos;
                return ByteBuffer.wrap(bArr, i, streamDecoder2.pos - i);
            }

            @Override // com.google.protobuf.CodedInputStream.StreamDecoder.RefillCallback
            public void onRefill() {
                if (this.byteArrayStream == null) {
                    this.byteArrayStream = new ByteArrayOutputStream();
                }
                ByteArrayOutputStream byteArrayOutputStream = this.byteArrayStream;
                StreamDecoder streamDecoder = StreamDecoder.this;
                byte[] bArr = streamDecoder.buffer;
                int i = this.lastPos;
                byteArrayOutputStream.write(bArr, i, streamDecoder.pos - i);
                this.lastPos = 0;
            }
        }

        public StreamDecoder(InputStream inputStream, int i) {
            this.currentLimit = Integer.MAX_VALUE;
            this.refillCallback = null;
            Internal.checkNotNull(inputStream, "input");
            this.input = inputStream;
            this.buffer = new byte[i];
            this.bufferSize = 0;
            this.pos = 0;
            this.totalBytesRetired = 0;
        }

        public static int available(InputStream inputStream) throws InvalidProtocolBufferException {
            try {
                return inputStream.available();
            } catch (InvalidProtocolBufferException e2) {
                e2.setThrownFromInputStream();
                throw e2;
            }
        }

        public static int read(InputStream inputStream, byte[] bArr, int i, int i2) throws InvalidProtocolBufferException {
            try {
                return inputStream.read(bArr, i, i2);
            } catch (InvalidProtocolBufferException e2) {
                e2.setThrownFromInputStream();
                throw e2;
            }
        }

        private ByteString readBytesSlowPath(int i) throws IOException {
            byte[] rawBytesSlowPathOneChunk = readRawBytesSlowPathOneChunk(i);
            if (rawBytesSlowPathOneChunk != null) {
                return ByteString.copyFrom(rawBytesSlowPathOneChunk);
            }
            int i2 = this.pos;
            int i3 = this.bufferSize;
            int length = i3 - i2;
            this.totalBytesRetired += i3;
            this.pos = 0;
            this.bufferSize = 0;
            List<byte[]> rawBytesSlowPathRemainingChunks = readRawBytesSlowPathRemainingChunks(i - length);
            byte[] bArr = new byte[i];
            System.arraycopy(this.buffer, i2, bArr, 0, length);
            for (byte[] bArr2 : rawBytesSlowPathRemainingChunks) {
                System.arraycopy(bArr2, 0, bArr, length, bArr2.length);
                length += bArr2.length;
            }
            return ByteString.wrap(bArr);
        }

        private byte[] readRawBytesSlowPath(int i, boolean z) throws IOException {
            byte[] rawBytesSlowPathOneChunk = readRawBytesSlowPathOneChunk(i);
            if (rawBytesSlowPathOneChunk != null) {
                return z ? (byte[]) rawBytesSlowPathOneChunk.clone() : rawBytesSlowPathOneChunk;
            }
            int i2 = this.pos;
            int i3 = this.bufferSize;
            int length = i3 - i2;
            this.totalBytesRetired += i3;
            this.pos = 0;
            this.bufferSize = 0;
            List<byte[]> rawBytesSlowPathRemainingChunks = readRawBytesSlowPathRemainingChunks(i - length);
            byte[] bArr = new byte[i];
            System.arraycopy(this.buffer, i2, bArr, 0, length);
            for (byte[] bArr2 : rawBytesSlowPathRemainingChunks) {
                System.arraycopy(bArr2, 0, bArr, length, bArr2.length);
                length += bArr2.length;
            }
            return bArr;
        }

        private byte[] readRawBytesSlowPathOneChunk(int i) throws InvalidProtocolBufferException {
            if (i == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            }
            if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int i2 = this.totalBytesRetired;
            int i3 = this.pos;
            int i4 = i2 + i3 + i;
            if (i4 - this.sizeLimit > 0) {
                throw InvalidProtocolBufferException.sizeLimitExceeded();
            }
            int i5 = this.currentLimit;
            if (i4 > i5) {
                skipRawBytes((i5 - i2) - i3);
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            int i6 = this.bufferSize - i3;
            int i7 = i - i6;
            if (i7 >= 4096 && i7 > available(this.input)) {
                return null;
            }
            byte[] bArr = new byte[i];
            System.arraycopy(this.buffer, this.pos, bArr, 0, i6);
            this.totalBytesRetired += this.bufferSize;
            this.pos = 0;
            this.bufferSize = 0;
            while (i6 < bArr.length) {
                int i8 = read(this.input, bArr, i6, i - i6);
                if (i8 == -1) {
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
                this.totalBytesRetired += i8;
                i6 += i8;
            }
            return bArr;
        }

        private List<byte[]> readRawBytesSlowPathRemainingChunks(int i) throws IOException {
            ArrayList arrayList = new ArrayList();
            while (i > 0) {
                byte[] bArr = new byte[Math.min(i, 4096)];
                int i2 = 0;
                while (i2 < bArr.length) {
                    int i3 = this.input.read(bArr, i2, bArr.length - i2);
                    if (i3 == -1) {
                        throw InvalidProtocolBufferException.truncatedMessage();
                    }
                    this.totalBytesRetired += i3;
                    i2 += i3;
                }
                i -= bArr.length;
                arrayList.add(bArr);
            }
            return arrayList;
        }

        private void recomputeBufferSizeAfterLimit() {
            this.bufferSize += this.bufferSizeAfterLimit;
            int i = this.totalBytesRetired;
            int i2 = this.bufferSize;
            int i3 = i + i2;
            int i4 = this.currentLimit;
            if (i3 <= i4) {
                this.bufferSizeAfterLimit = 0;
            } else {
                this.bufferSizeAfterLimit = i3 - i4;
                this.bufferSize = i2 - this.bufferSizeAfterLimit;
            }
        }

        private void refillBuffer(int i) throws InvalidProtocolBufferException {
            if (tryRefillBuffer(i)) {
                return;
            }
            if (i <= (this.sizeLimit - this.totalBytesRetired) - this.pos) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            throw InvalidProtocolBufferException.sizeLimitExceeded();
        }

        public static long skip(InputStream inputStream, long j) throws InvalidProtocolBufferException {
            try {
                return inputStream.skip(j);
            } catch (InvalidProtocolBufferException e2) {
                e2.setThrownFromInputStream();
                throw e2;
            }
        }

        private void skipRawBytesSlowPath(int i) throws InvalidProtocolBufferException {
            if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int i2 = this.totalBytesRetired;
            int i3 = this.pos;
            int i4 = i2 + i3 + i;
            int i5 = this.currentLimit;
            if (i4 > i5) {
                skipRawBytes((i5 - i2) - i3);
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            int i6 = 0;
            if (this.refillCallback == null) {
                this.totalBytesRetired = i2 + i3;
                int i7 = this.bufferSize - i3;
                this.bufferSize = 0;
                this.pos = 0;
                i6 = i7;
                while (i6 < i) {
                    try {
                        long j = i - i6;
                        long jSkip = skip(this.input, j);
                        if (jSkip < 0 || jSkip > j) {
                            throw new IllegalStateException(this.input.getClass() + "#skip returned invalid result: " + jSkip + "\nThe InputStream implementation is buggy.");
                        }
                        if (jSkip == 0) {
                            break;
                        } else {
                            i6 += (int) jSkip;
                        }
                    } catch (Throwable th) {
                        this.totalBytesRetired += i6;
                        recomputeBufferSizeAfterLimit();
                        throw th;
                    }
                }
                this.totalBytesRetired += i6;
                recomputeBufferSizeAfterLimit();
            }
            if (i6 >= i) {
                return;
            }
            int i8 = this.bufferSize;
            int i9 = i8 - this.pos;
            this.pos = i8;
            refillBuffer(1);
            while (true) {
                int i10 = i - i9;
                int i11 = this.bufferSize;
                if (i10 <= i11) {
                    this.pos = i10;
                    return;
                } else {
                    i9 += i11;
                    this.pos = i11;
                    refillBuffer(1);
                }
            }
        }

        private void skipRawVarint() throws InvalidProtocolBufferException {
            if (this.bufferSize - this.pos >= 10) {
                skipRawVarintFastPath();
            } else {
                skipRawVarintSlowPath();
            }
        }

        private void skipRawVarintFastPath() throws InvalidProtocolBufferException {
            for (int i = 0; i < 10; i++) {
                byte[] bArr = this.buffer;
                int i2 = this.pos;
                this.pos = i2 + 1;
                if (bArr[i2] >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void skipRawVarintSlowPath() throws InvalidProtocolBufferException {
            for (int i = 0; i < 10; i++) {
                if (readRawByte() >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private boolean tryRefillBuffer(int i) throws InvalidProtocolBufferException {
            int i2 = this.pos;
            if (i2 + i <= this.bufferSize) {
                throw new IllegalStateException(a.g("refillBuffer() called when ", i, " bytes were already available in buffer"));
            }
            int i3 = this.sizeLimit;
            int i4 = this.totalBytesRetired;
            if (i > (i3 - i4) - i2 || i4 + i2 + i > this.currentLimit) {
                return false;
            }
            RefillCallback refillCallback = this.refillCallback;
            if (refillCallback != null) {
                refillCallback.onRefill();
            }
            int i5 = this.pos;
            if (i5 > 0) {
                int i6 = this.bufferSize;
                if (i6 > i5) {
                    byte[] bArr = this.buffer;
                    System.arraycopy(bArr, i5, bArr, 0, i6 - i5);
                }
                this.totalBytesRetired += i5;
                this.bufferSize -= i5;
                this.pos = 0;
            }
            InputStream inputStream = this.input;
            byte[] bArr2 = this.buffer;
            int i7 = this.bufferSize;
            int i8 = read(inputStream, bArr2, i7, Math.min(bArr2.length - i7, (this.sizeLimit - this.totalBytesRetired) - i7));
            if (i8 == 0 || i8 < -1 || i8 > this.buffer.length) {
                throw new IllegalStateException(this.input.getClass() + "#read(byte[]) returned invalid result: " + i8 + "\nThe InputStream implementation is buggy.");
            }
            if (i8 <= 0) {
                return false;
            }
            this.bufferSize += i8;
            recomputeBufferSizeAfterLimit();
            if (this.bufferSize >= i) {
                return true;
            }
            return tryRefillBuffer(i);
        }

        @Override // com.google.protobuf.CodedInputStream
        public void checkLastTagWas(int i) throws InvalidProtocolBufferException {
            if (this.lastTag != i) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        @Override // com.google.protobuf.CodedInputStream
        public void enableAliasing(boolean z) {
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getBytesUntilLimit() {
            int i = this.currentLimit;
            if (i == Integer.MAX_VALUE) {
                return -1;
            }
            return i - (this.totalBytesRetired + this.pos);
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getLastTag() {
            return this.lastTag;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getTotalBytesRead() {
            return this.totalBytesRetired + this.pos;
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean isAtEnd() {
            return this.pos == this.bufferSize && !tryRefillBuffer(1);
        }

        @Override // com.google.protobuf.CodedInputStream
        public void popLimit(int i) {
            this.currentLimit = i;
            recomputeBufferSizeAfterLimit();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int pushLimit(int i) throws InvalidProtocolBufferException {
            if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int i2 = this.totalBytesRetired + this.pos + i;
            int i3 = this.currentLimit;
            if (i2 > i3) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.currentLimit = i2;
            recomputeBufferSizeAfterLimit();
            return i3;
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean readBool() {
            return readRawVarint64() != 0;
        }

        @Override // com.google.protobuf.CodedInputStream
        public byte[] readByteArray() {
            int rawVarint32 = readRawVarint32();
            int i = this.bufferSize;
            int i2 = this.pos;
            if (rawVarint32 > i - i2 || rawVarint32 <= 0) {
                return readRawBytesSlowPath(rawVarint32, false);
            }
            byte[] bArrCopyOfRange = Arrays.copyOfRange(this.buffer, i2, i2 + rawVarint32);
            this.pos += rawVarint32;
            return bArrCopyOfRange;
        }

        @Override // com.google.protobuf.CodedInputStream
        public ByteBuffer readByteBuffer() {
            int rawVarint32 = readRawVarint32();
            int i = this.bufferSize;
            int i2 = this.pos;
            if (rawVarint32 > i - i2 || rawVarint32 <= 0) {
                return rawVarint32 == 0 ? Internal.EMPTY_BYTE_BUFFER : ByteBuffer.wrap(readRawBytesSlowPath(rawVarint32, true));
            }
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(Arrays.copyOfRange(this.buffer, i2, i2 + rawVarint32));
            this.pos += rawVarint32;
            return byteBufferWrap;
        }

        @Override // com.google.protobuf.CodedInputStream
        public ByteString readBytes() {
            int rawVarint32 = readRawVarint32();
            int i = this.bufferSize;
            int i2 = this.pos;
            if (rawVarint32 > i - i2 || rawVarint32 <= 0) {
                return rawVarint32 == 0 ? ByteString.EMPTY : readBytesSlowPath(rawVarint32);
            }
            ByteString byteStringCopyFrom = ByteString.copyFrom(this.buffer, i2, rawVarint32);
            this.pos += rawVarint32;
            return byteStringCopyFrom;
        }

        @Override // com.google.protobuf.CodedInputStream
        public double readDouble() {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readEnum() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readFixed32() {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readFixed64() {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public float readFloat() {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public void readGroup(int i, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            int i2 = this.recursionDepth;
            if (i2 >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            this.recursionDepth = i2 + 1;
            builder.mergeFrom(this, extensionRegistryLite);
            checkLastTagWas((i << 3) | 4);
            this.recursionDepth--;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readInt32() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readInt64() {
            return readRawVarint64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (this.recursionDepth >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            int iPushLimit = pushLimit(rawVarint32);
            this.recursionDepth++;
            builder.mergeFrom(this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            popLimit(iPushLimit);
        }

        @Override // com.google.protobuf.CodedInputStream
        public byte readRawByte() throws InvalidProtocolBufferException {
            if (this.pos == this.bufferSize) {
                refillBuffer(1);
            }
            byte[] bArr = this.buffer;
            int i = this.pos;
            this.pos = i + 1;
            return bArr[i];
        }

        @Override // com.google.protobuf.CodedInputStream
        public byte[] readRawBytes(int i) {
            int i2 = this.pos;
            if (i > this.bufferSize - i2 || i <= 0) {
                return readRawBytesSlowPath(i, false);
            }
            int i3 = i + i2;
            this.pos = i3;
            return Arrays.copyOfRange(this.buffer, i2, i3);
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readRawLittleEndian32() throws InvalidProtocolBufferException {
            int i = this.pos;
            if (this.bufferSize - i < 4) {
                refillBuffer(4);
                i = this.pos;
            }
            byte[] bArr = this.buffer;
            this.pos = i + 4;
            return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readRawLittleEndian64() throws InvalidProtocolBufferException {
            int i = this.pos;
            if (this.bufferSize - i < 8) {
                refillBuffer(8);
                i = this.pos;
            }
            byte[] bArr = this.buffer;
            this.pos = i + 8;
            return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x0068, code lost:
        
            if (r2[r3] < 0) goto L34;
         */
        @Override // com.google.protobuf.CodedInputStream
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int readRawVarint32() {
            /*
                r5 = this;
                int r0 = r5.pos
                int r1 = r5.bufferSize
                if (r1 != r0) goto L7
                goto L6a
            L7:
                byte[] r2 = r5.buffer
                int r3 = r0 + 1
                r0 = r2[r0]
                if (r0 < 0) goto L12
                r5.pos = r3
                return r0
            L12:
                int r1 = r1 - r3
                r4 = 9
                if (r1 >= r4) goto L18
                goto L6a
            L18:
                int r1 = r3 + 1
                r3 = r2[r3]
                int r3 = r3 << 7
                r0 = r0 ^ r3
                if (r0 >= 0) goto L24
                r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
                goto L70
            L24:
                int r3 = r1 + 1
                r1 = r2[r1]
                int r1 = r1 << 14
                r0 = r0 ^ r1
                if (r0 < 0) goto L31
                r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            L2f:
                r1 = r3
                goto L70
            L31:
                int r1 = r3 + 1
                r3 = r2[r3]
                int r3 = r3 << 21
                r0 = r0 ^ r3
                if (r0 >= 0) goto L3f
                r2 = -2080896(0xffffffffffe03f80, float:NaN)
                r0 = r0 ^ r2
                goto L70
            L3f:
                int r3 = r1 + 1
                r1 = r2[r1]
                int r4 = r1 << 28
                r0 = r0 ^ r4
                r4 = 266354560(0xfe03f80, float:2.2112565E-29)
                r0 = r0 ^ r4
                if (r1 >= 0) goto L2f
                int r1 = r3 + 1
                r3 = r2[r3]
                if (r3 >= 0) goto L70
                int r3 = r1 + 1
                r1 = r2[r1]
                if (r1 >= 0) goto L2f
                int r1 = r3 + 1
                r3 = r2[r3]
                if (r3 >= 0) goto L70
                int r3 = r1 + 1
                r1 = r2[r1]
                if (r1 >= 0) goto L2f
                int r1 = r3 + 1
                r2 = r2[r3]
                if (r2 >= 0) goto L70
            L6a:
                long r0 = r5.readRawVarint64SlowPath()
                int r0 = (int) r0
                return r0
            L70:
                r5.pos = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.StreamDecoder.readRawVarint32():int");
        }

        /* JADX WARN: Code restructure failed: missing block: B:39:0x00b4, code lost:
        
            if (r2[r0] < 0) goto L40;
         */
        @Override // com.google.protobuf.CodedInputStream
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public long readRawVarint64() {
            /*
                r11 = this;
                int r0 = r11.pos
                int r1 = r11.bufferSize
                if (r1 != r0) goto L8
                goto Lb6
            L8:
                byte[] r2 = r11.buffer
                int r3 = r0 + 1
                r0 = r2[r0]
                if (r0 < 0) goto L14
                r11.pos = r3
                long r0 = (long) r0
                return r0
            L14:
                int r1 = r1 - r3
                r4 = 9
                if (r1 >= r4) goto L1b
                goto Lb6
            L1b:
                int r1 = r3 + 1
                r3 = r2[r3]
                int r3 = r3 << 7
                r0 = r0 ^ r3
                if (r0 >= 0) goto L29
                r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
            L26:
                long r2 = (long) r0
                goto Lbd
            L29:
                int r3 = r1 + 1
                r1 = r2[r1]
                int r1 = r1 << 14
                r0 = r0 ^ r1
                if (r0 < 0) goto L3a
                r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
                long r0 = (long) r0
                r9 = r0
                r1 = r3
                r2 = r9
                goto Lbd
            L3a:
                int r1 = r3 + 1
                r3 = r2[r3]
                int r3 = r3 << 21
                r0 = r0 ^ r3
                if (r0 >= 0) goto L48
                r2 = -2080896(0xffffffffffe03f80, float:NaN)
                r0 = r0 ^ r2
                goto L26
            L48:
                long r3 = (long) r0
                int r0 = r1 + 1
                r1 = r2[r1]
                long r5 = (long) r1
                r1 = 28
                long r5 = r5 << r1
                long r3 = r3 ^ r5
                r5 = 0
                int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r1 < 0) goto L5f
                r1 = 266354560(0xfe03f80, double:1.315966377E-315)
            L5b:
                long r2 = r3 ^ r1
                r1 = r0
                goto Lbd
            L5f:
                int r1 = r0 + 1
                r0 = r2[r0]
                long r7 = (long) r0
                r0 = 35
                long r7 = r7 << r0
                long r3 = r3 ^ r7
                int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r0 >= 0) goto L74
                r5 = -34093383808(0xfffffff80fe03f80, double:NaN)
            L71:
                long r2 = r3 ^ r5
                goto Lbd
            L74:
                int r0 = r1 + 1
                r1 = r2[r1]
                long r7 = (long) r1
                r1 = 42
                long r7 = r7 << r1
                long r3 = r3 ^ r7
                int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r1 < 0) goto L87
                r1 = 4363953127296(0x3f80fe03f80, double:2.1560793202584E-311)
                goto L5b
            L87:
                int r1 = r0 + 1
                r0 = r2[r0]
                long r7 = (long) r0
                r0 = 49
                long r7 = r7 << r0
                long r3 = r3 ^ r7
                int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r0 >= 0) goto L9a
                r5 = -558586000294016(0xfffe03f80fe03f80, double:NaN)
                goto L71
            L9a:
                int r0 = r1 + 1
                r1 = r2[r1]
                long r7 = (long) r1
                r1 = 56
                long r7 = r7 << r1
                long r3 = r3 ^ r7
                r7 = 71499008037633920(0xfe03f80fe03f80, double:6.838959413692434E-304)
                long r3 = r3 ^ r7
                int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r1 >= 0) goto Lbb
                int r1 = r0 + 1
                r0 = r2[r0]
                long r7 = (long) r0
                int r0 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
                if (r0 >= 0) goto Lbc
            Lb6:
                long r0 = r11.readRawVarint64SlowPath()
                return r0
            Lbb:
                r1 = r0
            Lbc:
                r2 = r3
            Lbd:
                r11.pos = r1
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.StreamDecoder.readRawVarint64():long");
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readRawVarint64SlowPath() throws InvalidProtocolBufferException {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                byte rawByte = readRawByte();
                j |= ((long) (rawByte & Ascii.DEL)) << i;
                if ((rawByte & UnsignedBytes.MAX_POWER_OF_TWO) == 0) {
                    return j;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readSFixed32() {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readSFixed64() {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readSInt32() {
            return CodedInputStream.decodeZigZag32(readRawVarint32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readSInt64() {
            return CodedInputStream.decodeZigZag64(readRawVarint64());
        }

        @Override // com.google.protobuf.CodedInputStream
        public String readString() throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (rawVarint32 > 0) {
                int i = this.bufferSize;
                int i2 = this.pos;
                if (rawVarint32 <= i - i2) {
                    String str = new String(this.buffer, i2, rawVarint32, Internal.UTF_8);
                    this.pos += rawVarint32;
                    return str;
                }
            }
            if (rawVarint32 == 0) {
                return "";
            }
            if (rawVarint32 > this.bufferSize) {
                return new String(readRawBytesSlowPath(rawVarint32, false), Internal.UTF_8);
            }
            refillBuffer(rawVarint32);
            String str2 = new String(this.buffer, this.pos, rawVarint32, Internal.UTF_8);
            this.pos += rawVarint32;
            return str2;
        }

        @Override // com.google.protobuf.CodedInputStream
        public String readStringRequireUtf8() throws IOException {
            byte[] rawBytesSlowPath;
            int rawVarint32 = readRawVarint32();
            int i = this.pos;
            int i2 = 0;
            if (rawVarint32 <= this.bufferSize - i && rawVarint32 > 0) {
                rawBytesSlowPath = this.buffer;
                this.pos = i + rawVarint32;
                i2 = i;
            } else {
                if (rawVarint32 == 0) {
                    return "";
                }
                if (rawVarint32 <= this.bufferSize) {
                    refillBuffer(rawVarint32);
                    rawBytesSlowPath = this.buffer;
                    this.pos = rawVarint32 + 0;
                } else {
                    rawBytesSlowPath = readRawBytesSlowPath(rawVarint32, false);
                }
            }
            return Utf8.decodeUtf8(rawBytesSlowPath, i2, rawVarint32);
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readTag() throws InvalidProtocolBufferException {
            if (isAtEnd()) {
                this.lastTag = 0;
                return 0;
            }
            this.lastTag = readRawVarint32();
            int i = this.lastTag;
            if ((i >>> 3) != 0) {
                return i;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readUInt32() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readUInt64() {
            return readRawVarint64();
        }

        @Override // com.google.protobuf.CodedInputStream
        @Deprecated
        public void readUnknownGroup(int i, MessageLite.Builder builder) throws InvalidProtocolBufferException {
            readGroup(i, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.protobuf.CodedInputStream
        public void resetSizeCounter() {
            this.totalBytesRetired = -this.pos;
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean skipField(int i) throws InvalidProtocolBufferException {
            int i2 = i & 7;
            if (i2 == 0) {
                skipRawVarint();
                return true;
            }
            if (i2 == 1) {
                skipRawBytes(8);
                return true;
            }
            if (i2 == 2) {
                skipRawBytes(readRawVarint32());
                return true;
            }
            if (i2 == 3) {
                skipMessage();
                checkLastTagWas(((i >>> 3) << 3) | 4);
                return true;
            }
            if (i2 == 4) {
                return false;
            }
            if (i2 != 5) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            skipRawBytes(4);
            return true;
        }

        @Override // com.google.protobuf.CodedInputStream
        public void skipMessage() throws InvalidProtocolBufferException {
            int tag;
            do {
                tag = readTag();
                if (tag == 0) {
                    return;
                }
            } while (skipField(tag));
        }

        @Override // com.google.protobuf.CodedInputStream
        public void skipRawBytes(int i) throws InvalidProtocolBufferException {
            int i2 = this.bufferSize;
            int i3 = this.pos;
            if (i > i2 - i3 || i < 0) {
                skipRawBytesSlowPath(i);
            } else {
                this.pos = i3 + i;
            }
        }

        @Override // com.google.protobuf.CodedInputStream
        public void skipMessage(CodedOutputStream codedOutputStream) throws InvalidProtocolBufferException {
            int tag;
            do {
                tag = readTag();
                if (tag == 0) {
                    return;
                }
            } while (skipField(tag, codedOutputStream));
        }

        @Override // com.google.protobuf.CodedInputStream
        public <T extends MessageLite> T readGroup(int i, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            int i2 = this.recursionDepth;
            if (i2 < this.recursionLimit) {
                this.recursionDepth = i2 + 1;
                T partialFrom = parser.parsePartialFrom(this, extensionRegistryLite);
                checkLastTagWas((i << 3) | 4);
                this.recursionDepth--;
                return partialFrom;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean skipField(int i, CodedOutputStream codedOutputStream) throws InvalidProtocolBufferException {
            int i2 = i & 7;
            if (i2 == 0) {
                long int64 = readInt64();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeUInt64NoTag(int64);
                return true;
            }
            if (i2 == 1) {
                long rawLittleEndian64 = readRawLittleEndian64();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeFixed64NoTag(rawLittleEndian64);
                return true;
            }
            if (i2 == 2) {
                ByteString bytes = readBytes();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeBytesNoTag(bytes);
                return true;
            }
            if (i2 == 3) {
                codedOutputStream.writeRawVarint32(i);
                skipMessage(codedOutputStream);
                int i3 = ((i >>> 3) << 3) | 4;
                checkLastTagWas(i3);
                codedOutputStream.writeRawVarint32(i3);
                return true;
            }
            if (i2 == 4) {
                return false;
            }
            if (i2 == 5) {
                int rawLittleEndian32 = readRawLittleEndian32();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeFixed32NoTag(rawLittleEndian32);
                return true;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }

        @Override // com.google.protobuf.CodedInputStream
        public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (this.recursionDepth < this.recursionLimit) {
                int iPushLimit = pushLimit(rawVarint32);
                this.recursionDepth++;
                T partialFrom = parser.parsePartialFrom(this, extensionRegistryLite);
                checkLastTagWas(0);
                this.recursionDepth--;
                popLimit(iPushLimit);
                return partialFrom;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }
    }

    private static final class UnsafeDirectNioDecoder extends CodedInputStream {
        public final long address;
        public final ByteBuffer buffer;
        public int bufferSizeAfterLimit;
        public int currentLimit;
        public boolean enableAliasing;
        public final boolean immutable;
        public int lastTag;
        public long limit;
        public long pos;
        public long startPos;

        public UnsafeDirectNioDecoder(ByteBuffer byteBuffer, boolean z) {
            this.currentLimit = Integer.MAX_VALUE;
            this.buffer = byteBuffer;
            this.address = UnsafeUtil.addressOffset(byteBuffer);
            this.limit = this.address + ((long) byteBuffer.limit());
            this.pos = this.address + ((long) byteBuffer.position());
            this.startPos = this.pos;
            this.immutable = z;
        }

        private int bufferPos(long j) {
            return (int) (j - this.address);
        }

        public static boolean isSupported() {
            return UnsafeUtil.HAS_UNSAFE_BYTEBUFFER_OPERATIONS;
        }

        private void recomputeBufferSizeAfterLimit() {
            this.limit += (long) this.bufferSizeAfterLimit;
            long j = this.limit;
            int i = (int) (j - this.startPos);
            int i2 = this.currentLimit;
            if (i <= i2) {
                this.bufferSizeAfterLimit = 0;
            } else {
                this.bufferSizeAfterLimit = i - i2;
                this.limit = j - ((long) this.bufferSizeAfterLimit);
            }
        }

        private int remaining() {
            return (int) (this.limit - this.pos);
        }

        private void skipRawVarint() throws InvalidProtocolBufferException {
            if (remaining() >= 10) {
                skipRawVarintFastPath();
            } else {
                skipRawVarintSlowPath();
            }
        }

        private void skipRawVarintFastPath() throws InvalidProtocolBufferException {
            for (int i = 0; i < 10; i++) {
                long j = this.pos;
                this.pos = 1 + j;
                if (UnsafeUtil.getByte(j) >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void skipRawVarintSlowPath() throws InvalidProtocolBufferException {
            for (int i = 0; i < 10; i++) {
                if (readRawByte() >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private ByteBuffer slice(long j, long j2) {
            int iPosition = this.buffer.position();
            int iLimit = this.buffer.limit();
            try {
                try {
                    this.buffer.position((int) (j - this.address));
                    this.buffer.limit((int) (j2 - this.address));
                    ByteBuffer byteBufferSlice = this.buffer.slice();
                    this.buffer.position(iPosition);
                    this.buffer.limit(iLimit);
                    return byteBufferSlice;
                } catch (IllegalArgumentException unused) {
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
            } catch (Throwable th) {
                this.buffer.position(iPosition);
                this.buffer.limit(iLimit);
                throw th;
            }
        }

        @Override // com.google.protobuf.CodedInputStream
        public void checkLastTagWas(int i) throws InvalidProtocolBufferException {
            if (this.lastTag != i) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        @Override // com.google.protobuf.CodedInputStream
        public void enableAliasing(boolean z) {
            this.enableAliasing = z;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getBytesUntilLimit() {
            int i = this.currentLimit;
            if (i == Integer.MAX_VALUE) {
                return -1;
            }
            return i - getTotalBytesRead();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getLastTag() {
            return this.lastTag;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getTotalBytesRead() {
            return (int) (this.pos - this.startPos);
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean isAtEnd() {
            return this.pos == this.limit;
        }

        @Override // com.google.protobuf.CodedInputStream
        public void popLimit(int i) {
            this.currentLimit = i;
            recomputeBufferSizeAfterLimit();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int pushLimit(int i) throws InvalidProtocolBufferException {
            if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int totalBytesRead = getTotalBytesRead() + i;
            int i2 = this.currentLimit;
            if (totalBytesRead > i2) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.currentLimit = totalBytesRead;
            recomputeBufferSizeAfterLimit();
            return i2;
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean readBool() {
            return readRawVarint64() != 0;
        }

        @Override // com.google.protobuf.CodedInputStream
        public byte[] readByteArray() {
            return readRawBytes(readRawVarint32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public ByteBuffer readByteBuffer() throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (rawVarint32 <= 0 || rawVarint32 > remaining()) {
                if (rawVarint32 == 0) {
                    return Internal.EMPTY_BYTE_BUFFER;
                }
                if (rawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            if (this.immutable || !this.enableAliasing) {
                byte[] bArr = new byte[rawVarint32];
                long j = rawVarint32;
                UnsafeUtil.copyMemory(this.pos, bArr, 0L, j);
                this.pos += j;
                return ByteBuffer.wrap(bArr);
            }
            long j2 = this.pos;
            long j3 = rawVarint32;
            ByteBuffer byteBufferSlice = slice(j2, j2 + j3);
            this.pos += j3;
            return byteBufferSlice;
        }

        @Override // com.google.protobuf.CodedInputStream
        public ByteString readBytes() throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (rawVarint32 <= 0 || rawVarint32 > remaining()) {
                if (rawVarint32 == 0) {
                    return ByteString.EMPTY;
                }
                if (rawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            if (this.immutable && this.enableAliasing) {
                long j = this.pos;
                long j2 = rawVarint32;
                ByteBuffer byteBufferSlice = slice(j, j + j2);
                this.pos += j2;
                return ByteString.wrap(byteBufferSlice);
            }
            byte[] bArr = new byte[rawVarint32];
            long j3 = rawVarint32;
            UnsafeUtil.copyMemory(this.pos, bArr, 0L, j3);
            this.pos += j3;
            return ByteString.wrap(bArr);
        }

        @Override // com.google.protobuf.CodedInputStream
        public double readDouble() {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readEnum() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readFixed32() {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readFixed64() {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public float readFloat() {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public void readGroup(int i, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            int i2 = this.recursionDepth;
            if (i2 >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            this.recursionDepth = i2 + 1;
            builder.mergeFrom(this, extensionRegistryLite);
            checkLastTagWas((i << 3) | 4);
            this.recursionDepth--;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readInt32() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readInt64() {
            return readRawVarint64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (this.recursionDepth >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            int iPushLimit = pushLimit(rawVarint32);
            this.recursionDepth++;
            builder.mergeFrom(this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            popLimit(iPushLimit);
        }

        @Override // com.google.protobuf.CodedInputStream
        public byte readRawByte() throws InvalidProtocolBufferException {
            long j = this.pos;
            if (j == this.limit) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.pos = 1 + j;
            return UnsafeUtil.getByte(j);
        }

        @Override // com.google.protobuf.CodedInputStream
        public byte[] readRawBytes(int i) throws InvalidProtocolBufferException {
            if (i < 0 || i > remaining()) {
                if (i > 0) {
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
                if (i == 0) {
                    return Internal.EMPTY_BYTE_ARRAY;
                }
                throw InvalidProtocolBufferException.negativeSize();
            }
            byte[] bArr = new byte[i];
            long j = this.pos;
            long j2 = i;
            slice(j, j + j2).get(bArr);
            this.pos += j2;
            return bArr;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readRawLittleEndian32() throws InvalidProtocolBufferException {
            long j = this.pos;
            if (this.limit - j < 4) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.pos = 4 + j;
            return ((UnsafeUtil.getByte(j + 3) & 255) << 24) | (UnsafeUtil.getByte(j) & 255) | ((UnsafeUtil.getByte(1 + j) & 255) << 8) | ((UnsafeUtil.getByte(2 + j) & 255) << 16);
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readRawLittleEndian64() throws InvalidProtocolBufferException {
            long j = this.pos;
            if (this.limit - j < 8) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.pos = 8 + j;
            return ((((long) UnsafeUtil.getByte(j + 7)) & 255) << 56) | (((long) UnsafeUtil.getByte(j)) & 255) | ((((long) UnsafeUtil.getByte(1 + j)) & 255) << 8) | ((((long) UnsafeUtil.getByte(2 + j)) & 255) << 16) | ((((long) UnsafeUtil.getByte(3 + j)) & 255) << 24) | ((((long) UnsafeUtil.getByte(4 + j)) & 255) << 32) | ((((long) UnsafeUtil.getByte(5 + j)) & 255) << 40) | ((((long) UnsafeUtil.getByte(6 + j)) & 255) << 48);
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x0083, code lost:
        
            if (com.google.protobuf.UnsafeUtil.getByte(r4) < 0) goto L34;
         */
        @Override // com.google.protobuf.CodedInputStream
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int readRawVarint32() {
            /*
                r10 = this;
                long r0 = r10.pos
                long r2 = r10.limit
                int r2 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
                if (r2 != 0) goto La
                goto L85
            La:
                r2 = 1
                long r4 = r0 + r2
                byte r0 = com.google.protobuf.UnsafeUtil.getByte(r0)
                if (r0 < 0) goto L17
                r10.pos = r4
                return r0
            L17:
                long r6 = r10.limit
                long r6 = r6 - r4
                r8 = 9
                int r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r1 >= 0) goto L21
                goto L85
            L21:
                long r6 = r4 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r4)
                int r1 = r1 << 7
                r0 = r0 ^ r1
                if (r0 >= 0) goto L2f
                r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
                goto L8b
            L2f:
                long r4 = r6 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r6)
                int r1 = r1 << 14
                r0 = r0 ^ r1
                if (r0 < 0) goto L3e
                r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            L3c:
                r6 = r4
                goto L8b
            L3e:
                long r6 = r4 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r4)
                int r1 = r1 << 21
                r0 = r0 ^ r1
                if (r0 >= 0) goto L4e
                r1 = -2080896(0xffffffffffe03f80, float:NaN)
                r0 = r0 ^ r1
                goto L8b
            L4e:
                long r4 = r6 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r6)
                int r6 = r1 << 28
                r0 = r0 ^ r6
                r6 = 266354560(0xfe03f80, float:2.2112565E-29)
                r0 = r0 ^ r6
                if (r1 >= 0) goto L3c
                long r6 = r4 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r4)
                if (r1 >= 0) goto L8b
                long r4 = r6 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r6)
                if (r1 >= 0) goto L3c
                long r6 = r4 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r4)
                if (r1 >= 0) goto L8b
                long r4 = r6 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r6)
                if (r1 >= 0) goto L3c
                long r6 = r4 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r4)
                if (r1 >= 0) goto L8b
            L85:
                long r0 = r10.readRawVarint64SlowPath()
                int r0 = (int) r0
                return r0
            L8b:
                r10.pos = r6
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.UnsafeDirectNioDecoder.readRawVarint32():int");
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readRawVarint64() {
            long j;
            long j2;
            long j3;
            long j4;
            int i;
            long j5 = this.pos;
            if (this.limit != j5) {
                long j6 = j5 + 1;
                byte b2 = UnsafeUtil.getByte(j5);
                if (b2 >= 0) {
                    this.pos = j6;
                    return b2;
                }
                if (this.limit - j6 >= 9) {
                    long j7 = j6 + 1;
                    int i2 = b2 ^ (UnsafeUtil.getByte(j6) << 7);
                    if (i2 >= 0) {
                        j = j7 + 1;
                        int i3 = i2 ^ (UnsafeUtil.getByte(j7) << Ascii.SO);
                        if (i3 >= 0) {
                            j2 = i3 ^ 16256;
                        } else {
                            j7 = j + 1;
                            int i4 = i3 ^ (UnsafeUtil.getByte(j) << Ascii.NAK);
                            if (i4 < 0) {
                                i = i4 ^ (-2080896);
                            } else {
                                j = j7 + 1;
                                long j8 = ((long) i4) ^ (((long) UnsafeUtil.getByte(j7)) << 28);
                                if (j8 >= 0) {
                                    j4 = 266354560;
                                } else {
                                    long j9 = j + 1;
                                    long j10 = j8 ^ (((long) UnsafeUtil.getByte(j)) << 35);
                                    if (j10 < 0) {
                                        j3 = -34093383808L;
                                    } else {
                                        j = j9 + 1;
                                        j8 = j10 ^ (((long) UnsafeUtil.getByte(j9)) << 42);
                                        if (j8 >= 0) {
                                            j4 = 4363953127296L;
                                        } else {
                                            j9 = j + 1;
                                            j10 = j8 ^ (((long) UnsafeUtil.getByte(j)) << 49);
                                            if (j10 < 0) {
                                                j3 = -558586000294016L;
                                            } else {
                                                j = j9 + 1;
                                                j2 = (j10 ^ (((long) UnsafeUtil.getByte(j9)) << 56)) ^ 71499008037633920L;
                                                if (j2 < 0) {
                                                    long j11 = 1 + j;
                                                    if (UnsafeUtil.getByte(j) >= 0) {
                                                        j = j11;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    j2 = j10 ^ j3;
                                    j = j9;
                                }
                                j2 = j8 ^ j4;
                            }
                        }
                        this.pos = j;
                        return j2;
                    }
                    i = i2 ^ (-128);
                    j2 = i;
                    j = j7;
                    this.pos = j;
                    return j2;
                }
            }
            return readRawVarint64SlowPath();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readRawVarint64SlowPath() throws InvalidProtocolBufferException {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                byte rawByte = readRawByte();
                j |= ((long) (rawByte & Ascii.DEL)) << i;
                if ((rawByte & UnsignedBytes.MAX_POWER_OF_TWO) == 0) {
                    return j;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readSFixed32() {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readSFixed64() {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readSInt32() {
            return CodedInputStream.decodeZigZag32(readRawVarint32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readSInt64() {
            return CodedInputStream.decodeZigZag64(readRawVarint64());
        }

        @Override // com.google.protobuf.CodedInputStream
        public String readString() throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (rawVarint32 <= 0 || rawVarint32 > remaining()) {
                if (rawVarint32 == 0) {
                    return "";
                }
                if (rawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            byte[] bArr = new byte[rawVarint32];
            long j = rawVarint32;
            UnsafeUtil.copyMemory(this.pos, bArr, 0L, j);
            String str = new String(bArr, Internal.UTF_8);
            this.pos += j;
            return str;
        }

        @Override // com.google.protobuf.CodedInputStream
        public String readStringRequireUtf8() throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (rawVarint32 > 0 && rawVarint32 <= remaining()) {
                String strDecodeUtf8 = Utf8.decodeUtf8(this.buffer, bufferPos(this.pos), rawVarint32);
                this.pos += (long) rawVarint32;
                return strDecodeUtf8;
            }
            if (rawVarint32 == 0) {
                return "";
            }
            if (rawVarint32 <= 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readTag() throws InvalidProtocolBufferException {
            if (isAtEnd()) {
                this.lastTag = 0;
                return 0;
            }
            this.lastTag = readRawVarint32();
            int i = this.lastTag;
            if ((i >>> 3) != 0) {
                return i;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readUInt32() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readUInt64() {
            return readRawVarint64();
        }

        @Override // com.google.protobuf.CodedInputStream
        @Deprecated
        public void readUnknownGroup(int i, MessageLite.Builder builder) throws InvalidProtocolBufferException {
            readGroup(i, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.protobuf.CodedInputStream
        public void resetSizeCounter() {
            this.startPos = this.pos;
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean skipField(int i) throws InvalidProtocolBufferException {
            int i2 = i & 7;
            if (i2 == 0) {
                skipRawVarint();
                return true;
            }
            if (i2 == 1) {
                skipRawBytes(8);
                return true;
            }
            if (i2 == 2) {
                skipRawBytes(readRawVarint32());
                return true;
            }
            if (i2 == 3) {
                skipMessage();
                checkLastTagWas(((i >>> 3) << 3) | 4);
                return true;
            }
            if (i2 == 4) {
                return false;
            }
            if (i2 != 5) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            skipRawBytes(4);
            return true;
        }

        @Override // com.google.protobuf.CodedInputStream
        public void skipMessage() throws InvalidProtocolBufferException {
            int tag;
            do {
                tag = readTag();
                if (tag == 0) {
                    return;
                }
            } while (skipField(tag));
        }

        @Override // com.google.protobuf.CodedInputStream
        public void skipRawBytes(int i) throws InvalidProtocolBufferException {
            if (i >= 0 && i <= remaining()) {
                this.pos += (long) i;
            } else {
                if (i >= 0) {
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
                throw InvalidProtocolBufferException.negativeSize();
            }
        }

        @Override // com.google.protobuf.CodedInputStream
        public void skipMessage(CodedOutputStream codedOutputStream) throws InvalidProtocolBufferException {
            int tag;
            do {
                tag = readTag();
                if (tag == 0) {
                    return;
                }
            } while (skipField(tag, codedOutputStream));
        }

        @Override // com.google.protobuf.CodedInputStream
        public <T extends MessageLite> T readGroup(int i, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            int i2 = this.recursionDepth;
            if (i2 < this.recursionLimit) {
                this.recursionDepth = i2 + 1;
                T partialFrom = parser.parsePartialFrom(this, extensionRegistryLite);
                checkLastTagWas((i << 3) | 4);
                this.recursionDepth--;
                return partialFrom;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean skipField(int i, CodedOutputStream codedOutputStream) throws InvalidProtocolBufferException {
            int i2 = i & 7;
            if (i2 == 0) {
                long int64 = readInt64();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeUInt64NoTag(int64);
                return true;
            }
            if (i2 == 1) {
                long rawLittleEndian64 = readRawLittleEndian64();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeFixed64NoTag(rawLittleEndian64);
                return true;
            }
            if (i2 == 2) {
                ByteString bytes = readBytes();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeBytesNoTag(bytes);
                return true;
            }
            if (i2 == 3) {
                codedOutputStream.writeRawVarint32(i);
                skipMessage(codedOutputStream);
                int i3 = ((i >>> 3) << 3) | 4;
                checkLastTagWas(i3);
                codedOutputStream.writeRawVarint32(i3);
                return true;
            }
            if (i2 == 4) {
                return false;
            }
            if (i2 == 5) {
                int rawLittleEndian32 = readRawLittleEndian32();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeFixed32NoTag(rawLittleEndian32);
                return true;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }

        @Override // com.google.protobuf.CodedInputStream
        public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            int rawVarint32 = readRawVarint32();
            if (this.recursionDepth < this.recursionLimit) {
                int iPushLimit = pushLimit(rawVarint32);
                this.recursionDepth++;
                T partialFrom = parser.parsePartialFrom(this, extensionRegistryLite);
                checkLastTagWas(0);
                this.recursionDepth--;
                popLimit(iPushLimit);
                return partialFrom;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }
    }

    public CodedInputStream() {
        this.recursionLimit = 100;
        this.sizeLimit = Integer.MAX_VALUE;
        this.shouldDiscardUnknownFields = false;
    }

    public static CodedInputStream newInstance(InputStream inputStream, int i) {
        if (i > 0) {
            return inputStream == null ? newInstance(Internal.EMPTY_BYTE_ARRAY) : new StreamDecoder(inputStream, i);
        }
        throw new IllegalArgumentException("bufferSize must be > 0");
    }

    public static CodedInputStream newInstance(Iterable<ByteBuffer> iterable) {
        if (!UnsafeUtil.HAS_UNSAFE_BYTEBUFFER_OPERATIONS) {
            return newInstance(new IterableByteBufferInputStream(iterable));
        }
        return newInstance(iterable, false);
    }

    public static int readRawVarint32(InputStream inputStream) throws IOException {
        int i = inputStream.read();
        if (i != -1) {
            return readRawVarint32(i, inputStream);
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static CodedInputStream newInstance(Iterable<ByteBuffer> iterable, boolean z) {
        int i = 0;
        int iRemaining = 0;
        for (ByteBuffer byteBuffer : iterable) {
            iRemaining += byteBuffer.remaining();
            if (byteBuffer.hasArray()) {
                i |= 1;
            } else {
                i = byteBuffer.isDirect() ? i | 2 : i | 4;
            }
        }
        if (i == 2) {
            return new IterableDirectByteBufferDecoder(iterable, iRemaining, z);
        }
        return newInstance(new IterableByteBufferInputStream(iterable));
    }

    public static CodedInputStream newInstance(byte[] bArr) {
        return newInstance(bArr, 0, bArr.length);
    }

    public static CodedInputStream newInstance(byte[] bArr, int i, int i2) {
        return newInstance(bArr, i, i2, false);
    }

    public static CodedInputStream newInstance(byte[] bArr, int i, int i2, boolean z) {
        ArrayDecoder arrayDecoder = new ArrayDecoder(bArr, i, i2, z);
        try {
            arrayDecoder.pushLimit(i2);
            return arrayDecoder;
        } catch (InvalidProtocolBufferException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public static CodedInputStream newInstance(ByteBuffer byteBuffer) {
        return newInstance(byteBuffer, false);
    }

    public static CodedInputStream newInstance(ByteBuffer byteBuffer, boolean z) {
        if (byteBuffer.hasArray()) {
            return newInstance(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.remaining(), z);
        }
        if (byteBuffer.isDirect() && UnsafeUtil.HAS_UNSAFE_BYTEBUFFER_OPERATIONS) {
            return new UnsafeDirectNioDecoder(byteBuffer, z);
        }
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.duplicate().get(bArr);
        return newInstance(bArr, 0, bArr.length, true);
    }
}
