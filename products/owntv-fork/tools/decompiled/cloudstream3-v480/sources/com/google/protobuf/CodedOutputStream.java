package com.google.protobuf;

import androidx.work.WorkInfo;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;

/* JADX INFO: loaded from: classes6.dex */
public abstract class CodedOutputStream extends ByteOutput {
    public static final int DEFAULT_BUFFER_SIZE = 4096;
    private static final boolean HAS_UNSAFE_ARRAY_OPERATIONS = UnsafeUtil.hasUnsafeArrayOperations();

    @Deprecated
    public static final int LITTLE_ENDIAN_32_SIZE = 4;
    private boolean serializationDeterministic;
    Object wrapper;

    public static int computeBoolSizeNoTag(final boolean unused) {
        return 1;
    }

    public static int computeDoubleSizeNoTag(final double unused) {
        return 8;
    }

    public static int computeFixed32SizeNoTag(final int unused) {
        return 4;
    }

    public static int computeFixed64SizeNoTag(final long unused) {
        return 8;
    }

    public static int computeFloatSizeNoTag(final float unused) {
        return 4;
    }

    static int computePreferredBufferSize(int dataLength) {
        if (dataLength > 4096) {
            return 4096;
        }
        return dataLength;
    }

    public static int computeSFixed32SizeNoTag(final int unused) {
        return 4;
    }

    public static int computeSFixed64SizeNoTag(final long unused) {
        return 8;
    }

    public static int encodeZigZag32(final int n) {
        return (n >> 31) ^ (n << 1);
    }

    public static long encodeZigZag64(final long n) {
        return (n >> 63) ^ (n << 1);
    }

    public abstract void flush() throws IOException;

    public abstract int getTotalBytesWritten();

    public abstract int spaceLeft();

    @Override // com.google.protobuf.ByteOutput
    public abstract void write(byte value) throws IOException;

    @Override // com.google.protobuf.ByteOutput
    public abstract void write(ByteBuffer value) throws IOException;

    @Override // com.google.protobuf.ByteOutput
    public abstract void write(byte[] value, int offset, int length) throws IOException;

    public abstract void writeBool(int fieldNumber, boolean value) throws IOException;

    public abstract void writeByteArray(int fieldNumber, byte[] value) throws IOException;

    public abstract void writeByteArray(int fieldNumber, byte[] value, int offset, int length) throws IOException;

    abstract void writeByteArrayNoTag(final byte[] value, final int offset, final int length) throws IOException;

    public abstract void writeByteBuffer(int fieldNumber, ByteBuffer value) throws IOException;

    public abstract void writeBytes(int fieldNumber, ByteString value) throws IOException;

    public abstract void writeBytesNoTag(final ByteString value) throws IOException;

    public abstract void writeFixed32(int fieldNumber, int value) throws IOException;

    public abstract void writeFixed32NoTag(int value) throws IOException;

    public abstract void writeFixed64(int fieldNumber, long value) throws IOException;

    public abstract void writeFixed64NoTag(long value) throws IOException;

    public abstract void writeInt32(int fieldNumber, int value) throws IOException;

    public abstract void writeInt32NoTag(final int value) throws IOException;

    @Override // com.google.protobuf.ByteOutput
    public abstract void writeLazy(ByteBuffer value) throws IOException;

    @Override // com.google.protobuf.ByteOutput
    public abstract void writeLazy(byte[] value, int offset, int length) throws IOException;

    public abstract void writeMessage(final int fieldNumber, final MessageLite value) throws IOException;

    public abstract void writeMessageNoTag(final MessageLite value) throws IOException;

    public abstract void writeMessageSetExtension(final int fieldNumber, final MessageLite value) throws IOException;

    public abstract void writeRawBytes(final ByteBuffer value) throws IOException;

    public abstract void writeRawMessageSetExtension(final int fieldNumber, final ByteString value) throws IOException;

    public abstract void writeString(int fieldNumber, String value) throws IOException;

    public abstract void writeStringNoTag(String value) throws IOException;

    public abstract void writeTag(int fieldNumber, int wireType) throws IOException;

    public abstract void writeUInt32(int fieldNumber, int value) throws IOException;

    public abstract void writeUInt32NoTag(int value) throws IOException;

    public abstract void writeUInt64(int fieldNumber, long value) throws IOException;

    public abstract void writeUInt64NoTag(long value) throws IOException;

    public static CodedOutputStream newInstance(final OutputStream output) {
        return newInstance(output, 4096);
    }

    public static CodedOutputStream newInstance(final OutputStream output, final int bufferSize) {
        return new OutputStreamEncoder(output, bufferSize);
    }

    public static CodedOutputStream newInstance(final byte[] flatArray) {
        return newInstance(flatArray, 0, flatArray.length);
    }

    public static CodedOutputStream newInstance(final byte[] flatArray, final int offset, final int length) {
        return new ArrayEncoder(flatArray, offset, length);
    }

    public static CodedOutputStream newInstance(ByteBuffer buffer) {
        if (buffer.isReadOnly()) {
            throw new IllegalArgumentException("ByteBuffer is read-only");
        }
        if (buffer.hasArray()) {
            return new HeapNioEncoder(buffer);
        }
        if (buffer.isDirect()) {
            return new DirectNioEncoder(buffer);
        }
        throw new IllegalArgumentException("ByteBuffer mut be either direct or array");
    }

    public void useDeterministicSerialization() {
        this.serializationDeterministic = true;
    }

    boolean isSerializationDeterministic() {
        return this.serializationDeterministic;
    }

    @Deprecated
    public static CodedOutputStream newInstance(ByteBuffer byteBuffer, int unused) {
        return newInstance(byteBuffer);
    }

    private CodedOutputStream() {
    }

    public final void writeSInt32(final int fieldNumber, final int value) throws IOException {
        writeUInt32(fieldNumber, encodeZigZag32(value));
    }

    public final void writeSFixed32(final int fieldNumber, final int value) throws IOException {
        writeFixed32(fieldNumber, value);
    }

    public final void writeInt64(final int fieldNumber, final long value) throws IOException {
        writeUInt64(fieldNumber, value);
    }

    public final void writeSInt64(final int fieldNumber, final long value) throws IOException {
        writeUInt64(fieldNumber, encodeZigZag64(value));
    }

    public final void writeSFixed64(final int fieldNumber, final long value) throws IOException {
        writeFixed64(fieldNumber, value);
    }

    public final void writeFloat(final int fieldNumber, final float value) throws IOException {
        writeFixed32(fieldNumber, Float.floatToRawIntBits(value));
    }

    public final void writeDouble(final int fieldNumber, final double value) throws IOException {
        writeFixed64(fieldNumber, Double.doubleToRawLongBits(value));
    }

    public final void writeEnum(final int fieldNumber, final int value) throws IOException {
        writeInt32(fieldNumber, value);
    }

    public final void writeRawByte(final byte value) throws IOException {
        write(value);
    }

    public final void writeRawByte(final int value) throws IOException {
        write((byte) value);
    }

    public final void writeRawBytes(final byte[] value) throws IOException {
        write(value, 0, value.length);
    }

    public final void writeRawBytes(final byte[] value, int offset, int length) throws IOException {
        write(value, offset, length);
    }

    public final void writeRawBytes(final ByteString value) throws IOException {
        value.writeTo(this);
    }

    public final void writeSInt32NoTag(final int value) throws IOException {
        writeUInt32NoTag(encodeZigZag32(value));
    }

    public final void writeSFixed32NoTag(final int value) throws IOException {
        writeFixed32NoTag(value);
    }

    public final void writeInt64NoTag(final long value) throws IOException {
        writeUInt64NoTag(value);
    }

    public final void writeSInt64NoTag(final long value) throws IOException {
        writeUInt64NoTag(encodeZigZag64(value));
    }

    public final void writeSFixed64NoTag(final long value) throws IOException {
        writeFixed64NoTag(value);
    }

    public final void writeFloatNoTag(final float value) throws IOException {
        writeFixed32NoTag(Float.floatToRawIntBits(value));
    }

    public final void writeDoubleNoTag(final double value) throws IOException {
        writeFixed64NoTag(Double.doubleToRawLongBits(value));
    }

    public final void writeBoolNoTag(boolean z) throws IOException {
        write(z ? (byte) 1 : (byte) 0);
    }

    public final void writeEnumNoTag(final int value) throws IOException {
        writeInt32NoTag(value);
    }

    public final void writeByteArrayNoTag(final byte[] value) throws IOException {
        writeByteArrayNoTag(value, 0, value.length);
    }

    public static int computeInt32Size(final int fieldNumber, final int value) {
        return computeTagSize(fieldNumber) + computeInt32SizeNoTag(value);
    }

    public static int computeUInt32Size(final int fieldNumber, final int value) {
        return computeTagSize(fieldNumber) + computeUInt32SizeNoTag(value);
    }

    public static int computeSInt32Size(final int fieldNumber, final int value) {
        return computeTagSize(fieldNumber) + computeSInt32SizeNoTag(value);
    }

    public static int computeFixed32Size(final int fieldNumber, final int value) {
        return computeTagSize(fieldNumber) + computeFixed32SizeNoTag(value);
    }

    public static int computeSFixed32Size(final int fieldNumber, final int value) {
        return computeTagSize(fieldNumber) + computeSFixed32SizeNoTag(value);
    }

    public static int computeInt64Size(final int fieldNumber, final long value) {
        return computeTagSize(fieldNumber) + computeInt64SizeNoTag(value);
    }

    public static int computeUInt64Size(final int fieldNumber, final long value) {
        return computeTagSize(fieldNumber) + computeUInt64SizeNoTag(value);
    }

    public static int computeSInt64Size(final int fieldNumber, final long value) {
        return computeTagSize(fieldNumber) + computeSInt64SizeNoTag(value);
    }

    public static int computeFixed64Size(final int fieldNumber, final long value) {
        return computeTagSize(fieldNumber) + computeFixed64SizeNoTag(value);
    }

    public static int computeSFixed64Size(final int fieldNumber, final long value) {
        return computeTagSize(fieldNumber) + computeSFixed64SizeNoTag(value);
    }

    public static int computeFloatSize(final int fieldNumber, final float value) {
        return computeTagSize(fieldNumber) + computeFloatSizeNoTag(value);
    }

    public static int computeDoubleSize(final int fieldNumber, final double value) {
        return computeTagSize(fieldNumber) + computeDoubleSizeNoTag(value);
    }

    public static int computeBoolSize(final int fieldNumber, final boolean value) {
        return computeTagSize(fieldNumber) + computeBoolSizeNoTag(value);
    }

    public static int computeEnumSize(final int fieldNumber, final int value) {
        return computeTagSize(fieldNumber) + computeEnumSizeNoTag(value);
    }

    public static int computeStringSize(final int fieldNumber, final String value) {
        return computeTagSize(fieldNumber) + computeStringSizeNoTag(value);
    }

    public static int computeBytesSize(final int fieldNumber, final ByteString value) {
        return computeTagSize(fieldNumber) + computeBytesSizeNoTag(value);
    }

    public static int computeByteArraySize(final int fieldNumber, final byte[] value) {
        return computeTagSize(fieldNumber) + computeByteArraySizeNoTag(value);
    }

    public static int computeByteBufferSize(final int fieldNumber, final ByteBuffer value) {
        return computeTagSize(fieldNumber) + computeByteBufferSizeNoTag(value);
    }

    @Deprecated
    public static int computeLazyFieldSize(final int fieldNumber, final LazyFieldLite value) {
        return value.computeSize(fieldNumber);
    }

    public static int computeMessageSize(final int fieldNumber, final MessageLite value) {
        return computeTagSize(fieldNumber) + computeMessageSizeNoTag(value);
    }

    public static int computeMessageSetExtensionSize(final int fieldNumber, final MessageLite value) {
        return (computeTagSize(1) * 2) + computeUInt32Size(2, fieldNumber) + computeMessageSize(3, value);
    }

    public static int computeRawMessageSetExtensionSize(final int fieldNumber, final ByteString value) {
        return (computeTagSize(1) * 2) + computeUInt32Size(2, fieldNumber) + computeBytesSize(3, value);
    }

    @Deprecated
    public static int computeLazyFieldMessageSetExtensionSize(final int fieldNumber, final LazyFieldLite value) {
        return value.computeMessageSetExtensionSize(fieldNumber);
    }

    public static int computeTagSize(final int fieldNumber) {
        return computeUInt32SizeNoTag(WireFormat.makeTag(fieldNumber, 0));
    }

    public static int computeInt32SizeNoTag(final int value) {
        return computeUInt64SizeNoTag(value);
    }

    public static int computeUInt32SizeNoTag(final int value) {
        return (352 - (Integer.numberOfLeadingZeros(value) * 9)) >>> 6;
    }

    public static int computeSInt32SizeNoTag(final int value) {
        return computeUInt32SizeNoTag(encodeZigZag32(value));
    }

    public static int computeInt64SizeNoTag(final long value) {
        return computeUInt64SizeNoTag(value);
    }

    public static int computeUInt64SizeNoTag(long value) {
        return (640 - (Long.numberOfLeadingZeros(value) * 9)) >>> 6;
    }

    public static int computeSInt64SizeNoTag(final long value) {
        return computeUInt64SizeNoTag(encodeZigZag64(value));
    }

    public static int computeEnumSizeNoTag(final int value) {
        return computeInt32SizeNoTag(value);
    }

    public static int computeStringSizeNoTag(final String value) {
        return computeLengthDelimitedFieldSize(Utf8.encodedLength(value));
    }

    @Deprecated
    public static int computeLazyFieldSizeNoTag(final LazyFieldLite value) {
        return value.computeSizeNoTag();
    }

    public static int computeBytesSizeNoTag(final ByteString value) {
        return computeLengthDelimitedFieldSize(value.size());
    }

    public static int computeByteArraySizeNoTag(final byte[] value) {
        return computeLengthDelimitedFieldSize(value.length);
    }

    public static int computeByteBufferSizeNoTag(final ByteBuffer value) {
        return computeLengthDelimitedFieldSize(value.capacity());
    }

    public static int computeMessageSizeNoTag(final MessageLite value) {
        return computeLengthDelimitedFieldSize(value.getSerializedSize());
    }

    static int computeLengthDelimitedFieldSize(int fieldLength) {
        return computeUInt32SizeNoTag(fieldLength) + fieldLength;
    }

    public final void checkNoSpaceLeft() {
        if (spaceLeft() > 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
        if (spaceLeft() < 0) {
            throw new IllegalStateException("Wrote more data than expected.");
        }
    }

    public static class OutOfSpaceException extends IOException {
        private static final String MESSAGE = "CodedOutputStream was writing to a flat byte array and ran out of space.";
        private static final long serialVersionUID = -6947486886997889499L;

        OutOfSpaceException() {
            super(MESSAGE);
        }

        OutOfSpaceException(String explanationMessage) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.: " + explanationMessage);
        }

        OutOfSpaceException(Throwable cause) {
            super(MESSAGE, cause);
        }

        OutOfSpaceException(String explanationMessage, Throwable cause) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.: " + explanationMessage, cause);
        }

        OutOfSpaceException(int position, int limit, int length) {
            this(position, limit, length, (Throwable) null);
        }

        OutOfSpaceException(int position, int limit, int length, Throwable cause) {
            this(position, limit, length, cause);
        }

        OutOfSpaceException(long position, long limit, int length) {
            this(position, limit, length, (Throwable) null);
        }

        OutOfSpaceException(long position, long limit, int length, Throwable cause) {
            this(String.format(Locale.US, "Pos: %d, limit: %d, len: %d", Long.valueOf(position), Long.valueOf(limit), Integer.valueOf(length)), cause);
        }
    }

    @Deprecated
    public final void writeGroup(final int fieldNumber, final MessageLite value) throws IOException {
        writeTag(fieldNumber, 3);
        writeGroupNoTag(value);
        writeTag(fieldNumber, 4);
    }

    @Deprecated
    public final void writeGroupNoTag(final MessageLite value) throws IOException {
        value.writeTo(this);
    }

    @Deprecated
    public static int computeGroupSize(final int fieldNumber, final MessageLite value) {
        return (computeTagSize(fieldNumber) * 2) + value.getSerializedSize();
    }

    @Deprecated
    public static int computeGroupSizeNoTag(final MessageLite value) {
        return value.getSerializedSize();
    }

    @Deprecated
    public final void writeRawVarint32(int value) throws IOException {
        writeUInt32NoTag(value);
    }

    @Deprecated
    public final void writeRawVarint64(long value) throws IOException {
        writeUInt64NoTag(value);
    }

    @Deprecated
    public static int computeRawVarint32Size(final int value) {
        return computeUInt32SizeNoTag(value);
    }

    @Deprecated
    public static int computeRawVarint64Size(long value) {
        return computeUInt64SizeNoTag(value);
    }

    @Deprecated
    public final void writeRawLittleEndian32(final int value) throws IOException {
        writeFixed32NoTag(value);
    }

    @Deprecated
    public final void writeRawLittleEndian64(final long value) throws IOException {
        writeFixed64NoTag(value);
    }

    private static class ArrayEncoder extends CodedOutputStream {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        @Override // com.google.protobuf.CodedOutputStream
        public void flush() {
        }

        ArrayEncoder(byte[] buffer, int offset, int length) {
            super();
            if (buffer == null) {
                throw new NullPointerException("buffer");
            }
            int i = offset + length;
            if ((offset | length | (buffer.length - i)) < 0) {
                throw new IllegalArgumentException(String.format(Locale.US, "Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(buffer.length), Integer.valueOf(offset), Integer.valueOf(length)));
            }
            this.buffer = buffer;
            this.offset = offset;
            this.position = offset;
            this.limit = i;
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeTag(final int fieldNumber, final int wireType) throws IOException {
            writeUInt32NoTag(WireFormat.makeTag(fieldNumber, wireType));
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeInt32(final int fieldNumber, final int value) throws IOException {
            writeTag(fieldNumber, 0);
            writeInt32NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeUInt32(final int fieldNumber, final int value) throws IOException {
            writeTag(fieldNumber, 0);
            writeUInt32NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeFixed32(final int fieldNumber, final int value) throws IOException {
            writeTag(fieldNumber, 5);
            writeFixed32NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeUInt64(final int fieldNumber, final long value) throws IOException {
            writeTag(fieldNumber, 0);
            writeUInt64NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeFixed64(final int fieldNumber, final long value) throws IOException {
            writeTag(fieldNumber, 1);
            writeFixed64NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeBool(int i, boolean z) throws IOException {
            writeTag(i, 0);
            write(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeString(final int fieldNumber, final String value) throws IOException {
            writeTag(fieldNumber, 2);
            writeStringNoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeBytes(final int fieldNumber, final ByteString value) throws IOException {
            writeTag(fieldNumber, 2);
            writeBytesNoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeByteArray(final int fieldNumber, final byte[] value) throws IOException {
            writeByteArray(fieldNumber, value, 0, value.length);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeByteArray(final int fieldNumber, final byte[] value, final int offset, final int length) throws IOException {
            writeTag(fieldNumber, 2);
            writeByteArrayNoTag(value, offset, length);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeByteBuffer(final int fieldNumber, final ByteBuffer value) throws IOException {
            writeTag(fieldNumber, 2);
            writeUInt32NoTag(value.capacity());
            writeRawBytes(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeBytesNoTag(final ByteString value) throws IOException {
            writeUInt32NoTag(value.size());
            value.writeTo(this);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeByteArrayNoTag(final byte[] value, int offset, int length) throws IOException {
            writeUInt32NoTag(length);
            write(value, offset, length);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeRawBytes(final ByteBuffer value) throws IOException {
            if (value.hasArray()) {
                write(value.array(), value.arrayOffset(), value.capacity());
                return;
            }
            ByteBuffer byteBufferDuplicate = value.duplicate();
            Java8Compatibility.clear(byteBufferDuplicate);
            write(byteBufferDuplicate);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeMessage(final int fieldNumber, final MessageLite value) throws IOException {
            writeTag(fieldNumber, 2);
            writeMessageNoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeMessageSetExtension(final int fieldNumber, final MessageLite value) throws IOException {
            writeTag(1, 3);
            writeUInt32(2, fieldNumber);
            writeMessage(3, value);
            writeTag(1, 4);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeRawMessageSetExtension(final int fieldNumber, final ByteString value) throws IOException {
            writeTag(1, 3);
            writeUInt32(2, fieldNumber);
            writeBytes(3, value);
            writeTag(1, 4);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeMessageNoTag(final MessageLite value) throws IOException {
            writeUInt32NoTag(value.getSerializedSize());
            value.writeTo(this);
        }

        @Override // com.google.protobuf.CodedOutputStream, com.google.protobuf.ByteOutput
        public final void write(byte value) throws IOException {
            int i = this.position;
            try {
                int i2 = i + 1;
                try {
                    this.buffer[i] = value;
                    this.position = i2;
                } catch (IndexOutOfBoundsException e) {
                    e = e;
                    i = i2;
                    throw new OutOfSpaceException(i, this.limit, 1, (Throwable) e);
                }
            } catch (IndexOutOfBoundsException e2) {
                e = e2;
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeInt32NoTag(int value) throws IOException {
            if (value >= 0) {
                writeUInt32NoTag(value);
            } else {
                writeUInt64NoTag(value);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeUInt32NoTag(int value) throws IOException {
            int i;
            int i2 = this.position;
            while (true) {
                int i3 = value & WorkInfo.STOP_REASON_FOREGROUND_SERVICE_TIMEOUT;
                byte[] bArr = this.buffer;
                if (i3 == 0) {
                    i = i2 + 1;
                    bArr[i2] = (byte) value;
                    this.position = i;
                    return;
                } else {
                    i = i2 + 1;
                    try {
                        bArr[i2] = (byte) (value | 128);
                        value >>>= 7;
                        i2 = i;
                    } catch (IndexOutOfBoundsException e) {
                        throw new OutOfSpaceException(i, this.limit, 1, (Throwable) e);
                    }
                }
                throw new OutOfSpaceException(i, this.limit, 1, (Throwable) e);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeFixed32NoTag(int value) throws IOException {
            int i = this.position;
            try {
                byte[] bArr = this.buffer;
                bArr[i] = (byte) value;
                bArr[i + 1] = (byte) (value >> 8);
                bArr[i + 2] = (byte) (value >> 16);
                bArr[i + 3] = (byte) (value >> 24);
                this.position = i + 4;
            } catch (IndexOutOfBoundsException e) {
                throw new OutOfSpaceException(i, this.limit, 4, (Throwable) e);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeUInt64NoTag(long value) throws IOException {
            byte[] bArr;
            int i;
            byte[] bArr2;
            int i2 = this.position;
            if (CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS && spaceLeft() >= 10) {
                while (true) {
                    long j = value & (-128);
                    bArr2 = this.buffer;
                    if (j == 0) {
                        break;
                    }
                    UnsafeUtil.putByte(bArr2, i2, (byte) (((int) value) | 128));
                    value >>>= 7;
                    i2++;
                }
                i = i2 + 1;
                UnsafeUtil.putByte(bArr2, i2, (byte) value);
            } else {
                while (true) {
                    long j2 = value & (-128);
                    bArr = this.buffer;
                    if (j2 == 0) {
                        break;
                    }
                    i = i2 + 1;
                    try {
                        bArr[i2] = (byte) (((int) value) | 128);
                        value >>>= 7;
                        i2 = i;
                    } catch (IndexOutOfBoundsException e) {
                        throw new OutOfSpaceException(i, this.limit, 1, (Throwable) e);
                    }
                    throw new OutOfSpaceException(i, this.limit, 1, (Throwable) e);
                }
                i = i2 + 1;
                bArr[i2] = (byte) value;
            }
            this.position = i;
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeFixed64NoTag(long value) throws IOException {
            int i = this.position;
            try {
                byte[] bArr = this.buffer;
                bArr[i] = (byte) value;
                bArr[i + 1] = (byte) (value >> 8);
                bArr[i + 2] = (byte) (value >> 16);
                bArr[i + 3] = (byte) (value >> 24);
                bArr[i + 4] = (byte) (value >> 32);
                bArr[i + 5] = (byte) (value >> 40);
                bArr[i + 6] = (byte) (value >> 48);
                bArr[i + 7] = (byte) (value >> 56);
                this.position = i + 8;
            } catch (IndexOutOfBoundsException e) {
                throw new OutOfSpaceException(i, this.limit, 8, (Throwable) e);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream, com.google.protobuf.ByteOutput
        public final void write(byte[] value, int offset, int length) throws IOException {
            try {
                System.arraycopy(value, offset, this.buffer, this.position, length);
                this.position += length;
            } catch (IndexOutOfBoundsException e) {
                throw new OutOfSpaceException(this.position, this.limit, length, (Throwable) e);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream, com.google.protobuf.ByteOutput
        public final void writeLazy(byte[] value, int offset, int length) throws IOException {
            write(value, offset, length);
        }

        @Override // com.google.protobuf.CodedOutputStream, com.google.protobuf.ByteOutput
        public final void write(ByteBuffer value) throws IOException {
            int iRemaining = value.remaining();
            try {
                value.get(this.buffer, this.position, iRemaining);
                this.position += iRemaining;
            } catch (IndexOutOfBoundsException e) {
                throw new OutOfSpaceException(this.position, this.limit, iRemaining, (Throwable) e);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream, com.google.protobuf.ByteOutput
        public final void writeLazy(ByteBuffer value) throws IOException {
            write(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeStringNoTag(String value) throws IOException {
            int i = this.position;
            try {
                int iComputeUInt32SizeNoTag = computeUInt32SizeNoTag(value.length() * 3);
                int iComputeUInt32SizeNoTag2 = computeUInt32SizeNoTag(value.length());
                if (iComputeUInt32SizeNoTag2 == iComputeUInt32SizeNoTag) {
                    int i2 = i + iComputeUInt32SizeNoTag2;
                    this.position = i2;
                    byte[] bArr = this.buffer;
                    int iEncode = Utf8.encode(value, bArr, i2, bArr.length - i2);
                    this.position = i;
                    writeUInt32NoTag((iEncode - i) - iComputeUInt32SizeNoTag2);
                    this.position = iEncode;
                    return;
                }
                writeUInt32NoTag(Utf8.encodedLength(value));
                byte[] bArr2 = this.buffer;
                int i3 = this.position;
                this.position = Utf8.encode(value, bArr2, i3, bArr2.length - i3);
            } catch (IndexOutOfBoundsException e) {
                throw new OutOfSpaceException(e);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final int spaceLeft() {
            return this.limit - this.position;
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final int getTotalBytesWritten() {
            return this.position - this.offset;
        }
    }

    private static final class HeapNioEncoder extends ArrayEncoder {
        private final ByteBuffer byteBuffer;
        private int initialPosition;

        HeapNioEncoder(ByteBuffer byteBuffer) {
            super(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
            this.byteBuffer = byteBuffer;
            this.initialPosition = byteBuffer.position();
        }

        @Override // com.google.protobuf.CodedOutputStream.ArrayEncoder, com.google.protobuf.CodedOutputStream
        public void flush() {
            Java8Compatibility.position(this.byteBuffer, this.initialPosition + getTotalBytesWritten());
        }
    }

    private static final class DirectNioEncoder extends CodedOutputStream {
        private final ByteBuffer buffer;
        private final int initialPosition;
        private final ByteBuffer originalBuffer;

        DirectNioEncoder(ByteBuffer buffer) {
            super();
            this.originalBuffer = buffer;
            this.buffer = buffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.initialPosition = buffer.position();
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeTag(final int fieldNumber, final int wireType) throws IOException {
            writeUInt32NoTag(WireFormat.makeTag(fieldNumber, wireType));
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeInt32(final int fieldNumber, final int value) throws IOException {
            writeTag(fieldNumber, 0);
            writeInt32NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeUInt32(final int fieldNumber, final int value) throws IOException {
            writeTag(fieldNumber, 0);
            writeUInt32NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeFixed32(final int fieldNumber, final int value) throws IOException {
            writeTag(fieldNumber, 5);
            writeFixed32NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeUInt64(final int fieldNumber, final long value) throws IOException {
            writeTag(fieldNumber, 0);
            writeUInt64NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeFixed64(final int fieldNumber, final long value) throws IOException {
            writeTag(fieldNumber, 1);
            writeFixed64NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeBool(int i, boolean z) throws IOException {
            writeTag(i, 0);
            write(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeString(final int fieldNumber, final String value) throws IOException {
            writeTag(fieldNumber, 2);
            writeStringNoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeBytes(final int fieldNumber, final ByteString value) throws IOException {
            writeTag(fieldNumber, 2);
            writeBytesNoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeByteArray(final int fieldNumber, final byte[] value) throws IOException {
            writeByteArray(fieldNumber, value, 0, value.length);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeByteArray(final int fieldNumber, final byte[] value, final int offset, final int length) throws IOException {
            writeTag(fieldNumber, 2);
            writeByteArrayNoTag(value, offset, length);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeByteBuffer(final int fieldNumber, final ByteBuffer value) throws IOException {
            writeTag(fieldNumber, 2);
            writeUInt32NoTag(value.capacity());
            writeRawBytes(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeMessage(final int fieldNumber, final MessageLite value) throws IOException {
            writeTag(fieldNumber, 2);
            writeMessageNoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeMessageSetExtension(final int fieldNumber, final MessageLite value) throws IOException {
            writeTag(1, 3);
            writeUInt32(2, fieldNumber);
            writeMessage(3, value);
            writeTag(1, 4);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeRawMessageSetExtension(final int fieldNumber, final ByteString value) throws IOException {
            writeTag(1, 3);
            writeUInt32(2, fieldNumber);
            writeBytes(3, value);
            writeTag(1, 4);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeMessageNoTag(final MessageLite value) throws IOException {
            writeUInt32NoTag(value.getSerializedSize());
            value.writeTo(this);
        }

        @Override // com.google.protobuf.CodedOutputStream, com.google.protobuf.ByteOutput
        public void write(byte value) throws IOException {
            try {
                this.buffer.put(value);
            } catch (BufferOverflowException e) {
                throw new OutOfSpaceException(this.buffer.position(), this.buffer.limit(), 1, (Throwable) e);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeBytesNoTag(final ByteString value) throws IOException {
            writeUInt32NoTag(value.size());
            value.writeTo(this);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeByteArrayNoTag(final byte[] value, int offset, int length) throws IOException {
            writeUInt32NoTag(length);
            write(value, offset, length);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeRawBytes(final ByteBuffer value) throws IOException {
            if (value.hasArray()) {
                write(value.array(), value.arrayOffset(), value.capacity());
                return;
            }
            ByteBuffer byteBufferDuplicate = value.duplicate();
            Java8Compatibility.clear(byteBufferDuplicate);
            write(byteBufferDuplicate);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeInt32NoTag(int value) throws IOException {
            if (value >= 0) {
                writeUInt32NoTag(value);
            } else {
                writeUInt64NoTag(value);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeUInt32NoTag(int value) throws IOException {
            while (true) {
                int i = value & WorkInfo.STOP_REASON_FOREGROUND_SERVICE_TIMEOUT;
                ByteBuffer byteBuffer = this.buffer;
                if (i == 0) {
                    byteBuffer.put((byte) value);
                    return;
                }
                try {
                    byteBuffer.put((byte) (value | 128));
                    value >>>= 7;
                } catch (BufferOverflowException e) {
                    throw new OutOfSpaceException(e);
                }
                throw new OutOfSpaceException(e);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeFixed32NoTag(int value) throws IOException {
            try {
                this.buffer.putInt(value);
            } catch (BufferOverflowException e) {
                throw new OutOfSpaceException(this.buffer.position(), this.buffer.limit(), 4, (Throwable) e);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeUInt64NoTag(long value) throws IOException {
            while (true) {
                long j = (-128) & value;
                ByteBuffer byteBuffer = this.buffer;
                if (j == 0) {
                    byteBuffer.put((byte) value);
                    return;
                }
                try {
                    byteBuffer.put((byte) (((int) value) | 128));
                    value >>>= 7;
                } catch (BufferOverflowException e) {
                    throw new OutOfSpaceException(e);
                }
                throw new OutOfSpaceException(e);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeFixed64NoTag(long value) throws IOException {
            try {
                this.buffer.putLong(value);
            } catch (BufferOverflowException e) {
                throw new OutOfSpaceException(this.buffer.position(), this.buffer.limit(), 8, (Throwable) e);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream, com.google.protobuf.ByteOutput
        public void write(byte[] value, int offset, int length) throws IOException {
            try {
                this.buffer.put(value, offset, length);
            } catch (IndexOutOfBoundsException e) {
                throw new OutOfSpaceException(e);
            } catch (BufferOverflowException e2) {
                throw new OutOfSpaceException(e2);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream, com.google.protobuf.ByteOutput
        public void writeLazy(byte[] value, int offset, int length) throws IOException {
            write(value, offset, length);
        }

        @Override // com.google.protobuf.CodedOutputStream, com.google.protobuf.ByteOutput
        public void write(ByteBuffer value) throws IOException {
            try {
                this.buffer.put(value);
            } catch (BufferOverflowException e) {
                throw new OutOfSpaceException(e);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream, com.google.protobuf.ByteOutput
        public void writeLazy(ByteBuffer value) throws IOException {
            write(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeStringNoTag(String value) throws IOException {
            int iPosition = this.buffer.position();
            try {
                int iComputeUInt32SizeNoTag = computeUInt32SizeNoTag(value.length() * 3);
                int iComputeUInt32SizeNoTag2 = computeUInt32SizeNoTag(value.length());
                if (iComputeUInt32SizeNoTag2 == iComputeUInt32SizeNoTag) {
                    int iPosition2 = this.buffer.position() + iComputeUInt32SizeNoTag2;
                    Java8Compatibility.position(this.buffer, iPosition2);
                    encode(value);
                    int iPosition3 = this.buffer.position();
                    Java8Compatibility.position(this.buffer, iPosition);
                    writeUInt32NoTag(iPosition3 - iPosition2);
                    Java8Compatibility.position(this.buffer, iPosition3);
                    return;
                }
                writeUInt32NoTag(Utf8.encodedLength(value));
                encode(value);
            } catch (IllegalArgumentException e) {
                throw new OutOfSpaceException(e);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void flush() {
            Java8Compatibility.position(this.originalBuffer, this.buffer.position());
        }

        @Override // com.google.protobuf.CodedOutputStream
        public int spaceLeft() {
            return this.buffer.remaining();
        }

        @Override // com.google.protobuf.CodedOutputStream
        public int getTotalBytesWritten() {
            return this.buffer.position() - this.initialPosition;
        }

        private void encode(String value) throws IOException {
            try {
                Utf8.encodeUtf8(value, this.buffer);
            } catch (IndexOutOfBoundsException e) {
                throw new OutOfSpaceException(e);
            }
        }
    }

    private static final class OutputStreamEncoder extends CodedOutputStream {
        private final byte[] buffer;
        private final int limit;
        private final OutputStream out;
        private int position;
        private int totalBytesWritten;

        OutputStreamEncoder(OutputStream out, int bufferSize) {
            super();
            if (out == null) {
                throw new NullPointerException("out");
            }
            this.out = out;
            if (bufferSize < 0) {
                throw new IllegalArgumentException("bufferSize must be >= 0");
            }
            byte[] bArr = new byte[Math.max(bufferSize, 20)];
            this.buffer = bArr;
            this.limit = bArr.length;
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final int spaceLeft() {
            throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array or ByteBuffer.");
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final int getTotalBytesWritten() {
            return this.totalBytesWritten;
        }

        final void buffer(byte value) {
            int i = this.position;
            this.buffer[i] = value;
            this.position = i + 1;
            this.totalBytesWritten++;
        }

        final void bufferTag(final int fieldNumber, final int wireType) {
            bufferUInt32NoTag(WireFormat.makeTag(fieldNumber, wireType));
        }

        final void bufferInt32NoTag(final int value) {
            if (value >= 0) {
                bufferUInt32NoTag(value);
            } else {
                bufferUInt64NoTag(value);
            }
        }

        final void bufferUInt32NoTag(int value) {
            if (CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS) {
                long j = this.position;
                while (true) {
                    int i = value & WorkInfo.STOP_REASON_FOREGROUND_SERVICE_TIMEOUT;
                    byte[] bArr = this.buffer;
                    if (i == 0) {
                        int i2 = this.position;
                        this.position = i2 + 1;
                        UnsafeUtil.putByte(bArr, i2, (byte) value);
                        this.totalBytesWritten += (int) (((long) this.position) - j);
                        return;
                    }
                    int i3 = this.position;
                    this.position = i3 + 1;
                    UnsafeUtil.putByte(bArr, i3, (byte) (value | 128));
                    value >>>= 7;
                }
            } else {
                while (true) {
                    int i4 = value & WorkInfo.STOP_REASON_FOREGROUND_SERVICE_TIMEOUT;
                    byte[] bArr2 = this.buffer;
                    if (i4 == 0) {
                        int i5 = this.position;
                        this.position = i5 + 1;
                        bArr2[i5] = (byte) value;
                        this.totalBytesWritten++;
                        return;
                    }
                    int i6 = this.position;
                    this.position = i6 + 1;
                    bArr2[i6] = (byte) (value | 128);
                    this.totalBytesWritten++;
                    value >>>= 7;
                }
            }
        }

        final void bufferUInt64NoTag(long value) {
            if (CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS) {
                long j = this.position;
                while (true) {
                    long j2 = value & (-128);
                    byte[] bArr = this.buffer;
                    if (j2 == 0) {
                        int i = this.position;
                        this.position = i + 1;
                        UnsafeUtil.putByte(bArr, i, (byte) value);
                        this.totalBytesWritten += (int) (((long) this.position) - j);
                        return;
                    }
                    int i2 = this.position;
                    this.position = i2 + 1;
                    UnsafeUtil.putByte(bArr, i2, (byte) (((int) value) | 128));
                    value >>>= 7;
                }
            } else {
                while (true) {
                    long j3 = value & (-128);
                    byte[] bArr2 = this.buffer;
                    if (j3 == 0) {
                        int i3 = this.position;
                        this.position = i3 + 1;
                        bArr2[i3] = (byte) value;
                        this.totalBytesWritten++;
                        return;
                    }
                    int i4 = this.position;
                    this.position = i4 + 1;
                    bArr2[i4] = (byte) (((int) value) | 128);
                    this.totalBytesWritten++;
                    value >>>= 7;
                }
            }
        }

        final void bufferFixed32NoTag(int value) {
            int i = this.position;
            byte[] bArr = this.buffer;
            bArr[i] = (byte) value;
            bArr[i + 1] = (byte) (value >> 8);
            bArr[i + 2] = (byte) (value >> 16);
            bArr[i + 3] = (byte) (value >> 24);
            this.position = i + 4;
            this.totalBytesWritten += 4;
        }

        final void bufferFixed64NoTag(long value) {
            int i = this.position;
            byte[] bArr = this.buffer;
            bArr[i] = (byte) value;
            bArr[i + 1] = (byte) (value >> 8);
            bArr[i + 2] = (byte) (value >> 16);
            bArr[i + 3] = (byte) (value >> 24);
            bArr[i + 4] = (byte) (value >> 32);
            bArr[i + 5] = (byte) (value >> 40);
            bArr[i + 6] = (byte) (value >> 48);
            bArr[i + 7] = (byte) (value >> 56);
            this.position = i + 8;
            this.totalBytesWritten += 8;
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeTag(final int fieldNumber, final int wireType) throws IOException {
            writeUInt32NoTag(WireFormat.makeTag(fieldNumber, wireType));
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeInt32(final int fieldNumber, final int value) throws IOException {
            flushIfNotAvailable(20);
            bufferTag(fieldNumber, 0);
            bufferInt32NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeUInt32(final int fieldNumber, final int value) throws IOException {
            flushIfNotAvailable(20);
            bufferTag(fieldNumber, 0);
            bufferUInt32NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeFixed32(final int fieldNumber, final int value) throws IOException {
            flushIfNotAvailable(14);
            bufferTag(fieldNumber, 5);
            bufferFixed32NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeUInt64(final int fieldNumber, final long value) throws IOException {
            flushIfNotAvailable(20);
            bufferTag(fieldNumber, 0);
            bufferUInt64NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeFixed64(final int fieldNumber, final long value) throws IOException {
            flushIfNotAvailable(18);
            bufferTag(fieldNumber, 1);
            bufferFixed64NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeBool(int i, boolean z) throws IOException {
            flushIfNotAvailable(11);
            bufferTag(i, 0);
            buffer(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeString(final int fieldNumber, final String value) throws IOException {
            writeTag(fieldNumber, 2);
            writeStringNoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeBytes(final int fieldNumber, final ByteString value) throws IOException {
            writeTag(fieldNumber, 2);
            writeBytesNoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeByteArray(final int fieldNumber, final byte[] value) throws IOException {
            writeByteArray(fieldNumber, value, 0, value.length);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeByteArray(final int fieldNumber, final byte[] value, final int offset, final int length) throws IOException {
            writeTag(fieldNumber, 2);
            writeByteArrayNoTag(value, offset, length);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeByteBuffer(final int fieldNumber, final ByteBuffer value) throws IOException {
            writeTag(fieldNumber, 2);
            writeUInt32NoTag(value.capacity());
            writeRawBytes(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeBytesNoTag(final ByteString value) throws IOException {
            writeUInt32NoTag(value.size());
            value.writeTo(this);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeByteArrayNoTag(final byte[] value, int offset, int length) throws IOException {
            writeUInt32NoTag(length);
            write(value, offset, length);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeRawBytes(final ByteBuffer value) throws IOException {
            if (value.hasArray()) {
                write(value.array(), value.arrayOffset(), value.capacity());
                return;
            }
            ByteBuffer byteBufferDuplicate = value.duplicate();
            Java8Compatibility.clear(byteBufferDuplicate);
            write(byteBufferDuplicate);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeMessage(final int fieldNumber, final MessageLite value) throws IOException {
            writeTag(fieldNumber, 2);
            writeMessageNoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeMessageSetExtension(final int fieldNumber, final MessageLite value) throws IOException {
            writeTag(1, 3);
            writeUInt32(2, fieldNumber);
            writeMessage(3, value);
            writeTag(1, 4);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeRawMessageSetExtension(final int fieldNumber, final ByteString value) throws IOException {
            writeTag(1, 3);
            writeUInt32(2, fieldNumber);
            writeBytes(3, value);
            writeTag(1, 4);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeMessageNoTag(final MessageLite value) throws IOException {
            writeUInt32NoTag(value.getSerializedSize());
            value.writeTo(this);
        }

        @Override // com.google.protobuf.CodedOutputStream, com.google.protobuf.ByteOutput
        public void write(byte value) throws IOException {
            if (this.position == this.limit) {
                doFlush();
            }
            buffer(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeInt32NoTag(int value) throws IOException {
            if (value >= 0) {
                writeUInt32NoTag(value);
            } else {
                writeUInt64NoTag(value);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeUInt32NoTag(int value) throws IOException {
            flushIfNotAvailable(5);
            bufferUInt32NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeFixed32NoTag(final int value) throws IOException {
            flushIfNotAvailable(4);
            bufferFixed32NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeUInt64NoTag(long value) throws IOException {
            flushIfNotAvailable(10);
            bufferUInt64NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeFixed64NoTag(final long value) throws IOException {
            flushIfNotAvailable(8);
            bufferFixed64NoTag(value);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void writeStringNoTag(String value) throws IOException {
            int iEncodedLength;
            int length = value.length() * 3;
            int iComputeUInt32SizeNoTag = computeUInt32SizeNoTag(length);
            int i = iComputeUInt32SizeNoTag + length;
            int i2 = this.limit;
            if (i > i2) {
                byte[] bArr = new byte[length];
                int iEncode = Utf8.encode(value, bArr, 0, length);
                writeUInt32NoTag(iEncode);
                writeLazy(bArr, 0, iEncode);
                return;
            }
            if (i > i2 - this.position) {
                doFlush();
            }
            int iComputeUInt32SizeNoTag2 = computeUInt32SizeNoTag(value.length());
            int i3 = this.position;
            try {
                if (iComputeUInt32SizeNoTag2 == iComputeUInt32SizeNoTag) {
                    int i4 = i3 + iComputeUInt32SizeNoTag2;
                    this.position = i4;
                    int iEncode2 = Utf8.encode(value, this.buffer, i4, this.limit - i4);
                    this.position = i3;
                    iEncodedLength = (iEncode2 - i3) - iComputeUInt32SizeNoTag2;
                    bufferUInt32NoTag(iEncodedLength);
                    this.position = iEncode2;
                } else {
                    iEncodedLength = Utf8.encodedLength(value);
                    bufferUInt32NoTag(iEncodedLength);
                    this.position = Utf8.encode(value, this.buffer, this.position, iEncodedLength);
                }
                this.totalBytesWritten += iEncodedLength;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new OutOfSpaceException(e);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public void flush() throws IOException {
            if (this.position > 0) {
                doFlush();
            }
        }

        @Override // com.google.protobuf.CodedOutputStream, com.google.protobuf.ByteOutput
        public void write(byte[] value, int offset, int length) throws IOException {
            int i = this.limit;
            int i2 = this.position;
            int i3 = i - i2;
            byte[] bArr = this.buffer;
            if (i3 >= length) {
                System.arraycopy(value, offset, bArr, i2, length);
                this.position += length;
                this.totalBytesWritten += length;
                return;
            }
            int i4 = i - i2;
            System.arraycopy(value, offset, bArr, i2, i4);
            int i5 = offset + i4;
            int i6 = length - i4;
            this.position = this.limit;
            this.totalBytesWritten += i4;
            doFlush();
            if (i6 <= this.limit) {
                System.arraycopy(value, i5, this.buffer, 0, i6);
                this.position = i6;
            } else {
                this.out.write(value, i5, i6);
            }
            this.totalBytesWritten += i6;
        }

        @Override // com.google.protobuf.CodedOutputStream, com.google.protobuf.ByteOutput
        public void writeLazy(byte[] value, int offset, int length) throws IOException {
            write(value, offset, length);
        }

        @Override // com.google.protobuf.CodedOutputStream, com.google.protobuf.ByteOutput
        public void write(ByteBuffer value) throws IOException {
            int iRemaining = value.remaining();
            int i = this.limit;
            int i2 = this.position;
            int i3 = i - i2;
            byte[] bArr = this.buffer;
            if (i3 >= iRemaining) {
                value.get(bArr, i2, iRemaining);
                this.position += iRemaining;
                this.totalBytesWritten += iRemaining;
                return;
            }
            int i4 = i - i2;
            value.get(bArr, i2, i4);
            int i5 = iRemaining - i4;
            this.position = this.limit;
            this.totalBytesWritten += i4;
            doFlush();
            while (true) {
                int i6 = this.limit;
                byte[] bArr2 = this.buffer;
                if (i5 > i6) {
                    value.get(bArr2, 0, i6);
                    this.out.write(this.buffer, 0, this.limit);
                    int i7 = this.limit;
                    i5 -= i7;
                    this.totalBytesWritten += i7;
                } else {
                    value.get(bArr2, 0, i5);
                    this.position = i5;
                    this.totalBytesWritten += i5;
                    return;
                }
            }
        }

        @Override // com.google.protobuf.CodedOutputStream, com.google.protobuf.ByteOutput
        public void writeLazy(ByteBuffer value) throws IOException {
            write(value);
        }

        private void flushIfNotAvailable(int requiredSize) throws IOException {
            if (this.limit - this.position < requiredSize) {
                doFlush();
            }
        }

        private void doFlush() throws IOException {
            this.out.write(this.buffer, 0, this.position);
            this.position = 0;
        }
    }
}
