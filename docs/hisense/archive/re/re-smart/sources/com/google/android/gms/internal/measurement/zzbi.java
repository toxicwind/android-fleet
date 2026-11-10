package com.google.android.gms.internal.measurement;

import b.a.a.a.a;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzbi extends zzaw {
    public zzbi() {
        this.zza.add(zzbl.ADD);
        this.zza.add(zzbl.DIVIDE);
        this.zza.add(zzbl.MODULUS);
        this.zza.add(zzbl.MULTIPLY);
        this.zza.add(zzbl.NEGATE);
        this.zza.add(zzbl.POST_DECREMENT);
        this.zza.add(zzbl.POST_INCREMENT);
        this.zza.add(zzbl.PRE_DECREMENT);
        this.zza.add(zzbl.PRE_INCREMENT);
        this.zza.add(zzbl.SUBTRACT);
    }

    @Override // com.google.android.gms.internal.measurement.zzaw
    public final zzap zza(String str, zzg zzgVar, List<zzap> list) {
        zzbl zzblVar = zzbl.ADD;
        int iOrdinal = zzh.zze(str).ordinal();
        if (iOrdinal == 0) {
            zzap zzapVarZzb = zzgVar.zzb((zzap) a.a(zzbl.ADD, 2, list, 0));
            zzap zzapVarZzb2 = zzgVar.zzb(list.get(1));
            if (!(zzapVarZzb instanceof zzal) && !(zzapVarZzb instanceof zzat) && !(zzapVarZzb2 instanceof zzal) && !(zzapVarZzb2 instanceof zzat)) {
                return new zzah(Double.valueOf(zzapVarZzb2.zzh().doubleValue() + zzapVarZzb.zzh().doubleValue()));
            }
            String strValueOf = String.valueOf(zzapVarZzb.zzi());
            String strValueOf2 = String.valueOf(zzapVarZzb2.zzi());
            return new zzat(strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf));
        }
        if (iOrdinal == 21) {
            return new zzah(Double.valueOf(zzgVar.zzb((zzap) a.a(zzbl.DIVIDE, 2, list, 0)).zzh().doubleValue() / zzgVar.zzb(list.get(1)).zzh().doubleValue()));
        }
        if (iOrdinal == 59) {
            zzap zzapVarZzb3 = zzgVar.zzb((zzap) a.a(zzbl.SUBTRACT, 2, list, 0));
            return new zzah(Double.valueOf(new zzah(Double.valueOf(-zzgVar.zzb(list.get(1)).zzh().doubleValue())).zzh().doubleValue() + zzapVarZzb3.zzh().doubleValue()));
        }
        if (iOrdinal == 52 || iOrdinal == 53) {
            zzh.zzh(str, 2, list);
            zzap zzapVarZzb4 = zzgVar.zzb(list.get(0));
            zzgVar.zzb(list.get(1));
            return zzapVarZzb4;
        }
        if (iOrdinal == 55 || iOrdinal == 56) {
            zzh.zzh(str, 1, list);
            return zzgVar.zzb(list.get(0));
        }
        switch (iOrdinal) {
            case 44:
                return new zzah(Double.valueOf(zzgVar.zzb((zzap) a.a(zzbl.MODULUS, 2, list, 0)).zzh().doubleValue() % zzgVar.zzb(list.get(1)).zzh().doubleValue()));
            case 45:
                return new zzah(Double.valueOf(zzgVar.zzb((zzap) a.a(zzbl.MULTIPLY, 2, list, 0)).zzh().doubleValue() * zzgVar.zzb(list.get(1)).zzh().doubleValue()));
            case 46:
                return new zzah(Double.valueOf(-zzgVar.zzb((zzap) a.a(zzbl.NEGATE, 1, list, 0)).zzh().doubleValue()));
            default:
                super.zzb(str);
                throw null;
        }
    }
}
