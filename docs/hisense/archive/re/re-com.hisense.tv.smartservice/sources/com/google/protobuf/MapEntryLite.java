package com.google.protobuf;

import java.util.AbstractMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class MapEntryLite<K, V> {
    public static final int KEY_FIELD_NUMBER = 1;
    public static final int VALUE_FIELD_NUMBER = 2;
    public final K key;
    public final Metadata<K, V> metadata;
    public final V value;

    /* JADX INFO: renamed from: com.google.protobuf.MapEntryLite$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType = new int[WireFormat.FieldType.values().length];

        static {
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.GROUP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    static class Metadata<K, V> {
        public final K defaultKey;
        public final V defaultValue;
        public final WireFormat.FieldType keyType;
        public final WireFormat.FieldType valueType;

        public Metadata(WireFormat.FieldType fieldType, K k, WireFormat.FieldType fieldType2, V v) {
            this.keyType = fieldType;
            this.defaultKey = k;
            this.valueType = fieldType2;
            this.defaultValue = v;
        }
    }

    public MapEntryLite(WireFormat.FieldType fieldType, K k, WireFormat.FieldType fieldType2, V v) {
        this.metadata = new Metadata<>(fieldType, k, fieldType2, v);
        this.key = k;
        this.value = v;
    }

    public static <K, V> int computeSerializedSize(Metadata<K, V> metadata, K k, V v) {
        return FieldSet.computeElementSize(metadata.valueType, 2, v) + FieldSet.computeElementSize(metadata.keyType, 1, k);
    }

    public static <K, V> MapEntryLite<K, V> newDefaultInstance(WireFormat.FieldType fieldType, K k, WireFormat.FieldType fieldType2, V v) {
        return new MapEntryLite<>(fieldType, k, fieldType2, v);
    }

    public static <T> T parseField(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, WireFormat.FieldType fieldType, T t) {
        int iOrdinal = fieldType.ordinal();
        if (iOrdinal == 9) {
            throw new RuntimeException("Groups are not allowed in maps.");
        }
        if (iOrdinal != 10) {
            return iOrdinal != 13 ? (T) FieldSet.readPrimitiveField(codedInputStream, fieldType, true) : (T) Integer.valueOf(codedInputStream.readEnum());
        }
        MessageLite.Builder builder = ((MessageLite) t).toBuilder();
        codedInputStream.readMessage(builder, extensionRegistryLite);
        return (T) builder.buildPartial();
    }

    public static <K, V> void writeTo(CodedOutputStream codedOutputStream, Metadata<K, V> metadata, K k, V v) {
        FieldSet.writeElement(codedOutputStream, metadata.keyType, 1, k);
        FieldSet.writeElement(codedOutputStream, metadata.valueType, 2, v);
    }

    public int computeMessageSize(int i, K k, V v) {
        return CodedOutputStream.computeLengthDelimitedFieldSize(computeSerializedSize(this.metadata, k, v)) + CodedOutputStream.computeTagSize(i);
    }

    public K getKey() {
        return this.key;
    }

    public Metadata<K, V> getMetadata() {
        return this.metadata;
    }

    public V getValue() {
        return this.value;
    }

    public Map.Entry<K, V> parseEntry(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return parseEntry(byteString.newCodedInput(), this.metadata, extensionRegistryLite);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void parseInto(MapFieldLite<K, V> mapFieldLite, CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        int iPushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
        Metadata<K, V> metadata = this.metadata;
        Object field = metadata.defaultKey;
        Object field2 = metadata.defaultValue;
        while (true) {
            int tag = codedInputStream.readTag();
            if (tag == 0) {
                break;
            }
            if (tag == (this.metadata.keyType.getWireType() | 8)) {
                field = parseField(codedInputStream, extensionRegistryLite, this.metadata.keyType, field);
            } else if (tag == (this.metadata.valueType.getWireType() | 16)) {
                field2 = parseField(codedInputStream, extensionRegistryLite, this.metadata.valueType, field2);
            } else if (!codedInputStream.skipField(tag)) {
                break;
            }
        }
        codedInputStream.checkLastTagWas(0);
        codedInputStream.popLimit(iPushLimit);
        mapFieldLite.put(field, field2);
    }

    public void serializeTo(CodedOutputStream codedOutputStream, int i, K k, V v) {
        codedOutputStream.writeTag(i, 2);
        codedOutputStream.writeUInt32NoTag(computeSerializedSize(this.metadata, k, v));
        writeTo(codedOutputStream, this.metadata, k, v);
    }

    public static <K, V> Map.Entry<K, V> parseEntry(CodedInputStream codedInputStream, Metadata<K, V> metadata, ExtensionRegistryLite extensionRegistryLite) {
        Object field = metadata.defaultKey;
        Object field2 = metadata.defaultValue;
        while (true) {
            int tag = codedInputStream.readTag();
            if (tag == 0) {
                break;
            }
            if (tag == (metadata.keyType.getWireType() | 8)) {
                field = parseField(codedInputStream, extensionRegistryLite, metadata.keyType, field);
            } else if (tag == (metadata.valueType.getWireType() | 16)) {
                field2 = parseField(codedInputStream, extensionRegistryLite, metadata.valueType, field2);
            } else if (!codedInputStream.skipField(tag)) {
                break;
            }
        }
        return new AbstractMap.SimpleImmutableEntry(field, field2);
    }

    public MapEntryLite(Metadata<K, V> metadata, K k, V v) {
        this.metadata = metadata;
        this.key = k;
        this.value = v;
    }
}
