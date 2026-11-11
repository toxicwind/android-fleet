package io.ktor.utils.io.core;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.ktor.http.ContentDisposition;
import io.ktor.http.ContentType;
import io.ktor.utils.io.DeprecationKt;
import java.nio.charset.Charset;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.io.Buffer;
import kotlinx.io.Sink;
import kotlinx.io.Source;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: BytePacketBuilder.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\r\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0001\u0010\u0002\u001a-\u0010\t\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\t\u0010\n\u001a\u0011\u0010\f\u001a\u00020\u000b*\u00020\u0000¢\u0006\u0004\b\f\u0010\r\u001a-\u0010\u0012\u001a\u00020\b*\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u0005¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u0019\u0010\u0015\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u000b¢\u0006\u0004\b\u0015\u0010\u0016\"\u001b\u0010\u001b\u001a\u00020\u0005*\u00020\u00008F¢\u0006\f\u0012\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018*>\b\u0007\u0010\u0001\"\u00020\u00002\u00020\u0000B0\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\"\b\u001f\u0012\u001e\b\u000bB\u001a\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\f\b#\u0012\b\b\fJ\u0004\b\b($¨\u0006%"}, d2 = {"Lkotlinx/io/Sink;", "BytePacketBuilder", "()Lkotlinx/io/Sink;", "", ES6Iterator.VALUE_PROPERTY, "", "startIndex", "endIndex", "", "append", "(Lkotlinx/io/Sink;Ljava/lang/CharSequence;II)V", "Lkotlinx/io/Source;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "(Lkotlinx/io/Sink;)Lkotlinx/io/Source;", "", "buffer", TypedValues.CycleType.S_WAVE_OFFSET, "length", "writeFully", "(Lkotlinx/io/Sink;[BII)V", "packet", "writePacket", "(Lkotlinx/io/Sink;Lkotlinx/io/Source;)V", "getSize", "(Lkotlinx/io/Sink;)I", "getSize$annotations", "(Lkotlinx/io/Sink;)V", ContentDisposition.Parameters.Size, "Lkotlin/Deprecated;", ContentType.Message.TYPE, DeprecationKt.IO_DEPRECATION_MESSAGE, "replaceWith", "Lkotlin/ReplaceWith;", "expression", "Sink", "imports", "kotlinx.io.Sink", "ktor-io"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class BytePacketBuilderKt {
    @Deprecated(message = DeprecationKt.IO_DEPRECATION_MESSAGE, replaceWith = @ReplaceWith(expression = "Sink", imports = {"kotlinx.io.Sink"}))
    public static /* synthetic */ void BytePacketBuilder$annotations() {
    }

    public static /* synthetic */ void getSize$annotations(Sink sink) {
    }

    public static final int getSize(Sink sink) {
        Intrinsics.checkNotNullParameter(sink, "<this>");
        return (int) sink.getBufferField().getSizeMut();
    }

    public static final Sink BytePacketBuilder() {
        return new Buffer();
    }

    public static /* synthetic */ void append$default(Sink sink, CharSequence charSequence, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = charSequence.length();
        }
        append(sink, charSequence, i, i2);
    }

    public static final void append(Sink sink, CharSequence value, int i, int i2) {
        Intrinsics.checkNotNullParameter(sink, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        StringsKt.writeText$default(sink, value, i, i2, (Charset) null, 8, (Object) null);
    }

    public static final Source build(Sink sink) {
        Intrinsics.checkNotNullParameter(sink, "<this>");
        return sink.getBufferField();
    }

    public static /* synthetic */ void writeFully$default(Sink sink, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length - i;
        }
        writeFully(sink, bArr, i, i2);
    }

    public static final void writeFully(Sink sink, byte[] buffer, int i, int i2) {
        Intrinsics.checkNotNullParameter(sink, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        sink.write(buffer, i, i2 + i);
    }

    public static final void writePacket(Sink sink, Source packet) {
        Intrinsics.checkNotNullParameter(sink, "<this>");
        Intrinsics.checkNotNullParameter(packet, "packet");
        sink.transferFrom(packet);
    }
}
