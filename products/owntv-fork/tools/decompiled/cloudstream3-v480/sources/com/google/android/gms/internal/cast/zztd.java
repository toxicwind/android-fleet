package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zztd extends zzxi implements zzym {
    private static final zztd zzb;
    private int zzd;
    private int zze;

    static {
        zztd zztdVar = new zztd();
        zzb = zztdVar;
        zzxi.zzJ(zztd.class, zztdVar);
    }

    private zztd() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001᠌\u0000", new Object[]{"zzd", "zze", zztc.zza});
        }
        if (i2 == 3) {
            return new zztd();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zztb(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
