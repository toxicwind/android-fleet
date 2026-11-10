package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zzfe extends zzjx<zzfe, zzfd> implements zzld {
    public static final zzfe zza;
    public int zze;
    public String zzf = "";
    public String zzg = "";

    static {
        zzfe zzfeVar = new zzfe();
        zza = zzfeVar;
        zzjx.zza.put(zzfe.class, zzfeVar);
    }

    public final String zzb() {
        return this.zzf;
    }

    public final String zzc() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.measurement.zzjx
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzjx.zzbF(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zze", "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzfe();
        }
        if (i2 == 4) {
            return new zzfd(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
