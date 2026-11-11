package com.fleeksoft.io;

import java.nio.CharBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: compiled from: CharBufferFactory.jvmAndAndroid.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0004\u001a\u00060\u0005j\u0002`\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\nJ&\u0010\u0004\u001a\u00060\u0005j\u0002`\u00062\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\nJ\u0012\u0010\u000e\u001a\u00060\u0005j\u0002`\u00062\u0006\u0010\u000f\u001a\u00020\n¨\u0006\u0010"}, d2 = {"Lcom/fleeksoft/io/CharBufferFactory;", "", "<init>", "()V", "wrap", "Ljava/nio/CharBuffer;", "Lcom/fleeksoft/io/CharBuffer;", "charArray", "", DebugKt.DEBUG_PROPERTY_VALUE_OFF, "", "len", "charSequence", "", "allocate", "capacity", "io-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class CharBufferFactory {
    public static final CharBufferFactory INSTANCE = new CharBufferFactory();

    private CharBufferFactory() {
    }

    public static /* synthetic */ CharBuffer wrap$default(CharBufferFactory charBufferFactory, char[] cArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = cArr.length;
        }
        return charBufferFactory.wrap(cArr, i, i2);
    }

    public final CharBuffer wrap(char[] charArray, int off, int len) {
        Intrinsics.checkNotNullParameter(charArray, "charArray");
        CharBuffer charBufferWrap = CharBuffer.wrap(charArray, off, len);
        Intrinsics.checkNotNullExpressionValue(charBufferWrap, "wrap(...)");
        return charBufferWrap;
    }

    public static /* synthetic */ CharBuffer wrap$default(CharBufferFactory charBufferFactory, CharSequence charSequence, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = charSequence.length();
        }
        return charBufferFactory.wrap(charSequence, i, i2);
    }

    public final CharBuffer wrap(CharSequence charSequence, int off, int len) {
        Intrinsics.checkNotNullParameter(charSequence, "charSequence");
        CharBuffer charBufferWrap = CharBuffer.wrap(charSequence, off, len);
        Intrinsics.checkNotNullExpressionValue(charBufferWrap, "wrap(...)");
        return charBufferWrap;
    }

    public final CharBuffer allocate(int capacity) {
        CharBuffer charBufferAllocate = CharBuffer.allocate(capacity);
        Intrinsics.checkNotNullExpressionValue(charBufferAllocate, "allocate(...)");
        return charBufferAllocate;
    }
}
