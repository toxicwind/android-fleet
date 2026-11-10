package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zzfu extends zzjx<zzfu, zzft> implements zzld {
    public static final zzfu zza;
    public int zze;
    public String zzf = "";
    public String zzg = "";
    public zzfi zzh;

    static {
        zzfu zzfuVar = new zzfu();
        zza = zzfuVar;
        zzjx.zza.put(zzfu.class, zzfuVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzjx
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzjx.zzbF(zza, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဉ\u0002", new Object[]{"zze", "zzf", "zzg", "zzh"});
        }
        if (i2 == 3) {
            return new zzfu();
        }
        if (i2 == 4) {
            return new zzft(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
