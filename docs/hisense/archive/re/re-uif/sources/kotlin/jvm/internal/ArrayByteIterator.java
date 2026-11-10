package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.collections.ByteIterator;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ArrayIterators.kt */
/* JADX INFO: loaded from: classes.dex */
final class ArrayByteIterator extends ByteIterator {
    private final byte[] array;
    private int index;

    public ArrayByteIterator(@NotNull byte[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        this.array = array;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.index < this.array.length;
    }

    @Override // kotlin.collections.ByteIterator
    public byte nextByte() {
        try {
            byte[] bArr = this.array;
            int i = this.index;
            this.index = i + 1;
            return bArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.index--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
