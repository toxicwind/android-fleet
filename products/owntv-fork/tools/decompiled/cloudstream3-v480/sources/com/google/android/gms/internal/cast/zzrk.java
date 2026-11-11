package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzrk extends zzxi implements zzym {
    private static final zzrk zzb;
    private int zzd;
    private boolean zzf;
    private boolean zzg;
    private zzur zzh;
    private boolean zzi;
    private long zzk;
    private long zzl;
    private String zze = "";
    private zzxl zzj = zzB();

    static {
        zzrk zzrkVar = new zzrk();
        zzb = zzrkVar;
        zzxi.zzJ(zzrk.class, zzrkVar);
    }

    private zzrk() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဇ\u0001\u0003ဉ\u0003\u0004ဇ\u0004\u0005ࠬ\u0006ဇ\u0002\u0007ဂ\u0005\bဂ\u0006", new Object[]{"zzd", "zze", "zzf", "zzh", "zzi", "zzj", zzox.zza(), "zzg", "zzk", "zzl"});
        }
        if (i2 == 3) {
            return new zzrk();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzrj(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
