package io.ktor.utils.io.core;

import java.nio.ByteBuffer;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.Buffer;
import kotlinx.io.BuffersJvmKt;

/* JADX INFO: compiled from: BufferPrimitivesJvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a\u001b\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lkotlinx/io/Buffer;", "Ljava/nio/ByteBuffer;", "source", "", "writeByteBuffer", "(Lkotlinx/io/Buffer;Ljava/nio/ByteBuffer;)V", "ktor-io"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class BufferPrimitivesJvmKt {
    @Deprecated(message = "[writeByteBuffer] is deprecated. Consider using [transferFrom] instead", replaceWith = @ReplaceWith(expression = "this.transferFrom(source)", imports = {}))
    public static final void writeByteBuffer(Buffer buffer, ByteBuffer source) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(source, "source");
        BuffersJvmKt.transferFrom(buffer, source);
    }
}
