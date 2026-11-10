package com.google.android.gms.internal.measurement;

import b.a.a.a.a;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzbc extends zzaw {
    public zzbc() {
        this.zza.add(zzbl.AND);
        this.zza.add(zzbl.NOT);
        this.zza.add(zzbl.OR);
    }

    @Override // com.google.android.gms.internal.measurement.zzaw
    public final zzap zza(String str, zzg zzgVar, List<zzap> list) {
        zzbl zzblVar = zzbl.ADD;
        int iOrdinal = zzh.zze(str).ordinal();
        if (iOrdinal == 1) {
            zzap zzapVarZzb = zzgVar.zzb((zzap) a.a(zzbl.AND, 2, list, 0));
            return !zzapVarZzb.zzg().booleanValue() ? zzapVarZzb : zzgVar.zzb(list.get(1));
        }
        if (iOrdinal == 47) {
            return new zzaf(Boolean.valueOf(!zzgVar.zzb((zzap) a.a(zzbl.NOT, 1, list, 0)).zzg().booleanValue()));
        }
        if (iOrdinal == 50) {
            zzap zzapVarZzb2 = zzgVar.zzb((zzap) a.a(zzbl.OR, 2, list, 0));
            return zzapVarZzb2.zzg().booleanValue() ? zzapVarZzb2 : zzgVar.zzb(list.get(1));
        }
        super.zzb(str);
        throw null;
    }
}
