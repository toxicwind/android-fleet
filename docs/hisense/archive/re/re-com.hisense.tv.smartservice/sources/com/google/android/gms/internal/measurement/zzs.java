package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzs extends zzai {
    public final boolean zza;
    public final boolean zzb;
    public final /* synthetic */ zzt zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzs(zzt zztVar, boolean z, boolean z2) {
        super("log");
        this.zzc = zztVar;
        this.zza = z;
        this.zzb = z2;
    }

    @Override // com.google.android.gms.internal.measurement.zzai
    public final zzap zza(zzg zzgVar, List<zzap> list) {
        int i;
        zzh.zzi("log", 1, list);
        if (list.size() == 1) {
            this.zzc.zza.zza(3, zzgVar.zzb(list.get(0)).zzi(), Collections.emptyList(), this.zza, this.zzb);
            return zzap.zzf;
        }
        int iZzb = zzh.zzb(zzgVar.zzb(list.get(0)).zzh().doubleValue());
        if (iZzb == 2) {
            i = 4;
        } else if (iZzb == 3) {
            i = 1;
        } else if (iZzb != 5) {
            i = iZzb != 6 ? 3 : 2;
        } else {
            i = 5;
        }
        String strZzi = zzgVar.zzb(list.get(1)).zzi();
        if (list.size() == 2) {
            this.zzc.zza.zza(i, strZzi, Collections.emptyList(), this.zza, this.zzb);
            return zzap.zzf;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 2; i2 < Math.min(list.size(), 5); i2++) {
            arrayList.add(zzgVar.zzb(list.get(i2)).zzi());
        }
        this.zzc.zza.zza(i, strZzi, arrayList, this.zza, this.zzb);
        return zzap.zzf;
    }
}
