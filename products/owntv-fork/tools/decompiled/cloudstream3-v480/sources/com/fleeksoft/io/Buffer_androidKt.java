package com.fleeksoft.io;

import com.lagradost.cloudstream3.actions.temp.CloudStreamPackage;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Buffer.android.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\u001a\u001a\u0010\u0002\u001a\u00060\u0001j\u0002`\u0003*\u00060\u0001j\u0002`\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u001a\u0010\u0006\u001a\u00060\u0001j\u0002`\u0003*\u00060\u0001j\u0002`\u00032\u0006\u0010\u0007\u001a\u00020\u0005\u001a\u0012\u0010\b\u001a\u00060\u0001j\u0002`\u0003*\u00060\u0001j\u0002`\u0003\u001a\u0012\u0010\t\u001a\u00060\u0001j\u0002`\u0003*\u00060\u0001j\u0002`\u0003\u001a\u0012\u0010\n\u001a\u00060\u0001j\u0002`\u0003*\u00060\u0001j\u0002`\u0003\u001a\u0012\u0010\u000b\u001a\u00060\u0001j\u0002`\u0003*\u00060\u0001j\u0002`\u0003\u001a\u0012\u0010\f\u001a\u00060\u0001j\u0002`\u0003*\u00060\u0001j\u0002`\u0003\u001a\u0014\u0010\r\u001a\u00060\u0001j\u0002`\u0003*\u00060\u0001j\u0002`\u0003H\u0007\u001a$\u0010\r\u001a\u00060\u0001j\u0002`\u0003*\u00060\u0001j\u0002`\u00032\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0007*\n\u0010\u0000\"\u00020\u00012\u00020\u0001¨\u0006\u0010"}, d2 = {"Buffer", "Ljava/nio/Buffer;", "setPositionExt", "Lcom/fleeksoft/io/Buffer;", CloudStreamPackage.POSITION_EXTRA, "", "setLimitExt", "newLimit", "clearExt", "flipExt", "rewindExt", "markExt", "resetExt", "sliceExt", "index", "length", "io-core_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class Buffer_androidKt {
    public static final Buffer setPositionExt(Buffer buffer, int i) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Buffer bufferPosition = buffer.position(i);
        Intrinsics.checkNotNullExpressionValue(bufferPosition, "position(...)");
        return bufferPosition;
    }

    public static final Buffer setLimitExt(Buffer buffer, int i) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Buffer bufferLimit = buffer.limit(i);
        Intrinsics.checkNotNullExpressionValue(bufferLimit, "limit(...)");
        return bufferLimit;
    }

    public static final Buffer clearExt(Buffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Buffer bufferClear = buffer.clear();
        Intrinsics.checkNotNullExpressionValue(bufferClear, "clear(...)");
        return bufferClear;
    }

    public static final Buffer flipExt(Buffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Buffer bufferFlip = buffer.flip();
        Intrinsics.checkNotNullExpressionValue(bufferFlip, "flip(...)");
        return bufferFlip;
    }

    public static final Buffer rewindExt(Buffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Buffer bufferRewind = buffer.rewind();
        Intrinsics.checkNotNullExpressionValue(bufferRewind, "rewind(...)");
        return bufferRewind;
    }

    public static final Buffer markExt(Buffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Buffer bufferMark = buffer.mark();
        Intrinsics.checkNotNullExpressionValue(bufferMark, "mark(...)");
        return bufferMark;
    }

    public static final Buffer resetExt(Buffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Buffer bufferReset = buffer.reset();
        Intrinsics.checkNotNullExpressionValue(bufferReset, "reset(...)");
        return bufferReset;
    }

    public static final Buffer sliceExt(Buffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        if (buffer instanceof CharBuffer) {
            CharBuffer charBufferSlice = ((CharBuffer) buffer).slice();
            Intrinsics.checkNotNullExpressionValue(charBufferSlice, "slice(...)");
            return charBufferSlice;
        }
        if (buffer instanceof ByteBuffer) {
            ByteBuffer byteBufferSlice = ((ByteBuffer) buffer).slice();
            Intrinsics.checkNotNullExpressionValue(byteBufferSlice, "slice(...)");
            return byteBufferSlice;
        }
        Buffer bufferSlice = buffer.slice();
        Intrinsics.checkNotNullExpressionValue(bufferSlice, "slice(...)");
        return bufferSlice;
    }

    public static final Buffer sliceExt(Buffer buffer, int i, int i2) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Buffer bufferSlice = buffer.slice(i, i2);
        Intrinsics.checkNotNullExpressionValue(bufferSlice, "slice(...)");
        return bufferSlice;
    }
}
