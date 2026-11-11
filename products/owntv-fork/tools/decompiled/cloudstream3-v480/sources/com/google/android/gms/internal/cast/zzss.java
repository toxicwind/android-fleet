package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzss extends zzxi implements zzym {
    private static final zzss zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;

    static {
        zzss zzssVar = new zzss();
        zzb = zzssVar;
        zzxi.zzJ(zzss.class, zzssVar);
    }

    private zzss() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001င\u0000\u0002᠌\u0001\u0003င\u0002", new Object[]{"zzd", "zze", "zzf", zznf.zza(), "zzg"});
        }
        if (i2 == 3) {
            return new zzss();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzsr(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
