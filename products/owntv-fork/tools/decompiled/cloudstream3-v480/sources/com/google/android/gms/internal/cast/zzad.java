package com.google.android.gms.internal.cast;

import com.google.android.gms.common.util.DefaultClock;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzad {
    final long zza;
    long zzb;
    private long zzc;
    private final AtomicInteger zzd;
    private final int zze;

    public zzad(zzac zzacVar) {
        this.zze = zzacVar.zza;
        long jCurrentTimeMillis = DefaultClock.getInstance().currentTimeMillis();
        this.zza = jCurrentTimeMillis;
        this.zzb = jCurrentTimeMillis;
        this.zzd = new AtomicInteger(1);
    }

    public final zzqo zza() {
        zzqn zzqnVarZza = zzqo.zza();
        zzqnVarZza.zzd(this.zze);
        zzqnVarZza.zza(this.zzd.get());
        zzqnVarZza.zzb((int) (this.zza - this.zzc));
        zzqnVarZza.zzc((int) (this.zzb - this.zzc));
        return (zzqo) zzqnVarZza.zzr();
    }

    public final void zzb() {
        this.zzd.incrementAndGet();
        this.zzb = DefaultClock.getInstance().currentTimeMillis();
    }

    public final void zzc(long j) {
        this.zzc = j;
    }
}
