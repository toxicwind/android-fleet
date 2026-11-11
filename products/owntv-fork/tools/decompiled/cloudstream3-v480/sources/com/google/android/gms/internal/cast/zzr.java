package com.google.android.gms.internal.cast;

import com.google.android.gms.common.util.DefaultClock;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzr {
    private final int zza;
    private final long zzb = DefaultClock.getInstance().currentTimeMillis();
    private long zzc;

    public zzr(zzq zzqVar) {
        this.zza = zzqVar.zza;
    }

    public final zzqi zza() {
        int i = this.zza;
        zzqh zzqhVarZza = zzqi.zza();
        int i2 = 2;
        if (i != 1) {
            if (i != 2) {
                i2 = 4;
                if (i != 3) {
                    i2 = i != 4 ? 1 : 5;
                }
            } else {
                i2 = 3;
            }
        }
        zzqhVarZza.zzb(i2);
        zzqhVarZza.zza((int) (this.zzb - this.zzc));
        return (zzqi) zzqhVarZza.zzr();
    }

    public final void zzb(long j) {
        this.zzc = j;
    }

    final boolean zzc() {
        return this.zza == 2;
    }
}
