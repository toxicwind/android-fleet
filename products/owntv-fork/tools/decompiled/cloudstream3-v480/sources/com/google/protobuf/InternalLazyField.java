package com.google.protobuf;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
class InternalLazyField {
    private volatile ByteString bytes;
    private volatile boolean corrupted;
    private final MessageLite defaultInstance;
    private final ExtensionRegistryLite extensionRegistry;
    protected volatile MessageLite value;

    InternalLazyField(MessageLite defaultInstance, ExtensionRegistryLite extensionRegistry, ByteString bytes) {
        if (defaultInstance == null) {
            throw new IllegalArgumentException("defaultInstance cannot be null");
        }
        if (extensionRegistry == null) {
            throw new IllegalArgumentException("extensionRegistry cannot be null");
        }
        if (bytes == null) {
            throw new IllegalArgumentException("bytes cannot be null");
        }
        this.defaultInstance = defaultInstance;
        this.extensionRegistry = extensionRegistry;
        this.bytes = bytes;
        this.corrupted = false;
        this.value = null;
    }

    InternalLazyField(MessageLite message) {
        if (message == null) {
            throw new IllegalArgumentException("message cannot be null");
        }
        this.value = message;
        this.defaultInstance = message.getDefaultInstanceForType();
        this.extensionRegistry = ExtensionRegistryLite.getEmptyRegistry();
        this.bytes = null;
        this.corrupted = false;
    }

    static InternalLazyField mergeFrom(InternalLazyField lazyField, CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        ByteString bytes = input.readBytes();
        if (lazyField.isEmpty()) {
            return new InternalLazyField(lazyField.defaultInstance, extensionRegistry, bytes);
        }
        if (bytes.isEmpty()) {
            return lazyField;
        }
        if (lazyField.hasBytes()) {
            if (lazyField.extensionRegistry == extensionRegistry) {
                return new InternalLazyField(lazyField.defaultInstance, extensionRegistry, lazyField.bytes.concat(bytes));
            }
            try {
                lazyField.ensureInitialized();
            } catch (InvalidProtocolBufferException e) {
                throw new InvalidProtobufRuntimeException("Cannot merge invalid lazy field from bytes that is absent or with a different extension registry.", e);
            }
        }
        return mergeValueFromBytes(lazyField, bytes, extensionRegistry);
    }

    static InternalLazyField mergeFrom(InternalLazyField self, InternalLazyField other) {
        ExtensionRegistryLite extensionRegistryLite;
        if (self.defaultInstance != other.defaultInstance) {
            throw new IllegalArgumentException("LazyFields with different default instances cannot be merged.");
        }
        if (self.isEmpty()) {
            return other;
        }
        if (other.isEmpty()) {
            return self;
        }
        if (self.hasBytes() && other.hasBytes() && (extensionRegistryLite = self.extensionRegistry) == other.extensionRegistry) {
            return new InternalLazyField(self.defaultInstance, extensionRegistryLite, self.bytes.concat(other.bytes));
        }
        try {
            self.ensureInitialized();
            if (other.value != null) {
                return new InternalLazyField(self.value.toBuilder().mergeFrom(other.value).build());
            }
            return mergeValueFromBytes(self, other.bytes, other.extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
            throw new InvalidProtobufRuntimeException("Cannot merge invalid lazy field from bytes that is absent or with a different extension registry.", e);
        }
    }

    private static InternalLazyField mergeValueFromBytes(InternalLazyField self, ByteString inputBytes, ExtensionRegistryLite extensionRegistry) {
        try {
            return new InternalLazyField(self.value.toBuilder().mergeFrom(inputBytes, extensionRegistry).build());
        } catch (InvalidProtocolBufferException e) {
            throw new InvalidProtobufRuntimeException("Cannot merge lazy field from invalid bytes with a different extension registry.", e);
        }
    }

    private boolean hasBytes() {
        return this.bytes != null;
    }

    private boolean isEmpty() {
        return this.bytes != null ? this.bytes.isEmpty() : this.value.equals(this.defaultInstance);
    }

    private void ensureInitialized() throws InvalidProtocolBufferException {
        if (this.value != null) {
            return;
        }
        synchronized (this) {
            if (this.corrupted) {
                throw new InvalidProtocolBufferException("Repeat access to corrupted lazy field");
            }
            try {
                this.value = this.defaultInstance.getParserForType().parseFrom(this.bytes, this.extensionRegistry);
            } catch (InvalidProtocolBufferException e) {
                this.corrupted = true;
                throw e;
            }
        }
    }

    MessageLite getValue() {
        try {
            ensureInitialized();
            return this.value;
        } catch (InvalidProtocolBufferException e) {
            if (ExtensionRegistryLite.lazyExtensionEnabled()) {
                throw new InvalidProtobufRuntimeException(e);
            }
            return this.defaultInstance;
        }
    }

    int getSerializedSize() {
        return this.bytes != null ? this.bytes.size() : this.value.getSerializedSize();
    }

    ByteString toByteString() {
        if (this.bytes != null) {
            return this.bytes;
        }
        synchronized (this) {
            if (this.bytes != null) {
                return this.bytes;
            }
            this.bytes = this.value.toByteString();
            return this.bytes;
        }
    }

    int computeSizeNoTag() {
        return CodedOutputStream.computeLengthDelimitedFieldSize(getSerializedSize());
    }

    int computeSize(final int fieldNumber) {
        return CodedOutputStream.computeTagSize(fieldNumber) + computeSizeNoTag();
    }

    int computeMessageSetExtensionSize(final int fieldNumber) {
        return (CodedOutputStream.computeTagSize(1) * 2) + CodedOutputStream.computeUInt32Size(2, fieldNumber) + computeSize(3);
    }

    void writeTo(Writer writer, int fieldNumber) throws IOException {
        if (this.bytes != null) {
            writer.writeBytes(fieldNumber, this.bytes);
        } else {
            writer.writeMessage(fieldNumber, this.value);
        }
    }

    public int hashCode() {
        return getValue().hashCode();
    }

    public boolean equals(Object obj) {
        return getValue().equals(obj);
    }

    public String toString() {
        return getValue().toString();
    }

    static class LazyEntry<K> implements Map.Entry<K, Object> {
        private final Map.Entry<K, InternalLazyField> entry;

        private LazyEntry(Map.Entry<K, InternalLazyField> entry) {
            this.entry = entry;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.entry.getKey();
        }

        @Override // java.util.Map.Entry
        public Object getValue() {
            InternalLazyField value = this.entry.getValue();
            if (value == null) {
                return null;
            }
            return value.getValue();
        }

        public InternalLazyField getField() {
            return this.entry.getValue();
        }

        @Override // java.util.Map.Entry
        public Object setValue(Object value) {
            if (!(value instanceof MessageLite)) {
                throw new IllegalArgumentException("Lazy field only supports MessageLite values.");
            }
            MessageLite messageLite = this.entry.getValue().value;
            this.entry.setValue(new InternalLazyField((MessageLite) value));
            return messageLite;
        }
    }

    static class LazyIterator<K> implements Iterator<Map.Entry<K, Object>> {
        private Iterator<Map.Entry<K, Object>> iterator;

        public LazyIterator(Iterator<Map.Entry<K, Object>> iterator) {
            this.iterator = iterator;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override // java.util.Iterator
        public Map.Entry<K, Object> next() {
            Map.Entry<K, Object> next = this.iterator.next();
            return next.getValue() instanceof InternalLazyField ? new LazyEntry(next) : next;
        }

        @Override // java.util.Iterator
        public void remove() {
            this.iterator.remove();
        }
    }
}
