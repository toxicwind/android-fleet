package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.collections.FloatIterator;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ArrayIterators.kt */
/* JADX INFO: loaded from: classes.dex */
final class ArrayFloatIterator extends FloatIterator {
    private final float[] array;
    private int index;

    public ArrayFloatIterator(@NotNull float[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        this.array = array;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.index < this.array.length;
    }

    @Override // kotlin.collections.FloatIterator
    public float nextFloat() {
        try {
            float[] fArr = this.array;
            int i = this.index;
            this.index = i + 1;
            return fArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.index--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
