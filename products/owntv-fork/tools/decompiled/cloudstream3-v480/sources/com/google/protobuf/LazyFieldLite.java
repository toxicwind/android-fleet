package com.google.protobuf;

import java.io.IOException;

/* JADX INFO: loaded from: classes6.dex */
public class LazyFieldLite {
    private volatile boolean corrupted;
    private final MessageLite defaultInstance;
    private ByteString delayedBytes;
    private ExtensionRegistryLite extensionRegistry;
    private volatile ByteString memoizedBytes;
    protected volatile MessageLite value;

    public int hashCode() {
        return 1;
    }

    public LazyFieldLite(ExtensionRegistryLite extensionRegistry, ByteString bytes) {
        checkArguments(extensionRegistry, bytes);
        this.extensionRegistry = extensionRegistry;
        this.delayedBytes = bytes;
        this.defaultInstance = null;
    }

    LazyFieldLite(MessageLite defaultInstance, ExtensionRegistryLite extensionRegistry, ByteString bytes) {
        checkArguments(extensionRegistry, bytes);
        this.defaultInstance = defaultInstance;
        this.extensionRegistry = extensionRegistry;
        this.delayedBytes = bytes;
    }

    public LazyFieldLite() {
        this.defaultInstance = null;
    }

    public static LazyFieldLite fromValue(MessageLite value) {
        LazyFieldLite lazyFieldLite = new LazyFieldLite();
        lazyFieldLite.setValue(value);
        return lazyFieldLite;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LazyFieldLite)) {
            return false;
        }
        LazyFieldLite lazyFieldLite = (LazyFieldLite) o;
        MessageLite messageLite = this.value;
        MessageLite messageLite2 = lazyFieldLite.value;
        if (messageLite == null && messageLite2 == null) {
            return toByteString().equals(lazyFieldLite.toByteString());
        }
        if (messageLite != null && messageLite2 != null) {
            return messageLite.equals(messageLite2);
        }
        if (messageLite != null) {
            return messageLite.equals(lazyFieldLite.getValue(messageLite.getDefaultInstanceForType()));
        }
        return getValue(messageLite2.getDefaultInstanceForType()).equals(messageLite2);
    }

    public boolean containsDefaultInstance() {
        ByteString byteString;
        if (this.memoizedBytes != null && this.memoizedBytes.isEmpty()) {
            return true;
        }
        if (this.value == null && ((byteString = this.delayedBytes) == null || byteString.isEmpty())) {
            return true;
        }
        return this.defaultInstance != null && this.value == this.defaultInstance;
    }

    public void clear() {
        this.delayedBytes = null;
        this.value = null;
        this.memoizedBytes = null;
    }

    public void set(LazyFieldLite other) {
        this.delayedBytes = other.delayedBytes;
        this.value = other.value;
        this.memoizedBytes = other.memoizedBytes;
        ExtensionRegistryLite extensionRegistryLite = other.extensionRegistry;
        if (extensionRegistryLite != null) {
            this.extensionRegistry = extensionRegistryLite;
        }
    }

    @Deprecated
    public MessageLite getValue(MessageLite defaultInstance) {
        ensureInitialized(defaultInstance);
        return this.value;
    }

    public MessageLite getValue() {
        return getValue(this.defaultInstance);
    }

    public MessageLite setValue(MessageLite value) {
        MessageLite messageLite = this.value;
        this.delayedBytes = null;
        this.memoizedBytes = null;
        this.value = value;
        return messageLite;
    }

    private void mergeValue(LazyFieldLite other) {
        if (this.value == null && other.value != null) {
            setValue(getValue(other.value.getDefaultInstanceForType()).toBuilder().mergeFrom(other.value).build());
        } else if (this.value != null && other.value == null) {
            setValue(mergeValueAndBytes(this.value, other.delayedBytes, other.extensionRegistry));
        } else {
            setValue(this.value.toBuilder().mergeFrom(other.value).build());
        }
    }

    public void merge(LazyFieldLite other) {
        ByteString byteString;
        if (other.containsDefaultInstance()) {
            return;
        }
        if (containsDefaultInstance()) {
            set(other);
            return;
        }
        if (this.extensionRegistry == null) {
            this.extensionRegistry = other.extensionRegistry;
        }
        ByteString byteString2 = this.delayedBytes;
        if (byteString2 != null && (byteString = other.delayedBytes) != null && this.extensionRegistry == other.extensionRegistry) {
            this.delayedBytes = byteString2.concat(byteString);
            return;
        }
        if (this.value != null || other.value != null) {
            mergeValue(other);
        } else if (this.defaultInstance == null) {
            this.delayedBytes = this.delayedBytes.concat(other.delayedBytes);
        } else {
            setValue(mergeValueAndBytes(getValue(), other.delayedBytes, other.extensionRegistry));
        }
    }

    public void mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        if (containsDefaultInstance()) {
            setByteString(input.readBytes(), extensionRegistry);
            return;
        }
        if (this.extensionRegistry == null) {
            this.extensionRegistry = extensionRegistry;
        }
        ByteString byteString = this.delayedBytes;
        if (byteString != null) {
            setByteString(byteString.concat(input.readBytes()), this.extensionRegistry);
        } else {
            try {
                setValue(this.value.toBuilder().mergeFrom(input, extensionRegistry).build());
            } catch (InvalidProtocolBufferException unused) {
            }
        }
    }

    private static MessageLite mergeValueAndBytes(MessageLite value, ByteString otherBytes, ExtensionRegistryLite extensionRegistry) {
        try {
            return value.toBuilder().mergeFrom(otherBytes, extensionRegistry).build();
        } catch (InvalidProtocolBufferException unused) {
            return value;
        }
    }

    public void setByteString(ByteString bytes, ExtensionRegistryLite extensionRegistry) {
        checkArguments(extensionRegistry, bytes);
        this.delayedBytes = bytes;
        this.extensionRegistry = extensionRegistry;
        this.value = null;
        this.memoizedBytes = null;
    }

    public int getSerializedSize() {
        ByteString byteString = this.delayedBytes;
        if (byteString != null) {
            return byteString.size();
        }
        if (this.memoizedBytes != null) {
            return this.memoizedBytes.size();
        }
        if (this.value != null) {
            return this.value.getSerializedSize();
        }
        return 0;
    }

    public ByteString toByteString() {
        ByteString byteString = this.delayedBytes;
        if (byteString != null) {
            return byteString;
        }
        if (this.memoizedBytes != null) {
            return this.memoizedBytes;
        }
        synchronized (this) {
            if (this.memoizedBytes != null) {
                return this.memoizedBytes;
            }
            if (this.value == null) {
                this.memoizedBytes = ByteString.EMPTY;
            } else {
                this.memoizedBytes = this.value.toByteString();
            }
            return this.memoizedBytes;
        }
    }

    public int computeSizeNoTag() {
        return CodedOutputStream.computeLengthDelimitedFieldSize(getSerializedSize());
    }

    public int computeSize(final int fieldNumber) {
        return CodedOutputStream.computeTagSize(fieldNumber) + computeSizeNoTag();
    }

    public int computeMessageSetExtensionSize(final int fieldNumber) {
        return (CodedOutputStream.computeTagSize(1) * 2) + CodedOutputStream.computeUInt32Size(2, fieldNumber) + computeSize(3);
    }

    void writeTo(Writer writer, int fieldNumber) throws IOException {
        if (this.memoizedBytes != null) {
            writer.writeBytes(fieldNumber, this.memoizedBytes);
            return;
        }
        ByteString byteString = this.delayedBytes;
        if (byteString != null) {
            writer.writeBytes(fieldNumber, byteString);
        } else if (this.value != null) {
            writer.writeMessage(fieldNumber, this.value);
        } else {
            writer.writeBytes(fieldNumber, ByteString.EMPTY);
        }
    }

    protected void ensureInitialized(MessageLite defaultInstance) {
        if (this.value != null) {
            return;
        }
        synchronized (this) {
            if (this.value != null) {
                return;
            }
            try {
                if (this.delayedBytes != null) {
                    this.value = defaultInstance.getParserForType().parseFrom(this.delayedBytes, this.extensionRegistry);
                    this.memoizedBytes = this.delayedBytes;
                } else {
                    this.value = defaultInstance;
                    this.memoizedBytes = ByteString.EMPTY;
                }
            } catch (InvalidProtocolBufferException unused) {
                this.corrupted = true;
                this.value = defaultInstance;
                this.memoizedBytes = ByteString.EMPTY;
            }
        }
    }

    private static void checkArguments(ExtensionRegistryLite extensionRegistry, ByteString bytes) {
        if (extensionRegistry == null) {
            throw new NullPointerException("found null ExtensionRegistry");
        }
        if (bytes == null) {
            throw new NullPointerException("found null ByteString");
        }
    }

    boolean isCorrupted() {
        return this.corrupted;
    }
}
