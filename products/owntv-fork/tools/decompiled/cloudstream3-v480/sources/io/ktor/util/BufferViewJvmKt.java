package io.ktor.util;

import io.ktor.utils.io.core.internal.ChunkBufferJvmKt;
import io.ktor.utils.io.core.internal.ChunkBufferKt;
import java.io.EOFException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.io.Buffer;

/* JADX INFO: compiled from: BufferViewJvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0019\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001b\u0010\u0007\u001a\u00020\u0003*\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Ljava/nio/channels/ReadableByteChannel;", "Lkotlinx/io/Buffer;", "buffer", "", "read", "(Ljava/nio/channels/ReadableByteChannel;Lkotlinx/io/Buffer;)I", "Ljava/nio/channels/WritableByteChannel;", "write", "(Ljava/nio/channels/WritableByteChannel;Lkotlinx/io/Buffer;)I", "ktor-utils"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class BufferViewJvmKt {
    public static final int read(final ReadableByteChannel readableByteChannel, Buffer buffer) {
        Intrinsics.checkNotNullParameter(readableByteChannel, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        if (ChunkBufferKt.getWriteRemaining(buffer) == 0) {
            return 0;
        }
        final Ref.IntRef intRef = new Ref.IntRef();
        ChunkBufferJvmKt.writeDirect(buffer, 1, new Function1() { // from class: io.ktor.util.BufferViewJvmKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BufferViewJvmKt.read$lambda$0(intRef, readableByteChannel, (ByteBuffer) obj);
            }
        });
        return intRef.element;
    }

    static final Unit read$lambda$0(Ref.IntRef intRef, ReadableByteChannel readableByteChannel, ByteBuffer bb) {
        Intrinsics.checkNotNullParameter(bb, "bb");
        intRef.element = readableByteChannel.read(bb);
        return Unit.INSTANCE;
    }

    public static final int write(final WritableByteChannel writableByteChannel, Buffer buffer) throws EOFException {
        Intrinsics.checkNotNullParameter(writableByteChannel, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        final Ref.IntRef intRef = new Ref.IntRef();
        ChunkBufferJvmKt.readDirect(buffer, new Function1() { // from class: io.ktor.util.BufferViewJvmKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BufferViewJvmKt.write$lambda$0(intRef, writableByteChannel, (ByteBuffer) obj);
            }
        });
        return intRef.element;
    }

    static final Unit write$lambda$0(Ref.IntRef intRef, WritableByteChannel writableByteChannel, ByteBuffer bb) {
        Intrinsics.checkNotNullParameter(bb, "bb");
        intRef.element = writableByteChannel.write(bb);
        return Unit.INSTANCE;
    }
}
