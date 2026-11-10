package com.google.android.gms.internal.measurement;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzgd extends zzjx<zzgd, zzgc> implements zzld {
    public static final zzgd zza;
    public zzkd zze = zzjx.zzby();
    public zzkd zzf = zzjx.zzby();
    public zzke<zzfm> zzg = zzjx.zzbA();
    public zzke<zzgf> zzh = zzjx.zzbA();

    static {
        zzgd zzgdVar = new zzgd();
        zza = zzgdVar;
        zzjx.zza.put(zzgd.class, zzgdVar);
    }

    public static zzgc zzf() {
        return zza.zzbu();
    }

    public static zzgd zzh() {
        return zza;
    }

    public static /* synthetic */ void zzo(zzgd zzgdVar, Iterable iterable) {
        zzkd zzkdVar = zzgdVar.zze;
        if (!zzkdVar.zzc()) {
            zzgdVar.zze = zzjx.zzbz(zzkdVar);
        }
        zzih.zzbq(iterable, zzgdVar.zze);
    }

    public static /* synthetic */ void zzq(zzgd zzgdVar, Iterable iterable) {
        zzkd zzkdVar = zzgdVar.zzf;
        if (!zzkdVar.zzc()) {
            zzgdVar.zzf = zzjx.zzbz(zzkdVar);
        }
        zzih.zzbq(iterable, zzgdVar.zzf);
    }

    public static /* synthetic */ void zzs(zzgd zzgdVar, Iterable iterable) {
        zzgdVar.zzw();
        zzih.zzbq(iterable, zzgdVar.zzg);
    }

    public static /* synthetic */ void zzt(zzgd zzgdVar, int i) {
        zzgdVar.zzw();
        zzgdVar.zzg.remove(i);
    }

    public static /* synthetic */ void zzu(zzgd zzgdVar, Iterable iterable) {
        zzgdVar.zzx();
        zzih.zzbq(iterable, zzgdVar.zzh);
    }

    public static /* synthetic */ void zzv(zzgd zzgdVar, int i) {
        zzgdVar.zzx();
        zzgdVar.zzh.remove(i);
    }

    private final void zzw() {
        zzke<zzfm> zzkeVar = this.zzg;
        if (zzkeVar.zzc()) {
            return;
        }
        this.zzg = zzjx.zzbB(zzkeVar);
    }

    private final void zzx() {
        zzke<zzgf> zzkeVar = this.zzh;
        if (zzkeVar.zzc()) {
            return;
        }
        this.zzh = zzjx.zzbB(zzkeVar);
    }

    public final int zza() {
        return this.zzg.size();
    }

    public final int zzb() {
        return this.zzf.size();
    }

    public final int zzc() {
        return this.zzh.size();
    }

    public final int zzd() {
        return this.zze.size();
    }

    public final zzfm zze(int i) {
        return this.zzg.get(i);
    }

    public final zzgf zzi(int i) {
        return this.zzh.get(i);
    }

    public final List<zzfm> zzj() {
        return this.zzg;
    }

    public final List<Long> zzk() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzjx
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzjx.zzbF(zza, "\u0001\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0004\u0000\u0001\u0015\u0002\u0015\u0003\u001b\u0004\u001b", new Object[]{"zze", "zzf", "zzg", zzfm.class, "zzh", zzgf.class});
        }
        if (i2 == 3) {
            return new zzgd();
        }
        zzff zzffVar = null;
        if (i2 == 4) {
            return new zzgc(zzffVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }

    public final List<zzgf> zzm() {
        return this.zzh;
    }

    public final List<Long> zzn() {
        return this.zze;
    }
}
