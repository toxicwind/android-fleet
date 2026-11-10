package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtIncompatible
public final class JdkFutureAdapters {

    private static class ListenableFutureAdapter<V> extends ForwardingFuture<V> implements ListenableFuture<V> {
        public final Executor adapterExecutor;
        public final Future<V> delegate;
        public final ExecutionList executionList;
        public final AtomicBoolean hasListeners;
        public static final ThreadFactory threadFactory = new ThreadFactoryBuilder().setDaemon(true).setNameFormat("ListenableFutureAdapter-thread-%d").build();
        public static final Executor defaultAdapterExecutor = Executors.newCachedThreadPool(threadFactory);

        public ListenableFutureAdapter(Future<V> future) {
            this(future, defaultAdapterExecutor);
        }

        @Override // com.google.common.util.concurrent.ListenableFuture
        public void addListener(Runnable runnable, Executor executor) {
            this.executionList.add(runnable, executor);
            if (this.hasListeners.compareAndSet(false, true)) {
                if (this.delegate.isDone()) {
                    this.executionList.execute();
                } else {
                    this.adapterExecutor.execute(new Runnable() { // from class: com.google.common.util.concurrent.JdkFutureAdapters.ListenableFutureAdapter.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                Uninterruptibles.getUninterruptibly(ListenableFutureAdapter.this.delegate);
                            } catch (Throwable unused) {
                            }
                            ListenableFutureAdapter.this.executionList.execute();
                        }
                    });
                }
            }
        }

        public ListenableFutureAdapter(Future<V> future, Executor executor) {
            this.executionList = new ExecutionList();
            this.hasListeners = new AtomicBoolean(false);
            if (future == null) {
                throw new NullPointerException();
            }
            this.delegate = future;
            if (executor == null) {
                throw new NullPointerException();
            }
            this.adapterExecutor = executor;
        }

        @Override // com.google.common.util.concurrent.ForwardingFuture, com.google.common.collect.ForwardingObject
        public Future<V> delegate() {
            return this.delegate;
        }
    }

    public static <V> ListenableFuture<V> listenInPoolThread(Future<V> future) {
        return future instanceof ListenableFuture ? (ListenableFuture) future : new ListenableFutureAdapter(future, ListenableFutureAdapter.defaultAdapterExecutor);
    }

    public static <V> ListenableFuture<V> listenInPoolThread(Future<V> future, Executor executor) {
        if (executor != null) {
            if (future instanceof ListenableFuture) {
                return (ListenableFuture) future;
            }
            return new ListenableFutureAdapter(future, executor);
        }
        throw new NullPointerException();
    }
}
