package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzun extends zzxi implements zzym {
    private static final zzun zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzi;
    private boolean zzk;
    private boolean zzl;
    private zzxn zzg = zzD();
    private zzxn zzh = zzD();
    private zzxl zzj = zzB();

    static {
        zzun zzunVar = new zzun();
        zzb = zzunVar;
        zzxi.zzJ(zzun.class, zzunVar);
    }

    private zzun() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0003\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003\u001b\u0004\u001b\u0005᠌\u0002\u0006ࠬ\u0007ဇ\u0003\bဇ\u0004", new Object[]{"zzd", "zze", zzot.zza(), "zzf", zzov.zza(), "zzg", zzrc.class, "zzh", zzrc.class, "zzi", zzlx.zza(), "zzj", zzot.zza(), "zzk", "zzl"});
        }
        if (i2 == 3) {
            return new zzun();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzum(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
