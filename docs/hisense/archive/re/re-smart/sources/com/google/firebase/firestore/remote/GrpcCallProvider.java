package com.google.firebase.firestore.remote;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import b.a.a.a.a;
import b.e.b.d.f.u;
import c.a.AbstractC0218e;
import c.a.AbstractC0226j;
import c.a.C0212ba;
import c.a.C0220f;
import c.a.C0223ga;
import c.a.EnumC0234s;
import c.a.X;
import c.a.Y;
import c.a.Z;
import c.a.a.d;
import c.a.c.h;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.core.DatabaseInfo;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Supplier;
import com.google.firestore.v1.FirestoreGrpc;
import com.ju.lib.utils.base.LoopLogic;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class GrpcCallProvider {
    public static final int CONNECTIVITY_ATTEMPT_TIMEOUT_MS = 15000;
    public static final String LOG_TAG = "GrpcCallProvider";
    public static Supplier<Y<?>> overrideChannelBuilderSupplier;
    public final AsyncQueue asyncQueue;
    public C0220f callOptions;
    public Task<X> channelTask;
    public AsyncQueue.DelayedTask connectivityAttemptTimer;
    public final Context context;
    public final DatabaseInfo databaseInfo;
    public final AbstractC0218e firestoreHeaders;

    public GrpcCallProvider(AsyncQueue asyncQueue, Context context, DatabaseInfo databaseInfo, AbstractC0218e abstractC0218e) {
        this.asyncQueue = asyncQueue;
        this.context = context;
        this.databaseInfo = databaseInfo;
        this.firestoreHeaders = abstractC0218e;
        initChannelTask();
    }

    private void clearConnectivityAttemptTimer() {
        if (this.connectivityAttemptTimer != null) {
            Logger.debug(LOG_TAG, "Clearing the connectivityAttemptTimer", new Object[0]);
            this.connectivityAttemptTimer.cancel();
            this.connectivityAttemptTimer = null;
        }
    }

    private X initChannel(Context context, DatabaseInfo databaseInfo) {
        Y<?> y;
        try {
            ProviderInstaller.installIfNeeded(context);
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IllegalStateException e2) {
            Logger.warn(LOG_TAG, "Failed to update ssl context: %s", e2);
        }
        Supplier<Y<?>> supplier = overrideChannelBuilderSupplier;
        if (supplier != null) {
            y = supplier.get();
        } else {
            String host = databaseInfo.getHost();
            List<Z> listC = C0212ba.a().c();
            Z z = listC.isEmpty() ? null : listC.get(0);
            if (z == null) {
                throw new Z.a("No functional channel service provider found. Try adding a dependency on the grpc-okhttp, grpc-netty, or grpc-netty-shaded artifact");
            }
            h hVar = new h(host);
            if (!databaseInfo.isSslEnabled()) {
                hVar.b();
            }
            y = hVar;
        }
        y.a(30L, TimeUnit.SECONDS);
        d dVar = new d(y);
        dVar.f867b = context;
        return new d.a(dVar.f866a.a(), dVar.f867b);
    }

    private void initChannelTask() {
        this.channelTask = Tasks.call(Executors.BACKGROUND_EXECUTOR, new Callable() { // from class: b.e.b.d.f.o
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f514a.a();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onConnectivityStateChange, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void c(final X x) {
        EnumC0234s enumC0234sA = x.a(true);
        Logger.debug(LOG_TAG, a.a("Current gRPC connectivity state: ", enumC0234sA), new Object[0]);
        clearConnectivityAttemptTimer();
        if (enumC0234sA == EnumC0234s.CONNECTING) {
            Logger.debug(LOG_TAG, "Setting the connectivityAttemptTimer", new Object[0]);
            this.connectivityAttemptTimer = this.asyncQueue.enqueueAfterDelay(AsyncQueue.TimerId.CONNECTIVITY_ATTEMPT_TIMER, LoopLogic.DEFAULT_OVER_TIME_DURATION, new Runnable() { // from class: b.e.b.d.f.q
                @Override // java.lang.Runnable
                public final void run() {
                    this.f517a.b(x);
                }
            });
        }
        x.a(enumC0234sA, new Runnable() { // from class: b.e.b.d.f.p
            @Override // java.lang.Runnable
            public final void run() {
                this.f515a.d(x);
            }
        });
    }

    @VisibleForTesting
    public static void overrideChannelBuilder(Supplier<Y<?>> supplier) {
        overrideChannelBuilderSupplier = supplier;
    }

    private void resetChannel(X x) {
        this.asyncQueue.enqueueAndForget(new u(this, x));
    }

    public /* synthetic */ Task a(C0223ga c0223ga, Task task) {
        return Tasks.forResult(((X) task.getResult()).a(c0223ga, this.callOptions));
    }

    public /* synthetic */ void b(X x) {
        Logger.debug(LOG_TAG, "connectivityAttemptTimer elapsed. Resetting the channel.", new Object[0]);
        clearConnectivityAttemptTimer();
        this.asyncQueue.enqueueAndForget(new u(this, x));
    }

    public <ReqT, RespT> Task<AbstractC0226j<ReqT, RespT>> createClientCall(final C0223ga<ReqT, RespT> c0223ga) {
        return (Task<AbstractC0226j<ReqT, RespT>>) this.channelTask.continueWithTask(this.asyncQueue.getExecutor(), new Continuation() { // from class: b.e.b.d.f.t
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.f523a.a(c0223ga, task);
            }
        });
    }

    public /* synthetic */ void d(final X x) {
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: b.e.b.d.f.r
            @Override // java.lang.Runnable
            public final void run() {
                this.f519a.c(x);
            }
        });
    }

    public /* synthetic */ void e(X x) {
        x.e();
        initChannelTask();
    }

    public void shutdown() {
        try {
            X x = (X) Tasks.await(this.channelTask);
            x.d();
            try {
                if (x.a(1L, TimeUnit.SECONDS)) {
                    return;
                }
                Logger.debug(FirestoreChannel.class.getSimpleName(), "Unable to gracefully shutdown the gRPC ManagedChannel. Will attempt an immediate shutdown.", new Object[0]);
                x.e();
                if (x.a(60L, TimeUnit.SECONDS)) {
                    return;
                }
                Logger.warn(FirestoreChannel.class.getSimpleName(), "Unable to forcefully shutdown the gRPC ManagedChannel.", new Object[0]);
            } catch (InterruptedException unused) {
                x.e();
                Logger.warn(FirestoreChannel.class.getSimpleName(), "Interrupted while shutting down the gRPC Managed Channel", new Object[0]);
                Thread.currentThread().interrupt();
            }
        } catch (InterruptedException unused2) {
            Logger.warn(FirestoreChannel.class.getSimpleName(), "Interrupted while retrieving the gRPC Managed Channel", new Object[0]);
            Thread.currentThread().interrupt();
        } catch (ExecutionException e2) {
            Logger.warn(FirestoreChannel.class.getSimpleName(), "Channel is not initialized, shutdown will just do nothing. Channel initializing run into exception: %s", e2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ X a() {
        final X xInitChannel = initChannel(this.context, this.databaseInfo);
        this.asyncQueue.enqueueAndForget(new Runnable() { // from class: b.e.b.d.f.s
            @Override // java.lang.Runnable
            public final void run() {
                this.f521a.a(xInitChannel);
            }
        });
        this.callOptions = ((FirestoreGrpc.FirestoreStub) ((FirestoreGrpc.FirestoreStub) FirestoreGrpc.newStub(xInitChannel).withCallCredentials(this.firestoreHeaders)).withExecutor(this.asyncQueue.getExecutor())).getCallOptions();
        Logger.debug(LOG_TAG, "Channel successfully reset.", new Object[0]);
        return xInitChannel;
    }
}
