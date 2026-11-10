package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.collections.CharIterator;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ArrayIterators.kt */
/* JADX INFO: loaded from: classes.dex */
final class ArrayCharIterator extends CharIterator {
    private final char[] array;
    private int index;

    public ArrayCharIterator(@NotNull char[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        this.array = array;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.index < this.array.length;
    }

    @Override // kotlin.collections.CharIterator
    public char nextChar() {
        try {
            char[] cArr = this.array;
            int i = this.index;
            this.index = i + 1;
            return cArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.index--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
