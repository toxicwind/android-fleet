package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzgt implements zzgr {
    private static final zzgr zza = new zzgr() { // from class: com.google.android.gms.internal.cast.zzgs
        @Override // com.google.android.gms.internal.cast.zzgr
        public final Object zza() {
            throw new IllegalStateException();
        }
    };
    private final zzgv zzb = new zzgv();
    private volatile zzgr zzc;
    private Object zzd;

    zzgt(zzgr zzgrVar) {
        this.zzc = zzgrVar;
    }

    public final String toString() {
        Object obj = this.zzc;
        if (obj == zza) {
            obj = "<supplier that returned " + String.valueOf(this.zzd) + ">";
        }
        return "Suppliers.memoize(" + String.valueOf(obj) + ")";
    }

    @Override // com.google.android.gms.internal.cast.zzgr
    public final Object zza() {
        zzgr zzgrVar = this.zzc;
        zzgr zzgrVar2 = zza;
        if (zzgrVar != zzgrVar2) {
            synchronized (this.zzb) {
                if (this.zzc != zzgrVar2) {
                    Object objZza = this.zzc.zza();
                    this.zzd = objZza;
                    this.zzc = zzgrVar2;
                    return objZza;
                }
            }
        }
        return this.zzd;
    }
}
