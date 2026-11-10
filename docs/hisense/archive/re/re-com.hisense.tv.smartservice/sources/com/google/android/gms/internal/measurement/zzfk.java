package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zzfk extends zzjx<zzfk, zzfj> implements zzld {
    public static final zzfk zza;
    public int zze;
    public int zzf;
    public zzgd zzg;
    public zzgd zzh;
    public boolean zzi;

    static {
        zzfk zzfkVar = new zzfk();
        zza = zzfkVar;
        zzjx.zza.put(zzfk.class, zzfkVar);
    }

    public static zzfj zzb() {
        return zza.zzbu();
    }

    public static /* synthetic */ void zzf(zzfk zzfkVar, int i) {
        zzfkVar.zze |= 1;
        zzfkVar.zzf = i;
    }

    public static /* synthetic */ void zzg(zzfk zzfkVar, zzgd zzgdVar) {
        zzgdVar.getClass();
        zzfkVar.zzg = zzgdVar;
        zzfkVar.zze |= 2;
    }

    public static /* synthetic */ void zzh(zzfk zzfkVar, zzgd zzgdVar) {
        zzfkVar.zzh = zzgdVar;
        zzfkVar.zze |= 4;
    }

    public static /* synthetic */ void zzi(zzfk zzfkVar, boolean z) {
        zzfkVar.zze |= 8;
        zzfkVar.zzi = z;
    }

    public final int zza() {
        return this.zzf;
    }

    public final zzgd zzd() {
        zzgd zzgdVar = this.zzg;
        return zzgdVar == null ? zzgd.zza : zzgdVar;
    }

    public final zzgd zze() {
        zzgd zzgdVar = this.zzh;
        return zzgdVar == null ? zzgd.zza : zzgdVar;
    }

    public final boolean zzj() {
        return this.zzi;
    }

    public final boolean zzk() {
        return (this.zze & 1) != 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzjx
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzjx.zzbF(zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001င\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဇ\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
        }
        if (i2 == 3) {
            return new zzfk();
        }
        zzff zzffVar = null;
        if (i2 == 4) {
            return new zzfj(zzffVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }

    public final boolean zzm() {
        return (this.zze & 8) != 0;
    }

    public final boolean zzn() {
        return (this.zze & 4) != 0;
    }
}
