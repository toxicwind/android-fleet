package com.google.android.gms.internal.cast;

import com.google.android.gms.common.util.DefaultClock;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzcb {
    final long zza = DefaultClock.getInstance().currentTimeMillis();
    private final Integer zzb;
    private final Boolean zzc;
    private long zzd;
    private final int zze;

    public zzcb(zzca zzcaVar) {
        this.zze = zzcaVar.zzc;
        this.zzb = zzcaVar.zza;
        this.zzc = zzcaVar.zzb;
    }

    public final zzqk zza() {
        zzqj zzqjVarZza = zzqk.zza();
        zzqjVarZza.zze(this.zze);
        int i = (int) (this.zza - this.zzd);
        zzqjVarZza.zzc(i);
        zzqjVarZza.zzd(i);
        Integer num = this.zzb;
        if (num != null) {
            zzqjVarZza.zzb(num.intValue());
        }
        Boolean bool = this.zzc;
        if (bool != null) {
            zzqjVarZza.zza(bool.booleanValue());
        }
        return (zzqk) zzqjVarZza.zzr();
    }

    public final void zzb(long j) {
        this.zzd = j;
    }

    public final int zzc() {
        return this.zze;
    }
}
