package io.ktor.utils.io.core;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import io.ktor.http.ContentType;
import io.ktor.utils.io.pool.ObjectPool;
import java.io.EOFException;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.Buffer;
import kotlinx.io.Sink;
import kotlinx.io.Source;
import kotlinx.io.SourcesKt;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;

/* JADX INFO: compiled from: ByteReadPacket.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a)\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u001b\u0010\u000b\u001a\u00020\n2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\bH\u0007¢\u0006\u0004\b\u000b\u0010\f\u001a\u000f\u0010\u000b\u001a\u00020\nH\u0007¢\u0006\u0004\b\u000b\u0010\r\u001a\u0019\u0010\u000f\u001a\u00020\u0002*\u00020\u00052\u0006\u0010\u000e\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u0013\u0010\u0011\u001a\u00020\u0005*\u00020\u0005H\u0007¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0011\u0010\u0014\u001a\u00020\u0013*\u00020\u0005¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u001b\u0010\u0018\u001a\u00020\u0016*\u00020\u00052\b\b\u0002\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u0018\u0010\u0019\u001a%\u0010\u001e\u001a\u00020\u001d*\u00020\u00052\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u001b0\u001a¢\u0006\u0004\b\u001e\u0010\u001f\u001a-\u0010 \u001a\u00020\u001d*\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b \u0010!\u001a+\u0010$\u001a\u00028\u0000\"\u0004\b\u0000\u0010\"*\u00020\u00052\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00028\u00000\u001a¢\u0006\u0004\b$\u0010%\u001a+\u0010$\u001a\u00028\u0000\"\u0004\b\u0000\u0010\"*\u00020&2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00028\u00000\u001a¢\u0006\u0004\b$\u0010'\u001a\u0013\u0010(\u001a\u00020\u001d*\u00020\u0005H\u0007¢\u0006\u0004\b(\u0010)\"\u0017\u0010*\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-\"\u001b\u00101\u001a\u00020\u0016*\u00020\u00058F¢\u0006\f\u0012\u0004\b0\u0010)\u001a\u0004\b.\u0010/*>\b\u0007\u0010\u0006\"\u00020\u00052\u00020\u0005B0\b2\u0012\b\b3\u0012\u0004\b\b(4\u0012\"\b5\u0012\u001e\b\u000bB\u001a\b6\u0012\b\b7\u0012\u0004\b\b(8\u0012\f\b9\u0012\b\b\fJ\u0004\b\b(:¨\u0006;"}, d2 = {"", "array", "", TypedValues.CycleType.S_WAVE_OFFSET, "length", "Lkotlinx/io/Source;", "ByteReadPacket", "([BII)Lkotlinx/io/Source;", "Lio/ktor/utils/io/pool/ObjectPool;", "pool", "Lkotlinx/io/Buffer;", "Sink", "(Lio/ktor/utils/io/pool/ObjectPool;)Lkotlinx/io/Buffer;", "()Lkotlinx/io/Buffer;", "out", "readAvailable", "(Lkotlinx/io/Source;Lkotlinx/io/Buffer;)I", "copy", "(Lkotlinx/io/Source;)Lkotlinx/io/Source;", "", "readShortLittleEndian", "(Lkotlinx/io/Source;)S", "", PeertubeParsingHelper.COUNT_KEY, "discard", "(Lkotlinx/io/Source;J)J", "Lkotlin/Function1;", "", "block", "", "takeWhile", "(Lkotlinx/io/Source;Lkotlin/jvm/functions/Function1;)V", "readFully", "(Lkotlinx/io/Source;[BII)V", ExifInterface.GPS_DIRECTION_TRUE, "function", "preview", "(Lkotlinx/io/Source;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Lkotlinx/io/Sink;", "(Lkotlinx/io/Sink;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "release", "(Lkotlinx/io/Source;)V", "ByteReadPacketEmpty", "Lkotlinx/io/Source;", "getByteReadPacketEmpty", "()Lkotlinx/io/Source;", "getRemaining", "(Lkotlinx/io/Source;)J", "getRemaining$annotations", "remaining", "Lkotlin/Deprecated;", ContentType.Message.TYPE, "Use Source instead", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "Source", "imports", "kotlinx.io.Source", "ktor-io"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ByteReadPacketKt {
    private static final Source ByteReadPacketEmpty = new Buffer();

    @Deprecated(message = "Use Source instead", replaceWith = @ReplaceWith(expression = "Source", imports = {"kotlinx.io.Source"}))
    public static /* synthetic */ void ByteReadPacket$annotations() {
    }

    public static /* synthetic */ void getRemaining$annotations(Source source) {
    }

    public static final Source getByteReadPacketEmpty() {
        return ByteReadPacketEmpty;
    }

    public static /* synthetic */ Source ByteReadPacket$default(byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        return ByteReadPacket(bArr, i, i2);
    }

    public static final Source ByteReadPacket(byte[] array, int i, int i2) {
        Intrinsics.checkNotNullParameter(array, "array");
        Buffer buffer = new Buffer();
        buffer.write(array, i, i2 + i);
        return buffer;
    }

    public static final long getRemaining(Source source) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        return source.getBufferField().getSizeMut();
    }

    @Deprecated(message = "Use Buffer instead", replaceWith = @ReplaceWith(expression = "Buffer()", imports = {"kotlinx.io.Buffer"}))
    public static final Buffer Sink(ObjectPool<?> pool) {
        Intrinsics.checkNotNullParameter(pool, "pool");
        return new Buffer();
    }

    @Deprecated(message = "Use Buffer instead", replaceWith = @ReplaceWith(expression = "Buffer()", imports = {"kotlinx.io.Buffer"}))
    public static final Buffer Sink() {
        return new Buffer();
    }

    public static final int readAvailable(Source source, Buffer out) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        Intrinsics.checkNotNullParameter(out, "out");
        long sizeMut = source.getBufferField().getSizeMut();
        out.transferFrom(source);
        return (int) sizeMut;
    }

    @Deprecated(message = "Use peek() or buffer.copy() instead, depending on your use case.", replaceWith = @ReplaceWith(expression = "peek()", imports = {"kotlinx.io.Source"}))
    public static final Source copy(Source source) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        return source.peek();
    }

    public static final short readShortLittleEndian(Source source) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        return SourcesKt.readShortLe(source.getBufferField());
    }

    public static /* synthetic */ long discard$default(Source source, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = Long.MAX_VALUE;
        }
        return discard(source, j);
    }

    public static final long discard(Source source, long j) throws EOFException {
        Intrinsics.checkNotNullParameter(source, "<this>");
        source.request(j);
        long jMin = Math.min(j, getRemaining(source));
        source.getBufferField().skip(jMin);
        return jMin;
    }

    public static final void takeWhile(Source source, Function1<? super Buffer, Boolean> block) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        while (!source.exhausted() && block.invoke(source.getBufferField()).booleanValue()) {
        }
    }

    public static /* synthetic */ void readFully$default(Source source, byte[] bArr, int i, int i2, int i3, Object obj) throws EOFException {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length - i;
        }
        readFully(source, bArr, i, i2);
    }

    public static final void readFully(Source source, byte[] out, int i, int i2) throws EOFException {
        Intrinsics.checkNotNullParameter(source, "<this>");
        Intrinsics.checkNotNullParameter(out, "out");
        SourcesKt.readTo(source, out, i, i2 + i);
    }

    public static final <T> T preview(Source source, Function1<? super Source, ? extends T> function) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        Intrinsics.checkNotNullParameter(function, "function");
        Source sourcePeek = source.getBufferField().peek();
        try {
            T tInvoke = function.invoke(sourcePeek);
            AutoCloseableKt.closeFinally(sourcePeek, null);
            return tInvoke;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                AutoCloseableKt.closeFinally(sourcePeek, th);
                throw th2;
            }
        }
    }

    public static final <T> T preview(Sink sink, Function1<? super Source, ? extends T> function) {
        Intrinsics.checkNotNullParameter(sink, "<this>");
        Intrinsics.checkNotNullParameter(function, "function");
        Source sourcePeek = sink.getBufferField().peek();
        try {
            T tInvoke = function.invoke(sourcePeek);
            AutoCloseableKt.closeFinally(sourcePeek, null);
            return tInvoke;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                AutoCloseableKt.closeFinally(sourcePeek, th);
                throw th2;
            }
        }
    }

    @Deprecated(message = "Use close instead", replaceWith = @ReplaceWith(expression = "this.close()", imports = {}))
    public static final void release(Source source) {
        Intrinsics.checkNotNullParameter(source, "<this>");
        source.close();
    }
}
