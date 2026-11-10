package com.google.android.gms.internal.measurement;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzgf extends zzjx<zzgf, zzge> implements zzld {
    public static final zzgf zza;
    public int zze;
    public int zzf;
    public zzkd zzg = zzjx.zzby();

    static {
        zzgf zzgfVar = new zzgf();
        zza = zzgfVar;
        zzjx.zza.put(zzgf.class, zzgfVar);
    }

    public static zzge zzd() {
        return zza.zzbu();
    }

    public static /* synthetic */ void zzg(zzgf zzgfVar, int i) {
        zzgfVar.zze |= 1;
        zzgfVar.zzf = i;
    }

    public static /* synthetic */ void zzh(zzgf zzgfVar, Iterable iterable) {
        zzkd zzkdVar = zzgfVar.zzg;
        if (!zzkdVar.zzc()) {
            zzgfVar.zzg = zzjx.zzbz(zzkdVar);
        }
        zzih.zzbq(iterable, zzgfVar.zzg);
    }

    public final int zza() {
        return this.zzg.size();
    }

    public final int zzb() {
        return this.zzf;
    }

    public final long zzc(int i) {
        return this.zzg.zza(i);
    }

    public final List<Long> zzf() {
        return this.zzg;
    }

    public final boolean zzi() {
        return (this.zze & 1) != 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzjx
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzjx.zzbF(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001င\u0000\u0002\u0014", new Object[]{"zze", "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzgf();
        }
        zzff zzffVar = null;
        if (i2 == 4) {
            return new zzge(zzffVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
