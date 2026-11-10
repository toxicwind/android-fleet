package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public abstract class TransformedIterator<F, T> implements Iterator<T> {
    public final Iterator<? extends F> backingIterator;

    public TransformedIterator(Iterator<? extends F> it) {
        if (it == null) {
            throw new NullPointerException();
        }
        this.backingIterator = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.backingIterator.hasNext();
    }

    @Override // java.util.Iterator
    public final T next() {
        return transform(this.backingIterator.next());
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.backingIterator.remove();
    }

    public abstract T transform(F f2);
}
