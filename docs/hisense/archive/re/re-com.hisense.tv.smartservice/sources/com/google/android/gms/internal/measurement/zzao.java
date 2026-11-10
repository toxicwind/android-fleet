package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzao extends zzai implements zzal {
    public final List<String> zza;
    public final List<zzap> zzb;
    public zzg zzc;

    public zzao(zzao zzaoVar) {
        super(zzaoVar.zzd);
        this.zza = new ArrayList(zzaoVar.zza.size());
        this.zza.addAll(zzaoVar.zza);
        this.zzb = new ArrayList(zzaoVar.zzb.size());
        this.zzb.addAll(zzaoVar.zzb);
        this.zzc = zzaoVar.zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zzai
    public final zzap zza(zzg zzgVar, List<zzap> list) {
        zzg zzgVarZza = this.zzc.zza();
        for (int i = 0; i < this.zza.size(); i++) {
            if (i < list.size()) {
                zzgVarZza.zze(this.zza.get(i), zzgVar.zzb(list.get(i)));
            } else {
                zzgVarZza.zze(this.zza.get(i), zzap.zzf);
            }
        }
        for (zzap zzapVar : this.zzb) {
            zzap zzapVarZzb = zzgVarZza.zzb(zzapVar);
            if (zzapVarZzb instanceof zzaq) {
                zzapVarZzb = zzgVarZza.zzb(zzapVar);
            }
            if (zzapVarZzb instanceof zzag) {
                return ((zzag) zzapVarZzb).zzb();
            }
        }
        return zzap.zzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzai, com.google.android.gms.internal.measurement.zzap
    public final zzap zzd() {
        return new zzao(this);
    }

    public zzao(String str, List<zzap> list, List<zzap> list2, zzg zzgVar) {
        super(str);
        this.zza = new ArrayList();
        this.zzc = zzgVar;
        if (!list.isEmpty()) {
            Iterator<zzap> it = list.iterator();
            while (it.hasNext()) {
                this.zza.add(it.next().zzi());
            }
        }
        this.zzb = new ArrayList(list2);
    }
}
