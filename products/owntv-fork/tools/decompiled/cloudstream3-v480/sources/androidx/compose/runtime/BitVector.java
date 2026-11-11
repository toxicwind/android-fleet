package androidx.compose.runtime;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: SlotTable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nH\u0086\u0002J\u0019\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u000eH\u0086\u0002J\u000e\u0010\u0013\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\nJ\u000e\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\nJ%\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\n2\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0017H\u0082\bJ\u0016\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\nJ\b\u0010\u001b\u001a\u00020\u001cH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u001d"}, d2 = {"Landroidx/compose/runtime/BitVector;", "", "<init>", "()V", "first", "", "second", "others", "", ContentDisposition.Parameters.Size, "", "getSize", "()I", "get", "", "index", "set", "", ES6Iterator.VALUE_PROPERTY, "nextSet", "nextClear", "nextBit", "valueSelector", "Lkotlin/Function1;", "setRange", "start", TtmlNode.END, "toString", "", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class BitVector {
    public static final int $stable = 8;
    private long first;
    private long[] others = SlotTableKt.EmptyLongArray;
    private long second;

    public final int getSize() {
        return (this.others.length + 2) * 64;
    }

    public final boolean get(int index) {
        int i;
        if (index < 64) {
            return ((1 << index) & this.first) != 0;
        }
        if (index < 128) {
            return ((1 << (index - 64)) & this.second) != 0;
        }
        long[] jArr = this.others;
        int length = jArr.length;
        if (length != 0 && (i = (index / 64) - 2) < length) {
            return ((1 << (index % 64)) & jArr[i]) != 0;
        }
        return false;
    }

    public final void set(int index, boolean value) {
        if (index < 64) {
            this.first = ((value ? 1L : 0L) << index) | ((~(1 << index)) & this.first);
            return;
        }
        if (index < 128) {
            this.second = ((value ? 1L : 0L) << index) | ((~(1 << (index - 64))) & this.second);
            return;
        }
        int i = index / 64;
        int i2 = i - 2;
        int i3 = index % 64;
        long j = 1 << i3;
        long[] jArrCopyOf = this.others;
        if (i2 >= jArrCopyOf.length) {
            jArrCopyOf = Arrays.copyOf(jArrCopyOf, i - 1);
            Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "copyOf(...)");
            this.others = jArrCopyOf;
        }
        jArrCopyOf[i2] = ((value ? 1L : 0L) << i3) | ((~j) & jArrCopyOf[i2]);
    }

    private final int nextBit(int index, Function1<? super Long, Long> valueSelector) {
        int iNumberOfTrailingZeros;
        if (index < 64 && (iNumberOfTrailingZeros = Long.numberOfTrailingZeros((valueSelector.invoke(Long.valueOf(this.first)).longValue() >>> index) << index)) < 64) {
            return iNumberOfTrailingZeros;
        }
        if (index < 128) {
            int i = index - 64;
            int iNumberOfTrailingZeros2 = Long.numberOfTrailingZeros((valueSelector.invoke(Long.valueOf(this.second)).longValue() >>> i) << i);
            if (iNumberOfTrailingZeros2 < 64) {
                return iNumberOfTrailingZeros2 + 64;
            }
        }
        int iMax = Math.max(index, 128);
        int i2 = (iMax / 64) - 2;
        long[] jArr = this.others;
        int length = jArr.length;
        for (int i3 = i2; i3 < length; i3++) {
            long jLongValue = valueSelector.invoke(Long.valueOf(jArr[i3])).longValue();
            if (i3 == i2) {
                int i4 = iMax % 64;
                jLongValue = (jLongValue >>> i4) << i4;
            }
            int iNumberOfTrailingZeros3 = Long.numberOfTrailingZeros(jLongValue);
            if (iNumberOfTrailingZeros3 < 64) {
                return (i3 * 64) + 128 + iNumberOfTrailingZeros3;
            }
        }
        return Integer.MAX_VALUE;
    }

    public final void setRange(int start, int end) {
        long j = start < end ? -1L : 0L;
        this.first = (((((long) (start < 64 ? 1 : 0)) * j) >>> (64 - (Math.min(64, end) - start))) << start) | this.first;
        if (end > 64) {
            int iMax = Math.max(start, 64);
            this.second = (((j * ((long) (iMax < 128 ? 1 : 0))) >>> (128 - (Math.min(128, end) - iMax))) << iMax) | this.second;
            if (end > 128) {
                for (int iMax2 = Math.max(iMax, 128); iMax2 < end; iMax2++) {
                    set(iMax2, true);
                }
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("BitVector [");
        int size = getSize();
        boolean z = true;
        for (int i = 0; i < size; i++) {
            if (get(i)) {
                if (!z) {
                    sb.append(", ");
                }
                sb.append(i);
                z = false;
            }
        }
        sb.append(AbstractJsonLexerKt.END_LIST);
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    public final int nextSet(int index) {
        int iNumberOfTrailingZeros;
        if (index < 64 && (iNumberOfTrailingZeros = Long.numberOfTrailingZeros((this.first >>> index) << index)) < 64) {
            return iNumberOfTrailingZeros;
        }
        if (index < 128) {
            int i = index - 64;
            int iNumberOfTrailingZeros2 = Long.numberOfTrailingZeros((this.second >>> i) << i);
            if (iNumberOfTrailingZeros2 < 64) {
                return iNumberOfTrailingZeros2 + 64;
            }
        }
        int iMax = Math.max(index, 128);
        int i2 = (iMax / 64) - 2;
        long[] jArr = this.others;
        int length = jArr.length;
        for (int i3 = i2; i3 < length; i3++) {
            long j = jArr[i3];
            if (i3 == i2) {
                int i4 = iMax % 64;
                j = (j >>> i4) << i4;
            }
            int iNumberOfTrailingZeros3 = Long.numberOfTrailingZeros(j);
            if (iNumberOfTrailingZeros3 < 64) {
                return (i3 * 64) + 128 + iNumberOfTrailingZeros3;
            }
        }
        return Integer.MAX_VALUE;
    }

    public final int nextClear(int index) {
        int iNumberOfTrailingZeros;
        if (index < 64 && (iNumberOfTrailingZeros = Long.numberOfTrailingZeros(((~this.first) >>> index) << index)) < 64) {
            return iNumberOfTrailingZeros;
        }
        if (index < 128) {
            int i = index - 64;
            int iNumberOfTrailingZeros2 = Long.numberOfTrailingZeros(((~this.second) >>> i) << i);
            if (iNumberOfTrailingZeros2 < 64) {
                return iNumberOfTrailingZeros2 + 64;
            }
        }
        int iMax = Math.max(index, 128);
        int i2 = (iMax / 64) - 2;
        long[] jArr = this.others;
        int length = jArr.length;
        for (int i3 = i2; i3 < length; i3++) {
            long j = ~jArr[i3];
            if (i3 == i2) {
                int i4 = iMax % 64;
                j = (j >>> i4) << i4;
            }
            int iNumberOfTrailingZeros3 = Long.numberOfTrailingZeros(j);
            if (iNumberOfTrailingZeros3 < 64) {
                return (i3 * 64) + 128 + iNumberOfTrailingZeros3;
            }
        }
        return Integer.MAX_VALUE;
    }
}
