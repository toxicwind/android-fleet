package com.fleeksoft.io;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ByteBuffer.jvmAndAndroid.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0002\u001a\u00060\u0001j\u0002`\u0003*\u00060\u0001j\u0002`\u0003*\n\u0010\u0000\"\u00020\u00012\u00020\u0001¨\u0006\u0004"}, d2 = {"ByteBuffer", "Ljava/nio/ByteBuffer;", "duplicateExt", "Lcom/fleeksoft/io/ByteBuffer;", "io-core_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ByteBuffer_jvmAndAndroidKt {
    public static final ByteBuffer duplicateExt(ByteBuffer byteBuffer) {
        Intrinsics.checkNotNullParameter(byteBuffer, "<this>");
        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
        Intrinsics.checkNotNullExpressionValue(byteBufferDuplicate, "duplicate(...)");
        return byteBufferDuplicate;
    }
}
