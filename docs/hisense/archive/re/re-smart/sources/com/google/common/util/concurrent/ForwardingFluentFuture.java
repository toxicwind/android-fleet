package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public final class ForwardingFluentFuture<V> extends FluentFuture<V> {
    public final ListenableFuture<V> delegate;

    public ForwardingFluentFuture(ListenableFuture<V> listenableFuture) {
        if (listenableFuture == null) {
            throw new NullPointerException();
        }
        this.delegate = listenableFuture;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture, com.google.common.util.concurrent.ListenableFuture
    public void addListener(Runnable runnable, Executor executor) {
        this.delegate.addListener(runnable, executor);
    }

    @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
    public boolean cancel(boolean z) {
        return this.delegate.cancel(z);
    }

    @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
    public V get() {
        return this.delegate.get();
    }

    @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
    public boolean isCancelled() {
        return this.delegate.isCancelled();
    }

    @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
    public boolean isDone() {
        return this.delegate.isDone();
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public String toString() {
        return this.delegate.toString();
    }

    @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
    public V get(long j, TimeUnit timeUnit) {
        return this.delegate.get(j, timeUnit);
    }
}
