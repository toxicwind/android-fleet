package com.fleeksoft.io;

import java.nio.CharBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CharBuffer.jvmAndAndroid.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0012\u0010\u0002\u001a\u00060\u0001j\u0002`\u0003*\u00060\u0001j\u0002`\u0003\u001a\u0016\u0010\u0004\u001a\u00020\u0005*\u00060\u0001j\u0002`\u00032\u0006\u0010\u0006\u001a\u00020\u0007*\n\u0010\u0000\"\u00020\u00012\u00020\u0001¨\u0006\b"}, d2 = {"CharBuffer", "Ljava/nio/CharBuffer;", "duplicateExt", "Lcom/fleeksoft/io/CharBuffer;", "getChar", "", "index", "", "io-core_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class CharBuffer_jvmAndAndroidKt {
    public static final CharBuffer duplicateExt(CharBuffer charBuffer) {
        Intrinsics.checkNotNullParameter(charBuffer, "<this>");
        CharBuffer charBufferDuplicate = charBuffer.duplicate();
        Intrinsics.checkNotNullExpressionValue(charBufferDuplicate, "duplicate(...)");
        return charBufferDuplicate;
    }

    public static final char getChar(CharBuffer charBuffer, int i) {
        Intrinsics.checkNotNullParameter(charBuffer, "<this>");
        return charBuffer.get(i);
    }
}
