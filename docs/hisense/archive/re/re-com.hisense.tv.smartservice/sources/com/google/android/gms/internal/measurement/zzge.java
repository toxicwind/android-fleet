package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zzge extends zzjt<zzgf, zzge> implements zzld {
    public zzge() {
        super(zzgf.zza);
    }

    public final zzge zza(Iterable<? extends Long> iterable) {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        zzgf.zzh((zzgf) this.zza, iterable);
        return this;
    }

    public final zzge zzb(int i) {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        zzgf.zzg((zzgf) this.zza, i);
        return this;
    }

    public /* synthetic */ zzge(zzff zzffVar) {
        super(zzgf.zza);
    }
}
