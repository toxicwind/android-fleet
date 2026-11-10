package com.google.android.gms.internal.measurement;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzgm extends zzjx<zzgm, zzgl> implements zzld {
    public static final zzgm zza;
    public int zze;
    public String zzf = "";
    public zzke<zzgt> zzg = zzjx.zzbA();

    static {
        zzgm zzgmVar = new zzgm();
        zza = zzgmVar;
        zzjx.zza.put(zzgm.class, zzgmVar);
    }

    public final String zzb() {
        return this.zzf;
    }

    public final List<zzgt> zzc() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.measurement.zzjx
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzjx.zzbF(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဈ\u0000\u0002\u001b", new Object[]{"zze", "zzf", "zzg", zzgt.class});
        }
        if (i2 == 3) {
            return new zzgm();
        }
        if (i2 == 4) {
            return new zzgl(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
