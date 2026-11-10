package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: loaded from: classes.dex */
public final class zzdg extends zzdt {
    public final /* synthetic */ String zza;
    public final /* synthetic */ Object zzb;
    public final /* synthetic */ zzee zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdg(zzee zzeeVar, boolean z, int i, String str, Object obj, Object obj2, Object obj3) {
        super(zzeeVar, false);
        this.zzc = zzeeVar;
        this.zza = str;
        this.zzb = obj;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    public final void zza() {
        zzcc zzccVar = this.zzc.zzj;
        Preconditions.checkNotNull(zzccVar);
        zzccVar.logHealthData(5, this.zza, new ObjectWrapper(this.zzb), new ObjectWrapper(null), new ObjectWrapper(null));
    }
}
