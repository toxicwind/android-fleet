package org.conscrypt;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes8.dex */
final class BufferUtils {
    private BufferUtils() {
    }

    static void checkNotNull(ByteBuffer[] byteBufferArr) {
        for (ByteBuffer byteBuffer : byteBufferArr) {
            if (byteBuffer == null) {
                throw new IllegalArgumentException("Null buffer in array");
            }
        }
    }

    static long remaining(ByteBuffer[] byteBufferArr) {
        long jRemaining = 0;
        for (ByteBuffer byteBuffer : byteBufferArr) {
            jRemaining += (long) byteBuffer.remaining();
        }
        return jRemaining;
    }

    static void consume(ByteBuffer[] byteBufferArr, int i) {
        for (ByteBuffer byteBuffer : byteBufferArr) {
            int iMin = Math.min(byteBuffer.remaining(), i);
            if (iMin > 0) {
                byteBuffer.position(byteBuffer.position() + iMin);
                i -= iMin;
                if (i == 0) {
                    break;
                }
            }
        }
        if (i > 0) {
            throw new IllegalArgumentException("toConsume > data size");
        }
    }

    static ByteBuffer getBufferLargerThan(ByteBuffer[] byteBufferArr, int i) {
        int length = byteBufferArr.length;
        int i2 = 0;
        while (i2 < length) {
            ByteBuffer byteBuffer = byteBufferArr[i2];
            int iRemaining = byteBuffer.remaining();
            if (iRemaining > 0) {
                if (iRemaining < i) {
                    do {
                        i2++;
                        if (i2 < length) {
                        }
                    } while (byteBufferArr[i2].remaining() <= 0);
                    return null;
                }
                return byteBuffer;
            }
            i2++;
        }
        return null;
    }

    static ByteBuffer copyNoConsume(ByteBuffer[] byteBufferArr, ByteBuffer byteBuffer, int i) {
        Preconditions.checkArgument(byteBuffer.remaining() >= i, "Destination buffer too small");
        for (ByteBuffer byteBuffer2 : byteBufferArr) {
            int iRemaining = byteBuffer2.remaining();
            if (iRemaining > 0) {
                int iPosition = byteBuffer2.position();
                if (iRemaining <= i) {
                    byteBuffer.put(byteBuffer2);
                    i -= iRemaining;
                } else {
                    int iLimit = byteBuffer2.limit();
                    byteBuffer2.limit(byteBuffer2.position() + i);
                    byteBuffer.put(byteBuffer2);
                    byteBuffer2.limit(iLimit);
                    i = 0;
                }
                byteBuffer2.position(iPosition);
                if (i == 0) {
                    break;
                }
            }
        }
        byteBuffer.flip();
        return byteBuffer;
    }
}
