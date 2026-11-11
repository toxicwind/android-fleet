package j$.util;

import java.io.Serializable;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/* JADX INFO: loaded from: classes3.dex */
final class Comparators$NullComparator implements java.util.Comparator, Serializable, Comparator {
    private static final long serialVersionUID = -7569533591570686392L;
    private final boolean nullFirst;
    private final java.util.Comparator real;

    @Override // java.util.Comparator, j$.util.Comparator
    public /* synthetic */ java.util.Comparator thenComparing(Function function) {
        return Comparator.EL.thenComparing(this, Comparator.CC.comparing(function));
    }

    @Override // java.util.Comparator, j$.util.Comparator
    public /* synthetic */ java.util.Comparator thenComparing(Function function, java.util.Comparator comparator) {
        return Comparator.EL.thenComparing(this, Comparator.CC.comparing(function, comparator));
    }

    @Override // java.util.Comparator, j$.util.Comparator
    public /* synthetic */ java.util.Comparator thenComparingDouble(ToDoubleFunction toDoubleFunction) {
        return Comparator.EL.thenComparing(this, Comparator.CC.comparingDouble(toDoubleFunction));
    }

    @Override // java.util.Comparator, j$.util.Comparator
    public /* synthetic */ java.util.Comparator thenComparingInt(ToIntFunction toIntFunction) {
        return Comparator.EL.thenComparing(this, Comparator.CC.comparingInt(toIntFunction));
    }

    @Override // java.util.Comparator, j$.util.Comparator
    public /* synthetic */ java.util.Comparator thenComparingLong(ToLongFunction toLongFunction) {
        return Comparator.EL.thenComparing(this, Comparator.CC.comparingLong(toLongFunction));
    }

    Comparators$NullComparator(boolean z, java.util.Comparator comparator) {
        this.nullFirst = z;
        this.real = comparator;
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        if (obj == null) {
            if (obj2 == null) {
                return 0;
            }
            return this.nullFirst ? -1 : 1;
        }
        if (obj2 == null) {
            return this.nullFirst ? 1 : -1;
        }
        java.util.Comparator comparator = this.real;
        if (comparator == null) {
            return 0;
        }
        return comparator.compare(obj, obj2);
    }

    @Override // java.util.Comparator, j$.util.Comparator
    public java.util.Comparator thenComparing(java.util.Comparator comparator) {
        Objects.requireNonNull(comparator);
        boolean z = this.nullFirst;
        java.util.Comparator comparator2 = this.real;
        if (comparator2 != null) {
            comparator = Comparator.EL.thenComparing(comparator2, comparator);
        }
        return new Comparators$NullComparator(z, comparator);
    }

    @Override // java.util.Comparator, j$.util.Comparator
    public java.util.Comparator reversed() {
        boolean z = !this.nullFirst;
        java.util.Comparator comparator = this.real;
        return new Comparators$NullComparator(z, comparator == null ? null : Comparator.EL.reversed(comparator));
    }
}
