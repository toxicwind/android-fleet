package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzul extends zzxi implements zzym {
    private static final zzul zzb;
    private int zzd;
    private int zze;
    private zzxn zzf = zzD();
    private zzxn zzg = zzD();
    private int zzh;

    static {
        zzul zzulVar = new zzul();
        zzb = zzulVar;
        zzxi.zzJ(zzul.class, zzulVar);
    }

    private zzul() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0002\u0000\u0001᠌\u0000\u0002\u001b\u0003\u001b\u0004င\u0001", new Object[]{"zzd", "zze", zzor.zza(), "zzf", zzso.class, "zzg", zzso.class, "zzh"});
        }
        if (i2 == 3) {
            return new zzul();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzuk(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
