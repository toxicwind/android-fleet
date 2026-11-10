package com.google.protobuf;

import b.a.a.a.a;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import okhttp3.internal.http2.Http2Stream;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BinaryWriter extends ByteOutput implements Writer {
    public static final int DEFAULT_CHUNK_SIZE = 4096;
    public static final int MAP_KEY_NUMBER = 1;
    public static final int MAP_VALUE_NUMBER = 2;
    public final BufferAllocator alloc;
    public final ArrayDeque<AllocatedBuffer> buffers;
    public final int chunkSize;
    public int totalDoneBytes;

    /* JADX INFO: renamed from: com.google.protobuf.BinaryWriter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType = new int[WireFormat.FieldType.values().length];

        static {
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FLOAT.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.DOUBLE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BYTES.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    private static final class SafeDirectWriter extends BinaryWriter {
        public ByteBuffer buffer;
        public int limitMinusOne;
        public int pos;

        public SafeDirectWriter(BufferAllocator bufferAllocator, int i) {
            super(bufferAllocator, i);
            nextBuffer();
        }

        private int bytesWrittenToCurrentBuffer() {
            return this.limitMinusOne - this.pos;
        }

        private void nextBuffer() {
            nextBuffer(newDirectBuffer());
        }

        private int spaceLeft() {
            return this.pos + 1;
        }

        private void writeVarint32FiveBytes(int i) {
            ByteBuffer byteBuffer = this.buffer;
            int i2 = this.pos;
            this.pos = i2 - 1;
            byteBuffer.put(i2, (byte) (i >>> 28));
            this.pos -= 4;
            this.buffer.putInt(this.pos + 1, (i & 127) | 128 | ((((i >>> 21) & 127) | 128) << 24) | ((((i >>> 14) & 127) | 128) << 16) | ((((i >>> 7) & 127) | 128) << 8));
        }

        private void writeVarint32FourBytes(int i) {
            this.pos -= 4;
            this.buffer.putInt(this.pos + 1, (i & 127) | 128 | ((266338304 & i) << 3) | (((2080768 & i) | 2097152) << 2) | (((i & 16256) | 16384) << 1));
        }

        private void writeVarint32OneByte(int i) {
            ByteBuffer byteBuffer = this.buffer;
            int i2 = this.pos;
            this.pos = i2 - 1;
            byteBuffer.put(i2, (byte) i);
        }

        private void writeVarint32ThreeBytes(int i) {
            this.pos -= 3;
            this.buffer.putInt(this.pos, (((i & 127) | 128) << 8) | ((2080768 & i) << 10) | (((i & 16256) | 16384) << 9));
        }

        private void writeVarint32TwoBytes(int i) {
            this.pos -= 2;
            this.buffer.putShort(this.pos + 1, (short) ((i & 127) | 128 | ((i & 16256) << 1)));
        }

        private void writeVarint64EightBytes(long j) {
            this.pos -= 8;
            this.buffer.putLong(this.pos + 1, (j & 127) | 128 | ((71494644084506624L & j) << 7) | (((558551906910208L & j) | 562949953421312L) << 6) | (((4363686772736L & j) | 4398046511104L) << 5) | (((34091302912L & j) | 34359738368L) << 4) | (((266338304 & j) | 268435456) << 3) | (((2080768 & j) | 2097152) << 2) | (((16256 & j) | Http2Stream.FramingSink.EMIT_BUFFER_SIZE) << 1));
        }

        private void writeVarint64EightBytesWithSign(long j) {
            this.pos -= 8;
            this.buffer.putLong(this.pos + 1, (j & 127) | 128 | (((71494644084506624L & j) | 72057594037927936L) << 7) | (((558551906910208L & j) | 562949953421312L) << 6) | (((4363686772736L & j) | 4398046511104L) << 5) | (((34091302912L & j) | 34359738368L) << 4) | (((266338304 & j) | 268435456) << 3) | (((2080768 & j) | 2097152) << 2) | (((16256 & j) | Http2Stream.FramingSink.EMIT_BUFFER_SIZE) << 1));
        }

        private void writeVarint64FiveBytes(long j) {
            this.pos -= 5;
            this.buffer.putLong(this.pos - 2, (((j & 127) | 128) << 24) | ((34091302912L & j) << 28) | (((266338304 & j) | 268435456) << 27) | (((2080768 & j) | 2097152) << 26) | (((16256 & j) | Http2Stream.FramingSink.EMIT_BUFFER_SIZE) << 25));
        }

        private void writeVarint64FourBytes(long j) {
            writeVarint32FourBytes((int) j);
        }

        private void writeVarint64NineBytes(long j) {
            ByteBuffer byteBuffer = this.buffer;
            int i = this.pos;
            this.pos = i - 1;
            byteBuffer.put(i, (byte) (j >>> 56));
            writeVarint64EightBytesWithSign(j & 72057594037927935L);
        }

        private void writeVarint64OneByte(long j) {
            writeVarint32OneByte((int) j);
        }

        private void writeVarint64SevenBytes(long j) {
            this.pos -= 7;
            this.buffer.putLong(this.pos, (((j & 127) | 128) << 8) | ((558551906910208L & j) << 14) | (((4363686772736L & j) | 4398046511104L) << 13) | (((34091302912L & j) | 34359738368L) << 12) | (((266338304 & j) | 268435456) << 11) | (((2080768 & j) | 2097152) << 10) | (((16256 & j) | Http2Stream.FramingSink.EMIT_BUFFER_SIZE) << 9));
        }

        private void writeVarint64SixBytes(long j) {
            this.pos -= 6;
            this.buffer.putLong(this.pos - 1, (((j & 127) | 128) << 16) | ((4363686772736L & j) << 21) | (((34091302912L & j) | 34359738368L) << 20) | (((266338304 & j) | 268435456) << 19) | (((2080768 & j) | 2097152) << 18) | (((16256 & j) | Http2Stream.FramingSink.EMIT_BUFFER_SIZE) << 17));
        }

        private void writeVarint64TenBytes(long j) {
            ByteBuffer byteBuffer = this.buffer;
            int i = this.pos;
            this.pos = i - 1;
            byteBuffer.put(i, (byte) (j >>> 63));
            ByteBuffer byteBuffer2 = this.buffer;
            int i2 = this.pos;
            this.pos = i2 - 1;
            byteBuffer2.put(i2, (byte) (((j >>> 56) & 127) | 128));
            writeVarint64EightBytesWithSign(j & 72057594037927935L);
        }

        private void writeVarint64ThreeBytes(long j) {
            writeVarint32ThreeBytes((int) j);
        }

        private void writeVarint64TwoBytes(long j) {
            writeVarint32TwoBytes((int) j);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void finishCurrentBuffer() {
            if (this.buffer != null) {
                this.totalDoneBytes += bytesWrittenToCurrentBuffer();
                this.buffer.position(this.pos + 1);
                this.buffer = null;
                this.pos = 0;
                this.limitMinusOne = 0;
            }
        }

        @Override // com.google.protobuf.BinaryWriter
        public int getTotalBytesWritten() {
            return this.totalDoneBytes + bytesWrittenToCurrentBuffer();
        }

        @Override // com.google.protobuf.BinaryWriter
        public void requireSpace(int i) {
            if (this.pos + 1 < i) {
                nextBuffer(newDirectBuffer(i));
            }
        }

        @Override // com.google.protobuf.ByteOutput
        public void write(byte[] bArr, int i, int i2) {
            if (this.pos + 1 < i2) {
                nextBuffer(newDirectBuffer(i2));
            }
            this.pos -= i2;
            this.buffer.position(this.pos + 1);
            this.buffer.put(bArr, i, i2);
        }

        @Override // com.google.protobuf.Writer
        public void writeBool(int i, boolean z) {
            requireSpace(6);
            write(z ? (byte) 1 : (byte) 0);
            writeTag(i, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeBytes(int i, ByteString byteString) {
            try {
                byteString.writeToReverse(this);
                requireSpace(10);
                writeVarint32(byteString.size());
                writeTag(i, 2);
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }

        @Override // com.google.protobuf.Writer
        public void writeEndGroup(int i) {
            writeTag(i, 4);
        }

        @Override // com.google.protobuf.Writer
        public void writeFixed32(int i, int i2) {
            requireSpace(9);
            writeFixed32(i2);
            writeTag(i, 5);
        }

        @Override // com.google.protobuf.Writer
        public void writeFixed64(int i, long j) {
            requireSpace(13);
            writeFixed64(j);
            writeTag(i, 1);
        }

        @Override // com.google.protobuf.Writer
        public void writeGroup(int i, Object obj) {
            writeTag(i, 4);
            Protobuf.INSTANCE.writeTo(obj, this);
            writeTag(i, 3);
        }

        @Override // com.google.protobuf.Writer
        public void writeInt32(int i, int i2) {
            requireSpace(15);
            writeInt32(i2);
            writeTag(i, 0);
        }

        @Override // com.google.protobuf.ByteOutput
        public void writeLazy(byte[] bArr, int i, int i2) {
            int i3 = this.pos;
            if (i3 + 1 < i2) {
                this.totalDoneBytes += i2;
                this.buffers.addFirst(AllocatedBuffer.wrap(bArr, i, i2));
                nextBuffer();
            } else {
                this.pos = i3 - i2;
                this.buffer.position(this.pos + 1);
                this.buffer.put(bArr, i, i2);
            }
        }

        @Override // com.google.protobuf.Writer
        public void writeMessage(int i, Object obj) {
            int totalBytesWritten = getTotalBytesWritten();
            Protobuf.INSTANCE.writeTo(obj, this);
            int totalBytesWritten2 = getTotalBytesWritten() - totalBytesWritten;
            requireSpace(10);
            writeVarint32(totalBytesWritten2);
            writeTag(i, 2);
        }

        @Override // com.google.protobuf.Writer
        public void writeSInt32(int i, int i2) {
            requireSpace(10);
            writeSInt32(i2);
            writeTag(i, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeSInt64(int i, long j) {
            requireSpace(15);
            writeSInt64(j);
            writeTag(i, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeStartGroup(int i) {
            writeTag(i, 3);
        }

        @Override // com.google.protobuf.Writer
        public void writeString(int i, String str) {
            int totalBytesWritten = getTotalBytesWritten();
            writeString(str);
            int totalBytesWritten2 = getTotalBytesWritten() - totalBytesWritten;
            requireSpace(10);
            writeVarint32(totalBytesWritten2);
            writeTag(i, 2);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeTag(int i, int i2) {
            writeVarint32((i << 3) | i2);
        }

        @Override // com.google.protobuf.Writer
        public void writeUInt32(int i, int i2) {
            requireSpace(10);
            writeVarint32(i2);
            writeTag(i, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeUInt64(int i, long j) {
            requireSpace(15);
            writeVarint64(j);
            writeTag(i, 0);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeVarint32(int i) {
            if ((i & (-128)) == 0) {
                ByteBuffer byteBuffer = this.buffer;
                int i2 = this.pos;
                this.pos = i2 - 1;
                byteBuffer.put(i2, (byte) i);
                return;
            }
            if ((i & (-16384)) == 0) {
                writeVarint32TwoBytes(i);
                return;
            }
            if (((-2097152) & i) == 0) {
                writeVarint32ThreeBytes(i);
            } else if (((-268435456) & i) == 0) {
                writeVarint32FourBytes(i);
            } else {
                writeVarint32FiveBytes(i);
            }
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeVarint64(long j) {
            switch (BinaryWriter.computeUInt64SizeNoTag(j)) {
                case 1:
                    writeVarint32OneByte((int) j);
                    break;
                case 2:
                    writeVarint32TwoBytes((int) j);
                    break;
                case 3:
                    writeVarint32ThreeBytes((int) j);
                    break;
                case 4:
                    writeVarint32FourBytes((int) j);
                    break;
                case 5:
                    writeVarint64FiveBytes(j);
                    break;
                case 6:
                    writeVarint64SixBytes(j);
                    break;
                case 7:
                    writeVarint64SevenBytes(j);
                    break;
                case 8:
                    writeVarint64EightBytes(j);
                    break;
                case 9:
                    writeVarint64NineBytes(j);
                    break;
                case 10:
                    writeVarint64TenBytes(j);
                    break;
            }
        }

        private void nextBuffer(int i) {
            nextBuffer(newDirectBuffer(i));
        }

        private void nextBuffer(AllocatedBuffer allocatedBuffer) {
            if (allocatedBuffer.hasNioBuffer()) {
                ByteBuffer byteBufferNioBuffer = allocatedBuffer.nioBuffer();
                if (byteBufferNioBuffer.isDirect()) {
                    finishCurrentBuffer();
                    this.buffers.addFirst(allocatedBuffer);
                    this.buffer = byteBufferNioBuffer;
                    ByteBuffer byteBuffer = this.buffer;
                    byteBuffer.limit(byteBuffer.capacity());
                    this.buffer.position(0);
                    this.buffer.order(ByteOrder.LITTLE_ENDIAN);
                    this.limitMinusOne = this.buffer.limit() - 1;
                    this.pos = this.limitMinusOne;
                    return;
                }
                throw new RuntimeException("Allocator returned non-direct buffer");
            }
            throw new RuntimeException("Allocated buffer does not have NIO buffer");
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeBool(boolean z) {
            write(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeFixed32(int i) {
            this.pos -= 4;
            this.buffer.putInt(this.pos + 1, i);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeFixed64(long j) {
            this.pos -= 8;
            this.buffer.putLong(this.pos + 1, j);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeInt32(int i) {
            if (i >= 0) {
                writeVarint32(i);
            } else {
                writeVarint64(i);
            }
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeSInt32(int i) {
            writeVarint32(CodedOutputStream.encodeZigZag32(i));
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeSInt64(long j) {
            writeVarint64(CodedOutputStream.encodeZigZag64(j));
        }

        @Override // com.google.protobuf.Writer
        public void writeGroup(int i, Object obj, Schema schema) {
            writeTag(i, 4);
            schema.writeTo(obj, this);
            writeTag(i, 3);
        }

        @Override // com.google.protobuf.ByteOutput
        public void write(byte b2) {
            ByteBuffer byteBuffer = this.buffer;
            int i = this.pos;
            this.pos = i - 1;
            byteBuffer.put(i, b2);
        }

        @Override // com.google.protobuf.ByteOutput
        public void write(ByteBuffer byteBuffer) {
            int iRemaining = byteBuffer.remaining();
            if (this.pos + 1 < iRemaining) {
                nextBuffer(iRemaining);
            }
            this.pos -= iRemaining;
            this.buffer.position(this.pos + 1);
            this.buffer.put(byteBuffer);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeString(String str) {
            int i;
            int i2;
            int i3;
            char cCharAt;
            requireSpace(str.length());
            int length = str.length() - 1;
            this.pos -= length;
            while (length >= 0 && (cCharAt = str.charAt(length)) < 128) {
                this.buffer.put(this.pos + length, (byte) cCharAt);
                length--;
            }
            if (length == -1) {
                this.pos--;
                return;
            }
            this.pos += length;
            while (length >= 0) {
                char cCharAt2 = str.charAt(length);
                if (cCharAt2 < 128 && (i3 = this.pos) >= 0) {
                    ByteBuffer byteBuffer = this.buffer;
                    this.pos = i3 - 1;
                    byteBuffer.put(i3, (byte) cCharAt2);
                } else if (cCharAt2 < 2048 && (i2 = this.pos) > 0) {
                    ByteBuffer byteBuffer2 = this.buffer;
                    this.pos = i2 - 1;
                    byteBuffer2.put(i2, (byte) ((cCharAt2 & '?') | 128));
                    ByteBuffer byteBuffer3 = this.buffer;
                    int i4 = this.pos;
                    this.pos = i4 - 1;
                    byteBuffer3.put(i4, (byte) ((cCharAt2 >>> 6) | 960));
                } else if ((cCharAt2 < 55296 || 57343 < cCharAt2) && (i = this.pos) > 1) {
                    ByteBuffer byteBuffer4 = this.buffer;
                    this.pos = i - 1;
                    byteBuffer4.put(i, (byte) ((cCharAt2 & '?') | 128));
                    ByteBuffer byteBuffer5 = this.buffer;
                    int i5 = this.pos;
                    this.pos = i5 - 1;
                    byteBuffer5.put(i5, (byte) (((cCharAt2 >>> 6) & 63) | 128));
                    ByteBuffer byteBuffer6 = this.buffer;
                    int i6 = this.pos;
                    this.pos = i6 - 1;
                    byteBuffer6.put(i6, (byte) ((cCharAt2 >>> '\f') | 480));
                } else {
                    if (this.pos > 2) {
                        if (length != 0) {
                            char cCharAt3 = str.charAt(length - 1);
                            if (Character.isSurrogatePair(cCharAt3, cCharAt2)) {
                                length--;
                                int codePoint = Character.toCodePoint(cCharAt3, cCharAt2);
                                ByteBuffer byteBuffer7 = this.buffer;
                                int i7 = this.pos;
                                this.pos = i7 - 1;
                                byteBuffer7.put(i7, (byte) ((codePoint & 63) | 128));
                                ByteBuffer byteBuffer8 = this.buffer;
                                int i8 = this.pos;
                                this.pos = i8 - 1;
                                byteBuffer8.put(i8, (byte) (((codePoint >>> 6) & 63) | 128));
                                ByteBuffer byteBuffer9 = this.buffer;
                                int i9 = this.pos;
                                this.pos = i9 - 1;
                                byteBuffer9.put(i9, (byte) (((codePoint >>> 12) & 63) | 128));
                                ByteBuffer byteBuffer10 = this.buffer;
                                int i10 = this.pos;
                                this.pos = i10 - 1;
                                byteBuffer10.put(i10, (byte) ((codePoint >>> 18) | 240));
                            }
                        }
                        throw new Utf8.UnpairedSurrogateException(length - 1, length);
                    }
                    requireSpace(length);
                    length++;
                }
                length--;
            }
        }

        @Override // com.google.protobuf.ByteOutput
        public void writeLazy(ByteBuffer byteBuffer) {
            int iRemaining = byteBuffer.remaining();
            int i = this.pos;
            if (i + 1 < iRemaining) {
                this.totalDoneBytes += iRemaining;
                this.buffers.addFirst(AllocatedBuffer.wrap(byteBuffer));
                nextBuffer();
            } else {
                this.pos = i - iRemaining;
                this.buffer.position(this.pos + 1);
                this.buffer.put(byteBuffer);
            }
        }

        @Override // com.google.protobuf.Writer
        public void writeMessage(int i, Object obj, Schema schema) {
            int totalBytesWritten = getTotalBytesWritten();
            schema.writeTo(obj, this);
            int totalBytesWritten2 = getTotalBytesWritten() - totalBytesWritten;
            requireSpace(10);
            writeVarint32(totalBytesWritten2);
            writeTag(i, 2);
        }
    }

    private static final class SafeHeapWriter extends BinaryWriter {
        public AllocatedBuffer allocatedBuffer;
        public byte[] buffer;
        public int limit;
        public int limitMinusOne;
        public int offset;
        public int offsetMinusOne;
        public int pos;

        public SafeHeapWriter(BufferAllocator bufferAllocator, int i) {
            super(bufferAllocator, i);
            nextBuffer();
        }

        private void nextBuffer() {
            nextBuffer(newHeapBuffer());
        }

        private void writeVarint32FiveBytes(int i) {
            byte[] bArr = this.buffer;
            int i2 = this.pos;
            this.pos = i2 - 1;
            bArr[i2] = (byte) (i >>> 28);
            int i3 = this.pos;
            this.pos = i3 - 1;
            bArr[i3] = (byte) (((i >>> 21) & 127) | 128);
            int i4 = this.pos;
            this.pos = i4 - 1;
            bArr[i4] = (byte) (((i >>> 14) & 127) | 128);
            int i5 = this.pos;
            this.pos = i5 - 1;
            bArr[i5] = (byte) (((i >>> 7) & 127) | 128);
            int i6 = this.pos;
            this.pos = i6 - 1;
            bArr[i6] = (byte) ((i & 127) | 128);
        }

        private void writeVarint32FourBytes(int i) {
            byte[] bArr = this.buffer;
            int i2 = this.pos;
            this.pos = i2 - 1;
            bArr[i2] = (byte) (i >>> 21);
            int i3 = this.pos;
            this.pos = i3 - 1;
            bArr[i3] = (byte) (((i >>> 14) & 127) | 128);
            int i4 = this.pos;
            this.pos = i4 - 1;
            bArr[i4] = (byte) (((i >>> 7) & 127) | 128);
            int i5 = this.pos;
            this.pos = i5 - 1;
            bArr[i5] = (byte) ((i & 127) | 128);
        }

        private void writeVarint32OneByte(int i) {
            byte[] bArr = this.buffer;
            int i2 = this.pos;
            this.pos = i2 - 1;
            bArr[i2] = (byte) i;
        }

        private void writeVarint32ThreeBytes(int i) {
            byte[] bArr = this.buffer;
            int i2 = this.pos;
            this.pos = i2 - 1;
            bArr[i2] = (byte) (i >>> 14);
            int i3 = this.pos;
            this.pos = i3 - 1;
            bArr[i3] = (byte) (((i >>> 7) & 127) | 128);
            int i4 = this.pos;
            this.pos = i4 - 1;
            bArr[i4] = (byte) ((i & 127) | 128);
        }

        private void writeVarint32TwoBytes(int i) {
            byte[] bArr = this.buffer;
            int i2 = this.pos;
            this.pos = i2 - 1;
            bArr[i2] = (byte) (i >>> 7);
            int i3 = this.pos;
            this.pos = i3 - 1;
            bArr[i3] = (byte) ((i & 127) | 128);
        }

        private void writeVarint64EightBytes(long j) {
            byte[] bArr = this.buffer;
            int i = this.pos;
            this.pos = i - 1;
            bArr[i] = (byte) (j >>> 49);
            int i2 = this.pos;
            this.pos = i2 - 1;
            bArr[i2] = (byte) (((j >>> 42) & 127) | 128);
            int i3 = this.pos;
            this.pos = i3 - 1;
            bArr[i3] = (byte) (((j >>> 35) & 127) | 128);
            int i4 = this.pos;
            this.pos = i4 - 1;
            bArr[i4] = (byte) (((j >>> 28) & 127) | 128);
            int i5 = this.pos;
            this.pos = i5 - 1;
            bArr[i5] = (byte) (((j >>> 21) & 127) | 128);
            int i6 = this.pos;
            this.pos = i6 - 1;
            bArr[i6] = (byte) (((j >>> 14) & 127) | 128);
            int i7 = this.pos;
            this.pos = i7 - 1;
            bArr[i7] = (byte) (((j >>> 7) & 127) | 128);
            int i8 = this.pos;
            this.pos = i8 - 1;
            bArr[i8] = (byte) ((j & 127) | 128);
        }

        private void writeVarint64FiveBytes(long j) {
            byte[] bArr = this.buffer;
            int i = this.pos;
            this.pos = i - 1;
            bArr[i] = (byte) (j >>> 28);
            int i2 = this.pos;
            this.pos = i2 - 1;
            bArr[i2] = (byte) (((j >>> 21) & 127) | 128);
            int i3 = this.pos;
            this.pos = i3 - 1;
            bArr[i3] = (byte) (((j >>> 14) & 127) | 128);
            int i4 = this.pos;
            this.pos = i4 - 1;
            bArr[i4] = (byte) (((j >>> 7) & 127) | 128);
            int i5 = this.pos;
            this.pos = i5 - 1;
            bArr[i5] = (byte) ((j & 127) | 128);
        }

        private void writeVarint64FourBytes(long j) {
            byte[] bArr = this.buffer;
            int i = this.pos;
            this.pos = i - 1;
            bArr[i] = (byte) (j >>> 21);
            int i2 = this.pos;
            this.pos = i2 - 1;
            bArr[i2] = (byte) (((j >>> 14) & 127) | 128);
            int i3 = this.pos;
            this.pos = i3 - 1;
            bArr[i3] = (byte) (((j >>> 7) & 127) | 128);
            int i4 = this.pos;
            this.pos = i4 - 1;
            bArr[i4] = (byte) ((j & 127) | 128);
        }

        private void writeVarint64NineBytes(long j) {
            byte[] bArr = this.buffer;
            int i = this.pos;
            this.pos = i - 1;
            bArr[i] = (byte) (j >>> 56);
            int i2 = this.pos;
            this.pos = i2 - 1;
            bArr[i2] = (byte) (((j >>> 49) & 127) | 128);
            int i3 = this.pos;
            this.pos = i3 - 1;
            bArr[i3] = (byte) (((j >>> 42) & 127) | 128);
            int i4 = this.pos;
            this.pos = i4 - 1;
            bArr[i4] = (byte) (((j >>> 35) & 127) | 128);
            int i5 = this.pos;
            this.pos = i5 - 1;
            bArr[i5] = (byte) (((j >>> 28) & 127) | 128);
            int i6 = this.pos;
            this.pos = i6 - 1;
            bArr[i6] = (byte) (((j >>> 21) & 127) | 128);
            int i7 = this.pos;
            this.pos = i7 - 1;
            bArr[i7] = (byte) (((j >>> 14) & 127) | 128);
            int i8 = this.pos;
            this.pos = i8 - 1;
            bArr[i8] = (byte) (((j >>> 7) & 127) | 128);
            int i9 = this.pos;
            this.pos = i9 - 1;
            bArr[i9] = (byte) ((j & 127) | 128);
        }

        private void writeVarint64OneByte(long j) {
            byte[] bArr = this.buffer;
            int i = this.pos;
            this.pos = i - 1;
            bArr[i] = (byte) j;
        }

        private void writeVarint64SevenBytes(long j) {
            byte[] bArr = this.buffer;
            int i = this.pos;
            this.pos = i - 1;
            bArr[i] = (byte) (j >>> 42);
            int i2 = this.pos;
            this.pos = i2 - 1;
            bArr[i2] = (byte) (((j >>> 35) & 127) | 128);
            int i3 = this.pos;
            this.pos = i3 - 1;
            bArr[i3] = (byte) (((j >>> 28) & 127) | 128);
            int i4 = this.pos;
            this.pos = i4 - 1;
            bArr[i4] = (byte) (((j >>> 21) & 127) | 128);
            int i5 = this.pos;
            this.pos = i5 - 1;
            bArr[i5] = (byte) (((j >>> 14) & 127) | 128);
            int i6 = this.pos;
            this.pos = i6 - 1;
            bArr[i6] = (byte) (((j >>> 7) & 127) | 128);
            int i7 = this.pos;
            this.pos = i7 - 1;
            bArr[i7] = (byte) ((j & 127) | 128);
        }

        private void writeVarint64SixBytes(long j) {
            byte[] bArr = this.buffer;
            int i = this.pos;
            this.pos = i - 1;
            bArr[i] = (byte) (j >>> 35);
            int i2 = this.pos;
            this.pos = i2 - 1;
            bArr[i2] = (byte) (((j >>> 28) & 127) | 128);
            int i3 = this.pos;
            this.pos = i3 - 1;
            bArr[i3] = (byte) (((j >>> 21) & 127) | 128);
            int i4 = this.pos;
            this.pos = i4 - 1;
            bArr[i4] = (byte) (((j >>> 14) & 127) | 128);
            int i5 = this.pos;
            this.pos = i5 - 1;
            bArr[i5] = (byte) (((j >>> 7) & 127) | 128);
            int i6 = this.pos;
            this.pos = i6 - 1;
            bArr[i6] = (byte) ((j & 127) | 128);
        }

        private void writeVarint64TenBytes(long j) {
            byte[] bArr = this.buffer;
            int i = this.pos;
            this.pos = i - 1;
            bArr[i] = (byte) (j >>> 63);
            int i2 = this.pos;
            this.pos = i2 - 1;
            bArr[i2] = (byte) (((j >>> 56) & 127) | 128);
            int i3 = this.pos;
            this.pos = i3 - 1;
            bArr[i3] = (byte) (((j >>> 49) & 127) | 128);
            int i4 = this.pos;
            this.pos = i4 - 1;
            bArr[i4] = (byte) (((j >>> 42) & 127) | 128);
            int i5 = this.pos;
            this.pos = i5 - 1;
            bArr[i5] = (byte) (((j >>> 35) & 127) | 128);
            int i6 = this.pos;
            this.pos = i6 - 1;
            bArr[i6] = (byte) (((j >>> 28) & 127) | 128);
            int i7 = this.pos;
            this.pos = i7 - 1;
            bArr[i7] = (byte) (((j >>> 21) & 127) | 128);
            int i8 = this.pos;
            this.pos = i8 - 1;
            bArr[i8] = (byte) (((j >>> 14) & 127) | 128);
            int i9 = this.pos;
            this.pos = i9 - 1;
            bArr[i9] = (byte) (((j >>> 7) & 127) | 128);
            int i10 = this.pos;
            this.pos = i10 - 1;
            bArr[i10] = (byte) ((j & 127) | 128);
        }

        private void writeVarint64ThreeBytes(long j) {
            byte[] bArr = this.buffer;
            int i = this.pos;
            this.pos = i - 1;
            bArr[i] = (byte) (((int) j) >>> 14);
            int i2 = this.pos;
            this.pos = i2 - 1;
            bArr[i2] = (byte) (((j >>> 7) & 127) | 128);
            int i3 = this.pos;
            this.pos = i3 - 1;
            bArr[i3] = (byte) ((j & 127) | 128);
        }

        private void writeVarint64TwoBytes(long j) {
            byte[] bArr = this.buffer;
            int i = this.pos;
            this.pos = i - 1;
            bArr[i] = (byte) (j >>> 7);
            int i2 = this.pos;
            this.pos = i2 - 1;
            bArr[i2] = (byte) ((((int) j) & 127) | 128);
        }

        public int bytesWrittenToCurrentBuffer() {
            return this.limitMinusOne - this.pos;
        }

        @Override // com.google.protobuf.BinaryWriter
        public void finishCurrentBuffer() {
            if (this.allocatedBuffer != null) {
                this.totalDoneBytes = bytesWrittenToCurrentBuffer() + this.totalDoneBytes;
                AllocatedBuffer allocatedBuffer = this.allocatedBuffer;
                allocatedBuffer.position((this.pos - allocatedBuffer.arrayOffset()) + 1);
                this.allocatedBuffer = null;
                this.pos = 0;
                this.limitMinusOne = 0;
            }
        }

        @Override // com.google.protobuf.BinaryWriter
        public int getTotalBytesWritten() {
            return bytesWrittenToCurrentBuffer() + this.totalDoneBytes;
        }

        @Override // com.google.protobuf.BinaryWriter
        public void requireSpace(int i) {
            if (spaceLeft() < i) {
                nextBuffer(newHeapBuffer(i));
            }
        }

        public int spaceLeft() {
            return this.pos - this.offsetMinusOne;
        }

        @Override // com.google.protobuf.ByteOutput
        public void write(byte b2) {
            byte[] bArr = this.buffer;
            int i = this.pos;
            this.pos = i - 1;
            bArr[i] = b2;
        }

        @Override // com.google.protobuf.Writer
        public void writeBool(int i, boolean z) {
            requireSpace(6);
            write(z ? (byte) 1 : (byte) 0);
            writeTag(i, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeBytes(int i, ByteString byteString) {
            try {
                byteString.writeToReverse(this);
                requireSpace(10);
                writeVarint32(byteString.size());
                writeTag(i, 2);
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }

        @Override // com.google.protobuf.Writer
        public void writeEndGroup(int i) {
            writeTag(i, 4);
        }

        @Override // com.google.protobuf.Writer
        public void writeFixed32(int i, int i2) {
            requireSpace(9);
            writeFixed32(i2);
            writeTag(i, 5);
        }

        @Override // com.google.protobuf.Writer
        public void writeFixed64(int i, long j) {
            requireSpace(13);
            writeFixed64(j);
            writeTag(i, 1);
        }

        @Override // com.google.protobuf.Writer
        public void writeGroup(int i, Object obj) {
            writeTag(i, 4);
            Protobuf.INSTANCE.writeTo(obj, this);
            writeTag(i, 3);
        }

        @Override // com.google.protobuf.Writer
        public void writeInt32(int i, int i2) {
            requireSpace(15);
            writeInt32(i2);
            writeTag(i, 0);
        }

        @Override // com.google.protobuf.ByteOutput
        public void writeLazy(byte[] bArr, int i, int i2) {
            if (spaceLeft() >= i2) {
                this.pos -= i2;
                System.arraycopy(bArr, i, this.buffer, this.pos + 1, i2);
            } else {
                this.totalDoneBytes += i2;
                this.buffers.addFirst(AllocatedBuffer.wrap(bArr, i, i2));
                nextBuffer();
            }
        }

        @Override // com.google.protobuf.Writer
        public void writeMessage(int i, Object obj) {
            int totalBytesWritten = getTotalBytesWritten();
            Protobuf.INSTANCE.writeTo(obj, this);
            int totalBytesWritten2 = getTotalBytesWritten() - totalBytesWritten;
            requireSpace(10);
            writeVarint32(totalBytesWritten2);
            writeTag(i, 2);
        }

        @Override // com.google.protobuf.Writer
        public void writeSInt32(int i, int i2) {
            requireSpace(10);
            writeSInt32(i2);
            writeTag(i, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeSInt64(int i, long j) {
            requireSpace(15);
            writeSInt64(j);
            writeTag(i, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeStartGroup(int i) {
            writeTag(i, 3);
        }

        @Override // com.google.protobuf.Writer
        public void writeString(int i, String str) {
            int totalBytesWritten = getTotalBytesWritten();
            writeString(str);
            int totalBytesWritten2 = getTotalBytesWritten() - totalBytesWritten;
            requireSpace(10);
            writeVarint32(totalBytesWritten2);
            writeTag(i, 2);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeTag(int i, int i2) {
            writeVarint32((i << 3) | i2);
        }

        @Override // com.google.protobuf.Writer
        public void writeUInt32(int i, int i2) {
            requireSpace(10);
            writeVarint32(i2);
            writeTag(i, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeUInt64(int i, long j) {
            requireSpace(15);
            writeVarint64(j);
            writeTag(i, 0);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeVarint32(int i) {
            if ((i & (-128)) == 0) {
                byte[] bArr = this.buffer;
                int i2 = this.pos;
                this.pos = i2 - 1;
                bArr[i2] = (byte) i;
                return;
            }
            if ((i & (-16384)) == 0) {
                writeVarint32TwoBytes(i);
                return;
            }
            if (((-2097152) & i) == 0) {
                writeVarint32ThreeBytes(i);
            } else if (((-268435456) & i) == 0) {
                writeVarint32FourBytes(i);
            } else {
                writeVarint32FiveBytes(i);
            }
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeVarint64(long j) {
            switch (BinaryWriter.computeUInt64SizeNoTag(j)) {
                case 1:
                    writeVarint64OneByte(j);
                    break;
                case 2:
                    writeVarint64TwoBytes(j);
                    break;
                case 3:
                    writeVarint64ThreeBytes(j);
                    break;
                case 4:
                    writeVarint64FourBytes(j);
                    break;
                case 5:
                    writeVarint64FiveBytes(j);
                    break;
                case 6:
                    writeVarint64SixBytes(j);
                    break;
                case 7:
                    writeVarint64SevenBytes(j);
                    break;
                case 8:
                    writeVarint64EightBytes(j);
                    break;
                case 9:
                    writeVarint64NineBytes(j);
                    break;
                case 10:
                    writeVarint64TenBytes(j);
                    break;
            }
        }

        private void nextBuffer(int i) {
            nextBuffer(newHeapBuffer(i));
        }

        @Override // com.google.protobuf.ByteOutput
        public void write(byte[] bArr, int i, int i2) {
            if (spaceLeft() < i2) {
                nextBuffer(newHeapBuffer(i2));
            }
            this.pos -= i2;
            System.arraycopy(bArr, i, this.buffer, this.pos + 1, i2);
        }

        private void nextBuffer(AllocatedBuffer allocatedBuffer) {
            if (allocatedBuffer.hasArray()) {
                finishCurrentBuffer();
                this.buffers.addFirst(allocatedBuffer);
                this.allocatedBuffer = allocatedBuffer;
                this.buffer = allocatedBuffer.array();
                int iArrayOffset = allocatedBuffer.arrayOffset();
                this.limit = allocatedBuffer.limit() + iArrayOffset;
                this.offset = allocatedBuffer.position() + iArrayOffset;
                this.offsetMinusOne = this.offset - 1;
                this.limitMinusOne = this.limit - 1;
                this.pos = this.limitMinusOne;
                return;
            }
            throw new RuntimeException("Allocator returned non-heap buffer");
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeBool(boolean z) {
            write(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeFixed32(int i) {
            byte[] bArr = this.buffer;
            int i2 = this.pos;
            this.pos = i2 - 1;
            bArr[i2] = (byte) ((i >> 24) & 255);
            int i3 = this.pos;
            this.pos = i3 - 1;
            bArr[i3] = (byte) ((i >> 16) & 255);
            int i4 = this.pos;
            this.pos = i4 - 1;
            bArr[i4] = (byte) ((i >> 8) & 255);
            int i5 = this.pos;
            this.pos = i5 - 1;
            bArr[i5] = (byte) (i & 255);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeFixed64(long j) {
            byte[] bArr = this.buffer;
            int i = this.pos;
            this.pos = i - 1;
            bArr[i] = (byte) (((int) (j >> 56)) & 255);
            int i2 = this.pos;
            this.pos = i2 - 1;
            bArr[i2] = (byte) (((int) (j >> 48)) & 255);
            int i3 = this.pos;
            this.pos = i3 - 1;
            bArr[i3] = (byte) (((int) (j >> 40)) & 255);
            int i4 = this.pos;
            this.pos = i4 - 1;
            bArr[i4] = (byte) (((int) (j >> 32)) & 255);
            int i5 = this.pos;
            this.pos = i5 - 1;
            bArr[i5] = (byte) (((int) (j >> 24)) & 255);
            int i6 = this.pos;
            this.pos = i6 - 1;
            bArr[i6] = (byte) (((int) (j >> 16)) & 255);
            int i7 = this.pos;
            this.pos = i7 - 1;
            bArr[i7] = (byte) (((int) (j >> 8)) & 255);
            int i8 = this.pos;
            this.pos = i8 - 1;
            bArr[i8] = (byte) (((int) j) & 255);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeInt32(int i) {
            if (i >= 0) {
                writeVarint32(i);
            } else {
                writeVarint64(i);
            }
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeSInt32(int i) {
            writeVarint32(CodedOutputStream.encodeZigZag32(i));
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeSInt64(long j) {
            writeVarint64(CodedOutputStream.encodeZigZag64(j));
        }

        @Override // com.google.protobuf.Writer
        public void writeGroup(int i, Object obj, Schema schema) {
            writeTag(i, 4);
            schema.writeTo(obj, this);
            writeTag(i, 3);
        }

        @Override // com.google.protobuf.ByteOutput
        public void write(ByteBuffer byteBuffer) {
            int iRemaining = byteBuffer.remaining();
            if (spaceLeft() < iRemaining) {
                nextBuffer(iRemaining);
            }
            this.pos -= iRemaining;
            byteBuffer.get(this.buffer, this.pos + 1, iRemaining);
        }

        @Override // com.google.protobuf.ByteOutput
        public void writeLazy(ByteBuffer byteBuffer) {
            int iRemaining = byteBuffer.remaining();
            if (spaceLeft() < iRemaining) {
                this.totalDoneBytes += iRemaining;
                this.buffers.addFirst(AllocatedBuffer.wrap(byteBuffer));
                nextBuffer();
            }
            this.pos -= iRemaining;
            byteBuffer.get(this.buffer, this.pos + 1, iRemaining);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeString(String str) {
            int i;
            int i2;
            int i3;
            char cCharAt;
            requireSpace(str.length());
            int length = str.length() - 1;
            this.pos -= length;
            while (length >= 0 && (cCharAt = str.charAt(length)) < 128) {
                this.buffer[this.pos + length] = (byte) cCharAt;
                length--;
            }
            if (length == -1) {
                this.pos--;
                return;
            }
            this.pos += length;
            while (length >= 0) {
                char cCharAt2 = str.charAt(length);
                if (cCharAt2 < 128 && (i3 = this.pos) > this.offsetMinusOne) {
                    byte[] bArr = this.buffer;
                    this.pos = i3 - 1;
                    bArr[i3] = (byte) cCharAt2;
                } else if (cCharAt2 < 2048 && (i2 = this.pos) > this.offset) {
                    byte[] bArr2 = this.buffer;
                    this.pos = i2 - 1;
                    bArr2[i2] = (byte) ((cCharAt2 & '?') | 128);
                    int i4 = this.pos;
                    this.pos = i4 - 1;
                    bArr2[i4] = (byte) ((cCharAt2 >>> 6) | 960);
                } else if ((cCharAt2 < 55296 || 57343 < cCharAt2) && (i = this.pos) > this.offset + 1) {
                    byte[] bArr3 = this.buffer;
                    this.pos = i - 1;
                    bArr3[i] = (byte) ((cCharAt2 & '?') | 128);
                    int i5 = this.pos;
                    this.pos = i5 - 1;
                    bArr3[i5] = (byte) (((cCharAt2 >>> 6) & 63) | 128);
                    int i6 = this.pos;
                    this.pos = i6 - 1;
                    bArr3[i6] = (byte) ((cCharAt2 >>> '\f') | 480);
                } else {
                    if (this.pos > this.offset + 2) {
                        if (length != 0) {
                            char cCharAt3 = str.charAt(length - 1);
                            if (Character.isSurrogatePair(cCharAt3, cCharAt2)) {
                                length--;
                                int codePoint = Character.toCodePoint(cCharAt3, cCharAt2);
                                byte[] bArr4 = this.buffer;
                                int i7 = this.pos;
                                this.pos = i7 - 1;
                                bArr4[i7] = (byte) ((codePoint & 63) | 128);
                                int i8 = this.pos;
                                this.pos = i8 - 1;
                                bArr4[i8] = (byte) (((codePoint >>> 6) & 63) | 128);
                                int i9 = this.pos;
                                this.pos = i9 - 1;
                                bArr4[i9] = (byte) (((codePoint >>> 12) & 63) | 128);
                                int i10 = this.pos;
                                this.pos = i10 - 1;
                                bArr4[i10] = (byte) ((codePoint >>> 18) | 240);
                            }
                        }
                        throw new Utf8.UnpairedSurrogateException(length - 1, length);
                    }
                    requireSpace(length);
                    length++;
                }
                length--;
            }
        }

        @Override // com.google.protobuf.Writer
        public void writeMessage(int i, Object obj, Schema schema) {
            int totalBytesWritten = getTotalBytesWritten();
            schema.writeTo(obj, this);
            int totalBytesWritten2 = getTotalBytesWritten() - totalBytesWritten;
            requireSpace(10);
            writeVarint32(totalBytesWritten2);
            writeTag(i, 2);
        }
    }

    private static final class UnsafeDirectWriter extends BinaryWriter {
        public ByteBuffer buffer;
        public long bufferOffset;
        public long limitMinusOne;
        public long pos;

        public UnsafeDirectWriter(BufferAllocator bufferAllocator, int i) {
            super(bufferAllocator, i);
            nextBuffer();
        }

        private int bufferPos() {
            return (int) (this.pos - this.bufferOffset);
        }

        private int bytesWrittenToCurrentBuffer() {
            return (int) (this.limitMinusOne - this.pos);
        }

        public static boolean isSupported() {
            return UnsafeUtil.HAS_UNSAFE_BYTEBUFFER_OPERATIONS;
        }

        private void nextBuffer() {
            nextBuffer(newDirectBuffer());
        }

        private int spaceLeft() {
            return bufferPos() + 1;
        }

        private void writeVarint32FiveBytes(int i) {
            long j = this.pos;
            this.pos = j - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j, (byte) (i >>> 28));
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j2, (byte) (((i >>> 21) & 127) | 128));
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j3, (byte) (((i >>> 14) & 127) | 128));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j4, (byte) (((i >>> 7) & 127) | 128));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j5, (byte) ((i & 127) | 128));
        }

        private void writeVarint32FourBytes(int i) {
            long j = this.pos;
            this.pos = j - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j, (byte) (i >>> 21));
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j2, (byte) (((i >>> 14) & 127) | 128));
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j3, (byte) (((i >>> 7) & 127) | 128));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j4, (byte) ((i & 127) | 128));
        }

        private void writeVarint32OneByte(int i) {
            long j = this.pos;
            this.pos = j - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j, (byte) i);
        }

        private void writeVarint32ThreeBytes(int i) {
            long j = this.pos;
            this.pos = j - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j, (byte) (i >>> 14));
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j2, (byte) (((i >>> 7) & 127) | 128));
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j3, (byte) ((i & 127) | 128));
        }

        private void writeVarint32TwoBytes(int i) {
            long j = this.pos;
            this.pos = j - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j, (byte) (i >>> 7));
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j2, (byte) ((i & 127) | 128));
        }

        private void writeVarint64EightBytes(long j) {
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j2, (byte) (j >>> 49));
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j3, (byte) (((j >>> 42) & 127) | 128));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j4, (byte) (((j >>> 35) & 127) | 128));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j5, (byte) (((j >>> 28) & 127) | 128));
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j6, (byte) (((j >>> 21) & 127) | 128));
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j7, (byte) (((j >>> 14) & 127) | 128));
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j8, (byte) (((j >>> 7) & 127) | 128));
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j9, (byte) ((j & 127) | 128));
        }

        private void writeVarint64FiveBytes(long j) {
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j2, (byte) (j >>> 28));
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j3, (byte) (((j >>> 21) & 127) | 128));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j4, (byte) (((j >>> 14) & 127) | 128));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j5, (byte) (((j >>> 7) & 127) | 128));
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j6, (byte) ((j & 127) | 128));
        }

        private void writeVarint64FourBytes(long j) {
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j2, (byte) (j >>> 21));
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j3, (byte) (((j >>> 14) & 127) | 128));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j4, (byte) (((j >>> 7) & 127) | 128));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j5, (byte) ((j & 127) | 128));
        }

        private void writeVarint64NineBytes(long j) {
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j2, (byte) (j >>> 56));
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j3, (byte) (((j >>> 49) & 127) | 128));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j4, (byte) (((j >>> 42) & 127) | 128));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j5, (byte) (((j >>> 35) & 127) | 128));
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j6, (byte) (((j >>> 28) & 127) | 128));
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j7, (byte) (((j >>> 21) & 127) | 128));
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j8, (byte) (((j >>> 14) & 127) | 128));
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j9, (byte) (((j >>> 7) & 127) | 128));
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j10, (byte) ((j & 127) | 128));
        }

        private void writeVarint64OneByte(long j) {
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j2, (byte) j);
        }

        private void writeVarint64SevenBytes(long j) {
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j2, (byte) (j >>> 42));
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j3, (byte) (((j >>> 35) & 127) | 128));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j4, (byte) (((j >>> 28) & 127) | 128));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j5, (byte) (((j >>> 21) & 127) | 128));
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j6, (byte) (((j >>> 14) & 127) | 128));
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j7, (byte) (((j >>> 7) & 127) | 128));
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j8, (byte) ((j & 127) | 128));
        }

        private void writeVarint64SixBytes(long j) {
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j2, (byte) (j >>> 35));
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j3, (byte) (((j >>> 28) & 127) | 128));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j4, (byte) (((j >>> 21) & 127) | 128));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j5, (byte) (((j >>> 14) & 127) | 128));
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j6, (byte) (((j >>> 7) & 127) | 128));
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j7, (byte) ((j & 127) | 128));
        }

        private void writeVarint64TenBytes(long j) {
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j2, (byte) (j >>> 63));
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j3, (byte) (((j >>> 56) & 127) | 128));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j4, (byte) (((j >>> 49) & 127) | 128));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j5, (byte) (((j >>> 42) & 127) | 128));
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j6, (byte) (((j >>> 35) & 127) | 128));
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j7, (byte) (((j >>> 28) & 127) | 128));
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j8, (byte) (((j >>> 21) & 127) | 128));
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j9, (byte) (((j >>> 14) & 127) | 128));
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j10, (byte) (((j >>> 7) & 127) | 128));
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j11, (byte) ((j & 127) | 128));
        }

        private void writeVarint64ThreeBytes(long j) {
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j2, (byte) (((int) j) >>> 14));
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j3, (byte) (((j >>> 7) & 127) | 128));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j4, (byte) ((j & 127) | 128));
        }

        private void writeVarint64TwoBytes(long j) {
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j2, (byte) (j >>> 7));
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j3, (byte) ((((int) j) & 127) | 128));
        }

        @Override // com.google.protobuf.BinaryWriter
        public void finishCurrentBuffer() {
            if (this.buffer != null) {
                this.totalDoneBytes += bytesWrittenToCurrentBuffer();
                this.buffer.position(bufferPos() + 1);
                this.buffer = null;
                this.pos = 0L;
                this.limitMinusOne = 0L;
            }
        }

        @Override // com.google.protobuf.BinaryWriter
        public int getTotalBytesWritten() {
            return this.totalDoneBytes + bytesWrittenToCurrentBuffer();
        }

        @Override // com.google.protobuf.BinaryWriter
        public void requireSpace(int i) {
            if (spaceLeft() < i) {
                nextBuffer(newDirectBuffer(i));
            }
        }

        @Override // com.google.protobuf.ByteOutput
        public void write(byte b2) {
            long j = this.pos;
            this.pos = j - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j, b2);
        }

        @Override // com.google.protobuf.Writer
        public void writeBool(int i, boolean z) {
            requireSpace(6);
            write(z ? (byte) 1 : (byte) 0);
            writeTag(i, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeBytes(int i, ByteString byteString) {
            try {
                byteString.writeToReverse(this);
                requireSpace(10);
                writeVarint32(byteString.size());
                writeTag(i, 2);
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }

        @Override // com.google.protobuf.Writer
        public void writeEndGroup(int i) {
            writeTag(i, 4);
        }

        @Override // com.google.protobuf.Writer
        public void writeFixed32(int i, int i2) {
            requireSpace(9);
            writeFixed32(i2);
            writeTag(i, 5);
        }

        @Override // com.google.protobuf.Writer
        public void writeFixed64(int i, long j) {
            requireSpace(13);
            writeFixed64(j);
            writeTag(i, 1);
        }

        @Override // com.google.protobuf.Writer
        public void writeGroup(int i, Object obj) {
            writeTag(i, 4);
            Protobuf.INSTANCE.writeTo(obj, this);
            writeTag(i, 3);
        }

        @Override // com.google.protobuf.Writer
        public void writeInt32(int i, int i2) {
            requireSpace(15);
            writeInt32(i2);
            writeTag(i, 0);
        }

        @Override // com.google.protobuf.ByteOutput
        public void writeLazy(byte[] bArr, int i, int i2) {
            if (spaceLeft() < i2) {
                this.totalDoneBytes += i2;
                this.buffers.addFirst(AllocatedBuffer.wrap(bArr, i, i2));
                nextBuffer();
            } else {
                this.pos -= (long) i2;
                this.buffer.position(bufferPos() + 1);
                this.buffer.put(bArr, i, i2);
            }
        }

        @Override // com.google.protobuf.Writer
        public void writeMessage(int i, Object obj) {
            int totalBytesWritten = getTotalBytesWritten();
            Protobuf.INSTANCE.writeTo(obj, this);
            int totalBytesWritten2 = getTotalBytesWritten() - totalBytesWritten;
            requireSpace(10);
            writeVarint32(totalBytesWritten2);
            writeTag(i, 2);
        }

        @Override // com.google.protobuf.Writer
        public void writeSInt32(int i, int i2) {
            requireSpace(10);
            writeSInt32(i2);
            writeTag(i, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeSInt64(int i, long j) {
            requireSpace(15);
            writeSInt64(j);
            writeTag(i, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeStartGroup(int i) {
            writeTag(i, 3);
        }

        @Override // com.google.protobuf.Writer
        public void writeString(int i, String str) {
            int totalBytesWritten = getTotalBytesWritten();
            writeString(str);
            int totalBytesWritten2 = getTotalBytesWritten() - totalBytesWritten;
            requireSpace(10);
            writeVarint32(totalBytesWritten2);
            writeTag(i, 2);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeTag(int i, int i2) {
            writeVarint32((i << 3) | i2);
        }

        @Override // com.google.protobuf.Writer
        public void writeUInt32(int i, int i2) {
            requireSpace(10);
            writeVarint32(i2);
            writeTag(i, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeUInt64(int i, long j) {
            requireSpace(15);
            writeVarint64(j);
            writeTag(i, 0);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeVarint32(int i) {
            if ((i & (-128)) == 0) {
                writeVarint32OneByte(i);
                return;
            }
            if ((i & (-16384)) == 0) {
                writeVarint32TwoBytes(i);
                return;
            }
            if (((-2097152) & i) == 0) {
                writeVarint32ThreeBytes(i);
            } else if (((-268435456) & i) == 0) {
                writeVarint32FourBytes(i);
            } else {
                writeVarint32FiveBytes(i);
            }
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeVarint64(long j) {
            switch (BinaryWriter.computeUInt64SizeNoTag(j)) {
                case 1:
                    writeVarint64OneByte(j);
                    break;
                case 2:
                    writeVarint64TwoBytes(j);
                    break;
                case 3:
                    writeVarint64ThreeBytes(j);
                    break;
                case 4:
                    writeVarint64FourBytes(j);
                    break;
                case 5:
                    writeVarint64FiveBytes(j);
                    break;
                case 6:
                    writeVarint64SixBytes(j);
                    break;
                case 7:
                    writeVarint64SevenBytes(j);
                    break;
                case 8:
                    writeVarint64EightBytes(j);
                    break;
                case 9:
                    writeVarint64NineBytes(j);
                    break;
                case 10:
                    writeVarint64TenBytes(j);
                    break;
            }
        }

        private void nextBuffer(int i) {
            nextBuffer(newDirectBuffer(i));
        }

        private void nextBuffer(AllocatedBuffer allocatedBuffer) {
            if (allocatedBuffer.hasNioBuffer()) {
                ByteBuffer byteBufferNioBuffer = allocatedBuffer.nioBuffer();
                if (byteBufferNioBuffer.isDirect()) {
                    finishCurrentBuffer();
                    this.buffers.addFirst(allocatedBuffer);
                    this.buffer = byteBufferNioBuffer;
                    ByteBuffer byteBuffer = this.buffer;
                    byteBuffer.limit(byteBuffer.capacity());
                    this.buffer.position(0);
                    this.bufferOffset = UnsafeUtil.addressOffset(this.buffer);
                    this.limitMinusOne = this.bufferOffset + ((long) (this.buffer.limit() - 1));
                    this.pos = this.limitMinusOne;
                    return;
                }
                throw new RuntimeException("Allocator returned non-direct buffer");
            }
            throw new RuntimeException("Allocated buffer does not have NIO buffer");
        }

        @Override // com.google.protobuf.ByteOutput
        public void write(byte[] bArr, int i, int i2) {
            if (spaceLeft() < i2) {
                nextBuffer(newDirectBuffer(i2));
            }
            this.pos -= (long) i2;
            this.buffer.position(bufferPos() + 1);
            this.buffer.put(bArr, i, i2);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeBool(boolean z) {
            write(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeFixed32(int i) {
            long j = this.pos;
            this.pos = j - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j, (byte) ((i >> 24) & 255));
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j2, (byte) ((i >> 16) & 255));
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j3, (byte) ((i >> 8) & 255));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j4, (byte) (i & 255));
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeFixed64(long j) {
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j2, (byte) (((int) (j >> 56)) & 255));
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j3, (byte) (((int) (j >> 48)) & 255));
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j4, (byte) (((int) (j >> 40)) & 255));
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j5, (byte) (((int) (j >> 32)) & 255));
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j6, (byte) (((int) (j >> 24)) & 255));
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j7, (byte) (((int) (j >> 16)) & 255));
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j8, (byte) (((int) (j >> 8)) & 255));
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.MEMORY_ACCESSOR.putByte(j9, (byte) (((int) j) & 255));
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeInt32(int i) {
            if (i >= 0) {
                writeVarint32(i);
            } else {
                writeVarint64(i);
            }
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeSInt32(int i) {
            writeVarint32(CodedOutputStream.encodeZigZag32(i));
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeSInt64(long j) {
            writeVarint64(CodedOutputStream.encodeZigZag64(j));
        }

        @Override // com.google.protobuf.Writer
        public void writeGroup(int i, Object obj, Schema schema) {
            writeTag(i, 4);
            schema.writeTo(obj, this);
            writeTag(i, 3);
        }

        /* JADX WARN: Code duplicated, block: B:17:0x0048  */
        /* JADX WARN: Code duplicated, block: B:19:0x004c  */
        /* JADX WARN: Code duplicated, block: B:21:0x0054  */
        /* JADX WARN: Code duplicated, block: B:22:0x0073  */
        /* JADX WARN: Code duplicated, block: B:24:0x0078  */
        /* JADX WARN: Code duplicated, block: B:26:0x007d  */
        /* JADX WARN: Code duplicated, block: B:28:0x0086  */
        /* JADX WARN: Code duplicated, block: B:29:0x00b5  */
        /* JADX WARN: Code duplicated, block: B:31:0x00c0 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:32:0x00c2  */
        /* JADX WARN: Code duplicated, block: B:34:0x00ce  */
        /* JADX WARN: Code duplicated, block: B:37:0x011e  */
        /* JADX WARN: Code duplicated, block: B:43:0x0116 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:44:0x0116 A[SYNTHETIC] */
        @Override // com.google.protobuf.BinaryWriter
        public void writeString(String str) {
            long j;
            char cCharAt;
            long j2;
            char cCharAt2;
            requireSpace(str.length());
            int length = str.length();
            while (true) {
                length--;
                if (length < 0 || (cCharAt2 = str.charAt(length)) >= 128) {
                    break;
                }
                long j3 = this.pos;
                this.pos = j3 - 1;
                UnsafeUtil.MEMORY_ACCESSOR.putByte(j3, (byte) cCharAt2);
            }
            if (length == -1) {
                return;
            }
            while (length >= 0) {
                char cCharAt3 = str.charAt(length);
                if (cCharAt3 < 128) {
                    long j4 = this.pos;
                    if (j4 >= this.bufferOffset) {
                        this.pos = j4 - 1;
                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j4, (byte) cCharAt3);
                    } else if (cCharAt3 < 2048) {
                        j2 = this.pos;
                        if (j2 > this.bufferOffset) {
                            this.pos = j2 - 1;
                            UnsafeUtil.MEMORY_ACCESSOR.putByte(j2, (byte) ((cCharAt3 & '?') | 128));
                            long j5 = this.pos;
                            this.pos = j5 - 1;
                            UnsafeUtil.MEMORY_ACCESSOR.putByte(j5, (byte) ((cCharAt3 >>> 6) | 960));
                        } else if (cCharAt3 >= 55296 || 57343 < cCharAt3) {
                            j = this.pos;
                            if (j > this.bufferOffset + 1) {
                                this.pos = j - 1;
                                UnsafeUtil.MEMORY_ACCESSOR.putByte(j, (byte) ((cCharAt3 & '?') | 128));
                                long j6 = this.pos;
                                this.pos = j6 - 1;
                                UnsafeUtil.MEMORY_ACCESSOR.putByte(j6, (byte) (((cCharAt3 >>> 6) & 63) | 128));
                                long j7 = this.pos;
                                this.pos = j7 - 1;
                                UnsafeUtil.MEMORY_ACCESSOR.putByte(j7, (byte) ((cCharAt3 >>> '\f') | 480));
                            } else {
                                if (this.pos > this.bufferOffset + 2) {
                                    if (length != 0) {
                                        cCharAt = str.charAt(length - 1);
                                        if (Character.isSurrogatePair(cCharAt, cCharAt3)) {
                                            length--;
                                            int codePoint = Character.toCodePoint(cCharAt, cCharAt3);
                                            long j8 = this.pos;
                                            this.pos = j8 - 1;
                                            UnsafeUtil.MEMORY_ACCESSOR.putByte(j8, (byte) ((codePoint & 63) | 128));
                                            long j9 = this.pos;
                                            this.pos = j9 - 1;
                                            UnsafeUtil.MEMORY_ACCESSOR.putByte(j9, (byte) (((codePoint >>> 6) & 63) | 128));
                                            long j10 = this.pos;
                                            this.pos = j10 - 1;
                                            UnsafeUtil.MEMORY_ACCESSOR.putByte(j10, (byte) (((codePoint >>> 12) & 63) | 128));
                                            long j11 = this.pos;
                                            this.pos = j11 - 1;
                                            UnsafeUtil.MEMORY_ACCESSOR.putByte(j11, (byte) ((codePoint >>> 18) | 240));
                                        }
                                    }
                                    throw new Utf8.UnpairedSurrogateException(length - 1, length);
                                }
                                requireSpace(length);
                                length++;
                            }
                        } else {
                            if (this.pos > this.bufferOffset + 2) {
                                if (length != 0) {
                                    cCharAt = str.charAt(length - 1);
                                    if (Character.isSurrogatePair(cCharAt, cCharAt3)) {
                                        length--;
                                        int codePoint2 = Character.toCodePoint(cCharAt, cCharAt3);
                                        long j12 = this.pos;
                                        this.pos = j12 - 1;
                                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j12, (byte) ((codePoint2 & 63) | 128));
                                        long j13 = this.pos;
                                        this.pos = j13 - 1;
                                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j13, (byte) (((codePoint2 >>> 6) & 63) | 128));
                                        long j14 = this.pos;
                                        this.pos = j14 - 1;
                                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j14, (byte) (((codePoint2 >>> 12) & 63) | 128));
                                        long j15 = this.pos;
                                        this.pos = j15 - 1;
                                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j15, (byte) ((codePoint2 >>> 18) | 240));
                                    }
                                }
                                throw new Utf8.UnpairedSurrogateException(length - 1, length);
                            }
                            requireSpace(length);
                            length++;
                        }
                    } else if (cCharAt3 >= 55296) {
                        j = this.pos;
                        if (j > this.bufferOffset + 1) {
                            this.pos = j - 1;
                            UnsafeUtil.MEMORY_ACCESSOR.putByte(j, (byte) ((cCharAt3 & '?') | 128));
                            long j16 = this.pos;
                            this.pos = j16 - 1;
                            UnsafeUtil.MEMORY_ACCESSOR.putByte(j16, (byte) (((cCharAt3 >>> 6) & 63) | 128));
                            long j17 = this.pos;
                            this.pos = j17 - 1;
                            UnsafeUtil.MEMORY_ACCESSOR.putByte(j17, (byte) ((cCharAt3 >>> '\f') | 480));
                        } else {
                            if (this.pos > this.bufferOffset + 2) {
                                if (length != 0) {
                                    cCharAt = str.charAt(length - 1);
                                    if (Character.isSurrogatePair(cCharAt, cCharAt3)) {
                                        length--;
                                        int codePoint3 = Character.toCodePoint(cCharAt, cCharAt3);
                                        long j18 = this.pos;
                                        this.pos = j18 - 1;
                                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j18, (byte) ((codePoint3 & 63) | 128));
                                        long j19 = this.pos;
                                        this.pos = j19 - 1;
                                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j19, (byte) (((codePoint3 >>> 6) & 63) | 128));
                                        long j110 = this.pos;
                                        this.pos = j110 - 1;
                                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j110, (byte) (((codePoint3 >>> 12) & 63) | 128));
                                        long j111 = this.pos;
                                        this.pos = j111 - 1;
                                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j111, (byte) ((codePoint3 >>> 18) | 240));
                                    }
                                }
                                throw new Utf8.UnpairedSurrogateException(length - 1, length);
                            }
                            requireSpace(length);
                            length++;
                        }
                    } else {
                        j = this.pos;
                        if (j > this.bufferOffset + 1) {
                            this.pos = j - 1;
                            UnsafeUtil.MEMORY_ACCESSOR.putByte(j, (byte) ((cCharAt3 & '?') | 128));
                            long j112 = this.pos;
                            this.pos = j112 - 1;
                            UnsafeUtil.MEMORY_ACCESSOR.putByte(j112, (byte) (((cCharAt3 >>> 6) & 63) | 128));
                            long j113 = this.pos;
                            this.pos = j113 - 1;
                            UnsafeUtil.MEMORY_ACCESSOR.putByte(j113, (byte) ((cCharAt3 >>> '\f') | 480));
                        } else {
                            if (this.pos > this.bufferOffset + 2) {
                                if (length != 0) {
                                    cCharAt = str.charAt(length - 1);
                                    if (Character.isSurrogatePair(cCharAt, cCharAt3)) {
                                        length--;
                                        int codePoint4 = Character.toCodePoint(cCharAt, cCharAt3);
                                        long j114 = this.pos;
                                        this.pos = j114 - 1;
                                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j114, (byte) ((codePoint4 & 63) | 128));
                                        long j115 = this.pos;
                                        this.pos = j115 - 1;
                                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j115, (byte) (((codePoint4 >>> 6) & 63) | 128));
                                        long j116 = this.pos;
                                        this.pos = j116 - 1;
                                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j116, (byte) (((codePoint4 >>> 12) & 63) | 128));
                                        long j117 = this.pos;
                                        this.pos = j117 - 1;
                                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j117, (byte) ((codePoint4 >>> 18) | 240));
                                    }
                                }
                                throw new Utf8.UnpairedSurrogateException(length - 1, length);
                            }
                            requireSpace(length);
                            length++;
                        }
                    }
                } else if (cCharAt3 < 2048) {
                    j2 = this.pos;
                    if (j2 > this.bufferOffset) {
                        this.pos = j2 - 1;
                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j2, (byte) ((cCharAt3 & '?') | 128));
                        long j20 = this.pos;
                        this.pos = j20 - 1;
                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j20, (byte) ((cCharAt3 >>> 6) | 960));
                    } else if (cCharAt3 >= 55296) {
                        j = this.pos;
                        if (j > this.bufferOffset + 1) {
                            this.pos = j - 1;
                            UnsafeUtil.MEMORY_ACCESSOR.putByte(j, (byte) ((cCharAt3 & '?') | 128));
                            long j118 = this.pos;
                            this.pos = j118 - 1;
                            UnsafeUtil.MEMORY_ACCESSOR.putByte(j118, (byte) (((cCharAt3 >>> 6) & 63) | 128));
                            long j119 = this.pos;
                            this.pos = j119 - 1;
                            UnsafeUtil.MEMORY_ACCESSOR.putByte(j119, (byte) ((cCharAt3 >>> '\f') | 480));
                        } else {
                            if (this.pos > this.bufferOffset + 2) {
                                if (length != 0) {
                                    cCharAt = str.charAt(length - 1);
                                    if (Character.isSurrogatePair(cCharAt, cCharAt3)) {
                                        length--;
                                        int codePoint5 = Character.toCodePoint(cCharAt, cCharAt3);
                                        long j1110 = this.pos;
                                        this.pos = j1110 - 1;
                                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j1110, (byte) ((codePoint5 & 63) | 128));
                                        long j1111 = this.pos;
                                        this.pos = j1111 - 1;
                                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j1111, (byte) (((codePoint5 >>> 6) & 63) | 128));
                                        long j1112 = this.pos;
                                        this.pos = j1112 - 1;
                                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j1112, (byte) (((codePoint5 >>> 12) & 63) | 128));
                                        long j1113 = this.pos;
                                        this.pos = j1113 - 1;
                                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j1113, (byte) ((codePoint5 >>> 18) | 240));
                                    }
                                }
                                throw new Utf8.UnpairedSurrogateException(length - 1, length);
                            }
                            requireSpace(length);
                            length++;
                        }
                    } else {
                        j = this.pos;
                        if (j > this.bufferOffset + 1) {
                            this.pos = j - 1;
                            UnsafeUtil.MEMORY_ACCESSOR.putByte(j, (byte) ((cCharAt3 & '?') | 128));
                            long j1114 = this.pos;
                            this.pos = j1114 - 1;
                            UnsafeUtil.MEMORY_ACCESSOR.putByte(j1114, (byte) (((cCharAt3 >>> 6) & 63) | 128));
                            long j1115 = this.pos;
                            this.pos = j1115 - 1;
                            UnsafeUtil.MEMORY_ACCESSOR.putByte(j1115, (byte) ((cCharAt3 >>> '\f') | 480));
                        } else {
                            if (this.pos > this.bufferOffset + 2) {
                                if (length != 0) {
                                    cCharAt = str.charAt(length - 1);
                                    if (Character.isSurrogatePair(cCharAt, cCharAt3)) {
                                        length--;
                                        int codePoint6 = Character.toCodePoint(cCharAt, cCharAt3);
                                        long j1116 = this.pos;
                                        this.pos = j1116 - 1;
                                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j1116, (byte) ((codePoint6 & 63) | 128));
                                        long j1117 = this.pos;
                                        this.pos = j1117 - 1;
                                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j1117, (byte) (((codePoint6 >>> 6) & 63) | 128));
                                        long j1118 = this.pos;
                                        this.pos = j1118 - 1;
                                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j1118, (byte) (((codePoint6 >>> 12) & 63) | 128));
                                        long j1119 = this.pos;
                                        this.pos = j1119 - 1;
                                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j1119, (byte) ((codePoint6 >>> 18) | 240));
                                    }
                                }
                                throw new Utf8.UnpairedSurrogateException(length - 1, length);
                            }
                            requireSpace(length);
                            length++;
                        }
                    }
                } else if (cCharAt3 >= 55296) {
                    j = this.pos;
                    if (j > this.bufferOffset + 1) {
                        this.pos = j - 1;
                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j, (byte) ((cCharAt3 & '?') | 128));
                        long j11110 = this.pos;
                        this.pos = j11110 - 1;
                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j11110, (byte) (((cCharAt3 >>> 6) & 63) | 128));
                        long j11111 = this.pos;
                        this.pos = j11111 - 1;
                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j11111, (byte) ((cCharAt3 >>> '\f') | 480));
                    } else {
                        if (this.pos > this.bufferOffset + 2) {
                            if (length != 0) {
                                cCharAt = str.charAt(length - 1);
                                if (Character.isSurrogatePair(cCharAt, cCharAt3)) {
                                    length--;
                                    int codePoint7 = Character.toCodePoint(cCharAt, cCharAt3);
                                    long j11112 = this.pos;
                                    this.pos = j11112 - 1;
                                    UnsafeUtil.MEMORY_ACCESSOR.putByte(j11112, (byte) ((codePoint7 & 63) | 128));
                                    long j11113 = this.pos;
                                    this.pos = j11113 - 1;
                                    UnsafeUtil.MEMORY_ACCESSOR.putByte(j11113, (byte) (((codePoint7 >>> 6) & 63) | 128));
                                    long j11114 = this.pos;
                                    this.pos = j11114 - 1;
                                    UnsafeUtil.MEMORY_ACCESSOR.putByte(j11114, (byte) (((codePoint7 >>> 12) & 63) | 128));
                                    long j11115 = this.pos;
                                    this.pos = j11115 - 1;
                                    UnsafeUtil.MEMORY_ACCESSOR.putByte(j11115, (byte) ((codePoint7 >>> 18) | 240));
                                }
                            }
                            throw new Utf8.UnpairedSurrogateException(length - 1, length);
                        }
                        requireSpace(length);
                        length++;
                    }
                } else {
                    j = this.pos;
                    if (j > this.bufferOffset + 1) {
                        this.pos = j - 1;
                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j, (byte) ((cCharAt3 & '?') | 128));
                        long j11116 = this.pos;
                        this.pos = j11116 - 1;
                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j11116, (byte) (((cCharAt3 >>> 6) & 63) | 128));
                        long j11117 = this.pos;
                        this.pos = j11117 - 1;
                        UnsafeUtil.MEMORY_ACCESSOR.putByte(j11117, (byte) ((cCharAt3 >>> '\f') | 480));
                    } else {
                        if (this.pos > this.bufferOffset + 2) {
                            if (length != 0) {
                                cCharAt = str.charAt(length - 1);
                                if (Character.isSurrogatePair(cCharAt, cCharAt3)) {
                                    length--;
                                    int codePoint8 = Character.toCodePoint(cCharAt, cCharAt3);
                                    long j11118 = this.pos;
                                    this.pos = j11118 - 1;
                                    UnsafeUtil.MEMORY_ACCESSOR.putByte(j11118, (byte) ((codePoint8 & 63) | 128));
                                    long j11119 = this.pos;
                                    this.pos = j11119 - 1;
                                    UnsafeUtil.MEMORY_ACCESSOR.putByte(j11119, (byte) (((codePoint8 >>> 6) & 63) | 128));
                                    long j111110 = this.pos;
                                    this.pos = j111110 - 1;
                                    UnsafeUtil.MEMORY_ACCESSOR.putByte(j111110, (byte) (((codePoint8 >>> 12) & 63) | 128));
                                    long j111111 = this.pos;
                                    this.pos = j111111 - 1;
                                    UnsafeUtil.MEMORY_ACCESSOR.putByte(j111111, (byte) ((codePoint8 >>> 18) | 240));
                                }
                            }
                            throw new Utf8.UnpairedSurrogateException(length - 1, length);
                        }
                        requireSpace(length);
                        length++;
                    }
                }
                length--;
            }
        }

        @Override // com.google.protobuf.ByteOutput
        public void write(ByteBuffer byteBuffer) {
            int iRemaining = byteBuffer.remaining();
            if (spaceLeft() < iRemaining) {
                nextBuffer(iRemaining);
            }
            this.pos -= (long) iRemaining;
            this.buffer.position(bufferPos() + 1);
            this.buffer.put(byteBuffer);
        }

        @Override // com.google.protobuf.ByteOutput
        public void writeLazy(ByteBuffer byteBuffer) {
            int iRemaining = byteBuffer.remaining();
            if (spaceLeft() < iRemaining) {
                this.totalDoneBytes += iRemaining;
                this.buffers.addFirst(AllocatedBuffer.wrap(byteBuffer));
                nextBuffer();
            } else {
                this.pos -= (long) iRemaining;
                this.buffer.position(bufferPos() + 1);
                this.buffer.put(byteBuffer);
            }
        }

        @Override // com.google.protobuf.Writer
        public void writeMessage(int i, Object obj, Schema schema) {
            int totalBytesWritten = getTotalBytesWritten();
            schema.writeTo(obj, this);
            int totalBytesWritten2 = getTotalBytesWritten() - totalBytesWritten;
            requireSpace(10);
            writeVarint32(totalBytesWritten2);
            writeTag(i, 2);
        }
    }

    private static final class UnsafeHeapWriter extends BinaryWriter {
        public AllocatedBuffer allocatedBuffer;
        public byte[] buffer;
        public long limit;
        public long limitMinusOne;
        public long offset;
        public long offsetMinusOne;
        public long pos;

        public UnsafeHeapWriter(BufferAllocator bufferAllocator, int i) {
            super(bufferAllocator, i);
            nextBuffer();
        }

        private int arrayPos() {
            return (int) this.pos;
        }

        public static boolean isSupported() {
            return UnsafeUtil.HAS_UNSAFE_ARRAY_OPERATIONS;
        }

        private void nextBuffer() {
            nextBuffer(newHeapBuffer());
        }

        private void writeVarint32FiveBytes(int i) {
            byte[] bArr = this.buffer;
            long j = this.pos;
            this.pos = j - 1;
            UnsafeUtil.putByte(bArr, j, (byte) (i >>> 28));
            byte[] bArr2 = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(bArr2, j2, (byte) (((i >>> 21) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr3, j3, (byte) (((i >>> 14) & 127) | 128));
            byte[] bArr4 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(bArr4, j4, (byte) (((i >>> 7) & 127) | 128));
            byte[] bArr5 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(bArr5, j5, (byte) ((i & 127) | 128));
        }

        private void writeVarint32FourBytes(int i) {
            byte[] bArr = this.buffer;
            long j = this.pos;
            this.pos = j - 1;
            UnsafeUtil.putByte(bArr, j, (byte) (i >>> 21));
            byte[] bArr2 = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(bArr2, j2, (byte) (((i >>> 14) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr3, j3, (byte) (((i >>> 7) & 127) | 128));
            byte[] bArr4 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(bArr4, j4, (byte) ((i & 127) | 128));
        }

        private void writeVarint32OneByte(int i) {
            byte[] bArr = this.buffer;
            long j = this.pos;
            this.pos = j - 1;
            UnsafeUtil.putByte(bArr, j, (byte) i);
        }

        private void writeVarint32ThreeBytes(int i) {
            byte[] bArr = this.buffer;
            long j = this.pos;
            this.pos = j - 1;
            UnsafeUtil.putByte(bArr, j, (byte) (i >>> 14));
            byte[] bArr2 = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(bArr2, j2, (byte) (((i >>> 7) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr3, j3, (byte) ((i & 127) | 128));
        }

        private void writeVarint32TwoBytes(int i) {
            byte[] bArr = this.buffer;
            long j = this.pos;
            this.pos = j - 1;
            UnsafeUtil.putByte(bArr, j, (byte) (i >>> 7));
            byte[] bArr2 = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(bArr2, j2, (byte) ((i & 127) | 128));
        }

        private void writeVarint64EightBytes(long j) {
            byte[] bArr = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(bArr, j2, (byte) (j >>> 49));
            byte[] bArr2 = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr2, j3, (byte) (((j >>> 42) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(bArr3, j4, (byte) (((j >>> 35) & 127) | 128));
            byte[] bArr4 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(bArr4, j5, (byte) (((j >>> 28) & 127) | 128));
            byte[] bArr5 = this.buffer;
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.putByte(bArr5, j6, (byte) (((j >>> 21) & 127) | 128));
            byte[] bArr6 = this.buffer;
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.putByte(bArr6, j7, (byte) (((j >>> 14) & 127) | 128));
            byte[] bArr7 = this.buffer;
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.putByte(bArr7, j8, (byte) (((j >>> 7) & 127) | 128));
            byte[] bArr8 = this.buffer;
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.putByte(bArr8, j9, (byte) ((j & 127) | 128));
        }

        private void writeVarint64FiveBytes(long j) {
            byte[] bArr = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(bArr, j2, (byte) (j >>> 28));
            byte[] bArr2 = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr2, j3, (byte) (((j >>> 21) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(bArr3, j4, (byte) (((j >>> 14) & 127) | 128));
            byte[] bArr4 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(bArr4, j5, (byte) (((j >>> 7) & 127) | 128));
            byte[] bArr5 = this.buffer;
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.putByte(bArr5, j6, (byte) ((j & 127) | 128));
        }

        private void writeVarint64FourBytes(long j) {
            byte[] bArr = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(bArr, j2, (byte) (j >>> 21));
            byte[] bArr2 = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr2, j3, (byte) (((j >>> 14) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(bArr3, j4, (byte) (((j >>> 7) & 127) | 128));
            byte[] bArr4 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(bArr4, j5, (byte) ((j & 127) | 128));
        }

        private void writeVarint64NineBytes(long j) {
            byte[] bArr = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(bArr, j2, (byte) (j >>> 56));
            byte[] bArr2 = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr2, j3, (byte) (((j >>> 49) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(bArr3, j4, (byte) (((j >>> 42) & 127) | 128));
            byte[] bArr4 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(bArr4, j5, (byte) (((j >>> 35) & 127) | 128));
            byte[] bArr5 = this.buffer;
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.putByte(bArr5, j6, (byte) (((j >>> 28) & 127) | 128));
            byte[] bArr6 = this.buffer;
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.putByte(bArr6, j7, (byte) (((j >>> 21) & 127) | 128));
            byte[] bArr7 = this.buffer;
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.putByte(bArr7, j8, (byte) (((j >>> 14) & 127) | 128));
            byte[] bArr8 = this.buffer;
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.putByte(bArr8, j9, (byte) (((j >>> 7) & 127) | 128));
            byte[] bArr9 = this.buffer;
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(bArr9, j10, (byte) ((j & 127) | 128));
        }

        private void writeVarint64OneByte(long j) {
            byte[] bArr = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(bArr, j2, (byte) j);
        }

        private void writeVarint64SevenBytes(long j) {
            byte[] bArr = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(bArr, j2, (byte) (j >>> 42));
            byte[] bArr2 = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr2, j3, (byte) (((j >>> 35) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(bArr3, j4, (byte) (((j >>> 28) & 127) | 128));
            byte[] bArr4 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(bArr4, j5, (byte) (((j >>> 21) & 127) | 128));
            byte[] bArr5 = this.buffer;
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.putByte(bArr5, j6, (byte) (((j >>> 14) & 127) | 128));
            byte[] bArr6 = this.buffer;
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.putByte(bArr6, j7, (byte) (((j >>> 7) & 127) | 128));
            byte[] bArr7 = this.buffer;
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.putByte(bArr7, j8, (byte) ((j & 127) | 128));
        }

        private void writeVarint64SixBytes(long j) {
            byte[] bArr = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(bArr, j2, (byte) (j >>> 35));
            byte[] bArr2 = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr2, j3, (byte) (((j >>> 28) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(bArr3, j4, (byte) (((j >>> 21) & 127) | 128));
            byte[] bArr4 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(bArr4, j5, (byte) (((j >>> 14) & 127) | 128));
            byte[] bArr5 = this.buffer;
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.putByte(bArr5, j6, (byte) (((j >>> 7) & 127) | 128));
            byte[] bArr6 = this.buffer;
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.putByte(bArr6, j7, (byte) ((j & 127) | 128));
        }

        private void writeVarint64TenBytes(long j) {
            byte[] bArr = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(bArr, j2, (byte) (j >>> 63));
            byte[] bArr2 = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr2, j3, (byte) (((j >>> 56) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(bArr3, j4, (byte) (((j >>> 49) & 127) | 128));
            byte[] bArr4 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(bArr4, j5, (byte) (((j >>> 42) & 127) | 128));
            byte[] bArr5 = this.buffer;
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.putByte(bArr5, j6, (byte) (((j >>> 35) & 127) | 128));
            byte[] bArr6 = this.buffer;
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.putByte(bArr6, j7, (byte) (((j >>> 28) & 127) | 128));
            byte[] bArr7 = this.buffer;
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.putByte(bArr7, j8, (byte) (((j >>> 21) & 127) | 128));
            byte[] bArr8 = this.buffer;
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.putByte(bArr8, j9, (byte) (((j >>> 14) & 127) | 128));
            byte[] bArr9 = this.buffer;
            long j10 = this.pos;
            this.pos = j10 - 1;
            UnsafeUtil.putByte(bArr9, j10, (byte) (((j >>> 7) & 127) | 128));
            byte[] bArr10 = this.buffer;
            long j11 = this.pos;
            this.pos = j11 - 1;
            UnsafeUtil.putByte(bArr10, j11, (byte) ((j & 127) | 128));
        }

        private void writeVarint64ThreeBytes(long j) {
            byte[] bArr = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(bArr, j2, (byte) (((int) j) >>> 14));
            byte[] bArr2 = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr2, j3, (byte) (((j >>> 7) & 127) | 128));
            byte[] bArr3 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(bArr3, j4, (byte) ((j & 127) | 128));
        }

        private void writeVarint64TwoBytes(long j) {
            byte[] bArr = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(bArr, j2, (byte) (j >>> 7));
            byte[] bArr2 = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr2, j3, (byte) ((((int) j) & 127) | 128));
        }

        public int bytesWrittenToCurrentBuffer() {
            return (int) (this.limitMinusOne - this.pos);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void finishCurrentBuffer() {
            if (this.allocatedBuffer != null) {
                this.totalDoneBytes = bytesWrittenToCurrentBuffer() + this.totalDoneBytes;
                AllocatedBuffer allocatedBuffer = this.allocatedBuffer;
                allocatedBuffer.position((((int) this.pos) - allocatedBuffer.arrayOffset()) + 1);
                this.allocatedBuffer = null;
                this.pos = 0L;
                this.limitMinusOne = 0L;
            }
        }

        @Override // com.google.protobuf.BinaryWriter
        public int getTotalBytesWritten() {
            return bytesWrittenToCurrentBuffer() + this.totalDoneBytes;
        }

        @Override // com.google.protobuf.BinaryWriter
        public void requireSpace(int i) {
            if (spaceLeft() < i) {
                nextBuffer(newHeapBuffer(i));
            }
        }

        public int spaceLeft() {
            return (int) (this.pos - this.offsetMinusOne);
        }

        @Override // com.google.protobuf.ByteOutput
        public void write(byte b2) {
            byte[] bArr = this.buffer;
            long j = this.pos;
            this.pos = j - 1;
            UnsafeUtil.putByte(bArr, j, b2);
        }

        @Override // com.google.protobuf.Writer
        public void writeBool(int i, boolean z) {
            requireSpace(6);
            write(z ? (byte) 1 : (byte) 0);
            writeTag(i, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeBytes(int i, ByteString byteString) {
            try {
                byteString.writeToReverse(this);
                requireSpace(10);
                writeVarint32(byteString.size());
                writeTag(i, 2);
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }

        @Override // com.google.protobuf.Writer
        public void writeEndGroup(int i) {
            writeTag(i, 4);
        }

        @Override // com.google.protobuf.Writer
        public void writeFixed32(int i, int i2) {
            requireSpace(9);
            writeFixed32(i2);
            writeTag(i, 5);
        }

        @Override // com.google.protobuf.Writer
        public void writeFixed64(int i, long j) {
            requireSpace(13);
            writeFixed64(j);
            writeTag(i, 1);
        }

        @Override // com.google.protobuf.Writer
        public void writeGroup(int i, Object obj) {
            writeTag(i, 4);
            Protobuf.INSTANCE.writeTo(obj, this);
            writeTag(i, 3);
        }

        @Override // com.google.protobuf.Writer
        public void writeInt32(int i, int i2) {
            requireSpace(15);
            writeInt32(i2);
            writeTag(i, 0);
        }

        @Override // com.google.protobuf.ByteOutput
        public void writeLazy(byte[] bArr, int i, int i2) {
            if (i < 0 || i + i2 > bArr.length) {
                throw new ArrayIndexOutOfBoundsException(String.format("value.length=%d, offset=%d, length=%d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
            }
            if (spaceLeft() >= i2) {
                this.pos -= (long) i2;
                System.arraycopy(bArr, i, this.buffer, ((int) this.pos) + 1, i2);
            } else {
                this.totalDoneBytes += i2;
                this.buffers.addFirst(AllocatedBuffer.wrap(bArr, i, i2));
                nextBuffer();
            }
        }

        @Override // com.google.protobuf.Writer
        public void writeMessage(int i, Object obj) {
            int totalBytesWritten = getTotalBytesWritten();
            Protobuf.INSTANCE.writeTo(obj, this);
            int totalBytesWritten2 = getTotalBytesWritten() - totalBytesWritten;
            requireSpace(10);
            writeVarint32(totalBytesWritten2);
            writeTag(i, 2);
        }

        @Override // com.google.protobuf.Writer
        public void writeSInt32(int i, int i2) {
            requireSpace(10);
            writeSInt32(i2);
            writeTag(i, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeSInt64(int i, long j) {
            requireSpace(15);
            writeSInt64(j);
            writeTag(i, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeStartGroup(int i) {
            writeTag(i, 3);
        }

        @Override // com.google.protobuf.Writer
        public void writeString(int i, String str) {
            int totalBytesWritten = getTotalBytesWritten();
            writeString(str);
            int totalBytesWritten2 = getTotalBytesWritten() - totalBytesWritten;
            requireSpace(10);
            writeVarint32(totalBytesWritten2);
            writeTag(i, 2);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeTag(int i, int i2) {
            writeVarint32((i << 3) | i2);
        }

        @Override // com.google.protobuf.Writer
        public void writeUInt32(int i, int i2) {
            requireSpace(10);
            writeVarint32(i2);
            writeTag(i, 0);
        }

        @Override // com.google.protobuf.Writer
        public void writeUInt64(int i, long j) {
            requireSpace(15);
            writeVarint64(j);
            writeTag(i, 0);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeVarint32(int i) {
            if ((i & (-128)) == 0) {
                writeVarint32OneByte(i);
                return;
            }
            if ((i & (-16384)) == 0) {
                writeVarint32TwoBytes(i);
                return;
            }
            if (((-2097152) & i) == 0) {
                writeVarint32ThreeBytes(i);
            } else if (((-268435456) & i) == 0) {
                writeVarint32FourBytes(i);
            } else {
                writeVarint32FiveBytes(i);
            }
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeVarint64(long j) {
            switch (BinaryWriter.computeUInt64SizeNoTag(j)) {
                case 1:
                    writeVarint64OneByte(j);
                    break;
                case 2:
                    writeVarint64TwoBytes(j);
                    break;
                case 3:
                    writeVarint64ThreeBytes(j);
                    break;
                case 4:
                    writeVarint64FourBytes(j);
                    break;
                case 5:
                    writeVarint64FiveBytes(j);
                    break;
                case 6:
                    writeVarint64SixBytes(j);
                    break;
                case 7:
                    writeVarint64SevenBytes(j);
                    break;
                case 8:
                    writeVarint64EightBytes(j);
                    break;
                case 9:
                    writeVarint64NineBytes(j);
                    break;
                case 10:
                    writeVarint64TenBytes(j);
                    break;
            }
        }

        private void nextBuffer(int i) {
            nextBuffer(newHeapBuffer(i));
        }

        @Override // com.google.protobuf.ByteOutput
        public void write(byte[] bArr, int i, int i2) {
            if (i < 0 || i + i2 > bArr.length) {
                throw new ArrayIndexOutOfBoundsException(String.format("value.length=%d, offset=%d, length=%d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
            }
            requireSpace(i2);
            this.pos -= (long) i2;
            System.arraycopy(bArr, i, this.buffer, ((int) this.pos) + 1, i2);
        }

        private void nextBuffer(AllocatedBuffer allocatedBuffer) {
            if (allocatedBuffer.hasArray()) {
                finishCurrentBuffer();
                this.buffers.addFirst(allocatedBuffer);
                this.allocatedBuffer = allocatedBuffer;
                this.buffer = allocatedBuffer.array();
                int iArrayOffset = allocatedBuffer.arrayOffset();
                this.limit = allocatedBuffer.limit() + iArrayOffset;
                this.offset = allocatedBuffer.position() + iArrayOffset;
                this.offsetMinusOne = this.offset - 1;
                this.limitMinusOne = this.limit - 1;
                this.pos = this.limitMinusOne;
                return;
            }
            throw new RuntimeException("Allocator returned non-heap buffer");
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeBool(boolean z) {
            write(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeFixed32(int i) {
            byte[] bArr = this.buffer;
            long j = this.pos;
            this.pos = j - 1;
            UnsafeUtil.putByte(bArr, j, (byte) ((i >> 24) & 255));
            byte[] bArr2 = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(bArr2, j2, (byte) ((i >> 16) & 255));
            byte[] bArr3 = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr3, j3, (byte) ((i >> 8) & 255));
            byte[] bArr4 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(bArr4, j4, (byte) (i & 255));
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeFixed64(long j) {
            byte[] bArr = this.buffer;
            long j2 = this.pos;
            this.pos = j2 - 1;
            UnsafeUtil.putByte(bArr, j2, (byte) (((int) (j >> 56)) & 255));
            byte[] bArr2 = this.buffer;
            long j3 = this.pos;
            this.pos = j3 - 1;
            UnsafeUtil.putByte(bArr2, j3, (byte) (((int) (j >> 48)) & 255));
            byte[] bArr3 = this.buffer;
            long j4 = this.pos;
            this.pos = j4 - 1;
            UnsafeUtil.putByte(bArr3, j4, (byte) (((int) (j >> 40)) & 255));
            byte[] bArr4 = this.buffer;
            long j5 = this.pos;
            this.pos = j5 - 1;
            UnsafeUtil.putByte(bArr4, j5, (byte) (((int) (j >> 32)) & 255));
            byte[] bArr5 = this.buffer;
            long j6 = this.pos;
            this.pos = j6 - 1;
            UnsafeUtil.putByte(bArr5, j6, (byte) (((int) (j >> 24)) & 255));
            byte[] bArr6 = this.buffer;
            long j7 = this.pos;
            this.pos = j7 - 1;
            UnsafeUtil.putByte(bArr6, j7, (byte) (((int) (j >> 16)) & 255));
            byte[] bArr7 = this.buffer;
            long j8 = this.pos;
            this.pos = j8 - 1;
            UnsafeUtil.putByte(bArr7, j8, (byte) (((int) (j >> 8)) & 255));
            byte[] bArr8 = this.buffer;
            long j9 = this.pos;
            this.pos = j9 - 1;
            UnsafeUtil.putByte(bArr8, j9, (byte) (((int) j) & 255));
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeInt32(int i) {
            if (i >= 0) {
                writeVarint32(i);
            } else {
                writeVarint64(i);
            }
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeSInt32(int i) {
            writeVarint32(CodedOutputStream.encodeZigZag32(i));
        }

        @Override // com.google.protobuf.BinaryWriter
        public void writeSInt64(long j) {
            writeVarint64(CodedOutputStream.encodeZigZag64(j));
        }

        @Override // com.google.protobuf.Writer
        public void writeGroup(int i, Object obj, Schema schema) {
            writeTag(i, 4);
            schema.writeTo(obj, this);
            writeTag(i, 3);
        }

        /* JADX WARN: Code duplicated, block: B:17:0x0048  */
        /* JADX WARN: Code duplicated, block: B:19:0x004c  */
        /* JADX WARN: Code duplicated, block: B:21:0x0054  */
        /* JADX WARN: Code duplicated, block: B:22:0x0073  */
        /* JADX WARN: Code duplicated, block: B:24:0x0078  */
        /* JADX WARN: Code duplicated, block: B:26:0x007d  */
        /* JADX WARN: Code duplicated, block: B:28:0x0086  */
        /* JADX WARN: Code duplicated, block: B:29:0x00b5  */
        /* JADX WARN: Code duplicated, block: B:31:0x00c0 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:32:0x00c2  */
        /* JADX WARN: Code duplicated, block: B:34:0x00ce  */
        /* JADX WARN: Code duplicated, block: B:37:0x011e  */
        /* JADX WARN: Code duplicated, block: B:43:0x0116 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:44:0x0116 A[SYNTHETIC] */
        @Override // com.google.protobuf.BinaryWriter
        public void writeString(String str) {
            long j;
            char cCharAt;
            long j2;
            char cCharAt2;
            requireSpace(str.length());
            int length = str.length();
            while (true) {
                length--;
                if (length < 0 || (cCharAt2 = str.charAt(length)) >= 128) {
                    break;
                }
                byte[] bArr = this.buffer;
                long j3 = this.pos;
                this.pos = j3 - 1;
                UnsafeUtil.putByte(bArr, j3, (byte) cCharAt2);
            }
            if (length == -1) {
                return;
            }
            while (length >= 0) {
                char cCharAt3 = str.charAt(length);
                if (cCharAt3 < 128) {
                    long j4 = this.pos;
                    if (j4 > this.offsetMinusOne) {
                        byte[] bArr2 = this.buffer;
                        this.pos = j4 - 1;
                        UnsafeUtil.putByte(bArr2, j4, (byte) cCharAt3);
                    } else if (cCharAt3 < 2048) {
                        j2 = this.pos;
                        if (j2 > this.offset) {
                            byte[] bArr3 = this.buffer;
                            this.pos = j2 - 1;
                            UnsafeUtil.putByte(bArr3, j2, (byte) ((cCharAt3 & '?') | 128));
                            byte[] bArr4 = this.buffer;
                            long j5 = this.pos;
                            this.pos = j5 - 1;
                            UnsafeUtil.putByte(bArr4, j5, (byte) ((cCharAt3 >>> 6) | 960));
                        } else if (cCharAt3 >= 55296 || 57343 < cCharAt3) {
                            j = this.pos;
                            if (j > this.offset + 1) {
                                byte[] bArr5 = this.buffer;
                                this.pos = j - 1;
                                UnsafeUtil.putByte(bArr5, j, (byte) ((cCharAt3 & '?') | 128));
                                byte[] bArr6 = this.buffer;
                                long j6 = this.pos;
                                this.pos = j6 - 1;
                                UnsafeUtil.putByte(bArr6, j6, (byte) (((cCharAt3 >>> 6) & 63) | 128));
                                byte[] bArr7 = this.buffer;
                                long j7 = this.pos;
                                this.pos = j7 - 1;
                                UnsafeUtil.putByte(bArr7, j7, (byte) ((cCharAt3 >>> '\f') | 480));
                            } else {
                                if (this.pos > this.offset + 2) {
                                    if (length != 0) {
                                        cCharAt = str.charAt(length - 1);
                                        if (Character.isSurrogatePair(cCharAt, cCharAt3)) {
                                            length--;
                                            int codePoint = Character.toCodePoint(cCharAt, cCharAt3);
                                            byte[] bArr8 = this.buffer;
                                            long j8 = this.pos;
                                            this.pos = j8 - 1;
                                            UnsafeUtil.putByte(bArr8, j8, (byte) ((codePoint & 63) | 128));
                                            byte[] bArr9 = this.buffer;
                                            long j9 = this.pos;
                                            this.pos = j9 - 1;
                                            UnsafeUtil.putByte(bArr9, j9, (byte) (((codePoint >>> 6) & 63) | 128));
                                            byte[] bArr10 = this.buffer;
                                            long j10 = this.pos;
                                            this.pos = j10 - 1;
                                            UnsafeUtil.putByte(bArr10, j10, (byte) (((codePoint >>> 12) & 63) | 128));
                                            byte[] bArr11 = this.buffer;
                                            long j11 = this.pos;
                                            this.pos = j11 - 1;
                                            UnsafeUtil.putByte(bArr11, j11, (byte) ((codePoint >>> 18) | 240));
                                        }
                                    }
                                    throw new Utf8.UnpairedSurrogateException(length - 1, length);
                                }
                                requireSpace(length);
                                length++;
                            }
                        } else {
                            if (this.pos > this.offset + 2) {
                                if (length != 0) {
                                    cCharAt = str.charAt(length - 1);
                                    if (Character.isSurrogatePair(cCharAt, cCharAt3)) {
                                        length--;
                                        int codePoint2 = Character.toCodePoint(cCharAt, cCharAt3);
                                        byte[] bArr12 = this.buffer;
                                        long j12 = this.pos;
                                        this.pos = j12 - 1;
                                        UnsafeUtil.putByte(bArr12, j12, (byte) ((codePoint2 & 63) | 128));
                                        byte[] bArr13 = this.buffer;
                                        long j13 = this.pos;
                                        this.pos = j13 - 1;
                                        UnsafeUtil.putByte(bArr13, j13, (byte) (((codePoint2 >>> 6) & 63) | 128));
                                        byte[] bArr14 = this.buffer;
                                        long j14 = this.pos;
                                        this.pos = j14 - 1;
                                        UnsafeUtil.putByte(bArr14, j14, (byte) (((codePoint2 >>> 12) & 63) | 128));
                                        byte[] bArr15 = this.buffer;
                                        long j15 = this.pos;
                                        this.pos = j15 - 1;
                                        UnsafeUtil.putByte(bArr15, j15, (byte) ((codePoint2 >>> 18) | 240));
                                    }
                                }
                                throw new Utf8.UnpairedSurrogateException(length - 1, length);
                            }
                            requireSpace(length);
                            length++;
                        }
                    } else if (cCharAt3 >= 55296) {
                        j = this.pos;
                        if (j > this.offset + 1) {
                            byte[] bArr16 = this.buffer;
                            this.pos = j - 1;
                            UnsafeUtil.putByte(bArr16, j, (byte) ((cCharAt3 & '?') | 128));
                            byte[] bArr17 = this.buffer;
                            long j16 = this.pos;
                            this.pos = j16 - 1;
                            UnsafeUtil.putByte(bArr17, j16, (byte) (((cCharAt3 >>> 6) & 63) | 128));
                            byte[] bArr18 = this.buffer;
                            long j17 = this.pos;
                            this.pos = j17 - 1;
                            UnsafeUtil.putByte(bArr18, j17, (byte) ((cCharAt3 >>> '\f') | 480));
                        } else {
                            if (this.pos > this.offset + 2) {
                                if (length != 0) {
                                    cCharAt = str.charAt(length - 1);
                                    if (Character.isSurrogatePair(cCharAt, cCharAt3)) {
                                        length--;
                                        int codePoint3 = Character.toCodePoint(cCharAt, cCharAt3);
                                        byte[] bArr19 = this.buffer;
                                        long j18 = this.pos;
                                        this.pos = j18 - 1;
                                        UnsafeUtil.putByte(bArr19, j18, (byte) ((codePoint3 & 63) | 128));
                                        byte[] bArr110 = this.buffer;
                                        long j19 = this.pos;
                                        this.pos = j19 - 1;
                                        UnsafeUtil.putByte(bArr110, j19, (byte) (((codePoint3 >>> 6) & 63) | 128));
                                        byte[] bArr111 = this.buffer;
                                        long j110 = this.pos;
                                        this.pos = j110 - 1;
                                        UnsafeUtil.putByte(bArr111, j110, (byte) (((codePoint3 >>> 12) & 63) | 128));
                                        byte[] bArr112 = this.buffer;
                                        long j111 = this.pos;
                                        this.pos = j111 - 1;
                                        UnsafeUtil.putByte(bArr112, j111, (byte) ((codePoint3 >>> 18) | 240));
                                    }
                                }
                                throw new Utf8.UnpairedSurrogateException(length - 1, length);
                            }
                            requireSpace(length);
                            length++;
                        }
                    } else {
                        j = this.pos;
                        if (j > this.offset + 1) {
                            byte[] bArr113 = this.buffer;
                            this.pos = j - 1;
                            UnsafeUtil.putByte(bArr113, j, (byte) ((cCharAt3 & '?') | 128));
                            byte[] bArr114 = this.buffer;
                            long j112 = this.pos;
                            this.pos = j112 - 1;
                            UnsafeUtil.putByte(bArr114, j112, (byte) (((cCharAt3 >>> 6) & 63) | 128));
                            byte[] bArr115 = this.buffer;
                            long j113 = this.pos;
                            this.pos = j113 - 1;
                            UnsafeUtil.putByte(bArr115, j113, (byte) ((cCharAt3 >>> '\f') | 480));
                        } else {
                            if (this.pos > this.offset + 2) {
                                if (length != 0) {
                                    cCharAt = str.charAt(length - 1);
                                    if (Character.isSurrogatePair(cCharAt, cCharAt3)) {
                                        length--;
                                        int codePoint4 = Character.toCodePoint(cCharAt, cCharAt3);
                                        byte[] bArr116 = this.buffer;
                                        long j114 = this.pos;
                                        this.pos = j114 - 1;
                                        UnsafeUtil.putByte(bArr116, j114, (byte) ((codePoint4 & 63) | 128));
                                        byte[] bArr117 = this.buffer;
                                        long j115 = this.pos;
                                        this.pos = j115 - 1;
                                        UnsafeUtil.putByte(bArr117, j115, (byte) (((codePoint4 >>> 6) & 63) | 128));
                                        byte[] bArr118 = this.buffer;
                                        long j116 = this.pos;
                                        this.pos = j116 - 1;
                                        UnsafeUtil.putByte(bArr118, j116, (byte) (((codePoint4 >>> 12) & 63) | 128));
                                        byte[] bArr119 = this.buffer;
                                        long j117 = this.pos;
                                        this.pos = j117 - 1;
                                        UnsafeUtil.putByte(bArr119, j117, (byte) ((codePoint4 >>> 18) | 240));
                                    }
                                }
                                throw new Utf8.UnpairedSurrogateException(length - 1, length);
                            }
                            requireSpace(length);
                            length++;
                        }
                    }
                } else if (cCharAt3 < 2048) {
                    j2 = this.pos;
                    if (j2 > this.offset) {
                        byte[] bArr20 = this.buffer;
                        this.pos = j2 - 1;
                        UnsafeUtil.putByte(bArr20, j2, (byte) ((cCharAt3 & '?') | 128));
                        byte[] bArr21 = this.buffer;
                        long j20 = this.pos;
                        this.pos = j20 - 1;
                        UnsafeUtil.putByte(bArr21, j20, (byte) ((cCharAt3 >>> 6) | 960));
                    } else if (cCharAt3 >= 55296) {
                        j = this.pos;
                        if (j > this.offset + 1) {
                            byte[] bArr1110 = this.buffer;
                            this.pos = j - 1;
                            UnsafeUtil.putByte(bArr1110, j, (byte) ((cCharAt3 & '?') | 128));
                            byte[] bArr1111 = this.buffer;
                            long j118 = this.pos;
                            this.pos = j118 - 1;
                            UnsafeUtil.putByte(bArr1111, j118, (byte) (((cCharAt3 >>> 6) & 63) | 128));
                            byte[] bArr1112 = this.buffer;
                            long j119 = this.pos;
                            this.pos = j119 - 1;
                            UnsafeUtil.putByte(bArr1112, j119, (byte) ((cCharAt3 >>> '\f') | 480));
                        } else {
                            if (this.pos > this.offset + 2) {
                                if (length != 0) {
                                    cCharAt = str.charAt(length - 1);
                                    if (Character.isSurrogatePair(cCharAt, cCharAt3)) {
                                        length--;
                                        int codePoint5 = Character.toCodePoint(cCharAt, cCharAt3);
                                        byte[] bArr1113 = this.buffer;
                                        long j1110 = this.pos;
                                        this.pos = j1110 - 1;
                                        UnsafeUtil.putByte(bArr1113, j1110, (byte) ((codePoint5 & 63) | 128));
                                        byte[] bArr1114 = this.buffer;
                                        long j1111 = this.pos;
                                        this.pos = j1111 - 1;
                                        UnsafeUtil.putByte(bArr1114, j1111, (byte) (((codePoint5 >>> 6) & 63) | 128));
                                        byte[] bArr1115 = this.buffer;
                                        long j1112 = this.pos;
                                        this.pos = j1112 - 1;
                                        UnsafeUtil.putByte(bArr1115, j1112, (byte) (((codePoint5 >>> 12) & 63) | 128));
                                        byte[] bArr1116 = this.buffer;
                                        long j1113 = this.pos;
                                        this.pos = j1113 - 1;
                                        UnsafeUtil.putByte(bArr1116, j1113, (byte) ((codePoint5 >>> 18) | 240));
                                    }
                                }
                                throw new Utf8.UnpairedSurrogateException(length - 1, length);
                            }
                            requireSpace(length);
                            length++;
                        }
                    } else {
                        j = this.pos;
                        if (j > this.offset + 1) {
                            byte[] bArr1117 = this.buffer;
                            this.pos = j - 1;
                            UnsafeUtil.putByte(bArr1117, j, (byte) ((cCharAt3 & '?') | 128));
                            byte[] bArr1118 = this.buffer;
                            long j1114 = this.pos;
                            this.pos = j1114 - 1;
                            UnsafeUtil.putByte(bArr1118, j1114, (byte) (((cCharAt3 >>> 6) & 63) | 128));
                            byte[] bArr1119 = this.buffer;
                            long j1115 = this.pos;
                            this.pos = j1115 - 1;
                            UnsafeUtil.putByte(bArr1119, j1115, (byte) ((cCharAt3 >>> '\f') | 480));
                        } else {
                            if (this.pos > this.offset + 2) {
                                if (length != 0) {
                                    cCharAt = str.charAt(length - 1);
                                    if (Character.isSurrogatePair(cCharAt, cCharAt3)) {
                                        length--;
                                        int codePoint6 = Character.toCodePoint(cCharAt, cCharAt3);
                                        byte[] bArr11110 = this.buffer;
                                        long j1116 = this.pos;
                                        this.pos = j1116 - 1;
                                        UnsafeUtil.putByte(bArr11110, j1116, (byte) ((codePoint6 & 63) | 128));
                                        byte[] bArr11111 = this.buffer;
                                        long j1117 = this.pos;
                                        this.pos = j1117 - 1;
                                        UnsafeUtil.putByte(bArr11111, j1117, (byte) (((codePoint6 >>> 6) & 63) | 128));
                                        byte[] bArr11112 = this.buffer;
                                        long j1118 = this.pos;
                                        this.pos = j1118 - 1;
                                        UnsafeUtil.putByte(bArr11112, j1118, (byte) (((codePoint6 >>> 12) & 63) | 128));
                                        byte[] bArr11113 = this.buffer;
                                        long j1119 = this.pos;
                                        this.pos = j1119 - 1;
                                        UnsafeUtil.putByte(bArr11113, j1119, (byte) ((codePoint6 >>> 18) | 240));
                                    }
                                }
                                throw new Utf8.UnpairedSurrogateException(length - 1, length);
                            }
                            requireSpace(length);
                            length++;
                        }
                    }
                } else if (cCharAt3 >= 55296) {
                    j = this.pos;
                    if (j > this.offset + 1) {
                        byte[] bArr11114 = this.buffer;
                        this.pos = j - 1;
                        UnsafeUtil.putByte(bArr11114, j, (byte) ((cCharAt3 & '?') | 128));
                        byte[] bArr11115 = this.buffer;
                        long j11110 = this.pos;
                        this.pos = j11110 - 1;
                        UnsafeUtil.putByte(bArr11115, j11110, (byte) (((cCharAt3 >>> 6) & 63) | 128));
                        byte[] bArr11116 = this.buffer;
                        long j11111 = this.pos;
                        this.pos = j11111 - 1;
                        UnsafeUtil.putByte(bArr11116, j11111, (byte) ((cCharAt3 >>> '\f') | 480));
                    } else {
                        if (this.pos > this.offset + 2) {
                            if (length != 0) {
                                cCharAt = str.charAt(length - 1);
                                if (Character.isSurrogatePair(cCharAt, cCharAt3)) {
                                    length--;
                                    int codePoint7 = Character.toCodePoint(cCharAt, cCharAt3);
                                    byte[] bArr11117 = this.buffer;
                                    long j11112 = this.pos;
                                    this.pos = j11112 - 1;
                                    UnsafeUtil.putByte(bArr11117, j11112, (byte) ((codePoint7 & 63) | 128));
                                    byte[] bArr11118 = this.buffer;
                                    long j11113 = this.pos;
                                    this.pos = j11113 - 1;
                                    UnsafeUtil.putByte(bArr11118, j11113, (byte) (((codePoint7 >>> 6) & 63) | 128));
                                    byte[] bArr11119 = this.buffer;
                                    long j11114 = this.pos;
                                    this.pos = j11114 - 1;
                                    UnsafeUtil.putByte(bArr11119, j11114, (byte) (((codePoint7 >>> 12) & 63) | 128));
                                    byte[] bArr111110 = this.buffer;
                                    long j11115 = this.pos;
                                    this.pos = j11115 - 1;
                                    UnsafeUtil.putByte(bArr111110, j11115, (byte) ((codePoint7 >>> 18) | 240));
                                }
                            }
                            throw new Utf8.UnpairedSurrogateException(length - 1, length);
                        }
                        requireSpace(length);
                        length++;
                    }
                } else {
                    j = this.pos;
                    if (j > this.offset + 1) {
                        byte[] bArr111111 = this.buffer;
                        this.pos = j - 1;
                        UnsafeUtil.putByte(bArr111111, j, (byte) ((cCharAt3 & '?') | 128));
                        byte[] bArr111112 = this.buffer;
                        long j11116 = this.pos;
                        this.pos = j11116 - 1;
                        UnsafeUtil.putByte(bArr111112, j11116, (byte) (((cCharAt3 >>> 6) & 63) | 128));
                        byte[] bArr111113 = this.buffer;
                        long j11117 = this.pos;
                        this.pos = j11117 - 1;
                        UnsafeUtil.putByte(bArr111113, j11117, (byte) ((cCharAt3 >>> '\f') | 480));
                    } else {
                        if (this.pos > this.offset + 2) {
                            if (length != 0) {
                                cCharAt = str.charAt(length - 1);
                                if (Character.isSurrogatePair(cCharAt, cCharAt3)) {
                                    length--;
                                    int codePoint8 = Character.toCodePoint(cCharAt, cCharAt3);
                                    byte[] bArr111114 = this.buffer;
                                    long j11118 = this.pos;
                                    this.pos = j11118 - 1;
                                    UnsafeUtil.putByte(bArr111114, j11118, (byte) ((codePoint8 & 63) | 128));
                                    byte[] bArr111115 = this.buffer;
                                    long j11119 = this.pos;
                                    this.pos = j11119 - 1;
                                    UnsafeUtil.putByte(bArr111115, j11119, (byte) (((codePoint8 >>> 6) & 63) | 128));
                                    byte[] bArr111116 = this.buffer;
                                    long j111110 = this.pos;
                                    this.pos = j111110 - 1;
                                    UnsafeUtil.putByte(bArr111116, j111110, (byte) (((codePoint8 >>> 12) & 63) | 128));
                                    byte[] bArr111117 = this.buffer;
                                    long j111111 = this.pos;
                                    this.pos = j111111 - 1;
                                    UnsafeUtil.putByte(bArr111117, j111111, (byte) ((codePoint8 >>> 18) | 240));
                                }
                            }
                            throw new Utf8.UnpairedSurrogateException(length - 1, length);
                        }
                        requireSpace(length);
                        length++;
                    }
                }
                length--;
            }
        }

        @Override // com.google.protobuf.Writer
        public void writeMessage(int i, Object obj, Schema schema) {
            int totalBytesWritten = getTotalBytesWritten();
            schema.writeTo(obj, this);
            int totalBytesWritten2 = getTotalBytesWritten() - totalBytesWritten;
            requireSpace(10);
            writeVarint32(totalBytesWritten2);
            writeTag(i, 2);
        }

        @Override // com.google.protobuf.ByteOutput
        public void write(ByteBuffer byteBuffer) {
            int iRemaining = byteBuffer.remaining();
            requireSpace(iRemaining);
            this.pos -= (long) iRemaining;
            byteBuffer.get(this.buffer, ((int) this.pos) + 1, iRemaining);
        }

        @Override // com.google.protobuf.ByteOutput
        public void writeLazy(ByteBuffer byteBuffer) {
            int iRemaining = byteBuffer.remaining();
            if (spaceLeft() < iRemaining) {
                this.totalDoneBytes += iRemaining;
                this.buffers.addFirst(AllocatedBuffer.wrap(byteBuffer));
                nextBuffer();
            }
            this.pos -= (long) iRemaining;
            byteBuffer.get(this.buffer, ((int) this.pos) + 1, iRemaining);
        }
    }

    public /* synthetic */ BinaryWriter(BufferAllocator bufferAllocator, int i, AnonymousClass1 anonymousClass1) {
        this(bufferAllocator, i);
    }

    public static byte computeUInt64SizeNoTag(long j) {
        byte b2;
        if (((-128) & j) == 0) {
            return (byte) 1;
        }
        if (j < 0) {
            return (byte) 10;
        }
        if (((-34359738368L) & j) != 0) {
            b2 = (byte) 6;
            j >>>= 28;
        } else {
            b2 = 2;
        }
        if (((-2097152) & j) != 0) {
            b2 = (byte) (b2 + 2);
            j >>>= 14;
        }
        return (j & (-16384)) != 0 ? (byte) (b2 + 1) : b2;
    }

    public static boolean isUnsafeDirectSupported() {
        return UnsafeUtil.HAS_UNSAFE_BYTEBUFFER_OPERATIONS;
    }

    public static boolean isUnsafeHeapSupported() {
        return UnsafeUtil.HAS_UNSAFE_ARRAY_OPERATIONS;
    }

    public static BinaryWriter newDirectInstance(BufferAllocator bufferAllocator, int i) {
        return UnsafeUtil.HAS_UNSAFE_BYTEBUFFER_OPERATIONS ? newUnsafeDirectInstance(bufferAllocator, i) : new SafeDirectWriter(bufferAllocator, i);
    }

    public static BinaryWriter newHeapInstance(BufferAllocator bufferAllocator) {
        return newHeapInstance(bufferAllocator, 4096);
    }

    public static BinaryWriter newSafeDirectInstance(BufferAllocator bufferAllocator, int i) {
        return new SafeDirectWriter(bufferAllocator, i);
    }

    public static BinaryWriter newSafeHeapInstance(BufferAllocator bufferAllocator, int i) {
        return new SafeHeapWriter(bufferAllocator, i);
    }

    public static BinaryWriter newUnsafeDirectInstance(BufferAllocator bufferAllocator, int i) {
        if (UnsafeUtil.HAS_UNSAFE_BYTEBUFFER_OPERATIONS) {
            return new UnsafeDirectWriter(bufferAllocator, i);
        }
        throw new UnsupportedOperationException("Unsafe operations not supported");
    }

    public static BinaryWriter newUnsafeHeapInstance(BufferAllocator bufferAllocator, int i) {
        if (UnsafeUtil.HAS_UNSAFE_ARRAY_OPERATIONS) {
            return new UnsafeHeapWriter(bufferAllocator, i);
        }
        throw new UnsupportedOperationException("Unsafe operations not supported");
    }

    private final void writeBoolList_Internal(int i, List<Boolean> list, boolean z) {
        if (z) {
            requireSpace(list.size() + 10);
            int totalBytesWritten = getTotalBytesWritten();
            int size = list.size();
            while (true) {
                size--;
                if (size < 0) {
                    a.a(this, totalBytesWritten, i, 2);
                    return;
                }
                writeBool(list.get(size).booleanValue());
            }
        } else {
            int size2 = list.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return;
                } else {
                    writeBool(i, list.get(size2).booleanValue());
                }
            }
        }
    }

    private final void writeDoubleList_Internal(int i, List<Double> list, boolean z) {
        if (z) {
            requireSpace((list.size() * 8) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            int size = list.size();
            while (true) {
                size--;
                if (size < 0) {
                    a.a(this, totalBytesWritten, i, 2);
                    return;
                }
                writeFixed64(Double.doubleToRawLongBits(list.get(size).doubleValue()));
            }
        } else {
            int size2 = list.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return;
                } else {
                    writeDouble(i, list.get(size2).doubleValue());
                }
            }
        }
    }

    private final void writeFixed32List_Internal(int i, List<Integer> list, boolean z) {
        if (z) {
            requireSpace((list.size() * 4) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            int size = list.size();
            while (true) {
                size--;
                if (size < 0) {
                    a.a(this, totalBytesWritten, i, 2);
                    return;
                }
                writeFixed32(list.get(size).intValue());
            }
        } else {
            int size2 = list.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return;
                } else {
                    writeFixed32(i, list.get(size2).intValue());
                }
            }
        }
    }

    private final void writeFixed64List_Internal(int i, List<Long> list, boolean z) {
        if (z) {
            requireSpace((list.size() * 8) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            int size = list.size();
            while (true) {
                size--;
                if (size < 0) {
                    a.a(this, totalBytesWritten, i, 2);
                    return;
                }
                writeFixed64(list.get(size).longValue());
            }
        } else {
            int size2 = list.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return;
                } else {
                    writeFixed64(i, list.get(size2).longValue());
                }
            }
        }
    }

    private final void writeFloatList_Internal(int i, List<Float> list, boolean z) {
        if (z) {
            requireSpace((list.size() * 4) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            int size = list.size();
            while (true) {
                size--;
                if (size < 0) {
                    a.a(this, totalBytesWritten, i, 2);
                    return;
                }
                writeFixed32(Float.floatToRawIntBits(list.get(size).floatValue()));
            }
        } else {
            int size2 = list.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return;
                } else {
                    writeFloat(i, list.get(size2).floatValue());
                }
            }
        }
    }

    private final void writeInt32List_Internal(int i, List<Integer> list, boolean z) {
        if (z) {
            requireSpace((list.size() * 10) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            int size = list.size();
            while (true) {
                size--;
                if (size < 0) {
                    a.a(this, totalBytesWritten, i, 2);
                    return;
                }
                writeInt32(list.get(size).intValue());
            }
        } else {
            int size2 = list.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return;
                } else {
                    writeInt32(i, list.get(size2).intValue());
                }
            }
        }
    }

    private void writeLazyString(int i, Object obj) {
        if (obj instanceof String) {
            writeString(i, (String) obj);
        } else {
            writeBytes(i, (ByteString) obj);
        }
    }

    public static final void writeMapEntryField(Writer writer, int i, WireFormat.FieldType fieldType, Object obj) {
        switch (fieldType.ordinal()) {
            case 0:
                writer.writeDouble(i, ((Double) obj).doubleValue());
                return;
            case 1:
                writer.writeFloat(i, ((Float) obj).floatValue());
                return;
            case 2:
                writer.writeInt64(i, ((Long) obj).longValue());
                return;
            case 3:
                writer.writeUInt64(i, ((Long) obj).longValue());
                return;
            case 4:
                writer.writeInt32(i, ((Integer) obj).intValue());
                return;
            case 5:
                writer.writeFixed64(i, ((Long) obj).longValue());
                return;
            case 6:
                writer.writeFixed32(i, ((Integer) obj).intValue());
                return;
            case 7:
                writer.writeBool(i, ((Boolean) obj).booleanValue());
                return;
            case 8:
                writer.writeString(i, (String) obj);
                return;
            case 9:
            default:
                throw new IllegalArgumentException(a.a("Unsupported map value type for: ", fieldType));
            case 10:
                writer.writeMessage(i, obj);
                return;
            case 11:
                writer.writeBytes(i, (ByteString) obj);
                return;
            case 12:
                writer.writeUInt32(i, ((Integer) obj).intValue());
                return;
            case 13:
                if (obj instanceof Internal.EnumLite) {
                    writer.writeEnum(i, ((Internal.EnumLite) obj).getNumber());
                    return;
                } else {
                    if (!(obj instanceof Integer)) {
                        throw new IllegalArgumentException("Unexpected type for enum in map.");
                    }
                    writer.writeEnum(i, ((Integer) obj).intValue());
                    return;
                }
            case 14:
                writer.writeSFixed32(i, ((Integer) obj).intValue());
                return;
            case 15:
                writer.writeSFixed64(i, ((Long) obj).longValue());
                return;
            case 16:
                writer.writeSInt32(i, ((Integer) obj).intValue());
                return;
            case 17:
                writer.writeSInt64(i, ((Long) obj).longValue());
                return;
        }
    }

    private final void writeSInt32List_Internal(int i, List<Integer> list, boolean z) {
        if (z) {
            requireSpace((list.size() * 5) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            int size = list.size();
            while (true) {
                size--;
                if (size < 0) {
                    a.a(this, totalBytesWritten, i, 2);
                    return;
                }
                writeSInt32(list.get(size).intValue());
            }
        } else {
            int size2 = list.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return;
                } else {
                    writeSInt32(i, list.get(size2).intValue());
                }
            }
        }
    }

    private final void writeSInt64List_Internal(int i, List<Long> list, boolean z) {
        if (z) {
            requireSpace((list.size() * 10) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            int size = list.size();
            while (true) {
                size--;
                if (size < 0) {
                    a.a(this, totalBytesWritten, i, 2);
                    return;
                }
                writeSInt64(list.get(size).longValue());
            }
        } else {
            int size2 = list.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return;
                } else {
                    writeSInt64(i, list.get(size2).longValue());
                }
            }
        }
    }

    private final void writeUInt32List_Internal(int i, List<Integer> list, boolean z) {
        if (z) {
            requireSpace((list.size() * 5) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            int size = list.size();
            while (true) {
                size--;
                if (size < 0) {
                    a.a(this, totalBytesWritten, i, 2);
                    return;
                }
                writeVarint32(list.get(size).intValue());
            }
        } else {
            int size2 = list.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return;
                } else {
                    writeUInt32(i, list.get(size2).intValue());
                }
            }
        }
    }

    private final void writeUInt64List_Internal(int i, List<Long> list, boolean z) {
        if (z) {
            requireSpace((list.size() * 10) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            int size = list.size();
            while (true) {
                size--;
                if (size < 0) {
                    a.a(this, totalBytesWritten, i, 2);
                    return;
                }
                writeVarint64(list.get(size).longValue());
            }
        } else {
            int size2 = list.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return;
                } else {
                    writeUInt64(i, list.get(size2).longValue());
                }
            }
        }
    }

    public final Queue<AllocatedBuffer> complete() {
        finishCurrentBuffer();
        return this.buffers;
    }

    @Override // com.google.protobuf.Writer
    public final Writer.FieldOrder fieldOrder() {
        return Writer.FieldOrder.DESCENDING;
    }

    public abstract void finishCurrentBuffer();

    public abstract int getTotalBytesWritten();

    public final AllocatedBuffer newDirectBuffer() {
        return this.alloc.allocateDirectBuffer(this.chunkSize);
    }

    public final AllocatedBuffer newHeapBuffer() {
        return this.alloc.allocateHeapBuffer(this.chunkSize);
    }

    public abstract void requireSpace(int i);

    public abstract void writeBool(boolean z);

    @Override // com.google.protobuf.Writer
    public final void writeBoolList(int i, List<Boolean> list, boolean z) {
        if (list instanceof BooleanArrayList) {
            writeBoolList_Internal(i, (BooleanArrayList) list, z);
        } else {
            writeBoolList_Internal(i, list, z);
        }
    }

    @Override // com.google.protobuf.Writer
    public final void writeBytesList(int i, List<ByteString> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            writeBytes(i, list.get(size));
        }
    }

    @Override // com.google.protobuf.Writer
    public final void writeDouble(int i, double d2) {
        writeFixed64(i, Double.doubleToRawLongBits(d2));
    }

    @Override // com.google.protobuf.Writer
    public final void writeDoubleList(int i, List<Double> list, boolean z) {
        if (list instanceof DoubleArrayList) {
            writeDoubleList_Internal(i, (DoubleArrayList) list, z);
        } else {
            writeDoubleList_Internal(i, list, z);
        }
    }

    @Override // com.google.protobuf.Writer
    public final void writeEnum(int i, int i2) {
        writeInt32(i, i2);
    }

    @Override // com.google.protobuf.Writer
    public final void writeEnumList(int i, List<Integer> list, boolean z) {
        writeInt32List(i, list, z);
    }

    public abstract void writeFixed32(int i);

    @Override // com.google.protobuf.Writer
    public final void writeFixed32List(int i, List<Integer> list, boolean z) {
        if (list instanceof IntArrayList) {
            writeFixed32List_Internal(i, (IntArrayList) list, z);
        } else {
            writeFixed32List_Internal(i, list, z);
        }
    }

    public abstract void writeFixed64(long j);

    @Override // com.google.protobuf.Writer
    public final void writeFixed64List(int i, List<Long> list, boolean z) {
        if (list instanceof LongArrayList) {
            writeFixed64List_Internal(i, (LongArrayList) list, z);
        } else {
            writeFixed64List_Internal(i, list, z);
        }
    }

    @Override // com.google.protobuf.Writer
    public final void writeFloat(int i, float f2) {
        writeFixed32(i, Float.floatToRawIntBits(f2));
    }

    @Override // com.google.protobuf.Writer
    public final void writeFloatList(int i, List<Float> list, boolean z) {
        if (list instanceof FloatArrayList) {
            writeFloatList_Internal(i, (FloatArrayList) list, z);
        } else {
            writeFloatList_Internal(i, list, z);
        }
    }

    @Override // com.google.protobuf.Writer
    public final void writeGroupList(int i, List<?> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            writeGroup(i, list.get(size));
        }
    }

    public abstract void writeInt32(int i);

    @Override // com.google.protobuf.Writer
    public final void writeInt32List(int i, List<Integer> list, boolean z) {
        if (list instanceof IntArrayList) {
            writeInt32List_Internal(i, (IntArrayList) list, z);
        } else {
            writeInt32List_Internal(i, list, z);
        }
    }

    @Override // com.google.protobuf.Writer
    public final void writeInt64(int i, long j) {
        writeUInt64(i, j);
    }

    @Override // com.google.protobuf.Writer
    public final void writeInt64List(int i, List<Long> list, boolean z) {
        writeUInt64List(i, list, z);
    }

    @Override // com.google.protobuf.Writer
    public <K, V> void writeMap(int i, MapEntryLite.Metadata<K, V> metadata, Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            int totalBytesWritten = getTotalBytesWritten();
            writeMapEntryField(this, 2, metadata.valueType, entry.getValue());
            writeMapEntryField(this, 1, metadata.keyType, entry.getKey());
            writeVarint32(getTotalBytesWritten() - totalBytesWritten);
            writeTag(i, 2);
        }
    }

    @Override // com.google.protobuf.Writer
    public final void writeMessageList(int i, List<?> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            writeMessage(i, list.get(size));
        }
    }

    @Override // com.google.protobuf.Writer
    public final void writeMessageSetItem(int i, Object obj) {
        writeTag(1, 4);
        if (obj instanceof ByteString) {
            writeBytes(3, (ByteString) obj);
        } else {
            writeMessage(3, obj);
        }
        writeUInt32(2, i);
        writeTag(1, 3);
    }

    @Override // com.google.protobuf.Writer
    public final void writeSFixed32(int i, int i2) {
        writeFixed32(i, i2);
    }

    @Override // com.google.protobuf.Writer
    public final void writeSFixed32List(int i, List<Integer> list, boolean z) {
        writeFixed32List(i, list, z);
    }

    @Override // com.google.protobuf.Writer
    public final void writeSFixed64(int i, long j) {
        writeFixed64(i, j);
    }

    @Override // com.google.protobuf.Writer
    public final void writeSFixed64List(int i, List<Long> list, boolean z) {
        writeFixed64List(i, list, z);
    }

    public abstract void writeSInt32(int i);

    @Override // com.google.protobuf.Writer
    public final void writeSInt32List(int i, List<Integer> list, boolean z) {
        if (list instanceof IntArrayList) {
            writeSInt32List_Internal(i, (IntArrayList) list, z);
        } else {
            writeSInt32List_Internal(i, list, z);
        }
    }

    public abstract void writeSInt64(long j);

    @Override // com.google.protobuf.Writer
    public final void writeSInt64List(int i, List<Long> list, boolean z) {
        if (list instanceof LongArrayList) {
            writeSInt64List_Internal(i, (LongArrayList) list, z);
        } else {
            writeSInt64List_Internal(i, list, z);
        }
    }

    public abstract void writeString(String str);

    @Override // com.google.protobuf.Writer
    public final void writeStringList(int i, List<String> list) {
        if (!(list instanceof LazyStringList)) {
            for (int size = list.size() - 1; size >= 0; size--) {
                writeString(i, list.get(size));
            }
            return;
        }
        LazyStringList lazyStringList = (LazyStringList) list;
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            writeLazyString(i, lazyStringList.getRaw(size2));
        }
    }

    public abstract void writeTag(int i, int i2);

    @Override // com.google.protobuf.Writer
    public final void writeUInt32List(int i, List<Integer> list, boolean z) {
        if (list instanceof IntArrayList) {
            writeUInt32List_Internal(i, (IntArrayList) list, z);
        } else {
            writeUInt32List_Internal(i, list, z);
        }
    }

    @Override // com.google.protobuf.Writer
    public final void writeUInt64List(int i, List<Long> list, boolean z) {
        if (list instanceof LongArrayList) {
            writeUInt64List_Internal(i, (LongArrayList) list, z);
        } else {
            writeUInt64List_Internal(i, list, z);
        }
    }

    public abstract void writeVarint32(int i);

    public abstract void writeVarint64(long j);

    public BinaryWriter(BufferAllocator bufferAllocator, int i) {
        this.buffers = new ArrayDeque<>(4);
        if (i <= 0) {
            throw new IllegalArgumentException("chunkSize must be > 0");
        }
        Internal.checkNotNull(bufferAllocator, "alloc");
        this.alloc = bufferAllocator;
        this.chunkSize = i;
    }

    public static BinaryWriter newHeapInstance(BufferAllocator bufferAllocator, int i) {
        return UnsafeUtil.HAS_UNSAFE_ARRAY_OPERATIONS ? newUnsafeHeapInstance(bufferAllocator, i) : new SafeHeapWriter(bufferAllocator, i);
    }

    public final AllocatedBuffer newDirectBuffer(int i) {
        return this.alloc.allocateDirectBuffer(Math.max(i, this.chunkSize));
    }

    public final AllocatedBuffer newHeapBuffer(int i) {
        return this.alloc.allocateHeapBuffer(Math.max(i, this.chunkSize));
    }

    @Override // com.google.protobuf.Writer
    public final void writeGroupList(int i, List<?> list, Schema schema) {
        for (int size = list.size() - 1; size >= 0; size--) {
            writeGroup(i, list.get(size), schema);
        }
    }

    @Override // com.google.protobuf.Writer
    public final void writeMessageList(int i, List<?> list, Schema schema) {
        for (int size = list.size() - 1; size >= 0; size--) {
            writeMessage(i, list.get(size), schema);
        }
    }

    public static BinaryWriter newDirectInstance(BufferAllocator bufferAllocator) {
        return newDirectInstance(bufferAllocator, 4096);
    }

    private final void writeBoolList_Internal(int i, BooleanArrayList booleanArrayList, boolean z) {
        if (z) {
            requireSpace(booleanArrayList.size() + 10);
            int totalBytesWritten = getTotalBytesWritten();
            int size = booleanArrayList.size();
            while (true) {
                size--;
                if (size >= 0) {
                    writeBool(booleanArrayList.getBoolean(size));
                } else {
                    a.a(this, totalBytesWritten, i, 2);
                    return;
                }
            }
        } else {
            int size2 = booleanArrayList.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return;
                } else {
                    writeBool(i, booleanArrayList.getBoolean(size2));
                }
            }
        }
    }

    private final void writeDoubleList_Internal(int i, DoubleArrayList doubleArrayList, boolean z) {
        if (z) {
            requireSpace((doubleArrayList.size() * 8) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            int size = doubleArrayList.size();
            while (true) {
                size--;
                if (size >= 0) {
                    writeFixed64(Double.doubleToRawLongBits(doubleArrayList.getDouble(size)));
                } else {
                    a.a(this, totalBytesWritten, i, 2);
                    return;
                }
            }
        } else {
            int size2 = doubleArrayList.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return;
                } else {
                    writeDouble(i, doubleArrayList.getDouble(size2));
                }
            }
        }
    }

    private final void writeFixed32List_Internal(int i, IntArrayList intArrayList, boolean z) {
        if (z) {
            requireSpace((intArrayList.size() * 4) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            int size = intArrayList.size();
            while (true) {
                size--;
                if (size >= 0) {
                    writeFixed32(intArrayList.getInt(size));
                } else {
                    a.a(this, totalBytesWritten, i, 2);
                    return;
                }
            }
        } else {
            int size2 = intArrayList.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return;
                } else {
                    writeFixed32(i, intArrayList.getInt(size2));
                }
            }
        }
    }

    private final void writeFixed64List_Internal(int i, LongArrayList longArrayList, boolean z) {
        if (z) {
            requireSpace((longArrayList.size() * 8) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            int size = longArrayList.size();
            while (true) {
                size--;
                if (size >= 0) {
                    writeFixed64(longArrayList.getLong(size));
                } else {
                    a.a(this, totalBytesWritten, i, 2);
                    return;
                }
            }
        } else {
            int size2 = longArrayList.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return;
                } else {
                    writeFixed64(i, longArrayList.getLong(size2));
                }
            }
        }
    }

    private final void writeFloatList_Internal(int i, FloatArrayList floatArrayList, boolean z) {
        if (z) {
            requireSpace((floatArrayList.size() * 4) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            int size = floatArrayList.size();
            while (true) {
                size--;
                if (size >= 0) {
                    writeFixed32(Float.floatToRawIntBits(floatArrayList.getFloat(size)));
                } else {
                    a.a(this, totalBytesWritten, i, 2);
                    return;
                }
            }
        } else {
            int size2 = floatArrayList.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return;
                } else {
                    writeFloat(i, floatArrayList.getFloat(size2));
                }
            }
        }
    }

    private final void writeInt32List_Internal(int i, IntArrayList intArrayList, boolean z) {
        if (z) {
            requireSpace((intArrayList.size() * 10) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            int size = intArrayList.size();
            while (true) {
                size--;
                if (size >= 0) {
                    writeInt32(intArrayList.getInt(size));
                } else {
                    a.a(this, totalBytesWritten, i, 2);
                    return;
                }
            }
        } else {
            int size2 = intArrayList.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return;
                } else {
                    writeInt32(i, intArrayList.getInt(size2));
                }
            }
        }
    }

    private final void writeSInt32List_Internal(int i, IntArrayList intArrayList, boolean z) {
        if (z) {
            requireSpace((intArrayList.size() * 5) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            int size = intArrayList.size();
            while (true) {
                size--;
                if (size >= 0) {
                    writeSInt32(intArrayList.getInt(size));
                } else {
                    a.a(this, totalBytesWritten, i, 2);
                    return;
                }
            }
        } else {
            int size2 = intArrayList.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return;
                } else {
                    writeSInt32(i, intArrayList.getInt(size2));
                }
            }
        }
    }

    private final void writeSInt64List_Internal(int i, LongArrayList longArrayList, boolean z) {
        if (z) {
            requireSpace((longArrayList.size() * 10) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            int size = longArrayList.size();
            while (true) {
                size--;
                if (size >= 0) {
                    writeSInt64(longArrayList.getLong(size));
                } else {
                    a.a(this, totalBytesWritten, i, 2);
                    return;
                }
            }
        } else {
            int size2 = longArrayList.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return;
                } else {
                    writeSInt64(i, longArrayList.getLong(size2));
                }
            }
        }
    }

    private final void writeUInt32List_Internal(int i, IntArrayList intArrayList, boolean z) {
        if (z) {
            requireSpace((intArrayList.size() * 5) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            int size = intArrayList.size();
            while (true) {
                size--;
                if (size >= 0) {
                    writeVarint32(intArrayList.getInt(size));
                } else {
                    a.a(this, totalBytesWritten, i, 2);
                    return;
                }
            }
        } else {
            int size2 = intArrayList.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return;
                } else {
                    writeUInt32(i, intArrayList.getInt(size2));
                }
            }
        }
    }

    private final void writeUInt64List_Internal(int i, LongArrayList longArrayList, boolean z) {
        if (z) {
            requireSpace((longArrayList.size() * 10) + 10);
            int totalBytesWritten = getTotalBytesWritten();
            int size = longArrayList.size();
            while (true) {
                size--;
                if (size >= 0) {
                    writeVarint64(longArrayList.getLong(size));
                } else {
                    a.a(this, totalBytesWritten, i, 2);
                    return;
                }
            }
        } else {
            int size2 = longArrayList.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    return;
                } else {
                    writeUInt64(i, longArrayList.getLong(size2));
                }
            }
        }
    }
}
