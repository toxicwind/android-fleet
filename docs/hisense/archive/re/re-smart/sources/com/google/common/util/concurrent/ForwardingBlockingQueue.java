package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ForwardingQueue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
@CanIgnoreReturnValue
@GwtIncompatible
public abstract class ForwardingBlockingQueue<E> extends ForwardingQueue<E> implements BlockingQueue<E> {
    @Override // com.google.common.collect.ForwardingQueue, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public abstract BlockingQueue<E> delegate();

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> collection, int i) {
        return delegate().drainTo(collection, i);
    }

    @Override // java.util.concurrent.BlockingQueue
    public boolean offer(E e2, long j, TimeUnit timeUnit) {
        return delegate().offer(e2, j, timeUnit);
    }

    @Override // java.util.concurrent.BlockingQueue
    public E poll(long j, TimeUnit timeUnit) {
        return delegate().poll(j, timeUnit);
    }

    @Override // java.util.concurrent.BlockingQueue
    public void put(E e2) throws InterruptedException {
        delegate().put(e2);
    }

    @Override // java.util.concurrent.BlockingQueue
    public int remainingCapacity() {
        return delegate().remainingCapacity();
    }

    @Override // java.util.concurrent.BlockingQueue
    public E take() {
        return delegate().take();
    }

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> collection) {
        return delegate().drainTo(collection);
    }
}
