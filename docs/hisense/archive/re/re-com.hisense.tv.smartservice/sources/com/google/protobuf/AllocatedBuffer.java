package com.google.protobuf;

import b.a.a.a.a;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AllocatedBuffer {

    /* JADX INFO: renamed from: com.google.protobuf.AllocatedBuffer$2, reason: invalid class name */
    static class AnonymousClass2 extends AllocatedBuffer {
        public int position;
        public final /* synthetic */ byte[] val$bytes;
        public final /* synthetic */ int val$length;
        public final /* synthetic */ int val$offset;

        public AnonymousClass2(byte[] bArr, int i, int i2) {
            this.val$bytes = bArr;
            this.val$offset = i;
            this.val$length = i2;
        }

        @Override // com.google.protobuf.AllocatedBuffer
        public byte[] array() {
            return this.val$bytes;
        }

        @Override // com.google.protobuf.AllocatedBuffer
        public int arrayOffset() {
            return this.val$offset;
        }

        @Override // com.google.protobuf.AllocatedBuffer
        public boolean hasArray() {
            return true;
        }

        @Override // com.google.protobuf.AllocatedBuffer
        public boolean hasNioBuffer() {
            return false;
        }

        @Override // com.google.protobuf.AllocatedBuffer
        public int limit() {
            return this.val$length;
        }

        @Override // com.google.protobuf.AllocatedBuffer
        public ByteBuffer nioBuffer() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.protobuf.AllocatedBuffer
        public int position() {
            return this.position;
        }

        @Override // com.google.protobuf.AllocatedBuffer
        public int remaining() {
            return this.val$length - this.position;
        }

        @Override // com.google.protobuf.AllocatedBuffer
        public AllocatedBuffer position(int i) {
            if (i < 0 || i > this.val$length) {
                throw new IllegalArgumentException(a.a("Invalid position: ", i));
            }
            this.position = i;
            return this;
        }
    }

    public static AllocatedBuffer wrap(byte[] bArr) {
        return new AnonymousClass2(bArr, 0, bArr.length);
    }

    public static AllocatedBuffer wrapNoCheck(byte[] bArr, int i, int i2) {
        return new AnonymousClass2(bArr, i, i2);
    }

    public abstract byte[] array();

    public abstract int arrayOffset();

    public abstract boolean hasArray();

    public abstract boolean hasNioBuffer();

    public abstract int limit();

    public abstract ByteBuffer nioBuffer();

    public abstract int position();

    public abstract AllocatedBuffer position(int i);

    public abstract int remaining();

    public static AllocatedBuffer wrap(byte[] bArr, int i, int i2) {
        if (i >= 0 && i2 >= 0 && i + i2 <= bArr.length) {
            return new AnonymousClass2(bArr, i, i2);
        }
        throw new IndexOutOfBoundsException(String.format("bytes.length=%d, offset=%d, length=%d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
    }

    public static AllocatedBuffer wrap(final ByteBuffer byteBuffer) {
        Internal.checkNotNull(byteBuffer, "buffer");
        return new AllocatedBuffer() { // from class: com.google.protobuf.AllocatedBuffer.1
            @Override // com.google.protobuf.AllocatedBuffer
            public byte[] array() {
                return byteBuffer.array();
            }

            @Override // com.google.protobuf.AllocatedBuffer
            public int arrayOffset() {
                return byteBuffer.arrayOffset();
            }

            @Override // com.google.protobuf.AllocatedBuffer
            public boolean hasArray() {
                return byteBuffer.hasArray();
            }

            @Override // com.google.protobuf.AllocatedBuffer
            public boolean hasNioBuffer() {
                return true;
            }

            @Override // com.google.protobuf.AllocatedBuffer
            public int limit() {
                return byteBuffer.limit();
            }

            @Override // com.google.protobuf.AllocatedBuffer
            public ByteBuffer nioBuffer() {
                return byteBuffer;
            }

            @Override // com.google.protobuf.AllocatedBuffer
            public int position() {
                return byteBuffer.position();
            }

            @Override // com.google.protobuf.AllocatedBuffer
            public int remaining() {
                return byteBuffer.remaining();
            }

            @Override // com.google.protobuf.AllocatedBuffer
            public AllocatedBuffer position(int i) {
                byteBuffer.position(i);
                return this;
            }
        };
    }
}
