package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.collections.IntIterator;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ArrayIterators.kt */
/* JADX INFO: loaded from: classes.dex */
final class ArrayIntIterator extends IntIterator {
    private final int[] array;
    private int index;

    public ArrayIntIterator(@NotNull int[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        this.array = array;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.index < this.array.length;
    }

    @Override // kotlin.collections.IntIterator
    public int nextInt() {
        try {
            int[] iArr = this.array;
            int i = this.index;
            this.index = i + 1;
            return iArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.index--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
