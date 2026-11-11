package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzsa extends zzxi implements zzym {
    private static final zzsa zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";
    private boolean zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;

    static {
        zzsa zzsaVar = new zzsa();
        zzb = zzsaVar;
        zzxi.zzJ(zzsa.class, zzsaVar);
    }

    private zzsa() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဇ\u0002\u0004᠌\u0003\u0005᠌\u0004\u0006᠌\u0005\u0007᠌\u0006", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", zzmp.zza(), "zzi", zzmt.zza(), "zzj", zzmr.zza(), "zzk", zzmv.zza()});
        }
        if (i2 == 3) {
            return new zzsa();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzrz(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
