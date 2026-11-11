package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzqq extends zzxi implements zzym {
    private static final zzqq zzb;
    private int zzd;
    private int zze;
    private int zzf;

    static {
        zzqq zzqqVar = new zzqq();
        zzb = zzqqVar;
        zzxi.zzJ(zzqq.class, zzqqVar);
    }

    private zzqq() {
    }

    public static zzqp zza() {
        return (zzqp) zzb.zzy();
    }

    static /* synthetic */ void zzd(zzqq zzqqVar, int i) {
        zzqqVar.zzd |= 2;
        zzqqVar.zzf = i;
    }

    static /* synthetic */ void zze(zzqq zzqqVar, int i) {
        zzqqVar.zze = i - 1;
        zzqqVar.zzd |= 1;
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001᠌\u0000\u0002င\u0001", new Object[]{"zzd", "zze", zzop.zza(), "zzf"});
        }
        if (i2 == 3) {
            return new zzqq();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzqp(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
