package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
@Beta
public final class ExecutionSequencer {
    public final AtomicReference<ListenableFuture<Void>> ref = new AtomicReference<>(ImmediateFuture.NULL);
    public ThreadConfinedTaskQueue latestTaskQueue = new ThreadConfinedTaskQueue(null);

    enum RunningState {
        NOT_RUN,
        CANCELLED,
        STARTED
    }

    private static final class TaskNonReentrantExecutor extends AtomicReference<RunningState> implements Executor, Runnable {
        public Executor delegate;
        public ExecutionSequencer sequencer;
        public Thread submitting;
        public Runnable task;

        /* JADX INFO: Access modifiers changed from: private */
        public boolean trySetCancelled() {
            return compareAndSet(RunningState.NOT_RUN, RunningState.CANCELLED);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean trySetStarted() {
            return compareAndSet(RunningState.NOT_RUN, RunningState.STARTED);
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            if (get() == RunningState.CANCELLED) {
                this.delegate = null;
                this.sequencer = null;
                return;
            }
            this.submitting = Thread.currentThread();
            try {
                ThreadConfinedTaskQueue threadConfinedTaskQueue = this.sequencer.latestTaskQueue;
                if (threadConfinedTaskQueue.thread == this.submitting) {
                    this.sequencer = null;
                    Preconditions.checkState(threadConfinedTaskQueue.nextTask == null);
                    threadConfinedTaskQueue.nextTask = runnable;
                    threadConfinedTaskQueue.nextExecutor = this.delegate;
                    this.delegate = null;
                } else {
                    Executor executor = this.delegate;
                    this.delegate = null;
                    this.task = runnable;
                    executor.execute(this);
                }
            } finally {
                this.submitting = null;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            Thread threadCurrentThread = Thread.currentThread();
            AnonymousClass1 anonymousClass1 = null;
            if (threadCurrentThread != this.submitting) {
                Runnable runnable = this.task;
                this.task = null;
                runnable.run();
                return;
            }
            ThreadConfinedTaskQueue threadConfinedTaskQueue = new ThreadConfinedTaskQueue(anonymousClass1);
            threadConfinedTaskQueue.thread = threadCurrentThread;
            this.sequencer.latestTaskQueue = threadConfinedTaskQueue;
            this.sequencer = null;
            try {
                Runnable runnable2 = this.task;
                this.task = null;
                runnable2.run();
                while (true) {
                    Runnable runnable3 = threadConfinedTaskQueue.nextTask;
                    boolean z = true;
                    boolean z2 = runnable3 != null;
                    Executor executor = threadConfinedTaskQueue.nextExecutor;
                    if (executor == null) {
                        z = false;
                    }
                    if (!z || !z2) {
                        threadConfinedTaskQueue.thread = null;
                        return;
                    } else {
                        threadConfinedTaskQueue.nextTask = null;
                        threadConfinedTaskQueue.nextExecutor = null;
                        executor.execute(runnable3);
                    }
                }
            } catch (Throwable th) {
                threadConfinedTaskQueue.thread = null;
                throw th;
            }
        }

        public TaskNonReentrantExecutor(Executor executor, ExecutionSequencer executionSequencer) {
            super(RunningState.NOT_RUN);
            this.delegate = executor;
            this.sequencer = executionSequencer;
        }
    }

    private static final class ThreadConfinedTaskQueue {
        public Executor nextExecutor;
        public Runnable nextTask;
        public Thread thread;

        public ThreadConfinedTaskQueue() {
        }

        public /* synthetic */ ThreadConfinedTaskQueue(AnonymousClass1 anonymousClass1) {
        }
    }

    public static ExecutionSequencer create() {
        return new ExecutionSequencer();
    }

    public <T> ListenableFuture<T> submit(final Callable<T> callable, Executor executor) {
        if (callable == null) {
            throw new NullPointerException();
        }
        if (executor != null) {
            return submitAsync(new AsyncCallable<T>(this) { // from class: com.google.common.util.concurrent.ExecutionSequencer.1
                @Override // com.google.common.util.concurrent.AsyncCallable
                public ListenableFuture<T> call() {
                    return Futures.immediateFuture(callable.call());
                }

                public String toString() {
                    return callable.toString();
                }
            }, executor);
        }
        throw new NullPointerException();
    }

    public <T> ListenableFuture<T> submitAsync(final AsyncCallable<T> asyncCallable, Executor executor) {
        if (asyncCallable == null) {
            throw new NullPointerException();
        }
        if (executor == null) {
            throw new NullPointerException();
        }
        final TaskNonReentrantExecutor taskNonReentrantExecutor = new TaskNonReentrantExecutor(executor, this);
        AsyncCallable<T> asyncCallable2 = new AsyncCallable<T>(this) { // from class: com.google.common.util.concurrent.ExecutionSequencer.2
            @Override // com.google.common.util.concurrent.AsyncCallable
            public ListenableFuture<T> call() {
                return !taskNonReentrantExecutor.trySetStarted() ? new ImmediateFuture.ImmediateCancelledFuture() : asyncCallable.call();
            }

            public String toString() {
                return asyncCallable.toString();
            }
        };
        final SettableFuture settableFuture = new SettableFuture();
        final ListenableFuture<Void> andSet = this.ref.getAndSet(settableFuture);
        final TrustedListenableFutureTask trustedListenableFutureTask = new TrustedListenableFutureTask(asyncCallable2);
        andSet.addListener(trustedListenableFutureTask, taskNonReentrantExecutor);
        final ListenableFuture<T> listenableFutureNonCancellationPropagating = Futures.nonCancellationPropagating(trustedListenableFutureTask);
        Runnable runnable = new Runnable(this) { // from class: com.google.common.util.concurrent.ExecutionSequencer.3
            @Override // java.lang.Runnable
            public void run() {
                if (trustedListenableFutureTask.isDone()) {
                    settableFuture.setFuture(andSet);
                } else if (listenableFutureNonCancellationPropagating.isCancelled() && taskNonReentrantExecutor.trySetCancelled()) {
                    trustedListenableFutureTask.cancel(false);
                }
            }
        };
        listenableFutureNonCancellationPropagating.addListener(runnable, DirectExecutor.INSTANCE);
        trustedListenableFutureTask.addListener(runnable, DirectExecutor.INSTANCE);
        return listenableFutureNonCancellationPropagating;
    }
}
