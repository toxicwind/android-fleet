package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzzy extends zzxi implements zzym {
    private static final zzzy zzb;
    private int zzd;
    private zzaaj zze;
    private int zzf;
    private int zzg;

    static {
        zzzy zzzyVar = new zzzy();
        zzb = zzzyVar;
        zzxi.zzJ(zzzy.class, zzzyVar);
    }

    private zzzy() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002᠌\u0001\u0003᠌\u0002", new Object[]{"zzd", "zze", "zzf", zzzw.zza, "zzg", zzzv.zza});
        }
        if (i2 == 3) {
            return new zzzy();
        }
        zzzx zzzxVar = null;
        if (i2 == 4) {
            return new zzzu(zzzxVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
