package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zzmd extends zzmb<zzmc, zzmc> {
    @Override // com.google.android.gms.internal.measurement.zzmb
    public final /* synthetic */ int zza(zzmc zzmcVar) {
        return zzmcVar.zza();
    }

    @Override // com.google.android.gms.internal.measurement.zzmb
    public final /* synthetic */ int zzb(zzmc zzmcVar) {
        return zzmcVar.zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzmb
    public final /* synthetic */ zzmc zzc(Object obj) {
        return ((zzjx) obj).zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zzmb
    public final zzmc zzd(zzmc zzmcVar, zzmc zzmcVar2) {
        zzmc zzmcVar3 = zzmcVar2;
        return zzmcVar3.equals(zzmc.zza) ? zzmcVar : zzmc.zzd(zzmcVar, zzmcVar3);
    }

    @Override // com.google.android.gms.internal.measurement.zzmb
    public final /* synthetic */ zzmc zze() {
        return zzmc.zze();
    }

    @Override // com.google.android.gms.internal.measurement.zzmb
    public final /* bridge */ /* synthetic */ void zzf(zzmc zzmcVar, int i, long j) {
        zzmcVar.zzh(i << 3, Long.valueOf(j));
    }

    @Override // com.google.android.gms.internal.measurement.zzmb
    public final void zzg(Object obj) {
        ((zzjx) obj).zzc.zzf();
    }

    @Override // com.google.android.gms.internal.measurement.zzmb
    public final /* synthetic */ void zzh(Object obj, zzmc zzmcVar) {
        ((zzjx) obj).zzc = zzmcVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzmb
    public final /* synthetic */ void zzi(zzmc zzmcVar, zzjf zzjfVar) {
        zzmcVar.zzi(zzjfVar);
    }
}
