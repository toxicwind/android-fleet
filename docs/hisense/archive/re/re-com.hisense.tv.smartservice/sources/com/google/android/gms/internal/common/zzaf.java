package com.google.android.gms.internal.common;

import com.google.firebase.analytics.FirebaseAnalytics;

/* JADX INFO: loaded from: classes.dex */
public final class zzaf extends zzag {
    public final transient int zza;
    public final transient int zzb;
    public final /* synthetic */ zzag zzc;

    public zzaf(zzag zzagVar, int i, int i2) {
        this.zzc = zzagVar;
        this.zza = i;
        this.zzb = i2;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzs.zza(i, this.zzb, FirebaseAnalytics.Param.INDEX);
        return this.zzc.get(i + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.common.zzac
    public final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    @Override // com.google.android.gms.internal.common.zzac
    public final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    @Override // com.google.android.gms.internal.common.zzac
    public final boolean zzf() {
        return true;
    }

    @Override // com.google.android.gms.internal.common.zzac
    public final Object[] zzg() {
        return this.zzc.zzg();
    }

    @Override // com.google.android.gms.internal.common.zzag, java.util.List
    /* JADX INFO: renamed from: zzh, reason: merged with bridge method [inline-methods] */
    public final zzag subList(int i, int i2) {
        zzs.zzc(i, i2, this.zzb);
        zzag zzagVar = this.zzc;
        int i3 = this.zza;
        return zzagVar.subList(i + i3, i2 + i3);
    }
}
