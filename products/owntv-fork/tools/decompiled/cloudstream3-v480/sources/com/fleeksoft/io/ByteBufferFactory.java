package com.fleeksoft.io;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: compiled from: ByteBufferFactory.jvmAndAndroid.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0004\u001a\u00060\u0005j\u0002`\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\nJ\u0012\u0010\f\u001a\u00060\u0005j\u0002`\u00062\u0006\u0010\r\u001a\u00020\n¨\u0006\u000e"}, d2 = {"Lcom/fleeksoft/io/ByteBufferFactory;", "", "<init>", "()V", "wrap", "Ljava/nio/ByteBuffer;", "Lcom/fleeksoft/io/ByteBuffer;", "byteArray", "", DebugKt.DEBUG_PROPERTY_VALUE_OFF, "", "len", "allocate", "capacity", "io-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ByteBufferFactory {
    public static final ByteBufferFactory INSTANCE = new ByteBufferFactory();

    private ByteBufferFactory() {
    }

    public static /* synthetic */ ByteBuffer wrap$default(ByteBufferFactory byteBufferFactory, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        return byteBufferFactory.wrap(bArr, i, i2);
    }

    public final ByteBuffer wrap(byte[] byteArray, int off, int len) {
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(byteArray, off, len);
        Intrinsics.checkNotNullExpressionValue(byteBufferWrap, "wrap(...)");
        return byteBufferWrap;
    }

    public final ByteBuffer allocate(int capacity) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(capacity);
        Intrinsics.checkNotNullExpressionValue(byteBufferAllocate, "allocate(...)");
        return byteBufferAllocate;
    }
}
