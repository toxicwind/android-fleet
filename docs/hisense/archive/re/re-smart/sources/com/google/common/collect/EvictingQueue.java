package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtCompatible
public final class EvictingQueue<E> extends ForwardingQueue<E> implements Serializable {
    public static final long serialVersionUID = 0;
    public final Queue<E> delegate;

    @VisibleForTesting
    public final int maxSize;

    public EvictingQueue(int i) {
        Preconditions.checkArgument(i >= 0, "maxSize (%s) must >= 0", i);
        this.delegate = new ArrayDeque(i);
        this.maxSize = i;
    }

    public static <E> EvictingQueue<E> create(int i) {
        return new EvictingQueue<>(i);
    }

    @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Queue
    @CanIgnoreReturnValue
    public boolean add(E e2) {
        if (e2 == null) {
            throw new NullPointerException();
        }
        if (this.maxSize == 0) {
            return true;
        }
        if (size() == this.maxSize) {
            this.delegate.remove();
        }
        this.delegate.add(e2);
        return true;
    }

    @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
    @CanIgnoreReturnValue
    public boolean addAll(Collection<? extends E> collection) {
        int size = collection.size();
        if (size < this.maxSize) {
            return standardAddAll(collection);
        }
        clear();
        return Iterables.addAll(this, Iterables.skip(collection, size - this.maxSize));
    }

    @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        Queue<E> queueDelegate = delegate();
        if (obj != null) {
            return queueDelegate.contains(obj);
        }
        throw new NullPointerException();
    }

    @Override // com.google.common.collect.ForwardingQueue, java.util.Queue
    @CanIgnoreReturnValue
    public boolean offer(E e2) {
        return add(e2);
    }

    public int remainingCapacity() {
        return this.maxSize - size();
    }

    @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
    @CanIgnoreReturnValue
    public boolean remove(Object obj) {
        Queue<E> queueDelegate = delegate();
        if (obj != null) {
            return queueDelegate.remove(obj);
        }
        throw new NullPointerException();
    }

    @Override // com.google.common.collect.ForwardingQueue, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public Queue<E> delegate() {
        return this.delegate;
    }
}
