package kotlin.comparisons;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.exoplayer.upstream.CmcdData;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Comparisons.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u0012\u0012\u0004\u0012\u0002H\u00010\u0002j\b\u0012\u0004\u0012\u0002H\u0001`\u0003B!\bF\u0012\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0002j\b\u0012\u0004\u0012\u00028\u0000`\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00028\u00002\u0006\u0010\r\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\u000eJ\u001f\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0002j\b\u0012\u0004\u0012\u00028\u0000`\u0003H\u0086\u0080\u0004¢\u0006\u0002\u0010\bR'\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0002j\b\u0012\u0004\u0012\u00028\u0000`\u0003X\u0086\u0084\b¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b¨\u0006\u0010"}, d2 = {"Lkotlin/comparisons/ReversedComparator;", ExifInterface.GPS_DIRECTION_TRUE, "Ljava/util/Comparator;", "Lkotlin/Comparator;", "comparator", "<init>", "(Ljava/util/Comparator;)V", "getComparator", "()Ljava/util/Comparator;", "Ljava/util/Comparator;", "compare", "", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "(Ljava/lang/Object;Ljava/lang/Object;)I", "reversed", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class ReversedComparator<T> implements Comparator<T>, j$.util.Comparator {
    private final Comparator<T> comparator;

    @Override // java.util.Comparator, j$.util.Comparator
    public /* synthetic */ Comparator thenComparing(Comparator comparator) {
        return j$.util.Comparator.CC.$default$thenComparing(this, comparator);
    }

    @Override // java.util.Comparator, j$.util.Comparator
    public /* synthetic */ Comparator thenComparing(Function function) {
        return j$.util.Comparator.EL.thenComparing(this, j$.util.Comparator.CC.comparing(function));
    }

    @Override // java.util.Comparator, j$.util.Comparator
    public /* synthetic */ Comparator thenComparing(Function function, Comparator comparator) {
        return j$.util.Comparator.EL.thenComparing(this, j$.util.Comparator.CC.comparing(function, comparator));
    }

    @Override // java.util.Comparator, j$.util.Comparator
    public /* synthetic */ Comparator thenComparingDouble(ToDoubleFunction toDoubleFunction) {
        return j$.util.Comparator.EL.thenComparing(this, j$.util.Comparator.CC.comparingDouble(toDoubleFunction));
    }

    @Override // java.util.Comparator, j$.util.Comparator
    public /* synthetic */ Comparator thenComparingInt(ToIntFunction toIntFunction) {
        return j$.util.Comparator.EL.thenComparing(this, j$.util.Comparator.CC.comparingInt(toIntFunction));
    }

    @Override // java.util.Comparator, j$.util.Comparator
    public /* synthetic */ Comparator thenComparingLong(ToLongFunction toLongFunction) {
        return j$.util.Comparator.EL.thenComparing(this, j$.util.Comparator.CC.comparingLong(toLongFunction));
    }

    public ReversedComparator(Comparator<T> comparator) {
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        this.comparator = comparator;
    }

    public final Comparator<T> getComparator() {
        return this.comparator;
    }

    @Override // java.util.Comparator
    public int compare(T a, T b) {
        return this.comparator.compare(b, a);
    }

    @Override // java.util.Comparator, j$.util.Comparator
    public final Comparator<T> reversed() {
        return this.comparator;
    }
}
