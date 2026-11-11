package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzrg extends zzxi implements zzym {
    private static final zzrg zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private zzxn zzh = zzD();

    static {
        zzrg zzrgVar = new zzrg();
        zzb = zzrgVar;
        zzxi.zzJ(zzrg.class, zzrgVar);
    }

    private zzrg() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002\u0004\u001b", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", zzrf.class});
        }
        if (i2 == 3) {
            return new zzrg();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzrd(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
