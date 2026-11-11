package java.nio.channels;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public interface SeekableByteChannel extends ByteChannel {
    SeekableByteChannel position(long j);

    @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.ReadableByteChannel
    int read(ByteBuffer byteBuffer);

    long size();
}
