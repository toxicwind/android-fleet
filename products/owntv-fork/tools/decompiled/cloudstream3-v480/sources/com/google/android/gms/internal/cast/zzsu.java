package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzsu extends zzxi implements zzym {
    private static final zzsu zzb;
    private int zzd;
    private zzxn zze = zzD();
    private zzxn zzf = zzD();
    private zztt zzg;

    static {
        zzsu zzsuVar = new zzsu();
        zzb = zzsuVar;
        zzxi.zzJ(zzsu.class, zzsuVar);
    }

    private zzsu() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0002\u0000\u0001\u001b\u0002\u001b\u0003ဉ\u0000", new Object[]{"zzd", "zze", zzuj.class, "zzf", zzra.class, "zzg"});
        }
        if (i2 == 3) {
            return new zzsu();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzst(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
