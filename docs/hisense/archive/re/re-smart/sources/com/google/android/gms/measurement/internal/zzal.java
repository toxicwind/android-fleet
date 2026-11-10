package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
public final class zzal implements Runnable {
    public final /* synthetic */ zzgq zza;
    public final /* synthetic */ zzam zzb;

    public zzal(zzam zzamVar, zzgq zzgqVar) {
        this.zzb = zzamVar;
        this.zza = zzgqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzaw();
        if (zzaa.zza()) {
            this.zza.zzaz().zzp(this);
            return;
        }
        boolean zZze = this.zzb.zze();
        zzam zzamVar = this.zzb;
        zzamVar.zzd = 0L;
        if (zZze) {
            zzamVar.zzc();
        }
    }
}
