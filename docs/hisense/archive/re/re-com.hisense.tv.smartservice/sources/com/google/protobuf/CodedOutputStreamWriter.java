package com.google.protobuf;

import b.a.a.a.a;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class CodedOutputStreamWriter implements Writer {
    public final CodedOutputStream output;

    /* JADX INFO: renamed from: com.google.protobuf.CodedOutputStreamWriter$1, reason: invalid class name */
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
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    public CodedOutputStreamWriter(CodedOutputStream codedOutputStream) {
        Internal.checkNotNull(codedOutputStream, "output");
        this.output = codedOutputStream;
        this.output.wrapper = this;
    }

    public static CodedOutputStreamWriter forCodedOutput(CodedOutputStream codedOutputStream) {
        CodedOutputStreamWriter codedOutputStreamWriter = codedOutputStream.wrapper;
        return codedOutputStreamWriter != null ? codedOutputStreamWriter : new CodedOutputStreamWriter(codedOutputStream);
    }

    private <V> void writeDeterministicBooleanMapEntry(int i, boolean z, V v, MapEntryLite.Metadata<Boolean, V> metadata) {
        this.output.writeTag(i, 2);
        this.output.writeUInt32NoTag(MapEntryLite.computeSerializedSize(metadata, Boolean.valueOf(z), v));
        CodedOutputStream codedOutputStream = this.output;
        FieldSet.writeElement(codedOutputStream, metadata.keyType, 1, Boolean.valueOf(z));
        FieldSet.writeElement(codedOutputStream, metadata.valueType, 2, v);
    }

    private <V> void writeDeterministicIntegerMap(int i, MapEntryLite.Metadata<Integer, V> metadata, Map<Integer, V> map) {
        int[] iArr = new int[map.size()];
        Iterator<Integer> it = map.keySet().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            iArr[i2] = it.next().intValue();
            i2++;
        }
        Arrays.sort(iArr);
        for (int i3 : iArr) {
            V v = map.get(Integer.valueOf(i3));
            this.output.writeTag(i, 2);
            this.output.writeUInt32NoTag(MapEntryLite.computeSerializedSize(metadata, Integer.valueOf(i3), v));
            CodedOutputStream codedOutputStream = this.output;
            FieldSet.writeElement(codedOutputStream, metadata.keyType, 1, Integer.valueOf(i3));
            FieldSet.writeElement(codedOutputStream, metadata.valueType, 2, v);
        }
    }

    private <V> void writeDeterministicLongMap(int i, MapEntryLite.Metadata<Long, V> metadata, Map<Long, V> map) {
        long[] jArr = new long[map.size()];
        Iterator<Long> it = map.keySet().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            jArr[i2] = it.next().longValue();
            i2++;
        }
        Arrays.sort(jArr);
        for (long j : jArr) {
            V v = map.get(Long.valueOf(j));
            this.output.writeTag(i, 2);
            this.output.writeUInt32NoTag(MapEntryLite.computeSerializedSize(metadata, Long.valueOf(j), v));
            CodedOutputStream codedOutputStream = this.output;
            FieldSet.writeElement(codedOutputStream, metadata.keyType, 1, Long.valueOf(j));
            FieldSet.writeElement(codedOutputStream, metadata.valueType, 2, v);
        }
    }

    private <K, V> void writeDeterministicMap(int i, MapEntryLite.Metadata<K, V> metadata, Map<K, V> map) {
        switch (metadata.keyType.ordinal()) {
            case 2:
            case 3:
            case 5:
            case 15:
            case 17:
                writeDeterministicLongMap(i, metadata, map);
                return;
            case 4:
            case 6:
            case 12:
            case 14:
            case 16:
                writeDeterministicIntegerMap(i, metadata, map);
                return;
            case 7:
                V v = map.get(Boolean.FALSE);
                if (v != null) {
                    writeDeterministicBooleanMapEntry(i, false, v, metadata);
                }
                V v2 = map.get(Boolean.TRUE);
                if (v2 != null) {
                    writeDeterministicBooleanMapEntry(i, true, v2, metadata);
                    return;
                }
                return;
            case 8:
                writeDeterministicStringMap(i, metadata, map);
                return;
            case 9:
            case 10:
            case 11:
            case 13:
            default:
                StringBuilder sbA = a.a("does not support key type: ");
                sbA.append(metadata.keyType);
                throw new IllegalArgumentException(sbA.toString());
        }
    }

    private <V> void writeDeterministicStringMap(int i, MapEntryLite.Metadata<String, V> metadata, Map<String, V> map) {
        String[] strArr = new String[map.size()];
        Iterator<String> it = map.keySet().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            strArr[i2] = it.next();
            i2++;
        }
        Arrays.sort(strArr);
        for (String str : strArr) {
            V v = map.get(str);
            this.output.writeTag(i, 2);
            this.output.writeUInt32NoTag(MapEntryLite.computeSerializedSize(metadata, str, v));
            CodedOutputStream codedOutputStream = this.output;
            FieldSet.writeElement(codedOutputStream, metadata.keyType, 1, str);
            FieldSet.writeElement(codedOutputStream, metadata.valueType, 2, v);
        }
    }

    private void writeLazyString(int i, Object obj) {
        if (obj instanceof String) {
            this.output.writeString(i, (String) obj);
        } else {
            this.output.writeBytes(i, (ByteString) obj);
        }
    }

    @Override // com.google.protobuf.Writer
    public Writer.FieldOrder fieldOrder() {
        return Writer.FieldOrder.ASCENDING;
    }

    public int getTotalBytesWritten() {
        return this.output.getTotalBytesWritten();
    }

    @Override // com.google.protobuf.Writer
    public void writeBool(int i, boolean z) {
        this.output.writeBool(i, z);
    }

    @Override // com.google.protobuf.Writer
    public void writeBoolList(int i, List<Boolean> list, boolean z) {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.output.writeBool(i, list.get(i2).booleanValue());
                i2++;
            }
            return;
        }
        this.output.writeTag(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            CodedOutputStream.computeBoolSizeNoTag(list.get(i4).booleanValue());
            i3++;
        }
        this.output.writeUInt32NoTag(i3);
        while (i2 < list.size()) {
            this.output.writeBoolNoTag(list.get(i2).booleanValue());
            i2++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeBytes(int i, ByteString byteString) {
        this.output.writeBytes(i, byteString);
    }

    @Override // com.google.protobuf.Writer
    public void writeBytesList(int i, List<ByteString> list) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.output.writeBytes(i, list.get(i2));
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeDouble(int i, double d2) {
        this.output.writeDouble(i, d2);
    }

    @Override // com.google.protobuf.Writer
    public void writeDoubleList(int i, List<Double> list, boolean z) {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.output.writeDouble(i, list.get(i2).doubleValue());
                i2++;
            }
            return;
        }
        this.output.writeTag(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            CodedOutputStream.computeDoubleSizeNoTag(list.get(i4).doubleValue());
            i3 += 8;
        }
        this.output.writeUInt32NoTag(i3);
        while (i2 < list.size()) {
            this.output.writeDoubleNoTag(list.get(i2).doubleValue());
            i2++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeEndGroup(int i) {
        this.output.writeTag(i, 4);
    }

    @Override // com.google.protobuf.Writer
    public void writeEnum(int i, int i2) {
        this.output.writeEnum(i, i2);
    }

    @Override // com.google.protobuf.Writer
    public void writeEnumList(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.output.writeEnum(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.output.writeTag(i, 2);
        int iComputeInt32SizeNoTag = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iComputeInt32SizeNoTag += CodedOutputStream.computeInt32SizeNoTag(list.get(i3).intValue());
        }
        this.output.writeUInt32NoTag(iComputeInt32SizeNoTag);
        while (i2 < list.size()) {
            this.output.writeEnumNoTag(list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeFixed32(int i, int i2) {
        this.output.writeFixed32(i, i2);
    }

    @Override // com.google.protobuf.Writer
    public void writeFixed32List(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.output.writeFixed32(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.output.writeTag(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            CodedOutputStream.computeFixed32SizeNoTag(list.get(i4).intValue());
            i3 += 4;
        }
        this.output.writeUInt32NoTag(i3);
        while (i2 < list.size()) {
            this.output.writeFixed32NoTag(list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeFixed64(int i, long j) {
        this.output.writeFixed64(i, j);
    }

    @Override // com.google.protobuf.Writer
    public void writeFixed64List(int i, List<Long> list, boolean z) {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.output.writeFixed64(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        this.output.writeTag(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            CodedOutputStream.computeFixed64SizeNoTag(list.get(i4).longValue());
            i3 += 8;
        }
        this.output.writeUInt32NoTag(i3);
        while (i2 < list.size()) {
            this.output.writeFixed64NoTag(list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeFloat(int i, float f2) {
        this.output.writeFloat(i, f2);
    }

    @Override // com.google.protobuf.Writer
    public void writeFloatList(int i, List<Float> list, boolean z) {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.output.writeFloat(i, list.get(i2).floatValue());
                i2++;
            }
            return;
        }
        this.output.writeTag(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            CodedOutputStream.computeFloatSizeNoTag(list.get(i4).floatValue());
            i3 += 4;
        }
        this.output.writeUInt32NoTag(i3);
        while (i2 < list.size()) {
            this.output.writeFloatNoTag(list.get(i2).floatValue());
            i2++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeGroup(int i, Object obj) {
        this.output.writeGroup(i, (MessageLite) obj);
    }

    @Override // com.google.protobuf.Writer
    public void writeGroupList(int i, List<?> list) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            writeGroup(i, list.get(i2));
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeInt32(int i, int i2) {
        this.output.writeInt32(i, i2);
    }

    @Override // com.google.protobuf.Writer
    public void writeInt32List(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.output.writeInt32(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.output.writeTag(i, 2);
        int iComputeInt32SizeNoTag = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iComputeInt32SizeNoTag += CodedOutputStream.computeInt32SizeNoTag(list.get(i3).intValue());
        }
        this.output.writeUInt32NoTag(iComputeInt32SizeNoTag);
        while (i2 < list.size()) {
            this.output.writeInt32NoTag(list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeInt64(int i, long j) {
        this.output.writeInt64(i, j);
    }

    @Override // com.google.protobuf.Writer
    public void writeInt64List(int i, List<Long> list, boolean z) {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.output.writeInt64(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        this.output.writeTag(i, 2);
        int iComputeUInt64SizeNoTag = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iComputeUInt64SizeNoTag += CodedOutputStream.computeUInt64SizeNoTag(list.get(i3).longValue());
        }
        this.output.writeUInt32NoTag(iComputeUInt64SizeNoTag);
        while (i2 < list.size()) {
            this.output.writeInt64NoTag(list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.protobuf.Writer
    public <K, V> void writeMap(int i, MapEntryLite.Metadata<K, V> metadata, Map<K, V> map) {
        if (this.output.isSerializationDeterministic()) {
            writeDeterministicMap(i, metadata, map);
            return;
        }
        for (Map.Entry<K, V> entry : map.entrySet()) {
            this.output.writeTag(i, 2);
            this.output.writeUInt32NoTag(MapEntryLite.computeSerializedSize(metadata, entry.getKey(), entry.getValue()));
            CodedOutputStream codedOutputStream = this.output;
            K key = entry.getKey();
            V value = entry.getValue();
            FieldSet.writeElement(codedOutputStream, metadata.keyType, 1, key);
            FieldSet.writeElement(codedOutputStream, metadata.valueType, 2, value);
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeMessage(int i, Object obj) {
        this.output.writeMessage(i, (MessageLite) obj);
    }

    @Override // com.google.protobuf.Writer
    public void writeMessageList(int i, List<?> list) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            writeMessage(i, list.get(i2));
        }
    }

    @Override // com.google.protobuf.Writer
    public final void writeMessageSetItem(int i, Object obj) {
        if (obj instanceof ByteString) {
            this.output.writeRawMessageSetExtension(i, (ByteString) obj);
        } else {
            this.output.writeMessageSetExtension(i, (MessageLite) obj);
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeSFixed32(int i, int i2) {
        this.output.writeSFixed32(i, i2);
    }

    @Override // com.google.protobuf.Writer
    public void writeSFixed32List(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.output.writeSFixed32(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.output.writeTag(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            CodedOutputStream.computeSFixed32SizeNoTag(list.get(i4).intValue());
            i3 += 4;
        }
        this.output.writeUInt32NoTag(i3);
        while (i2 < list.size()) {
            this.output.writeSFixed32NoTag(list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeSFixed64(int i, long j) {
        this.output.writeSFixed64(i, j);
    }

    @Override // com.google.protobuf.Writer
    public void writeSFixed64List(int i, List<Long> list, boolean z) {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.output.writeSFixed64(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        this.output.writeTag(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            CodedOutputStream.computeSFixed64SizeNoTag(list.get(i4).longValue());
            i3 += 8;
        }
        this.output.writeUInt32NoTag(i3);
        while (i2 < list.size()) {
            this.output.writeSFixed64NoTag(list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeSInt32(int i, int i2) {
        this.output.writeSInt32(i, i2);
    }

    @Override // com.google.protobuf.Writer
    public void writeSInt32List(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.output.writeSInt32(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.output.writeTag(i, 2);
        int iComputeSInt32SizeNoTag = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iComputeSInt32SizeNoTag += CodedOutputStream.computeSInt32SizeNoTag(list.get(i3).intValue());
        }
        this.output.writeUInt32NoTag(iComputeSInt32SizeNoTag);
        while (i2 < list.size()) {
            this.output.writeSInt32NoTag(list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeSInt64(int i, long j) {
        this.output.writeSInt64(i, j);
    }

    @Override // com.google.protobuf.Writer
    public void writeSInt64List(int i, List<Long> list, boolean z) {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.output.writeSInt64(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        this.output.writeTag(i, 2);
        int iComputeSInt64SizeNoTag = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iComputeSInt64SizeNoTag += CodedOutputStream.computeSInt64SizeNoTag(list.get(i3).longValue());
        }
        this.output.writeUInt32NoTag(iComputeSInt64SizeNoTag);
        while (i2 < list.size()) {
            this.output.writeSInt64NoTag(list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeStartGroup(int i) {
        this.output.writeTag(i, 3);
    }

    @Override // com.google.protobuf.Writer
    public void writeString(int i, String str) {
        this.output.writeString(i, str);
    }

    @Override // com.google.protobuf.Writer
    public void writeStringList(int i, List<String> list) {
        int i2 = 0;
        if (!(list instanceof LazyStringList)) {
            while (i2 < list.size()) {
                this.output.writeString(i, list.get(i2));
                i2++;
            }
        } else {
            LazyStringList lazyStringList = (LazyStringList) list;
            while (i2 < list.size()) {
                writeLazyString(i, lazyStringList.getRaw(i2));
                i2++;
            }
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeUInt32(int i, int i2) {
        this.output.writeUInt32(i, i2);
    }

    @Override // com.google.protobuf.Writer
    public void writeUInt32List(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.output.writeUInt32(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.output.writeTag(i, 2);
        int iComputeUInt32SizeNoTag = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iComputeUInt32SizeNoTag += CodedOutputStream.computeUInt32SizeNoTag(list.get(i3).intValue());
        }
        this.output.writeUInt32NoTag(iComputeUInt32SizeNoTag);
        while (i2 < list.size()) {
            this.output.writeUInt32NoTag(list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeUInt64(int i, long j) {
        this.output.writeUInt64(i, j);
    }

    @Override // com.google.protobuf.Writer
    public void writeUInt64List(int i, List<Long> list, boolean z) {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.output.writeUInt64(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        this.output.writeTag(i, 2);
        int iComputeUInt64SizeNoTag = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iComputeUInt64SizeNoTag += CodedOutputStream.computeUInt64SizeNoTag(list.get(i3).longValue());
        }
        this.output.writeUInt32NoTag(iComputeUInt64SizeNoTag);
        while (i2 < list.size()) {
            this.output.writeUInt64NoTag(list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeGroup(int i, Object obj, Schema schema) {
        this.output.writeGroup(i, (MessageLite) obj, schema);
    }

    @Override // com.google.protobuf.Writer
    public void writeMessage(int i, Object obj, Schema schema) {
        this.output.writeMessage(i, (MessageLite) obj, schema);
    }

    @Override // com.google.protobuf.Writer
    public void writeGroupList(int i, List<?> list, Schema schema) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            writeGroup(i, list.get(i2), schema);
        }
    }

    @Override // com.google.protobuf.Writer
    public void writeMessageList(int i, List<?> list, Schema schema) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            writeMessage(i, list.get(i2), schema);
        }
    }
}
