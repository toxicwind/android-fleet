package com.google.common.util.concurrent;

import b.a.a.a.a;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public abstract class AbstractScheduledService implements Service {
    public static final Logger logger = Logger.getLogger(AbstractScheduledService.class.getName());
    public final AbstractService delegate = new ServiceDelegate();

    public static abstract class CustomScheduler extends Scheduler {

        private class ReschedulableCallable extends ForwardingFuture<Void> implements Callable<Void> {

            @GuardedBy("lock")
            public Future<Void> currentFuture;
            public final ScheduledExecutorService executor;
            public final ReentrantLock lock = new ReentrantLock();
            public final AbstractService service;
            public final Runnable wrappedRunnable;

            public ReschedulableCallable(AbstractService abstractService, ScheduledExecutorService scheduledExecutorService, Runnable runnable) {
                this.wrappedRunnable = runnable;
                this.executor = scheduledExecutorService;
                this.service = abstractService;
            }

            @Override // com.google.common.util.concurrent.ForwardingFuture, java.util.concurrent.Future
            public boolean cancel(boolean z) {
                this.lock.lock();
                try {
                    return this.currentFuture.cancel(z);
                } finally {
                    this.lock.unlock();
                }
            }

            @Override // com.google.common.util.concurrent.ForwardingFuture, java.util.concurrent.Future
            public boolean isCancelled() {
                this.lock.lock();
                try {
                    return this.currentFuture.isCancelled();
                } finally {
                    this.lock.unlock();
                }
            }

            public void reschedule() {
                try {
                    Schedule nextSchedule = CustomScheduler.this.getNextSchedule();
                    Throwable th = null;
                    this.lock.lock();
                    try {
                        if (this.currentFuture == null || !this.currentFuture.isCancelled()) {
                            this.currentFuture = this.executor.schedule(this, nextSchedule.delay, nextSchedule.unit);
                        }
                    } catch (Throwable th2) {
                        this.lock.unlock();
                        throw th2;
                    }
                    this.lock.unlock();
                    if (th != null) {
                        this.service.notifyFailed(th);
                    }
                } catch (Throwable th3) {
                    this.service.notifyFailed(th3);
                }
            }

            @Override // java.util.concurrent.Callable
            public Void call() {
                this.wrappedRunnable.run();
                reschedule();
                return null;
            }

            @Override // com.google.common.util.concurrent.ForwardingFuture, com.google.common.collect.ForwardingObject
            public Future<Void> delegate() {
                throw new UnsupportedOperationException("Only cancel and isCancelled is supported by this future");
            }
        }

        protected static final class Schedule {
            public final long delay;
            public final TimeUnit unit;

            public Schedule(long j, TimeUnit timeUnit) {
                this.delay = j;
                if (timeUnit == null) {
                    throw new NullPointerException();
                }
                this.unit = timeUnit;
            }
        }

        public CustomScheduler() {
            super(null);
        }

        public abstract Schedule getNextSchedule();

        @Override // com.google.common.util.concurrent.AbstractScheduledService.Scheduler
        public final Future<?> schedule(AbstractService abstractService, ScheduledExecutorService scheduledExecutorService, Runnable runnable) {
            ReschedulableCallable reschedulableCallable = new ReschedulableCallable(abstractService, scheduledExecutorService, runnable);
            reschedulableCallable.reschedule();
            return reschedulableCallable;
        }
    }

    public static abstract class Scheduler {
        public Scheduler() {
        }

        public static Scheduler newFixedDelaySchedule(final long j, final long j2, final TimeUnit timeUnit) {
            if (timeUnit == null) {
                throw new NullPointerException();
            }
            Preconditions.checkArgument(j2 > 0, "delay must be > 0, found %s", j2);
            return new Scheduler() { // from class: com.google.common.util.concurrent.AbstractScheduledService.Scheduler.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(null);
                }

                @Override // com.google.common.util.concurrent.AbstractScheduledService.Scheduler
                public Future<?> schedule(AbstractService abstractService, ScheduledExecutorService scheduledExecutorService, Runnable runnable) {
                    return scheduledExecutorService.scheduleWithFixedDelay(runnable, j, j2, timeUnit);
                }
            };
        }

        public static Scheduler newFixedRateSchedule(final long j, final long j2, final TimeUnit timeUnit) {
            if (timeUnit == null) {
                throw new NullPointerException();
            }
            Preconditions.checkArgument(j2 > 0, "period must be > 0, found %s", j2);
            return new Scheduler() { // from class: com.google.common.util.concurrent.AbstractScheduledService.Scheduler.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(null);
                }

                @Override // com.google.common.util.concurrent.AbstractScheduledService.Scheduler
                public Future<?> schedule(AbstractService abstractService, ScheduledExecutorService scheduledExecutorService, Runnable runnable) {
                    return scheduledExecutorService.scheduleAtFixedRate(runnable, j, j2, timeUnit);
                }
            };
        }

        public abstract Future<?> schedule(AbstractService abstractService, ScheduledExecutorService scheduledExecutorService, Runnable runnable);

        public /* synthetic */ Scheduler(AnonymousClass1 anonymousClass1) {
        }
    }

    private final class ServiceDelegate extends AbstractService {
        public volatile ScheduledExecutorService executorService;
        public final ReentrantLock lock;
        public volatile Future<?> runningTask;
        public final Runnable task;

        class Task implements Runnable {
            public Task() {
            }

            @Override // java.lang.Runnable
            public void run() {
                ServiceDelegate.this.lock.lock();
                try {
                    try {
                        if (ServiceDelegate.this.runningTask.isCancelled()) {
                            return;
                        }
                        AbstractScheduledService.this.runOneIteration();
                    } catch (Throwable th) {
                        try {
                            AbstractScheduledService.this.shutDown();
                        } catch (Exception e2) {
                            AbstractScheduledService.logger.log(Level.WARNING, "Error while attempting to shut down the service after failure.", (Throwable) e2);
                        }
                        ServiceDelegate.this.notifyFailed(th);
                        ServiceDelegate.this.runningTask.cancel(false);
                    }
                } finally {
                    ServiceDelegate.this.lock.unlock();
                }
            }
        }

        public ServiceDelegate() {
            this.lock = new ReentrantLock();
            this.task = new Task();
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public final void doStart() {
            this.executorService = MoreExecutors.renamingDecorator(AbstractScheduledService.this.executor(), new Supplier<String>() { // from class: com.google.common.util.concurrent.AbstractScheduledService.ServiceDelegate.1
                @Override // com.google.common.base.Supplier
                public String get() {
                    String strServiceName = AbstractScheduledService.this.serviceName();
                    String strValueOf = String.valueOf(ServiceDelegate.this.state());
                    return a.a(strValueOf.length() + a.a((Object) strServiceName, 1), strServiceName, " ", strValueOf);
                }
            });
            this.executorService.execute(new Runnable() { // from class: com.google.common.util.concurrent.AbstractScheduledService.ServiceDelegate.2
                @Override // java.lang.Runnable
                public void run() {
                    ServiceDelegate.this.lock.lock();
                    try {
                        try {
                            AbstractScheduledService.this.startUp();
                            ServiceDelegate.this.runningTask = AbstractScheduledService.this.scheduler().schedule(AbstractScheduledService.this.delegate, ServiceDelegate.this.executorService, ServiceDelegate.this.task);
                            ServiceDelegate.this.notifyStarted();
                        } catch (Throwable th) {
                            ServiceDelegate.this.notifyFailed(th);
                            if (ServiceDelegate.this.runningTask != null) {
                                ServiceDelegate.this.runningTask.cancel(false);
                            }
                        }
                    } finally {
                        ServiceDelegate.this.lock.unlock();
                    }
                }
            });
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public final void doStop() {
            this.runningTask.cancel(false);
            this.executorService.execute(new Runnable() { // from class: com.google.common.util.concurrent.AbstractScheduledService.ServiceDelegate.3
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        ServiceDelegate.this.lock.lock();
                        try {
                            if (ServiceDelegate.this.state() != Service.State.STOPPING) {
                                ServiceDelegate.this.lock.unlock();
                                return;
                            }
                            AbstractScheduledService.this.shutDown();
                            ServiceDelegate.this.lock.unlock();
                            ServiceDelegate.this.notifyStopped();
                        } catch (Throwable th) {
                            ServiceDelegate.this.lock.unlock();
                            throw th;
                        }
                    } catch (Throwable th2) {
                        ServiceDelegate.this.notifyFailed(th2);
                    }
                }
            });
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public String toString() {
            return AbstractScheduledService.this.toString();
        }
    }

    @Override // com.google.common.util.concurrent.Service
    public final void addListener(Service.Listener listener, Executor executor) {
        this.delegate.addListener(listener, executor);
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitRunning() {
        this.delegate.awaitRunning();
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitTerminated() {
        this.delegate.awaitTerminated();
    }

    public ScheduledExecutorService executor() {
        final ScheduledExecutorService scheduledExecutorServiceNewSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() { // from class: com.google.common.util.concurrent.AbstractScheduledService.1ThreadFactoryImpl
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                return MoreExecutors.newThread(AbstractScheduledService.this.serviceName(), runnable);
            }
        });
        addListener(new Service.Listener(this) { // from class: com.google.common.util.concurrent.AbstractScheduledService.1
            @Override // com.google.common.util.concurrent.Service.Listener
            public void failed(Service.State state, Throwable th) {
                scheduledExecutorServiceNewSingleThreadScheduledExecutor.shutdown();
            }

            @Override // com.google.common.util.concurrent.Service.Listener
            public void terminated(Service.State state) {
                scheduledExecutorServiceNewSingleThreadScheduledExecutor.shutdown();
            }
        }, DirectExecutor.INSTANCE);
        return scheduledExecutorServiceNewSingleThreadScheduledExecutor;
    }

    @Override // com.google.common.util.concurrent.Service
    public final Throwable failureCause() {
        return this.delegate.failureCause();
    }

    @Override // com.google.common.util.concurrent.Service
    public final boolean isRunning() {
        return this.delegate.isRunning();
    }

    public abstract void runOneIteration();

    public abstract Scheduler scheduler();

    public String serviceName() {
        return AbstractScheduledService.class.getSimpleName();
    }

    public void shutDown() {
    }

    @Override // com.google.common.util.concurrent.Service
    @CanIgnoreReturnValue
    public final Service startAsync() {
        this.delegate.startAsync();
        return this;
    }

    public void startUp() {
    }

    @Override // com.google.common.util.concurrent.Service
    public final Service.State state() {
        return this.delegate.state();
    }

    @Override // com.google.common.util.concurrent.Service
    @CanIgnoreReturnValue
    public final Service stopAsync() {
        this.delegate.stopAsync();
        return this;
    }

    public String toString() {
        String strServiceName = serviceName();
        String strValueOf = String.valueOf(state());
        return a.b(strValueOf.length() + a.a((Object) strServiceName, 3), strServiceName, " [", strValueOf, "]");
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitRunning(long j, TimeUnit timeUnit) throws TimeoutException {
        this.delegate.awaitRunning(j, timeUnit);
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitTerminated(long j, TimeUnit timeUnit) throws TimeoutException {
        this.delegate.awaitTerminated(j, timeUnit);
    }
}
