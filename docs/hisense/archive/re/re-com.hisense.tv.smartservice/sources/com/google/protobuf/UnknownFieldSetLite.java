package com.google.protobuf;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class UnknownFieldSetLite {
    public static final UnknownFieldSetLite DEFAULT_INSTANCE = new UnknownFieldSetLite(0, new int[0], new Object[0], false);
    public static final int MIN_CAPACITY = 8;
    public int count;
    public boolean isMutable;
    public int memoizedSerializedSize;
    public Object[] objects;
    public int[] tags;

    public UnknownFieldSetLite() {
        this(0, new int[8], new Object[8], true);
    }

    private void ensureCapacity() {
        int i = this.count;
        if (i == this.tags.length) {
            int i2 = this.count + (i < 4 ? 8 : i >> 1);
            this.tags = Arrays.copyOf(this.tags, i2);
            this.objects = Arrays.copyOf(this.objects, i2);
        }
    }

    public static UnknownFieldSetLite getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static int hashCode(int[] iArr, int i) {
        int i2 = 17;
        for (int i3 = 0; i3 < i; i3++) {
            i2 = (i2 * 31) + iArr[i3];
        }
        return i2;
    }

    private UnknownFieldSetLite mergeFrom(CodedInputStream codedInputStream) {
        int tag;
        do {
            tag = codedInputStream.readTag();
            if (tag == 0) {
                break;
            }
        } while (mergeFieldFrom(tag, codedInputStream));
        return this;
    }

    public static UnknownFieldSetLite mutableCopyOf(UnknownFieldSetLite unknownFieldSetLite, UnknownFieldSetLite unknownFieldSetLite2) {
        int i = unknownFieldSetLite.count + unknownFieldSetLite2.count;
        int[] iArrCopyOf = Arrays.copyOf(unknownFieldSetLite.tags, i);
        System.arraycopy(unknownFieldSetLite2.tags, 0, iArrCopyOf, unknownFieldSetLite.count, unknownFieldSetLite2.count);
        Object[] objArrCopyOf = Arrays.copyOf(unknownFieldSetLite.objects, i);
        System.arraycopy(unknownFieldSetLite2.objects, 0, objArrCopyOf, unknownFieldSetLite.count, unknownFieldSetLite2.count);
        return new UnknownFieldSetLite(i, iArrCopyOf, objArrCopyOf, true);
    }

    public static UnknownFieldSetLite newInstance() {
        return new UnknownFieldSetLite();
    }

    public static boolean objectsEquals(Object[] objArr, Object[] objArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (!objArr[i2].equals(objArr2[i2])) {
                return false;
            }
        }
        return true;
    }

    public static boolean tagsEquals(int[] iArr, int[] iArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static void writeField(int i, Object obj, Writer writer) {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 == 0) {
            writer.writeInt64(i2, ((Long) obj).longValue());
            return;
        }
        if (i3 == 1) {
            writer.writeFixed64(i2, ((Long) obj).longValue());
            return;
        }
        if (i3 == 2) {
            writer.writeBytes(i2, (ByteString) obj);
            return;
        }
        if (i3 != 3) {
            if (i3 != 5) {
                throw new RuntimeException(InvalidProtocolBufferException.invalidWireType());
            }
            writer.writeFixed32(i2, ((Integer) obj).intValue());
        } else if (writer.fieldOrder() == Writer.FieldOrder.ASCENDING) {
            writer.writeStartGroup(i2);
            ((UnknownFieldSetLite) obj).writeTo(writer);
            writer.writeEndGroup(i2);
        } else {
            writer.writeEndGroup(i2);
            ((UnknownFieldSetLite) obj).writeTo(writer);
            writer.writeStartGroup(i2);
        }
    }

    public void checkMutable() {
        if (!this.isMutable) {
            throw new UnsupportedOperationException();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UnknownFieldSetLite)) {
            return false;
        }
        UnknownFieldSetLite unknownFieldSetLite = (UnknownFieldSetLite) obj;
        int i = this.count;
        return i == unknownFieldSetLite.count && tagsEquals(this.tags, unknownFieldSetLite.tags, i) && objectsEquals(this.objects, unknownFieldSetLite.objects, this.count);
    }

    public int getSerializedSize() {
        int iComputeUInt64Size;
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int serializedSize = 0;
        for (int i2 = 0; i2 < this.count; i2++) {
            int i3 = this.tags[i2];
            int i4 = i3 >>> 3;
            int i5 = i3 & 7;
            if (i5 == 0) {
                iComputeUInt64Size = CodedOutputStream.computeUInt64Size(i4, ((Long) this.objects[i2]).longValue());
            } else if (i5 == 1) {
                iComputeUInt64Size = CodedOutputStream.computeFixed64Size(i4, ((Long) this.objects[i2]).longValue());
            } else if (i5 != 2) {
                if (i5 == 3) {
                    serializedSize = ((UnknownFieldSetLite) this.objects[i2]).getSerializedSize() + (CodedOutputStream.computeTagSize(i4) * 2) + serializedSize;
                } else {
                    if (i5 != 5) {
                        throw new IllegalStateException(InvalidProtocolBufferException.invalidWireType());
                    }
                    iComputeUInt64Size = CodedOutputStream.computeFixed32Size(i4, ((Integer) this.objects[i2]).intValue());
                }
            } else {
                iComputeUInt64Size = CodedOutputStream.computeBytesSize(i4, (ByteString) this.objects[i2]);
            }
            serializedSize = iComputeUInt64Size + serializedSize;
        }
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public int getSerializedSizeAsMessageSet() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int iComputeRawMessageSetExtensionSize = 0;
        for (int i2 = 0; i2 < this.count; i2++) {
            iComputeRawMessageSetExtensionSize += CodedOutputStream.computeRawMessageSetExtensionSize(this.tags[i2] >>> 3, (ByteString) this.objects[i2]);
        }
        this.memoizedSerializedSize = iComputeRawMessageSetExtensionSize;
        return iComputeRawMessageSetExtensionSize;
    }

    public void makeImmutable() {
        this.isMutable = false;
    }

    public boolean mergeFieldFrom(int i, CodedInputStream codedInputStream) throws InvalidProtocolBufferException.InvalidWireTypeException {
        checkMutable();
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 == 0) {
            storeField(i, Long.valueOf(codedInputStream.readInt64()));
            return true;
        }
        if (i3 == 1) {
            storeField(i, Long.valueOf(codedInputStream.readFixed64()));
            return true;
        }
        if (i3 == 2) {
            storeField(i, codedInputStream.readBytes());
            return true;
        }
        if (i3 == 3) {
            UnknownFieldSetLite unknownFieldSetLite = new UnknownFieldSetLite();
            unknownFieldSetLite.mergeFrom(codedInputStream);
            codedInputStream.checkLastTagWas((i2 << 3) | 4);
            storeField(i, unknownFieldSetLite);
            return true;
        }
        if (i3 == 4) {
            return false;
        }
        if (i3 != 5) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        storeField(i, Integer.valueOf(codedInputStream.readFixed32()));
        return true;
    }

    public UnknownFieldSetLite mergeLengthDelimitedField(int i, ByteString byteString) {
        checkMutable();
        if (i == 0) {
            throw new IllegalArgumentException("Zero is not a valid field number.");
        }
        storeField((i << 3) | 2, byteString);
        return this;
    }

    public UnknownFieldSetLite mergeVarintField(int i, int i2) {
        checkMutable();
        if (i == 0) {
            throw new IllegalArgumentException("Zero is not a valid field number.");
        }
        storeField((i << 3) | 0, Long.valueOf(i2));
        return this;
    }

    public final void printWithIndent(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.count; i2++) {
            MessageLiteToString.printField(sb, i, String.valueOf(this.tags[i2] >>> 3), this.objects[i2]);
        }
    }

    public void storeField(int i, Object obj) {
        checkMutable();
        ensureCapacity();
        int[] iArr = this.tags;
        int i2 = this.count;
        iArr[i2] = i;
        this.objects[i2] = obj;
        this.count = i2 + 1;
    }

    public void writeAsMessageSetTo(CodedOutputStream codedOutputStream) {
        for (int i = 0; i < this.count; i++) {
            codedOutputStream.writeRawMessageSetExtension(this.tags[i] >>> 3, (ByteString) this.objects[i]);
        }
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws InvalidProtocolBufferException.InvalidWireTypeException {
        for (int i = 0; i < this.count; i++) {
            int i2 = this.tags[i];
            int i3 = i2 >>> 3;
            int i4 = i2 & 7;
            if (i4 == 0) {
                codedOutputStream.writeUInt64(i3, ((Long) this.objects[i]).longValue());
            } else if (i4 == 1) {
                codedOutputStream.writeFixed64(i3, ((Long) this.objects[i]).longValue());
            } else if (i4 == 2) {
                codedOutputStream.writeBytes(i3, (ByteString) this.objects[i]);
            } else if (i4 == 3) {
                codedOutputStream.writeTag(i3, 3);
                ((UnknownFieldSetLite) this.objects[i]).writeTo(codedOutputStream);
                codedOutputStream.writeTag(i3, 4);
            } else {
                if (i4 != 5) {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
                codedOutputStream.writeFixed32(i3, ((Integer) this.objects[i]).intValue());
            }
        }
    }

    public UnknownFieldSetLite(int i, int[] iArr, Object[] objArr, boolean z) {
        this.memoizedSerializedSize = -1;
        this.count = i;
        this.tags = iArr;
        this.objects = objArr;
        this.isMutable = z;
    }

    public static int hashCode(Object[] objArr, int i) {
        int iHashCode = 17;
        for (int i2 = 0; i2 < i; i2++) {
            iHashCode = (iHashCode * 31) + objArr[i2].hashCode();
        }
        return iHashCode;
    }

    public int hashCode() {
        int i = this.count;
        return hashCode(this.objects, this.count) + ((hashCode(this.tags, i) + ((527 + i) * 31)) * 31);
    }

    public void writeAsMessageSetTo(Writer writer) {
        if (writer.fieldOrder() == Writer.FieldOrder.DESCENDING) {
            for (int i = this.count - 1; i >= 0; i--) {
                writer.writeMessageSetItem(this.tags[i] >>> 3, this.objects[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.count; i2++) {
            writer.writeMessageSetItem(this.tags[i2] >>> 3, this.objects[i2]);
        }
    }

    public void writeTo(Writer writer) {
        if (this.count == 0) {
            return;
        }
        if (writer.fieldOrder() == Writer.FieldOrder.ASCENDING) {
            for (int i = 0; i < this.count; i++) {
                writeField(this.tags[i], this.objects[i], writer);
            }
            return;
        }
        for (int i2 = this.count - 1; i2 >= 0; i2--) {
            writeField(this.tags[i2], this.objects[i2], writer);
        }
    }
}
