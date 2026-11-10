package com.google.common.util.concurrent;

import b.a.a.a.a;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Supplier;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public abstract class AbstractIdleService implements Service {
    public final Service delegate;
    public final Supplier<String> threadNameSupplier;

    private final class DelegateService extends AbstractService {
        public DelegateService() {
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public final void doStart() {
            MoreExecutors.renamingDecorator(AbstractIdleService.this.executor(), AbstractIdleService.this.threadNameSupplier).execute(new Runnable() { // from class: com.google.common.util.concurrent.AbstractIdleService.DelegateService.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        AbstractIdleService.this.startUp();
                        DelegateService.this.notifyStarted();
                    } catch (Throwable th) {
                        DelegateService.this.notifyFailed(th);
                    }
                }
            });
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public final void doStop() {
            MoreExecutors.renamingDecorator(AbstractIdleService.this.executor(), AbstractIdleService.this.threadNameSupplier).execute(new Runnable() { // from class: com.google.common.util.concurrent.AbstractIdleService.DelegateService.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        AbstractIdleService.this.shutDown();
                        DelegateService.this.notifyStopped();
                    } catch (Throwable th) {
                        DelegateService.this.notifyFailed(th);
                    }
                }
            });
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public String toString() {
            return AbstractIdleService.this.toString();
        }

        public /* synthetic */ DelegateService(AnonymousClass1 anonymousClass1) {
        }
    }

    private final class ThreadNameSupplier implements Supplier<String> {
        public ThreadNameSupplier() {
        }

        public /* synthetic */ ThreadNameSupplier(AnonymousClass1 anonymousClass1) {
        }

        @Override // com.google.common.base.Supplier
        public String get() {
            String strServiceName = AbstractIdleService.this.serviceName();
            String strValueOf = String.valueOf(AbstractIdleService.this.state());
            return a.a(strValueOf.length() + a.a((Object) strServiceName, 1), strServiceName, " ", strValueOf);
        }
    }

    public AbstractIdleService() {
        AnonymousClass1 anonymousClass1 = null;
        this.threadNameSupplier = new ThreadNameSupplier(anonymousClass1);
        this.delegate = new DelegateService(anonymousClass1);
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

    public Executor executor() {
        return new Executor() { // from class: com.google.common.util.concurrent.AbstractIdleService.1
            @Override // java.util.concurrent.Executor
            public void execute(Runnable runnable) {
                MoreExecutors.newThread(AbstractIdleService.this.threadNameSupplier.get(), runnable).start();
            }
        };
    }

    @Override // com.google.common.util.concurrent.Service
    public final Throwable failureCause() {
        return this.delegate.failureCause();
    }

    @Override // com.google.common.util.concurrent.Service
    public final boolean isRunning() {
        return this.delegate.isRunning();
    }

    public String serviceName() {
        return AbstractIdleService.class.getSimpleName();
    }

    public abstract void shutDown();

    @Override // com.google.common.util.concurrent.Service
    @CanIgnoreReturnValue
    public final Service startAsync() {
        this.delegate.startAsync();
        return this;
    }

    public abstract void startUp();

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
    public final void awaitRunning(long j, TimeUnit timeUnit) {
        this.delegate.awaitRunning(j, timeUnit);
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitTerminated(long j, TimeUnit timeUnit) {
        this.delegate.awaitTerminated(j, timeUnit);
    }
}
