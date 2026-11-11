package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzur extends zzxi implements zzym {
    private static final zzur zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzh;
    private long zzj;
    private zzxl zzg = zzB();
    private zzxn zzi = zzD();

    static {
        zzur zzurVar = new zzur();
        zzb = zzurVar;
        zzxi.zzJ(zzur.class, zzurVar);
    }

    private zzur() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0006\u0000\u0001\u0001\u0007\u0006\u0000\u0002\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003ࠞ\u0005᠌\u0002\u0006\u001b\u0007ဂ\u0003", new Object[]{"zzd", "zze", zzoz.zza(), "zzf", zzlx.zza(), "zzg", zzox.zza(), "zzh", zzll.zza(), "zzi", zzup.class, "zzj"});
        }
        if (i2 == 3) {
            return new zzur();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzuq(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
