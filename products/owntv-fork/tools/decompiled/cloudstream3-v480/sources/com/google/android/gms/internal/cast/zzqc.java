package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzqc extends zzxi implements zzym {
    private static final zzqc zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private boolean zzh;
    private int zzi;
    private int zzj;
    private boolean zzk;

    static {
        zzqc zzqcVar = new zzqc();
        zzb = zzqcVar;
        zzxi.zzJ(zzqc.class, zzqcVar);
    }

    private zzqc() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003᠌\u0002\u0004ဇ\u0003\u0005င\u0004\u0006င\u0005\u0007ဇ\u0006", new Object[]{"zzd", "zze", zznl.zza(), "zzf", zznh.zza(), "zzg", zznj.zza(), "zzh", "zzi", "zzj", "zzk"});
        }
        if (i2 == 3) {
            return new zzqc();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzqb(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
