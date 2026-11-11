package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zztn extends zzxi implements zzym {
    private static final zztn zzb;
    private int zzd;
    private zzrc zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private long zzj;
    private zzxn zzk = zzD();

    static {
        zztn zztnVar = new zztn();
        zzb = zztnVar;
        zzxi.zzJ(zztn.class, zztnVar);
    }

    private zztn() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0001\u0000\u0001ဉ\u0000\u0002᠌\u0001\u0003᠌\u0002\u0004᠌\u0003\u0005᠌\u0004\u0006ဂ\u0005\u0007\u001b", new Object[]{"zzd", "zze", "zzf", zznp.zza(), "zzg", zznn.zza(), "zzh", zzlx.zza(), "zzi", zzlb.zza(), "zzj", "zzk", zzrc.class});
        }
        if (i2 == 3) {
            return new zztn();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zztm(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
