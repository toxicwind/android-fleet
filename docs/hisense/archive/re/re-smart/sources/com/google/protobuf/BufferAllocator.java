package com.google.protobuf;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BufferAllocator {
    public static final BufferAllocator UNPOOLED = new BufferAllocator() { // from class: com.google.protobuf.BufferAllocator.1
        @Override // com.google.protobuf.BufferAllocator
        public AllocatedBuffer allocateDirectBuffer(int i) {
            return AllocatedBuffer.wrap(ByteBuffer.allocateDirect(i));
        }

        @Override // com.google.protobuf.BufferAllocator
        public AllocatedBuffer allocateHeapBuffer(int i) {
            byte[] bArr = new byte[i];
            return new AllocatedBuffer.AnonymousClass2(bArr, 0, bArr.length);
        }
    };

    public static BufferAllocator unpooled() {
        return UNPOOLED;
    }

    public abstract AllocatedBuffer allocateDirectBuffer(int i);

    public abstract AllocatedBuffer allocateHeapBuffer(int i);
}
