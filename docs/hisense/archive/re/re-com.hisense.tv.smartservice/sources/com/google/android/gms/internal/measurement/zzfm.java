package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zzfm extends zzjx<zzfm, zzfl> implements zzld {
    public static final zzfm zza;
    public int zze;
    public int zzf;
    public long zzg;

    static {
        zzfm zzfmVar = new zzfm();
        zza = zzfmVar;
        zzjx.zza.put(zzfm.class, zzfmVar);
    }

    public static zzfl zzc() {
        return zza.zzbu();
    }

    public static /* synthetic */ void zze(zzfm zzfmVar, int i) {
        zzfmVar.zze |= 1;
        zzfmVar.zzf = i;
    }

    public static /* synthetic */ void zzf(zzfm zzfmVar, long j) {
        zzfmVar.zze |= 2;
        zzfmVar.zzg = j;
    }

    public final int zza() {
        return this.zzf;
    }

    public final long zzb() {
        return this.zzg;
    }

    public final boolean zzg() {
        return (this.zze & 2) != 0;
    }

    public final boolean zzh() {
        return (this.zze & 1) != 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzjx
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzjx.zzbF(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002ဂ\u0001", new Object[]{"zze", "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzfm();
        }
        zzff zzffVar = null;
        if (i2 == 4) {
            return new zzfl(zzffVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
