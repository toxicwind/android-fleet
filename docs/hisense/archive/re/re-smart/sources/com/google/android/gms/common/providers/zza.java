package com.google.android.gms.common.providers;

import com.google.android.gms.internal.common.zze;
import com.google.android.gms.internal.common.zzh;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: loaded from: classes.dex */
public final class zza implements PooledExecutorsProvider.PooledExecutorFactory {
    @Override // com.google.android.gms.common.providers.PooledExecutorsProvider.PooledExecutorFactory
    public final ScheduledExecutorService newSingleThreadScheduledExecutor() {
        zze zzeVar = zzh.zzb;
        return Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(1));
    }
}
