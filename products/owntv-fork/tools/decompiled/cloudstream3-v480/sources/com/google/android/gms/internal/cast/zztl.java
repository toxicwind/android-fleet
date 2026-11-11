package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zztl extends zzxi implements zzym {
    private static final zztl zzb;
    private int zzd;
    private int zze;
    private int zzf;

    static {
        zztl zztlVar = new zztl();
        zzb = zztlVar;
        zzxi.zzJ(zztl.class, zztlVar);
    }

    private zztl() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001", new Object[]{"zzd", "zze", zznl.zza(), "zzf", zznl.zza()});
        }
        if (i2 == 3) {
            return new zztl();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zztk(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
