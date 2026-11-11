package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzrm extends zzxi implements zzym {
    private static final zzrm zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private zzxl zzg = zzB();
    private zzxl zzh = zzB();
    private zzxn zzi = zzxi.zzD();
    private zzxn zzj = zzxi.zzD();
    private int zzk;

    static {
        zzrm zzrmVar = new zzrm();
        zzb = zzrmVar;
        zzxi.zzJ(zzrm.class, zzrmVar);
    }

    private zzrm() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0004\u0000\u0001င\u0000\u0002᠌\u0001\u0003\u0016\u0004\u0016\u0005\u001a\u0006\u001a\u0007᠌\u0002", new Object[]{"zzd", "zze", "zzf", zzmf.zza(), "zzg", "zzh", "zzi", "zzj", "zzk", zzlx.zza()});
        }
        if (i2 == 3) {
            return new zzrm();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzrl(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
