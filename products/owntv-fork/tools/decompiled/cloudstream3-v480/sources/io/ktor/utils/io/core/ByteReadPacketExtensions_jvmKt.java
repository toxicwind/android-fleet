package io.ktor.utils.io.core;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.Buffer;
import kotlinx.io.Segment;
import kotlinx.io.SinksJvmKt;
import kotlinx.io.Source;
import kotlinx.io.SourcesJvmKt;
import kotlinx.io.unsafe.UnsafeBufferOperations;

/* JADX INFO: compiled from: ByteReadPacketExtensions.jvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0015\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0019\u0010\u0007\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a\u0019\u0010\n\u001a\u00020\t*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a%\u0010\u000e\u001a\u00020\t*\u00020\u00022\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\t0\f¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Ljava/nio/ByteBuffer;", "byteBuffer", "Lkotlinx/io/Source;", "ByteReadPacket", "(Ljava/nio/ByteBuffer;)Lkotlinx/io/Source;", "buffer", "", "readAvailable", "(Lkotlinx/io/Source;Ljava/nio/ByteBuffer;)I", "", "readFully", "(Lkotlinx/io/Source;Ljava/nio/ByteBuffer;)V", "Lkotlin/Function1;", "block", "read", "(Lkotlinx/io/Source;Lkotlin/jvm/functions/Function1;)V", "ktor-io"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ByteReadPacketExtensions_jvmKt {
    public static final Source ByteReadPacket(ByteBuffer byteBuffer) {
        Intrinsics.checkNotNullParameter(byteBuffer, "byteBuffer");
        Buffer buffer = new Buffer();
        SinksJvmKt.write(buffer, byteBuffer);
        return buffer;
    }

    public static final int readAvailable(Source source, ByteBuffer buffer) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        int iRemaining = buffer.remaining();
        SourcesJvmKt.readAtMostTo(source, buffer);
        return iRemaining - buffer.remaining();
    }

    public static final void readFully(Source source, ByteBuffer buffer) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        while (!source.exhausted() && buffer.hasRemaining()) {
            SourcesJvmKt.readAtMostTo(source, buffer);
        }
    }

    public static final void read(Source source, Function1<? super ByteBuffer, Unit> block) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        UnsafeBufferOperations unsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
        Buffer bufferField = source.getBufferField();
        if (bufferField.exhausted()) {
            throw new IllegalArgumentException("Buffer is empty".toString());
        }
        Segment head = bufferField.getHead();
        Intrinsics.checkNotNull(head);
        byte[] bArrDataAsByteArray = head.dataAsByteArray(true);
        int pos = head.getPos();
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArrDataAsByteArray, pos, head.getLimit() - pos);
        Intrinsics.checkNotNull(byteBufferWrap);
        block.invoke(byteBufferWrap);
        int iPosition = byteBufferWrap.position() - pos;
        if (iPosition != 0) {
            if (iPosition < 0) {
                throw new IllegalStateException("Returned negative read bytes count");
            }
            if (iPosition > head.getSize()) {
                throw new IllegalStateException("Returned too many bytes");
            }
            bufferField.skip(iPosition);
        }
    }
}
