package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zzfq extends zzjx<zzfq, zzfp> implements zzld {
    public static final zzfq zza;
    public int zze;
    public String zzf = "";
    public long zzg;

    static {
        zzfq zzfqVar = new zzfq();
        zza = zzfqVar;
        zzjx.zza.put(zzfq.class, zzfqVar);
    }

    public static zzfp zza() {
        return zza.zzbu();
    }

    public static /* synthetic */ void zzc(zzfq zzfqVar, String str) {
        str.getClass();
        zzfqVar.zze |= 1;
        zzfqVar.zzf = str;
    }

    public static /* synthetic */ void zzd(zzfq zzfqVar, long j) {
        zzfqVar.zze |= 2;
        zzfqVar.zzg = j;
    }

    @Override // com.google.android.gms.internal.measurement.zzjx
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzjx.zzbF(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001", new Object[]{"zze", "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzfq();
        }
        zzff zzffVar = null;
        if (i2 == 4) {
            return new zzfp(zzffVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
