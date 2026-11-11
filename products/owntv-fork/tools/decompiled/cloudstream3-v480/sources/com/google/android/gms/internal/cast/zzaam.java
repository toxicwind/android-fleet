package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzaam extends zzxi implements zzym {
    private static final zzaam zzb;
    private int zzd;
    private long zze;
    private long zzf;
    private int zzg;

    static {
        zzaam zzaamVar = new zzaam();
        zzb = zzaamVar;
        zzxi.zzJ(zzaam.class, zzaamVar);
    }

    private zzaam() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဂ\u0001\u0003င\u0002", new Object[]{"zzd", "zze", "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzaam();
        }
        zzaal zzaalVar = null;
        if (i2 == 4) {
            return new zzaak(zzaalVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
