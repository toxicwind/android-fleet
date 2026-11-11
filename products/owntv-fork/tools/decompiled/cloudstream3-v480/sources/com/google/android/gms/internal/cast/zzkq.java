package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzkq extends zzxi implements zzym {
    private static final zzkq zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private zzkm zzi;
    private int zzj;

    static {
        zzkq zzkqVar = new zzkq();
        zzb = zzkqVar;
        zzxi.zzJ(zzkq.class, zzkqVar);
    }

    private zzkq() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001င\u0000\u0002᠌\u0001\u0003င\u0002\u0004င\u0003\u0005ဉ\u0004\u0006᠌\u0005", new Object[]{"zzd", "zze", "zzf", zzkp.zza, "zzg", "zzh", "zzi", "zzj", zzko.zza});
        }
        if (i2 == 3) {
            return new zzkq();
        }
        zzkr zzkrVar = null;
        if (i2 == 4) {
            return new zzkn(zzkrVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
