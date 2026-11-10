package com.google.common.util.concurrent;

import b.a.a.a.a;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.ForOverride;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public abstract class AbstractService implements Service {
    public static final ListenerCallQueue.Event<Service.Listener> STARTING_EVENT = new ListenerCallQueue.Event<Service.Listener>() { // from class: com.google.common.util.concurrent.AbstractService.1
        public String toString() {
            return "starting()";
        }

        @Override // com.google.common.util.concurrent.ListenerCallQueue.Event
        public void call(Service.Listener listener) {
            listener.starting();
        }
    };
    public static final ListenerCallQueue.Event<Service.Listener> RUNNING_EVENT = new ListenerCallQueue.Event<Service.Listener>() { // from class: com.google.common.util.concurrent.AbstractService.2
        public String toString() {
            return "running()";
        }

        @Override // com.google.common.util.concurrent.ListenerCallQueue.Event
        public void call(Service.Listener listener) {
            listener.running();
        }
    };
    public static final ListenerCallQueue.Event<Service.Listener> STOPPING_FROM_STARTING_EVENT = new AnonymousClass4(Service.State.STARTING);
    public static final ListenerCallQueue.Event<Service.Listener> STOPPING_FROM_RUNNING_EVENT = new AnonymousClass4(Service.State.RUNNING);
    public static final ListenerCallQueue.Event<Service.Listener> TERMINATED_FROM_NEW_EVENT = new AnonymousClass3(Service.State.NEW);
    public static final ListenerCallQueue.Event<Service.Listener> TERMINATED_FROM_STARTING_EVENT = new AnonymousClass3(Service.State.STARTING);
    public static final ListenerCallQueue.Event<Service.Listener> TERMINATED_FROM_RUNNING_EVENT = new AnonymousClass3(Service.State.RUNNING);
    public static final ListenerCallQueue.Event<Service.Listener> TERMINATED_FROM_STOPPING_EVENT = new AnonymousClass3(Service.State.STOPPING);
    public final Monitor monitor = new Monitor(false);
    public final Monitor.Guard isStartable = new IsStartableGuard();
    public final Monitor.Guard isStoppable = new IsStoppableGuard();
    public final Monitor.Guard hasReachedRunning = new HasReachedRunningGuard();
    public final Monitor.Guard isStopped = new IsStoppedGuard();
    public final ListenerCallQueue<Service.Listener> listeners = new ListenerCallQueue<>();
    public volatile StateSnapshot snapshot = new StateSnapshot(Service.State.NEW);

    /* JADX INFO: renamed from: com.google.common.util.concurrent.AbstractService$3, reason: invalid class name */
    public class AnonymousClass3 implements ListenerCallQueue.Event<Service.Listener> {
        public final /* synthetic */ Service.State val$from;

        public AnonymousClass3(Service.State state) {
            this.val$from = state;
        }

        public String toString() {
            String strValueOf = String.valueOf(this.val$from);
            return a.a(strValueOf.length() + 21, "terminated({from = ", strValueOf, "})");
        }

        @Override // com.google.common.util.concurrent.ListenerCallQueue.Event
        public void call(Service.Listener listener) {
            listener.terminated(this.val$from);
        }
    }

    /* JADX INFO: renamed from: com.google.common.util.concurrent.AbstractService$4, reason: invalid class name */
    public class AnonymousClass4 implements ListenerCallQueue.Event<Service.Listener> {
        public final /* synthetic */ Service.State val$from;

        public AnonymousClass4(Service.State state) {
            this.val$from = state;
        }

        public String toString() {
            String strValueOf = String.valueOf(this.val$from);
            return a.a(strValueOf.length() + 19, "stopping({from = ", strValueOf, "})");
        }

        @Override // com.google.common.util.concurrent.ListenerCallQueue.Event
        public void call(Service.Listener listener) {
            listener.stopping(this.val$from);
        }
    }

    /* JADX INFO: renamed from: com.google.common.util.concurrent.AbstractService$5, reason: invalid class name */
    public class AnonymousClass5 implements ListenerCallQueue.Event<Service.Listener> {
        public final /* synthetic */ Throwable val$cause;
        public final /* synthetic */ Service.State val$from;

        public AnonymousClass5(AbstractService abstractService, Service.State state, Throwable th) {
            this.val$from = state;
            this.val$cause = th;
        }

        public String toString() {
            String strValueOf = String.valueOf(this.val$from);
            String strValueOf2 = String.valueOf(this.val$cause);
            StringBuilder sbA = a.a(strValueOf2.length() + strValueOf.length() + 27, "failed({from = ", strValueOf, ", cause = ", strValueOf2);
            sbA.append("})");
            return sbA.toString();
        }

        @Override // com.google.common.util.concurrent.ListenerCallQueue.Event
        public void call(Service.Listener listener) {
            listener.failed(this.val$from, this.val$cause);
        }
    }

    /* JADX INFO: renamed from: com.google.common.util.concurrent.AbstractService$6, reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$common$util$concurrent$Service$State = new int[Service.State.values().length];

        static {
            try {
                $SwitchMap$com$google$common$util$concurrent$Service$State[Service.State.NEW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$common$util$concurrent$Service$State[Service.State.STARTING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$common$util$concurrent$Service$State[Service.State.RUNNING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$common$util$concurrent$Service$State[Service.State.STOPPING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$common$util$concurrent$Service$State[Service.State.TERMINATED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$common$util$concurrent$Service$State[Service.State.FAILED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private final class HasReachedRunningGuard extends Monitor.Guard {
        public HasReachedRunningGuard() {
            super(AbstractService.this.monitor);
        }

        @Override // com.google.common.util.concurrent.Monitor.Guard
        public boolean isSatisfied() {
            return AbstractService.this.state().compareTo(Service.State.RUNNING) >= 0;
        }
    }

    private final class IsStartableGuard extends Monitor.Guard {
        public IsStartableGuard() {
            super(AbstractService.this.monitor);
        }

        @Override // com.google.common.util.concurrent.Monitor.Guard
        public boolean isSatisfied() {
            return AbstractService.this.state() == Service.State.NEW;
        }
    }

    private final class IsStoppableGuard extends Monitor.Guard {
        public IsStoppableGuard() {
            super(AbstractService.this.monitor);
        }

        @Override // com.google.common.util.concurrent.Monitor.Guard
        public boolean isSatisfied() {
            return AbstractService.this.state().compareTo(Service.State.RUNNING) <= 0;
        }
    }

    private final class IsStoppedGuard extends Monitor.Guard {
        public IsStoppedGuard() {
            super(AbstractService.this.monitor);
        }

        @Override // com.google.common.util.concurrent.Monitor.Guard
        public boolean isSatisfied() {
            return AbstractService.this.state().isTerminal();
        }
    }

    private static final class StateSnapshot {
        public final Throwable failure;
        public final boolean shutdownWhenStartupFinishes;
        public final Service.State state;

        public StateSnapshot(Service.State state) {
            this(state, false, null);
        }

        public Service.State externalState() {
            return (this.shutdownWhenStartupFinishes && this.state == Service.State.STARTING) ? Service.State.STOPPING : this.state;
        }

        public Throwable failureCause() {
            Preconditions.checkState(this.state == Service.State.FAILED, "failureCause() is only valid if the service has failed, service is %s", this.state);
            return this.failure;
        }

        public StateSnapshot(Service.State state, boolean z, Throwable th) {
            Preconditions.checkArgument(!z || state == Service.State.STARTING, "shutdownWhenStartupFinishes can only be set if state is STARTING. Got %s instead.", state);
            Preconditions.checkArgument((th != null) ^ (state == Service.State.FAILED) ? false : true, "A failure cause should be set if and only if the state is failed.  Got %s and %s instead.", state, th);
            this.state = state;
            this.shutdownWhenStartupFinishes = z;
            this.failure = th;
        }
    }

    @GuardedBy("monitor")
    private void checkCurrentState(Service.State state) {
        Service.State state2 = state();
        if (state2 != state) {
            if (state2 == Service.State.FAILED) {
                String strValueOf = String.valueOf(this);
                String strValueOf2 = String.valueOf(state);
                StringBuilder sbA = a.a(strValueOf2.length() + strValueOf.length() + 56, "Expected the service ", strValueOf, " to be ", strValueOf2);
                sbA.append(", but the service has FAILED");
                throw new IllegalStateException(sbA.toString(), failureCause());
            }
            String strValueOf3 = String.valueOf(this);
            String strValueOf4 = String.valueOf(state);
            String strValueOf5 = String.valueOf(state2);
            throw new IllegalStateException(a.a(a.a(strValueOf5.length() + strValueOf4.length() + strValueOf3.length() + 38, "Expected the service ", strValueOf3, " to be ", strValueOf4), ", but was ", strValueOf5));
        }
    }

    private void dispatchListenerEvents() {
        if (this.monitor.isOccupiedByCurrentThread()) {
            return;
        }
        this.listeners.dispatch();
    }

    private void enqueueFailedEvent(Service.State state, Throwable th) {
        this.listeners.enqueue(new AnonymousClass5(this, state, th));
    }

    private void enqueueRunningEvent() {
        this.listeners.enqueue(RUNNING_EVENT);
    }

    private void enqueueStartingEvent() {
        this.listeners.enqueue(STARTING_EVENT);
    }

    private void enqueueStoppingEvent(Service.State state) {
        if (state == Service.State.STARTING) {
            this.listeners.enqueue(STOPPING_FROM_STARTING_EVENT);
        } else {
            if (state != Service.State.RUNNING) {
                throw new AssertionError();
            }
            this.listeners.enqueue(STOPPING_FROM_RUNNING_EVENT);
        }
    }

    private void enqueueTerminatedEvent(Service.State state) {
        int iOrdinal = state.ordinal();
        if (iOrdinal == 0) {
            this.listeners.enqueue(TERMINATED_FROM_NEW_EVENT);
            return;
        }
        if (iOrdinal == 1) {
            this.listeners.enqueue(TERMINATED_FROM_STARTING_EVENT);
            return;
        }
        if (iOrdinal == 2) {
            this.listeners.enqueue(TERMINATED_FROM_RUNNING_EVENT);
        } else if (iOrdinal == 3) {
            this.listeners.enqueue(TERMINATED_FROM_STOPPING_EVENT);
        } else if (iOrdinal == 4 || iOrdinal == 5) {
            throw new AssertionError();
        }
    }

    public static ListenerCallQueue.Event<Service.Listener> stoppingEvent(Service.State state) {
        return new AnonymousClass4(state);
    }

    public static ListenerCallQueue.Event<Service.Listener> terminatedEvent(Service.State state) {
        return new AnonymousClass3(state);
    }

    @Override // com.google.common.util.concurrent.Service
    public final void addListener(Service.Listener listener, Executor executor) {
        this.listeners.addListener(listener, executor);
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitRunning() {
        this.monitor.enterWhenUninterruptibly(this.hasReachedRunning);
        try {
            checkCurrentState(Service.State.RUNNING);
        } finally {
            this.monitor.leave();
        }
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitTerminated() {
        this.monitor.enterWhenUninterruptibly(this.isStopped);
        try {
            checkCurrentState(Service.State.TERMINATED);
        } finally {
            this.monitor.leave();
        }
    }

    @Beta
    @ForOverride
    public void doCancelStart() {
    }

    @ForOverride
    public abstract void doStart();

    @ForOverride
    public abstract void doStop();

    @Override // com.google.common.util.concurrent.Service
    public final Throwable failureCause() {
        return this.snapshot.failureCause();
    }

    @Override // com.google.common.util.concurrent.Service
    public final boolean isRunning() {
        return state() == Service.State.RUNNING;
    }

    public final void notifyFailed(Throwable th) {
        if (th == null) {
            throw new NullPointerException();
        }
        this.monitor.enter();
        try {
            Service.State state = state();
            int iOrdinal = state.ordinal();
            if (iOrdinal != 0) {
                if (iOrdinal == 1 || iOrdinal == 2 || iOrdinal == 3) {
                    this.snapshot = new StateSnapshot(Service.State.FAILED, false, th);
                    this.listeners.enqueue(new AnonymousClass5(this, state, th));
                } else if (iOrdinal != 4) {
                }
                this.monitor.leave();
                dispatchListenerEvents();
                return;
            }
            String strValueOf = String.valueOf(state);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 22);
            sb.append("Failed while in state:");
            sb.append(strValueOf);
            throw new IllegalStateException(sb.toString(), th);
        } catch (Throwable th2) {
            this.monitor.leave();
            dispatchListenerEvents();
            throw th2;
        }
    }

    public final void notifyStarted() {
        this.monitor.enter();
        try {
            if (this.snapshot.state != Service.State.STARTING) {
                String strValueOf = String.valueOf(this.snapshot.state);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 43);
                sb.append("Cannot notifyStarted() when the service is ");
                sb.append(strValueOf);
                IllegalStateException illegalStateException = new IllegalStateException(sb.toString());
                notifyFailed(illegalStateException);
                throw illegalStateException;
            }
            if (this.snapshot.shutdownWhenStartupFinishes) {
                this.snapshot = new StateSnapshot(Service.State.STOPPING);
                doStop();
            } else {
                this.snapshot = new StateSnapshot(Service.State.RUNNING);
                enqueueRunningEvent();
            }
            this.monitor.leave();
            dispatchListenerEvents();
        } catch (Throwable th) {
            this.monitor.leave();
            dispatchListenerEvents();
            throw th;
        }
    }

    public final void notifyStopped() {
        this.monitor.enter();
        try {
            Service.State state = state();
            int iOrdinal = state.ordinal();
            if (iOrdinal != 0) {
                if (iOrdinal == 1 || iOrdinal == 2 || iOrdinal == 3) {
                    this.snapshot = new StateSnapshot(Service.State.TERMINATED);
                    enqueueTerminatedEvent(state);
                } else if (iOrdinal != 4 && iOrdinal != 5) {
                }
                this.monitor.leave();
                dispatchListenerEvents();
                return;
            }
            String strValueOf = String.valueOf(state);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 43);
            sb.append("Cannot notifyStopped() when the service is ");
            sb.append(strValueOf);
            throw new IllegalStateException(sb.toString());
        } catch (Throwable th) {
            this.monitor.leave();
            dispatchListenerEvents();
            throw th;
        }
    }

    @Override // com.google.common.util.concurrent.Service
    @CanIgnoreReturnValue
    public final Service startAsync() {
        if (!this.monitor.enterIf(this.isStartable)) {
            String strValueOf = String.valueOf(this);
            throw new IllegalStateException(a.a(strValueOf.length() + 33, "Service ", strValueOf, " has already been started"));
        }
        try {
            try {
                this.snapshot = new StateSnapshot(Service.State.STARTING);
                enqueueStartingEvent();
                doStart();
            } catch (Throwable th) {
                notifyFailed(th);
            }
            return this;
        } finally {
            this.monitor.leave();
            dispatchListenerEvents();
        }
    }

    @Override // com.google.common.util.concurrent.Service
    public final Service.State state() {
        return this.snapshot.externalState();
    }

    @Override // com.google.common.util.concurrent.Service
    @CanIgnoreReturnValue
    public final Service stopAsync() {
        try {
            if (this.monitor.enterIf(this.isStoppable)) {
                try {
                    Service.State state = state();
                    int iOrdinal = state.ordinal();
                    if (iOrdinal == 0) {
                        this.snapshot = new StateSnapshot(Service.State.TERMINATED);
                        enqueueTerminatedEvent(Service.State.NEW);
                    } else if (iOrdinal == 1) {
                        this.snapshot = new StateSnapshot(Service.State.STARTING, true, null);
                        enqueueStoppingEvent(Service.State.STARTING);
                        doCancelStart();
                    } else if (iOrdinal == 2) {
                        this.snapshot = new StateSnapshot(Service.State.STOPPING);
                        enqueueStoppingEvent(Service.State.RUNNING);
                        doStop();
                    } else if (iOrdinal == 3 || iOrdinal == 4 || iOrdinal == 5) {
                        String strValueOf = String.valueOf(state);
                        StringBuilder sb = new StringBuilder(strValueOf.length() + 45);
                        sb.append("isStoppable is incorrectly implemented, saw: ");
                        sb.append(strValueOf);
                        throw new AssertionError(sb.toString());
                    }
                } catch (Throwable th) {
                    notifyFailed(th);
                }
                this.monitor.leave();
                dispatchListenerEvents();
            }
            return this;
        } catch (Throwable th2) {
            this.monitor.leave();
            dispatchListenerEvents();
            throw th2;
        }
    }

    public String toString() {
        String simpleName = getClass().getSimpleName();
        String strValueOf = String.valueOf(state());
        return a.b(strValueOf.length() + simpleName.length() + 3, simpleName, " [", strValueOf, "]");
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitRunning(long j, TimeUnit timeUnit) throws TimeoutException {
        if (this.monitor.enterWhenUninterruptibly(this.hasReachedRunning, j, timeUnit)) {
            try {
                checkCurrentState(Service.State.RUNNING);
                return;
            } finally {
                this.monitor.leave();
            }
        }
        String strValueOf = String.valueOf(this);
        throw new TimeoutException(a.a(strValueOf.length() + 50, "Timed out waiting for ", strValueOf, " to reach the RUNNING state."));
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitTerminated(long j, TimeUnit timeUnit) throws TimeoutException {
        if (this.monitor.enterWhenUninterruptibly(this.isStopped, j, timeUnit)) {
            try {
                checkCurrentState(Service.State.TERMINATED);
            } finally {
                this.monitor.leave();
            }
        } else {
            String strValueOf = String.valueOf(this);
            String strValueOf2 = String.valueOf(state());
            throw new TimeoutException(a.b(strValueOf2.length() + strValueOf.length() + 65, "Timed out waiting for ", strValueOf, " to reach a terminal state. Current state: ", strValueOf2));
        }
    }
}
