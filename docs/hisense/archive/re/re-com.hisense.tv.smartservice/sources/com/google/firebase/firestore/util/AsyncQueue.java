package com.google.firebase.firestore.util;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.zzw;
import com.google.firebase.firestore.util.AsyncQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class AsyncQueue {
    public final ArrayList<TimerId> timerIdsToSkip = new ArrayList<>();
    public final ArrayList<DelayedTask> delayedTasks = new ArrayList<>();
    public final SynchronizedShutdownAwareExecutor executor = new SynchronizedShutdownAwareExecutor();

    /* JADX INFO: Access modifiers changed from: private */
    public class SynchronizedShutdownAwareExecutor implements Executor {
        public final ScheduledThreadPoolExecutor internalExecutor;
        public boolean isShuttingDown;
        public final Thread thread;

        public SynchronizedShutdownAwareExecutor() {
            DelayedStartFactory delayedStartFactory = new DelayedStartFactory(null);
            this.thread = java.util.concurrent.Executors.defaultThreadFactory().newThread(delayedStartFactory);
            this.thread.setName("FirestoreWorker");
            this.thread.setDaemon(true);
            this.thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: b.e.b.d.g.d
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public final void uncaughtException(Thread thread, Throwable th) {
                    this.f540a.a(thread, th);
                }
            });
            this.internalExecutor = new ScheduledThreadPoolExecutor(1, delayedStartFactory) { // from class: com.google.firebase.firestore.util.AsyncQueue.SynchronizedShutdownAwareExecutor.1
                @Override // java.util.concurrent.ThreadPoolExecutor
                public void afterExecute(Runnable runnable, Throwable th) {
                    super.afterExecute(runnable, th);
                    if (th == null && (runnable instanceof Future)) {
                        Future future = (Future) runnable;
                        try {
                            if (future.isDone()) {
                                future.get();
                            }
                        } catch (InterruptedException unused) {
                            Thread.currentThread().interrupt();
                        } catch (CancellationException unused2) {
                        } catch (ExecutionException e2) {
                            th = e2.getCause();
                        }
                    }
                    if (th != null) {
                        AsyncQueue.this.panic(th);
                    }
                }
            };
            this.internalExecutor.setKeepAliveTime(3L, TimeUnit.SECONDS);
            this.isShuttingDown = false;
        }

        public static /* synthetic */ void access$1000(SynchronizedShutdownAwareExecutor synchronizedShutdownAwareExecutor, int i) {
            synchronizedShutdownAwareExecutor.internalExecutor.setCorePoolSize(i);
        }

        public static /* synthetic */ void access$800(SynchronizedShutdownAwareExecutor synchronizedShutdownAwareExecutor) {
            synchronizedShutdownAwareExecutor.internalExecutor.shutdownNow();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized Task<Void> executeAndInitiateShutdown(final Runnable runnable) {
            if (isShuttingDown()) {
                zzw zzwVar = new zzw();
                zzwVar.zzb(null);
                return zzwVar;
            }
            Task<Void> taskExecuteAndReportResult = executeAndReportResult(new Callable() { // from class: b.e.b.d.g.f
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    AsyncQueue.SynchronizedShutdownAwareExecutor.a(runnable);
                    return null;
                }
            });
            this.isShuttingDown = true;
            return taskExecuteAndReportResult;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public <T> Task<T> executeAndReportResult(final Callable<T> callable) {
            final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            try {
                execute(new Runnable() { // from class: b.e.b.d.g.e
                    @Override // java.lang.Runnable
                    public final void run() {
                        AsyncQueue.SynchronizedShutdownAwareExecutor.a(taskCompletionSource, callable);
                    }
                });
            } catch (RejectedExecutionException unused) {
                Logger.warn(AsyncQueue.class.getSimpleName(), "Refused to enqueue task after panic", new Object[0]);
            }
            return taskCompletionSource.getTask();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized boolean isShuttingDown() {
            return this.isShuttingDown;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public synchronized ScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
            if (this.isShuttingDown) {
                return null;
            }
            return this.internalExecutor.schedule(runnable, j, timeUnit);
        }

        private void setCorePoolSize(int i) {
            this.internalExecutor.setCorePoolSize(i);
        }

        private void shutdownNow() {
            this.internalExecutor.shutdownNow();
        }

        public /* synthetic */ void a(Thread thread, Throwable th) {
            AsyncQueue.this.panic(th);
        }

        @Override // java.util.concurrent.Executor
        public synchronized void execute(Runnable runnable) {
            if (!this.isShuttingDown) {
                this.internalExecutor.execute(runnable);
            }
        }

        public void executeEvenAfterShutdown(Runnable runnable) {
            try {
                this.internalExecutor.execute(runnable);
            } catch (RejectedExecutionException unused) {
                Logger.warn(AsyncQueue.class.getSimpleName(), "Refused to enqueue task after panic", new Object[0]);
            }
        }

        private class DelayedStartFactory implements Runnable, ThreadFactory {
            public Runnable delegate;
            public final CountDownLatch latch = new CountDownLatch(1);

            public DelayedStartFactory() {
            }

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(@NonNull Runnable runnable) {
                Assert.hardAssert(this.delegate == null, "Only one thread may be created in an AsyncQueue.", new Object[0]);
                this.delegate = runnable;
                this.latch.countDown();
                return SynchronizedShutdownAwareExecutor.this.thread;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    this.latch.await();
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
                this.delegate.run();
            }

            public /* synthetic */ DelayedStartFactory(AnonymousClass1 anonymousClass1) {
            }
        }

        public static /* synthetic */ void a(TaskCompletionSource taskCompletionSource, Callable callable) {
            try {
                taskCompletionSource.setResult(callable.call());
            } catch (Exception e2) {
                taskCompletionSource.setException(e2);
                throw new RuntimeException(e2);
            }
        }

        public static /* synthetic */ Void a(Runnable runnable) {
            runnable.run();
            return null;
        }
    }

    public enum TimerId {
        ALL,
        LISTEN_STREAM_IDLE,
        LISTEN_STREAM_CONNECTION_BACKOFF,
        WRITE_STREAM_IDLE,
        WRITE_STREAM_CONNECTION_BACKOFF,
        HEALTH_CHECK_TIMEOUT,
        ONLINE_STATE_TIMEOUT,
        GARBAGE_COLLECTION,
        RETRY_TRANSACTION,
        CONNECTIVITY_ATTEMPT_TIMER,
        INDEX_BACKFILL
    }

    public static <TResult> Task<TResult> callTask(final Executor executor, final Callable<Task<TResult>> callable) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        executor.execute(new Runnable() { // from class: b.e.b.d.g.k
            @Override // java.lang.Runnable
            public final void run() {
                AsyncQueue.a(callable, executor, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    private DelayedTask createAndScheduleDelayedTask(TimerId timerId, long j, Runnable runnable) {
        DelayedTask delayedTask = new DelayedTask(timerId, System.currentTimeMillis() + j, runnable, null);
        delayedTask.start(j);
        return delayedTask;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeDelayedTask(DelayedTask delayedTask) {
        Assert.hardAssert(this.delayedTasks.remove(delayedTask), "Delayed task not found.", new Object[0]);
    }

    @VisibleForTesting
    public boolean containsDelayedTask(TimerId timerId) {
        Iterator<DelayedTask> it = this.delayedTasks.iterator();
        while (it.hasNext()) {
            if (it.next().timerId == timerId) {
                return true;
            }
        }
        return false;
    }

    public <T> Task<T> enqueue(Callable<T> callable) {
        return this.executor.executeAndReportResult(callable);
    }

    public DelayedTask enqueueAfterDelay(TimerId timerId, long j, Runnable runnable) {
        if (this.timerIdsToSkip.contains(timerId)) {
            j = 0;
        }
        DelayedTask delayedTaskCreateAndScheduleDelayedTask = createAndScheduleDelayedTask(timerId, j, runnable);
        this.delayedTasks.add(delayedTaskCreateAndScheduleDelayedTask);
        return delayedTaskCreateAndScheduleDelayedTask;
    }

    public void enqueueAndForget(Runnable runnable) {
        enqueue(runnable);
    }

    public void enqueueAndForgetEvenAfterShutdown(Runnable runnable) {
        this.executor.executeEvenAfterShutdown(runnable);
    }

    public Task<Void> enqueueAndInitiateShutdown(Runnable runnable) {
        return this.executor.executeAndInitiateShutdown(runnable);
    }

    public Executor getExecutor() {
        return this.executor;
    }

    public boolean isShuttingDown() {
        return this.executor.isShuttingDown();
    }

    public void panic(final Throwable th) {
        SynchronizedShutdownAwareExecutor.access$800(this.executor);
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: b.e.b.d.g.g
            @Override // java.lang.Runnable
            public final void run() {
                AsyncQueue.a(th);
                throw null;
            }
        });
    }

    @VisibleForTesting
    public void runDelayedTasksUntil(final TimerId timerId) throws InterruptedException {
        runSync(new Runnable() { // from class: b.e.b.d.g.j
            @Override // java.lang.Runnable
            public final void run() {
                this.f547a.a(timerId);
            }
        });
    }

    @VisibleForTesting
    public void runSync(final Runnable runnable) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(0);
        final Throwable[] thArr = new Throwable[1];
        enqueueAndForget(new Runnable() { // from class: b.e.b.d.g.b
            @Override // java.lang.Runnable
            public final void run() {
                AsyncQueue.a(runnable, thArr, semaphore);
            }
        });
        semaphore.acquire(1);
        if (thArr[0] != null) {
            throw new RuntimeException("Synchronous task failed", thArr[0]);
        }
    }

    public void shutdown() {
        SynchronizedShutdownAwareExecutor.access$1000(this.executor, 0);
    }

    @VisibleForTesting
    public void skipDelaysForTimerId(TimerId timerId) {
        this.timerIdsToSkip.add(timerId);
    }

    public void verifyIsCurrentThread() {
        Thread threadCurrentThread = Thread.currentThread();
        if (this.executor.thread == threadCurrentThread) {
            return;
        }
        Assert.fail("We are running on the wrong thread. Expected to be on the AsyncQueue thread %s/%d but was %s/%d", this.executor.thread.getName(), Long.valueOf(this.executor.thread.getId()), threadCurrentThread.getName(), Long.valueOf(threadCurrentThread.getId()));
        throw null;
    }

    public Task<Void> enqueue(final Runnable runnable) {
        return enqueue(new Callable() { // from class: b.e.b.d.g.c
            @Override // java.util.concurrent.Callable
            public final Object call() {
                runnable.run();
                return null;
            }
        });
    }

    public static /* synthetic */ void a(Callable callable, Executor executor, final TaskCompletionSource taskCompletionSource) {
        try {
            ((Task) callable.call()).continueWith(executor, new Continuation() { // from class: b.e.b.d.g.h
                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task) {
                    AsyncQueue.a(taskCompletionSource, task);
                    return null;
                }
            });
        } catch (Exception e2) {
            taskCompletionSource.setException(e2);
        } catch (Throwable th) {
            taskCompletionSource.setException(new IllegalStateException("Unhandled throwable in callTask.", th));
        }
    }

    public class DelayedTask {
        public ScheduledFuture scheduledFuture;
        public final long targetTimeMs;
        public final Runnable task;
        public final TimerId timerId;

        public DelayedTask(TimerId timerId, long j, Runnable runnable) {
            this.timerId = timerId;
            this.targetTimeMs = j;
            this.task = runnable;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void handleDelayElapsed() {
            AsyncQueue.this.verifyIsCurrentThread();
            if (this.scheduledFuture != null) {
                markDone();
                this.task.run();
            }
        }

        private void markDone() {
            Assert.hardAssert(this.scheduledFuture != null, "Caller should have verified scheduledFuture is non-null.", new Object[0]);
            this.scheduledFuture = null;
            AsyncQueue.this.removeDelayedTask(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void start(long j) {
            this.scheduledFuture = AsyncQueue.this.executor.schedule(new Runnable() { // from class: b.e.b.d.g.a
                @Override // java.lang.Runnable
                public final void run() {
                    this.f535a.handleDelayElapsed();
                }
            }, j, TimeUnit.MILLISECONDS);
        }

        public void cancel() {
            AsyncQueue.this.verifyIsCurrentThread();
            ScheduledFuture scheduledFuture = this.scheduledFuture;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
                markDone();
            }
        }

        public void skipDelay() {
            handleDelayElapsed();
        }

        public /* synthetic */ DelayedTask(TimerId timerId, long j, Runnable runnable, AnonymousClass1 anonymousClass1) {
            this.timerId = timerId;
            this.targetTimeMs = j;
            this.task = runnable;
        }
    }

    public static /* synthetic */ Void a(TaskCompletionSource taskCompletionSource, Task task) {
        if (task.isSuccessful()) {
            taskCompletionSource.setResult(task.getResult());
            return null;
        }
        taskCompletionSource.setException(task.getException());
        return null;
    }

    public static /* synthetic */ void a(Throwable th) {
        if (th instanceof OutOfMemoryError) {
            OutOfMemoryError outOfMemoryError = new OutOfMemoryError("Firestore (24.0.1) ran out of memory. Check your queries to make sure they are not loading an excessive amount of data.");
            outOfMemoryError.initCause(th);
            throw outOfMemoryError;
        }
        throw new RuntimeException("Internal error in Cloud Firestore (24.0.1).", th);
    }

    public static /* synthetic */ void a(Runnable runnable, Throwable[] thArr, Semaphore semaphore) {
        try {
            runnable.run();
        } catch (Throwable th) {
            thArr[0] = th;
        }
        semaphore.release();
    }

    public /* synthetic */ void a(TimerId timerId) {
        Assert.hardAssert(timerId == TimerId.ALL || containsDelayedTask(timerId), "Attempted to run tasks until missing TimerId: %s", timerId);
        Collections.sort(this.delayedTasks, new Comparator() { // from class: b.e.b.d.g.i
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Long.compare(((AsyncQueue.DelayedTask) obj).targetTimeMs, ((AsyncQueue.DelayedTask) obj2).targetTimeMs);
            }
        });
        for (DelayedTask delayedTask : new ArrayList(this.delayedTasks)) {
            delayedTask.skipDelay();
            if (timerId != TimerId.ALL && delayedTask.timerId == timerId) {
                return;
            }
        }
    }
}
