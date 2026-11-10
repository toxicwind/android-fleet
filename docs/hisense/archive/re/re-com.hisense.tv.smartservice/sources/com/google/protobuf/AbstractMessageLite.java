package com.google.protobuf;

import b.a.a.a.a;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractMessageLite.Builder;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractMessageLite<MessageType extends AbstractMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> implements MessageLite {
    public int memoizedHashCode = 0;

    public static abstract class Builder<MessageType extends AbstractMessageLite<MessageType, BuilderType>, BuilderType extends Builder<MessageType, BuilderType>> implements MessageLite.Builder {
        @Deprecated
        public static <T> void addAll(Iterable<T> iterable, Collection<? super T> collection) {
            addAll((Iterable) iterable, (List) collection);
        }

        public static <T> void addAllCheckingNulls(Iterable<T> iterable, List<? super T> list) {
            if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
                ((ArrayList) list).ensureCapacity(((Collection) iterable).size() + list.size());
            }
            int size = list.size();
            for (T t : iterable) {
                if (t == null) {
                    StringBuilder sbA = a.a("Element at index ");
                    sbA.append(list.size() - size);
                    sbA.append(" is null.");
                    String string = sbA.toString();
                    int size2 = list.size();
                    while (true) {
                        size2--;
                        if (size2 < size) {
                            throw new NullPointerException(string);
                        }
                        list.remove(size2);
                    }
                } else {
                    list.add(t);
                }
            }
        }

        private String getReadingExceptionMessage(String str) {
            StringBuilder sbA = a.a("Reading ");
            sbA.append(getClass().getName());
            sbA.append(" from a ");
            sbA.append(str);
            sbA.append(" threw an IOException (should never happen).");
            return sbA.toString();
        }

        public static UninitializedMessageException newUninitializedMessageException(MessageLite messageLite) {
            return new UninitializedMessageException(messageLite);
        }

        @Override // 
        /* JADX INFO: renamed from: clone */
        public abstract BuilderType mo6clone();

        public abstract BuilderType internalMergeFrom(MessageType messagetype);

        @Override // com.google.protobuf.MessageLite.Builder
        public boolean mergeDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int i = inputStream.read();
            if (i == -1) {
                return false;
            }
            mergeFrom((InputStream) new LimitedInputStream(inputStream, CodedInputStream.readRawVarint32(i, inputStream)), extensionRegistryLite);
            return true;
        }

        @Override // com.google.protobuf.MessageLite.Builder
        public abstract BuilderType mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite);

        public static <T> void addAll(Iterable<T> iterable, List<? super T> list) {
            Internal.checkNotNull(iterable);
            if (!(iterable instanceof LazyStringList)) {
                if (iterable instanceof PrimitiveNonBoxingCollection) {
                    list.addAll((Collection) iterable);
                    return;
                } else {
                    addAllCheckingNulls(iterable, list);
                    return;
                }
            }
            List<?> underlyingElements = ((LazyStringList) iterable).getUnderlyingElements();
            LazyStringList lazyStringList = (LazyStringList) list;
            int size = list.size();
            for (Object obj : underlyingElements) {
                if (obj == null) {
                    StringBuilder sbA = a.a("Element at index ");
                    sbA.append(lazyStringList.size() - size);
                    sbA.append(" is null.");
                    String string = sbA.toString();
                    int size2 = lazyStringList.size();
                    while (true) {
                        size2--;
                        if (size2 < size) {
                            throw new NullPointerException(string);
                        }
                        lazyStringList.remove(size2);
                    }
                } else if (obj instanceof ByteString) {
                    lazyStringList.add((ByteString) obj);
                } else {
                    lazyStringList.add((String) obj);
                }
            }
        }

        static final class LimitedInputStream extends FilterInputStream {
            public int limit;

            public LimitedInputStream(InputStream inputStream, int i) {
                super(inputStream);
                this.limit = i;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int available() {
                return Math.min(super.available(), this.limit);
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int read() throws IOException {
                if (this.limit <= 0) {
                    return -1;
                }
                int i = super.read();
                if (i >= 0) {
                    this.limit--;
                }
                return i;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public long skip(long j) throws IOException {
                long jSkip = super.skip(Math.min(j, this.limit));
                if (jSkip >= 0) {
                    this.limit = (int) (((long) this.limit) - jSkip);
                }
                return jSkip;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int read(byte[] bArr, int i, int i2) throws IOException {
                int i3 = this.limit;
                if (i3 <= 0) {
                    return -1;
                }
                int i4 = super.read(bArr, i, Math.min(i2, i3));
                if (i4 >= 0) {
                    this.limit -= i4;
                }
                return i4;
            }
        }

        @Override // com.google.protobuf.MessageLite.Builder
        public boolean mergeDelimitedFrom(InputStream inputStream) {
            return mergeDelimitedFrom(inputStream, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.protobuf.MessageLite.Builder
        public BuilderType mergeFrom(CodedInputStream codedInputStream) {
            return (BuilderType) mergeFrom(codedInputStream, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.protobuf.MessageLite.Builder
        public BuilderType mergeFrom(ByteString byteString) throws InvalidProtocolBufferException {
            try {
                CodedInputStream codedInputStreamNewCodedInput = byteString.newCodedInput();
                mergeFrom(codedInputStreamNewCodedInput);
                codedInputStreamNewCodedInput.checkLastTagWas(0);
                return this;
            } catch (InvalidProtocolBufferException e2) {
                throw e2;
            } catch (IOException e3) {
                throw new RuntimeException(getReadingExceptionMessage("ByteString"), e3);
            }
        }

        @Override // com.google.protobuf.MessageLite.Builder
        public BuilderType mergeFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            try {
                CodedInputStream codedInputStreamNewCodedInput = byteString.newCodedInput();
                mergeFrom(codedInputStreamNewCodedInput, extensionRegistryLite);
                codedInputStreamNewCodedInput.checkLastTagWas(0);
                return this;
            } catch (InvalidProtocolBufferException e2) {
                throw e2;
            } catch (IOException e3) {
                throw new RuntimeException(getReadingExceptionMessage("ByteString"), e3);
            }
        }

        @Override // com.google.protobuf.MessageLite.Builder
        public BuilderType mergeFrom(byte[] bArr) {
            return (BuilderType) mergeFrom(bArr, 0, bArr.length);
        }

        @Override // com.google.protobuf.MessageLite.Builder
        public BuilderType mergeFrom(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException {
            try {
                CodedInputStream codedInputStreamNewInstance = CodedInputStream.newInstance(bArr, i, i2);
                mergeFrom(codedInputStreamNewInstance);
                codedInputStreamNewInstance.checkLastTagWas(0);
                return this;
            } catch (InvalidProtocolBufferException e2) {
                throw e2;
            } catch (IOException e3) {
                throw new RuntimeException(getReadingExceptionMessage("byte array"), e3);
            }
        }

        @Override // com.google.protobuf.MessageLite.Builder
        public BuilderType mergeFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (BuilderType) mergeFrom(bArr, 0, bArr.length, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite.Builder
        public BuilderType mergeFrom(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            try {
                CodedInputStream codedInputStreamNewInstance = CodedInputStream.newInstance(bArr, i, i2);
                mergeFrom(codedInputStreamNewInstance, extensionRegistryLite);
                codedInputStreamNewInstance.checkLastTagWas(0);
                return this;
            } catch (InvalidProtocolBufferException e2) {
                throw e2;
            } catch (IOException e3) {
                throw new RuntimeException(getReadingExceptionMessage("byte array"), e3);
            }
        }

        @Override // com.google.protobuf.MessageLite.Builder
        public BuilderType mergeFrom(InputStream inputStream) {
            CodedInputStream codedInputStreamNewInstance = CodedInputStream.newInstance(inputStream);
            mergeFrom(codedInputStreamNewInstance);
            codedInputStreamNewInstance.checkLastTagWas(0);
            return this;
        }

        @Override // com.google.protobuf.MessageLite.Builder
        public BuilderType mergeFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            CodedInputStream codedInputStreamNewInstance = CodedInputStream.newInstance(inputStream);
            mergeFrom(codedInputStreamNewInstance, extensionRegistryLite);
            codedInputStreamNewInstance.checkLastTagWas(0);
            return this;
        }

        @Override // com.google.protobuf.MessageLite.Builder
        public BuilderType mergeFrom(MessageLite messageLite) {
            if (getDefaultInstanceForType().getClass().isInstance(messageLite)) {
                return (BuilderType) internalMergeFrom((AbstractMessageLite) messageLite);
            }
            throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
        }
    }

    protected interface InternalOneOfEnum {
        int getNumber();
    }

    @Deprecated
    public static <T> void addAll(Iterable<T> iterable, Collection<? super T> collection) {
        Builder.addAll((Iterable) iterable, (List) collection);
    }

    public static void checkByteStringIsUtf8(ByteString byteString) {
        if (!byteString.isValidUtf8()) {
            throw new IllegalArgumentException("Byte string is not UTF-8.");
        }
    }

    private String getSerializingExceptionMessage(String str) {
        StringBuilder sbA = a.a("Serializing ");
        sbA.append(getClass().getName());
        sbA.append(" to a ");
        sbA.append(str);
        sbA.append(" threw an IOException (should never happen).");
        return sbA.toString();
    }

    public int getMemoizedSerializedSize() {
        throw new UnsupportedOperationException();
    }

    public int getSerializedSize(Schema schema) {
        int memoizedSerializedSize = getMemoizedSerializedSize();
        if (memoizedSerializedSize != -1) {
            return memoizedSerializedSize;
        }
        int serializedSize = schema.getSerializedSize(this);
        setMemoizedSerializedSize(serializedSize);
        return serializedSize;
    }

    public UninitializedMessageException newUninitializedMessageException() {
        return new UninitializedMessageException(this);
    }

    public void setMemoizedSerializedSize(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.protobuf.MessageLite
    public byte[] toByteArray() {
        try {
            byte[] bArr = new byte[getSerializedSize()];
            CodedOutputStream codedOutputStreamNewInstance = CodedOutputStream.newInstance(bArr);
            writeTo(codedOutputStreamNewInstance);
            codedOutputStreamNewInstance.checkNoSpaceLeft();
            return bArr;
        } catch (IOException e2) {
            throw new RuntimeException(getSerializingExceptionMessage("byte array"), e2);
        }
    }

    @Override // com.google.protobuf.MessageLite
    public ByteString toByteString() {
        try {
            ByteString.CodedBuilder codedBuilderNewCodedBuilder = ByteString.newCodedBuilder(getSerializedSize());
            writeTo(codedBuilderNewCodedBuilder.getCodedOutput());
            return codedBuilderNewCodedBuilder.build();
        } catch (IOException e2) {
            throw new RuntimeException(getSerializingExceptionMessage("ByteString"), e2);
        }
    }

    @Override // com.google.protobuf.MessageLite
    public void writeDelimitedTo(OutputStream outputStream) {
        int serializedSize = getSerializedSize();
        CodedOutputStream codedOutputStreamNewInstance = CodedOutputStream.newInstance(outputStream, CodedOutputStream.computePreferredBufferSize(CodedOutputStream.computeUInt32SizeNoTag(serializedSize) + serializedSize));
        codedOutputStreamNewInstance.writeRawVarint32(serializedSize);
        writeTo(codedOutputStreamNewInstance);
        codedOutputStreamNewInstance.flush();
    }

    @Override // com.google.protobuf.MessageLite
    public void writeTo(OutputStream outputStream) {
        CodedOutputStream codedOutputStreamNewInstance = CodedOutputStream.newInstance(outputStream, CodedOutputStream.computePreferredBufferSize(getSerializedSize()));
        writeTo(codedOutputStreamNewInstance);
        codedOutputStreamNewInstance.flush();
    }

    public static <T> void addAll(Iterable<T> iterable, List<? super T> list) {
        Builder.addAll((Iterable) iterable, (List) list);
    }
}
