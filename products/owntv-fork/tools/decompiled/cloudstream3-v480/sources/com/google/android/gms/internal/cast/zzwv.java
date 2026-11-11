package com.google.android.gms.internal.cast;

import java.io.IOException;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzwv implements zzzt {
    private final zzwu zza;

    private zzwv(zzwu zzwuVar) {
        byte[] bArr = zzxo.zzb;
        this.zza = zzwuVar;
        zzwuVar.zza = this;
    }

    public static zzwv zza(zzwu zzwuVar) {
        zzwv zzwvVar = zzwuVar.zza;
        return zzwvVar != null ? zzwvVar : new zzwv(zzwuVar);
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzA(int i, int i2) throws IOException {
        zzwu zzwuVar = this.zza;
        zzwuVar.zzs(i, (i2 >> 31) ^ (i2 + i2));
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzC(int i, long j) throws IOException {
        zzwu zzwuVar = this.zza;
        zzwuVar.zzu(i, (j >> 63) ^ (j + j));
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzE(int i, String str) throws IOException {
        this.zza.zzp(i, str);
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzG(int i, int i2) throws IOException {
        this.zza.zzs(i, i2);
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzI(int i, long j) throws IOException {
        this.zza.zzu(i, j);
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzb(int i, boolean z) throws IOException {
        this.zza.zzd(i, z);
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzd(int i, zzwn zzwnVar) throws IOException {
        this.zza.zze(i, zzwnVar);
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zze(int i, List list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zza.zze(i, (zzwn) list.get(i2));
        }
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzf(int i, double d) throws IOException {
        this.zza.zzh(i, Double.doubleToRawLongBits(d));
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzh(int i, int i2) throws IOException {
        this.zza.zzj(i, i2);
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzj(int i, int i2) throws IOException {
        this.zza.zzf(i, i2);
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzl(int i, long j) throws IOException {
        this.zza.zzh(i, j);
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzn(int i, float f) throws IOException {
        this.zza.zzf(i, Float.floatToRawIntBits(f));
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzp(int i, Object obj, zzyv zzyvVar) throws IOException {
        zzwu zzwuVar = this.zza;
        zzwuVar.zzr(i, 3);
        zzyvVar.zzf((zzyl) obj, zzwuVar.zza);
        zzwuVar.zzr(i, 4);
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzq(int i, int i2) throws IOException {
        this.zza.zzj(i, i2);
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzs(int i, long j) throws IOException {
        this.zza.zzu(i, j);
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzu(int i, Object obj, zzyv zzyvVar) throws IOException {
        this.zza.zzm(i, (zzyl) obj, zzyvVar);
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzv(int i, Object obj) throws IOException {
        boolean z = obj instanceof zzwn;
        zzwu zzwuVar = this.zza;
        if (z) {
            zzwuVar.zzo(i, (zzwn) obj);
        } else {
            zzwuVar.zzn(i, (zzyl) obj);
        }
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzw(int i, int i2) throws IOException {
        this.zza.zzf(i, i2);
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzy(int i, long j) throws IOException {
        this.zza.zzh(i, j);
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzF(int i, List list) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzxx)) {
            while (i2 < list.size()) {
                this.zza.zzp(i, (String) list.get(i2));
                i2++;
            }
            return;
        }
        zzxx zzxxVar = (zzxx) list;
        while (i2 < list.size()) {
            Object objZza = zzxxVar.zza();
            boolean z = objZza instanceof String;
            zzwu zzwuVar = this.zza;
            if (z) {
                zzwuVar.zzp(i, (String) objZza);
            } else {
                zzwuVar.zze(i, (zzwn) objZza);
            }
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzH(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzxj)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzs(i, ((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            zzwu zzwuVar = this.zza;
            zzwuVar.zzr(i, 2);
            int iZzz = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzz += zzwu.zzz(((Integer) list.get(i3)).intValue());
            }
            zzwuVar.zzt(iZzz);
            while (i2 < list.size()) {
                zzwuVar.zzt(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        zzxj zzxjVar = (zzxj) list;
        if (!z) {
            while (i2 < zzxjVar.size()) {
                this.zza.zzs(i, zzxjVar.zze(i2));
                i2++;
            }
            return;
        }
        zzwu zzwuVar2 = this.zza;
        zzwuVar2.zzr(i, 2);
        int iZzz2 = 0;
        for (int i4 = 0; i4 < zzxjVar.size(); i4++) {
            iZzz2 += zzwu.zzz(zzxjVar.zze(i4));
        }
        zzwuVar2.zzt(iZzz2);
        while (i2 < zzxjVar.size()) {
            zzwuVar2.zzt(zzxjVar.zze(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzJ(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzya)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzu(i, ((Long) list.get(i2)).longValue());
                    i2++;
                }
                return;
            }
            zzwu zzwuVar = this.zza;
            zzwuVar.zzr(i, 2);
            int iZzA = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzA += zzwu.zzA(((Long) list.get(i3)).longValue());
            }
            zzwuVar.zzt(iZzA);
            while (i2 < list.size()) {
                zzwuVar.zzv(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        zzya zzyaVar = (zzya) list;
        if (!z) {
            while (i2 < zzyaVar.size()) {
                this.zza.zzu(i, zzyaVar.zze(i2));
                i2++;
            }
            return;
        }
        zzwu zzwuVar2 = this.zza;
        zzwuVar2.zzr(i, 2);
        int iZzA2 = 0;
        for (int i4 = 0; i4 < zzyaVar.size(); i4++) {
            iZzA2 += zzwu.zzA(zzyaVar.zze(i4));
        }
        zzwuVar2.zzt(iZzA2);
        while (i2 < zzyaVar.size()) {
            zzwuVar2.zzv(zzyaVar.zze(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzk(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzxj)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzf(i, ((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            zzwu zzwuVar = this.zza;
            zzwuVar.zzr(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Integer) list.get(i4)).intValue();
                i3 += 4;
            }
            zzwuVar.zzt(i3);
            while (i2 < list.size()) {
                zzwuVar.zzg(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        zzxj zzxjVar = (zzxj) list;
        if (!z) {
            while (i2 < zzxjVar.size()) {
                this.zza.zzf(i, zzxjVar.zze(i2));
                i2++;
            }
            return;
        }
        zzwu zzwuVar2 = this.zza;
        zzwuVar2.zzr(i, 2);
        int i5 = 0;
        for (int i6 = 0; i6 < zzxjVar.size(); i6++) {
            zzxjVar.zze(i6);
            i5 += 4;
        }
        zzwuVar2.zzt(i5);
        while (i2 < zzxjVar.size()) {
            zzwuVar2.zzg(zzxjVar.zze(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzm(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzya)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzh(i, ((Long) list.get(i2)).longValue());
                    i2++;
                }
                return;
            }
            zzwu zzwuVar = this.zza;
            zzwuVar.zzr(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Long) list.get(i4)).longValue();
                i3 += 8;
            }
            zzwuVar.zzt(i3);
            while (i2 < list.size()) {
                zzwuVar.zzi(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        zzya zzyaVar = (zzya) list;
        if (!z) {
            while (i2 < zzyaVar.size()) {
                this.zza.zzh(i, zzyaVar.zze(i2));
                i2++;
            }
            return;
        }
        zzwu zzwuVar2 = this.zza;
        zzwuVar2.zzr(i, 2);
        int i5 = 0;
        for (int i6 = 0; i6 < zzyaVar.size(); i6++) {
            zzyaVar.zze(i6);
            i5 += 8;
        }
        zzwuVar2.zzt(i5);
        while (i2 < zzyaVar.size()) {
            zzwuVar2.zzi(zzyaVar.zze(i2));
            i2++;
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzc(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzwe)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzd(i, ((Boolean) list.get(i2)).booleanValue());
                    i2++;
                }
                return;
            }
            zzwu zzwuVar = this.zza;
            zzwuVar.zzr(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Boolean) list.get(i4)).booleanValue();
                i3++;
            }
            zzwuVar.zzt(i3);
            while (i2 < list.size()) {
                zzwuVar.zzb(((Boolean) list.get(i2)).booleanValue() ? (byte) 1 : (byte) 0);
                i2++;
            }
            return;
        }
        zzwe zzweVar = (zzwe) list;
        if (!z) {
            while (i2 < zzweVar.size()) {
                this.zza.zzd(i, zzweVar.zze(i2));
                i2++;
            }
            return;
        }
        zzwu zzwuVar2 = this.zza;
        zzwuVar2.zzr(i, 2);
        int i5 = 0;
        for (int i6 = 0; i6 < zzweVar.size(); i6++) {
            zzweVar.zze(i6);
            i5++;
        }
        zzwuVar2.zzt(i5);
        while (i2 < zzweVar.size()) {
            zzwuVar2.zzb(zzweVar.zze(i2) ? (byte) 1 : (byte) 0);
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzr(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzxj)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzj(i, ((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            zzwu zzwuVar = this.zza;
            zzwuVar.zzr(i, 2);
            int iZzA = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzA += zzwu.zzA(((Integer) list.get(i3)).intValue());
            }
            zzwuVar.zzt(iZzA);
            while (i2 < list.size()) {
                zzwuVar.zzk(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        zzxj zzxjVar = (zzxj) list;
        if (!z) {
            while (i2 < zzxjVar.size()) {
                this.zza.zzj(i, zzxjVar.zze(i2));
                i2++;
            }
            return;
        }
        zzwu zzwuVar2 = this.zza;
        zzwuVar2.zzr(i, 2);
        int iZzA2 = 0;
        for (int i4 = 0; i4 < zzxjVar.size(); i4++) {
            iZzA2 += zzwu.zzA(zzxjVar.zze(i4));
        }
        zzwuVar2.zzt(iZzA2);
        while (i2 < zzxjVar.size()) {
            zzwuVar2.zzk(zzxjVar.zze(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzg(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzww)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzh(i, Double.doubleToRawLongBits(((Double) list.get(i2)).doubleValue()));
                    i2++;
                }
                return;
            }
            zzwu zzwuVar = this.zza;
            zzwuVar.zzr(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Double) list.get(i4)).doubleValue();
                i3 += 8;
            }
            zzwuVar.zzt(i3);
            while (i2 < list.size()) {
                zzwuVar.zzi(Double.doubleToRawLongBits(((Double) list.get(i2)).doubleValue()));
                i2++;
            }
            return;
        }
        zzww zzwwVar = (zzww) list;
        if (!z) {
            while (i2 < zzwwVar.size()) {
                this.zza.zzh(i, Double.doubleToRawLongBits(zzwwVar.zze(i2)));
                i2++;
            }
            return;
        }
        zzwu zzwuVar2 = this.zza;
        zzwuVar2.zzr(i, 2);
        int i5 = 0;
        for (int i6 = 0; i6 < zzwwVar.size(); i6++) {
            zzwwVar.zze(i6);
            i5 += 8;
        }
        zzwuVar2.zzt(i5);
        while (i2 < zzwwVar.size()) {
            zzwuVar2.zzi(Double.doubleToRawLongBits(zzwwVar.zze(i2)));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzo(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzxd)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzf(i, Float.floatToRawIntBits(((Float) list.get(i2)).floatValue()));
                    i2++;
                }
                return;
            }
            zzwu zzwuVar = this.zza;
            zzwuVar.zzr(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Float) list.get(i4)).floatValue();
                i3 += 4;
            }
            zzwuVar.zzt(i3);
            while (i2 < list.size()) {
                zzwuVar.zzg(Float.floatToRawIntBits(((Float) list.get(i2)).floatValue()));
                i2++;
            }
            return;
        }
        zzxd zzxdVar = (zzxd) list;
        if (!z) {
            while (i2 < zzxdVar.size()) {
                this.zza.zzf(i, Float.floatToRawIntBits(zzxdVar.zze(i2)));
                i2++;
            }
            return;
        }
        zzwu zzwuVar2 = this.zza;
        zzwuVar2.zzr(i, 2);
        int i5 = 0;
        for (int i6 = 0; i6 < zzxdVar.size(); i6++) {
            zzxdVar.zze(i6);
            i5 += 4;
        }
        zzwuVar2.zzt(i5);
        while (i2 < zzxdVar.size()) {
            zzwuVar2.zzg(Float.floatToRawIntBits(zzxdVar.zze(i2)));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzx(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzxj)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzf(i, ((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            zzwu zzwuVar = this.zza;
            zzwuVar.zzr(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Integer) list.get(i4)).intValue();
                i3 += 4;
            }
            zzwuVar.zzt(i3);
            while (i2 < list.size()) {
                zzwuVar.zzg(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        zzxj zzxjVar = (zzxj) list;
        if (!z) {
            while (i2 < zzxjVar.size()) {
                this.zza.zzf(i, zzxjVar.zze(i2));
                i2++;
            }
            return;
        }
        zzwu zzwuVar2 = this.zza;
        zzwuVar2.zzr(i, 2);
        int i5 = 0;
        for (int i6 = 0; i6 < zzxjVar.size(); i6++) {
            zzxjVar.zze(i6);
            i5 += 4;
        }
        zzwuVar2.zzt(i5);
        while (i2 < zzxjVar.size()) {
            zzwuVar2.zzg(zzxjVar.zze(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzz(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzya)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzh(i, ((Long) list.get(i2)).longValue());
                    i2++;
                }
                return;
            }
            zzwu zzwuVar = this.zza;
            zzwuVar.zzr(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Long) list.get(i4)).longValue();
                i3 += 8;
            }
            zzwuVar.zzt(i3);
            while (i2 < list.size()) {
                zzwuVar.zzi(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        zzya zzyaVar = (zzya) list;
        if (!z) {
            while (i2 < zzyaVar.size()) {
                this.zza.zzh(i, zzyaVar.zze(i2));
                i2++;
            }
            return;
        }
        zzwu zzwuVar2 = this.zza;
        zzwuVar2.zzr(i, 2);
        int i5 = 0;
        for (int i6 = 0; i6 < zzyaVar.size(); i6++) {
            zzyaVar.zze(i6);
            i5 += 8;
        }
        zzwuVar2.zzt(i5);
        while (i2 < zzyaVar.size()) {
            zzwuVar2.zzi(zzyaVar.zze(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzB(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzxj)) {
            if (!z) {
                while (i2 < list.size()) {
                    zzwu zzwuVar = this.zza;
                    int iIntValue = ((Integer) list.get(i2)).intValue();
                    zzwuVar.zzs(i, (iIntValue >> 31) ^ (iIntValue + iIntValue));
                    i2++;
                }
                return;
            }
            zzwu zzwuVar2 = this.zza;
            zzwuVar2.zzr(i, 2);
            int iZzz = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                int iIntValue2 = ((Integer) list.get(i3)).intValue();
                iZzz += zzwu.zzz((iIntValue2 >> 31) ^ (iIntValue2 + iIntValue2));
            }
            zzwuVar2.zzt(iZzz);
            while (i2 < list.size()) {
                int iIntValue3 = ((Integer) list.get(i2)).intValue();
                zzwuVar2.zzt((iIntValue3 >> 31) ^ (iIntValue3 + iIntValue3));
                i2++;
            }
            return;
        }
        zzxj zzxjVar = (zzxj) list;
        if (!z) {
            while (i2 < zzxjVar.size()) {
                zzwu zzwuVar3 = this.zza;
                int iZze = zzxjVar.zze(i2);
                zzwuVar3.zzs(i, (iZze >> 31) ^ (iZze + iZze));
                i2++;
            }
            return;
        }
        zzwu zzwuVar4 = this.zza;
        zzwuVar4.zzr(i, 2);
        int iZzz2 = 0;
        for (int i4 = 0; i4 < zzxjVar.size(); i4++) {
            int iZze2 = zzxjVar.zze(i4);
            iZzz2 += zzwu.zzz((iZze2 >> 31) ^ (iZze2 + iZze2));
        }
        zzwuVar4.zzt(iZzz2);
        while (i2 < zzxjVar.size()) {
            int iZze3 = zzxjVar.zze(i2);
            zzwuVar4.zzt((iZze3 >> 31) ^ (iZze3 + iZze3));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzD(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzya)) {
            if (!z) {
                while (i2 < list.size()) {
                    zzwu zzwuVar = this.zza;
                    long jLongValue = ((Long) list.get(i2)).longValue();
                    zzwuVar.zzu(i, (jLongValue >> 63) ^ (jLongValue + jLongValue));
                    i2++;
                }
                return;
            }
            zzwu zzwuVar2 = this.zza;
            zzwuVar2.zzr(i, 2);
            int iZzA = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                long jLongValue2 = ((Long) list.get(i3)).longValue();
                iZzA += zzwu.zzA((jLongValue2 >> 63) ^ (jLongValue2 + jLongValue2));
            }
            zzwuVar2.zzt(iZzA);
            while (i2 < list.size()) {
                long jLongValue3 = ((Long) list.get(i2)).longValue();
                zzwuVar2.zzv((jLongValue3 >> 63) ^ (jLongValue3 + jLongValue3));
                i2++;
            }
            return;
        }
        zzya zzyaVar = (zzya) list;
        if (!z) {
            while (i2 < zzyaVar.size()) {
                zzwu zzwuVar3 = this.zza;
                long jZze = zzyaVar.zze(i2);
                zzwuVar3.zzu(i, (jZze >> 63) ^ (jZze + jZze));
                i2++;
            }
            return;
        }
        zzwu zzwuVar4 = this.zza;
        zzwuVar4.zzr(i, 2);
        int iZzA2 = 0;
        for (int i4 = 0; i4 < zzyaVar.size(); i4++) {
            long jZze2 = zzyaVar.zze(i4);
            iZzA2 += zzwu.zzA((jZze2 >> 63) ^ (jZze2 + jZze2));
        }
        zzwuVar4.zzt(iZzA2);
        while (i2 < zzyaVar.size()) {
            long jZze3 = zzyaVar.zze(i2);
            zzwuVar4.zzv((jZze3 >> 63) ^ (jZze3 + jZze3));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzi(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzxj)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzj(i, ((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            zzwu zzwuVar = this.zza;
            zzwuVar.zzr(i, 2);
            int iZzA = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzA += zzwu.zzA(((Integer) list.get(i3)).intValue());
            }
            zzwuVar.zzt(iZzA);
            while (i2 < list.size()) {
                zzwuVar.zzk(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        zzxj zzxjVar = (zzxj) list;
        if (!z) {
            while (i2 < zzxjVar.size()) {
                this.zza.zzj(i, zzxjVar.zze(i2));
                i2++;
            }
            return;
        }
        zzwu zzwuVar2 = this.zza;
        zzwuVar2.zzr(i, 2);
        int iZzA2 = 0;
        for (int i4 = 0; i4 < zzxjVar.size(); i4++) {
            iZzA2 += zzwu.zzA(zzxjVar.zze(i4));
        }
        zzwuVar2.zzt(iZzA2);
        while (i2 < zzxjVar.size()) {
            zzwuVar2.zzk(zzxjVar.zze(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.cast.zzzt
    public final void zzt(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzya)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzu(i, ((Long) list.get(i2)).longValue());
                    i2++;
                }
                return;
            }
            zzwu zzwuVar = this.zza;
            zzwuVar.zzr(i, 2);
            int iZzA = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzA += zzwu.zzA(((Long) list.get(i3)).longValue());
            }
            zzwuVar.zzt(iZzA);
            while (i2 < list.size()) {
                zzwuVar.zzv(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        zzya zzyaVar = (zzya) list;
        if (!z) {
            while (i2 < zzyaVar.size()) {
                this.zza.zzu(i, zzyaVar.zze(i2));
                i2++;
            }
            return;
        }
        zzwu zzwuVar2 = this.zza;
        zzwuVar2.zzr(i, 2);
        int iZzA2 = 0;
        for (int i4 = 0; i4 < zzyaVar.size(); i4++) {
            iZzA2 += zzwu.zzA(zzyaVar.zze(i4));
        }
        zzwuVar2.zzt(iZzA2);
        while (i2 < zzyaVar.size()) {
            zzwuVar2.zzv(zzyaVar.zze(i2));
            i2++;
        }
    }
}
