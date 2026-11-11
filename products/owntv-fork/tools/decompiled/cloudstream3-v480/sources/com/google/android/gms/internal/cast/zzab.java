package com.google.android.gms.internal.cast;

import com.google.android.gms.common.util.DefaultClock;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzab {
    final int zza;
    final long zzb = DefaultClock.getInstance().currentTimeMillis();
    private long zzc;

    public zzab(zzaa zzaaVar) {
        this.zza = zzaaVar.zza;
    }

    public final zzqq zza() {
        zzqp zzqpVarZza = zzqq.zza();
        zzqpVarZza.zza((int) (this.zzb - this.zzc));
        int i = this.zza;
        int i2 = 2;
        if (i != 1) {
            if (i != 2) {
                i2 = i != 3 ? 1 : 4;
            } else {
                i2 = 3;
            }
        }
        zzqpVarZza.zzb(i2);
        return (zzqq) zzqpVarZza.zzr();
    }

    public final void zzb(long j) {
        this.zzc = j;
    }
}
