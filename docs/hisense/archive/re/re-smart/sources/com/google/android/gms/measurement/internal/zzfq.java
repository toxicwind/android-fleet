package com.google.android.gms.measurement.internal;

import androidx.annotation.NonNull;
import b.a.a.a.a;
import com.google.android.gms.common.internal.Preconditions;
import com.google.common.primitives.UnsignedLong;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/* JADX INFO: loaded from: classes.dex */
public final class zzfq<V> extends FutureTask<V> implements Comparable<zzfq<V>> {
    public final boolean zza;
    public final /* synthetic */ zzfs zzb;
    public final long zzc;
    public final String zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzfq(zzfs zzfsVar, Runnable runnable, boolean z, String str) {
        super(runnable, null);
        this.zzb = zzfsVar;
        Preconditions.checkNotNull(str);
        long andIncrement = zzfs.zza.getAndIncrement();
        this.zzc = andIncrement;
        this.zzd = str;
        this.zza = z;
        if (andIncrement == UnsignedLong.UNSIGNED_MASK) {
            a.a(zzfsVar.zzs, "Tasks index overflow");
        }
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(@NonNull Object obj) {
        zzfq zzfqVar = (zzfq) obj;
        boolean z = this.zza;
        if (z != zzfqVar.zza) {
            return !z ? 1 : -1;
        }
        long j = this.zzc;
        long j2 = zzfqVar.zzc;
        if (j < j2) {
            return -1;
        }
        if (j > j2) {
            return 1;
        }
        this.zzb.zzs.zzay().zzh().zzb("Two tasks share the same index. index", Long.valueOf(this.zzc));
        return 0;
    }

    @Override // java.util.concurrent.FutureTask
    public final void setException(Throwable th) {
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;
        this.zzb.zzs.zzay().zzd().zzb(this.zzd, th);
        if ((th instanceof zzfo) && (defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()) != null) {
            defaultUncaughtExceptionHandler.uncaughtException(Thread.currentThread(), th);
        }
        super.setException(th);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzfq(zzfs zzfsVar, Callable<V> callable, boolean z, String str) {
        super(callable);
        this.zzb = zzfsVar;
        Preconditions.checkNotNull("Task exception on worker thread");
        long andIncrement = zzfs.zza.getAndIncrement();
        this.zzc = andIncrement;
        this.zzd = "Task exception on worker thread";
        this.zza = z;
        if (andIncrement == UnsignedLong.UNSIGNED_MASK) {
            a.a(zzfsVar.zzs, "Tasks index overflow");
        }
    }
}
