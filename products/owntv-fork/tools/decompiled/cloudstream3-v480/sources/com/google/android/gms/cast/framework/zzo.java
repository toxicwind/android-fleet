package com.google.android.gms.cast.framework;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzo {
    private int zza = 0;
    private int zzb = -1;

    public final zzo zza(int i) {
        this.zza = i;
        return this;
    }

    public final zzo zzb(int i) {
        this.zzb = i;
        return this;
    }

    public final zzq zzc() {
        int iZza = this.zza;
        if (iZza == 0) {
            iZza = CastContext.zza(this.zzb);
            this.zza = iZza;
        }
        return new zzq(iZza, this.zzb, null);
    }
}
