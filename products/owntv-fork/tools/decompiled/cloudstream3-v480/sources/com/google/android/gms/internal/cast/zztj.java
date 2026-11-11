package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zztj extends zzxi implements zzym {
    private static final zztj zzb;
    private int zzd;
    private zzaag zze;
    private zzxn zzf = zzxi.zzD();
    private zzxn zzg = zzD();
    private zzxn zzh = zzD();
    private String zzi = "";
    private long zzj;
    private int zzk;

    static {
        zztj zztjVar = new zztj();
        zzb = zztjVar;
        zzxi.zzJ(zztj.class, zztjVar);
    }

    private zztj() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0003\u0000\u0001ဉ\u0000\u0002\u001a\u0003\u001b\u0004\u001b\u0005ဈ\u0001\u0006ဂ\u0002\u0007᠌\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", zzpr.class, "zzh", zzta.class, "zzi", "zzj", "zzk", zzpy.zza});
        }
        if (i2 == 3) {
            return new zztj();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzti(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
