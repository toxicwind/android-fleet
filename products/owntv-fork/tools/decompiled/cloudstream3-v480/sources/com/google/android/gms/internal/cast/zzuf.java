package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzuf extends zzxi implements zzym {
    private static final zzuf zzb;
    private int zzd;
    private int zze;
    private boolean zzf;
    private zzxn zzg = zzD();
    private zzxn zzh = zzD();

    static {
        zzuf zzufVar = new zzuf();
        zzb = zzufVar;
        zzxi.zzJ(zzuf.class, zzufVar);
    }

    private zzuf() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0002\u0000\u0001᠌\u0000\u0002ဇ\u0001\u0003\u001b\u0004\u001b", new Object[]{"zzd", "zze", zznz.zza(), "zzf", "zzg", zzub.class, "zzh", zzud.class});
        }
        if (i2 == 3) {
            return new zzuf();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzue(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
