package kotlin.ranges;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Unexpected interfaces in signature: [java.lang.Object<java.lang.Integer>] */
/* JADX INFO: compiled from: Ranges.kt */
/* JADX INFO: loaded from: classes.dex */
public final class IntRange extends IntProgression {
    public IntRange(int i, int i2) {
        super(i, i2, 1);
    }

    public boolean isEmpty() {
        return getFirst() > getLast();
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof IntRange) {
            if (!isEmpty() || !((IntRange) obj).isEmpty()) {
                IntRange intRange = (IntRange) obj;
                if (getFirst() != intRange.getFirst() || getLast() != intRange.getLast()) {
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return getLast() + (getFirst() * 31);
    }

    @NotNull
    public String toString() {
        return getFirst() + ".." + getLast();
    }

    static {
        new IntRange(1, 0);
    }
}
