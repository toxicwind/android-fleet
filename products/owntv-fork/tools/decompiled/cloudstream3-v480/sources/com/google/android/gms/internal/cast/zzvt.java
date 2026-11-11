package com.google.android.gms.internal.cast;

import android.os.Build;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzvt extends zzvq implements ScheduledExecutorService, AutoCloseable, zzvp {
    final ScheduledExecutorService zza;

    zzvt(ScheduledExecutorService scheduledExecutorService) {
        super(scheduledExecutorService);
        scheduledExecutorService.getClass();
        this.zza = scheduledExecutorService;
    }

    @Override // com.google.android.gms.internal.cast.zzvd, java.lang.AutoCloseable
    public final /* synthetic */ void close() {
        if ((Build.VERSION.SDK_INT <= 23 || this != ForkJoinPool.commonPool()) && !isTerminated()) {
            shutdown();
            boolean zAwaitTermination = false;
            boolean z = false;
            while (!zAwaitTermination) {
                try {
                    zAwaitTermination = awaitTermination(1L, TimeUnit.DAYS);
                } catch (InterruptedException unused) {
                    if (!z) {
                        shutdownNow();
                    }
                    z = true;
                }
            }
            if (z) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final /* bridge */ /* synthetic */ ScheduledFuture schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        ScheduledExecutorService scheduledExecutorService = this.zza;
        zzvx zzvxVarZzo = zzvx.zzo(runnable, null);
        return new zzvr(zzvxVarZzo, scheduledExecutorService.schedule(zzvxVarZzo, j, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final /* bridge */ /* synthetic */ ScheduledFuture scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        zzvs zzvsVar = new zzvs(runnable);
        return new zzvr(zzvsVar, this.zza.scheduleAtFixedRate(zzvsVar, j, j2, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final /* bridge */ /* synthetic */ ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        zzvs zzvsVar = new zzvs(runnable);
        return new zzvr(zzvsVar, this.zza.scheduleWithFixedDelay(zzvsVar, j, j2, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final /* bridge */ /* synthetic */ ScheduledFuture schedule(Callable callable, long j, TimeUnit timeUnit) {
        zzvx zzvxVar = new zzvx(callable);
        return new zzvr(zzvxVar, this.zza.schedule(zzvxVar, j, timeUnit));
    }
}
