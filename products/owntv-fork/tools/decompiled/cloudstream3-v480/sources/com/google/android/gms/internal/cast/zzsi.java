package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzsi extends zzxi implements zzym {
    private static final zzsi zzb;
    private int zzd;
    private long zze;
    private zzxm zzf = zzC();
    private zzxm zzg = zzC();

    static {
        zzsi zzsiVar = new zzsi();
        zzb = zzsiVar;
        zzxi.zzJ(zzsi.class, zzsiVar);
    }

    private zzsi() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0002\u0000\u0001စ\u0000\u0002\u0017\u0003\u0017", new Object[]{"zzd", "zze", "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzsi();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzsh(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
