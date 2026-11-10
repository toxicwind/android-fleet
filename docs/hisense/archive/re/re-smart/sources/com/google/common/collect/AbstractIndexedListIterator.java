package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public abstract class AbstractIndexedListIterator<E> extends UnmodifiableListIterator<E> {
    public int position;
    public final int size;

    public AbstractIndexedListIterator(int i) {
        Preconditions.checkPositionIndex(0, i, FirebaseAnalytics.Param.INDEX);
        this.size = i;
        this.position = 0;
    }

    public abstract E get(int i);

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return this.position < this.size;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.position > 0;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i = this.position;
        this.position = i + 1;
        return get(i);
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.position;
    }

    @Override // java.util.ListIterator
    public final E previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        int i = this.position - 1;
        this.position = i;
        return get(i);
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.position - 1;
    }

    public AbstractIndexedListIterator(int i, int i2) {
        Preconditions.checkPositionIndex(i2, i, FirebaseAnalytics.Param.INDEX);
        this.size = i;
        this.position = i2;
    }
}
