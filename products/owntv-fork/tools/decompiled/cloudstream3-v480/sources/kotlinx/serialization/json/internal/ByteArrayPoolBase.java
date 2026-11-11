package kotlinx.serialization.json.internal;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ArrayPools.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001B\t\bF¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\bH\u0084\u0080\u0004J\u0012\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006H\u0084\u0080\u0004R\u0015\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\u00020\bX\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlinx/serialization/json/internal/ByteArrayPoolBase;", "", "<init>", "()V", "arrays", "Lkotlin/collections/ArrayDeque;", "", "bytesTotal", "", "take", ContentDisposition.Parameters.Size, "releaseImpl", "", "array", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class ByteArrayPoolBase {
    private final ArrayDeque<byte[]> arrays = new ArrayDeque<>();
    private int bytesTotal;

    protected final byte[] take(int size) {
        byte[] bArrRemoveLastOrNull;
        synchronized (this) {
            bArrRemoveLastOrNull = this.arrays.removeLastOrNull();
            if (bArrRemoveLastOrNull != null) {
                this.bytesTotal -= bArrRemoveLastOrNull.length / 2;
            } else {
                bArrRemoveLastOrNull = null;
            }
        }
        return bArrRemoveLastOrNull == null ? new byte[size] : bArrRemoveLastOrNull;
    }

    protected final void releaseImpl(byte[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        synchronized (this) {
            if (this.bytesTotal + array.length < ArrayPoolsKt.MAX_CHARS_IN_POOL) {
                this.bytesTotal += array.length / 2;
                this.arrays.addLast(array);
            }
            Unit unit = Unit.INSTANCE;
        }
    }
}
