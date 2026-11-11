package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzut extends zzxi implements zzym {
    private static final zzut zzb;
    private int zzd;
    private String zze = "";
    private long zzf;
    private long zzg;
    private zzuv zzh;

    static {
        zzut zzutVar = new zzut();
        zzb = zzutVar;
        zzxi.zzJ(zzut.class, zzutVar);
    }

    private zzut() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဉ\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh"});
        }
        if (i2 == 3) {
            return new zzut();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzus(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
