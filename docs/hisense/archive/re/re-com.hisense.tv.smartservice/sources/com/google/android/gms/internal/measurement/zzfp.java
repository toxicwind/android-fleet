package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zzfp extends zzjt<zzfq, zzfp> implements zzld {
    public zzfp() {
        super(zzfq.zza);
    }

    public final zzfp zza(long j) {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        zzfq.zzd((zzfq) this.zza, j);
        return this;
    }

    public final zzfp zzb(String str) {
        if (this.zzb) {
            zzaE();
            this.zzb = false;
        }
        zzfq.zzc((zzfq) this.zza, str);
        return this;
    }

    public /* synthetic */ zzfp(zzff zzffVar) {
        super(zzfq.zza);
    }
}
