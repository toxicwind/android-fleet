package com.google.android.gms.internal.cast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzvu {
    public static zzvp zza(ExecutorService executorService) {
        if (executorService instanceof zzvp) {
            return (zzvp) executorService;
        }
        return executorService instanceof ScheduledExecutorService ? new zzvt((ScheduledExecutorService) executorService) : new zzvq(executorService);
    }
}
