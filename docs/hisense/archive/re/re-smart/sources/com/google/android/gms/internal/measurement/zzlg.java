package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zzlg<T> implements zzln<T> {
    public final zzlc zza;
    public final zzmb<?, ?> zzb;
    public final boolean zzc;
    public final zzjk<?> zzd;

    public zzlg(zzmb<?, ?> zzmbVar, zzjk<?> zzjkVar, zzlc zzlcVar) {
        this.zzb = zzmbVar;
        this.zzc = zzjkVar.zzc(zzlcVar);
        this.zzd = zzjkVar;
        this.zza = zzlcVar;
    }

    public static <T> zzlg<T> zzc(zzmb<?, ?> zzmbVar, zzjk<?> zzjkVar, zzlc zzlcVar) {
        return new zzlg<>(zzmbVar, zzjkVar, zzlcVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzln
    public final int zza(T t) {
        zzmb<?, ?> zzmbVar = this.zzb;
        int iZzb = zzmbVar.zzb(zzmbVar.zzc(t));
        if (!this.zzc) {
            return iZzb;
        }
        this.zzd.zza(t);
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzln
    public final int zzb(T t) {
        int iHashCode = this.zzb.zzc(t).hashCode();
        if (!this.zzc) {
            return iHashCode;
        }
        this.zzd.zza(t);
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzln
    public final T zze() {
        return (T) this.zza.zzbC().zzaC();
    }

    @Override // com.google.android.gms.internal.measurement.zzln
    public final void zzf(T t) {
        this.zzb.zzg(t);
        this.zzd.zzb(t);
    }

    @Override // com.google.android.gms.internal.measurement.zzln
    public final void zzg(T t, T t2) {
        zzlp.zzF(this.zzb, t, t2);
        if (this.zzc) {
            zzlp.zzE(this.zzd, t, t2);
            throw null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.measurement.zzln
    public final void zzh(T t, byte[] bArr, int i, int i2, zzik zzikVar) {
        zzjx zzjxVar = (zzjx) t;
        if (zzjxVar.zzc == zzmc.zza) {
            zzjxVar.zzc = zzmc.zze();
        }
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzln
    public final boolean zzi(T t, T t2) {
        if (!this.zzb.zzc(t).equals(this.zzb.zzc(t2))) {
            return false;
        }
        if (!this.zzc) {
            return true;
        }
        this.zzd.zza(t);
        this.zzd.zza(t2);
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzln
    public final boolean zzj(T t) {
        this.zzd.zza(t);
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzln
    public final void zzm(T t, zzjf zzjfVar) {
        this.zzd.zza(t);
        throw null;
    }
}
