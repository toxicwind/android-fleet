package com.google.firebase.firestore.remote;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import c.a.AbstractC0226j;
import c.a.C0219ea;
import c.a.C0223ga;
import c.a.za;
import com.google.firebase.firestore.remote.Stream.StreamCallback;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.ExponentialBackoff;
import com.google.firebase.firestore.util.Logger;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractStream<ReqT, RespT, CallbackT extends Stream.StreamCallback> implements Stream<CallbackT> {
    public static final double BACKOFF_FACTOR = 1.5d;
    public final ExponentialBackoff backoff;
    public AbstractC0226j<ReqT, RespT> call;
    public final FirestoreChannel firestoreChannel;

    @Nullable
    public AsyncQueue.DelayedTask healthCheck;
    public final AsyncQueue.TimerId healthTimerId;

    @Nullable
    public AsyncQueue.DelayedTask idleTimer;
    public final AsyncQueue.TimerId idleTimerId;
    public final CallbackT listener;
    public final C0223ga<ReqT, RespT> methodDescriptor;
    public final AsyncQueue workerQueue;
    public static final long BACKOFF_INITIAL_DELAY_MS = TimeUnit.SECONDS.toMillis(1);
    public static final long BACKOFF_MAX_DELAY_MS = TimeUnit.MINUTES.toMillis(1);
    public static final long IDLE_TIMEOUT_MS = TimeUnit.MINUTES.toMillis(1);
    public static final long HEALTHY_TIMEOUT_MS = TimeUnit.SECONDS.toMillis(10);
    public static final long BACKOFF_CLIENT_NETWORK_FAILURE_MAX_DELAY_MS = TimeUnit.SECONDS.toMillis(10);
    public Stream.State state = Stream.State.Initial;
    public long closeCount = 0;
    public final AbstractStream<ReqT, RespT, CallbackT>.IdleTimeoutRunnable idleTimeoutRunnable = new IdleTimeoutRunnable();

    class CloseGuardedRunner {
        public final long initialCloseCount;

        public CloseGuardedRunner(long j) {
            this.initialCloseCount = j;
        }

        public void run(Runnable runnable) {
            AbstractStream.this.workerQueue.verifyIsCurrentThread();
            if (AbstractStream.this.closeCount == this.initialCloseCount) {
                runnable.run();
            } else {
                Logger.debug(AbstractStream.this.getClass().getSimpleName(), "stream callback skipped by CloseGuardedRunner.", new Object[0]);
            }
        }
    }

    @VisibleForTesting
    class IdleTimeoutRunnable implements Runnable {
        public IdleTimeoutRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractStream.this.handleIdleCloseTimer();
        }
    }

    public AbstractStream(FirestoreChannel firestoreChannel, C0223ga<ReqT, RespT> c0223ga, AsyncQueue asyncQueue, AsyncQueue.TimerId timerId, AsyncQueue.TimerId timerId2, AsyncQueue.TimerId timerId3, CallbackT callbackt) {
        this.firestoreChannel = firestoreChannel;
        this.methodDescriptor = c0223ga;
        this.workerQueue = asyncQueue;
        this.idleTimerId = timerId2;
        this.healthTimerId = timerId3;
        this.listener = callbackt;
        this.backoff = new ExponentialBackoff(asyncQueue, timerId, BACKOFF_INITIAL_DELAY_MS, 1.5d, BACKOFF_MAX_DELAY_MS);
    }

    private void cancelHealthCheck() {
        AsyncQueue.DelayedTask delayedTask = this.healthCheck;
        if (delayedTask != null) {
            delayedTask.cancel();
            this.healthCheck = null;
        }
    }

    private void cancelIdleCheck() {
        AsyncQueue.DelayedTask delayedTask = this.idleTimer;
        if (delayedTask != null) {
            delayedTask.cancel();
            this.idleTimer = null;
        }
    }

    private void close(Stream.State state, za zaVar) {
        Assert.hardAssert(isStarted(), "Only started streams should be closed.", new Object[0]);
        Assert.hardAssert(state == Stream.State.Error || zaVar.c(), "Can't provide an error when not in an error state.", new Object[0]);
        this.workerQueue.verifyIsCurrentThread();
        Datastore.isMissingSslCiphers(zaVar);
        cancelIdleCheck();
        cancelHealthCheck();
        this.backoff.cancel();
        this.closeCount++;
        za.a aVar = zaVar.p;
        if (aVar == za.a.OK) {
            this.backoff.reset();
        } else if (aVar == za.a.RESOURCE_EXHAUSTED) {
            Logger.debug(getClass().getSimpleName(), "(%x) Using maximum backoff delay to prevent overloading the backend.", Integer.valueOf(System.identityHashCode(this)));
            this.backoff.resetToMax();
        } else if (aVar == za.a.UNAUTHENTICATED && this.state != Stream.State.Healthy) {
            this.firestoreChannel.invalidateToken();
        } else if (aVar == za.a.UNAVAILABLE) {
            Throwable th = zaVar.r;
            if ((th instanceof UnknownHostException) || (th instanceof ConnectException)) {
                this.backoff.setTemporaryMaxDelay(BACKOFF_CLIENT_NETWORK_FAILURE_MAX_DELAY_MS);
            }
        }
        if (state != Stream.State.Error) {
            Logger.debug(getClass().getSimpleName(), "(%x) Performing stream teardown", Integer.valueOf(System.identityHashCode(this)));
            tearDown();
        }
        if (this.call != null) {
            if (zaVar.c()) {
                Logger.debug(getClass().getSimpleName(), "(%x) Closing stream client-side", Integer.valueOf(System.identityHashCode(this)));
                this.call.halfClose();
            }
            this.call = null;
        }
        this.state = state;
        this.listener.onClose(zaVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleIdleCloseTimer() {
        if (isOpen()) {
            close(Stream.State.Initial, za.f1936c);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onOpen() {
        this.state = Stream.State.Open;
        this.listener.onOpen();
        if (this.healthCheck == null) {
            this.healthCheck = this.workerQueue.enqueueAfterDelay(this.healthTimerId, HEALTHY_TIMEOUT_MS, new Runnable() { // from class: b.e.b.d.f.f
                @Override // java.lang.Runnable
                public final void run() {
                    this.f494a.a();
                }
            });
        }
    }

    private void performBackoff() {
        Assert.hardAssert(this.state == Stream.State.Error, "Should only perform backoff in an error state", new Object[0]);
        this.state = Stream.State.Backoff;
        this.backoff.backoffAndRun(new Runnable() { // from class: b.e.b.d.f.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f486a.b();
            }
        });
    }

    public /* synthetic */ void a() {
        if (isOpen()) {
            this.state = Stream.State.Healthy;
        }
    }

    public /* synthetic */ void b() {
        Assert.hardAssert(this.state == Stream.State.Backoff, "State should still be backoff but was %s", this.state);
        this.state = Stream.State.Initial;
        start();
        Assert.hardAssert(isStarted(), "Stream should have started", new Object[0]);
    }

    @VisibleForTesting
    public void handleServerClose(za zaVar) {
        Assert.hardAssert(isStarted(), "Can't handle server close on non-started stream!", new Object[0]);
        close(Stream.State.Error, zaVar);
    }

    @Override // com.google.firebase.firestore.remote.Stream
    public void inhibitBackoff() {
        Assert.hardAssert(!isStarted(), "Can only inhibit backoff after in a stopped state", new Object[0]);
        this.workerQueue.verifyIsCurrentThread();
        this.state = Stream.State.Initial;
        this.backoff.reset();
    }

    @Override // com.google.firebase.firestore.remote.Stream
    public boolean isOpen() {
        this.workerQueue.verifyIsCurrentThread();
        Stream.State state = this.state;
        return state == Stream.State.Open || state == Stream.State.Healthy;
    }

    @Override // com.google.firebase.firestore.remote.Stream
    public boolean isStarted() {
        this.workerQueue.verifyIsCurrentThread();
        Stream.State state = this.state;
        return state == Stream.State.Starting || state == Stream.State.Backoff || isOpen();
    }

    public void markIdle() {
        if (isOpen() && this.idleTimer == null) {
            this.idleTimer = this.workerQueue.enqueueAfterDelay(this.idleTimerId, IDLE_TIMEOUT_MS, this.idleTimeoutRunnable);
        }
    }

    public abstract void onNext(RespT respt);

    @Override // com.google.firebase.firestore.remote.Stream
    public void start() {
        this.workerQueue.verifyIsCurrentThread();
        Assert.hardAssert(this.call == null, "Last call still set", new Object[0]);
        Assert.hardAssert(this.idleTimer == null, "Idle timer still set", new Object[0]);
        Stream.State state = this.state;
        if (state == Stream.State.Error) {
            performBackoff();
            return;
        }
        Assert.hardAssert(state == Stream.State.Initial, "Already started", new Object[0]);
        this.call = this.firestoreChannel.runBidiStreamingRpc(this.methodDescriptor, new StreamObserver(new CloseGuardedRunner(this.closeCount)));
        this.state = Stream.State.Starting;
    }

    @Override // com.google.firebase.firestore.remote.Stream
    public void stop() {
        if (isStarted()) {
            close(Stream.State.Initial, za.f1936c);
        }
    }

    public void tearDown() {
    }

    public void writeRequest(ReqT reqt) {
        this.workerQueue.verifyIsCurrentThread();
        Logger.debug(getClass().getSimpleName(), "(%x) Stream sending: %s", Integer.valueOf(System.identityHashCode(this)), reqt);
        cancelIdleCheck();
        this.call.sendMessage(reqt);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public class StreamObserver implements IncomingStreamObserver<RespT> {
        public final AbstractStream<ReqT, RespT, CallbackT>.CloseGuardedRunner dispatcher;

        public StreamObserver(AbstractStream<ReqT, RespT, CallbackT>.CloseGuardedRunner closeGuardedRunner) {
            this.dispatcher = closeGuardedRunner;
        }

        public /* synthetic */ void a(C0219ea c0219ea) {
            Set<String> setUnmodifiableSet;
            if (Logger.isDebugEnabled()) {
                HashMap map = new HashMap();
                if (c0219ea.b()) {
                    setUnmodifiableSet = Collections.emptySet();
                } else {
                    HashSet hashSet = new HashSet(c0219ea.f1801d);
                    for (int i = 0; i < c0219ea.f1801d; i++) {
                        hashSet.add(new String(c0219ea.b(i), 0));
                    }
                    setUnmodifiableSet = Collections.unmodifiableSet(hashSet);
                }
                for (String str : setUnmodifiableSet) {
                    if (Datastore.WHITE_LISTED_HEADERS.contains(str.toLowerCase(Locale.ENGLISH))) {
                        map.put(str, (String) c0219ea.b(C0219ea.e.a(str, C0219ea.f1798a)));
                    }
                }
                if (map.isEmpty()) {
                    return;
                }
                Logger.debug(AbstractStream.this.getClass().getSimpleName(), "(%x) Stream received headers: %s", Integer.valueOf(System.identityHashCode(AbstractStream.this)), map);
            }
        }

        @Override // com.google.firebase.firestore.remote.IncomingStreamObserver
        public void onClose(final za zaVar) {
            this.dispatcher.run(new Runnable() { // from class: b.e.b.d.f.b
                @Override // java.lang.Runnable
                public final void run() {
                    this.f487a.a(zaVar);
                }
            });
        }

        @Override // com.google.firebase.firestore.remote.IncomingStreamObserver
        public void onHeaders(final C0219ea c0219ea) {
            this.dispatcher.run(new Runnable() { // from class: b.e.b.d.f.e
                @Override // java.lang.Runnable
                public final void run() {
                    this.f492a.a(c0219ea);
                }
            });
        }

        @Override // com.google.firebase.firestore.remote.IncomingStreamObserver
        public void onNext(final RespT respt) {
            this.dispatcher.run(new Runnable() { // from class: b.e.b.d.f.d
                @Override // java.lang.Runnable
                public final void run() {
                    this.f490a.a(respt);
                }
            });
        }

        @Override // com.google.firebase.firestore.remote.IncomingStreamObserver
        public void onOpen() {
            this.dispatcher.run(new Runnable() { // from class: b.e.b.d.f.c
                @Override // java.lang.Runnable
                public final void run() {
                    this.f489a.a();
                }
            });
        }

        public /* synthetic */ void a(Object obj) {
            if (Logger.isDebugEnabled()) {
                Logger.debug(AbstractStream.this.getClass().getSimpleName(), "(%x) Stream received: %s", Integer.valueOf(System.identityHashCode(AbstractStream.this)), obj);
            }
            AbstractStream.this.onNext(obj);
        }

        public /* synthetic */ void a() {
            Logger.debug(AbstractStream.this.getClass().getSimpleName(), "(%x) Stream is open", Integer.valueOf(System.identityHashCode(AbstractStream.this)));
            AbstractStream.this.onOpen();
        }

        public /* synthetic */ void a(za zaVar) {
            if (zaVar.c()) {
                Logger.debug(AbstractStream.this.getClass().getSimpleName(), "(%x) Stream closed.", Integer.valueOf(System.identityHashCode(AbstractStream.this)));
            } else {
                Logger.warn(AbstractStream.this.getClass().getSimpleName(), "(%x) Stream closed with status: %s.", Integer.valueOf(System.identityHashCode(AbstractStream.this)), zaVar);
            }
            AbstractStream.this.handleServerClose(zaVar);
        }
    }
}
