package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
@CanIgnoreReturnValue
@GwtIncompatible
public abstract class WrappingExecutorService implements ExecutorService {
    public final ExecutorService delegate;

    public WrappingExecutorService(ExecutorService executorService) {
        if (executorService == null) {
            throw new NullPointerException();
        }
        this.delegate = executorService;
    }

    private <T> ImmutableList<Callable<T>> wrapTasks(Collection<? extends Callable<T>> collection) {
        ImmutableList.Builder builder = ImmutableList.builder();
        Iterator<? extends Callable<T>> it = collection.iterator();
        while (it.hasNext()) {
            builder.add(wrapTask(it.next()));
        }
        return builder.build();
    }

    @Override // java.util.concurrent.ExecutorService
    public final boolean awaitTermination(long j, TimeUnit timeUnit) {
        return this.delegate.awaitTermination(j, timeUnit);
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        this.delegate.execute(wrapTask(runnable));
    }

    @Override // java.util.concurrent.ExecutorService
    public final <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) {
        return this.delegate.invokeAll(wrapTasks(collection));
    }

    @Override // java.util.concurrent.ExecutorService
    public final <T> T invokeAny(Collection<? extends Callable<T>> collection) {
        return (T) this.delegate.invokeAny(wrapTasks(collection));
    }

    @Override // java.util.concurrent.ExecutorService
    public final boolean isShutdown() {
        return this.delegate.isShutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public final boolean isTerminated() {
        return this.delegate.isTerminated();
    }

    @Override // java.util.concurrent.ExecutorService
    public final void shutdown() {
        this.delegate.shutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public final List<Runnable> shutdownNow() {
        return this.delegate.shutdownNow();
    }

    @Override // java.util.concurrent.ExecutorService
    public final <T> Future<T> submit(Callable<T> callable) {
        ExecutorService executorService = this.delegate;
        if (callable != null) {
            return executorService.submit(wrapTask(callable));
        }
        throw new NullPointerException();
    }

    public Runnable wrapTask(Runnable runnable) {
        final Callable callableWrapTask = wrapTask(Executors.callable(runnable, null));
        return new Runnable(this) { // from class: com.google.common.util.concurrent.WrappingExecutorService.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    callableWrapTask.call();
                } catch (Exception e2) {
                    Throwables.throwIfUnchecked(e2);
                    throw new RuntimeException(e2);
                }
            }
        };
    }

    public abstract <T> Callable<T> wrapTask(Callable<T> callable);

    @Override // java.util.concurrent.ExecutorService
    public final <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) {
        return this.delegate.invokeAll(wrapTasks(collection), j, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public final <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) {
        return (T) this.delegate.invokeAny(wrapTasks(collection), j, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public final Future<?> submit(Runnable runnable) {
        return this.delegate.submit(wrapTask(runnable));
    }

    @Override // java.util.concurrent.ExecutorService
    public final <T> Future<T> submit(Runnable runnable, T t) {
        return this.delegate.submit(wrapTask(runnable), t);
    }
}
