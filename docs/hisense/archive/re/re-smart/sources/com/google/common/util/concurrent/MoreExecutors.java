package com.google.common.util.concurrent;

import b.a.a.a.a;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true)
public final class MoreExecutors {

    /* JADX INFO: renamed from: com.google.common.util.concurrent.MoreExecutors$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        public final /* synthetic */ ListenableFuture val$future;
        public final /* synthetic */ BlockingQueue val$queue;

        public AnonymousClass1(BlockingQueue blockingQueue, ListenableFuture listenableFuture) {
            this.val$queue = blockingQueue;
            this.val$future = listenableFuture;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.val$queue.add(this.val$future);
        }
    }

    @GwtIncompatible
    private static class ListeningDecorator extends AbstractListeningExecutorService {
        public final ExecutorService delegate;

        public ListeningDecorator(ExecutorService executorService) {
            if (executorService == null) {
                throw new NullPointerException();
            }
            this.delegate = executorService;
        }

        @Override // java.util.concurrent.ExecutorService
        public final boolean awaitTermination(long j, TimeUnit timeUnit) {
            return this.delegate.awaitTermination(j, timeUnit);
        }

        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            this.delegate.execute(runnable);
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
    }

    @GwtIncompatible
    private static final class ScheduledListeningDecorator extends ListeningDecorator implements ListeningScheduledExecutorService {
        public final ScheduledExecutorService delegate;

        private static final class ListenableScheduledTask<V> extends ForwardingListenableFuture.SimpleForwardingListenableFuture<V> implements ListenableScheduledFuture<V> {
            public final ScheduledFuture<?> scheduledDelegate;

            public ListenableScheduledTask(ListenableFuture<V> listenableFuture, ScheduledFuture<?> scheduledFuture) {
                super(listenableFuture);
                this.scheduledDelegate = scheduledFuture;
            }

            @Override // com.google.common.util.concurrent.ForwardingFuture, java.util.concurrent.Future
            public boolean cancel(boolean z) {
                boolean zCancel = delegate().cancel(z);
                if (zCancel) {
                    this.scheduledDelegate.cancel(z);
                }
                return zCancel;
            }

            @Override // java.util.concurrent.Delayed
            public long getDelay(TimeUnit timeUnit) {
                return this.scheduledDelegate.getDelay(timeUnit);
            }

            @Override // java.lang.Comparable
            public int compareTo(Delayed delayed) {
                return this.scheduledDelegate.compareTo(delayed);
            }
        }

        @GwtIncompatible
        private static final class NeverSuccessfulListenableFutureTask extends AbstractFuture.TrustedFuture<Void> implements Runnable {
            public final Runnable delegate;

            public NeverSuccessfulListenableFutureTask(Runnable runnable) {
                if (runnable == null) {
                    throw new NullPointerException();
                }
                this.delegate = runnable;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    this.delegate.run();
                } catch (Throwable th) {
                    setException(th);
                    Throwables.propagate(th);
                    throw null;
                }
            }
        }

        public ScheduledListeningDecorator(ScheduledExecutorService scheduledExecutorService) {
            super(scheduledExecutorService);
            this.delegate = scheduledExecutorService;
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public <V> ListenableScheduledFuture<V> schedule(Callable<V> callable, long j, TimeUnit timeUnit) {
            TrustedListenableFutureTask trustedListenableFutureTask = new TrustedListenableFutureTask(callable);
            return new ListenableScheduledTask(trustedListenableFutureTask, this.delegate.schedule(trustedListenableFutureTask, j, timeUnit));
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public ListenableScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            NeverSuccessfulListenableFutureTask neverSuccessfulListenableFutureTask = new NeverSuccessfulListenableFutureTask(runnable);
            return new ListenableScheduledTask(neverSuccessfulListenableFutureTask, this.delegate.scheduleAtFixedRate(neverSuccessfulListenableFutureTask, j, j2, timeUnit));
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public ListenableScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            NeverSuccessfulListenableFutureTask neverSuccessfulListenableFutureTask = new NeverSuccessfulListenableFutureTask(runnable);
            return new ListenableScheduledTask(neverSuccessfulListenableFutureTask, this.delegate.scheduleWithFixedDelay(neverSuccessfulListenableFutureTask, j, j2, timeUnit));
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public ListenableScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
            TrustedListenableFutureTask trustedListenableFutureTaskCreate = TrustedListenableFutureTask.create(runnable, null);
            return new ListenableScheduledTask(trustedListenableFutureTaskCreate, this.delegate.schedule(trustedListenableFutureTaskCreate, j, timeUnit));
        }
    }

    @Beta
    @GwtIncompatible
    public static void addDelayedShutdownHook(ExecutorService executorService, long j, TimeUnit timeUnit) {
        new Application().addDelayedShutdownHook(executorService, j, timeUnit);
    }

    public static Executor directExecutor() {
        return DirectExecutor.INSTANCE;
    }

    @Beta
    @GwtIncompatible
    public static ExecutorService getExitingExecutorService(ThreadPoolExecutor threadPoolExecutor, long j, TimeUnit timeUnit) {
        return new Application().getExitingExecutorService(threadPoolExecutor, j, timeUnit);
    }

    @Beta
    @GwtIncompatible
    public static ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor, long j, TimeUnit timeUnit) {
        return new Application().getExitingScheduledExecutorService(scheduledThreadPoolExecutor, j, timeUnit);
    }

    @GwtIncompatible
    public static <T> T invokeAnyImpl(ListeningExecutorService listeningExecutorService, Collection<? extends Callable<T>> collection, boolean z, long j, TimeUnit timeUnit) throws ExecutionException {
        long jNanoTime;
        if (listeningExecutorService == null) {
            throw new NullPointerException();
        }
        if (timeUnit == null) {
            throw new NullPointerException();
        }
        int size = collection.size();
        Preconditions.checkArgument(size > 0);
        ArrayList arrayListNewArrayListWithCapacity = Lists.newArrayListWithCapacity(size);
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        long nanos = timeUnit.toNanos(j);
        if (z) {
            try {
                jNanoTime = System.nanoTime();
            } catch (Throwable th) {
                Iterator it = arrayListNewArrayListWithCapacity.iterator();
                while (it.hasNext()) {
                    ((Future) it.next()).cancel(true);
                }
                throw th;
            }
        } else {
            jNanoTime = 0;
        }
        Iterator<? extends Callable<T>> it2 = collection.iterator();
        ListenableFuture<T> listenableFutureSubmit = listeningExecutorService.submit((Callable) it2.next());
        listenableFutureSubmit.addListener(new AnonymousClass1(linkedBlockingQueue, listenableFutureSubmit), DirectExecutor.INSTANCE);
        arrayListNewArrayListWithCapacity.add(listenableFutureSubmit);
        int i = size - 1;
        long j2 = jNanoTime;
        ExecutionException executionException = null;
        int i2 = 1;
        while (true) {
            Future future = (Future) linkedBlockingQueue.poll();
            if (future == null) {
                if (i > 0) {
                    i--;
                    ListenableFuture<T> listenableFutureSubmit2 = listeningExecutorService.submit((Callable) it2.next());
                    listenableFutureSubmit2.addListener(new AnonymousClass1(linkedBlockingQueue, listenableFutureSubmit2), DirectExecutor.INSTANCE);
                    arrayListNewArrayListWithCapacity.add(listenableFutureSubmit2);
                    i2++;
                } else {
                    if (i2 == 0) {
                        if (executionException == null) {
                            throw new ExecutionException((Throwable) null);
                        }
                        throw executionException;
                    }
                    if (z) {
                        future = (Future) linkedBlockingQueue.poll(nanos, TimeUnit.NANOSECONDS);
                        if (future == null) {
                            throw new TimeoutException();
                        }
                        long jNanoTime2 = System.nanoTime();
                        nanos -= jNanoTime2 - j2;
                        j2 = jNanoTime2;
                    } else {
                        future = (Future) linkedBlockingQueue.take();
                    }
                }
            }
            if (future != null) {
                int i3 = i2 - 1;
                try {
                    T t = (T) future.get();
                    Iterator it3 = arrayListNewArrayListWithCapacity.iterator();
                    while (it3.hasNext()) {
                        ((Future) it3.next()).cancel(true);
                    }
                    return t;
                } catch (RuntimeException e2) {
                    e = new ExecutionException(e2);
                    executionException = e;
                    i2 = i3;
                } catch (ExecutionException e3) {
                    e = e3;
                    executionException = e;
                    i2 = i3;
                }
            }
        }
    }

    @GwtIncompatible
    public static boolean isAppEngineWithApiClasses() {
        if (System.getProperty("com.google.appengine.runtime.environment") == null) {
            return false;
        }
        try {
            Class.forName("com.google.appengine.api.utils.SystemProperty");
            return Class.forName("com.google.apphosting.api.ApiProxy").getMethod("getCurrentEnvironment", new Class[0]).invoke(null, new Object[0]) != null;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return false;
        }
    }

    @GwtIncompatible
    public static ListeningExecutorService listeningDecorator(ExecutorService executorService) {
        if (executorService instanceof ListeningExecutorService) {
            return (ListeningExecutorService) executorService;
        }
        return executorService instanceof ScheduledExecutorService ? new ScheduledListeningDecorator((ScheduledExecutorService) executorService) : new ListeningDecorator(executorService);
    }

    @GwtIncompatible
    public static ListeningExecutorService newDirectExecutorService() {
        return new DirectExecutorService();
    }

    @Beta
    @GwtIncompatible
    public static Executor newSequentialExecutor(Executor executor) {
        return new SequentialExecutor(executor);
    }

    @GwtIncompatible
    public static Thread newThread(String str, Runnable runnable) {
        if (str == null) {
            throw new NullPointerException();
        }
        if (runnable == null) {
            throw new NullPointerException();
        }
        Thread threadNewThread = platformThreadFactory().newThread(runnable);
        try {
            threadNewThread.setName(str);
        } catch (SecurityException unused) {
        }
        return threadNewThread;
    }

    @Beta
    @GwtIncompatible
    public static ThreadFactory platformThreadFactory() {
        if (!isAppEngineWithApiClasses()) {
            return Executors.defaultThreadFactory();
        }
        try {
            return (ThreadFactory) Class.forName("com.google.appengine.api.ThreadManager").getMethod("currentRequestThreadFactory", new Class[0]).invoke(null, new Object[0]);
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException("Couldn't invoke ThreadManager.currentRequestThreadFactory", e2);
        } catch (IllegalAccessException e3) {
            throw new RuntimeException("Couldn't invoke ThreadManager.currentRequestThreadFactory", e3);
        } catch (NoSuchMethodException e4) {
            throw new RuntimeException("Couldn't invoke ThreadManager.currentRequestThreadFactory", e4);
        } catch (InvocationTargetException e5) {
            Throwables.propagate(e5.getCause());
            throw null;
        }
    }

    public static Executor rejectionPropagatingExecutor(final Executor executor, final AbstractFuture<?> abstractFuture) {
        if (executor == null) {
            throw new NullPointerException();
        }
        if (abstractFuture != null) {
            return executor == DirectExecutor.INSTANCE ? executor : new Executor() { // from class: com.google.common.util.concurrent.MoreExecutors.5
                @Override // java.util.concurrent.Executor
                public void execute(Runnable runnable) {
                    try {
                        executor.execute(runnable);
                    } catch (RejectedExecutionException e2) {
                        abstractFuture.setException(e2);
                    }
                }
            };
        }
        throw new NullPointerException();
    }

    @GwtIncompatible
    public static Executor renamingDecorator(final Executor executor, final Supplier<String> supplier) {
        if (executor == null) {
            throw new NullPointerException();
        }
        if (supplier != null) {
            return new Executor() { // from class: com.google.common.util.concurrent.MoreExecutors.2
                @Override // java.util.concurrent.Executor
                public void execute(Runnable runnable) {
                    executor.execute(Callables.threadRenaming(runnable, (Supplier<String>) supplier));
                }
            };
        }
        throw new NullPointerException();
    }

    @CanIgnoreReturnValue
    @Beta
    @GwtIncompatible
    public static boolean shutdownAndAwaitTermination(ExecutorService executorService, long j, TimeUnit timeUnit) {
        long nanos = timeUnit.toNanos(j) / 2;
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(nanos, TimeUnit.NANOSECONDS)) {
                executorService.shutdownNow();
                executorService.awaitTermination(nanos, TimeUnit.NANOSECONDS);
            }
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            executorService.shutdownNow();
        }
        return executorService.isTerminated();
    }

    @GwtIncompatible
    public static <T> ListenableFuture<T> submitAndAddQueueListener(ListeningExecutorService listeningExecutorService, Callable<T> callable, BlockingQueue<Future<T>> blockingQueue) {
        ListenableFuture<T> listenableFutureSubmit = listeningExecutorService.submit((Callable) callable);
        listenableFutureSubmit.addListener(new AnonymousClass1(blockingQueue, listenableFutureSubmit), DirectExecutor.INSTANCE);
        return listenableFutureSubmit;
    }

    @GwtIncompatible
    public static void useDaemonThreadFactory(ThreadPoolExecutor threadPoolExecutor) {
        threadPoolExecutor.setThreadFactory(new ThreadFactoryBuilder().setDaemon(true).setThreadFactory(threadPoolExecutor.getThreadFactory()).build());
    }

    @Beta
    @GwtIncompatible
    public static ExecutorService getExitingExecutorService(ThreadPoolExecutor threadPoolExecutor) {
        return new Application().getExitingExecutorService(threadPoolExecutor);
    }

    @VisibleForTesting
    @GwtIncompatible
    static class Application {
        public final void addDelayedShutdownHook(final ExecutorService executorService, final long j, final TimeUnit timeUnit) {
            if (executorService == null) {
                throw new NullPointerException();
            }
            if (timeUnit == null) {
                throw new NullPointerException();
            }
            String strValueOf = String.valueOf(executorService);
            addShutdownHook(MoreExecutors.newThread(a.a(strValueOf.length() + 24, "DelayedShutdownHook-for-", strValueOf), new Runnable(this) { // from class: com.google.common.util.concurrent.MoreExecutors.Application.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        executorService.shutdown();
                        executorService.awaitTermination(j, timeUnit);
                    } catch (InterruptedException unused) {
                    }
                }
            }));
        }

        @VisibleForTesting
        public void addShutdownHook(Thread thread) {
            Runtime.getRuntime().addShutdownHook(thread);
        }

        public final ExecutorService getExitingExecutorService(ThreadPoolExecutor threadPoolExecutor, long j, TimeUnit timeUnit) {
            MoreExecutors.useDaemonThreadFactory(threadPoolExecutor);
            ExecutorService executorServiceUnconfigurableExecutorService = Executors.unconfigurableExecutorService(threadPoolExecutor);
            addDelayedShutdownHook(threadPoolExecutor, j, timeUnit);
            return executorServiceUnconfigurableExecutorService;
        }

        public final ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor, long j, TimeUnit timeUnit) {
            MoreExecutors.useDaemonThreadFactory(scheduledThreadPoolExecutor);
            ScheduledExecutorService scheduledExecutorServiceUnconfigurableScheduledExecutorService = Executors.unconfigurableScheduledExecutorService(scheduledThreadPoolExecutor);
            addDelayedShutdownHook(scheduledThreadPoolExecutor, j, timeUnit);
            return scheduledExecutorServiceUnconfigurableScheduledExecutorService;
        }

        public final ExecutorService getExitingExecutorService(ThreadPoolExecutor threadPoolExecutor) {
            return getExitingExecutorService(threadPoolExecutor, 120L, TimeUnit.SECONDS);
        }

        public final ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
            return getExitingScheduledExecutorService(scheduledThreadPoolExecutor, 120L, TimeUnit.SECONDS);
        }
    }

    @Beta
    @GwtIncompatible
    public static ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        return new Application().getExitingScheduledExecutorService(scheduledThreadPoolExecutor);
    }

    @GwtIncompatible
    private static final class DirectExecutorService extends AbstractListeningExecutorService {
        public final Object lock;

        @GuardedBy("lock")
        public int runningTasks;

        @GuardedBy("lock")
        public boolean shutdown;

        public DirectExecutorService() {
            this.lock = new Object();
            this.runningTasks = 0;
            this.shutdown = false;
        }

        private void endTask() {
            synchronized (this.lock) {
                int i = this.runningTasks - 1;
                this.runningTasks = i;
                if (i == 0) {
                    this.lock.notifyAll();
                }
            }
        }

        private void startTask() {
            synchronized (this.lock) {
                if (this.shutdown) {
                    throw new RejectedExecutionException("Executor already shutdown");
                }
                this.runningTasks++;
            }
        }

        @Override // java.util.concurrent.ExecutorService
        public boolean awaitTermination(long j, TimeUnit timeUnit) {
            long nanos = timeUnit.toNanos(j);
            synchronized (this.lock) {
                while (true) {
                    if (this.shutdown && this.runningTasks == 0) {
                        return true;
                    }
                    if (nanos <= 0) {
                        return false;
                    }
                    long jNanoTime = System.nanoTime();
                    TimeUnit.NANOSECONDS.timedWait(this.lock, nanos);
                    nanos -= System.nanoTime() - jNanoTime;
                }
            }
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            startTask();
            try {
                runnable.run();
            } finally {
                endTask();
            }
        }

        @Override // java.util.concurrent.ExecutorService
        public boolean isShutdown() {
            boolean z;
            synchronized (this.lock) {
                z = this.shutdown;
            }
            return z;
        }

        @Override // java.util.concurrent.ExecutorService
        public boolean isTerminated() {
            boolean z;
            synchronized (this.lock) {
                z = this.shutdown && this.runningTasks == 0;
            }
            return z;
        }

        @Override // java.util.concurrent.ExecutorService
        public void shutdown() {
            synchronized (this.lock) {
                this.shutdown = true;
                if (this.runningTasks == 0) {
                    this.lock.notifyAll();
                }
            }
        }

        @Override // java.util.concurrent.ExecutorService
        public List<Runnable> shutdownNow() {
            shutdown();
            return Collections.emptyList();
        }

        public /* synthetic */ DirectExecutorService(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    @GwtIncompatible
    public static ExecutorService renamingDecorator(ExecutorService executorService, final Supplier<String> supplier) {
        if (executorService == null) {
            throw new NullPointerException();
        }
        if (supplier != null) {
            return new WrappingExecutorService(executorService) { // from class: com.google.common.util.concurrent.MoreExecutors.3
                @Override // com.google.common.util.concurrent.WrappingExecutorService
                public <T> Callable<T> wrapTask(Callable<T> callable) {
                    return Callables.threadRenaming(callable, (Supplier<String>) supplier);
                }

                @Override // com.google.common.util.concurrent.WrappingExecutorService
                public Runnable wrapTask(Runnable runnable) {
                    return Callables.threadRenaming(runnable, (Supplier<String>) supplier);
                }
            };
        }
        throw new NullPointerException();
    }

    @GwtIncompatible
    public static ListeningScheduledExecutorService listeningDecorator(ScheduledExecutorService scheduledExecutorService) {
        if (scheduledExecutorService instanceof ListeningScheduledExecutorService) {
            return (ListeningScheduledExecutorService) scheduledExecutorService;
        }
        return new ScheduledListeningDecorator(scheduledExecutorService);
    }

    @GwtIncompatible
    public static ScheduledExecutorService renamingDecorator(ScheduledExecutorService scheduledExecutorService, final Supplier<String> supplier) {
        if (scheduledExecutorService == null) {
            throw new NullPointerException();
        }
        if (supplier != null) {
            return new WrappingScheduledExecutorService(scheduledExecutorService) { // from class: com.google.common.util.concurrent.MoreExecutors.4
                @Override // com.google.common.util.concurrent.WrappingExecutorService
                public <T> Callable<T> wrapTask(Callable<T> callable) {
                    return Callables.threadRenaming(callable, (Supplier<String>) supplier);
                }

                @Override // com.google.common.util.concurrent.WrappingExecutorService
                public Runnable wrapTask(Runnable runnable) {
                    return Callables.threadRenaming(runnable, (Supplier<String>) supplier);
                }
            };
        }
        throw new NullPointerException();
    }
}
