package io.ktor.utils.io.core;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.BuffersJvmKt;
import kotlinx.io.Sink;

/* JADX INFO: compiled from: BytePacketBuilderExtensions.jvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a\u0019\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lkotlinx/io/Sink;", "Ljava/nio/ByteBuffer;", "buffer", "", "writeFully", "(Lkotlinx/io/Sink;Ljava/nio/ByteBuffer;)V", "ktor-io"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class BytePacketBuilderExtensions_jvmKt {
    public static final void writeFully(Sink sink, ByteBuffer buffer) {
        Intrinsics.checkNotNullParameter(sink, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        BuffersJvmKt.transferFrom(sink.getBufferField(), buffer);
    }
}
