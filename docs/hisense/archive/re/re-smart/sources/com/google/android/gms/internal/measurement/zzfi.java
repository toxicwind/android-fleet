package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zzfi extends zzjx<zzfi, zzfh> implements zzld {
    public static final zzfi zza;
    public int zze;
    public String zzf = "";
    public String zzg = "";
    public String zzh = "";
    public String zzi = "";
    public String zzj = "";
    public String zzk = "";
    public String zzl = "";

    static {
        zzfi zzfiVar = new zzfi();
        zza = zzfiVar;
        zzjx.zza.put(zzfi.class, zzfiVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzjx
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzjx.zzbF(zza, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဈ\u0006", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl"});
        }
        if (i2 == 3) {
            return new zzfi();
        }
        if (i2 == 4) {
            return new zzfh(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
