package com.google.android.gms.internal.measurement;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzgk extends zzjx<zzgk, zzgj> implements zzld {
    public static final zzgk zza;
    public zzke<zzgm> zze = zzjx.zzbA();

    static {
        zzgk zzgkVar = new zzgk();
        zza = zzgkVar;
        zzjx.zza.put(zzgk.class, zzgkVar);
    }

    public static zzgk zzc() {
        return zza;
    }

    public final int zza() {
        return this.zze.size();
    }

    public final List<zzgm> zzd() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.measurement.zzjx
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzjx.zzbF(zza, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zze", zzgm.class});
        }
        if (i2 == 3) {
            return new zzgk();
        }
        if (i2 == 4) {
            return new zzgj(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
