package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public abstract class TransformedListIterator<F, T> extends TransformedIterator<F, T> implements ListIterator<T> {
    public TransformedListIterator(ListIterator<? extends F> listIterator) {
        super(listIterator);
    }

    private ListIterator<? extends F> backingIterator() {
        return (ListIterator) this.backingIterator;
    }

    @Override // java.util.ListIterator
    public void add(T t) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return ((ListIterator) this.backingIterator).hasPrevious();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return ((ListIterator) this.backingIterator).nextIndex();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.ListIterator
    public final T previous() {
        return (T) transform(((ListIterator) this.backingIterator).previous());
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return ((ListIterator) this.backingIterator).previousIndex();
    }

    public void set(T t) {
        throw new UnsupportedOperationException();
    }
}
