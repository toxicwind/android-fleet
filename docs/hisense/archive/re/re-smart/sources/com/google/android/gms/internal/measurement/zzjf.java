package com.google.android.gms.internal.measurement;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzjf {
    public final zzje zza;

    public zzjf(zzje zzjeVar) {
        zzkf.zzf(zzjeVar, "output");
        this.zza = zzjeVar;
        this.zza.zza = this;
    }

    public static zzjf zza(zzje zzjeVar) {
        zzjf zzjfVar = zzjeVar.zza;
        return zzjfVar != null ? zzjfVar : new zzjf(zzjeVar);
    }

    public final void zzA(int i, int i2) {
        this.zza.zzp(i, (i2 >> 31) ^ (i2 + i2));
    }

    public final void zzB(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                zzje zzjeVar = this.zza;
                int iIntValue = list.get(i2).intValue();
                zzjeVar.zzp(i, (iIntValue >> 31) ^ (iIntValue + iIntValue));
                i2++;
            }
            return;
        }
        this.zza.zzo(i, 2);
        int iZzA = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            int iIntValue2 = list.get(i3).intValue();
            iZzA += zzje.zzA((iIntValue2 >> 31) ^ (iIntValue2 + iIntValue2));
        }
        this.zza.zzq(iZzA);
        while (i2 < list.size()) {
            zzje zzjeVar2 = this.zza;
            int iIntValue3 = list.get(i2).intValue();
            zzjeVar2.zzq((iIntValue3 >> 31) ^ (iIntValue3 + iIntValue3));
            i2++;
        }
    }

    public final void zzC(int i, long j) {
        this.zza.zzr(i, (j >> 63) ^ (j + j));
    }

    public final void zzD(int i, List<Long> list, boolean z) {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                zzje zzjeVar = this.zza;
                long jLongValue = list.get(i2).longValue();
                zzjeVar.zzr(i, (jLongValue >> 63) ^ (jLongValue + jLongValue));
                i2++;
            }
            return;
        }
        this.zza.zzo(i, 2);
        int iZzB = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            long jLongValue2 = list.get(i3).longValue();
            iZzB += zzje.zzB((jLongValue2 >> 63) ^ (jLongValue2 + jLongValue2));
        }
        this.zza.zzq(iZzB);
        while (i2 < list.size()) {
            zzje zzjeVar2 = this.zza;
            long jLongValue3 = list.get(i2).longValue();
            zzjeVar2.zzs((jLongValue3 >> 63) ^ (jLongValue3 + jLongValue3));
            i2++;
        }
    }

    @Deprecated
    public final void zzE(int i) {
        this.zza.zzo(i, 3);
    }

    public final void zzF(int i, String str) {
        this.zza.zzm(i, str);
    }

    public final void zzG(int i, List<String> list) {
        int i2 = 0;
        if (!(list instanceof zzkm)) {
            while (i2 < list.size()) {
                this.zza.zzm(i, list.get(i2));
                i2++;
            }
            return;
        }
        zzkm zzkmVar = (zzkm) list;
        while (i2 < list.size()) {
            Object objZzf = zzkmVar.zzf(i2);
            if (objZzf instanceof String) {
                this.zza.zzm(i, (String) objZzf);
            } else {
                this.zza.zze(i, (zzix) objZzf);
            }
            i2++;
        }
    }

    public final void zzH(int i, int i2) {
        this.zza.zzp(i, i2);
    }

    public final void zzI(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzp(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.zza.zzo(i, 2);
        int iZzA = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iZzA += zzje.zzA(list.get(i3).intValue());
        }
        this.zza.zzq(iZzA);
        while (i2 < list.size()) {
            this.zza.zzq(list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzJ(int i, long j) {
        this.zza.zzr(i, j);
    }

    public final void zzK(int i, List<Long> list, boolean z) {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzr(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        this.zza.zzo(i, 2);
        int iZzB = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iZzB += zzje.zzB(list.get(i3).longValue());
        }
        this.zza.zzq(iZzB);
        while (i2 < list.size()) {
            this.zza.zzs(list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzb(int i, boolean z) {
        this.zza.zzd(i, z);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void zzc(int i, List<Boolean> list, boolean z) {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzd(i, list.get(i2).booleanValue());
                i2++;
            }
            return;
        }
        this.zza.zzo(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            list.get(i4).booleanValue();
            i3++;
        }
        this.zza.zzq(i3);
        while (i2 < list.size()) {
            this.zza.zzb(list.get(i2).booleanValue() ? (byte) 1 : (byte) 0);
            i2++;
        }
    }

    public final void zzd(int i, zzix zzixVar) {
        this.zza.zze(i, zzixVar);
    }

    public final void zze(int i, List<zzix> list) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zza.zze(i, list.get(i2));
        }
    }

    public final void zzf(int i, double d2) {
        this.zza.zzh(i, Double.doubleToRawLongBits(d2));
    }

    public final void zzg(int i, List<Double> list, boolean z) {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzh(i, Double.doubleToRawLongBits(list.get(i2).doubleValue()));
                i2++;
            }
            return;
        }
        this.zza.zzo(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            list.get(i4).doubleValue();
            i3 += 8;
        }
        this.zza.zzq(i3);
        while (i2 < list.size()) {
            this.zza.zzi(Double.doubleToRawLongBits(list.get(i2).doubleValue()));
            i2++;
        }
    }

    @Deprecated
    public final void zzh(int i) {
        this.zza.zzo(i, 4);
    }

    public final void zzi(int i, int i2) {
        this.zza.zzj(i, i2);
    }

    public final void zzj(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzj(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.zza.zzo(i, 2);
        int iZzv = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iZzv += zzje.zzv(list.get(i3).intValue());
        }
        this.zza.zzq(iZzv);
        while (i2 < list.size()) {
            this.zza.zzk(list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzk(int i, int i2) {
        this.zza.zzf(i, i2);
    }

    public final void zzl(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzf(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.zza.zzo(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            list.get(i4).intValue();
            i3 += 4;
        }
        this.zza.zzq(i3);
        while (i2 < list.size()) {
            this.zza.zzg(list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzm(int i, long j) {
        this.zza.zzh(i, j);
    }

    public final void zzn(int i, List<Long> list, boolean z) {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzh(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        this.zza.zzo(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            list.get(i4).longValue();
            i3 += 8;
        }
        this.zza.zzq(i3);
        while (i2 < list.size()) {
            this.zza.zzi(list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzo(int i, float f2) {
        this.zza.zzf(i, Float.floatToRawIntBits(f2));
    }

    public final void zzp(int i, List<Float> list, boolean z) {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzf(i, Float.floatToRawIntBits(list.get(i2).floatValue()));
                i2++;
            }
            return;
        }
        this.zza.zzo(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            list.get(i4).floatValue();
            i3 += 4;
        }
        this.zza.zzq(i3);
        while (i2 < list.size()) {
            this.zza.zzg(Float.floatToRawIntBits(list.get(i2).floatValue()));
            i2++;
        }
    }

    public final void zzq(int i, Object obj, zzln zzlnVar) {
        zzje zzjeVar = this.zza;
        zzjeVar.zzo(i, 3);
        zzlnVar.zzm((zzlc) obj, zzjeVar.zza);
        zzjeVar.zzo(i, 4);
    }

    public final void zzr(int i, int i2) {
        this.zza.zzj(i, i2);
    }

    public final void zzs(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzj(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.zza.zzo(i, 2);
        int iZzv = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iZzv += zzje.zzv(list.get(i3).intValue());
        }
        this.zza.zzq(iZzv);
        while (i2 < list.size()) {
            this.zza.zzk(list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzt(int i, long j) {
        this.zza.zzr(i, j);
    }

    public final void zzu(int i, List<Long> list, boolean z) {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzr(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        this.zza.zzo(i, 2);
        int iZzB = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iZzB += zzje.zzB(list.get(i3).longValue());
        }
        this.zza.zzq(iZzB);
        while (i2 < list.size()) {
            this.zza.zzs(list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzv(int i, Object obj, zzln zzlnVar) {
        zzlc zzlcVar = (zzlc) obj;
        zzjc zzjcVar = (zzjc) this.zza;
        zzjcVar.zzq((i << 3) | 2);
        zzih zzihVar = (zzih) zzlcVar;
        int iZzbo = zzihVar.zzbo();
        if (iZzbo == -1) {
            iZzbo = zzlnVar.zza(zzihVar);
            zzihVar.zzbr(iZzbo);
        }
        zzjcVar.zzq(iZzbo);
        zzlnVar.zzm(zzlcVar, zzjcVar.zza);
    }

    public final void zzw(int i, int i2) {
        this.zza.zzf(i, i2);
    }

    public final void zzx(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzf(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.zza.zzo(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            list.get(i4).intValue();
            i3 += 4;
        }
        this.zza.zzq(i3);
        while (i2 < list.size()) {
            this.zza.zzg(list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzy(int i, long j) {
        this.zza.zzh(i, j);
    }

    public final void zzz(int i, List<Long> list, boolean z) {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzh(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        this.zza.zzo(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            list.get(i4).longValue();
            i3 += 8;
        }
        this.zza.zzq(i3);
        while (i2 < list.size()) {
            this.zza.zzi(list.get(i2).longValue());
            i2++;
        }
    }
}
