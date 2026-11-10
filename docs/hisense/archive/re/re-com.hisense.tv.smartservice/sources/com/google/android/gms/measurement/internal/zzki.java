package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
public abstract class zzki extends zzkh {
    public boolean zza;

    public zzki(zzks zzksVar) {
        super(zzksVar);
        this.zzf.zzL();
    }

    public final void zzY() {
        if (!zzaa()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzZ() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        }
        zzb();
        this.zzf.zzG();
        this.zza = true;
    }

    public final boolean zzaa() {
        return this.zza;
    }

    public abstract boolean zzb();
}
