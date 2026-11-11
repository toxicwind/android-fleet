package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzud extends zzxi implements zzym {
    private static final zzud zzb;
    private int zzd;
    private int zze;
    private long zzf;
    private zzxn zzg = zzD();
    private zzxn zzh = zzD();
    private zzxn zzi = zzD();

    static {
        zzud zzudVar = new zzud();
        zzb = zzudVar;
        zzxi.zzJ(zzud.class, zzudVar);
    }

    private zzud() {
    }

    @Override // com.google.android.gms.internal.cast.zzxi
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzG(zzb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0003\u0000\u0001᠌\u0000\u0002ဂ\u0001\u0003\u001b\u0004\u001b\u0005\u001b", new Object[]{"zzd", "zze", zznx.zza(), "zzf", "zzg", zztv.class, "zzh", zzqg.class, "zzi", zzub.class});
        }
        if (i2 == 3) {
            return new zzud();
        }
        zzuw zzuwVar = null;
        if (i2 == 4) {
            return new zzuc(zzuwVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        throw null;
    }
}
