package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zzfl extends zzjt<zzfm, zzfl> implements zzld {
    public zzfl() {
        super(zzfm.zza);
    }

    public final zzfl zza(long j) {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        zzfm.zzf((zzfm) this.zza, j);
        return this;
    }

    public final zzfl zzb(int i) {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        zzfm.zze((zzfm) this.zza, i);
        return this;
    }

    public /* synthetic */ zzfl(zzff zzffVar) {
        super(zzfm.zza);
    }
}
