package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public class zzxw {
    protected volatile zzyl zza;
    private volatile zzwn zzb;
    private volatile boolean zzc;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzxw)) {
            return false;
        }
        zzxw zzxwVar = (zzxw) obj;
        zzyl zzylVar = this.zza;
        zzyl zzylVar2 = zzxwVar.zza;
        if (zzylVar == null && zzylVar2 == null) {
            return zzb().equals(zzxwVar.zzb());
        }
        if (zzylVar != null && zzylVar2 != null) {
            return zzylVar.equals(zzylVar2);
        }
        if (zzylVar != null) {
            zzxwVar.zzd(zzylVar.zzu());
            return zzylVar.equals(zzxwVar.zza);
        }
        zzd(zzylVar2.zzu());
        return this.zza.equals(zzylVar2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzb != null) {
            return ((zzwl) this.zzb).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzw();
        }
        return 0;
    }

    public final zzwn zzb() {
        if (this.zzb != null) {
            return this.zzb;
        }
        synchronized (this) {
            if (this.zzb != null) {
                return this.zzb;
            }
            if (this.zza == null) {
                this.zzb = zzwn.zzb;
            } else {
                this.zzb = this.zza.zzs();
            }
            return this.zzb;
        }
    }

    public final zzyl zzc(zzyl zzylVar) {
        zzyl zzylVar2 = this.zza;
        this.zzb = null;
        this.zza = zzylVar;
        return zzylVar2;
    }

    protected final void zzd(zzyl zzylVar) {
        if (this.zza != null) {
            return;
        }
        synchronized (this) {
            if (this.zza != null) {
                return;
            }
            try {
                this.zza = zzylVar;
                this.zzb = zzwn.zzb;
            } catch (zzxq unused) {
                this.zzc = true;
                this.zza = zzylVar;
                this.zzb = zzwn.zzb;
            }
        }
    }
}
