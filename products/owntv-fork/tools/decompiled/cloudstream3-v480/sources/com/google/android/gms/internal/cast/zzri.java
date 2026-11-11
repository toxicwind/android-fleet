package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzri extends zzxi implements zzym {
    private static final zzri zzb;
    private int zzd;
    private boolean zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private zzsg zzi;
    private int zzj;
    private boolean zzk;

    static {
        zzri zzriVar = new zzri();
        zzb = zzriVar;
        zzxi.zzJ(zzri.class, zzriVar);
    }

    private zzri() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဇ\u0000\u0002᠌\u0001\u0003᠌\u0002\u0004᠌\u0003\u0005ဉ\u0004\u0006᠌\u0005\u0007ဇ\u0006", new Object[]{"zzd", "zze", "zzf", zzlx.zza(), "zzg", zzmb.zza(), "zzh", zzkz.zza(), "zzi", "zzj", zzlz.zza(), "zzk"});
        }
        if (i2 == 3) {
            return new zzri();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzrh(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
