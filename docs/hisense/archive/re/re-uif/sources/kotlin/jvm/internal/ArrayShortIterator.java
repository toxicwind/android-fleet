package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.collections.ShortIterator;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ArrayIterators.kt */
/* JADX INFO: loaded from: classes.dex */
final class ArrayShortIterator extends ShortIterator {
    private final short[] array;
    private int index;

    public ArrayShortIterator(@NotNull short[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        this.array = array;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.index < this.array.length;
    }

    @Override // kotlin.collections.ShortIterator
    public short nextShort() {
        try {
            short[] sArr = this.array;
            int i = this.index;
            this.index = i + 1;
            return sArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.index--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
