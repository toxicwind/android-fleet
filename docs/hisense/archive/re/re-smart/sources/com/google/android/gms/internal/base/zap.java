package com.google.android.gms.internal.base;

import androidx.annotation.NonNull;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class zap implements zal {
    public zap() {
    }

    @Override // com.google.android.gms.internal.base.zal
    @NonNull
    public final ExecutorService zaa(int i, int i2) {
        return zaa(4, Executors.defaultThreadFactory(), i2);
    }

    public /* synthetic */ zap(zam zamVar) {
    }

    @Override // com.google.android.gms.internal.base.zal
    @NonNull
    public final ExecutorService zaa(int i, ThreadFactory threadFactory, int i2) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(i, i, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), threadFactory);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return Executors.unconfigurableExecutorService(threadPoolExecutor);
    }
}
