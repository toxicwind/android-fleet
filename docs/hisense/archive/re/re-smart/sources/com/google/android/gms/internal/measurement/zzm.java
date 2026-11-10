package com.google.android.gms.internal.measurement;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzm extends zzai {
    public final /* synthetic */ zzo zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzm(zzn zznVar, String str, zzo zzoVar) {
        super("getValue");
        this.zza = zzoVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzai
    public final zzap zza(zzg zzgVar, List<zzap> list) {
        zzh.zzh("getValue", 2, list);
        zzap zzapVarZzb = zzgVar.zzb(list.get(0));
        zzap zzapVarZzb2 = zzgVar.zzb(list.get(1));
        String strZza = this.zza.zza(zzapVarZzb.zzi());
        return strZza != null ? new zzat(strZza) : zzapVarZzb2;
    }
}
