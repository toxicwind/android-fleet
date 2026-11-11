package io.ktor.utils.io.core;

import androidx.exifinterface.media.ExifInterface;
import io.ktor.http.ContentDisposition;
import io.ktor.http.ContentType;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Memory.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a/\u0010\u0006\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0004\b\u0006\u0010\u0007\u001a!\u0010\u000b\u001a\u00020\n*\u00020\u00042\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u0001¢\u0006\u0004\b\u000b\u0010\f*8\b\u0007\u0010\u0015\"\u00020\u00042\u00020\u0004B*\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u001c\b\u0010\u0012\u0018\b\u000bB\u0014\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0006\b\u0014\u0012\u0002\b\f¨\u0006\u0016"}, d2 = {ExifInterface.GPS_DIRECTION_TRUE, "", ContentDisposition.Parameters.Size, "Lkotlin/Function1;", "", "block", "withMemory", "(ILkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "index", ES6Iterator.VALUE_PROPERTY, "", "storeIntAt", "([BII)V", "Lkotlin/Deprecated;", ContentType.Message.TYPE, "ByteArray instead", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "ByteArray", "imports", "Memory", "ktor-io"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class MemoryKt {
    @Deprecated(message = "ByteArray instead", replaceWith = @ReplaceWith(expression = "ByteArray", imports = {}))
    public static /* synthetic */ void Memory$annotations() {
    }

    public static final <T> T withMemory(int i, Function1<? super byte[], ? extends T> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        return block.invoke(new byte[i]);
    }

    public static final void storeIntAt(byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        bArr[i] = (byte) (i2 >> 24);
        bArr[i + 1] = (byte) (i2 >> 16);
        bArr[i + 2] = (byte) (i2 >> 8);
        bArr[i + 3] = (byte) i2;
    }
}
