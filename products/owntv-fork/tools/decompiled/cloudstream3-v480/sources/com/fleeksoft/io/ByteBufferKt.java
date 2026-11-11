package com.fleeksoft.io;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ByteBuffer.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0013\u0010\u0000\u001a\u00020\u0001*\u00060\u0002j\u0002`\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"getInt", "", "Ljava/nio/ByteBuffer;", "Lcom/fleeksoft/io/ByteBuffer;", "(Ljava/nio/ByteBuffer;)I", "io-core_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ByteBufferKt {
    public static final int getInt(ByteBuffer byteBuffer) {
        Intrinsics.checkNotNullParameter(byteBuffer, "<this>");
        return byteBuffer.get();
    }
}
