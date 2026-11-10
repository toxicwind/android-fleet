package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public class ListenableFutureTask<V> extends FutureTask<V> implements ListenableFuture<V> {
    public final ExecutionList executionList;

    public ListenableFutureTask(Callable<V> callable) {
        super(callable);
        this.executionList = new ExecutionList();
    }

    public static <V> ListenableFutureTask<V> create(Callable<V> callable) {
        return new ListenableFutureTask<>(callable);
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(Runnable runnable, Executor executor) {
        this.executionList.add(runnable, executor);
    }

    @Override // java.util.concurrent.FutureTask
    public void done() {
        this.executionList.execute();
    }

    @Override // java.util.concurrent.FutureTask, java.util.concurrent.Future
    @CanIgnoreReturnValue
    public V get(long j, TimeUnit timeUnit) {
        long nanos = timeUnit.toNanos(j);
        return nanos <= OverflowAvoidingLockSupport.MAX_NANOSECONDS_THRESHOLD ? (V) super.get(j, timeUnit) : (V) super.get(Math.min(nanos, OverflowAvoidingLockSupport.MAX_NANOSECONDS_THRESHOLD), TimeUnit.NANOSECONDS);
    }

    public static <V> ListenableFutureTask<V> create(Runnable runnable, V v) {
        return new ListenableFutureTask<>(runnable, v);
    }

    public ListenableFutureTask(Runnable runnable, V v) {
        super(runnable, v);
        this.executionList = new ExecutionList();
    }
}
