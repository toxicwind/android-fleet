package com.google.android.gms.internal.measurement;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzgo extends zzjx<zzgo, zzgn> implements zzld {
    public static final zzgo zza;
    public int zze;
    public zzke<zzgt> zzf = zzjx.zzbA();
    public zzgk zzg;

    static {
        zzgo zzgoVar = new zzgo();
        zza = zzgoVar;
        zzjx.zza.put(zzgo.class, zzgoVar);
    }

    public final zzgk zza() {
        zzgk zzgkVar = this.zzg;
        return zzgkVar == null ? zzgk.zza : zzgkVar;
    }

    public final List<zzgt> zzc() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzjx
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzjx.zzbF(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002ဉ\u0000", new Object[]{"zze", "zzf", zzgt.class, "zzg"});
        }
        if (i2 == 3) {
            return new zzgo();
        }
        if (i2 == 4) {
            return new zzgn(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zza;
    }
}
